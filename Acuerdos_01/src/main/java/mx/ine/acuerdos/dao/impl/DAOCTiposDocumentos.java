package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOCTiposDocumentosInterface;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoCTiposDocumentos")
public class DAOCTiposDocumentos extends DAOGeneric<DTOCTipoDocumento, Integer>	implements DAOCTiposDocumentosInterface {

	private static final Log log = LogFactory.getLog(DAOCTiposDocumentos.class);
	
	@Override
	public List<DTOCTipoDocumento> consultaTiposDocumentos() throws Exception {

		List<DTOCTipoDocumento> resultado = new ArrayList<>();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOCTipoDocumento.class);
		criteria.add(Restrictions.eq("cg", 1 ));
		criteria.addOrder(Order.asc("tipo"));
		resultado = (ArrayList<DTOCTipoDocumento>) criteria.list();
		return resultado;
	}

	@Override
	public String recuperaTipoDocumentoS(Integer idDoc) {
		
	try{
		
		// creamos una sesion a la BD
		Session session = getSession();

		// armado de Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOCTipoDocumento.class);
		criteria.add(Restrictions.eq("idTipoDocumento", idDoc));
		criteria.addOrder(Order.asc("tipo"));
		// eujecutamos la operación en BD
		
		DTOCTipoDocumento dtoTiDoc = (DTOCTipoDocumento) criteria.uniqueResult();
		return dtoTiDoc.getTipo();
		
	} catch (Exception e) {
		log.error( " DAOCTiposDocumentos - recuperaTipoDocumentoS() - Ocurrio un error: " + e.getMessage()  );
		return null;
	}
	}

}
