/**
 * @(#)ASRemitentesExternos.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASRemitentesExternosInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesExtInterface;
import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de gestionar los objetos DAO utilizados para soportar la
 * funcionalidad de la vista de Remitentes Externos.
 * 
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
@Service("asRemitentesExternos")
@Scope("prototype")
public class ASRemitentesExternos implements ASRemitentesExternosInterface {

	@Autowired
	@Qualifier("daoOficialiasAreas")
	private DAOOficialiasAreasInterface daoOficialiaAreas;
	
	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daoCAreas;

	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daoCTipoArea;

	@Autowired
	@Qualifier("daoRemitentesExternosOf")
	private DAORemitentesExternosOfInterface daoRemitentesExt;

	@Autowired
	@Qualifier("daoDocumentosRemitentesExt")
	private DAODocumentosRemitentesExtInterface daoDocsRemitsExt;
	
	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASRemitentesExternosInterface#recuperarAreasPorOficialia(String rolUsuario, Integer idOficialia)
	 */
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	@Override
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia) {
		if(rolUsuario.equals("GESTION4.OFICIALIA.OC")) {
			return daoOficialiaAreas.consultarAreasOficialia(idOficialia);
		} else {
			return daoOficialiaAreas.consultarTodasLasAreasOficialia();
		}
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASRemitentesExternosInterface#recuperarRemitentesExternos(Integer idArea, Integer tipoArea)
	 */
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	@Override
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExternos(Integer idArea, Integer tipoArea) {
		return daoRemitentesExt.consultarRemitente(idArea, tipoArea);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASRemitentesExternosInterface#cargarRemitentesExt(List<DTORemitentesExternosOfEntity> listaRemitentesExt, List<DTORemitentesExternosOfEntity> listaRemitentesExtAux)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void cargarRemitentesExt(List<DTORemitentesExternosOfEntity> listaRemitentesExt, List<DTORemitentesExternosOfEntity> listaRemitentesExtElim, DTORemitentesExternosOfEntity dtoRemitenteExt) {

		for(DTORemitentesExternosOfEntity remitenteExt : listaRemitentesExt) {
			if(remitenteExt.getIdRemitente() != null) {		// 1. Ha ocurrido una modificaicón a un remitente externo
				daoRemitentesExt.emergencia(remitenteExt);
			} else {	// 2. Ha ocurrido una inserción de un remitente externo
				if(daoRemitentesExt.consultarRemitenteAreaNombreDependenciaExacto(remitenteExt.getIdArea(), remitenteExt.getTipoArea(),
																				  remitenteExt.getNombreRemitente(),
																				  remitenteExt.getDependencia()) == null) {
					Integer idRemitente = daoRemitentesExt.consultarUltimoRemitente(remitenteExt.getIdArea(), remitenteExt.getTipoArea());
					remitenteExt.setIdRemitente(idRemitente == null ? 1 : ++idRemitente);
					daoRemitentesExt.guardar(remitenteExt);
				}
			}
		}

		//	3. Ha ocurrido la eliminación de uno o varios remitentes externos
		for(DTORemitentesExternosOfEntity remitenteExtAux : listaRemitentesExtElim) {
			daoRemitentesExt.emergencia(remitenteExtAux);
			daoRemitentesExt.eliminar(remitenteExtAux);
		}
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASRemitentesExternosInterface#recuperarRemitentesExtAsignados(Integer idArea)
	 */
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	@Override
	public List<Integer> recuperarRemitentesExtAsignados(Integer idArea) {
		return daoDocsRemitsExt.consultarRemitentesExtAsignados(idArea);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASRemitentesExternosInterface#recuperarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea)
	 */
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	@Override
	public DTORemitentesExternosOfEntity recuperarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea) {
		return daoRemitentesExt.consultarRemitentePorId(idRemitente, idArea, tipoArea);
	}
	
}
