package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCNegociosInterface;
import mx.ine.acuerdos.dto.DTOCNegocios;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCNegocios")
public class DAOCNegocios extends DAOGeneric<DTOCNegocios, Integer> implements DAOCNegociosInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCNegocios> consultarNegocios() throws Exception {

		List<DTOCNegocios> resultado = new ArrayList<DTOCNegocios>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCNegocios.class);
		resultado = (ArrayList<DTOCNegocios>) criteria.list();

		return resultado;
	}

}
