package mx.ine.gestion.mb;

/**
 * @(#)MBCapturaDocumento.java 25/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

/**
 * Clase de la capa de MB para el módulo captura de documento.
 * 
 * 
 * @author Sergio Ley Garcia
 * @since 09/10/2017
 */
@ManagedBean(name = "mbModificacionDoc")
public class MBModificacionDocumento implements Serializable {

//	/**************************************************************** Components ****************************************************************************/
//	@Autowired
//	@Qualifier("bsdCapturarDocumentos")
//	private transient BSDCapturarDocumentoInterface	bsdCapturaDoc;
//	@Autowired
//	@Qualifier("boArchivo")
//	private transient BOArchivoInteface				boArchivo;
//	@Autowired
//	@Qualifier("vhCapturaDocumento")
//	private transient VHCapturaDocumentoInterface	vh;
//	/**************************************************************** ATRIBUTOS ****************************************************************************/
//	private static final long						serialVersionUID	= -4724673890129812391L;
//
//	/**
//	 * Atributo que contiene los datos que van a DB, acumulara el documento,
//	 * destinatarios, ccp y anexos.
//	 */
//	private DTODocumentoCapturadoHelper				documentoCapturado;
//	/**
//	 * Atributo que contiene los catalogos de la pantalla.
//	 */
//	private DTOCapturaDocumentoCatalogosHelper		catalogos;
//	/**
//	 * Atributo que contiene los atributos de la pantalla.
//	 */
//	private DTOCapturaDocumentoHelper				pantalla;
//	/**
//	 * Atributo que contiene los atributos auxiliares.
//	 */
//	private DTOCapturaDocumentoAuxiliaresHelper		auxiliares;
//	/**
//	 * Atributo que guarda los datos del documento (Archivo subido al gluster)
//	 */
//	private DTOArchivoHelper						archivoDocumento;
//	/**
//	 * Atributo que guarda los datos de los Anexos (Archivos subidos al gluster)
//	 */
//	private List<DTOArchivoHelper>					archivosAnexos;
//	/**
//	 * Atributo que se ocupa para guardar el objeto documento recibido
//	 */
//	private Integer									idDocumento;
//
//	public Integer getIdDocumento() {
//
//		return idDocumento;
//	}
//
//	public void setIdDocumento(Integer idDocumento) {
//
//		this.idDocumento = idDocumento;
//	}
//
//	/******************************************************************* METODOS ****************************************************************************/
//	/**
//	 * Método de carga inicial de la pantalla
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 05/09/2017
//	 */
//	public void init() {
//
//		iniciaCampos();
//		catalogos = new DTOCapturaDocumentoCatalogosHelper();
//		catalogos.setTiposDocumentos(bsdCapturaDoc.buscarTiposDoc());
//		List<DTOCAreaEntity> areas = bsdCapturaDoc.buscarAreas(1, 0);
//		catalogos.setAreas(areas);
//		catalogos.setAreasCCP(areas);
//		catalogos.setAreasRemitente(areas);
//		catalogos.setEntidades(bsdCapturaDoc.buscarEntidades());
//		this.cargaDocumento(idDocumento);
//	}
//
//	private void iniciaCampos() {
//		bsdCapturaDoc.borrarTemporales();
//		pantalla = new DTOCapturaDocumentoHelper();
//		auxiliares = new DTOCapturaDocumentoAuxiliaresHelper();
//		pantalla.setAcronimoSelecto(Boolean.TRUE);
//		pantalla.setEsAdjunto(Boolean.TRUE);
//		pantalla.setEsPlantilla(Boolean.TRUE);
//		pantalla.setEsBlanco(Boolean.TRUE);
//		pantalla.setAreaVisible(Boolean.TRUE);
//		archivosAnexos = new ArrayList<DTOArchivoHelper>();
//		archivoDocumento = null;
//		pantalla.setTipoAreaSeleccionado(1);
//		pantalla.setTipoAreaccp(1);
//	}
//
//	/**
//	 * Método de liberacion final de la pantalla
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 03/10/2017
//	 */
//	public void borrarTemporales() {
//
//		bsdCapturaDoc.borrarTemporales(archivosAnexos, archivoDocumento);
//	}
//
//	/**
//	 * Método privado para buscar personas
//	 * 
//	 * @param nombre
//	 *            nombre de la persona a buscar.
//	 * @param apellido
//	 *            apellido de la persona a buscar.
//	 * @param area
//	 *            id del area de la persona a buscar.
//	 * @return Lista de personas que coinciden con los parametros de busqueda.
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 08/09/2017
//	 */
//	private void buscarPersonas(String nombre, String apellido, Integer area, Integer tipoArea) {
//
//		String error = vh.validaBusqueda(nombre, apellido, area, null);
//		if(!error.isEmpty()){
//			vh.mostrarMensajeError(null, error, "");
//		} else{
//			catalogos.setPersonas(bsdCapturaDoc.buscarPersonas(nombre, apellido, area, tipoArea));
//			if(catalogos.getPersonas() == null || catalogos.getPersonas().isEmpty()){
//				vh.mostrarMensajeInfo(null, "INFORMATIVO", "No se encontraron resultados");
//				return;
//			}
//			if(auxiliares.getEsRemitente())
//				RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaRemitente').show()");
//			else if(auxiliares.getEsDestinatario())
//				RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersona').show()");
//			else
//				RequestContext.getCurrentInstance().execute("PF('dlgBuscarPersonaCCP').show()");
//
//		}
//	}
//
//	/**
//	 * Método que se manda llamar cuando se utiliza el componente de fileupload
//	 * de primefaces
//	 * 
//	 * @param archivo
//	 *            objeto que manda primefaces con la información del componente
//	 * @param tipo
//	 *            1 BOarchivo.DOCUMENTO o 2 BOarchivo.DOCUMENTOANEXO
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 14/09/2017
//	 */
//	private void adjuntarArchivo(FileUploadEvent archivo, Integer tipo) {
//
//		DTOArchivoHelper arch;
//		try{
//			arch = bsdCapturaDoc.guardarTemporal(archivo, tipo, archivosAnexos);
//		} catch(Exception e){
//			vh.mostrarMensajeError(null, e.getMessage(), "");
//			return;
//		}
//		if(tipo.intValue() == BOArchivoInteface.DOCUMENTOANEXO){
//			archivosAnexos.add(arch);
//		} else{
//			// si existe hay que borrarlo
//			if(archivoDocumento != null)
//				bsdCapturaDoc.borrarTemporal(archivoDocumento);
//			archivoDocumento = arch;
//		}
//	}
//
//	/**
//	 * Método que carga un documento previo
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 05/10/2017
//	 */
//	public void cargaDocumento(Integer idDoc) {
//
//		if(idDoc != null){
//			DTODocumentoEntity docu = new DTODocumentoEntity();
//			docu.setIdDocumento(idDoc);
//			documentoCapturado = bsdCapturaDoc.buscarDocumento(docu);
//			archivosAnexos = bsdCapturaDoc.cargarArchivosDocumentosAnexos(documentoCapturado);
//			vh.cargaDocumento(auxiliares, documentoCapturado, pantalla);
//			tipoDocEvento();
//			acronimoEvento();
//			metodoCapturaEvento();
//			if(auxiliares.getTipoIngreso().equals(2)){
//				vh.plantillaEvento(catalogos, pantalla, auxiliares);
//				// necesitamos cambiar a que sea el documento guardado -.-
//				archivoDocumento = bsdCapturaDoc.crearArchivoDocumento(documentoCapturado);
//				pantalla.setHiperLink(bsdCapturaDoc.obtenerHiperLinkDocumento(archivoDocumento));
//			} else if(auxiliares.getTipoIngreso().equals(1)){
//				// necesitamos cambiar a que sea el documento guardado -.-
//				archivoDocumento = bsdCapturaDoc.crearArchivoDocumento(documentoCapturado);
//				pantalla.setHiperLink(bsdCapturaDoc.obtenerHiperLinkDocumento(archivoDocumento));
//			} else if(auxiliares.getTipoIngreso().equals(3)){
//				archivoDocumento = bsdCapturaDoc.crearArchivoDocumento(documentoCapturado);
//			}
//		}
//	}
//
//	/******************************************************************* eventoS ****************************************************************************/
//	/**
//	 * Método de evento que se llama al elegir un acronimo
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 06/09/2017
//	 */
//	public void acronimoEvento() {
//
//		vh.acronimoEvento(catalogos, pantalla, auxiliares);
//		catalogos.setPlantillas(bsdCapturaDoc.buscarplantillas(auxiliares.getAcronimo()));
//	}
//
//	public void tipoDocEvento() {
//
//		vh.tipoDocEvento(catalogos, pantalla, auxiliares);
//		catalogos.setAcronimos(bsdCapturaDoc.buscarAcronimos(auxiliares.getTipoDoc()));
//	}
//
//	/**
//	 * Método de evento que se llama al elegir una plantilla
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 15/09/2017
//	 */
//	public void plantillaEvento() {
//
//		vh.plantillaEvento(catalogos, pantalla, auxiliares);
//		pantalla.setHiperLink(bsdCapturaDoc.obtenerHiperLinkPlantilla(auxiliares.getPlantilla()));
//		archivoDocumento = bsdCapturaDoc.crearArchivoPlantilla(auxiliares);
//	}
//
//	public void blancoEvento() {
//
//		pantalla.setHiperLink(bsdCapturaDoc.obtenerHiperLinkPlantilla(null));
//		archivoDocumento = bsdCapturaDoc.crearArchivoPlantilla(auxiliares);
//	}
//
//	/**
//	 * Método de evento que se llama al elegir un metodo de captura
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 15/09/2017
//	 */
//	public void metodoCapturaEvento() {
//
//		if(archivoDocumento != null){
//			archivoDocumento = null;
//			auxiliares.setPlantilla(null);
//		}
//		if(auxiliares.getTipoIngreso().intValue() == 1){
//			pantalla.setEsBlanco(Boolean.FALSE);
//			blancoEvento();
//		} else
//			pantalla.setEsBlanco(Boolean.TRUE);
//		pantalla.setEsPlantilla(auxiliares.getTipoIngreso().intValue() != 2);
//		pantalla.setEsAdjunto(auxiliares.getTipoIngreso().intValue() != 3);
//	}
//
//	/**
//	 * Método de evento que se dispara al seleccionar un tipo de area o una
//	 * entidad.
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 29/11/2017
//	 */
//	public void tipoAreaRemitEvento() {
//
//		if(pantalla.getTipoAreaSeleccionado() == 1){
//			catalogos.setAreas(bsdCapturaDoc.buscarAreas(pantalla.getTipoAreaSeleccionado(), 0));
//			pantalla.setEntidad(0);
//		} else
//			// es entidad
//			catalogos.setAreas(bsdCapturaDoc.buscarAreas(pantalla.getTipoAreaSeleccionado(), pantalla.getEntidad()));
//	}
//
//	/**
//	 * Método de evento ue se dispara al seleccionar un tipo de area o una
//	 * entidad.
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 26/10/2017
//	 */
//	public void tipoAreaDestEvento() {
//
//		if(pantalla.getTipoAreaSeleccionado() == 1){
//			catalogos.setAreas(bsdCapturaDoc.buscarAreas(pantalla.getTipoAreaSeleccionado(), 0));
//			pantalla.setEntidad(0);
//			;
//		}
//		else
//			// es entidad
//			catalogos.setAreas(bsdCapturaDoc.buscarAreas(pantalla.getTipoAreaSeleccionado(), pantalla.getEntidad()));
//	}
//
//	/**
//	 * 
//	 * 
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 26/10/2017
//	 */
//	public void tipoAreaCcpEvento() {
//
//		if(pantalla.getTipoAreaccp() == 1){
//			catalogos.setAreasCCP(bsdCapturaDoc.buscarAreas(pantalla.getTipoAreaccp(), 0));
//			pantalla.setEntidadccp(0);
//		}
//		else
//			// es entidad
//			catalogos.setAreasCCP(bsdCapturaDoc.buscarAreas(pantalla.getTipoAreaccp(), pantalla.getEntidadccp()));
//	}
//
//	/**
//	 * Método de evento que se llama para buscar las personas para elegir un
//	 * destinatario
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 29/11/2017
//	 */
//	public void buscarRemitenteEvento() {
//
//		auxiliares.setEsRemitente(Boolean.TRUE);
//		auxiliares.setEsDestinatario(Boolean.FALSE);
//		pantalla.setAreaVisible(pantalla.getAreaRemitente() == null || pantalla.getAreaRemitente() == 0);
//		buscarPersonas(pantalla.getNombreDestinatario(), pantalla.getApellidoRemitente(), pantalla.getAreaRemitente(),
//				pantalla.getTipoAreaRemitente());
//	}
//
//	/**
//	 * Método de evento que limpia los campos de busqueda del remitente
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 29/11/2017
//	 */
//	public void limpiarRemitenteEvento() {
//
//		pantalla.setNombreRemitente("");
//		pantalla.setApellidoRemitente("");
//		pantalla.setAreaRemitente(null);
//	}
//
//	/**
//	 * Método de evento que se llama para buscar las personas para elegir un
//	 * destinatario
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 08/09/2017
//	 */
//	public void buscarDestinatarioEvento() {
//
//		auxiliares.setEsRemitente(Boolean.FALSE);
//		auxiliares.setEsDestinatario(Boolean.TRUE);
//		pantalla.setAreaVisible(pantalla.getAreaDestinatario() == null || pantalla.getAreaDestinatario() == 0);
//		buscarPersonas(pantalla.getNombreDestinatario(), pantalla.getApellidoDestinatario(), pantalla.getAreaDestinatario(),
//				pantalla.getTipoAreaSeleccionado());
//	}
//
//	/**
//	 * Método de evento que limpia los campos de busqueda del destinatario
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 12/09/2017
//	 */
//	public void limpiarDestinatariosEvento() {
//
//		pantalla.setNombreDestinatario("");
//		pantalla.setApellidoDestinatario("");
//		pantalla.setAreaDestinatario(null);
//	}
//
//	/**
//	 * Método de evento que se llama para buscar las personas para elegir un ccp
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 08/09/2017
//	 */
//	public void ccpEvento() {
//
//		auxiliares.setEsRemitente(Boolean.FALSE);
//		auxiliares.setEsDestinatario(Boolean.FALSE);
//		pantalla.setAreaVisible(pantalla.getAreaCcp() == null || pantalla.getAreaCcp() == 0);
//		buscarPersonas(pantalla.getNombreCcp(), pantalla.getApellidoCcp(), pantalla.getAreaCcp(), pantalla.getTipoAreaccp());
//	}
//
//	/**
//	 * Método de evento que limpia los campos de busqueda del ccp
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 12/09/2017
//	 */
//	public void limpiarCcpEvento() {
//
//		pantalla.setNombreCcp("");
//		pantalla.setApellidoCcp("");
//		pantalla.setAreaCcp(null);
//	}
//
//	/**
//	 * 
//	 * Método de evento que se llama para al elegir una persona de la lista
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 08/09/2017
//	 */
//	public void agregarPersona() {
//
//		String error = vh.validaPersonas(auxiliares);
//		if(!error.isEmpty()){
//			vh.mostrarMensajeError("tabla_personas", error, "");
//			return;
//		}
//		error = bsdCapturaDoc.agregarPersona(auxiliares);
//		if(!error.isEmpty()){
//			vh.mostrarMensajeInfo(null, error, "");
//			return;
//		}
//		if(auxiliares.getEsDestinatario())
//			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersona').hide()");
//		else
//			RequestContext.getCurrentInstance().execute("PF('dlgBuscarPersonaCCP').hide()");
//	}
//
//	/**
//	 * Método de evento que se llama para eliminar una persona de la lista de
//	 * remitentes
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 29/11/2017
//	 */
//	public void eliminarRemitente(Integer idRemitente) {
//
//		bsdCapturaDoc.eliminarRemitente(idRemitente, auxiliares);
//	}
//
//	/**
//	 * 
//	 * Método de evento que se llama para eliminar una persona de la lista de
//	 * destinatarios
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 11/09/2017
//	 */
//	public void eliminarDestinatario(Integer idDestinatario) {
//
//		bsdCapturaDoc.eliminarDestinatario(idDestinatario, auxiliares);
//	}
//
//	/**
//	 * Método de evento que asigna el remitente de la lista de remitentes que se
//	 * va a ver en detalle.
//	 * 
//	 * @param idRemitente
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 29/11/2017
//	 */
//	public void detalleRemitente(Integer idRemitente) {
//
//		bsdCapturaDoc.detalleRemitente(idRemitente, auxiliares);
//	}
//
//	/**
//	 * Método de evento que asigna el destinatario de la lista de destinatarios
//	 * que se va a ver en detalle.
//	 * 
//	 * @param idDestinatario
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 26/10/2017
//	 */
//	public void detalleDestinatario(Integer idDestinatario) {
//
//		bsdCapturaDoc.detalleDestinatario(idDestinatario, auxiliares);
//	}
//
//	/**
//	 * 
//	 * Método de evento que se llama para eliminar una persona de la lista de
//	 * ccp
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 11/09/2017
//	 */
//	public void eliminarCcp(Integer idCcp) {
//
//		bsdCapturaDoc.eliminarCcp(idCcp, auxiliares);
//	}
//
//	/**
//	 * Método de evento que asigna el ccp de la lista de ccps que se va a ver en
//	 * detalle.
//	 * 
//	 * @param idCcp
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 26/10/2017
//	 */
//	public void detalleCcp(Integer idCcp) {
//
//		bsdCapturaDoc.detalleCcp(idCcp, auxiliares);
//	}
//
//	/**
//	 * Método que se manda llamar cuando se utiliza el componente de fileupload
//	 * de primefaces para un documento anexo
//	 * 
//	 * @param archivo
//	 *            : objeto que manda primefaces con la información del
//	 *            componente
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 14/09/2017
//	 */
//	public void adjuntarArchivoAnexo(FileUploadEvent archivo) {
//
//		adjuntarArchivo(archivo, BOArchivoInteface.DOCUMENTOANEXO);
//	}
//
//	/**
//	 * Método que se manda llamar cuando se utiliza el componente de fileupload
//	 * de primefaces para un documento
//	 * 
//	 * @param archivo
//	 *            : objeto que manda primefaces con la información del
//	 *            componente
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 14/09/2017
//	 */
//	public void adjuntarArchivoDocumento(FileUploadEvent archivo) {
//
//		adjuntarArchivo(archivo, BOArchivoInteface.DOCUMENTO);
//	}
//
//	/**
//	 * 
//	 * Método de evento que se llama para eliminar un anexo de la lista de
//	 * archivos anexos
//	 *
//	 * @author Sergio Ley Garcia
//	 * @since 28/09/2017
//	 */
//	public void eliminarArchivoAnexo(String nombreAnexo) {
//
//		bsdCapturaDoc.eliminarArchivoAnexo(nombreAnexo, archivosAnexos);
//	}
//
//	/**
//	 * Método que se manda llamar cuando se finaliza la captura del documento,
//	 * este guardara el documento capturado completo.
//	 * 
//	 * @author Sergio Ley Garcia
//	 * @throws Exception
//	 * @since 15/09/2017
//	 */
//	public void finEvento() throws Exception {
//		
//		//1.- Validamos que se haya capturado todo lo necesario para crear el documento
//		String error = this.vh.validarCaputuraDocumento(this.auxiliares, this.archivoDocumento, this.pantalla);
//		if(!error.isEmpty()) {
//			vh.mostrarMensajeError(null, error, "");
//			throw new Exception("Error controlado");
//		}
//
//		// 1.- Preparamos el documentoCapturado(todos los datos a guardar)
//		DTODocumentoCapturadoHelper documentoModificado = bsdCapturaDoc.preprararDocumento(archivoDocumento, archivosAnexos, auxiliares);
//
//		// 2.- Llamamos a guardar.
//		try{
//			bsdCapturaDoc.modificar(documentoCapturado, documentoModificado);
//		} catch(Exception e){
//			bsdCapturaDoc.renombrarArchivos(documentoModificado, archivoDocumento, archivosAnexos);
//			vh.mostrarMensajeGrowl("advertencia", e.getMessage(), "");
//			throw new Exception(e);
//		}
//
//	}
//
//	/**************************************************************** GETTERS Y SETTERS ****************************************************************************/
//	/**
//	 * @return pantalla
//	 */
//	public DTOCapturaDocumentoHelper getPantalla() {
//
//		return pantalla;
//	}
//
//	/**
//	 * @param pantalla
//	 *            pantalla del MBCapturaDocumento
//	 */
//	public void setPantalla(DTOCapturaDocumentoHelper mbAuxiliar) {
//
//		this.pantalla = mbAuxiliar;
//	}
//
//	/**
//	 * @return catalogos
//	 */
//	public DTOCapturaDocumentoCatalogosHelper getCatalogos() {
//
//		return catalogos;
//	}
//
//	/**
//	 * @param catalogos
//	 *            catalogos del MBCapturaDocumento
//	 */
//	public void setCatalogos(DTOCapturaDocumentoCatalogosHelper catalogos) {
//
//		this.catalogos = catalogos;
//	}
//
//	/**
//	 * @return auxiliares
//	 */
//	public DTOCapturaDocumentoAuxiliaresHelper getAuxiliares() {
//
//		return auxiliares;
//	}
//
//	/**
//	 * @param auxiliares
//	 *            auxiliares del MBCapturaDocumento
//	 */
//	public void setAuxiliares(DTOCapturaDocumentoAuxiliaresHelper auxiliares) {
//
//		this.auxiliares = auxiliares;
//	}
//
//	/**
//	 * @return archivoDocumento
//	 */
//	public DTOArchivoHelper getArchivoDocumento() {
//
//		return archivoDocumento;
//	}
//
//	/**
//	 * @param archivoDocumento
//	 *            archivoDocumento del MBCapturaDocumento
//	 */
//	public void setArchivoDocumento(DTOArchivoHelper archivoDocumento) {
//
//		this.archivoDocumento = archivoDocumento;
//	}
//
//	/**
//	 * @return archivosAnexos
//	 */
//	public List<DTOArchivoHelper> getArchivosAnexos() {
//
//		return archivosAnexos;
//	}
//
//	/**
//	 * @param archivosAnexos
//	 *            archivosAnexos del MBCapturaDocumento
//	 */
//	public void setArchivosAnexos(List<DTOArchivoHelper> archivosAnexos) {
//
//		this.archivosAnexos = archivosAnexos;
//	}
}
