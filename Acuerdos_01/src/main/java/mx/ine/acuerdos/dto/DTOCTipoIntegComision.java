package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.C_TIPO_INTEGRANTES_COMISION")
@NamedQuery(name = "DTOCTipoIntegComision.findAll", query = "SELECT d FROM DTOCTipoIntegComision d")
public class DTOCTipoIntegComision extends DTOBase implements Serializable {
	private static final long serialVersionUID = -3851713888985432593L;

	@Id
	@Column(name = "ID_TIPO_INTEGRANTE", nullable = false)
	private Integer idTipoIntegrante;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "TIENE_VOTO", nullable = false)
	private Integer tieneVoto;

	@Column(name = "TIENE_VOZ", nullable = false)
	private Integer tieneVoz;

	@Column(name = "ORDEN", nullable = false)
	private Integer orden;

	/**
	 * Constructor default para la clase DTOCTipoIntegComision
	 * @update José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCTipoIntegComision() {
		idTipoIntegrante	= new Integer(0);
		descripcion			= "";
		tieneVoto			= new Integer(0);
		tieneVoz			= new Integer(0);
		orden				= new Integer(0);
	}

	public Integer getIdTipoIntegrante() {
		return idTipoIntegrante;
	}

	public void setIdTipoIntegrante(Integer idTipoIntegrante) {
		this.idTipoIntegrante = idTipoIntegrante;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTieneVoto() {
		return tieneVoto;
	}

	public void setTieneVoto(Integer tieneVoto) {
		this.tieneVoto = tieneVoto;
	}

	public Integer getTieneVoz() {
		return tieneVoz;
	}

	public void setTieneVoz(Integer tieneVoz) {
		this.tieneVoz = tieneVoz;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
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
