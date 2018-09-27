package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOComisionesUnidas;

public interface DAOComisionesUnidasInterface extends DAOGenericInterface<DTOComisionesUnidas, Integer> {

	public List<DTOComisionesUnidas> obtenerComisionesUnidas(Integer idComisionPreside) throws Exception;

}
