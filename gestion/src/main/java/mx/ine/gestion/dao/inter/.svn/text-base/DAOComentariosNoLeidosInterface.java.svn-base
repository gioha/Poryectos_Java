/**
 * @(#)DAOComentariosNoLeidosInterface.java 17/04/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidosID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @author Homero Fidel Villanueva
 * @since 17/04/2018
 *
 */
public interface DAOComentariosNoLeidosInterface extends DAOGenericGestionInterface<DTOComentariosNoLeidos, DTOComentariosNoLeidosID> {
	
	public List<DTOComentariosNoLeidos> obtenerEstatusComentarios(DTODocumentoEntity documento,DTOEstructuraAreasEntity persona);
	
	public List<DTOComentariosNoLeidos> obtenerComentarios(DTODocumentoEntity documento,DTOEstructuraAreasEntity persona, Integer noLeido);

}
