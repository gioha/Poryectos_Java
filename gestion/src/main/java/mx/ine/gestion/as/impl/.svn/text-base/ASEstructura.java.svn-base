/**
 * @(#)ASEstructura.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import mx.ine.gestion.as.inter.ASEstructuraInterface;
import mx.ine.gestion.bo.inter.BOEstructuraInterface;
import mx.ine.gestion.dao.impl.DAOLdapEstructura;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOCorresponsalInterface;
import mx.ine.gestion.dao.inter.DAOEstadosInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesOFInterface;
import mx.ine.gestion.dao.inter.DAOOficialiaInterface;
import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.mb.MBEstructura;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.jboss.logging.Logger;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO del módulo Estructura.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 17/08/2017
 */
@Component("asEstructura")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASEstructura implements ASEstructuraInterface{

	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daoCAreas;

	@Autowired
	@Qualifier("daoJerarquiaPersonas")
	private DAOJerarquiaPersonasInterface daoJerarquiaPersonasInterface;

	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;
	
	@Autowired
	@Qualifier("daoOficialia")
	private DAOOficialiaInterface daoOficialiaInterface;
	
	@Autowired
	@Qualifier("daoOficialiasAreas")
	private DAOOficialiasAreasInterface daoOficialiasAreasInterface;
	
	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daoCTiposAreasInterface;
	
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
	@Autowired
	@Qualifier("daoNotificacionesOF")
	private DAONotificacionesOFInterface daoNotificacionesOFInterface;
	
	@Autowired
	@Qualifier("boEstructura")
	private BOEstructuraInterface BOEstructuraInterface;
	
	@Autowired
	@Qualifier("ldapUserSearchByGivenNameSnUid")
	private DAOLdapEstructura ldapSearch;

	@Autowired
	@Qualifier("daoEstados")
	private DAOEstadosInterface daoEstadosInterface;
	
	@Autowired
	@Qualifier("daoCorresponsal")
	private DAOCorresponsalInterface daoCorresponsalInterface;
	
	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBEstructura.class);

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#buscarPersonalPorNombreApellidoArea(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> buscarEstructuraLDAPPorNombreApellidoArea(String nombre, String apellido, String areaNombre, Integer idEstado) throws Exception{
		NamingEnumeration<SearchResult> listaPersonasEncontradas = ldapSearch.searchForUsers(nombre, apellido, areaNombre, idEstado);
		List<DTOEstructuraAreasEntity> listaPersonal = new ArrayList<DTOEstructuraAreasEntity>();
		while (listaPersonasEncontradas.hasMoreElements()) {
			
			SearchResult persona = listaPersonasEncontradas.nextElement();
			Attributes atributosPersona = persona.getAttributes();

			if(atributosPersona.get("personaltitle") != null) {

				String nombrePersonal = atributosPersona.get("givenname").getAll().next().toString();
				String apellidosPersonal = atributosPersona.get("sn").getAll().next().toString();
				String nombreCompleto = atributosPersona.get("cn").getAll().next().toString();
				
				String puesto = atributosPersona.get("personaltitle").getAll().next().toString();
	
				String area = atributosPersona.get("ou").getAll().next().toString();
				String cuentaLdap = atributosPersona.get("uid").getAll().next().toString();

				String genero = "";
				if(atributosPersona.get("curp") != null) {
					genero = atributosPersona.get("curp").getAll().next().toString();
					if(genero.length() >= 18 ) {
						if(genero.substring(10, 11).equals("H") || genero.substring(10, 11).equals("M")) {
							genero = genero.substring(10, 11);
						}
					} else {
						genero = "";
					}
				}
				String tratamiento = null;
				if(atributosPersona.get("tratamiento") != null) {
					String tratamientoAux = atributosPersona.get("tratamiento").getAll().next().toString();
					if(!tratamientoAux.equals("null")) {
						tratamiento = tratamientoAux;
					}
				}
				String extension = null;
				if(atributosPersona.get("telephonenumber") != null) {
					if (atributosPersona.get("telephonenumber").getAll().hasMore()) {
						String extensionAux = atributosPersona.get("telephonenumber").getAll().next().toString();
						if (extensionAux.contains("IP")) {					
							if(extensionAux.length()> 6) {						
								extension = extensionAux.substring(0,6);
							}
						}
					}
				}

				DTOEstructuraAreasEntity empleado = new DTOEstructuraAreasEntity(nombrePersonal, apellidosPersonal, nombreCompleto, puesto, area, cuentaLdap, "");
				empleado.setGenero(genero);
				empleado.setEstatus("SI");
				if (tratamiento != null) {
					empleado.setTratamiento(tratamiento);
				}
				if (extension != null) {
					empleado.setExtensionTel(extension);
				}
				listaPersonal.add(empleado);
			}
		}
		return listaPersonal;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#buscarPersonalPorNombreApellidoArea(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void buscarTodasAreasLDAP(int i) throws Exception{
		
			Map<String, String> map = new HashMap<String, String>();
			NamingEnumeration<SearchResult> listaPersonasEncontradas = ldapSearch.searchForUsers(null, null, null, i);

			while (listaPersonasEncontradas.hasMoreElements()) {
				
				SearchResult persona = listaPersonasEncontradas.nextElement();
				Attributes atributosPersona = persona.getAttributes();
	
				String area = atributosPersona.get("ou").getAll().next().toString();
				if(!map.containsKey(area)) {
					map.put(area, area);
				}
			}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#buscarPersonalPorNombreApellido(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOOficialiaEntity> buscarOficialiaLDAPPorNombreApellidoArea(String nombre, String apellido, String area, Integer idEstado) throws Exception{
		
		NamingEnumeration<SearchResult> listaPersonasEncontradas = ldapSearch.searchForUsers(nombre, apellido, area, idEstado);
		List<DTOOficialiaEntity> listaPersonal = new ArrayList<DTOOficialiaEntity>();

		while (listaPersonasEncontradas.hasMoreElements()) {
			
			
			SearchResult persona = listaPersonasEncontradas.nextElement();
			Attributes atributosPersona = persona.getAttributes();
			
			if(atributosPersona.get("personaltitle") != null) {

				String nombrePersonal = atributosPersona.get("givenname").getAll().next().toString();
				
				String apellidosPersonal = atributosPersona.get("sn").getAll().next().toString();
	
				String puesto = atributosPersona.get("personaltitle").getAll().next().toString();
	
				String areaNombre = atributosPersona.get("ou").getAll().next().toString();
	
				String cuentaLdap = atributosPersona.get("uid").getAll().next().toString();
				String genero = null;
				
				if(atributosPersona.get("curp") != null) {
					genero = atributosPersona.get("curp").getAll().next().toString();
					if(genero.length() >= 18 ) {
						if(genero.substring(10, 11).equals("H") || genero.substring(10, 11).equals("M")) {
							genero = genero.substring(10, 11);
						}
					} else {
						genero = null;
					}
				}
				
				String tratamiento = null;
				if(atributosPersona.get("tratamiento") != null) {
					String tratamientoAux = atributosPersona.get("tratamiento").getAll().next().toString();
					if(!tratamientoAux.equals("null")) {
						tratamiento = tratamientoAux;
					}
				}
				String extension = null;
				if(atributosPersona.get("telephonenumber") != null) {
					if (atributosPersona.get("telephonenumber").getAll().hasMore()) {
						String extensionAux = atributosPersona.get("telephonenumber").getAll().next().toString();
						if (extensionAux.contains("IP")) {					
							if(extensionAux.length()> 6) {						
								extension = extensionAux.substring(0,6);
							}
						}
					}
				}
	
				DTOOficialiaEntity empleado = new DTOOficialiaEntity();
	
				empleado.setNombre(nombrePersonal);
				empleado.setApellidos(apellidosPersonal);
				empleado.setCuentaLDAP(cuentaLdap);
				empleado.setPuesto(puesto);
				empleado.setNombreArea(areaNombre);
				empleado.setGenero(genero);
				empleado.setEstatus("SI");
				if (tratamiento != null) {
					empleado.setTratamiento(tratamiento);
				}
				if (extension != null) {
					empleado.setExtensionTel(extension);
				}
				
				listaPersonal.add(empleado);
			}
		}
		return listaPersonal;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASJerarquiaPersonasInterface#consultarTitulares()
	 */
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarTitulares() {
		return daoJerarquiaPersonasInterface.consultarTitulares();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEstructuraPorArea(java.lang.Integer)
	 */
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarEstructuraPorArea(Integer tipoArea, Integer idArea) {
		return daoJerarquiaPersonasInterface.consultarEstructuraPorArea(tipoArea, idArea);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarArbolEstructura(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public TreeNode consultarArbolEstructura(Integer tipoArea, Integer idArea) {
		List<DTOJerarquiaPersonasEntity> listaJerarquias = daoJerarquiaPersonasInterface.consultarEstructuraPorArea(tipoArea, idArea);
		BOEstructuraInterface.agregarCuentasALista(listaJerarquias);
		return BOEstructuraInterface.listaJerarquiaAArbol(listaJerarquias);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarOficialiasPorArea(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOOficialiaEntity> consultarOficialiasPorArea(Integer tipoArea, Integer idArea) {
		List<DTOOficialiasAreasEntity> listaOficialiasAreas = daoOficialiasAreasInterface.consultarOficialiasPorArea(tipoArea, idArea);
		return BOEstructuraInterface.oficialiasAresaAOficialia(listaOficialiasAreas);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarOficialiasAreasPorArea(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOOficialiasAreasEntity> consultarOficialiasAreasPorArea(Integer tipoArea, Integer idArea) {
		return daoOficialiasAreasInterface.consultarOficialiasPorArea(tipoArea, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarPersonaPorCuenta(java.lang.String)
	 */
	@Override
	public DTOEstructuraAreasEntity consultarPersonaPorCuenta(String cuentaLdap) {
		return daoEstructuraAreaInterface.consultarPersonaXCuenta(cuentaLdap);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarOficialiaPorCuenta(java.lang.String)
	 */
	@Override
	public DTOOficialiaEntity consultarOficialiaPorCuenta(String cuentaLdap) {
		return daoOficialiaInterface.consultarOficialiaXCuenta(cuentaLdap);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#guardarEstructuraCompleta(java.lang.Integer, java.lang.Integer, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void guardarEstructuraCompleta(Integer idAreaSeleccionada, Integer tipoAreaSeleccionada, 
			List<DTOEstructuraAreasEntity> personas, List<DTOJerarquiaPersonasEntity> listaJerarquiasAgregar, 
			List<DTOJerarquiaPersonasEntity> listaJerarquiasEliminar, List<DTOOficialiaEntity> listaOficialiaAgregar, 
			List<DTOOficialiasAreasEntity> listaOficialiaEliminar) throws Exception {

		/// GUARDAR ORGANIGRAMA
		HashMap<String, Integer> mapa = new HashMap<String, Integer>();

		for (DTOEstructuraAreasEntity persona : personas) {
			if(persona.getIdPersona() == null && !mapa.containsKey(persona.getCuentaLDAP())) {
				Integer id = daoEstructuraAreaInterface.guardar(persona);
				List<DTONotificacionesEntity> notificaciones = BOEstructuraInterface.inicializarNotificaciones(id);
				for (DTONotificacionesEntity notificacion : notificaciones) {
					daoNotificacionesInterface.emergencia(notificacion);
				}
				mapa.put(persona.getCuentaLDAP(), id);
			} else {
				if (persona.getEstatus().equals("NO")) {
					persona.setEstatus("SI");
					daoEstructuraAreaInterface.modificar(persona);
				}
				if(!mapa.containsKey(persona.getCuentaLDAP())) {
					mapa.put(persona.getCuentaLDAP(), persona.getIdPersona());
				}
			}
		}

		BOEstructuraInterface.obtenerIdsJerarquia(listaJerarquiasAgregar, mapa);

		Integer idArea = null;
		Integer tipoArea = null;
		
		for (DTOJerarquiaPersonasEntity jerarquia : listaJerarquiasAgregar) {
			if(idArea == null || tipoArea == null) {
				idArea = jerarquia.getIdArea();
				tipoArea = jerarquia.getTipoArea();
 			}
			jerarquia.setDtoEstructuraAreas(null);
			jerarquia.setDtoEstructuraAreasPadre(null);
			daoJerarquiaPersonasInterface.guardar(jerarquia);
		}

		if(listaJerarquiasEliminar != null) {
			for (DTOJerarquiaPersonasEntity jerarquia : listaJerarquiasEliminar) {
				if(idArea == null || tipoArea == null) {
					idArea = jerarquia.getIdArea();
					tipoArea = jerarquia.getTipoArea();
	 			}
				jerarquia.setDtoEstructuraAreas(null);
				jerarquia.setDtoEstructuraAreasPadre(null);
				if (jerarquia.getIdNivel().intValue() == 1 ) {
					daoEstructuraAreaInterface.reiniciarNombreNivelArea(jerarquia.getIdPersona());
				}
				daoJerarquiaPersonasInterface.eliminar(jerarquia);
				daoEstructuraAreaInterface.flush();
				daoEstructuraAreaInterface.actualizarEstatusEstructura(jerarquia.getIdPersona());
				daoCorresponsalInterface.actualizarEstatusCorresponsales(jerarquia.getIdPersona());
			}
		}

		daoEstructuraAreaInterface.flush();
		
		if ((listaJerarquiasEliminar != null && !listaJerarquiasEliminar.isEmpty())  || (listaJerarquiasAgregar != null && !listaJerarquiasAgregar.isEmpty())) {
			daoEstructuraAreaInterface.reiniciarCorresponsalesArea(idArea, tipoArea);
			daoEstructuraAreaInterface.actualizarCorresponsalesArea(idArea, tipoArea);
		}
		/// GUARDAR OFICIALÍAS
		
		List<DTONotificacionesOFEntity> notificaciones = BOEstructuraInterface.inicializarNotificacionesOF(idAreaSeleccionada, tipoAreaSeleccionada);
		for (DTONotificacionesOFEntity notificacion : notificaciones) {
			daoNotificacionesOFInterface.emergencia(notificacion);
		}
		List<DTOOficialiasAreasEntity> listaOficialiasAreas = new ArrayList<DTOOficialiasAreasEntity>(); 
		for (DTOOficialiaEntity persona : listaOficialiaAgregar) {
			DTOOficialiasAreasEntity oficialiaArea = new DTOOficialiasAreasEntity();
			if(persona.getIdOficialia() == null) {
				Integer id = daoOficialiaInterface.guardar(persona);
				oficialiaArea.setIdOficialia(id);
			} else {
				if (persona.getEstatus().equals("NO")) {
					persona.setEstatus("SI");
					daoOficialiaInterface.modificar(persona);
				}
				oficialiaArea.setIdOficialia(persona.getIdOficialia());
			}
			oficialiaArea.setIdArea(idAreaSeleccionada);
			oficialiaArea.setTipoArea(tipoAreaSeleccionada);
			listaOficialiasAreas.add(oficialiaArea);
		}
		
		if(listaOficialiaEliminar != null) {
			for (DTOOficialiasAreasEntity oficialiaArea : listaOficialiaEliminar) {
				daoJerarquiaPersonasInterface.eliminar(oficialiaArea);
				daoJerarquiaPersonasInterface.flush();
				daoOficialiaInterface.actualizarEstatusOficialia(oficialiaArea.getIdOficialia());
			}
		}
		
		for (DTOOficialiasAreasEntity oficialiaArea : listaOficialiasAreas) {
			daoOficialiasAreasInterface.guardar(oficialiaArea);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarAreasSinOrganigrama(int)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public List<DTOCAreaEntity> consultarAreasSinOrganigrama(int tipoArea, int idEntidad) throws Exception {
		return daoCAreas.consultarAreasSinOrganigrama(tipoArea, idEntidad);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarAreasConOrganigrama(int, int)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public List<DTOCAreaEntity> consultarAreasConOrganigrama(int tipoArea, int idEntidad) throws Exception {
		return daoCAreas.consultarAreasConOrganigrama(tipoArea, idEntidad);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarAreas(int, int)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad) throws Exception {
		return daoCAreas.consultarAreas(tipoArea, idEntidad);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEntidadesConOrganigrama()
	 */
	@Override
	public List<Integer> consultarEntidadesConOrganigrama() throws Exception {
		return BOEstructuraInterface.pasarListaAreasAEntidades(
				daoCAreas.consultarEntidadesConOrganigrama());
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEntidadesSinOrganigrama()
	 */
	@Override
	public List<Integer> consultarEntidadesSinOrganigrama() throws Exception {
		return BOEstructuraInterface.pasarListaAreasAEntidades(
				daoCAreas.consultarEntidadesSinOrganigrama());
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarExisteEstructura(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean consultarExisteEstructura(Integer tipoArea, Integer idArea) {
		List<DTOJerarquiaPersonasEntity> lista = daoJerarquiaPersonasInterface.consultarExisteEstructura(tipoArea, idArea);
		if(lista == null || lista.size() == 0) return false;
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarTiposAreas()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTiposAreas() throws Exception {
		return daoCTiposAreasInterface.consultarTodosOrdenadosAscPor("descripcion");
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEstados()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstados() throws Exception {
		return daoEstadosInterface.consultarEstadosSinOC();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEstadosSinOrganigrama()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstadosSinOrganigrama() throws Exception {
		return daoEstadosInterface.consultarEstadosSinOrganigrama();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEstadosConOrganigrama()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstadosConOrganigrama() throws Exception {
		return daoEstadosInterface.consultarEstadosConOrganigrama();
	}
}
