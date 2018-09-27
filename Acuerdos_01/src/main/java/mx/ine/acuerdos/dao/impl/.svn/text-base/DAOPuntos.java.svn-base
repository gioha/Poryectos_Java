package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Scope("prototype")
@Repository("daoPuntos")
public class DAOPuntos extends DAOGeneric<DTOPuntosAcuerdo, Integer> implements
		DAOPuntosInterface {
	private static final Log log = LogFactory.getLog(DAOAcuerdos.class);

	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	@Override
	public List<DTOPuntosAcuerdo> consultaPuntos() throws Exception {

		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOAcuerdos.class);

		criteria.addOrder(Order.asc("estatus"));

		// eujecutamos la operación en BD
		resultado = (List<DTOPuntosAcuerdo>) criteria.list();

		// retornamos el resultado
		return resultado;
	}

	@Override
	public void guardaroActualizarPunto(DTOPuntosAcuerdo punto) throws Exception {
		guardarOactualizar(punto);
	

	}
	@Override
	public void actualizar(DTOPuntosAcuerdo punto)  throws Exception {
		modificar(punto);

	}
	
	@Override
	public List<DTOPuntosAcuerdo> consultaPuntosAcuerdo(String idNumAcuerdo) throws Exception {
		
		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();
		List<DTOSeguimientosCG> completados = new ArrayList<DTOSeguimientosCG>();

		// creamos una sesion a la BD
		Session session = getSession();

		//Query para obtener los puntos de un acuerdo que esten activo (1)--- ya que 0= baja logica 
		Criteria criteria = session.createCriteria(DTOPuntosAcuerdo.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo", idNumAcuerdo));		
		criteria.add(Restrictions.eq("activo", 1 ));
		criteria.addOrder(Order.asc("id.numeralia"));
		resultado = (List<DTOPuntosAcuerdo>) criteria.list();
	
		//----AQUI SE REVISA EL ESTATUS GLOBAL DE CADA PUNTO EN SEGUIMIENTO 
		for (DTOPuntosAcuerdo punto : resultado) {
			Integer numResponsables = new Integer(0);
				Criteria criteriaEstatus = session.createCriteria(DTOSeguimientosCG.class);
				criteriaEstatus.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
				criteriaEstatus.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
				criteriaEstatus.setProjection(Projections.groupProperty("id.idArea"));			
				numResponsables = criteriaEstatus.list().size();
				
				if(numResponsables.equals(0)){//si no tiene seguimiento
					punto.setEstatusGlobal("Sin Áreas");
					punto.setSemaforo("gris");
					continue;
				}
			
				//----SE EVALUA CUANTAS AREAS ASIGNADAS AL PUNTO ESTAN EN COMPLETADO
					Criteria criteriaCompletado = session.createCriteria(DTOSeguimientosCG.class);
					criteriaCompletado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
					criteriaCompletado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
					criteriaCompletado.add(Restrictions.eq("id.idEstatusPunto",7));
					completados = (List<DTOSeguimientosCG>)criteriaCompletado.list();						
						
						if(completados.size() == 0){
							punto.setEstatusGlobal("Pendiente");
						}else{
							punto.setEstatusGlobal("Cumplido Parcialmente");
						}						
						if(completados.size() == numResponsables){
							punto.setEstatusGlobal("Cumplido");
						}
						
						punto.setSemaforo(semaforoPunto(punto, numResponsables));
		}		
		
		return resultado;
	}
	
	@Override
	public String semaforoPunto(DTOPuntosAcuerdo punto, Integer numResponsables)throws Exception{
		Session session = getSession();
		//----APARTADO PARA EL SEMAFORO GENERAL DEL PUNTO--Se verifica si el punto es de tipo notificacion
		if(punto.getNotificacion().equals(1)){
			//------Se verifica si esta rechazado
			List<DTOSeguimientosCG> rechazados = new ArrayList<DTOSeguimientosCG>();
			Criteria criteriaRechazado = session.createCriteria(DTOSeguimientosCG.class);
			criteriaRechazado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
			criteriaRechazado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
			criteriaRechazado.add(Restrictions.eq("id.idEstatusPunto", 2));
			rechazados = (List<DTOSeguimientosCG>) criteriaRechazado.list();
			
			if(rechazados.size() >= 1){//////////////si por lo menos hay un rechazado
				//se recorren los rechazados por area  y se checa su ultimo movimiento 
				
				for (DTOSeguimientosCG puntoRechazado : rechazados) {
					List<DTOSeguimientosCG> rechazadoArea = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaRechazadoArea = session.createCriteria(DTOSeguimientosCG.class);
					criteriaRechazadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
					criteriaRechazadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
					criteriaRechazadoArea.add(Restrictions.eq("id.idArea", puntoRechazado.getId().getIdArea()));
					criteriaRechazadoArea.addOrder(Order.desc("id.fechaMovimiento"));
					rechazadoArea =  (List<DTOSeguimientosCG>)criteriaRechazadoArea.list();
					if(rechazadoArea.get(0).getId().getIdEstatusPunto() == 2){//si el ultimo movimiento de un area continua en rechazado el retorno sera equis							
							return  "equis";							
					}	
				}			
			}		
				
			//------Se verifica si hay un estatus de finalizado 
				List<DTOSeguimientosCG> finalizados = new ArrayList<DTOSeguimientosCG>();
				Criteria criteriafinalizado = session.createCriteria(DTOSeguimientosCG.class);
				criteriafinalizado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
				criteriafinalizado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
				criteriafinalizado.add(Restrictions.eq("id.idEstatusPunto", 5));
				finalizados = (List<DTOSeguimientosCG>) criteriafinalizado.list();
					
					if(finalizados.size() >= 1){//////////////si por lo menos hay un finalizado
						//se recorren los finalizados por area  y se checa su ultimo movimiento si es 5 entonces se coloca campana
						for (DTOSeguimientosCG puntoFinalizado : finalizados) {
							List<DTOSeguimientosCG> finalizadoArea = new ArrayList<DTOSeguimientosCG>();
							Criteria criteriaFinalizadoArea = session.createCriteria(DTOSeguimientosCG.class);
							criteriaFinalizadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
							criteriaFinalizadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
							criteriaFinalizadoArea.add(Restrictions.eq("id.idArea", puntoFinalizado.getId().getIdArea()));
							criteriaFinalizadoArea.addOrder(Order.desc("id.fechaMovimiento"));
							finalizadoArea =  (List<DTOSeguimientosCG>)criteriaFinalizadoArea.list();
							if(finalizadoArea.get(0).getId().getIdEstatusPunto() == 5){//si el ultimo movimiento de un area continua en finalizado el retorno sera campana		
									return  "campana";							
							}	
						}	
					}
			
				
					//------SI NO ESTA EN RECHAZADO Se continua verificando si todas las areasa estan en completado
					List<DTOSeguimientosCG> completado = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaCompletado = session.createCriteria(DTOSeguimientosCG.class);
					criteriaCompletado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
					criteriaCompletado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));					
					criteriaCompletado.add(Restrictions.eq("id.idEstatusPunto", 7));						
					completado = (List<DTOSeguimientosCG>) criteriaCompletado.list();		
					
						if(completado.size() == numResponsables){
							return "palomita";							
						}else{// SI TODAS LAS AREAS NO ESTAN EN COMPLETADO se obtine la la fecha del mas atrazado y se evalua el semaforo por tiempo
								
								Date fechaInicio = new Date();
								Criteria criteriaFecha = session.createCriteria(DTOSeguimientosCG.class);
								criteriaFecha.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
								criteriaFecha.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));							
								criteriaFecha.setProjection(Projections.min("id.fechaMovimiento"));
								fechaInicio = (Date) criteriaFecha.uniqueResult();							
								
								double diferenciaDias = (double) ((new Date().getTime()-fechaInicio.getTime())/86400000);								
								
								if(diferenciaDias <= 30.4 ){
									return "verde";
								}
								if(diferenciaDias > 30.4 && diferenciaDias <= 30.4*2 ){
									return "amarillo";
								}
								if(diferenciaDias > 30.4*2 ){
									return "rojo";
								}
								
							}			
				
		}else{//si no es de notificacion
		//	Se verifica si todas las areasa estan en completado
			List<DTOSeguimientosCG> completadoGris = new ArrayList<DTOSeguimientosCG>();
			Criteria criteriaCompletado = session.createCriteria(DTOSeguimientosCG.class);
			criteriaCompletado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
			criteriaCompletado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));					
			criteriaCompletado.add(Restrictions.eq("id.idEstatusPunto", 7));						
			completadoGris = (List<DTOSeguimientosCG>) criteriaCompletado.list();		
			
				if(completadoGris.size() == numResponsables){
					return "palomita";							
				}		
			    //SI NO ESTA COMPLETADO se continua verificando si esta en rechazado
					List<DTOSeguimientosCG> rechazadoGris = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaRechazado = session.createCriteria(DTOSeguimientosCG.class);
					criteriaRechazado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
					criteriaRechazado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
					criteriaRechazado.add(Restrictions.eq("id.idEstatusPunto", 2));
					rechazadoGris = (List<DTOSeguimientosCG>) criteriaRechazado.list();
					
						if(rechazadoGris.size() >= 1){//////////////si por lo menos hay un rechazado
							//se recorren los rechazados por area  y se checa su ultimo movimiento para confirmar que esta rechazado
							String valorRetorno= "gris";
							for (DTOSeguimientosCG puntoRechazado : rechazadoGris) {
								List<DTOSeguimientosCG> rechazadoArea = new ArrayList<DTOSeguimientosCG>();
								Criteria criteriaRechazadoArea = session.createCriteria(DTOSeguimientosCG.class);
								criteriaRechazadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
								criteriaRechazadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
								criteriaRechazadoArea.add(Restrictions.eq("id.idArea", puntoRechazado.getId().getIdArea()));
								criteriaRechazadoArea.addOrder(Order.desc("id.fechaMovimiento"));
								rechazadoArea =  (List<DTOSeguimientosCG>)criteriaRechazadoArea.list();
									if(rechazadoArea.get(0).getId().getIdEstatusPunto() == 2){//si el ultimo movimiento de un area continua en rechazado el retorno sera equis
										valorRetorno =  "equis";
										break;
									}	
							}						
							
							return valorRetorno; // si nunca hubo un rechazado como ultimo movimiento retornara gris
						}
							//------Se verifica si hay un estatus de finalizado 
							List<DTOSeguimientosCG> finalizados = new ArrayList<DTOSeguimientosCG>();
							Criteria criteriafinalizado = session.createCriteria(DTOSeguimientosCG.class);
							criteriafinalizado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
							criteriafinalizado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
							criteriafinalizado.add(Restrictions.eq("id.idEstatusPunto", 5));
							finalizados = (List<DTOSeguimientosCG>) criteriafinalizado.list();
							if(finalizados.size() >= 1){//////////////si por lo menos hay un finalizado
								//se recorren los finalizados por area  y se checa su ultimo movimiento si es 5 entonces se coloca campana
								for (DTOSeguimientosCG puntoFinalizado : finalizados) {
									
									List<DTOSeguimientosCG> finalizadoArea = new ArrayList<DTOSeguimientosCG>();
									Criteria criteriaFinalizadoArea = session.createCriteria(DTOSeguimientosCG.class);
									criteriaFinalizadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
									criteriaFinalizadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
									criteriaFinalizadoArea.add(Restrictions.eq("id.idArea", puntoFinalizado.getId().getIdArea()));
									criteriaFinalizadoArea.addOrder(Order.desc("id.fechaMovimiento"));
									finalizadoArea =  (List<DTOSeguimientosCG>)criteriaFinalizadoArea.list();
									if(finalizadoArea.get(0).getId().getIdEstatusPunto() == 5){//si el ultimo movimiento de un area continua en finalizado el retorno sera campana		
											return  "campana";							
									}	
								}	
							}//
							return "gris";
		}
		return null;
		
	}
	
	@Override
	public void eliminacionLogicaPuntos(List<DTOPuntosAcuerdo> puntos) throws Exception{
		for (DTOPuntosAcuerdo punto : puntos) {
			
			// se elimina el pdf del oficio de notificación, adjunto en el punto, si es que se le adjunto el oficio
			if( punto.getUrlArchivoAdj() != null ){
				if (boArchivos.eliminaArchivoEnGluster(punto.getUrlArchivoAdj()) ){
					punto.setActivo(0);
					modificar(punto);
				}
				else
					log.error("No se pudo eliminar el archivo adjunto del punto en gluster - DAOPuntos.eliminacionLogicaPuntos");
			}
			else{
				punto.setActivo(0);
				modificar(punto);
			}
		}
		
	}
	
	@Override
	public void eliminacionLogicaPunto(DTOPuntosAcuerdo punto) throws Exception{
			modificar(punto);	
			
	}
	
	@Override
	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia)throws Exception{

		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();

		// creamos una sesion a la BD
		Session session = getSession();

		// aramamos nuestro Query a travez de Criteria
		Criteria criteria = session.createCriteria(DTOPuntosAcuerdo.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo", idAcuerdo));
		criteria.add(Restrictions.eq("id.numeralia", numeralia ));
		// eujecutamos la operación en BD
//		resultado = (List<DTOPuntosAcuerdo>) criteria.list();
		
		DTOPuntosAcuerdo dtoPuntosAcuerdo = (DTOPuntosAcuerdo) criteria.uniqueResult();
		return dtoPuntosAcuerdo;
	}
	
	@Override
	public List<DTOPuntosAcuerdo> consultaPuntosAcuerdo(String idNumAcuerdo, Integer idArea) throws Exception {
		//subquery para obtener los puntos asignados de un area (se extraen de Seguimientos_CG ya que ahi estan asignadas las areas)
		DetachedCriteria userSubquery = DetachedCriteria.forClass(DTOSeguimientosCG.class)			   
			    .add(Restrictions.eq("id.idArea",idArea))
			    .add(Restrictions.eq("id.idNumAcuerdo",idNumAcuerdo))
			    .setProjection(Projections.distinct(Projections.property("id.numeralia")));	
		
		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();	

		// creamos una sesion a la BD
		Session session = getSession();

		// query para obtener los puntos asignados de un area coincidentes con el subquery que filtra los puntos del area
		Criteria criteria = session.createCriteria(DTOPuntosAcuerdo.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo", idNumAcuerdo));		
		criteria.add(Restrictions.eq("activo", 1 ));
		criteria.addOrder(Order.asc("id.numeralia"));
		criteria.add(Subqueries.propertyIn("id.numeralia", userSubquery));		
		resultado = (List<DTOPuntosAcuerdo>) criteria.list();
		
			for (DTOPuntosAcuerdo punto : resultado) {				
				//------Se verifica si el punto es de tipo notificacion
				if(punto.getNotificacion().equals(1)){
					//------Se verifica si esta completado
						List<DTOSeguimientosCG> completado = new ArrayList<DTOSeguimientosCG>();
						Criteria criteriaCompletado = session.createCriteria(DTOSeguimientosCG.class);
						criteriaCompletado.add(Restrictions.eq("id.idNumAcuerdo", idNumAcuerdo));		
						criteriaCompletado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
						criteriaCompletado.add(Restrictions.eq("id.idArea", idArea));
						criteriaCompletado.add(Restrictions.eq("id.idEstatusPunto", 7));						
						completado = (List<DTOSeguimientosCG>) criteriaCompletado.list();							
							
							if(completado.size() == 1){
								punto.setSemaforo("palomita");								
								continue; //salta al siguiente punto
							}
							//------Se verifica si esta rechazado
							List<DTOSeguimientosCG> rechazado = new ArrayList<DTOSeguimientosCG>();
							Criteria criteriaRechazado = session.createCriteria(DTOSeguimientosCG.class);
							criteriaRechazado.add(Restrictions.eq("id.idNumAcuerdo", idNumAcuerdo));		
							criteriaRechazado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
							criteriaRechazado.add(Restrictions.eq("id.idArea", idArea));
							criteriaRechazado.addOrder(Order.desc("id.fechaMovimiento"));
							rechazado = (List<DTOSeguimientosCG>) criteriaRechazado.list();
							
										if(rechazado.get(0).getId().getIdEstatusPunto().equals(2)){
											punto.setSemaforo("equis");
											continue; //salta al siguiente punto
										}
									
								//------Se verifica si hay un estatus de finalizado 
								List<DTOSeguimientosCG> finalizados = new ArrayList<DTOSeguimientosCG>();
								Criteria criteriafinalizado = session.createCriteria(DTOSeguimientosCG.class);
								criteriafinalizado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
								criteriafinalizado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
								criteriafinalizado.add(Restrictions.eq("id.idArea", idArea)); // aqui gio
								criteriafinalizado.add(Restrictions.eq("id.idEstatusPunto", 5));
								finalizados = (List<DTOSeguimientosCG>) criteriafinalizado.list();
									
									if(finalizados.size() >= 1){//////////////si por lo menos hay un finalizado
										//se recorren los finalizados por area  y se checa su ultimo movimiento si es 5 entonces se coloca campana
										for (DTOSeguimientosCG puntoFinalizado : finalizados) {
											List<DTOSeguimientosCG> finalizadoArea = new ArrayList<DTOSeguimientosCG>();
											Criteria criteriaFinalizadoArea = session.createCriteria(DTOSeguimientosCG.class);
											criteriaFinalizadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
											criteriaFinalizadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
											criteriaFinalizadoArea.add(Restrictions.eq("id.idArea", puntoFinalizado.getId().getIdArea()));
											criteriaFinalizadoArea.addOrder(Order.desc("id.fechaMovimiento"));
											finalizadoArea =  (List<DTOSeguimientosCG>)criteriaFinalizadoArea.list();
											if(finalizadoArea.get(0).getId().getIdEstatusPunto() == 5){//si el ultimo movimiento de un area continua en finalizado el retorno sera campana		
												punto.setSemaforo("campana");							
											}	
										}
										if(punto.getSemaforo().equalsIgnoreCase("campana")){
											continue; //salta al siguiente punto
										}
									}
								
								//------------
								
								
								//si no fue ninguno de los estados anteriores se evaluara su temporalidad
									Date fechaInicio = new Date();
									Criteria criteriaFecha = session.createCriteria(DTOSeguimientosCG.class);
									criteriaFecha.add(Restrictions.eq("id.idNumAcuerdo", idNumAcuerdo));		
									criteriaFecha.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
									criteriaFecha.add(Restrictions.eq("id.idArea", idArea));
									criteriaFecha.setProjection(Projections.min("id.fechaMovimiento"));
									fechaInicio = (Date) criteriaFecha.uniqueResult();
									
									double diferenciaDias = (double) ((new Date().getTime()-fechaInicio.getTime())/86400000);
								
									if(diferenciaDias <= 30.4 ){
										punto.setSemaforo("verde");
									}
									if(diferenciaDias > 30.4 && diferenciaDias <= 30.4*2 ){
										punto.setSemaforo("amarillo");
									}
									if(diferenciaDias > 30.4*2 ){
										punto.setSemaforo("rojo");
									}
								
				}else{//si no es de tipo notifiacion su icono sera gris
					//se verifica si su ultimo movimiento es Reanudado
					List<DTOSeguimientosCG> reanudadoArea = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaReanudadoArea = session.createCriteria(DTOSeguimientosCG.class);
					criteriaReanudadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
					criteriaReanudadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
					criteriaReanudadoArea.add(Restrictions.eq("id.idArea", idArea));
					criteriaReanudadoArea.addOrder(Order.desc("id.fechaMovimiento"));
					reanudadoArea =  (List<DTOSeguimientosCG>)criteriaReanudadoArea.list();	
					if(reanudadoArea.get(0).getId().getIdEstatusPunto() == 6){//6 igual a Reanudado	
						punto.setSemaforo("gris");	
						continue;
					}	

					List<DTOSeguimientosCG> completadoGris = new ArrayList<DTOSeguimientosCG>();
					Criteria criteriaCompletado = session.createCriteria(DTOSeguimientosCG.class);
					criteriaCompletado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
					criteriaCompletado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
					criteriaCompletado.add(Restrictions.eq("id.idArea", idArea));
					criteriaCompletado.add(Restrictions.eq("id.idEstatusPunto", 7));						
					completadoGris = (List<DTOSeguimientosCG>) criteriaCompletado.list();		
					
					if(completadoGris.size() == 1){
						punto.setSemaforo("palomita");								
						continue; //salta al siguiente punto
					}					
					   //se verifica si esta rechazado
						List<DTOSeguimientosCG> rechazadoGris = new ArrayList<DTOSeguimientosCG>();
						Criteria criteriaRechazado = session.createCriteria(DTOSeguimientosCG.class);
						criteriaRechazado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
						criteriaRechazado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
						criteriaCompletado.add(Restrictions.eq("id.idArea", idArea));
						criteriaRechazado.add(Restrictions.eq("id.idEstatusPunto", 2));
						rechazadoGris = (List<DTOSeguimientosCG>) criteriaRechazado.list();
						    
							if(rechazadoGris.size() >= 1){ //si por lo menos hay un rechazado se verifica si tambien hay un reasignado
								List<DTOSeguimientosCG> reasignado = new ArrayList<DTOSeguimientosCG>();
								Criteria criteriaReasignado = session.createCriteria(DTOSeguimientosCG.class);
								criteriaReasignado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
								criteriaReasignado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
								criteriaReasignado.add(Restrictions.eq("id.idArea", idArea));
								criteriaReasignado.add(Restrictions.eq("id.idEstatusPunto", 3));
								reasignado = (List<DTOSeguimientosCG>) criteriaReasignado.list();
									
									if(reasignado.size() == 1){ // si esta reasignado al ser un movimiento posterior al rechazo pasara a gris nuevamente
										punto.setSemaforo("gris");	
										continue; //salta al siguiente punto	
									}else{ // si no esta reasignado entonces su ultimo movimiento fue el rechazo y se asigna la equis
										punto.setSemaforo("equis");
										continue; //salta al siguiente punto
									}								
							}							
							
							//------Se verifica si hay un estatus de finalizado 
							List<DTOSeguimientosCG> finalizados = new ArrayList<DTOSeguimientosCG>();
							Criteria criteriafinalizado = session.createCriteria(DTOSeguimientosCG.class);
							criteriafinalizado.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
							criteriafinalizado.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
							criteriafinalizado.add(Restrictions.eq("id.idArea", idArea)); // aqui gio
							criteriafinalizado.add(Restrictions.eq("id.idEstatusPunto", 5));
							finalizados = (List<DTOSeguimientosCG>) criteriafinalizado.list();
							if(finalizados.size() >= 1){//////////////si por lo menos hay un finalizado
								//se recorren los finalizados por area  y se checa su ultimo movimiento si es 5 entonces se coloca campana
								for (DTOSeguimientosCG puntoFinalizado : finalizados) {
									
									List<DTOSeguimientosCG> finalizadoArea = new ArrayList<DTOSeguimientosCG>();
									Criteria criteriaFinalizadoArea = session.createCriteria(DTOSeguimientosCG.class);
									criteriaFinalizadoArea.add(Restrictions.eq("id.idNumAcuerdo", punto.getId().getIdNumAcuerdo()));		
									criteriaFinalizadoArea.add(Restrictions.eq("id.numeralia", punto.getId().getNumeralia()));
									criteriaFinalizadoArea.add(Restrictions.eq("id.idArea", puntoFinalizado.getId().getIdArea()));
									criteriaFinalizadoArea.addOrder(Order.desc("id.fechaMovimiento"));
									finalizadoArea =  (List<DTOSeguimientosCG>)criteriaFinalizadoArea.list();
									if(finalizadoArea.get(0).getId().getIdEstatusPunto() == 5){//si el ultimo movimiento de un area continua en finalizado el retorno sera campana		
										punto.setSemaforo("campana");	
										
									}	
								}
								if(punto.getSemaforo().equals("campana"));
								continue; //salta al siguiente punto
							}//
						punto.setSemaforo("gris");				
					}				
			}			
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOPuntosAcuerdo> obtenerPuntosAcuerdo(String idNumAcuerdo) throws Exception {

		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOPuntosAcuerdo.class);
		criteria.add(Restrictions.eq("id.idNumAcuerdo", idNumAcuerdo));
		criteria.add(Restrictions.eq("activo", 1));
		resultado = (ArrayList<DTOPuntosAcuerdo>) criteria.list();

		return resultado;
	}
	
	
	@Override
	public Integer obtenerPuntosAcuerdoPorIDClasif(Integer idClasif) {

		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();
		// Se crea una sesión para la base de datos
		Session session = getSession();
		// Se crea el query a través de criteria
		Criteria criteria = session.createCriteria(DTOPuntosAcuerdo.class);
		criteria.add(Restrictions.eq("idClasificacion", idClasif));
		criteria.add(Restrictions.eq("activo", 1));
		resultado = (ArrayList<DTOPuntosAcuerdo>) criteria.list();

		return resultado.size();
	}
	
	

}
