/**
 * @(#)DTOFirmaElectronicaHelper.java 04/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * Clase de ayuda que contendrá los parámetros para generar la firma
 * de archivos que se utiliza en el módulo de Firma.
 *
 * @author David Rodríguez Corral
 * @since 04/10/2017
 */
public class DTOFirmaElectronicaHelper implements Serializable{
	
	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = -9126459270714262695L;

	/**
	 * Contraseña que esta relacionada al certificado y llave que se utiliza para firmar.
	 */
	private String contrasenia;

	/**
	 * Algoritmo que se extrae del llavePrivada, se obtiene a través de javascript en la pantalla.
	 */
	private String algoritmoHash;

	/**
	 * Número de serie que se obtiene del certificado que se utiliza para firmar, se obtiene a través de javascript en la pantalla
	 */
	private String numeroSerie;
	
	/**
	 * Hash del certificado del usuario firmante
	 */
	private String hashCert;


	/* ------------------------------------- Getters/Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo String que esta contenido en la variable contrasenia
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable algoritmoHash
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public String getAlgoritmoHash() {
		return algoritmoHash;
	}

	/**
	 * @param algoritmoHash : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setAlgoritmoHash(String algoritmoHash) {
		this.algoritmoHash = algoritmoHash;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numeroSerie
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}

	/**
	 * @param numeroSerie : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getHashCert() {
		return hashCert;
	}

	public void setHashCert(String hashCert) {
		this.hashCert = hashCert;
	}
	
	
}
