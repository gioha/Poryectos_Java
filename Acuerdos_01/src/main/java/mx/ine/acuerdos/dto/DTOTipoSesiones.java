package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import mx.org.ine.servicios.dto.DTOBase;


@Entity
@Table(name = "ACUERDOS.C_TIPO_SESIONES")
@NamedQuery(name = "DTOTipoSesiones.findAll", query = "SELECT d FROM DTOTipoSesiones d")
public class DTOTipoSesiones extends DTOBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4131665455843733253L;
	
	@Id
	@NotNull
	@Column(name = "ID_TIPO_SESION", nullable = false)
	private Integer idTipoSesion;
	
	@Column(name="DESCRIPCION", nullable = false)
	private String descripcion;
	
	@Column(name="ORDEN", nullable = false)
	private Integer orden;
	
	
	/**
	 * Constructor default de la clase DTOTipoSesiones
	 *  @author José Hurtado
	 * @since 09/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOTipoSesiones(){
		this.idTipoSesion        = new Integer(0);
		this.descripcion         = "";
		this.orden               = new Integer(0);
	}


	public Integer getIdTipoSesion() {
		return idTipoSesion;
	}


	public void setIdTipoSesion(Integer idTipoSesion) {
		this.idTipoSesion = idTipoSesion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
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
	
	
}
