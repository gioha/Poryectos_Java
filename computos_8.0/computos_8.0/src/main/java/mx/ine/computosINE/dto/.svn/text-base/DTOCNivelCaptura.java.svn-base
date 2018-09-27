package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase que transporta y mapea la tabla C_NIVEL_CAPTURA del esquema de COMPUTOSINE.
 * 
 * @author Giovanni Hernandez Alonso
 * @since Abril-2016
 * @ver
 **/

@Entity
@Table(name = "COMPUTOSINE.C_NIVEL_CAPTURA")
@NamedQuery(name = "DTOCNivelCaptura.findAll", query = "SELECT d FROM DTOCNivelCaptura d")
public class DTOCNivelCaptura extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -625040235064012984L;
	
	@Id
	@Column(name="id_nivel_captura", nullable = false)
	private Integer idNivelCaptura;
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;
	
	@Column(name="usuario", nullable = false)
	private String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora", nullable = false)
	private Date fechaHora;
	
	@OneToMany(cascade= CascadeType.REMOVE )
	@JoinColumn(name="ID_NIVEL_CAPTURA", referencedColumnName ="ID_NIVEL_CAPTURA")
	private List<DTONivelCapturaCandidatura> capturasCandidaturas;
	
	
	public DTOCNivelCaptura(String descripcion, String usuario, Date fechaHora) {
		super();
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.fechaHora = fechaHora;
	}

	
	public Integer getIdNivelCaptura() {
		return idNivelCaptura;
	}

	public void setIdNivelCaptura(Integer idNivelCaptura) {
		this.idNivelCaptura = idNivelCaptura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Date getFechaHora() {
		// TODO Auto-generated method stub
		return fechaHora;
	}

	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public void setFechaHora(Date fechaHora) {
		// TODO Auto-generated method stub
		this.fechaHora = fechaHora;
	}

	@Override
	public void setUsuario(String usuario) {
		// TODO Auto-generated method stub
		this.usuario = usuario;
	}


}
