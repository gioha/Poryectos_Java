	package mx.ine.gestion.dao.impl;
/**
 * @(#)ASAdmonUsuarios.java 04/09/2013
 *
 * Copyright (C) 2013 Instituto Federal Electoral (IFE).
 * 
 * Todos los derechos reservados.
 */

import java.io.Serializable;

import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import mx.ine.gestion.bo.inter.BOEstructuraInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;

/**
 * Clase que sirve para hacer búsqueda de usuarios en ldap
 * 
 * @author Roberto Shirasago Dominguez
 * @since 04/09/2013
 * @copyright IFE
 */
public class DAOLdapEstructura implements Serializable {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("boEstructura")
	private BOEstructuraInterface boEstructuraInterface;	

	/**
	 * Objeto para la serialización de esta clase. 
	 */
	private static final long serialVersionUID = 6390043937682734977L;

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
	

	/* ------------------------------- PROCESAMIENTO LOCAL ------------------------------------- */

	/**
	 * Sobre carga del constructor para recibir los atributos antes mencionados
	 * 
	 * @param searchBase : Nombre de contexto para buscar con relación a la base de la ContextSource configurado
	 * @param searchFilter : Expresión para el filtro, utilizada en la búsqueda de los usuarios
	 * @param contextSource : Contexto de búsqueda de LDAP
	 */
	public DAOLdapEstructura(String searchBase, String searchFilter, BaseLdapPathContextSource contextSource) {
		
		 this.searchFilter = searchFilter;
	     this.contextSource = contextSource;
	     this.searchBase = searchBase;
	}
	
	 /**
	  * Método que hace la búsqueda de los usuarios de ldap por nombre, apellido y/o por usuario
	  * 
	  * @param nombre : parametro de búsqueda que contiene el nombre de la persona, en caso
	  * 			 	de mandarlo vacio buscara "todos" o por los otros parametros
	  * @param apellidos :  parámetro de búsqueda que contiene el/los apellidos de la persona,
	  * 					en caso de mandarlo vacio buscara "todos" o por los otros parametros
	  * @param usuario : parámetro de búsqueda que contiene el usuario de la persona, en caso
	  * 				 de mandarlo vacio buscara "todos" o por los otros parametros
	  * @return NamingEnumeration<SearchResult>: lista que contiene el/los registros encontrados
	  * 
	  * @throws Exception 
	  * 
	  * @author Roberto Shirasago Dominguez
	  * @author Pável Alexei Martínez Regalado
	  * 
	  * @since 04/09/2013
	  */
    public NamingEnumeration<SearchResult> searchForUsers(String nombre, String apellidos, String area, Integer idEstado) throws Exception {
    	boEstructuraInterface.sustituirAcentos(area);
    	boEstructuraInterface.quitarOtrosCaracteres(nombre);
    	boEstructuraInterface.sustituirAcentos(nombre);
    	
    	if (nombre == null || nombre.trim().equals("")) {
    		nombre = "";
    	} else {
        	nombre = boEstructuraInterface.quitarOtrosCaracteres(nombre);
        	nombre = boEstructuraInterface.sustituirAcentos(nombre);
    		nombre = "(givenName=*" + nombre.trim() + "*)";
    	}
    	if (apellidos == null || apellidos.trim().equals("")) {
    		apellidos = "";
    	} else {
        	apellidos = boEstructuraInterface.quitarOtrosCaracteres(apellidos);
        	apellidos = boEstructuraInterface.sustituirAcentos(apellidos);
    		apellidos = "(sn=*" + apellidos.trim() + "*)";
    	}
    	if (area == null || area.trim().equals("")) {
    		area = "";
    	} else {
    		area = boEstructuraInterface.sustituirAcentos(area);
    		area = "(ou=*" + area.trim() + "*)";
    	}
    	String estado = "(idEstado=" + idEstado + ")";
        
        DirContext contexto = contextSource.getContext("", "");
        
        String searchFilterAux = searchFilter;
        
        searchFilter = searchFilter.replace("{0}", area);
        searchFilter = searchFilter.replace("{1}", nombre);
        searchFilter = searchFilter.replace("{2}", apellidos);
        searchFilter = searchFilter.replace("{3}", estado);
        
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
