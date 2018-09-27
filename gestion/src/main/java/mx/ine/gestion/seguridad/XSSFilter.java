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
import javax.servlet.http.HttpServletRequest;
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
public class XSSFilter implements Filter{
	
	/** 
	 * Objeto para el servicio de bitácora de mensajes de la aplicación. 
	 */
	public static final Logger LOGGER = Logger.getLogger(XSSFilter.class);
	
	/**
	 * Variable del modo de operación del filtro
	 * 
	 * 0 - Filter disabled.
	 * 1 - Filter enabled. If a cross-site scripting attack is detected, in order to stop the attack, the browser will sanitize the page.
	 * 1; mode=block - Filter enabled. Rather than sanitize the page, when a XSS attack is detected, the browser will prevent rendering of the page.
	 * 1; report=http://[YOURDOMAIN]/your_report_URI - Filter enabled. The browser will sanitize the page and report the violation. This is a Chromium function utilizing CSP violation reports to send details to a URI of your choice. 
	 */
	private static String mode = "1";
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.trace("Inicializando el filtro XSSFilter");
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
		LOGGER.trace("Destruyendo el filtro XSSFilter");
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		LOGGER.trace("Procesando el filtro XSSFilter");		

		((HttpServletResponse) servletResponse).setHeader("X-XSS-Protection", mode);
		
		filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) servletRequest), servletResponse);		
	}

}
