package mx.ine.acuerdos.bsd;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;

public interface BSDModificaDocumentosInterface {

	public List<DTOCTipoDocumento> obtenTiposDocumentos();
	
	public List<DTOCAreas> obtenListadoCatalogoAreas(int tipoArea);
	
	public boolean actualizaDocumento(HLPFormDocumentos formDocumento);

	public DTODocumentosSivople encuentraDocumentoPorId(String idDocumento);
	
	public List<DTODocumentosPeriodos> 	encuentraPeriodosDocumento(String idDocumento);
	
}
