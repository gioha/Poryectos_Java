package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTOCNegociosCTiposIntegPK implements Serializable {
	private static final long serialVersionUID = -4817773906722531498L;

	@Column(name = "ID_TIPO_INTEGRANTE", nullable = false)
	private Integer idTipoIntegrante;

	@Column(name = "ID_NEGOCIO", nullable = false)
	private Integer idNegocio;

	/**
	 * Constructor default para la clase que mapea los campos para la
	 * llave compuesta de la tabla ACUERDOS.NEGOCIOS_INTEGRANTES_COMISION
	 * @author José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCNegociosCTiposIntegPK() {
		this.idTipoIntegrante	= new Integer(0);
		this.idNegocio 			= new Integer(0);
	}

	public Integer getIdTipoIntegrante() {
		return idTipoIntegrante;
	}

	public void setIdTipoIntegrante(Integer idTipoIntegrante) {
		this.idTipoIntegrante = idTipoIntegrante;
	}

	public Integer getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Integer idNegocio) {
		this.idNegocio = idNegocio;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOCNegociosCTiposIntegPK) ) {
			DTOCNegociosCTiposIntegPK dtoCNegCtiposInteg = (DTOCNegociosCTiposIntegPK) obj;
			if( idTipoIntegrante.equals(dtoCNegCtiposInteg.idTipoIntegrante) &&
					idNegocio.equals(dtoCNegCtiposInteg.idNegocio) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoIntegrante, idNegocio);
	}

}
