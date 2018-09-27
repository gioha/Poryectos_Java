package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASRegistroDocumentosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroDocumentosInterface;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;

@Component("bsdRegDocumentos")
@Scope("prototype")
public class BSDRegistroDocumentos implements BSDRegistroDocumentosInterface, Serializable {

	private static final long serialVersionUID = 2396944507961051648L;
	
	private static final Log log = LogFactory.getLog(BSDRegistroDocumentos.class);
	
	@Autowired
	@Qualifier("asRegDocumentos")
	private transient ASRegistroDocumentosInterface asRegDocumentos;

	@Override
	public List<DTOCTipoDocumento> obtenTiposDocumentos() {
		try {
			log.info("BSDRegistroDocumentos.obtenTiposDocumentos");
			return asRegDocumentos.obtenTiposDocumentos();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BSDRegistroDocumentos.obtenTiposDocumentos: error.");
			return null;
		}
	}

	@Override
	public List<DTOCAreas> obtenListadoCatalogoAreas(int tipoArea) {
		try {
			log.info("BSDRegistroDocumentos.obtenAreas");
			return asRegDocumentos.obtenElementosCatalogoAreas(tipoArea);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BSDRegistroDocumentos.obtenAreas: error.");
			return null;
		}
	}

	@Override
	public boolean guardaDocumento(HLPFormDocumentos formDocumento) {
		try {
			asRegDocumentos.guardaDocumento(formDocumento);
			return true;
		} catch (Exception e) {
			log.error("BSDRegistroDocumentos.guardaDocumento: error.");
			e.printStackTrace();
			return false;
		}
	}

}
