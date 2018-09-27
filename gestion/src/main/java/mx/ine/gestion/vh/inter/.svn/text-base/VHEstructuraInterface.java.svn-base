package mx.ine.gestion.vh.inter;

import java.util.HashMap;
import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.org.ine.servicios.dto.db.DTOEstado;

import org.primefaces.model.TreeNode;

/**
 * @(#)VHPersonalInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

/**
 * Interfaz que contiene la firma de los métodos que tendran la lógica 
 * y procedimientos del módulo de Personal
 *
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 */
public interface VHEstructuraInterface {

	/**
	 * Método para inicializar un árbol TreeNode con objeto DTOJerarquiaPersonas
	 * 
	 * @return objeto TreeNode inicializado para agregar más nodos.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public TreeNode inicializarArbol();
	
	/**
	 * Método para eliminar un nodo de un árbol.
	 * 
	 * @param nodoRaiz: objeto TreeNode con el árbol del organigrama.
	 * @param nodoBorrar: objeto TreeNode con el nodo que se eliminará.
	 * 
	 * @return boolean: true cuando se eliminó correctamente el nodo y false cuando no se pudo eliminar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public boolean eliminarNodoArbol(TreeNode nodoRaiz, TreeNode nodoBorrar);

	/**
	 * Método para buscar un nodo tipo TreeNode por la combinación de cuenta padre y cuenta hijo. 
	 * 
	 * @param nodoRaiz: objeto TreeNode en el que se busca el nodo.
	 * @param cuentaPersona: String con la cuenta de la persona para buscar.
	 * @param cuentaPadre: String con la cuenta del padre de la persona.
	 * 
	 * @return TreeNode: el nodo de tipo TreeNode que coincide con las cuentas. Si no se encontró, regresa null.
	 * @author Pável Alexei Martínez Regalado
	 * 
	 * @since 25/08/2017
	 */
    public TreeNode buscarNodoPorCuentas(TreeNode nodoRaiz, String cuentaPersona, String cuentaPadre);

	/**
	 * Método para buscar un nodo tipo TreeNode por la combinación de cuenta padre y cuenta hijo. 
	 * 
	 * @param nodoRaiz: objeto TreeNode en el que se busca el nodo.
	 * @param cuentaPersona: String con la cuenta de la persona para buscar.
	 * 
	 * @return TreeNode: el nodo de tipo TreeNode que coincide con las cuentas. Si no se encontró, regresa null.
	 * @author Pável Alexei Martínez Regalado
	 * 
	 * @since 25/08/2017
	 */
    public TreeNode buscarNodoPorCuenta(TreeNode nodoRaiz, String cuentaPersona);
    
    /**
     * 
     * Método para buscar en el árbol creado el titular
     * 
     * @param nodoRaiz: objeto TreeNode con el árbol
     * @return TreeNode: nodo con el titular. null si no se encuentra.
     * 
     * @author Pável Alexei Martínez Regalado
     * @since 4/10/2017
     */
    public TreeNode buscarNodoTitular(TreeNode nodoRaiz);
    
	/**
	 * Método que busca en una lista si ya existe una oficialía con cierta cuenta. 
	 * 
	 * 
	 * @param lista: objeto List<DTOOficialiaOficialiaEntity> con la lista de oficialías que hay para un área
	 * @param cuenta: String con la cuenta que se quiere buscar
	 * 
	 * @return boolean: true cuando ya existe la cuenta guardada en Oficialía. false cuando no existe la cuenta aún. 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/09/2017
	 */    
    public boolean existeOficialiaEnListaPorCuenta(List<DTOOficialiaEntity> lista, String cuenta);
    
	/**
	 * Método que recorre el árbol.
	 * 
	 * @param nodoRaiz: objeto TreeNode que se va a recorrer.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public boolean recorrerArbol(TreeNode nodoRaiz);

	/**
	 * Método recursivo que crea las listas de DTOEstructuraAreasEntity y de DTOJerarquiaPersonasEntity que se guardarán.
	 * 
	 * @param nodoRaiz: objeto TreeNode con el organigrama en árbol
	 * @param listaEstructura: lista List<DTOEstructuraAreasEntity> vacía. Dentro de está función se le van agregando los elementos.
	 * @param listaJerarquia: lista List<DTOJerarquiaPersonasEntity> vacía. Dentro de está función se le van agregando los elementos.
	 * 
	 * @return boolean: true o false para efecto de la recursividad.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/09/2017
	 */
	public boolean crearListasEstructura(TreeNode nodoRaiz, List<DTOEstructuraAreasEntity> listaEstructura,
			List<DTOJerarquiaPersonasEntity> listaJerarquia);
	
	/**
	 * Método para agregar un nodo nuevo en el árbol de organigrama 
	 * 
	 * @param nodo: objeto TreeNode que es el padre al que se le quiere agregar el nodo.
	 * @param relacion: objeto DTOJerarquiaPersonasEntity la relación que se quiere agregar al árbol.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public void agregarEnNodo(TreeNode nodo, DTOJerarquiaPersonasEntity relacion);
	
	/**
	 * Método para validar campos TODO
	 * 
	 * @param campo: valor del campo que se va a validar de tipo String.
	 * @param nombre: valor con el nombre del campo que se está validando.
	 * @param idCampo: valor con el id del campo que se va a validar de tipo String.
	 * @param validaciones: arreglo de String de qué se va a validar.
	 * 
	 * @return boolean: false si no se validó. true si se validó correctamente.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public boolean validarCampo(String campo, String nombre, String idCampo, String[] validaciones);
	
	/**
	 * Método para validar campos TODO
	 * 
	 * @param idCampo: String con el id donde se mostrará el mensaje.
	 * @param mensaje: Mensaje que se mostrará
	 * @param nota: Mensaje adicional que se mostrará. 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto);
	
	/**
	 * Método para validar campos TODO
	 * 
	 * @param idCampo: String con el id donde se mostrará el mensaje.
	 * @param mensaje: Mensaje que se mostrará
	 * @param nota: Mensaje adicional que se mostrará.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public void mostrarMensajeRequerido(String idCampo, String mensaje, String nota);
	
	/**
	 * Método para obtener las siglas de un área dentro de una lista.
	 * 
	 * @param areas: lista de DTOCAreas con las áreas.
	 * @param idArea: Integer con el id del área del que se quieren las siglas.
	 * 
	 * @return String: siglas del área requerida.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/09/2017
	 */
	public String obtenerSiglasDeListaAreas(List<DTOCAreaEntity> areas, Integer idArea);

	/**
	 * Método para obtener el nombre de un área dentro de una lista.
	 * 
	 * @param areas: lista de DTOCAreas con las áreas.
	 * @param idArea: Integer con el id del área del que se quiere el nombre.
	 * 
	 * @return String: nombre del área requerida.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/09/2017
	 */
	public String obtenerDescripcionDeListaAreas(List<DTOCAreaEntity> areas, Integer idArea);
	
	/**
	 * Método para ir poniendo los ids de las personas para las relaciones de jerarquías
	 * 
	 * @param lista: lista de DTOJerarquiaPersonasEntity con las relaciones de jerarquía
	 * @param mapa: HashMap con la relacion cuenta-id
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/09/2017
	 */	
	public void obtenerIdsJerarquia(List<DTOJerarquiaPersonasEntity> lista, HashMap<String, Integer> mapa);

	/**
	 * Método para buscar una relacion en una lista de jerarquía
	 * 
	 * @param lista: List<DTOJerarquiaPersonasEntity> con la lista de jerarquías en la que se va a buscar
	 * @param idPersona: Integer con el id de la persona que se va a buscar.
	 * @param idPadre: Integer con el id del padre que se va a buscar.
	 * 
	 * @return boolean: true si se encuentra, false sino.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public boolean buscarJerarquiaPersonaPorId(List<DTOJerarquiaPersonasEntity> listaJerarquias, Integer idPersona, Integer idPadre);

	/**
	 * Método para buscar un id en una lista de oficialías-area.
	 * 
	 * @param lista: List<DTOOficialiasAreasEntity> con la lista de oficialías-areas en la que se va a buscar
	 * @param idOficialia: Integer con el id de la oficialia que se va a buscar.
	 * 
	 * @return boolean: true si se encuentra, false sino.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public boolean buscarOficialiasAreaPorId(List<DTOOficialiasAreasEntity> listaOficialia, Integer idOficialia);
	
	/**
	 * Método para buscar un id en una lista de oficialías.
	 * 
	 * @param lista: List<DTOOficialiasAreasEntity> con la lista de oficialías en la que se va a buscar
	 * @param idOficialia: Integer con el id de la oficialia que se va a buscar.
	 * 
	 * @return boolean: true si se encuentra, false sino.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public boolean buscarOficialiaPorId(List<DTOOficialiaEntity> listaOficialia, Integer idOficialia);
	

	/**
	 * Método para determinar las jerarquías que se agregarán y eliminaran
	 * 
	 * @param listaAnterior: List<DTOJerarquiaPersonasEntity> lista de jerarquías que se tenía antes de hacer cambios
	 * @param listaNueva: List<DTOJerarquiaPersonasEntity> lista de jerarquías después de los cambios
	 * @param listaAgregados: List<DTOJerarquiaPersonasEntity> lista de jerarquías que se agregarán
	 * @param listaEliminar: List<DTOJerarquiaPersonasEntity> lista de las jerarquías que se quitarán
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 8/10/2017
	 */		
	public void procesarListasJerarquias(List<DTOJerarquiaPersonasEntity> listaAnterior, List<DTOJerarquiaPersonasEntity> listaNueva, 
			List<DTOJerarquiaPersonasEntity> listaAgregados, List<DTOJerarquiaPersonasEntity> listaEliminar);
	
	/**
	 * Método para determinar las oficialías que se agregarán y eliminaran
	 * 
	 * @param listaAnterior: List<DTOOficialiasAreasEntity> lista de oficialías que se tenía antes de hacer cambios
	 * @param listaNueva: List<DTOOficialiasAreasEntity> lista de oficialías después de los cambios
	 * @param listaAgregados: List<DTOOficialiasAreasEntity> lista de oficialías que se agregarán
	 * @param listaEliminar: List<DTOOficialiasAreasEntity> lista de las oficialías que se quitarán
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 8/10/2017
	 */		
	public void procesarListasOficialias(List<DTOOficialiasAreasEntity> listaAnterior, List<DTOOficialiaEntity> listaNueva, 
			List<DTOOficialiaEntity> listaAgregados, List<DTOOficialiasAreasEntity> listaEliminar);
	
	/**
	 * Método para traer una sóla área pero que quede en una lista
	 *
	 * @param lista: lista List<DTOCAreas> con las áreas.
	 * @param id: Integer con el id del área.
	 * 
	 * @return List<DTOCAreas> una lista con la única área encontrada
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */	
	public List<DTOCAreaEntity> buscarAreaPorIdEnLista(List<DTOCAreaEntity> listaAreas, Integer id);

	/**
	 * Método para mostrar los errores en consola con formato
	 * 
	 * @param e: Exception, excepción que originó el problema
	 * @param clase: String, clase en la que se lanzó la excepción
	 * @param metodo: String, método en el que se lanzó la excepción
	 * @param mensaje: String, mensaje adicional que se quiere mostrar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public void enviarError(Exception e, String clase, String metodo, String mensaje);
	
	/**
	 * Método para filtrar la lista de estados que se obtiene de comunicaiones con la lista de estados que se quieren mostrar
	 * 
	 * @param listaEstadosCompleta: List<DTOEstados> Lista con los estados traídos de comunicaciones
	 * @param listaEstadosEnBase: List<Integer> Lista con los ids de los estados requeridos
	 * 
	 * @return List<DTOEstados> lista con los estados que coinciden con los ids requeridos
	 */
	public List<DTOEstadosEntity> hacerMatchEntidades(List<DTOEstadosEntity> listaEstadosCompleta, List<Integer> listaEstadosEnBase);

	/**
	 * Método para obtener el nombre del estado por su id
	 * 
	 * @param estados: List<DTOEstados> todos los estados
	 * @param idEstado: Integer id del estado que buscamos el nombre
	 * 
	 * @return String con el nombre del estado
	 */
	public String obtenerDescripcionDeEntidades(List<DTOEstadosEntity> estados, Integer idEstado);

	/**
	 * Método que se manda llamar posterior a la captura con el cual en caso de que el administrador o el administrador de área
	 * se den de alta en la captura de Organigrama habilita sus permisos para que puedan entrar a los módulos posteriores
	 * y redirecciona dependiendo del rol a la pantalla de consulta.
	 * 
	 * @param listaEstructuras: Contiene la información de cada una de las personas que se dio de alta.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 22/11/2017
	 */
	public void validacionesPosCapturaParaAdministrador(List<DTOEstructuraAreasEntity> listaEstructuras);
}