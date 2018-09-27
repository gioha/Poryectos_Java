/**
 * @(#)DAOApartadoNumDocInterface.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.DTOApartadoNumDocID;

/**
 * Interface que contiene la firma de los métodos que harán 
 * consultas a la tabla de APARTADO_NUM_DOC del esquema GESTION 4
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
public interface DAOApartadoNumDocInterface extends DAOGenericGestionInterface<DTOApartadoNumDocEntity, DTOApartadoNumDocID>{

	/**
	 * Método que obtiene el último registro que fue generado por el usuario.
	 * 
	 * @param usuario: Cadena que contiene el usuario del cual se quiere obtener el último registro capturado.
	 * @return DTOApartadoNumDocEntity: registro encontrado con la información solicitada.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public DTOApartadoNumDocEntity consultarUltimoRegistroUsuario(String usuario);

	/**
	 * Método que inserta en la tabla APARTADO_NUM_DOC aumentando el ID_NUMERO_DOCUMENTO en base al id_area, tipo_area, id_tipo_documento y id_acronimo (secuencia logica)
	 * 
	 * @param tipoArea: Contiene el tipo de área a la que se le aparta el folio.
	 * @param idArea: Contiene el idenficador del área a la que se le aparta el folio.
	 * @param idTipoDocumento: Contiene el identificador del tipo de documento al que se le aparta el folio.
	 * @param idAcronimo: Contiene el identificador del acronimo al que se le aparta el folio. 
	 * @param tipoApartado: F - para documentos físicos y E - para documentos electronicos
	 * @return boolean: nos indica si se inserto correctamente el registro o no
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public boolean capturarApartadoFolios(Integer tipoArea, Integer idArea, Integer idTipoDocumento, Integer idAcronimo, String tipoApartado);

	/**
	 * Método que busca si hay acronimos utilizados en la tabla de documentos.
	 * 
	 * @param acronimo: Objeto que contiene la información del acronimo.
	 * @return DTODocumentoEntity: Registro/Objeto encontrado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public DTOApartadoNumDocEntity consultarAcronimoEnUso(DTOAcronimoEntity acronimo);
}
