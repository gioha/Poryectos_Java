package mx.ine.acuerdos.util;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;

import mx.org.ine.servicios.exception.CorreoNoEnviadoException;

import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 * Servicio encargado del envio de correos.
 * 
 * @author Luis guichosun del Campo
 * @since Junio 2014
 * @copyright Direccion de sistemas - IFE
 */
public class ServicioPostal {
	
	private static final Logger log = Logger.getLogger(ServicioPostal.class);
	
	private static String usernameFrom;
	
	private static String passwordFrom;
	
	private static String cuentaDeEnvio;
	
	static {
		try {
//			cuentaDeEnvio = Utilidades.mensajePropertiesConstants("application_correo_cuenta");
//			usernameFrom = Utilidades.mensajePropertiesConstants("application_correo_usuario");
//			passwordFrom = Utilidades.mensajePropertiesConstants("application_correo_password");
			
			cuentaDeEnvio = "jair.lopezj@ine.mx";
			usernameFrom = "jair.lopezj";
			passwordFrom = "J41r_L0p3z";

		} catch(Exception e) {
			log.error("No se ha configurado la cuenta del sistema. Se usa una por defecto", e);
			//cuentaDeEnvio = Utilidades.mensajeProperties("constante_correo_organizaciones_locales");
		}
		log.info("cuentaDeEnvio: "+cuentaDeEnvio+". usernameFrom: "+usernameFrom+". passwordFrom"+passwordFrom);
	}
	
	public ServicioPostal() {
	}
	
	/**
	 * Utileria para el envio de correos electronicos
	 * 
	 * @param asunto
	 * @param contenido
	 * @param arrayTO
	 * @throws NotSendMailException Si no fue posible enviar el correo
	 */
	public static void envia(String asunto, String contenido, List<String> arrayTO) 
			throws CorreoNoEnviadoException {
		
		Mensajero mensajero = Mensajero.getInstancia(usernameFrom, passwordFrom, cuentaDeEnvio);
        try {	
        	mensajero.envioMensajeSimple(asunto, contenido, arrayTO);
//        } catch (MessagingException e) {
        } catch (Exception e) {
			log.error("Mail no enviado", e);
        	throw new CorreoNoEnviadoException("Mail no enviado.", e);
			
		}
	}

	public static void enviarCorreoConAdjunto(String asunto, String contenido,
											  List<String> usuariosTo, UploadedFile archivoAdjuntoPDF)
							  				  throws CorreoNoEnviadoException {
		Mensajero mensajero = Mensajero.getInstancia(usernameFrom, passwordFrom, cuentaDeEnvio);
        try {
        	mensajero.enviarMensajeConAdjunto(asunto, contenido, usuariosTo, archivoAdjuntoPDF);
        } catch (Exception e) {
			log.error("Mail no enviado", e);
        	throw new CorreoNoEnviadoException("Mail no enviado.", e);
		}
	}

	/**
	 * @param asunto identificador del asunto del mensaje
	 * @param contenido identificador del contenido del mensaje
	 * @param usuariosTo  ArrayList que contiene cadenas (String) con los emails de los usuarios que van a ser TO.
	 * @param archivos  ArrayList que contiene los archivos (File) con las rutas completas de los archivos que se quieren adjuntar.
	 * @throws Exception Excepciï¿½n generica que me permite cachar cualquier 
	 * excepcion ocurrida en el proceso y enmascarla para presentarla al cliente.
	 */
	public static void envioMensajeConAdjunto(String asunto, String contenido,
											  List<String> usuariosTo, List<File> archivos) throws Exception {

		Mensajero mensajero = Mensajero.getInstancia(usernameFrom, passwordFrom, cuentaDeEnvio);
        try {	
        	mensajero.envioMensajeConAdjunto(asunto, contenido, usuariosTo, archivos);
        } catch (MessagingException e) {
			e.printStackTrace();
			log.error("ServicioPostal.envioMensajeConAdjunto: " + e.getMessage());
        	throw new CorreoNoEnviadoException("Mail no enviado.", e);
		}
	}

}

