package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;
import mx.ine.acuerdos.dto.DTOConvocatorias;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.HelperDTOMesesAnio;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

/**
 * Clase contenedora de los campos de las vistas de Captura y Consulta de la
 * Convocatoria y de los elementos necesarios para soportar su funcionalidad
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public class HelperConvocatoria implements Serializable {
	private static final long serialVersionUID = -5916499180099778183L;

	private Integer idAnio;
	private Integer mes;
	private Integer idComision;
	private Date inicioPeriodo;
	private Integer numSesion;
	private Integer numSesionSig;
	private String descNumSesion;
	private UploadedFile documentoAdjunto;
	private String nomArchivo;
	private List<DTOTipoSesiones> tiposDeSesiones;
	private List<DTOTipoSesiones> tiposDeSesionesAux;
	private DTOComisiones comision;
	private List<DTOComisiones> listaComisiones;
	private List<DTOComisiones> comSeleccionadas;
	private List<DTOComisiones> comisionesFinal;
	private List<DTOIntegrantesComision> integComision;
	private DTOResponsables dtoResponsable;
	private TreeNode arbolComisiones;
	private List<DTOResponsables> responsComision;
	private Integer tipoSesion;
	private String caracter;
	private String fechaFormato;
	private Date fechaSesion;
	private Date horaSesion;
	private String lugarConvoc;
	private String descLugar;
	private String asuntoRelevante;
	private boolean comisionConjunta;
	private List<DTOOrdenesDelDia> ordenDelDia;
	private DTOOrdenesDelDia ordenModificable;
	private String descPunto;
	private List<DTOCTipoIntegComision> tipoIntegComision;
	private List<DTOComisionesUnidas> comisionesUnidas;
	private List<DTOConvocatorias> listaConvocatorias;
	private List<DTOConvocatorias> listaConvocFiltro;
	private DTOConvocatorias convocatoria;
	private List<HelperDTOMesesAnio> listaMesesAnio;
	private List<Integer> listaAnios;
	private UploadedFile acuerdoFile;
	private String rutaAcuerdoFile;	
	private String renameAcuerdoFile;
	private String nomArchEn;
	private String nomArch;
	private boolean esExtNoPermitida;
	private boolean acuerdoAdjunto;
	private boolean esTamNoPermitido;
	private boolean exitoInsercion;

	private String nombreZIP;
	private boolean esTamNoPermitidoZIP;
	private boolean esExtNoPermitidaZIP;
	private UploadedFile adjuntoFileZIP;
	private String rutaFileZIP;	
	private String renombreFileZIP;
	private boolean guardaFileZIP;

	public HelperConvocatoria() {
		this.idAnio					= new Integer(0);
//		this.mes					= new Integer(0);
		this.idComision				= new Integer(0);
		this.inicioPeriodo				= new Date();
		this.numSesion				= new Integer(0);
		this.numSesionSig			= new Integer(0);
		this.descNumSesion			= "";
		this.nomArchivo				= "";
		this.tiposDeSesiones		= new ArrayList<DTOTipoSesiones>();
		this.tiposDeSesionesAux		= new ArrayList<DTOTipoSesiones>();
		this.comision				= new DTOComisiones();
		this.listaComisiones		= new ArrayList<DTOComisiones>();
		this.comSeleccionadas		= new ArrayList<DTOComisiones>();
		this.comisionesFinal		= new ArrayList<DTOComisiones>();
		this.integComision			= new ArrayList<DTOIntegrantesComision>();
		this.dtoResponsable			= new DTOResponsables();
		this.arbolComisiones		= new DefaultTreeNode();
		this.responsComision		= new ArrayList<DTOResponsables>();
//		this.tipoSesion				= new Integer(0);
		this.caracter				= "";
		this.fechaFormato			= (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
//		this.fechaSesion			= new Date();
//		this.horaSesion				= new Date();
		this.lugarConvoc 			= "Consejo general";
		this.descLugar				= "";
		this.asuntoRelevante		= "";
		this.comisionConjunta		= false;
		this.ordenDelDia			= new ArrayList<DTOOrdenesDelDia>();
		this.ordenModificable		= new DTOOrdenesDelDia();
		this.descPunto				= "";
		this.tipoIntegComision		= new ArrayList<DTOCTipoIntegComision>();
		this.comisionesUnidas		= new ArrayList<DTOComisionesUnidas>();
		this.listaConvocatorias		= new ArrayList<DTOConvocatorias>();
		this.listaConvocFiltro		= new ArrayList<DTOConvocatorias>();
//		this.convocatoria			= new DTOConvocatorias();
		this.listaMesesAnio			= new ArrayList<HelperDTOMesesAnio>();
		this.listaAnios				= new ArrayList<Integer>();
		this.rutaAcuerdoFile		= "";
		this.renameAcuerdoFile		= "";
		this.nomArchEn				= "";
		this.nomArch				= "";
		this.esExtNoPermitida		= false;
		this.acuerdoAdjunto			= false;
		this.esTamNoPermitido		= false;
		this.exitoInsercion			= false;

		this.nombreZIP				= "";
		this.esTamNoPermitidoZIP	= false;
		this.esExtNoPermitidaZIP	= false;
		this.rutaFileZIP			= "";
		this.renombreFileZIP		= "";
		this.guardaFileZIP			= false;
	}

	public Integer getIdAnio() {
		return idAnio;
	}

	public void setIdAnio(Integer idAnio) {
		this.idAnio = idAnio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getIdComision() {
		return idComision;
	}

	public void setIdComision(Integer idComision) {
		this.idComision = idComision;
	}

	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public Integer getNumSesion() {
		return numSesion;
	}

	public void setNumSesion(Integer numSesion) {
		this.numSesion = numSesion;
	}

	public Integer getNumSesionSig() {
		return numSesionSig;
	}

	public void setNumSesionSig(Integer numSesionSig) {
		this.numSesionSig = numSesionSig;
	}

	public String getDescNumSesion() {
		return descNumSesion;
	}

	public void setDescNumSesion(String descNumSesion) {
		this.descNumSesion = descNumSesion;
	}

	public UploadedFile getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(UploadedFile documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	public String getNomArchivo() {
		return nomArchivo;
	}

	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	public List<DTOTipoSesiones> getTiposDeSesiones() {
		return tiposDeSesiones;
	}

	public void setTiposDeSesiones(List<DTOTipoSesiones> tiposDeSesiones) {
		this.tiposDeSesiones = tiposDeSesiones;
	}

	public List<DTOTipoSesiones> getTiposDeSesionesAux() {
		return tiposDeSesionesAux;
	}

	public void setTiposDeSesionesAux(List<DTOTipoSesiones> tiposDeSesionesAux) {
		this.tiposDeSesionesAux = tiposDeSesionesAux;
	}

	public DTOComisiones getComision() {
		return comision;
	}

	public void setComision(DTOComisiones comision) {
		this.comision = comision;
	}

	public List<DTOComisiones> getListaComisiones() {
		return listaComisiones;
	}

	public void setListaComisiones(List<DTOComisiones> listaComisiones) {
		this.listaComisiones = listaComisiones;
	}

	public List<DTOComisiones> getComSeleccionadas() {
		return comSeleccionadas;
	}

	public void setComSeleccionadas(List<DTOComisiones> comSeleccionadas) {
		this.comSeleccionadas = comSeleccionadas;
	}

	public List<DTOComisiones> getComisionesFinal() {
		return comisionesFinal;
	}

	public void setComisionesFinal(List<DTOComisiones> comisionesFinal) {
		this.comisionesFinal = comisionesFinal;
	}

	public List<DTOIntegrantesComision> getIntegComision() {
		return integComision;
	}

	public void setIntegComision(List<DTOIntegrantesComision> integComision) {
		this.integComision = integComision;
	}

	public DTOResponsables getDtoResponsable() {
		return dtoResponsable;
	}

	public void setDtoResponsable(DTOResponsables dtoResponsable) {
		this.dtoResponsable = dtoResponsable;
	}

	public TreeNode getArbolComisiones() {
		return arbolComisiones;
	}

	public void setArbolComisiones(TreeNode arbolComisiones) {
		this.arbolComisiones = arbolComisiones;
	}

	public List<DTOResponsables> getResponsComision() {
		return responsComision;
	}

	public void setResponsComision(List<DTOResponsables> responsComision) {
		this.responsComision = responsComision;
	}

	public Integer getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(Integer tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public String getFechaFormato() {
		return fechaFormato;
	}

	public void setFechaFormato(String fechaFormato) {
		this.fechaFormato = fechaFormato;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public Date getHoraSesion() {
		return horaSesion;
	}

	public void setHoraSesion(Date horaSesion) {
		this.horaSesion = horaSesion;
	}

	public String getLugarConvoc() {
		return lugarConvoc;
	}

	public void setLugarConvoc(String lugarConvoc) {
		this.lugarConvoc = lugarConvoc;
	}

	public String getDescLugar() {
		return descLugar;
	}

	public void setDescLugar(String descLugar) {
		this.descLugar = descLugar;
	}

	public String getAsuntoRelevante() {
		return asuntoRelevante;
	}

	public void setAsuntoRelevante(String asuntoRelevante) {
		this.asuntoRelevante = asuntoRelevante;
	}

	public boolean getComisionConjunta() {
		return comisionConjunta;
	}

	public void setComisionConjunta(boolean comisionConjunta) {
		this.comisionConjunta = comisionConjunta;
	}

	public List<DTOOrdenesDelDia> getOrdenDelDia() {
		return ordenDelDia;
	}

	public void setOrdenDelDia(List<DTOOrdenesDelDia> ordenDelDia) {
		this.ordenDelDia = ordenDelDia;
	}

	public DTOOrdenesDelDia getOrdenModificable() {
		return ordenModificable;
	}

	public void setOrdenModificable(DTOOrdenesDelDia ordenModificable) {
		this.ordenModificable = ordenModificable;
	}

	public String getDescPunto() {
		return descPunto;
	}

	public void setDescPunto(String descPunto) {
		this.descPunto = descPunto;
	}

	public List<DTOCTipoIntegComision> getTipoIntegComision() {
		return tipoIntegComision;
	}

	public void setTipoIntegComision(List<DTOCTipoIntegComision> tipoIntegComision) {
		this.tipoIntegComision = tipoIntegComision;
	}

	public List<DTOComisionesUnidas> getComisionesUnidas() {
		return comisionesUnidas;
	}

	public void setComisionesUnidas(List<DTOComisionesUnidas> comisionesUnidas) {
		this.comisionesUnidas = comisionesUnidas;
	}

	public List<DTOConvocatorias> getListaConvocatorias() {
		return listaConvocatorias;
	}

	public void setListaConvocatorias(List<DTOConvocatorias> listaConvocatorias) {
		this.listaConvocatorias = listaConvocatorias;
	}

	public List<DTOConvocatorias> getListaConvocFiltro() {
		return listaConvocFiltro;
	}

	public void setListaConvocFiltro(List<DTOConvocatorias> listaConvocFiltro) {
		this.listaConvocFiltro = listaConvocFiltro;
	}

	public DTOConvocatorias getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(DTOConvocatorias convocatoria) {
		this.convocatoria = convocatoria;
	}

	public List<HelperDTOMesesAnio> getListaMesesAnio() {
		return listaMesesAnio;
	}

	public void setListaMesesAnio(List<HelperDTOMesesAnio> listaMesesAnio) {
		this.listaMesesAnio = listaMesesAnio;
	}

	public List<Integer> getListaAnios() {
		return listaAnios;
	}

	public void setListaAnios(List<Integer> listaAnios) {
		this.listaAnios = listaAnios;
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

	public boolean getEsExtNoPermitida() {
		return esExtNoPermitida;
	}

	public void setEsExtNoPermitida(boolean esExtNoPermitida) {
		this.esExtNoPermitida = esExtNoPermitida;
	}

	public boolean getAcuerdoAdjunto() {
		return acuerdoAdjunto;
	}

	public void setAcuerdoAdjunto(boolean acuerdoAdjunto) {
		this.acuerdoAdjunto = acuerdoAdjunto;
	}

	public boolean getEsTamNoPermitido() {
		return esTamNoPermitido;
	}

	public void setEsTamNoPermitido(boolean esTamNoPermitido) {
		this.esTamNoPermitido = esTamNoPermitido;
	}

	public boolean getExitoInsercion() {
		return exitoInsercion;
	}

	public void setExitoInsercion(boolean exitoInsercion) {
		this.exitoInsercion = exitoInsercion;
	}

	public String getNombreZIP() {
		return nombreZIP;
	}

	public void setNombreZIP(String nombreZIP) {
		this.nombreZIP = nombreZIP;
	}

	public boolean isEsTamNoPermitidoZIP() {
		return esTamNoPermitidoZIP;
	}

	public void setEsTamNoPermitidoZIP(boolean esTamNoPermitidoZIP) {
		this.esTamNoPermitidoZIP = esTamNoPermitidoZIP;
	}

	public boolean isEsExtNoPermitidaZIP() {
		return esExtNoPermitidaZIP;
	}

	public void setEsExtNoPermitidaZIP(boolean esExtNoPermitidaZIP) {
		this.esExtNoPermitidaZIP = esExtNoPermitidaZIP;
	}

	public UploadedFile getAdjuntoFileZIP() {
		return adjuntoFileZIP;
	}

	public void setAdjuntoFileZIP(UploadedFile adjuntoFileZIP) {
		this.adjuntoFileZIP = adjuntoFileZIP;
	}

	public String getRutaFileZIP() {
		return rutaFileZIP;
	}

	public void setRutaFileZIP(String rutaFileZIP) {
		this.rutaFileZIP = rutaFileZIP;
	}

	public String getRenombreFileZIP() {
		return renombreFileZIP;
	}

	public void setRenombreFileZIP(String renombreFileZIP) {
		this.renombreFileZIP = renombreFileZIP;
	}

	public boolean getGuardaFileZIP() {
		return guardaFileZIP;
	}

	public void setGuardaFileZIP(boolean guardaFileZIP) {
		this.guardaFileZIP = guardaFileZIP;
	}

}