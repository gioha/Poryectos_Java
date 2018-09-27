package mx.ine.acuerdos.util;


import java.io.File;
import java.util.List;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;



import org.primefaces.model.UploadedFile;

import mx.org.ine.servicios.exception.CorreoNoEnviadoException;

/**
 * Class para contar y notificar via correo electronico.
 * 
 * Esta class estï¿½ sincronizada
 * 
 * @author Luis "guichosun" del Campo
 * @since Abril 2013
 * @copyright Direcciï¿½n de sistemas - IFE
 */
public abstract class Notificador {

	/** Lista de correos a los cuales se les enviara el reporte */
	protected List<String> correos;
	
	/** El asunto de la notiificacion */
	protected StringBuilder asunto;
	
	/** El cuerpo de la notiificacion */
	protected StringBuilder cuerpo;
	
	/** El archivo adjunto del correo */
	protected List<File> adjuntos;

	/**
	 * Constructor que inicializa el mapa con los contadores
	 */
	public Notificador() {
		
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	    mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	    CommandMap.setDefaultCommandMap(mc);
	    
		asunto = new StringBuilder();
		cuerpo = new StringBuilder();
	}
	
	/**
	 * @param correos
	 */
	public Notificador(List<String> correos) {
		this();
		this.correos = correos;
	}

	/**
	 * Envia la notificacion con el reporte del procesamiento de los asistentes
	 */
	public void enviaNotification() throws CorreoNoEnviadoException {
		
		
		getAsuntoCorreo();
		getCuerpoCorreo();
		
		ServicioPostal.envia(asunto.toString(), cuerpo.toString(), correos);
	}

	public void enviarNotificacionConAdjunto(UploadedFile archivoAdjuntoPDF) throws CorreoNoEnviadoException {
		getAsuntoCorreo();
		getCuerpoCorreo();
		ServicioPostal.enviarCorreoConAdjunto(asunto.toString(), cuerpo.toString(), correos, archivoAdjuntoPDF);
	}

	public void enviaNotificationException() throws CorreoNoEnviadoException {
		
		
		getAsuntoCorreo();
		getCuerpoCorreoException();
		
		ServicioPostal.envia(asunto.toString(), cuerpo.toString(), correos);
	}
	
	
	
	/**
	 * Envia la notificacion con el reporte del procesamiento de los asistentes
	 * @throws Exception 
	 */
	public void enviaNotificationConAdjunto() throws Exception {
		getAsuntoCorreo();
		getCuerpoCorreo();

		ServicioPostal.envioMensajeConAdjunto(asunto.toString(), cuerpo.toString(), correos, adjuntos);
	}

	/**
	 * Arma el asunto del correo
	 */
	protected abstract void getAsuntoCorreo();
	
	/**
	 * Arma el cuerpo del correo
	 */
	protected abstract void getCuerpoCorreo();
	
	protected abstract void getCuerpoCorreoException();

	public List<String> getCorreos() {
		return correos;
	}
	
	/**
	 * El notificador puede mutar los correos a notificar
	 * @param correos
	 * @param exitStatus 
	 */
	public void setCorreos(List<String> correos) {
		this.correos = correos;
	}

	/**
	 * @return the adjuntos
	 */
	public List<File> getAdjuntos() {
		return adjuntos;
	}

	/**
	 * @param adjuntos the adjuntos to set
	 */
	public void setAdjuntos(List<File> adjuntos) {
		this.adjuntos = adjuntos;
	}

}

