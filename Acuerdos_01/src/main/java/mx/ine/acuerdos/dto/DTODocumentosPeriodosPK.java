package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla DOCUMENTOS_PERIODOS del esquema de ACUERDOS.
 * 
 * @author Giovanni Hernandez Alonso
 * @since Octubre-2017
 * @ver 1
 **/
@Embeddable
public class DTODocumentosPeriodosPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7552832379590565035L;
	

	@Column(name="ID_PERIODO", nullable = false)
	private Integer idPeriodo;
	
	@Column(name="ID_NUM_DOCUMENTO", nullable = false)
	private String idNumDocumento;
	
	@Column(name="ID_AREA", nullable = false)
	private Integer idArea;
	
	@Column(name="TIPO_AREA", nullable = false)
	private Integer tipoArea;

	/**
	 * Constructor default de la clase DTODocumentosPeriodosPK
	 *  @author Giovanni Hernández Alonso
	 * @since 12/10/2017
	 * @param 
	 * @return DTODocumentosPeriodosPK
	 * **/
	public DTODocumentosPeriodosPK(){
		
		this.idPeriodo 		= null;
		this.idNumDocumento = "";
		this.idArea			= null;
		this.tipoArea		= null;
		
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
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
		return "DTODocumentosPeriodosPK [idPeriodo=" + idPeriodo + ", idNumDocumento=" + idNumDocumento + ", idArea="
				+ idArea + ", tipoArea=" + tipoArea + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTODocumentosPeriodosPK) ) {
			DTODocumentosPeriodosPK dtoDocsPeriodos = (DTODocumentosPeriodosPK) obj;
			if( idPeriodo.equals(dtoDocsPeriodos.idPeriodo) &&
				idNumDocumento.equals(dtoDocsPeriodos.idNumDocumento) &&
				idArea.equals(dtoDocsPeriodos.idArea) &&
				tipoArea.equals(dtoDocsPeriodos.tipoArea) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPeriodo, idNumDocumento, idArea, tipoArea);
	}
	
}
