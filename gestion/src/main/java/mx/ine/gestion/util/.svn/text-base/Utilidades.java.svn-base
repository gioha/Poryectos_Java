/**
 * @(#)Utilidades.java 06/06/2016
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.util;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import mx.ine.gestion.dto.helper.DTOMesesHelper;

import org.jboss.logging.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;

public class Utilidades implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta
	 * clase.
	 */
	private static final long serialVersionUID = 2946134986103712192L;
	/**
	 * Ruta del gluster que viene por JNDI
	 */
	private static String		rutaGlusterFS;
	/**
	 * Ruta del Office que viene por JNDI
	 */
	private static String		rutaOfficeFS;

	/**
	 * Objeto para el servicio de bit�cora de mensajes de la aplicación.
	 */
	public static final Logger logger = Logger.getLogger(Utilidades.class);
	
	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------------- METODOS  ---------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */		
	
	
	/**
	 * Método que extrae una variable de properties para mostrar los mensajes
	 *
	 * @param String
	 *            : Identificador del valor a extraer
	 * @return String : Mensaje en forma de cadena
	 *
	 * @author Israel Vázquez Jiménez
	 * @since 06/06/2016
	 */
	public static String mensajeProperties(String llave) {

		/*Instancias*/
		ResourceBundleMessageSource messageSource = null;
		
		try
		{ 
		  messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils.getApplicationContext().getBean("messageSource"));
		
		  return messageSource.getMessage(llave, null, null);
		}
		catch (Exception e) {
			logger.error("Error:Utilidades-mensajeProperties =============================>>>>>>>>>>>>>>>>>", e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * Obtiene una lista de objetos que contienen los atributos de un mes (el nombre y el número)
	 * 
	 * @return List<DTOMesesHelper> . lista con la información de los meses.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 04/01/2018
	 */
	public static List<DTOMesesHelper> obtenerListaDeMeses() {
	
		List<DTOMesesHelper> listaMeses = new ArrayList<DTOMesesHelper>();
		Locale spanishLocale=new Locale("es", "ES");
		DateFormatSymbols dfs = new DateFormatSymbols(spanishLocale);
		String[] months = dfs.getMonths();
		
		for (int indice = 0; indice < 12; indice++ ) {
			
			DTOMesesHelper mes = new DTOMesesHelper();
			mes.setNombreMes(months[indice]);
			mes.setNumeroMes(indice+1);

			listaMeses.add(mes);
		}
		
		return listaMeses;
	}
	

	/**
	 * Método feo para mandar un mensaje..
	 * 
	 * @param target
	 * @param encabezado
	 * @param cuerpo
	 * @param severity
	 *
	 * @author No se sabe.. alguien lo puso aquí.
	 * @since 04/01/2018
	 */
	public static void enviaMensajeGeneral(String target, String encabezado, String cuerpo, Severity severity){
    	try{
    		
    			FacesMessage msg= new FacesMessage();
    			msg.setSeverity(severity);
    			msg.setDetail(cuerpo);
    			msg.setSummary(encabezado);
    			FacesContext.getCurrentInstance().addMessage(target, msg);
    			FacesContext.getCurrentInstance().renderResponse();
    		
    		
    	}catch(Exception e){
    		logger.error("Error en Utilidades.enviaMensaje ",e);
    	}
    }

	
	/**
	 * @return  rutaGlusterFS
	 */
	public static String getRutaGlusterFS() {
	
		return rutaGlusterFS;
	}

	
	/**
	 * @param rutaGlusterFS  rutaGlusterFS  del Utilidades
	 */
	public static void setRutaGlusterFS(String rutaGlusterFS) {
	
		Utilidades.rutaGlusterFS = rutaGlusterFS;
	}

	/**
	 * @return rutaOfficeFS
	 */
	public static String getRutaOfficeFS() {

		return rutaOfficeFS;
	}

	/**
	 * @param rutaOfficeFS
	 *            rutaOfficeFS del Utilidades
	 */
	public static void setrutaOfficeFS(String rutaOfficeFS) {

		Utilidades.rutaOfficeFS = rutaOfficeFS;
	}

}