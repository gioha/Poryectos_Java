package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASModificaDocumentosInterface;
import mx.ine.acuerdos.bsd.BSDModificaDocumentosInterface;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;

@Component("bsdModDocumentos")
@Scope("prototype")
public class BSDModificaDocumentos implements BSDModificaDocumentosInterface, Serializable {

	private static final long serialVersionUID = -7047556057522767552L;
	
	private static final Log log = LogFactory.getLog(BSDRegistroDocumentos.class);
	
	@Autowired
	@Qualifier("asModDocumentos")
	private transient ASModificaDocumentosInterface asModDocumentos;

	@Override
	public List<DTOCTipoDocumento> obtenTiposDocumentos() {
		try {
			log.info("BSDModificaDocumentos.obtenTiposDocumentos");
			return asModDocumentos.obtenTiposDocumentos();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BSDModificaDocumentos.obtenTiposDocumentos: error.");
			return null;
		}
	}

	@Override
	public List<DTOCAreas> obtenListadoCatalogoAreas(int tipoArea) {
		try {
			log.info("BSDModificaDocumentos.obtenAreas");
			return asModDocumentos.obtenElementosCatalogoAreas(tipoArea);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BSDModificaDocumentos.obtenAreas: error.");
			return null;
		}
	}

	@Override
	public boolean actualizaDocumento(HLPFormDocumentos formDocumento) {
		try {
			asModDocumentos.actulizaDocumento(formDocumento);
			return true;
		} catch (Exception e) {
			log.error("BSDModificaDocumentos.guardaDocumento: error.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DTODocumentosSivople encuentraDocumentoPorId(String idDocumento) {
		try {
			return asModDocumentos.encuentraDocumentoPorID(idDocumento);
		} catch (Exception e) {
			log.error("BSDModificaDocumentos.guardaDocumento: error.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DTODocumentosPeriodos> encuentraPeriodosDocumento(String idDocumento) {
		try {
			log.info("BSDModificaDocumentos.encuentraPeriodosDocumento");
			return asModDocumentos.encuentraPeriodosDocumento(idDocumento);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BSDModificaDocumentos.encuentraPeriodosDocumento: error.");
			return null;
		}
	}

}
