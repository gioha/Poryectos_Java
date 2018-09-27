package mx.ine.acuerdos.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
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
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * 
 * Singleton encargado de enviar correos.
 * 
 * @author Luis guichosun del Campo & Antonio Lopez
 * @copyright Direcciï¿½n de sistemas - IFE
 * @version 1.0
 */
public final class Mensajero {
	
	private static final Logger log = Logger.getLogger(Mensajero.class);
	
	/* El unico ejemplar */
	private static Mensajero instancia;
	
//	/* La direccion del host */
//	private static String host;
//	
//	/* El servidor smtp */
//	private static String smtp;
	@Value("${application.correo.cuenta}")
	private String usernameFrom;
	@Value("${application.correo.password}")
	private String passwordFrom;
	@Value("${application.correo.usuario}")
	private String userFrom;
	
	private final static String MAIL_JNDI = "java:jboss/MailSOA";
	
	private final static String PROTOCOLO_SMPT = "smtp";
	
	/** El cache que tendra la session */
	private static Map<String, Session> dataSourceCache;
	
	static {
		
//		/* Se recuperan valores del servidor */
//		
//		try {
//			host = (String)LocalizadorRecursos.
//	    			getInstance().obtenValorRecurso(Recurso.CORREO_HOST);
//			smtp = (String)LocalizadorRecursos.
//					getInstance().obtenValorRecurso(Recurso.CORREO_SMTP);
//			
//		} catch (ApplicationException e) {
//			throw new IllegalStateException(e.getMessage());
//		}
	}

	public Mensajero(){
		//Inicializa valores
				MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
				mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			    mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			    CommandMap.setDefaultCommandMap(mc);
	}
	
	/**
	 * Para evitar instancias externas
	 */
	private Mensajero(String usernameFrom, String passwordFrom, String userFrom) {

		this.usernameFrom = usernameFrom;
		this.passwordFrom = passwordFrom;
		this.userFrom = userFrom;
		
		dataSourceCache = new HashMap<String, Session>();
		
		log.info("Construccion del Mensajero");
	}

	/**
	 * Para recuperar el Mensajero
	 */
	public static Mensajero getInstancia(String usernameFrom, String passwordFrom, String userFrom) {
		log.info("Entra a getInstancia()");
		if(instancia == null) {
			instancia = new Mensajero(usernameFrom, passwordFrom, userFrom);
			return instancia;
		} else {
			return instancia;	
		}
		
	}
	
	/**
	 * Recupera una conexion a la base de datos, atravez del DS
	 * @param resource
	 * @return
	 * @throws ServiceLocatorException
	 */
	public Session getSession() {
		
		try {
			// Se revisa que la cache no contenga el servicio
			if(dataSourceCache.containsKey(MAIL_JNDI)) {
				Session ds = dataSourceCache.get(MAIL_JNDI);
//				conn = ds.getConnection();
				log.info("================= Obtiene Session de cache");
				return ds;
			} else {
				Context ctx = new InitialContext();
				Session ds =(Session)ctx.lookup(MAIL_JNDI);
				dataSourceCache.put(MAIL_JNDI, ds);
//				conn = ds.getConnection();
				log.info("================= Obtiene Session de jndi");
				return ds;
			}
		} catch (Exception e) {
//			throw new ServiceLocatorException("No es posible recuperar el recurso mediante el nombre JNDI: "+serviceName+".",e);
			log.error("error en getSession()", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param asunto identificador del asunto del mensaje
	 * @param contenido identificador del contenido del mensaje
	 * @param usuariosTo cuentas de correos que seran enviados.
	 * @param cuentaDeEnvio identificador de la cuenta de envï¿½o
	 * @throws IOException 
	 * @throws Exception Excepciï¿½n generica que me permite cachar cualquier 
	 * excepcion ocurrida en el proceso
	 */
	public void envioMensajeSimple(String asunto, String contenido, 
			List<String> usuariosTo) throws MessagingException, IOException {
		log.info("Entro a envioMensajeSimple()");
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
		
		
							MimeMultipart multipart = new MimeMultipart("related");
							BodyPart messageBodyPart = new MimeBodyPart();
							String htmlText = contenido;
							messageBodyPart.setContent(htmlText, "text/html");
							 
							 		multipart.addBodyPart(messageBodyPart);
							 		
							 		messageBodyPart = new MimeBodyPart(); 
							 		DataSource fds;
						 		
							 		String rutaImgs = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/Acuerdos01.png");
							 		String pathLogoAcuerdos = rutaImgs.replace("/", File.separator);							 	
							 		
							 			 fds = new FileDataSource(pathLogoAcuerdos);
							 		
									messageBodyPart.setDataHandler(new DataHandler(fds));
							        messageBodyPart.setHeader("Content-ID", "<image>");
							        
							        multipart.addBodyPart(messageBodyPart);
							        message.setContent(multipart);

											
		message.setFrom(new InternetAddress("seguimiento.acuerdos@ine.mx"));	    
		message.setSubject(asunto);
		//message.setSentDate(new Date());	
		//message.setText(contenido);	
		
		
		
		//message.setContent(contenido, "text/html; charset=utf-8");	
		
		
		
		for(String cuenta : usuariosTo) {
		
			//if ( cuenta.equalsIgnoreCase("xochiquetzal.hernand@ine.mx") || cuenta.equalsIgnoreCase("lizbeth.vargasl@ine.mx") 
			//		|| cuenta.equalsIgnoreCase("jorge.luna@ine.mx") ){
				message.addRecipients(Message.RecipientType.TO, cuenta);												
			//}
		
		}
		
		
		
		// almacena los cambios en el mensaje.
		message.saveChanges();

		Transport transport = session.getTransport(PROTOCOLO_SMPT);
		
        try {
        	log.info("Antes de conectar al Transport");
           transport.connect(usernameFrom, passwordFrom);
           log.info("Despues de transport.connect");
           log.info("Antes de transport.sendMessage");
           transport.sendMessage(message, message.getAllRecipients());
           log.info("Despuest transport.sendMessage");    
        }catch(Exception e){
        	log.info("Ocurrio un error en MBInfoGralSesion.enviaNotification", e);
        	//util.mostrarMensaje(Constantes.msjError, El correo no fue enviado correctamente.");
        } finally {
               transport.close();
        }
	}

	public void enviarMensajeConAdjunto(String asunto, String contenido,
									    List<String> usuariosTo, UploadedFile archivoAdjuntoPDF)
										throws MessagingException, IOException {
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
		MimeMultipart multipart = new MimeMultipart("related");
		BodyPart messageBodyPart;
		ByteArrayDataSource flujoEntrada;

		messageBodyPart = new MimeBodyPart();
		String htmlText = contenido;
		messageBodyPart.setContent(htmlText, "text/html");
		multipart.addBodyPart(messageBodyPart);
							 		
 		messageBodyPart = new MimeBodyPart();
 		DataSource fds;
 		String rutaImgs = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/Acuerdos01.png");
		String pathLogoAcuerdos = rutaImgs.replace("/", File.separator);
		fds = new FileDataSource(pathLogoAcuerdos);
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<image>");
		multipart.addBodyPart(messageBodyPart);

		messageBodyPart = new MimeBodyPart();
		flujoEntrada = new ByteArrayDataSource(archivoAdjuntoPDF.getInputstream(), "application/pdf");
		messageBodyPart.setDataHandler(new DataHandler(flujoEntrada));
		messageBodyPart.setFileName(archivoAdjuntoPDF.getFileName());
		multipart.addBodyPart(messageBodyPart);

		message.setContent(multipart);
		message.setFrom(new InternetAddress("seguimiento.acuerdos@ine.mx"));	    
		message.setSubject(asunto);

		for(String cuenta : usuariosTo) {
			message.addRecipients(Message.RecipientType.TO, cuenta);
		}

		// Almacena los cambios en el mensaje.
		message.saveChanges();

		Transport transport = session.getTransport(PROTOCOLO_SMPT);

        try {
        	transport.connect(usernameFrom, passwordFrom);
        	transport.sendMessage(message, message.getAllRecipients());
        }catch(Exception e){
        	log.info("Ocurrio un error en Mensajero.enviarMensajeConAdjunto", e);
        } finally {
        	transport.close();
        }
	}

	/**
	 * 
	 * @param asunto identificador del asunto del mensaje
	 * @param contenido identificador del contenido del mensaje
	 * @param usuariosTo cuentas de correos que seran enviados.
	 * @param cuentaDeEnvio identificador de la cuenta de envÃ¯Â¿Â½o
	 * @param archivos archivos a adjuntar en el envÃƒÂ­o
	 * @throws Exception ExcepciÃ¯Â¿Â½n generica que me permite cachar cualquier 
	 * excepcion ocurrida en el proceso
	 */
	public void envioMensajeConAdjunto(String asunto, String contenido, List<String> usuariosTo, 
			String cuentaDeEnvio, List<File> archivos) throws MessagingException {
		
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
						
		message.setFrom(new InternetAddress(userFrom));		    
		message.setSubject(asunto);
		message.setSentDate(new Date());	
		message.setText(contenido);
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(contenido);
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		 for( int i = 0; i < archivos.size(); i++ ){
			 File file = (File) archivos.get(i); 
			 messageBodyPart = new MimeBodyPart();
			 DataSource source = new FileDataSource(file.getAbsolutePath());
			 messageBodyPart.setDataHandler(new DataHandler(source));
			 messageBodyPart.setFileName( file.getName() );
			 multipart.addBodyPart(messageBodyPart);
			 // Put parts in message
			 message.setContent(multipart);
		 }
				
		for(String cuenta : usuariosTo) {
			message.addRecipients(Message.RecipientType.TO, cuenta);
		}
		
		// almacena los cambios en el mensaje.
		message.saveChanges();
		
		Transport transport = session.getTransport(PROTOCOLO_SMPT);
		try {
        	
	           transport.connect(usernameFrom, passwordFrom);
	           transport.sendMessage(message, message.getAllRecipients());
	               
//	               util.mostrarMensaje(Constantes.msjExito,
//							" El correo fue enviado correctamente a la lista de correos.");
	        }catch(Exception e){
	        	log.error("Ocurrio un error en al enviar correo", e);
//	        	Utilidades
//				.enviaMensajeGeneral(
//						"growl",
//						"Fallo",
//						"etiqueta_generales_error_correo",
//						FacesMessage.SEVERITY_ERROR);
	        	
	        } finally {
	               transport.close();
	        }
	}

	public void envioMensajeConAdjunto(String asunto, String contenido, 
									   List<String> usuariosTo, List<File> archivos) throws MessagingException, IOException {
		log.info("Entro a envioMensajeSimple()");
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
		
		
							MimeMultipart multipart = new MimeMultipart("related");
							BodyPart messageBodyPart = new MimeBodyPart();
							String htmlText = contenido;
							messageBodyPart.setContent(htmlText, "text/html");
							 
							 		multipart.addBodyPart(messageBodyPart);
							 		
							 		messageBodyPart = new MimeBodyPart(); 
							 		DataSource fds;
						 		
							 		String rutaImgs = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/Acuerdos01.png");
							 		String pathLogoAcuerdos = rutaImgs.replace("/", File.separator);							 	
							 		
							 			 fds = new FileDataSource(pathLogoAcuerdos);
							 		
									messageBodyPart.setDataHandler(new DataHandler(fds));
							        messageBodyPart.setHeader("Content-ID", "<image>");
							        
							        multipart.addBodyPart(messageBodyPart);
							   
							        
							        String rutaImgs2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/prueba.pdf");
							 		String pathLogoAcuerdos2 = rutaImgs2.replace("/", File.separator);
							 		DataSource fds2 = new FileDataSource(pathLogoAcuerdos2);
							        BodyPart adjunto = new MimeBodyPart();
						            adjunto.setDataHandler(new DataHandler(fds2));
						            adjunto.setFileName("prueba.pdf");
						            multipart.addBodyPart(adjunto);
							        
							        
							        
							        message.setContent(multipart);

											
		message.setFrom(new InternetAddress("seguimiento.acuerdos@ine.mx"));	    
		message.setSubject(asunto);
		//message.setSentDate(new Date());	
		//message.setText(contenido);	
		
		
		
		//message.setContent(contenido, "text/html; charset=utf-8");	
		
		
		
		for(String cuenta : usuariosTo) {
		
			//if ( cuenta.equalsIgnoreCase("xochiquetzal.hernand@ine.mx") || cuenta.equalsIgnoreCase("lizbeth.vargasl@ine.mx") 
			//		|| cuenta.equalsIgnoreCase("jorge.luna@ine.mx") ){
				message.addRecipients(Message.RecipientType.TO, cuenta);												
			//}
		
		}
		
		
		
		// almacena los cambios en el mensaje.
		message.saveChanges();

		Transport transport = session.getTransport(PROTOCOLO_SMPT);
		
        try {
        	log.info("Antes de conectar al Transport");
           transport.connect(usernameFrom, passwordFrom);
           log.info("Despues de transport.connect");
           log.info("Antes de transport.sendMessage");
           transport.sendMessage(message, message.getAllRecipients());
           log.info("Despuest transport.sendMessage");    
        }catch(Exception e){
        	log.info("Ocurrio un error en MBInfoGralSesion.enviaNotification", e);
        	//util.mostrarMensaje(Constantes.msjError, El correo no fue enviado correctamente.");
        } finally {
               transport.close();
        }
	}

}
