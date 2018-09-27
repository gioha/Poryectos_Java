package mx.ine.computosINE.as;

import java.util.List;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTOEstadosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOMunicipiosWS;
import mx.ine.computosINE.dto.DTOCEstatus;

/**
 * <code>ASCargaInformacionActaInterface.java</code>Descripcion de la interface
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 24/04/2017 11:00:57
 */
public interface ASCargaInformacionInterface {

	/**
	 * Casillas aprobadas por entidad
	 */
	public List<DTOCasillaWS> casillasAprobadasPorEntidad(Integer idEstado) throws Exception;
	
	/**
	 * Consume informacion de secciones y casillas por municipio
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipioLocal
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaWS> casillasAprobadasPorMunicipioLocal(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipioLocal) throws Exception;
	
	
	/**
	 * Consume informacion de secciones y casillas por distrito local
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idDistritoLocal
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaWS> casillasAprobadasPorDistritoLocal(Integer idProcesoElectoral, Integer idEstado,  Integer idDistritoLocal) throws Exception;

	/**
	 * Consume informacion de las secciones y casillas aprobadas por el consejo para regidurias
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipioLocal
	 * @param idRegiduria
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaWS> obtenSecCasillasAprobadasPorRegiduria(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipioLocal, Integer idRegiduria) throws Exception;

	/**
	 * Consume la informacion de las asociaciones de acuerdo con un proceso electoral
	 * Nota: tipo asociacion:  Indica el tipo de Asociacion Politica registrada en el sistema.
	 * y puede tomar los siguientes valores: 1.- PARTIDO, 2.- AGRUPACION 3.-COALICION 4.-INDEPENDIENTE.
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipio
	 * @param idRegiduria
	 * @param tipoAsociacion
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidatoWS> consumeAsociaciones(Integer idProcesoElectoral, Integer idEstado, Integer idMunicipio, Integer idRegiduria, Integer tipoAsociacion) throws Exception;
	
	/**
	 * Recupera todos lo estatus dados de alta para el sistema de computos,
	 * estos esatus estan dados de alta en el esquema de computos
	 * @return List<DTOCEstatus>
	 * @throws Exception
	 */
	public List<DTOCEstatus> estatusComputos() throws Exception;
	
	
	/**
	 * Consume la informacion de los tipos de candidaturas 
	 * Nota: tipo candidatura:  Indica el tipo de Cantidatura presente dentro del proceso electoral.
	 * y puede tomar los siguientes valores: 7.- DIPUTADO LOCAL MR, 8.- DIPUTADO LOCAL RP  6.-GOBERNADOR ESTATAL 9.-PRESIDENTE MUNICIPAL
	 * 15.- REGIDOR FISCALIZABLE MR  16.- REGIDOR FISCALIZABLE RP .
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param ambito
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidatoWS> consumeTiposCandidaturas(Integer idDetalleProceso, Integer idEstado, Integer ambito) throws Exception;

	
	/**
	 * Consume la informacion de las asociaciones de acuerdo con un proceso electoral
	 * Nota: tipo asociacion:  Indica el tipo de Asociacion Politica registrada en el sistema.
	 * y puede tomar los siguientes valores: 1.- PARTIDO, 2.- AGRUPACION 3.-COALICION 4.-INDEPENDIENTE.
	 * @param idProcesoElectoral
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idDistrito
	 * @param tipoCandidatura
	 * @param idMunicipio
	 * @param idRegiduria
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidatoWS> consumeAsociacionesCoaliciones(Integer idProcesoElectoral, Integer idEstado, Integer idDistrito, Integer tipoCandidatura, Integer idMunicipio, Integer idRegiduria) throws Exception;

	/**
	 * Obtiene los distritos en base a un ambito, donde ambito puede tomar los siguientes valores
	 * ambito: L('L', "Local")  F('F', "Federal")
	 * @param idEstado
	 * @param ambitoProceso
	 * @return
	 * @throws Exception
	 */
	public List<DTODistritosWS> obtenerCatalogoDistritos(Integer idEstado, EnumAmbitoDetalleProceso ambitoProceso) throws Exception; 
	
	
	/**
	 * Consume la información de los municipios por distrito local
	 * @param idEstado
	 * @param idDistrito
	 * @return List<DTOMunicipiosWS>
	 * @throws Exception
	 */
	public List<DTOMunicipiosWS> obtenerMunicipiosPorDistritoLocal(Integer idEstado, Integer idDistrito) throws Exception;
	
	
	/**
	 * Obtiene el nombre del distrito
	 * @param idEstado
	 * @param idDistrito
	 * @param ambitoProceso
	 * @return String
	 * @throws Exception
	 */
	public String obtenerNombreDistrito(Integer idEstado, Integer idDistrito,  EnumAmbitoDetalleProceso ambitoProceso) throws Exception;
	
	/**
	 * Consume la información de las Regidurias a nivel de municipio
	 * @param idEstado
	 * @param idMunicipio
	 * @return List<DTOListaRegiduriasWS>
	 * @throws Exception
	 */
	public List<DTOListaRegiduriasWS> consumeRegiduriasByMunicipio(Integer idEstado, Integer idMunicipio) throws Exception;
	
	/**
	 * Consume la información de los municipios por estado y por ambito del proceso
	 * @param idEstado
	 * @param ambitoProceso
	 * @return List<DTOMunicipiosWS>
	 * @throws Exception
	 */
	public List<DTOMunicipiosWS> obtenerCatalogoMunicipios(Integer idEstado, EnumAmbitoDetalleProceso ambitoProceso) throws Exception;
	
	/**
	 * 
	 * @param idEstado
	 * @return
	 * @throws Exception
	 */
	public DTOEstadosWS obtenDetalleEstado(Integer idEstado) throws Exception;
	
	/**
	 * 
	 * @param idEstado
	 * @param idMunicipio
	 * @param ambitoProceso
	 * @return
	 * @throws Exception
	 */
	public DTOMunicipiosWS obtenDetalleMunicipio(Integer idEstado,Integer idMunicipio, EnumAmbitoDetalleProceso ambitoProceso) throws Exception;

}
