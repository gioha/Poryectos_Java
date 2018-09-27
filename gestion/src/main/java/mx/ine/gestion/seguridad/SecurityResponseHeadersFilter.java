/**
 * 
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
 * Filtro de seguridad genérico del sistema, encargado de agregar los encabezados 
 * requeridos para proteger las peticiones HTTP.
 * 
 * @author UNICOM
 * @since 1.0
 * @version 1.0 - 09/05/2017
 *
 * @copyright INE
 */
public class SecurityResponseHeadersFilter implements Filter {

	/** 
	 * Objeto para el servicio de bitácora de mensajes de la aplicación. 
	 */
	public static final Logger LOGGER = Logger.getLogger(SecurityResponseHeadersFilter.class);	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.trace("Inicializando el filtro de los encabezados de seguridad para la respuesta");
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOGGER.trace("Destruyendo el filtro de los encabezados de seguridad para la respuesta");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		LOGGER.trace("Agregando los encabezados de seguridad a la respuesta");
		
		((HttpServletResponse) servletResponse).setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
		((HttpServletResponse) servletResponse).setHeader("Pragma", "no-cache");
		((HttpServletResponse) servletResponse).setHeader("Expires", "0");
//		((HttpServletResponse) servletResponse).setHeader("X-XSS-Protection", "deny");
//		((HttpServletResponse) servletResponse).setHeader("X-Frame-Options", "1");
		((HttpServletResponse) servletResponse).setHeader("X-Content-Type-Options", "nosniff");
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

}
