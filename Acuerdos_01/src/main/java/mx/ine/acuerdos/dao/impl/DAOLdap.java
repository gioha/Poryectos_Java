package mx.ine.acuerdos.dao.impl;

import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import mx.ine.acuerdos.bo.BOLdapInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;

//@Scope("prototype")
//@Repository("daoLdap")
public class DAOLdap {
	
	/**
	 * Contexto de búsqueda de LDAP
	 */
	private final ContextSource contextSource;
	
	/**
	 * Nombre de contexto para buscar con relación a la base de la ContextSource configurado
	 */
	private String searchBase = "";

	/**
	 * Expresión para el filtro, utilizada en la búsqueda de los usuarios. Se trata de un filtro de búsqueda LDAP (como se define en "RFC 2254")
     * con argumentos opcionales, en este caso "(|(&amp;(givenName=*{0}*)(sn=*{1}*))(uid=*{2}*))" definida en el SpringSecurity, la cual
	 * se refiere a la busqueda por nombre y apellido o por usuario
	 */
	private String searchFilter;
	
	@Autowired
	@Qualifier("boLdap")
	private BOLdapInterface boLdap;
	
	public DAOLdap(String searchBase, String searchFilter, BaseLdapPathContextSource contextSource) {
		 this.searchFilter = searchFilter;
	     this.contextSource = contextSource;
	     this.searchBase = searchBase;
	}

	
	public NamingEnumeration<SearchResult> buscaUsuariosLdap(String nombre, String apellidos, String usuario,
			String area) throws Exception {
		
		if (nombre == null || nombre.trim().equals("")) {
    		nombre = "";
    	} else {
        	nombre = boLdap.quitarOtrosCaracteres(nombre);
        	nombre = boLdap.sustituirAcentos(nombre);
    		nombre = "(givenName=*" + nombre.trim() + "*)";
    	}
    	if (apellidos == null || apellidos.trim().equals("")) {
    		apellidos = "";
    	} else {
        	apellidos = boLdap.quitarOtrosCaracteres(apellidos);
        	apellidos = boLdap.sustituirAcentos(apellidos);
    		apellidos = "(sn=*" + apellidos.trim() + "*)";
    	}
    	if (area == null || area.trim().equals("")) {
    		area = "";
    	} else {
    		area = boLdap.sustituirAcentos(area);
    		area = "(ou=*" + area.trim() + "*)";
    	}
    	if (usuario == null || usuario.trim().equals("")) {
    		usuario = "";
    	} else {
    		usuario = "(uid=*" + usuario.trim() + "*)";
    	}
//    	String estado = "(idEstado=" + idEstado + ")";
        
        DirContext contexto = contextSource.getContext("", "");
        
        String searchFilterAux = searchFilter;
        
        searchFilter = searchFilter.replace("{0}", area);
        searchFilter = searchFilter.replace("{1}", nombre);
        searchFilter = searchFilter.replace("{2}", apellidos);
        searchFilter = searchFilter.replace("{3}", usuario);
        
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        
        try {
        	
        	return contexto.search(searchBase, searchFilter, searchControls);
			
		} catch (Exception e) {
			
			throw new Exception("Error al hacer la búsqueda del usuario por LDAP");
			
		} finally {
			
			searchFilter = searchFilterAux;
		}
	}

}
