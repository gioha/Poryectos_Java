package mx.ine.computosINE.dao;

import java.util.List;

import mx.ine.computosINE.dto.DTODistrito;

public interface DAODistritoInterface extends
		DAOGenericInterface<DTODistrito, Integer> {
	
	List<DTODistrito> consultaDistritos(Integer idEstado, Integer idMunicipioLocal) throws Exception;

}
