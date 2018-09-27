package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTOComisionesUnidasPK implements Serializable {
	private static final long serialVersionUID = -8501702255879307713L;

	@Column(name="ID_COMISION_PRESIDE", nullable = false)
	private Integer idComisionPreside;

	@Column(name = "INICIO_PERIODO_PRESIDE", nullable = false)
	private Date inicioPeriodoPreside;

	@Column(name="ID_COMISION_INVITADO", nullable = false)
	private Integer idComisionInvitado;

	@Column(name = "INICIO_PERIODO_INVITADO", nullable = false)
	private Date inicioPeriodoInvitado;

	public DTOComisionesUnidasPK () {
		this.idComisionPreside		= new Integer (0);
		this.inicioPeriodoPreside	= new Date();
		this.idComisionInvitado		= new Integer (0);
		this.inicioPeriodoInvitado	= new Date();
	}

	public Integer getIdComisionPreside() {
		return idComisionPreside;
	}

	public void setIdComisionPreside(Integer idComisionPreside) {
		this.idComisionPreside = idComisionPreside;
	}

	public Date getInicioPeriodoPreside() {
		return inicioPeriodoPreside;
	}

	public void setInicioPeriodoPreside(Date inicioPeriodoPreside) {
		this.inicioPeriodoPreside = inicioPeriodoPreside;
	}

	public Integer getIdComisionInvitado() {
		return idComisionInvitado;
	}

	public void setIdComisionInvitado(Integer idComisionInvitado) {
		this.idComisionInvitado = idComisionInvitado;
	}

	public Date getInicioPeriodoInvitado() {
		return inicioPeriodoInvitado;
	}

	public void setInicioPeriodoInvitado(Date inicioPeriodoInvitado) {
		this.inicioPeriodoInvitado = inicioPeriodoInvitado;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOComisionesUnidasPK) ) {
			DTOComisionesUnidasPK dtoComisionesUnidas = (DTOComisionesUnidasPK) obj;
			if( idComisionPreside.equals(dtoComisionesUnidas.idComisionPreside) &&
				inicioPeriodoPreside.equals(dtoComisionesUnidas.inicioPeriodoPreside) &&
				idComisionInvitado.equals(dtoComisionesUnidas.idComisionInvitado) &&
				inicioPeriodoInvitado.equals(dtoComisionesUnidas.inicioPeriodoInvitado) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComisionPreside, inicioPeriodoPreside, idComisionInvitado, inicioPeriodoInvitado);
	}

}
