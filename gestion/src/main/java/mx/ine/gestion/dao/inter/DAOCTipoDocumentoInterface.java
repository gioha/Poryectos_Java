package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAOCTipoDocumentoInterface.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad Catalogo
 * de Tipo de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 06/09/2017
 */
@Scope("prototype")
@Repository("daoCTipoDocumento")
public interface DAOCTipoDocumentoInterface extends DAOGenericGestionInterface<DTOCTipoDocumentoEntity, Integer> {
	
	/**
	 * Método para consultar los tipos de área que sí tengan documentos;
	 * 
	 * @return List<DTOCTipoDocumentoEntity> lista con los tipos de documento con documentos
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/12/2017
	 */
	public List<DTOCTipoDocumentoEntity> consultarTipoDocumentoConDocumento();
	
}
