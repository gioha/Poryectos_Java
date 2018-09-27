package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOPeriodosInterface;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoPeriodos")
public class DAOPeriodos extends DAOGeneric<DTODocumentosPeriodos, Integer> implements DAOPeriodosInterface {
	private static final Log log = LogFactory.getLog(DAOPeriodos.class);

	@Override
	public boolean persistePeriodos(List<DTODocumentosPeriodos> periodos, DTODocumentosSivople documento) throws Exception {
		int idPeriodo = 1;
		for (DTODocumentosPeriodos periodo : periodos) {
			periodo.getId().setIdNumDocumento(documento.getId().getIdNumDocumento());
			periodo.getId().setIdArea(documento.getId().getIdArea());
			periodo.getId().setTipoArea(documento.getId().getTipoArea());
			periodo.getId().setIdPeriodo(idPeriodo);
			guardar(periodo);
			idPeriodo++;
		}
		return false;
	}

	@Override
	public List<DTODocumentosPeriodos> buscarTodos(String idDocumento) throws Exception {
		List<DTODocumentosPeriodos> resultado = new ArrayList<>();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTODocumentosPeriodos.class);
		criteria.add(Restrictions.eq("id.idNumDocumento", idDocumento));
//		criteria.add(Restrictions.eq("activo", 1 ));		
		resultado = (ArrayList<DTODocumentosPeriodos>) criteria.list();
		
		return resultado;
	}

	@Override
	public void eliminaPeriodos(String idDocumento) throws Exception {
		try {
			Query query = getSession().createSQLQuery(this.getContainer().getQuery("query.elimina.periodos"));
			query.setParameter("IDDOCUMENTO", idDocumento);
			//System.out.println(query.executeUpdate());
			
		} catch (Exception e) {
			log.info("Error al eliminar puntos de acuerdo. DAO " + e.getMessage());
			//System.out.println("Error al eliminar puntos de acuerdo. DAO " + e.getMessage());
			
		}
	}

}
