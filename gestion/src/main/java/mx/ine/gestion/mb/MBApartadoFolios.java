/**
 * @(#)MBApartadoFolios.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHApartadoFoliosInterface;
import mx.org.ine.servicios.utils.PaginarLazy;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase que recibe todas las peticiones que se ejecutan en el módulo de apartado de folios.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
public class MBApartadoFolios implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -7851362858754890591L;

	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBApartadoFolios.class);

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	@Autowired
	@Qualifier("vhApartadoFolios")
	private transient VHApartadoFoliosInterface vhApartadoFoliosInterface;

	@Autowired
	@Qualifier("bsdApartadoFolios")
	private transient BSDApartadoFoliosInterface bsdApartadoFoliosInterface;
	
	/**
	 * Objeto que contiene todos los filtros que se utilizan en la pantalla para poder
	 * apartar los folios.
	 */
	private DTOFiltrosApartadoFolioHelper filtros;

	/**
	 * Lista que contiene los folios que se han apartado para documentos físicos,
	 * se muestra en un datatable que esta páginado de manera Lazy
	 */
	private PaginarLazy<DTOApartadoNumDocEntity, DTOFiltrosApartadoFolioHelper> listaFoliosApartadosLazy;

	/**
	 * Objeto que contiene la información del folio que se va a eliminar/deshabilitar
	 */
	private DTOApartadoNumDocEntity folioParaEliminar;

	/* ------------------------------------------ Métodos --------------------------------------- */

	/**
	 * Método que inicializa las variables que se utilizan en la pantalla
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void inicializarPantalla() {
		
		this.filtros = new DTOFiltrosApartadoFolioHelper();
		this.listaFoliosApartadosLazy = new PaginarLazy<DTOApartadoNumDocEntity, DTOFiltrosApartadoFolioHelper>("bsdApartadoFolios", this.filtros);
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		if(!usuarioLogueado.getRol().equalsIgnoreCase("GESTION4.ADMIN.OC")) {
			this.vhApartadoFoliosInterface.cargarInformacionUsuarioEnSesion(filtros);
			this.consultarTiposDocumento();
		} else {
			this.bsdApartadoFoliosInterface.obtenerInfoTipoAreas(this.filtros);
		}
	}

	/**
	 * Método para validar que consulta se hará dependiendo del tipo de área que escogio el usuario
	 * para el caso en que sea organo descondentrado hará la búsqueda de entidades
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public void validarTipoAreaSeleccionada() {
		
		//1.- Reiniciamos filtros
		this.vhApartadoFoliosInterface.reiniciarFiltros(1, this.filtros);
		
		//2.- Validamos cual será la carga
		if (this.filtros.getTipoArea().equals(Integer.valueOf(Utilidades.mensajeProperties("id_tipo_area_organos_desconcentrados")))) {

			this.consultarEntidades();
			
		} else {

			this.consultarAreas();
		}
	}

	/**
	 * Método que obtiene las entidades que se utilizan en los filtros para el apartado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public void consultarEntidades() {

		try {
			//1.- Reiniciamos filtros
			this.vhApartadoFoliosInterface.reiniciarFiltros(2, this.filtros);

			//2.- Cargamos entidades
			this.bsdApartadoFoliosInterface.obtenerInfoEntidades(this.filtros);

		} catch (Exception e) {
			log.error("<===================== ERROR!! al obtener los estados de communicaciones.jar");
			log.error("<=================== Clase: MBApartadoFolios , Método: consultarEntidades");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	/**
	 * Método que hace la consulta de las áreas que se utilizan en los filtros para el apartado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public void consultarAreas() {
	
		//1.- Reiniciamos filtros
		this.vhApartadoFoliosInterface.reiniciarFiltros(3, this.filtros);

		//2.- Cargamos áreas
		this.bsdApartadoFoliosInterface.obtenerInfoAreas(this.filtros);
	}

	/**
	 * Método que obtiene los tipos de documento que se utilizan en los filtros para el apartado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void consultarTiposDocumento() {
		
		//1.- Reiniciamos filtros
		this.vhApartadoFoliosInterface.reiniciarFiltros(4, this.filtros);

		//2.- Cargamos tipo documentos
		this.bsdApartadoFoliosInterface.obtenerInfoTipoDocumentos(this.filtros);
	}

	/**
	 * Método que obtiene los acronimos relacionados al folio que se apartara
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public void consultarAcronimos() {
		this.bsdApartadoFoliosInterface.obtenerInfoActonimos(this.filtros);
	}

	/**
	 * Método que se manda llamar cuando se captura el apartado de folios (desde el botón Aceptar).
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public void capturarApartadoFolio() {
		
		//1.- Validamos la información de la captura (la que no se valido en front)
		String mensajeError = vhApartadoFoliosInterface.validarCapturaApartadoFolios(this.filtros);
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}

		try {

			this.bsdApartadoFoliosInterface.capturarApartadoFolios(this.filtros);
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
	        "El proceso de apartado fue exitoso, puede consultar los folios apartados en la tabla que se encuentra abajo", ""));

		} catch (Exception e) {
			log.error("<=================== Clase: MBApartadoFolios , Método: capturarApartadoFolio");
			log.error("<=================== Ocurrio un error al CAPTURAR LOS FOLIOS A APARTAR");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("",e);
		}
	}

	/**
	 * Método para eliminar el folio apartado.
	 * 
	 * @param folioApartado: Objeto que contiene el registro del folio que se aparto y que se va a deshabilitar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 14/12/2017
	 */
	public void eliminarApartadoFolio() {
		
		try {
		
			this.bsdApartadoFoliosInterface.deshabilitarFoliosApartados(this.folioParaEliminar);
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
	        "Se deshabilito correctamente el folio", ""));
		
		} catch (Exception e) {
			log.error("<=================== Clase: MBApartadoFolios , Método: eliminarApartadoFolio");
			log.error("<=================== Ocurrio un error al ELIMINAR LOS FOLIOS A APARTAR");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	        "Ocurrió un error al tratar de deshabilitar el folio, por favor intente otra vez, en caso de presentarse el error de nuevo comuniquese al CAU.", ""));
		}
	}
	
	/* ------------------------------------------ Getters & Setters --------------------------------------- */

	/**
	 * @return valor de tipo DTOFiltrosApartadoFolioHelper que esta contenido en la variable filtros
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public DTOFiltrosApartadoFolioHelper getFiltros() {
		return filtros;
	}

	/**
	 * @param filtros : valor que se ingresa a la variable de tipo DTOFiltrosApartadoFolioHelper
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setFiltros(DTOFiltrosApartadoFolioHelper filtros) {
		this.filtros = filtros;
	}

	/**
	 * @return valor de tipo PaginarLazy<DTOApartadoNumDocEntity,DTOFiltrosApartadoFolioHelper> que esta contenido en la variable listaFoliosApartadosLazy
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public PaginarLazy<DTOApartadoNumDocEntity, DTOFiltrosApartadoFolioHelper> getListaFoliosApartadosLazy() {
		return listaFoliosApartadosLazy;
	}

	/**
	 * @param listaFoliosApartadosLazy : valor que se ingresa a la variable de tipo PaginarLazy<DTOApartadoNumDocEntity,DTOFiltrosApartadoFolioHelper>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public void setListaFoliosApartadosLazy(
			PaginarLazy<DTOApartadoNumDocEntity, DTOFiltrosApartadoFolioHelper> listaFoliosApartadosLazy) {
		this.listaFoliosApartadosLazy = listaFoliosApartadosLazy;
	}

	/**
	 * @return valor de tipo DTOApartadoNumDocEntity que esta contenido en la variable folioParaEliminar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public DTOApartadoNumDocEntity getFolioParaEliminar() {
		return folioParaEliminar;
	}

	/**
	 * @param folioParaEliminar : valor que se ingresa a la variable de tipo DTOApartadoNumDocEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public void setFolioParaEliminar(DTOApartadoNumDocEntity folioParaEliminar) {
		this.folioParaEliminar = folioParaEliminar;
	}

}
