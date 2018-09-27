/**
 * @(#)DAOTurnInstruccion.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOTurnInstruccionInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOTurnInstruccionEntity;
import mx.ine.gestion.dto.db.DTOTurnInstruccionID;
import mx.ine.gestion.dto.helper.DTOCargaTurnadoInstruHelper;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 *
 */
@Scope("prototype")
@Repository("DAOTurnInstruccion")
public class DAOTurnInstruccion extends DAOGenericGestion<DTOTurnInstruccionEntity, DTOTurnInstruccionID> implements DAOTurnInstruccionInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOTurnInstruccionInterface#obtenerInstruccionesRealizadas(mx.ine.gestion.dto.DTOUsuarioLogin, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCargaTurnadoInstruHelper> obtenerInstruccionesRealizadas(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_turninstruccion_consultar_instrucciones_turnadas"))

				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
				.addScalar("idPersonaTurno", IntegerType.INSTANCE)
				.addScalar("idInstruccion", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("idPersonaTurnada", IntegerType.INSTANCE)
				.addScalar("tipoInstruccion", IntegerType.INSTANCE)
				.addScalar("ordenamiento", IntegerType.INSTANCE)
				;
		
		query.setResultTransformer(Transformers.aliasToBean(DTOCargaTurnadoInstruHelper.class));
		
		query.setInteger("idDocumento", documento.getIdDocumento());
		query.setInteger("idPersonaTurno", dtoEstructuraAreasEntity.getIdPersona());
		query.setString("estatus","A");
		
		return query.list();
	}
}
