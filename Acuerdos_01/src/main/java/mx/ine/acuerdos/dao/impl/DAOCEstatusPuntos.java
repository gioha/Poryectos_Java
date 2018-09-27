package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCEstatusPuntosInterface;
import mx.ine.acuerdos.dto.DTOCEstatusPuntos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Scope("prototype")
@Repository("daoEstatusPuntos")
public class DAOCEstatusPuntos extends DAOGeneric<DTOCEstatusPuntos, Integer> implements DAOCEstatusPuntosInterface {
	private static final Log log = LogFactory.getLog(DAOCEstatusPuntos.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCEstatusPuntos> obtenerEstatusPuntos() throws Exception {

		List<DTOCEstatusPuntos> resultado = new ArrayList<DTOCEstatusPuntos>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCEstatusPuntos.class);
		resultado = (ArrayList<DTOCEstatusPuntos>) criteria.list();

		return resultado;
	}

}
