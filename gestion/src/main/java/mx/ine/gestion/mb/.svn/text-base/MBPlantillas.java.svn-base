/**
 * @(#)MBPlantillas.java 27/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDPlantillasInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHPlantillasInterface;
import mx.org.ine.servicios.utils.PaginarLazy;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase encargada de recibir las peticiones que se ocupan en el módulo para configurar plantillas
 * 
 * @author Roberto Shirásago Domínguez
 * @since 08/07/2017
 */
public class MBPlantillas implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = -3132207748771196579L;

	/**
	 * Objeto para generar el log de la clase.
	 */
	private static Logger logger= Logger.getLogger(MBPlantillas.class);
	
	@Autowired
	@Qualifier("bsdPlantillas")
	private transient BSDPlantillasInterface bsdPlantillas;

	@Autowired
	@Qualifier("vhPlantillas")
	private transient VHPlantillasInterface vhPlantillasInterface;
	
	/**
	 * Objeto que contiene los valores del form de la página
	 * plantillas.xhtml
	 */
	private DTOFiltrosPlantillaHelper filtros;
	
	/**
	 * Lista tipo Lazy que contiene los registros de las plantillas del usuario
	 */
	private PaginarLazy<DTOPlantillaEntity, DTOFiltrosPlantillaHelper> listaPlantillas;

	/* ------------------------------------------ Métodos --------------------------------------- */
	
	/**
	 * Método inicial del MB, se ejecuta sin importar el flujo que lo genera
	 **/
	@PostConstruct
	public void init() {
		try {
			
			DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			this.filtros = new DTOFiltrosPlantillaHelper();
			filtros.setIdPersona(usuarioLogueado.getIdPersona());
			filtros.setIdArea(usuarioLogueado.getIdArea());
			filtros.setTipoArea(usuarioLogueado.getTipoArea());

			obtenerTipoDocumentos();
			listaPlantillas = new PaginarLazy<DTOPlantillaEntity, DTOFiltrosPlantillaHelper>("bsdPlantillas", this.filtros);
			
		} catch (Exception e) {
			logger.error("Error en MBPlantillas.init ", e);
		}
	}
	
	
	/**
	 * Método para obtener los tipos de documentos
	 *
	 * @author Luis Urrutia
	 * @since 05/12/2017
	 */
	private void obtenerTipoDocumentos() {
		this.filtros.setTipoDocumentos(bsdPlantillas.obtenerTipoDocumentos());
	}
	
	/**
	 * Método para indicar que se seleccionó el combo de tipo de documento
	 *
	 * @author Luis Urrutia
	 * @since 06/12/2017
	 */
	public void comboTipoDocumentoSeleccionado(){
		
		limpiarComboAcronimo();
		obtenerAcronimos();	
	}
	
	/**
	 * Método para obtener los acronimos
	 *
	 * @author Luis Urrutia
	 * @since 06/12/2017
	 */
	private void obtenerAcronimos() {
		this.filtros.setAcronimos(bsdPlantillas.obtenerAcronimos(filtros));
	}

	/**
	 * Método para generar una plantilla la cual se capturara (AUN NO SE GUARDA EN LA BD)
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 14/01/2018
	 */
	public void capturarPlantilla() {

		
		//Validamos que no exceda del permitido para el acronimo seleccionado
		if(this.bsdPlantillas.validarNumeroPlantillas(this.filtros)) {

			Utilidades.enviaMensajeGeneral(null, 
					"El número máximo de plantillas por acrónimo permitidas es de 2, si deseas agregar una nueva tendrás que borrar alguna anterior.","",
					FacesMessage.SEVERITY_WARN);
			
			return;
		}
		
		try {
			
			//1.- Creamos el temporal
			this.vhPlantillasInterface.crearTemporalPlantilla();
			
			//2.- Habilitamos los siguientes pasos
			this.filtros.setVisualizarElementosPosCaptura(true);
			
			RequestContext requestContext = RequestContext.getCurrentInstance();  
			requestContext.execute("creaHyperLink()");

		} catch (Exception e) {
			
			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar la plantilla, favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! al crear la plantilla temporal!!!");
			logger.error("<=================== Clase: MBPlantillas , Método: capturarPlantilla");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}	
	}

	/**
	 * Método que genera el hipervinculo de la plantilla temporal y abre el word a donde esta apuntando
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 14/01/2018
	 */
	public void abrirWordTemporal() {
	
		try {
		
			//1.-Generamos el hiperlink
			this.filtros.setHyperLinkDocumentoTmp(vhPlantillasInterface.generarHyperLinkPlantillaTmp());
	
			//2.-Abrimos el hiperlink
			FacesContext.getCurrentInstance().getExternalContext().redirect(this.filtros.getHyperLinkDocumentoTmp());
		
		} catch (Exception e) {
			
			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar la plantilla, favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! al crear el HYPERLINK de la plantilla temporal");
			logger.error("<=================== Clase: MBPlantillas , Método: generarHiperVinculo");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}	
	}

	/**
	 * Método que se ejecuta al cancelar el agregado de una plantilla
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 14/01/2018
	 */
	public void cancelarAgregarPlantilla() {

		//1.- Eliminamos el docx temporal
		this.vhPlantillasInterface.eliminarTemporalPlantilla();
		
		//2.- Ocultamos los datos
		this.filtros.setVisualizarElementosPosCaptura(false);
	}
	
	/**
	 * Método que agrega una plantilla creada por el usuario.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void agregarPlantilla() {
		try {
			
			this.bsdPlantillas.agregarPlantilla(this.filtros);
			limpiarCampos();
			Utilidades.enviaMensajeGeneral(null, "Éxito",
					Utilidades.mensajeProperties("validacion_mensajes_exito_captura_plantilla"),
					FacesMessage.SEVERITY_FATAL);

		} catch (Exception e) {

			Utilidades.enviaMensajeGeneral(null, "Ocurrio un error al agregar la plantilla, favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! al AGREGAR la plantilla a la BD");
			logger.error("<=================== Clase: MBPlantillas , Método: agregarPlantilla");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}
	}
	
	/**
	 * Método que elimina una plantilla
	 *
	 * @param plantilla
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public void eliminarPlantilla(DTOPlantillaEntity plantilla) {
		try {
			
			bsdPlantillas.eliminarPlantilla(plantilla);
			Utilidades.enviaMensajeGeneral(null, "Éxito",
					Utilidades.mensajeProperties("validacion_mensajes_exito_eliminar_plantilla"),
					FacesMessage.SEVERITY_FATAL);

		} catch (Exception e) {
			
			Utilidades.enviaMensajeGeneral(null, "Ocurrio un error al capturar la plantilla, favor de reportar al CAU.", "",
						FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! eliminar la plantilla");
			logger.error("<=================== Clase: MBPlantillas , Método: eliminarPlantilla");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}
	}

	/**
	 * Método que se manda llamar cuando se edita la plantilla (abre el documento en word para ser editado)
	 * 
	 * @param plantilla: Objeto que contiene la información de la plantilla que se va a editar.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void editarPlantilla(DTOPlantillaEntity plantilla) {

		try {
			
			//1.-Obtenemos el hiperlink
			String hiperVinculo = this.vhPlantillasInterface.generarHyperLinkPlantilla(plantilla);
			
			//2.-Abrimos el hiperlink
			FacesContext.getCurrentInstance().getExternalContext().redirect(hiperVinculo);

		} catch (Exception e) {
			
			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al editar la plantilla, favor de intentarlo de nuevo, si el error persiste reportar al CAU.", "",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! al editar la plantilla");
			logger.error("<=================== Clase: MBPlantillas , Método: editarPlantilla");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("<=================== ID_ACRONIMO: " + plantilla.getIdAcronimo());
			logger.error("<=================== ID_AREA: " + plantilla.getIdArea());
			logger.error("<=================== TIPO_AREA: " + plantilla.getTipoArea());
			logger.error("<=================== ID_TIPO_DOCUMENTO: " + plantilla.getIdTipoDocumento());
			logger.error("<=================== ID_PLANTILLA: " + plantilla.getIdPlantilla());
			
			logger.error("", e);
		}
	}
	
	/**
	 * Método que limpia los campos del formulario
	 *
	 * @author Luis Urrutia
	 * @since 08/12/2017
	 */
	private void limpiarCampos() {
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		filtros.setIdTipoDocumentoSeleccionado(null);
		filtros.setNombrePlantilla(null);
		filtros.setAcronimoSeleccionado(null);
		filtros.setVisualizarElementosPosCaptura(false);
		filtros.setHyperLinkDocumentoTmp(null);
		requestContext.reset("form_plantillas:comboTipoDocumento");
		requestContext.reset("form_plantillas:comboAcronimo");
		requestContext.reset("form_plantillas:comboNombrePlantilla");
	}
	
	/**
	 * Método que limpia el combo de acrónimo
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	private void limpiarComboAcronimo() {

		RequestContext requestContext = RequestContext.getCurrentInstance();
		filtros.setAcronimoSeleccionado(null);
		requestContext.reset("form_plantillas:comboAcronimo");
	}

	/* ------------------------------------------ Getters & Setters --------------------------------------- */
	
	/**
	 * @return valor de tipo DTOFiltrosPlantillaHelper que esta contenido en la variable filtros
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public DTOFiltrosPlantillaHelper getFiltros() {
		return filtros;
	}

	/**
	 * @param filtros : valor que se ingresa a la variable de tipo DTOFiltrosPlantillaHelper
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public void setFiltros(DTOFiltrosPlantillaHelper filtros) {
		this.filtros = filtros;
	}

	/**
	 * @return valor de tipo PaginarLazy<DTOPlantillaEntity, DTOFiltrosPlantillaHelper> que esta contenido en la variable listaPlantillas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public PaginarLazy<DTOPlantillaEntity, DTOFiltrosPlantillaHelper> getListaPlantillas() {
		return listaPlantillas;
	}

	/**
	 * @param listaPlantillas : valor que se ingresa a la variable de tipo PaginarLazy<DTOPlantillaEntity, DTOFiltrosPlantillaHelper>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public void setListaPlantillas(
			PaginarLazy<DTOPlantillaEntity, DTOFiltrosPlantillaHelper> listaPlantillas) {
		this.listaPlantillas = listaPlantillas;
	}

}
