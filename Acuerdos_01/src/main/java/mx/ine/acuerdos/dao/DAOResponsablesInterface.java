package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOResponsables;

public interface DAOResponsablesInterface extends DAOGenericInterface<DTOResponsables, Integer> {

	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception;

	public DTOResponsables obtenerResponsPorUsuario(String nomUsuario) throws Exception;
	
	public DTOResponsables obtenerResponsableSegunLdap(String usuario) throws Exception;
	
	public void persisteResponsable(DTOResponsables responsable) throws Exception;

	public DTOResponsables obtenerResponsablePorID(Integer idResponsable) throws Exception;

	public List<DTOResponsables> encuentraResponsables(String nombre, String apellidos,
			String usuario, Integer idArea, Integer tipoResponsableBusqueda) throws Exception;

	public void eliminaLogicamenteUsuario(DTOResponsables responsable) throws Exception;
	
	public DTOResponsables obtenerResponsPorUsuarioSinFiltro(String nomUsuario) throws Exception;

}
