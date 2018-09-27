package mx.ine.acuerdos.dao.impl;

import mx.ine.acuerdos.dao.DAODocumentosInterface;
import mx.ine.acuerdos.dto.DTODocumentosSivople;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoDocumentos")
public class DAODocumentos extends DAOGeneric<DTODocumentosSivople, Integer> implements DAODocumentosInterface {

	@Override
	public void persisteDocumento(DTODocumentosSivople documento) throws Exception {
		guardarOactualizar(documento);
	}

	@Override
	public DTODocumentosSivople buscarPorId(String idDocumento) throws Exception {
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTODocumentosSivople.class);
		criteria.add(Restrictions.eq("id.idNumDocumento", idDocumento));
//		criteria.add(Restrictions.eq("activo", 1 ));
		DTODocumentosSivople dtoDocumentos = (DTODocumentosSivople) criteria.uniqueResult();
		
		return dtoDocumentos;
	}

}
