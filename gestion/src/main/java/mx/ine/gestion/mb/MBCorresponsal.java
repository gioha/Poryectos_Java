package mx.ine.gestion.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import mx.ine.gestion.bsd.inter.BSDCapturaCorresponsalInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOCorresponsales;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOCapturaCorresponsalForm;
import mx.ine.gestion.dto.helper.DTOMesesHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface;
import mx.ine.gestion.vh.inter.VHCorresponsalInterface;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase (ManageBean) se encarga agregar, eliminar lógicamente y mostrar los
 * corresponsales del usuario logueado.
 *
 * @author Luis Urrutia
 * @since 16/11/2017
 */
public class MBCorresponsal implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 6746214322449672072L;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger logger = Logger.getLogger(MBCorresponsal.class);

	@Autowired
	@Qualifier("vhCapturaDocumento")
	private transient VHCapturaDocumentoInterface vh;

	@Autowired
	@Qualifier("vhCorresponsal")
	private transient VHCorresponsalInterface vhCorresponsal;

	@Autowired
	@Qualifier("bsdCapturaCorresponsal")
	private transient BSDCapturaCorresponsalInterface bscCapturaCorresponsal;

	/**
	 * Lista que contiene los corresponsales candidatos.
	 */
	private List<DTOEstructuraAreasEntity> listaCorresponsalesCandidatos;

	/**
	 * Lista que contiene los corresponsales asignados al usuario logueado.
	 */
	private List<DTOEstructuraAreasEntity> listaCorresponsales;

	/**
	 * Contiene la lista de los meses que se visualiza en la pantalla.
	 */
	private List<DTOMesesHelper> listaMeses;

	/**
	 * Valor para saber si se habilita el botón de "Agregar".
	 */
	private boolean isRowSelected;

	/**
	 * Variable para saber el rol del usuario logueado.
	 */
	private String rolUsuarioLogueado;

	/**
	 * Variable necesaria para almacenar la fecha actual para su posterior uso
	 */
	private Date fechaActual;

	/**
	 * Objeto que contiene las propiedades del usuario logueado
	 */
	private DTOUsuarioLogin usuarioLogueado;

	/**
	 * Objeto que contiene los valores del form de la página
	 * captura_corresponsal.xhtml
	 */
	private DTOCapturaCorresponsalForm form;

	/**
	 * Método inicial del MB, se ejecuta sin importar el flujo que lo genera
	 *
	 * @author Luis Urrutia
	 * @since 1/12/2017
	 */
	@PostConstruct
	public void init() {
		this.form = new DTOCapturaCorresponsalForm();
	}

	/**
	 * Método para inicializar los atributos que se necesiten en cuanto carga la
	 * pantalla captura_corresponsal.xhtml
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void iniciar() {

		usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		buscarCorresponsales(usuarioLogueado.getIdPersona());
		rolUsuarioLogueado = usuarioLogueado.getRol();
		listaMeses = Utilidades.obtenerListaDeMeses();
	}

	/**
	 * Método para buscar los corresponsales ya asignados a una persona
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	private void buscarCorresponsales(Integer idPersona) {
		listaCorresponsales = bscCapturaCorresponsal.buscarCorresponsales(idPersona);
	}

	/**
	 * Método que verifica que no se exceda el número máximo de corresponsales
	 * permitidos
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	private boolean isCorresponsalesCompletos() {
		if (listaCorresponsales.size() >= 3)
			return true;
		else
			return false;
	}

	/**
	 * Método que obtiene a las personas que podrían ser corresponsales
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void buscarCorresponsalesCandidatos() {

		isRowSelected = false;
		
		// Se valida que el usuario tenga completos sus corresponsales
		if (isCorresponsalesCompletos()) {
			Utilidades.enviaMensajeGeneral(null, Utilidades.mensajeProperties("validacion_mensajes_maximo_corresponsales"), "", FacesMessage.SEVERITY_WARN);
			return;
		}
		
		// Se valida que los campos no estén vacios
		String error = vhCorresponsal.validaInicioBusquedaCorresponsal(form);
		if (!error.isEmpty()) {
			
			Utilidades.enviaMensajeGeneral(null, error, "", FacesMessage.SEVERITY_WARN);
			return;
		}

		this.form.setCorresponsalCandidatoSeleccionado(null);
		this.listaCorresponsalesCandidatos = null;
		this.listaCorresponsalesCandidatos = bscCapturaCorresponsal.buscarCorresponsalesCandidatos(
				form.getNombreCorresponsalCandidato(), form.getApellidosCorresponsalCandidato(),
				usuarioLogueado.getIdPersona(), usuarioLogueado.getIdArea(), usuarioLogueado.getTipoArea());
		
		// Muestra la tabla con los empleados encontrados a traves de la búsqueda
		if (listaCorresponsalesCandidatos == null || listaCorresponsalesCandidatos.isEmpty())
			
			Utilidades.enviaMensajeGeneral(null, Utilidades.mensajeProperties("busqueda_mensajes_sin_corresponsales_candidatos"), "", FacesMessage.SEVERITY_WARN);
		else
			RequestContext.getCurrentInstance().execute("PF('dlgBuscarCorresponsal').show()");

	}

	/**
	 * Método de evento que muestra la información de datalle del corresponsal
	 * 
	 * @param corresponsal
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void detalleCorresponsal(DTOEstructuraAreasEntity corresponsal) {
		form.setCorresponsalDetalle(corresponsal);
	}

	/**
	 * Método que elimina lógicamente al corresponsal indicado
	 *
	 * @param corresponsal
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void eliminarCorresponsal(DTOEstructuraAreasEntity corresponsal) {
		try {
			logger.info("Iniciando la eliminación lógica del corresponsal en BD");
			bscCapturaCorresponsal.eliminarCorresponsal(corresponsal.getIdCorresponsal());
			Utilidades.enviaMensajeGeneral(null,Utilidades.mensajeProperties("validacion_mensajes_exito_eliminar_corresponsal"), "",
					FacesMessage.SEVERITY_FATAL);
			buscarCorresponsales(usuarioLogueado.getIdPersona());
		} catch (Exception e) {
			logger.error("Error al eliminar corresponsal en MBCorresponsal.eliminarCorresponsal ", e);
			Utilidades.enviaMensajeGeneral(null, Utilidades.mensajeProperties("validacion_mensajes_error_eliminar_corresponsal	"), "",
					FacesMessage.SEVERITY_WARN);
		}
	}

	/**
	 * Método que agrega un Corresponsal
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void agregarCorresponsal() {
		try {

			Integer idCorresponsal = form.getCorresponsalCandidatoSeleccionado().getIdPersona();
			DTOCorresponsales corresponsal = new DTOCorresponsales();
			corresponsal.setIdPersonaCo(idCorresponsal);
			corresponsal.setIdPersonaTi(usuarioLogueado.getIdPersona());
			corresponsal.setFechaCaptura(getFechaActual());
			corresponsal.setFechaInicio(form.getFechaInicio());
			corresponsal.setFechaFin(form.getFechaFin());
			corresponsal.setEstatus('A');
			bscCapturaCorresponsal.agregarCorresponsal(corresponsal);
			buscarCorresponsales(usuarioLogueado.getIdPersona());
			limpiarCampos();
			RequestContext.getCurrentInstance().execute("PF('dlgBuscarCorresponsal').hide()");
			Utilidades.enviaMensajeGeneral(null, Utilidades.mensajeProperties("validacion_mensajes_exito_captura_corresponsal"),
					"",FacesMessage.SEVERITY_FATAL);

		} catch (Exception e) {
			logger.error("Error en el insert del corresponsal en MBCorresponsal.agregarCorresponsal ", e);
			Utilidades.enviaMensajeGeneral(null, Utilidades.mensajeProperties("validacion_mensajes_error_captura_corresponsal"),
					"", FacesMessage.SEVERITY_WARN);
		}
	}

	/**
	 * Método que limpia los campos del formulario
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	private void limpiarCampos() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		form.setNombreCorresponsalCandidato(null);
		form.setApellidosCorresponsalCandidato(null);
		form.setFechaInicio(null);
		form.setFechaFin(null);
		requestContext.reset("form_corresponsales:InputNombreC");
		requestContext.reset("form_corresponsales:InputApellidoC");
	}

	/**
	 * Método que obtiene la fecha actual
	 *
	 * @author Luis Urrutia
	 * @since 30/11/2017
	 */
	public Date getFechaActual() {
		fechaActual = new Date();
		return fechaActual;
	}

	/**
	 * Método de evento que indica si se ha seleccionado un registro de la tabla
	 * de corresponsales candidatos
	 * 
	 * @param event
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void onRowSelect(SelectEvent event) {
		isRowSelected = true;
	}

	/* ------------------------------------- Getters y Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo boolean que esta contenido en la variable isRowSelected
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public boolean getIsRowSelected() {
		return isRowSelected;
	}

	/**
	 * @param isRowSelected : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void setIsRowSelected(boolean isRowSelected) {
		this.isRowSelected = isRowSelected;
	}

	/**
	 * @return valor de tipo DTOCapturaCorresponsalForm que esta contenido en la variable form
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public DTOCapturaCorresponsalForm getForm() {
		return form;
	}

	/**
	 * @param form : valor que se ingresa a la variable de tipo DTOCapturaCorresponsalForm
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void setForm(DTOCapturaCorresponsalForm form) {
		this.form = form;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaCorresponsalesCandidatos
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaCorresponsalesCandidatos() {
		return listaCorresponsalesCandidatos;
	}

	/**
	 * @param listaCorresponsalesCandidatos : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void setListaCorresponsalesCandidatos(List<DTOEstructuraAreasEntity> listaCorresponsalesCandidatos) {
		this.listaCorresponsalesCandidatos = listaCorresponsalesCandidatos;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaCorresponsales
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaCorresponsales() {
		return listaCorresponsales;
	}

	/**
	 * @param listaCorresponsales : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void setListaCorresponsales(List<DTOEstructuraAreasEntity> listaCorresponsales) {
		this.listaCorresponsales = listaCorresponsales;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable rolUsuarioLogueado
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public String getRolUsuarioLogueado() {
		return rolUsuarioLogueado;
	}

	/**
	 * @param rolUsuarioLogueado : valor que se ingresa a la variable de tipo String
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void setRolUsuarioLogueado(String rolUsuarioLogueado) {
		this.rolUsuarioLogueado = rolUsuarioLogueado;
	}

	/**
	 * @return valor de tipo List<DTOMesesHelper> que esta contenido en la variable listaMeses
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 04/01/2018
	 */
	public List<DTOMesesHelper> getListaMeses() {
		return listaMeses;
	}

	/**
	 * @param listaMeses : valor que se ingresa a la variable de tipo List<DTOMesesHelper>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 04/01/2018
	 */
	public void setListaMeses(List<DTOMesesHelper> listaMeses) {
		this.listaMeses = listaMeses;
	}

}
