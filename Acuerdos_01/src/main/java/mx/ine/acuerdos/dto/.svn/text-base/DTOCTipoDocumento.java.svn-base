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
@Table(name = "ACUERDOS.C_TIPO_DOCUMENTOS")
@NamedQuery(name = "DTOCTipoDocumento.findAll", query = "SELECT d FROM DTOCTipoDocumento d")
public class DTOCTipoDocumento extends DTOBase implements Serializable {
	private static final long serialVersionUID = 319377189277325810L;

	@Id
	@Column(name = "ID_TIPO_DOCUMENTO", nullable = false)
	private Integer idTipoDocumento;

	@Column(name = "TIPO", nullable = false)
	private String tipo;

	/**
	 * Constructor default para la clase DTOCTipoDocumento
	 * @author Giovanni Hernández Alonso
	 * @update José Miguel Ortiz
	 * @since 20/02/2018
	 * @param 
	 * @return void
	 **/
	public DTOCTipoDocumento() {
		this.idTipoDocumento	= new Integer(0);
		this.tipo				= "";
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
