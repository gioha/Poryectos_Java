/**
 * @(#)BSDCAreasInterface.java 30/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASCAreasInterface;
import mx.ine.gestion.bsd.inter.BSDCAreasInterface;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los BSD del módulo CAreas.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 30/08/2017
 */

@Component("bsdCAreas")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })

public class BSDCAreas implements BSDCAreasInterface {

	@Autowired
	@Qualifier("asCAreas")
	private ASCAreasInterface asCAreasInterface;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDCAreasInterface#buscarTodos()
	 */
	@Override
	public List<DTOCAreaEntity> buscarTodos() throws Exception{
		return asCAreasInterface.buscarTodos();
	}
	
}
