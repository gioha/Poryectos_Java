package mx.ine.computosINE.dto.helper;

import java.io.Serializable;

public class HLPDemarcacion implements Serializable, Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8775656366025374273L;
	private Integer idDemarcacion;
	private String nombreDemarcacion;
	public Integer getIdDemarcacion() {
		return idDemarcacion;
	}
	public void setIdDemarcacion(Integer idDemarcacion) {
		this.idDemarcacion = idDemarcacion;
	}
	public String getNombreDemarcacion() {
		return nombreDemarcacion;
	}
	public void setNombreDemarcacion(String nombreDemarcacion) {
		this.nombreDemarcacion = nombreDemarcacion;
	}
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof HLPDemarcacion)){
			return -1;
		}
		
		return this.idDemarcacion.compareTo(((HLPDemarcacion) o)
				.getIdDemarcacion());

	}
}
