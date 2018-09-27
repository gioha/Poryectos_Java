/**
 * 2017 (C) Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.util.StringUtils;

/**
 * @author José Guadalupe Burgos Colomoxcatl
 * @since 1.0
 * @version 1.0 - 23/05/2017
 *
 *
 * @copyright INE
 */
public class FiltroSeguridadAjax implements InvalidSessionStrategy {
	
	private static final Logger LOGGER = Logger.getLogger(FiltroSeguridadAjax.class);
	private static final String FACES_REQUEST_HEADER = "faces-request";
	private String invalidSessionUrl;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#onInvalidSessionDetected(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onInvalidSessionDetected(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws IOException, ServletException {
		boolean ajaxRedirect = "partial/ajax".equals(paramHttpServletRequest.getHeader("faces-request"));
		String requestURI;
		if (ajaxRedirect) {
			requestURI = paramHttpServletRequest.getContextPath();
			String redirectUrl = requestURI + this.invalidSessionUrl;
			this.LOGGER.debug("La petición ajax no se pudo ejecutar debido a que la sesión a finalizado , volver a dirigir a \'{}\'");
			String ajaxRedirectXml = this.createAjaxRedirectXml(redirectUrl);
			this.LOGGER.debug("Respuesta parcial de ajax redirigir a: {}");
			paramHttpServletResponse.setContentType("text/xml");
			paramHttpServletResponse.getWriter().write(ajaxRedirectXml);
		} else {
			requestURI = this.getRequestUrl(paramHttpServletRequest);
			this.LOGGER.debug("La sesión ha finalizado debido a la petición no ajax, iniciar una nueva sesión y redirigir a url solicitada \'{}\'");
			paramHttpServletRequest.getSession(true);
			paramHttpServletResponse.sendRedirect(requestURI);
		}
	}

	private String getRequestUrl(HttpServletRequest request) {
		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();
		if (StringUtils.hasText(queryString)) {
			requestURL.append("?").append(queryString);
		}

		return requestURL.toString();
	}

	private String createAjaxRedirectXml(String redirectUrl) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<partial-response><redirect url=\"" + redirectUrl
				+ "\"></redirect></partial-response>";
	}

	public void setInvalidSessionUrl(String invalidSessionUrl) {
		this.invalidSessionUrl = invalidSessionUrl;
	}
	
}