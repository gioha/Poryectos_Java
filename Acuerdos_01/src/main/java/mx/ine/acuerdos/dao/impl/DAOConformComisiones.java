package mx.ine.acuerdos.dao.impl;

import mx.ine.acuerdos.dao.DAOConformComisionesInterface;
import mx.ine.acuerdos.dto.DTOConformComisiones;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Scope("prototype")
@Repository("daoConformComisiones")
public class DAOConformComisiones extends DAOGeneric<DTOConformComisiones, Integer> implements DAOConformComisionesInterface {
	private static final Log log = LogFactory.getLog(DAOConformComisiones.class);

	@Override
	public DTOConformComisiones obtenerConformComisionActual(Integer idComision) throws Exception {

		DTOConformComisiones dtoConformComision = new DTOConformComisiones();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOConformComisiones.class);
		criteria.add(Restrictions.eq("id.idComision", idComision));
		criteria.addOrder(Order.desc("id.inicioPeriodo"));
		dtoConformComision = (DTOConformComisiones) criteria.list().get(0);

		return dtoConformComision;
	}

}
