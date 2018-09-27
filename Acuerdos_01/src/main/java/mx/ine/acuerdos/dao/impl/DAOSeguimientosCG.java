package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dao.DAOSeguimientosCGInterface;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoSeguimientosCG")
public class DAOSeguimientosCG extends DAOGeneric<DTOSeguimientosCG, Integer> implements DAOSeguimientosCGInterface{

	private static final Log log = LogFactory.getLog(DAOSeguimientosCG.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOSeguimientosCG> consultaSeguimiento(DTOPuntosAcuerdo punto) throws Exception {
		
		List<DTOSeguimientosCG> resultado = new ArrayList<DTOSeguimientosCG>();
		
		// creamos una sesion a la BD
		Session session = getSession();
		
		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		
		criteria.add(Restrictions.eq("id.idNumAcuerdo",punto.getId().getIdNumAcuerdo()));
		criteria.add(Restrictions.eq("id.numeralia",punto.getId().getNumeralia()));
		criteria.addOrder(Order.desc("id.fechaMovimiento"));

		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOSeguimientosCG>) criteria.list();
		
		// retornamos el resultado
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOSeguimientosCG> obtenerSeguimientoPorArea(String idNumAcuerdo, Integer numeralia, Integer idArea) throws Exception {

		List<DTOSeguimientosCG> resultado = new ArrayList<DTOSeguimientosCG>();

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo",idNumAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia",numeralia));
		criteria.add(Restrictions.eq("id.idArea",idArea));
		criteria.addOrder(Order.desc("id.fechaMovimiento"));

		resultado = (ArrayList<DTOSeguimientosCG>) criteria.list();

		return resultado;
	}

	@Override
	public DTOSeguimientosCG obtenerSeguimientoPorUsuario(String idNumAcuerdo, Integer numeralia, String nomUsuario) throws Exception {

		DTOSeguimientosCG dtoSeguimiento;

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo",idNumAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia",numeralia));
		criteria.add(Restrictions.eq("usuario",nomUsuario));

//		***** Status->Por comprobar->Un mismo usuario pudo haber realizado varios movimientos*****
		dtoSeguimiento = (DTOSeguimientosCG) criteria.list().get(0);

		return dtoSeguimiento;
	}

	public void insertarMovimiento(DTOSeguimientosCG seguimiento) {

		seguimiento.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		seguimiento.setFechaHora(new Date());

		try {
			getSession().saveOrUpdate(seguimiento);
		} catch (Exception e) {
			log.error("DAOSeguimientosCG.insertarMovimiento :" + e.getMessage());			
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception {

		List<DTOSeguimientosCG> resultado = new ArrayList<DTOSeguimientosCG>();

		// Se crea una sesión para la base de datos
		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo",idAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia",numeralia));		
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.eq("id.idEstatusPunto",1));
			or.add(Restrictions.eq("id.idEstatusPunto",4));
		criteria.add(or);
		criteria.addOrder(Order.asc("responsabilidadConjunta"));
		resultado = (ArrayList<DTOSeguimientosCG>) criteria.list();

		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOSeguimientosCG> obtenerSeguimiento(String idAcuerdo, Integer numeralia, Integer idArea) throws Exception {

		List<DTOSeguimientosCG> resultado = new ArrayList<DTOSeguimientosCG>();

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo",idAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia",numeralia));
		criteria.add(Restrictions.eq("id.idArea",idArea));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		resultado = (ArrayList<DTOSeguimientosCG>) criteria.list();

		return resultado;
	}

	public void eliminarSeguimientoArea(DTOSeguimientosCG movSeguimiento) throws Exception {
		eliminar(movSeguimiento);
	}
	
	public void modificaSeguimientoAreaGrupo(String idAcuerdo, Integer numeralia, Integer idArea, Integer grupo)throws Exception {
		
		List<DTOSeguimientosCG> resultado = new ArrayList<DTOSeguimientosCG>();

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo",idAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia",numeralia));
		criteria.add(Restrictions.eq("id.idArea",idArea));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		resultado = (ArrayList<DTOSeguimientosCG>) criteria.list();
		
		for (DTOSeguimientosCG seguimiento : resultado) {
			seguimiento.setResponsabilidadConjunta(grupo);
			
			seguimiento.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
			seguimiento.setFechaHora(new Date());
			
			getSession().saveOrUpdate(seguimiento);			
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOSeguimientosCG> obtenerRespConjunta(String idNumAcuerdo, Integer numeralia, Integer respConj) throws Exception {

		List<DTOSeguimientosCG> resultado = new ArrayList<DTOSeguimientosCG>();

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOSeguimientosCG.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo",idNumAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia",numeralia));
		criteria.add(Restrictions.eq("responsabilidadConjunta",respConj));
		criteria.addOrder(Order.desc("id.fechaMovimiento"));

		resultado = (ArrayList<DTOSeguimientosCG>) criteria.list();

		return resultado;
	}

}
