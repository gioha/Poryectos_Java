 /**
 * @(#)BORecuentoTotalInterface.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.bo;

import java.util.List;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;

 /**
 * Interface que establece los métodos de negocio para el módulo y reporte de 
 * recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
public interface BORecuentoTotalInterface {
    /**
     * Método encargado de obtener la lista de tipos de
     * candidaturas en duro
     * 
     * @return List<DTOCandidatoWS> : Tipos de candidaturas
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    List<DTOCandidatoWS> obtenTipoCandidaturas();
    /**
     * Método encargado de obtener las partes query a remplazar dependiendo
     * el tipo de candidatura
     * 
     * @param entorno : Entorno geográfico
     * @param dto : dto del módulo
     * 
     * @return List<String> : List[0] : <!REM_SELECT>,
     *                        List[1] : <!REM_GENERAL>,
     *                        List[2] : <!REM_WHERE>,
     *                        List[3] : <!REM_ORDER_GROUP>,
     *                        List[4] : <!REM_JOIN_AB>,
     *                        List[5] : <!REM_JOIN_AC>
     *                        
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    List<String> obtenRemplazaQuery(Integer entorno, FormRecuentoTotal dto);
    /**
     * Método encargado de obtener la parte del where del query a remplazar
     * dependiendo el entorno y tipo de candidatura
     * 
     * @param entorno : Entorno geográfico
     * @param dto : DTO módulo
     * 
     * @return String : parte del query
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    String obtenRemplazaQueryDeletePK(Integer entorno, FormRecuentoTotal dto);
    /**
     * Método encargado de obtener la parte del where del query a remplazar
     * dependiendo el entorno y tipo de candidatura
     * 
     * @param entorno : Entorno geográfico
     * @param dto : DTO módulo
     * 
     * @return String : parte del query
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    String obtenRemplazaQueryDeleteID(Integer entorno, FormRecuentoTotal dto);
    /**
     * Método encargado de obtener la parte del where del query a remplazar
     * dependiendo el entorno y tipo de candidatura
     * 
     * @param entorno : Entorno geográfico
     * @param dto : DTO módulo
     * 
     * @return String : parte del query
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    String obtenRemplazaQueryUpdate(Integer entorno, FormRecuentoTotal dto);
    /**
     * Método encargado de filtrar las candidaturas dependiendo 
     * la versión del sistema al entrar al reporte
     * 
     * @param listaCandidaturas : Lista de candidaturas
     * @param version : OC, JL o JM
     *
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    void filtarCandidaturasReporte(List<DTOCandidatoWS> listaCandidaturas, String version);
    /**
     * Método encargado de obtener las partes query a remplazar dependiendo
     * el tipo de candidatura
     * 
     * @param entorno : Entorno geográfico
     * @param dto : DTO reporte
     * 
     * @return List<String> : List[0] : <!REM_SELECT>,
     *                        List[1] : <!REM_GENERAL>,
     *                        List[2] : <!REM_ORDER_GROUP>,
     *                        List[3] : <!REM_WHERE>,
     *                        List[4] : <!REM_JOIN_AB>,
     *                        List[5] : <!REM_JOIN_AC>,
     *                        List[6] : <!REM_JOIN_GEO_B>
     *                        List[7] : <!REM_JOIN_GEO_C>,
     *                        List[8] : <!REM_WHERE_GEO>,
     *                        List[9] : <!REM_ORDER_GEO>,
     *                        List[10] : <!REM_TABLA_GEO>
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    List<String> obtenRemplazaQueryReporte(Integer entorno, FormReporteRecuento dto);
}
