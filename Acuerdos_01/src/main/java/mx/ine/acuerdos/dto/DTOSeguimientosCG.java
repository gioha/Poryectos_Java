package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.SEGUIMIENTOS_CG")
@NamedQuery(name = "DTOSeguimientos.findAll", query = "SELECT d FROM DTOSeguimientosCG d")
public class DTOSeguimientosCG extends DTOBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8340599830961609940L;
	
	
	@EmbeddedId
	private DTOSeguimientosCGPK id;
	
	@Column(name="TIPO_MOVIMIENTO", nullable = false)
	private Integer tipoMovimiento;
	
	@Column(name="DESCRIPCION", nullable = false)
	private String descripcion;
	
	@Column(name="USUARIO", nullable = false)
	private String usuario;
	
	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;
	
	@Column(name="RESPONSABILIDAD_CONJUNTA", nullable = true)
	private Integer responsabilidadConjunta;
	
	/**
	 * Constructor default de la clase DTOSeguimientosCG
	 * @author Sampier Cuevas Flores
	 * @since 11/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOSeguimientosCG (){
		this.id 				= new DTOSeguimientosCGPK();
		this.tipoMovimiento		= new Integer (0);
		this.descripcion 		= "";	
		this.usuario 			= "";
		this.fechaHora			= new Date();
		this.responsabilidadConjunta = new Integer(0);
	}

	public DTOSeguimientosCGPK getId() {
		return id;
	}

	public void setId(DTOSeguimientosCGPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getResponsabilidadConjunta() {
		return responsabilidadConjunta;
	}

	public void setResponsabilidadConjunta(Integer responsabilidadConjunta) {
		this.responsabilidadConjunta = responsabilidadConjunta;
	}
	
	

}
