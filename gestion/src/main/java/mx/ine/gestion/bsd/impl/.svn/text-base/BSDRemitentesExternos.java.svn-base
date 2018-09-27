/**
 * @(#)BSDRemitentesExternos.java 03/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASRemitentesExternosInterface;
import mx.ine.gestion.bsd.inter.BSDRemitentesExternosInterface;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de implementar los métodos declarados en la clase BSDRemitentesExternosInterface.
 * @author José Miguel Ortiz
 * @since 03/04/2018
 */
@Component("bsdRemitentesExternos")
@Scope("prototype")
public class BSDRemitentesExternos implements BSDRemitentesExternosInterface {
	@Autowired
	@Qualifier("asRemitentesExternos")
	private transient ASRemitentesExternosInterface asRemitentesExt;

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDRemitentesExternosInterface#recuperarAreas(String rolUsuario, Integer idOficialia)
	 */
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia) {
		return asRemitentesExt.recuperarAreas(rolUsuario, idOficialia);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDRemitentesExternosInterface#recuperarRemitentesExternos(DTOCAreaEntity dtoAreaOf)
	 */
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExternos(Integer idArea, Integer tipoArea) {
		return asRemitentesExt.recuperarRemitentesExternos(idArea, tipoArea);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDRemitentesExternosInterface#validarCamposRemitente(DTORemitentesExternosOfEntity dtoRemitenteExt, List<DTORemitentesExternosOfEntity> listaRemitentesExt)
	 */
	public String validarCamposRemitente(DTORemitentesExternosOfEntity dtoRemitenteExt, List<DTORemitentesExternosOfEntity> listaRemitentesExt) {
		if(dtoRemitenteExt.getNombreRemitente().isEmpty()) {
			return "Debes introducir un nombre del remitente";
		}
		if(dtoRemitenteExt.getDependencia().isEmpty()) {
			return "Debes introducir una dependencia del remitente";
		}
		if(dtoRemitenteExt.getIdArea().equals(0)) {
			return "Debes seleccionar un área";
		}

		for(DTORemitentesExternosOfEntity remitenteExt : listaRemitentesExt) {
			if(dtoRemitenteExt.getNombreRemitente().equals(remitenteExt.getNombreRemitente()) &&
			   dtoRemitenteExt.getDependencia().equals(remitenteExt.getDependencia())) {
				return "El remitente externo ya ha sido registrado";
			}
		}

		return "";
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDRemitentesExternosInterface#cargarRemitentesExt(List<DTORemitentesExternosOfEntity> listaRemitentesExt, List<DTORemitentesExternosOfEntity> listaRemitentesExtAux)
	 */
	public void cargarRemitentesExt(List<DTORemitentesExternosOfEntity> listaRemitentesExt, List<DTORemitentesExternosOfEntity> listaRemitentesExtElim, DTORemitentesExternosOfEntity dtoRemitenteExt) {
		asRemitentesExt.cargarRemitentesExt(listaRemitentesExt, listaRemitentesExtElim, dtoRemitenteExt);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDRemitentesExternosInterface#recuperarRemitentesExtAsignados(Integer idArea)
	 */
	public List<Integer> recuperarRemitentesExtAsignados(Integer idArea) {
		return asRemitentesExt.recuperarRemitentesExtAsignados(idArea);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDRemitentesExternosInterface#recuperarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea)
	 */
	public DTORemitentesExternosOfEntity recuperarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea) {
		return asRemitentesExt.recuperarRemitentePorId(idRemitente, idArea, tipoArea);
	}

}
