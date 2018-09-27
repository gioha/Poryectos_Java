package mx.ine.gestion.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ine.gestion.util.ApplicationContextUtils;
import nl.captcha.Captcha;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class FiltroSeguridadComplementaria extends UsernamePasswordAuthenticationFilter {

	private static final Logger						logger				= Logger.getLogger(FiltroSeguridadComplementaria.class);
	private String									succesUrl			= "";
	private String									errorUrl			= "";
	private String									captchaParamName	= "";
	private SimpleUrlAuthenticationSuccessHandler	simpleUrlSucces		= new SimpleUrlAuthenticationSuccessHandler();
	private SimpleUrlAuthenticationFailureHandler	simpleUrlFailure	= new SimpleUrlAuthenticationFailureHandler();
	
	@Autowired
	@Qualifier("ldapUserSearchUID")
	private transient FilterBasedLdapUserSearch		ldapUserSearch;
	
	@Autowired
	@Qualifier("ldapAuthoritiesPopulator")
	private transient LdapAuthoritiesPopulator		ldapAuthoritiesPopulator;
	
	@Autowired
	MessageSource									messageSource;

	public FiltroSeguridadComplementaria() {

		super();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainPassword(HttpServletRequest request) {

		String password = super.obtainPassword(request);
		if(password == null || password.isEmpty()){
			throw new BadCredentialsException("El campo contrase\u00f1a es obligatorio, favor de verificarlo");
		}
		return password;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainUsername(HttpServletRequest request) {

		String userName = super.obtainUsername(request);
		if(userName == null || userName.isEmpty()){
			throw new BadCredentialsException("El campo nombre de usuario es obligatorio, favor de verificarlo");
		}
		return userName;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		Authentication auth;

    	//Valida usuario
    	this.obtainUsername(request);
    	
    	//Valida contraseña
    	this.obtainPassword(request);
        
    	
    	if (captchaParamName == null || captchaParamName.isEmpty()) {

    		logger.error("<=============== ERROR en el FiltroSeguridadComplementaria, el captchaParamName esta vacío o nulo");
    		
            throw new BadCredentialsException("Error al tratar de ingresar al sistema, favor de reportarlo al CAU.");
        }

        Captcha captcha = (Captcha) (request.getSession().getAttribute(Captcha.NAME));
        String codigoSeguridad = request.getParameter(this.captchaParamName);
        ResourceBundleMessageSource messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils.getApplicationContext().getBean("messageSource"));
	    String llave =  messageSource.getMessage("application.captcha", null, null);
	    
	    if (( captcha != null && captcha.isCorrect(codigoSeguridad) ) ||  ( "false".equalsIgnoreCase(llave)) ) {

        	auth = super.attemptAuthentication(request, response);

        } else {
        	
        	if (codigoSeguridad == null || codigoSeguridad.isEmpty()) {

            	throw new BadCredentialsException("El c\u00f3digo de seguridad es incorrecto, favor de introducirlo nuevamente. ");
            }

        	throw new BadCredentialsException("El c\u00f3digo de seguridad es incorrecto, favor de introducirlo nuevamente");
        }
        
        return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {

		logger.info("La autentificacion fue exitosa ");
		simpleUrlSucces.setDefaultTargetUrl(succesUrl);
		this.setAuthenticationSuccessHandler(simpleUrlSucces);
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {

		logger.info("La autentificacion fue erronea ");
		simpleUrlFailure.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(simpleUrlFailure);
		super.unsuccessfulAuthentication(request, response, failed);
	}

	public String getSuccesUrl() {

		return succesUrl;
	}

	public void setSuccesUrl(String succesUrl) {

		this.succesUrl = succesUrl;
	}

	public String getErrorUrl() {

		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {

		this.errorUrl = errorUrl;
	}

	public String getCaptchaParamName() {

		return captchaParamName;
	}

	public void setCaptchaParamName(String captchaParamName) {

		this.captchaParamName = captchaParamName;
	}
}
