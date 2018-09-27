/**
 *  Software published by the Open Web Application Security Project (http://www.owasp.org)
 *  This software is licensed under the new BSD license.
 *
 * @author     Jeff Williams <a href="http://www.aspectsecurity.com">Aspect Security</a>
 * @created    February 6, 2009
 */

package mx.ine.gestion.seguridad;

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
 * 
 * @author UNICOM
 * @since 1.0
 * @version 1.0 - 09/05/2017
 *
 * @copyright INE
 */
public class ClickjackFilter implements Filter {

	/** 
	 * Objeto para el servicio de bitácora de mensajes de la aplicación. 
	 */
	public static final Logger LOGGER = Logger.getLogger(ClickjackFilter.class);
	
	/**
	 * Variable del modo de operación del filtro
	 * 
	 * deny - No rendering within a frame.
	 * sameorigin - No rendering if origin mismatch.
	 * allow-from: DOMAIN - Allows rendering if framed by frame loaded from DOMAIN.  
	 */
	private static String mode = "DENY";

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) {
		LOGGER.trace("Inicializando el filtro ClickjackFilter");
		String configMode = filterConfig.getInitParameter("mode");
		if (configMode != null && !configMode.trim().isEmpty()) {
			mode = configMode;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		LOGGER.trace("Destruyendo el filtro ClickjackFilter");
	}
	
	/**
	 * Add X-FRAME-OPTIONS response header to tell IE8 (and any other browsers who
	 * decide to implement) not to display this content in a frame. For details, please
	 * refer to http://blogs.msdn.com/sdl/archive/2009/02/05/clickjacking-defense-in-ie8.aspx.
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {		
		LOGGER.trace("Procesando el filtro ClickjackFilter");
		
		((HttpServletResponse) servletResponse).setHeader("X-Frame-Options", mode);		
        filterChain.doFilter(servletRequest, servletResponse);

	}
	
}