package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdoPK;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

public class HLPFormPuntos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984772268457161342L;

	
	private DTOPuntosAcuerdo punto;
	
	private DTOPuntosAcuerdoPK pkPunto;
	
	private List<DTOCClasificaciones> clasificaciones;
	
	private String numOrdinal ="";
	
	private boolean esPPN;
	
	private Date fechaSesion;
	
	private boolean esFechSesion;
	
	private UploadedFile oficioNotificacionFile;
	
	private String rutaOficioNotificacionFile;
	
	private String renameOficioNotificacionFile;
	
	private boolean oficioAdjunto;
	
	private Date fechaMin;
	
	private SimpleDateFormat formateador;
	
	private String fechaFormateada;

	private String nomArch;
	
	private List<String> listaImgsInfografias;
	
	private boolean notificacion;
	
	private String rutaPrevisualizacionOficio;

	private TreeNode arbolRespons;

	public HLPFormPuntos() {
		this.clasificaciones = new ArrayList<DTOCClasificaciones>();
		this.punto= new DTOPuntosAcuerdo();
		this.pkPunto = new DTOPuntosAcuerdoPK();
//		this.punto.setId(pkPunto);
		this.fechaSesion=null;
		this.esPPN= false;
		this.esFechSesion=true;
		this.numOrdinal = "";
		this.rutaOficioNotificacionFile 	= "";
		this.renameOficioNotificacionFile 	= "";
		this.oficioAdjunto = false;
    	this.fechaMin                       = new Date();
    	this.formateador                    = new SimpleDateFormat("dd/MM/yyyy");
    	this.fechaFormateada                = formateador.format(fechaMin);
    	this.nomArch                        = "";
    	this.listaImgsInfografias = new ArrayList<String>();
    	this.notificacion = false;
    	this.rutaPrevisualizacionOficio = "";
		this.arbolRespons					= new DefaultTreeNode();
	}

	public DTOPuntosAcuerdo getPunto() {
		return punto;
	}

	public void setPunto(DTOPuntosAcuerdo punto) {
		this.punto = punto;
	}

	public DTOPuntosAcuerdoPK getPkPunto() {
		return pkPunto;
	}

	public void setPkPunto(DTOPuntosAcuerdoPK pkPunto) {
		this.pkPunto = pkPunto;
	}

	public boolean isEsPPN() {
		return esPPN;
	}

	public void setEsPPN(boolean esPPN) {
		this.esPPN = esPPN;
	}

	public boolean isEsFechSesion() {
		return esFechSesion;
	}

	public void setEsFechSesion(boolean esFechSesion) {
		this.esFechSesion = esFechSesion;
	}

	public List<DTOCClasificaciones> getClasificaciones() {
		return clasificaciones;
	}

	public void setClasificaciones(
			List<DTOCClasificaciones> clasificaciones) {
		this.clasificaciones = clasificaciones;
	}

	public String getNumOrdinal() {
		return numOrdinal;
	}

	public void setNumOrdinal(String numOrdinal) {
		this.numOrdinal = numOrdinal;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public UploadedFile getOficioNotificacionFile() {
		return oficioNotificacionFile;
	}

	public void setOficioNotificacionFile(UploadedFile oficioNotificacionFile) {
		this.oficioNotificacionFile = oficioNotificacionFile;
	}

	public String getRutaOficioNotificacionFile() {
		return rutaOficioNotificacionFile;
	}

	public void setRutaOficioNotificacionFile(String rutaOficioNotificacionFile) {
		this.rutaOficioNotificacionFile = rutaOficioNotificacionFile;
	}

	public String getRenameOficioNotificacionFile() {
		return renameOficioNotificacionFile;
	}

	public void setRenameOficioNotificacionFile(String renameOficioNotificacionFile) {
		this.renameOficioNotificacionFile = renameOficioNotificacionFile;
	}

	public boolean isOficioAdjunto() {
		return oficioAdjunto;
	}

	public void setOficioAdjunto(boolean oficioAdjunto) {
		this.oficioAdjunto = oficioAdjunto;
	}

    public String getFechaFormateada() {
		return fechaFormateada;
	}

	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
	
	public String getNomArch() {
		return nomArch;
	}

	public void setNomArch(String nomArch) {
		this.nomArch = nomArch;
	}
	
	public List<String> getListaImgsInfografias() {
		return listaImgsInfografias;
	}

	public void setListaImgsInfografias(List<String> listaImgsInfografias) {
		this.listaImgsInfografias = listaImgsInfografias;
	}

	public boolean getNotificacion() {
		if(this.punto.getNotificacion() == 1)
			return true;
		else
			return false;
	}

	public void setNotificacion(boolean notificacion) {		
		this.notificacion = notificacion;
	}
	public void cambiaNotificacion(){
		if(this.notificacion)
			this.punto.setNotificacion(1);
		else
			this.punto.setNotificacion(0);
	}

	public String getRutaPrevisualizacionOficio() {
		return rutaPrevisualizacionOficio;
	}

	public void setRutaPrevisualizacionOficio(String rutaPrevisualizacionOficio) {
		this.rutaPrevisualizacionOficio = rutaPrevisualizacionOficio;
	}

	public TreeNode getArbolRespons() {
		return arbolRespons;
	}

	public void setArbolRespons(TreeNode arbolRespons) {
		this.arbolRespons = arbolRespons;
	}
	
}
