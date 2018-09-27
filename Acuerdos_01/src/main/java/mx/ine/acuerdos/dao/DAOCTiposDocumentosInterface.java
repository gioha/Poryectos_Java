package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCTipoDocumento;

public interface DAOCTiposDocumentosInterface extends DAOGenericInterface<DTOCTipoDocumento, Integer> {

	public List<DTOCTipoDocumento> consultaTiposDocumentos() throws Exception;

	public String recuperaTipoDocumentoS(Integer idDoc);
	
}
