package mx.ine.acuerdos.dao.impl;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dto.DTOResponsables;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Scope("prototype")
@Repository("daoResponsables")
public class DAOResponsables extends DAOGeneric<DTOResponsables, Integer> implements DAOResponsablesInterface {
	
	private static final Log log = LogFactory.getLog(DAOCAreas.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception {

		List<DTOResponsables> resultado = new ArrayList<DTOResponsables>();

		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOResponsables.class);
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("estatus", 1));
		//criteria.add(Restrictions.eq("tipoResponsable",1));
		resultado = (List<DTOResponsables>) criteria.list();

		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DTOResponsables obtenerResponsableSegunLdap(String usuario) throws Exception {

		List<DTOResponsables> resultado = new ArrayList<DTOResponsables>();

		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOResponsables.class);
		criteria.add(Restrictions.eq("cuentaLdap", usuario));
		resultado = (List<DTOResponsables>) criteria.list();
		

		return resultado.get(0);
	}

	@Override
	public DTOResponsables obtenerResponsPorUsuario(String nomUsuario) throws Exception {

		DTOResponsables dtoResponsable;

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOResponsables.class);
		criteria.add(Restrictions.eq("cuentaLdap", nomUsuario));
		criteria.add(Restrictions.eq("estatus", new Integer(1)));
		dtoResponsable = (DTOResponsables) criteria.uniqueResult();

		return dtoResponsable;
	}
	
	@Override
	public DTOResponsables obtenerResponsPorUsuarioSinFiltro(String nomUsuario) throws Exception {
		DTOResponsables dtoResponsable;
		
		// Se crea una sesión para la base de datos
		Session session = getSession();
		
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOResponsables.class);
		criteria.add(Restrictions.eq("cuentaLdap", nomUsuario));
		dtoResponsable = (DTOResponsables) criteria.uniqueResult();
		
		return dtoResponsable;
	}
	
	protected Integer obtenUltimoIdResponsable() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOResponsables.class)
			    .setProjection(Projections.max("idResponsable"));
		return (Integer) criteria.uniqueResult();
	}

	@Override
	public void persisteResponsable(DTOResponsables responsable) throws Exception {
		if (responsable.getIdResponsable() == null) {
			Integer ultimoId = obtenUltimoIdResponsable();
			ultimoId = ultimoId == null ? 1 : ultimoId + 1;
			responsable.setIdResponsable(ultimoId);
		}
		responsable.setEstatus(1);
		guardarOactualizar(responsable);
	}

	@Override
	public DTOResponsables obtenerResponsablePorID(Integer idResponsable) throws Exception {

		DTOResponsables dtoResponsable;
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOResponsables.class);
		criteria.add(Restrictions.eq("idResponsable", idResponsable));
		dtoResponsable = (DTOResponsables) criteria.uniqueResult();

		return dtoResponsable;
	}

	@Override
	public List<DTOResponsables> encuentraResponsables(String nombre, String apellidos, String usuario, Integer idArea,
			Integer tipoResponsableBusqueda) throws Exception {
		List<DTOResponsables> responsables = new ArrayList<>();
		
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(DTOResponsables.class);
		
		criteria.add(Restrictions.eq("estatus", 1));
		
		if (nombre != null && !nombre.equals("")) {
			nombre = Normalizer.normalize(nombre, Normalizer.Form.NFD)
					.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
					.toUpperCase();
			criteria.add(Restrictions.like("nombre", "%" + nombre + "%"));
		}
		if (apellidos != null && !apellidos.equals("")) {
			apellidos = Normalizer.normalize(apellidos, Normalizer.Form.NFD)
					.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
					.toUpperCase();
			criteria.add(Restrictions.like("apellidos", "%" + apellidos + "%"));
		}
		if (usuario != null && !usuario.equals("")) {
			criteria.add(Restrictions.eq("cuentaLdap", usuario));
		}
		if (idArea != null && idArea > 0) {
			criteria.add(Restrictions.eq("idArea", idArea));
		}
		if (tipoResponsableBusqueda != null && tipoResponsableBusqueda > 0) {
			criteria.add(Restrictions.eq("tipoResponsable", tipoResponsableBusqueda));
		}
		
		criteria.addOrder(Order.asc("cuentaLdap"));
		
		responsables = (List<DTOResponsables>) criteria.list();
		
		return responsables;
	}

	@Override
	public void eliminaLogicamenteUsuario(DTOResponsables responsable) throws Exception {
		modificar(responsable);
	}

}
