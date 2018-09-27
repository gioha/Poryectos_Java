package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCTipoIntegComision;

public interface DAOCTipoIntegComisionInterface extends DAOGenericInterface<DTOCTipoIntegComision, Integer> {

	public List<DTOCTipoIntegComision> obtenerTipoIntegComision() throws Exception;

}
