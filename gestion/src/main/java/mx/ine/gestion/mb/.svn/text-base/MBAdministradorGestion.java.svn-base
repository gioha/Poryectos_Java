/**
 * @(#)MBAdministradorGestion.java 21/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDAdministradorGestionInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.helper.DTOMenuHelper;
import mx.ine.gestion.vh.inter.VHAdministradorGestionInterface;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Managebean encargado del sistema en general, esta clase esta a nivel sesión, administra
 * los procesos generales que el usuario necesita mientras este logueado.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 08/07/2017
 */
public class MBAdministradorGestion implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = -4680796915239430091L;
	
	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBAdministradorGestion.class);
	
	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Lista que contiene la opciones del menú que se utilizan en el sistema.
	 */
	private List<DTOMenuHelper> menuGestion;
	
	/**
	 * Variable para saber que opción del menú esta seleccionado. 
	 */
	private Integer menuSelecionado;

	/**
	 * Valor para saber si se muestra el menú o no
	 */
	private String noMostrarMenu;

	@Autowired
	@Qualifier("bsdAdministradorGestion")
	private transient BSDAdministradorGestionInterface bsdAdministradorGestionInterface;

	@Autowired
	@Qualifier("vhAdministradorGestion")
	private transient VHAdministradorGestionInterface vhAdministradorGestionInterface;

	/* ------------------------------------------ Métodos --------------------------------------- */

	/**
	 * Método que obtiene los valores con los cuales se le relaciona al usuario que se logueo
	 * con los ya registrados en el sistema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void cargarValoresRegistradoEnGestionParaUsuarioLogueado() {

		try {
			
			this.bsdAdministradorGestionInterface.cargaInformacionGestionEnUsuario();

		} catch (Exception e) {

			log.error("<=================== Error al cargar la información al entrar al sistema");
			log.error("<=================== Clase: MBAdministradorGestion , Método: cargaValoresRegistradoEnGestionParaUsuarioLogueado");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
	}
	
	/**
	 * Método que hace la llamada para obtener el menú que se visualizara en el sistema de gestión
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/09/2017
	 */
	public void obtenerMenuGestion() {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (usuarioLogueado.getUsuarioProblemasEnRegistroCuenta()) {

			this.noMostrarMenu = "NO";
			return;
		}
		
		try {
			
			menuGestion = this.bsdAdministradorGestionInterface.obtenMenuGestion(null);
			
		} catch (Exception e) {
			
			log.error("<=================== Clase: MBAdministradorGestion , Método: obtenMenuGestion");
			log.error("<=================== ocurrio un error al obtener la información DEL MENÚ!!! del webservices");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("",e);
		}
	}

	/**
	 * Método que procesa según el rol y su información que tiene el usuario en que pantalla debe iniciar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/10/2017
	 */
	public void validarPantallaInicioSegunRol() {
		
		String ruta = this.vhAdministradorGestionInterface.obtenerPantallaInicioSegunRol();
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		if (!usuarioLogueado.getUsuarioAmbosRoles()) {
			
			this.obtenerMenuGestion();
		}

		try {

			FacesContext.getCurrentInstance().getExternalContext().redirect(ruta);

		} catch (IOException e) {

			log.error("<=================== Clase: MBAdministradorGestion , Método: validarPantallaInicioSegunRol");
			log.error("<=================== ocurrio un error al obtener la pantalla con la que inicia el usuario");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("<=================== RUTA OBTENIDA: " + ruta);
			log.error("",e);
		}
	}

	/**
	 * Método que procesa el menú para un usuario con dos roles (oficilia & otro)
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/11/2017
	 */
	public void cargarMenuParaUsuarioDobleRol(String tipoPerfil) {
	
		try {
			
			this.menuGestion = this.bsdAdministradorGestionInterface.obtenMenuGestion(tipoPerfil);
			String rutaPantallaInicio = this.vhAdministradorGestionInterface.obtenerPantallaInicioMasDeUnRol(tipoPerfil);
			this.noMostrarMenu = "NO";
			

			FacesContext.getCurrentInstance().getExternalContext().redirect(rutaPantallaInicio);

		} catch (Exception e) {

			log.error("<=================== Clase: MBAdministradorGestion , Método: cargarMenuParaUsuarioDobleRol");
			log.error("<=================== ocurrio un error al obtener la pantalla con la que inicia el usuario");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("",e);
		}
	}

	/**
	 * Método para obtener las notificaciones del menú para SEGUIMIENTO
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public void consultarNotificacionesSeguimiento() {
		
		this.bsdAdministradorGestionInterface.obtenNotificacionesMenuSeguimiento(this.menuGestion);
	}

	/**
	 * Método para obtener las notificaciones del menú para OFICIALIA 
	 * 
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public void consultarNotificacionesOficialia() {

		this.bsdAdministradorGestionInterface.obtenNotificacionesMenuOficilia(this.menuGestion);
	}

	/* ------------------------------------- Getters y Setters ------------------------------------ */

	/**
	 * @return valor de tipo Integer que esta contenido en la variable menuSelecionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public Integer getMenuSelecionado() {
		return menuSelecionado;
	}

	/**
	 * @param menuSelecionado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public void setMenuSelecionado(Integer menuSelecionado) {
		this.menuSelecionado = menuSelecionado;
	}

	/**
	 * @return valor de tipo List<DTOMenuHelper> que esta contenido en la variable menuGestion
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public List<DTOMenuHelper> getMenuGestion() {
		return menuGestion;
	}

	/**
	 * @param menuGestion : valor que se ingresa a la variable de tipo List<DTOMenuHelper>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public void setMenuGestion(List<DTOMenuHelper> menuGestion) {
		this.menuGestion = menuGestion;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable noMostrarMenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 09/11/2017
	 */
	public String getNoMostrarMenu() {
		return noMostrarMenu;
	}

	/**
	 * @param noMostrarMenu : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 09/11/2017
	 */
	public void setNoMostrarMenu(String noMostrarMenu) {
		this.noMostrarMenu = noMostrarMenu;
	}

}
