package mx.ine.acuerdos.dto;

import java.io.Serializable;

public class HelperFiltrosReporte implements Serializable {

	private static final long serialVersionUID = -6946849512568602985L;
	private String filtroAcuerdo;
	private String filtroPunto;
	private String filtroEstatus;
	private String filtroArea;
	private String filtroClasificacion;
	
	public String getFiltroAcuerdo() {
		return filtroAcuerdo;
	}
	public void setFiltroAcuerdo(String filtroAcuerdo) {
		this.filtroAcuerdo = filtroAcuerdo;
	}
	public String getFiltroPunto() {
		return filtroPunto;
	}
	public void setFiltroPunto(String filtroPunto) {
		this.filtroPunto = filtroPunto;
	}
	public String getFiltroEstatus() {
		return filtroEstatus;
	}
	public void setFiltroEstatus(String filtroEstatus) {
		this.filtroEstatus = filtroEstatus;
	}
	public String getFiltroArea() {
		return filtroArea;
	}
	public void setFiltroArea(String filtroArea) {
		this.filtroArea = filtroArea;
	}
	public String getFiltroClasificacion() {
		return filtroClasificacion;
	}
	public void setFiltroClasificacion(String filtroClasificacion) {
		this.filtroClasificacion = filtroClasificacion;
	}
	
}
