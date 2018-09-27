package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dao.DAOConvocatoriasInterface;
import mx.ine.acuerdos.dto.DTOConvocatorias;

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
@Repository("daoConvocatorias")
public class DAOConvocatorias extends DAOGeneric<DTOConvocatorias, Integer> implements DAOConvocatoriasInterface {
	private static final Log log = LogFactory.getLog(DAOConvocatorias.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOConvocatorias> obtenerConvocatoriasPorAnio(Integer anio) throws Exception {

		List<DTOConvocatorias> resultado = new ArrayList<DTOConvocatorias>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOConvocatorias.class);
		criteria.add(Restrictions.eq("id.anio", anio));
		criteria.addOrder(Order.desc("id.numSesion"));
		resultado = (ArrayList<DTOConvocatorias>) criteria.list();

		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOConvocatorias> obtenerConvocatoriasFechaTipo(Integer idAnio, Integer tipoSesion) throws Exception {

		List<DTOConvocatorias> resultado = new ArrayList<DTOConvocatorias>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOConvocatorias.class);
		criteria.add(Restrictions.eq("id.anio", idAnio));
		criteria.add(Restrictions.eq("tipoSesion", tipoSesion));
		criteria.addOrder(Order.asc("id.numSesion"));
		resultado = (ArrayList<DTOConvocatorias>) criteria.list();

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<DTOConvocatorias> obtenerConvocatoriasFormatAnio(Date inicioAnio, Date finAnio) throws Exception {

		List<DTOConvocatorias> resultado = new ArrayList<DTOConvocatorias>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOConvocatorias.class);
		criteria.add(Restrictions.between("fechaSesion", inicioAnio, finAnio));
		criteria.addOrder(Order.asc("fechaSesion"));
		resultado = (ArrayList<DTOConvocatorias>) criteria.list();

		return resultado;
	}

	@Override
	public void insertarConvocatoria(DTOConvocatorias convocatoria) throws Exception {

		convocatoria.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		convocatoria.setFechaHora(new Date());

		try {
			getSession().save(convocatoria);
		} catch (Exception e) {
			log.error("DAOConvocatorias.insertarConvocatoria :" + e.getMessage());			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOConvocatorias> obtenerConvocatorias() throws Exception {

		List<DTOConvocatorias> resultado = new ArrayList<DTOConvocatorias>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOConvocatorias.class);
		criteria.addOrder(Order.desc("id.anio"));
		resultado = (ArrayList<DTOConvocatorias>) criteria.list();

		return resultado;
	}

}
