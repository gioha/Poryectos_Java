/**
 * @(#)BSDHistorial.java 17/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASHistorialInterface;
import mx.ine.gestion.bsd.inter.BSDHistorialInterface;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los DAO del módulo de Historial.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 17/11/2017
 */
@Component("bsdHistorial")
@Scope("prototype")
public class BSDHistorial implements BSDHistorialInterface{
	
	@Autowired
	@Qualifier("asHistorial")
	private ASHistorialInterface asHistorialInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHistorialInterface#consultarHistorialPorIdDocumento(int)
	 */
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception {
		return asHistorialInterface.consultarHistorialPorIdDocumento(idDocumento);
	}
}
