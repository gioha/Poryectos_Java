package mx.ine.gestion.bsd.inter;

import java.util.HashMap;
import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * @(#)BSDEstructuraInterface.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */

/**
 * Interfaz donde se declararan los métodos de la clase BSD.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 21/08/2017
 */
public interface BSDEstructuraInterface {

	/**
	 * Método que consulta los usuarios de LDAP filtrados por nombre y apellido
	 * 
	 * @return List<DTOEstructuraAreasEntity>: Lista que contiene las personas de LDAP como objetos de DTOEstructuraAreasEntity filtradas por nombre y apellido.
	 * @param String nombre: nombre de la persona que se quiere buscar
	 * @param String apellido: apellido de la persona que se quiere buscar
	 * @param String area: area de la persona que se quiere buscar 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 30/08/2017
	 */
	public List<DTOEstructuraAreasEntity> buscarEstructuraLDAPPorNombreApellidoArea(String nombre, String apellido, String area, Integer idEstado) throws Exception;

	/**
	 * Método que consulta en LDAP las personas para la oficialía.
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que contiene las instrucciones del usuario en sesión.
	 * @param String nombre: nombre de la persona que se quiere buscar
	 * @param String apellidos: apellidos de la persona que se quiere buscar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/09/2017
	 */
	public List<DTOOficialiaEntity> buscarOficialiaLDAPPorNombreApellidoArea(String nombre, String apellido, String area, Integer idEstado) throws Exception;

	/**
	 * Método que consulta en la bd los titulares.
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que contiene la lista de los titulares.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public List<DTOJerarquiaPersonasEntity> consultarTitulares();
	
	/**
	 * Método que consulta en la bd las relaciones para crear la estructura.
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que contiene las relaciones de estructura filtradas por tipo de área y área.
	 * @param Integer tipo: id del tipo de area para la búsqueda
	 * @param Integer idArea: id del área para la búsqueda
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/09/2017
	 */
	public List<DTOJerarquiaPersonasEntity> consultarEstructuraPorArea(Integer tipoArea, Integer idArea);
	
	/**
	 * Método que consulta en la bd las oficialías.
	 * 
	 * @return List<DTOOficialiaEntity>: Lista que contiene las oficialías filtradas por tipo de área y área.
	 * @param Integer tipo: id del tipo de area para la búsqueda
	 * @param Integer idArea: id del área para la búsqueda
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */
	public List<DTOOficialiaEntity> consultarOficialiasPorArea(Integer tipoArea, Integer idArea);
	
	/**
	 * Método que consulta en la bd las oficialías.
	 * 
	 * @return List<DTOOficialiasAreasEntity>: Lista que contiene las oficialías filtradas por tipo de área y área.
	 * @param Integer tipo: id del tipo de area para la búsqueda
	 * @param Integer idArea: id del área para la búsqueda
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 9/10/2017
	 */
	public List<DTOOficialiasAreasEntity> consultarOficialiasAreasPorArea(Integer tipoArea, Integer idArea);

	/**
	 * Método que consulta en la bd una persona por su cuenta.
	 * 
	 * @return DTOEstructuraAreasEntity: objeto DTOEstructuraAreasEntity que coincide con la cuenta.
	 * @param String cuentaLdap: cuenta de LDAP de la persona que se quiere buscar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/09/2017
	 */
	public DTOEstructuraAreasEntity consultarPersonaPorCuenta(String cuentaLdap);

	/**
	 * Método que consulta en la bd las relaciones para crear la estructura.
	 * 
	 * @return DTOEstructuraAreasEntity: objeto DTOOficialia que coincide con la cuenta.
	 * @param String cuentaLdap: cuenta de LDAP de la oficialia que se quiere buscar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/09/2017
	 */
	public DTOOficialiaEntity consultarOficialiaPorCuenta(String cuentaLdap);

	/**
	 * Método que consulta en el cátalogo de áreas las áreas que no tienen organigrama todavía
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que las relaciones de estructura con las áreas que no tienen organigrama.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/09/2017
	 */
	public List<DTOCAreaEntity> consultarAreasSinOrganigrama(int tipoArea, int idEntidad) throws Exception;
	
	/**
	 * Método que consulta en el cátalogo de áreas las áreas que ya tienen organigrama
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que las relaciones de estructura con las áreas que ya tienen organigrama.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/09/2017
	 */
	public List<DTOCAreaEntity> consultarAreasConOrganigrama(int tipoArea, int idEntidad) throws Exception;

	/**
	 * Método que consulta en el cátalogo de todas las áreas.
	 * 
	 * @return List<DTOCAreas>: Lista que las relaciones de estructura con todas las áreas.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad) throws Exception;
	
	/**
	 * Método que consulta en la bd las relaciones para crear la estructura y crea el árbol filtrado por tipoArea y idArea.
	 * 
	 * @return TreeNode: Árbol con el organigrama.
	 * @param int tipoArea: id del tipo de área
	 * @param int idArea: id del área
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 17/09/2017
	 */
	public TreeNode consultarArbolEstructura(Integer tipoArea, Integer idArea);
	
	/**
	 * Método que consulta en la bd las entidades que ya tienen estructuras creadas.
	 * 
	 * @return List<Integer>: Lista con los ids de los estados que ya tienen organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/10/2017
	 */
	public List<Integer> consultarEntidadesConOrganigrama() throws Exception;
	
	/**
	 * Método que consulta en la bd las entidades que no tienen estructuras creadas.
	 * 
	 * @return List<Integer>: Lista con los ids de los estados que no tienen organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/10/2017
	 */
	public List<Integer> consultarEntidadesSinOrganigrama() throws Exception;
	
	public void guardarEstructuraCompleta(Integer idAreaSeleccionada, Integer tipoAreaSeleccionada, 
			List<DTOEstructuraAreasEntity> personas, List<DTOJerarquiaPersonasEntity> listaJerarquiasAgregar, 
			List<DTOJerarquiaPersonasEntity> listaJerarquiasEliminar, List<DTOOficialiaEntity> listaOficialiaAgregar, 
			List<DTOOficialiasAreasEntity> listaOficialiaEliminar) throws Exception;
	
	/**
	 * Método que consulta en la bd si ya existe la estructura para un área.
	 * 
	 * @return boolen: true si existe la estructura, false si no- 
	 * @param Integer
	 *            tipo: id del tipo de area para la búsqueda
	 * @param Integer
	 *            idArea: id del área para la búsqueda
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/11/2017
	 */
	public boolean consultarExisteEstructura(Integer tipoArea, Integer idArea);

	/**
	 * Método para obtener los tipos de área
	 * 
	 * @return List<DTOCTipoAreaEntity>: lista con los tipos de área
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/12/2017
	 * 
	 * @throws Exception
	 */
	public List<DTOCTipoAreaEntity> consultarTiposAreas() throws Exception;
	
	/**
	 * Método para consultar los estados
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/12/2017
	 */
	public List<DTOEstadosEntity> consultarEstados() throws Exception;
	
	/**
	 * Método para consultar los estados sin organigrama
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados sin organigrama
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/12/2017
	 */
	public List<DTOEstadosEntity> consultarEstadosSinOrganigrama() throws Exception;

	/**
	 * Método para consultar los estados con organigrama
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados con organigrama
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/12/2017
	 */
	public List<DTOEstadosEntity> consultarEstadosConOrganigrama() throws Exception;
}
