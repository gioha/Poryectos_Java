/**
 * 
 */
package mx.ine.computosINE.as.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ine.common.enums.EnumEstatusModulo;
import mx.ine.common.helper.HLPAdministracionInterface;
import mx.ine.common.ws.administracion.dto.response.DTODetalleDistritoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.as.ASAdministradorSistemaInterface;

/**
 * @author INE
 *
 */
@Service("asAdmin")
@Scope("prototype")
public class ASAdministradorSistemaImpl implements ASAdministradorSistemaInterface{
	
	@Autowired
    @Qualifier("hlpAdministracion")
    private HLPAdministracionInterface hlpAdmin;

	/**
	    * {@inheritDoc}
	    */
	    @Override
	    public List<DTODetalleProcesoWS> obtenerDetallesProceso(Integer idSistema,
	            String vigente, Integer anio, Integer idEstado, Integer idDistrito)
	            throws ClienteWebServiceException {
	        return hlpAdmin.obtenerDetallesProceso(idSistema, vigente, anio, idEstado, idDistrito);
	    }

	    /**
	    * {@inheritDoc}
	    */
	    @Override
	    public List<DTODetalleEstadoProcesoWS> obtenerEstadosDestalle(
	            Integer idSistema, Integer idProceso, Integer idDetalle)
	            throws ClienteWebServiceException {
	        return hlpAdmin.obtenerEstadosDestalle(idSistema, idProceso, idDetalle);
	    }

	    /**
	    * {@inheritDoc}
	    */
	    @Override
	    public List<DTODetalleDistritoProcesoWS> obtenerDistritosDetalle(
	            Integer idSistema, Integer idProceso, Integer idDetalle,
	            Integer idEstado) throws ClienteWebServiceException {
	        return hlpAdmin.obtenerDistritosDetalle(idSistema, idProceso, idDetalle, idEstado);
	    }

		/* (non-Javadoc)
		 * @see mx.ine.computosINE.as.ASAdministradorSistemaInterface#obtenerMunicipiosDetalle(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
		 */
		@Override
		public List<DTODetalleMunicipioProcesoWS> obtenerMunicipiosDetalle(
				Integer idSistema, Integer idProceso, Integer idDetalle,
				Integer idEstado) throws ClienteWebServiceException {
			return hlpAdmin.obtenerMunicipiosDetalle(idSistema, idProceso, idDetalle, idEstado);
		}

		/* (non-Javadoc)
		 * @see mx.ine.computosINE.as.ASAdministradorSistemaInterface#obtenEstatusModuloPorMunicipio(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
		 */
		@Override
		public EnumEstatusModulo obtenEstatusModuloPorMunicipio(
				Integer idProceso, Integer idDetalle, Integer idSistema,
				Integer idEstado, Integer idMunicipio, String grupo,
				Integer idModulo) throws ClienteWebServiceException {
			return hlpAdmin.obtenEstatusModuloPorMunicipio(idProceso, idDetalle, idSistema, idEstado, 
					idMunicipio, grupo, idModulo);
		}
}
