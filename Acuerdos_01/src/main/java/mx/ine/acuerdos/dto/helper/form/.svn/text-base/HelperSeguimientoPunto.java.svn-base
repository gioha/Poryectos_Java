package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOCEstatusPuntos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.HelperDTOTipoMov;

import org.primefaces.model.TreeNode;

/**
 * Clase contenedora de los campos de la vista de Consulta y Seguimiento de Punto
 * (tanto del lado del área reportante y del lado del secretariado) y de los
 * elementos necesarios para soportar su funcionalidad
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public class HelperSeguimientoPunto implements Serializable {
	private static final long serialVersionUID = -4108639030761721235L;
	
	private DTOAcuerdos acuerdo;
	private DTOPuntosAcuerdo punto;	
	private DTOCClasificaciones clasificacion;
	private List<DTOSeguimientosCG> accionesSegPunto;
	private List<DTOSeguimientosCG> accionesSegPorArea;
	private List<HelperDTOTipoMov> tiposMovimiento;
	private List<DTOCEstatusPuntos> estatusPuntos;
	private List<Integer> areasInvolucradas;
	private List<Integer> responsConjInvoluc;
	private List<DTOCAreas> areasInvolucPunto;
	private List<DTOResponsables> resposInvolucPunto;
	private TreeNode arbolResponsables;
	private Integer tipoMovimiento;
	private String descMovimiento;
	private String descRechazoCierre;
	private String msjMovimiento;
	private String statusInsert;
	private DTOSeguimientosCG movModificable;
	private boolean cerrarPunto;
	private boolean esMovValido;

	private DTOResponsables dtoResponsable;
	private boolean esVistaCompartida;

	public HelperSeguimientoPunto() {
		this.cerrarPunto = false;
		this.esVistaCompartida = false;
		this.accionesSegPorArea = new ArrayList<DTOSeguimientosCG>();
	}

	public DTOAcuerdos getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(DTOAcuerdos acuerdo) {
		this.acuerdo = acuerdo;
	}

	public DTOPuntosAcuerdo getPunto() {
		return punto;
	}

	public void setPunto(DTOPuntosAcuerdo punto) {
		this.punto = punto;
	}

	public DTOCClasificaciones getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(DTOCClasificaciones clasificacion) {
		this.clasificacion = clasificacion;
	}

	public List<DTOSeguimientosCG> getAccionesSegPunto() {
		return accionesSegPunto;
	}

	public void setAccionesSegPunto(List<DTOSeguimientosCG> accionesSegPunto) {
		this.accionesSegPunto = accionesSegPunto;
	}

	public List<DTOSeguimientosCG> getAccionesSegPorArea() {
		return accionesSegPorArea;
	}

	public void setAccionesSegPorArea(List<DTOSeguimientosCG> accionesSegPorArea) {
		this.accionesSegPorArea = accionesSegPorArea;
	}

	public List<HelperDTOTipoMov> getTiposMovimiento() {
		return tiposMovimiento;
	}

	public void setTiposMovimiento(List<HelperDTOTipoMov> tiposMovimiento) {
		this.tiposMovimiento = tiposMovimiento;
	}

	public List<DTOCEstatusPuntos> getEstatusPuntos() {
		return estatusPuntos;
	}

	public void setEstatusPuntos(List<DTOCEstatusPuntos> estatusPuntos) {
		this.estatusPuntos = estatusPuntos;
	}
	
	public List<Integer> getAreasInvolucradas() {
		return areasInvolucradas;
	}

	public void setAreasInvolucradas(List<Integer> areasInvolucradas) {
		this.areasInvolucradas = areasInvolucradas;
	}

	public List<Integer> getResponsConjInvoluc() {
		return responsConjInvoluc;
	}

	public void setResponsConjInvoluc(List<Integer> responsConjInvoluc) {
		this.responsConjInvoluc = responsConjInvoluc;
	}

	public List<DTOCAreas> getAreasInvolucPunto() {
		return areasInvolucPunto;
	}

	public void setAreasInvolucPunto(List<DTOCAreas> areasInvolucPunto) {
		this.areasInvolucPunto = areasInvolucPunto;
	}

	public List<DTOResponsables> getResposInvolucPunto() {
		return resposInvolucPunto;
	}

	public void setResposInvolucPunto(List<DTOResponsables> resposInvolucPunto) {
		this.resposInvolucPunto = resposInvolucPunto;
	}

	public TreeNode getArbolResponsables() {
		return arbolResponsables;
	}

	public void setArbolResponsables(TreeNode arbolResponsables) {
		this.arbolResponsables = arbolResponsables;
	}

	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getDescMovimiento() {
		return descMovimiento;
	}

	public void setDescMovimiento(String descMovimiento) {
		this.descMovimiento = descMovimiento;
	}

	public String getDescRechazoCierre() {
		return descRechazoCierre;
	}

	public void setDescRechazoCierre(String descRechazoCierre) {
		this.descRechazoCierre = descRechazoCierre;
	}

	public String getMsjMovimiento() {
		return msjMovimiento;
	}

	public void setMsjMovimiento(String msjMovimiento) {
		this.msjMovimiento = msjMovimiento;
	}

	public String getStatusInsert() {
		return statusInsert;
	}

	public void setStatusInsert(String statusInsert) {
		this.statusInsert = statusInsert;
	}

	public DTOSeguimientosCG getMovModificable() {
		return movModificable;
	}

	public void setMovModificable(DTOSeguimientosCG movModificable) {
		this.movModificable = movModificable;
	}

	public boolean getCerrarPunto() {
		return cerrarPunto;
	}

	public void setCerrarPunto(boolean cerrarPunto) {
		this.cerrarPunto = cerrarPunto;
	}

	public boolean getEsMovValido() {
		return esMovValido;
	}

	public void setEsMovValido(boolean esMovValido) {
		this.esMovValido = esMovValido;
	}

	public DTOResponsables getDtoResponsable() {
		return dtoResponsable;
	}

	public void setDtoResponsable(DTOResponsables dtoResponsable) {
		this.dtoResponsable = dtoResponsable;
	}

	public boolean getEsVistaCompartida() {
		return esVistaCompartida;
	}

	public void setEsVistaCompartida(boolean esVistaCompartida) {
		this.esVistaCompartida = esVistaCompartida;
	}

}
