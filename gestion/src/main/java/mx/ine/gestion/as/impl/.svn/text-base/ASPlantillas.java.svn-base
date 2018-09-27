/**
 * @(#)ASPlantillas.java 05/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASPlantillasInterface;
import mx.ine.gestion.bo.inter.BOPlantillasInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOPlantillaInterface;
import mx.ine.gestion.dao.inter.DAOPlantillasInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO de las plantillas de documento del sistema de Gestión
 *
 * @author Luis Urrutia
 * @since 05/12/2017
 */
@Service("asPlantillas")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class ASPlantillas implements ASPlantillasInterface {

	@Autowired
	@Qualifier("daoPlantillas")
	private DAOPlantillasInterface daoPlantillas;

	@Autowired
	@Qualifier("daoPlantilla")
	private DAOPlantillaInterface daoPlantillaInterface;
	
	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daocTipoAreaInterface;
	
	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daocAreasInterface;

	@Autowired
	@Qualifier("boPlantillas")
	private BOPlantillasInterface boPlantillasInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#obtenerTipoDocumentos()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> obtenerTipoDocumentos(){
		return daoPlantillas.obtenerTipoDocumentos();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#obtenerAcronimos(mx.ine.gestion.dto.helper.DTOPlantillaHelper)
	 */
	@Override
	public List<DTOAcronimoEntity> obtenerAcronimos(DTOFiltrosPlantillaHelper form){
		return daoPlantillas.obtenerAcronimos(form);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#consultarPlantillasLazy(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOPlantillaEntity> consultarPlantillasLazy(DTOFiltrosPlantillaHelper filtros, 
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		return daoPlantillas.consultarPlantillasLazy(filtros, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosColumna);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#consultarTotalPlantillasLazy(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public Integer consultarTotalPlantillasLazy(DTOFiltrosPlantillaHelper filtros) {

		return daoPlantillas.consultarTotalPlantillasLazy(filtros);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#validarNumeroPlantillas(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public Boolean validarNumeroPlantillas(DTOFiltrosPlantillaHelper filtros) {

		DTOAcronimoID idAcronimo = new DTOAcronimoID();
		idAcronimo.setIdArea(filtros.getIdArea());
		idAcronimo.setTipoArea(filtros.getTipoArea());
		idAcronimo.setIdTipoDocumento(filtros.getIdTipoDocumentoSeleccionado());
		idAcronimo.setIdAcronimo(filtros.getAcronimoSeleccionado().getIdAcronimo());
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());		
		
		List<DTOPlantillaEntity> plantillas = daoPlantillaInterface.consultarPlantillasXPersonaXAcronimo(idAcronimo, usuarioLogueado.getIdPersona());
		
		return plantillas.size() > 1 ? true : false;
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#agregarPlantilla(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void agregarPlantilla(DTOFiltrosPlantillaHelper filtros) throws Exception {
		
		//1.- Insertamos en la base de datos la plantilla
		this.daoPlantillaInterface.insertarPlantilla(filtros);
		
		//2.- Obtenemos ese registro porque necesitaremos el id_plantilla
		DTOPlantillaEntity plantilla = this.daoPlantillaInterface.consultarUltimaPlantillaXAcronimoXUsuario(
				filtros.getIdArea(), filtros.getTipoArea(), filtros.getIdTipoDocumentoSeleccionado(), filtros.getAcronimoSeleccionado().getIdAcronimo());
		
		//2.- Copiamos la plantilla temporal a la carpeta correcta y la renombramos
		this.boPlantillasInterface.guardarPlantilla(filtros, plantilla.getIdPlantilla());
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASPlantillasInterface#eliminarPlantilla()
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminarPlantilla(DTOPlantillaEntity plantilla) throws Exception{
		
		//1.- Se elimina el registro
		this.daoPlantillas.eliminar(plantilla);

		//2.- Se elimina la plantilla del gluster
		this.boPlantillasInterface.eliminarPlantilla(plantilla);
	}
}
