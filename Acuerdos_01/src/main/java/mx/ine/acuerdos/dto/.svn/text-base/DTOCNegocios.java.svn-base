package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.C_NEGOCIOS")
@NamedQuery(name = "DTOCNegocios.findAll", query = "SELECT d FROM DTOCNegocios d")
public class DTOCNegocios extends DTOBase implements Serializable {
	private static final long serialVersionUID = -9009800204435980518L;

	@Id
	@Column(name = "ID_NEGOCIO", nullable = false)
	private Integer idNegocio;

	@Column(name = "DESCRIPCION", nullable = true)
	private String descripcion;

	@Column(name = "ESTATUS", nullable = true)
	private Integer estatus;

	/**
	 * Constructor default para la clase DTOCNegocios
	 * @author José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCNegocios() {
		this.idNegocio 		= new Integer(0);
		this.descripcion	= "";
		this.estatus		= new Integer(0);
	}

	public Integer getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Integer idNegocio) {
		this.idNegocio = idNegocio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	@Override
	public Date getFechaHora() {
		return null;
	}

	@Override
	public String getUsuario() {
		return null;
	}

	@Override
	public void setFechaHora(Date arg0) {
	}

	@Override
	public void setUsuario(String arg0) {
	}
	
}
