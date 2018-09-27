/**
 * @(#)BOApartadoFolios.java 07/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.ine.gestion.bo.inter.BOApartadoFoliosInterface;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos que tienen la lógica, procesos y reglas de negocio que se utiliza en el módulo de
 * apartado de folios para las capas de BSD, AS Y DAO
 * 
 * @author Roberto Shirásago Domínguez
 * @since 07/12/2017
 */
@Component("boApartadoFolios")
@Scope("prototype")
public class BOApartadoFolios implements BOApartadoFoliosInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOApartadoFoliosInterface#procesarInfoParaCrearNumeroDocumento(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String procesarInfoParaCrearNumeroDocumento(String acronimoAgrupacion, Integer numeroConsecutivoFolio) {
		
		String[] componentesNumeroFolio = acronimoAgrupacion.split("\\|");
		String numeroDocumento = "";
		
		for (String componente : componentesNumeroFolio) {
			
			if (componente.contains("acr")) {
				
				numeroDocumento = numeroDocumento + componente.replaceAll("acr=", "");

			} else if (componente.contains("sep") && !componente.contains("sep=*")) {
				
				numeroDocumento = numeroDocumento + componente.replaceAll("sep=", "");
				
			} else if (componente.contains("num")) {
				
				if (componente.contains("num=*")) {
					
					numeroDocumento = numeroDocumento + numeroConsecutivoFolio;

				} else {
					
					String numeroConsecutivoString = String.valueOf(numeroConsecutivoFolio);
					String numeroConsecutivoConCeros = numeroConsecutivoString;
					
					if (numeroConsecutivoString.length() < 5) {
						
						for (int indice = numeroConsecutivoString.length();  indice < 5; indice++) {
							
							numeroConsecutivoConCeros = "0" + numeroConsecutivoConCeros;
						}
					}
					
					numeroDocumento = numeroDocumento + numeroConsecutivoConCeros;
				}
				
			} else if (componente.contains("ani")) {
				
				componente = componente.replaceAll("ani=", "");
				
				if (componente.length() == 2) {
					
					DateFormat df = new SimpleDateFormat("yy");
					numeroDocumento = numeroDocumento + df.format(Calendar.getInstance().getTime());

				} else {
					
					numeroDocumento = numeroDocumento + Calendar.getInstance().get(Calendar.YEAR);
				}
			}
		}
		
		return numeroDocumento;
	}

	
}
