package mx.ine.acuerdos.dao.impl;

import java.util.Date;

import mx.ine.acuerdos.dao.DAOAnexosOrdenesDelDiaInterface;
import mx.ine.acuerdos.dto.DTOAnexosOrdenesDelDia;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoAnexosOrdenesDelDia")
public class DAOAnexosOrdenesDelDia extends DAOGeneric<DTOAnexosOrdenesDelDia, Integer> implements DAOAnexosOrdenesDelDiaInterface {
	private static final Log log = LogFactory.getLog(DAOAnexosOrdenesDelDia.class);

	@Override
	public void insertarAnexoOrdenDia(DTOAnexosOrdenesDelDia anexoOrdenDia) throws Exception {

		anexoOrdenDia.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		anexoOrdenDia.setFechaHora(new Date());

		try {
			getSession().saveOrUpdate(anexoOrdenDia);
		} catch (Exception e) {
			log.error("DAOAnexosOrdenesDelDia.insertarAnexoOrdenDia :" + e.getMessage());			
		}
	}

}
