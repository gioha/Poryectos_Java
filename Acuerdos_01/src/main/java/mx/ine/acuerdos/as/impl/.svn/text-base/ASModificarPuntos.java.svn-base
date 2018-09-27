package mx.ine.acuerdos.as.impl;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.as.ASModificarPuntosInterface;
import mx.ine.acuerdos.dao.DAOAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOCAreasInterface;
import mx.ine.acuerdos.dao.DAOCClasificacionesInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dao.DAOSeguimientosCGInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service("asModPuntos")
public class ASModificarPuntos implements ASModificarPuntosInterface {
	@Autowired
	@Qualifier("daoCClasifiaciones")
	private transient DAOCClasificacionesInterface daoCClasifiaciones;

	@Autowired
	@Qualifier("daoPuntos")
	private transient DAOPuntosInterface daoPunto;
	
	@Autowired
	@Qualifier("daoAcuerdos")
	private transient DAOAcuerdosInterface daoAcuerdo;
	
	@Autowired
	@Qualifier("daoCAreas")
	private transient DAOCAreasInterface daoCAreas;  
	
	@Autowired
	@Qualifier("daoSeguimientosCG")
	private transient DAOSeguimientosCGInterface daoSeguimiento;
	
	@Autowired
	@Qualifier("daoResponsables")
	private transient DAOResponsablesInterface daoRespons;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCClasificaciones> recuperaClasificaciones() throws Exception {

		// return null;
		return daoCClasifiaciones.consultaClasificaiones();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void actualizarPunto(DTOPuntosAcuerdo punto)  throws Exception {
	
		daoPunto.guardaroActualizarPunto(punto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia)  throws Exception {
		try {
			return daoPunto.obtenerPunto(idAcuerdo,numeralia);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public Date obtenerFechaSesion(String idAcuerdo) {
		return daoAcuerdo.obtenerFechaSesion(idAcuerdo);	
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception{
		return daoSeguimiento.obtenerResponsablesDelPunto(idAcuerdo, numeralia);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCAreas> obtenerTodasLasAreas() throws Exception{
		return daoCAreas.obtenerTodosAreas();
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOCAreas obtenerArea(Integer idArea) throws Exception{
		return daoCAreas.obtenerArea(idArea);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception{
		return daoRespons.obtenerResponsable(idArea);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void guardaroActualizarSeguimiento(DTOSeguimientosCG seguimiento ) throws Exception{
		daoSeguimiento.insertarMovimiento(seguimiento);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void eliminarSeguimiento(DTOSeguimientosCG seguimiento ) throws Exception{
		daoSeguimiento.eliminar(seguimiento);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public List<DTOSeguimientosCG> obtenerSeguimiento(String idAcuerdo, Integer numeralia, Integer idArea) throws Exception{
		return daoSeguimiento.obtenerSeguimiento(idAcuerdo, numeralia, idArea);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCClasificaciones> obtenerSiPPN() throws Exception {		
		return daoCClasifiaciones.obtenerSiPPN();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo) throws Exception {		
		return daoAcuerdo.obtenerAcuerdo(idAcuerdo);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void modificaSeguimientoAreaGrupo(String idAcuerdo, Integer numeralia, Integer idArea, Integer grupo) throws Exception{
		 daoSeguimiento.modificaSeguimientoAreaGrupo(idAcuerdo, numeralia, idArea,grupo);
	}

}
