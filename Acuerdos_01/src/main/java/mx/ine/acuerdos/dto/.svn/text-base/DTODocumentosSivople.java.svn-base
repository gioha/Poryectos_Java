package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.DOCUMENTOS_SIVOPLE")
@NamedQuery(name = "DTODocumentosSivople.findAll", query = "SELECT d FROM DTODocumentosSivople d")
public class DTODocumentosSivople extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8963623927959095498L;

	
	@EmbeddedId
	private DTODocumentosSivoplePK id;
	@Column(name = "ID_TIPO_DOCUMENTO", nullable = false)
	private Integer idTipoDocumento;
	@Column(name = "NOMBRE_DOCUMENTO", nullable = true)
	private String nombreDocumento;
	@Column(name = "NUM_PERIODO", nullable = false)
	private Integer numPeriodo;
	@Column(name = "USUARIO", nullable = false)
	private String usuario;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;
	@Column(name = "ACTIVO", nullable = false)
	private Integer activo;
	@Column(name = "URL_ARCHIVO_ADJ", nullable = true)
	private String urlArchivoAdjunto;


	/**
	 * Constructor default de la clase DTODocumentosSivople
	 *  @author Giovanni Hernández Alonso
	 * @since 12/10/2017
	 * @param 
	 * @return DTODocumentosSivople
	 * **/
	public DTODocumentosSivople() {
		this.id 				= new DTODocumentosSivoplePK();
		this.idTipoDocumento	= null;
		this.nombreDocumento 	= "";
		this.numPeriodo			= null;
		this.activo				= new Integer(1);
		this.urlArchivoAdjunto 	= "";
		setUsuario("");
		setFechaHora( new Date() );
	}
	
	@Override
	public Date getFechaHora() {
		return this.fechaHora;
	}

	@Override
	public String getUsuario() {
		return this.usuario;
	}

	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public DTODocumentosSivoplePK getId() {
		return id;
	}

	public void setId(DTODocumentosSivoplePK id) {
		this.id = id;
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public Integer getNumPeriodo() {
		return numPeriodo;
	}

	public void setNumPeriodo(Integer numPeriodo) {
		this.numPeriodo = numPeriodo;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getUrlArchivoAdjunto() {
		return urlArchivoAdjunto;
	}

	public void setUrlArchivoAdjunto(String urlArchivoAdjunto) {
		this.urlArchivoAdjunto = urlArchivoAdjunto;
	}

	@Override
	public String toString() {
		return "DTODocumentosSivople [id=" + id + ", idTipoDocumento=" + idTipoDocumento + ", nombreDocumento="
				+ nombreDocumento + ", numPeriodo=" + numPeriodo + ", usuario=" + usuario + ", fechaHora=" + fechaHora
				+ ", activo=" + activo + ", urlArchivoAdjunto=" + urlArchivoAdjunto + "]";
	}

}
