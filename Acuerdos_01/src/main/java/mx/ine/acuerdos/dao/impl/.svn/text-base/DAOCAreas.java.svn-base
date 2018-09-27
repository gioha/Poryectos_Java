package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCAreasInterface;
import mx.ine.acuerdos.dto.DTOCAreas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCAreas")
public class DAOCAreas extends DAOGeneric<DTOCAreas, Integer> implements DAOCAreasInterface {
	private static final Log log = LogFactory.getLog(DAOCAreas.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreas> consultaCatalogoAreas(int tipoArea) throws Exception {
		List<DTOCAreas> resultado = new ArrayList<>();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOCAreas.class);
		criteria.add(Restrictions.eq("id.tipoArea", tipoArea));
		criteria.addOrder(Order.asc("descripcion"));
		resultado = (ArrayList<DTOCAreas>) criteria.list();
		return resultado;
	}

	@Override
	public DTOCAreas obtenerArea(Integer idArea) throws Exception {

		DTOCAreas dtoArea;

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCAreas.class);
		criteria.add(Restrictions.eq("id.idArea", idArea));
		dtoArea = (DTOCAreas) criteria.uniqueResult();

		return dtoArea;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreas> obtenerTodosAreas() throws Exception {
		//restriccion area tipo 1 solo lo visualizan 
		List<DTOCAreas> resultado = new ArrayList<>();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOCAreas.class);
				 criteria.add(Restrictions.eq("id.tipoArea", 1));
				 criteria.addOrder(Order.asc("descripcion"));
		resultado = (ArrayList<DTOCAreas>) criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreas> obtenerAreas() throws Exception {

		List<DTOCAreas> resultado = new ArrayList<DTOCAreas>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCAreas.class);
		resultado = (ArrayList<DTOCAreas>) criteria.list();

		return resultado;
	}

	@Override
	public String buscaPorDescripcion(String descripcion) throws Exception {

		// Se crea una sesión para la base de datos
		Session session = getSession();
		
		SQLQuery query = session.createSQLQuery(this.getContainer().getQuery("query.compara.area.con.ldap"));
		query.setParameter("descTexto", descripcion);
		List<Object[]> resultado = (List<Object[]>) query.list();
		
		if (resultado.size() == 1) {
			// Regresamos el id del área.
			return resultado.get(0)[0].toString();
		}
		
		return null;
	}
	
	@Override
	public List<DTOCAreas> obtenAreasResponsablesRelacionados(int tipoArea) throws Exception {
		List<DTOCAreas> areas = new ArrayList<DTOCAreas>();
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(this.getContainer().getQuery("query.areas.responsables.relacionados"));
		query.addEntity(DTOCAreas.class);
		query.setString("tipoArea", String.valueOf(tipoArea));
		areas = (ArrayList<DTOCAreas>) query.list();
		return areas;
	}

}
