package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOComisionesInterface;
import mx.ine.acuerdos.dto.DTOComisiones;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoComisiones")
public class DAOComisiones extends DAOGeneric<DTOComisiones, Integer> implements DAOComisionesInterface {
	private static final Log log = LogFactory.getLog(DAOComisiones.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOComisiones> obtenerComisionesConFiltro(Integer idComision) throws Exception {

		List<DTOComisiones> resultado = new ArrayList<DTOComisiones>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOComisiones.class);
		// Todos menos la comisión cuyo ID es idComision
		criteria.add(Restrictions.ne("idComision", idComision));
		resultado = (ArrayList<DTOComisiones>) criteria.list();
					for (DTOComisiones com : resultado) {
						if(com.getTipoComision().equals(1))
							com.setTipoComisionCadena("Permanente");
						else
							com.setTipoComisionCadena("Temporal");
					}
		return resultado;
	}


	public DTOComisiones obtenerComision(Integer idComision) throws Exception {

		DTOComisiones dtoComision;

		// Se crea una sesión para la base de datos
		Session session = getSession();

		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOComisiones.class);
		criteria.add(Restrictions.eq("idComision", idComision));
		dtoComision = (DTOComisiones) criteria.uniqueResult();
				if(dtoComision.getTipoComision().equals(1))
					dtoComision.setTipoComisionCadena("Permanente");
				else
					dtoComision.setTipoComisionCadena("Temporal");
		
		
		return dtoComision;
	}
	
	
	public List<DTOComisiones> obtenerTodasComisionesActivas() throws Exception {
		
		List<DTOComisiones> resultado = new ArrayList<DTOComisiones>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOComisiones.class);
		// Todos menos la comisión cuyo ID es idComision
		criteria.add(Restrictions.eq("estatus", 1));
		resultado = (ArrayList<DTOComisiones>) criteria.list();
				for (DTOComisiones com : resultado) {
					if(com.getTipoComision().equals(1))
						com.setTipoComisionCadena("Permanente");
					else
						com.setTipoComisionCadena("Temporal");
				}
		return resultado;
	}
	
	public List<DTOComisiones> obtenerTodasComisionesInactivas() throws Exception {
		
		List<DTOComisiones> resultado = new ArrayList<DTOComisiones>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOComisiones.class);
		// Todos menos la comisión cuyo ID es idComision
		criteria.add(Restrictions.eq("estatus", 0));
		resultado = (ArrayList<DTOComisiones>) criteria.list();
					for (DTOComisiones com : resultado) {
						if(com.getTipoComision().equals(1))
							com.setTipoComisionCadena("Permanente");
						else
							com.setTipoComisionCadena("Temporal");
					}
		return resultado;
	}
	
	public boolean guardarComision(DTOComisiones comision, int motivo) throws Exception {
		if(motivo == 1){
				List<DTOComisiones> resultado = new ArrayList<DTOComisiones>();		
				Session session = getSession();		
				Criteria criteria = session.createCriteria(DTOComisiones.class);	
				resultado = (ArrayList<DTOComisiones>) criteria.list();
				
				boolean existe = false;
				for (DTOComisiones com : resultado) {
					if(com.getNomComision().equals(comision.getNomComision())){
						existe = true;
						break;
					}
				}
				if(existe)
					return false;
				else{
					comision.setIdComision(resultado.size()+1);
					guardarOactualizar(comision);		
					return true;
				}
		}
		if(motivo == 2){
			List<DTOComisiones> resultado = new ArrayList<DTOComisiones>();		
			Session session = getSession();		
			Criteria criteria = session.createCriteria(DTOComisiones.class);	
			resultado = (ArrayList<DTOComisiones>) criteria.list();
			
			
			for (DTOComisiones com : resultado) {
				if(com.getNomComision().equals(comision.getNomComision())){
					com.setEstatus(1);
					guardarOactualizar(com);	
					break;
				}
			}			
		}
		return false;
	}

}
