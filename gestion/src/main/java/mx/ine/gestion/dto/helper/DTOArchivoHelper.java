package mx.ine.gestion.dto.helper;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @(#)DTOArchivoHelper.java 04/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase Helper para representar un archivo a subir al gluster.
 * 
 * @author Sergio Ley Garcia
 * @since 04/09/2017
 */
public class DTOArchivoHelper implements Serializable {

	private static final long	serialVersionUID	= -6883237545134648205L;
	/**
	 * Atributo que guarda el nombre del archivo en disco.
	 */
	private String				nombreArchivo;
	/**
	 * Atributo que guarda el nombre del archivo del cliente.
	 */
	private String				nombre;
	/**
	 * Atributo que guarda el archivo en si en caso de ser necesario.
	 * 
	 * No formara parte del ciclo normal de ejecucion.
	 * 
	 */
	private File				archivo;
	/**
	 * Atributo que guarda la extension del archivo.
	 */
	private String				extension;
	/**
	 * Atributo que guarda el tamaño del archivo
	 */
	private Integer				tamanio;
	/**
	 * Atributo que guarda el tamaño del archivo en megas
	 */
	private String				tamanioMegas;
	/**
	 * Atributo que guarda la ruta donde se aloja el archivo.
	 */
	private String				ruta;

	/**
	 * @return tamanio peso del archivo en bytes
	 */
	public Integer getTamanio() {

		return tamanio;
	}

	/**
	 * @param tamanio
	 *            peso del archivo en bytes
	 */
	public void setTamanio(Integer tamanio) {

		this.tamanio = tamanio;
		BigDecimal aux = new BigDecimal(tamanio);
		aux = aux.divide(new BigDecimal(1048576));
		aux = aux.setScale(2, RoundingMode.HALF_UP);
		tamanioMegas = aux.toString();
	}

	/**
	 * @return nombreArchivo
	 */
	public String getNombreArchivo() {

		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 */
	public void setNombreArchivo(String nombreArchivo) {

		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return archivo
	 */
	public File getArchivo() {

		return archivo;
	}

	/**
	 * @param archivo
	 */
	public void setArchivo(File archivo) {

		this.archivo = archivo;
	}

	/**
	 * @return extension del archivo
	 */
	public String getExtension() {

		return extension;
	}

	/**
	 * @param extension
	 *            del archivo
	 */
	public void setExtension(String extension) {

		this.extension = extension;
	}

	/**
	 * @return nombre
	 */
	public String getNombre() {

		return nombre;
	}

	/**
	 * @param nombre
	 *            nombre del DTOArchivoHelper
	 */
	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	/**
	 * @return tamanioMegas
	 */
	public String getTamanioMegas() {

		return tamanioMegas;
	}

	/**
	 * @return ruta
	 */
	public String getRuta() {

		return ruta;
	}


	/**
	 * @param ruta
	 *            ruta del DTOArchivoHelper
	 */
	public void setRuta(String ruta) {

		this.ruta = ruta;
	}

	/**
	 * @param tamanioMegas
	 *            tamanioMegas del DTOArchivoHelper
	 */
	public void setTamanioMegas(String tamanioMegas) {

		this.tamanioMegas = tamanioMegas;
	}
}
