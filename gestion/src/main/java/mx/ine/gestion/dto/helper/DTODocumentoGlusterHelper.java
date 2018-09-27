package mx.ine.gestion.dto.helper;

import java.io.File;
import java.io.Serializable;

/**
 * Clase para guardar documentos del Gluster
 *
 * @autor INE
 * @since 12/07/2016
 */
public class DTODocumentoGlusterHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = 4305758220849077780L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Nombre del documento en gluster
	 */
	private String nombreDocumento;

	/**
	 * Objeto con la información
	 */
	private File documento;

	/**
	 * Para saber si es un folder o un archivo
	 */
	private boolean folder;

	/**
	 * El tamaño de los archivos, para los directorios es el peso de todos los archivos dentro
	 */
	private Double size;

	/* ------------------------------------- Getters/Setters ------------------------------------ */

	/**
	 * @return valor de tipo String que esta contenido en la variable HLPDocumentoGluster.java
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * @param nombreDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * @return valor de tipo File que esta contenido en la variable HLPDocumentoGluster.java
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public File getDocumento() {
		return documento;
	}

	/**
	 * @param documento : valor que se ingresa a la variable de tipo File
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public void setDocumento(File documento) {
		this.documento = documento;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable HLPDocumentoGluster.java
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public boolean isFolder() {
		return folder;
	}

	/**
	 * @param folder : valor que se ingresa a la variable de tipo boolean
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable size
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public Double getSize() {
		return size;
	}

	/**
	 * @param size : valor que se ingresa a la variable de tipo Double
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public void setSize(Double size) {
		this.size = size;
	}

}
