package mx.ine.acuerdos.bo;

public interface BOLdapInterface {
	
	/**
	 * Método para quitar caracteres que no queremos en la consulta de LDAP
	 * 
	 * @return String sin los caracteres que no están permitidos 
	 * @param String cadena: cadena
	 */
	public String quitarOtrosCaracteres(String cadena);
	
	/**
	 * Método para sustituir acentos por * para evitar problemas con los nombres con acento.
	 * 
	 * @return String sin caracteres sin acento sustituidos por *'s 
	 * @param String cadena: cadena
	 */
	public String sustituirAcentos(String cadena);

}
