package mx.ine.acuerdos.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import nl.captcha.Captcha;

public class FiltroSeguridadComplementaria extends
		UsernamePasswordAuthenticationFilter {

	private static final Logger logger = Logger
			.getLogger(FiltroSeguridadComplementaria.class);
	private String succesUrl = "";
	private String errorUrl = "";
	private String captchaParamName = "";
	private SimpleUrlAuthenticationSuccessHandler simpleUrlSucces = new SimpleUrlAuthenticationSuccessHandler();
	private SimpleUrlAuthenticationFailureHandler simpleUrlFailure = new SimpleUrlAuthenticationFailureHandler();

	@Autowired
	MessageSource messageSource;

	public FiltroSeguridadComplementaria() {
		super();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String password = super.obtainPassword(request);
		if (password == null || password.trim().isEmpty()) {
			this.errorUrl = "/app/login?error=103";
			throw new BadCredentialsException(
					"El campo Contraseña es obligatorio, favor de verificarlo.");
		}
		return password;
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String userName = super.obtainUsername(request);
		if (userName == null || userName.trim().isEmpty()) {
			
			this.errorUrl = "/app/login?error=101";
			throw new BadCredentialsException("El campo Usuario es obligatorio, favor de verificarlo.");
		}
		return userName;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		Authentication auth;
		logger.info("El usuario: " + this.obtainUsername(request)
				+ " trata de entrar al sistema.");
		if (captchaParamName == null || captchaParamName.isEmpty()) {
			this.errorUrl = "/app/login?error=102";
			throw new BadCredentialsException(
					"El código de seguridad es obligatorio, favor de  verificarlo.");
		}

		Captcha captcha = (Captcha) (request.getSession()
				.getAttribute(Captcha.NAME));
		String codigoSeguridad = request.getParameter(this.captchaParamName);

		if (captcha == null) {
			logger.info("El objeto captcha es nulo ... ");
		}
		// Obteniendo el valor del resources
		// CAMBIARLO A FALSE EN EL APPLICATIONRESOURCES SI QUIEREN DESACTIVAR EL
		// CAPTCHA
		String llave = messageSource.getMessage("application.captcha", null,
				null);

		// Validando captcha vacio
		if (!"false".equals(llave)) {
			if (codigoSeguridad.isEmpty()) {
				this.errorUrl = "/app/login?error=104";
				throw new BadCredentialsException(
						"El código de seguridad es obligatorio, favor de  verificarlo.");
			}
		}

		if ((captcha != null && captcha.isCorrect(codigoSeguridad))
				|| ("false".equals(llave))) {
			
			DTOUsuarioLogin userToValidate = null;
			
			try {
				auth = super.attemptAuthentication(request, response);
				userToValidate = (DTOUsuarioLogin)auth.getPrincipal();
				
				if (!auth.isAuthenticated() ||  (userToValidate != null && userToValidate.getRolUsuario() == null )   ) {
					this.errorUrl = "/app/login?error=105";
					
					throw new BadCredentialsException(
							"Usuario y/o contraseña inválidos, por favor, intenta nuevamente.");
					
				}
			} catch (BadCredentialsException e) {
				if (this.errorUrl.equals("/app/login?error=103")) {
					throw new BadCredentialsException(
							"El campo Contraseña es obligatorio, favor de verificarlo.");
				}else{
					
					if(userToValidate != null && userToValidate.getRolUsuario() == null){
						throw new BadCredentialsException(
								"No cuenta con los permisos para acceder al sistema.");
					}
					else{
						throw new BadCredentialsException(
								"El usuario y/o la contrase\u00f1a son incorrectos, favor de introducirlos nuevamente.");
					}	
				}
				
			}

		} else {
			if (!this.errorUrl.equals("/app/login?error=104")&&!this.errorUrl.equals("/app/login?error=102")) {
				this.errorUrl = "/app/login?error=106";
				throw new BadCredentialsException(
						"El código de seguridad es incorrecto, por favor, intenta nuevamente.");
			}else{
				throw new BadCredentialsException(
			    "El código de seguridad es obligatorio, favor de  verificarlo.");
			}
				
		}

		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		logger.info("La autentificacion fue exitosa ");
		simpleUrlSucces.setDefaultTargetUrl(succesUrl);
		this.setAuthenticationSuccessHandler(simpleUrlSucces);
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
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
