 /**
 * @(#)ASRecuentoTotalInterface.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.as;

import java.util.List;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.dto.reportes.DTOReporteRecuento;
import mx.org.ine.servicios.exception.ApplicationException;

 /**
 * Interface que establece los métodos de servicio para el módulo y reporte de 
 * recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
public interface ASRecuentoTotalInterface {
    /**
     * Método encargado de obtener la lista de candidaturas
     * 
     * @param idDetalle : Id detalle proceso
     * @param idEstado : Id estado
     * @param ambito : 1=federal, 2=local
     * 
     * @return List<DTOCandidatoWS> : Lista de candidaturas
     * 
     * @throws ApplicationException : En caso de error
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    List<DTOCandidatoWS> obtenTipoCandidaturas(
            Integer idDetalle, Integer idEstado, Integer ambito) throws ApplicationException;
    /**
     * Método encargado de obtener la geografía dependiendo el entorno geográfico del 
     * tipo de candidatura
     * 
     * @param dto : DTO del módulo
     * 
     * @return List<HLPEntornoGeografico> : Lista de geografía
     * 
     * @throws ApplicationException : En caso de error
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    List<HLPEntornoGeografico> obtenGeografia(FormRecuentoTotal dto) throws ApplicationException;
    /**
     * Método encargado de actualizar y eliminar registros de las distintas tablas
     * 
     * @param dto : DTO módulo
     * 
     * @throws ApplicationException : En caso de error
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    public void guardar(FormRecuentoTotal dto);
    /**
     * Método encargado de obtener la lista de candidaturas
     * 
     * @param idDetalle : Id detalle proceso
     * @param idEstado : Id estado
     * @param ambito : 1=federal, 2=local
     * @param version : OC, JL o JM
     * 
     * @return List<DTOCandidatoWS> : Lista de candidaturas
     * 
     * @throws ApplicationException : En caso de error
     *
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    List<DTOCandidatoWS> obtenTipoCandidaturas(
            Integer idDetalle, Integer idEstado, Integer ambito, String version) throws ApplicationException;
    /**
     * Método encargado de obtener la información del reporte
     * 
     * @param dto : DTO del reporte
     * 
     * @return List<DTOReporteRecuento> : Información del reporte
     * 
     * @throws ApplicationException : En caso de error
     *
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    List<DTOReporteRecuento> generaReporte(FormReporteRecuento dto) throws ApplicationException;
}
