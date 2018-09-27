package mx.ine.gestion.dto.helper;

import java.io.File;
import java.io.Serializable;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;

/**
 *
 * @autor Giovanni Hernández Alonso
 * @since 22/03/2018
 */
public class DTODocumentoAnexoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6147273298099026859L;
	

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Nombre temporal asignado al documento
	 */
	private String nombreTemporal;

	/**
	 * Nombre original del documento
	 */
	private String nombreOriginal;

	/**
	 * Extención que nos indica de que tipo es el documento
	 */
	private String tipoExtencion;

	/**
	 * El tamaño del documento
	 */
	private Double size;
	
	/**
	 * Objeto con la información del documento
	 */
	private File documento;


	/* ------------------------------------- Metodos ------------------------------------ */
	
	/**
	 * Constructor default de la clase
	 */
	public DTODocumentoAnexoHelper(){
		
		this.nombreTemporal = "";
		this.nombreOriginal = "";
		this.tipoExtencion 	= "";
		this.size			= new Double(0);
		this.documento		= null;
	}


	/* ------------------------------------- Getters y Setters ------------------------------------ */

	
	public String getNombreTemporal() {
		return nombreTemporal;
	}


	public void setNombreTemporal(String nombreTemporal) {
		this.nombreTemporal = nombreTemporal;
	}


	public String getNombreOriginal() {
		return nombreOriginal;
	}


	public void setNombreOriginal(String nombreOriginal) {
		this.nombreOriginal = nombreOriginal;
	}


	public String getTipoExtencion() {
		return tipoExtencion;
	}


	public void setTipoExtencion(String tipoExtencion) {
		this.tipoExtencion = tipoExtencion;
	}


	public Double getSize() {
		return size;
	}


	public void setSize(Double size) {
		this.size = size;
	}


	public File getDocumento() {
		return documento;
	}


	public void setDocumento(File documento) {
		this.documento = documento;
	}
}
