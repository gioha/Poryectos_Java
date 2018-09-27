/**
 * @(#)MBBandejaComun.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;

import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * Managebean encargado encargado del módulo de la Bandeja de Recepción (Menu)
 * 
 * @author Homero Fidel Villanueva
 * @since 01/10/2017
 *
 */
public class MBBandejaMenu implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 7490432562079305763L;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaSeguimiento")
	private transient BSDBandejaSeguimientoInterface bsdBandejaSeguimientoInterface;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBorradorDocumentos")
	private transient BSDBandejaBorradoresInterface bsdBorradorDocumentosInterface;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger
			.getLogger(MBBandejaBorradores.class);

	/**
	 * Objeto donde se guarda la información que contiene la tabla de
	 * "ESTRUCTURA_AREAS" del usuario que inició sesión.
	 */
	private DTOEstructuraAreasEntity usuario;

	/**
	 * Atributo en donde se indica que opción del menú se ha seleccionado.
	 */
	private String menuBandejaSeleccionado;
	/**
	 * Atributo que guarda la cadena que serÃ¡ mostrada en el apartado del menÃº
	 * con su contador
	 */
	private Integer contBandeja;

	/**
	 * Atributo que guarda la cadena que serÃ¡ mostrada en el apartado del menÃº
	 * con su contador
	 */
	private Integer contEnviados;

	/**
	 * Atributo que guarda la cadena que será mostrada en el apartado del menú
	 * con su contador
	 */
	private Integer contBorradores;

	/**
	 * Atributo que guarda la cadena que será mostrada en el apartado del menú
	 * con su contador
	 */
	private Integer contHistorico;

	// ------------------------ Métodos ------------------------ //

	/**
	 * Método en el cual se incializan las variables utilzadas en el menú de
	 * Bandeja de Seguimiento.
	 * 
	 * @param opcionMenu
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void iniciar(String opcionMenu) {
		setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		
		seleccionarMenu(opcionMenu);
	}
	
	/**
	 * Método en el cual se determina cual será el la vista mostrada en la
	 * pantalla de Bandeja de Seguimiento.
	 * 
	 * @param opcionMenu
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarMenu(String opcionMenu){
		if (opcionMenu.equals(Utilidades.mensajeProperties("vista_entrada"))) {
			seleccionarEntrada();
		} else if (opcionMenu.equals(Utilidades.mensajeProperties("vista_borradores"))) {
			seleccionarBorradores();
		} else if (opcionMenu.equals(Utilidades.mensajeProperties("vista_enviados"))) {
			seleccionarEnviados();
		} else if (opcionMenu.equals(Utilidades.mensajeProperties("vista_historico"))) {
			seleccionarHistorico();
		}else {
			seleccionarEntrada();
		}
	}

	/**
	 * Método que selecciona la vista de Bandeja de Entrada en la pantalla de
	 * Bandeja de Seguimiento y actualiza los contadores del menú de la Bandeja
	 * de Seguimiento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarEntrada() {
		menuBandejaSeleccionado = Utilidades.mensajeProperties("vista_entrada");
		consultarMenu();
	}

	/**
	 * Método que selecciona la vista de Bandeja de Enviados en la pantalla de
	 * Bandeja de Seguimiento y actualiza los contadores del menú de la Bandeja
	 * de Seguimiento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarEnviados() {
		menuBandejaSeleccionado = Utilidades
				.mensajeProperties("vista_enviados");
		consultarMenu();
	}
	/**
	 * Método que selecciona la vista de Bandeja de Borradores en la pantalla de
	 * Bandeja de Seguimiento y actualiza los contadores del menú de la Bandeja
	 * de Seguimiento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarBorradores() {
		menuBandejaSeleccionado = Utilidades
				.mensajeProperties("vista_borradores");
		consultarMenu();
	}
	
	/**
	 * Método que selecciona la vista de Bandeja de Borradores en la pantalla de
	 * Bandeja de Seguimiento y actualiza los contadores del menú de la Bandeja
	 * de Seguimiento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarHistorico() {
		menuBandejaSeleccionado = Utilidades
				.mensajeProperties("vista_historico");
		consultarMenu();
	}

	/**
	 * Método que actualiza los contadores del menú de la Bandeja de
	 * Seguimiento.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void consultarMenu() {

		contBandeja = bsdBandejaSeguimientoInterface.consultarNotificacionesBEntrada(usuario);

		contEnviados = bsdBandejaSeguimientoInterface.consultarNotificacionesBEnviados(usuario);

		contBorradores = bsdBandejaSeguimientoInterface.consultarNotificacionesBBorradores(usuario);

		//contHistorico = bsdBorradorDocumentosInterface.consultarNumeroHistoricos(usuario);
	}

	// ------------------- GETTERS & SETTERS -------------------- //
	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         menuBandejaSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public String getMenuBandejaSeleccionado() {
		return menuBandejaSeleccionado;
	}

	/**
	 * @param menuBandejaSeleccionado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setMenuBandejaSeleccionado(String menuBandejaSeleccionado) {
		this.menuBandejaSeleccionado = menuBandejaSeleccionado;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public DTOEstructuraAreasEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public void setUsuario(DTOEstructuraAreasEntity usuario) {
		this.usuario = usuario;
	}

	public void setUsuario(String usuario) {

		this.usuario = bsdBandejaSeguimientoInterface
				.consultarPersonaXCuenta(usuario);
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         contBandeja
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getContBandeja() {
		return contBandeja;
	}

	/**
	 * @param contBandeja
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setContBandeja(Integer contBandeja) {
		this.contBandeja = contBandeja;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         contEnviados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getContEnviados() {
		return contEnviados;
	}

	/**
	 * @param contEnviados
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setContEnviados(Integer contEnviados) {
		this.contEnviados = contEnviados;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         contBorradores
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getContBorradores() {
		return contBorradores;
	}

	/**
	 * @param contBorradores
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setContBorradores(Integer contBorradores) {
		this.contBorradores = contBorradores;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         contHistorico
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getContHistorico() {
		return contHistorico;
	}

	/**
	 * @param contHistorico
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setContHistorico(Integer contHistorico) {
		this.contHistorico = contHistorico;
	}
}
