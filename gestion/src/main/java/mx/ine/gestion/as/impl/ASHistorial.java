/**
 * @(#)ASHistorial.java 17/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASHistorialInterface;
import mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO del módulo de Historial.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 17/11/2017
 */
@Component("asHistorial")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASHistorial implements ASHistorialInterface{
	
	@Autowired
	@Qualifier("daoHistDocCreacion")
	private DAOHistDocCreacionInterface daoHistDocCreacionInterface;
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarAreas(int, int)
	 */
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception {
		return daoHistDocCreacionInterface.consultarHistorialPorIdDocumento(idDocumento);
	}
}
