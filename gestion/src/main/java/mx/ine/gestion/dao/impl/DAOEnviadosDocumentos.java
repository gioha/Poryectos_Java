/**
 * @(#)DAODatosFirmaDoc.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosID;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;
import mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper;
import mx.ine.gestion.dto.helper.DTOMenuBandejaHelper;
import mx.ine.gestion.util.Utilidades;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoEnviadosDocumentos")
public class DAOEnviadosDocumentos extends DAOGenericGestion<DTOEnviadosDocumentosEntity, DTOEnviadosDocumentosID> implements DAOEnviadosDocumentosInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface#consultarEnviados
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEnviadosDocumentosEntity> consultarEnviados(DTOEnviadosDocumentosHelper helperEnviados) {

		//String sentenciaSQL = "query_consultar_borradores_ordenados_nombre";
		String sentenciaSQL = this.getContainer().getQuery("query_consultar_enviados");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>", helperEnviados.obtenerFiltros());
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",helperEnviados.obtenerOrdenamiento());

		Query query = getSession()
				.createSQLQuery(sentenciaSQL)

				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("usuarioEnvio", StringType.INSTANCE)
				.addScalar("numEnviadoFirma", IntegerType.INSTANCE)
				.addScalar("numEnviadoValidacion", IntegerType.INSTANCE)
				.addScalar("noLeido", IntegerType.INSTANCE)
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

		query.setResultTransformer(Transformers.aliasToBean(DTOEnviadosDocumentosEntity.class));
		
		return query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface#
	 * consultarNumeroEnviados()
	 */
	@Override
	public int consultarNumeroEnviados(
			DTOEstructuraAreasEntity dtoEstructuraAreasEntity) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_consultar_numero_enviados_porusuario"))
				.addScalar("numEnviados", IntegerType.INSTANCE);

		query.setInteger("idPersona", dtoEstructuraAreasEntity.getIdPersona());

		query.setResultTransformer(Transformers
				.aliasToBean(DTOMenuBandejaHelper.class));

		return ((DTOMenuBandejaHelper) query.uniqueResult()).getNumEnviados();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface#insertarEnviado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void insertarEnviado(DTOBorradorDocumentosEntity borrador,
			DTOEstructuraAreasEntity persona) {
		
		DTOEnviadosDocumentosEntity enviado = new DTOEnviadosDocumentosEntity();
		
		enviado.setIdDocumento(borrador.getIdDocumento());
		enviado.setAnio(borrador.getAnio());
		enviado.setIdPersona(persona.getIdPersona());
		enviado.setUsuarioEnvio(persona.getCuentaLDAP());
//		enviado.setContieneAnexos(borrador.getDocumento().getContieneAnexos());
		enviado.setIdArea(borrador.getIdArea());
		enviado.setTipoArea(borrador.getTipoArea());
		enviado.setNumEnviadoFirma(borrador.getNumEnviadoFirma());
		enviado.setNumEnviadoValidacion(borrador.getNumEnviadoValidacion());
		enviado.setEstatus('A');
		enviado.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")) );
		
		guardar(enviado);
		
	}
}
