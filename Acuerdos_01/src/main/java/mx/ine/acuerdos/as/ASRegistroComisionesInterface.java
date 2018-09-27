package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOComisiones;

public interface ASRegistroComisionesInterface {

	public List<DTOComisiones> obtenerTodasComisionesActivas() throws Exception;
	public List<DTOComisiones> obtenerTodasComisionesInactivas() throws Exception;
	public boolean guardarComision(DTOComisiones comision,int motivo) throws Exception;
}
