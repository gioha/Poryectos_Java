package mx.ine.acuerdos.as.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import mx.ine.acuerdos.as.ASCatalogoUsuariosInterface;
import mx.ine.acuerdos.dao.DAOCAreasInterface;
import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dao.impl.DAOLdap;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOResponsablesVista;
import mx.ine.acuerdos.dto.helper.form.HLPFormCatalogoUsuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("asCatalogoUsuarios")
@Scope("prototype")
public class ASCatalogoUsuarios implements ASCatalogoUsuariosInterface {
	
	@Autowired
	@Qualifier("ldapUserSearchByGivenNameSnUid")
	private transient DAOLdap ldapSearch;
	
	@Autowired
	@Qualifier("daoCAreas")
	private transient DAOCAreasInterface daoCAreas;
	
	@Autowired
	@Qualifier("daoResponsables")
	private transient DAOResponsablesInterface daoResponsables;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCAreas> obtenElementosCatalogoAreas(int tipoArea) throws Exception {
		return daoCAreas.consultaCatalogoAreas(tipoArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCAreas> obtenElementosCatalogoAreasResponsablesLigados(int tipoArea) throws Exception {
		return daoCAreas.obtenAreasResponsablesRelacionados(tipoArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOResponsables> obtenListadoUsuariosLdap(HLPFormCatalogoUsuarios formCatalogoUsuarios) throws Exception {
		NamingEnumeration<SearchResult> resultados = ldapSearch.buscaUsuariosLdap(
				formCatalogoUsuarios.getNombreBusqueda(),
				formCatalogoUsuarios.getApellidosBusqueda(),
				formCatalogoUsuarios.getUsuarioBusqueda(),
				formCatalogoUsuarios.getAreaAdscripcion());
		List<DTOResponsables> responsables = new ArrayList<DTOResponsables>();
		
		while (resultados.hasMoreElements()) {
			SearchResult persona = resultados.nextElement();
			Attributes atributosPersona = persona.getAttributes();

			if (atributosPersona.get("personaltitle") != null) {

				String nombrePersonal = atributosPersona.get("givenname").getAll().next().toString();
				String apellidosPersonal = atributosPersona.get("sn").getAll().next().toString();
//				String nombreCompleto = atributosPersona.get("cn").getAll().next().toString();
				String puesto = atributosPersona.get("personaltitle").getAll().next().toString();
				String area = atributosPersona.get("ou").getAll().next().toString();
				String cuentaLdap = atributosPersona.get("uid").getAll().next().toString();

				String genero = "";
				if (atributosPersona.get("curp") != null) {
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
				if (atributosPersona.get("tratamiento") != null) {
					String tratamientoAux = atributosPersona.get("tratamiento").getAll().next().toString();
					if (!tratamientoAux.equals("null")) {
						tratamiento = tratamientoAux;
					}
				}
				String extension = null;
				if (atributosPersona.get("telephonenumber") != null) {
					if (atributosPersona.get("telephonenumber").getAll().hasMore()) {
						String extensionAux = atributosPersona.get("telephonenumber").getAll().next().toString();
						if (extensionAux.contains("IP")) {					
							if (extensionAux.length() > 6) {						
								extension = extensionAux.substring(0,6);
							}
						}
					}
				}
				String email = null;
				if (atributosPersona.get("mail") != null) {
					String emailAux = atributosPersona.get("mail").getAll().next().toString();
					if (!emailAux.equals("null")) {
						email = emailAux;
					}
				}

				DTOResponsables responsable = new DTOResponsables(nombrePersonal, apellidosPersonal,
						puesto, tratamiento, extension, cuentaLdap, email, area, genero);

				responsables.add(responsable);
			}
		}
		
		return responsables;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void guardaResponsable(DTOResponsables responsable) throws Exception {
		daoResponsables.persisteResponsable(responsable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public String obtenCoincidenciaArea(String nomNivelArea) throws Exception {
		return daoCAreas.buscaPorDescripcion(nomNivelArea);
	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
//	public boolean existeResponsable(DTOResponsables responsable) throws Exception {
//		return daoResponsables.obtenerResponsPorUsuario(responsable.getCuentaLdap()) != null;
//	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOResponsablesVista> obtenResponsables(HLPFormCatalogoUsuarios formCatalogoUsuarios) throws Exception {
		List<DTOResponsablesVista> responsablesVista = new ArrayList<>();
		List<DTOResponsables> responsables = daoResponsables.encuentraResponsables(
				formCatalogoUsuarios.getNombreBusqueda(),
				formCatalogoUsuarios.getApellidosBusqueda(),
				formCatalogoUsuarios.getUsuarioBusqueda(),
				formCatalogoUsuarios.getIdAreaBusqueda(),
				formCatalogoUsuarios.getTipoResponsableBusqueda()
			);
		
		for (DTOResponsables responsable : responsables) {
			DTOResponsablesVista rv = new DTOResponsablesVista(
					responsable,
					daoCAreas.obtenerArea(responsable.getIdArea())
			);
			responsablesVista.add(rv);
		}
		
		return responsablesVista;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void actualizaResponsable(DTOResponsables responsable) {
		daoResponsables.guardarOactualizar(responsable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOResponsables buscaUsuarioPorCuentaLdap(String usuario) throws Exception {
		return daoResponsables.obtenerResponsPorUsuarioSinFiltro(usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void cambiaEstatusResponsable(DTOResponsables responsable) throws Exception {
		daoResponsables.eliminaLogicamenteUsuario(responsable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOCAreas buscaAreaPorId(Integer idArea) throws Exception {
		return daoCAreas.obtenerArea(idArea);
	}

}
