/**
 * @(#)BSDHistorialTurnado.java 04/05/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import mx.ine.gestion.as.inter.ASHistorialTurnadoInterface;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Homero Fidel Villanueva
 * @since 04/05/2018
 *
 */
@Component("asHistorialTurnado")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASHistorialTurnado implements ASHistorialTurnadoInterface {

}
