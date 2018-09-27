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
@Table(name = "ACUERDOS.C_CARGOS")
@NamedQuery(name = "DTOCCargos.findAll", query = "SELECT d FROM DTOCCargos d")
public class DTOCCargos extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3777036442021338851L;

	@Id
	@Column(name = "ID_CARGO", nullable = false)
	private Integer idCargo;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	
	/**
	 * Constructor default de la clase DTOCCargos
	 *  @author Giovanni Hernández Alonso
	 * @since 10/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOCCargos(){
		
		this.idCargo 		= new Integer(0);
		this.descripcion 	= "";
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

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
