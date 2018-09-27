package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.REGLAS_ESTATUS")
@NamedQuery(name = "DTOReglasEstatus.findAll", query = "SELECT d FROM DTOReglasEstatus d")
public class DTOReglasEstatus extends DTOBase implements Serializable {

	private static final long serialVersionUID = 300320171850L;

	@EmbeddedId
	private DTOReglasEstatusPK id;

	public DTOReglasEstatusPK getId() {
		return id;
	}

	public void setId(DTOReglasEstatusPK id) {
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
