 /**
 * @(#)MBRecuentoTotal.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.bsd.BSDRecuentoTotalInterface;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;
import mx.org.ine.servicios.exception.ApplicationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

 /**
 * Clase controlador para el módulo de recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
public class MBRecuentoTotal extends MBGeneric implements Serializable{
    /**
     * Elemento para la serialización de los objetos generados por esta clase
     */
    private static final long serialVersionUID = -4261238663578329305L;
    /**
     * Elemento para generar log
     */
    private static final Log LOGGER = LogFactory.getLog(MBRecuentoTotal.class);
    
    @Autowired
    @Qualifier("mbAdmin")
    private MBAdministradorSistema mbAdmin;
    
    @Autowired
    @Qualifier("bsdRecuentoTotal")
    private transient BSDRecuentoTotalInterface bsd;
    
    /**
     * DTO del módulo
     */
    private FormRecuentoTotal dto;
    /**
     * Constante del ámbito federal
     */
    private static final int AMBITO_FED = 1;
    /**
     * Constante del ámbito local
     */
    private static final int AMBITO_LOC = 2;
    /**
     * Variable para saber si se selecciona el check de todos
     */
    private boolean seleccionaTodo;
    
    /**
     * Método llamado desde el flow encargado de inicializar los valores a mostrar 
     * en la vista del módulo de recuento total
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    public void inicializa(){
        dto = new FormRecuentoTotal();
        dto.setUsuario(mbAdmin.getDto().getUsuario());
        obtenAmbito();
        cargaCandidaturas();
    }
    
    /**
     * Método encargado de actualizar la información del recuento
     * 
     * @return String : "captura"-Todo bien, ""-Algo falló
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    public String guardar(){
        try{
            bsd.guardar(dto);
        }catch(Exception e){
            LOGGER.info("MBRecuentoTotal - guardar()", e);
            agregaMensaje(TipoMensaje.ERROR_MENSAJE, 
                    Utilidades.mensajeProperties("mensaje_recuento_total_error_guardar"));
            return "";
        }
        agregaMensaje(TipoMensaje.INFO_MENSAJE, 
                Utilidades.mensajeProperties("mensaje_recuento_total_ok_guardar"));
        return "captura";
    }
    
    /**
     * Método activado al seleccionar un tipo de candidatura del combo
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    public void seleccionaCandidatura(){
        //Limpia
        dto.setListaMunicipio(null);
        dto.setIdMunicipio(null);
        dto.setListaGeografia(null);
        dto.setListaGeografiaSelect(null);
        dto.setCandidatura(null);
        //localiza
        localizaCandidatura();
        //Solo para regidurias carga municipios
        if(dto.getIdCandidatura().intValue() == 
                Constantes.ID_TIPO_CAND_REGIDURIA_MR){
            cargaMunicipio();
        }else{
            cargaGeografia();
        }
    }
    
    /**
     * Método activado al seleccionar un municipio del combo
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    public void seleccionaMunicipio(){
        //Limpia
        dto.setListaGeografia(null);
        dto.setListaGeografiaSelect(null);
        //Carga
        cargaGeografia();
    }
    
    /**
     * Método activado al seleccionar el checkbox en el encabezado de la
     * columna
     * 
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    public void seleccionaTodo(){
        if(seleccionaTodo){
            //Quita todo
            seleccionaTodo = false;
            dto.setListaGeografiaSelect(
                    new ArrayList<HLPEntornoGeografico>());
        }else{
            //Agrega todos
            seleccionaTodo = true;
            dto.setListaGeografiaSelect(
                    new ArrayList<HLPEntornoGeografico>());
            for(HLPEntornoGeografico a : dto.getListaGeografia()){
                if(!a.getConRecuento()){
                    dto.getListaGeografiaSelect().add(a);
                }
            }
        }
    }
    
    /**
     * Método encargado de obtener la lista de municipios
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void cargaMunicipio(){
        try{
            dto.setListaMunicipio(bsd.obtenGeografia(dto));
        }catch(ApplicationException e){
            LOGGER.info("MBRecuentoTotal - cargaMunicipio() : Evento al cargar los municipios");
            mostrarMensaje(e.getMessage(), e.getCodigoError());
        }
    }
    
    /**
     * Método encargado de obtener la lista de distritos, municipio, demarcaciones, etc. 
     * Dependiendo el entorno geográfico de la candidatura
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void cargaGeografia(){
        try{
            dto.setListaGeografia(bsd.obtenGeografia(dto));
            //Si en Gobernador llenar lista para habilitar boton aceptar
            if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_GOBERNADOR){
            	List<HLPEntornoGeografico> lista = new ArrayList<>();
            	lista.add(dto.getListaGeografia().get(0));
            	dto.setListaGeografiaSelect(lista);
            }
        }catch(ApplicationException e){
            LOGGER.info("MBRecuentoTotal - cargaGeografia()) : Evento al cargar la geografia");
            mostrarMensaje(e.getMessage(), e.getCodigoError());
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
                    dto.getUsuario().getIdEstadoSeleccionado(), ambito));
        }catch(ApplicationException e){
            LOGGER.info("MBRecuentoTotal - cargaCandidaturas() : Evento al obtener las candidaturas");
            mostrarMensaje(e.getMessage(), e.getCodigoError());
        }
    }
    
    /**
     * Método encargado de localizar el objeto de la candidatura
     * seleccionada
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void localizaCandidatura(){
        //Localiza
        for(DTOCandidatoWS a : dto.getListaCandidaturas()){
            if(a.getIdTipoCandidatura().intValue() 
                    == dto.getIdCandidatura().intValue()){
                dto.setCandidatura(a);
                break;
            }
        }
    }
    
    /**
     * Método encargado de obtener el ambito del detalle
     * 
     * @author José Antonio López Torres 
     * @since 12/05/2017
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
     * @return FormRecuentoTotal : valor que tiene el atributo dto
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public FormRecuentoTotal getDto() {
        return dto;
    }

    /**
     * Método que ingresa el valor de el atributo dto
     *
     * @param dto : valor que ingresa a el atributo dto
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setDto(FormRecuentoTotal dto) {
        this.dto = dto;
    }

    /**
     * Método que obtiene el valor de el atributo seleccionaTodo
     *
     * @return boolean : valor que tiene el atributo seleccionaTodo
     * 
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public boolean isSeleccionaTodo() {
        return seleccionaTodo;
    }

    /**
     * Método que ingresa el valor de el atributo seleccionaTodo
     *
     * @param seleccionaTodo : valor que ingresa a el atributo seleccionaTodo
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void setSeleccionaTodo(boolean seleccionaTodo) {
        this.seleccionaTodo = seleccionaTodo;
    }
}
