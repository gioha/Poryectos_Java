/**
 * @(#)DAOHBandejaERecibidos.java 10/01/2018
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

import mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosID;
import mx.ine.gestion.dto.helper.DTOBandejaEntradaContadorHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Scope("prototype")
@Repository("daoHBandejaERecibidos")
public class DAOHBandejaERecibidos extends
DAOGenericGestion<DTOHBandejaERecibidosEntity, DTOHBandejaERecibidosID>
implements DAOHBandejaERecibidosInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface#insertarHRecibidos(java.util.List, mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarHRecibidos(
			List<DTOEstructuraAreasEntity> listaPersonas,
			DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario) {
		
		DTOHBandejaERecibidosEntity recibido;
		for (DTOEstructuraAreasEntity persona : listaPersonas) {
			recibido = new DTOHBandejaERecibidosEntity();
			recibido.setIdDocumento(documento.getIdDocumento());
			recibido.setIdPersona(persona.getIdPersona());
			recibido.setAnio(documento.getAnio());
			recibido.setIdArea(persona.getIdArea());
			recibido.setTipoArea(persona.getTipoArea());
			recibido.setFechaRecepcion(new Date());
			recibido.setUsuario(usuario.getCuentaLDAP());
			recibido.setFechaHora(new Date());
			//recibido.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));

			guardar(recibido);
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface#consultarNumeroRecibidos(mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper)
	 */
	@Override
	public int consultarNumeroHRecibidos(
			DTOHBandejaERecibidosHelper filtroHRecibidoHelper) {
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_num_bandeja_e_hrecibidos");
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroHRecibidoHelper.obtenerFiltro(false));
		Query query = getSession().createSQLQuery(sentenciaSQL).
				addScalar("totalRecibidos", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaEntradaContadorHelper.class));

		return ((DTOBandejaEntradaContadorHelper) query.uniqueResult())
				.getTotalRecibidos();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface#obtenerListaRecibidosLazy(mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOHBandejaERecibidosEntity> obtenerListaRecibidosLazy(
			DTOHBandejaERecibidosHelper filtroHRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		//
		this.activaMatchModeWords();
		
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_bandeja_e_hrecibidos");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroHRecibidoHelper.obtenerFiltro(true));
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",filtroHRecibidoHelper.obtenerOrdenamiento());
		
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
				.addScalar("enAtencion", IntegerType.INSTANCE)
				.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
				.addScalar("tieneRespuesta", IntegerType.INSTANCE)

				// Columnas de la tabla de "DOCUMENTOS"
				.addScalar("idTipoDocumento", IntegerType.INSTANCE)
				.addScalar("idAcronimoDocumento", IntegerType.INSTANCE)
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
				.aliasToBean(DTOHBandejaERecibidosEntity.class));

		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOHBandejaERecibidosEntity>) query.list();
	}

}
