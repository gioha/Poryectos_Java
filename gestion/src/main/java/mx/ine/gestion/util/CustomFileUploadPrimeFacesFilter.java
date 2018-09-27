/**
 * @(#)MBAdministradorSistemaPautas.java 27/05/2016
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.util;

import java.io.File;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jboss.logging.Logger;
import org.primefaces.util.Constants;
import org.primefaces.webapp.MultipartRequest;

/**
 * Clase que se usa para sobreescribir la clase por default que utiliza el
 * componente de "FILEUPLOAD" de primefaces, en este caso para decirle donde
 * generar las carpetas temporales que utiliza
 * 
 * @author Roberto Shirásago Domínguez
 * @since 27/05/2016
 * @copyright INE
 */
public class CustomFileUploadPrimeFacesFilter implements Filter {

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private final static Logger	log						= Logger.getLogger(CustomFileUploadPrimeFacesFilter.class);
	/**
	 * Variable que contiene el nombre del JNDI con el valor del gluster
	 */
	private static final String	GLUSTER_FS_NAME			= "java:/util/glusterFS";
	/**
	 * Nombre del parámetro/variable que se contiene el tamaño máximo que ocupa
	 * un temporal
	 */
	private final static String	THRESHOLD_SIZE_PARAM	= "thresholdSize";
	/**
	 * Tamaño máximo que ocupa un temporal, esta declarado en el web.xml
	 */
	private String				thresholdSize;
	/**
	 * Es el directorio donde se estaran guardando los archivos temporales
	 */
	private String				directorioTemporalArchivos;
	/**
	 * Para saber el tipo de Java que se usa y la opción del FileUpload más
	 * apropiada
	 */
	private boolean				bypass;

	/*
	 * (El comentario se encuentra en la interfase donde esta declarado el
	 * método)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		boolean isAtLeastJSF22 = detectJSF22();
		String uploader = filterConfig.getServletContext().getInitParameter(Constants.ContextParams.UPLOADER);
		if(uploader == null || uploader.equalsIgnoreCase("auto")){
			bypass = isAtLeastJSF22 ? true : false;
		} else if(uploader.equalsIgnoreCase("native")){
			bypass = true;
		} else if(uploader.equalsIgnoreCase("commons")){
			bypass = false;
		}
		try{
			Context env = new InitialContext();
			thresholdSize = filterConfig.getInitParameter(THRESHOLD_SIZE_PARAM);
			directorioTemporalArchivos = env.lookup(GLUSTER_FS_NAME) + File.separator
					+ Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") + File.separator
					+ Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmpPrimefaces");
		} catch(NamingException e){
			log.error("<======================== ERROR!!!!! en el CustomFileUploadPrimeFacesFilter");
			log.error("<======================== NO ENCONTRO EL VALOR DEL JNDI 'glusterFS'");
		}
	}

	/*
	 * (El comentario se encuentra en la interfase donde esta declarado el
	 * método)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		if(bypass){
			filterChain.doFilter(request, response);
			return;
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		boolean isMultipart = ServletFileUpload.isMultipartContent(httpServletRequest);
		if(isMultipart){
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			diskFileItemFactory.setFileCleaningTracker(null);
			if(thresholdSize != null){
				diskFileItemFactory.setSizeThreshold(Integer.valueOf(thresholdSize));
			}
			if(directorioTemporalArchivos != null){
				diskFileItemFactory.setRepository(new File(directorioTemporalArchivos));
			}
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			MultipartRequest multipartRequest = new MultipartRequest(httpServletRequest, servletFileUpload);
			filterChain.doFilter(multipartRequest, response);
		} else{
			filterChain.doFilter(request, response);
		}
	}

	/*
	 * (El comentario se encuentra en la interfase donde esta declarado el
	 * método)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

		// TODO Auto-generated method stub
	}

	/**
	 * Método para detectar si se tiene una versión mayor o menor a la 2.2 de
	 * Java Server Faces
	 * 
	 * @return boolean : bandera que indica si es menor o mayor
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 27/05/2016
	 */
	private boolean detectJSF22() {

		String version = FacesContext.class.getPackage().getImplementationVersion();
		if(version != null){
			return version.startsWith("2.2");
		} else{
			try{
				Class.forName("javax.faces.flow.Flow");
				return true;
			} catch(ClassNotFoundException ex){
				return false;
			}
		}
	}
}
