package mx.ine.gestion.bo.impl;

import mx.ine.gestion.seguridad.SeguriSignServiceImpl;

import org.jboss.logging.Logger;

import seguridata.segurisign.service.SSignDocument;
import seguridata.segurisign.service.SSignEvidence;
import seguridata.segurisign.service.SeguriSignService;

public class BOFirmaUnilateral {

	/**
	 * Elemento necesario para la serializaciÃ³n de los objetos generados de esta clase.
	 */
	public static final Logger logger = Logger.getLogger(BOFirmaUnilateral.class);

	/**
	 * Clase para obtener la ruta del servidor donde se firma la informaciÃ³n
	 */
	private SeguriSignServiceImpl serviceImpl;

	/**
	 * Clase para mandar llamar el webservice con los métodos para firmar
	 */
	private SeguriSignService service;

	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------- INICIO - EVENTOS -------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */

	/**
	 * Se sobreescribe clase para inicializar variables.
	 */
	public BOFirmaUnilateral() {

		serviceImpl = new SeguriSignServiceImpl();
		service = serviceImpl.getSeguriSignServicePort();
	}

	/**
	 * Método principal para firmar archivos de manera unilateral, a través del webservice proporcionado por SeguriSign
	 * y con los valores generados en pantalla (pcks7, pcks1, nÃºmero de serie, etc.), estos valores fueron generados con javascript,
	 * si necesitan ver la funcionalidad de como se obtienen dirigirse a el archivo JS "firmaElectronicaPautas.js".
	 * 
	 * @param pkcs7: Cadena generada en pantalla a través del certificado, llave privada y cadena original del archivo que se quiere firmar.
	 * @param numeroDeSerie: Número de serie que se obtiene en pantalla a través del certificado con el que firma el usuario.
	 * @param labelRequest: Nombre/cadena asociado a la firma, en este caso usaremos el idMedio relacionado a la orden de transmisión, pero puede ser cualquier cosa. 
	 * @param nombreArchivo: Nombre del archivo que se firmara (nombre del excel que tiene la orden de transmisiÃ³n).
	 * @param cadenaOriginal: cadena a validar (La cadena de compone del nombre del archivo junto con el hash, 
	 * 						  separado por pipes, dos al inicio y final, y uno en medio)
	 * @return String: Identificador para saber si se firmo correctamente o hubo un error, los errores son los valores 0,-1,-2,-3 y -4, cualquier otro valor es la secuencia
	 * 				   devuelta por SeguriSign que utiliza para registrar lo firmado.
	 * 
	 * @author Victor del Carmen Robles
	 * @update Roberto ShirÃ¡sago DomÃ­nguez
	 */
	public String firmarUnilateral(String pkcs7, String numeroDeSerie, String labelRequest, String nombreArchivo, String cadenaOriginal) throws Exception {

		logger.error("<================ ENTRO al método de la firma unilateral(BOFirmaUnilateral), CON LOS VALORES: ");
		logger.error("<================ PKCS7: " + pkcs7);
		logger.error("<================ NUMERODESERIE: " + numeroDeSerie);
		logger.error("<================ LABELREQUEST: " + labelRequest);
		logger.error("<================ NOMBREARCHIVO: " + nombreArchivo);
		logger.error("<================ CADENAORIGINAL: " + cadenaOriginal);

		try {

			// Crear objetos de Request para la firma
			SSignDocument signedMessageRequest = new SSignDocument();
			SSignDocument externContentRequest = new SSignDocument();

			// Llenar el primer objeto Request con la informaciÃ³n de la firma
			byte[] signedMessage = pkcs7.getBytes();
			signedMessageRequest.setBase64(false);
			signedMessageRequest.setData(signedMessage);
			signedMessageRequest.setFileName(nombreArchivo);

			// Llenar el segundo objeto Request con la informaciÃ³n que se quiere comparar
			externContentRequest.setBase64(false);
			externContentRequest.setData(cadenaOriginal.getBytes());

			// Enviar datos al webservice de segurisign
			if(service != null) {

				SSignEvidence stamp = service.authenticatePKCS7(signedMessageRequest, numeroDeSerie, labelRequest, externContentRequest);
				String idSecuencia = stamp.getSequence();

				logger.error("<================ ID_SECUENCIA SEGURISIGN GENERADO: " + idSecuencia);

				return idSecuencia;

			} else {

				logger.error("<=================== NO SE PUDO ENVIAR LA INFORMACIÃ“N AL WEBSERVICE, POSIBLEMENTE SE DESCONECTO/ SE PERDIO LA RED");
				logger.error("<=================== LA CLASE 'SeguriSignService' es NULL ");

				throw new Exception("NOCONEXION");
			}

		} catch(Exception e) {

			logger.error("<================ ERROR al ejecutar el webservice de SSign ");
			logger.error("",e);

			String mensaje = e.getMessage();

			if(mensaje.contains("El emisor del certificado del firmante no es de confianza")) {
				throw new Exception("NOCONFIANZA");
			} else if(mensaje.contains("Par\u00e1metro Folio incorrecto")) {
				throw new Exception("FOLIOINCORRECTO");
			} else if(mensaje.contains("La informaci\u00f3n tiene formato inv\u00e1lido")) {
				throw new Exception("FORMATOINVALIDO");
			}

			throw new Exception("DESCONOCIDO");
		}
	}
	
}
