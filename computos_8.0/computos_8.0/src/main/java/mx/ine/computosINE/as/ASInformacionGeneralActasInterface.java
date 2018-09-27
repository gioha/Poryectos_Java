package mx.ine.computosINE.as;

import java.math.BigDecimal;
import java.util.List;

import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;

/**
 * <code>ASInformacionGeneralActasInterface.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 06/05/2017 13:40:00
 */
public interface ASInformacionGeneralActasInterface {
	
	/**
	 * Obtiene el numero de actas capturadas
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCountActasCapturadasByCandidatura(Object [] arg0) throws Exception;
	
	/**
	 * Obtiene el numero de actas capturadas por demarcacion
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCountActasCapturadasByDemarcacion(Object[] arg0) throws Exception;
	
	/**
	 * Obtiene el numero de actas capturadas por el tipo de Candidatura y por municipio
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getActasCapturadasxMunicipioCandidatura(Object [] arg0) throws Exception;
	
	/**
	 * Obtiene el total de municipios del estado que ya tienen actas capturadas
	 * @param idProceso
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public Integer getTotalMunicipiosActasCapturadas(Integer idProceso, Integer idDetalleProceso,
            Integer idEstado, Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * Consulta la informacion de los municipios y distritos que ya tuvieron actas capturadas
	 * @param idProceso
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> consultaActasCaptByMunDistrito(Integer idProceso, Integer idDetalleProceso,
            Integer idEstado, Integer idTipoCandidatura) throws Exception;
	
	
	 /**
	  * Obtiene el acumulado de votos nulos y candidatos no registrados por tipo de candidatura. </BR>
	  * arg0[idProcesoElectoral, idDetalleProceso, idEstado, idDistrito,
	  * idMunicipio, idRegiduria, idTipoCandidatura, tipoAsociacion, idAsociacion]
	  * @param id
	  * @return
	  * @throws Exception
	  */
    public BigDecimal getTotalVotosNulosCNR(DTOActaCasillaVotosPK id) throws Exception;
    
	/**
	 * Obtiene la suma de votos por cada partido 
	 * este metodo se tiene contemplado solo para el tipo de candidatura
	 * Diputados RP y Diputados MR
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPActaCasillaVotos> getTotalVotosActaDiputados(Object [] arg0) throws Exception ;
	
	
	/**
	 * Obtiene la suma de votos por cada partido 
	 * este metodo se tiene contemplado solo para el tipo de candidatura
	 * Gobernador , porque es a nivel municipio
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPActaCasillaVotos> getTotalVotosActaGobernador(Object [] arg0) throws Exception;
	
	/**
	 * Obtiene la suma de votos por regiduria mr, para el acta de regiduria rp final
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaMR(Object[] arg0) throws Exception;
	
	/**
	 * Obtiene la suma de votos por regiduria rp, es decir solo se contemplan casillas especiales
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRP(Object[] arg0) throws Exception;
	
	
	/**
	 * Obtiene la suma de votos por regiduria mr, esto aplica cuando es un acta final de diputados rp
	 * aunque no se tengan casillas especiales
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosDiputadosMR(Object[] arg0) throws Exception;
	
	
	
	/**
	 * Obtiene la suma de votos por regiduria rp, es decir solo se contemplan casillas especiales,
	 * esto aplica solo para cuando es un acta parcial
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRPParcial(Object[] arg0)throws Exception;
	
	 /**
	  * Nos dice si las casillas tienen el estatus de recuento parcial
	  * @param idTipoCandidatura
	  * @return
	  * @throws Exception
	  */
	 public boolean esRecuentoParcial(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion) throws Exception;

	 /**
	  * Nos dice si las casillas tienen el estatus de recuento total
	  * @param idTipoCandidatura
	  * @return
	  * @throws Exception
	  */
	 public boolean esRecuentoTotal(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion) throws Exception;


}
