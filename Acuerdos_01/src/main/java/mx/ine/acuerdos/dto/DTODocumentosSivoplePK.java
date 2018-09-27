package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla DOCUMENTOS_SIVOPLE del esquema de ACUERDOS.
 * 
 * @author Giovanni Hernandez Alonso
 * @since Octubre-2017
 * @ver 1
 **/

@Embeddable
public class DTODocumentosSivoplePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7967543306225037922L;

	
	@Column(name="ID_NUM_DOCUMENTO", nullable = false)
	private String idNumDocumento;
	
	@Column(name="ID_AREA", nullable = false)
	private Integer idArea;
	
	@Column(name="TIPO_AREA", nullable = false)
	private Integer tipoArea;
	
	
	/**
	 * Constructor default de la clase DTODocumentosSivoplePK
	 * @author Giovanni Hernández Alonso
	 * @since 12/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTODocumentosSivoplePK(){

		this.idNumDocumento 	= "";
		this.idArea				= new Integer(0);
		this.tipoArea			= new Integer(0);

	}

	public String getIdNumDocumento() {
		return idNumDocumento;
	}


	public void setIdNumDocumento(String idNumDocumento) {
		this.idNumDocumento = idNumDocumento;
	}


	public Integer getIdArea() {
		return idArea;
	}


	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}


	public Integer getTipoArea() {
		return tipoArea;
	}


	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	@Override
	public String toString() {
		return "DTODocumentosSivoplePK [idNumDocumento=" + idNumDocumento + ", idArea=" + idArea + ", tipoArea="
				+ tipoArea + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTODocumentosSivoplePK) ) {
			DTODocumentosSivoplePK dtoDocsSivople = (DTODocumentosSivoplePK) obj;
			if( idNumDocumento.equals(dtoDocsSivople.idNumDocumento) &&
				idArea.equals(dtoDocsSivople.idArea) &&
				tipoArea.equals(dtoDocsSivople.tipoArea) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNumDocumento, idArea, tipoArea);
	}

}
