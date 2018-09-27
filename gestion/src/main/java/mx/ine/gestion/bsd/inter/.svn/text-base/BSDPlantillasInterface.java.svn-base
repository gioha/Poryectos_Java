/**
 * @(#)BSDPlantillasInterface.java 05/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

/**
 * Interface  que contiene la firma de los métodos que acceden a el o los AS de las plantillas de documento del sistema de Gestión
 *
 * @author Luis Urrutia
 * @since 05/12/2017
 */
public interface BSDPlantillasInterface {

	/**
	 * Método para obtener los tipos de documentos
	 *
	 * @return List<DTOCTipoDocumentoEntity>: Lista que contiene los diferentes tipos de documentos.
	 *
	 * @author Luis Urrutia
	 * @since 05/12/2017
	 */
	public List<DTOCTipoDocumentoEntity> obtenerTipoDocumentos();

	/**
	 * Método para obtener los acrónimos
	 * 
	 * @param form: Objeto que contiene el idTipoDocumento, id y tipo del área para buscar los acrónimos
	 * 
	 * @return List<DTOAcronimoEntity>: Lista que contiene los diferentes acrónimos
	 * 
	 * @author Luis Urrutia
	 * @since 06/12/2017
	 */
	public List<DTOAcronimoEntity> obtenerAcronimos(DTOFiltrosPlantillaHelper form);
	
	/**
	 * Método para validar que no se supere el número de plantillas permitidas para un acronimo
	 * 
	 * @param filtros: Objeto que contiene la información de los filtros que selecciono el usuario.
	 * @return Boolean: Indica si se supero el máximo permitido
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Boolean validarNumeroPlantillas(DTOFiltrosPlantillaHelper filtros);

	/**
	 * Método para insertar la plantilla en la BD y copiarla al gluster
	 * 
	 * @param filtros: Objeto que contiene toda la información a insertar en la BD
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void agregarPlantilla(DTOFiltrosPlantillaHelper filtros) throws Exception;

	/**
	 * Método para eliminar una plantilla de la BD
	 * 
	 * @param plantilla: Objeto que contiene la información de la plantilla a eliminar
	 * 
	 * @author Luis Urrutia
	 * @throws Exception 
	 * @since 14/12/2017
	 */
	public void eliminarPlantilla(DTOPlantillaEntity plantilla) throws Exception;

}
