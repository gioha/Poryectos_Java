package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCNegociosCTiposIntegInterface;
import mx.ine.acuerdos.dto.DTOCNegociosCTiposInteg;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCNegociosCTiposInteg")
public class DAOCNegociosCTiposInteg extends DAOGeneric<DTOCNegociosCTiposInteg, Integer> implements DAOCNegociosCTiposIntegInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCNegociosCTiposInteg> consultarNegociosTiposInteg() throws Exception {

		List<DTOCNegociosCTiposInteg> resultado = new ArrayList<DTOCNegociosCTiposInteg>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCNegociosCTiposInteg.class);
		resultado = (ArrayList<DTOCNegociosCTiposInteg>) criteria.list();

		return resultado;
	}

}
