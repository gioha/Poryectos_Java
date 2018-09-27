package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;

public interface ASModificaDocumentosInterface {
	
	public List<DTOCTipoDocumento> obtenTiposDocumentos() throws Exception;
	
	public List<DTOCAreas> obtenElementosCatalogoAreas(int tipoArea) throws Exception;
	
	public void actulizaDocumento(HLPFormDocumentos formDocumento) throws Exception;

	public DTODocumentosSivople encuentraDocumentoPorID(String idDocumento) throws Exception;

	public List<DTODocumentosPeriodos> encuentraPeriodosDocumento(String idDocumento) throws Exception;
	
}
