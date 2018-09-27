 /**
 * @(#)ASRecuentoTotalImpl.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.as.impl;

import java.util.List;

import mx.ine.common.helper.HLPCandidatosInterface;
import mx.ine.common.helper.HLPGeograficosInterface;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.common.ws.geografico.dto.response.DTOMunicipiosWS;
import mx.ine.computosINE.as.ASRecuentoTotalInterface;
import mx.ine.computosINE.bo.BORecuentoTotalInterface;
import mx.ine.computosINE.dao.DAORecuentoTotalInterface;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.dto.reportes.DTOReporteRecuento;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;
import mx.org.ine.servicios.exception.ApplicationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 /**
 * Clase que establece la implementación de la interface de servicio para el módulo y reporte de 
 * recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
@Service("asRecuentoTotal")
@Scope("prototype")
public class ASRecuentoTotalImpl implements ASRecuentoTotalInterface{
    
    /**
     * Elemento para generar log
     */
    private static final Log LOGGER = LogFactory.getLog(ASRecuentoTotalImpl.class);
    
    @Autowired
    @Qualifier("boRecuentoTotal")
    private BORecuentoTotalInterface bo;
    
    @Autowired
    @Qualifier("daoRecuentoTotal")
    private DAORecuentoTotalInterface dao;
    
    @Autowired
    @Qualifier("hlpCandidatos")
    private HLPCandidatosInterface hlpCandidatos;
    
    @Autowired
    @Qualifier("hlpGeograficos")
    private HLPGeograficosInterface hlpGeo;

    /**
    * {@inheritDoc}
    */
    @Override
    public List<DTOCandidatoWS> obtenTipoCandidaturas(Integer idDetalle,
            Integer idEstado, Integer ambito) throws ApplicationException {
        List<DTOCandidatoWS> listaCandidaturas = null;
        try{
            //Obtener candidaturas
            //TODO Cambiar la forma de obtener las candidaturas para
            //que sea consumiendo el servicio y no en duro
            listaCandidaturas = bo.obtenTipoCandidaturas();
        }catch(Exception e){
            LOGGER.error("Error ASRecuentoTotalImpl - obtenTipoCandidaturas()", e);
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_recuento_total_error_candidaturas"), 
                    Constantes.CODIGO_ERROR);
        }
        //Si no hay candidaturas
        if(listaCandidaturas == null || CollectionUtils.isEmpty(listaCandidaturas)){
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_recuento_total_no_candidaturas"), 
                    Constantes.CODIGO_WARN);
        }
        return listaCandidaturas;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public List<HLPEntornoGeografico> obtenGeografia(FormRecuentoTotal dto)
            throws ApplicationException {
        //Obtener entorno candidatura
        int entorno = obtenEntorno(dto);
        //Lista
        List<HLPEntornoGeografico> geografia = null;
        List<HLPEntornoGeografico> conRecuento = null;
        try{
            //Remplaza
            List<String> remplaza = bo.obtenRemplazaQuery(entorno, dto);
            //Obten lista
            geografia = dao.obtenGeografia(dto, remplaza);
            //Obten lista con recuento
            conRecuento = dao.obtenGeografiaConRecuento(dto, remplaza);
        }catch(Exception e){
            LOGGER.error("Error ASRecuentoTotalImpl - obtenGeografia()", e);
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_recuento_total_error_geografia"), 
                    Constantes.CODIGO_ERROR);
        }
        //No existen registros
        if(geografia == null || CollectionUtils.isEmpty(geografia)){
            throw new ApplicationException(
                    obtenMensajeNoGeografia(entorno, dto.getIdMunicipio()), 
                    Constantes.CODIGO_WARN);
        }
        try{
            if(conRecuento != null && CollectionUtils.isNotEmpty(conRecuento)){
                //Filtra
                filtraConRecuento(geografia, conRecuento);
            }
            //Obten nombres
            obtenNombreGeografia(geografia, entorno, dto);
        }catch(Exception e){
            LOGGER.error("Error ASRecuentoTotalImpl - obtenGeografia()", e);
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_recuento_total_error_geografia"), 
                    Constantes.CODIGO_ERROR);
        }
        return geografia;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void guardar(FormRecuentoTotal dto){
        //Obtener entorno candidatura
        int entorno = obtenEntorno(dto);
        String remplazaDeletePK = bo.obtenRemplazaQueryDeletePK(entorno, dto);
        String remplazaDeleteID = bo.obtenRemplazaQueryDeleteID(entorno, dto);
        String remplazaUpdate = bo.obtenRemplazaQueryUpdate(entorno, dto);
        int num = 0;
        //Deletes
        num = dao.ejecutaDelete(dto, remplazaDeletePK, Constantes.REPRESENTANTES);
        LOGGER.info("ASRecuentoTotal - Eliminados REPRESENTANTES: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeletePK, Constantes.CONSEJEROS);
        LOGGER.info("ASRecuentoTotal - Eliminados CONSEJEROS: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeletePK, Constantes.ACTA_TIPO_CANDIDATURA);
        LOGGER.info("ASRecuentoTotal - Eliminados ACTA_TIPO_CANDIDATURA: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeleteID, Constantes.DISTRIBUCION_TOT_PARCIAL);
        LOGGER.info("ASRecuentoTotal - Eliminados DISTRIBUCION_TOT_PARCIAL: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeleteID, Constantes.DISTRIBUCION_TOTALES);
        LOGGER.info("ASRecuentoTotal - Eliminados DISTRIBUCION_TOTALES: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeleteID, Constantes.DISTRIBUCION_PARTIDOS_CI);
        LOGGER.info("ASRecuentoTotal - Eliminados DISTRIBUCION_PARTIDOS_CI: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeleteID, Constantes.DISTRIBUCION_CANDIDATOS);
        LOGGER.info("ASRecuentoTotal - Eliminados DISTRIBUCION_CANDIDATOS: " + num);
        num = dao.ejecutaDelete(dto, remplazaDeleteID, Constantes.DISTRIBUCION_CAND_PARCIAL);
        LOGGER.info("ASRecuentoTotal - Eliminados DISTRIBUCION_CAND_PARCIAL: " + num);
        //Update
        num = dao.ejecutaUpdate(dto, remplazaUpdate);
        LOGGER.info("ASRecuentoTotal - Actualizados: " + num);
        LOGGER.info("ASRecuentoTotal - guardar() : Se eliminaron y actualizaron todos los registros del recuento total.");
    }
    
    /**
     * Método encargado de obtener el entorno geográfico dependiendo  
     * el tipo de candidatura
     * 
     * @param dto : DTO del módulo
     * @return int : entorno
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    private int obtenEntorno(FormRecuentoTotal dto){
        int entorno = Constantes.ENTORNO_ESTADO;
        if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR){
            entorno = Constantes.ENTORNO_DISTRITO;
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_AYUNTAMIENTO){
            entorno = Constantes.ENTORNO_MUNICIPIO;
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_MR){
            //Cargar primero municipio y se valida si el valor de idMunicipio es nulo
            //de lo contrario carga demarcaciones 
            if(dto.getIdMunicipio() == null){
                entorno = Constantes.ENTORNO_MUNICIPIO;
            }else{
                entorno = Constantes.ENTORNO_DEMARCACION;
            }
        }
        return entorno;
    }
    
    /**
     * Método encargado de obtener el nombre del estado, distrito, municipio, etc. 
     * de la lista obteneida dependiendo el entorno geográfico
     * 
     * @param geografia : Lista de registros
     * @param entorno : entorno geográfico
     * @param dto : DTO del módulo
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void obtenNombreGeografia(List<HLPEntornoGeografico> geografia, 
            int entorno, FormRecuentoTotal dto) throws Exception{
        try{
            if(entorno == Constantes.ENTORNO_DISTRITO){
                List<DTODistritosWS> distritos = hlpGeo.obtenerCatalogoDistritos(
                        dto.getUsuario().getIdEstadoSeleccionado(), dto.getAmbito());
                obtenNombresDistritos(geografia, distritos);
                dto.setTituloTabla(Utilidades.mensajeProperties("etiqueta_recuento_total_distritos"));
            }else if(entorno == Constantes.ENTORNO_MUNICIPIO){
                List<DTOMunicipiosWS> municipios = hlpGeo.obtenerCatalogoMunicipios(
                        dto.getUsuario().getIdEstadoSeleccionado(), dto.getAmbito());
                obtenNombresMunicipios(geografia, municipios);
                dto.setTituloTabla(Utilidades.mensajeProperties("etiqueta_recuento_total_municpios"));
            }else if(entorno == Constantes.ENTORNO_DEMARCACION){
                List<DTOListaRegiduriasWS> regidurias = hlpGeo.regiduriasPorMunicipio(
                        dto.getUsuario().getIdEstadoSeleccionado(), 
                        dto.getIdMunicipio());
                obtenNombresRegidurias(geografia, regidurias);
                dto.setTituloTabla(Utilidades.mensajeProperties("etiqueta_recuento_total_regidurias"));
            }
        }catch(ClienteWebServiceException e){
            LOGGER.error("Error ASRecuentoTotalImpl - obtenNombreGeografia()", e);
            throw new Exception("Error ASRecuentoTotalImpl - obtenNombreGeografia()");
        }
    }
    
    /**
     * Método encargado de obtener los nombres de los distritos
     * 
     * @param geografia : Lista de geografía
     * @param distritos : Lista de distritos
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void obtenNombresDistritos(List<HLPEntornoGeografico> geografia, 
            List<DTODistritosWS> distritos){
        for(DTODistritosWS a : distritos){
            for(HLPEntornoGeografico b : geografia){
                if(a.getIdDistrito().intValue() == b.getId().intValue()){
                    b.setNombre(a.getNombreDistrito());
                    break;
                }
            }
        }
    }
    
    /**
     * Método encargado de obtener los nombres de los municipios
     * 
     * @param geografia : Lista de geografía
     * @param municipios : Lista de municipios
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void obtenNombresMunicipios(List<HLPEntornoGeografico> geografia, 
            List<DTOMunicipiosWS> municipios){
        for(DTOMunicipiosWS a : municipios){
            for(HLPEntornoGeografico b : geografia){
                if(a.getIdMunicipio().intValue() == b.getId().intValue()){
                    b.setNombre(a.getNombreMunicipio());
                    break;
                }
            }
        }
    }
    
    /**
     * Método encargado de obtener los nombres de las regidurias
     * 
     * @param geografia : Lista de geografía
     * @param regidurias : Lista de regidurias
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private void obtenNombresRegidurias(List<HLPEntornoGeografico> geografia, 
            List<DTOListaRegiduriasWS> regidurias){
        for(DTOListaRegiduriasWS a : regidurias){
            for(HLPEntornoGeografico b : geografia){
                if(a.getIdRegiduria().intValue() == b.getId().intValue()){
                    b.setNombre(a.getNombreRegiduria());
                    break;
                }
            }
        }
    }
    
    /**
     * Método encargado de obtener el mensaje, dependiendo el entorno cuando,  
     * no existen registros de geografia
     * 
     * @return String : mensaje
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    private String obtenMensajeNoGeografia(int entorno, Integer idMunicipio){
        String mensaje = "";
        if(entorno == Constantes.ENTORNO_ESTADO){
            mensaje = Utilidades.mensajeProperties(
                    "mensaje_recuento_total_no_recuento_estado");
        }else if(entorno == Constantes.ENTORNO_DISTRITO){
            mensaje = Utilidades.mensajeProperties(
                    "mensaje_recuento_total_no_recuento_distrito");
        }else if(entorno == Constantes.ENTORNO_MUNICIPIO){
            mensaje = Utilidades.mensajeProperties(
                    "mensaje_recuento_total_no_recuento_municipio");
        }else if(entorno == Constantes.ENTORNO_DEMARCACION){
            //validar municipio para saber si se estan cargando los municipios
            //o las demarcaciones
            if(idMunicipio == null){
                mensaje = Utilidades.mensajeProperties(
                        "mensaje_recuento_total_no_recuento_municipio");
            }else{
                mensaje = Utilidades.mensajeProperties(
                        "mensaje_recuento_total_no_recuento_regidurias");
            }
        }
        return mensaje;
    }
    
    /**
     * Método encargado de validar que geografia ya cuenta con recuento
     * 
     * @param geografia : Lista de geografia
     * @param conRecuento : Lista de geografia en recuento
     *
     * @author José Antonio López Torres 
     * @since 05/06/2017
     */
    private void filtraConRecuento(List<HLPEntornoGeografico> geografia, List<HLPEntornoGeografico> conRecuento){
        for(HLPEntornoGeografico geo : geografia){
            for(HLPEntornoGeografico con : conRecuento){
                if(geo.getId().intValue() == con.getId().intValue()){
                    geo.setConRecuento(true);
                    break;
                }
            }
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<DTOCandidatoWS> obtenTipoCandidaturas(Integer idDetalle,
            Integer idEstado, Integer ambito, String version)
            throws ApplicationException {
        List<DTOCandidatoWS> listaCandidaturas = null;
        try{
            //Obtener candidaturas
            //TODO Cambiar la forma de obtener las candidaturas para
            //que sea consumiendo el servicio y no en duro
            listaCandidaturas = bo.obtenTipoCandidaturas();
            bo.filtarCandidaturasReporte(listaCandidaturas, version);
        }catch(Exception e){
            LOGGER.error("Error ASRecuentoTotalImpl - obtenTipoCandidaturas()", e);
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_recuento_total_error_candidaturas"), 
                    Constantes.CODIGO_ERROR);
        }
        //Si no hay candidaturas
        if(listaCandidaturas == null || CollectionUtils.isEmpty(listaCandidaturas)){
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_recuento_total_no_candidaturas"), 
                    Constantes.CODIGO_WARN);
        }
        return listaCandidaturas;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public List<DTOReporteRecuento> generaReporte(FormReporteRecuento dto)
            throws ApplicationException {
        //Obtener entorno candidatura
        int entorno = obtenEntornoReporte(dto);
        //Lista
        List<DTOReporteRecuento> info = null;
        try{
            //Remplaza
            List<String> remplaza = bo.obtenRemplazaQueryReporte(entorno, dto);
            //Obten lista
            info = dao.obtenReporte(dto, remplaza);
        }catch(Exception e){
            LOGGER.error("Error ASRecuentoTotalImpl - generaReporte()", e);
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_reporte_recuento_total_error"), 
                    Constantes.CODIGO_ERROR);
        }
        //No existen registros
        if(info == null || CollectionUtils.isEmpty(info)){
            throw new ApplicationException(
                    Utilidades.mensajeProperties("mensaje_reporte_recuento_total_no_info"), 
                    Constantes.CODIGO_WARN);
        }
        return info;
    }
    
    /**
     * Método encargado de obtener el entorno geográfico dependiendo  
     * el tipo de candidatura
     * 
     * @param dto : DTO del módulo
     * @return int : entorno
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    private int obtenEntornoReporte(FormRecuentoTotal dto){
        int entorno = Constantes.ENTORNO_ESTADO;
        if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR){
            entorno = Constantes.ENTORNO_DISTRITO;
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_AYUNTAMIENTO){
            entorno = Constantes.ENTORNO_MUNICIPIO;
        }else if(dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_MR){
            entorno = Constantes.ENTORNO_DEMARCACION;
        }
        return entorno;
    }
}
