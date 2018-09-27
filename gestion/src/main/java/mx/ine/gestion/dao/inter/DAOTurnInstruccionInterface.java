/**
 * @(#)DAOTurnInstruccionInterface.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOTurnInstruccionEntity;
import mx.ine.gestion.dto.db.DTOTurnInstruccionID;
import mx.ine.gestion.dto.helper.DTOCargaTurnadoInstruHelper;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 *
 */
public interface DAOTurnInstruccionInterface extends DAOGenericGestionInterface<DTOTurnInstruccionEntity, DTOTurnInstruccionID> {

	/**
	 * Método creado para la obtención de las instrucciones que ya han sido turnadas de un documento en especifico
	 * 
	 * @param dtoUsuarioLogin
	 * @param documento
	 * @return: Lista de instrucciones
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/03/2018
	 */
	public List<DTOCargaTurnadoInstruHelper> obtenerInstruccionesRealizadas(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento);
}
