/**
 * @(#)BOInstrucciones.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.bo.inter.BOInstruccionesInterface;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Clase que tendrá la lóogica y procedimientos del módulo de instrucciones.
 * 
 * @author David Rodríguez Corral
 * @since 28/08/2017
 */
@Component("boInstrucciones")
@Scope("prototype")
public class BOInstrucciones implements BOInstruccionesInterface{

	private static final Logger logger = Logger.getLogger(BOInstrucciones.class);
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOInstruccionesInterface#limitarInstrucciones(java.util.List, int)
	 */
	@Override
	public String limitarInstrucciones(List<DTOInstruccionesEntity> lista, int limite) {
		String tipo = messageSource.getMessage("mensaje_instrucciones_limiteAtencion", null, null);
		if(limite==3){
			tipo = messageSource.getMessage("mensaje_instrucciones_limiteInformativa", null, null);;
		}
		String mensajeError = "";
		logger.info("tamaño " + lista.size());
		if((limite-1)<lista.size()){
			return mensajeError= tipo;
		}
		return mensajeError;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOInstruccionesInterface#reOrdena(java.util.List)
	 */
	@Override
	public void reOrdenar(List<DTOInstruccionesEntity> lista) {
		for (int i=0;i<lista.size();i++){
			lista.get(i).setOrdenamiento(i+1);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOInstruccionesInterface#concatenarListas(java.util.List, java.util.List)
	 */
	@Override
	public List<DTOInstruccionesEntity> concatenarListas(
			List<DTOInstruccionesEntity> listaAtencion,
			List<DTOInstruccionesEntity> listaInformativa) {
		List<DTOInstruccionesEntity> listaInstrucciones = new ArrayList<DTOInstruccionesEntity>();
		listaInstrucciones.addAll(listaAtencion);
		listaInstrucciones.addAll(listaInformativa);
		return listaInstrucciones;
	}

}
