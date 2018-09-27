package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroDocumentosInterface;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("mbRegDocumentos")
public class MBRegistroDocumentos extends MBGeneric implements Serializable {

	private static final long serialVersionUID = 7540151924041411525L;
	private static final Log log = LogFactory.getLog(MBRegistroDocumentos.class);
	
	@Autowired
	@Qualifier("bsdRegDocumentos")
	private transient BSDRegistroDocumentosInterface bsdRegDocumentos;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	private HLPFormDocumentos formDocumentos;
	
	public void init() {
		//System.out.println("init del MBRegistroDocumentos 1st flag");
		this.formDocumentos = new HLPFormDocumentos();
		this.formDocumentos.setTiposDocumentos(bsdRegDocumentos.obtenTiposDocumentos());
		this.formDocumentos.setAreas(bsdRegDocumentos.obtenListadoCatalogoAreas(1));
		this.formDocumentos.setListaOPL(bsdRegDocumentos.obtenListadoCatalogoAreas(3));
		//System.out.println("init del MBRegistroDocumentos 2nd flag");
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
	
	public void registraDocumento() {
		//System.out.println("registraDocumento: funka!");
//		System.out.println(this.formDocumentos.toString());
		if (bsdRegDocumentos.guardaDocumento(this.formDocumentos)) {
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
				//System.out.println(formDocumentos.getRutaDocumentoFile());
				//System.out.println(formDocumentos.getRenameDocumentoFile());
				formDocumentos.setDocumentoAdjunto(
						boArchivos.almacenarArchivoEnGluster(formDocumentos.getDocumentoFile() , formDocumentos.getRutaDocumentoFile() , formDocumentos.getRenameDocumentoFile())
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
