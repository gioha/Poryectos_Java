package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCCargosInterface;
import mx.ine.acuerdos.dto.DTOCCargos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Scope("prototype")
@Repository("daoCCargos")
public class DAOCCargos extends DAOGeneric<DTOCCargos, Integer> implements DAOCCargosInterface {
	private static final Log log = LogFactory.getLog(DAOCCargos.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCCargos> obtenerCargos() throws Exception {

		List<DTOCCargos> resultado = new ArrayList<DTOCCargos>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCCargos.class);
		resultado = (ArrayList<DTOCCargos>) criteria.list();

		return resultado;
	}

	@Override
	public DTOCCargos obtenerCargo(Integer idCargo) throws Exception {

		DTOCCargos dtoCargo;

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCCargos.class);
		criteria.add(Restrictions.eq("idCargo", idCargo));
		dtoCargo = (DTOCCargos) criteria.uniqueResult();

		return dtoCargo;
	}
}
