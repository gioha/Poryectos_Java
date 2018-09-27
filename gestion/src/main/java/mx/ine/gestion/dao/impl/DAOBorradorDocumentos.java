/**
 * DAOBorradorDocumentos.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;

import mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface;
import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;
import mx.ine.gestion.dto.helper.DTOMenuBandejaHelper;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que implementa los métodos que consultaran la tabla
 * "BORRADOR_DOCUMENTOS" del esquema "gestion4"
 * 
 * @author Homero Villanueva Dominguez
 * @since 05/08/2017
 */
@Scope("prototype")
@Repository("daoBorradorDocumentos")
public class DAOBorradorDocumentos extends DAOGenericGestion<DTOBorradorDocumentosEntity, DTOBorradorDocumentosID>
		implements DAOBorradorDocumentosInterface {

	@Autowired
	@Qualifier("daoDocumento")
	private DAODocumentoInterface daoDocumentosInterface;

	public DAOBorradorDocumentos() {
		super();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#
	 * consultarBorradoresPorUsuario(char)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBorradorDocumentosEntity> consutarBorradoresPorPersona(DTOBorradorDocumentosHelper helperBorradores) {

		//String sentenciaSQL = "query_consultar_borradores_ordenados_nombre";
		String sentenciaSQL = this.getContainer().getQuery("query_consultar_borradores");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>", helperBorradores.obtenerFiltros());
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",helperBorradores.obtenerOrdenamiento());

		Query query = getSession()
				.createSQLQuery(sentenciaSQL)

				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("conModificaciones", IntegerType.INSTANCE)
				.addScalar("conComentarios", IntegerType.INSTANCE)
				.addScalar("conFirma", IntegerType.INSTANCE)
				.addScalar("conValidacion", IntegerType.INSTANCE)
				.addScalar("numEnviadoFirma", IntegerType.INSTANCE)
				.addScalar("numEnviadoValidacion", IntegerType.INSTANCE)
				.addScalar("siglasEnviados", StringType.INSTANCE)
				.addScalar("fechaEnvio", DateType.INSTANCE)
				.addScalar("fechaRegreso", DateType.INSTANCE)
				.addScalar("noLeido", IntegerType.INSTANCE)
				.addScalar("conRechazo", IntegerType.INSTANCE)
				.addScalar("estatus", CharacterType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				
				// Columnas de la tabla de "DOCUMENTOS"
				.addScalar("idPersonaDoc", IntegerType.INSTANCE)
				.addScalar("idOficialiaDoc", IntegerType.INSTANCE)
				.addScalar("idAreaDoc", IntegerType.INSTANCE)
				.addScalar("tipoAreaDoc", IntegerType.INSTANCE)
				.addScalar("idTipoDocumento", IntegerType.INSTANCE)
				.addScalar("idAcronimoDoc", IntegerType.INSTANCE)
				.addScalar("numDocumento", StringType.INSTANCE)
				.addScalar("nombreDocumento", StringType.INSTANCE)
				.addScalar("estatusDoc", CharacterType.INSTANCE)
				.addScalar("firmadoDoc", IntegerType.INSTANCE)
				.addScalar("validadoDoc", IntegerType.INSTANCE)
				.addScalar("tipoCapturaDoc", IntegerType.INSTANCE)
				.addScalar("asuntoDoc", StringType.INSTANCE)
				.addScalar("procedenciaDocumento", CharacterType.INSTANCE)
				.addScalar("editadoDoc", IntegerType.INSTANCE)
				.addScalar("fechaCreacionDoc", DateType.INSTANCE)
				.addScalar("fechaRecepcionOfDoc", DateType.INSTANCE)
				.addScalar("contieneAnexosDoc", IntegerType.INSTANCE)
				.addScalar("idDocumentoRespuesta", IntegerType.INSTANCE)
				.addScalar("usuarioDoc", StringType.INSTANCE)
				.addScalar("fechaHoraDoc", DateType.INSTANCE)
				
				// Columnas de la tabla "C_AREAS"
				.addScalar("idEntidadArea", IntegerType.INSTANCE)
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE)
				
				;

		query.setResultTransformer(Transformers.aliasToBean(DTOBorradorDocumentosEntity.class));
		
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#activarComentario
	 * (java.lang.Integer)
	 */
	@Override
	public void activarComentario(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery("query_firma_activarComentario"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	@Override
	public void activarModificacion(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_validacion_activarModificacion"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	@Override
	public void activarValidacion(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_validacion_activarValidacion"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	@Override
	public void desactivarValidacion(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_validacion_desactivarValidacion"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	@Override
	public void restarValidacion(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_validacion_restarValidacion"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	@Override
	public void ponerContadorEnNull(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_validacion_poner_num_null"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
		
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#obtenerBorrador
	 * (java.lang.Integer)
	 */
	@Override
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento,
			Integer idPersona) {
		Criteria criteria = getSession().createCriteria(
				DTOBorradorDocumentosEntity.class);
		criteria.add(Restrictions.eq("idPersona", idPersona));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));

		return (DTOBorradorDocumentosEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#activarFirma(
	 * java.lang.Integer)
	 */
	@Override
	public void activarFirma(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery("query_firma_activarFirma"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#restarFirma(java
	 * .lang.Integer)
	 */
	@Override
	public void restarFirma(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery("query_firma_restarFirma"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#
	 * obtenerNumeroFirmasBorrador(java.lang.Integer)
	 */
	@Override
	public Integer obtenerNumeroFirmasBorrador(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(
				DTOBorradorDocumentosEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		DTOBorradorDocumentosEntity numeroFirmados = (DTOBorradorDocumentosEntity) criteria
				.uniqueResult();

		return numeroFirmados.getNumEnviadoFirma();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#
	 * eliminarPorIdDocumento(java.lang.Integer)
	 */
	@Override
	public void eliminarPorIdDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				getContainer().getQuery("query_firma_eliminarBorrador"));

		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#
	 * consultarNumeroBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public int consultarNumeroBorradores(
			DTOEstructuraAreasEntity dtoEstructuraAreasEntity) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_consultar_numero_borradores_porusuario"))
				.addScalar("numBorradores", IntegerType.INSTANCE);

		query.setInteger("idPersona", dtoEstructuraAreasEntity.getIdPersona());

		query.setResultTransformer(Transformers
				.aliasToBean(DTOMenuBandejaHelper.class));

		return ((DTOMenuBandejaHelper) query.uniqueResult()).getNumBorradores();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#obtenerContieneAnexos(java.lang.Integer)
	 */
	@Override
	public Integer obtenerContieneAnexos(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(
				DTODocumentoEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		DTODocumentoEntity numeroFirmados = (DTODocumentoEntity) criteria
				.uniqueResult();

		return numeroFirmados.getContieneAnexos();
	}
	
	@Override
	public void desactivarFirma(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_desactivarFirmaBorrador"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBorradorDocumentosInterface#registrarFechaRegreso(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void registrarFechaRegreso(Integer idDocumento, Integer numEnviado) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_registrarRegresoRemitente"));
		query.setInteger("idDocumento", idDocumento);
		//query.setInteger("numEnviadoFirma", numEnviado);
		query.setParameter("numEnviadoFirma", numEnviado, IntegerType.INSTANCE);
		query.setDate("fechaRegreso", new Date());
		query.executeUpdate();
	}
}
