package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOComisiones;

public interface DAOComisionesInterface extends DAOGenericInterface<DTOComisiones, Integer> {

	public List<DTOComisiones> obtenerComisionesConFiltro(Integer idComision) throws Exception;
	public DTOComisiones obtenerComision(Integer idComision) throws Exception;
	public List<DTOComisiones> obtenerTodasComisionesActivas() throws Exception;
	public List<DTOComisiones> obtenerTodasComisionesInactivas() throws Exception;
	public boolean guardarComision(DTOComisiones comision,int motivo) throws Exception;

}
