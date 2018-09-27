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
@Table(name = "ACUERDOS.RESPONSABLES_AREAS")
@NamedQuery(name = "DTOResponsables.findAll", query = "SELECT d FROM DTOResponsables d")
public class DTOResponsables extends DTOBase implements Serializable {
	private static final long serialVersionUID = -7583439161874562996L;

	@Id
	@Column(name = "ID_RESPONSABLE", nullable = false)
	private Integer idResponsable;

	@Column(name = "ID_AREA", nullable = false)
	private Integer idArea;

	@Column(name = "TIPO_AREA", nullable = false)
	private Integer tipoArea;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "APELLIDOS", nullable = false)
	private String apellidos;

	@Column(name = "PUESTO", nullable = false)
	private String puesto;

	@Column(name = "TRATAMIENTO", nullable = true)
	private String tratamiento;

	@Column(name = "EXTENSION_TEL", nullable = true)
	private String extensionTel;

	@Column(name = "CUENTA_LDAP", nullable = false)
	private String cuentaLdap;

	@Column(name = "CORREO", nullable = false)
	private String correo;

	@Column(name = "NOMBRE_NIVEL_AREA", nullable = true)
	private String nomNivelArea;

	@Column(name = "GENERO", nullable = true)
	private String genero;

	@Column(name = "ESTATUS", nullable = false)
	private Integer estatus;

	@Column(name="USUARIO", nullable = false)
	private String usuario;

	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;
	
	@Column(name="TIPO_RESPONSABLE", nullable = false)
	private Integer tipoResponsable;
	
//	@ManyToOne
//    private DTOCAreas area;

	public DTOResponsables (){
		this.idResponsable 	= new Integer(0);
		this.idArea			= new Integer(0);
		this.tipoArea		= new Integer(0);	
		this.nombre			= "";
		this.apellidos		= "";
		this.puesto			= "";
		this.tratamiento	= "";
		this.extensionTel	= "";
		this.cuentaLdap		= "";
		this.correo			= "";
		this.nomNivelArea		= "";
		this.genero			= "";
		this.estatus		= new Integer(0);	
		this.usuario 		= "";
		this.fechaHora		= new Date();
		this.tipoResponsable= new Integer(0); 
//		this.area			= new DTOCAreas();
	}

	public DTOResponsables(String nombre, String apellidos, String puesto,
			String tratamiento, String extensionTel, String cuentaLdap,
			String correo, String nomNivelArea, String genero) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.puesto = puesto;
		this.tratamiento = tratamiento;
		this.extensionTel = extensionTel;
		this.cuentaLdap = cuentaLdap;
		this.correo = correo;
		this.nomNivelArea = nomNivelArea;
		this.genero = genero;
	}

	public Integer getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getExtensionTel() {
		return extensionTel;
	}

	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}

	public String getCuentaLdap() {
		return cuentaLdap;
	}

	public void setCuentaLdap(String cuentaLdap) {
		this.cuentaLdap = cuentaLdap;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNomNivelArea() {
		return nomNivelArea;
	}

	public void setNomNivelArea(String nomNivelArea) {
		this.nomNivelArea = nomNivelArea;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result
				+ ((cuentaLdap == null) ? 0 : cuentaLdap.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result
				+ ((extensionTel == null) ? 0 : extensionTel.hashCode());
		result = prime * result
				+ ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idResponsable == null) ? 0 : idResponsable.hashCode());
		result = prime * result
				+ ((nomNivelArea == null) ? 0 : nomNivelArea.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((puesto == null) ? 0 : puesto.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
		result = prime * result
				+ ((tratamiento == null) ? 0 : tratamiento.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOResponsables other = (DTOResponsables) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (cuentaLdap == null) {
			if (other.cuentaLdap != null)
				return false;
		} else if (!cuentaLdap.equals(other.cuentaLdap))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (extensionTel == null) {
			if (other.extensionTel != null)
				return false;
		} else if (!extensionTel.equals(other.extensionTel))
			return false;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idResponsable == null) {
			if (other.idResponsable != null)
				return false;
		} else if (!idResponsable.equals(other.idResponsable))
			return false;
		if (nomNivelArea == null) {
			if (other.nomNivelArea != null)
				return false;
		} else if (!nomNivelArea.equals(other.nomNivelArea))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (puesto == null) {
			if (other.puesto != null)
				return false;
		} else if (!puesto.equals(other.puesto))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		if (tratamiento == null) {
			if (other.tratamiento != null)
				return false;
		} else if (!tratamiento.equals(other.tratamiento))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public Integer getTipoResponsable() {
		return tipoResponsable;
	}

	public void setTipoResponsable(Integer tipoResponsable) {
		this.tipoResponsable = tipoResponsable;
	}

//	public DTOCAreas getArea() {
//		return area;
//	}
//
//	public void setArea(DTOCAreas area) {
//		this.area = area;
//	}

	@Override
	public String toString() {
		return "DTOResponsables [idResponsable=" + idResponsable + ", idArea=" + idArea + ", tipoArea=" + tipoArea
				+ ", nombre=" + nombre + ", apellidos=" + apellidos + ", puesto=" + puesto + ", tratamiento="
				+ tratamiento + ", extensionTel=" + extensionTel + ", cuentaLdap=" + cuentaLdap + ", correo=" + correo
				+ ", nomNivelArea=" + nomNivelArea + ", genero=" + genero + ", estatus=" + estatus + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora + ", tipoResponsable=" + tipoResponsable + "]";
	}

}
