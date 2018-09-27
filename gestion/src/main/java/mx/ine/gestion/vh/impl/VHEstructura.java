package mx.ine.gestion.vh.impl;

/**
 * @(#)VHPersonal.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.mb.MBEstructura;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHEstructuraInterface;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que tendran la lógica y procedimientos del módulo Personal.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 */

@Component("vhEstructura")
@Scope("prototype")
public class VHEstructura implements VHEstructuraInterface {

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBEstructura.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#inicializarArbol()
	 */
	public TreeNode inicializarArbol() {
		DTOJerarquiaPersonasEntity raiz = new DTOJerarquiaPersonasEntity(
				Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		DTOEstructuraAreasEntity raizPersona = new DTOEstructuraAreasEntity(
				"root", "root", null, null, null, "root", null);
		raiz.setDtoEstructuraAreas(raizPersona);
		TreeNode root = new DefaultTreeNode("root", raiz, null);
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#eliminarNodoArbol(org.
	 * primefaces.model.TreeNode, org.primefaces.model.TreeNode)
	 */
	public boolean eliminarNodoArbol(TreeNode nodoRaiz, TreeNode nodoBorrar) {

		if (nodoRaiz.getChildren().remove(nodoBorrar)) {
			return true;
		} else {
			for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
				boolean encontrado = false;
				if (nodoHijo.getChildCount() > 0) {
					encontrado = eliminarNodoArbol(nodoHijo, nodoBorrar);
					if (encontrado == true) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarNodoPorType(org.
	 * primefaces.model.TreeNode, java.lang.String)
	 */
	@Override
	public TreeNode buscarNodoPorCuentas(TreeNode nodoRaiz,
			String cuentaPersona, String cuentaPadre) {
		if (nodoRaiz != null) {

			DTOJerarquiaPersonasEntity relacion = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			boolean igual = true;
			if (cuentaPersona == null) {
				if (!(relacion.getCuentaPersona() == null)) {
					igual = false;
				}
			} else if (!cuentaPersona.equals(relacion.getCuentaPersona())) {
				igual = false;
			}
			if (cuentaPadre == null) {
				if (!(relacion.getCuentaPadre() == null)) {
					igual = false;
				}
			} else if (!cuentaPadre.equals(relacion.getCuentaPadre())) {
				igual = false;
			}
			if (igual) {
				return nodoRaiz;
			} else {
				TreeNode nodoEncontrado = null;
				for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
					nodoEncontrado = buscarNodoPorCuentas(nodoHijo,
							cuentaPersona, cuentaPadre);
					if (nodoEncontrado != null) {
						return nodoEncontrado;
					}
				}
				return nodoEncontrado;
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarNodoPorType(org.
	 * primefaces.model.TreeNode, java.lang.String)
	 */
	@Override
	public TreeNode buscarNodoPorCuenta(TreeNode nodoRaiz, String cuentaPersona) {
		if (nodoRaiz != null) {

			DTOJerarquiaPersonasEntity relacion = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			boolean igual = true;
			if (cuentaPersona == null) {
				if (!(relacion.getCuentaPersona() == null)) {
					igual = false;
				}
			} else if (!cuentaPersona.equals(relacion.getCuentaPersona())) {
				igual = false;
			}
			if (igual) {
				return nodoRaiz;
			} else {
				TreeNode nodoEncontrado = null;
				for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
					nodoEncontrado = buscarNodoPorCuenta(nodoHijo,
							cuentaPersona);
					if (nodoEncontrado != null) {
						return nodoEncontrado;
					}
				}
				return nodoEncontrado;
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarNodoTitular(org.primefaces.model.TreeNode)
	 */
	@Override
	public TreeNode buscarNodoTitular(TreeNode nodoRaiz) {
		if (nodoRaiz != null) {
			DTOJerarquiaPersonasEntity relacion = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			if (relacion.getIdNivel().intValue() == 1) {
				return nodoRaiz;
			} else {
				TreeNode nodoEncontrado = null;
				for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
					nodoEncontrado = buscarNodoTitular(nodoHijo);
					if (nodoEncontrado != null) {
						return nodoEncontrado;
					}
				}
				return nodoEncontrado;
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#existeOficialiaEnListaPorCuenta
	 * (java.util.List, java.lang.String)
	 */
	@Override
	public boolean existeOficialiaEnListaPorCuenta(
			List<DTOOficialiaEntity> lista, String cuenta) {
		for (DTOOficialiaEntity oficialia : lista) {
			if (oficialia.getCuentaLDAP().equals(cuenta)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#recorrerArbol(org.primefaces
	 * .model.TreeNode)
	 */
	public boolean recorrerArbol(TreeNode nodoRaiz) {
		if (nodoRaiz.getChildCount() == 0) {
			DTOJerarquiaPersonasEntity data = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			log.info("JERARQUIA-> " + data + "\nPERSONA-> "
					+ data.getDtoEstructuraAreas());
			if (nodoRaiz.getParent() != null) {
				DTOJerarquiaPersonasEntity padreData = (DTOJerarquiaPersonasEntity) nodoRaiz
						.getParent().getData();
				log.info("JERARQUIA-> " + padreData + "\nPERSONA-> "
						+ padreData.getDtoEstructuraAreas());
			}
			return true;
		} else {
			for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
				recorrerArbol(nodoHijo);
			}
			DTOJerarquiaPersonasEntity data = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			log.info("JERARQUIA-> " + data + "\nPERSONA-> "
					+ data.getDtoEstructuraAreas());
			if (nodoRaiz.getParent() != null) {
				DTOJerarquiaPersonasEntity padreData = (DTOJerarquiaPersonasEntity) nodoRaiz
						.getParent().getData();
				log.info("JERARQUIA-> " + padreData + "\nPERSONA-> "
						+ padreData.getDtoEstructuraAreas());
			} else {
				log.info("PADRE -> null");
			}
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#crearListasEstructura(org
	 * .primefaces.model.TreeNode, java.util.List, java.util.List)
	 */
	@Override
	public boolean crearListasEstructura(TreeNode nodoRaiz,
			List<DTOEstructuraAreasEntity> listaEstructura,
			List<DTOJerarquiaPersonasEntity> listaJerarquia) {
		if (nodoRaiz.getChildCount() == 0) {
			DTOJerarquiaPersonasEntity data = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			if (data.getIdPersona() != null) {
				if (data.getIdPersona() != 0) {
					listaJerarquia.add(data);
				}
			} else {
				listaJerarquia.add(data);
			}

			DTOEstructuraAreasEntity persona = data.getDtoEstructuraAreas();
			if (!persona.getCuentaLDAP().equals("root")) {
				listaEstructura.add(persona);
			}
			return true;
		} else {
			for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
				crearListasEstructura(nodoHijo, listaEstructura, listaJerarquia);
			}
			DTOJerarquiaPersonasEntity data = (DTOJerarquiaPersonasEntity) nodoRaiz
					.getData();
			if (data.getIdPersona() != null) {
				if (data.getIdPersona() != 0) {
					listaJerarquia.add(data);
				}
			} else {
				listaJerarquia.add(data);
			}
			DTOEstructuraAreasEntity persona = data.getDtoEstructuraAreas();
			if (!persona.getCuentaLDAP().equals("root")) {
				listaEstructura.add(persona);
			}
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#obtenerIdsJerarquia(java
	 * .util.List, java.util.HashMap)
	 */
	@Override
	public void obtenerIdsJerarquia(List<DTOJerarquiaPersonasEntity> lista,
			HashMap<String, Integer> mapa) {
		for (DTOJerarquiaPersonasEntity jerarquia : lista) {
			if (jerarquia.getIdPersona() == null) {
				jerarquia.setIdPersona(mapa.get(jerarquia.getCuentaPersona()));
			}
			if (jerarquia.getIdPersonaPadre() == null) {
				jerarquia
						.setIdPersonaPadre(mapa.get(jerarquia.getCuentaPadre()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#agregarEnNodo(org.primefaces.model.TreeNode, mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity)
	 */
	public void agregarEnNodo(TreeNode nodo, DTOJerarquiaPersonasEntity relacion) {
		TreeNode arbol = new DefaultTreeNode(relacion.getDtoEstructuraAreas()
				.getCuentaLDAP(), relacion, nodo);
		arbol.setExpanded(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#validarCampo(java.lang.
	 * String, java.lang.String, java.lang.String[])
	 */
	public boolean validarCampo(String campo, String nombre, String idCampo,
			String[] validaciones) {
		boolean validado = true;
		for (int i = 0; i < validaciones.length; i++) {
			if (validaciones[i] == "requerido") {
				if (campo.equals("")) {
					mostrarMensajeRequerido(idCampo, "El campo " + nombre
							+ " es requerido.", "");
					validado = false;
				}
			}
		}
		return validado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#mostrarMensajeFalla(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto) {
		if(tipo.equals(Utilidades.mensajeProperties("constante_growl_info"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_advertencia"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_exito"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, texto));			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#mostrarMensajeExito(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public void mostrarMensajeRequerido(String idCampo, String mensaje,
			String nota) {
		FacesContext.getCurrentInstance().addMessage(idCampo,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, nota));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#obtnenerSiglasDeListaAreas
	 * (java.util.List, java.lang.String)
	 */
	public String obtenerSiglasDeListaAreas(List<DTOCAreaEntity> areas,
			Integer idArea) {
		for (DTOCAreaEntity area : areas) {
			if (area.getIdArea().equals(idArea)) {
				return area.getSiglas();
			}
		}
		return "";
	}
	

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#obtenerDescripcionDeEntidades(java.util.List, java.lang.Integer)
	 */
	@Override
	public String obtenerDescripcionDeEntidades(List<DTOEstadosEntity> estados, Integer idEstado) {
		for (DTOEstadosEntity estado : estados) {
			if (estado.getIdEstado().equals(idEstado)) {
				return estado.getNombreEstado();
			}
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#obtenerDescripcionDeListaAreas(java.util.List, java.lang.Integer)
	 */
	@Override
	public String obtenerDescripcionDeListaAreas(List<DTOCAreaEntity> areas, Integer idArea) {
		for (DTOCAreaEntity area : areas) {
			if (area.getIdArea().equals(idArea)) {
				return area.getDescripcion();
			}
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarJerarquiaPersonaPorId(java.util.List, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean buscarJerarquiaPersonaPorId(
			List<DTOJerarquiaPersonasEntity> listaJerarquias,
			Integer idPersona, Integer idPadre) {
		for (DTOJerarquiaPersonasEntity jerarquia : listaJerarquias) {
			if (jerarquia.getIdPersona() != null) {
				if (jerarquia.getIdPersonaPadre() != null) {
					if (jerarquia.getIdPersona().compareTo(idPersona) == 0
							&& jerarquia.getIdPersonaPadre().compareTo(idPadre) == 0) {
						return true;
					}
				} else {
					if (jerarquia.getIdPersona().compareTo(idPersona) == 0) {
						return true;
					}
				}

			}
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarOficialiasAreaPorId(java.util.List, java.lang.Integer)
	 */
	@Override
	public boolean buscarOficialiasAreaPorId(
			List<DTOOficialiasAreasEntity> listaOficialia, Integer idOficialia) {
		for (DTOOficialiasAreasEntity oficialia : listaOficialia) {
			if (oficialia.getIdOficialia() != null) {
				if (oficialia.getIdOficialia().compareTo(idOficialia) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarOficialiaPorId(java.util.List, java.lang.Integer)
	 */
	@Override
	public boolean buscarOficialiaPorId(
			List<DTOOficialiaEntity> listaOficialia, Integer idOficialia) {
		for (DTOOficialiaEntity oficialia : listaOficialia) {
			if (oficialia.getIdOficialia() != null) {
				if (oficialia.getIdOficialia().compareTo(idOficialia) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#procesarListasJerarquias(java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void procesarListasJerarquias(
			List<DTOJerarquiaPersonasEntity> listaAnterior, List<DTOJerarquiaPersonasEntity> listaNueva,
			List<DTOJerarquiaPersonasEntity> listaAgregados,List<DTOJerarquiaPersonasEntity> listaEliminar) {
		for (DTOJerarquiaPersonasEntity nuevo : listaNueva) {
			if (nuevo.getIdPersona() == null) {
				if (nuevo.getIdPersonaPadre() == null) {
					listaAgregados.add(nuevo);
				} else if (nuevo.getIdPersonaPadre().compareTo(0) == 0) {
					listaAgregados.add(nuevo);
				}
			} else if(nuevo.getIdPersonaPadre() == null) {
				listaAgregados.add(nuevo);
			} else if (!buscarJerarquiaPersonaPorId(listaAnterior,
					nuevo.getIdPersona(), nuevo.getIdPersonaPadre())) {
				listaAgregados.add(nuevo);
			} else if (nuevo.getIdPersonaPadre() == null) {
				listaAgregados.add(nuevo);
			}
		}
		for (DTOJerarquiaPersonasEntity anterior : listaAnterior) {

			if (!buscarJerarquiaPersonaPorId(listaNueva,
					anterior.getIdPersona(), anterior.getIdPersonaPadre())) {
				listaEliminar.add(anterior);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#procesarListasOficialias(java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void procesarListasOficialias(
			List<DTOOficialiasAreasEntity> listaAnterior,
			List<DTOOficialiaEntity> listaNueva,
			List<DTOOficialiaEntity> listaAgregados,
			List<DTOOficialiasAreasEntity> listaEliminar) {
		for (DTOOficialiaEntity nuevo : listaNueva) {
			if (nuevo.getIdOficialia() == null) {
				listaAgregados.add(nuevo);
			} else if (!buscarOficialiasAreaPorId(listaAnterior, nuevo.getIdOficialia())) {
				listaAgregados.add(nuevo);
			}
		}
		for (DTOOficialiasAreasEntity anterior : listaAnterior) {
			if (!buscarOficialiaPorId(listaNueva, anterior.getIdOficialia())) {
				listaEliminar.add(anterior);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#buscarAreaPorIdEnLista(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> buscarAreaPorIdEnLista(List<DTOCAreaEntity> listaAreas,
			Integer id) {
		List<DTOCAreaEntity> listaArea = new ArrayList<DTOCAreaEntity>();
		for (DTOCAreaEntity area : listaAreas) {
			if (area.getIdArea().compareTo(id) == 0) {
				listaArea.add(area);
				return listaArea;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#enviarError(java.lang.Exception, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void enviarError(Exception e, String clase, String metodo,
			String mensaje) {
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		log.error("<=================== Clase: " + clase + " , Método: "
				+ metodo);
		if (!mensaje.equals("")) {
			log.error("<=================== " + mensaje);
		}
		if (usuarioLogueado != null) {
			log.error("<=================== USUARIO LOGUEADO: "
					+ usuarioLogueado.getNombreUsuario());
		}
		log.error("", e);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#hacerMatchEntidades(java.util.List, java.util.List)
	 */
	@Override
	public List<DTOEstadosEntity> hacerMatchEntidades(
			List<DTOEstadosEntity> listaEstadosCompleta,
			List<Integer> listaEstadosEnBase) {
		List<DTOEstadosEntity> listaEstadosNueva = new ArrayList<DTOEstadosEntity>();
		for (Integer idEntidad : listaEstadosEnBase) {
			for (DTOEstadosEntity estado : listaEstadosCompleta) {
				if (idEntidad.compareTo(estado.getIdEstado()) == 0) {
					listaEstadosNueva.add(estado);
					break;
				}
			}
		}
		return listaEstadosNueva;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHEstructuraInterface#validacionesPosCapturaParaAdministrador(java.util.List)
	 */
	@Override
	public void validacionesPosCapturaParaAdministrador(List<DTOEstructuraAreasEntity> listaEstructuras) {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		//1.- Revisamos 1 x 1 las personas que se dieron de alta
		for (DTOEstructuraAreasEntity persona : listaEstructuras) {
		
			//2.- En caso de que entre las personas que se dieron de alta se encuentre el administrador se habilitan los permisos
			if (persona.getCuentaLDAP().equalsIgnoreCase(usuarioLogueado.getUsername())) {
				
				usuarioLogueado.setUsuarioNoRegistradoEnGestion(false);
				usuarioLogueado.setUsuarioAmbosRoles(false);
				usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
				usuarioLogueado.setPuedeApartarFolios(true);
				usuarioLogueado.setIdArea(persona.getIdArea());
				usuarioLogueado.setTipoArea(persona.getTipoArea());
				usuarioLogueado.setIdPersona(persona.getIdPersona());
				
				break;
			}
		}
	}
}
