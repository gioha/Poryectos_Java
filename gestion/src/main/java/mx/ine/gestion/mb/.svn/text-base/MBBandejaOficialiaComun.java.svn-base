/**
 * @(#)MBBandejaOficialiaComun.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;
import mx.org.ine.servicios.utils.PaginarLazy;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase (ManageBean) que se encarga de recibir y delegar las peticiones que lleguen 
 * de la pantalla de administracion para bandeja de oficialiía
 *
 * @author David Rodríguez Corral
 * @since 02/11/2017
 */
public class MBBandejaOficialiaComun implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.  
	 */
	private static final long serialVersionUID = -8185509575874961099L;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBBandejaOficialia.class);

	/**
	 * Indica el nodo raíz para el menú de muchas áreas
	 */
	private TreeNode root;
	
	/**
	 * Indica el nodo de la Bandeja de entrada
	 */
	private TreeNode entrada;
	
	/**
	 * Indica el nodo que fue seleccionados
	 */
	private TreeNode areaSeleccion;
	
	/**
	 * Objeto para guardar las áreas en las que está registrada la oficialía
	 */
	private DTOCAreaEntity bandejaEntrada;
	
	/**
	 * Lista que carga las areas de las oficialias
	 */
	private List<DTOCAreaEntity> dtoCAreas;
	
	/**
	 * Objeto en donde se guarda el documento seleccionado
	 */	
	private DTOBandejaEntradasOficialiaEntity dtBandejaSeleccionados;

	/**
	 * String que guarda la ruta del documento en pdf
	 */	
	private String rutaDocumento;
	
	/**
	 * Lista que guarda la lista de anexos de un documento
	 */	
	private List<DTODocumentoAnexoEntity> listaAnexos;
	
	/**
	 * Integer que guarda el id del área que es seleccionada en caso de que el usuario en sesión este 
	 * registrado en más de una oficialia
	 */	
	private Integer idAreaBusqueda;
	
	/**
	 * Integer que guarda el id del área que es seleccionada en caso de que el usuario en sesión este 
	 * registrado en más de una oficialia
	 */	
	private Integer tipoAreaBusqueda;
	
	/**
	 * Integer que guarda número de notificaciones
	 */	
	private Integer numeroNotificaciones;
	
	/**
	 * String que guarda la ruta de anexos en el gluster
	 */
	private String rutaGlusterAnexos;
	
	/**
	 * Objeto que contiene la lista de documentos para mostrar en la bandeja de entrada de oficialia tipo lazy
	 */
	private PaginarLazy<DTOBandejaEntradasOficialiaEntity, DTOBandejaOficialiaHelper> listaBandejaLazy;
	
	/**
	 * Objeto para usar de filtro para la lista lazy
	 */
	private DTOBandejaOficialiaHelper dtoBandejaFiltro;
	
	/**
	 * Indica si el documento fue clasificado=1 o cancelado=2 para saber que mensaje mostrar al regresar a esta pantalla
	 */
	private Integer mostrarMensaje;
	
	/**
	 * Lista que contiene los remitentes de un documento
	 */
	private String listaDTORemitentes;
	
	@Autowired
	@Qualifier("bsdBandejaOficialia")
	private transient BSDBandejaOficialiaInterface bsdBandejaOficialiaInterface;
	
	
	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------- INICIO - EVENTOS -------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */
	
	/**
	 * Método para inicializar los atributos que se necesiten en cuanto carga 
	 * la pantalla de firmas
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void iniciar() {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		log.info("<================== INICIANDO BANDEJA DE OFICIALIA ==================>");
		
		//OBTIENE LA RUTA DEL GLUSTER
		this.rutaGlusterAnexos = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaAnexos")+ File.separator;
		
		//SE CONSULTA LAS ÁREAS EN LAS QUE ESTÁ REGISTRADA LA OFICIALIA
		this.dtoCAreas = bsdBandejaOficialiaInterface.consultarAreasOficialias(usuarioLogueado.getIdOficialia());
		
		//SE INICIALIZA EL OBJETO DONDE SE GUARDA LA SELECCION
		this.dtBandejaSeleccionados = new DTOBandejaEntradasOficialiaEntity();
		
		//SE INICIALIZA LA RAIZ PARA MOSTRAR EL MENÚ CON LAS ÁREAS
		bandejaEntrada = new DTOCAreaEntity();
		bandejaEntrada.setSiglas("Bandeja de entrada");
		root = new DefaultTreeNode("Root", null);
		entrada = new DefaultTreeNode(bandejaEntrada, root);
		
		//SE CREAN LOS FILTROS PARA LA LISTA LAZY
		this.dtoBandejaFiltro = new DTOBandejaOficialiaHelper();
		this.dtoBandejaFiltro.setIdArea(dtoCAreas.get(0).getIdArea());
		this.dtoBandejaFiltro.setIdOficialia(usuarioLogueado.getIdOficialia());
		this.dtoBandejaFiltro.setCampoOrden("fechaRecepcion");
		
		//SE INICIALIZA LA LISTA LAZY
		this.listaBandejaLazy = 
				new PaginarLazy<DTOBandejaEntradasOficialiaEntity, DTOBandejaOficialiaHelper>("bsdBandejaOficialia",  this.dtoBandejaFiltro);
		
		//SE CONSTRUYE MENÚ DE ACUERDO A CUANTAS ÁREAS ESTÁ REGISTRADA LA OFICIALÍA
		construirMenu(true,false);
		mostrarMensaje();
		this.idAreaBusqueda = dtoCAreas.get(0).getIdArea();
		this.tipoAreaBusqueda = dtoCAreas.get(0).getTipoArea();
		this.dtBandejaSeleccionados = null;
		
		MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
		administrador.consultarNotificacionesOficialia();
		
	}
	
	/**
	 * Método que construye el menú
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void construirMenu(Boolean areaSeleccionada, Boolean recargarMenu){
		
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		bandejaEntrada = new DTOCAreaEntity();
		bandejaEntrada.setSiglas("Bandeja de entrada");
		root = new DefaultTreeNode("Root", null);
		entrada = new DefaultTreeNode(bandejaEntrada, root);
		this.dtoCAreas = bsdBandejaOficialiaInterface.consultarAreasOficialias(usuarioLogueado.getIdOficialia());
		
		if(this.dtoCAreas.size() == 1){
			consultarPendientes(this.dtoCAreas.get(0).getIdArea(), this.dtoCAreas.get(0).getTipoArea());
			this.bandejaEntrada.setSiglas(bandejaEntrada.getSiglas() + 
					" ( "+this.numeroNotificaciones + " )");
			
			entrada.setExpanded(true);
			entrada.setSelectable(false);
			entrada.setSelected(true);
			//this.idAreaBusqueda = dtoCAreas.get(0).getIdArea();
			//this.tipoAreaBusqueda = dtoCAreas.get(0).getTipoArea();
			//this.dtBandejaSeleccionados = null;

		}else{
			
			entrada.setExpanded(true);
			entrada.setSelectable(false);
			TreeNode area = null;
			
			
			for(DTOCAreaEntity dtoBandejaOficialia : dtoCAreas){
				consultarPendientes(dtoBandejaOficialia.getIdArea(), dtoBandejaOficialia.getTipoArea());
				
				dtoBandejaOficialia.setSiglas(dtoBandejaOficialia.getSiglas() + 
						" ( "+ this.numeroNotificaciones + " )");
				
				area = new DefaultTreeNode(dtoBandejaOficialia, entrada);
					
				if(areaSeleccionada.equals(true)){
					area.setSelected(true);
					area.setExpanded(true);
					areaSeleccionada = false;
				}
				if(recargarMenu==true){
					
					if(this.idAreaBusqueda.intValue() == dtoBandejaOficialia.getIdArea().intValue() 
							&& this.tipoAreaBusqueda.intValue() == dtoBandejaOficialia.getTipoArea().intValue()){
						
						area.setSelected(true);
						area.setSelectable(false);
						areaSeleccionada = false;
					
					}
				}
				
			}
			
			
			
			//this.idAreaBusqueda = dtoCAreas.get(0).getIdArea();
			//this.tipoAreaBusqueda = dtoCAreas.get(0).getTipoArea();
			//this.dtBandejaSeleccionados = null;
			
		}
		
	}
	
	/**
	 * Método que consulta el total de documentos pendientes en notificaciones de las áreas
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void consultarPendientes(Integer idArea, Integer tipoArea){
		
		this.numeroNotificaciones = bsdBandejaOficialiaInterface.consultarNotificacionesPorArea(idArea, 
				tipoArea, Integer.parseInt(Utilidades.mensajeProperties("id_apartado_bandeja_oficialia")));
		
	}
	
	/**
	 * Método que muestra el tipo de mensaje del growl dependiendo de lo que se regresó de la pantalla de
	 * clasificar
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void mostrarMensaje(){
		
		if(this.mostrarMensaje != 0){
			
			if(this.mostrarMensaje == 1){
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), "El documento fue clasificado exitosamente"));
			
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.mensajeProperties("titulo_growl_info"), "Se ha cancelado la clasificación del documento"));
			}
		}
	}
	
	/**
	 * Método que muestra los documentos del área seleccionada
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void seleccionarArea(NodeSelectEvent event) {
		//event.getTreeNode().setSelectable(false);
		//
		List<TreeNode> areas = this.root.getChildren().get(0).getChildren();
		for(TreeNode t : areas ){
			t.setSelected(false);
			t.setSelectable(true);
			t.setExpanded(false);
		}
		this.areaSeleccion = event.getTreeNode();
		this.areaSeleccion.setSelected(true);
		this.areaSeleccion.setSelectable(false);
		this.areaSeleccion.setExpanded(true);
		
		this.idAreaBusqueda = ((DTOCAreaEntity)event.getTreeNode().getData()).getIdArea();
		this.tipoAreaBusqueda = ((DTOCAreaEntity)event.getTreeNode().getData()).getTipoArea();
		
		
		if(this.idAreaBusqueda != null){
			this.dtoBandejaFiltro.setIdArea(this.idAreaBusqueda);
		}
		
       	this.dtBandejaSeleccionados = null;
   		
    }
	
	/**
	 * Método que muestra el previo del primer documento después de paginar lazy
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void mostrarPrimero(){
		
		this.dtBandejaSeleccionados = this.listaBandejaLazy.getListaDatosLazy().get(0);
    	seleccionarEnviado(this.dtBandejaSeleccionados);
	}
	
	/**
	 * Método que muestra el previo del documento al seleccionar un documento
	 * 
	 * @param dtoBandeja objeto seleccionado
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void seleccionarEnviado(DTOBandejaEntradasOficialiaEntity dtoBandeja) {
		
		dtBandejaSeleccionados = dtoBandeja;
		setRutaDocumento(dtBandejaSeleccionados);
		
		if (dtBandejaSeleccionados.getDtoDocumento().getContieneAnexos() > 0) {
			listaAnexos = bsdBandejaOficialiaInterface
					.consultarAnexos(dtBandejaSeleccionados);
		}
		this.listaDTORemitentes = bsdBandejaOficialiaInterface.consultarRemitentes(dtBandejaSeleccionados.getIdDocumento(), 
				this.idAreaBusqueda, this.tipoAreaBusqueda, this.dtBandejaSeleccionados.getNoLeido());
		
		
		log.info(this.idAreaBusqueda+" - " + this.tipoAreaBusqueda);
		log.info(dtoBandeja.getEnClasificacion());
		
		if(this.dtBandejaSeleccionados.getNoLeido().intValue()==1){
			this.dtBandejaSeleccionados.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_leido")));
			construirMenu(false,true);
			
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesOficialia();
		}
		
		
		
	}

	/**
	 * Evento que muestra el previo del documento al seleccionar un documento
	 * 
	 * @param event: evento selected
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void seleccionarEnviado(SelectEvent event) {

		seleccionarEnviado(((DTOBandejaEntradasOficialiaEntity) event.getObject()));
	}

	/**
	 * Método que muestra el dálogo de anexos
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void mostrarDialogAnexos() {
		RequestContext.getCurrentInstance().execute("PF('dialogVerAnexosEnvaidos').show()");
	}
	
	/**
	 * Método que ordena la lista lazy por nombre de documento
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void ordenarPorNombre(){
		this.dtoBandejaFiltro.setCampoOrden("dtoDocumento.noDocumento");
	}
	
	/**
	 * Método que ordena la lista lazy por fecha
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void ordenarPorFecha(){
		this.dtoBandejaFiltro.setCampoOrden("fechaRecepcion");
	}

	/**
	 * Método que actualiza la pantalla cuando el reloj llega a cero
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void actualizarPantallaBandeja() {
		iniciar();
	}
	
	/**
	 * Método manda a la pantalla de clasificar documento
	 *
	 * @author David Rodríguez Corral
	 * @since 02/11/2017
	 */
	public void clasificarDocumento(){
		DTOBandejaEntradasOficialiaEntity dtoBandeja = this.dtBandejaSeleccionados;
		
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		String mensajeClasificacion = bsdBandejaOficialiaInterface.clasificarDocumento(dtoBandeja, usuarioLogueado.getIdOficialia(), this.idAreaBusqueda);
		
		
		if (!mensajeClasificacion.isEmpty()){
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), mensajeClasificacion));
			
		}else{
			
			RequestContext context = RequestContext.getCurrentInstance();
        	context.execute("clasificarDoc()");
        	
        	MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
    		administrador.consultarNotificacionesOficialia();
    		administrador.setMenuSelecionado(Integer.parseInt(Utilidades.mensajeProperties("id_modulo_clasificacion_oficialia")));
			
		}
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	
	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable root
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/11/2017
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author David Rodríguez Corral
	 * @since 20/11/2017
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @return valor de tipo List<DTOCAreas> que esta contenido en la variable dtoCAreas
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/11/2017
	 */
	public List<DTOCAreaEntity> getDtoCAreas() {
		return dtoCAreas;
	}

	/**
	 * @param dtoCAreas : valor que se ingresa a la variable de tipo List<DTOCAreas>
	 *
	 * @author David Rodríguez Corral
	 * @since 20/11/2017
	 */
	public void setDtoCAreas(List<DTOCAreaEntity> dtoCAreas) {
		this.dtoCAreas = dtoCAreas;
	}

	/**
	 * @return valor de tipo DTOBandejaEntradasOficialiaEntity que esta contenido en la variable dtBandejaSeleccionados
	 * 
	 * @author David Rodríguez Corral
	 * @since 23/11/2017
	 */
	public DTOBandejaEntradasOficialiaEntity getDtBandejaSeleccionados() {
		return dtBandejaSeleccionados;
	}

	/**
	 * @param dtBandejaSeleccionados : valor que se ingresa a la variable de tipo DTOBandejaEntradasOficialiaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 23/11/2017
	 */
	public void setDtBandejaSeleccionados(
			DTOBandejaEntradasOficialiaEntity dtBandejaSeleccionados) {
		this.dtBandejaSeleccionados = dtBandejaSeleccionados;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable rutaDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 24/11/2017
	 */
	public String getRutaDocumento() {
		return rutaDocumento;
	}

	/**
	 * @param rutaDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 24/11/2017
	 */
	public void setRutaDocumento(DTOBandejaEntradasOficialiaEntity dtoEnviadosDocumentosEntity) {
		if (dtoEnviadosDocumentosEntity != null) {
			this.rutaDocumento = Utilidades.getRutaGlusterFS()+File.separator+"Gestion4"+File.separator+"pdf"
					+ File.separator
					+ dtoEnviadosDocumentosEntity.getDtoDocumento().getIdDocumento()+ "_"
							+ dtoEnviadosDocumentosEntity.getDtoDocumento().getAnio() + ".pdf";
		} else {
			this.rutaDocumento = "";
		}
	}

	/**
	 * @return valor de tipo List<DTODocumentoAnexoEntity> que esta contenido en la variable listaAnexos
	 * 
	 * @author David Rodríguez Corral
	 * @since 24/11/2017
	 */
	public List<DTODocumentoAnexoEntity> getListaAnexos() {
		return listaAnexos;
	}

	/**
	 * @param listaAnexos : valor que se ingresa a la variable de tipo List<DTODocumentoAnexoEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 24/11/2017
	 */
	public void setListaAnexos(List<DTODocumentoAnexoEntity> listaAnexos) {
		this.listaAnexos = listaAnexos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable rutaGlusterAnexos
	 * 
	 * @author David Rodríguez Corral
	 * @since 26/11/2017
	 */
	public String getRutaGlusterAnexos() {
		return rutaGlusterAnexos;
	}

	/**
	 * @param rutaGlusterAnexos : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 26/11/2017
	 */
	public void setRutaGlusterAnexos(String rutaGlusterAnexos) {
		this.rutaGlusterAnexos = rutaGlusterAnexos;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaBusqueda
	 * 
	 * @author David Rodríguez Corral
	 * @since 27/11/2017
	 */
	public Integer getIdAreaBusqueda() {
		return idAreaBusqueda;
	}

	/**
	 * @param idAreaBusqueda : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 27/11/2017
	 */
	public void setIdAreaBusqueda(Integer idAreaBusqueda) {
		this.idAreaBusqueda = idAreaBusqueda;
	}

	/**
	 * @return valor de tipo PaginarLazy<DTOBandejaEntradasOficialiaEntity,DTOBandejaOficialiaHelper> que esta contenido en la variable listaBandejaLazy
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/12/2017
	 */
	public PaginarLazy<DTOBandejaEntradasOficialiaEntity, DTOBandejaOficialiaHelper> getListaBandejaLazy() {
		return listaBandejaLazy;
	}

	/**
	 * @param listaBandejaLazy : valor que se ingresa a la variable de tipo PaginarLazy<DTOBandejaEntradasOficialiaEntity,DTOBandejaOficialiaHelper>
	 *
	 * @author David Rodríguez Corral
	 * @since 01/12/2017
	 */
	public void setListaBandejaLazy(
			PaginarLazy<DTOBandejaEntradasOficialiaEntity, DTOBandejaOficialiaHelper> listaBandejaLazy) {
		this.listaBandejaLazy = listaBandejaLazy;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable mostrarMensaje
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getMostrarMensaje() {
		return mostrarMensaje;
	}

	/**
	 * @param mostrarMensaje : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setMostrarMensaje(Integer mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	/**
	 * @return valor de tipo DTOBandejaOficialiaHelper que esta contenido en la variable dtoBandejaFiltro
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public DTOBandejaOficialiaHelper getDtoBandejaFiltro() {
		return dtoBandejaFiltro;
	}

	/**
	 * @param dtoBandejaFiltro : valor que se ingresa a la variable de tipo DTOBandejaOficialiaHelper
	 *
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public void setDtoBandejaFiltro(DTOBandejaOficialiaHelper dtoBandejaFiltro) {
		this.dtoBandejaFiltro = dtoBandejaFiltro;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable listaDTORemitentes
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/01/2018
	 */
	public String getListaDTORemitentes() {
		return listaDTORemitentes;
	}

	/**
	 * @param listaDTORemitentes : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/01/2018
	 */
	public void setListaDTORemitentes(String listaDTORemitentes) {
		this.listaDTORemitentes = listaDTORemitentes;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaBusqueda
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public Integer getTipoAreaBusqueda() {
		return tipoAreaBusqueda;
	}

	/**
	 * @param tipoAreaBusqueda : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void setTipoAreaBusqueda(Integer tipoAreaBusqueda) {
		this.tipoAreaBusqueda = tipoAreaBusqueda;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable numeroNotificaciones
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public Integer getNumeroNotificaciones() {
		return numeroNotificaciones;
	}

	/**
	 * @param numeroNotificaciones : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void setNumeroNotificaciones(Integer numeroNotificaciones) {
		this.numeroNotificaciones = numeroNotificaciones;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable bandejaEntrada
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public DTOCAreaEntity getBandejaEntrada() {
		return bandejaEntrada;
	}

	/**
	 * @param bandejaEntrada : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void setBandejaEntrada(DTOCAreaEntity bandejaEntrada) {
		this.bandejaEntrada = bandejaEntrada;
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable entrada
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public TreeNode getEntrada() {
		return entrada;
	}

	/**
	 * @param entrada : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void setEntrada(TreeNode entrada) {
		this.entrada = entrada;
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable areaSeleccion
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public TreeNode getAreaSeleccion() {
		return areaSeleccion;
	}

	/**
	 * @param areaSeleccion : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void setAreaSeleccion(TreeNode areaSeleccion) {
		this.areaSeleccion = areaSeleccion;
	}
	
	

}
