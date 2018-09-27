/**
 * @(#)DTOBandejaEInfo.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.util.Utilidades;
import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.dto.DTOPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 * @since 05/08/2017
 */
@Entity
@Table(name = "BANDEJA_E_INFO", schema = "gestion4")
@IdClass(value = DTOBandejaEInfoID.class)
public class DTOBandejaEInfoEntity extends DTOBase implements DTOPaginarLazyInterface{

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 8853445689720137658L;

	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "BORRADOR_DOCUMENTOS",
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer idPersona;

	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "BORRADOR_DOCUMENTOS",
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;

	/**
	 * Atributo para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity persona;

	/**
	 * Atributo para cargar la información de la tabla de DOCUMENTOS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity documento;

	/**
	 * Atributo que guarda el año del documento
	 */
	@Column(name = "ANIO")
	private Integer anio;

	/**
	 * Atributo que indica el id Area
	 */
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Atributo que indica el tipo area
	 */
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;
	
	/**
	 * Atributo que indica si se ha leido
	 */
	@Column(name = "NO_LEIDO")
	private Integer noLeido;
	
	/**
	 * Atributo con el histórico recep
	 */
	 @Column(name = "ID_HISTORICO_RECEP")
	 private Integer idHistoricoRecep;
	 
	/**
	 * Atributo con el histórico padre
	 */
	 @Column(name = "ID_HISTORICO_PADRE")
	 private Integer idHistoricoPadre;
	
	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_RECEPCION")
	private Date fechaRecepcion;

	/**
	 * Atributo que permite guardar el nombre del usuario
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	/**
	 * Método que sirve para obtener los filtros utilizados en la consulta de
	 * Documentos Recibidos
	 * 
	 * @param conAlias
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public String obtenerAtributos(boolean conAlias){
		String cad = "";
		boolean hayCampos = false;
		if (this.idPersona != null) {
			cad = (conAlias) ? cad + Utilidades.mensajeProperties("alias_tabla_bandeja_e_info")+".": cad;
			cad += "ID_PERSONA=" + this.idPersona;
			hayCampos = true;
		}

		if (this.idDocumento != null) {
			cad = (conAlias) ? cad + Utilidades.mensajeProperties("alias_tabla_bandeja_e_info")+".": cad;
			cad = (hayCampos) ? cad + ", " : cad;
			cad += "ID_DOCUMENTO=" + this.idDocumento;
		}

		if (this.anio != null) {
			cad = (conAlias) ? cad + Utilidades.mensajeProperties("alias_tabla_bandeja_e_info")+".": cad;
			cad = (hayCampos) ? cad + ", " : cad;
			cad += "ANIO=" + this.anio;
		}

		if (this.idArea != null) {
			cad = (conAlias) ? cad + Utilidades.mensajeProperties("alias_tabla_bandeja_e_info")+".": cad;
			cad = (hayCampos) ? cad + ", " : cad;
			cad += "ID_AREA=" + this.idArea;
		}

		if (this.tipoArea != null) {
			cad = (conAlias) ? cad + Utilidades.mensajeProperties("alias_tabla_bandeja_e_info")+".": cad;
			cad = (hayCampos) ? cad + ", " : cad;
			cad += "TIPO_AREA=" + this.tipoArea;
		}

//		if (this.contieneAnexos != null) {
//			cad = (conAlias) ? cad + Utilidades.mensajeProperties("alias_tabla_bandeja_e_info")+".": cad;
//			cad = (hayCampos) ? cad + ", " : cad;
//			cad += "CONTIENE_ANEXOS=" + this.contieneAnexos;
//		}

//		if (this.fechaRecepcion != null) {
//			cad = (hayCampos) ? cad + ", " : cad;
//			cad += "FECHA_HORA=" + this.fechaRecepcion.toString();
//		}

		return cad;
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOBandejaEInfoEntity other = (DTOBandejaEInfoEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}
	
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOBandejaEInfoEntity [idPersona=" + idPersona
				+ ", idDocumento=" + idDocumento + ", persona=" + persona
				+ ", documento=" + documento + ", anio=" + anio + ", idArea="
				+ idArea + ", tipoArea=" + tipoArea + ", noLeido=" + noLeido
				+ ", idHistoricoRecep=" + idHistoricoRecep
				+ ", idHistoricoPadre=" + idHistoricoPadre
				+ ", fechaRecepcion=" + fechaRecepcion + ", usuario=" + usuario
				+ ", fechaHora=" + fechaHora + "]";
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOPaginarLazyInterface#getRowKey()
	 */
	@Override
	public String getRowKey() {
		return new StringBuilder().append(idDocumento).append(idPersona)
				.toString();
	}

	// -------------- GETTERS & SETTERS para Join -------------------- //

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y
	 * "ESTRUCTURA_AREAS". Método que inicializa el Id Área del objeto
	 * documento.
	 * 
	 * @param idAreaDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdAreaDocumento(int idAreaDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}
		documento.setIdArea(idAreaDocumento);
		documento.getArea().setIdArea(idAreaDocumento);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y
	 * "ESTRUCTURA_AREAS". Método que inicializa el tipo Área del objeto
	 * documento.
	 * 
	 * @param tipoAreaDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setTipoAreaDocumento(int tipoAreaDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}
		documento.setTipoArea(tipoAreaDocumento);
		documento.getArea().setTipoArea(tipoAreaDocumento);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS",
	 * "ESTRUCTURA_AREAS" y "C_AREAS" Método que inicializa el Id Entidad del
	 * Área del objeto documento.
	 * 
	 * @param idEntidadArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdEntidadArea(int idEntidadArea) {

		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}
		documento.getArea().setIdEntidad(idEntidadArea);

	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y
	 * "ESTRUCTURA_AREAS". Método que inicializa la descripción del Área del
	 * objeto documento.
	 * 
	 * @param descripcionArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setDescripcionArea(String descripcionArea) {

		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}

		documento.getArea().setDescripcion(descripcionArea);

	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y
	 * "ESTRUCTURA_AREAS" . Método que inicializa las siglas del Área del objeto
	 * documento.
	 * 
	 * @param siglasArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setSiglasArea(String siglasArea) {
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}

		documento.getArea().setSiglas(siglasArea);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS"
	 * "ESTRUCTURA_AREAS" y C_TIPO_DOCUMENTOS. Método que inicializa el Id del
	 * tipo de documento del objeto documento.
	 * 
	 * @param idTipoDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdTipoDocumento(int idTipoDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getTipoDocumento() == null) {
			documento.setTipoDocumento(new DTOCTipoDocumentoEntity());
		}

		documento.getTipoDocumento().setIdTipoDocumento(idTipoDocumento);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y "ACRONIMOS".
	 * Método que inicializa el Id del acronimo del documento del objeto
	 * documento.
	 * 
	 * @param idAcronimoDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdAcronimoDocumento(int idAcronimoDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getAcronimo() == null) {
			documento.setAcronimo(new DTOAcronimoEntity());
		}
		documento.setIdAcronimo(idAcronimoDocumento);
		documento.getAcronimo().setIdAcronimo(idAcronimoDocumento);

	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el número del documento del objeto documento.
	 * 
	 * @param numDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setNumDocumento(String numDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		documento.setNoDocumento(numDocumento);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el nombre del documento del objeto documento.
	 * 
	 * @param nombreDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setNombreDocumento(String nombreDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		documento.setNombreDocumento(nombreDocumento);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el estatus del documento del objeto documento.
	 * 
	 * @param estatusDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setEstatusDocumento(char estatusDocumento) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		documento.setEstatus(estatusDocumento);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa la fecha de creación del objeto documento.
	 * 
	 * @param fechaCreacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since Nov 22, 2017
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}
		documento.setFechaCreacion(fechaCreacion);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el estatus del documento del objeto documento.
	 * 
	 * @param tipoCaptura
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setTipoCaptura(Integer tipoCaptura) {
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setTipoCaptura(tipoCaptura);
	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el asunto del documento del objeto documento.
	 * 
	 * @param asunto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/11/2017
	 */
	public void setAsunto(String asunto) {
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setAsunto(asunto);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa la bandera de contieneAnexos del documento del objeto documento.
	 * 
	 * @param contieneAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setContieneAnexos(Integer contieneAnexos){
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setContieneAnexos(contieneAnexos);
	}
	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		if (documento.getPersona() == null) {
			documento.setPersona(new DTOEstructuraAreasEntity());
		}
		documento.setIdPersona(idPersona);
		documento.getPersona().setIdPersona(idPersona);
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;

		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdDocumento(idDocumento);
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public DTOEstructuraAreasEntity getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {
		this.persona = persona;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la
	 *         variable documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public DTODocumentoEntity getDocumento() {
		return documento;
	}

	/**
	 * @param documento
	 *            : valor que se ingresa a la variable de tipo
	 *            DTODocumentoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setDocumento(DTODocumentoEntity documento) {
		this.documento = documento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;

		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		documento.setAnio(anio);
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}



	/**
	 * @return valor de tipo Date que esta contenido en la variable
	 *         fechaRecepcion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion
	 *            : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return variable de tipo Integer contenida en noLeido
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido: variable de tipo Integer contenida en noLeido
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoRecep
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoRecep() {
		return idHistoricoRecep;
	}

	/**
	 * @param idHistoricoRecep: variable de tipo Integer contenida en idHistoricoRecep
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoRecep(Integer idHistoricoRecep) {
		this.idHistoricoRecep = idHistoricoRecep;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoPadre
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoPadre() {
		return idHistoricoPadre;
	}

	/**
	 * @param idHistoricoPadre: variable de tipo Integer contenida en idHistoricoPadre
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoPadre(Integer idHistoricoPadre) {
		this.idHistoricoPadre = idHistoricoPadre;
	}
	
	
}
