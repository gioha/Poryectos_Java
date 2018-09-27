/**
 * @(#)BSDHistorialTurnado.java 04/05/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.ine.gestion.as.inter.ASHistorialTurnadoInterface;
import mx.ine.gestion.bsd.inter.BSDHistorialTurnadoInterface;

/**
 * @author Homero Fidel Villanueva
 * @since 04/05/2018
 *
 */
public class BSDHistorialTurnado implements BSDHistorialTurnadoInterface{

	@Autowired
	@Qualifier("asHistorial")
	private ASHistorialTurnadoInterface asHistorialTurnadoInterface;
}
