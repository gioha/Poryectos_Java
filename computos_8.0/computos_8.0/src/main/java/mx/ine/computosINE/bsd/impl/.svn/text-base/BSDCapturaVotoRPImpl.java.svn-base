package mx.ine.computosINE.bsd.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.computosINE.as.ASCapturaVotoRPInterface;
import mx.ine.computosINE.bsd.BSDCapturaVotoRPInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;

/**
 * <code>BSDCapturaVotoRPImpl.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 27/04/2017 11:27:00
 */
@Component("bsdCapturaVotoRP")
@Scope("prototype")
public class BSDCapturaVotoRPImpl implements BSDCapturaVotoRPInterface {

    @Autowired
    @Qualifier("asCapturaVotoRP")
    private ASCapturaVotoRPInterface asCapturaRP;

	@Override
	public BigDecimal getCountVotosCapturados(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.getCountVotosCapturados(id);
	}

	@Override
	public BigDecimal getVotosAcumuladosDiputados(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.getVotosAcumuladosDiputados(id);
	}

	@Override
	public List<DTOActaCasillaVotos> getListActaCasillaVotos(DTOActaCasillaVotosPK id)	throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.getListActaCasillaVotos(id);
	}

	@Override
	public List<DTOActaCasillaVotos> consultaListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.consultaListActaCasillaVotos(id);
	}

	@Override
	public List<Object[]> consultaActasCapturadasXestado(DTOActaCasillaVotosPK id, String modulo) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.consultaActasCapturadasXestado(id, modulo);
	}

	@Override
	public List<DTOActaCasillaVotos> consultaCandidatosXacta(DTOActaCasillaVotosPK id, Integer idEstatus, String modulo) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.consultaCandidatosXacta(id, idEstatus, modulo);
	}

	@Override
	public boolean existeDistribucionTotales(DTODistribucionTotalesPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.existeDistribucionTotales(id);
	}

	@Override
	public boolean existeDistribucionPartidosCI(DTODistribucionPartidosCIPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.existeDistribucionPartidosCI(id);
	}

	@Override
	public boolean existeDistribucionCandidatos(DTODistribucionCandidatosPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.existeDistribucionCandidatos(id);
	}

	@Override
	public boolean existeGeneracionActa(DTOActaTipoCandidaturaPK id) throws Exception {
		// TODO Auto-generated method stub
		return asCapturaRP.existeGeneracionActa(id);
	}

	@Override
	public void guardar(List<DTOActaCasillaVotos> votos) throws Exception {
		// TODO Auto-generated method stub
		asCapturaRP.guardar(votos);
	}

	@Override
	public void eliminar(List<DTOActaCasillaVotos> votos) throws Exception {
		// TODO Auto-generated method stub
		asCapturaRP.eliminar(votos);
	}
}
