package mx.ine.computosINE.bsd.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.computosINE.as.ASInformacionGeneralActasInterface;
import mx.ine.computosINE.bsd.BSDInformacionGeneralActaInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;

/**
 * <code>BSDRegistroActaDiputadoRPImpl.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 06/05/2017 13:50:00
 */
@Component("bsdInfoGralActas")
@Scope("prototype")
public class BSDInformacionGeneralActasImpl implements BSDInformacionGeneralActaInterface {

    @Autowired
    @Qualifier("asInfoGralActas")
    private ASInformacionGeneralActasInterface asInfoGralActas;

	
	@Override
	public BigDecimal getCountActasCapturadasByCandidatura(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return asInfoGralActas.getCountActasCapturadasByCandidatura(arg0);
	}
	

	public BigDecimal getCountActasCapturadasByDemarcacion(Object[] arg0) throws Exception{
		return asInfoGralActas.getCountActasCapturadasByDemarcacion(arg0);
	}
	
	@Override
	public BigDecimal getActasCapturadasxMunicipioCandidatura(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return asInfoGralActas.getActasCapturadasxMunicipioCandidatura(arg0);
	}
	
	@Override
	public Integer getTotalMunicipiosActasCapturadas(Integer idProceso, Integer idDetalleProceso,
            Integer idEstado, Integer idTipoCandidatura) throws Exception{
		// TODO Auto-generated method stub
		return asInfoGralActas.getTotalMunicipiosActasCapturadas(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}
	
	@Override
	public List<Object[]> consultaActasCaptByMunDistrito(Integer idProceso, Integer idDetalleProceso,
            Integer idEstado, Integer idTipoCandidatura) throws Exception{
		// TODO Auto-generated method stub
		return asInfoGralActas.consultaActasCaptByMunDistrito(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}

	@Override
	public BigDecimal getTotalVotosNulosCNR(DTOActaCasillaVotosPK id) throws Exception{
		// TODO Auto-generated method stub
		return asInfoGralActas.getTotalVotosNulosCNR(id);
	}
	
	@Override
	public List<HLPActaCasillaVotos> getTotalVotosActaDiputados(Object [] arg0) throws Exception{
		// TODO Auto-generated method stub
		return asInfoGralActas.getTotalVotosActaDiputados(arg0);
	}
	
	@Override
	public List<HLPActaCasillaVotos> getTotalVotosActaGobernador(Object [] arg0) throws Exception{
		return asInfoGralActas.getTotalVotosActaGobernador(arg0);
	}
	
	@Override
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaMR(Object[] arg0) throws Exception{
		return asInfoGralActas.getTotalVotosRegiduriaMR(arg0);
	}
	
	
	@Override
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRP(Object[] arg0) throws Exception{
		return asInfoGralActas.getTotalVotosRegiduriaRP(arg0);
	}
	
	@Override
	public List<HLPActaCasillaVotos> getTotalVotosDiputadosMR(Object[] arg0) throws Exception{
		return asInfoGralActas.getTotalVotosDiputadosMR(arg0);
	}
	
	@Override
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRPParcial(Object[] arg0)throws Exception{
		return asInfoGralActas.getTotalVotosRegiduriaRPParcial(arg0);
	}

	@Override
	public boolean esRecuentoParcial(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion)
			throws Exception {
		return asInfoGralActas.esRecuentoParcial(idTipoCandidatura, usuario, distrito, demarcacion);
	}


	@Override
	public boolean esRecuentoTotal(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion) throws Exception {
		return asInfoGralActas.esRecuentoTotal(idTipoCandidatura, usuario, distrito, demarcacion);
	}
}
