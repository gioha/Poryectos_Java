package mx.ine.acuerdos.bo.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.bo.BOLdapInterface;

@Scope("prototype")
@Component("boLdap")
public class BOLdap implements BOLdapInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.acuerdos.bo.BOLdapInterface#quitarOtrosCaracteres(java.lang.String)
	 */
	@Override
	public String quitarOtrosCaracteres(String cadena) {
		if(cadena == null) return null;
		cadena = cadena.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑÜü ]+", "");
		return cadena;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.BOLdapInterface#sustituirAcentos(java.lang.String)
	 */
	@Override
	public String sustituirAcentos(String cadena) {
		if(cadena == null) return null;
		cadena = cadena.replaceAll("[áéíóúÁÉÍÓÚ]+", "*");
		return cadena;
	}

}
