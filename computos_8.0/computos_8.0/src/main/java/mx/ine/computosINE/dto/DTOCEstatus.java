package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.C_ESTATUS")
@NamedQuery(name = "DTOCEstatus.findAll", query = "SELECT d FROM DTOCEstatus d")
public class DTOCEstatus extends DTOBase implements Serializable {

	private static final long serialVersionUID = 300320171821L;

	@Id
	@Column(name = "ID_ESTATUS", nullable = false)
	private Integer idEstatus;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "VALIDA", nullable = false)
	private Integer valida;

	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;

	// no sabemos si va (por ahora)
	// @OneToMany(mappedBy="estatus")
	// List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();

	@OneToMany
	private List<DTOReglasEstatus> reglasEstatus;

	public DTOCEstatus(){
	}
	
	public DTOCEstatus(Integer idEstatus, String descripcion, Integer valida,
			String usuario, Date fechaHora) {
		super();
		this.idEstatus = idEstatus;
		this.descripcion = descripcion;
		this.valida = valida;
		this.usuario = usuario;
		this.fechaHora = fechaHora;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getValida() {
		return valida;
	}

	public void setValida(Integer valida) {
		this.valida = valida;
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

	public List<DTOReglasEstatus> getReglasEstatus() {
		return reglasEstatus;
	}

	public void setReglasEstatus(List<DTOReglasEstatus> reglasEstatus) {
		this.reglasEstatus = reglasEstatus;
	}

}
