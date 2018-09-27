package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * ---------------------------------------------------------------------
 * DTOAccesosSistema -- Persistencia en inicio de sesiones
 * 						para tener una estadistica y conocimiento
 * 						de quien entra al sistema.
 * ---------------------------------------------------------------------
 * @author JOSE GERARDO LOPEZ ROLDAN
 *
 */

@Entity
@Table(name = "COMPUTOSINE.ACCESOS_SISTEMA")
@NamedQuery(name = "DTOAccesosSistema.findAll", query = "SELECT d FROM DTOAccesosSistema d")
public class DTOAccesosSistema extends DTOBase implements Serializable{
	
	private static final long serialVersionUID = -3042783875130854016L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MY_SEQ_GEN")
	@SequenceGenerator(name = "MY_SEQ_GEN", sequenceName = "S_ACCESOS_SISTEMA")
	@Column(name = "ID_ACCESO_SISTEMA", nullable = false, precision = 7, scale = 0)
	public Integer idAccesoSistema;
	
	@Column(name = "USUARIO", nullable = false, length = 50)
	public String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA_ENTRADA", nullable = false)
	public Date fechaHoraEntrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA_SALIDA", nullable = false)
	public Date fechaHoraSalida;
	
	
	public DTOAccesosSistema(Integer idAccesoSistema, String usuario, Date fechaHoraEntrada, Date fechaHoraSalida) {
		super();
		this.idAccesoSistema = idAccesoSistema;
		this.usuario = usuario;
		this.fechaHoraEntrada = fechaHoraEntrada;
		this.fechaHoraSalida = fechaHoraSalida;
	}

	/*
	 * Metodos Get & Set de atributos de DTOAccesosSistema 
	 */
	
	public Integer getIdAccesoSistema() {
		return idAccesoSistema;
	}

	public void setIdAccesoSistema(Integer idAccesoSistema) {
		this.idAccesoSistema = idAccesoSistema;
	}

	@Override
	public Date getFechaHora() {
		return fechaHoraEntrada;
	}

	@Override
	public String getUsuario() {
		return usuario;
	}

	@Override
	public void setFechaHora(Date fechaHoraEntrada) {
		this.fechaHoraEntrada = fechaHoraEntrada;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(Date fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}
	
}
