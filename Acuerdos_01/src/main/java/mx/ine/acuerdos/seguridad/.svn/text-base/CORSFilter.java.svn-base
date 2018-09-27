/**
 * 2017 (C) Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.seguridad;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

/**
 * @author José Guadalupe Burgos Colomoxcatl
 * @since 1.0
 * @version 1.0 - 20/06/2017
 *
 *
 * @copyright INE
 */
public class CORSFilter implements Filter {
	
	/** 
	 * Objeto para el servicio de bit�cora de mensajes de la aplicaci�n. 
	 */
	public static final Logger LOGGER = Logger.getLogger(CORSFilter.class);

	/**
	 * Variable del modo de operación del filtro
	 * 
	 * deny - No rendering within a frame.
	 * sameorigin - No rendering if origin mismatch.
	 * allow-from: DOMAIN - Allows rendering if framed by frame loaded from DOMAIN.  
	 */
	private static String methods = "GET, PUT, PATCH, DELETE, TRACE, OPTIONS";
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.trace("Inicializando el filtro CORSFilter");
		String configMethods = filterConfig.getInitParameter("methods");
//		if (configMethods != null && !configMethods.trim().isEmpty()) {
//			methods = configMethods;
//		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		LOGGER.trace("Destruyendo el filtro CORSFilter");
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		LOGGER.trace("Procesando el filtro CORSFilter");
		
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods", methods);
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Max-Age", "3600");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		((HttpServletResponse) servletResponse).setHeader("Content-Type", "application/json;charset=UTF-8");
		((HttpServletResponse) servletResponse).setHeader("Accept","application/json;charset=UTF-8");
//		((HttpServletResponse) servletResponse).setHeader("X-XSS-Protection", "1");
//		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
        filterChain.doFilter(servletRequest, servletResponse);
		
	}
 
}