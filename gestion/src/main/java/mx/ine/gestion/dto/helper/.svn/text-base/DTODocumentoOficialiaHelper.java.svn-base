/**
 * @(#)DTODocumentoOficialiaHelper.java 12/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoCcpEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;

/**
 * Clase DTO Helper para el módulo de clasificación.
 * 
 * @author Sergio Ley Garcia
 * @since 12/11/2017
 * 
 * @update David Rodríguez Corral
 * @since 09/12/2017
 * 
 */
public class DTODocumentoOficialiaHelper implements Serializable {

	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = 1747789825944293336L;
	
	/**
	 * Lista que contiene los destinatarios de un documento
	 */
	private List<DTODocumentoDestinatarioEntity> listaDTODestinatarios;
	
	/**
	 * Lista que contiene los remitentes de un documento
	 */
	private List<DTODocumentosRemitentesEntity>	listaDTORemitentes;
	
	/**
	 * Lista que contiene los remitentes de un documento
	 */
	private List<DTODocumentoCcpEntity>	listaDTOCcp;

	/**
	 * Lista que contiene los remitentes de un documento
	 */
	private List<DTODocumentoAnexoEntity> listaDTOAnexos;
	
	/**
	 * String que guarda el folio apartado
	 */
	private String folio;
	
	/**
	 * String que guarda el tipo de documento
	 */
	private String tipoDocumento;

	/**
	 * String con los remitentes separados por coma
	 */
	private String remitentes;
	
	/**
	 * String con los destinatarios separados por coma
	 */
	private String destinatarios;
	
	/**
	 * String con las copias de conocimiento separados por coma
	 */
	private String ccps;

	/**
	 * Lista con el historial del documentos
	 */
	private List<DTOHistDocCreacionEntity> historial;
	
	/**
	 * Comentarios del documento
	 */
	private List<DTOComentariosDocumentoEntity> comentarios;
	
	/**
	 * Comentario actual que se esté viendo del documento
	 */
	private DTOComentariosDocumentoEntity comentario;
	
	/**
	 * Tipo de informacion que se está viendo en el detalle del documento
	 */
	private String tipoInfoDetalle;
	
	/**
	 * Título para la informacion que se está viendo en el detalle del documento
	 */
	private String tituloInfoDetalle;
	
	/**
	 * @return valor de tipo List<DTODocumentoDestinatarioEntity> que esta contenido en la variable listaDTODestinatarios
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public List<DTODocumentoDestinatarioEntity> getListaDTODestinatarios() {
		return listaDTODestinatarios;
	}

	/**
	 * @param listaDTODestinatarios : valor que se ingresa a la variable de tipo List<DTODocumentoDestinatarioEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setListaDTODestinatarios(
			List<DTODocumentoDestinatarioEntity> listaDTODestinatarios) {
		this.listaDTODestinatarios = listaDTODestinatarios;
	}

	/**
	 * @return valor de tipo List<DTODocumentosRemitentesEntity> que esta contenido en la variable listaDTORemitentes
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public List<DTODocumentosRemitentesEntity> getListaDTORemitentes() {
		return listaDTORemitentes;
	}

	/**
	 * @param listaDTORemitentes : valor que se ingresa a la variable de tipo List<DTODocumentosRemitentesEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setListaDTORemitentes(
			List<DTODocumentosRemitentesEntity> listaDTORemitentes) {
		this.listaDTORemitentes = listaDTORemitentes;
	}

	/**
	 * @return valor de tipo List<DTODocumentoCcpEntity> que esta contenido en la variable listaDTOCcp
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public List<DTODocumentoCcpEntity> getListaDTOCcp() {
		return listaDTOCcp;
	}

	/**
	 * @param listaDTOCcp : valor que se ingresa a la variable de tipo List<DTODocumentoCcpEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setListaDTOCcp(List<DTODocumentoCcpEntity> listaDTOCcp) {
		this.listaDTOCcp = listaDTOCcp;
	}

	/**
	 * @return valor de tipo List<DTODocumentoAnexoEntity> que esta contenido en la variable listaDTOAnexos
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public List<DTODocumentoAnexoEntity> getListaDTOAnexos() {
		return listaDTOAnexos;
	}

	/**
	 * @param listaDTOAnexos : valor que se ingresa a la variable de tipo List<DTODocumentoAnexoEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setListaDTOAnexos(List<DTODocumentoAnexoEntity> listaDTOAnexos) {
		this.listaDTOAnexos = listaDTOAnexos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable folio
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return variable de tipo String contenida en remitentes
	 * 
	 * @since 02/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getRemitentes() {
		return remitentes;
	}

	/**
	 * @param remitentes: variable de tipo String contenida en remitentes
	 *
	 * @since 02/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setRemitentes(String remitentes) {
		this.remitentes = remitentes;
	}

	/**
	 * @return variable de tipo String contenida en destinatarios
	 * 
	 * @since 02/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios: variable de tipo String contenida en destinatarios
	 *
	 * @since 02/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return variable de tipo String contenida en ccps
	 * 
	 * @since 02/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getCcps() {
		return ccps;
	}

	/**
	 * @param ccps: variable de tipo String contenida en ccps
	 *
	 * @since 02/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setCcps(String ccps) {
		this.ccps = ccps;
	}

	/**
	 * @return variable de tipo List<DTOHistDocCreacionEntity> contenida en historial
	 * 
	 * @since 04/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOHistDocCreacionEntity> getHistorial() {
		return historial;
	}

	/**
	 * @param historial: variable de tipo List<DTOHistDocCreacionEntity> contenida en historial
	 *
	 * @since 04/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setHistorial(List<DTOHistDocCreacionEntity> historial) {
		this.historial = historial;
	}

	/**
	 * @return variable de tipo List<DTOComentariosDocumentoEntity> contenida en comentarios
	 * 
	 * @since 04/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOComentariosDocumentoEntity> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios: variable de tipo List<DTOComentariosDocumentoEntity> contenida en comentarios
	 *
	 * @since 04/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setComentarios(List<DTOComentariosDocumentoEntity> comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return variable de tipo DTOComentariosDocumentoEntity contenida en comentario
	 * 
	 * @since 04/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOComentariosDocumentoEntity getComentario() {
		return comentario;
	}

	/**
	 * @param comentario: variable de tipo DTOComentariosDocumentoEntity contenida en comentario
	 *
	 * @since 04/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setComentario(DTOComentariosDocumentoEntity comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return variable de tipo String contenida en tipoInfoDetalle
	 * 
	 * @since 04/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getTipoInfoDetalle() {
		return tipoInfoDetalle;
	}

	/**
	 * @param tipoInfoDetalle: variable de tipo String contenida en tipoInfoDetalle
	 *
	 * @since 04/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setTipoInfoDetalle(String tipoInfoDetalle) {
		this.tipoInfoDetalle = tipoInfoDetalle;
	}

	/**
	 * @return variable de tipo String contenida en tituloInfoDetalle
	 * 
	 * @since 05/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getTituloInfoDetalle() {
		return tituloInfoDetalle;
	}

	/**
	 * @param tituloInfoDetalle: variable de tipo String contenida en tituloInfoDetalle
	 *
	 * @since 05/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setTituloInfoDetalle(String tituloInfoDetalle) {
		this.tituloInfoDetalle = tituloInfoDetalle;
	}

}
