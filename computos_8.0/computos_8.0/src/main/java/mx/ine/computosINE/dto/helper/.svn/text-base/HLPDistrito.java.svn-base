package mx.ine.computosINE.dto.helper;

import java.io.Serializable;

public class HLPDistrito implements Serializable, Comparable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2031292896278002538L;
	private Integer idDistrito;
	private String cabecera;
	public Integer getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof HLPDistrito)){
			return -1;
		}
		return this.idDistrito.compareTo(((HLPDistrito)o).getIdDistrito());
	}
	
}
