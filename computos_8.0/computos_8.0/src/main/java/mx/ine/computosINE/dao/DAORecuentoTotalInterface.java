 /**
 * @(#)DAORecuentoTotalInterface.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.dao;

import java.io.Serializable;
import java.util.List;

import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.dto.reportes.DTOReporteRecuento;

 /**
 * Interface que establece los métodos de acceso a las base de datos 
 * para el móduloy reporte de recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
public interface DAORecuentoTotalInterface extends DAOGenericInterface<Serializable, Serializable>{
    /**
     * Método encargado de obtener la geografía dependiendo el entorno geográfico del 
     * tipo de candidatura
     * 
     * @param dto : DTO del módulo
     * @param remplaza : Lista con partes a remplazar
     *                        List[0] : <!REM_SELECT>,
     *                        List[1] : <!REM_GENERAL>,
     *                        List[2] : <!REM_WHERE>,
     *                        List[3] : <!REM_ORDER_GROUP>,
     *                        List[4] : <!REM_JOIN_AB>,
     *                        List[5] : <!REM_JOIN_AC>
     * 
     * @return List<HLPEntornoGeografico> : Lista de geografía
     *
     * @author José Antonio López Torres 
     * @since 12/05/2017
     */
    List<HLPEntornoGeografico> obtenGeografia(FormRecuentoTotal dto, List<String> remplaza);
    /**
     * Método encargado de obtener la geografía que ya cuento con recuento total dependiendo
     * el entorno del tipo de candidatura
     * 
     * @param dto : DTO del módulo
     * @param remplaza : Lista con partes a remplazar
     *                        List[0] : <!REM_SELECT>,
     *                        List[1] : <!REM_GENERAL>,
     *                        List[2] : <!REM_WHERE>,
     *                        List[3] : <!REM_ORDER_GROUP>,
     *                        List[4] : <!REM_JOIN_AB>,
     *                        List[5] : <!REM_JOIN_AC>
     * 
     * @return List<HLPEntornoGeografico> : Lista de geografía
     *
     * @author José Antonio López Torres 
     * @since 05/06/2017
     */
    List<HLPEntornoGeografico> obtenGeografiaConRecuento(FormRecuentoTotal dto, List<String> remplaza);
    /**
     * Método encargado de actualizar y eliminar los registros de las tablas
     * para el recuento
     * 
     * @param dto : DTO del módulo
     * @param remplaza : where del query
     * @param tabla : tabla 
     * 
     * @return int : num de registros eliminados
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    int ejecutaDelete(FormRecuentoTotal dto, String remplaza, String tabla);
    /**
     * Método encargado de actualizar la tabla de ACTA_CASILLA_VOTOS
     * 
     * @param dto : DTO del módulo
     * @param remplaza : where del query
     * 
     * @return int : num de registros actualizados
     *
     * @author José Antonio López Torres 
     * @since 15/05/2017
     */
    int ejecutaUpdate(FormRecuentoTotal dto, String remplaza);
    /**
     * Método encargado de obtener la infromación del reporte
     * 
     * @param dto : DTO del reporte
     * @param remplaza Lista con partes a remplazar
     *                        List[0] : <!REM_SELECT>,
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
     * 
     * @return List<DTOReporteRecuento> : Lista de información
     *
     * @author José Antonio López Torres 
     * @since 22/05/2017
     */
    List<DTOReporteRecuento> obtenReporte(FormReporteRecuento dto, List<String> remplaza);
    
}
