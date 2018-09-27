package mx.ine.computosINE.dto;

import java.io.Serializable; 
import java.util.Calendar;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.ACTA_TIPO_CANDIDATURA")
@NamedQuery(name = "DTOActaTipoCandidatura.findAll", query = "SELECT d FROM DTOActaTipoCandidatura d")
public class DTOActaTipoCandidatura extends DTOBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2468571460675242982L;

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
	private DTOActaTipoCandidaturaPK pk;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA_ACTA", nullable = false)
	private Date fechaHoraActa;
	
	@Column(name = "CONDICIONES_COMPUTO", nullable = true)
	private String condicionesComputo;
	
	@Column(name = "UBICACION_COMPUTO", nullable = false)
	private String ubicacionComputo;
	
	@Column(name = "BOLETAS_SOBRANTES", nullable = true)
	private Integer boletasSobrantes;
	
	@Column(name = "VOTANTES_LISTA", nullable = true)
	private Integer votantesLista;
	
	@Column(name = "REP_VOTANTES_NOLISTA", nullable = true)
	private Integer repVotantesNoLista;
	
	@Column(name = "TOTAL_VALIDOS_NULOS", nullable = true)
	private Integer totalValidosNulos;
	
	@Column(name = "COINCIDE_SUMA", nullable = true)
	private Character coincideSuma;
	
	@Column(name = "COINCIDE_RESULTADO", nullable = true)
	private Character coincideResultado;
	
	@Column(name = "EDITABLE", nullable = false)
	private Integer editable;
	
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
	
    /**
     * Constructor vacío
     */
    public DTOActaTipoCandidatura(){}
    
    public DTOActaTipoCandidatura(DTOActaTipoCandidaturaPK pk){
    	this.pk = pk;
    }
    
	public DTOActaTipoCandidatura(DTOActaTipoCandidaturaPK pk,
			Date fechaHoraActa, String condicionesComputo,
			String ubicacionComputo, Integer boletasSobrantes,
			Integer votantesLita, Integer repVotantesNoLista,
			Integer totalValidosNulos, char coincideSuma,
			char coincideResultado) {
		this.pk = pk;
		this.fechaHoraActa = fechaHoraActa;
		this.condicionesComputo = condicionesComputo;
		this.ubicacionComputo = ubicacionComputo;
		this.boletasSobrantes = boletasSobrantes;
		this.votantesLista = votantesLita;
		this.repVotantesNoLista = repVotantesNoLista;
		this.totalValidosNulos = totalValidosNulos;
		this.coincideSuma = coincideSuma;
		this.coincideResultado = coincideResultado;
	}
	
	
	public DTOActaTipoCandidaturaPK getPk() {
		return pk;
	}


	public void setPk(DTOActaTipoCandidaturaPK pk) {
		this.pk = pk;
	}

	public Date getFechaHoraActa() {
		return fechaHoraActa;
	}

	public void setFechaHoraActa(Date fechaHoraActa) {
		this.fechaHoraActa = fechaHoraActa;
	}

	public String getCondicionesComputo() {
		return condicionesComputo;
	}


	public void setCondicionesComputo(String condicionesComputo) {
		this.condicionesComputo = condicionesComputo;
	}


	public String getUbicacionComputo() {
		return ubicacionComputo;
	}


	public void setUbicacionComputo(String ubicacionComputo) {
		this.ubicacionComputo = ubicacionComputo;
	}


	public Integer getBoletasSobrantes() {
		return boletasSobrantes;
	}


	public void setBoletasSobrantes(Integer boletasSobrantes) {
		this.boletasSobrantes = boletasSobrantes;
	}


	public Integer getVotantesLista() {
		return votantesLista;
	}


	public void setVotantesLista(Integer votantesLista) {
		this.votantesLista = votantesLista;
	}


	public Integer getRepVotantesNoLista() {
		return repVotantesNoLista;
	}


	public void setRepVotantesNoLista(Integer repVotantesNoLista) {
		this.repVotantesNoLista = repVotantesNoLista;
	}


	public Integer getTotalValidosNulos() {
		return totalValidosNulos;
	}


	public void setTotalValidosNulos(Integer totalValidosNulos) {
		this.totalValidosNulos = totalValidosNulos;
	}

	public Character getCoincideSuma() {
		return coincideSuma;
	}

	public void setCoincideSuma(Character coincideSuma) {
		this.coincideSuma = coincideSuma;
	}

	public Character getCoincideResultado() {
		return coincideResultado;
	}

	public void setCoincideResultado(Character coincideResultado) {
		this.coincideResultado = coincideResultado;
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

	public Integer getEditable() {
		return editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
	}



}
