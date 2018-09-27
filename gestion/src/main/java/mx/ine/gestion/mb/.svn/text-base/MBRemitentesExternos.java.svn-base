/**
 * @(#)MBRemitentesExternos.java 03/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import mx.ine.gestion.bsd.inter.BSDRemitentesExternosInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

/**
 * Clase que recibe todas las peticiones que se ejecutan en la vista de Remitentes Externos.
 * @author José Miguel Ortiz
 * @since 03/04/2018
 */
public class MBRemitentesExternos implements Serializable {
	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 8981190882034981535L;

	@Autowired
	@Qualifier("bsdRemitentesExternos")
	private transient BSDRemitentesExternosInterface bsdRemitentesExt;

	/* ------------------------------ Atributos generales ------------------------------ */
	/**
	 * Atributo que contiene el objetos del usaurio logeado
	 */
	private DTOUsuarioLogin usuarioLogueado;

	/**
	 * Atributo que contiene una lista actualizada de áreas
	 */
	private List<DTOCAreaEntity> listaAreas;

	/**
	 * Atributo que contiene una lista actualizada de remitentes externos
	 */
	private List<DTORemitentesExternosOfEntity> listaRemitentesExt;

	/**
	 * Atributo que contiene una lista de remitentes externos eliminables
	 */
	private List<DTORemitentesExternosOfEntity> listaRemitentesExtElim;

	/**
	 * Atributo que contiene una información relativa al dtoRemitentesExternosOf (introducida y/o/seleccionada por el usuario)
	 */
	private DTORemitentesExternosOfEntity dtoRemitenteExt;

	/**
	 * Atributo que contiene una lista de remitentes externos ya asignados
	 */
	private List<Integer> listaRemitentesExtAsig;

	/**
	 * Método de carga inicial una vez que el usuario ingresa a la vista de Remitentes Externos.
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public void init() {
		listaRemitentesExt = new ArrayList<DTORemitentesExternosOfEntity>();
		listaRemitentesExtElim = new ArrayList<DTORemitentesExternosOfEntity>();
		dtoRemitenteExt = new DTORemitentesExternosOfEntity();

		usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		listaAreas = bsdRemitentesExt.recuperarAreas(usuarioLogueado.getRol(), usuarioLogueado.getIdOficialia());

		//1. Caso en que el usuario logueado sólo tiene un área asociada a su oficialía
		if(listaAreas.size() == 1) {
			dtoRemitenteExt.setIdArea(listaAreas.get(0).getIdArea());
			dtoRemitenteExt.setTipoArea(listaAreas.get(0).getTipoArea());
			listaRemitentesExt = bsdRemitentesExt.recuperarRemitentesExternos(listaAreas.get(0).getIdArea(), listaAreas.get(0).getTipoArea());
			listaRemitentesExtAsig = bsdRemitentesExt.recuperarRemitentesExtAsignados(listaAreas.get(0).getIdArea());
		}
	}

	/**
	 * Método encargado de agregar un remitente a la lista de remitentes externos visualizada en la
	 * vista de Remitentes Externos.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public void agregarRemitente() {
		// 1. Validación de campos
		String msjValidacion = bsdRemitentesExt.validarCamposRemitente(dtoRemitenteExt, listaRemitentesExt);
		Integer idAreaAux, idTipoAreaAux;

		// 2. Adición del remitente a la lista de remitentes externos conservando el área elegida
		if(msjValidacion.isEmpty()) {
			idAreaAux = dtoRemitenteExt.getIdArea();
			idTipoAreaAux = dtoRemitenteExt.getTipoArea();
			listaRemitentesExt.add(dtoRemitenteExt);

			dtoRemitenteExt = new DTORemitentesExternosOfEntity();
			dtoRemitenteExt.setIdArea(idAreaAux);
			dtoRemitenteExt.setTipoArea(idTipoAreaAux);
			return;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjValidacion, ""));
			return;			
		}
		
	}

	/**
	 * Método encargado de eliminar a un remitente de la lista de remitentes externos visualizada en la
	 * vista de Remitentes Externos.
	 * @param DTORemitentesExternosOfEntity remitenteExt
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public void eliminarRemitenteExt(DTORemitentesExternosOfEntity remitenteExt) {
		// 1. Si el remitente venía de la BD, lo agrega a una lista auxiliar de remitentes
		if(remitenteExt.getIdRemitente() != null) {
			listaRemitentesExtElim.add(remitenteExt);
		}
		listaRemitentesExt.remove(remitenteExt);
	}

	/**
	 * Método encargado responder a la edición de un remitente externo.
	 * @param DTORemitentesExternosOfEntity remitenteExt
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 03/05/2018
	 */
	public void editarRemitenteExt(DTORemitentesExternosOfEntity remitenteExt) {
		if(remitenteExt.getIdRemitente() != null) {
			if(bsdRemitentesExt.recuperarRemitentePorId(remitenteExt.getIdRemitente(), remitenteExt.getIdArea(),
														remitenteExt.getTipoArea()) == null) {
//				listaRemitentesExtClone.remove(remitenteExt);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia. El remitente editado ya ha sido eliminado por otro usuario.", ""));
			}
		}
	}

	/**
	 * Método encargado de validar si existe un registro de Remitente externo por agregar.
	 * @return boolean
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public boolean tieneRemitenteExt() {
		if((dtoRemitenteExt.getNombreRemitente() != null || dtoRemitenteExt.getNombreRemitente() == "") &&
		   (dtoRemitenteExt.getDependencia() != null || dtoRemitenteExt.getDependencia() == "") &&
		   !dtoRemitenteExt.getIdArea().equals(0)) {
			return true;
		}

		return false;
	}

	/**
	 * Método encargado de cargar una lista de remitentes externos a la BD. Dicha lista proviene de la
	 * vista de Remitentes Externos.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public void cargarRemitentesExt() {
		// 1. Los campos de captura no se encuentran vacíos
		if(dtoRemitenteExt.getNombreRemitente() != "" && dtoRemitenteExt.getDependencia() != "" && !dtoRemitenteExt.getIdArea().equals(0)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha agregado el remitente capturado.", ""));
		} else {
			// 2. La lista de remitentes externos no se encuentra vacía
			if(listaRemitentesExt.size() > 0) {
				bsdRemitentesExt.cargarRemitentesExt(listaRemitentesExt, listaRemitentesExtElim, dtoRemitenteExt);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Los Remitentes externos se han guardado correctamente", ""));
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay remitentes externos que cargar", ""));
			}
		}
	}

	/**
	 * Método encargado de recuperar una lista de remitentes externos desde la BD. Dicha lista es
	 * desplegada en la vista de Remitentes Externos.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public void recuperarRemitentesExt(String tipoMovimiento) {
		// 1. El usuario ha seleccionado un área inválida
		if(dtoRemitenteExt.getIdArea().equals(0)) {
			listaRemitentesExt = new ArrayList<DTORemitentesExternosOfEntity>();
			listaRemitentesExtElim = new ArrayList<DTORemitentesExternosOfEntity>();
		}

		// 2. El usuario ha seleccionado un área válida
		for(DTOCAreaEntity dtoAreaOf : listaAreas) {
			if(dtoAreaOf.getIdArea().equals(dtoRemitenteExt.getIdArea())) {
				dtoRemitenteExt.setTipoArea(dtoAreaOf.getTipoArea());
				listaRemitentesExt = bsdRemitentesExt.recuperarRemitentesExternos(dtoAreaOf.getIdArea(), dtoAreaOf.getTipoArea());
				listaRemitentesExtAsig = bsdRemitentesExt.recuperarRemitentesExtAsignados(dtoAreaOf.getIdArea());
				listaRemitentesExtElim = new ArrayList<DTORemitentesExternosOfEntity>();
				break;
			}
		}

		// 3. El usuario ha cancelado la captura
		if(tipoMovimiento.equals("Cancelar")) {
			Integer idAreaAux = dtoRemitenteExt.getIdArea();
			dtoRemitenteExt = new DTORemitentesExternosOfEntity();
			dtoRemitenteExt.setIdArea(idAreaAux);
		}
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public List<DTOCAreaEntity> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<DTOCAreaEntity> listaAreas) {
		this.listaAreas = listaAreas;
	}

	public List<DTORemitentesExternosOfEntity> getListaRemitentesExt() {
		return listaRemitentesExt;
	}

	public void setListaRemitentesExt(List<DTORemitentesExternosOfEntity> listaRemitentesExt) {
		this.listaRemitentesExt = listaRemitentesExt;
	}

	public DTORemitentesExternosOfEntity getDtoRemitenteExt() {
		return dtoRemitenteExt;
	}

	public void setDtoRemitenteExt(DTORemitentesExternosOfEntity dtoRemitenteExt) {
		this.dtoRemitenteExt = dtoRemitenteExt;
	}

	public List<Integer> getListaRemitentesExtAsig() {
		return listaRemitentesExtAsig;
	}

	public void setListaRemitentesExtAsig(List<Integer> listaRemitentesExtAsig) {
		this.listaRemitentesExtAsig = listaRemitentesExtAsig;
	}

}
