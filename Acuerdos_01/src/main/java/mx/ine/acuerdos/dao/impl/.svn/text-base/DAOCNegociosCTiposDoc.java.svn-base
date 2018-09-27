package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCNegociosCTiposDocInterface;
import mx.ine.acuerdos.dto.DTOCNegociosCTiposDoc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCNegociosCTiposDoc")
public class DAOCNegociosCTiposDoc extends DAOGeneric<DTOCNegociosCTiposDoc, Integer> implements DAOCNegociosCTiposDocInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCNegociosCTiposDoc> consultarNegociosTiposDoc() throws Exception {

		List<DTOCNegociosCTiposDoc> resultado = new ArrayList<DTOCNegociosCTiposDoc>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCNegociosCTiposDoc.class);
		resultado = (ArrayList<DTOCNegociosCTiposDoc>) criteria.list();

		return resultado;
	}

}
