package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla C_AREAS del esquema de ACUERDOS.
 * 
 * @author Óscar Pérez Vargas
 * @since Noviembre-2017
 * @ver 1
 **/
@Embeddable
public class DTOCAreasPK implements Serializable {

	private static final long serialVersionUID = -4801423719450589642L;
	
	@Column(name = "ID_AREA", nullable = false)
	private Integer idArea;

	@Column(name = "TIPO_AREA", nullable = false)
	private Integer tipoArea;
	
	public DTOCAreasPK() {
		this.idArea = new Integer(0);
		this.tipoArea = new Integer(0);
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
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOCAreasPK) ) {
			DTOCAreasPK dtoCAreas = (DTOCAreasPK) obj;
			if( idArea.equals(dtoCAreas.idArea) &&
				tipoArea.equals(dtoCAreas.tipoArea) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArea, tipoArea);
	}
	
}
