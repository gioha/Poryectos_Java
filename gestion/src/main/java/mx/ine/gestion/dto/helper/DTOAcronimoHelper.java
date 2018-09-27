/**
 * @(#)DTOAcronimoHelper.java 10/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase de ayuda que contendrá los parámetros para generar los acrónimos
 *
 * @author Pável Alexei Martínez Regalado
 * @since 10/11/2017
 */
public class DTOAcronimoHelper implements Serializable{
	/*
	 * Serial
	 */
	private static final long serialVersionUID = -1792666183096642985L;
	
   /*
    * Atributo que contiene el tipo de documento seleccionado;
    */
    private Integer idTipoDocumentoSeleccionado;
	
    /*
     * Atributo que contiene un arreglo con las claves
     */
    private String[][] estructuraAcronimos;
	
   /*
    * Relación de la ubicación de las cajas
    */
    private Map<String, String> relacionAcronimos;
    
    
    /*
     * Atributo con el formato para número seleccionado
     */
    private String formatoNumeroSeleccionado;

    /*
     * Atributo con el formato para año seleccionado
     */
    private String formatoAnioSeleccionado;
    
    /*
     * Atributo que contiene el vista previa del acrónimo
     */
    private String vistaPreviaAcronimo;
    
    /*
     * Atributo que contiene el valor de separador
     */
    private String sepa1;
    
    /*
     * Atributo que contiene el valor de separador
     */
    private String sepa2;
    
    /*
     * Acrónimo
     */
    private String acronimo;
    
    /*
     * Atributo para saber si el acronimo debe de ser sólo de lectura
     */
    private boolean soloLectura;
    
	/**
	 * @return valor de tipo Integer que está contenido en el atributo idTipoDocumento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public Integer getIdTipoDocumentoSeleccionado() {
		return idTipoDocumentoSeleccionado;
	}

	/**
	 * @param : valor que se ingresa al atributo idTipoDocumentoSeleccionado de tipo Integer
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public void setIdTipoDocumentoSeleccionado(Integer idTipoDocumentoSeleccionado) {
		this.idTipoDocumentoSeleccionado = idTipoDocumentoSeleccionado;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo estructuraAcronimos
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public String[][] getEstructuraAcronimos() {
		return estructuraAcronimos;
	}

	/**
	 * @param : valor que se ingresa al atributo estructuraAcronimos de tipo String[][]
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public void setEstructuraAcronimos(String[][] estructuraAcronimos) {
		this.estructuraAcronimos = estructuraAcronimos;
	}

	/**
	 * @return valor de tipo Map<String,String> que está contenido en el atributo relacionAcronimos 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public Map<String, String> getRelacionAcronimos() {
		return relacionAcronimos;
	}

	/**
	 * @param : valor que se ingresa al atributo relacionAcronimos de tipo Map<String,String>
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public void setRelacionAcronimos(Map<String, String> relacionAcronimos) {
		this.relacionAcronimos = relacionAcronimos;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo acronimo
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public String getAcronimo() {
		return acronimo;
	}

	/**
	 * @param : valor que se ingresa al atributo acronimo de tipo String
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo formatoNumeroSeleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public String getFormatoNumeroSeleccionado() {
		return formatoNumeroSeleccionado;
	}

	/**
	 * @param : valor que se ingresa al atributo formatoNumeroSeleccionado de tipo String
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public void setFormatoNumeroSeleccionado(String formatoNumeroSeleccionado) {
		this.formatoNumeroSeleccionado = formatoNumeroSeleccionado;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo formatoAnioSeleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public String getFormatoAnioSeleccionado() {
		return formatoAnioSeleccionado;
	}

	/**
	 * @param : valor que se ingresa al atributo formatoAnioSeleccionado de tipo String
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public void setFormatoAnioSeleccionado(String formatoAnioSeleccionado) {
		this.formatoAnioSeleccionado = formatoAnioSeleccionado;
	}
	
	/**
	 * @return valor de tipo String que está contenido en el atributo vistaPreviaAcronimo
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public String getVistaPreviaAcronimo() {
		return vistaPreviaAcronimo;
	}

	/**
	 * @param : valor que se ingresa al atributo vistaPreviaAcronimo de tipo String
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setVistaPreviaAcronimo(String vistaPreviaAcronimo) {
		this.vistaPreviaAcronimo = vistaPreviaAcronimo;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo sepa1
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public String getSepa1() {
		return sepa1;
	}

	/**
	 * @param : valor que se ingresa al atributo sepa1 de tipo String
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setSepa1(String sepa1) {
		this.sepa1 = sepa1;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo sepa2
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public String getSepa2() {
		return sepa2;
	}

	/**
	 * @param : valor que se ingresa al atributo sepa2 de tipo String
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setSepa2(String sepa2) {
		this.sepa2 = sepa2;
	}

	/**
	 * @return variable de tipo boolean contenida en soloLectura
	 * 
	 * @since 04/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public boolean isSoloLectura() {
		return soloLectura;
	}

	/**
	 * @param soloLectura: variable de tipo boolean contenida en soloLectura
	 *
	 * @since 04/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setSoloLectura(boolean soloLectura) {
		this.soloLectura = soloLectura;
	}
	
	

}
