package mx.ine.acuerdos.dao;

import mx.ine.acuerdos.dto.DTOConformComisiones;

public interface DAOConformComisionesInterface extends DAOGenericInterface<DTOConformComisiones, Integer> {

	public DTOConformComisiones obtenerConformComisionActual(Integer idComision) throws Exception;
	
}
