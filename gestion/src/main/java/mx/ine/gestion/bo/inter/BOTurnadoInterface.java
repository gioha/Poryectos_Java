/**
 * @(#)BOTurnadoInterface.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */

package mx.ine.gestion.bo.inter;

import java.util.List;

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


/**
 * Interfaz donde se declararan los métodos de la clase BO del módulo de
 * Turnado.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 */
public interface BOTurnadoInterface {

	public DTOHistDocRecepEntity construirHistDocRecep(Integer idArea, Integer tipoArea, Integer idDocumento,
			Integer idHistoricoPadre, Integer idPersonaHist, Integer idEstatusRecep);

	public DTOHistDocTurnoEntity construirHistDocTurno(Integer idArea, Integer tipoArea, Integer idDocumento, 
			Integer idHistoricoRecep, Integer idPersonaHist, Integer idEstatusRecep, String comentarioGrl);
	
	public DTOHBandejaERecibidosEntity construirHBandejaERecibidos(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea);

	public DTOBandejaEAtencionEntity construirBandejaEAtencion(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea, 
		 Integer noLeido, Integer idHistoricoRecep, Integer idHistoricoPadre);
	
	public DTOBandejaEInfoEntity construirBandejaEInfo(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea, 
			 Integer noLeido, Integer idHistoricoRecep, Integer idHistoricoPadre);
	
	public DTOHBandejaECCPEntity construirHBandejaECCP(Integer idDocumento, Integer idPersona, Integer idArea, Integer tipoArea);
	
	public DTOTurnInstruccionEntity construirInstruccion(Integer idDocumento, Integer idHistoricoRecep, Integer idPersona, Integer idPersonaTurnado, Integer idInstruccion);
		
	public DTOHBandejaEAtencionEntity construirHBandejaEAtencion(Integer idDocumento, Integer idPersona, Integer idArea, 
			Integer tipoArea, Integer idHistoricoPadre, Integer idHistoricoRecep);
	
	public DTOHBandejaEInfoEntity construirHBandejaEInfo(Integer idDocumento, Integer idPersona, Integer idArea,
			Integer tipoArea, Integer idHistoricoPadre, Integer idHistoricoRecep);
	
	public Boolean[] construirAregloInstrucciones(Boolean[] instrucciones, List<DTOTurnInstruccionEntity> listaIntruccionesRegistradas);
	
	public List<DTOTurnadoInstruHelper> construirInstruccionesRealizadas(List<DTOCargaTurnadoInstruHelper> listaCarga);
	
	public int contienePersona(List<DTOTurnadoInstruHelper> lista, DTOEstructuraAreasEntity persona);
	
	/**
	 * Método que se encarga de quitar las instrucciones que ya han sido guardadas en la BD y dejar los arreglos con las instrucciones nuevas 
	 * 
	 * @param turnado
	 * @param tipoInstruccion
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public DTOTurnadoHelper consultarHayNuevasInstrucciones(DTOTurnadoHelper turnado, String tipoInstruccion);
	
	public boolean[] crearNuevoArreglo(boolean[] arreglo);
	
	public boolean revisarArreglo(boolean[] arreglo);
}