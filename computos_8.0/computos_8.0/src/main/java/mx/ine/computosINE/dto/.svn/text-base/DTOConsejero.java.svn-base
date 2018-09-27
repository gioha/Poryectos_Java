package mx.ine.computosINE.dto;

import java.io.Serializable; 
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.CONSEJEROS")
@NamedQuery(name = "DTOConsejero.findAll", query = "SELECT d FROM DTOConsejero d")
public class DTOConsejero extends DTOBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920934597182393490L;
	
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
	private DTOConsejeroPK pk;
	
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
	private DTOActaTipoCandidatura actaTipoCandidaturaCon;
	
	
	@OneToOne
    @JoinColumn(name = "ID_PUESTO_FUNCIONARIO")
	private DTOCConsejo tipoConsejero;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "APATERNO", nullable = true)
	private String apellidoPaterno;
	
	@Column(name = "AMATERNO", nullable = true)
	private String apellidoMaterno;
	
	@Column(name = "CALIDAD_CONSEJERO", nullable = false)
	private char calidadConsejero;
	
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
	
    public DTOConsejero(){}
    
    public DTOConsejero(DTOConsejeroPK pk){
    	this.pk = pk;
    }
    
	public DTOConsejero(DTOConsejeroPK pk, Integer idConsejero,
			DTOCConsejo tipoConsejero, String nombre, String apellidoPaterno,
			String apellidoMaterno, char calidadConsejero) {
		
		this.pk = pk;
		this.tipoConsejero = tipoConsejero;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.calidadConsejero = calidadConsejero;

	}
    
	public DTOConsejeroPK getPk() {
		return pk;
	}

	public void setPk(DTOConsejeroPK pk) {
		this.pk = pk;
	}

	public DTOCConsejo getTipoConsejero() {
		return tipoConsejero;
	}

	public void setTipoConsejero(DTOCConsejo tipoConsejero) {
		this.tipoConsejero = tipoConsejero;
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

	public char getCalidadConsejero() {
		return calidadConsejero;
	}

	public void setCalidadConsejero(char calidadConsejero) {
		this.calidadConsejero = calidadConsejero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public DTOActaTipoCandidatura getActaTipoCandidaturaCon() {
		return actaTipoCandidaturaCon;
	}

	public void setActaTipoCandidaturaCon(
			DTOActaTipoCandidatura actaTipoCandidaturaCon) {
		this.actaTipoCandidaturaCon = actaTipoCandidaturaCon;
	}
	
	
}
