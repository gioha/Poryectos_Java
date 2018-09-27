package mx.ine.acuerdos.dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mx.ine.acuerdos.dao.DAOGraphicsIndexInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOGraphicAcuerdos;

import org.hibernate.SQLQuery;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



@Scope("prototype")
@Repository("daoGraphicsIndex")
public class DAOGraphicsIndex extends DAOGeneric<DTOAcuerdos,Integer> implements DAOGraphicsIndexInterface {
	private static Logger log = Logger.getLogger(DAOGraphicsIndex.class);

	
	
	@Override
	public List<DTOAcuerdos> AcuerdosAnio(int tipoDocumento) throws Exception{
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DAY_OF_YEAR, 1);
		SimpleDateFormat anio = new SimpleDateFormat("yyyy");
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal1 = new GregorianCalendar(Integer.parseInt(anio.format(cal2.getTime())),0,1,0,0,0);
		
		
		try {
			
		
		List<DTOAcuerdos> listDTOAcuerdos = new ArrayList<DTOAcuerdos>();
		DTOAcuerdos acuerdo;
		SQLQuery query;	
		
		query = getSession().createSQLQuery(this.getContainer().getQuery("query.graphics.acuerdos"));
		
		query.setParameter("fechaIn", fecha.format(cal1.getTime()));
		query.setParameter("fechaFin", fecha.format(cal2.getTime()));
		query.setParameter("tipoDocumento", tipoDocumento);
		List<Object[]> resultado = (List<Object[]>) query.list();
		
		for(Object[] aux: resultado) {
			acuerdo = new DTOAcuerdos();
			acuerdo.setIdNumAcuerdo(aux==null?"":aux[0].toString());
			acuerdo.setFechaSesion((Date) aux[1]);
			acuerdo.setFechaHora((Date) aux[2]);
			listDTOAcuerdos.add(acuerdo);
		}
		
			return listDTOAcuerdos;
		}catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<DTOGraphicAcuerdos> acuerdosPuntosEstatus(int tipoDocumento){
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DAY_OF_YEAR, 1);
		SimpleDateFormat anio = new SimpleDateFormat("yyyy");
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal1 = new GregorianCalendar(Integer.parseInt(anio.format(cal2.getTime())),0,1,0,0,0);
		
		try {
			
			
			List<DTOGraphicAcuerdos> listDTOAcuerdos = new ArrayList<DTOGraphicAcuerdos>();
			DTOGraphicAcuerdos acuerdo;
			SQLQuery query;	
			
			query = getSession().createSQLQuery(this.getContainer().getQuery("query.graphics.acuerdos.estatus.puntos"));
			
			query.setParameter("fechaIn", fecha.format(cal1.getTime()));
			query.setParameter("fechaFin", fecha.format(cal2.getTime()));
			query.setParameter("tipoDocumento", tipoDocumento);
			List<Object[]> resultado = (List<Object[]>) query.list();
			
			for(Object[] aux: resultado) {
				acuerdo = new DTOGraphicAcuerdos();
				acuerdo.setIdNumAcuerdo(aux[0]==null?"":aux[0].toString());
				try {
					acuerdo.setIdTipoDocumento(aux[3]==null?0:Integer.parseInt(aux[3].toString()));
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				acuerdo.setNumeralia(aux[5]==null?0:Integer.parseInt(aux[5].toString()));
				acuerdo.setIdEstatusPunto(aux[6]==null?0:Integer.parseInt(aux[6].toString()));
				acuerdo.setEstatus(aux[7]==null?"":aux[7].toString());
				acuerdo.setIdArea(aux[4]==null?0:Integer.parseInt(aux[4].toString()));
				listDTOAcuerdos.add(acuerdo);
			}
			
				return listDTOAcuerdos;
			}catch(Exception e) {
				log.error("DAOGraphicsIndex.AcuerdosAnio "+ e.getMessage());
				e.printStackTrace();
				return null;
			}
		
		
		
	}
	
	

}
