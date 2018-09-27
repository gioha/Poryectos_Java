/**
 * @(#)ASFirma.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import mx.ine.gestion.as.inter.ASFirmaInterface;
import mx.ine.gestion.bo.impl.BOFirmaMultilateral;
import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.bo.inter.BOFirmaInterface;
import mx.ine.gestion.dao.inter.DAOBandejaECCPInterface;
import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface;
import mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface;
import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentosCCPInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface;
import mx.ine.gestion.dao.inter.DAOEdicionesDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface;
import mx.ine.gestion.dao.inter.DAOHistDocRecepInterface;
import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesOFInterface;
import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODatosFirmaDocEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.mb.MBAdministradorGestion;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import seguridata.segurisign.service.MultiSignedMessageOut;

/**
 * Clase encargada de administrar el o los DAO del módulo de Firma.
 *
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */
@Component("asFirma")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASFirma implements ASFirmaInterface{
		
	private static final Logger logger = Logger.getLogger(ASFirma.class);
	
	@Autowired
	@Qualifier("boFirma")
	private BOFirmaInterface boFirmaInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	@Autowired
	@Qualifier("boBandejaBorradores")
	private BOBandejaBorradoresInterface boBandejaBorradoresInterface;
	
	@Autowired
	@Qualifier("daoFirmaDocumentos")
	private DAOFirmaDocumentosInterface daoFirmaDocumentosInterface;
		
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
	@Qualifier("daoDatosFirmaDoc")
	private DAODatosFirmaDocInterface daoDatosFirmaDocInterface;
	
	@Autowired
	@Qualifier("daoDocumento")
	private DAODocumentoInterface daoDocumentoInterface;
	
	@Autowired
	@Qualifier("daoEnviadosDocumentos")
	private DAOEnviadosDocumentosInterface daoEnviadosDocumentosInterface;
	
	@Autowired
	@Qualifier("daoEdicionesDocumento")
	private DAOEdicionesDocumentoInterface daoEdicionesDocumentoInterface;
	
	@Autowired
	@Qualifier("daoBandejaEntradasOficialia")
	private DAOBandejaEntradasOficialiaInterface daoBandejaEntradasOficialiaInterface;
	
	@Autowired
	@Qualifier("daoDestinatario")
	private DAODocumentoDestinatarioInterface daoDestinatarioInterface;
	
	@Autowired
	@Qualifier("daoOficialiasAreas")
	private DAOOficialiasAreasInterface daoOficialiasAreasInterface;
	
	@Autowired
	@Qualifier("daoDocumentosRemitentes")
	private DAODocumentosRemitentesInterface daoDocumentosRemitentesInterface;
	
	@Autowired
	@Qualifier("daoJerarquiaPersonas")
	private DAOJerarquiaPersonasInterface daoJerarquiaPersonasInterface;
	
	@Autowired
	@Qualifier("daoBandejaERecibidos")
	private DAOBandejaERecibidosInterface daoBandejaERecibidosInterface;
	
	@Autowired
	@Qualifier("daoDocumentosCCP")
	private DAODocumentosCCPInterface daoCcpInterface;
	
	@Autowired
	@Qualifier("daoBandejaECCP")
	private DAOBandejaECCPInterface daoBandejaECCPInterface;
	
	@Autowired
	@Qualifier("daoNotificacionesOF")
	private DAONotificacionesOFInterface daoNotificacionesOFInterface;
	
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoDocumentoAnexo;
	
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
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#consultarDocsFirmas(java.lang.Integer)
	 */
	public List<DTOFirmaDocumentosEntity> consultarDocsFirmas(Integer idPersona, Integer pendienteEnvio){
		
		List<DTOFirmaDocumentosEntity> firmas = daoFirmaDocumentosInterface.consultarDocsFirmas(idPersona, pendienteEnvio);
		
		for( DTOFirmaDocumentosEntity dtofirmas : firmas ) {
			DTODatosFirmaDocEntity dtoFirmaInfo = daoDatosFirmaDocInterface.consultar(dtofirmas.getIdDocumento(), dtofirmas.getIdPersona());
			if (dtoFirmaInfo == null){
				DTODatosFirmaDocEntity dtoFirmaInfoN = new DTODatosFirmaDocEntity();
				dtofirmas.setDtoFirmaElectronica(dtoFirmaInfoN);
			}else{
				dtofirmas.setDtoFirmaElectronica(dtoFirmaInfo);
			}
				
		}
		
		return firmas;
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#guardarComentario(java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void guardarComentario(List<DTOComentariosDocumentoEntity> dtoComentario, DTOEstructuraAreasEntity persona) {
		for( DTOComentariosDocumentoEntity dtoComentarios : dtoComentario ) {
			daoComentariosDocumentoInterface.guardarComentario(dtoComentarios, persona);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#guardarComentario(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void guardarComentario(DTOFirmaDocumentosEntity dtoFirma, DTOEstructuraAreasEntity persona){
		DTOComentariosDocumentoEntity dtoComentario = dtoFirma.getDtoComentario();
		Integer idComentario = null;

		if (dtoComentario.getIdComentario() == null) {
			boFirmaInterface.configurarComentario(dtoComentario);
			daoComentariosDocumentoInterface.guardarComentario(dtoComentario, persona);
			idComentario = daoFirmaDocumentosInterface.obtenerIdComentario(dtoFirma.getIdDocumento(), dtoComentario.getUsuarioComento());
		} else {
			idComentario = dtoComentario.getIdComentario();
			daoComentariosDocumentoInterface.modificar(dtoComentario);
		}
		daoFirmaDocumentosInterface.insertarComentario(dtoFirma.getIdPersona(), dtoFirma.getIdPersonaRemitente(), 
				dtoFirma.getIdDocumento(), idComentario);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#eliminarComentario(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void eliminarComentario(DTOFirmaDocumentosEntity dtoValidacion){
		DTOComentariosDocumentoEntity dtoComentario = dtoValidacion.getDtoComentario();
		daoComentariosDocumentoInterface.eliminar(dtoComentario);
		daoFirmaDocumentosInterface.quitarComentario(dtoValidacion.getIdPersona(), dtoValidacion.getIdPersonaRemitente(), 
				dtoValidacion.getIdDocumento());
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#obtenerBorrador(java.lang.Integer)
	 */
	@Override
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento, Integer idPersona) {
		return daoBorradorDocumentosInterface.obtenerBorrador(idDocumento, idPersona);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#regresarARemitente(java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public String regresarARemitente(List<DTOFirmaDocumentosEntity> dtoLSeleccionados) throws Exception {
		
		String mensajeError = "";

		
		Integer estatusHist = 0;
		
		for(DTOFirmaDocumentosEntity dtoSeleccionado : dtoLSeleccionados){
			
			DTOBorradorDocumentosEntity dtoBorrador = daoBorradorDocumentosInterface.obtenerBorrador(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersonaRemitente());
			
			if(dtoSeleccionado.getDtoComentario() != null){
				//DTOComentariosDocumentoEntity dtoComentario = boFirmaInterface.crearComentario(dtoSeleccionado);
				//daoComentariosDocumentoInterface.guardarComentario(dtoComentario);
				
				dtoBorrador.setConComentarios(Integer.valueOf(1));
				
				//daoBorradorDocumentosInterface.activarComentario(dtoSeleccionado.getIdDocumento());
				
				//SE ACTIVA EL ESTATUS EN COMENTARIOS PARA QUE SEA VISTO POR LA BANDEJA
				daoComentariosDocumentoInterface.activarEstatus(SecurityContextHolder.getContext()
						.getAuthentication().getName(),dtoSeleccionado.getIdDocumento());
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentado"));
			}
			Integer idEdicion = null;
			//SI EL DOCUMENTO TIENE MODIFICACIONES SE INSERTA EN LA TABLA DE EDICIONES 
			if(dtoSeleccionado.getConModificaciones() == 1){
				
				logger.info("El documento "+dtoSeleccionado.getDtoDocumento().getNoDocumento()+" se editó");
				DTOEdicionesDocumentoEntity dtoEdiciones = boFirmaInterface.crearEdicion(dtoSeleccionado);
				
				idEdicion = daoEdicionesDocumentoInterface.obtenerIdEdicion(dtoSeleccionado.getIdDocumento());
				if (idEdicion == null ) {
					idEdicion = 1;
				} else {
					idEdicion = idEdicion + 1;
				}
				dtoEdiciones.setIdEdicion(idEdicion);
				
				daoEdicionesDocumentoInterface.guardar(dtoEdiciones);
				
				dtoBorrador.setConModificaciones(Integer.valueOf(1));
				dtoBorrador.setConValidacion(0);
				dtoBorrador.setNumEnviadoValidacion(null);
				dtoBorrador.setSiglasEnviados(null);
				//daoBorradorDocumentosInterface.activarModificacion(dtoSeleccionado.getIdDocumento());
				
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoEditado"));
				
				
			}
			
			if(dtoSeleccionado.getDtoComentario() != null && dtoSeleccionado.getConModificaciones() == 1){
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentadoEditado"));
			}
			
			//SE REGISTRA EN EL HISTORIAL
			DTOHistDocCreacionEntity dtoHist = boFirmaInterface.crearHistorial(dtoSeleccionado);
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
			
			if(daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getFirmado().intValue() == 1){
				//SE ELIMINA LA FIRMA DE TODOS LOS USUARIOS QUE YA HABÍAN FIRMADO
				daoDatosFirmaDocInterface.eliminarPorIDFirma(dtoSeleccionado.getIdDocumento());
				daoDocumentoInterface.desactivarFirmaDocumento(dtoSeleccionado.getIdDocumento());
				
				dtoBorrador.setConFirma(Integer.valueOf(0));
				//daoBorradorDocumentosInterface.desactivarFirma(dtoSeleccionado.getIdDocumento());
			}
			//SE VERIFICA QUE NO SE HAYA MODIFICADO EL DOCUMENTO
			if((daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getEditado()) == 1){
				boFirmaInterface.crearPdf(dtoSeleccionado.getDtoDocumento(),"documentos");
				daoDocumentoInterface.desactivarEdicionDocumento(dtoSeleccionado.getIdDocumento());
			}
			daoFirmaDocumentosInterface.desbloquearDocumento(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersona());
			if(daoFirmaDocumentosInterface.consultarRegresado(dtoSeleccionado.getIdPersona(),dtoSeleccionado.getIdDocumento()).intValue()!=1){

				daoFirmaDocumentosInterface.activarEstatusPersonaRegresado(dtoSeleccionado.getIdPersona(),dtoSeleccionado.getIdDocumento());
			}
			daoFirmaDocumentosInterface.eliminar(dtoSeleccionado);
			
			//SE VERIFICA QUE EL DOCUMENTO SEA EL ÚLTIMO EN REGRESARSE
			if(dtoBorrador.getNumEnviadoFirma().intValue() == 1){
				
				dtoBorrador.setFechaEnvio(null);
				dtoBorrador.setFechaRegreso(null);
				dtoBorrador.setNumEnviadoFirma(null);
				//daoBorradorDocumentosInterface.registrarFechaRegreso(dtoSeleccionado.getIdDocumento(), null);
			
			}else{
				
				//SE DECREMENTA EL NUMERO DE FIRMANTES
				dtoBorrador.setNumEnviadoFirma(dtoBorrador.getNumEnviadoFirma() - 1);
				//daoBorradorDocumentosInterface.restarFirma(dtoSeleccionado.getIdDocumento());
			}
			 
			if(dtoBorrador.getNoLeido().intValue() == 0) {
				dtoBorrador.setNoLeido(1);
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(dtoBorrador.getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes = notificacion.getDocumentosPendientes2().intValue() + 1;
				notificacion.setDocumentosPendientes2(Integer.valueOf(documentosPendientes));
				daoNotificacionesInterface.modificar(notificacion);
			}
			
			daoBorradorDocumentosInterface.modificar(dtoBorrador);
		}
		
		return mensajeError;

	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#verificarFirmaYValidacion(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer verificarFirmaYValidacion(Integer idDocumento) {
		DTODocumentoEntity dtoDocumento = daoDocumentoInterface.consultarDocumento(idDocumento);
		Integer mensaje = boFirmaInterface.verificarFirmaYValidacion(dtoDocumento);
		
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#obtenerDatosFirmas(java.util.List, java.io.File)
	 */
	@Override
	public List<DTOFirmaDocumentosEntity> obtenerDatosFirmas(List<DTOFirmaDocumentosEntity> dtoListaFirma, byte[] certificado, String tipoAlgoritmo) throws Exception {
		
		//SE VERIFICA QUE NO SE HAYA MODIFICADO EL DOCUMENTO
			
		logger.error("<============ COMENZANDO PROCESO DE FIRMA ==============>");
		
		logger.error("<============ ESTABLECIENDO CONEXIÓN CON SEGURISIGN ==============>");
		
		BOFirmaMultilateral boFirmaMultilateral = null;
		
		try {
			
			//LA CONEXIÓN SE HACE DE
			boFirmaMultilateral = new BOFirmaMultilateral(); 
		
		} catch(Exception e) {
		
			logger.error("<============ CONEXIÓN FALLIDA!!  ==============> (HOUSTON WE HAVE A PROBLEM)");
			//throw new Exception("NOCONEXION");
		}
		logger.error("<============ CONEXIÓN ÉXITOSA ==============>");
		
		
		MultiSignedMessageOut processID=null;
		//String hashCertificado;
		Integer numeroFirmantes;
		
		String cadenaOriginal="";
		String nombreArchivo;
		
		logger.error("<============ SE VERIFICA SI YA EXISTE EL ID DE PROCESO   ==============>");
		
		for(DTOFirmaDocumentosEntity dtoFirma: dtoListaFirma){
			
			//SE VERIFICA QUE NO SE HAYA MODIFICADO EL DOCUMENTO
			if((daoDocumentoInterface.consultarDocumento(dtoFirma.getIdDocumento()).getEditado()) == 1){
				//boFirmaInterface.crearPdf(dtoFirma.getDtoDocumento());
				daoDocumentoInterface.desactivarEdicionDocumento(dtoFirma.getIdDocumento());
			}
			
			DTODatosFirmaDocEntity dtoDatosFirma = daoDatosFirmaDocInterface.consultarDatosPorDocumento(dtoFirma.getIdDocumento());
			
			if(dtoDatosFirma==null){
				String hashDocumento = boFirmaInterface.crearHashDocumento(dtoFirma.getDtoDocumento().getNombreDocumento(), Utilidades.getRutaGlusterFS());
				cadenaOriginal = "||"+dtoFirma.getDtoDocumento().getIdDocumento()+"|"+ hashDocumento +"|"+dtoFirma.getDtoDocumento().getNombreDocumento()+"||";
				nombreArchivo = dtoFirma.getDtoDocumento().getNombreDocumento();
				
				logger.error("<============ NUEVO PROCESO PARA EL DOCUMENTO "+ dtoFirma.getDtoDocumento().getNoDocumento()+ " ==============>");
				
				logger.error("<============ PASO 1: SE CREA EL ID DE PROCESO ==============>");
				
				//SE OBTIENE EL NÚMERO DE FIRMANTES DEL DOCUMENTO 
				numeroFirmantes = daoBorradorDocumentosInterface.obtenerNumeroFirmasBorrador(dtoFirma.getIdDocumento());
				
				//SE CREA NUEVO ID DE PROCESO PARA PDF SIGNATURE
				//processID = boFirmaMultilateral.iniciar(dtoFirma.getDtoDocumento().getIdDocumento()+"_"+dtoFirma.getDtoDocumento().getAnio(), numeroFirmantes, tipoAlgoritmo);
				//dtoFirma.getDtoFirmaElectronica().setIdProceso(processID);
				
				//SE CREA NUEVO ID DE PROCESO PARA CMS CON CONTENIDO
				processID = boFirmaMultilateral.iniciarCMS(nombreArchivo,cadenaOriginal, numeroFirmantes, tipoAlgoritmo);
				
				//processID = boFirmaMultilateral.iniciarCMSArchivo(nombreArchivo, 10, tipoAlgoritmo);
				dtoFirma.getDtoFirmaElectronica().setIdProceso(processID.getProcessID());
				dtoFirma.getDtoFirmaElectronica().setCadenaOriginal(cadenaOriginal);
				
				logger.error("<============ EL ID DEL PROCESO ES " +processID.getProcessID()+ " ==============>");

				logger.error("<============ PASO 2: SE OBTIENE EL HASH DEL CERTIFICADO DEL FIRMANTE  ==============>");
				
				//SE OBTIENE EL HASH PARA PDF SIGNATURE
				//hashCertificado = boFirmaMultilateral.getHash(processID, certificado);
				//dtoFirma.getDtoFirmaElectronica().setHashCertificado(hashCertificado);
				
				//SE OBTIENE EL HASH PARA CMS 
				//hashCertificado = boFirmaMultilateral.getHash(processID, certificado);
				byte[] data = boFirmaMultilateral.hexStringToByteArray(processID.getHash());
		    	String hashCadenaOriginal = Base64.encodeBase64String(data);
				dtoFirma.getDtoFirmaElectronica().setHashCadenaOriginal(hashCadenaOriginal);
				
				
				//logger.error("<============ HASH: " + hashCertificado + "   ==============>");
				
				logger.error("<============ FINALIZA PASO 1 Y 2 PARA EL DOCUMENTO "+ dtoFirma.getDtoDocumento().getNoDocumento()+ " ==============>");
				
			}else{
				
				logger.error("<============ EL DOCUMENTO "+ dtoFirma.getDtoDocumento().getNoDocumento()+ " YA TIENE UN ID DE PROCESO ==============>");
				
				//processID = dtoDatosFirma;
				dtoFirma.getDtoFirmaElectronica().setIdProceso(dtoDatosFirma.getIdProceso());
				dtoFirma.getDtoFirmaElectronica().setCadenaOriginal(dtoDatosFirma.getCadenaOriginal());
				
				
				logger.error("<============ EL ID DEL PROCESO ES " +dtoDatosFirma.getIdProceso()+ " ==============>");
				
				logger.error("<============ PASO 2: SE OBTIENE EL HASH DEL CERTIFICADO DEL FIRMANTE  ==============>");
				
				//SE OBTIENE EL HASH
				//hashCertificado = boFirmaMultilateral.getHash(processID, certificado);
				//dtoFirma.getDtoFirmaElectronica().setHashCertificado(hashCertificado);
				
				dtoFirma.getDtoFirmaElectronica().setHashCadenaOriginal(dtoDatosFirma.getHashCadenaOriginal());
				
				//logger.error("<============ HASH: " + hashCertificado + "   ==============>");
				
				logger.error("<============ FINALIZA PASO 1 Y 2 PARA EL DOCUMENTO "+ dtoFirma.getDtoDocumento().getNoDocumento()+ " ==============>");
				
			}		
			
		}
		
		return dtoListaFirma;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#enviarPKCS7(java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override 
	public void enviarPKCS7(List<DTOFirmaDocumentosEntity> dtoListaDatosFirma) throws Exception, FileNotFoundException {
		
		logger.error("<============ PASO 3: AQUÍ YA SE OBTUVO EL PKCS7 DE LADO EL CLIENTE ==============>");
		
		logger.error("<============ ESTABLECIENDO CONEXIÓN CON SEGURISIGN ==============>");
		
		BOFirmaMultilateral boFirmaMultilateral = null;
		
		try {
		
			boFirmaMultilateral = new BOFirmaMultilateral(); //la conexión se hace dentro de la clase SeguriSignServiceImpl que instancia esta clase.
		
		} catch(Exception e) {
		
			logger.error("<============ CONEXIÓN FALLIDA!!  ==============> (HOUSTON WE HAVE A PROBLEM)");
			//throw new Exception("NOCONEXION");
		}
		
		logger.error("<============ CONEXIÓN ÉXITOSA ==============>");
		
		logger.error("<============ PASO 4: ACTUALIZAR (GENERANDO DOCUMENTO) =============>");
		
		for(DTOFirmaDocumentosEntity dtoDocs: dtoListaDatosFirma){
			
			DTODocumentoEntity dtoDocumento = daoDocumentoInterface.consultarDocumento(dtoDocs.getIdDocumento());
			
			logger.error("<============ CONTINUA EL PROCESO PARA EL DOCUMENTO "+ dtoDocs.getDtoDocumento().getNoDocumento()+ " ==============>");
			
			logger.error("<============ PKCS 7 "+ dtoDocs.getDtoFirmaElectronica().getPkcs7() +" =============>");
			
			//SE OBTIENE EL ID DE SECUENCIA PARA EL DOCUMENTO EN PDF SIGNATURE
			//dtoDocs.getDtoFirmaElectronica().setIdSecuencia(boFirmaMultilateral.actualizar(dtoDocs.getDtoFirmaElectronica().getPkcs7(), dtoDocs.getDtoDocumento().getIdDocumento()+"_"+dtoDocs.getDtoDocumento().getAnio(), dtoDocs.getDtoFirmaElectronica().getIdProceso()));
			//dtoDocs.getDtoFirmaElectronica().setIdDocumento(dtoDocs.getIdDocumento());
			//dtoDocs.getDtoFirmaElectronica().setIdPersonaFirma(dtoDocs.getIdPersona());
			
			
			//SE OBTIENE EL ID DE SECUENCIA PARA EL DOCUMENTO CMS CON CONTENIDO
			dtoDocs.getDtoFirmaElectronica().setIdSecuencia(boFirmaMultilateral.actualizarCMS(dtoDocs.getDtoFirmaElectronica().getPkcs7(), dtoDocs.getDtoFirmaElectronica().getIdProceso()));
			dtoDocs.getDtoFirmaElectronica().setIdDocumento(dtoDocs.getIdDocumento());
			dtoDocs.getDtoFirmaElectronica().setIdPersonaFirma(dtoDocs.getIdPersona());
			dtoDocs.getDtoFirmaElectronica().setHashPkcs7(boFirmaInterface.crearHashCadena(dtoDocs.getDtoFirmaElectronica().getPkcs7()));
			
				//SE OBTIENE EL DOCUMENTO SIN CERRARLO PARA QUE PUEDA SER VISIBLE EN LA BANDEJA
				//boFirmaMultilateral.cerrarProceso(dtoDocs.getDtoFirmaElectronica().getIdProceso(), 0, dtoDocs.getDtoDocumento().getNoDocumento());
				
			
			logger.error("<============ ACTUALIZAMOS LA BASE  =============>");
			
			//SE GUARDAN LOS DATOS DE FIRMA EN DATOS_FIRMA_DOC
			daoDatosFirmaDocInterface.guardar(dtoDocs.getDtoFirmaElectronica());
			
			//EL DOCUMENTO SE QUEDA COMO PENDIENTE DE ENVIO
			daoFirmaDocumentosInterface.actualizarPendienteEnvio(dtoDocs, 1);
			
			//SE DESBLOQUEA EL DOCUMENTO EN CASO DE QUE EL DOCUMENTO HAYA SIDO BLOQUEADO
			daoFirmaDocumentosInterface.desbloquearDocumento(dtoDocs.getIdDocumento(), dtoDocs.getIdPersona());
			
			//SE PRENDE LA BANDERA DE FIRMADO EN DOCUMENTOS
			daoDocumentoInterface.activarFirmaDocumento(dtoDocs.getIdDocumento());
			
			//SE PRENDE LA BANDERA DE FIRMADO EN BORRADORES_DOCUMENTOS
			//daoBorradorDocumentosInterface.activarFirma(dtoDocs.getIdDocumento());
			
			//SE OBTIENE EL DOCUMENTO SIN CERRARLO PARA QUE PUEDA SER VISIBLE EN LA BANDEJA
			//boFirmaMultilateral.cerrarProceso(dtoDocs.getDtoFirmaElectronica().getIdProceso(), 0, dtoDocs.getDtoDocumento().getNoDocumento());
			
			//SE ESCRIBE LA FIRMA EN EL DOCUMENTO
			boFirmaInterface.insertarFirmaEnDcumento(dtoDocumento, dtoDocs);
			
			
		}
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#regresarARemitenteDocFirmado(java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void regresarARemitenteDocFirmado(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados) throws Exception {
	
		Integer estatusHistFirmado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_firmado"));
		Integer estatusHist = 0;
		
		
		for(DTOFirmaDocumentosEntity dtoFirmados: dtoDocumentosSeleccionadosFirmados){
			
			DTOBorradorDocumentosEntity dtoBorrador = daoBorradorDocumentosInterface.obtenerBorrador(dtoFirmados.getIdDocumento(), dtoFirmados.getIdPersonaRemitente());
			
			//SE VERIFICA SI EL DOCUMENTO FUE COMENTADO
			if(dtoFirmados.getDtoComentario() != null){

				//SE ACTIVA LOS COMENTARIOS
				dtoBorrador.setConComentarios(Integer.valueOf(1));
				
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentado"));
				
				//SE ACTIVA EL ESTATUS EN COMENTARIOS PARA QUE SEA VISTO POR LA BANDEJA
				daoComentariosDocumentoInterface.activarEstatus(SecurityContextHolder.getContext()
						.getAuthentication().getName(),dtoFirmados.getIdDocumento());
			}
			Integer idEdicion = null;
			if(dtoFirmados.getConModificaciones() == 1){
				
				//SE GUARDA LA EDICIÓN EN EDICIONES_DOCUMENTO
				DTOEdicionesDocumentoEntity dtoEdiciones = boFirmaInterface.crearEdicion(dtoFirmados);
				idEdicion = daoEdicionesDocumentoInterface.obtenerIdEdicion(dtoFirmados.getIdDocumento());
				if (idEdicion == null ) {
					idEdicion = 1;
				} else {
					idEdicion = idEdicion + 1;
				}
				
				dtoEdiciones.setIdEdicion(idEdicion);
				daoEdicionesDocumentoInterface.guardar(dtoEdiciones);
				
				//SE PRENDE LA BANDERA DE MODIFICADO EN BORRADORES Y SE ELIMINAN LAS VALIDACIONES EN CASO DE TENERLAS
				dtoBorrador.setConModificaciones(Integer.valueOf(1));
				dtoBorrador.setConValidacion(0);
				dtoBorrador.setNumEnviadoValidacion(null);
				dtoBorrador.setSiglasEnviados(null);
				
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoEditado"));
			}
			
			//SI EL DOCUMENTO FUE EDITADO & COMENTADO CAMBIA EL ESTATUS
			if(dtoFirmados.getDtoComentario() != null && dtoFirmados.getConModificaciones() == 1){
				estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoComentadoEditado"));
			}
			
			//SE ELIMINA DE LA TABLA FIRMA_DOCUMETNOS
			daoFirmaDocumentosInterface.eliminar(dtoFirmados);
			
			//SE REGISTRA EN EL HISTORAL
			DTOHistDocCreacionEntity dtoHistFirmado = boFirmaInterface.crearHistorial(dtoFirmados);
			
			
			Integer idHistorial = daoHistDocCreacionInterface.obtenerIdHistorial(dtoFirmados.getIdDocumento());
			if (idHistorial == null ) {
				idHistorial = 1;
			} else {
				idHistorial = idHistorial + 1;
			}
			dtoHistFirmado.setIdEstatus(estatusHistFirmado);
			dtoHistFirmado.setIdHistorico(idHistorial);
			daoHistDocCreacionInterface.guardar(dtoHistFirmado);
			
			//SE REGISTRA EN EL HISTORIAL SI EL DOCUMENTO FUE EDITADO Y/O MODIFICADO
			if(estatusHist != 0){
				DTOHistDocCreacionEntity dtoHist = boFirmaInterface.crearHistorial(dtoFirmados);
					idHistorial = idHistorial + 1;
					if (idEdicion != null) {
						dtoHist.setIdEdicion(idEdicion);
					}
					if (dtoFirmados.getDtoComentario() != null) {
						dtoHist.setIdComentario(dtoFirmados.getDtoComentario().getIdComentario());
					}
				dtoHist.setIdEstatus(estatusHist);
				dtoHist.setIdHistorico(idHistorial);
				daoHistDocCreacionInterface.guardar(dtoHist);
			}
			
			Integer regresado = daoFirmaDocumentosInterface.consultarRegresado(dtoFirmados.getIdPersona(), dtoFirmados.getIdDocumento());
			//SE VERIFICA QUE EL NÚMERO DE FIRMADOS SEA 1 Y EL ESTATUS DEL DOCUMENTO SEA CERO 
			if(dtoBorrador.getNumEnviadoFirma().intValue() == 1 ){
				
				//SE VERIFICA QUE NO SE HAYA REGRESADO EL DOCUMENTO POR OTRA PERSONA
				if(regresado.intValue()!=1){
					
					dtoBorrador.setFechaRegreso(new Date());
					dtoBorrador.setFechaEnvio(null);
					dtoBorrador.setNumEnviadoFirma(0);





					
				}else{
					
					dtoBorrador.setFechaRegreso(new Date());
					dtoBorrador.setFechaEnvio(null);
					dtoBorrador.setNumEnviadoFirma(null);

					
				}
				
			}else{
			
				//SE DECREMENTA EL NUMERO DE FIRMANTES
				dtoBorrador.setNumEnviadoFirma(dtoBorrador.getNumEnviadoFirma() - 1);
			}
			
			//SE VERIFICA SI EL DOCUMENTO NO HA SIDO LEIDO
			if(dtoBorrador.getNoLeido().intValue() == 0) {
				
				dtoBorrador.setNoLeido(1);
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(dtoBorrador.getIdPersona(), Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));

				int documentosPendientes = notificacion.getDocumentosPendientes2().intValue() + 1;
				notificacion.setDocumentosPendientes2(Integer.valueOf(documentosPendientes));
				daoNotificacionesInterface.modificar(notificacion);
			}

			//SE PRENDE LA BANDERA DE FIRMADO EN BORRADORES_DOCUMENTOS
			dtoBorrador.setConFirma(Integer.valueOf(1));
			
			daoBorradorDocumentosInterface.modificar(dtoBorrador);
			
			//SE TRANSFORMA A PDF
			if(regresado.intValue()!=1){
			
				//SI NADIE LO REGRESO A REMITENTE
				boFirmaInterface.crearPdf(dtoFirmados.getDtoDocumento(),"documentos_firmados");
				
			}else{
				
				//SI ALGUIEN LO REGRESO A REMITENTE ELIMINANDO LAS FIRMAS
				boFirmaInterface.crearPdf(dtoFirmados.getDtoDocumento(),"documentos");
			}
			
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#enviarADestinatario(java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public String enviarADestinatario(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados, Integer idArea, Integer tipoArea, Integer opcionEnvio) throws Exception {
		
		String mensajeError = "";
		//Objeto necesario para insertar en el historico de recepción
		DTOHistDocRecepEntity dtoHistRecep;
		
		List<DTOEstructuraAreasEntity> dtoListaCcp;
				
		//Objeto necesario para insertar en el historico de Turno
		DTOHistDocTurnoEntity dtoHistTurno;
		
		//SE VERIFICA QUE NO EXISTA UN DOCUMENTO CON NUMERO DE FIRMAS > 1
		for(DTOFirmaDocumentosEntity dtoFirmados: dtoDocumentosSeleccionadosFirmados){
			if(daoBorradorDocumentosInterface.obtenerNumeroFirmasBorrador(dtoFirmados.getIdDocumento())>1){
				mensajeError = "El documento " + dtoFirmados.getDtoDocumento().getNoDocumento() +" "+ Utilidades.mensajeProperties("mensaje_firma_faltanPorFirmar");
				logger.info(mensajeError);
				return mensajeError;
			}
		}
		
		Integer estatusHistFirmado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_firmado"));
		Integer estatusHistEnviado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_enviado"));
		
		logger.error("<============ ESTABLECIENDO CONEXIÓN CON SEGURISIGN ==============>");
		
		BOFirmaMultilateral boFirmaMultilateral = null;
		
		try {
			
			boFirmaMultilateral = new BOFirmaMultilateral(); 
		
		} catch(Exception e) {
			
			logger.error("<============ CONEXIÓN FALLIDA!!  ==============> (HOUSTON WE HAVE A PROBLEM)");
		}
		
		for(DTOFirmaDocumentosEntity dtoFirmados: dtoDocumentosSeleccionadosFirmados){
			
			DTOBorradorDocumentosEntity dtoBorrador = daoBorradorDocumentosInterface.obtenerBorrador(dtoFirmados.getIdDocumento(), dtoFirmados.getIdPersonaRemitente());

			DTOEnviadosDocumentosEntity dtoEnviado = boFirmaInterface.crearEnviados(dtoFirmados, idArea, tipoArea, dtoFirmados.getIdPersona());

			DTOEnviadosDocumentosEntity dtoCreado = boFirmaInterface.crearEnviados(dtoFirmados, idArea, tipoArea, dtoFirmados.getDtoDocumento().getIdPersona());

			if(dtoFirmados.getDtoComentario() != null){
			
				estatusHistEnviado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_enviadoComentado"));
				
				//SE ACTIVA EL ESTATUS EN COMENTARIOS PARA QUE SEA VISTO POR LA BANDEJA
				daoComentariosDocumentoInterface.activarEstatus(SecurityContextHolder.getContext()
						.getAuthentication().getName(),dtoFirmados.getIdDocumento());
			}
			
			Integer idEdicion = null;
			if(dtoFirmados.getConModificaciones() == 1){
				logger.info("El documento "+dtoFirmados.getDtoDocumento().getNoDocumento()+" se editó");
				DTOEdicionesDocumentoEntity dtoEdiciones = boFirmaInterface.crearEdicion(dtoFirmados);
				
				idEdicion = daoEdicionesDocumentoInterface.obtenerIdEdicion(dtoFirmados.getIdDocumento());
				if (idEdicion == null ) {
					idEdicion = 1;
				} else {
					idEdicion = idEdicion + 1;
				}
				dtoEdiciones.setIdEdicion(idEdicion);
				
				daoEdicionesDocumentoInterface.guardar(dtoEdiciones);
				
				//dtoBorrador.setConModificaciones(Integer.valueOf(1));
				//daoBorradorDocumentosInterface.activarModificacion(dtoFirmados.getIdDocumento());
				estatusHistEnviado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_enviadoEditado"));
				
			}
			
			if(dtoFirmados.getDtoComentario() != null && dtoFirmados.getConModificaciones() == 1){
				estatusHistEnviado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_enviadoComentadoEditado"));
			}
			
			
			//SE ELIMINA DE FIRMA_DOCUMENTOS
			daoFirmaDocumentosInterface.eliminar(dtoFirmados);
			
			logger.info("El usuario que envió es "+dtoFirmados.getDtoEstructuraAreas().getCuentaLDAP());
			logger.info("El usuario que creó el documento es " + dtoFirmados.getDtoDocumento().getPersona().getCuentaLDAP());
			
			if(dtoFirmados.getDtoEstructuraAreas().getCuentaLDAP()==(dtoFirmados.getDtoDocumento().getPersona().getCuentaLDAP())){
				
				daoEnviadosDocumentosInterface.guardar(dtoCreado);
				logger.info("El usuario que envió el documento es el mismo que lo creo");
				
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(dtoFirmados.getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes = notificacion.getDocumentosPendientes3().intValue() + 1;
				notificacion.setDocumentosPendientes3(Integer.valueOf(documentosPendientes));
				daoNotificacionesInterface.modificar(notificacion);
				
			}else{
				
				logger.info("El usuario que envió el documento es diferente que lo creo");
				daoEnviadosDocumentosInterface.guardar(dtoEnviado);
				daoEnviadosDocumentosInterface.guardar(dtoCreado);
				
				DTONotificacionesEntity notificacion1 = daoNotificacionesInterface.consultarNotificacion(dtoFirmados.getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes1 = notificacion1.getDocumentosPendientes3().intValue() + 1;
				notificacion1.setDocumentosPendientes3(Integer.valueOf(documentosPendientes1));
				daoNotificacionesInterface.modificar(notificacion1);
				
				DTONotificacionesEntity notificacion2 = daoNotificacionesInterface.consultarNotificacion(dtoFirmados.getDtoDocumento().getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes2 = notificacion2.getDocumentosPendientes3().intValue() + 1;
				notificacion2.setDocumentosPendientes3(Integer.valueOf(documentosPendientes2));
				daoNotificacionesInterface.modificar(notificacion2);
				
			}
			
			DTOHistDocCreacionEntity dtoHistFirmado = boFirmaInterface.crearHistorial(dtoFirmados);
			DTOHistDocCreacionEntity dtoHistEnviado = boFirmaInterface.crearHistorial(dtoFirmados);
			
			Integer idHistorial = daoHistDocCreacionInterface.obtenerIdHistorial(dtoFirmados.getIdDocumento());
			if (idHistorial == null ) {
				idHistorial = 1;
			} else {
				idHistorial = idHistorial + 1;
			}
			if (idEdicion != null) {
				dtoHistEnviado.setIdEdicion(idEdicion);
			}
			if (dtoFirmados.getDtoComentario() != null) {
				dtoHistEnviado.setIdComentario(dtoFirmados.getDtoComentario().getIdComentario());
			}
			
			dtoHistFirmado.setIdEstatus(estatusHistFirmado);
			dtoHistFirmado.setIdHistorico(idHistorial);
			logger.info("h1 "+idHistorial);
			idHistorial = idHistorial + 1;
			dtoHistEnviado.setIdEstatus(estatusHistEnviado);
			dtoHistEnviado.setIdHistorico(idHistorial);
			logger.info("h2"+idHistorial);
			
			daoHistDocCreacionInterface.guardar(dtoHistFirmado);
			daoHistDocCreacionInterface.guardar(dtoHistEnviado);
			
			daoBorradorDocumentosInterface.eliminarPorIdDocumento(dtoFirmados.getIdDocumento());
			
			//SI EL DOCUMENTO NO HA SIDO LEIDO EN BORRADORES SE DECREMENTA EN NOTIFICACIONES AL ENVIAR A DESTINATARIO
			if(dtoBorrador.getNoLeido().intValue() == 1){
				
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(dtoBorrador.getIdPersona(),
						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				int documentosPendientes = notificacion.getDocumentosPendientes2().intValue() - 1;
				notificacion.setDocumentosPendientes2(Integer.valueOf(documentosPendientes));
				daoNotificacionesInterface.modificar(notificacion);
			}
			
			//ENVIANDO A OFICIALIA, TITULARES
			List<DTOBandejaEntradasOficialiaEntity> dtoListaDest = daoDestinatarioInterface.consultarPersonasOficialias(dtoFirmados.getIdDocumento());
			logger.info(dtoListaDest.size());
			if(opcionEnvio == 1 || opcionEnvio == 3){		
				for(DTOBandejaEntradasOficialiaEntity dtoDest : dtoListaDest){
					
						DTOBandejaEntradasOficialiaEntity dtoBandejaOficialia = boFirmaInterface.crearBandejaEntrada(dtoDest);
						dtoBandejaOficialia.setIdDocumento(dtoFirmados.getIdDocumento());
						daoBandejaEntradasOficialiaInterface.guardar(dtoBandejaOficialia);
				}	
					
			}
			
			//INSERTANDO EN NOTIFICACIONES OFICIALÍA
			List<DTONotificacionesOFEntity> notificacionesOF = daoDestinatarioInterface.consultarAreasDestinatarios(dtoFirmados.getIdDocumento());
			
			for(DTONotificacionesOFEntity notif : notificacionesOF){
				daoNotificacionesOFInterface.incrementar(notif);
			}

			//ENVIANDO A CCP
			dtoListaCcp = daoCcpInterface.obtenerPersonasCCP(dtoFirmados.getDtoDocumento());
			//daoBandejaECCPInterface.insertarCCP(dtoListaCcp, dtoFirmados.getDtoDocumento(), dtoFirmados.getDtoEstructuraAreas());
			
			//INSERTANDO EN NOTIFICACIONES A CCP
			//Se inserta en la bandeja de CCP y se actualiza la notificacion en la bandeja de entrada
			if (dtoListaCcp != null && dtoListaCcp.size() > 0) {
				for (DTOEstructuraAreasEntity ccp : dtoListaCcp) {
					daoBandejaECCPInterface.insertarCCP(ccp, dtoFirmados.getDtoDocumento(), dtoFirmados.getDtoEstructuraAreas());
					notificarEntrada(ccp, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
				}
			}
//			for(DTOEstructuraAreasEntity ccp : dtoListaCcp){
//				
//				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(ccp.getIdPersona(),
//						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
//				int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
//				notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
//				daoNotificacionesInterface.modificar(notificacion);
//				
//			}

			//ENVIANDO A TITULARES
			List<DTOEstructuraAreasEntity> dtoListaTitulares = daoJerarquiaPersonasInterface.consultarTitularesDestinatarios(dtoFirmados.getDtoDocumento(), Integer.parseInt(Utilidades.mensajeProperties("nivel_titular")));
			//daoBandejaERecibidosInterface.insertarRecibidos(dtoListaTitulares, dtoFirmados.getDtoDocumento(), dtoFirmados.getDtoEstructuraAreas());
			//Se inserta el documento en la bandeja de recibidos(Bandeja de los tiutulares) y se actualiza la notificación en la bandeja de entrada
			
			for (DTOEstructuraAreasEntity titular : dtoListaTitulares) {
				Integer idHistoricoRecep;
				DTOBandejaERecibidosEntity recibido;
				
				//Se insera en el historico de recepción
				dtoHistRecep = boBandejaSeguimientoInterface.crearHistoricoRecep(titular, dtoFirmados.getDtoDocumento(), null, Integer.parseInt(Utilidades.mensajeProperties("estatus_asignado_a")));
				idHistoricoRecep = daoHistDocRecepInterface.guardar(dtoHistRecep);
				
				//Se inserta el documento en la Bandeja de Recibidos
				recibido = boBandejaBorradoresInterface.crearDocumentoRecibido(titular, dtoFirmados.getDtoDocumento(), idHistoricoRecep);
				daoBandejaERecibidosInterface.guardar(recibido);
				notificarEntrada(titular, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
				
				//Se inserta en el historico de turno
				dtoHistTurno = boBandejaSeguimientoInterface.crearHistoricoTurno(titular, dtoFirmados.getDtoDocumento(), idHistoricoRecep, Integer.parseInt(Utilidades.mensajeProperties("id_estatus_noenterado")) , null);
				daoHistDocTurnoInterface.guardar(dtoHistTurno);
			}
//			//INSERTANDO EN NOTIFICACIONES A TITULARES
//			for(DTOEstructuraAreasEntity titulares : dtoListaTitulares){
//				
//				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(titulares.getIdPersona(),
//						Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
//				int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
//				notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
//				daoNotificacionesInterface.modificar(notificacion);
//				
//			}
			
			
			
			
			//REGISTRANDO ENVIO A DESTINATARIOS EN HISTORIAL
			List<DTODocumentoDestinatarioEntity> destinatarios = daoDestinatarioInterface.consutarDestinatarios(dtoFirmados.getIdDocumento());
			
			for(DTODocumentoDestinatarioEntity historial : destinatarios){
				idHistorial = idHistorial + 1;
				DTOHistDocCreacionEntity seEnvioA = boFirmaInterface.crearHistorial(dtoFirmados);
				seEnvioA.setIdHistorico(idHistorial);
				seEnvioA.setIdPersonaHist(historial.getIdPersonaDestinataria());
				seEnvioA.setIdEstatus(Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_seEnvioA")));
				daoHistDocCreacionInterface.guardar(seEnvioA);
			}
			
			//ACTUALIZANDO EL MENÚ
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			
			//SE TRANSFORMA A PDF EL DOCUMENTO FIRMADO
			boFirmaInterface.crearPdf(dtoFirmados.getDtoDocumento(), "documentos_firmados");
			
			logger.error("<============ PASO 5: CERRAR EL PROCESO DE FIRMADO   ==============> ");
			try {
				
			boFirmaMultilateral.cerrarProcesoCMS(dtoFirmados.getDtoFirmaElectronica().getIdProceso(), 1);
			
			}
			catch (Exception e) {
				logger.error("<============ NO SE PUDO CREAR EL DOCUMENTO  ==============> ");
			}
				
			}
		
		return mensajeError;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#modificarEdicionEnFirma(java.lang.Integer, java.lang.Integer)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void activarModificacionFirma(Integer idPersona, Integer idDocumento) {
		daoFirmaDocumentosInterface.bloquearDocumento(idDocumento, idPersona);
		daoFirmaDocumentosInterface.activarModificacionFirma(idPersona, idDocumento);
		
		daoDocumentoInterface.activarEdicionDocumento(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#eliminarValidacionEnDocumento(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void eliminarValidacionEnDocumento(DTOFirmaDocumentosEntity dtoFirma) {
		daoDocumentoInterface.eliminarValidacionEnDocumento(dtoFirma.getIdDocumento());
		daoFirmaDocumentosInterface.activarModificacionFirma(dtoFirma.getIdPersona(), dtoFirma.getIdDocumento());
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#activarVisualizacion(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void activarVisualizacion(Integer idPersona, Integer idDocumento) {
		daoFirmaDocumentosInterface.activarVisualizacion(idPersona, idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#marcarComoLeido(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void marcarComoLeido(Integer idPersona, Integer idDocumento, Integer totalDocumentos) {
		DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(idPersona,
				Integer.parseInt(Utilidades.mensajeProperties("id_modulo_firma")));
		int documentosPendientes = notificacion.getDocumentosPendientes().intValue() - 1;
		notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
		daoNotificacionesInterface.modificar(notificacion);
		daoFirmaDocumentosInterface.marcarComoLeido(idPersona, idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#marcarComoLeido(java.lang.Integer, java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void marcarComoLeido(Integer idPersona, List<DTOFirmaDocumentosEntity> noLeidos) {
		for(DTOFirmaDocumentosEntity dtoFirmaNoLeidos : noLeidos){
			marcarComoLeido(idPersona, dtoFirmaNoLeidos.getIdDocumento(), 0);
			dtoFirmaNoLeidos.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_leido")));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#verificarDocumentosFirmados(java.util.List)
	 */
	@Override
	public String verificarDocumentosFirmados(List<DTOFirmaDocumentosEntity> dtoDocsFirma) {
		
		String mensajeConfirmacion="";
		
		for(DTOFirmaDocumentosEntity dtoSeleccionado: dtoDocsFirma){
			
			if(daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getFirmado().intValue() == 1){
				
				mensajeConfirmacion = mensajeConfirmacion+", "+dtoSeleccionado.getDtoDocumento().getNoDocumento();
			}
		}
		
		return mensajeConfirmacion;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#verificarBloqueo(java.util.List)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarBloqueo(List<DTOFirmaDocumentosEntity> dtoFirmaBloqueo) {
		
		for(DTOFirmaDocumentosEntity dtoFirma : dtoFirmaBloqueo){
			DTOFirmaDocumentosEntity dtoBloqueo = daoFirmaDocumentosInterface.obtenerFirma(dtoFirma.getIdDocumento(), dtoFirma.getIdPersona());
			
			if(dtoBloqueo !=null && dtoBloqueo.getDtoPersonaBloqueado()!=null){
				
				return dtoBloqueo;
			}
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#verificarBloqueo(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarBloqueo(DTOFirmaDocumentosEntity dtoFirmaBloqueo) {
		
		DTOFirmaDocumentosEntity dtoBloqueo = daoFirmaDocumentosInterface.obtenerFirma(dtoFirmaBloqueo.getIdDocumento(), dtoFirmaBloqueo.getIdPersona());
		
		if(dtoBloqueo !=null && dtoBloqueo.getDtoPersonaBloqueado()!=null){
			
			return dtoBloqueo;
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#verificarRegresado(java.util.List)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarRegresado(List<DTOFirmaDocumentosEntity> dtoFirmaRegreso) {
		
		for(DTOFirmaDocumentosEntity dtoFirma : dtoFirmaRegreso){
			DTOFirmaDocumentosEntity dtoRegreso = daoFirmaDocumentosInterface.obtenerFirma(dtoFirma.getIdDocumento(), dtoFirma.getIdPersona());
			
			if(dtoRegreso !=null && dtoRegreso.getDtoPersonaRegreso()!=null){
				
				return dtoRegreso;
			}
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#obtenerDetalleDocumento(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception {
		return daoFirmaDocumentosInterface.obtenerDetalleDocumento(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#obtenerAnexoPorDocumento(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> obtenerAnexoPorDocumento(DTODocumentoEntity documento) throws Exception {
		return daoDocumentoAnexo.buscarPorDocumento(documento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#consultarHistorialPorIdDocumento(int)
	 */
	@Override
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception {
		return daoHistDocCreacionInterface.consultarHistorialPorIdDocumento(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#consultarComentarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception {
		return daoComentariosDocumentoInterface.consultarComentarios(dtoDocumentoEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASFirmaInterface#rechazarDocumentos(java.util.List)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public String rechazarDocumentos(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados) throws Exception {
		
		String mensajeError = "";
		
		Integer estatusHist = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_rechazado"));
		Integer estatusHistEditado = null;
		
		for(DTOFirmaDocumentosEntity dtoSeleccionado : dtoDocumentosSeleccionados){
			
			DTOBorradorDocumentosEntity dtoBorrador = daoBorradorDocumentosInterface.obtenerBorrador(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersonaRemitente());
			Integer idHistorial = daoHistDocCreacionInterface.obtenerIdHistorial(dtoSeleccionado.getIdDocumento());
			
			dtoBorrador.setConRechazo(Integer.valueOf(1));
			
			if(dtoSeleccionado.getDtoComentario() != null){
				
				dtoBorrador.setConComentarios(Integer.valueOf(1));
				
				//SE ACTIVA EL ESTATUS EN COMENTARIOS PARA QUE SEA VISTO POR LA BANDEJA
				daoComentariosDocumentoInterface.activarEstatus(SecurityContextHolder.getContext().getAuthentication().getName(),dtoSeleccionado.getIdDocumento());
				
			}
			
			Integer idEdicion = null;
			
			//SI EL DOCUMENTO TIENE MODIFICACIONES SE INSERTA EN LA TABLA DE EDICIONES 
			if(dtoSeleccionado.getConModificaciones() == 1){
				
				DTOEdicionesDocumentoEntity dtoEdiciones = boFirmaInterface.crearEdicion(dtoSeleccionado);
				
				idEdicion = daoEdicionesDocumentoInterface.obtenerIdEdicion(dtoSeleccionado.getIdDocumento());
				
				if (idEdicion == null ) {
					
					idEdicion = 1;
				
				} else {
					
					idEdicion = idEdicion + 1;
				}
				
				dtoEdiciones.setIdEdicion(idEdicion);
				daoEdicionesDocumentoInterface.guardar(dtoEdiciones);
				
				dtoBorrador.setConModificaciones(Integer.valueOf(1));
				dtoBorrador.setConValidacion(0);
				dtoBorrador.setNumEnviadoValidacion(null);
				dtoBorrador.setSiglasEnviados(null);
				
				estatusHistEditado = Integer.parseInt(Utilidades.mensajeProperties("documento_estatus_regresadoEditado"));
				
				DTOHistDocCreacionEntity dtoHist = boFirmaInterface.crearHistorial(dtoSeleccionado);	
				
				if (idHistorial == null ) {
					
					idHistorial = 1;
				
				} else {
					
					idHistorial = idHistorial + 1;
				}
				
				dtoHist.setIdEstatus(estatusHistEditado);
				dtoHist.setIdHistorico(idHistorial);
				dtoHist.setIdEdicion(idEdicion);
				
				if (dtoSeleccionado.getDtoComentario() != null) {
					dtoHist.setIdComentario(dtoSeleccionado.getDtoComentario().getIdComentario());
				}
				
				daoHistDocCreacionInterface.guardar(dtoHist);
				
				
			}
			
			//SE REGISTRA EN EL HISTORIAL
			DTOHistDocCreacionEntity dtoHist = boFirmaInterface.crearHistorial(dtoSeleccionado);
			
			
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
			
			//SE VERIFICA QUE NO SE HAYA MODIFICADO EL DOCUMENTO
			if((daoDocumentoInterface.consultarDocumento(dtoSeleccionado.getIdDocumento()).getEditado()) == 1){
				boFirmaInterface.crearPdf(dtoSeleccionado.getDtoDocumento(),"documentos");
				daoDocumentoInterface.desactivarEdicionDocumento(dtoSeleccionado.getIdDocumento());
			}
			daoFirmaDocumentosInterface.desbloquearDocumento(dtoSeleccionado.getIdDocumento(), dtoSeleccionado.getIdPersona());
			daoFirmaDocumentosInterface.eliminar(dtoSeleccionado);
			
			//SE VERIFICA QUE EL DOCUMENTO SEA EL ÚLTIMO EN REGRESARSE
			if(dtoBorrador.getNumEnviadoFirma().intValue() == 1){
				
				dtoBorrador.setFechaEnvio(null);
				dtoBorrador.setFechaRegreso(null);
				dtoBorrador.setNumEnviadoFirma(null);
			
			}else{
				
				//SE DECREMENTA EL NUMERO DE FIRMANTES
				dtoBorrador.setNumEnviadoFirma(dtoBorrador.getNumEnviadoFirma() - 1);
			}
			 
			if(dtoBorrador.getNoLeido().intValue() == 0) {
				
				dtoBorrador.setNoLeido(1);
				
				DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(dtoBorrador.getIdPersona(), Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
				
				int documentosPendientes = notificacion.getDocumentosPendientes2().intValue() + 1;
				
				notificacion.setDocumentosPendientes2(Integer.valueOf(documentosPendientes));
				
				daoNotificacionesInterface.modificar(notificacion);
			}
			
			daoBorradorDocumentosInterface.modificar(dtoBorrador);
		}
		
		return mensajeError;
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

}
