package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOTipoSesiones;

import org.primefaces.model.UploadedFile;

/**
 * Clase que proveer las varibales necesarias para manipular los controles de la pantalla de Registro de Acuerdos
 *
 * @author Giovanni
 * @version 1.0
 * @since 12/10/2017 
 */
public class HLPFormRegistroAcuerdos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3287579640796035515L;

	
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
	
	private List<String> listaImgsInfografias;
	
	private List<DTOCTipoDocumento> tiposDocumentos;

	private Integer lblIniciales;
	
	boolean ligaValida;
	
	private String nomArchExt;

	boolean tamNoPermitido;
	
	public HLPFormRegistroAcuerdos() {

    	this.acuerdo 			  = new DTOAcuerdos();
    	this.hayEngrose 		  = false;
    	this.datosReqCompletos 	  = false;
    	this.tiposSesiones		  = new ArrayList<DTOTipoSesiones>();
    	this.rutaAcuerdoFile 	  = "";
    	this.renameAcuerdoFile 	  = "";
    	this.rutaEngroseFile 	  = "";
    	this.renameEngroseFile 	  = "";
    	this.engroseAdjunto 	  = false;
    	this.acuerdoAdjunto		  = false;
    	this.existeAcuerdo        = false;
    	this.acuerdosPrueba       = "";
    	this.nomArch              = "";
    	this.nomArchEn            = "";
    	this.fechaMin             = new Date();
    	this.formateador          = new SimpleDateFormat("dd/MM/yyyy");
    	this.fechaFormateada      = formateador.format(fechaMin);
    	this.noRegistroRG         = false;
    	this.listaImgsInfografias = new ArrayList<String>();
    	this.tiposDocumentos      = new ArrayList<DTOCTipoDocumento>();
    	this.lblIniciales         = new Integer(0); 
    	this.ligaValida           = false;
    	this.nomArchExt           = " ";
    	this.tamNoPermitido       = false;
    }



	public String getNomArchEn() {
		return nomArchEn;
	}



	public void setNomArchEn(String nomArchEn) {
		this.nomArchEn = nomArchEn;
	}



	public String getNomArch() {
		return nomArch;
	}



	public void setNomArch(String nomArch) {
		this.nomArch = nomArch;
	}



	public String getAcuerdosPrueba() {
		return acuerdosPrueba;
	}



	public void setAcuerdosPrueba(String acuerdosPrueba) {
		this.acuerdosPrueba = acuerdosPrueba;
	}



	public DTOAcuerdos getAcuerdo() {
		return acuerdo;
	}



	public void setAcuerdo(DTOAcuerdos acuerdo) {
		this.acuerdo = acuerdo;
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



	public List<DTOTipoSesiones> getTiposSesiones() {
		return tiposSesiones;
	}



	public void setTiposSesiones(List<DTOTipoSesiones> tiposSesiones) {
		this.tiposSesiones = tiposSesiones;
	}



	public UploadedFile getAcuerdoFile() {
		return acuerdoFile;
	}



	public void setAcuerdoFile(UploadedFile acuerdoFile) {
		this.acuerdoFile = acuerdoFile;
	}



	public UploadedFile getEngroseFile() {
		return engroseFile;
	}



	public void setEngroseFile(UploadedFile engroseFile) {
		this.engroseFile = engroseFile;
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



	public List<String> getListaImgsInfografias() {
		return listaImgsInfografias;
	}



	public void setListaImgsInfografias(List<String> listaImgsInfografias) {
		this.listaImgsInfografias = listaImgsInfografias;
	}



	public List<DTOCTipoDocumento> getTiposDocumentos() {
		return tiposDocumentos;
	}



	public void setTiposDocumentos(List<DTOCTipoDocumento> tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
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
