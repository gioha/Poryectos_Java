package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDModificaDocumentosInterface;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBModificaDocumentos extends MBGeneric implements Serializable {

	private static final long serialVersionUID = -3402255550983540251L;
	private static final Log log = LogFactory.getLog(MBModificaDocumentos.class);
	
	@Autowired
	@Qualifier("bsdModDocumentos")
	private transient BSDModificaDocumentosInterface bsdModDocumentos;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	private HLPFormDocumentos formDocumentos;
	
	public void init() {
		//System.out.println("init MBModificaDocumentos");
		this.formDocumentos = new HLPFormDocumentos();
		this.formDocumentos.setTiposDocumentos(bsdModDocumentos.obtenTiposDocumentos());
		this.formDocumentos.setAreas(bsdModDocumentos.obtenListadoCatalogoAreas(1));
		this.formDocumentos.setListaOPL(bsdModDocumentos.obtenListadoCatalogoAreas(3));
		this.formDocumentos.setDocumento(bsdModDocumentos.encuentraDocumentoPorId(("INE/DO/123")));
		this.formDocumentos.setPeriodos(bsdModDocumentos.encuentraPeriodosDocumento("INE/DO/123"));
	}

	public HLPFormDocumentos getFormDocumentos() {
		return formDocumentos;
	}

	public void setFormDocumentos(HLPFormDocumentos formDocumentos) {
		this.formDocumentos = formDocumentos;
	}
	
	public void generaPeriodosAtencion() {
		//System.out.println("BSDRegistroDocumentosInterface.generaPeriodosAtencion");
		List<DTODocumentosPeriodos> periodosTemp = this.formDocumentos.getPeriodos();
		int numPeriodos = this.formDocumentos.getDocumento().getNumPeriodo();
		int tamPeriodos = periodosTemp.size();
		int dif = Math.abs(numPeriodos - tamPeriodos);
		
		for (int i = 0; i < dif; i++) {
			if (tamPeriodos > numPeriodos) {
				periodosTemp.remove(periodosTemp.size() -1);
			} else {
				periodosTemp.add(new DTODocumentosPeriodos());
			}
		}
		
		this.formDocumentos.setPeriodos(periodosTemp);
	}
	
	public void modificaDocumento() {
		if (bsdModDocumentos.actualizaDocumento(this.formDocumentos)) {
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					"Los datos se han guardado correctamente.");
		}
		else {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE,
					"Ocurrió un error, revise los datos ingresados.");
			
		}
	}
	
	public void adjuntaDocumento(ActionEvent actionEvent) {
		//System.out.println("Funka adjuntaDocumento");
		try {
			// si hay un PDF del oficio de notificacion que se haya adjuntado, lo guardamos en el gluster
			if (formDocumentos.getDocumentoFile() != null
					&& !formDocumentos.getDocumento().getId().getIdNumDocumento().equals("")) {
				
				tipoArchivo tipoArch;
				switch (formDocumentos.getDocumento().getIdTipoDocumento()) {
					case 1:
						tipoArch = tipoArchivo.ACUERDOS;
						break;
					case 2:
						tipoArch = tipoArchivo.CIRCULAR;
						break;
					case 3:
						tipoArch = tipoArchivo.OFICIO;
						break;
					case 4:
						tipoArch = tipoArchivo.LINEAMIENTO;
						break;
					default:
						tipoArch = tipoArchivo.OTRO;
				}
				
				formDocumentos.setRutaDocumentoFile(boArchivos.getRutaBaseGluster());
				formDocumentos.setRutaDocumentoFile(boArchivos.getSufijoRutaGluster(moduloArchivo.DOCUMENTOS_ARCHIVO , formDocumentos.getRutaDocumentoFile()));
				formDocumentos.setRenameDocumentoFile(boArchivos.getSufijoNombreTipoDocumentoFile(tipoArch, formDocumentos.getDocumento().getId().getIdNumDocumento(), formDocumentos.getDocumentoFile().getFileName()));
				formDocumentos.setDocumentoAdjunto(
						boArchivos.almacenarArchivoEnGluster(formDocumentos.getDocumentoFile() , formDocumentos.getRutaDocumentoFile() , formDocumentos.getRenameDocumentoFile())
				);
				formDocumentos.getDocumento().setUrlArchivoAdjunto(formDocumentos.getRutaDocumentoFile() + formDocumentos.getDocumentoFile());
			}
		} catch (Exception e) {			
			log.error("Error en MBRegistroPuntos.agregaAdjuntos() :"+ e.getMessage());	
		}
	}
	
	
	
	public void agregaDocumentoAdjunto(FileUploadEvent event) {
		try {
			formDocumentos.setDocumentoFile(event.getFile());
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Error en MBRegistroDocumentos.agregaDocumentoAdjunto() :"+ e.getMessage());				
		}
	}
}
