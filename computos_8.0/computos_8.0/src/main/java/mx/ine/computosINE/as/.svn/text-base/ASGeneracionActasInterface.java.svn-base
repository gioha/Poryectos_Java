package mx.ine.computosINE.as;

import java.util.List;

import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCConsejo;
import mx.ine.computosINE.dto.DTOConsejero;
import mx.ine.computosINE.dto.DTORepresentante;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.org.ine.servicios.dto.DTOBase;

public interface ASGeneracionActasInterface {

	/**
	 * Guarda o actualiza un acta
	 * @param acta - El objeto que representa al acta
	 * @throws Exception 
	 */
	public void guardaActualizaActa(DTOBase acta) throws Exception;
	
	/**
	 * Elimina un acta 
	 * @param acta - El objeto que representa al acta.
	 */
	public void eliminaActa(DTOActaTipoCandidatura acta);
	
	/**
	 * Guarda un representante
	 * @param representante - EL objeto que representa al repre.
	 */
	public void guardaRepresentante(DTORepresentante representante) throws Exception;

	/**
	 * Guarda un consejero
	 * @param consejero
	 */
	public void guardaConsejero(DTOConsejero consejero) throws Exception;

	/**
	 * Obtiene el catalogo de consejeros
	 * @return El catalogo de los tipos de consejeros
	 */
	public List<DTOCConsejo> getCatalogoConsejeros();

	/**
	 * 
	 * Obtiene un acta dada su pk.
	 * 
	 * @param pkActa
	 * @return
	 */
	public DTOBase getActa(Object pkActa);

	/**
	 * Obtiene la lista de consejeros asociada al acta
	 * @param pkActa - La pk del acta.
	 * @return List<DTOConsejero> - La lista de consejeros.
	 */
	public List<DTOConsejero> getConsejeros(Object pkActa);

	/**
	 * Obtiene la lista de representantes asociados al acta
	 * @param pkActa - La pk del acta
	 * @return List<DTORepresentante> - La lista de representantes.
	 */
	public List<DTORepresentante> getRepresentantes(
			Object pkActa);
	
	/**
	 * Cambia el campo editable
	 * @param pkActa
	 */
	public void setEditable(DTOActaTipoCandidaturaPK pkActa) throws Exception;
	
	
	/**
	 * Obtiene la PK del acta y elimina representantes, consejeros
	 * y el acta misma.
	 * @param pkActa
	 */
	public void eliminandoActa(DTOActaTipoCandidaturaPK pkActa);
	
	
	/**
	 * Elimina consejeros
	 * 
	 * @param acta
	 */
	public void eliminaConsejeros(List<DTOConsejero> consejeros);
	
	
	/**
	 * Elimina representantes
	 * 
	 * @param acta
	 */
	public void eliminaRepresentantes(List<DTORepresentante> representantes);
	
	/**
	 * Consulta los distritos que le correponden a cada municipio
	 * en ACTA_CASILLA_VOTOS para DIPUTADOS_RP en la generación del acta de OPLE
	 * @param idTipoCandidatura
	 * @return List<DTOActaTipoCasillaVotos>
	 */
	public List<Object[]> consultaDistritosConSusMunicipios(Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * Consulta ACTA_CASILLA_VOTOS para obtener idAsociacion de todos los partidos
	 * para ACTA DIPUTADOS RP ESTATAL
	 */
	public List<Integer> consultaIdAsociacionesDipRPEstatal() throws Exception;
	
	
	/**
	 * Consulta ACTA_CASILLA_VOTOS para obtener distritosPorMunicipioo de acuerdo al tipo de candidatura
	 * de Diputados
	 */
	public List<Integer> consultaDistritosLocalesPorMunicipio(DTOUsuarioLogin usuario) throws Exception;
	
	
	/**
	 * Lista de demarcaciones capturadas de MR para poder mostrar RP posibles por capturar
	 */
	public List<Integer> consultaDemarcacionesPorMR(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer idSeleccion) throws Exception;
	
	
	/**
	 * Lista de distritos capturadas de MR para poder mostrar RP posibles por capturar
	 */
	public List<Integer> consultaDistritosPorMR(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer idSeleccion) throws Exception;
	
	
	/**
	 * Validacion de que el id regiduria y id distrito tienen casilla especial
	 */
	public boolean tieneEspeciales(Integer idTipoCandidatura, Integer idSeleccion);
	
	/**
	 * Validación de que esten todas las actas finales capturadas para Diputados RP
	 */
	public boolean isGeneradasDiputadosRPFinal();
	
}
