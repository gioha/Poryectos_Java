/**
 * @(#)BOFirmaMultilateral.java 16/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


import mx.ine.gestion.seguridad.SeguriSignServiceImpl;
import mx.ine.gestion.seguridad.SeguriSignService_Service;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

import seguridata.segurisign.service.MultiSignedMessageIn;
import seguridata.segurisign.service.MultiSignedMessageOut;
import seguridata.segurisign.service.MultiSignedMessageUpdate;
import seguridata.segurisign.service.SSignDocument;
import seguridata.segurisign.service.SSignEvidence;
import seguridata.segurisign.service.SeguriSignService;

/**
 * Clase para consumir los webservices de la firma electronica de manera MULTILATERAL
 * 
 * @author Roberto Shirásago Domínguez
 * 
 * @since 16/10/2017
 */
public class BOFirmaMultilateral {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	public static final Logger logger = Logger.getLogger(BOFirmaMultilateral.class);
	
	/**
	 * Clase para obtener la ruta del servidor donde se firma la información
	 */
	private SeguriSignService_Service serviceImpl;

	/**
	 * Clase para mandar llamar el webservice con los métodos para firmar
	 */
	private SeguriSignService service;

	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------- INICIO - EVENTOS -------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */

	public BOFirmaMultilateral() {
		
		serviceImpl = new SeguriSignService_Service();
		service = serviceImpl.getSeguriSignServicePort();
	}
	
    public static byte[] getBytesFromFile(File file) throws FileNotFoundException, IOException{
		FileInputStream fis = null;
		byte[] bytes = null;
		try {
			fis = new FileInputStream(file);
			bytes = new byte[(int) file.length()];
			fis.read(bytes);
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				logger.error("Error al transformar a Bytes");
				return null;
			}
		}
		return bytes;
	}
    
    public byte[] hexStringToByteArray(String s){
        int len = s.length();
        byte [] data = new byte[len/2];
        for(int i = 0; i < len; i += 2){
             data[i/2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
     }
    
    /**
	 * Método que inicia (1er paso) el proceso para firma de pdf's con PDFSignature
	 * 
	 * @param nombreArchivo: nombre del pdf a firmar
	 * @param numeroFirmantes: Número de firmantes
	 * @param algoritmo: Algoritmo del certificado (SHA1, SHA-256) 
	 * 
	 * @return String: Cadena que contiene el id de proceso
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
    public String iniciar(String nombreArchivo, Integer numeroFirmantes, String algoritmo) throws FileNotFoundException, IOException{
    	
    	File archivoPDF = new File(Utilidades.getRutaGlusterFS() + "/Gestion4/pdf/" + nombreArchivo+".pdf");
		
		if (archivoPDF.exists()) {
		
			MultiSignedMessageIn parametros = new MultiSignedMessageIn();
			parametros.setData(getBytesFromFile(archivoPDF));
			parametros.setDataInfo(nombreArchivo);
			parametros.setSignersNum((short)+numeroFirmantes);
			parametros.setHashAlgorithm(algoritmo);
			parametros.setFlags((byte)0x03);
			parametros.setDataType(1);
			MultiSignedMessageOut respuesta = service.multiSignedMessageInit(parametros);
		
			return respuesta.getProcessID();
		}else{
			logger.error("El archivo PDF no existe");
			return null;
		}
		
    }
    
    /**
	 * Método que inicia (1er paso) el proceso para firma un documento con CMS con contenido
	 * 
	 * @param cadenaOriginal: cadena original del documento a firmar
	 * @param numeroFirmantes: Número de firmantes
	 * @param algoritmo: Algoritmo del certificado (SHA1, SHA-256) 
	 * 
	 * @return String: Cadena que contiene el id de proceso
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/01/2017
	 */
    public MultiSignedMessageOut iniciarCMS(String nombreArchivo, String cadenaOriginal, Integer numeroFirmantes, String algoritmo) {
		
    	MultiSignedMessageOut respuesta = null;
    	
		if (!cadenaOriginal.equals(null)) {
		
			MultiSignedMessageIn parametros = new MultiSignedMessageIn();
			parametros.setData(cadenaOriginal.getBytes());
			parametros.setDataInfo(nombreArchivo);
			//parametros.setSignersNum((short)+numeroFirmantes);
			parametros.setHashAlgorithm(algoritmo);
			parametros.setFlags((byte)0x00);
			parametros.setDataType(1);
			respuesta = service.multiSignedMessageInit(parametros);
			//byte[] data = hexStringToByteArray(respuesta.getHash());
	    	//String hashCertificado = Base64.encodeBase64String(data);
			//return respuesta;
		//}else{
			//logger.error("No se creó la cadena original");
			//return null;
		}
		
		return respuesta;
		
    }
    
    /**
	 * Método que inicia (1er paso) el proceso para firma un documento con CMS con contenido
	 * 
	 * @param cadenaOriginal: cadena original del documento a firmar
	 * @param numeroFirmantes: Número de firmantes
	 * @param algoritmo: Algoritmo del certificado (SHA1, SHA-256) 
	 * 
	 * @return String: Cadena que contiene el id de proceso
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/01/2017
	 */
    public MultiSignedMessageOut iniciarCMSArchivo(String nombreArchivo, Integer numeroFirmantes, String algoritmo) throws FileNotFoundException, IOException{
		
    	File archivo = new File(Utilidades.getRutaGlusterFS() + "/Gestion4/documentos/" + nombreArchivo);
		
		if (archivo.exists()) {
		
			MultiSignedMessageIn parametros = new MultiSignedMessageIn();
			parametros.setData(getBytesFromFile(archivo));
			parametros.setDataInfo(nombreArchivo);
			//parametros.setSignersNum((short)+numeroFirmantes);
			parametros.setHashAlgorithm(algoritmo);
			parametros.setFlags((byte)0x01);
			parametros.setDataType(1);
			MultiSignedMessageOut respuesta = service.multiSignedMessageInit(parametros);
		
			return respuesta;
			
		}else{
			logger.error("El archivo PDF no existe");
			return null;
		}
		
    }
    
    /**
	 * Método que obtiene el hash del certificado del firmante regresado por el web service (2do paso)
	 * 
	 * @param processID: Id de proceso que se generó en el 1er paso
	 * @param certificado: Número de firmantes
	 * 
	 * @return String: Hash del certificado en Base64
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
    public String getHash(String processID, byte[] certificado) throws FileNotFoundException, IOException{
		
    	if(processID != null){
	    	String signatureB64 = null;
	    	SSignEvidence ssCertificado = new SSignEvidence();
	    	ssCertificado.setData(certificado); 
	    	String getHash = service.multiSignedMessageGetHash(processID, ssCertificado, signatureB64);
	    	byte[] data = hexStringToByteArray(getHash);
	    	String hashCertificado = Base64.encodeBase64String(data);
	    	logger.error("Paso 2: -----   Hash:------- "+ hashCertificado);
	    	
	    	return hashCertificado;
    	}else{
    		return null;
    	}
    }
    
    //El 3er paso se realiza al obtener el .cer, .key y la contraseña para obtener el pkcs7. esto se hace de lado del cliente con javascript
    
    /**
	 * Método que envía el pkcs7 (4to paso)
	 * 
	 * @param pkcs7: cadena obtenida de firmar el hash del certificado en el 3er paso
	 * @param nombreArchivo: Nombre del archivo a firmar
	 * @param processID: Id de proceso que se generó en el 1er paso
	 * 
	 * @return String: id de Secuencia que regresa el web service por si se requiere evidencia
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
    public String actualizar(String pkcs7, String nombreArchivo, String processID){
    	MultiSignedMessageUpdate update = new MultiSignedMessageUpdate();
    	SSignDocument document = new SSignDocument();
    	document.setFileName(nombreArchivo);
        document.setBase64(false);
        document.setData(Base64.decodeBase64(pkcs7));
        
        String idSecuencia = service.multiSignedMessageUpdate(document, processID, "111111111111111111111");
        return idSecuencia;  
    }
    
    /**
	 * Método que envía el pkcs7 (4to paso)
	 * 
	 * @param pkcs7: cadena obtenida de firmar el hash del certificado en el 3er paso
	 * @param nombreArchivo: Nombre del archivo a firmar
	 * @param processID: Id de proceso que se generó en el 1er paso
	 * 
	 * @return String: id de Secuencia que regresa el web service por si se requiere evidencia
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
    public String actualizarCMS(String pkcs7, String processID){
    	MultiSignedMessageUpdate update = new MultiSignedMessageUpdate();
    	SSignDocument document = new SSignDocument();
    	//document.setFileName(nombreArchivo);
        document.setBase64(false);
        document.setData(Base64.decodeBase64(pkcs7));
        
        String idSecuencia = service.multiSignedMessageUpdate(document, processID, "111111111111111111111");
        return idSecuencia;  
    }
    
    /**
	 * Método que cierra el proceso cuando ya han firmado todos los firmantes (5to paso)
	 * 
	 * @param processID: Id de proceso que se generó en el 1er paso
	 * @param cerrar: Integer que cierra el proceso cuando ya no hay mas firmantes - 0 cerrado, 1 abierto
	 * @param nombreArchivo: Nombre del archivo a firmar
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
    public byte[] cerrarProceso(String processID, Integer cerrar, String nombreArchivo) throws IOException {
        List<SSignEvidence> document = new ArrayList<SSignEvidence>();
        document = service.multiSignedMessageFinal(processID, cerrar); //0 cerrado, 1 abierto
        FileOutputStream fos = new FileOutputStream(Utilidades.getRutaGlusterFS() + "/Gestion4/pdf/" + nombreArchivo+".pdf");
        fos.write(document.get(0).getData());
        fos.close();
        return document.get(0).getData();
    }
    
    /**
	 * Método que cierra el proceso cuando ya han firmado todos los firmantes (5to paso)
	 * 
	 * @param processID: Id de proceso que se generó en el 1er paso
	 * @param cerrar: Integer que cierra el proceso cuando ya no hay mas firmantes - 0 cerrado, 1 abierto
	 * @param nombreArchivo: Nombre del archivo a firmar
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/01/2017
	 */
    public byte[] cerrarProcesoCMS(String processID, Integer cerrar){
        List<SSignEvidence> document = new ArrayList<SSignEvidence>();
        document = service.multiSignedMessageFinal(processID, cerrar); //0 cerrado, 1 abierto
        return document.get(0).getData();
    }
	
}
