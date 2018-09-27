package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCClasificaciones;

public interface ASCClasificacionConsultaInterface {

	
	/**
	 * Obtiene una lista de clasificaciones punto
	 * @param void
	 * @return List<DTOCClasificaciones>
	 * @throws 
	 */
	
	public List<DTOCClasificaciones> recuperaClasificaciones() throws Exception;
	
}
