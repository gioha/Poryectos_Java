package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;

import org.primefaces.model.UploadedFile;

public class HLPFormDocumentos implements Serializable {

	private static final long serialVersionUID = 4827472152305153021L;
	private DTODocumentosSivople documento;
	private List<DTOCTipoDocumento> tiposDocumentos;
	private List<DTOCAreas> areas;
	private List<DTOCAreas> listaOPL;
//	private String instancia;
//	private Integer numeroPeriodos;
	private List<DTODocumentosPeriodos> periodos;
	private UploadedFile documentoFile;
	private String rutaDocumentoFile;
	private String renameDocumentoFile;
	private boolean documentoAdjunto;
	
	public HLPFormDocumentos() {
		this.documento = new DTODocumentosSivople();
		this.tiposDocumentos = new ArrayList<>();
		this.areas = new ArrayList<>();
		this.listaOPL = new ArrayList<>();
//		this.instancia = "";
//		this.numeroPeriodos = null;
		this.periodos = new ArrayList<>();
		this.documentoFile = null;
		this.renameDocumentoFile = "";
		this.renameDocumentoFile = "";
		this.documentoAdjunto = false;
	}

	public DTODocumentosSivople getDocumento() {
		return documento;
	}

	public void setDocumento(DTODocumentosSivople documento) {
		this.documento = documento;
	}

	public List<DTOCTipoDocumento> getTiposDocumentos() {
		return tiposDocumentos;
	}

	public void setTiposDocumentos(List<DTOCTipoDocumento> tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}

	public List<DTOCAreas> getAreas() {
		return areas;
	}

	public void setAreas(List<DTOCAreas> areas) {
		this.areas = areas;
	}

//	public String getInstancia() {
//		return instancia;
//	}
//
//	public void setInstancia(String instancia) {
//		this.instancia = instancia;
//	}

//	public Integer getNumeroPeriodos() {
//		return numeroPeriodos;
//	}
//
//	public void setNumeroPeriodos(Integer numeroPeriodos) {
//		this.numeroPeriodos = numeroPeriodos;
//	}

	public List<DTODocumentosPeriodos> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<DTODocumentosPeriodos> periodos) {
		this.periodos = periodos;
	}

	public List<DTOCAreas> getListaOPL() {
		return listaOPL;
	}

	public void setListaOPL(List<DTOCAreas> listaOPL) {
		this.listaOPL = listaOPL;
	}

	public UploadedFile getDocumentoFile() {
		return documentoFile;
	}

	public void setDocumentoFile(UploadedFile documentoFile) {
		this.documentoFile = documentoFile;
	}

	public String getRutaDocumentoFile() {
		return rutaDocumentoFile;
	}

	public void setRutaDocumentoFile(String rutaDocumentoFile) {
		this.rutaDocumentoFile = rutaDocumentoFile;
	}

	public String getRenameDocumentoFile() {
		return renameDocumentoFile;
	}

	public void setRenameDocumentoFile(String renameDocumentoFile) {
		this.renameDocumentoFile = renameDocumentoFile;
	}

	public boolean isDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(boolean documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	@Override
	public String toString() {
		return "HLPFormRegistroDocumentos [documento=" + documento + ", periodos=" + periodos + ", documentoFile="
				+ documentoFile + ", rutaDocumentoFile=" + rutaDocumentoFile + ", renameDocumentoFile="
				+ renameDocumentoFile + ", documentoAdjunto=" + documentoAdjunto + "]";
	}

}
