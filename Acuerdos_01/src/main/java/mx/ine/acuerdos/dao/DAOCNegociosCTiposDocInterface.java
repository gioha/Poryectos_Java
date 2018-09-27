package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCNegociosCTiposDoc;

public interface DAOCNegociosCTiposDocInterface extends DAOGenericInterface<DTOCNegociosCTiposDoc, Integer> {

	public List<DTOCNegociosCTiposDoc> consultarNegociosTiposDoc() throws Exception;

}
