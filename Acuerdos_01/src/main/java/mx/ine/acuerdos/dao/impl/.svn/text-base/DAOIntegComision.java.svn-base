package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOIntegComisionInterface;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoIntegComision")
public class DAOIntegComision extends DAOGeneric<DTOIntegrantesComision, Integer> implements DAOIntegComisionInterface {
	private static final Log log = LogFactory.getLog(DAOIntegComision.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOIntegrantesComision> obtenerIntegsComision(Integer idComision) throws Exception {

		List<DTOIntegrantesComision> resultado = new ArrayList<DTOIntegrantesComision>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOIntegrantesComision.class);
		criteria.add(Restrictions.eq("id.idComision", idComision));
		resultado = (ArrayList<DTOIntegrantesComision>) criteria.list();

		return resultado;
	}

	@Override
	public DTOIntegrantesComision obtenerIntegComision(Integer idResponsable) throws Exception {

		DTOIntegrantesComision dtoIntegComision;

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOIntegrantesComision.class);
		criteria.add(Restrictions.eq("id.idResponsable", idResponsable));
		dtoIntegComision = (DTOIntegrantesComision) criteria.uniqueResult();

		return dtoIntegComision;
	}

}
