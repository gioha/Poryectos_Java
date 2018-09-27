package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;

public interface DAOPeriodosInterface extends DAOGenericInterface<DTODocumentosPeriodos, Integer> {

	public boolean persistePeriodos(List<DTODocumentosPeriodos> periodos, DTODocumentosSivople documento) throws Exception;

	public List<DTODocumentosPeriodos> buscarTodos(String idDocumento) throws Exception;
	
	public void eliminaPeriodos(String idDocumento) throws Exception;
	
}
