/**
 * @(#)DAOBandejaECPP.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.primefaces.model.SortOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.gestion.dao.inter.DAOBandejaECCPInterface;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaECCPHelper;
import mx.ine.gestion.dto.helper.DTOBandejaEntradaContadorHelper;
import mx.ine.gestion.util.Utilidades;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Scope("prototype")
@Repository("daoBandejaECCP")
public class DAOBandejaECCP extends
		DAOGenericGestion<DTOBandejaECCPEntity, DTOBandejaECCPID> implements
		DAOBandejaECCPInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaECCPInterface#insertarCCP(java.util.List, mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarCCP(List<DTOEstructuraAreasEntity> listaPersonas, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario) {
		DTOBandejaECCPEntity ccp;
		for (DTOEstructuraAreasEntity persona : listaPersonas) {
			ccp = new DTOBandejaECCPEntity();
			ccp.setIdDocumento(documento.getIdDocumento());
			ccp.setIdPersona(persona.getIdPersona());
			ccp.setAnio(documento.getAnio());
			ccp.setIdArea(persona.getIdArea());
			ccp.setTipoArea(persona.getTipoArea());
//			ccp.setContieneAnexos(0);
			ccp.setFechaRecepcion(new Date());
			ccp.setUsuario(usuario.getCuentaLDAP());
			ccp.setFechaHora(new Date());
			ccp.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));

			guardar(ccp);
		}
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaECCPInterface#insertarCCP(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarCCP(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario) {
		DTOBandejaECCPEntity ccp;
			ccp = new DTOBandejaECCPEntity();
			ccp.setIdDocumento(documento.getIdDocumento());
			ccp.setIdPersona(dtoEstructuraAreasEntity.getIdPersona());
			ccp.setAnio(documento.getAnio());
			ccp.setIdArea(dtoEstructuraAreasEntity.getIdArea());
			ccp.setTipoArea(dtoEstructuraAreasEntity.getTipoArea());
//			ccp.setContieneAnexos(0);
			ccp.setFechaRecepcion(new Date());
			ccp.setUsuario(usuario.getCuentaLDAP());
			ccp.setFechaHora(new Date());
			ccp.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));

			guardar(ccp);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaECCPInterface#consultarNumeroRecibidos(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity)
	 */
	@Override
	public int consultarNumeroCCP(DTOBandejaECCPHelper filtroCCPHelper) {
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_num_bandeja_e_ccp");
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroCCPHelper.obtenerFiltro(false));
		Query query = getSession().createSQLQuery(sentenciaSQL).
				addScalar("totalCCP", IntegerType.INSTANCE);
		
		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaEntradaContadorHelper.class));

		return ((DTOBandejaEntradaContadorHelper) query.uniqueResult())
				.getTotalCCP();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaECCPInterface#obtenerListaRecibidosLazy(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaECCPEntity> obtenerListaCCPLazy(
			DTOBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		//
		this.activaMatchModeWords();
		
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_bandeja_e_ccp");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroCCPHelper.obtenerFiltro(true));
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",filtroCCPHelper.obtenerOrdenamiento());
		
		Query query = getSession()
				.createSQLQuery(sentenciaSQL)
				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("fechaRecepcion", DateType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				.addScalar("noLeido", IntegerType.INSTANCE)

				// Columnas de la tabla de "DOCUMENTOS"
				.addScalar("idTipoDocumento", IntegerType.INSTANCE)
				.addScalar("idAcronimoDocumento", IntegerType.INSTANCE)
//				.addScalar("secNumDocDocumento", IntegerType.INSTANCE)
				.addScalar("numDocumento", StringType.INSTANCE)
				.addScalar("nombreDocumento", StringType.INSTANCE)
				.addScalar("estatusDocumento", CharacterType.INSTANCE)
				.addScalar("tipoCaptura", IntegerType.INSTANCE)
				.addScalar("asunto", StringType.INSTANCE)
				.addScalar("fechaCreacion", DateType.INSTANCE)
				.addScalar("contieneAnexos", IntegerType.INSTANCE)

				// Columnas de la tabla "C_AREAS"
				.addScalar("idAreaDocumento", IntegerType.INSTANCE)
				.addScalar("tipoAreaDocumento", IntegerType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE)
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaECCPEntity.class));

		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOBandejaECCPEntity>) query.list();
	}

	
}