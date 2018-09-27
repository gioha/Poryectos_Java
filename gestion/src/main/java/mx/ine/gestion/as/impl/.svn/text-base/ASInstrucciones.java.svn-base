/**
 * @(#)ASInstrucciones.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASInstruccionesInterface;
import mx.ine.gestion.dao.inter.DAOInstruccionesInterface;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.mb.MBInstrucciones;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO del módulo de Instrucciones.
 *
 * @author David Rodríguez Corral
 * @since 20/08/2017
 */
@Component("asInstrucciones")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASInstrucciones implements ASInstruccionesInterface{

	/**
	 * Atributo log. 
	 */
	private static final Logger logger = Logger.getLogger(MBInstrucciones.class);
	
	
	@Autowired
	@Qualifier("daoInstrucciones")
	private DAOInstruccionesInterface daoInstruccionesInterface;
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASInstruccionesInterface#consultarInstrucccionesAtencion()
	 */
	@Override
	public List<DTOInstruccionesEntity> consultarInstruccciones(Integer idPersona, Integer tipoInstruccion) {
		return daoInstruccionesInterface.consultarInstruccciones(idPersona, tipoInstruccion);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASInstruccionesInterface#actualizarInstrucciones(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void actualizarInstrucciones(List<DTOInstruccionesEntity> lista) {	
		for( DTOInstruccionesEntity dtoInstrucciones : lista ) {
			if( dtoInstrucciones != null )
				if(dtoInstrucciones.getIdInstruccion()==null){
					logger.info("inst" +dtoInstrucciones.getIdInstruccion());
					daoInstruccionesInterface.actualizarInstrucciones(dtoInstrucciones);
				}else{
					daoInstruccionesInterface.modificar(dtoInstrucciones);
			}					
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASInstruccionesInterface#eliminarInstruccionesLogicas(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void eliminarInstruccionesLogicas(Integer idPersona) {
		daoInstruccionesInterface.eliminarInstruccionesLogicas(idPersona);
	}

}

