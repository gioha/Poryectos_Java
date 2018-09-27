package mx.ine.acuerdos.dao;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOConvocatorias;

public interface DAOConvocatoriasInterface extends DAOGenericInterface<DTOConvocatorias, Integer> {

	public List<DTOConvocatorias> obtenerConvocatoriasPorAnio(Integer anio) throws Exception;
	public List<DTOConvocatorias> obtenerConvocatoriasFechaTipo(Integer idAnio, Integer tipoSesion) throws Exception;
	public void insertarConvocatoria(DTOConvocatorias convocatoria) throws Exception;
	public List<DTOConvocatorias> obtenerConvocatorias() throws Exception;
	public List<DTOConvocatorias> obtenerConvocatoriasFormatAnio(Date inicioAnio, Date finAnio) throws Exception;

}
