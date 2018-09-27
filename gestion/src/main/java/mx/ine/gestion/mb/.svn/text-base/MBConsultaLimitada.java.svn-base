/**
 * @(#)MBConsultaLimitada.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bo.inter.BOConsultaLimitadaInterface;
import mx.ine.gestion.bsd.inter.BSDConsultaLimitadaInterface;
import mx.ine.gestion.dto.helper.DTODocumentoGlusterHelper;
import mx.ine.gestion.dto.helper.DTOResultadoConsultaHelper;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Clase para hacer consultas
 *
 * @autor INE
 * @copy MAVO
 * @since 12/07/2016
 */
public class MBConsultaLimitada implements Serializable {

	/**
	 * Objeto para la serialización de esta clase. 
	 */
	private static final long serialVersionUID = 5333599393846193628L;

	@Autowired
	@Qualifier("boConsultaLimitada")
	private transient BOConsultaLimitadaInterface boConsultaLimitadaInterface;

	@Autowired
	@Qualifier("bsdConsultaLimitada")
	private transient BSDConsultaLimitadaInterface bsdConsultaLimitadaInterface;

	/**
	 * Guarda operación
	 */
	private String operacion;

	/**
	 * Guarda contraseña
	 */
	private String contrasenia;

	/**
	 * Ruta ingresada en el sistema
	 */
	private String ruta;

	/**
	 * Constante que contiene el booleano de la validación para saber si puede subir de nivel
	 */
	private boolean validaRutaRegresar;

	/**
	 * Lista de resultado
	 */
	private List<DTOResultadoConsultaHelper> listaResultado;

	/**
	 * Lista de archivos encontrada
	 */
	private List<DTODocumentoGlusterHelper> listaArchivos;

	/**
	 * Archivo que será eliminado
	 */
	private DTODocumentoGlusterHelper archivoAEliminar;
	
	
	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------- INICIO - EVENTOS -------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */



	/**
	 * Método para validar la operación
	 * 
	 * @throws Exception: error en caso de que ocurra un problema o no pase las validaciones
	 *
	 * @autor INE
	 * @since 12/07/2016
	 */
	public void validarOperacion() throws Exception {
		
		String mensajeError = this.boConsultaLimitadaInterface.validarContraseniaYPermisosOperacion(this.operacion, this.contrasenia);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeError, ""));
	        
	        return;
	     }
		
		String operacionMinisculas = this.operacion.toLowerCase();
		
		if (operacionMinisculas.contains("delete")) {
			
			RequestContext.getCurrentInstance().execute("PF('confirmaDelete').show()");
			return;
		}
		
		if (operacionMinisculas.contains("insert")) {
			
			RequestContext.getCurrentInstance().execute("PF('confirmaInsert').show()");
			return;
		}
		
		if (operacionMinisculas.contains("update")) {
			
			RequestContext.getCurrentInstance().execute("PF('confirmaUpdate').show()");
			return;
		}
		
		if ((operacionMinisculas.contains("select")) 
		&& (!operacionMinisculas.contains("where"))) {
			
			RequestContext.getCurrentInstance().execute("PF('confirmaSinWhere').show()");
			return;
		}

		if (operacionMinisculas.contains("drop") || operacionMinisculas.contains("alter")) {
			
			RequestContext.getCurrentInstance().execute("PF('confirmaNOEJECUTABLE').show()");
			return;
		}
	
		this.generarOperacion();
	}
	
	/**
	 * Método para generar operación
	 *
	 * @autor INE
	 * @since 12/07/2016
	 */
	public void generarOperacion() {
		
		this.listaResultado = bsdConsultaLimitadaInterface.ejecutaAccion(this.operacion);
	}

	/**
	 * Método para consultar los archivos del gluster
	 *
	 * @autor INE
	 * @since 12/07/2016
	 */
	public void consultarArchivos() {
		
		this.listaArchivos = boConsultaLimitadaInterface.obtenerArchivosGluster(this.ruta);

		if (this.listaArchivos.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se obtuvieron resultados de la ruta especificada", 
	        		"En caso de ser una carpeta puede que este vacía, de lo contrario valide la ruta y los permisos de lectura"));
		}

		this.validarRegresarEnRuta();
	}

	/**
	 * Método para consultar los archivos del gluster cada que le 
	 * den clic en uno de los registros(en el nombre) que les arrojo la consulta
	 *
	 * @author INE
	 * @since 22/08/2016
	 */
	public void consultarArchivosPorLink(DTODocumentoGlusterHelper archivo) {
		
		String rutaUltimaPosicion = String.valueOf(this.ruta != null && !this.ruta.isEmpty() ? this.ruta.charAt(this.ruta.length()-1) : "");
		this.ruta = this.ruta + (rutaUltimaPosicion.equalsIgnoreCase("/") || rutaUltimaPosicion.equalsIgnoreCase("\\") ?  "" : File.separator) 
							  + archivo.getNombreDocumento();

		this.consultarArchivos();
	}

	/**
	 * Método para ir subiendo de nivel dependiendo de la ruta que se tenga 
	 *
	 * @author INE
	 * @since 22/08/2016
	 */
	public void regresarEnRuta() {

		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] rutaArreglo = this.ruta.split(pattern);		
		this.ruta = "";
		
		for (int indice = 0; indice < rutaArreglo.length-1; indice++) {
			this.ruta = this.ruta + (indice == 0 ? "" : File.separator) + rutaArreglo[indice];
		}

		this.consultarArchivos();
	}

	/**
	 * Método para validar si se puede subir de nivel 
	 *
	 * @author INE
	 * @since 22/08/2016
	 */
	public void validarRegresarEnRuta() {

		if (!this.ruta.isEmpty()) {

			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] rutaArreglo = this.ruta.split(pattern);
			
			if(rutaArreglo.length > 1) {
	
				validaRutaRegresar = true;
				
			} else {
				
				validaRutaRegresar = false;
			}

		} else {
			
			validaRutaRegresar = false;
		}
	}

	/**
	 * Método para verificar que tenga permisos para eliminar en gluster
	 * 
	 * @autor INE
	 * @since 12/07/2016
	 */
	public void validarEliminarArchivos() {
		
		if (this.contrasenia.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña es requerida para esta operación", ""));
	        
	        return;
		}
		
		String mensajeError = this.boConsultaLimitadaInterface.validarContraseniaYPermisosOperacion("DELETE", this.contrasenia);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeError, ""));
	        
	        return;
	     }

		RequestContext.getCurrentInstance().execute("PF('confirmaDeleteGluster').show()");
	}

	/**
	 * Método para eliminar archivos del gluster
	 *
	 * @author INE
	 * @since 10/08/2016
	 */
	public void eliminarArchivo() {

		String nombreArchivo = archivoAEliminar.getNombreDocumento();
		boolean seBorro = this.boConsultaLimitadaInterface.eliminarArchivosGluster(this.listaArchivos, archivoAEliminar);
		
		if (seBorro) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Se elimino el archivo " + nombreArchivo + " correctamente", ""));

		} else {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el archivo " + nombreArchivo + ", reintente de nuevo o verifique los permisos", ""));
		}
	}

	/**
	 * Método para verificar que tenga permisos para eliminar el contenido de una carpeta en gluster
	 * 
	 * @throws Exception: error en caso de que ocurra un problema o no pase las validaciones
	 *
	 * @autor INE
	 * @since 12/07/2016
	 */
	public void validarEliminarContenidoCarpeta() {
		
		if (this.contrasenia.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña es requerida para esta operación", ""));
	        
	        return;
		}
		
		String mensajeError = this.boConsultaLimitadaInterface.validarContraseniaYPermisosOperacion("DELETE", this.contrasenia);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeError, ""));
	        
	        return;
	     }

		RequestContext.getCurrentInstance().execute("PF('confirmaDeleteGlusterDirectory').show()");
	}

	/**
	 * Método para eliminar el contenido de una carpeta de gluster
	 *
	 * @author INE
	 * @since 10/08/2016
	 */
	public void eliminarContenidoCarpeta() {
		
		boolean seBorro = this.boConsultaLimitadaInterface.eliminarContenidoCarpeta(archivoAEliminar);
		
		if (seBorro) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Se elimino el contenido de la carpeta  correctamente", ""));

		} else {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	        		"Hubo un error al eliminar el contenido, uno o más archivos pudieron no borrarse , reintente de nuevo o verifique los permisos", ""));
		}
		
		this.consultarArchivos();
	}

	/**
	 * Método para agregar contenido en el gluster
	 * 
	 * @param archivo
	 *
	 * @author INE
	 * @since 23/02/2017
	 */
	public void adjuntarArchivo(FileUploadEvent archivo) {

		
	}
	
	/* ----------------------------------------------------------------------------------------- */
	/* ----------------------------------  FIN - EVENTOS --------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */	
	
	
	/* ---------------------------------- GETTERS SETTERS -------------------------------------- */

	/**
	 * @return valor de tipo String que esta contenido en la variable operacion
	 *
	 * @autor INE
	 * @since 19/07/2016
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion : valor que se ingresa a la variable de tipo String
	 *
	 * @autor INE
	 * @since 19/07/2016
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable contrasenia
	 *
	 * @autor INE
	 * @since 19/07/2016
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia : valor que se ingresa a la variable de tipo String
	 *
	 * @autor INE
	 * @since 19/07/2016
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return valor de tipo List<HLPResultadoConsulta> que esta contenido en la variable listaResultado
	 *
	 * @autor INE
	 * @since 19/07/2016
	 */
	public List<DTOResultadoConsultaHelper> getListaResultado() {
		return listaResultado;
	}

	/**
	 * @param listaResultado : valor que se ingresa a la variable de tipo List<HLPResultadoConsulta>
	 *
	 * @autor INE
	 * @since 19/07/2016
	 */
	public void setListaResultado(List<DTOResultadoConsultaHelper> listaResultado) {
		this.listaResultado = listaResultado;
	}

	/**
	 * @return valor de tipo List<HLPDocumentoGluster> que esta contenido en la variable listaArchivos
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public List<DTODocumentoGlusterHelper> getListaArchivos() {
		return listaArchivos;
	}

	/**
	 * @param listaArchivos : valor que se ingresa a la variable de tipo List<HLPDocumentoGluster>
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public void setListaArchivos(List<DTODocumentoGlusterHelper> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable ruta
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta : valor que se ingresa a la variable de tipo String
	 *
	 * @autor INE
	 * @since 22/07/2016
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * @return valor de tipo HLPDocumentoGluster que esta contenido en la variable archivoAEliminar
	 *
	 * @author INE
	 * @since 10/08/2016
	 */
	public DTODocumentoGlusterHelper getArchivoAEliminar() {
		return archivoAEliminar;
	}

	/**
	 * @param archivoAEliminar : valor que se ingresa a la variable de tipo HLPDocumentoGluster
	 *
	 * @author INE
	 * @since 10/08/2016
	 */
	public void setArchivoAEliminar(DTODocumentoGlusterHelper archivoAEliminar) {
		this.archivoAEliminar = archivoAEliminar;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable validaRutaRegresar
	 *
	 * @author INE
	 * @since 22/08/2016
	 */
	public boolean isValidaRutaRegresar() {
		return validaRutaRegresar;
	}

	/**
	 * @param validaRutaRegresar : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author INE
	 * @since 22/08/2016
	 */
	public void setValidaRutaRegresar(boolean validaRutaRegresar) {
		this.validaRutaRegresar = validaRutaRegresar;
	}

}
