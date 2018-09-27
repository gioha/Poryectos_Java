package mx.ine.computosINE.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dao.DAODemarcacionInterface;
import mx.ine.computosINE.dto.DTORegiduria;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("daoDemarcaciones")
@Scope("prototype")
public class DAODemarcacionImpl extends DAOGeneric<DTORegiduria, Integer>
		implements DAODemarcacionInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTORegiduria> consultaDemarcaciones(Integer idEstado,
			Integer idMunicipiolocal) {

		List<Object[]> resultado = new ArrayList<>();
		List<DTORegiduria> regidurias = new ArrayList<>();
		String qry = getContainer().getQuery(
				"query_reportes_consultaDemarcaciones");
		Query query = getSession().createSQLQuery(qry);
		query.setParameter("P_IdEstado", idEstado);
		query.setParameter("P_IdMunicipioLocal", idMunicipiolocal);
		resultado = query.list();
		for (Object[] row : resultado) {
			DTORegiduria regiduria = new DTORegiduria();
			regiduria.setIdRegiduria(new Integer(row[0].toString()));
			regiduria.setNombreRegiduria((String) row[1]);
			regidurias.add(regiduria);
		}
		return regidurias;

	}

}
