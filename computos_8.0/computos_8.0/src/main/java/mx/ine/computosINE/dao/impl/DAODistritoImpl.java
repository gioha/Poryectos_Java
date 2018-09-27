package mx.ine.computosINE.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dao.DAODistritoInterface;
import mx.ine.computosINE.dto.DTODistrito;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("daoDistritos")
@Scope("prototype")
public class DAODistritoImpl extends DAOGeneric<DTODistrito, Integer> implements
		DAODistritoInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistrito> consultaDistritos(Integer idEstado,
			Integer idMunicipiolocal) {

		List<Object[]> resultado = new ArrayList<>();
		List<DTODistrito> distritos = new ArrayList<DTODistrito>();
		String qry = getContainer()
				.getQuery("query_reportes_consultaDistritos");
		Query query = getSession().createSQLQuery(qry);
		query.setParameter("P_IdEstado", idEstado);
		query.setParameter("P_IdMunicipioLocal", idMunicipiolocal);
		resultado = query.list();
		for (Object[] row : resultado) {
			DTODistrito distrito = new DTODistrito();
			distrito.setDistrito(new Integer(row[0].toString()));
			distrito.setCabeceraDistrital((String) row[1]);
			distritos.add(distrito);
		}
		return distritos;
	}

}
