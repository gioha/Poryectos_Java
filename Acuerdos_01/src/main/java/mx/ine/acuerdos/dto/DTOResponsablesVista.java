package mx.ine.acuerdos.dto;

import java.io.Serializable;

public class DTOResponsablesVista implements Serializable {

	private static final long serialVersionUID = -3368302110453208884L;
	private DTOResponsables responsable;
	private DTOCAreas area;
	
	public DTOResponsablesVista() {
		
	}
	
	public DTOResponsablesVista(DTOResponsables responsable, DTOCAreas area) {
		super();
		this.responsable = responsable;
		this.area = area;
	}

	public DTOResponsables getResponsable() {
		return responsable;
	}

	public void setResponsable(DTOResponsables responsable) {
		this.responsable = responsable;
	}

	public DTOCAreas getArea() {
		return area;
	}

	public void setArea(DTOCAreas area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "DTOResponsablesVista [responsable=" + responsable + ", area=" + area + "]";
	}
	
}
