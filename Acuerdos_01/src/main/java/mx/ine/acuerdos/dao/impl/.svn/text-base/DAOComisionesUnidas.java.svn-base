package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOComisionesUnidasInterface;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoComisionesUnidas")
public class DAOComisionesUnidas extends DAOGeneric<DTOComisionesUnidas, Integer> implements DAOComisionesUnidasInterface {
	private static final Log log = LogFactory.getLog(DAOComisionesUnidas.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOComisionesUnidas> obtenerComisionesUnidas(Integer idComisionPreside) throws Exception {

		List<DTOComisionesUnidas> resultado = new ArrayList<DTOComisionesUnidas>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOComisionesUnidas.class);
		criteria.add(Restrictions.eq("id.idComisionPreside", idComisionPreside));
		resultado = (ArrayList<DTOComisionesUnidas>) criteria.list();

		return resultado;
	}

}
