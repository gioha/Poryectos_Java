package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla SEGUIMIENTOS_CG del esquema de ACUERDOS.
 * 
 * @author Sampier Cuevas Flores
 * @since Octubre-2017
 * @ver 1
 **/

@Embeddable
public class DTOSeguimientosCGPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 524398538322768600L;
	
	@Column(name="ID_NUM_ACUERDO", nullable = false)
	private String idNumAcuerdo;
	
	@Column(name="NUMERALIA", nullable = false)
	private Integer numeralia;
	
	@Column(name="ID_AREA", nullable = false)
	private Integer idArea;
	
	@Column(name="TIPO_AREA", nullable = false)
	private Integer tipoArea;
	
	@Column(name="FECHA_MOVIMIENTO", nullable = false)
	private Date fechaMovimiento;
	
	@Column(name="ID_ESTATUS_PUNTO", nullable = false)
	private Integer idEstatusPunto;
	
	/**
	 * Constructor default de la clase DTOSeguimientos_CGPK, que es la llave compuesta de la clase  DTOSeguimientos_CG
	 *  @author Sampier Cuevas Flores
	 * @since 11/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOSeguimientosCGPK(){
		this.idNumAcuerdo		= "";
		this.numeralia			= new Integer(0);
		this.idArea 			= new Integer(0);
		this.tipoArea			= new Integer(0);
		this.fechaMovimiento 	= new Date();
		this.idEstatusPunto		= new Integer(0);
		
	}

	public String getIdNumAcuerdos() {
		return idNumAcuerdo;
	}

	public void setIdNumAcuerdos(String idNumAcuerdos) {
		this.idNumAcuerdo = idNumAcuerdos;
	}

	public Integer getNumeralia() {
		return numeralia;
	}

	public void setNumeralia(Integer numeralia) {
		this.numeralia = numeralia;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Integer getIdEstatusPunto() {
		return idEstatusPunto;
	}

	public void setIdEstatusPunto(Integer idEstatusPunto) {
		this.idEstatusPunto = idEstatusPunto;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOSeguimientosCGPK) ) {
			DTOSeguimientosCGPK dtoSeguimientosCG = (DTOSeguimientosCGPK) obj;
			if( idNumAcuerdo.equals(dtoSeguimientosCG.idNumAcuerdo) &&
				numeralia.equals(dtoSeguimientosCG.numeralia) &&
				idArea.equals(dtoSeguimientosCG.idArea) &&
				tipoArea.equals(dtoSeguimientosCG.tipoArea) &&
				fechaMovimiento.equals(dtoSeguimientosCG.fechaMovimiento) &&
				idEstatusPunto.equals(dtoSeguimientosCG.idEstatusPunto) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNumAcuerdo, numeralia, idArea, tipoArea, fechaMovimiento, idEstatusPunto);
	}

}
