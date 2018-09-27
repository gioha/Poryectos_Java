package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOTipoSesionesInterface;
import mx.ine.acuerdos.dto.DTOTipoSesiones;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Scope("prototype")
@Repository("daoTiposSesiones")
public class DAOTipoSesiones extends DAOGeneric<DTOTipoSesiones, Integer> implements DAOTipoSesionesInterface{

	private static final Log log = LogFactory.getLog(DAOTipoSesiones.class);
	
	@Override
	public List<DTOTipoSesiones> consultaTipoSesiones() throws Exception {
		
		List<DTOTipoSesiones> resultado = new ArrayList<DTOTipoSesiones>();
		

		// creamos una sesion a la BD
		Session session = getSession();
		
		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOTipoSesiones.class);
		
		criteria.addOrder(Order.asc("orden"));
		
		// eujecutamos la operación en BD
		resultado = (List<DTOTipoSesiones>) criteria.list();
		
		// retornamos el resultado
		return resultado;
		
	}

	
	
}
