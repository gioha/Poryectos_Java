package mx.ine.computosINE.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla ACTA_CASILLA_VOTOS del esquema de COMPUTOSINE.
 * 
 * @author Giovanni Hernandez Alonso
 * @since Abril-2016
 * @ver
 **/

@Embeddable
public class DTOActaCasillaVotosPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 574015328920601160L;

	@Column(name="ID_PROCESO_ELECTORAL", nullable = false)
	private Integer idProcesoElectoral;
	
	@Column(name="ID_DETALLE_PROCESO", nullable = false)
	private Integer idDetalleProceso;
	
	@Column(name="ID_ESTADO", nullable = false)
	private Integer idEstado;
	
	@Column(name="ID_DISTRITO", nullable = false)
	private Integer idDistrito;
	
	@Column(name="ID_MUNICIPIO", nullable = false)
	private Integer idMunicipio;
	
	@Column(name="ID_REGIDURIA", nullable = false)
	private Integer idRegiduria;
	
	@Column(name="SECCION", nullable = false)
	private Integer seccion;
	
	@Column(name="ID_COMUNIDAD", nullable = false)
	private Integer idComunidad;
	
	@Column(name="ID_CASILLA", nullable = false)
	private Integer idCasilla;
	
	@Column(name="TIPO_CASILLA", nullable = false)
	private String tipoCasilla;
	
	@Column(name="EXT_CONTIGUA", nullable = false)
	private Integer extContigua;
	
	@Column(name="ID_TIPO_CANDIDATURA", nullable = false)
	private Integer idTipoCandidatura;

	@Column(name="ID_ASOCIACION", nullable = false)
	private Integer idAsociacion;

	@Column(name="TIPO_ASOCIACION", nullable = false)
	private Integer tipoAsociacion;

	@Column(name="ID_COALICION", nullable = false)
	private Integer idCoalicion;

	public DTOActaCasillaVotosPK(){
		
		idProcesoElectoral 	= new Integer(0);
		idDetalleProceso 	= new Integer(0);
	    idEstado			= new Integer(0);
		idDistrito 			= new Integer(0);
		idMunicipio 		= new Integer(0);
		idRegiduria 		= new Integer(0);
		seccion 			= new Integer(0);
		idComunidad 		= new Integer(0);
		idCasilla 			= new Integer(0);
		tipoCasilla 		= "" ;
		extContigua 		= new Integer(0);
		idTipoCandidatura 	= new Integer(0);
		idAsociacion 		= new Integer(0);
		tipoAsociacion		= new Integer(0);
		idCoalicion			= new Integer(0);
		
	}

	/**
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idDistrito
	 * @param idMunicipio
	 * @param idRegiduria
	 * @param seccion
	 * @param idComunidad
	 * @param idCasilla
	 * @param tipoCasilla
	 * @param extContigua
	 * @param idTipoCandidatura
	 * @param idAsociacion
	 * @param tipoAsociacion
	 */
	public DTOActaCasillaVotosPK(Integer idProcesoElectoral, Integer idDetalleProceso, Integer idEstado,
			Integer idDistrito, Integer idMunicipio, Integer idRegiduria, Integer seccion, Integer idComunidad,
			Integer idCasilla, String tipoCasilla, Integer extContigua, Integer idTipoCandidatura,
			Integer idAsociacion, Integer tipoAsociacion) {
		super();
		this.idProcesoElectoral = idProcesoElectoral;
		this.idDetalleProceso = idDetalleProceso;
		this.idEstado = idEstado;
		this.idDistrito = idDistrito;
		this.idMunicipio = idMunicipio;
		this.idRegiduria = idRegiduria;
		this.seccion = seccion;
		this.idComunidad = idComunidad;
		this.idCasilla = idCasilla;
		this.tipoCasilla = tipoCasilla;
		this.extContigua = extContigua;
		this.idTipoCandidatura = idTipoCandidatura;
		this.idAsociacion = idAsociacion;
		this.tipoAsociacion = tipoAsociacion;
	}

	public Integer getIdProcesoElectoral() {
		return idProcesoElectoral;
	}

	public void setIdProcesoElectoral(Integer idProcesoElectoral) {
		this.idProcesoElectoral = idProcesoElectoral;
	}

	public Integer getIdDetalleProceso() {
		return idDetalleProceso;
	}

	public void setIdDetalleProceso(Integer idDetalleProceso) {
		this.idDetalleProceso = idDetalleProceso;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdRegiduria() {
		return idRegiduria;
	}

	public void setIdRegiduria(Integer idRegiduria) {
		this.idRegiduria = idRegiduria;
	}

	public Integer getSeccion() {
		return seccion;
	}

	public void setSeccion(Integer seccion) {
		this.seccion = seccion;
	}

	public Integer getIdComunidad() {
		return idComunidad;
	}

	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
	}

	public Integer getIdCasilla() {
		return idCasilla;
	}

	public void setIdCasilla(Integer idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(String tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}

	public Integer getExtContigua() {
		return extContigua;
	}

	public void setExtContigua(Integer extContigua) {
		this.extContigua = extContigua;
	}

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	public Integer getIdAsociacion() {
		return idAsociacion;
	}

	public void setIdAsociacion(Integer idAsociacion) {
		this.idAsociacion = idAsociacion;
	}

	/**
	 * @return the tipoAsociacion
	 */
	public Integer getTipoAsociacion() {
		return tipoAsociacion;
	}
	/**
	 * @param tipoAsociacion the tipoAsociacion to set
	 */
	public void setTipoAsociacion(Integer tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}
	/**
	 * @return the idCoalicion
	 */
	public Integer getIdCoalicion() {
		return idCoalicion;
	}
	/**
	 * @param idCoalicion the idCoalicion to set
	 */
	public void setIdCoalicion(Integer idCoalicion) {
		this.idCoalicion = idCoalicion;
	}

	/**
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idDistrito
	 * @param idMunicipio
	 * @param idRegiduria
	 * @param seccion
	 * @param idComunidad
	 * @param idCasilla
	 * @param tipoCasilla
	 * @param extContigua
	 * @param idTipoCandidatura
	 * @param idAsociacion
	 * @param tipoAsociacion
	 * @param idCoalicion
	 */
	public DTOActaCasillaVotosPK(Integer idProcesoElectoral,
			Integer idDetalleProceso, Integer idEstado, Integer idDistrito,
			Integer idMunicipio, Integer idRegiduria, Integer seccion,
			Integer idComunidad, Integer idCasilla, String tipoCasilla,
			Integer extContigua, Integer idTipoCandidatura,
			Integer idAsociacion, Integer tipoAsociacion, Integer idCoalicion) {

		this.idProcesoElectoral = idProcesoElectoral;
		this.idDetalleProceso = idDetalleProceso;
		this.idEstado = idEstado;
		this.idDistrito = idDistrito;
		this.idMunicipio = idMunicipio;
		this.idRegiduria = idRegiduria;
		this.seccion = seccion;
		this.idComunidad = idComunidad;
		this.idCasilla = idCasilla;
		this.tipoCasilla = tipoCasilla;
		this.extContigua = extContigua;
		this.idTipoCandidatura = idTipoCandidatura;
		this.idAsociacion = idAsociacion;
		this.tipoAsociacion = tipoAsociacion;
		this.idCoalicion = idCoalicion;
	}
}
