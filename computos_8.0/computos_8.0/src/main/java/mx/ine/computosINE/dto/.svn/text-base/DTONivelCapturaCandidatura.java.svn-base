package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * -----------------------------------------------------------------
 * DTONivelCaptutaCandidatura -- Capturar el nivel de la candidatura: 
 * 								Presidente, gobernador, diputados,
 * 							 	municipal, regiduría...
 * ----------------------------------------------------------------- 
 * @author JOSE GERARDO LOPEZ ROLDAN
 *
 */

@Entity
@Table(name = "COMPUTOSINE.NIVEL_CAPTURA_CANDIDATURA")
@NamedQuery(name = "DTONivelCapturaCandidatura.findAll", query = "SELECT d FROM DTONivelCapturaCandidatura d")
public class DTONivelCapturaCandidatura extends DTOBase implements Serializable {

	
	private static final long serialVersionUID = -3038672327632672335L;
	
	@EmbeddedId
	private DTONivelCapturaCandidaturaPK id;
	
	@ManyToOne
	@JoinColumn(name = "ID_NIVEL_CAPTURA", referencedColumnName = "ID_NIVEL_CAPTURA", insertable = false, updatable = false)
	private DTOCNivelCaptura idNivelCaptura;
	
	@Column(name = "USUARIO", nullable = false, length = 100)
	private String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;
	
	
	public DTONivelCapturaCandidatura(DTONivelCapturaCandidaturaPK id, DTOCNivelCaptura idNivelCaptura, String usuario,
			Date fechaHora) {
		super();
		this.id = id;
		this.idNivelCaptura = idNivelCaptura;
		this.usuario = usuario;
		this.fechaHora = fechaHora;
	}

	public DTONivelCapturaCandidaturaPK getId() {
		return id;
	}

	public void setId(DTONivelCapturaCandidaturaPK id) {
		this.id = id;
	}

	public DTOCNivelCaptura getIdNivelCaptura() {
		return idNivelCaptura;
	}

	public void setIdNivelCaptura(DTOCNivelCaptura idNivelCaptura) {
		this.idNivelCaptura = idNivelCaptura;
	}

	@Override
	public String getUsuario() {
		return usuario;
	}
	
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

}
