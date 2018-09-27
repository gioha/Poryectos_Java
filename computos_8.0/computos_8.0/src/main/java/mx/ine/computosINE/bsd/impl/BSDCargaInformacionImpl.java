/**
 * 
 */
package mx.ine.computosINE.bsd.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTOEstadosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOMunicipiosWS;
import mx.ine.computosINE.as.ASCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.dto.DTOCEstatus;

/**
 * <code>BSDCargaInformacionImpl.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 24/04/2017 11:20:00
 */
@Component("bsdCargaInformacion")
@Scope("prototype")
public class BSDCargaInformacionImpl implements BSDCargaInformacionInterface, Serializable {

	private static final long serialVersionUID = -2161147868065280666L;

	private static final Log log = LogFactory.getLog(BSDCargaInformacionImpl.class);

    @Autowired
    @Qualifier("asCargaInformacion")
    private ASCargaInformacionInterface asCargaInformacion;

    @Override
    public List<DTOCasillaWS> casillasAprobadasPorEntidad(Integer idEstado) throws Exception
    {
    	return asCargaInformacion.casillasAprobadasPorEntidad(idEstado);
    }
    
    @Override
	public List<DTOCasillaWS> casillasAprobadasPorMunicipioLocal(Integer idProcesoElectoral, Integer idEstado,	Integer idMunicipioLocal) throws Exception {
		// TODO Auto-generated method stub
		log.info("BSDCargaInformacionImpl.casillasAprobadasPorMunicipioLocal");
		return asCargaInformacion.casillasAprobadasPorMunicipioLocal(idProcesoElectoral, idEstado, idMunicipioLocal);
	}
    
    
    public List<DTOCasillaWS> casillasAprobadasPorDistritoLocal(Integer idProcesoElectoral, Integer idEstado,  Integer idDistritoLocal) throws Exception{
    	// TODO Auto-generated method stub
    	log.info("BSDCargaInformacionImpl.casillasAprobadasPorDistritoLocal");
    	return asCargaInformacion.casillasAprobadasPorDistritoLocal(idProcesoElectoral, idEstado, idDistritoLocal);
    }

	@Override
	public List<DTOCasillaWS> obtenSecCasillasAprobadasPorRegiduria(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipioLocal, Integer idRegiduria) throws Exception {
		// TODO Auto-generated method stub
		return asCargaInformacion.obtenSecCasillasAprobadasPorRegiduria(idProcesoElectoral, idEstado, idMunicipioLocal, idRegiduria);
	}

	@Override
	public List<DTOCandidatoWS> consumeAsociaciones(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipio, Integer idRegiduria, Integer tipoAsociacion) throws Exception {
		// TODO Auto-generated method stub
		log.info("BSDCargaInformacionImpl.consumeAsociaciones");
		return asCargaInformacion.consumeAsociaciones(idProcesoElectoral, idEstado, idMunicipio, idRegiduria, tipoAsociacion);
	}

	@Override
	public List<DTOCEstatus> estatusComputos() throws Exception {
		log.info("BSDCargaInformacionImpl.estatusComputos");
		return asCargaInformacion.estatusComputos();
	}
	
	public List<DTOCandidatoWS> consumeTiposCandidaturas(Integer idDetalleProceso, Integer idEstado, Integer ambito) throws Exception{
		// TODO Auto-generated method stub
		log.info("BSDCargaInformacionImpl.consumeTiposCandidaturas");
		return asCargaInformacion.consumeTiposCandidaturas(idDetalleProceso, idEstado, ambito);
	}
	
	public List<DTOCandidatoWS> consumeAsociacionesCoaliciones(Integer idProcesoElectoral, Integer idEstado, Integer idDistrito, Integer tipoCandidatura, Integer idMunicipio, Integer idRegiduria) throws Exception{
		// TODO Auto-generated method stub
		log.info("BSDCargaInformacionImpl.consumeAsociacionesCoaliciones");
		return asCargaInformacion.consumeAsociacionesCoaliciones(idProcesoElectoral, idEstado, idDistrito, tipoCandidatura, idMunicipio, idRegiduria);
	}

	@Override
	public List<DTODistritosWS> obtenerCatalogoDistritos(Integer idEstado,	EnumAmbitoDetalleProceso ambitoProceso) throws Exception {
		// TODO Auto-generated method stub
		return asCargaInformacion.obtenerCatalogoDistritos(idEstado, ambitoProceso);
	}
	
	@Override
	public List<DTOMunicipiosWS> obtenerMunicipiosPorDistritoLocal(Integer idEstado, Integer idDistrito) throws Exception{
		// TODO Auto-generated method stub
		return asCargaInformacion.obtenerMunicipiosPorDistritoLocal(idEstado, idDistrito);
	}
	
	
	@Override
	public String obtenerNombreDistrito(Integer idEstado, Integer idDistrito,  EnumAmbitoDetalleProceso ambitoProceso) throws Exception{
		// TODO Auto-generated method stub
		return asCargaInformacion.obtenerNombreDistrito(idEstado, idDistrito, ambitoProceso);
	}
	
	public List<DTOListaRegiduriasWS> consumeRegiduriasByMunicipio(Integer idEstado, Integer idMunicipio) throws Exception{
		log.info("BSDCargaInformacionImpl.consumeRegiduriasByMunicipio");
		return asCargaInformacion.consumeRegiduriasByMunicipio(idEstado, idMunicipio);
	}
	
	public List<DTOMunicipiosWS> obtenerCatalogoMunicipios(Integer idEstado, EnumAmbitoDetalleProceso ambitoProceso) throws Exception{
		log.info("BSDCargaInformacionImpl.obtenerCatalogoMunicipios");
		return asCargaInformacion.obtenerCatalogoMunicipios(idEstado, ambitoProceso);
	}


	@Override
	public DTOEstadosWS obtenDetalleEstado(Integer idEstado)
			throws Exception {
		return asCargaInformacion.obtenDetalleEstado(idEstado);
	}


	@Override
	public DTOMunicipiosWS obtenDetalleMunicipio(Integer idEstado,
			Integer idMunicipio, EnumAmbitoDetalleProceso ambitoProceso)
			throws Exception {
		return asCargaInformacion.obtenDetalleMunicipio(idEstado,idMunicipio,ambitoProceso);
	}

}
