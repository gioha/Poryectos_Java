package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCCargos;

public interface DAOCCargosInterface extends DAOGenericInterface<DTOCCargos, Integer> {

	public List<DTOCCargos> obtenerCargos() throws Exception;
	public DTOCCargos obtenerCargo(Integer idCargo) throws Exception;
	
}
