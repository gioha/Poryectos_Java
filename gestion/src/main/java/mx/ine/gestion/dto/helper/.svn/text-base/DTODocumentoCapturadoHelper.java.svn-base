package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoCcpEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;

/**
 * @(#)DTODocumentoCapturadoHelper.java 04/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase Helper para reprentar la captura de un documento. Contiene el documento
 * en sí, como todos sus anexos, destinatarios y ccp para la validacion y
 * guardado final o lectura completa de dicho documento.
 * 
 * @author Sergio Ley Garcia
 * @since 04/09/2017
 */
public class DTODocumentoCapturadoHelper implements Serializable{

	
	private static final long						serialVersionUID	= -141013219491981973L;
	/**
	 * Atributo que reprsenta el Documento en sí.
	 */
	private DTODocumentoEntity						documento;
	/**
	 * Atributo que reprsenta los remitentes del documento.
	 */
	private List<DTODocumentosRemitentesEntity>		remitentes;
	/**
	 * Atributo que reprsenta los destinatarios del documento.
	 */
	private List<DTODocumentoDestinatarioEntity>	destinatarios;
	/**
	 * Atributo que reprsenta los ccp del documento.
	 */
	private List<DTODocumentoCcpEntity>				ccp;
	/**
	 * Atributo que reprsenta los anexos del documento.
	 */
	private List<DTODocumentoAnexoEntity>			anexos;

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la
	 *         variable documento
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public DTODocumentoEntity getDocumento() {

		return documento;
	}

	/**
	 * @param documento
	 *            documento del DTODocumentoCapturadoHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setDocumento(DTODocumentoEntity documento) {

		this.documento = documento;
	}

	/**
	 * @return valor de tipo List<DTODocumentoDestinatarioEntity> que esta
	 *         contenido en la variable remitentes
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTODocumentosRemitentesEntity> getRemitentes() {

		return remitentes;
	}

	/**
	 * @param remitentes
	 *            remitentes del DTODocumentoCapturadoHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setRemitentes(List<DTODocumentosRemitentesEntity> remitentes) {

		this.remitentes = remitentes;
	}

	/**
	 * @return valor de tipo List<DTODocumentoDestinatarioEntity> que esta
	 *         contenido en la variable destinatarios
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTODocumentoDestinatarioEntity> getDestinatarios() {

		return destinatarios;
	}

	/**
	 * @param destinatarios
	 *            destinatarios del DTODocumentoCapturadoHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setDestinatarios(List<DTODocumentoDestinatarioEntity> destinatarios) {

		this.destinatarios = destinatarios;
	}

	/**
	 * @return valor de tipo List<DTODocumentoCcpEntity> que esta contenido en
	 *         la variable ccp
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTODocumentoCcpEntity> getCcp() {

		return ccp;
	}

	/**
	 * @param ccp
	 *            ccp del DTODocumentoCapturadoHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setCcp(List<DTODocumentoCcpEntity> ccp) {

		this.ccp = ccp;
	}

	/**
	 * @return valor de tipo List<DTODocumentoAnexoEntity> que esta contenido en
	 *         la variable anexos
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTODocumentoAnexoEntity> getAnexos() {

		return anexos;
	}

	/**
	 * @param anexos
	 *            anexos del DTODocumentoCapturadoHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setAnexos(List<DTODocumentoAnexoEntity> anexos) {

		this.anexos = anexos;
	}

}
