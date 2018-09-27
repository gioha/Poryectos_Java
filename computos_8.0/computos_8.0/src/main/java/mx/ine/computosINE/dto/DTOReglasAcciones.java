package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.REGLAS_ACCIONES")
@NamedQuery(name = "DTOReglasAcciones.findAll", query = "SELECT d FROM DTOReglasAcciones d")
public class DTOReglasAcciones extends DTOBase implements Serializable {

	private static final long serialVersionUID = 300320171850L;

	@EmbeddedId
	private DTOReglasAccionesPK id;	

	public DTOReglasAcciones(DTOReglasAccionesPK id) {
		super();
		this.id = id;
	}

	public DTOReglasAccionesPK getId() {
		return id;
	}

	public void setId(DTOReglasAccionesPK id) {
		this.id = id;
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
