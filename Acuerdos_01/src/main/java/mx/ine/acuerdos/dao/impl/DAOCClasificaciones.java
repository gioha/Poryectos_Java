package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCClasificacionesInterface;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.util.Constantes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCClasifiaciones")
public class DAOCClasificaciones extends
		DAOGeneric<DTOCClasificaciones, Integer> implements
		DAOCClasificacionesInterface {
	
	private static final Log log = LogFactory.getLog(DAOTipoSesiones.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCClasificaciones> consultaClasificaiones() throws Exception {
		List<DTOCClasificaciones> resultado = new ArrayList<DTOCClasificaciones>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOCClasificaciones.class);

		criteria.addOrder(Order.asc("orden"));

		// eujecutamos la operación en BD
		resultado = (List<DTOCClasificaciones>) criteria.list();

		// retornamos el resultado
		return resultado;
	}

	@Override
	public DTOCClasificaciones obtenerClasificacion(Integer idClasificacion) throws Exception {
		DTOCClasificaciones dtoClasificacion;
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCClasificaciones.class);
		criteria.add(Restrictions.eq("idClasificacion", idClasificacion));
		// Se ejecuta la operación en la base de datos
		dtoClasificacion = (DTOCClasificaciones) criteria.uniqueResult();

		return dtoClasificacion;
	}
	
	@Override
	public List<DTOCClasificaciones> obtenerSiPPN() throws Exception {
		List<DTOCClasificaciones> resultado = new ArrayList<DTOCClasificaciones>();
		DTOCClasificaciones dtoClasificacion;
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOCClasificaciones.class);
		criteria.add(Restrictions.eq("tipoClasificacion", 1));
		// Se ejecuta la operación en la base de datos
		resultado = (List<DTOCClasificaciones>) criteria.list();

		return resultado;
	}
	
	
	@Override
	public String actualizarClasif(DTOCClasificaciones dtoClasif){
		
		try{
			guardarOactualizar(dtoClasif);
			return Constantes.REGISTRO_RG;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constantes.NO_REGISTRO_RG;
		}
	}

	@Override
	public String eliminarClasif(DTOCClasificaciones dtoClasif) {
		try{
			Query query = getSession().createSQLQuery(this.getContainer().getQuery("query.elimina.clasificacion"));
			query.setParameter("IDCLASIFICACION", dtoClasif.getIdClasificacion());
			query.executeUpdate();
			
			return Constantes.REGISTRO_RG;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constantes.NO_REGISTRO_RG;
		}
	}
	

}
