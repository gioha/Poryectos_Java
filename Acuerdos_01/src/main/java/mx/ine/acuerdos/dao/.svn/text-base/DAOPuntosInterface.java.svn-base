package mx.ine.acuerdos.dao;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdoPK;

public interface DAOPuntosInterface {
	public List<DTOPuntosAcuerdo> consultaPuntos() throws Exception;

	public void guardaroActualizarPunto(DTOPuntosAcuerdo punto)  throws Exception;
	
	public void actualizar(DTOPuntosAcuerdo punto)  throws Exception;
	
	public List<DTOPuntosAcuerdo> consultaPuntosAcuerdo(String idNumAcuerdo) throws Exception;
	
	public void eliminacionLogicaPuntos(List<DTOPuntosAcuerdo> puntos) throws Exception;
	
	public void eliminacionLogicaPunto(DTOPuntosAcuerdo punto) throws Exception;

	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia) throws Exception;
	
	public List<DTOPuntosAcuerdo> consultaPuntosAcuerdo(String idNumAcuerdo, Integer idArea) throws Exception;
	
	public String semaforoPunto(DTOPuntosAcuerdo punto, Integer numResponsables)throws Exception;

	public List<DTOPuntosAcuerdo> obtenerPuntosAcuerdo(String idNumAcuerdo) throws Exception;

	public Integer obtenerPuntosAcuerdoPorIDClasif(Integer idClasif);

}
