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

import mx.ine.computosINE.as.ASInformacionGeneralActasInterface;
import mx.ine.computosINE.dao.DAOActaCasillaVotosInterface;
import mx.ine.computosINE.dao.DAOGeneracionActasInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;

/**
 * <code>ASInformacionGeneralActasImpl.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 06/05/2017 13:42:00
 */
@Scope("prototype")
@Service("asInfoGralActas")
public class ASInformacionGeneralActasImpl implements ASInformacionGeneralActasInterface{

	@Autowired
	@Qualifier("daoActaCasillaVotos")
	private transient DAOActaCasillaVotosInterface daoActaCasillaVotos;
	
	@Autowired
	@Qualifier("daoGeneracionActas")
	private transient DAOGeneracionActasInterface daoGeneracionActas;
	


	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public BigDecimal getCountActasCapturadasByCandidatura(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getCountActasCapturadasByCandidatura(arg0);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public BigDecimal getCountActasCapturadasByDemarcacion(Object[] arg0) throws Exception{
		return daoActaCasillaVotos.getCountActasCapturadasByDemarcacion(arg0);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public BigDecimal getActasCapturadasxMunicipioCandidatura(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getActasCapturadasxMunicipioCandidatura(arg0);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public Integer getTotalMunicipiosActasCapturadas(Integer idProceso, Integer idDetalleProceso,
            Integer idEstado, Integer idTipoCandidatura) throws Exception{
		// TODO Auto-generated method stub
		return daoGeneracionActas.getTotalMunicipiosActasCapturadas(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Object[]> consultaActasCaptByMunDistrito(Integer idProceso, Integer idDetalleProceso,
            Integer idEstado, Integer idTipoCandidatura) throws Exception{
		// TODO Auto-generated method stub
		return daoGeneracionActas.consultaActasCaptByMunDistrito(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public BigDecimal getTotalVotosNulosCNR(DTOActaCasillaVotosPK id) throws Exception{
		// TODO Auto-generated method stub
	   return daoActaCasillaVotos.getTotalVotosNulosCNR(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPActaCasillaVotos> getTotalVotosActaDiputados(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getTotalVotosActaDiputados(arg0);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPActaCasillaVotos> getTotalVotosActaGobernador(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return daoActaCasillaVotos.getTotalVotosActaGobernador(arg0);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaMR(Object[] arg0) throws Exception{
		return daoActaCasillaVotos.getTotalVotosRegiduriaMR(arg0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRP(Object[] arg0) throws Exception{
		return daoActaCasillaVotos.getTotalVotosRegiduriaRP(arg0);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPActaCasillaVotos> getTotalVotosDiputadosMR(Object[] arg0) throws Exception{
		return daoActaCasillaVotos.getTotalVotosDiputadosMR(arg0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRPParcial(Object[] arg0)throws Exception{
		return daoActaCasillaVotos.getTotalVotosRegiduriaRPParcial(arg0);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean esRecuentoParcial(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion)
			throws Exception {
		return daoActaCasillaVotos.esRecuentoParcial(idTipoCandidatura, usuario, distrito, demarcacion);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean esRecuentoTotal(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion) throws Exception {
		return daoActaCasillaVotos.esRecuentoTotal(idTipoCandidatura, usuario, distrito, demarcacion);
	}
	

	

}
