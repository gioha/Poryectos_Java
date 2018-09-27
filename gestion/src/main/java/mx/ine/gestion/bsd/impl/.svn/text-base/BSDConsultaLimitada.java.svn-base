/**
 * @(#)BSDConsultaLimitada.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASConsultaLimitadaInterface;
import mx.ine.gestion.bsd.inter.BSDConsultaLimitadaInterface;
import mx.ine.gestion.dto.helper.DTOResultadoConsultaHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS
 *
 * @author INE
 * @copy MAVO
 */
@Component("bsdConsultaLimitada")
@Scope("prototype")
public class BSDConsultaLimitada implements BSDConsultaLimitadaInterface {

	@Autowired
	@Qualifier("asConsultaLimitada")
	private ASConsultaLimitadaInterface asConsultaLimitadaInterface;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.bsd.BSDConsultaLimitadaInterface#ejecutaAccion(java.lang.String)
	 */
	@Override
	public List<DTOResultadoConsultaHelper> ejecutaAccion(String operacion) {
		
		if (operacion.contains("update") || operacion.contains("delete") || operacion.contains("insert")
		 || operacion.contains("UPDATE") || operacion.contains("DELETE") || operacion.contains("INSERT")) {
			
			return asConsultaLimitadaInterface.ejecutaOperacion(operacion);
			
		} else {
			
			return asConsultaLimitadaInterface.ejecutaConsulta(operacion);
		}
	}
}
