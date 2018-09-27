package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOCorresponsalInterface;
import mx.ine.gestion.dto.db.DTOCorresponsales;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de configuración/corresponsales.
 * 
 * @author Luis Urrutia
 * @since 17/11/2017
 */
@Repository("daoCorresponsal")
@Scope("prototype")
public class DAOCorresponsal extends DAOGenericGestion<DTOCorresponsales, Integer> implements DAOCorresponsalInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCapturaCorresponsalInterface#buscarCorresponsales(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> buscarCorresponsales(Integer idPersona){
		
		//CUIDADO - Este método esta siendo ocupado por más de un módulo
		
		String queryFinal = this.getContainer().getQuery("query_consultar_corresponsales");
		
		Query query = getSession().createSQLQuery(queryFinal)
				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("apellidos", StringType.INSTANCE)
				.addScalar("puesto", StringType.INSTANCE)
				.addScalar("genero", StringType.INSTANCE)
				.addScalar("cuentaLDAP", StringType.INSTANCE)
				.addScalar("extensionTel", StringType.INSTANCE)
				.addScalar("tratamiento", StringType.INSTANCE)
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("idCorresponsal", IntegerType.INSTANCE)
				.addScalar("fechaInicio", DateType.INSTANCE)
				.addScalar("fechaFin", DateType.INSTANCE)
				.addScalar("periodoVencido", BooleanType.INSTANCE);
				
		query.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));

		query.setInteger("idPersona", idPersona);

		return query.list();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCapturaCorresponsalInterface#eliminarCorresponsal(java.lang.Integer)
	 */
	@Override
	public void eliminarCorresponsal(Integer idCorresponsal){
		
		Query query = getSession().createSQLQuery(getContainer().getQuery("query_eliminar_corresponsal"));

		query.setInteger("idCorresponsal", idCorresponsal);
		query.executeUpdate();
	}
	
	@Override
	public void actualizarEstatusCorresponsales(Integer idPersona) {
		
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_estructura_actualizar_corresponsales"));
		query.setInteger("idPersona", idPersona);
		query.executeUpdate();
	}

	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCapturaCorresponsalInterface#buscarCorresponsalesCandidatos(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> buscarCorresponsalesCandidatos(String nombre, String apellidos,
			Integer idPersona, Integer idArea, Integer tipoArea){
		
		this.activaMatchModeWords();
		String condicionNombre="";
		String condicionApellidos="";
		String queryFinal = "";
		
		
		if (!(nombre == null || nombre.trim().equals("")))
			condicionNombre = "ea.nombre LIKE '%"+nombre.toUpperCase()+"%' AND ";
		if (!(apellidos == null || apellidos.trim().equals("")))
			condicionApellidos = "ea.apellidos LIKE '%"+apellidos.toUpperCase()+"%' AND ";
			
		queryFinal = this.getContainer().getQuery("query_consultar_corresponsales_candidatos")
				.replace("<condicionNombre>", condicionNombre)
				.replace("<condicionApellidos>", condicionApellidos);
		
		Query query = getSession()
				.createSQLQuery(queryFinal)

				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("apellidos", StringType.INSTANCE)
				.addScalar("puesto", StringType.INSTANCE)
				.addScalar("tratamiento", StringType.INSTANCE)
				.addScalar("descripcionArea", StringType.INSTANCE);
				
		query.setResultTransformer(Transformers
				.aliasToBean(DTOEstructuraAreasEntity.class));
		
		query.setInteger("idPersona", idPersona);
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);

		return query.list();
	}
	
}
