package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;

public interface DAOCAreasInterface extends DAOGenericInterface<DTOCAreas, Integer> {

	public List<DTOCAreas> consultaCatalogoAreas(int tipoArea) throws Exception;
	
	public DTOCAreas obtenerArea(Integer idArea) throws Exception;
	
	public List<DTOCAreas> obtenerTodosAreas() throws Exception;

	public List<DTOCAreas> obtenerAreas() throws Exception;

	public String buscaPorDescripcion(String descripcion) throws Exception;
	
	public List<DTOCAreas> obtenAreasResponsablesRelacionados(int tipoArea) throws Exception;
}
