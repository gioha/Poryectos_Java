package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCTipoIntegComisionInterface;
import mx.ine.acuerdos.dto.DTOCTipoIntegComision;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCTipoIntegComision")
public class DAOCTipoIntegComision extends DAOGeneric<DTOCTipoIntegComision, Integer> implements DAOCTipoIntegComisionInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCTipoIntegComision> obtenerTipoIntegComision() throws Exception {

		List<DTOCTipoIntegComision> resultado = new ArrayList<DTOCTipoIntegComision>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCTipoIntegComision.class);
//		criteria.addOrder(Order.asc("comisiones"));		// ROQUE TEMPORAL (CORREGIR)
		criteria.addOrder(Order.asc("orden"));
		resultado = (ArrayList<DTOCTipoIntegComision>) criteria.list();

		return resultado;
	}

}
