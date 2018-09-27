package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.NEGOCIOS_TIPO_DOCUMENTOS")
@NamedQuery(name = "DTOCNegociosCTiposDoc.findAll", query = "SELECT d FROM DTOCNegociosCTiposDoc d")
public class DTOCNegociosCTiposDoc extends DTOBase implements Serializable {
	private static final long serialVersionUID = -5934137303188704724L;

	@EmbeddedId
	private DTOCNegociosCTiposDocPK id;

	/**
	 * Constructor default para la clase DTOCNegociosCTiposDoc
	 * @author José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCNegociosCTiposDoc() {
		this.id	= new DTOCNegociosCTiposDocPK();
	}

	public DTOCNegociosCTiposDocPK getId() {
		return id;
	}

	public void setId(DTOCNegociosCTiposDocPK id) {
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
