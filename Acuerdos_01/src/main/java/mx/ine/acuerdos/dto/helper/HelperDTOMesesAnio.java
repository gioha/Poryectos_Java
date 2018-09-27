package mx.ine.acuerdos.dto.helper;

import java.io.Serializable;

/**
 * Clase que simula el comportamiento de un DTOMesesAnio, necesaria para soportar
 * la funcionalidad de la vista de Consulta de Convocatoria
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public class HelperDTOMesesAnio implements Serializable {
	private static final long serialVersionUID = 2545109908151099842L;

	private Integer idMes;
	private String descMes;

	public HelperDTOMesesAnio(Integer idMes, String descMes) {
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
