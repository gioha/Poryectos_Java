package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.COMISIONES")
@NamedQuery(name = "DTOComisiones.findAll", query = "SELECT d FROM DTOComisiones d")
public class DTOComisiones extends DTOBase implements Serializable {
	private static final long serialVersionUID = -8242210236438696593L;

	@Id
	@Column(name = "ID_COMISION", nullable = false)
	private Integer idComision;

	@Column(name = "NOMBRE_COMISION", nullable = false)
	private String nomComision;

	@Column(name = "ESTATUS", nullable = false)
	private Integer estatus;

	@Column(name = "TIPO_COMISION", nullable = true)
	private Integer tipoComision;

	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;
	
	@Transient
	private String tipoComisionCadena; 

	public DTOComisiones() {
		idComision			= new Integer(0);
		nomComision			= "";
		estatus				= new Integer(0);
		tipoComision		= new Integer(0);
		usuario				= "";
		fechaHora			= new Date();
		tipoComisionCadena  = "";
	}

	public Integer getIdComision() {
		return idComision;
	}

	public void setIdComision(Integer idComision) {
		this.idComision = idComision;
	}

	public String getNomComision() {
		return nomComision;
	}

	public void setNomComision(String nomComision) {
		this.nomComision = nomComision;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Integer getTipoComision() {
		return tipoComision;
	}

	public void setTipoComision(Integer tipoComision) {
		this.tipoComision = tipoComision;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getTipoComisionCadena() {
		return tipoComisionCadena;
	}

	public void setTipoComisionCadena(String tipoComisionCadena) {
		this.tipoComisionCadena = tipoComisionCadena;
	}

}
