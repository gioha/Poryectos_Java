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
@Table(name = "ACUERDOS.CONVOCATORIAS")
@NamedQuery(name = "DTOConvocatorias.findAll", query = "SELECT d FROM DTOConvocatorias d")
public class DTOConvocatorias extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4187905537648874076L;

	
	@EmbeddedId
	private DTOConvocatoriasPK id;

	@Column(name="TIPO_SESION", nullable = false)
	private Integer tipoSesion;

	@Column(name="CARACTER", nullable = true)
	private String caracter;
	
	@Column(name="FECHA_SESION", nullable = false)
	private Date fechaSesion;
	
	@Column(name="HORA", nullable = false)
	private Date hora;
	
	@Column(name="LUGAR", nullable = false)
	private String lugar;
	
	@Column(name="ASUNTO", nullable = true)
	private String asunto;
	
	@Column(name="ORDEN_DIA_ADJ", nullable = false)
	private String ordenDiaAdj;
	
	@Column(name="DOC_ANEXO_ADJ", nullable = true)
	private String docAnexoAdj;

	@Column(name="USUARIO", nullable = false)
	private String usuario;

	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;
	
	/**
	 * Constructor default de la clase DTOConvocatorias
	 * @author Giovanni Hernández Alonso
	 * @update Miguel Ortiz
	 * @since 12/10/2017
	 * @param 
	 * @return DTOConvocatorias
	 * **/
	public DTOConvocatorias() {

		this.id 				= new DTOConvocatoriasPK();
		this.tipoSesion			= new Integer(0);
		this.caracter 			= "";
		this.fechaSesion 		= new Date();
		this.hora	 			= new Date();
		this.lugar 				= "";
		this.asunto 			= "";
		this.ordenDiaAdj	 	= "";
		this.docAnexoAdj		= "";
		this.usuario 			= "";
		this.fechaHora			= new Date();
	
	}

	public DTOConvocatoriasPK getId() {
		return id;
	}

	public void setId(DTOConvocatoriasPK id) {
		this.id = id;
	}

	public Integer getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(Integer tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getOrdenDiaAdj() {
		return ordenDiaAdj;
	}

	public void setOrdenDiaAdj(String ordenDiaAdj) {
		this.ordenDiaAdj = ordenDiaAdj;
	}

	public String getDocAnexoAdj() {
		return docAnexoAdj;
	}

	public void setDocAnexoAdj(String docAnexoAdj) {
		this.docAnexoAdj = docAnexoAdj;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

}
