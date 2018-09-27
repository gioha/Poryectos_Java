/**
 * @(#)ASReportesOficialiaInterface.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOReportesOficialia;

/**
 * Interfaz donde se declararan los métodos de la clase ASReportesOficialia, dicha clase
 * sirve para gestionar los objetos DAO utilizados para soportar la funcionalidad de la
 * vista de Reportes de Oficialía.
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
public interface ASReportesOficialiaInterface {

	/**
	 * Método que obtiene una lista de folios de oficialía, dichos folios habrán de ser procesados para
	 * la posterior selección por parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOApartadosNumDocOfEntity>: Lista de folios de oficialía
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public List<DTOApartadosNumDocOfEntity> recuperarFoliosDocOf();

	/**
	 * Método que obtiene una lista de tipos de documento disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCTipoDocumentoEntity>: Lista de tipos de documento recuperados
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCTipoDocumentoEntity> recuperarTiposDocumento();

	/**
	 * Método que obtiene una lista de tipos de sección disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCSeccionesEntity>: Lista de tipos de sección recuperados
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCSeccionesEntity> recuperarSecciones();

	/**
	 * Método que obtiene una lista de tipos de categoría disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCCategoriasEntity>: Lista de tipos de categoría recuperados
	 * @param Integer idSeccion
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCCategoriasEntity> recuperarCategorias(Integer idSeccion);

	/**
	 * Método que obtiene una lista de tipos de área disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCTipoAreaEntity>: Lista de tipos de área recuperados
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCTipoAreaEntity> recuperarTiposDeArea();

	/**
	 * Método que obtiene una lista de tipos de entidad disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOEstadosEntity>: Lista de tipos de entidad recuperados
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOEstadosEntity> recuperarEntidades();

	/**
	 * Método que obtiene una lista de áreas disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCAreaEntity>: Lista de áreas recuperadas
	 * @param Integer idTipoArea, Integer idEstado
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCAreaEntity> recuperarAreas(Integer idTipoArea, Integer idEstado);

	/**
	 * Método que obtiene una lista de personas encontradas, resultado de una búsqueda por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOEstructuraAreasEntity>: Lista de personas encontradas
	 * @param String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOEstructuraAreasEntity> recuperarPersonas(String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente);

	/**
	 * Método que recupera una lista de documentos consultados que componen al reporte a generar, resultado de la
	 * introducción y/o selección de filtros por parte del usuario de acuerdo a su rol en la vista de Reportes de Oficialía.
	 * @return List<DTOReportesOficialia>: Lista de documentos consultados
	 * @param String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public List<DTOReportesOficialia> recuperarReporte(String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método que recupera una lista de remitentes externos que componen al reporte a generar, resultado de la
	 * introducción de información por parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTORemitentesExternosOfEntity>: Lista de remitnetes externos consultados
	 * @param String nombreRemitenteExt, String dependenciaRemitenteExt
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExt(String nombreRemitenteExt, String dependenciaRemitenteExt);

	/**
	 * Método que recupera una lista de áreas con base en el rol del usaurio logueado.
	 * Dicha lista será desplegada al usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCAreaEntity>: Lista de áreas recuperadas
	 * @param String rolUsuario, Integer idOficialia
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia);

}
