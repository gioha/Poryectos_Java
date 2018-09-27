package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dao.DAOOrdenesDelDiaInterface;
import mx.ine.acuerdos.dto.DTOConvocatoriasPK;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoOrdenesDelDia")
public class DAOOrdenesDelDia extends DAOGeneric<DTOOrdenesDelDia, Integer> implements DAOOrdenesDelDiaInterface {
	private static final Log log = LogFactory.getLog(DAOOrdenesDelDia.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOOrdenesDelDia> obtenerOrdenDelDia(DTOConvocatoriasPK dtoConvoctariaPK) throws Exception {

		List<DTOOrdenesDelDia> resultado = new ArrayList<DTOOrdenesDelDia>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOOrdenesDelDia.class);
		criteria.add(Restrictions.eq("id.anio", dtoConvoctariaPK.getAnio()));
		criteria.add(Restrictions.eq("id.idComision", dtoConvoctariaPK.getIdComision()));
		criteria.add(Restrictions.eq("id.inicioPeriodo", dtoConvoctariaPK.getInicioPeriodo()));
		criteria.add(Restrictions.eq("id.numSesion", dtoConvoctariaPK.getNumSesion()));
		criteria.addOrder(Order.asc("id.numPunto"));
		resultado = (ArrayList<DTOOrdenesDelDia>) criteria.list();

		return resultado;
	}

	@Override
	public void insertarPuntoOrdenDia(DTOOrdenesDelDia ordenDelDia) throws Exception {

		ordenDelDia.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		ordenDelDia.setFechaHora(new Date());

		try {
			getSession().save(ordenDelDia);
		} catch (Exception e) {
			log.error("DAOOrdenesDelDia.insertarPuntoConvoc :" + e.getMessage());			
		}
	}

}
