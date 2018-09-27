/**
 * @(#)DAODocumentosClasifArea.java 10/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import mx.ine.gestion.dao.inter.DAODocumentoFoliosInterface;
import mx.ine.gestion.dto.db.DTODocumentoFoliosEntity;
import mx.ine.gestion.dto.db.DTODocumentoFoliosID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a la tabla de DOCUMENTOS_CLASIF_AREA
 * de el esquema GESTION 4
 * 
 * @author David Rodríguez Corral
 * @since 10/12/2017
 */
@Scope("prototype")
@Repository("daoDocumentoFolios")
public class DAODocumentoFolios extends DAOGenericGestion<DTODocumentoFoliosEntity, DTODocumentoFoliosID> implements DAODocumentoFoliosInterface {

}
