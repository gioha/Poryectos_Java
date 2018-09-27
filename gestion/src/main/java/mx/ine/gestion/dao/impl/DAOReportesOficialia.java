/**
 * @(#)DAOReportesOficialia.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOReportesOficialiaInterface;
import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.helper.DTOReportesOficialia;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.hibernate.type.DateType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase DAO que genera los querys utilizados en la vista de Reportes de Oficialía
 * que no pueden ser DTO's extraídos desde una tabla en BD (sino de varias tablas)
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
@Repository("daoReportesOficialia")
@Scope("prototype")
public class DAOReportesOficialia extends DAOGenericGestion<Integer, Integer> implements DAOReportesOficialiaInterface {

	/**
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOReportesOficialiaInterface#consultarReporteOficialia()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOReportesOficialia> consultarReporteOficialia(DTOReportesOficialiaHelper filtrosReportesOf) {
		String postQuerySQL = "";
		String clausWhere;

		// 1. Se genera un pre-query, el cual sólo contiene las columnas a recuperar, las tablas de origen y sus respectivos join
		String preQuerySQL = this.getContainer().getQuery("query_reporte_oficialia");

		// 2. Se construyen las cláusulas where de acuerdo a la información introducida y/o seleccionada por el usuario
		if(!filtrosReportesOf.getNumeroDocumento().isEmpty()) {
			postQuerySQL += " DOCUMENTOS.NUM_DOCUMENTO LIKE '%' || :numDocumento || '%' AND";
		}
		if(!filtrosReportesOf.getIdTipoDocumento().equals(0)) {
			postQuerySQL += " DOCUMENTOS.ID_TIPO_DOCUMENTO = :idTipoDocumento AND";
		}
		if(!filtrosReportesOf.getIdSeccion().equals(0)) {
			postQuerySQL += " C_SECCIONES.ID_SECCION = :idSeccion AND";
		}
		if(!filtrosReportesOf.getIdCategoria().equals(0)) {
			postQuerySQL += " C_CATEGORIAS.ID_CATEGORIA = :idCategoria AND";
		}
		if(!filtrosReportesOf.getConFiltroFolios() && !filtrosReportesOf.getFolioOficialia().isEmpty()) {
			postQuerySQL += " APARTADOS_NUM_DOC_OF.FOLIO_OFICIALIA LIKE '%' || :folioOficialia || '%' AND";
		}
		if(filtrosReportesOf.getConFiltroFolios() && !filtrosReportesOf.getAcronimoFolio().isEmpty() &&
		  !filtrosReportesOf.getFolioA().equals(0) && !filtrosReportesOf.getFolioDe().equals(0) &&
		  !filtrosReportesOf.getAnioFolio().equals(0)) {
			postQuerySQL += " APARTADOS_NUM_DOC_OF.FOLIO_OFICIALIA LIKE '%' || :acronimoFolio || '%' AND substr(APARTADOS_NUM_DOC_OF.FOLIO_OFICIALIA, LENGTH(APARTADOS_NUM_DOC_OF.FOLIO_OFICIALIA)-9, 5) BETWEEN :folioA AND :folioDe AND APARTADOS_NUM_DOC_OF.ANIO = :anioFolio AND";
		}
		if(!filtrosReportesOf.getAsunto().isEmpty()) {
			postQuerySQL += " DOCUMENTOS.ASUNTO LIKE '%' || :asunto || '%' AND";
		}
		if(filtrosReportesOf.getFechaInicial() != null && filtrosReportesOf.getFechaFinal() == null) {
			postQuerySQL += " DOCUMENTOS.FECHA_CREACION >= :fechaInicial AND";
		}
		if(filtrosReportesOf.getFechaInicial() == null && filtrosReportesOf.getFechaFinal() != null) {
			postQuerySQL += " DOCUMENTOS.FECHA_CREACION <= :fechaFinal AND";
		}	
		if(filtrosReportesOf.getFechaInicial() != null && filtrosReportesOf.getFechaFinal() != null) {
			postQuerySQL += " DOCUMENTOS.FECHA_CREACION BETWEEN :fechaInicial AND :fechaFinal AND";
		}

		clausWhere = "";
		for(DTOEstructuraAreasEntity remitente : filtrosReportesOf.getRemitentes()) {
			clausWhere += remitente.getIdPersona() + ",";
		}
		if(!clausWhere.isEmpty()) {
			postQuerySQL += " DOCUMENTOS_REMITENTES.ID_PERSONA_REMITENTE IN (" + clausWhere.substring(0, clausWhere.length()-1) + ") AND";
		}

		clausWhere = "";
		for(DTORemitentesExternosOfEntity remitenteExt : filtrosReportesOf.getRemitentesExtAgregados()) {
			clausWhere += remitenteExt.getIdRemitente() + ",";
		}
		if(!clausWhere.isEmpty()) {
			postQuerySQL += " DOCUMENTOS_REMITENTES_EXT.ID_REMITENTE IN (" + clausWhere.substring(0, clausWhere.length()-1) + ") AND";
		}

		clausWhere = "";
		for(DTOEstructuraAreasEntity destinatario : filtrosReportesOf.getDestinatarios()) {
			clausWhere += destinatario.getIdPersona() + ",";
		}
		if(!clausWhere.isEmpty()) {
			postQuerySQL += " DOCUMENTOS_DESTINATARIOS.ID_PERSONA_DESTINATARIA IN (" + clausWhere.substring(0, clausWhere.length()-1) + ") AND";
		}

		if(filtrosReportesOf.getCopiasConocimiento().equals("Si")) {
			postQuerySQL += " DOCUMENTOS_CCP.ID_PERSONA IS NOT NULL AND";
		}
		if(filtrosReportesOf.getCopiasConocimiento().equals("No")) {
			postQuerySQL += " DOCUMENTOS_CCP.ID_PERSONA IS NULL AND";
		}

		if(!postQuerySQL.isEmpty()) {
			postQuerySQL = " where"  + postQuerySQL.substring(0, postQuerySQL.length()-4);
		}

		// 3. Se genera la cláusula order by de acuerdo al atributo seleccionado por el usuario
		if(filtrosReportesOf.getParametroOrden().equals("Asunto")) {
			postQuerySQL += " order by DOCUMENTOS.ASUNTO";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Categoría")) {
			postQuerySQL += " order by C_CATEGORIAS.DESCRIPCION";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Fecha de registro")) {
			postQuerySQL += " order by DOCUMENTOS.FECHA_CREACION";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Folio de oficialía")) {
			postQuerySQL += " order by APARTADOS_NUM_DOC_OF.FOLIO_OFICIALIA";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Número de documento")) {
			postQuerySQL += " order by DOCUMENTOS.NUM_DOCUMENTO";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Sección")) {
			postQuerySQL += " order by C_SECCIONES.DESCRIPCION";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Tipo de documento")) {
			postQuerySQL += " order by C_TIPO_DOCUMENTOS.DESCRIPCION";
		}		
		if(filtrosReportesOf.getParametroOrden().equals("Tipo de área")) {
			postQuerySQL += " order by C_TIPO_AREAS.DESCRIPCION";
		}

		// 4. El query completo (unión de pre y post) es creado con Hibernate
		Query query = getSession().createSQLQuery(preQuerySQL + postQuerySQL)
					 .addScalar("folioOficialia", StringType.INSTANCE)
					 .addScalar("numDocumento", StringType.INSTANCE)
					 .addScalar("asunto", StringType.INSTANCE)
					 .addScalar("personaCC", StringType.INSTANCE)
					 .addScalar("fechaCreacion", DateType.INSTANCE)
					 .addScalar("tipoDocumento", StringType.INSTANCE)
					 .addScalar("descSeccion", StringType.INSTANCE)
					 .addScalar("descCategoria", StringType.INSTANCE)
					 .addScalar("descArea", StringType.INSTANCE)
					 .addScalar("tipoArea", StringType.INSTANCE)
					 .addScalar("personaRemitente", StringType.INSTANCE)
					 .addScalar("personaRemitenteExt", StringType.INSTANCE)
					 .addScalar("personaDestinataria", StringType.INSTANCE);

		// 5. Se proporcionan los parámetros necesarios para una correcta ejecución del query
		if(postQuerySQL.contains(":numDocumento")) {
			query.setParameter("numDocumento", filtrosReportesOf.getNumeroDocumento());
		}
		if(postQuerySQL.contains(":idTipoDocumento")) {
			query.setParameter("idTipoDocumento", filtrosReportesOf.getIdTipoDocumento());
		}
		if(postQuerySQL.contains(":idSeccion")) {
			query.setParameter("idSeccion", filtrosReportesOf.getIdSeccion());
		}
		if(postQuerySQL.contains(":idCategoria")) {
			query.setParameter("idCategoria", filtrosReportesOf.getIdCategoria());
		}
		if(postQuerySQL.contains(":folioOficialia")) {
			query.setParameter("folioOficialia", filtrosReportesOf.getFolioOficialia());
		}
		if(postQuerySQL.contains(":asunto")) {
			query.setParameter("asunto", filtrosReportesOf.getAsunto());
		}
		if(postQuerySQL.contains(":fechaInicial")) {
			query.setParameter("fechaInicial", filtrosReportesOf.getFechaInicial());
		}
		if(postQuerySQL.contains(":fechaFinal")) {
			query.setParameter("fechaFinal", filtrosReportesOf.getFechaFinal());
		}
		if(postQuerySQL.contains(":acronimoFolio")) {
			query.setParameter("acronimoFolio", filtrosReportesOf.getAcronimoFolio());
			query.setParameter("folioA", filtrosReportesOf.getFolioA());
			query.setParameter("folioDe", filtrosReportesOf.getFolioDe());
			query.setParameter("anioFolio", filtrosReportesOf.getAnioFolio());
		}

		// 6. Se mapea la tabla resultante al DTOReportesOficialia
		query.setResultTransformer(Transformers.aliasToBean(DTOReportesOficialia.class));

		// 7. La lista de documentos resultante es devuelta al método llamante
		return (List<DTOReportesOficialia>) query.list();
	}

}
