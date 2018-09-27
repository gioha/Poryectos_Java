package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;

public interface ASRegistroDocumentosInterface {
	
	public List<DTOCTipoDocumento> obtenTiposDocumentos() throws Exception;
	
	public List<DTOCAreas> obtenElementosCatalogoAreas(int tipoArea) throws Exception;
	
	public void guardaDocumento(HLPFormDocumentos formDocumento) throws Exception;

}
