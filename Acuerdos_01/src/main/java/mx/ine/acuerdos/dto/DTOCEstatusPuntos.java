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
@Table(name = "ACUERDOS.C_ESTATUS_PUNTOS")
@NamedQuery(name = "DTOCEstatusPuntos.findAll", query = "SELECT d FROM DTOCEstatusPuntos d")
public class DTOCEstatusPuntos extends DTOBase implements Serializable {
	private static final long serialVersionUID = -2687066801974000327L;

	@Id
	@Column(name = "ID_ESTATUS_PUNTO", nullable = false)
	private Integer idEstatusPunto;
	
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;
	
	@Column(name = "ORDEN", nullable = false)
	private Integer orden;
	
	/**
	 * Constructor default de la clase DTOCEstatusPuntos
	 * @author Sampier Cuevas Flores
	 * @update Miguel Ortiz
	 * @since 12/10/2017
	 * @return DTOCEstatusPuntos
	 */
	public DTOCEstatusPuntos() {
		this.idEstatusPunto	= new Integer(0);
		this.descripcion	= "";
		this.orden			= new Integer(0);
	}

	public Integer getIdEstatusPunto() {
		return idEstatusPunto;
	}

	public void setIdEstatusPunto(Integer idEstatusPunto) {
		this.idEstatusPunto = idEstatusPunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Override
	public Date getFechaHora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFechaHora(Date arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setUsuario(String arg0) {
		// TODO Auto-generated method stub
	}

}
