package mx.ine.gestion.mb;

/**
 * @(#)MBHistorial.java 17/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.bsd.inter.BSDHistorialInterface;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;

import org.jboss.logging.Logger;
//import org.primefaces.context.RequestContext;
//import mx.ine.comunicaciones.dto.DTOEstados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Clase de la capa de MB para el módulo de Historial.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 17/11/2017
 */
public class MBHistorial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8660889204220243652L;

	// /////////////////////////////////////////////
	// -----------------ATRIBUTOS-----------------//
	// /////////////////////////////////////////////
	
	private List<DTOHistDocCreacionEntity> listaHistorial;
	
	private DTOComentariosDocumentoEntity comentario;
	
	@Autowired
	@Qualifier("bsdHistorial")
	private transient BSDHistorialInterface bsdHistorialInterface;

/*
	@Autowired
	@Qualifier("vhAcronimos")
	private transient VHAcronimosInterface vhAcronimosInterface;
*/

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBAcronimos.class);

	// /////////////////////////////////////////////
	// -----------------MÉTODOS-------------------//
	// /////////////////////////////////////////////

	public void iniciar() {
		listaHistorial = new ArrayList<DTOHistDocCreacionEntity>();	
	}
	
	/**
	 * Método para método para consultar los acrónimos que existen por área
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public void consultarHistorial(Integer idDocumento) {
		try {
			listaHistorial = bsdHistorialInterface.consultarHistorialPorIdDocumento(idDocumento);
			//RequestContext.getCurrentInstance().execute("PF('dialogHistorial').show()");
			//RequestContext.getCurrentInstance().execute("PF('dialogHistorialComentario').hide()");
			log.error(listaHistorial.size());
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * Método para método para consultar comentarios del historial
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public void mostrarComentarioHistorial(DTOComentariosDocumentoEntity dtoComentario) {
		if(dtoComentario != null) {
			comentario = dtoComentario;
			//RequestContext.getCurrentInstance().execute("PF('dialogHistorialComentario').show()");
		} else {

		}
	}

	public void cerrarComentario() {
		//RequestContext.getCurrentInstance().execute("PF('dialogHistorialComentario').hide()");
	}

	// /////////////////////////////////////////////
	// ---------------GETTERS Y SETTERS-----------//
	// /////////////////////////////////////////////

	/**
	 * @return variable de tipo List<DTOHistDocCreacionEntity> contenida en listaHistorial
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOHistDocCreacionEntity> getListaHistorial() {
		return listaHistorial;
	}

	/**
	 * @param listaHistorial: variable de tipo List<DTOHistDocCreacionEntity> contenida en listaHistorial
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaHistorial(List<DTOHistDocCreacionEntity> listaHistorial) {
		this.listaHistorial = listaHistorial;
	}

	/**
	 * @return the comentario
	 */
	public DTOComentariosDocumentoEntity getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(DTOComentariosDocumentoEntity comentario) {
		this.comentario = comentario;
	}
	
}
