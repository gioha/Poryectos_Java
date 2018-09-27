/**
 * 
 */
package mx.ine.computosINE.as.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.ine.computosINE.as.ASCapturaVotoRPInterface;
import mx.ine.computosINE.dao.DAOActaCasillaVotosInterface;
import mx.ine.computosINE.dao.DAODistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;

/**
 * <code>ASCapturaVotoRPImpl.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 27/04/2017 11:27:00
 */
@Scope("prototype")
@Service("asCapturaVotoRP")
public class ASCapturaVotoRPImpl implements ASCapturaVotoRPInterface {

	@Autowired
	@Qualifier("daoActaCasillaVotos")
	private transient DAOActaCasillaVotosInterface daoActaCasillaVotos;

	@Autowired
	@Qualifier("daoDistribucionVotos")
	private transient DAODistribucionVotosInterface daoDistribucionVotos;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public BigDecimal getCountVotosCapturados(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getCountVotosCapturados(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public BigDecimal getVotosAcumuladosDiputados(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getVotosAcumuladosDiputados(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getListActaCasillaVotos(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultaListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.consultaListActaCasillaVotos(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Object[]>  consultaActasCapturadasXestado(DTOActaCasillaVotosPK id, String modulo) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.consultaActasCapturadasXestado(id, modulo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultaCandidatosXacta(DTOActaCasillaVotosPK id, Integer idEstatus, String modulo) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.consultaCandidatosXacta(id, idEstatus, modulo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean existeDistribucionTotales(DTODistribucionTotalesPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoDistribucionVotos.existeDistribucionTotales(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean existeDistribucionPartidosCI(DTODistribucionPartidosCIPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoDistribucionVotos.existeDistribucionPartidosCI(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean existeDistribucionCandidatos(DTODistribucionCandidatosPK id)	throws Exception {
		// TODO Auto-generated method stub
		return daoDistribucionVotos.existeDistribucionCandidatos(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean existeGeneracionActa(DTOActaTipoCandidaturaPK id) throws Exception {
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.existeGeneracionActa(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardar(List<DTOActaCasillaVotos> votos) throws Exception {
		// TODO Auto-generated method stub
		daoActaCasillaVotos.saveOrUpdate(votos);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminar(List<DTOActaCasillaVotos> votos) throws Exception {
		// TODO Auto-generated method stub
		daoActaCasillaVotos.eliminar(votos);
	}
}
