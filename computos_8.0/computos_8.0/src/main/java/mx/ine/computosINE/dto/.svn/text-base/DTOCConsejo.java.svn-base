package mx.ine.computosINE.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPUTOSINE.C_CONSEJEROS")
public class DTOCConsejo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8326253486370758343L;

	@Id
	@Column(name = "ID_PUESTO_FUNCIONARIO", nullable = false)
	private Integer idPuestoFuncionario;
	
	@Column(name = "TITULO_CONSEJERO", nullable = false)
	private String tituloConsejero;

	public DTOCConsejo(){
		
	}

	public DTOCConsejo(Integer idPuestoFuncionario, String tituloConsejero){
		this.idPuestoFuncionario = idPuestoFuncionario;
		this.tituloConsejero = tituloConsejero;
	}


	public Integer getIdPuestoFuncionario() {
		return idPuestoFuncionario;
	}


	public void setIdPuestoFuncionario(Integer idPuestoFuncionario) {
		this.idPuestoFuncionario = idPuestoFuncionario;
	}


	public String getTituloConsejero() {
		return tituloConsejero;
	}


	public void setTituloConsejero(String tituloConsejero) {
		this.tituloConsejero = tituloConsejero;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null) return false;
		if(this == other) return true;
		if(!(other instanceof DTOCConsejo)){
			return false;
		}
		DTOCConsejo oother = (DTOCConsejo) other;

		return (this.idPuestoFuncionario.equals(oother.getIdPuestoFuncionario()) && this.tituloConsejero.equals(oother.getTituloConsejero()));
		
	}
	@Override
	public int hashCode(){
        int result = 17;
        result = 31 * result + this.idPuestoFuncionario.hashCode();
        result = 31 * result + this.tituloConsejero.hashCode();
        return result;
	}

	
}
