/**
 * @(#)DAOBandejaERecibidos.java 27/11/2017
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

import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTOBandejaEntradaContadorHelper;
import mx.ine.gestion.dto.helper.DTOMenuBandejaHelper;
import mx.ine.gestion.util.Utilidades;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Scope("prototype")
@Repository("daoBandejaERecibidos")
public class DAOBandejaERecibidos extends DAOGenericGestion<DTOBandejaERecibidosEntity, DTOBandejaERecibidosID> implements DAOBandejaERecibidosInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOBandejaERecibidoInterface#insertarRecibidos
	 * (java.util.List)
	 */
	@Override
	public void insertarRecibidos(List<DTOEstructuraAreasEntity> listaPersonas, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario) {
		
		DTOBandejaERecibidosEntity recibido;
		for (DTOEstructuraAreasEntity persona : listaPersonas) {
			recibido = new DTOBandejaERecibidosEntity();
			recibido.setIdDocumento(documento.getIdDocumento());
			recibido.setIdPersona(persona.getIdPersona());
			recibido.setAnio(documento.getAnio());
			recibido.setIdArea(persona.getIdArea());
			recibido.setTipoArea(persona.getTipoArea());
//			recibido.setContieneAnexos(0);
			recibido.setFechaRecepcion(new Date());
			recibido.setUsuario(usuario.getCuentaLDAP());
			recibido.setFechaHora(new Date());
			recibido.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			recibido.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));
			recibido.setTieneRespuesta(0);

			guardar(recibido);
		}
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface#insertarRecibidos(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarRecibidos(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario, Integer idHistoricoRecep) {
		DTOBandejaERecibidosEntity recibido;
			recibido = new DTOBandejaERecibidosEntity();
			recibido.setIdDocumento(documento.getIdDocumento());
			recibido.setIdPersona(dtoEstructuraAreasEntity.getIdPersona());
			recibido.setAnio(documento.getAnio());
			recibido.setIdArea(dtoEstructuraAreasEntity.getIdArea());
			recibido.setTipoArea(dtoEstructuraAreasEntity.getTipoArea());
//			recibido.setContieneAnexos(0);
			recibido.setFechaRecepcion(new Date());
			recibido.setUsuario(usuario.getCuentaLDAP());
			recibido.setFechaHora(new Date());
			recibido.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			recibido.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));

			guardar(recibido);
	}

	/*
	 * (El comentario se encuentra en la interfaz dÃ³nde esta declarado el
	 * mÃ©todo)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBandejaERecibidoInterface#
	 * consultarContadorBandejaE(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int consultarDocumentosBandejaE(DTOEstructuraAreasEntity persona) {
		int contador = 0;
		List<DTOMenuBandejaHelper> miLista;
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_consultar_numero_bandeja_e_porusuario"))
				.addScalar("numRecibidos", IntegerType.INSTANCE);

		query.setInteger("idPersona", persona.getIdPersona());

		query.setResultTransformer(Transformers
				.aliasToBean(DTOMenuBandejaHelper.class));

		miLista = query.list();

		for (DTOMenuBandejaHelper elemento : miLista) {
			contador += elemento.getNumRecibidos();
		}
		return contador;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface#
	 * consultarNumeroRecibidos
	 * (mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity)
	 */
	@Override
	public int consultarNumeroRecibidos(
			DTOBandejaERecibidosHelper filtroRecibidoHelper) {
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_num_bandeja_e_recibidos");
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroRecibidoHelper.obtenerFiltro(false));
		Query query = getSession().createSQLQuery(sentenciaSQL).
				addScalar("totalRecibidos", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaEntradaContadorHelper.class));

		return ((DTOBandejaEntradaContadorHelper) query.uniqueResult())
				.getTotalRecibidos();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBandejaERecibidoInterface#
	 * obtenerListaRecibidosLazy
	 * (mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, int, int,
	 * java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaERecibidosEntity> obtenerListaRecibidosLazy(
			DTOBandejaERecibidosHelper filtroRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {

		//
		this.activaMatchModeWords();
		
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_bandeja_e_recibidos");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroRecibidoHelper.obtenerFiltro(true));
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",filtroRecibidoHelper.obtenerOrdenamiento());
		
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
				.aliasToBean(DTOBandejaERecibidosEntity.class));

		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOBandejaERecibidosEntity>) query.list();
	}

	

}