/**
 * @(#)BSDEstructuraInterface.java 18/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASEstructuraInterface;
import mx.ine.gestion.bsd.inter.BSDEstructuraInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del módulo Estructura.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 17/08/2017
 */
@Component("bsdEstructura")
@Scope("prototype")
public class BSDEstructura implements BSDEstructuraInterface{

	@Autowired
	@Qualifier("asEstructura")
	private ASEstructuraInterface asEstructuraInterface;
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#buscarPersonalPorNombreApellido(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> buscarEstructuraLDAPPorNombreApellidoArea(String nombre, String apellido, String area, Integer idEstado) throws Exception{
		return asEstructuraInterface.buscarEstructuraLDAPPorNombreApellidoArea(nombre, apellido, area, idEstado);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#buscarPersonalPorNombreApellido(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOOficialiaEntity> buscarOficialiaLDAPPorNombreApellidoArea(String nombre, String apellido, String area, Integer idEstado) throws Exception{
		return asEstructuraInterface.buscarOficialiaLDAPPorNombreApellidoArea(nombre, apellido, area, idEstado);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDJerarquiaPersonasInterface#consultarTitulares()
	 */
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarTitulares() {
		return asEstructuraInterface.consultarTitulares();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarEstructuraPorArea(java.lang.Integer)
	 */
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarEstructuraPorArea(Integer tipoArea, Integer idArea) {
		return asEstructuraInterface.consultarEstructuraPorArea(tipoArea, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarOficialiasPorArea(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOOficialiaEntity> consultarOficialiasPorArea(Integer tipoArea, Integer idArea) {
		return asEstructuraInterface.consultarOficialiasPorArea(tipoArea, idArea);
	}
	
	@Override
	public List<DTOOficialiasAreasEntity> consultarOficialiasAreasPorArea(Integer tipoArea, Integer idArea) {
		return asEstructuraInterface.consultarOficialiasAreasPorArea(tipoArea, idArea);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarPersonaPorCuenta(java.lang.String)
	 */
	@Override
	public DTOEstructuraAreasEntity consultarPersonaPorCuenta(String cuentaLdap){
		return asEstructuraInterface.consultarPersonaPorCuenta(cuentaLdap);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarOficialiaPorCuenta(java.lang.String)
	 */
	@Override
	public DTOOficialiaEntity consultarOficialiaPorCuenta(String cuentaLdap){
		return asEstructuraInterface.consultarOficialiaPorCuenta(cuentaLdap);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarAreasSinOrganigrama(int)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasSinOrganigrama(int tipoArea, int idEntidad) throws Exception {
		return asEstructuraInterface.consultarAreasSinOrganigrama(tipoArea, idEntidad);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarAreasConOrganigrama(int)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasConOrganigrama(int tipoArea, int idEntidad) throws Exception {
		return asEstructuraInterface.consultarAreasConOrganigrama(tipoArea, idEntidad);
	}

	@Override
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad) throws Exception {
		return asEstructuraInterface.consultarAreas(tipoArea, idEntidad);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarArbolEstructura(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public TreeNode consultarArbolEstructura(Integer tipoArea, Integer idArea) {
		return asEstructuraInterface.consultarArbolEstructura(tipoArea, idArea);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarEntidadesConOrganigrama()
	 */
	@Override
	public List<Integer> consultarEntidadesConOrganigrama() throws Exception {
		return asEstructuraInterface.consultarEntidadesConOrganigrama();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarEntidadesSinOrganigrama()
	 */
	@Override
	public List<Integer> consultarEntidadesSinOrganigrama() throws Exception {
		return asEstructuraInterface.consultarEntidadesSinOrganigrama();
	}

	@Override
	public void guardarEstructuraCompleta(Integer idAreaSeleccionada, Integer tipoAreaSeleccionada, 
			List<DTOEstructuraAreasEntity> personas, List<DTOJerarquiaPersonasEntity> listaJerarquiasAgregar, 
			List<DTOJerarquiaPersonasEntity> listaJerarquiasEliminar, List<DTOOficialiaEntity> listaOficialiaAgregar, 
			List<DTOOficialiasAreasEntity> listaOficialiaEliminar) throws Exception {
		asEstructuraInterface.guardarEstructuraCompleta(idAreaSeleccionada, tipoAreaSeleccionada, personas, listaJerarquiasAgregar, listaJerarquiasEliminar, listaOficialiaAgregar, listaOficialiaEliminar);
	
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarExisteEstructura(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean consultarExisteEstructura(Integer tipoArea, Integer idArea) {
		return asEstructuraInterface.consultarExisteEstructura(tipoArea, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarTiposAreas()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTiposAreas() throws Exception {
		return asEstructuraInterface.consultarTiposAreas();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarEstados()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstados() throws Exception {
		return asEstructuraInterface.consultarEstados();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarEstadosSinOrganigrama()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstadosSinOrganigrama() throws Exception {
		return asEstructuraInterface.consultarEstadosSinOrganigrama();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDEstructuraInterface#consultarEstadosConOrganigrama()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstadosConOrganigrama() throws Exception {
		return asEstructuraInterface.consultarEstadosConOrganigrama();
	}
	
}
