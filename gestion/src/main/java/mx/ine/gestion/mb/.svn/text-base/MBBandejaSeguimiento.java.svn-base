/**
 * @(#)MBBandejaComunes.java 16/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.File;
import java.io.Serializable;

import mx.ine.gestion.bo.inter.BOArchivoInteface;
import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Homero Fidel Villanueva
 * @since 16/11/2017
 */
public class MBBandejaSeguimiento implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 1124030777936279855L;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaSeguimiento")
	private transient BSDBandejaSeguimientoInterface bsdBandejaSeguimientoInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBorradorDocumentos")
	private transient BSDBandejaBorradoresInterface bsdBorradores;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boArchivo")
	private transient BOArchivoInteface boArchivoInteface;
	
	

	/**
	 * Atributo utilizado para guardar la ruta de los pdf's en el Gluster
	 */
	private String rutaGlusterPDF;

	/**
	 * Atributo utilizado para guardar la ruta de los Anexos en el Gluster
	 */
	private String rutaGlusterAnexos;

	/**
	 * Atributo utilizado para guardar la ruta de los documentos en el Gluster
	 */
	private String rutaGlusterDocumentos;

	/**
	 * 
	 */
	private DTOEstructuraAreasEntity usuario;

	

	// ------------------------ Métodos ------------------------ //
	public void iniciar() {
		setUsuario(SecurityContextHolder.getContext().getAuthentication()
				.getName());
		
		rutaGlusterAnexos = boArchivoInteface.obtenerRutaGlusterAnexosNube()
				+ File.separator;

		rutaGlusterPDF = boArchivoInteface.obtenerRutaGlusterPdfNube()
				+ File.separator;

	}

	// ------------------------ GETTERS SETTERS ------------------------ //
	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         rutaGlusterPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public String getRutaGlusterPDF() {
		return rutaGlusterPDF;
	}

	/**
	 * @param rutaGlusterPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setRutaGlusterPDF(String rutaGlusterPDF) {
		this.rutaGlusterPDF = rutaGlusterPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         rutaGlusterAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public String getRutaGlusterAnexos() {
		return rutaGlusterAnexos;
	}

	/**
	 * @param rutaGlusterAnexos
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setRutaGlusterAnexos(String rutaGlusterAnexos) {
		this.rutaGlusterAnexos = rutaGlusterAnexos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         rutaGlusterDocumentos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public String getRutaGlusterDocumentos() {
		return rutaGlusterDocumentos;
	}

	/**
	 * @param rutaGlusterDocumentos
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setRutaGlusterDocumentos(String rutaGlusterDocumentos) {
		this.rutaGlusterDocumentos = rutaGlusterDocumentos;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/11/2017
	 */
	public DTOEstructuraAreasEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/11/2017
	 */
	public void setUsuario(DTOEstructuraAreasEntity usuario) {
		this.usuario = usuario;
	}

	public void setUsuario(String usuario) {

		this.usuario = bsdBandejaSeguimientoInterface.consultarPersonaXCuenta(usuario);
	}

}
