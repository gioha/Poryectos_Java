package mx.ine.computosINE.bsd;

import java.util.List;

import mx.ine.common.enums.EnumEstatusModulo;
import mx.ine.common.ws.administracion.dto.response.DTODetalleDistritoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;

/**
 * Interfaz que permite el manejo de datos obtenidos de servicios del esquema de administración
 * @author Mayra Victoria
 * @updatedBy José Antonio López Torres
 * @since 22/08/2016
 *
 */
public interface BSDAdministradorSistemaInterface{
	/**
     * Método encagardo de obtener la lista de detalles proceso electoral dependiendo de los parámetros
     *
     * @param idSistema  : Id del sistema
     * @param vigente    : S-vigentes N-no vigentes (Puede ser nulo)
     * @param anio       : Año (Puede ser nulo)
     * @param idEstado   : Id estado (Puede ser nulo)
     * @param idDistrito : Id distrito (Puede ser nulo)
     *
     * @return List<DTODetalleProcesoWS> : Lista de detalles proceso
     *
     * @throws ClienteWebServiceException En caso de ocurrir un error durante la conexi&oacute;n con el webservice.
     * @author José Antonio López Torres
     * @since 07/02/2017
     */
    List<DTODetalleProcesoWS> obtenerDetallesProceso(Integer idSistema, String vigente, Integer anio, Integer idEstado,
            Integer idDistrito) throws ClienteWebServiceException;

    /**
     * Método encargado de obtener la lista de estados involucrados en el proceso electoral
     *
     * @param idSistema : Id del sistema
     * @param idProceso : Id proceso electoral
     * @param idDetalle : Id detalle proceso electoral (Puede ser nulo)
     *
     * @return List<DTODetalleEstadoProcesoWS> : Lista de estados
     *
     * @throws ClienteWebServiceException : En caso de ocurrir un error durante la conexi&oacute;n con el webservice.
     * @author José Antonio López Torres
     * @since 07/02/2017
     */
    List<DTODetalleEstadoProcesoWS> obtenerEstadosDetalle(Integer idSistema, Integer idProceso, Integer idDetalle)
            throws ClienteWebServiceException;

    /**
     * Método encargado de obtener la lista de distritos invlucrados en el estado y proceso electoral
     *
     * @param idSistema : Id del sistema
     * @param idProceso : Id proceso electoral
     * @param idDetalle : Id detalle proceso electoral (puede ser nulo)
     * @param idEstado  : Id estado
     *
     * @return List<DTODetalleDistritoProcesoWS> : Lista de distritos
     *
     * @throws ClienteWebServiceException : En caso de ocurrir un error durante la conexi&oacute;n con el webservice.
     * @author José Antonio López Torres
     * @since 07/02/2017
     */
    List<DTODetalleDistritoProcesoWS> obtenerDistritosDetalle(Integer idSistema, Integer idProceso, Integer idDetalle,
            Integer idEstado) throws ClienteWebServiceException;
    /**
     * Método utilizado para obtener la información de los módulos a mostrar 
     * en el menú según los datos geográficos seleccionados o cargados por el usuario
     * 
     * @param parametros
     * @return String : respuesta json
     */
    public String generaMenuLateral(Integer idProceso, Integer idDetalle, Integer idSistema,
            Integer idEstado, Integer idMunicipio, String grupo) throws ClienteWebServiceException;
    
    /**
     * Método utilizado para obtener las acciones de un módulo seleccionado
     * 
     * @param parametros
     * @return String : respuesta json
     */
    public String generaMenuAcciones(Integer idProceso, Integer idDetalle, Integer idSistema,
            Integer idEstado, Integer idMunicipio, String grupo) throws ClienteWebServiceException;
    
    /**
     * Método encargado de obtener la lista de municipos invlucrados en el estado y proceso electoral
     *
     * @param idSistema : Id del sistema
     * @param idProceso : Id proceso electoral
     * @param idDetalle : Id detalle proceso electoral (puede ser nulo)
     * @param idEstado  : Id estado
     *
     * @return List<DTODetalleDistritoProcesoWS> : Lista de municipios
     *
     * @throws ClienteWebServiceException : En caso de ocurrir un error durante la conexi&oacute;n con el webservice.
     * @author Pablo Zuñiga Mata
     * @since 07/02/2017
     */
    List<DTODetalleMunicipioProcesoWS> obtenerMunicipiosDetalle(Integer idSistema, Integer idProceso, Integer idDetalle,
            Integer idEstado) throws ClienteWebServiceException;
    
    /**
     * Método encargado de obtener el estatus del modulo
     * 
     * @param idProceso : Id proceso electoral
     * @param idDetalle : Id detalle proceso
     * @param idSistema : Id sistema
     * @param idEstado : Id estado
     * @param idMunicipio : Id municipio
     * @param grupo : Grupo
     * @param idModulo : Id modulo
     * 
     * @return EnumEstatusModulo : estatus
     * 
     * @throws ClienteWebServiceException : En caso de ocurrir un error durante la conexi&oacute;n con el webservice.
     *
     * @author Pablo Zuñiga Mata
     * @since 16/03/2017
     */
    EnumEstatusModulo obtenEstatusModuloPorMunicipio(Integer idProceso, Integer idDetalle, Integer idSistema, 
    		Integer idEstado, Integer idMunicipio, String grupo, Integer idModulo) 
            throws ClienteWebServiceException;
}
