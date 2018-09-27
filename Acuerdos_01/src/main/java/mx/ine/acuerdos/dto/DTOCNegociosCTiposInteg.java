package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.NEGOCIOS_INTEGRANTES_COMISION")
@NamedQuery(name = "DTOCNegociosCTiposInteg.findAll", query = "SELECT d FROM DTOCNegociosCTiposInteg d")
public class DTOCNegociosCTiposInteg extends DTOBase implements Serializable {
	private static final long serialVersionUID = 4108253867221988801L;

	@EmbeddedId
	private DTOCNegociosCTiposIntegPK id;

	/**
	 * Constructor default para la clase DTOCNegociosCTipoInteg
	 * @author José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCNegociosCTiposInteg() {
		this.id	= new DTOCNegociosCTiposIntegPK();
	}

	public DTOCNegociosCTiposIntegPK getId() {
		return id;
	}

	public void setId(DTOCNegociosCTiposIntegPK id) {
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
