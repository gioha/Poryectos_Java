/**
 * @(#)BSDBandejaSeguimiento.java 07/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaSeguimiento")
@Scope("prototype")
public class BSDBandejaSeguimiento implements BSDBandejaSeguimientoInterface{

	@Autowired
	@Qualifier("asBandejaSeguimiento")
	private ASBandejaSeguimientoInterface asBandejaSeguimientoInterface;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarPersonaXCuenta(java.lang.String)
	 */
	@Override
	public DTOEstructuraAreasEntity consultarPersonaXCuenta(String cuentaINE) {
		return asBandejaSeguimientoInterface.consultarPersonaXCuenta(cuentaINE);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarComentariosNoLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOComentariosNoLeidos> consultarComentariosNoLeidos(DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity persona) throws Exception {
		return asBandejaSeguimientoInterface.consultarComentariosNoLeidos(dtoDocumentoEntity, persona);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarComentariosLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOComentariosNoLeidos> consultarComentariosLeidos(DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity persona)throws Exception {
		return asBandejaSeguimientoInterface.consultarComentariosLeidos(dtoDocumentoEntity,persona);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#leerComentarios(java.util.List)
	 */
	@Override
	public void leerComentarios(List<DTOComentariosNoLeidos> listaComentarios) {
		asBandejaSeguimientoInterface.leerComentarios(listaComentarios);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#notificacionBandejaBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity, boolean)
	 */
	@Override
	public void notificacionBandejaBorradores(DTOEstructuraAreasEntity persona, DTOEnviadosDocumentosEntity enviado, boolean activar) {
		asBandejaSeguimientoInterface.notificacionBandejaEnviados(persona, enviado, activar);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarNotificacionesBEntrada(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public int consultarNotificacionesBEntrada(DTOEstructuraAreasEntity persona) {
		return asBandejaSeguimientoInterface.consultarNotificacionesBEntrada(persona);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarNotificacionesBBorradores(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public int consultarNotificacionesBBorradores(DTOEstructuraAreasEntity persona) {
		return asBandejaSeguimientoInterface.consultarNotificacionesBBorradores(persona);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarNotificacionesBEnviados(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public int consultarNotificacionesBEnviados(DTOEstructuraAreasEntity persona) {
		return asBandejaSeguimientoInterface.consultarNotificacionesBEnviados(persona);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#consultarAnexos(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> consultarAnexos(DTODocumentoEntity dtoDocumentoEntity) {
		
		return asBandejaSeguimientoInterface.consultarAnexos(dtoDocumentoEntity);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#obtenerRemitentes(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> obtenerRemitentes(DTODocumentoEntity documento) throws Exception {
		return asBandejaSeguimientoInterface.obtenerRemitentes(documento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#obtenerPersonasCCP(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> obtenerPersonasCCP(DTODocumentoEntity documento) throws Exception {
		return asBandejaSeguimientoInterface.obtenerPersonasCCP(documento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface#obtenerDestinatarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoDestinatarioEntity> obtenerDestinatarios(DTODocumentoEntity documento) throws Exception {
		return asBandejaSeguimientoInterface.obtenerDestinatarios(documento);
	}
	

}
