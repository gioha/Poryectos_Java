package mx.ine.acuerdos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dao.DAOAcuerdosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.util.Constantes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoAcuerdos")
public class DAOAcuerdos extends DAOGeneric<DTOAcuerdos, Integer> implements DAOAcuerdosInterface {
	
	private static final Log log = LogFactory.getLog(DAOAcuerdos.class);
	
	@Override
	public List<DTOAcuerdos> consultaAcuerdos(Integer idTipoDocumento, Integer idNegocio) throws Exception {
		
		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();
		
		// creamos una sesion a la BD
		Session session = getSession();
		
		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);
		
		criteria.add(Restrictions.eq("activo", 1 ));
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento ));
		criteria.add(Restrictions.eq("idNegocio", idNegocio ));
		criteria.addOrder(Order.desc("fechaSesion"));
		criteria.addOrder(Order.desc("idNumAcuerdo"));
		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();
		
			for (DTOAcuerdos acuerdo : resultado) {
				if(!acuerdo.getEstatus().equals(1)){
					
					ArrayList estatusProceso = new ArrayList<Integer>();
					estatusProceso.add(new Integer(2));
					estatusProceso.add(new Integer(3));
					estatusProceso.add(new Integer(4));
					estatusProceso.add(new Integer(5));
					estatusProceso.add(new Integer(6));
					
					List<DTOSeguimientosCG> avanzando = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaAvanzando = session.createCriteria(DTOSeguimientosCG.class);
					criteriaAvanzando.add(Restrictions.eq("id.idNumAcuerdo", acuerdo.getIdNumAcuerdo()));						
					criteriaAvanzando.add(Restrictions.in("id.idEstatusPunto", estatusProceso));
					avanzando = (List<DTOSeguimientosCG>) criteriaAvanzando.list();
					
					if(avanzando.size() == 0){//si no hay ningun punto del Acuerdo con algun movimiento de avance
							acuerdo.setEstatus(0);
					}else{
						acuerdo.setEstatus(2);
					}
				}
			}
		// retornamos el resultado
		return resultado;
	}

	@Override
	public String registrarAcuerdo( DTOAcuerdos acuerdo){
	
		try {
		
			acuerdo.setEstatus(0);
			acuerdo.setActivo(1);
			guardarOactualizar(acuerdo);
			return Constantes.REGISTRO_RG;
			
		} catch (Exception e) {
			log.error( " DAOAcuerdos - registrarAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return Constantes.NO_REGISTRO_RG;
			
		}
	}
	
	@Override
	public void eliminacionLogicaAcuerdo(DTOAcuerdos acuerdo) throws Exception{	
		modificar(acuerdo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);

		criteria.add(Restrictions.like("idNumAcuerdo", "%"+idAcuerdo+"%"));
		criteria.add(Restrictions.eq("activo", 1));
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento));
		criteria.add(Restrictions.eq("idNegocio", idNegocio));
		criteria.addOrder(Order.asc("estatus"));

		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();

		// retornamos el resultado
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception {

		DetachedCriteria userSubquery = DetachedCriteria.forClass(DTOSeguimientosCG.class)
									    .add(Restrictions.eq("id.idArea",idArea))
									    .setProjection( Projections.distinct(Projections.property("id.idNumAcuerdo")));

		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);

		criteria.add(Restrictions.like("idNumAcuerdo", "%"+idAcuerdo+"%"));
		criteria.add(Restrictions.eq("activo", 1));
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento));
		criteria.add(Restrictions.eq("idTipoDocumento", idNegocio));
		criteria.addOrder(Order.asc("estatus"));
		criteria.add(Subqueries.propertyIn("idNumAcuerdo", userSubquery));

		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();

		// retornamos el resultado
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		String fechaIni = "01-01-"+fechaSesion;
		String fechaFin = "31-12-"+fechaSesion;

		Date fechaInicial = null;
		fechaInicial = formatoDelTexto.parse(fechaIni);

		Date fechaFinal = null;
		fechaFinal = formatoDelTexto.parse(fechaFin);

		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);

		criteria.add(Restrictions.ge("fechaSesion", fechaInicial));
		criteria.add(Restrictions.lt("fechaSesion", fechaFinal));

		criteria.add(Restrictions.eq("activo", 1));
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento));
		criteria.add(Restrictions.eq("idNegocio", idNegocio));
		criteria.addOrder(Order.asc("estatus"));

		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();

		// retornamos el resultado
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		
		DetachedCriteria userSubquery = DetachedCriteria.forClass(DTOSeguimientosCG.class)
									    .add(Restrictions.eq("id.idArea",idArea))
									    .setProjection( Projections.distinct(Projections.property("id.idNumAcuerdo")));

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		String fechaIni = "01-01-"+fechaSesion;
		String fechaFin = "31-12-"+fechaSesion;

		Date fechaInicial = null;
		fechaInicial = formatoDelTexto.parse(fechaIni);

		Date fechaFinal = null;
		fechaFinal = formatoDelTexto.parse(fechaFin);

		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);

		criteria.add(Restrictions.ge("fechaSesion", fechaInicial));
		criteria.add(Restrictions.lt("fechaSesion", fechaFinal));

		criteria.add(Restrictions.eq("activo", 1));
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento));
		criteria.add(Restrictions.eq("idNegocio", idNegocio));
		criteria.addOrder(Order.asc("estatus"));
		criteria.add(Subqueries.propertyIn("idNumAcuerdo", userSubquery));

		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();

		// retornamos el resultado
		return resultado;
	}
	
	@Override
	public List<DTOAcuerdos> consultaAcuerdo(String idAcuerdo) throws Exception{	
	
		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();
		
		// creamos una sesion a la BD
		Session session = getSession();
		
		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);
		
		criteria.add(Restrictions.eq("idNumAcuerdo", idAcuerdo ));
		criteria.add(Restrictions.eq("activo", 1 ));		
		
		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();
		
		// retornamos el resultado
		return resultado;
	}
	
	@Override
	public void eliminarSeguimiento(String idAcuerdo){
		

		try {
			
			Query query = getSession().createSQLQuery(this.getContainer().getQuery("query.elimina.seguimientocg"));
			query.setParameter("IDACUERDO1", idAcuerdo);

			query.executeUpdate();
			
			
		} catch (Exception e) {
			log.error( " DAOAcuerdos - eliminarSeguimiento() - Ocurrio un error: " + e.getMessage()  );
		}
	}

	@Override
	public void eliminarPuntosAc(String idAcuerdo){
		
		try {
	
			Query query = getSession().createSQLQuery(this.getContainer().getQuery("query.elimina.puntosacuerdo"));
			query.setParameter("IDACUERDO", idAcuerdo);

			query.executeUpdate();
			
			
		} catch (Exception e) {
			log.error( " DAOAcuerdos - eliminarPuntosAc() - Ocurrio un error: " + e.getMessage()  );
			
		}
	}	

	@Override
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo)  {

		try {

			List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();

			// creamos una sesion a la BD
			Session session = getSession();

			// armado de Query a travez de Criteria
			Criteria criteria = session.createCriteria(DTOAcuerdos.class);
			criteria.add(Restrictions.eq("idNumAcuerdo", idAcuerdo));
			// eujecutamos la operación en BD
			
			DTOAcuerdos dtoAcuerdos = (DTOAcuerdos) criteria.uniqueResult();
			return dtoAcuerdos;
			
		} catch (Exception e) {
			
			log.error( " DAOAcuerdos - obtenerAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}

	}
	
	@Override
	public String guardaroActualizarAcuerdo(DTOAcuerdos acuerdo) throws Exception {
		
		try{
			acuerdo.setEstatus(0);
			acuerdo.setActivo(1);
			guardarOactualizar(acuerdo);
			return Constantes.REGISTRO_RG;
			
		} catch (Exception e) {
		
			log.error( " DAOAcuerdos - guardaroActualizarAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return Constantes.NO_REGISTRO_RG;
			
		}
	}	
	
	@Override
	public Date obtenerFechaSesion(String idAcuerdo) {

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);
		criteria.add(Restrictions.eq("idNumAcuerdo", idAcuerdo));
		
		// eujecutamos la operación en BD
//		resultado = (List<DTOPuntosAcuerdo>) criteria.list();
		
		DTOAcuerdos dtoAcuerdo = (DTOAcuerdos) criteria.uniqueResult();
		
		return dtoAcuerdo.getFechaSesion();
	}
	
	@Override
	public List<DTOAcuerdos> consultaAcuerdosPorArea(Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception {
		
				DetachedCriteria userSubquery = DetachedCriteria.forClass(DTOSeguimientosCG.class)			   
			    .add(Restrictions.eq("id.idArea",idArea))
			    .setProjection( Projections.distinct(Projections.property("id.idNumAcuerdo")));		
		
		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();			
		Session session = getSession();	
		
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);
		
		criteria.add(Restrictions.eq("activo",1));		
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento ));
		criteria.add(Restrictions.eq("idNegocio", idNegocio ));
		criteria.addOrder(Order.desc("fechaSesion"));
		criteria.addOrder(Order.desc("idNumAcuerdo"));
		criteria.add(Subqueries.propertyIn("idNumAcuerdo", userSubquery));
		
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();
			for (DTOAcuerdos acuerdo : resultado) {
				Integer puntosAgignados = 0;
				Integer puntosFinalizados = 0;
				Criteria criteriaAsignados = session.createCriteria(DTOSeguimientosCG.class);
				criteriaAsignados.add(Restrictions.eq("id.idNumAcuerdo", acuerdo.getIdNumAcuerdo()));		
				criteriaAsignados.add(Restrictions.eq("id.idArea", idArea));
				criteriaAsignados.setProjection( Projections.distinct(Projections.property("id.numeralia")));				
				puntosAgignados = criteriaAsignados.list().size();
				
				Criteria criteriaFinalizados = session.createCriteria(DTOSeguimientosCG.class);
				criteriaFinalizados.add(Restrictions.eq("id.idNumAcuerdo", acuerdo.getIdNumAcuerdo()));		
				criteriaFinalizados.add(Restrictions.eq("id.idArea", idArea));
				criteriaFinalizados.add(Restrictions.eq("id.idEstatusPunto", 7));
				puntosFinalizados = criteriaFinalizados.list().size();
				
				if(puntosAgignados.equals(puntosFinalizados)){
					acuerdo.setEstatus(1);
				}else{			
					ArrayList estatusProceso = new ArrayList<Integer>();
					estatusProceso.add(new Integer(2));
					estatusProceso.add(new Integer(3));
					estatusProceso.add(new Integer(4));
					estatusProceso.add(new Integer(5));
					estatusProceso.add(new Integer(6));
					
					List<DTOSeguimientosCG> avanzando = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaAvanzando = session.createCriteria(DTOSeguimientosCG.class);
					criteriaAvanzando.add(Restrictions.eq("id.idNumAcuerdo", acuerdo.getIdNumAcuerdo()));
					criteriaAvanzando.add(Restrictions.eq("id.idArea", idArea));
					criteriaAvanzando.add(Restrictions.in("id.idEstatusPunto", estatusProceso));
					avanzando = (List<DTOSeguimientosCG>) criteriaAvanzando.list();
					
					if(avanzando.size() == 0){//si no hay ningun punto del Acuerdo con algun movimiento de avance
							acuerdo.setEstatus(0);
					}else{
						acuerdo.setEstatus(2);
					}											
				}
			}
					
		return resultado;
	}
	
	@Override
	public List<DTOAcuerdos> consultaResoluciones() throws Exception{
	
		List<DTOAcuerdos> resultado = new ArrayList<DTOAcuerdos>();
		
		// creamos una sesion a la BD
		Session session = getSession();
		
		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);
		
		criteria.add(Restrictions.eq("activo", 1 ));
		criteria.add(Restrictions.eq("tipo", 6 ));	
		criteria.addOrder(Order.desc("fechaSesion"));
		criteria.addOrder(Order.asc("idNumAcuerdo"));
		
		// eujecutamos la operación en BD
		resultado = (ArrayList<DTOAcuerdos>) criteria.list();
		
		// retornamos el resultado
		return resultado;
	}

	@Override
	public void actualizarAcuerdo(DTOAcuerdos acuerdo) throws Exception {
		guardarOactualizar(acuerdo);	
	}

}
