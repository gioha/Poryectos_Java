/**
 * @(#)BSDAdministradorSistemaImpl.java 07/02/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.bsd.impl;

import java.util.List;

import mx.ine.common.enums.EnumEstatusModulo;
import mx.ine.common.ws.administracion.dto.response.DTODetalleDistritoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.as.ASAdministradorSistemaInterface;
import mx.ine.computosINE.as.ASMenuServiceInterface;
import mx.ine.computosINE.bsd.BSDAdministradorSistemaInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa los métodos para obtener datos de servicios de
 * administración
 *
 * @author Mayra Victoria
 * @since 09/09/16
 */
@Component("bsdAdmin")
@Scope("prototype")
public class BSDAdministradorSistemaImpl implements BSDAdministradorSistemaInterface {
    
    @Autowired
    @Qualifier("asAdmin")
    private ASAdministradorSistemaInterface asAdmin;

    @Autowired
    @Qualifier("asMenu")
    private transient ASMenuServiceInterface asMenu;
    
    /**
     * {@inheritDoc}
     */
     @Override
     public List<DTODetalleProcesoWS> obtenerDetallesProceso(Integer idSistema,
             String vigente, Integer anio, Integer idEstado, Integer idDistrito)
             throws ClienteWebServiceException {
         return asAdmin.obtenerDetallesProceso(idSistema, vigente, anio, idEstado, idDistrito);
     }

     /**
     * {@inheritDoc}
     */
     @Override
     public List<DTODetalleEstadoProcesoWS> obtenerEstadosDetalle(
             Integer idSistema, Integer idProceso, Integer idDetalle)
             throws ClienteWebServiceException {
         return asAdmin.obtenerEstadosDestalle(idSistema, idProceso, idDetalle);
     }

     /**
     * {@inheritDoc}
     */
     @Override
     public List<DTODetalleDistritoProcesoWS> obtenerDistritosDetalle(
             Integer idSistema, Integer idProceso, Integer idDetalle,
             Integer idEstado) throws ClienteWebServiceException {
         return asAdmin.obtenerDistritosDetalle(idSistema, idProceso, idDetalle, idEstado);
     }

     /**
     * {@inheritDoc}
     */
    @Override
    public String generaMenuLateral(Integer idProceso, Integer idDetalle, Integer idSistema,
            Integer idEstado, Integer idMunicipio, String grupo) throws ClienteWebServiceException {
        return asMenu.generaMenuLateral(idProceso, idDetalle, idSistema, idEstado, idMunicipio, grupo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generaMenuAcciones(Integer idProceso, Integer idDetalle, Integer idSistema,
            Integer idEstado, Integer idMunicipio, String grupo) throws ClienteWebServiceException {
            return asMenu.generaMenuAcciones(idProceso, idDetalle, idSistema, idEstado, idMunicipio, grupo);
    }

     /**
      * {@inheritDoc}
      */
      @Override
	public List<DTODetalleMunicipioProcesoWS> obtenerMunicipiosDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle,
			Integer idEstado) throws ClienteWebServiceException {
		return asAdmin.obtenerMunicipiosDetalle(idSistema, idProceso, idDetalle, idEstado);
	}

    /**
    * {@inheritDoc}
    */
 	@Override
	public EnumEstatusModulo obtenEstatusModuloPorMunicipio(Integer idProceso,
			Integer idDetalle, Integer idSistema, Integer idEstado,
			Integer idMunicipio, String grupo, Integer idModulo)
			throws ClienteWebServiceException {
		return asAdmin.obtenEstatusModuloPorMunicipio(idProceso, idDetalle, idSistema, idEstado, idMunicipio, grupo, idModulo);
	}
}
