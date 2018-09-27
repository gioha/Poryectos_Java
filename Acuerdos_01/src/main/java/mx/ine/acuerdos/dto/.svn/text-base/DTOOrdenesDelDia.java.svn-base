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
@Table(name = "ACUERDOS.ORDENES_DEL_DIA")
@NamedQuery(name = "DTOOrdenesDelDia.findAll", query = "SELECT d FROM DTOOrdenesDelDia d")
public class DTOOrdenesDelDia extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4692206541548912534L;

	@EmbeddedId
	private DTOOrdenesDelDiaPK id;

	@Column(name="DESCRIPCION_PUNTO", nullable = false)
	private String descripcionPunto;

	@Column(name="USUARIO", nullable = false)
	private String usuario;

	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;

	/**
	 * Constructor default de la clase DTOOrdenDelDia
	 * @author Giovanni Hernández Alonso
	 * @update Miguel Ortiz
	 * @since 06/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOOrdenesDelDia() {
		
		this.id 				= new DTOOrdenesDelDiaPK();
		this.descripcionPunto	= "";
		this.usuario 			= "";
		this.fechaHora			= new Date();
	}

	public DTOOrdenesDelDiaPK getId() {
		return id;
	}

	public void setId(DTOOrdenesDelDiaPK id) {
		this.id = id;
	}

	public String getDescripcionPunto() {
		return descripcionPunto;
	}

	public void setDescripcionPunto(String descripcionPunto) {
		this.descripcionPunto = descripcionPunto;
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

}
