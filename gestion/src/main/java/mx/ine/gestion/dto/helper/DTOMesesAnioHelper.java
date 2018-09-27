/**
 * @(#)DTOMesesAnioHelper.java 26/03/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * Clase que simula el comportamiento de un DTOMeses, necesaria para desplegar
 * un catálogo de meses en la vista de Reportes de Oficialía.
 * @author José Miguel Ortiz
 * @since 26/03/2018
 */
public class DTOMesesAnioHelper implements Serializable {
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 2545109908151099842L;

	/* ------------------------------ Atributos de la clase ------------------------------ */
	/**
	 * Atributo que contiene el identificador del mes
	 */
	private Integer idMes;
	/**
	 * Atributo que contiene la descripción del mes
	 */
	private String descMes;

	public DTOMesesAnioHelper(Integer idMes, String descMes) {
		this.idMes = idMes;
		this.descMes = descMes;
	}

	public Integer getIdMes() {
		return idMes;
	}

	public void setIdMes(Integer idMes) {
		this.idMes = idMes;
	}

	public String getDescMes() {
		return descMes;
	}

	public void setDescMes(String descMes) {
		this.descMes = descMes;
	}

}
