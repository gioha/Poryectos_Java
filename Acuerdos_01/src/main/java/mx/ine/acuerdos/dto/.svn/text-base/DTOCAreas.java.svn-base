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
@Table(name = "ACUERDOS.C_AREAS")
@NamedQuery(name = "DTOCAreas.findAll", query = "SELECT d FROM DTOCAreas d")
public class DTOCAreas extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5938823043995847160L;
	
	@EmbeddedId
	private DTOCAreasPK id;

	@Column(name = "SIGLAS", nullable = false)
	private String siglas;
	
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "ID_ENTIDAD", nullable = false)
	private Integer idEntidad;

	
	/**
	 * Constructor default de la clase DTOCAreas
	 *  @author Giovanni Hernández Alonso
	 * @since 10/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOCAreas(){
		this.id 			= new DTOCAreasPK();
		this.siglas 		= "";
		this.descripcion 	= "";
		this.idEntidad 		= new Integer(0);
		this.setUsuario("");
		this.setFechaHora(new Date());
	}

	@Override
	public Date getFechaHora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFechaHora(Date arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUsuario(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public DTOCAreasPK getId() {
		return id;
	}

	public void setId(DTOCAreasPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public Integer getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}

}
