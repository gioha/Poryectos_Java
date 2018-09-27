/**
 * @(#)CorreosGestion.java 20/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 * Clase que proporciona el servicio postal para el envío de correos electrónicos.
 * @author José Miguel Ortiz
 * @since 20/04/2018
 */
public class ServicioPostal implements Serializable {
	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 4947685282055831516L;
	private static final Logger log = Logger.getLogger(ServicioPostal.class);

	/* ------------------------------ Atributos generales ------------------------------ */
	/**
	 * Atributos que contiene la información acerca del usuario del remitente
	 */
	private String usuarioRemitente = "jair.lopezj";
	private String contraseniaRemitente = "J41r_L0p3z";
//	private String correoRemitente = "jair.lopezj@ine.mx";

	/**
	 * Atributo que contiene la información para el envío de un correo electrónico
	 */
	private final static String MAIL_JNDI = "java:jboss/MailSOA";
	private final static String PROTOCOLO_SMPT = "smtp";
	private static Map<String, Session> sesionCache = new HashMap<String, Session>();

	/**
	 * Método encargado de recuperar una sesión para el envío de un correo electrónico.
	 * @return Session: sesión recuperada
	 * @author José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public Session recuperarSesion() {
		Session session = null;
		Context contexto = null;

		try {
			// 1. El caché no contiene al servicio
			if(sesionCache.containsKey(MAIL_JNDI)) {
				session = sesionCache.get(MAIL_JNDI);
			} else {
				contexto = new InitialContext();
				session = (Session) contexto.lookup(MAIL_JNDI);
				sesionCache.put(MAIL_JNDI, session);
			}
		} catch(Exception e) {
			log.error("ServicioPostal.recuperarSesion: " + e.getMessage());
		}

		return session;
	}

	/**
	 * Método encargado de enviar un correo electrónico.
	 * @param (String asuntoCorreo, String cuerpoCorreo, List<String> listaCorreos, UploadedFile archivoAdjunto
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 26/04/2018
	 */
	public void enviarCorreoNotificacion(String asuntoCorreo, String cuerpoCorreo,
									    List<String> listaCorreos, UploadedFile archivoAdjunto)
										throws Exception {
		Session session = recuperarSesion();
		MimeMessage message = new MimeMessage(session);
		MimeMultipart multipart = new MimeMultipart("related");
		BodyPart messageBodyPart;

		messageBodyPart = new MimeBodyPart();
		String htmlText = cuerpoCorreo;
		messageBodyPart.setContent(htmlText, "text/html");
		multipart.addBodyPart(messageBodyPart);
							 		
// 		messageBodyPart = new MimeBodyPart();
// 		DataSource fds;
// 		String rutaImgs = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/Acuerdos01.png");
//		String pathLogoAcuerdos = rutaImgs.replace("/", File.separator);
//		fds = new FileDataSource(pathLogoAcuerdos);
//		messageBodyPart.setDataHandler(new DataHandler(fds));
//		messageBodyPart.setHeader("Content-ID", "<image>");
//		multipart.addBodyPart(messageBodyPart);

		if(archivoAdjunto != null) {
			ByteArrayDataSource outputStream = new ByteArrayDataSource(archivoAdjunto.getInputstream(), "application/pdf");
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(outputStream));
			messageBodyPart.setFileName(archivoAdjunto.getFileName());
			multipart.addBodyPart(messageBodyPart);
		}

		message.setContent(multipart);
		message.setFrom(new InternetAddress("gestion.documentos@ine.mx", "Gestión de documentos"));
		message.setSubject(asuntoCorreo);

		for(String correo : listaCorreos) {
			message.addRecipients(Message.RecipientType.TO, correo);
		}

		// Almacena los cambios en el mensaje.
		message.saveChanges();

		Transport transport = session.getTransport(PROTOCOLO_SMPT);

        try {
        	transport.connect(usuarioRemitente, contraseniaRemitente);
        	transport.sendMessage(message, message.getAllRecipients());
        }catch(Exception e){
        	log.error("ServicioPostal.enviarCorreoNotificacion: " + e.getMessage());
        } finally {
        	transport.close();
        }
	}

}
