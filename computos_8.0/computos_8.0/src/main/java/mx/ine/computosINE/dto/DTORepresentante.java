package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.REPRESENTANTES")
@NamedQuery(name = "DTOConsejeros.findAll", query = "SELECT d FROM DTORepresentante d")
public class DTORepresentante extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5028462350042635194L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "idProcesoElectoral", column = @Column(name = "ID_PROCESO_ELECTORAL", nullable = false, precision = 5, scale = 0)),
		@AttributeOverride(name = "idDetalleProceso", column = @Column(name = "ID_DETALLE_PROCESO", nullable = false, precision = 7, scale = 0)),
		@AttributeOverride(name = "idEstado", column = @Column(name = "ID_ESTADO", nullable = false, precision = 2, scale = 0)),
		@AttributeOverride(name = "idDistrito", column = @Column(name = "ID_DISTRITO", nullable = false, precision = 2, scale = 0)),
		@AttributeOverride(name = "idMunicipio", column = @Column(name = "ID_MUNICIPIO", nullable = false, precision = 3, scale = 0)),
		@AttributeOverride(name = "idRegiduria", column = @Column(name = "ID_REGIDURIA", nullable = false, precision = 2, scale = 0)),
		@AttributeOverride(name = "idComunidad", column = @Column(name = "ID_COMUNIDAD", nullable = false, precision = 4, scale = 0)),
		@AttributeOverride(name = "idTipoCandidatura", column = @Column(name = "ID_TIPO_CANDIDATURA", nullable = false, precision = 3, scale = 0)),
		@AttributeOverride(name = "tipoActa", column = @Column(name = "TIPO_ACTA", nullable = false, precision = 1, scale = 0))})
	private DTORepresentantePK pk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_PROCESO_ELECTORAL", referencedColumnName = "ID_PROCESO_ELECTORAL", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_DETALLE_PROCESO", referencedColumnName = "ID_DETALLE_PROCESO", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_DISTRITO", referencedColumnName = "ID_DISTRITO", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_REGIDURIA", referencedColumnName = "ID_REGIDURIA", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_COMUNIDAD", referencedColumnName = "ID_COMUNIDAD", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ID_TIPO_CANDIDATURA", referencedColumnName = "ID_TIPO_CANDIDATURA", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "TIPO_ACTA", referencedColumnName = "TIPO_ACTA", nullable = false, insertable = false, updatable = false)})
	private DTOActaTipoCandidatura actaTipoCandidaturaRep;
	
	@Column(name = "ID_ASOCIACION", nullable = false)
	private Integer idAsociacion;
		
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "APATERNO", nullable = false)
	private String apellidoPaterno;
	
	@Column(name = "AMATERNO", nullable = false)
	private String apellidoMaterno;
	
	@Column(name = "CALIDAD_REPRESENTANTE", nullable = false)
	private char calidadRepresentante;

	@Column(name = "TIPO_ASOCIACION", nullable = false)
	private Integer tipoAsociacion;
	
	@Column(name = "NOMBRE_ASOCIACION", nullable = false)
	private String nombreAsociacion;
	
	@Column(name = "EMBLEMA_ASOCIACION", nullable = true)
	private String emblemaAsociacion;
	
	@Column(name = "SIGLAS_ASOCIACION", nullable = false)
	private String siglasAsociacion;
		
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    
    @Column(name = "ORDEN", nullable = true)
    private Integer orden;

	public DTORepresentante() {}
    
	public DTORepresentante(DTORepresentantePK pk){
		this.pk = pk;
	}
	
	public DTORepresentante(DTORepresentantePK pk, Integer idAsociacion, Integer idRepresentante, String nombre, String apellidoPaterno,
			String apellidoMaterno, char calidadRepresentante) {
		
		this.pk = pk;
		this.idAsociacion = idAsociacion;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.calidadRepresentante = calidadRepresentante;

	}

	public DTORepresentantePK getPk() {
		return pk;
	}

	public void setPk(DTORepresentantePK pk) {
		this.pk = pk;
	}

	public Integer getIdAsociacion() {
		return idAsociacion;
	}

	public void setIdAsociacion(Integer idAsociacion) {
		this.idAsociacion = idAsociacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public char getCalidadRepresentante() {
		return calidadRepresentante;
	}

	public void setCalidadRepresentante(char calidadRepresentante) {
		this.calidadRepresentante = calidadRepresentante;
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


	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	@Override
	public String getUsuario() {
		return usuario;
	}

	@Override
	public void setFechaHora(Date arg0) {
		fechaHora = arg0;
		
	}

	@Override
	public void setUsuario(String arg0) {
		usuario = arg0;
		
	}

	public DTOActaTipoCandidatura getActaTipoCandidaturaRep() {
		return actaTipoCandidaturaRep;
	}

	public void setActaTipoCandidaturaRep(
			DTOActaTipoCandidatura actaTipoCandidaturaRep) {
		this.actaTipoCandidaturaRep = actaTipoCandidaturaRep;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
