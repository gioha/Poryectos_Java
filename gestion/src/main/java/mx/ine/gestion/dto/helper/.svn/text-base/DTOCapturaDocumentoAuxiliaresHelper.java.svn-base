/**
 * @(#)DTOCapturaDocumentoAuxiliaresHelper.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;


/**
 * Clase Helper para reprentar los atributos auxiliares de pantalla de la
 * pantalla de captura documento. TODO
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
public class DTOCapturaDocumentoAuxiliaresHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long				serialVersionUID	= 4995502392041366756L;
	
	/**
	 * Lista que se carga con los resultados de la búsqueda de destinatarios que
	 * realizo el usuario en la pantalla de captura/ modificación
	 */
	private List<DTOEstructuraAreasEntity>	destinatarios;
	
	/**
	 * Atributo que almacena temporalmente los ccp posteriormente, al guardar se
	 * convertira en una lista tipo DTOCcpEntity.
	 */
	private List<DTOEstructuraAreasEntity>	ccp;
	/**
	 * Lista que se carga con los resultados de la búsqueda de remitentes que
	 * realizo el usuario en la pantalla de captura/ modificación
	 */
	private List<DTOEstructuraAreasEntity>	remitentes;
	/**
	 * Atributo que corresponde a la lista de personas que fueron seleccionadas
	 * para fungir como destinatarios en la captura de documento
	 */
	private List<DTOEstructuraAreasEntity>	personasSeleccionadas;
	
	/**
	 * Atributo que corresponde a la lista de personas seleccionadas ccp.
	 */
	private List<DTOEstructuraAreasEntity>	personasSeleccionadasccp;
	/**
	 * Atributo que corresponde a la lista de personas que fueron seleccionadas
	 * para fungir como destinatarios en la captura de documento
	 */
	private List<DTOEstructuraAreasEntity>	personasSeleccionadasRemitente;
	/**
	 * Atributo que distingue si se capturara o adjuntara el documento.
	 */
	private Integer							tipoIngreso;
	/**
	 * Atributo que corresponde a la plantilla selecta por el usuario.
	 */
	private DTOPlantillaEntity				plantilla;
	/**
	 * Atributo que corresponde con el acrónimo selecto por el usuario
	 */
	private DTOAcronimoEntity				acronimo;
	/**
	 * Atributo que corresponde con el Tipo de documento selecto por el usuario
	 */
	private DTOCTipoDocumentoEntity				tipoDoc;
	/**
	 * Atributo Privado que distinguira si se agrega un destinatario o un ccp
	 * true = se agregara a destinatario, false = se agregara a ccp o remitente.
	 */
	private Boolean							esDestinatario;
	/**
	 * Atributo Privado que distinguira si se agrega un remitente o no true = se
	 * agregara a Remitente.
	 */
	private Boolean							esRemitente;
	/**
	 * Atributo que almacena temporalmente la persona que se mostrara en
	 * detalle.
	 * 
	 */
	private DTOEstructuraAreasEntity		detalle;
	/**
	 * Atributo que corresponde al asunto del documento.
	 */
	private String							asunto;

	/******************************************************* SETERS Y GETERS *********************************************************************/

	/**
	 * Sobeescritura de constructor para inicializar atributos necesarios para
	 * el negocio.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 24/11/2017
	 */
	public DTOCapturaDocumentoAuxiliaresHelper() {

		destinatarios = new ArrayList<DTOEstructuraAreasEntity>();
		ccp	= new ArrayList<DTOEstructuraAreasEntity>();
		remitentes = new ArrayList<DTOEstructuraAreasEntity>();
		personasSeleccionadas = new ArrayList<DTOEstructuraAreasEntity>();
		personasSeleccionadasccp = new ArrayList<DTOEstructuraAreasEntity>();
		personasSeleccionadasRemitente = new ArrayList<DTOEstructuraAreasEntity>();
		detalle	= new DTOEstructuraAreasEntity();
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable destinatarios
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getDestinatarios() {

		return destinatarios;
	}

	/**
	 * @param destinatarios
	 *            destinatarios del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setDestinatarios(List<DTOEstructuraAreasEntity> destinatarios) {

		this.destinatarios = destinatarios;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable ccp
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getCcp() {

		return ccp;
	}

	/**
	 * @param ccp
	 *            ccp del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setCcp(List<DTOEstructuraAreasEntity> ccp) {

		this.ccp = ccp;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable remitentes
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getRemitentes() {

		return remitentes;
	}

	/**
	 * @param remitentes
	 *            remitentes del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setRemitentes(List<DTOEstructuraAreasEntity> remitentes) {

		this.remitentes = remitentes;
	}
	
	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable personasSeleccionadas
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getPersonasSeleccionadas() {

		return personasSeleccionadas;
	}

	/**
	 * @param personasSeleccionadas
	 *            personasSeleccionadas del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setPersonasSeleccionadas(List<DTOEstructuraAreasEntity> personasSeleccionadas) {

		this.personasSeleccionadas = personasSeleccionadas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable personasSeleccionadasccp
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getPersonasSeleccionadasccp() {

		return personasSeleccionadasccp;
	}

	/**
	 * @param personasSeleccionadasccp
	 *            personasSeleccionadasccp del
	 *            DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setPersonasSeleccionadasccp(List<DTOEstructuraAreasEntity> personasSeleccionadasccp) {

		this.personasSeleccionadasccp = personasSeleccionadasccp;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable personasSeleccionadasRemitente
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getPersonasSeleccionadasRemitente() {

		return personasSeleccionadasRemitente;
	}

	/**
	 * @param personasSeleccionadasRemitente
	 *            personasSeleccionadasRemitente del
	 *            DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setPersonasSeleccionadasRemitente(List<DTOEstructuraAreasEntity> personasSeleccionadasRemitente) {

		this.personasSeleccionadasRemitente = personasSeleccionadasRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         tipoIngreso
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public Integer getTipoIngreso() {

		return tipoIngreso;
	}

	/**
	 * @param tipoIngreso
	 *            tipoIngreso del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setTipoIngreso(Integer tipoIngreso) {

		this.tipoIngreso = tipoIngreso;
	}

	/**
	 * @return valor de tipo DTOPlantillaEntity que esta contenido en la
	 *         variable plantilla
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public DTOPlantillaEntity getPlantilla() {

		return plantilla;
	}

	/**
	 * @param plantilla
	 *            plantilla del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setPlantilla(DTOPlantillaEntity plantilla) {

		this.plantilla = plantilla;
	}

	/**
	 * @return valor de tipo DTOAcronimoEntity que esta contenido en la variable
	 *         acronimo
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public DTOAcronimoEntity getAcronimo() {

		return acronimo;
	}

	/**
	 * @param acronimo
	 *            acronimo del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setAcronimo(DTOAcronimoEntity acronimo) {

		this.acronimo = acronimo;
	}

	/**
	 * @return valor de tipo DTOCTiposDocumentos que esta contenido en la
	 *         variable tipoDoc
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public DTOCTipoDocumentoEntity getTipoDoc() {

		return tipoDoc;
	}

	/**
	 * @param tipoDoc
	 *            tipoDoc del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setTipoDoc(DTOCTipoDocumentoEntity tipoDoc) {

		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable
	 *         esDestinatario
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public Boolean getEsDestinatario() {

		return esDestinatario;
	}

	/**
	 * @param esDestinatario
	 *            esDestinatario del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setEsDestinatario(Boolean esDestinatario) {

		this.esDestinatario = esDestinatario;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable detalle
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public DTOEstructuraAreasEntity getDetalle() {

		return detalle;
	}

	/**
	 * @param detalle
	 *            detalle del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setDetalle(DTOEstructuraAreasEntity detalle) {

		this.detalle = detalle;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable asunto
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public String getAsunto() {

		return asunto;
	}

	/**
	 * @param asunto
	 *            asunto del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setAsunto(String asunto) {

		this.asunto = asunto;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable
	 *         esRemitente
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public Boolean getEsRemitente() {

		return esRemitente;
	}

	/**
	 * @param esRemitente
	 *            esRemitente del DTOCapturaDocumentoAuxiliaresHelper
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/11/2017
	 */
	public void setEsRemitente(Boolean esRemitente) {

		this.esRemitente = esRemitente;
	}
	
}
