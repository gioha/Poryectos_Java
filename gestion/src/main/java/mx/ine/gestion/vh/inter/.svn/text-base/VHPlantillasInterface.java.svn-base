/**
 * @(#)VHPlantillasInterface.java 14/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

import java.io.IOException;
import java.security.GeneralSecurityException;

import mx.ine.gestion.dto.db.DTOPlantillaEntity;

/**
 * Interfaz que contiene la firma de los métodos que tienen la logica de negocio 
 * que únicamente llega a la primera capa la cual tiene que ver con el módulo de plantillas.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 14/01/2018
 */
public interface VHPlantillasInterface {

	/**
	 * Método para crear el temporal de una plantilla la cual será utilizada para crearla.
	 * 
	 * @return String: Ruta tmp de la plantilla creada
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 14/01/2018
	 */
	public String crearTemporalPlantilla() throws IOException;

	/**
	 * Método que genera el hyper link que abre el word el cual se convertira en una plantilla.
	 * 
	 * @return String: Cadena que contiene el hyperLink que abre el word
	 */
	public String generarHyperLinkPlantillaTmp() throws GeneralSecurityException, IOException;
	
	/**
	 * Método que genera el hyper link que abre el word el cual se convertira en una plantilla.
	 * 
	 * @return String: Cadena que contiene el hyperLink que abre el word
	 */
	public String generarHyperLinkPlantilla(DTOPlantillaEntity plantilla) throws GeneralSecurityException, IOException;

	/**
	 * Método para eliminar el temporal de una plantilla (el archivo .docx).
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 14/01/2018
	 */
	public void eliminarTemporalPlantilla();

}