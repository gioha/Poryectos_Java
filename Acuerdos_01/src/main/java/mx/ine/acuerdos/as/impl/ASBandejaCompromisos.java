package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASBandejaCompromisosInterface;
import mx.ine.acuerdos.dao.DAOAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dao.DAOSeguimientosCGInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <code>ASBandejaCompromisos.java</code>Clase encargada de administrar el o los DAO del administrador general del sistema de Acuerdos
 *
 * @author Sampier Cuevas Flores
 * @version 1.0
 * @since 13/10/2017 
 */
@Scope("prototype")
@Service("asBandejaCompromisos")
public class ASBandejaCompromisos implements ASBandejaCompromisosInterface{

////////////////////////////////VARIABLES///////////////////////////////////
	
	@Autowired
	@Qualifier("daoAcuerdos")
	private transient DAOAcuerdosInterface daoAcuerdos;
	
	@Autowired
	@Qualifier("daoPuntos")
	private transient DAOPuntosInterface daoPuntos;
	
	@Autowired
	@Qualifier("daoSeguimientosCG")
	private transient DAOSeguimientosCGInterface daoSeguimientos;
	
	@Autowired
	@Qualifier("daoResponsables")
	private transient DAOResponsablesInterface daoResponsables;
	

////////////////////////////////METODOS////////////////////////////////////
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> recuperaAcuerdos(Integer idTipoDocumento, Integer idNegocio) throws Exception {
		return daoAcuerdos.consultaAcuerdos(idTipoDocumento, idNegocio);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo) throws Exception {
		
		return daoPuntos.consultaPuntosAcuerdo(idNumAcuerdo);
				
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public void eliminacionLogicaAcuerdo(DTOAcuerdos acuerdo) throws Exception{
		daoAcuerdos.eliminacionLogicaAcuerdo(acuerdo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public void eliminacionLogicaPuntos(List<DTOPuntosAcuerdo> puntos) throws Exception{
		daoPuntos.eliminacionLogicaPuntos(puntos);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public void eliminacionLogicaPunto(DTOPuntosAcuerdo punto) throws Exception{
		daoPuntos.eliminacionLogicaPunto(punto);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		return daoAcuerdos.busquedaAcuerdo(idAcuerdo, idTipoDocumento, idNegocio);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		return daoAcuerdos.busquedaAcuerdo(idAcuerdo, idArea, idTipoDocumento, idNegocio);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> consultaAcuerdo(String idAcuerdo) throws Exception{
		return daoAcuerdos.consultaAcuerdo(idAcuerdo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		return daoAcuerdos.busquedaAcuerdoPorFecha(fechaSesion, idTipoDocumento, idNegocio);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		return daoAcuerdos.busquedaAcuerdoPorFecha(fechaSesion, idArea, idTipoDocumento, idNegocio);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOSeguimientosCG> consultaPuntoEnSeguimiento(DTOPuntosAcuerdo punto) throws Exception{
		return daoSeguimientos.consultaSeguimiento(punto);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> consultaAcuerdosPorArea(Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		return daoAcuerdos.consultaAcuerdosPorArea(idArea, idTipoDocumento, idNegocio);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTOResponsables obtenerResponsableSegunLdap(String usuario) throws Exception {		
		return daoResponsables.obtenerResponsableSegunLdap(usuario);		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo, Integer idArea) throws Exception {		
		return daoPuntos.consultaPuntosAcuerdo(idNumAcuerdo,idArea);				
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception{
		return daoSeguimientos.obtenerResponsablesDelPunto(idAcuerdo,numeralia);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception{
		return daoResponsables.obtenerResponsable(idArea);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public void eliminarSeguimientoArea(DTOSeguimientosCG seguimiento)throws Exception{
		daoSeguimientos.eliminarSeguimientoArea(seguimiento);
	}
}
