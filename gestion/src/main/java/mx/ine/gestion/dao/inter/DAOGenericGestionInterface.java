/**
 * @(#)DAOGenericGestionInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.io.Serializable;
import java.util.List;

import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.query.QRYContainerInterface;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Esta interfaz es la que contendra los métodos generales para cualquier DAO
 * 
 * @author INE
 */
public interface DAOGenericGestionInterface <T extends Serializable, ID extends Serializable> {

	/**
	 * Obtiene el contenedor de querys
	 * 
	 * @return QRYContainerInterface contenedor con las querys.
	 */
	public QRYContainerInterface getContainer();

	/**
	 * Persiste un objeto a la base de datos .
	 * 
	 * @return ID: el objeto que representa el identificador del objeto que se ha guardado a la base de datos
	 * 
	 */
	public ID guardar(DTOBase object);

	/**
	 * Modifica o actualiza un bojeto a la base de datos 
	 * 
	 */
	public void modificar(DTOBase object);

	/**
	 * Elimina un objeto de la base de datos
	 * 
	 */
	public void eliminar(DTOBase object);
	
	/**
	 * Método genérico para obtener una la lista de objetos ordenados por un atributo ordenado ascendentemente
	 * 
	 * @param propiedadOrdenamiento Atributo por el cÃºal se ordenara el resultado
	 * @param orden Orden ascendente (true) o descendente (false) de la lista
	 * @return Lista con los registros recuperados de la base de datos
	 */
	public List<T> consultarTodosOrdenadosAscPor(String propiedadOrdenamiento);
	
	/**
	 * Método genérico para obtener una la lista de objetos ordenados por un atributo ordenado descendentemente
	 * 
	 * @param propiedadOrdenamiento Atributo por el cÃºal se ordenara el resultado
	 * @param orden Orden ascendente (true) o descendente (false) de la lista
	 * @return Lista con los registros recuperados de la base de datos
	 */
	public List<T> consultarTodosOrdenadosDescPor(String propiedadOrdenamiento);
	
	
	/**
	 * Obtiene la sesión actual de base de datos.
	 * 
	 * @return Session : sesión actual de bd.
	 * 
	 * @author IFE
	 * @since 21/02/2014
	 */
	public Session getSession();
	
	/**
	 * Obtiene la instancia del objeto que 
	 * se encarga de crear las sesiones de base de datos.
	 * 
	 * @return SessionFactory : el valor del atributo <code>sessionFactory</code>
	 * 
	 * @author IFE
	 * @since 21/02/2014
	 */
	public SessionFactory getSessionFactory();
	
	/**
	 * Abre una sesión a base de datos.
	 * 
	 * @return Session : Sesión de bd.
	 *
	 * @author IFE
	 * @since 21/02/2014
	 */
	public Session openSession();
	
	/**
	 * Método que busca un objeto por su llave primaria
	 * 
	 * @param id : llave primaria del objeto que se busca
	 * @return T : Objeto encontrado
	 * 
	 * @author IFE
	 * @since 21/02/2014
	 */
	public T buscarPorId(ID id);

	/**
	 * Método que busca todos los registros en una tabla
	 * 
	 * @return List<T> : lista con todos los objetos
	 * 
	 * @author IFE
	 * @since 24/03/2014
	 */
	public List<T> buscarTodos();

	/**
	 * Método para eliminar un objeto pero con la regla donde 
	 * primero se modifica el registro y despues se elimina en la BD.
	 * 
	 * @param object : objeto que se modificara
	 * 
	 * @author IFE
	 * @since 21/02/2014
	 */
	public void modificarYeliminar(DTOBase object);
	
	/**
	 * Método que ejecuta una serie de codigos los cuales
	 * hacen que durante la consulta en las comparaciones no se haga 
	 * distinción entre Mayusculas, Minusculas y Acentos, por ejemplo
	 * si se hace un like sobre un campo digamos "Nombre" y se esta 
	 * pasando el valor "maria" y existen valores como MarÃ­a, Maria, mAria,
	 * este los traera como resultado.
	 * 
	 * @author José Guadalupe Burgos Colomoxcatl
	 * @author Roberto ShirÃ¡sago DomÃ­nguez
	 * @since 21/02/2014
	 */
	public void activaMatchModeWords();

	/**
	 * Método para sincronizar los datos en sesión con los que se encuentran en la base de datos
	 * 
	 * @author IFE
	 * @since 21/02/2014
	 */
	public void flush();
	
	/**
	 * Obtiene la clase de persistencia de este dao
	 * @return Class<T> una clase de algÃºn tipo
	 */
	public Class<T> getPersistentClass();

	/**
	 * Método que guarda o actualiza, pero solo se usa en emergencia.
	 * @param object
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public void emergencia(DTOBase object);
}
