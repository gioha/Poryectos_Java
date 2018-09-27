package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOTipoSesiones;

import org.primefaces.model.UploadedFile;

public class HLPFormModificarAcuerdos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6644114330214014557L;

	
	private DTOAcuerdos acuerdo;
	
	private List<DTOTipoSesiones> tiposSesiones;
	
	private boolean hayEngrose;
	
	private boolean datosReqCompletos;
	
	private UploadedFile acuerdoFile;
	
	private String rutaAcuerdoFile;
	
	private String renameAcuerdoFile;
	
	private UploadedFile engroseFile;
	
	private String rutaEngroseFile;
	
	private String renameEngroseFile;
	
	boolean acuerdoAdjunto;
	
	boolean engroseAdjunto;
	
	boolean existeAcuerdo;
	
	private String acuerdosPrueba;
	
	private String nomArch;
	
	private String nomArchEn;
	
	private Date fechaMin;
	
	private SimpleDateFormat formateador;
	
	private String fechaFormateada;
	
	boolean noRegistroRG;
	
	private String rutaPrevisualizacion;
	
	private String rutaPrevisualizacionEngrose;
	
	private List<String> listaImgsInfografias;
	
	private String acuerdoOreolucion;
	
	private Integer lblIniciales;
	
	boolean ligaValida;

	private String nomArchExt;

	boolean tamNoPermitido;

    public HLPFormModificarAcuerdos() {

    	this.acuerdo 			         = new DTOAcuerdos();
    	this.hayEngrose 		         = false;
    	this.datosReqCompletos 	         = false;
    	this.tiposSesiones		         = new ArrayList<DTOTipoSesiones>();
    	this.rutaAcuerdoFile 	         = "";
    	this.renameAcuerdoFile 	         = "";
    	this.rutaEngroseFile 	         = "";
    	this.renameEngroseFile 	         = "";
    	this.engroseAdjunto 	         = false;
    	this.acuerdoAdjunto		         = false;
    	this.existeAcuerdo               = false;
    	this.acuerdosPrueba              = "";
    	this.nomArch                     = "";
    	this.nomArchEn                   = "";
    	this.fechaMin                    = new Date();
    	this.formateador                 = new SimpleDateFormat("dd/MM/yyyy");
    	this.fechaFormateada             = formateador.format(fechaMin);
    	this.noRegistroRG                = false;
    	this.rutaPrevisualizacion        = "";
    	this.rutaPrevisualizacionEngrose = "";
    	this.listaImgsInfografias        = new ArrayList<String>();
    	this.acuerdoOreolucion           = "";
    	this.lblIniciales                = new Integer(0);
    	this.ligaValida                  = false;
    	this.nomArchExt                  = " ";
    	this.tamNoPermitido              = false;
    }



	public DTOAcuerdos getAcuerdo() {
		return acuerdo;
	}



	public void setAcuerdo(DTOAcuerdos acuerdo) {
		this.acuerdo = acuerdo;
	}



	public List<DTOTipoSesiones> getTiposSesiones() {
		return tiposSesiones;
	}



	public void setTiposSesiones(List<DTOTipoSesiones> tiposSesiones) {
		this.tiposSesiones = tiposSesiones;
	}



	public boolean isHayEngrose() {
		return hayEngrose;
	}



	public void setHayEngrose(boolean hayEngrose) {
		this.hayEngrose = hayEngrose;
	}



	public boolean isDatosReqCompletos() {
		return datosReqCompletos;
	}



	public void setDatosReqCompletos(boolean datosReqCompletos) {
		this.datosReqCompletos = datosReqCompletos;
	}



	public UploadedFile getAcuerdoFile() {
		return acuerdoFile;
	}



	public void setAcuerdoFile(UploadedFile acuerdoFile) {
		this.acuerdoFile = acuerdoFile;
	}



	public String getRutaAcuerdoFile() {
		return rutaAcuerdoFile;
	}



	public void setRutaAcuerdoFile(String rutaAcuerdoFile) {
		this.rutaAcuerdoFile = rutaAcuerdoFile;
	}



	public String getRenameAcuerdoFile() {
		return renameAcuerdoFile;
	}



	public void setRenameAcuerdoFile(String renameAcuerdoFile) {
		this.renameAcuerdoFile = renameAcuerdoFile;
	}



	public UploadedFile getEngroseFile() {
		return engroseFile;
	}



	public void setEngroseFile(UploadedFile engroseFile) {
		this.engroseFile = engroseFile;
	}



	public String getRutaEngroseFile() {
		return rutaEngroseFile;
	}



	public void setRutaEngroseFile(String rutaEngroseFile) {
		this.rutaEngroseFile = rutaEngroseFile;
	}



	public String getRenameEngroseFile() {
		return renameEngroseFile;
	}



	public void setRenameEngroseFile(String renameEngroseFile) {
		this.renameEngroseFile = renameEngroseFile;
	}



	public boolean isAcuerdoAdjunto() {
		return acuerdoAdjunto;
	}



	public void setAcuerdoAdjunto(boolean acuerdoAdjunto) {
		this.acuerdoAdjunto = acuerdoAdjunto;
	}



	public boolean isEngroseAdjunto() {
		return engroseAdjunto;
	}



	public void setEngroseAdjunto(boolean engroseAdjunto) {
		this.engroseAdjunto = engroseAdjunto;
	}



	public boolean isExisteAcuerdo() {
		return existeAcuerdo;
	}



	public void setExisteAcuerdo(boolean existeAcuerdo) {
		this.existeAcuerdo = existeAcuerdo;
	}



	public String getAcuerdosPrueba() {
		return acuerdosPrueba;
	}



	public void setAcuerdosPrueba(String acuerdosPrueba) {
		this.acuerdosPrueba = acuerdosPrueba;
	}



	public String getNomArch() {
		return nomArch;
	}



	public void setNomArch(String nomArch) {
		this.nomArch = nomArch;
	}



	public String getNomArchEn() {
		return nomArchEn;
	}



	public void setNomArchEn(String nomArchEn) {
		this.nomArchEn = nomArchEn;
	}


	public String getFechaFormateada() {
		return fechaFormateada;
	}



	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}



	public boolean isNoRegistroRG() {
		return noRegistroRG;
	}



	public void setNoRegistroRG(boolean noRegistroRG) {
		this.noRegistroRG = noRegistroRG;
	}



	public String getRutaPrevisualizacion() {
		return rutaPrevisualizacion;
	}

	
	public void setRutaPrevisualizacion(String rutaPrevisualizacion) {
		this.rutaPrevisualizacion = rutaPrevisualizacion;
	}

	
	public String getRutaPrevisualizacionEngrose() {
		return rutaPrevisualizacionEngrose;
	}

	
	public void setRutaPrevisualizacionEngrose(String rutaPrevisualizacionEngrose) {
		this.rutaPrevisualizacionEngrose = rutaPrevisualizacionEngrose;
	}

	
	
	public List<String> getListaImgsInfografias() {
		return listaImgsInfografias;
	}

	

	public void setListaImgsInfografias(List<String> listaImgsInfografias) {
		this.listaImgsInfografias = listaImgsInfografias;
	}



	public String getAcuerdoOreolucion() {
		return acuerdoOreolucion;
	}



	public void setAcuerdoOreolucion(String acuerdoOreolucion) {
		this.acuerdoOreolucion = acuerdoOreolucion;
	}



	public Integer getLblIniciales() {
		return lblIniciales;
	}



	public void setLblIniciales(Integer lblIniciales) {
		this.lblIniciales = lblIniciales;
	}



	public boolean isLigaValida() {
		return ligaValida;
	}



	public void setLigaValida(boolean ligaValida) {
		this.ligaValida = ligaValida;
	}



	public String getNomArchExt() {
		return nomArchExt;
	}



	public void setNomArchExt(String nomArchExt) {
		this.nomArchExt = nomArchExt;
	}



	public boolean isTamNoPermitido() {
		return tamNoPermitido;
	}



	public void setTamNoPermitido(boolean tamNoPermitido) {
		this.tamNoPermitido = tamNoPermitido;
	}
}
