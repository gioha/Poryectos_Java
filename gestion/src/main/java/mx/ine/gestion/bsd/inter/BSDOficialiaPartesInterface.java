/**
 * @(#)BSDOficialiaPartesInterface.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOCapturaOficialiaHelper;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;

/**
 * Interface de la clase encargada de administrar el o los AS relacionados para
 * la pantalla de Oficialia de partes.
 * 
 * @author Sergio Ley Garcia
 * @since 02/11/2017
 * @update David Rodríguez Corral
 * @since 06/12/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public interface BSDOficialiaPartesInterface {
	/**
	 * Método que obtiene la lista de secciones del catálogo C_SECCIONES
	 * 
	 * @return List<DTOCSeccionesEntity> que contiene la lista de secciones.
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public List<DTOCSeccionesEntity> consultarSecciones();

	/**
	 * Método que la obtiene la lista de categorías del catálogo C_CATEGORIAS
	 * 
	 * @param idSeccion: id de la sección para consultar las categorías de esta sección
	 * 
	 * @return List<DTOCCategoriasEntity> que contiene la lista de categorias.
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public List<DTOCCategoriasEntity> consultarCategorias(Integer idSeccion);

	/**
	 * Método que obtiene el folio y las listas destinatarios, remitentes, ccp y anexos de un documento.
	 * 
	 * @param dtoBandejaEntrada: DTOBandejaEntradasOficialiaEntity donde se guardan los datos del documento a clasificar
	 * 
	 * @return DTODocumentoOficialiaHelper que contiene las listas
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public DTODocumentoOficialiaHelper consultarDatosClasificacion(DTOBandejaEntradasOficialiaEntity dtoBandejaEntradaString, String tipoApartado);

	/**
	 * Método que cancela la clasificación del documento actualizando el estatus a L=Libre
	 * 
	 * @param idDocumento: Integer que contiene el id del documento
	 * @param idArea: Integer que contiene el id del área
	 * @param tipoArea: Integer que contiene el tipo del área
	 * @param folio: String que contiene el folio a liberar
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void cancelarClasificarDocumento(Integer idDocumento, Integer idArea, Integer tipoArea, String folio);

	/**
	 * Método que clasifica el documento, insertando en la tabla de DOCUMENTO_CLASIF_AREA y DOCUMENTO_FOLIOS, eliminando
	 * el documento de la bandeja de entrada de oficialia y cambiando el estatus a C=Concluido
	 * 
	 * @param dtoBandejaEntrada: DTOBandejaEntradasOficialiaEntity donde se guardan los datos del documento a clasificar
	 * @param seccionSeleccionada: Sección donde se clasificará el documento
	 * @param categoriaSeleccionada: Categoría donde se clasificará el documento
	 * @param folio: String que contiene el folio
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void clasificarDocumento(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String seccionSeleccionada, 
			String categoriaSeleccionada, String folio);

	/**
	 * Método que consulta las áreas a las que esta registrada una oficialía 
	 * 
	 * @param idOficialia: id de la oficialia a consultar las áreas
	 * 
	 * @return List<DTOCAreaEntity> lista que contiene las áreas
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/12/2017
	 */
	public List<DTOCAreaEntity> consultarAreasPorOficialia(Integer idOficialia);
	
	/**
	 * Método que obtiene un folio para la captura de un documento electrónico.
	 * 
	 * @param idOficialia: Integer que tiene el id de oficialia que obtendrá el folio
	 * @param idArea: Integer que tiene el id del área que obtendrá el folio
	 * @param tipoArea: Integer que tiene el tipo del área que obtendrá el folio
	 * @param siglas: String que tiene las siglas que obtendrá el folio
	 * 
	 * @return DTODocumentoOficialiaHelper que contiene las listas
	 *
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public DTOApartadosNumDocOfEntity obtenerFolioDocFisico(Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, DTOApartadosNumDocOfEntity folioAnterior, String tipoApartado);

	/**
	 * Método que obtiene los combos que se necesitan al iniciar la pantalla
	 * @param String rolUsuario, Integer idOficialia
	 * @return DTODocumentoOficialiaHelper que contiene las listas
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 * @update José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public DTOCapturaOficialiaHelper consultarCombosIniciales(String rolUsuario, Integer idOficialia);

	/**
	 * Método que obtiene las areas
	 * 
	 * @param tipoArea: Integer que contiene el tipo de area a buscar
	 * @param idEntidad: Integer que contiene el id de la entidad
	 * 
	 * @return DTOCAreaEntity Lista que contiene las areas del tipo de área
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> consultarAreas(Integer tipoArea, Integer idEntidad);

	/**
	 * Método que obtiene los estados
	 * 
	 * @return DTOEstadosEntity Lista que contiene los estados
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOEstadosEntity> consultarEstados();

	/**
	 * Método que captura un remitente externo
	 * 
	 * @param dtoRemitenteExterno: Objeto que contiene el remitente externo
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void capturarRemitenteExterno(DTORemitentesExternosOfEntity dtoRemitenteExterno);

	/**
	 * Método que consulta si hay un folio pendiente
	 * 
	 * @param idOficialia: Contiene el id de oficialia
	 * @param estatus: Contiene el estatus 'A'
	 * 
	 * @return DTOBandejaEntradasOficialiaEntity Objeto que contiene el folio pendiente
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOBandejaEntradasOficialiaEntity consultarFoliosPendientes(Integer idOficialia, char estatus);

	/**
	 * Método libera un folio fisico
	 * 
	 * @param dtoApartado: Objeto que contiene el folio a liberar
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void liberarFolio(DTOApartadosNumDocOfEntity dtoApartado);

	/**
	 * Método que consulta una persona
	 * 
	 * @param idarea: Id del área para la búsqueda
	 * @param tipoArea: tipo de área para la búsqueda
	 * @param nombre: nombre para la búsqueda
	 * @param apellidos: apellidos para la búsqueda
	 * 
	 * @retun List<DTOEstructuraAreasEntity> Lista que contiene las personas encontradas
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonas(Integer idArea, Integer tipoArea, String nombre, String apellidos);

	/**
	 * Método encargado de recuperar una lista de remitentes externos encontrados con el fin de mostrarla al usuario.
	 * @param DTOCapturaOficialiaHelper dtoCapturaOf, DTOApartadosNumDocOfEntity dtoApartadoOf
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void recuperarRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf, DTOApartadosNumDocOfEntity dtoApartadoOf);

	/**
	 * Método encargado de cargar toda la información relacionada a un nuevo documento de oficialía de tipo físico.
	 * Dicha carga es transmitida a la capa ASA para su posterior inserción en BD.
	 * @param usuarioLogueado, dtoCapturaOf, dtoDocumentoOf, dtoApartadoOf, listaRemitentesDoc, listaDestinatariosDoc, listaCcpDoc
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void cargarDocumento(DTOUsuarioLogin usuarioLogueado, DTOCapturaOficialiaHelper dtoCapturaOf,
								DTODocumentoEntity dtoDocumentoOf, DTOApartadosNumDocOfEntity dtoApartadoOf,
								List<DTOEstructuraAreasEntity> listaRemitentesDoc,
								List<DTOEstructuraAreasEntity> listaDestinatariosDoc,
								List<DTOEstructuraAreasEntity> listaCcpDoc);

	/**
	 * Método encargado de recuperar un documento físico de oficialía con base en su identificador.
	 * @param Integer idDocumento
	 * @return DTODocumentoEntity: documento recuperado
	 * @author José Miguel Ortiz
	 * @since 26/04/2018
	 */
	public DTODocumentoEntity recuperarDocumento(Integer idDocumento);

}
