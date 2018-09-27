package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASSeguimientoPuntosInterface;
import mx.ine.acuerdos.dao.DAOAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOCAreasInterface;
import mx.ine.acuerdos.dao.DAOCClasificacionesInterface;
import mx.ine.acuerdos.dao.DAOCEstatusPuntosInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dao.DAOSeguimientosCGInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOCEstatusPuntos;
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
@Service("asSegPuntos")
public class ASSeguimientoPuntos implements ASSeguimientoPuntosInterface {
	@Autowired
	@Qualifier("daoAcuerdos")
	private transient DAOAcuerdosInterface daoAcuerdo;
	
	@Autowired
	@Qualifier("daoPuntos")
	private transient DAOPuntosInterface daoPunto;
	
	@Autowired
	@Qualifier("daoCClasifiaciones")
	private transient DAOCClasificacionesInterface daoClasificacion;
	
	@Autowired
	@Qualifier("daoSeguimientosCG")
	private transient DAOSeguimientosCGInterface daoSeguimiento;

	@Autowired
	@Qualifier("daoCAreas")
	private transient DAOCAreasInterface daoArea;
	
	@Autowired
	@Qualifier("daoResponsables")
	private transient DAOResponsablesInterface daoResponsable;

	@Autowired
	@Qualifier("daoEstatusPuntos")
	private transient DAOCEstatusPuntosInterface daoEstatusPunto;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOAcuerdos recuperarAcuerdo(String idNumAcuerdo) throws Exception {
		return daoAcuerdo.obtenerAcuerdo(idNumAcuerdo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOPuntosAcuerdo recuperarPunto(String idNumAcuerdo, Integer numeralia) throws Exception {
		return daoPunto.obtenerPunto(idNumAcuerdo, numeralia);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOCClasificaciones recuperarClasificacion(Integer idClasificacion) throws Exception {
		return daoClasificacion.obtenerClasificacion(idClasificacion);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOSeguimientosCG> recuperarSeguimiento(DTOPuntosAcuerdo dtoPunto) throws Exception {
		return daoSeguimiento.consultaSeguimiento(dtoPunto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOSeguimientosCG> recuperarSeguimientoArea(String idNumAcuerdo, Integer numeralia, Integer idArea) throws Exception {
		return daoSeguimiento.obtenerSeguimientoPorArea(idNumAcuerdo, numeralia, idArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOResponsables recuperarAreaUsuarioResponsable(String nomUsuario) throws Exception {
		return daoResponsable.obtenerResponsPorUsuario(nomUsuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOCAreas recuperarAreaInvolucrada(Integer idArea) throws Exception {
		return daoArea.obtenerArea(idArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOResponsables> recuperarResponsInvolucrado(Integer idArea) throws Exception {
		return daoResponsable.obtenerResponsable(idArea);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOCAreas recuperarArea(Integer idArea) throws Exception {
		return daoArea.obtenerArea(idArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOSeguimientosCG> recuperarRespConj( String idNumAcuerdo, Integer numeralia, Integer respConj ) throws Exception {
		return daoSeguimiento.obtenerRespConjunta(idNumAcuerdo, numeralia, respConj);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void insertarMovimiento(DTOSeguimientosCG dtoSegCG) {
		daoSeguimiento.insertarMovimiento(dtoSegCG);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOPuntosAcuerdo recuperarPuntoAcuerdo(String idNumAcuerdos, Integer numeralia) throws Exception {
		return daoPunto.obtenerPunto(idNumAcuerdos, numeralia);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOPuntosAcuerdo> recuperarPuntosAcuerdo(String idNumAcuerdo) throws Exception {
		return daoPunto.obtenerPuntosAcuerdo(idNumAcuerdo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void actualizarPuntoAcuerdo(DTOPuntosAcuerdo puntoPorActualizar) throws Exception {
		daoPunto.eliminacionLogicaPunto(puntoPorActualizar);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void actualizarAcuerdo(DTOAcuerdos acuerdo) throws Exception {
		daoAcuerdo.actualizarAcuerdo(acuerdo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void eliminarSegPunto(DTOSeguimientosCG movSeguimiento) throws Exception {
		daoSeguimiento.eliminarSeguimientoArea(movSeguimiento);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCEstatusPuntos> recuperarEstatusPuntos() throws Exception {
		return daoEstatusPunto.obtenerEstatusPuntos();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOResponsables recuperarDtoResponsable(String nomUsuario) throws Exception {
		return daoResponsable.obtenerResponsPorUsuario(nomUsuario);
	}

}
