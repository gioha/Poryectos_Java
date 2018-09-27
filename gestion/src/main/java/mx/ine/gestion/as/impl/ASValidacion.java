/**
 * @(#)ASFirma.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;
 
import java.util.Date;
import java.util.List;

import mx.ine.gestion.as.inter.ASValidacionInterface;
import mx.ine.gestion.bo.inter.BOFirmaInterface;
import mx.ine.gestion.bo.inter.BOValidacionInterface;
import mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dao.inter.DAOEdicionesDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.util.Utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO del módulo de Validación.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 12/10/2017
 */
@Component("asValidacion")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASValidacion implements ASValidacionInterface{
		
	//private static final Logger logger = Logger.getLogger(ASValidacion.class);
	
	@Autowired
	@Qualifier("daoValidacionDocumentos")
	private DAOValidacionDocumentosInterface daoValidacionDocumentosInterface;
	
	@Autowired
	@Qualifier("daoComentariosDocumento")
	private DAOComentariosDocumentoInterface daoComentariosDocumentoInterface;
	
	@Autowired
	@Qualifier("daoBorradorDocumentos")
	private DAOBorradorDocumentosInterface daoBorradorDocumentosInterface;
	
	@Autowired
	@Qualifier("daoHistDocCreacion")
	private DAOHistDocCreacionInterface daoHistDocCreacionInterface;
	
	@Autowired
	@Qualifier("boValidacion")
	private BOValidacionInterface boValidacionInterface;
	
	@Autowired
	@Qualifier("boFirma")
	private BOFirmaInterface boFirmaInterface;
	
	@Autowired
	@Qualifier("daoDocumento")
	private DAODocumentoInterface daoDocumentoInterface;
	
	@Autowired
	@Qualifier("daoEdicionesDocumento")
	private DAOEdicionesDocumentoInterface daoEdicionesDocumentoInterface;
	
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoDocumentoAnexo;
	
	@Autowired
	@Qualifier("daoComentariosDocumento")
	private DAOComentariosDocumentoInterface daoComentariosDocumento;
	
	@Override
	public List<DTOValidacionDocumentosEntity> consultarDocsParaValidacion(Integer idPersona) throws Exception {
		return daoValidacionDocumentosInterface.consultarDocsParaValidacion(idPersona);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#guardarComentario(java.util.List, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void guardarComentario(List<DTOComentariosDocumentoEntity> dtoComentario, DTOEstructuraAreasEntity persona) throws Exception {
		for( DTOComentariosDocumentoEntity dtoComentarios : dtoComentario ) {
			daoComentariosDocumentoInterface.guardarComentario(dtoComentarios, persona);
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#guardarComentario(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void guardarComentario(DTOValidacionDocumentosEntity dtoValidacion, DTOEstructuraAreasEntity persona) throws Exception  {
		DTOComentariosDocumentoEntity dtoComentario = dtoValidacion.getDtoComentario();
		Integer idComentario = null;

		if (dtoComentario.getIdComentario() == null) {
			boValidacionInterface.configurarComentario(dtoComentario);
			daoComentariosDocumentoInterface.guardarComentario(dtoComentario, persona);
			idComentario = daoValidacionDocumentosInterface.obtenerIdComentario(dtoValidacion.getIdDocumento(), dtoComentario.getUsuarioComento());
		} else {
			idComentario = dtoComentario.getIdComentario();
			daoComentariosDocumentoInterface.modificar(dtoComentario);
		}
		daoValidacionDocumentosInterface.insertarComentario(dtoValidacion.getIdPersona(), dtoValidacion.getIdPersonaRemitente(), 
				dtoValidacion.getIdDocumento(), idComentario);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#eliminarComentario(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void eliminarComentario(DTOValidacionDocumentosEntity dtoValidacion) throws Exception {
		DTOComentariosDocumentoEntity dtoComentario = dtoValidacion.getDtoComentario();
		daoComentariosDocumentoInterface.eliminar(dtoComentario);
		daoValidacionDocumentosInterface.quitarComentario(dtoValidacion.getIdPersona(), dtoValidacion.getIdPersonaRemitente(), dtoValidacion.getIdDocumento());
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#eliminarValidacion(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void eliminarValidacion(List<DTOValidacionDocumentosEntity> dtoDocumentosSeleccionados) throws Exception  {
		for( DTOValidacionDocumentosEntity dtoDocsSelec : dtoDocumentosSeleccionados ) {
			daoValidacionDocumentosInterface.eliminar(dtoDocsSelec);
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#activarComentario(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void activarComentario( List<DTOComentariosDocumentoEntity> dtoComentarios )throws Exception {
		for( DTOComentariosDocumentoEntity dtoIdDocs : dtoComentarios ) {
			daoBorradorDocumentosInterface.activarComentario(dtoIdDocs.getIdDocumento());
		}
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#obtenerBorrador(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento, Integer idPersona) throws Exception {
		return daoBorradorDocumentosInterface.obtenerBorrador(idDocumento, idPersona);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#registrarEnHistorial(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void registrarEnHistorial(List<DTOHistDocCreacionEntity> dtoHistorial) throws Exception {
		for( DTOHistDocCreacionEntity dtoHist : dtoHistorial ) {
			daoHistDocCreacionInterface.registrarEnHistorial(dtoHist);
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#regresarARemitente(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void regresarARemitente(List<DTOValidacionDocumentosEntity> dtoLSeleccionados) throws Exception {

		Integer estatusHist = null;
		

		for(DTOValidacionDocumentosEntity dtoSeleccionado : dtoLSeleccionados) {
			DTOBorradorDocumentosEntity borrador = daoBorradorDocumentosInterface.obtenerBorrador(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersonaRemitente());

			if(dtoSeleccionado.getDtoComentario() != null) {
				borrador.setConComentarios(Integer.valueOf(1));
				daoBorradorDocumentosInterface.activarComentario(dtoSeleccionado.getIdDocumento());
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentado"));
			}

			Integer idEdicion = null;
			if(dtoSeleccionado.getConModificaciones() == 1) {
				DTOEdicionesDocumentoEntity dtoEdiciones = boValidacionInterface.crearEdicion(dtoSeleccionado);
				borrador.setConModificaciones(Integer.valueOf(1));
				idEdicion = daoEdicionesDocumentoInterface.obtenerIdEdicion(dtoSeleccionado.getIdDocumento());
				if (idEdicion == null ) {
					idEdicion = 1;
				} else {
					idEdicion = idEdicion + 1;
				}
				dtoEdiciones.setIdEdicion(idEdicion);
				daoEdicionesDocumentoInterface.insertar(dtoEdiciones);
				
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoEditado"));
			}
			
			//SE VERIFICA QUE NO SE HAYA MODIFICADO EL DOCUMENTO
			if ((daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getEditado()) == 1) {
				boFirmaInterface.crearPdf(dtoSeleccionado.getDtoDocumento(),"documentos");
				daoDocumentoInterface.desactivarEdicionDocumento(dtoSeleccionado.getIdDocumento());
			}

			if (dtoSeleccionado.getDtoComentario() != null && dtoSeleccionado.getConModificaciones() == 1) {
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentadoEditado"));
			}

			Integer estatusRegresado = daoValidacionDocumentosInterface.obtenerEstatusRegresado(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersona());
			
			if (estatusRegresado.intValue() == 0) {
				daoValidacionDocumentosInterface.actualizarValidaciones(dtoSeleccionado.getIdPersona(),
						dtoSeleccionado.getIdPersonaRemitente(), dtoSeleccionado.getIdDocumento());
			}

			DTOHistDocCreacionEntity dtoHist = boValidacionInterface.crearHistorial(dtoSeleccionado);
			Integer idHistorial = daoHistDocCreacionInterface.obtenerIdHistorial(dtoSeleccionado.getIdDocumento());
			if (idHistorial == null ) {
				idHistorial = 1;
			} else {
				idHistorial = idHistorial + 1;
			}
			dtoHist.setIdEstatus(estatusHist);
			dtoHist.setIdHistorico(idHistorial);
			if (idEdicion != null) {
				dtoHist.setIdEdicion(idEdicion);
			}
			if (dtoSeleccionado.getDtoComentario() != null) {
				dtoHist.setIdComentario(dtoSeleccionado.getDtoComentario().getIdComentario());
			}
			daoHistDocCreacionInterface.guardar(dtoHist);

			borrador.setConValidacion(Integer.valueOf(0));
			borrador.setNumEnviadoValidacion(Integer.valueOf(borrador.getNumEnviadoValidacion().intValue() -1));
			
			if(borrador.getNumEnviadoValidacion().intValue() == 0) {
				borrador.setFechaEnvio(null);
				borrador.setFechaRegreso(new Date());
				borrador.setNumEnviadoValidacion(null);
			}

			if(borrador.getNoLeido().intValue() == 0) {
				borrador.setNoLeido(1);
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(borrador.getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes = notificacion.getDocumentosPendientes2().intValue() + 1;
				notificacion.setDocumentosPendientes2(Integer.valueOf(documentosPendientes));
				daoNotificacionesInterface.modificar(notificacion);
			}

			daoBorradorDocumentosInterface.guardar(borrador);
			daoValidacionDocumentosInterface.eliminarValidacion(dtoSeleccionado.getIdPersona(), dtoSeleccionado.getIdPersonaRemitente(), 
					dtoSeleccionado.getIdDocumento());
		}

	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#validar(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void validar(List<DTOValidacionDocumentosEntity> dtoLSeleccionados) throws Exception {
		
		Integer estatusHist = null;

		for(DTOValidacionDocumentosEntity dtoSeleccionado : dtoLSeleccionados) {

			DTOBorradorDocumentosEntity borrador = daoBorradorDocumentosInterface.obtenerBorrador(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersonaRemitente());
			Integer idDocumento = dtoSeleccionado.getIdDocumento();
			if(dtoSeleccionado.getDtoComentario() != null) {
				borrador.setConComentarios(Integer.valueOf(1));
				daoBorradorDocumentosInterface.activarComentario(dtoSeleccionado.getIdDocumento());
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentado"));
			}
			
			Integer idEdicion = null;
			if(dtoSeleccionado.getConModificaciones() == 1) {
				DTOEdicionesDocumentoEntity dtoEdiciones = boValidacionInterface.crearEdicion(dtoSeleccionado);
				borrador.setConModificaciones(Integer.valueOf(1));
				idEdicion = daoEdicionesDocumentoInterface.obtenerIdEdicion(dtoSeleccionado.getIdDocumento());
				if (idEdicion == null ) {
					idEdicion = 1;
				} else {
					idEdicion = idEdicion + 1;
				}
				dtoEdiciones.setIdEdicion(idEdicion);
				daoEdicionesDocumentoInterface.insertar(dtoEdiciones);

				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoEditado"));
			}

			if (daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getEditado() == 1) {
				daoDocumentoInterface.desactivarEdicionDocumento(dtoSeleccionado.getIdDocumento());
			}

			if (dtoSeleccionado.getDtoComentario() != null && dtoSeleccionado.getConModificaciones() == 1) {
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentadoEditado"));
			}

			Integer estatusRegresado = daoValidacionDocumentosInterface.obtenerEstatusRegresado(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersona());
			
			if (estatusRegresado.intValue() == 1) {
				throw new Exception("Concurrencia");
			}
			
			DTOHistDocCreacionEntity dtoHist1 = boValidacionInterface.crearHistorial(dtoSeleccionado);
			Integer idHistorial = daoHistDocCreacionInterface.obtenerIdHistorial(dtoSeleccionado.getIdDocumento());
			if (idHistorial == null ) {
				idHistorial = 1;
			} else {
				idHistorial = idHistorial + 1;
			}
			dtoHist1.setIdEstatus(Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_validado")));
			dtoHist1.setIdHistorico(idHistorial);
			daoHistDocCreacionInterface.guardar(dtoHist1);
			
			if (dtoSeleccionado.getDtoComentario() != null ) {
				DTOHistDocCreacionEntity dtoHist2 = boValidacionInterface.crearHistorial(dtoSeleccionado);
				
				dtoHist2.setIdEstatus(estatusHist);
				dtoHist2.setIdHistorico(idHistorial + 2);
				if (idEdicion != null) {
					dtoHist2.setIdEdicion(idEdicion);
				}
				if (dtoSeleccionado.getDtoComentario() != null) {
					dtoHist2.setIdComentario(dtoSeleccionado.getDtoComentario().getIdComentario());
				}
				daoHistDocCreacionInterface.guardar(dtoHist2);
			}
			borrador.setConValidacion(Integer.valueOf(1));
			borrador.setNumEnviadoValidacion(Integer.valueOf(borrador.getNumEnviadoValidacion().intValue() -1));
			
			if(borrador.getConValidacion().intValue() == 1 && borrador.getNumEnviadoValidacion().intValue() == 0) {
				borrador.setFechaEnvio(null);
				borrador.setFechaRegreso(new Date());
				daoDocumentoInterface.activarValidacionDocumento(idDocumento);
				boValidacionInterface.validarDocumento(dtoSeleccionado, borrador.getSiglasEnviados());
			}
			
			//SE VERIFICA QUE NO SE HAYA MODIFICADO EL DOCUMENTO
			if ((borrador.getConValidacion().intValue() == 1 && borrador.getNumEnviadoValidacion().intValue() == 0) || 
					daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getEditado() == 1) {
				if (daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getEditado() == 1) {
					daoDocumentoInterface.desactivarEdicionDocumento(dtoSeleccionado.getIdDocumento());
				}
				boFirmaInterface.crearPdf(dtoSeleccionado.getDtoDocumento(),"documentos");
			}

			if(borrador.getNoLeido().intValue() == 0) {
				borrador.setNoLeido(1);
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(borrador.getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes = notificacion.getDocumentosPendientes2().intValue() + 1;
				notificacion.setDocumentosPendientes2(Integer.valueOf(documentosPendientes));
				daoNotificacionesInterface.modificar(notificacion);
			}
			
			daoBorradorDocumentosInterface.guardar(borrador);
			daoValidacionDocumentosInterface.eliminarValidacion(dtoSeleccionado.getIdPersona(), dtoSeleccionado.getIdPersonaRemitente(), 
					dtoSeleccionado.getIdDocumento());
		}
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#insertarVisualizacion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarVisualizacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) throws Exception {
		daoValidacionDocumentosInterface.insertarVisualizacion(idPersona, idPersonaRemitente, idDocumento);
		
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#insertarEdicion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarEdicion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) throws Exception {
		daoValidacionDocumentosInterface.insertarEdicion(idPersona, idPersonaRemitente, idDocumento);
		daoDocumentoInterface.activarEdicionDocumento(idDocumento);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#cambiarALeido(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void cambiarALeido(DTOValidacionDocumentosEntity dtoValidacion) throws Exception{
		DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(dtoValidacion.getIdPersona(),
				Integer.valueOf(Utilidades.mensajeProperties("id_modulo_validacion")));
		int documentosPendientes = notificacion.getDocumentosPendientes().intValue() - 1;
		notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
		daoNotificacionesInterface.modificar(notificacion);
		dtoValidacion.setNoLeido(0);
		
		daoValidacionDocumentosInterface.modificar(dtoValidacion);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#obtenerDetalleDocumento(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception {
		return daoValidacionDocumentosInterface.obtenerDetalleDocumento(idDocumento);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#obtenerAnexoPorDocumento(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> obtenerAnexoPorDocumento(DTODocumentoEntity documento) throws Exception{
		return daoDocumentoAnexo.buscarPorDocumento(documento);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#consultarHistorialPorIdDocumento(int)
	 */
	@Override
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception {
		return daoHistDocCreacionInterface.consultarHistorialPorIdDocumento(idDocumento);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASValidacionInterface#consultarComentarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception {
		return daoComentariosDocumentoInterface.consultarComentarios(dtoDocumentoEntity);
	}
}
