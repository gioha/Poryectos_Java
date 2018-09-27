/**
 * 
 */
package mx.ine.computosINE.as.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.helper.HLPCandidatosInterface;
import mx.ine.common.helper.HLPGeograficosInterface;
import mx.ine.common.helper.HLPSecCasillasInterface;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTOEstadosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOMunicipiosWS;
import mx.ine.computosINE.as.ASCargaInformacionInterface;
import mx.ine.computosINE.dao.DAOCEstatusInterface;
import mx.ine.computosINE.dao.DAODistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOCEstatus;

/**
 * <code>ASCargaInformacionImpl.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 24/04/2017 10:15:00
 */
@Scope("prototype")
@Service("asCargaInformacion")
public class ASCargaInformacionImpl implements ASCargaInformacionInterface, Serializable {

	private static final long serialVersionUID = -1096080154600586639L;

	private static final Log log = LogFactory.getLog(ASCargaInformacionImpl.class);

	@Autowired
	@Qualifier("hlpSecCasillas")
	private transient HLPSecCasillasInterface hlpSecCasillas;

	@Autowired
	@Qualifier("hlpCandidatos")
	private transient HLPCandidatosInterface hlpCandidatos;

	@Autowired
	@Qualifier("hlpGeograficos")
	private transient HLPGeograficosInterface hlpGeograficos;
	
    @Autowired
    @Qualifier("daoCEstatus")
    private DAOCEstatusInterface daoCEstatus;
    
    @Autowired
    @Qualifier("daoDistribucionVotos")
    private DAODistribucionVotosInterface daoDistribucionVotos;


	@Override
	@Transactional(readOnly = true, rollbackFor={Exception.class})
	public List<DTOCasillaWS> casillasAprobadasPorEntidad(Integer idEstado)
	{
		List<DTOCasillaWS> list = new ArrayList<>();
		try {
			list = daoDistribucionVotos.casillasAprobadasPorEntidad(idEstado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
    
    @Override
	public List<DTOCasillaWS> casillasAprobadasPorMunicipioLocal(Integer idProcesoElectoral, Integer idEstado,	Integer idMunicipioLocal) throws Exception {
		// TODO Auto-generated method stub
		return hlpSecCasillas.casillasAprobadasPorMunicipioLocal(idProcesoElectoral, idEstado, idMunicipioLocal);
	}

	@Override
	public List<DTOCasillaWS> casillasAprobadasPorDistritoLocal(Integer idProcesoElectoral, Integer idEstado,  Integer idDistritoLocal) throws Exception{
		// TODO Auto-generated method stub
		return hlpSecCasillas.casillasAprobadasPorDistritoLocal(idProcesoElectoral, idEstado, idDistritoLocal);
	}

	@Override
	public List<DTOCasillaWS> obtenSecCasillasAprobadasPorRegiduria(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipioLocal, Integer idRegiduria) throws Exception {
		List<DTOCasillaWS> aprobadas = new ArrayList<>();
		try
		{
			aprobadas = hlpSecCasillas.obtenSecCasillasAprobadasPorRegiduria(idProcesoElectoral, idEstado, idMunicipioLocal, idRegiduria);
		}
		catch (Exception e) {
			log.error("Error ASCargaInformacionImpl - estatusComputos()", e);
			e.printStackTrace();
		}
		return aprobadas;
	}

	@Override
	public List<DTOCandidatoWS> consumeAsociaciones(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipio, Integer idRegiduria, Integer tipoAsociacion) throws Exception {
		// TODO Auto-generated method stub
		log.info("ASCargaInformacionImpl.consumeAsociaciones");
		return hlpCandidatos.consultarAsociaciones(idProcesoElectoral, idEstado, idMunicipio, idRegiduria, tipoAsociacion);
	}

	@Override
	@Transactional(readOnly = true, rollbackFor={Exception.class})
	public List<DTOCEstatus> estatusComputos(){
		
		List<DTOCEstatus> list = new ArrayList<>();
		
		try {
		
			list = daoCEstatus.estatusComputos();
		
		} catch (Exception e) {
			log.error("Error ASCargaInformacionImpl - estatusComputos()", e);
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<DTOCandidatoWS> consumeTiposCandidaturas(Integer idDetalleProceso, Integer idEstado, Integer ambito) throws Exception{
		// TODO Auto-generated method stub
	    log.info("ASCargaInformacionImpl.consumeTiposCandidaturas");
	    return hlpCandidatos.consultarTiposCandidatura(idDetalleProceso, idEstado, ambito);
	}

	@Override
	public List<DTOCandidatoWS> consumeAsociacionesCoaliciones(Integer idProcesoElectoral, Integer idEstado, Integer idDistrito, Integer tipoCandidatura, Integer idMunicipio, Integer idRegiduria) throws Exception{
		// TODO Auto-generated method stub
	    log.info("ASCargaInformacionImpl.consumeAsociacionesCoaliciones");
	    return hlpCandidatos.consultarAsociacionesCoaliciones(idProcesoElectoral, idEstado, tipoCandidatura, idDistrito, idMunicipio, idRegiduria);
	}

	@Override
	public List<DTODistritosWS> obtenerCatalogoDistritos(Integer idEstado, EnumAmbitoDetalleProceso ambitoProceso) throws Exception {
		// TODO Auto-generated method stub
		log.info("ASCargaInformacionImpl.obtenerCatalogoDistritos");
		return hlpGeograficos.obtenerCatalogoDistritos(idEstado, ambitoProceso);
	}
	
	@Override
	public List<DTOMunicipiosWS> obtenerMunicipiosPorDistritoLocal(Integer idEstado, Integer idDistrito) throws Exception{
		// TODO Auto-generated method stub
		log.info("ASCargaInformacionImpl.obtenerMunicipiosPorDistritoLocal");
		return hlpGeograficos.obtenerMunicipiosPorDistritoLocal(idEstado, idDistrito);
	}
	
	@Override
	public String obtenerNombreDistrito(Integer idEstado, Integer idDistrito,  EnumAmbitoDetalleProceso ambitoProceso) throws Exception{
		// TODO Auto-generated method stub
		log.info("ASCargaInformacionImpl.obtenerNombreDistrito");
		return hlpGeograficos.obtenerNombreDistrito(idEstado, idDistrito, ambitoProceso);
	}
	
	@Override
	public List<DTOListaRegiduriasWS> consumeRegiduriasByMunicipio(Integer idEstado, Integer idMunicipio) throws Exception{
		log.info("ASCargaInformacionImpl.consumeRegiduriasByMunicipio");
	    return hlpGeograficos.regiduriasPorMunicipio(idEstado, idMunicipio);
	}
	
	@Override
	public List<DTOMunicipiosWS> obtenerCatalogoMunicipios(Integer idEstado, EnumAmbitoDetalleProceso ambitoProceso) throws Exception{
		log.info("ASCargaInformacionImpl.obtenerCatalogoMunicipios");
		return hlpGeograficos.obtenerCatalogoMunicipios(idEstado, ambitoProceso);
	}

	@Override
	public DTOEstadosWS obtenDetalleEstado(Integer idEstado)
			throws Exception {
		return hlpGeograficos.obtenerEstado(idEstado);
	}

	@Override
	public DTOMunicipiosWS obtenDetalleMunicipio(Integer idEstado,
			Integer idMunicipio, EnumAmbitoDetalleProceso ambitoProceso)
			throws Exception {
		return hlpGeograficos.obtenerMunicipio(idEstado, idMunicipio, ambitoProceso);
	}

}
