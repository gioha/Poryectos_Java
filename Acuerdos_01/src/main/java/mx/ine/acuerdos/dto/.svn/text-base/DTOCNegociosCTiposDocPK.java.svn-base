package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTOCNegociosCTiposDocPK implements Serializable {
	private static final long serialVersionUID = -4652543553819936252L;

	@Column(name = "ID_TIPO_DOCUMENTO", nullable = false)
	private Integer idTipoDocumento;

	@Column(name = "ID_NEGOCIO", nullable = false)
	private Integer idNegocio;

	/**
	 * Constructor default para la clase que mapea los campos para la
	 * llave compuesta de la tabla ACUERDOS.NEGOCIOS_TIPO_DOCUMENTOS
	 * @author José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCNegociosCTiposDocPK() {
		this.idTipoDocumento	= new Integer(0);
		this.idNegocio			= new Integer(0);
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
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
		if((obj != null) && (obj instanceof DTOCNegociosCTiposDocPK) ) {
			DTOCNegociosCTiposDocPK dtoCNegCtiposDoc = (DTOCNegociosCTiposDocPK) obj;
			if( idTipoDocumento.equals(dtoCNegCtiposDoc.idTipoDocumento) &&
					idNegocio.equals(dtoCNegCtiposDoc.idNegocio) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoDocumento, idNegocio);
	}

}
