package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOResponsablesVista;

public class HLPFormCatalogoUsuarios implements Serializable {

	private static final long serialVersionUID = -232978816854933219L;
	private String nombreBusqueda;
	private String apellidosBusqueda;
	private String usuarioBusqueda;
	private Integer tipoResponsableBusqueda;
	private Integer idAreaBusqueda;
	private String areaAdscripcion;
	private boolean cambiaArea;
	private DTOResponsables responsable;
	private DTOResponsablesVista responsableVista;
	private List<DTOResponsables> responsables;
	private List<DTOResponsablesVista> responsablesVista;
	private List<DTOCAreas> areas;
	private List<DTOCAreas> areasResponsablesLigados;
	private boolean existeResponsableBD;
	
	public HLPFormCatalogoUsuarios() {
		nombreBusqueda = "";
		apellidosBusqueda = "";
		usuarioBusqueda = "";
		idAreaBusqueda = null;
		areaAdscripcion = "";
		responsableVista = null;
		responsables = null;
		responsable = null;
		responsablesVista = null;
		areas = new ArrayList<>();
		areasResponsablesLigados = new ArrayList<>();
		cambiaArea = false;
		existeResponsableBD = false;
		tipoResponsableBusqueda = new Integer(0);
	}
	
	public void limpiaFormulario() {
		nombreBusqueda = "";
		apellidosBusqueda = "";
		usuarioBusqueda = "";
		idAreaBusqueda = new Integer(0);
		areaAdscripcion = "";
		responsable = null;
		responsableVista = null;
		responsables = null;
		responsablesVista = null;
		existeResponsableBD = false;
		tipoResponsableBusqueda = new Integer(0);
	}

	public String getNombreBusqueda() {
		return nombreBusqueda;
	}

	public void setNombreBusqueda(String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	public String getApellidosBusqueda() {
		return apellidosBusqueda;
	}

	public void setApellidosBusqueda(String apellidosBusqueda) {
		this.apellidosBusqueda = apellidosBusqueda;
	}

	public String getUsuarioBusqueda() {
		return usuarioBusqueda;
	}

	public void setUsuarioBusqueda(String usuarioBusqueda) {
		this.usuarioBusqueda = usuarioBusqueda;
	}

	public Integer getIdAreaBusqueda() {
		return idAreaBusqueda;
	}

	public void setIdAreaBusqueda(Integer idAreaBusqueda) {
		this.idAreaBusqueda = idAreaBusqueda;
	}

	public String getAreaAdscripcion() {
		return areaAdscripcion;
	}

	public void setAreaAdscripcion(String areaAdscripcion) {
		this.areaAdscripcion = areaAdscripcion;
	}

	public boolean isCambiaArea() {
		return cambiaArea;
	}

	public void setCambiaArea(boolean cambiaArea) {
		this.cambiaArea = cambiaArea;
	}

	public Integer getTipoResponsableBusqueda() {
		return tipoResponsableBusqueda;
	}

	public void setTipoResponsableBusqueda(Integer tipoResponsableBusqueda) {
		this.tipoResponsableBusqueda = tipoResponsableBusqueda;
	}

	public DTOResponsables getResponsable() {
		return responsable;
	}

	public void setResponsable(DTOResponsables responsable) {
		this.responsable = responsable;
	}

	public DTOResponsablesVista getResponsableVista() {
		return responsableVista;
	}

	public void setResponsableVista(DTOResponsablesVista responsableVista) {
		this.responsableVista = responsableVista;
	}

	public List<DTOResponsables> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<DTOResponsables> responsables) {
		this.responsables = responsables;
	}

	public List<DTOResponsablesVista> getResponsablesVista() {
		return responsablesVista;
	}

	public void setResponsablesVista(List<DTOResponsablesVista> responsablesVista) {
		this.responsablesVista = responsablesVista;
	}

	public List<DTOCAreas> getAreas() {
		return areas;
	}

	public void setAreas(List<DTOCAreas> areas) {
		this.areas = areas;
	}

	public List<DTOCAreas> getAreasResponsablesLigados() {
		return areasResponsablesLigados;
	}

	public void setAreasResponsablesLigados(List<DTOCAreas> areasResponsablesLigados) {
		this.areasResponsablesLigados = areasResponsablesLigados;
	}

	public boolean isExisteResponsableBD() {
		return existeResponsableBD;
	}

	public void setExisteResponsableBD(boolean existeResponsableBD) {
		this.existeResponsableBD = existeResponsableBD;
	}

	@Override
	public String toString() {
		return "HLPFormCatalogoUsuarios [nombreBusqueda=" + nombreBusqueda + ", apellidosBusqueda=" + apellidosBusqueda
				+ ", usuarioBusqueda=" + usuarioBusqueda + ", tipoResponsableBusqueda=" + tipoResponsableBusqueda
				+ ", idAreaBusqueda=" + idAreaBusqueda + ", areaAdscripcion=" + areaAdscripcion + ", cambiaArea="
				+ cambiaArea + ", responsable=" + responsable + ", responsableVista=" + responsableVista
				+ ", responsables=" + responsables + ", responsablesVista=" + responsablesVista + ", areas=" + areas
				+ ", areasResponsablesLigados=" + areasResponsablesLigados + ", existeResponsableBD="
				+ existeResponsableBD + "]";
	}
}
