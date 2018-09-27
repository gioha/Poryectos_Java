/**
 * @(#)BSDReportesOficialiaInterface.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import org.primefaces.model.StreamedContent;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOMesesAnioHelper;
import mx.ine.gestion.dto.helper.DTOReportesOficialia;

/**
 * Interfaz donde se declararan los métodos de la clase BSDReportesOficialia, dicha clase
 * sirve para gestionar los métodos utilizados por la vista de Reportes de Oficialía.
 * 
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
public interface BSDReportesOficialiaInterface {

	/**
	 * Método que determina los acrónimos actualmente existentes en la BD. Recupera a todo
	 * el universo de folios de oficialia y devuelve una lista de los acrónimos extraídos.
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public void determinarCatalogosDeFolios(DTOReportesOficialiaHelper filtrosReportesOf);

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
	 * Método que determina un listado de meses disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOMesesAnioHelper>: Lista de meses
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOMesesAnioHelper> determinarMesesAnio();

	/**
	 * Método que determina un listado de parámetros de ordenamiento para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<String>: Lista de parámetros de ordenamiento
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<String> determinarParametrosOrden();

	/**
	 * Método que obtiene una lista de tipos de área disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCTipoAreaEntity>: Lista de tipos de área recuperados
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCTipoAreaEntity> recuperarTiposDeArea();

	/**
	 * Método que determina un listado de filtros disponibles para la selección por
	 * parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<String>: Lista de parámetros de ordenamiento
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<String> determinarFiltrosReporte();

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
	 * @return List<DTOCAreaEntity>: Lista de tipos de entidad recuperados
	 * @param Integer idTipoArea, Integer idEstado
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOCAreaEntity> recuperarAreas(Integer idTipoArea, Integer idEstado);

	/**
	 * Método que obtiene una lista de áreas de acuerdo al rol del usuario logueado disponibles
	 * para la selección por parte del usuario en la vista de Reportes de Oficialía.
	 * @return List<DTOCAreaEntity>: Lista de tipos de entidad recuperados
	 * @param String rolUsuario, Integer idOficialia
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia);

	/**
	 * Método encargado de validar los campos de búsqueda en la sección de Remitente (s) y/o Destinatario (s).
	 * @return String: Resultado de la validación
	 * @param String tipoBusqueda, DTOFiltrosReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public String validarFiltrosBusqueda(String tipoBusqueda, DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método encargado de validar los campos de búsqueda en la sección de Remitente (s) - Remitentes externos.
	 * @return String: Resultado de la validación
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public String validarFiltrosRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método que obtiene una lista de personas encontradas como resultado de una búsqueda por el usuario
	 * en la vista de Reportes de Oficialía.
	 * @return List<DTOEstructuraAreasEntity>: Lista de personas encontradas
	 * @param String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public List<DTOEstructuraAreasEntity> recuperarPersonas(String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente);

	/**
	 * Método que obtiene una lista de remitentes externos encontrados como resultado de una
	 * búsqueda por el usuario en la vista de Reportes de Oficialía.
	 * @return List<DTORemitentesExternosOfEntity>: Lista de remitentes externos encontrados
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método encargado de validar la correcta selección de personas una vez concluida la búsqueda de Remitente (s) o Destinatario (s).
	 * @return String: Resultado de la validación
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public String validarPersonasSeleccionadas(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método encargado de validar la correcta selección de personas una vez
	 * concluida la búsqueda de Remitentes externos.
	 * @return String: Resultado de la validación
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public String validarRemitenteExtSeleccionado(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método encargado de agregar una o varias personas a la lista de personas encontradas con base
	 * en la o las personas seleccionadas por el usuario.
	 * @return void
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void agregarPersonas(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método encargado de agregar uno o varios remitentes externos a la lista de
	 * remitentes externos con base en la selección por parte del usuario.
	 * @return void
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void agregarRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método que crea una lista final de filtros seleccionados en la vista de Reportes de Oficialía.
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @return List<String>: lista final de filtros seleccionados
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public List<String> generarFiltrosReporte(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método que consulta los documentos que coincidan con los filtros introducidos y/o seleccionados por
	 * el usuario de acuerdo a su rol en la vista de Reportes de Oficialía.
	 * @param String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf
	 * @return List<DTOReportesOficialia>: lista documentos que componen al reporte
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public List<DTOReportesOficialia> recuperarReporte(String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método que transforma la lista de documentos (reporte) en un outputStream
	 * para su correcta exportación en formato Excel.
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @return StreamedContent: flujo de bytes del reporte
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public StreamedContent generarReporte(DTOReportesOficialiaHelper filtrosReportesOf);

	/**
	 * Método que genera una lista de personas (remitentes, destinatarios y/o copias de conocimiento)
	 * con base en el reporte de documentos consultado con Hibernate.
	 * @param List<DTOReportesOficialia> reporteOficialia
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 29/03/2018
	 */
	public void generarListasPersonas(List<DTOReportesOficialia> reporteOficialia);

}
