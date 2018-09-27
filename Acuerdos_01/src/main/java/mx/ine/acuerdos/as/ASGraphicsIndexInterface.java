package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOGraphicAcuerdos;

public interface ASGraphicsIndexInterface {
	
	
	
	
	public List<DTOGraphicAcuerdos> acuerdosPuntosEstatus(int tipoDocumento) throws Exception;

}
