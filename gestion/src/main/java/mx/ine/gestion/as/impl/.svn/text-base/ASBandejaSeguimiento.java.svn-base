/**
 * @(#)ASBandejaSeguimiento.java 07/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOComentariosNoLeidosInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface;
import mx.ine.gestion.dao.inter.DAODocumentosCCPInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface;
import mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.util.Utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("asBandejaSeguimiento")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASBandejaSeguimiento implements ASBandejaSeguimientoInterface{

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "ESTRUCTURA_AREA"
	 */
	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "COMENTARIOS_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoComentariosDocumento")
	private DAOComentariosDocumentoInterface daoComentariosDocumentoInterface;
	
	@Autowired
	@Qualifier("daoComentariosNoLeidos")
	private DAOComentariosNoLeidosInterface daoComentariosNoLeidosInterface; 
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "BORRADOR_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoBorradorDocumentos")
	private DAOBorradorDocumentosInterface daoBorradorDocumentosInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "NOTIFICACIONES"
	 */
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "FIRMA_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoEnviadosDocumentos")
	private DAOEnviadosDocumentosInterface daoEnviadosDocumentosInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "DOCUMENTOS_ANEXOS"
	 */
	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoAnexoInterface;
	
	@Autowired
	@Qualifier("daoDocumentosRemitentes")
	private DAODocumentosRemitentesInterface daoDocumentosRemitentesInterface;
	
	@Autowired
	@Qualifier("daoDestinatario")
	private DAODocumentoDestinatarioInterface daoDocumentoDestinatarioInterface;
	
	@Autowired
	@Qualifier("daoDocumentosCCP")
	private DAODocumentosCCPInterface daoDocumentosCCPInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarPersonaXCuenta(java.lang.String)
	 */
	@Override
	public DTOEstructuraAreasEntity consultarPersonaXCuenta(String cuentaINE) {
		return daoEstructuraAreaInterface.consultarPersonaXCuentaActiva(cuentaINE);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarComentariosNoLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public List<DTOComentariosNoLeidos> consultarComentariosNoLeidos(DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity persona) throws Exception{
		revisarComentarios(dtoDocumentoEntity, persona);
		//return daoComentariosDocumentoInterface.consultarComentarios(dtoDocumentoEntity, Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
		return daoComentariosNoLeidosInterface.obtenerComentarios(dtoDocumentoEntity, persona, Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarComentariosLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public List<DTOComentariosNoLeidos> consultarComentariosLeidos(DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity persona) throws Exception{
		revisarComentarios(dtoDocumentoEntity, persona);
		//return daoComentariosDocumentoInterface.consultarComentarios(dtoDocumentoEntity, Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_leido")));
		return daoComentariosNoLeidosInterface.obtenerComentarios(dtoDocumentoEntity, persona, Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_leido")));
	}
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#leerComentarios(java.util.List)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void leerComentarios(List<DTOComentariosNoLeidos> listaComentarios) {
		
		for (DTOComentariosNoLeidos elemento : listaComentarios) {
			
			elemento.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_leido")));
			//.getDtoComentario().setEstatus(Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_leido")));	
			daoComentariosDocumentoInterface.modificar(elemento);
		}
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#notificacionBandejaBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity, boolean)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void notificacionBandejaEnviados(DTOEstructuraAreasEntity persona,
			DTOEnviadosDocumentosEntity enviado, boolean activar) {
		int numNotificacion;
		DTONotificacionesEntity miNotificacion = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
		if(Utilidades.mensajeProperties("id_apartado_bandeja_enviados").equals(""+miNotificacion.getIdApartado3())){
			numNotificacion = miNotificacion.getDocumentosPendientes3();
			if (activar) {
				miNotificacion.setDocumentosPendientes3(++numNotificacion);
				enviado.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")));
			} else if (!activar && numNotificacion > 0) {
				miNotificacion.setDocumentosPendientes3(--numNotificacion);
				enviado.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}else{
				miNotificacion.setDocumentosPendientes3(0);
				enviado.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}
			daoNotificacionesInterface.modificar(miNotificacion);
			daoEnviadosDocumentosInterface.modificar(enviado);
		}else{
//			log.error("La persona con ID: " + persona.getIdPersona() + "no tiene correcto su registro en la tabla notificaciones del ID_APARTADO3, ya que debería ser "+Utilidades.mensajeProperties("id_apartado_bandeja_borradores"));
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarNotificacionesBentrada(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public int consultarNotificacionesBEntrada(DTOEstructuraAreasEntity persona) {
		int cont = 0;
		DTONotificacionesEntity notificacionBanedeja = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt( Utilidades.mensajeProperties("id_modulo_bandeja")));
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_entrada").equals(""+notificacionBanedeja.getIdApartado())){
			cont = notificacionBanedeja.getDocumentosPendientes();
		}
		
		return cont;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarNotificacionesBBorradores(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public int consultarNotificacionesBBorradores(DTOEstructuraAreasEntity persona) {
		int cont = 0;
		DTONotificacionesEntity notificacionBanedeja = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt( Utilidades.mensajeProperties("id_modulo_bandeja")));
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_borradores").equals(""+notificacionBanedeja.getIdApartado2())){
			cont = notificacionBanedeja.getDocumentosPendientes2();
		}
		
		return cont;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarNotificacionesBEnviados(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public int consultarNotificacionesBEnviados(DTOEstructuraAreasEntity persona) {

		int cont = 0;
		DTONotificacionesEntity notificacionBanedeja = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt( Utilidades.mensajeProperties("id_modulo_bandeja")));
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_enviados").equals(""+notificacionBanedeja.getIdApartado3())){
			cont = notificacionBanedeja.getDocumentosPendientes3();
		}
		
		return cont;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#consultarAnexos(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> consultarAnexos(
			DTODocumentoEntity dtoDocumentoEntity) {
		return daoAnexoInterface.buscarPorDocumento(dtoDocumentoEntity);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#obtenerRemitentes(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> obtenerRemitentes(DTODocumentoEntity documento) throws Exception{
		return daoDocumentosRemitentesInterface.obtenerPersonasRemitentes(documento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#obtenerCCP(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> obtenerPersonasCCP(DTODocumentoEntity documento) throws Exception {
		return daoDocumentosCCPInterface.obtenerPersonasCCP(documento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaSeguimientoInterface#obtenerDestinatarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoDestinatarioEntity> obtenerDestinatarios(DTODocumentoEntity documento) throws Exception {
		return daoDocumentoDestinatarioInterface.consutarDocumentosDestinatarios(documento);
	}
	
	private void revisarComentarios(DTODocumentoEntity documento , DTOEstructuraAreasEntity persona) throws Exception{
		DTOComentariosNoLeidos comentarioNoLeido;
		DTOComentariosNoLeidos nuevoComentarioNoLeido;
		List<DTOComentariosDocumentoEntity> comentarios = daoComentariosDocumentoInterface.consultarComentarios(documento);
		List<DTOComentariosNoLeidos> comentariosNoLeidos = daoComentariosNoLeidosInterface.obtenerEstatusComentarios(documento, persona);
		List<DTOComentariosDocumentoEntity> comentariosFaltantes ;
		int diferencia = boBandejaSeguimientoInterface.obtenerDiferenciaRegistrosComentarios(comentarios, comentariosNoLeidos);
		
		if(diferencia > 0){
			comentariosFaltantes = new ArrayList<DTOComentariosDocumentoEntity>();
			comentarioNoLeido = new DTOComentariosNoLeidos();
			comentarioNoLeido.setIdDocumento(documento.getIdDocumento());
			comentarioNoLeido.setIdPersona(persona.getIdPersona());
			comentarioNoLeido.setIdArea(persona.getIdArea());
			comentarioNoLeido.setTipoArea(persona.getTipoArea());
			for (DTOComentariosDocumentoEntity comentario : comentarios) {
				comentarioNoLeido.setIdComentario(comentario.getIdComentario());
				if(!comentariosNoLeidos.contains(comentarioNoLeido)){
					comentariosFaltantes.add(comentario);
				}
			}
			
			for (DTOComentariosDocumentoEntity faltante : comentariosFaltantes) {
				
				nuevoComentarioNoLeido = boBandejaSeguimientoInterface.crearComentarioNoLeido(documento, persona, faltante);
				
				daoComentariosNoLeidosInterface.guardar(nuevoComentarioNoLeido);
			}
			
		}
	}
}
