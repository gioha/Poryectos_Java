package mx.ine.computosINE.dto.helper;

import java.io.Serializable; 

import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTORepresentante;
import mx.ine.computosINE.dto.DTORepresentantePK;
import mx.org.ine.servicios.dto.DTOBase;

public class HLPRepresentante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1153074446782716512L;

	/**
	 * Datos de la asociacion
	 * a la que pertenece el representante.
	 */
	private Integer idAsociacion;
	private Integer tipoAsociacion;
	private String nombreAsociacion;
	private String emblemaAsociacion;
	private String siglasAsociacion;
	private Integer orden;
	
	/**
	 * Datos del representante
	 */
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String calidad;
	
	
	
	/**
	 * Construye un objeto de tipo DTORepresentante usando la misma PK del acta ya guardada.
	 * @param acta : Objeto que representa el acta
	 * ya guardada.
	 * @return rep - Un objeto tipo DTORepresentante
	 * El atributo pk.idRepresentante no esta asignado.
	 */
	public DTORepresentante getDtoRepresentante(DTOBase acta){

		DTORepresentante rep = new DTORepresentante();
		DTORepresentantePK pk = new DTORepresentantePK();
		
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
			rep.setPk(pk);
			rep.setNombre(nombre);
			rep.setApellidoPaterno(aPaterno);
			rep.setApellidoMaterno(aMaterno);
			rep.setCalidadRepresentante(calidad.charAt(0));
			
			rep.setIdAsociacion(idAsociacion);
			rep.setTipoAsociacion(tipoAsociacion);
			rep.setNombreAsociacion(nombreAsociacion);
			rep.setEmblemaAsociacion(emblemaAsociacion);
			rep.setSiglasAsociacion(siglasAsociacion);
			rep.setOrden(orden);
			
		}

		return rep;
	}


	public Integer getIdAsociacion() {
		return idAsociacion;
	}


	public void setIdAsociacion(Integer idAsociacion) {
		this.idAsociacion = idAsociacion;
	}


	public Integer getTipoAsociacion() {
		return tipoAsociacion;
	}


	public void setTipoAsociacion(Integer tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}


	public String getNombreAsociacion() {
		return nombreAsociacion;
	}


	public void setNombreAsociacion(String nombreAsociacion) {
		this.nombreAsociacion = nombreAsociacion;
	}


	public String getEmblemaAsociacion() {
		return emblemaAsociacion;
	}


	public void setEmblemaAsociacion(String emblemaAsociacion) {
		this.emblemaAsociacion = emblemaAsociacion;
	}


	public String getSiglasAsociacion() {
		return siglasAsociacion;
	}


	public void setSiglasAsociacion(String siglasAsociacion) {
		this.siglasAsociacion = siglasAsociacion;
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


	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	


	/**
	 * Metodo que extrae el nombre completo del representante, empezando por nombre, y posteriormente apellido paterno
	 * y/o nombre
	 * @return El nombre completo del representante
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
