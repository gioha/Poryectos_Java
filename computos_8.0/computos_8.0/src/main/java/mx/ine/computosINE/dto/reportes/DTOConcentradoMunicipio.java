package mx.ine.computosINE.dto.reportes;

import java.io.Serializable;

public class DTOConcentradoMunicipio implements Serializable{
	
	
	private Integer gobernador;
	
	private Integer ayuntamiento;

	/**
	 * @return the gobernador
	 */
	public Integer getGobernador() {
		return gobernador;
	}

	/**
	 * @param gobernador the gobernador to set
	 */
	public void setGobernador(Integer gobernador) {
		this.gobernador = gobernador;
	}

	/**
	 * @return the ayuntamiento
	 */
	public Integer getAyuntamiento() {
		return ayuntamiento;
	}

	/**
	 * @param ayuntamiento the ayuntamiento to set
	 */
	public void setAyuntamiento(Integer ayuntamiento) {
		this.ayuntamiento = ayuntamiento;
	}
	
	
	

}
