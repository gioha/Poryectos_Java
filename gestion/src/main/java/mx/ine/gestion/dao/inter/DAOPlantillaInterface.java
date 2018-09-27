package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

/**
 * @(#)DAOPlantillaInterface.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad de
 * Plantilla.
 * 
 * @author Sergio Ley Garcia
 * @since 06/09/2017
 */
public interface DAOPlantillaInterface extends DAOGenericGestionInterface<DTOPlantillaEntity, Integer> {

	/**
	 * Método que busca las plantillas pertenecientes a un tipo de documento
	 * especifico para una persona.
	 * 
	 * @param idAcronimo: Llave compuesta del acronimo por la que se hace el filtro.
	 * @param idPersona: persona a la que pertenecen las plantillas buscadas.
	 * 
	 * @return lista de plantillas pertenecientes esa persona.
	 *
	 * @author Sergio Ley Garcia
	 * @since 06/09/2017
	 */
	public List<DTOPlantillaEntity> consultarPlantillasXPersonaXAcronimo(DTOAcronimoID idAcronimo, Integer idPersona);
	
	/**
	 * Método para insertar un registro en la tabla de plantillas, ES IMPORTATE hacer notar que se hace de esta manera ya
	 * que la secuencia de la plantilla es LOGICA no una secuencia como tal
	 * 
	 * @param filtros: Objeto que contiene los datos de los filtros seleccionados por el usuario
	 * @return boolean; nos indica si se inserto correctamente o no el registro
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public boolean insertarPlantilla(DTOFiltrosPlantillaHelper filtros);

	/**
	 * Método que obtiene la ultima plantilla creada por un usuario para un acronimo (Se ordena por fecha_hora)
	 * 
	 * @param idArea: Identificador del área del usuario
	 * @param tipoArea: identificador del tipo de área del usuario
	 * @param idTipoDocumento: identificador del tipo de documento
	 * @param idAcronimo: identificador del acronimo
	 * @return DTOPlantillaEntity: Registro/Objeto encontrado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public DTOPlantillaEntity consultarUltimaPlantillaXAcronimoXUsuario(Integer idArea, Integer tipoArea, Integer idTipoDocumento, Integer idAcronimo);
}
