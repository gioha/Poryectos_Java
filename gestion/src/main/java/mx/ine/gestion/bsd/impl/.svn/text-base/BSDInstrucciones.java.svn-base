/**
 * @(#)BSDInstrucciones.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASInstruccionesInterface;
import mx.ine.gestion.bo.inter.BOInstruccionesInterface;
import mx.ine.gestion.bsd.inter.BSDInstruccionesInterface;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;

/**
 * Clase encargada de administrar el o los AS del modulo de instrucciones..
 *
 * @author David Rodríguez Corral
 * @since 17/08/2017
 */
@Component("bsdInstrucciones")
@Scope("prototype")
public class BSDInstrucciones implements BSDInstruccionesInterface{

	@Autowired
	@Qualifier("asInstrucciones")
	private ASInstruccionesInterface asInstruccionesInterface;
	
	@Autowired
	@Qualifier("boInstrucciones")
	private BOInstruccionesInterface boInstruccionesInterface;
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDInstruccionesInterface#consultarInstrucccionesAtencion()
	 */
	@Override
	public List<DTOInstruccionesEntity> consultarInstruccciones(Integer idPersona, Integer tipoInstruccion) {
		return asInstruccionesInterface.consultarInstruccciones(idPersona, tipoInstruccion);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDInstruccionesInterface#limitarInstrucciones(java.util.List, int)
	 */
	@Override
	public String limitarInstrucciones(List<DTOInstruccionesEntity> lista, int limite) {
		return boInstruccionesInterface.limitarInstrucciones(lista, limite);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDInstruccionesInterface#actualizarInstrucciones(java.util.List)
	 */
	@Override
	public void actualizarInstrucciones(List<DTOInstruccionesEntity> lista) {
		asInstruccionesInterface.actualizarInstrucciones(lista);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDInstruccionesInterface#eliminarInstruccionesLogicas(java.lang.Integer)
	 */
	@Override
	public void eliminarInstruccionesLogicas(Integer idPersona) {
		asInstruccionesInterface.eliminarInstruccionesLogicas(idPersona);
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDInstruccionesInterface#concatenarListas(java.util.List, java.util.List)
	 */
	@Override
	public List<DTOInstruccionesEntity> concatenarListas(
			List<DTOInstruccionesEntity> listaAtencion,
			List<DTOInstruccionesEntity> listaInformativa) {
		return boInstruccionesInterface.concatenarListas(listaAtencion, listaInformativa);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDInstruccionesInterface#reOrdena(java.util.List)
	 */
	@Override
	public void reOrdenar(
			List<DTOInstruccionesEntity> lista) {
		boInstruccionesInterface.reOrdenar(lista);
	}

}
