/**
 * ASBorradorDocumentos.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.gestion.as.inter.ASBandejaBorradoresInterface;
import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.bo.inter.BOFirmaInterface;
import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface;
import mx.ine.gestion.dao.inter.DAOComentariosNoLeidosInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAOBandejaECCPInterface;
import mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface;
import mx.ine.gestion.dao.inter.DAODocumentosCCPInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface;
import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dao.inter.DAOEdicionesDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface;
import mx.ine.gestion.dao.inter.DAOHistDocRecepInterface;
import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesOFInterface;
import mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;
import mx.ine.gestion.dto.helper.DTOFiltroEstructuraAreaHelper;
import mx.ine.gestion.mb.MBBandejaBorradores;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHValidacionInterface;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Clase que contiene los métodos que acceden a el o los DAO que serán ocupados
 * en el módulo de Bandeja del sistema de Gestión.
 * 
 * @author Homero Villanueva Dominguez
 * @since 05/08/2017
 *
 */
@Component("asBandejaBorradores")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASBandejaBorradores implements ASBandejaBorradoresInterface {

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBBandejaBorradores.class);

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoDocumento")
	private DAODocumentoInterface daoDocumentoInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "BORRADOR_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoBorradorDocumentos")
	private DAOBorradorDocumentosInterface daoBorradorDocumentosInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "ESTRUCTURA_AREA"
	 */
	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "JERARQUIA_PERSONAS"
	 */
	@Autowired
	@Qualifier("daoJerarquiaPersonas")
	private DAOJerarquiaPersonasInterface daoJerarquiaPersonasInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_CREACION"
	 */
	@Autowired
	@Qualifier("daoHistDocCreacion")
	private DAOHistDocCreacionInterface daoHistDocCreacionInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_RECEPCION"
	 */
	@Autowired
	@Qualifier("daoHistDocRecep")
	private DAOHistDocRecepInterface daoHistDocRecepInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_TURNO"
	 */
	@Autowired
	@Qualifier("daoHistDocTurno")
	private DAOHistDocTurnoInterface daoHistDocTurnoInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "FIRMA_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoFirmaDocumentos")
	private DAOFirmaDocumentosInterface daoFirmaDocumentosInterface;

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
	 * "FIRMA_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoValidacionDocumentos")
	private DAOValidacionDocumentosInterface daoValidacionDocumentosInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "FIRMA_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoEnviadosDocumentos")
	private DAOEnviadosDocumentosInterface daoEnviadosDocumentosInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "EDICIONES_DOCUMENTO"
	 */
	@Autowired
	@Qualifier("daoEdicionesDocumento")
	private DAOEdicionesDocumentoInterface daoEdicionesDocumentoInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "DOCUMENTOS_DESTINATARIOS"
	 */
	@Autowired
	@Qualifier("daoDestinatario")
	private DAODocumentoDestinatarioInterface daoDestinatarioInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "DOCUMENTOS_ANEXOS"
	 */
	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoAnexoInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "DOCUMENTOS_CCP"
	 */
	@Autowired
	@Qualifier("daoDocumentosCCP")
	private DAODocumentosCCPInterface daoCcpInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "NOTIFICACIONES"
	 */
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "NOTIFICACIONES_OF"
	 */
	@Autowired
	@Qualifier("daoNotificacionesOF")
	private DAONotificacionesOFInterface daoNotificacionesOFInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "BANDEJA_E_RECIBIDOS"
	 */
	@Autowired
	@Qualifier("daoBandejaERecibidos")
	private DAOBandejaERecibidosInterface daoBandejaERecibidosInterface;

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "BANDEJA_E_CPP"
	 */
	@Autowired
	@Qualifier("daoBandejaECCP")
	private DAOBandejaECCPInterface daoBandejaECCPInterface;
	
	@Autowired
	@Qualifier("daoBandejaEntradasOficialia")
	private DAOBandejaEntradasOficialiaInterface daoBandejaEntradasOficialiaInterface;

	/**
	 * Interface que sirve para llamar el vhValidacion
	 */
	@Autowired
	@Qualifier("vhValidacion")
	private VHValidacionInterface vhValidacionInterface;
	
	@Autowired
	@Qualifier("boBandejaBorradores")
	private BOBandejaBorradoresInterface boBandejaBorradoresInterface;
	
	@Autowired
	@Qualifier("boFirma")
	private BOFirmaInterface boFirmaInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#consultarBorradores(java.lang.String, boolean)
	 */
	@Override
	public List<DTOBorradorDocumentosEntity> consultarBorradores(DTOBorradorDocumentosHelper helperBorradores) throws Exception {
		helperBorradores.setEstatusDocumento('A');
		helperBorradores.setEstatusBorrador('A');
		return daoBorradorDocumentosInterface.consutarBorradoresPorPersona(helperBorradores);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#consultarPersonasXArea(java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonasXArea(DTOEstructuraAreasEntity persona, String coincidencia) throws Exception {

		List<String> lista = new ArrayList<String>();
		lista.add(persona.getCuentaLDAP());
		DTOFiltroEstructuraAreaHelper filtro = boBandejaBorradoresInterface.obtenerFiltro(lista, persona, coincidencia);

		return daoEstructuraAreaInterface.buscarPersonasPorArea(filtro);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#consultaTitulares()
	 */
	@Override
	public List<DTOJerarquiaPersonasEntity> consultaTitulares() throws Exception{
		return daoJerarquiaPersonasInterface.consultarTitulares();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#consultarEstructurasTitulares(java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarEstructurasTitulares(String coincidencia) throws Exception {
		return daoJerarquiaPersonasInterface.consultarEstructurasTitulares(coincidencia);
	}
	
	/**
	 * Método que se encarga de las inserciones y actualizaciones para el
	 * proceso de la firma de un documento borrador
	 * 
	 * @param destinatario
	 * @param dtoBorradorDocumentosEntity
	 * @param remitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/02/2018
	 */
	private void enviarFirmar(DTOEstructuraAreasEntity destinatario, DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente){
		
		int numFirma = (dtoBorradorDocumentosEntity.getNumEnviadoFirma() == null) ? 1 : (dtoBorradorDocumentosEntity.getNumEnviadoFirma()+1);
		int estatus = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_envio_firma"));
		DTOHistDocCreacionEntity dtoHistCrea = boBandejaSeguimientoInterface.crearHistoricoCreacion(dtoBorradorDocumentosEntity.getDocumento(), destinatario, estatus);

		//Se inserta el registro para la tabla de FIRMA_DOCUMENTOS
		daoFirmaDocumentosInterface.insertarFirma(destinatario,	dtoBorradorDocumentosEntity.getDocumento(), remitente);
		
		//Se actualiza el registro de BORRADOR_DOCUMENTOS 
		dtoBorradorDocumentosEntity.setNumEnviadoFirma(numFirma);
		dtoBorradorDocumentosEntity.setFechaEnvio(new Date());
		daoBorradorDocumentosInterface.modificar(dtoBorradorDocumentosEntity);
		
		//Se notifica a la persona que se le envió a firmar
		notificarFirma(destinatario, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
		
		//Se inserta el registro en el historico 
		daoHistDocCreacionInterface.guardarHistCrea(dtoHistCrea);
		
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#insertarFirma(java.util.List, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void enviarFirmar(List<DTOEstructuraAreasEntity> listaDestinatarios, DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) throws Exception {
		if(listaDestinatarios != null){
			for (DTOEstructuraAreasEntity destinatario : listaDestinatarios) {
				enviarFirmar(destinatario, dtoBorradorDocumentosEntity, remitente);
			}
		}
		
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#eliminarDocumento(mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void eliminarDocumentoBorrador(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity dtoEstructuraAreasEntity) throws Exception {

		DTOHistDocCreacionEntity dtoHistCrea =  boBandejaSeguimientoInterface.crearHistoricoCreacion(dtoBorradorDocumentosEntity.getDocumento(), dtoEstructuraAreasEntity, Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_eliminado")));
		//Se guarda la operación en el Historico
		daoHistDocCreacionInterface.guardarHistCrea(dtoHistCrea);
		
		//Se elimina el documento de forma lógica
		dtoBorradorDocumentosEntity.getDocumento().setEstatus(Utilidades.mensajeProperties("documento_eliminado").charAt(0));
		daoDocumentoInterface.modificar(dtoBorradorDocumentosEntity.getDocumento());
		
		//Se elimina el Documento Borador
		dtoBorradorDocumentosEntity.setEstatus('E');
		daoBorradorDocumentosInterface.modificar(dtoBorradorDocumentosEntity);
		//daoBorradorDocumentosInterface.eliminar(dtoBorradorDocumentosEntity);
	}
	
	/**
	 * 
	 * @param destinatario
	 * @param dtoBorradorDocumentosEntity
	 * @param remitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/02/2018
	 */
	private void enviarValidar(DTOEstructuraAreasEntity destinatario, DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) {
		
		int estatus = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_envio_validacion"));
		int numValidaciones = (dtoBorradorDocumentosEntity.getNumEnviadoValidacion() == null)? 1: dtoBorradorDocumentosEntity.getNumEnviadoValidacion()+1;
		DTOHistDocCreacionEntity dtoHistCrea = boBandejaSeguimientoInterface.crearHistoricoCreacion(dtoBorradorDocumentosEntity.getDocumento(), destinatario, estatus);
	
		String siglasEnviados =  (dtoBorradorDocumentosEntity.getSiglasEnviados().equals(""))? "": dtoBorradorDocumentosEntity.getSiglasEnviados()+",";
		
		//Inserta en la tabla "VALIDACIÓN_DOCUMENTOS"
		daoValidacionDocumentosInterface.insertarValidacion(destinatario,dtoBorradorDocumentosEntity.getDocumento(), remitente);

		//Inserta en la tabla "HIST_DOC_CREACION"
		daoHistDocCreacionInterface.guardarHistCrea(dtoHistCrea);
//		insertar(dtoBorradorDocumentosEntity.getDocumento(), destinatario ,estatus);
		
		//Actualiza el número de Validaciones
		dtoBorradorDocumentosEntity.setNumEnviadoValidacion(numValidaciones);
		
		//Inserta Notificacion
		notificarValidacion(destinatario, Utilidades.mensajeProperties("constante_notificacion_incrementar"));

		siglasEnviados += destinatario.getNombreCompleto();  
				//vhValidacionInterface.obtenerInicialesNombre(destinatario.getNombreCompleto());
		dtoBorradorDocumentosEntity.setSiglasEnviados(siglasEnviados);
		dtoBorradorDocumentosEntity.setFechaEnvio(new Date());
		//Actualiza el borrador
		daoBorradorDocumentosInterface.modificar(dtoBorradorDocumentosEntity);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#insertarValidacion(java.util.List, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity,  mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void enviarValidar(List<DTOEstructuraAreasEntity> listaDestinatarios, DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) throws Exception{

		//Cuando se manda a validar, se pierden las siglas de las validaciones anteriores
		dtoBorradorDocumentosEntity.setSiglasEnviados("");
		for (DTOEstructuraAreasEntity destinatario : listaDestinatarios) {
			enviarValidar(destinatario, dtoBorradorDocumentosEntity, remitente);
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#leerComentarios(java.util.List)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void leerComentarios(DTOBorradorDocumentosEntity borrador, List<DTOComentariosNoLeidos> listaComentarios) throws Exception {
		
		//Se quita la notificación de Comentarios del Borrador recibido
		borrador.setConComentarios(Integer.parseInt( Utilidades.mensajeProperties("sin_notificacion_comentario")));
		daoBorradorDocumentosInterface.modificar(borrador);
		
		//Se actualiza el estatus de los comentarios que han sido leidos
		for (DTOComentariosNoLeidos elemento : listaComentarios) {
			elemento.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_leido")));
			
//			elemento.getDtoComentario().setEstatus(Integer.parseInt(Utilidades.mensajeProperties("comentario_estatus_leido")));	
			daoComentariosNoLeidosInterface.modificar(elemento);
		}
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaBorradoresInterface#obtenerListaEdicionesLeidas(int)
	 */
	@Override
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdicionesLeidas(int idDocumento) throws Exception {
		return daoEdicionesDocumentoInterface.obtenerListaEdiciones(idDocumento, Integer.parseInt(Utilidades.mensajeProperties("edicion_estatus_leido")));
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaBorradoresInterface#obtenerListaEdicionesNoLeidas(int)
	 */
	@Override
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdicionesNoLeidas(int idDocumento) throws Exception {
		return daoEdicionesDocumentoInterface.obtenerListaEdiciones(idDocumento, Integer.parseInt(Utilidades.mensajeProperties("edicion_estatus_no_leido")));
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaBorradoresInterface#enviarDestinatario(mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void enviarDestinatario(DTOBorradorDocumentosEntity borrador, DTOEstructuraAreasEntity persona) throws Exception{

		Integer estatus = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_enviado"));
		
		//Objeto necesario para guardar en el historico
		DTOHistDocCreacionEntity dtoHistCreaEnviado = boBandejaSeguimientoInterface.crearHistoricoCreacion(borrador.getDocumento(), persona, estatus);
		DTOHistDocCreacionEntity dtoHistCreaEnviadoA;
		
		//Objeto necesario para insertar en el historico de recepción
		DTOHistDocRecepEntity dtoHistRecep;
		
		//Objeto necesario para insertar en el historico de Turno
		DTOHistDocTurnoEntity dtoHistTurno;
		
		//Lista de los titulares que se crea a partir de los destinatarios del documento
		List<DTOEstructuraAreasEntity> listaTitulares = daoJerarquiaPersonasInterface.consultarTitularesDestinatarios(borrador.getDocumento(), Integer.parseInt(Utilidades.mensajeProperties("nivel_titular")));

		//Objeto con la lista de las CCP del documento recibido
		List<DTOEstructuraAreasEntity> listaCCP = daoCcpInterface.obtenerPersonasCCP(borrador.getDocumento());
		
		//Objeto con la lista de las CCP del documento recibido
		List<DTODocumentoDestinatarioEntity> listaDestinatarios = daoDestinatarioInterface.buscarPorDocumento(borrador.getDocumento());
		
		//Objeto que se utilza para la inserción de los registros en las bandejas de oficialia
		List<DTOBandejaEntradasOficialiaEntity> dtoListaDestOf = daoDestinatarioInterface.consultarPersonasOficialias(borrador.getIdDocumento());
		
		//Objeto necesario para las notificaciones de la oficialia
		List<DTONotificacionesOFEntity> notificacionesOf = daoDestinatarioInterface.consultarAreasDestinatarios(borrador.getIdDocumento());
		
		//Se crea el registro para la bandeja de Enviados
		daoEnviadosDocumentosInterface.insertarEnviado(borrador, persona);
		
		//Se inserta el documento en la bandeja de recibidos(Bandeja de los tiutulares) y se actualiza la notificación en la bandeja de entrada
		for (DTOEstructuraAreasEntity titular : listaTitulares) {
			Integer idHistoricoRecep;
			DTOBandejaERecibidosEntity recibido;
			
			//Se insera en el historico de recepción
			dtoHistRecep = boBandejaSeguimientoInterface.crearHistoricoRecep(titular, borrador.getDocumento(), null, Integer.parseInt(Utilidades.mensajeProperties("estatus_asignado_a")));
			idHistoricoRecep = daoHistDocRecepInterface.guardar(dtoHistRecep);
			
			//Se inserta el documento en la Bandeja de Recibidos
			recibido = boBandejaBorradoresInterface.crearDocumentoRecibido(titular, borrador.getDocumento(), idHistoricoRecep);
			daoBandejaERecibidosInterface.guardar(recibido);
			notificarEntrada(titular, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
			
			//Se inserta en el historico de turno
			dtoHistTurno = boBandejaSeguimientoInterface.crearHistoricoTurno(titular, borrador.getDocumento(), idHistoricoRecep, Integer.parseInt(Utilidades.mensajeProperties("id_estatus_noenterado")) , null);
			daoHistDocTurnoInterface.guardar(dtoHistTurno);
		}
		
		
		//Se inserta en la bandeja de CCP y se actualiza la notificacion en la bandeja de entrada
		if (listaCCP != null && listaCCP.size() > 0) {
			for (DTOEstructuraAreasEntity ccp : listaCCP) {
				daoBandejaECCPInterface.insertarCCP(ccp, borrador.getDocumento(), persona);
				notificarEntrada(ccp, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
			}
		}
		
		//Se guarda el enviado
		if (listaDestinatarios != null && listaDestinatarios.size() > 0) {
			estatus = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_seEnvioA"));
			for (DTODocumentoDestinatarioEntity destinatario : listaDestinatarios) {
				dtoHistCreaEnviadoA = boBandejaSeguimientoInterface.crearHistoricoCreacion(borrador.getDocumento(), destinatario, estatus);
				daoHistDocCreacionInterface.guardarHistCrea(dtoHistCreaEnviadoA);
			}
		}
		
		// Se insetan los documento en las bandejas de las oficialias
		for (DTOBandejaEntradasOficialiaEntity dtoDest : dtoListaDestOf) {
			DTOBandejaEntradasOficialiaEntity dtoBandejaOficialia = boFirmaInterface.crearBandejaEntrada(dtoDest);
			dtoBandejaOficialia.setIdDocumento(borrador.getIdDocumento());
			daoBandejaEntradasOficialiaInterface.guardar(dtoBandejaOficialia);
		}

		// Se insertan las notificaciones en la oficialia		
		for (DTONotificacionesOFEntity notificacion : notificacionesOf) {
			daoNotificacionesOFInterface.incrementar(notificacion);
		}
		
		//Se guarda quien envió el documento
		daoHistDocCreacionInterface.guardarHistCrea(dtoHistCreaEnviado);
		
		//Se elimina el documento borrador
		daoBorradorDocumentosInterface.eliminar(borrador);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#actualizarBorrador (mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void actualizarBorrador(DTOBorradorDocumentosEntity DTOBorradorDocumentosEntity) throws Exception {
		daoBorradorDocumentosInterface.modificar(DTOBorradorDocumentosEntity);
	}
	
	/**
	 * 
	 * @param persona
	 * @param accion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/02/2018
	 */
	private void notificarFirma(DTOEstructuraAreasEntity persona, String accion) {
		
		DTONotificacionesEntity notificacion = new DTONotificacionesEntity();
		
		notificacion.setIdPersona(persona.getIdPersona());
		notificacion.setIdModulo(Integer.parseInt(Utilidades.mensajeProperties("id_modulo_firma")));
		notificacion.setIdApartado(Integer.parseInt(Utilidades.mensajeProperties("id_apartado_firma")));
		
		daoNotificacionesInterface.insertarNotificacion(notificacion, accion);
	}

	/**
	 * 
	 * @param estructuraAreasEntity
	 * @param accion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/02/2018
	 */
	private void notificarValidacion(
			DTOEstructuraAreasEntity estructuraAreasEntity, String accion) {
		DTONotificacionesEntity notificacion = new DTONotificacionesEntity();
			
			notificacion.setIdPersona(estructuraAreasEntity.getIdPersona());
			notificacion.setIdModulo(Integer.parseInt(Utilidades.mensajeProperties("id_modulo_validacion")));
			notificacion.setIdApartado(Integer.parseInt(Utilidades.mensajeProperties("id_apartado_validacion")));
			
			daoNotificacionesInterface.insertarNotificacion(notificacion, accion);
		
	}

	/**
	 * 
	 * @param dtoEstructuraAreasEntity
	 * @param accion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/02/2018
	 */
	private void notificarEntrada(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, String accion) {
		
		DTONotificacionesEntity notificacion;
		
			notificacion = new DTONotificacionesEntity();
			
			notificacion.setIdPersona(dtoEstructuraAreasEntity.getIdPersona());
			notificacion.setIdModulo(Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
			notificacion.setIdApartado(Integer.parseInt(Utilidades.mensajeProperties("id_apartado_bandeja_entrada")));
			daoNotificacionesInterface.insertarNotificacion(notificacion, accion);
		
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBorradorDocumentosInterface#notificacionBandejaBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity, boolean)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void notificacionBandejaBorradores(DTOEstructuraAreasEntity persona, DTOBorradorDocumentosEntity borrador, boolean activar) throws Exception{
		int numNotificacion;
		DTONotificacionesEntity miNotificacion = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
		if(Utilidades.mensajeProperties("id_apartado_bandeja_borradores").equals(""+miNotificacion.getIdApartado2())){
			numNotificacion = miNotificacion.getDocumentosPendientes2();
			if (activar) {
				miNotificacion.setDocumentosPendientes2(++numNotificacion);
				borrador.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")));
			} else if (!activar && numNotificacion > 0) {
				miNotificacion.setDocumentosPendientes2(--numNotificacion);
				borrador.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}else{
				miNotificacion.setDocumentosPendientes2(0);
				borrador.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}
			daoNotificacionesInterface.modificar(miNotificacion);
			daoBorradorDocumentosInterface.modificar(borrador);
		}else{
			log.error("La persona con ID: " + persona.getIdPersona() + "no tiene correcto su registro en la tabla notificaciones del ID_APARTADO3, ya que debería ser "+Utilidades.mensajeProperties("id_apartado_bandeja_borradores"));
		}
	}
}