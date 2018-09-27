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
@Table(name = "ACUERDOS.DOCUMENTOS_PERIODOS")
@NamedQuery(name = "DTODocumentosPeriodos.findAll", query = "SELECT d FROM DTODocumentosPeriodos d")
public class DTODocumentosPeriodos extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2206234455998331500L;

	
	@EmbeddedId
	private DTODocumentosPeriodosPK id;
	
	@Column(name = "INICIO_PERIODO", nullable = false)
	private Date inicioPeriodo;
	
	@Column(name = "TERMINO_PERIODO", nullable = false)
	private Date terminoPeriodo;
	
	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;
	
	@Column(name = "DESCRIPCION", nullable = true)
	private String descripcion;
	
	/**
	 * Constructor default de la clase DTODocumentosPeriodos
	 *  @author Giovanni Hernández Alonso
	 * @since 12/10/2017
	 * @param 
	 * @return DTODocumentosPeriodos
	 * **/
	public DTODocumentosPeriodos(){
		this.id = new DTODocumentosPeriodosPK();
		this.inicioPeriodo = null;
		this.terminoPeriodo = null;
		this.descripcion = "";
		setUsuario("");
		setFechaHora( new Date());
	}
	
	public DTODocumentosPeriodosPK getId() {
		return id;
	}

	public void setId(DTODocumentosPeriodosPK id) {
		this.id = id;
	}

	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public Date getTerminoPeriodo() {
		return terminoPeriodo;
	}

	public void setTerminoPeriodo(Date terminoPeriodo) {
		this.terminoPeriodo = terminoPeriodo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Date getFechaHora() {
		return this.fechaHora;
	}

	@Override
	public String getUsuario() {
		return this.usuario;
	}

	@Override
	public void setFechaHora(Date date) {
		this.fechaHora = date;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "DTODocumentosPeriodos [id=" + id + ", inicioPeriodo=" + inicioPeriodo + ", terminoPeriodo="
				+ terminoPeriodo + ", descripcion=" + descripcion + "]";
	}
	
}
