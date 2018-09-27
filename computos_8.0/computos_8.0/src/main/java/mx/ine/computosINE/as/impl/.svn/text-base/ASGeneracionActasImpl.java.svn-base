package mx.ine.computosINE.as.impl;

import java.util.List;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.ine.computosINE.as.ASGeneracionActasInterface;
import mx.ine.computosINE.dao.DAOGeneracionActasInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCConsejo;
import mx.ine.computosINE.dto.DTOConsejero;
import mx.ine.computosINE.dto.DTORepresentante;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.org.ine.servicios.dto.DTOBase;

@Service("asGeneracionActas")
@Scope("prototype")
public class ASGeneracionActasImpl implements ASGeneracionActasInterface {

	@Autowired
	@Qualifier("daoGeneracionActas")
	DAOGeneracionActasInterface daoActas;

	/**
	 * {@inheritDoc}}
	 * @throws Exception 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void guardaActualizaActa(DTOBase acta) throws Exception {
	try{
		daoActas.guardaActualizaActa(acta);
	} catch(Exception e){
		e.printStackTrace();
		Log.error("en la tabla de el acta", e);
	}
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void eliminaActa(DTOActaTipoCandidatura acta) {
		daoActas.eliminaActa(acta);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void guardaRepresentante(DTORepresentante representante) throws Exception{
	  try{
		daoActas.guardaRepresentante(representante);
	  } catch(Exception e){
			e.printStackTrace();
			Log.error("en la tabla de representantes", e);
	  }
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void guardaConsejero(DTOConsejero consejero) throws Exception{
	  try{
		daoActas.guardaConsejero(consejero);
	  } catch(Exception e){
			e.printStackTrace();
			Log.error("en la tabla de consejeros", e);
	  }
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOCConsejo> getCatalogoConsejeros() {
		return daoActas.getCatalogoConsejeros();
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTOBase getActa(Object pkActa) {
		return daoActas.getActa(pkActa);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOConsejero> getConsejeros(Object pkActa) {
		return daoActas.getConsejeros(pkActa);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTORepresentante> getRepresentantes(Object pkActa) {
		return daoActas.getRepresentantes(pkActa);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void setEditable(DTOActaTipoCandidaturaPK pkActa) throws Exception{
		daoActas.setEditable(pkActa);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void eliminandoActa(DTOActaTipoCandidaturaPK pkActa) {
		daoActas.eliminandoActa(pkActa);
		
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void eliminaConsejeros(List<DTOConsejero> actaConsejeros) {
		daoActas.eliminaConsejeros(actaConsejeros);
		
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void eliminaRepresentantes(List<DTORepresentante> actaRepresentante) {
		daoActas.eliminaRepresentantes(actaRepresentante);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Object[]> consultaDistritosConSusMunicipios(
			Integer idTipoCandidatura) throws Exception {
		return daoActas.consultaDistritosConSusMunicipios(idTipoCandidatura);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> consultaIdAsociacionesDipRPEstatal() throws Exception {
		return daoActas.consultaIdAsociacionesDipRPEstatal();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> consultaDistritosLocalesPorMunicipio(
			DTOUsuarioLogin usuario) throws Exception {
		return daoActas.consultaDistritosLocalesPorMunicipio(usuario);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> consultaDemarcacionesPorMR(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer idSeleccion)
			throws Exception {
		return daoActas.consultaDemarcacionesPorMR(idTipoCandidatura, usuario, idSeleccion);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> consultaDistritosPorMR(Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer idSeleccion) throws Exception {
		return daoActas.consultaDistritosPorMR(idTipoCandidatura, usuario, idSeleccion);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean tieneEspeciales(Integer idTipoCandidatura,
			Integer idSeleccion) {
		return daoActas.tieneEspeciales(idTipoCandidatura, idSeleccion);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean isGeneradasDiputadosRPFinal() {
		return daoActas.isGeneradasDiputadosRPFinal();
	}
	
	
	
	
}