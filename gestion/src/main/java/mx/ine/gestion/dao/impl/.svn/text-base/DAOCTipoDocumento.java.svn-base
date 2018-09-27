package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOCTipoDocumentoInterface;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAOCTipoDocumento.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de dao para la entidad de TipoDocumento.
 * 
 * @author Sergio Ley Garcia
 * @since 06/09/2017
 */
@Scope("prototype")
@Repository("daoCTipoDocumento")
public class DAOCTipoDocumento extends DAOGenericGestion<DTOCTipoDocumentoEntity, Integer> implements DAOCTipoDocumentoInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCTipoDocumentoInterface#consultarTipoDocumentoConDocumento()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCTipoDocumentoEntity> consultarTipoDocumentoConDocumento() {
		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_consultar_tipo_documento_con_documentos"))
			.addScalar("idTipoDocumento", IntegerType.INSTANCE)
			.addScalar("descripcion", StringType.INSTANCE);

			query.setResultTransformer(Transformers.aliasToBean(DTOCTipoDocumentoEntity.class));
			query.executeUpdate();
			return (List<DTOCTipoDocumentoEntity>)query.list();
	}
	
}
