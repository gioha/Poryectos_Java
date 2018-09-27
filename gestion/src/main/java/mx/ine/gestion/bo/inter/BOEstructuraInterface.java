/**
 * @(#)BOEstructuraInterface.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.HashMap;
import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;


/**
 * Interfaz donde se declararan los métodos de la clase BO del módulo de
 * Estructura.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 16/09/2017
 */
public interface BOEstructuraInterface {

	/**
	 * Método para obtener la lista de DTOOficialiaEntity a partir de la lista DTOOficialiasAreas
	 * 
	 * @return List<DTOOficialiaEntity> lista de personas de oficialia.
	 * @param List<DTOOficialiasAreasEntity> lista de relaciones de las oficialías con el área.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */
	public List<DTOOficialiaEntity> oficialiasAresaAOficialia(List<DTOOficialiasAreasEntity> listaOficialiasAreas);

	/**
	 * Método que inicializa el árbol TreeNode
	 * 
	 * @return TreeNode: árbol nuevo listo para que se le agreguen nodos.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/09/2017
	 */
	public TreeNode inicializarArbol();
	
	/**
	 * Método que pasa la lista de jerarquías a un árbol
	 * 
	 * @return TreeNode: árbol con los nodos de las jerarquías
	 * @param List<DTOJerarquiaPersonasEntity> lista de jerarquías
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 17/09/2017
	 */
	public TreeNode listaJerarquiaAArbol(List<DTOJerarquiaPersonasEntity> listaJerarquia);
	
	/**
	 * Método para quitar caracteres que no queremos en la consulta de LDAP
	 * 
	 * @return String sin los caracteres que no están permitidos 
	 * @param String cadena: cadena
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/09/2017
	 */
	public String quitarOtrosCaracteres(String cadena);
	
	/**
	 * Método para sustituir acentos por * para evitar problemas con los nombres con acento.
	 * 
	 * @return String sin caracteres sin acento sustituidos por *'s 
	 * @param String cadena: cadena
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/09/2017
	 */
	public String sustituirAcentos(String cadena);

	/**
	 * Método para agregar las cuentas que faltan.
	 * 
	 * @param lista: lista List<DTOJerarquiaPersonasEntity>, se agregan las cuentas
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/10/2017
	 */	
	public void agregarCuentasALista(List<DTOJerarquiaPersonasEntity> lista);
	
	/**
	 * Método para agregar las cuentas que faltan.
	 * 
	 * @param lista: lista List<DTOJerarquiaPersonasEntity>, se agregan las cuentas
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/10/2017
	 */	
	public List<Integer> pasarListaAreasAEntidades(List<DTOCAreaEntity> listaAreas);
	
	public void obtenerIdsJerarquia(List<DTOJerarquiaPersonasEntity> lista,	HashMap<String, Integer> mapa);
	
	/**
	 * 
	 * @param idPersona: Integer con el id de la persona que acaba de ser insertada
	 * 
	 * @return Lista con las notificaciones que tiene que ser insertadas para una persona nueva
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/12/2017
	 */
	public List<DTONotificacionesEntity> inicializarNotificaciones(Integer idPersona);

	/**
	 * 
	 * @param idArea: Integer con el id del área guardada
	 * @param tipoArea: Integer con el id del tipo de área guardado
	 * 
	 * @return Lista con las notificaciones que tiene que ser insertadas para una estructura nueva
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/12/2017
	 */
	public List<DTONotificacionesOFEntity> inicializarNotificacionesOF(Integer idArea, Integer tipoArea);
}
