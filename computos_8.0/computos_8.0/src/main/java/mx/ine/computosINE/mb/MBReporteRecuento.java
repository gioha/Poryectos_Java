 /**
 * @(#)MBReporteRecuento.java 22/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.computosINE.bsd.BSDRecuentoTotalInterface;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.helper.HLPExcel;
import mx.ine.computosINE.helper.HLPPDFExporter;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;
import mx.org.ine.servicios.exception.ApplicationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

 /**
 * Clase controlador para el reporte de recuento total
 * 
 * @author José Antonio López Torres
 * @since 22/05/2017
 * @copyright Direccion de sistemas - INE
 */
public class MBReporteRecuento extends MBGeneric implements Serializable{

    /**
     * Elemento para la serialización de los objetos generados por esta clase
     */
    private static final long serialVersionUID = -3119029299674649817L;
    /**
     * Elemento para generar log
     */
    private static final Log LOGGER = LogFactory.getLog(MBReporteRecuento.class);
    
    @Autowired
    @Qualifier("mbAdmin")
    private MBAdministradorSistema mbAdmin;
    
    @Autowired
    @Qualifier("bsdRecuentoTotal")
    private transient BSDRecuentoTotalInterface bsd;
    
    @Autowired
    @Qualifier("hlpExcel")
    private transient HLPExcel hlpExcel;
        
    /**
     * Form de la vista del reporte
     */
    private FormReporteRecuento dto;
    /**
     * Constante del ámbito federal
     */
    public static final int AMBITO_FED = 1;
    /**
     * Constante del ámbito local
     */
    public static final int AMBITO_LOC = 2;
    /**
     * Numero de columnas del reporte
     */
    public static final Integer NUM_COLUMN = 3;
    /**
     * Parámetros para manipular excel y pdf
     */
    private Map<String, Serializable> parametros;
    /**
     * Data table ligada a la tabla de reporte
     */
    private transient DataTable dataTable;
    
    /**
     * Método llamado desde el flow encargado de inicializar los valores
     * a mostrar en la vista del reporte de recuento total
     * 
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    public void inicializa(){
        dto = new FormReporteRecuento();
        dto.setUsuario(mbAdmin.getDto().getUsuario());
        parametros = new LinkedHashMap<>();
        obtenAmbito();
        obtenVersion();
        cargaCandidaturas();
        parametros.put(Constantes.PARAMETRO_INTEGER_COLUMNAS, NUM_COLUMN);
    }
    
    /**
     * Método encargado de generar el reporte
     * 
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    public void generaReporte(){
        try{
            limpiaTabla();
            generaTituloReporte();
            dto.setListaReporte(bsd.generaReporte(dto));
        }catch(ApplicationException e){
            LOGGER.info("MBReporteRecuento - generaReporte() : Evento al generar reporte");
            mostrarMensaje(e.getMessage(), e.getCodigoError());
        }
    }
    
    /**
     * Método encargado de generar el titulo del reporte
     * 
     * @author José Antonio López Torres 
     * @since 02/06/2017
     */
    private void generaTituloReporte(){
        //Obten titulo reporte
        StringBuilder sb = new StringBuilder();
        sb.append(Utilidades.mensajeProperties("etiqueta_reporte_recuento_titulo"));
        sb.append(" PARA ");
        if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_GOBERNADOR){
            sb.append(Constantes.NOM_GOBERNADOR);
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR){
            sb.append(Constantes.NOM_CORTO_DIPUTADO_MR);
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_AYUNTAMIENTO){
            sb.append(Constantes.NOM_CORTO_AYUNTAMIENTO);
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_MR){
            sb.append(Constantes.NOM_REGIDOR_MR);
        }
        parametros.put(Constantes.PARAMETRO_STRING_TITULO, sb.toString());
        this.dto.setTituloTabla(sb.toString().toUpperCase());
    }
    
    /**
     * Método llamado para manipular el documento Excel
     *
     * @param document : Documento
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void postProcessXLS(Object document) {
        hlpExcel.postProcessXLS(document, parametros);
    }
    
    /**
     * Método encargado de exportar la tabla a formato PDF
     *
     * @throws IOException en caso de ocurrir un error al generar el archivo PDF
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void exportPDF() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Exporter exporter = new HLPPDFExporter(parametros);
        exporter.export(context, dataTable, Utilidades.mensajeProperties("etiqueta_reporte_recuento_titulo"),
                    false, false, "ISO-8859-1", null, null);
        context.responseComplete();
    }
    
    /**
     * Método encargado de limpiar la tabla
     * 
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    private void limpiaTabla(){
        dto.setListaReporte(null);
        UIComponent table = FacesContext.getCurrentInstance().
                getViewRoot().findComponent(":forma:tabla");
        table.setValueExpression("sortBy", null);
    }
    
    /**
     * Método activado al seleccionar un tipo de candidatura del combo
     * 
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    public void seleccionaCandidatura(){
        //Limpia
        dto.setListaReporte(null);
    }
    
    /**
     * Método encargado de obtener el ambito del detalle
     * 
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    private void obtenAmbito(){
        if(dto.getUsuario().getDetalleSeleccionado().getAmbitoDetalle().equals(
                EnumAmbitoDetalleProceso.L.getValor().toString())){
            dto.setAmbito(EnumAmbitoDetalleProceso.L);
        }else{
            dto.setAmbito(EnumAmbitoDetalleProceso.F);
        }
    }
    
    /**
     * Método encargado de obtener la versión del sistema al entrar al reporte
     * 
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    private void obtenVersion(){
        Integer idEstado = dto.getUsuario().getIdEstadoSeleccionado() == null ? 0 : 
                    dto.getUsuario().getIdEstadoSeleccionado();
        Integer idMunicipio = dto.getUsuario().getIdMunicipioSeleccionado() == null ? 0 : 
                    dto.getUsuario().getIdMunicipioSeleccionado();
        if(idEstado.intValue() == 0 && idMunicipio.intValue() == 0){
            dto.setVersion(Constantes.OC);
        }else if(idEstado.intValue() > 0 && idMunicipio.intValue() == 0){
            dto.setVersion(Constantes.JL);
            parametros.put(Constantes.PARAMETRO_OBJECT_ESTADO, 
                    dto.getUsuario().getEstadoSeleccionado());
        }else if(idEstado.intValue() > 0 & idMunicipio.intValue() > 0){
            parametros.put(Constantes.PARAMETRO_OBJECT_ESTADO, 
                    dto.getUsuario().getEstadoSeleccionado());
            parametros.put(Constantes.PARAMETRO_OBJECT_MUNICIPIO, 
                    dto.getUsuario().getMunicipioSeleccionado());
            dto.setVersion(Constantes.JM);
        }
    }
    
    /**
     * Método encargado de cargar la lista de tipos de candidaturas
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void cargaCandidaturas(){
        //Obtener ambito detalle
        int ambito = AMBITO_FED;
        if(dto.getAmbito() == EnumAmbitoDetalleProceso.L){
            ambito = AMBITO_LOC;
        }
        //Obtener candidaturas
        try{
            dto.setListaCandidaturas(bsd.obtenTipoCandidaturas(
                    dto.getUsuario().getIdDetalleProceso(), 
                    dto.getUsuario().getIdEstadoSeleccionado(), 
                    ambito, dto.getVersion()));
        }catch(ApplicationException e){
            LOGGER.info("MBReporteRecuento - cargaCandidaturas() : Evento al obtener las candidaturas");
            mostrarMensaje(e.getMessage(), e.getCodigoError());
        }
    }
    
    /**
     * Método encargado de enviar mensaje a la vista dependiendo la exception
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    private void mostrarMensaje(String mensaje, int codigoError) {
        switch (codigoError) {
            case Constantes.CODIGO_ERROR:
                agregaMensaje(TipoMensaje.ERROR_MENSAJE, mensaje);
                break;
            case Constantes.CODIGO_WARN:
                agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE, mensaje);
                break;
            default:
                agregaMensaje(TipoMensaje.INFO_MENSAJE, mensaje);
                break;
        }
    }

    /**
     * Método que obtiene el valor de el atributo dto
     *
     * @return FormReporteRecuento : valor que tiene el atributo dto
     * 
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public FormReporteRecuento getDto() {
        return dto;
    }

    /**
     * Método que ingresa el valor de el atributo dto
     *
     * @param dto : valor que ingresa a el atributo dto
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void setDto(FormReporteRecuento dto) {
        this.dto = dto;
    }

    /**
     * Método que obtiene el valor de el atributo dataTable
     *
     * @return DataTable : valor que tiene el atributo dataTable
     * 
     * @author José Antonio López Torres
     * @since 23/05/2017
     */
    public DataTable getDataTable() {
        return dataTable;
    }

    /**
     * Método que ingresa el valor de el atributo dataTable
     *
     * @param dataTable : valor que ingresa a el atributo dataTable
     *
     * @author José Antonio López Torres
     * @since 23/05/2017
     */
    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }
}
