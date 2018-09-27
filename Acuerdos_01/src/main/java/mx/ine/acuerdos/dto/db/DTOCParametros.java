package mx.ine.acuerdos.dto.db;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Clase que mapea la tabla C_PARAMETROS de la base de datos
 * 
 * @author Isabel Espinoza Espinoza (isabel.espinozae@ine.mx)
 * @author Isabel Espinoza Espinoza (isabel.espinozae@ine.mx)-10/10/16
 *         Actualización de mapeo
 * @version 1.0
 * @since 17/08/2016
 */
@Entity
@Table(name = "C_PARAMETROS", schema = "SUPYCAP")
public class DTOCParametros implements Serializable {
	/**
	 * Número de versión serializable
	 */
	private static final long serialVersionUID = 1392263230368565561L;
	@Id
	@NotNull
	@Column(name = "ID_PROCESO_ELECTORAL", nullable = false, precision = 5, scale = 0)
	private Integer idProcesoElectoral;
	@Id
	@NotNull
	@Column(name = "ID_ESTADO", nullable = false, precision = 2, scale = 0)
	private Integer idEstado;
	@Id
	@NotNull
	@Column(name = "ID_DISTRITO", nullable = false, precision = 2, scale = 0)
	private Integer idDistrito;
	@Id
	@NotNull
	@Column(name = "ID_MUNICIPIO", nullable = false, precision = 3, scale = 0)
	private Integer idMunicipio;
	@Id
	@NotNull
	@Column(name = "ID_LOCALIDAD", precision = 4, scale = 0)
	private Integer idLocalidad;
	@Id
	@NotNull
	@Column(name = "ID_COMUNIDAD", precision = 4, scale = 0)
	private Integer idComunidad;
	@Id
	@NotNull
	@Column(name = "TIPO_JUNTA", nullable = false, length = 2)
	private String tipoJunta;
	@Id
	@NotNull
	@Column(name = "ID_PARAMETRO", nullable = false, precision = 2, scale = 0)
	private Integer idParametro;
	@NotNull
	@Column(name = "VALOR_PARAMETRO", nullable = false, precision = 2, scale = 0)
	private Integer valorParametro;

	/**
	 * @return el atributo idProcesoElectoral
	 */
	public Integer getIdProcesoElectoral() {
		return idProcesoElectoral;
	}

	/**
	 * @param idProcesoElectoral
	 *            parametro idProcesoElectoral a actualizar
	 */
	public void setIdProcesoElectoral(Integer idProcesoElectoral) {
		this.idProcesoElectoral = idProcesoElectoral;
	}

	/**
	 * @return el atributo idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado
	 *            parametro idEstado a actualizar
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return el atributo idDistrito
	 */
	public Integer getIdDistrito() {
		return idDistrito;
	}

	/**
	 * @param idDistrito
	 *            parametro idDistritoFed a actualizar
	 */
	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	/**
	 * @return el atributo idMunicipio
	 */
	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio
	 *            parametro idMunicipioFed a actualizar
	 */
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return el atributo idLocalidad
	 */
	public Integer getIdLocalidad() {
		return idLocalidad;
	}

	/**
	 * @param idLocalidad
	 *            parametro idLocalidadFed a actualizar
	 */
	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	/**
	 * @return el atributo tipoJunta
	 */
	public String getTipoJunta() {
		return tipoJunta;
	}

	/**
	 * @param tipoJunta
	 *            parametro tipoJunta a actualizar
	 */
	public void setTipoJunta(String tipoJunta) {
		this.tipoJunta = tipoJunta;
	}

	/**
	 * @return el atributo idParametro
	 */
	public Integer getIdParametro() {
		return idParametro;
	}

	/**
	 * @param idParametro
	 *            parametro idParametro a actualizar
	 */
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	/**
	 * @return el atributo valorParametro
	 */
	public Integer getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param valorParametro
	 *            parametro valorParametro a actualizar
	 */
	public void setValorParametro(Integer valorParametro) {
		this.valorParametro = valorParametro;
	}

	/**
	 * @return el atributo idComunidad
	 */
	public Integer getIdComunidad() {
		return idComunidad;
	}

	/**
	 * @param idComunidad
	 *            parametro idComunidad a actualizar
	 */
	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOCParametros) ) {
			DTOCParametros dtoCParametros = (DTOCParametros) obj;
			if( idProcesoElectoral.equals(dtoCParametros.idProcesoElectoral) &&
				idEstado.equals(dtoCParametros.idEstado) &&
				idDistrito.equals(dtoCParametros.idDistrito) &&
				idMunicipio.equals(dtoCParametros.idMunicipio) &&
				idLocalidad.equals(dtoCParametros.idLocalidad) &&
				idComunidad.equals(dtoCParametros.idComunidad) &&
				tipoJunta.equals(dtoCParametros.tipoJunta) &&
				idParametro.equals(dtoCParametros.idParametro) &&
				valorParametro.equals(dtoCParametros.valorParametro) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProcesoElectoral, idEstado, idDistrito, idMunicipio, idLocalidad, idComunidad, tipoJunta, idParametro, valorParametro);
	}

}
