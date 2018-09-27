/**
} * @(#)BSDCapturarDocumentoInterface.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

/**
 * Interfaz donde se declararan los métodos de la clase BSD, es la clase para
 * gestionar los métodos para la captura de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
public interface BSDCapturarDocumentoInterface {

	/**
	 * Método que obtiene los tipos de documentos que se necesitan para la captura de documento.
	 * 
	 * @return List<DTOCTipoDocumentoEntity> : Lista que contiene los tipos de documentos seleccionables.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos();

	/**
	 * Método que obtiene los acrónimos que se utilizan en la captura de documento.
	 * 
	 * @param idArea: Identificador del área con la que se filtran los resultados.
	 * @param tipoArea:  Tipo del área con la que se filtran los resultados.
	 * @param idTipoDocumento:  Identificador del tipo de documento con la que se filtran los resultados.
	 * @return List<DTOAcronimoEntity>: Lista que contiene los resultados obtenidos.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 01/09/2017
	 */
	public List<DTOAcronimoEntity> consultarAcronimos(Integer idTipoDocumento);

	/**
	 * Método que obtiene las plantillas disponibles para el usuario
	 * (preeviamente tuvo que haberlas configurado)
	 * 
	 * @param idTipoDocumento: Identificador del tipo de documento.
	 * @param idAcronimo: Identificador del tipo de acrónimo.
	 * @return List<DTOPlantillaEntity>: Lista que contiene los resultados obtenidos.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 01/09/2017
	 */
	public List<DTOPlantillaEntity> consultarPlantillas(Integer idTipoDocumento, Integer idAcronimo);

	/**
	 * Método que obtiene los tipos de área disponibles para que el usuario pueda seleccionarlos.
	 * 
	 * @return List<DTOCTipoAreaEntity>: Lista que contiene los tipos de áreas encontrados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 01/09/2017
	 */
	public List<DTOCTipoAreaEntity> consultarTiposArea();

	/**
	 * Método que obtiene las entidades capturadas en gestion (NO TODAS) 
	 * 
	 * @return List<DTOEstadosEntity> lista de entidades encontradas.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstadosEntity> consultarEntidades();

	/**
	 * Método que obtiene las áreas capturadas en gestión
	 * (NO TODAS, si no se ha hecho el organigrama del área no la trae) 
	 * 
	 * @param tipoArea: Identificador del tipo de área por el que se requiere filtrar
	 * @param idEstado: Identificador de la entidad por la que se requiere filtrar
	 * @return List<DTOCAreaEntity> : lista de áreas encontradas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> consultarAreas(Integer tipoArea, Integer idEstado);
	
	/**
	 * Método que obtiene las áreas capturadas en gestión
	 * (NO TODAS, si no se ha hecho el organigrama del área no la trae) 
	 * 
	 * @param nombreArea: nombre del área por la que se requiere filtrar
	 * @return List<DTOCAreaEntity> : lista de áreas encontradas
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public List<DTOCAreaEntity> consultarAreasDestinatarias(String nombreArea);

	/**
	 * Método que obtiene a las personas que se buscan como remitentes, destinatarios o ccp en
	 * base a los filtros que fueron seleccionados.
	 * 
	 * @param tipoArea: tipo de área seleccionada
	 * @param idArea: Identificador del área seleccionada.
	 * @param nombre: nombre del usuario que estan buscando.
	 * @param apellidos: apellidos del usuario que estan buscando.
	 * @return List<DTOEstructuraAreasEntity> : 
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 02/04/2018
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonas(Integer tipoArea, Integer idArea, String nombre, String apellidos);

	/**
	 * Método que guarda toda la información que el usuario capturo en la pantalla y la pone en las diferentes
	 * tablas de la BD, así como en el gluster los documentos creados y/o adjuntados.
	 * 
	 * @param filtros: Objeto que contiene la información capturada por el usuario.
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 01/09/2017
	 */
	public Integer guardarDocumento(DTOFiltrosCapturaDocumentoHelper filtros) throws Exception;
	
	/**
	 * Método que guarda la respuesta del documento que ha sido turnado
	 * 
	 * @param filtroResopnder
	 */
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOBandejaEAtencionEntity atencion) throws Exception;
	
	/**
	 * Método que guarda la respuesta del documento que ha sido turnado
	 * 
	 * @param filtroResopnder
	 */
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOBandejaERecibidosEntity recibido) throws Exception;
	
	/**
	 * Método que guarda la respuesta del documento que ha sido turnado
	 * 
	 * @param filtroResopnder
	 */
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOHBandejaEAtencionEntity atencion) throws Exception;
	
	/**
	 * Método que guarda la respuesta del documento que ha sido turnado
	 * 
	 * @param filtroResopnder
	 */
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOHBandejaERecibidosEntity recibido) throws Exception;
}
