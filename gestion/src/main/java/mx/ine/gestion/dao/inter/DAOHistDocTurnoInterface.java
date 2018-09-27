/**
 * @(#)DAOHistDocTurnoInterface.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoID;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 *
 */
public interface DAOHistDocTurnoInterface extends DAOGenericGestionInterface<DTOHistDocTurnoEntity, DTOHistDocTurnoID> {
	
	public DTOHistDocTurnoEntity obtenerHistoricoTurnado(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona);
	
	public DTOHistDocTurnoEntity obtenerHistoricoTurnado(Integer idHistoricoRecep);
	
	public List<DTOHistDocTurnoEntity> obtenerPersonasTurnadas(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno);
	
}
