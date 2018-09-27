package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASCapturaCorresponsalInterface;
import mx.ine.gestion.dao.inter.DAOCorresponsalInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface;
import mx.ine.gestion.dto.db.DTOCorresponsales;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO de los corresponsales del sistema de Gestión
 *
 * @author Luis Urrutia
 * @since 16/11/2017
 */
@Scope("prototype")
@Service("asCapturaCorresponsal")
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class ASCapturaCorresponsal implements ASCapturaCorresponsalInterface {
	
	@Autowired
	@Qualifier("daoCorresponsal")
	private DAOCorresponsalInterface daoCapturaCorresponsal;

	@Autowired
	@Qualifier("daoJerarquiaPersonas")
	private DAOJerarquiaPersonasInterface daoJerarquiaPersonas;

	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturaCorresponsalInterface#buscarCorresponsales(java.lang.Integer)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> buscarCorresponsales(Integer idPersona) {
		return daoCapturaCorresponsal.buscarCorresponsales(idPersona);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturaCorresponsalInterface#agregarCorresponsal(DTOCorresponsales)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void agregarCorresponsal(DTOCorresponsales corresponsal) throws Exception {
		
		this.daoCapturaCorresponsal.guardar(corresponsal);

		//Validamos si su jefe es titular de área
		DTOJerarquiaPersonasID id = new DTOJerarquiaPersonasID();
		id.setIdPersona(corresponsal.getIdPersonaTi());
		id.setIdPersonaPadre(0);
		
		DTOJerarquiaPersonasEntity jerarquiaTitular = this.daoJerarquiaPersonas.buscarPorId(id);
		
		//Si es titular de área su jefe hay que actualizar la estructura
		if(jerarquiaTitular != null) {
			
			DTOEstructuraAreasEntity personaCorresponsable = this.daoEstructuraAreaInterface.buscarPorId(corresponsal.getIdPersonaCo());
			personaCorresponsable.setVerVersionT(1);
			this.daoEstructuraAreaInterface.modificar(personaCorresponsable);
		}
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturaCorresponsalInterface#eliminarCorresponsal(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminarCorresponsal(Integer idCorresponsal) throws Exception {
		daoCapturaCorresponsal.eliminarCorresponsal(idCorresponsal);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASCapturaCorresponsalInterface#buscarCorresponsalesCandidatos(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> buscarCorresponsalesCandidatos(String nombre, String apellido,
			Integer idPersona, Integer idArea, Integer tipoArea) {
		return daoCapturaCorresponsal.buscarCorresponsalesCandidatos(nombre, apellido, idPersona, idArea, tipoArea);
	}
}
