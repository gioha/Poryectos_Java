package mx.ine.acuerdos.seguridad;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jboss.logging.Logger;
 
/**
 * Filters Http requests and removes malicious characters/strings
 * (i.e. XSS) from the Query String
 */
public class XSSPreventionFilter implements Filter {class XSSRequestWrapper extends HttpServletRequestWrapper {
		
		/**
		 * Variable que tiene acceso al registro del LOG.
		 */
		Logger logger = Logger.getLogger(XSSPreventionFilter.class);
		
		private Map<String, String[]> sanitizedQueryString;
		
		public XSSRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		
				//QueryString overrides

//		@Override
//		public String getParameter(String name) {
//			String parameter = null;
//			String[] vals = getParameterMap().get(name); 
//			
//			if (vals != null && vals.length > 0) {
//				parameter = vals[0];
//			}
//			
//			return parameter;
//		}
		
		@Override
	    public String getParameter(String parameter) {
	        String value = super.getParameter(parameter);
	        return stripXSS(value);
	    }
		
		
//		@Override
//		public Part getPart(String parameter) throws IOException, ServletException{
//			Part parte = super.getPart(parameter);
//			logger.debug("Parametro: "+parameter+" PARTE: "+parte.toString());
//			return parte;
//		}
		
 
//		@Override
//		public String[] getParameterValues(String name) {
//			return getParameterMap().get(name);
//		}
		public String[] getParameterValues(String parameter) {
			logger.debug("entra parameterValues");
		    String[] values = super.getParameterValues(parameter);
		    
		    if (values == null) {
		        return null;
		    }
		    int count = values.length;
		    String[] encodedValues = new String[count];
		    for (int i = 0; i < count; i++) {
		        encodedValues[i] = stripXSS(values[i]);
		    }
		    return encodedValues;
		}
		
		@Override
		public Enumeration<String> getParameterNames() {
			return Collections.enumeration(getParameterMap().keySet());
		}
		
		@Override
		public Map<String,String[]> getParameterMap() {
			if(sanitizedQueryString == null) {
				Map<String, String[]> res = new HashMap<String, String[]>();
				Map<String, String[]> originalQueryString = super.getParameterMap();
				if(originalQueryString!=null) {
					for (String key : originalQueryString.keySet()) {
						String[] rawVals = originalQueryString.get(key);
						String[] snzVals = new String[rawVals.length];
						for (int i=0; i < rawVals.length; i++) {
							snzVals[i] = stripXSS(rawVals[i]);
							logger.debug("Sanitized: " + rawVals[i] + " to " + snzVals[i]+" key: "+key);
						}
						res.put(stripXSS(key), snzVals);
					}
				}
				sanitizedQueryString = res;
			}
			return sanitizedQueryString;
		}
		 
	    @Override
	    public String getHeader(String name) {
	        String value = super.getHeader(name);
	        return stripXSS(value);
	    }
		    
		/**
		 * Removes all the potentially malicious characters from a string
		 * @param value the raw string
		 * @return the sanitized string
		 */
		private String stripXSS(String value) {
			String cleanValue = null;
			if (value != null) {
//				cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);
				cleanValue = value;
 
				// Avoid null characters
				cleanValue = cleanValue.replaceAll("\0", "");
				
				// Avoid anything between script tags
				Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Avoid anything in a src='...' type of expression
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
 
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Remove any lonesome </script> tag
				scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
 
				// Remove any lonesome <script ...> tag
				scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
 
				// Avoid eval(...) expressions
				scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Avoid expression(...) expressions
				scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Avoid javascript:... expressions
				scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Avoid vbscript:... expressions
				scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Avoid onload= expressions
				scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				// Avoid scriptalert expressions
				scriptPattern = Pattern.compile("<scriptalert(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				//Avoid script expressions
				scriptPattern = Pattern.compile("script", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				//Avoid script expressions
				scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
				scriptPattern = Pattern.compile("[.*]/", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
				
			}
			return cleanValue;
		}
	}
 
	@Override
	public void destroy() {
		System.out.println("XSSPreventionFilter: destroy()");
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		XSSRequestWrapper wrapper = new XSSRequestWrapper((HttpServletRequest)request);
		chain.doFilter(wrapper, response);
	}
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("XSSPreventionFilter: init()");
	}
}