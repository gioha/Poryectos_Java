package mx.ine.computosINE.bsd;

import java.util.List;

import mx.ine.computosINE.dto.DTODistrito;

public interface BSDDistritosInterface {

	List<DTODistrito> cargaDistritos(Integer idEstado,
			Integer idMunicipioLocal) throws Exception;

}
