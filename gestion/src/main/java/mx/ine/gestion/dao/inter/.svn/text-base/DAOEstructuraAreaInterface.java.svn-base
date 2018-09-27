/**
 * @(#)DAOEstructuraAreaInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOFiltroEstructuraAreaHelper;

/**
 * Interfaz encargada de contener la firma de los métodos que obtendrán la
 * información de la base de datos para la tabla ESTRUCTURA_AREA del esquema de
 * GESTION4 en base a consultas hechas por criteria y/o sqlquery.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/08/2017
 */
public interface DAOEstructuraAreaInterface extends
		DAOGenericGestionInterface<DTOEstructuraAreasEntity, Integer> {

	/**
	 * Método que obtiene a una persona a través de su cuenta institucional la
	 * cual se registra en la tabla de ESTRUCTURA_AREA en el campo de
	 * CUENTA_LDAP
	 * 
	 * @param cuentaINE
	 *            : Cadena que contiene la cuenta institucional SIN EL @ine.mx
	 * @return DTOEstructuraAreasEntity: Objeto con el registro encontrado.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/08/2017
	 */
	public DTOEstructuraAreasEntity consultarPersonaXCuenta(String cuentaINE);

	/**
	 * Método que obtiene a una persona a través de su cuenta institucional la
	 * cual se registra en la tabla de ESTRUCTURA_AREA en el campo de
	 * CUENTA_LDAP PARA PERSONAS ACTIVAS!!
	 * 
	 * @param cuentaINE
	 *            : Cadena que contiene la cuenta institucional SIN EL @ine.mx
	 * @return DTOEstructuraAreasEntity: Objeto con el registro encontrado.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/11/2017
	 */
	public DTOEstructuraAreasEntity consultarPersonaXCuentaActiva(String cuentaINE);

	/**
	 * Método que obtiene las personas por su nombre o apellidos o área.
	 * NOTA: pueden ser cualquiera de ellas o ninguna
	 * 
	 * @param nombre: nombre de la persona que se busca
	 * @param apellido: apellido de la persona que se busca
	 * @param tipoArea : tipo de área de la persona que se esta buscando
	 * @param idArea : identificador del área de la persona que se esta buscando
	 * 
	 * @return lista de las persona que coinciden con la búsqueda
	 *
	 * @author Sergio Ley Garcia
	 * @update Roberto Shirásago Domínguez
	 * @since 08/09/2017
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonasXNombreXApellidosXArea(
			String nombre, String apellido, Integer tipoArea, Integer idArea);

//	/**
//	 * Método que busca en la tabla "ESTRUCTURA_AREAS" los registros que
//	 * coincidan con el ID_AREA, TIPO_AREA y que contengan la cadena
//	 * %coincidencia%.
//	 * 
//	 * Dentro de los registros encontrados con esas caracteristicas elimiana el
//	 * registro que tenga la "cuentaLDAP"
//	 * 
//	 * @param idArea
//	 * @param tipoArea
//	 * @param cuentaLDAP
//	 * @param coincidencia
//	 * @return
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 04/10/2017
//	 */
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(int idArea, int tipoArea, String coincidencia, String cuentaLDAP);
//
//	/**
//	 * Método que regresa todos los registros de la tabla "ESTRUCTURA_AREAS" que
//	 * coincidan con el ID_AREA Y TIPO_AREA recibidos.
//	 * 
//	 * @param idArea
//	 *            : Id del área
//	 * @param tipoArea
//	 *            : Tipo del Área
//	 * @return
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 04/10/2017
//	 */
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(int idArea, int tipoArea);
	
	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(DTOFiltroEstructuraAreaHelper filtro);
//
//	/**
//	 * Método que regresa todos los registros de la tabla "ESTRUCTURA_AREAS" que
//	 * coincidan con los atributos idArea y tipoArea del objeto recibido
//	 * dtoEstructuraEntity.
//	 * 
//	 * Excluye el registro que tiene la misma cuentaLDAP que el objeto recibido
//	 * dtoEstructuraEntity.
//	 * 
//	 * @param dtoEstructuraEntity
//	 *            : Objeto a partir del cual se realizará la consulta,
//	 *            dependiendo de su atributo idArea y tipoArea
//	 * @return
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 04/10/2017
//	 */
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(DTOEstructuraAreasEntity dtoEstructuraEntity);
//
//	/**
//	 * Método que regresa todos los registros de la tabla "ESTRUCTURA_AREAS" que
//	 * coincidan con los atributos idArea y tipoArea del objeto recibido
//	 * dtoEstructuraEntity y que coincidan en el nombre o los apellidos con la
//	 * cadena coincidencia .
//	 * 
//	 * Excluye el registro que tiene la misma cuentaLDAP que el objeto recibido
//	 * dtoEstructuraEntity.
//	 * 
//	 * @param dtoEstructuraEntity
//	 *            : Objeto a partir del cual se realizará la consulta,
//	 *            dependiendo de su atributo idArea y tipoArea
//	 * @return
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 04/10/2017
//	 */
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(DTOEstructuraAreasEntity dtoEstructuraEntity, String coincidencia);
	
	/**
	 * Método para cambiar el estatus de las personas que ya no se encuentren en
	 * el organigrama.
	 * 
	 * @param Integer
	 *            idPersona: id de la persona
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/11/2017
	 */
	public void actualizarEstatusEstructura(Integer idPersona);

	/**
	 * Método para quitar el nombre de nivel a las personas que ya no son titulares	
	 * 
	 * @param Integer
	 *            idPersona: id de la persona
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 23/01/2018
	 */
	public void reiniciarNombreNivelArea(Integer idPersona);

	/**
	 * Método que devuelve todos los registros de la tabla "ESTRUCTURA_AREAS"
	 * que coincidan con los ids
	 * 
	 * @param ids
	 *            id de los registros buscados
	 * @return
	 *
	 * @author Sergio Ley Garcia
	 * @since 19/12/2017
	 */
	public List<DTOEstructuraAreasEntity> buscarIds(List<Integer> ids);
	
	/**
	 * Función para volver nulos todos los campos VER_VERSION_T para un área
	 * 
	 * @param idArea: id del área a la cual se le reiniciarán sus valores
	 * @param tipoArea: tipo del área a la cual se le reiniciarán sus valores
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/01/2018
	 */
	public void reiniciarCorresponsalesArea(Integer idArea, Integer tipoArea);
	
	/**
	 * Función para modificar los campos VER_VERSION_T de acuerdo al titular de un área
	 * 
	 * @param idTitular: id del titular en base al cual se actualizará de acuerdo la sus corresponsales
	 * @throws Exception
	 * 
 	 * @author Pável Alexei Martínez Regalado
	 * @since 16/01/2018
	 */
	public void actualizarCorresponsalesArea(Integer idArea, Integer tipoArea);

	/**
	 * Método que busca un conjunto de personas en base a sus cuentas y el identificador de su área.
	 * 
	 * @param cuentas: Lista con las cuentas de LDAP que se utilizan como filtro
	 * @param tipoArea: Identificador del tipo de área
	 * @param idArea: Identificador del área
	 * @return List<DTOEstructuraAreasEntity>: lista de áreas encontradas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonasXCuentasXArea(List<String> cuentas, Integer tipoArea, Integer idArea);
}
