/**
 * @(#)BOTurnado.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.ine.gestion.bo.inter.BOTurnadoInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOTurnInstruccionEntity;
import mx.ine.gestion.dto.helper.DTOCargaTurnadoInstruHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.ine.gestion.util.Utilidades;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que tendrá la lógica y procedimientos del módulo de Turnado.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 */
@Component("boTurnado")
@Scope("prototype")
public class BOTurnado implements BOTurnadoInterface {

	public Integer obtenerFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);	
		return cal.get(Calendar.YEAR);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirHistDocRecep(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOHistDocRecepEntity construirHistDocRecep(Integer idArea, Integer tipoArea, Integer idDocumento,
			Integer idHistoricoPadre, Integer idPersonaHist, Integer idEstatusRecep) {
		
		DTOHistDocRecepEntity nuevo = new DTOHistDocRecepEntity();
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setIdDocumento(idDocumento);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdHistoricoPadre(idHistoricoPadre);
		nuevo.setIdPersonaHist(idPersonaHist);
		nuevo.setIdEstatusRecep(idEstatusRecep);
		
		return nuevo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirHistDocTurno(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DTOHistDocTurnoEntity construirHistDocTurno(Integer idArea, Integer tipoArea, Integer idDocumento, 
			Integer idHistoricoRecep, Integer idPersonaHist, Integer idEstatusRecep, String comentarioGrl) {
		DTOHistDocTurnoEntity nuevo = new DTOHistDocTurnoEntity();
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setIdDocumento(idDocumento);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdHistoricoRecep(idHistoricoRecep);
		nuevo.setIdPersonaHist(idPersonaHist);
		nuevo.setIdEstatusTurno(idEstatusRecep);
		nuevo.setComentarioGrl(comentarioGrl);
		
		return nuevo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirHBandejaERecibidos(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOHBandejaERecibidosEntity construirHBandejaERecibidos(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea) {

		DTOHBandejaERecibidosEntity nuevo = new DTOHBandejaERecibidosEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdPersona(idPersona);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setNoLeido(Integer.valueOf(0));
		nuevo.setFechaRecepcion(new Date());
		nuevo.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));
		return nuevo;
	}
	
	@Override
	public DTOHBandejaEAtencionEntity construirHBandejaEAtencion(Integer idDocumento, Integer idPersona, Integer idArea, 
			Integer tipoArea, Integer idHistoricoPadre, Integer idHistoricoRecep) {

		DTOHBandejaEAtencionEntity nuevo = new DTOHBandejaEAtencionEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdPersona(idPersona);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setNoLeido(Integer.valueOf(0));
		nuevo.setFechaRecepcion(new Date());
		nuevo.setIdHistoricoPadre(idHistoricoPadre);
		nuevo.setIdHistoricoRecep(idHistoricoRecep);
		nuevo.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));
		return nuevo;
	}
	
	@Override
	public DTOHBandejaEInfoEntity construirHBandejaEInfo(Integer idDocumento, Integer idPersona, Integer idArea,
			Integer tipoArea, Integer idHistoricoPadre, Integer idHistoricoRecep) {

		DTOHBandejaEInfoEntity nuevo = new DTOHBandejaEInfoEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdPersona(idPersona);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setNoLeido(Integer.valueOf(0));
		nuevo.setFechaRecepcion(new Date());
		nuevo.setIdHistoricoPadre(idHistoricoPadre);
		nuevo.setIdHistoricoRecep(idHistoricoRecep);
		return nuevo;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirBandejaECCP(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOHBandejaECCPEntity construirHBandejaECCP(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea) {
		
		DTOHBandejaECCPEntity nuevo = new DTOHBandejaECCPEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdPersona(idPersona);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setFechaRecepcion(new Date());
		nuevo.setNoLeido(Integer.valueOf(0));
		return nuevo;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirBandejaEAtencion(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOBandejaEAtencionEntity construirBandejaEAtencion(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea, 
		 Integer noLeido, Integer idHistoricoRecep, Integer idHistoricoPadre) {
		
		DTOBandejaEAtencionEntity nuevo = new DTOBandejaEAtencionEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdPersona(idPersona);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setNoLeido(noLeido);
		nuevo.setFechaRecepcion(new Date());
		nuevo.setIdHistoricoRecep(idHistoricoRecep);
		nuevo.setIdHistoricoPadre(idHistoricoPadre);
		nuevo.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));
		nuevo.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_sin_respuesta")));
		return nuevo;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirBandejaEInfo(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOBandejaEInfoEntity construirBandejaEInfo(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea, 
			 Integer noLeido, Integer idHistoricoRecep, Integer idHistoricoPadre) {
		DTOBandejaEInfoEntity nuevo = new DTOBandejaEInfoEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdPersona(idPersona);
		nuevo.setAnio(obtenerFecha());
		nuevo.setIdArea(idArea);
		nuevo.setTipoArea(tipoArea);
		nuevo.setNoLeido(noLeido);
		nuevo.setFechaRecepcion(new Date());
		nuevo.setIdHistoricoRecep(idHistoricoRecep);
		nuevo.setIdHistoricoPadre(idHistoricoPadre);
		return nuevo;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirInstruccion(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOTurnInstruccionEntity construirInstruccion(Integer idDocumento, Integer idHistoricoRecep, Integer idPersona, Integer idPersonaTurnado, Integer idInstruccion) {
		DTOTurnInstruccionEntity nuevo = new DTOTurnInstruccionEntity();
		nuevo.setIdDocumento(idDocumento);
		nuevo.setIdHistoricoRecep(idHistoricoRecep);
		nuevo.setIdPersona(idPersona);
		nuevo.setIdPersonaTurnado(idPersonaTurnado);
		nuevo.setIdInstruccion(idInstruccion);
		return nuevo;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirAregloInstrucciones(java.lang.Boolean, java.util.List)
	 */
	@Override
	public Boolean[] construirAregloInstrucciones(Boolean[] instrucciones, List<DTOTurnInstruccionEntity> listaIntruccionesRegistradas) {
		for (int i = 1; i <= instrucciones.length; i++) {
			for (DTOTurnInstruccionEntity instruccionReg : listaIntruccionesRegistradas) {
				if (i == instruccionReg.getIdInstruccion()) {
					instrucciones[i - 1] = true;
					break;
				}
			}
		}

		return instrucciones;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#construirInstruccionesRealizadas(java.util.List)
	 */
	@Override
	public List<DTOTurnadoInstruHelper> construirInstruccionesRealizadas(List<DTOCargaTurnadoInstruHelper> listaCarga) {
		List<DTOTurnadoInstruHelper> listaInstruccionesRealizadas = null;
		DTOTurnadoInstruHelper elementoAux;
		DTOEstructuraAreasEntity personaAux;
		int indice = 0;
		
		if(listaCarga != null){
			listaInstruccionesRealizadas = new ArrayList<DTOTurnadoInstruHelper>();
			
			for (DTOCargaTurnadoInstruHelper instruccion : listaCarga) {
				elementoAux = new DTOTurnadoInstruHelper();
				personaAux = new DTOEstructuraAreasEntity();
				personaAux.setIdPersona(instruccion.getIdPersonaTurnada());
				personaAux.setIdArea(instruccion.getIdArea());
				personaAux.setTipoArea(instruccion.getTipoArea());
				indice = contienePersona(listaInstruccionesRealizadas, personaAux); 
				if( indice == -1){
					elementoAux.setIdPersonaTurno(instruccion.getIdPersonaTurno());
					elementoAux.setPersonaTurnada(personaAux);
//					elementoAux.getPersonaTurnada().setIdPersona(instruccion.getIdPersonaTurnada());
//					elementoAux.getPersonaTurnada().setIdArea(instruccion.getIdArea());
//					elementoAux.getPersonaTurnada().setTipoArea(instruccion.getTipoArea());
					elementoAux.setIdHistoricoRecep(instruccion.getIdHistoricoRecep());
					elementoAux.agregarInstruccion(instruccion.getIdInstruccion(), instruccion.getTipoInstruccion(), instruccion.getOrdenamiento());
					listaInstruccionesRealizadas.add(elementoAux);
				}else if(indice >= 0){
					listaInstruccionesRealizadas.get(indice).agregarInstruccion(instruccion.getIdInstruccion(), instruccion.getTipoInstruccion(), instruccion.getOrdenamiento());
				}
			}
		}
		return listaInstruccionesRealizadas;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#contienePersona(java.util.List, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public int contienePersona(List<DTOTurnadoInstruHelper> lista, DTOEstructuraAreasEntity persona){
		int res = -1;
		if(lista != null && lista.size() > 0){
			for (int i = 0; i < lista.size(); i++) {
				if(lista.get(i).getPersonaTurnada().equals(persona)){
					res = i;
					break;
				}
			}
		}
		return res;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#consultarHayNuevasInstrucciones(boolean[], boolean[])
	 */
	@Override
	public DTOTurnadoHelper consultarHayNuevasInstrucciones(DTOTurnadoHelper turnado, String tipoInstruccion) {
		if(tipoInstruccion.equals("atencion")){
			for (int i = 0; i < turnado.getInstruccionesAtencion().length; i++) {
				if(turnado.getInstruccionesAtencion()[i] && turnado.getInstruccionesAtencionRealizadas()[i]){
					turnado.getInstruccionesAtencion()[i] = false;
				}
			}
		}else{
			for (int i = 0; i < turnado.getInstruccionesInformativas().length; i++) {
				if(turnado.getInstruccionesInformativas()[i] && turnado.getInstruccionesInformativasRealizadas()[i]){
					turnado.getInstruccionesInformativas()[i] = false;
				}
			}
		}
		return turnado;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#crearNuevoArreglo(boolean[])
	 */
	@Override
	public boolean[] crearNuevoArreglo(boolean[] arreglo) {
		boolean[] nuevoArreglo = null;
		if(arreglo != null){
			nuevoArreglo = new boolean[arreglo.length];
			for (int i = 0; i < arreglo.length; i++) {
				nuevoArreglo[i] = arreglo[i];
			}
		}
		return nuevoArreglo;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOTurnadoInterface#revisarArreglo(boolean[])
	 */
	@Override
	public boolean revisarArreglo(boolean[] arreglo) {
		boolean res = false;
		if(arreglo != null){
			for (boolean elemento : arreglo) {
				if(elemento == true){
					res = true;
					break;
				}
			}
		}
		return res;
	}
}