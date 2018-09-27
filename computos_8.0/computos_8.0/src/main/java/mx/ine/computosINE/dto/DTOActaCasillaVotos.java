package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase que transporta y mapea la tabla ACTA_CASILLA_VOTOS del esquema de COMPUTOSINE.
 * La clase que mapea la llave compuesta de esta tabla es la clase DTOActaCasillaVotosPK
 * 
 * @author Giovanni Hernandez Alonso
 * @since Abril-2016
 * @ver
 **/

@Entity
@Table(name = "COMPUTOSINE.ACTA_CASILLA_VOTOS")
@NamedQuery(name = "DTOActaCasillaVotos.findAll", query = "SELECT d FROM DTOActaCasillaVotos d")
public class DTOActaCasillaVotos extends DTOBase implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860114030848459503L;

	@EmbeddedId
	protected DTOActaCasillaVotosPK id;

	@Column(name="ID_ESTATUS", nullable = false)
	protected Integer idEstatus;
	
	@Column(name="ORDEN", nullable = false)
	protected Integer orden;
	
	@Column(name="VOTOS", nullable = false)
	protected Integer votos;

	@Column(name = "CAPTURADA", nullable = false)
	protected Integer capturada;

	@Column(name = "LISTA_NOMINAL", nullable = false)
	protected Integer listaNominal;

	@Column(name="USUARIO", nullable = false)
	protected String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA", nullable = false)
	protected Date fechaHora;

	@Transient 
	protected String cabeceraDistrital;
	
	@Transient 
	protected String emblema;

	
	public DTOActaCasillaVotos(DTOActaCasillaVotos obj){
		
		this.setIdEstatus( obj.getIdEstatus() );
		this.setOrden( obj.getOrden() );
		this.setVotos( obj.getVotos() );
		this.setCapturada( obj.getCapturada() );
		this.setListaNominal( obj.getListaNominal() );
		this.setUsuario( obj.getUsuario() );
		this.setFechaHora( obj.getFechaHora() );
		
		DTOActaCasillaVotosPK pk = new DTOActaCasillaVotosPK();
		
		pk.setIdProcesoElectoral( obj.getId().getIdProcesoElectoral() );
		pk.setIdDetalleProceso( obj.getId().getIdDetalleProceso() );
		pk.setIdEstado( obj.getId().getIdEstado() );
		pk.setIdDistrito( obj.getId().getIdDistrito() );
		pk.setIdMunicipio( obj.getId().getIdMunicipio() );
		pk.setIdRegiduria( obj.getId().getIdRegiduria() );
		pk.setSeccion( obj.getId().getSeccion() );
		pk.setIdComunidad( obj.getId().getIdComunidad() );
		pk.setIdCasilla( obj.getId().getIdCasilla() );
		pk.setTipoCasilla( obj.getId().getTipoCasilla() );
		pk.setExtContigua( obj.getId().getExtContigua() );
		pk.setIdTipoCandidatura( obj.getId().getIdTipoCandidatura() );
		pk.setIdAsociacion( obj.getId().getIdAsociacion() );
		pk.setTipoAsociacion( obj.getId().getTipoAsociacion() );
		pk.setIdCoalicion( obj.getId().getIdCoalicion() );
		
		this.setId( pk );
		
	}
	
	
	public DTOActaCasillaVotos(){
		
		this.id = new DTOActaCasillaVotosPK();
		this.idEstatus = new Integer(0);
		this.orden = new Integer(0);
		this.votos = new Integer(0);
		this.capturada = new Integer(0);
		this.listaNominal = new Integer(0);
		this.usuario = "";
		this.fechaHora = new Date();
		this.cabeceraDistrital = "";
		
	}
	public DTOActaCasillaVotos(DTOActaCasillaVotosPK id, DTOCEstatus estatus, Integer orden, Integer votos,
			String usuario, Date fechaHora) {
		super();
		this.id = id;
		this.orden = orden;
		this.votos = votos;
		this.usuario = usuario;
		this.fechaHora = fechaHora;
	}
	
	/**
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public DTOActaCasillaVotosPK getId() {
		return id;
	}

	public void setId(DTOActaCasillaVotosPK id) {
		this.id = id;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getVotos() {
		return votos;
	}

	public void setVotos(Integer votos) {
		this.votos = votos;
	}

	public Integer getCapturada() {
		return this.capturada;
	}

	public void setCapturada(Integer capturada) {
		this.capturada = capturada;
	}

	public Integer getListaNominal() {
		return this.listaNominal;
	}

	public void setListaNominal(Integer listaNominal) {
		this.listaNominal = listaNominal;
	}

	@Override
	public Date getFechaHora() {
		// TODO Auto-generated method stub
		return fechaHora;
	}

	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public void setFechaHora(Date fechaHora) {
		// TODO Auto-generated method stub
		this.fechaHora = fechaHora;
	}

	@Override
	public void setUsuario(String usuario) {
		// TODO Auto-generated method stub
		this.usuario = usuario;
	}
	/**
	 * @return the cabeceraDistrital
	 */
	public String getCabeceraDistrital() {
		return cabeceraDistrital;
	}
	/**
	 * @param cabeceraDistrital the cabeceraDistrital to set
	 */
	public void setCabeceraDistrital(String cabeceraDistrital) {
		this.cabeceraDistrital = cabeceraDistrital;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public String getEmblema() {
		return emblema;
	}
	
	
	public void setEmblema(String emblema) {
		this.emblema = emblema;
	}
	
	
}
