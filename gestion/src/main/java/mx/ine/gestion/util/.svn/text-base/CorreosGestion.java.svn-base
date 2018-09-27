/**
 * @(#)CorreosGestion.java 20/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.util;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;

/**
 * Clase que construye y almacena la información acerca de un correo electrónico.
 * @author José Miguel Ortiz
 * @since 26/04/2018
 */
public class CorreosGestion implements Serializable {
	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 2500369579468788966L;

	/* ------------------------------ Atributos generales ------------------------------ */
	/**
	 * Atributo que contiene el asunto del correo
	 */
	private StringBuilder asuntoCorreo;

	/**
	 * Atributo que contiene el cuerpo del correo
	 */
	private StringBuilder cuerpoCorreo;

	/**
	 * Constructor del correo de notificación para el turnado de documentos.
	 * @param DTOEstructuraAreasEntity remitente, DTOTurnadoHelper destinatario, DTODocumentoEntity documento, List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativas
	 * @author José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public CorreosGestion(DTOUsuarioLogin remitente, DTOTurnadoHelper destinatario, DTODocumentoEntity documento, List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativas) {
		asuntoCorreo = new StringBuilder();
		cuerpoCorreo = new StringBuilder();

		// Contiene la codificación HTML para el correo electrónico
		String plantilla = "";
		String listaInstrucciones = "";

		for(DTOInstruccionesEntity instruccion : listaAtencion) {
			if(destinatario.getIdsInstruccionesInsertar().contains(instruccion.getIdInstruccion()) &&
			   instruccion.getConNotificacion().equals(1)) {
				listaInstrucciones += "<br />" + instruccion.getOrdenamiento() + ". " + instruccion.getDescripcion();
			}
		}

//		if(destinatario.getTipoInstrucciones().equals("info")) {
//			for(Integer numInstruccion : destinatario.getIdsInstruccionesInsertar()) {
//				for(DTOInstruccionesEntity instruccion : listaInformativas) {
//					if(remitente.getIdPersona().equals(instruccion.getIdPersona()) && numInstruccion.equals(instruccion.getIdInstruccion())) {
//						listaInstrucciones += "<br />" + instruccion.getOrdenamiento() + ". " + instruccion.getDescripcion();
//						break;
//					}
//				}
//			}
//		}
		
		plantilla = "<html><body><div style ='height: 75px; background-color: #D5007F; font-family: Helvetica; color: #FFFFFF;'>"
				  + "<div style ='padding-top: 10px;'><span style='font-size: 30px; font-weight: bold; margin-left: 40px;'>Gestión</span>"
				  + "<span style='font-size: 40px; margin-left: 20px;'>|</span>"
				  + "<span style='font-size: 16px; font-weight: normal; margin-left: 20px;'>"
				  + "Sistema integral de gestión de documentos</span></div></div>"
				  + "<div style ='font-family: Helvetica; font-size: 16px; padding: 35px;'>"
				  + "<span style='font-weight: bold;'>Estimado usuario(a):</span><br /><br /><span style='font-weight: bold;'>"
				  + remitente.getUsername()
				  + "</span> te ha <span style='font-weight: bold;'>turnado</span> un documento con los siguientes datos:<br /><br />"
				  + "<span style='font-weight: bold;'>Folio: </span>"
				  + documento.getNoDocumento()
				  + "<br /><br /><span style='font-weight: bold;'>Asunto:</span><br />"
				  + reempCaracteres(documento.getAsunto())
				  + "<br /><br />Instrucciones <span style='font-weight: bold;'>De atención</span>:"
				  + reempCaracteres(listaInstrucciones)
				  + "<br /><br />Para revisar el documento completo en el Sistema de Gestión, da clic en la siguiente liga:</div>"
				  + "<div style ='font-family: Helvetica; font-size: 16px; text-align: center; color: #D5007F;'>"
				  + "<a style ='color: #D5007F;' href='https://10.0.78.150:8443/gestion4/app/login'>"
				  + "https://10.0.78.150:8443/gestion4/app/login</a><br /><br /><br /><br />"
				  + "<span style='font-size: 20px; font-weight: bold;'>Porque mi país me importa</span><br /><br /></div></body></html>"
				  ;

		asuntoCorreo.append(remitente.getUsername() + " te ha turnado un documento");
		cuerpoCorreo.append(plantilla);
	}

	/**
	 * Constructor del correo de notificación para el firmado y validado de documentos.
	 * @param DTOEstructuraAreasEntity remitente, DTOBorradorDocumentosEntity documento, String tipoEnvio
	 * @author José Miguel Ortiz
	 * @since 26/04/2018
	 */
	public CorreosGestion(DTOEstructuraAreasEntity remitente, DTOBorradorDocumentosEntity documento, String tipoEnvio) {
		asuntoCorreo = new StringBuilder();
		cuerpoCorreo = new StringBuilder();

		// Contiene la codificación HTML para el correo electrónico
		String plantilla = "";
		
		plantilla = "<html><body><div style ='height: 75px; background-color: #D5007F; font-family: Helvetica; color: #FFFFFF;'>"
				  + "<div style ='padding-top: 10px;'><span style='font-size: 30px; font-weight: bold; margin-left: 40px;'>Gestión</span>"
				  + "<span style='font-size: 40px; margin-left: 20px;'>|</span>"
				  + "<span style='font-size: 16px; font-weight: normal; margin-left: 20px;'>"
				  + "Sistema integral de gestión de documentos</span></div></div>"
				  + "<div style ='font-family: Helvetica; font-size: 16px; padding: 35px;'><span style='font-weight: bold;'>"
				  + "Estimado usuario(a):</span><br /><br /><span style='font-weight: bold;'>"
				  + remitente.getCuentaLDAP()
				  + "</span> te ha enviado el siguiente documento a <span style='font-weight: bold;'>"
				  + tipoEnvio
				  + "</span>:<br /><br /><span style='font-weight: bold;'>Folio: </span>"
				  + documento.getDocumento().getNoDocumento()
				  + "<br /><br /><span style='font-weight: bold;'>Asunto:</span><br />"
				  + reempCaracteres(documento.getDocumento().getAsunto())
				  + "<br /><br />Para revisar el documento completo en el Sistema de Gestión, da clic en la siguiente liga:</div>"
				  + "<div style ='font-family: Helvetica; font-size: 16px; text-align: center; color: #D5007F;'>"
				  + "<a style ='color: #D5007F;' href='https://10.0.78.150:8443/gestion4/app/login'>"
				  + "https://10.0.78.150:8443/gestion4/app/login</a><br /><br /><br /><br /><span style='font-size: 20px; font-weight: bold;'>"
				  + "Porque mi país me importa</span><br /><br /></div></body></html>"
				  ;

		asuntoCorreo.append(remitente.getCuentaLDAP() + " te ha enviado un documento a " + tipoEnvio);
		cuerpoCorreo.append(plantilla);
	}

	/**
	 * Método encargado de reemplazar caracteres expeciales dentro del correo electrónico.
	 * @param String cadena
	 * @return String: cadena con caracteres especiales reemplazados
	 * @author José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public String reempCaracteres(String cadena) {
		cadena = cadena.replace("á", "&aacute;");
		cadena = cadena.replace("Á", "&Aacute;");
		cadena = cadena.replace("é", "&eacute;");
		cadena = cadena.replace("É", "&Eacute;");
		cadena = cadena.replace("í", "&iacute;");
		cadena = cadena.replace("Í", "&Iacute;");
		cadena = cadena.replace("ó", "&oacute;");
		cadena = cadena.replace("Ó", "&Oacute;");
		cadena = cadena.replace("ú", "&uacute;");
		cadena = cadena.replace("Ú", "&Uacute;");
		cadena = cadena.replace("ñ", "&ntilde;");
		cadena = cadena.replace("Ñ", "&Ntilde;");
		cadena = cadena.replace("¡", "&iexcl;");
		cadena = cadena.replace("¿", "&iquest;");

		return cadena;
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public StringBuilder getAsuntoCorreo() {
		return asuntoCorreo;
	}

	public void setAsuntoCorreo(StringBuilder asuntoCorreo) {
		this.asuntoCorreo = asuntoCorreo;
	}

	public StringBuilder getCuerpoCorreo() {
		return cuerpoCorreo;
	}

	public void setCuerpoCorreo(StringBuilder cuerpoCorreo) {
		this.cuerpoCorreo = cuerpoCorreo;
	}

}
