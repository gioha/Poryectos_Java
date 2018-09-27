package mx.ine.computosINE.as;

import java.util.List;

import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;

/**
 * <code>ASCapturaVotosInterface.java</code>Descripcion de la clase
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 08/05/2017 
 */
public interface ASCapturaVotosInterface {

	/**
	 * Obtiene las casillas capturadas por entidad
	 */
	public List<DTOActaCasillaVotos> getActasEntidad(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas ordenadas por secciones
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnSecciones(DTOActaCasillaVotosPK id) throws Exception;
	
	public List<DTOActaCasillaVotos> getActasMunicipioEnSeccionesParaDistribucion(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas ordenadas por Distritos y Secciones
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnDistritosSecciones(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas parciales capturadas por distrito
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer getActasParcialesCapturadasEnDistrito(DTODistribucionCandParcialPK id) throws Exception;
	
	
	/**
	 * Obtiene una lista de casillas capturadas por entidad
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasCapturadasxEntidadCandidatura(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas capturadas por distrito
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasCapturadasEnDistrito(DTOActaCasillaVotosPK id) throws Exception;
	
	
	/**
	 * Obtiene una lista de casillas capturadas en una Regiduría
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegidurias(DTOActaCasillaVotosPK id) throws Exception;
	
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasParaDistribucion(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas ordenadas por Regidurias y Secciones
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasSecciones(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
 	 * Devuelve las subcoaliciones de una lista de coaliciones dada
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOSubcoalicion> recuperaSubcoaliciones(List<DTOAsociacion> coaliciones) throws Exception;
	
	/**
 	 * Inserta en Base de Datos los votos de asociaciones capturados en pantalla
	 * @param votosActa es de tipo List<DTOActaCasillaVotos>
	 * @return void
	 * @throws Exception
	 */
	public void guardaVotosDeActa(List<DTOActaCasillaVotos> votosActa) throws Exception;
	
	/**
	 * Obtiene los votos de las asociaciones de un acta
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getvotosAsociacionesActa(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Recupera todos los registros que representan un acta capturada.
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> consultaActa (DTOActaCasillaVotosPK id)	throws Exception;
	
	
	/**
 	 * elimina de BD los registros de un acta
	 * @param acta es de tipo List<DTOActaCasillaVotos>
	 * @return void
	 * @throws Exception
	 */
	public void eliminarActa(List<DTOActaCasillaVotos> acta) throws Exception;
}
