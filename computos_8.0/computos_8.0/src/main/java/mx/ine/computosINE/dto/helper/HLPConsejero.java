package mx.ine.computosINE.dto.helper;

import java.io.Serializable; 

import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCConsejo;
import mx.ine.computosINE.dto.DTOConsejero;
import mx.ine.computosINE.dto.DTOConsejeroPK;
import mx.org.ine.servicios.dto.DTOBase;

public class HLPConsejero implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6587379661736346624L;

	private Integer idTipoConsejero;
	private String tituloConsejero;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String calidad;
	
	/**
	 * Construye un objeto de tipo DTOConsejero usando la misma PK del acta ya guardada.
	 * @param acta : Objeto que es un acta.
	 * @return cons - Un objeto tipo DTOConsejero
	 * El atributo pk.idConsejero no esta asignado.
	 */
	public DTOConsejero getDtoConsejero(DTOBase acta){
		DTOConsejero cons = new DTOConsejero();
		DTOConsejeroPK pk = new DTOConsejeroPK();
		DTOCConsejo tipo = new DTOCConsejo(idTipoConsejero, tituloConsejero);
		
		if(acta instanceof DTOActaTipoCandidatura){
			DTOActaTipoCandidaturaPK pkActa = ((DTOActaTipoCandidatura)acta).getPk();
			pk.setIdProcesoElectoral(pkActa.getIdProcesoElectoral());
			pk.setIdDetalleProceso(pkActa.getIdDetalleProceso());
			pk.setIdTipoCandidatura(pkActa.getIdTipoCandidatura());
			pk.setIdEstado(pkActa.getIdEstado());
			pk.setIdDistrito(pkActa.getIdDistrito());
			pk.setIdMunicipio(pkActa.getIdMunicipio());
			pk.setIdComunidad(pkActa.getIdComunidad());
			pk.setIdRegiduria(pkActa.getIdRegiduria());
			cons.setPk(pk);
			cons.setNombre(nombre);
			cons.setApellidoPaterno(aPaterno);
			cons.setApellidoMaterno(aMaterno);
			cons.setCalidadConsejero(calidad.charAt(0));
			cons.setTipoConsejero(tipo);
		}
		
		return cons;
		
	}
	

	public Integer getIdTipoConsejero() {
		return idTipoConsejero;
	}

	public void setIdTipoConsejero(Integer idTipoConsejero) {
		this.idTipoConsejero = idTipoConsejero;
	}

	public String getTituloConsejero() {
		return tituloConsejero;
	}

	public void setTituloConsejero(String tituloConsejero) {
		this.tituloConsejero = tituloConsejero;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	/**
	 * Metodo que extrae el nombre completo del consejero, empezando por nombre, y posteriormente apellido paterno
	 * y/o nombre
	 * @return El nombre completo del consejero
	 */
	public String getNombreCompleto() {
		if( nombre!=null )
			return nombre + " " + (
					((aPaterno==null || aPaterno.trim().equals(""))?"":aPaterno) + " " +
					((aMaterno==null || aMaterno.trim().equals(""))?"":aMaterno) ).trim();
		else
			return null;
	}
}
