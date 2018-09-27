/**
 * @(#)ASCapturarDocumento.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.Calendar;
import java.util.List;

import mx.ine.gestion.as.inter.ASCapturarDocumentoInterface;
import mx.ine.gestion.bo.inter.BOApartadoFoliosInterface;
import mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOAcronimoInterface;
import mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOCTipoDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOCapturaDocumentosInterface;
import mx.ine.gestion.dao.inter.DAODocumentosCCPInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface;
import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface;
import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dao.inter.DAOPlantillaInterface;
import mx.ine.gestion.dao.inter.DAORespuestasTurnadosInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoCcpEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.DTORespuestaTurnado;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase ASCapturarDocumentos, encargada de administrar los objetos BO y DAO
 * para la captura de documento ademas de generar funciones de sistema
 * relacionados a estos.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
@Service("asCapturarDocumento")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class ASCapturarDocumento implements ASCapturarDocumentoInterface {

	/**
	 * Objeto para generar el log de la clase.
	 */
	private static Logger logger= Logger.getLogger(ASCapturarDocumento.class);

	@Autowired
	@Qualifier("daoCTipoDocumento")
	private DAOCTipoDocumentoInterface daocTipoDocumentoInterface;

	@Autowired
	@Qualifier("daoAcronimo")
	private DAOAcronimoInterface daoAcronimoInterface;

	@Autowired
	@Qualifier("daoPlantilla")
	private DAOPlantillaInterface daoPlantillaInterface;

	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daocTipoAreaInterface;

	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daocAreasInterface;
	
	@Autowired
	@Qualifier("daoCapturaDocumentos")
	private DAOCapturaDocumentosInterface daoCapturaDocumentosInterface;

	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;

	@Autowired
	@Qualifier("daoApartadoNumDoc")
	private DAOApartadoNumDocInterface daoApartadoNumDocInterface;

	@Autowired
	@Qualifier("daoDocumento")
	private DAODocumentoInterface daoDocumentoInterface;

	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoDocumentoAnexoInterface;
	
	@Autowired
	@Qualifier("daoDocumentosRemitentes")
	private DAODocumentosRemitentesInterface daoRemitenteInterface;

	@Autowired
	@Qualifier("daoDestinatario")
	private DAODocumentoDestinatarioInterface daoDocumentoDestinatarioInterface;

	@Autowired
	@Qualifier("daoDocumentosCCP")
	private DAODocumentosCCPInterface daoCcpInterface;

	@Autowired
	@Qualifier("daoBorradorDocumentos")
	private DAOBorradorDocumentosInterface daoBorradorDocumentosInterface;

	@Autowired
	@Qualifier("boApartadoFolios")
	private BOApartadoFoliosInterface boApartadoFoliosInterface;

	@Autowired
	@Qualifier("boCapturaDocumento")
	private BOCapturaDocumentoInterface boCapturaDocumentoInterface;

	@Autowired
	@Qualifier("daoHistDocCreacion")
	private DAOHistDocCreacionInterface daoHistDocCreacionInterface;
	
	@Autowired
	@Qualifier("daoBandejaEAtecion")
	private DAOBandejaEAtencionInterface daoBandejaEAtencionInterface;
	
	@Autowired
	@Qualifier("daoHBandejaEAtecion")
	private DAOHBandejaEAtencionInterface daoHBandejaEAtencionInterface;
	
	@Autowired
	@Qualifier("daoRespuestasTurnados")
	private DAORespuestasTurnadosInterface daoRespuestasTurnadosInterface;
	
	@Autowired
	@Qualifier("daoComentariosDocumento")
	private DAOComentariosDocumentoInterface daoComentariosDocumentoInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	@Autowired
	@Qualifier("boBandejaHistorico")
	private BOBandejaHistoricoInterface boBandejaHistoricoInterface;
	
	@Autowired
	@Qualifier("daoBandejaERecibidos")
	private DAOBandejaERecibidosInterface daoBandejaERecibidosInterface;
	
	@Autowired
	@Qualifier("daoHBandejaERecibidos")
	private DAOHBandejaERecibidosInterface daoHBandejaERecibidosInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_TURNO"
	 */
	@Autowired
	@Qualifier("daoHistDocTurno")
	private DAOHistDocTurnoInterface daoHistDocTurnoInterface;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarTiposDocumentos()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos() {

		return this.daocTipoDocumentoInterface.consultarTodosOrdenadosAscPor("descripcion");
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarAcronimos(java.lang.Integer)
	 */
	@Override
	public List<DTOAcronimoEntity> consultarAcronimos(Integer idTipoDocumento) {

		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		return this.daoAcronimoInterface.consultarAcronimosXAreaXTipoDocumento(usuarioLogueado.getTipoArea(), usuarioLogueado.getIdArea(), idTipoDocumento);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarPlantillas(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOPlantillaEntity> consultarPlantillas(Integer idTipoDocumento, Integer idAcronimo) {

		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		DTOAcronimoID acronimo = new DTOAcronimoID();
		acronimo.setTipoArea(usuarioLogueado.getTipoArea());
		acronimo.setIdArea(usuarioLogueado.getIdArea());
		acronimo.setIdTipoDocumento(idTipoDocumento);
		acronimo.setIdAcronimo(idAcronimo);
	
		return this.daoPlantillaInterface.consultarPlantillasXPersonaXAcronimo(acronimo, usuarioLogueado.getIdPersona());
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarTiposArea()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTiposArea() {

		return daocTipoAreaInterface.consultarTodosOrdenadosAscPor("descripcion");
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarEntidades()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEntidades() {

		return daoCapturaDocumentosInterface.consultarEntidadesCapturadasEnGestion();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarAreas(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreas(Integer tipoArea, Integer idEstado) {

		int idEntidad = idEstado == null ? -1 : idEstado;
		
		return daocAreasInterface.consultarAreasConOrganigrama(tipoArea, idEntidad);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarAreasDestinatarias(java.lang.String)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasDestinatarias(String nombreArea) {

		return daocAreasInterface.consultarAreasDestinatariasConOrganigrama(nombreArea);
	}
	
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#consultarPersonas(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonas(Integer tipoArea, Integer idArea, String nombre, String apellidos) {

		return daoEstructuraAreaInterface.consultarPersonasXNombreXApellidosXArea(nombre, apellidos, tipoArea, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#guardarDocumento(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public Integer guardarDocumento(DTOFiltrosCapturaDocumentoHelper filtros, List<DTOJerarquiaPersonasEntity> destinatariosArea) throws Exception {

		DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar calendario = Calendar.getInstance();
		
		//1- Apartamos el número de documento
		this.daoApartadoNumDocInterface.capturarApartadoFolios(user.getTipoArea(), user.getIdArea(), 
				filtros.getIdTipoDocumento(), filtros.getIdAcronimo(), Utilidades.mensajeProperties("constante_tipoApartado_electronico"));
		
		//2.- Consultamos el registro insertado
		DTOApartadoNumDocEntity registroGenerado = this.daoApartadoNumDocInterface.consultarUltimoRegistroUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		
		//3.- Buscamos la información completa del acronimo
		DTOAcronimoID idAcronimo = new DTOAcronimoID();
		idAcronimo.setTipoArea(user.getTipoArea());
		idAcronimo.setIdArea(user.getIdArea());
		idAcronimo.setIdTipoDocumento(filtros.getIdTipoDocumento());
		idAcronimo.setIdAcronimo(filtros.getIdAcronimo());
		DTOAcronimoEntity acronimo = this.daoAcronimoInterface.buscarPorId(idAcronimo);
		
		//4.- Procesamos el acronimo para generar el número de documento
		String numeroDocumento = this.boApartadoFoliosInterface.procesarInfoParaCrearNumeroDocumento(acronimo.getAcronimoAgrupacion(), registroGenerado.getIdNumeroDocumento());
		filtros.setNumeroDocumento(numeroDocumento);
		
		//5.- Actualizamos el número de documento pues este se muestra en
		// pantalla y se utilizara en el documento que se cree
		registroGenerado.setNumDocumento(numeroDocumento);
		this.daoApartadoNumDocInterface.modificar(registroGenerado);

		//6.- Guardamos el documento
		DTODocumentoEntity documento = this.boCapturaDocumentoInterface.generarEntidadDocumento(filtros);
		documento.setNoDocumento(numeroDocumento);
		Integer idDoc = daoDocumentoInterface.guardar(documento);
		documento.setIdDocumento(idDoc);
		documento.setNombreDocumento(idDoc + "_" + calendario.get(Calendar.YEAR) + (filtros.getTipoCapturaDocumento() == 3 ? ".pdf" : ".docx"));
		documento.setContieneAnexos(  filtros.getAnexos().size() > 0 ? 1 : 0  );
		daoDocumentoInterface.modificar(documento);

		//7.- Guardamos los remitentes
		for (DTOEstructuraAreasEntity remitente : filtros.getRemitentes()){
	
			DTODocumentosRemitentesEntity remitenteEntidad = new DTODocumentosRemitentesEntity();
			remitenteEntidad.setIdDocumento(idDoc);
			remitenteEntidad.setAnio(calendario.get(Calendar.YEAR));
			remitenteEntidad.setIdPersonaRemitente(remitente.getIdPersona());
			daoRemitenteInterface.guardar(remitenteEntidad);
		}
	
		//8.- Guardamos los destinatarios como personas
		for (DTOEstructuraAreasEntity destinatario : filtros.getDestinatarios()){
	
			DTODocumentoDestinatarioEntity destinatarioEntidad = new DTODocumentoDestinatarioEntity();
			destinatarioEntidad.setIdDocumento(idDoc);
			destinatarioEntidad.setAnio(calendario.get(Calendar.YEAR));
			destinatarioEntidad.setIdPersonaDestinataria(destinatario.getIdPersona());
			destinatarioEntidad.setTipoDestinatario( new Integer(1) ); // los destinatarios que vienen en la lista de Destinatarios se guardan como tipo persona
			daoDocumentoDestinatarioInterface.guardar(destinatarioEntidad);
		}
		
		//9.- Guardamos los destinatarios como áreas, realmente se guarda al titular del área, pero se indica que su tratamiento sera como área
		for (DTOJerarquiaPersonasEntity destinatarioArea : destinatariosArea){
			
			DTODocumentoDestinatarioEntity destinatarioEntidadComoArea = new DTODocumentoDestinatarioEntity();
			destinatarioEntidadComoArea.setIdDocumento(idDoc);
			destinatarioEntidadComoArea.setAnio(calendario.get(Calendar.YEAR));
			destinatarioEntidadComoArea.setIdPersonaDestinataria(destinatarioArea.getIdPersona());
			destinatarioEntidadComoArea.setTipoDestinatario( new Integer(2) ); // los destinatarios que vienen en la lista de destinatariosArea se guardan como tipo área
			daoDocumentoDestinatarioInterface.guardar(destinatarioEntidadComoArea);
		}

		//10.- Guardamos los ccp
		Integer acumulador = 1;
		for (DTOEstructuraAreasEntity ccp : filtros.getCcp()){
	
			DTODocumentoCcpEntity ccpEntidad = new DTODocumentoCcpEntity();
			ccpEntidad.setIdDocumento(idDoc);
			ccpEntidad.setAnio(calendario.get(Calendar.YEAR));
			ccpEntidad.setIdPersona(ccp.getIdPersona());
			ccpEntidad.setIdCcp(acumulador++);
			daoCcpInterface.guardar(ccpEntidad);
		}

		//11.- Guardamos los docuentos anexos
		int count = 1;
		for ( DTODocumentoAnexoHelper anexo : filtros.getAnexos() ) {
			
			DTODocumentoAnexoEntity a = new DTODocumentoAnexoEntity();
			a.setIdDocumento(idDoc);
			a.setIdAnexo(count);
			a.setAnio( calendario.get(Calendar.YEAR) );
			a.setNombreDocAnexo( anexo.getNombreOriginal() );
			a.setTamanio( new Double(anexo.getSize()) );
			a.setNombreAnexo( idDoc + "_" + calendario.get(Calendar.YEAR) + "_" + count + "." + anexo.getTipoExtencion() );
			daoDocumentoAnexoInterface.guardar(a);
			
			count ++;
		}
		
		//12.- Guardamos en borrador
		DTOBorradorDocumentosEntity borrador = this.boCapturaDocumentoInterface.generaEntidadBorrador(idDoc);
		daoBorradorDocumentosInterface.guardar(borrador);

		//13.- Guardamos en historico
		Integer estatus = Integer.valueOf(Utilidades.mensajeProperties("documento_estatus_creado"));
		DTOHistDocCreacionEntity dtoHistCrea = new DTOHistDocCreacionEntity();
		dtoHistCrea.setIdDocumento(idDoc);
		dtoHistCrea.setAnio(calendario.get(Calendar.YEAR));
		dtoHistCrea.setIdPersonaHist(user.getIdPersona());
		dtoHistCrea.setIdEstatus(estatus);
		//Se supone es la primera acción con respecto al documento, por ello se le asigna el id 1
		dtoHistCrea.setIdHistorico(1);
		daoHistDocCreacionInterface.guardar(dtoHistCrea);
		//daoHistDocCreacionInterface.guardarHistCrea(dtoHistCrea);

		//14.- Guardamos los archivos en gluster
		this.boCapturaDocumentoInterface.guardarDocumentoGlusterPrincipal(filtros, idDoc);

		//15.- Transformamos el word a pdf
		if (filtros.getTipoCapturaDocumento() != 3) {
			try {
				this.boCapturaDocumentoInterface.transformarAPdf(idDoc);
			} catch (Exception e) {
				logger.error("<================================ HUBO ERROR AL TRANSFORMAR A PDF!!!!" );
				logger.error("Exception mensaje : " + e.getMessage() );
				logger.error("Exception causa : " + e.getCause() );
				logger.error("Exception traza : " + e.getStackTrace() );
				this.boCapturaDocumentoInterface.reversaAGuardarDocumentoGlusterPrincipal(filtros, idDoc);
				throw new Exception("tiramos excepción para que no haga commit");
			}
		}
		return idDoc;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#guardaRespuestaTurnado(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder , DTOBandejaEAtencionEntity atencion)throws Exception {
		DTORespuestaTurnado respuesta = null;
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		DTOHBandejaEAtencionEntity historico =null;
		Integer idComentario = null;
		
		if(filtroResopnder != null){
			respuesta = new DTORespuestaTurnado();
			if(filtroResopnder.getComentario() != null && !filtroResopnder.getComentario().trim().equals("")){
				comentarioTurnado = new DTOComentariosDocumentoEntity(); 
				comentarioTurnado.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
				comentarioTurnado.setAnio(atencion.getAnio());
				comentarioTurnado.setComentarios(filtroResopnder.getComentario());
				comentarioTurnado.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
				comentarioTurnado.setUsuarioComento(SecurityContextHolder.getContext().getAuthentication().getName());
				comentarioTurnado.setTipoComentario(Utilidades.mensajeProperties("tipo_comentario_turnado").charAt(0));
				
				idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado,filtroResopnder.getPersonaComento());
				
				respuesta.setIdComentario(idComentario);
			}
			
			respuesta.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
			respuesta.setIdDocumentoRespuesta(filtroResopnder.getIdDocumentoRespuesta());
			respuesta.setIdHistoricoRecepcion(filtroResopnder.getIdHistoricoRecep());
			respuesta.setAnio(atencion.getAnio());
			daoRespuestasTurnadosInterface.guardar(respuesta);
			
			atencion.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
			atencion.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
			historico = boBandejaHistoricoInterface.crearHAtencion(atencion);
			daoHBandejaEAtencionInterface.guardar(historico);
			daoBandejaEAtencionInterface.eliminar(atencion);
		}
		
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder , DTOBandejaERecibidosEntity recibido)throws Exception {
		DTORespuestaTurnado respuesta = null;
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		DTOHBandejaERecibidosEntity historico;
		Integer idComentario = null;
		
		if(filtroResopnder != null){
			respuesta = new DTORespuestaTurnado();
			if(filtroResopnder.getComentario() != null && !filtroResopnder.getComentario().trim().equals("")){
				comentarioTurnado = new DTOComentariosDocumentoEntity(); 
				comentarioTurnado.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
				comentarioTurnado.setAnio(recibido.getAnio());
				comentarioTurnado.setComentarios(filtroResopnder.getComentario());
				comentarioTurnado.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
				comentarioTurnado.setUsuarioComento(SecurityContextHolder.getContext().getAuthentication().getName());
				comentarioTurnado.setTipoComentario(Utilidades.mensajeProperties("tipo_comentario_turnado").charAt(0));
				
				idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado, filtroResopnder.getPersonaComento());
				
				respuesta.setIdComentario(idComentario);
			}
			
			respuesta.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
			respuesta.setIdDocumentoRespuesta(filtroResopnder.getIdDocumentoRespuesta());
			respuesta.setIdHistoricoRecepcion(filtroResopnder.getIdHistoricoRecep());
			respuesta.setAnio(recibido.getAnio());
			daoRespuestasTurnadosInterface.guardar(respuesta);
			
			//Se actualiza el recibido
			recibido.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
			recibido.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
			historico = boBandejaHistoricoInterface.crearHRecibido(recibido);
			daoHBandejaERecibidosInterface.guardar(historico);
			daoBandejaERecibidosInterface.eliminar(recibido);
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#guardaRespuestaTurnado(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOHBandejaEAtencionEntity atencion) throws Exception {
		DTORespuestaTurnado respuesta = null;
		DTOHistDocTurnoEntity histDocTurno = null;
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		Integer idComentario = null;
		
		if(filtroResopnder != null){
			respuesta = new DTORespuestaTurnado();
			if(filtroResopnder.getComentario() != null && !filtroResopnder.getComentario().trim().equals("")){
				comentarioTurnado = new DTOComentariosDocumentoEntity(); 
				comentarioTurnado.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
				comentarioTurnado.setAnio(atencion.getAnio());
				comentarioTurnado.setComentarios(filtroResopnder.getComentario());
				comentarioTurnado.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
				comentarioTurnado.setUsuarioComento(SecurityContextHolder.getContext().getAuthentication().getName());
				comentarioTurnado.setTipoComentario(Utilidades.mensajeProperties("tipo_comentario_turnado").charAt(0));
				
				idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado, filtroResopnder.getPersonaComento());
				
				respuesta.setIdComentario(idComentario);
			}
			
			respuesta.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
			respuesta.setIdDocumentoRespuesta(filtroResopnder.getIdDocumentoRespuesta());
			respuesta.setIdHistoricoRecepcion(filtroResopnder.getIdHistoricoRecep());
			respuesta.setAnio(atencion.getAnio());
			daoRespuestasTurnadosInterface.guardar(respuesta);
			
			//Se actualiza el documento de atencion del historico
			atencion.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
			atencion.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
			daoHBandejaEAtencionInterface.modificar(atencion);
			
			//Se actualiza el estatus en el historico
			histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(atencion.getDocumento(), atencion.getPersona());
			histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
			daoHistDocTurnoInterface.modificar(histDocTurno);
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturarDocumentoInterface#guardaRespuestaTurnado(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOHBandejaERecibidosEntity recibido) throws Exception {
		DTORespuestaTurnado respuesta = null;
		DTOHistDocTurnoEntity histDocTurno = null;
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		Integer idComentario = null;
		
		if(filtroResopnder != null){
			respuesta = new DTORespuestaTurnado();
			if(filtroResopnder.getComentario() != null && !filtroResopnder.getComentario().trim().equals("")){
				comentarioTurnado = new DTOComentariosDocumentoEntity(); 
				comentarioTurnado.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
				comentarioTurnado.setAnio(recibido.getAnio());
				comentarioTurnado.setComentarios(filtroResopnder.getComentario());
				comentarioTurnado.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
				comentarioTurnado.setUsuarioComento(SecurityContextHolder.getContext().getAuthentication().getName());
				comentarioTurnado.setTipoComentario(Utilidades.mensajeProperties("tipo_comentario_turnado").charAt(0));
				
				idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado, filtroResopnder.getPersonaComento());
				
				respuesta.setIdComentario(idComentario);
			}
			
			respuesta.setIdDocumento(filtroResopnder.getIdDocumentoRespondido());
			respuesta.setIdDocumentoRespuesta(filtroResopnder.getIdDocumentoRespuesta());
			respuesta.setIdHistoricoRecepcion(filtroResopnder.getIdHistoricoRecep());
			respuesta.setAnio(recibido.getAnio());
			daoRespuestasTurnadosInterface.guardar(respuesta);
			
			//Se actualiza el recibido
			recibido.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
			recibido.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
			daoHBandejaERecibidosInterface.modificar(recibido);
			
			//Se actualiza el estatus en el historico
			histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(recibido.getDocumento(), recibido.getPersona());
			histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
			daoHistDocTurnoInterface.modificar(histDocTurno);
		}
		
	}
}
