/**
 * @(#)DAOReporteSeguimiento.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */


package mx.ine.acuerdos.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import mx.ine.acuerdos.dao.DAOReporteSeguimientoInterface;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOSeguimiento;

import org.hibernate.SQLQuery;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
@Scope("prototype")
@Repository("daoReporteSeguimiento")
public class DAOReporteSeguimiento extends DAOGeneric<DTOSeguimiento,Integer> implements DAOReporteSeguimientoInterface,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6334187760515559589L;
	private static Logger log = Logger.getLogger(DAOReporteSeguimiento.class);
	
	
	@Override
	public ArrayList<String> listaAnios(){	
		
		ArrayList<String> listaAnios = new ArrayList<String>();
		try {
			
		SQLQuery query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.lista.anios"));
		List<Object> resultado = query.list();
		
		
		for(Object ob: resultado) {
			listaAnios.add(ob.toString());		
		}
		
		return listaAnios;
		}catch(Exception e) {
			e.printStackTrace();
			return listaAnios;
		}	
	}
	
	
	@Override
	public ArrayList<String> listaAnios(Integer idArea){	
		ArrayList<String> listaAnios = new ArrayList<String>();
		try {
	    
		SQLQuery query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.lista.anios.area"));
		query.setParameter("area", idArea);
		List<Object> resultado = query.list();
		
		
		for(Object ob: resultado) {
			listaAnios.add(ob.toString());		
		}
		
		return listaAnios;
		}catch(Exception e) {
			e.printStackTrace();
			return listaAnios;
		}	
	}
	/**
	 * METODO QUE TRAE LA LISTA DE TODOS LOS ACUERDOS PARA
	 * EL REPORTE DE SEGUIMIENTO 
	 * */
	@Override
	public List<DTOSeguimiento> buscarAcuerdosSeguimiento(String date, int tipoDocumento,Integer idArea){
		try {
			
			SimpleDateFormat formatoES = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			String fecha1= date+"/01/01";
			String fecha2 = date+"/12/31";
			Calendar cal2 = new GregorianCalendar(Integer.parseInt(date),Calendar.DECEMBER,31,0,0,0);
			cal2.add(Calendar.DAY_OF_YEAR, 1);
			Date fechaSesion = null;
			
			//mx.ine.acuerdos.util.Utilidades.obtenArregloCadenaString(fecha1);
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			
			
			List<DTOSeguimiento> listDTOSeguimiento = new ArrayList<DTOSeguimiento>();
			DTOSeguimiento dtoSeguimiento;
			SQLQuery query;
			
			if(idArea==null) {
				if(tipoDocumento!=0) {
					
					query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.seguimiento.fecha.documento"));
					query.setParameter("fechaIn", fecha1);
					query.setParameter("fechaFin",format.format(cal2.getTime()));
					query.setParameter("documento", tipoDocumento);
				}else {
					
					query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.seguimiento.fecha"));
					query.setParameter("fechaIn", fecha1);
					query.setParameter("fechaFin",format.format(cal2.getTime()));
				}
				
			}else {
				if(tipoDocumento!=0) {
					
					query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.seguimiento.fecha.documento.area"));
					query.setParameter("fechaIn", fecha1);
					query.setParameter("fechaFin",format.format(cal2.getTime()));
					query.setParameter("documento", tipoDocumento);
					query.setParameter("area", idArea);
				}else {
					
					query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.seguimiento.fecha"));
					query.setParameter("fechaIn", fecha1);
					query.setParameter("fechaFin",format.format(cal2.getTime()));
				}				
			}		
					
				
				
				
					
			List<Object[]> resultado = (List<Object[]>) query.list();
			
			
			for(Object[] ob: resultado) {
				dtoSeguimiento = new DTOSeguimiento();
				dtoSeguimiento.setAcuerdo(ob[0]==null?"":ob[0].toString());
				try {
					dtoSeguimiento.setPunto(ob[5]==null?"":   mx.ine.acuerdos.util.Utilidades.clobToString(ob[5]) );
				}catch(Exception e) {
					dtoSeguimiento.setPunto(ob[5]==null?"":   ob[5].toString());
				}
				try {
					fechaSesion = formatoSQL.parse(ob[18].toString());
					dtoSeguimiento.setFechaEmision(formatoES.format(fechaSesion));
				}catch(Exception e) {
					dtoSeguimiento.setFechaEmision("");
					log.error("DAOReporteSeguimiento.buscarAcuerdosSeguimiento Error en el metodo que da formato fecha  en español");
					e.printStackTrace();
					
				}
				
				dtoSeguimiento.setFecha( formatoDeFecha.parse(ob[7].toString()));
				dtoSeguimiento.setEstatus(ob[11]==null?"":ob[11].toString());
				dtoSeguimiento.setArea(ob[10]==null?"":ob[10].toString());
				dtoSeguimiento.setTematica(ob[9]==null?"":ob[9].toString());
				dtoSeguimiento.setAnio(formatoAnio.format(fechaSesion));
				dtoSeguimiento.setNombreAcuerdo(ob[1]==null?"":ob[1].toString());
				dtoSeguimiento.setTextoPunto(ob[3]==null?"":mx.ine.acuerdos.util.Utilidades.clobToString(ob[3]));
				dtoSeguimiento.setAreaSiglas(ob[12]==null?"":ob[12].toString());
				dtoSeguimiento.setTipoSesion(ob[17]==null?"":ob[17].toString());
				//validamos si se tiene el tipo de documento para  ir por los datos
				if(tipoDocumento!=0) {
					dtoSeguimiento.setIdTipoDocumento(ob[13]==null?0:Integer.parseInt(ob[13].toString()));//13
					dtoSeguimiento.setDocumentoTipo(ob[14]==null?"":ob[14].toString());//14
				}
				
				dtoSeguimiento.setMovimientosHistorico(getMovimientosAcuerdo(Integer.parseInt(ob[15].toString()),ob[0].toString(), Integer.parseInt(ob[4].toString())));
				listDTOSeguimiento.add(dtoSeguimiento);
			}
			return listDTOSeguimiento;
			
		}catch(Exception e) {
			log.error("Error en el metodo buscarAcuerdosSeguimiento " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * METODO PARA IR POR EL TIPO DE SEGUIMIENTO QUE SE DESEA VISUALIZAR ACUERDO O RESOLUCION
	 * @PARAMS String anio
	 * @return List<String>
	 */
	@Override
	public  List<DTOCTipoDocumento> tipoSeguimientoList(String date) {
		try {
			String fecha1 = date+"/01/01";
			String fecha2 = date+"/12/31";
			List<DTOCTipoDocumento> listTipoSeguimiento = new ArrayList<DTOCTipoDocumento>();
			 
			
			SQLQuery query = getSession().createSQLQuery(this.getContainer().getQuery("query.tipo.reporte.anio"));
			query.setParameter("fechaIn", fecha1);
			query.setParameter("fechaFin", fecha2);
			
			List<Object[]> resultado = (List<Object[]>) query.list();
			
			
			DTOCTipoDocumento aux;
			for(Object[] ob: resultado) {
				aux = new  DTOCTipoDocumento();
				aux.setIdTipoDocumento(Integer.parseInt(ob[1].toString()));
				aux.setTipo(ob[0]==null?"":ob[0].toString());
				listTipoSeguimiento.add(aux);
			}
			return listTipoSeguimiento;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * METODO PARA IR POR EL HISTORICO DE MOVIMIENTOS DE UN ACUERDO
	 * @param String 
	 * **/
		
	private String getMovimientosAcuerdo(int id_area, String numAcuerdo, int numeralia){
		try {
			
			StringBuilder movimientos = new StringBuilder();
			SQLQuery query = getSession().createSQLQuery(this.getContainer().getQuery("query.reporte.seguimiento.acuerdo.historico"));
			query.setParameter("acuerdo", numAcuerdo);
			query.setParameter("area", id_area);
			query.setParameter("numeralia", numeralia);
			//query.setParameter("fechaIn", fechaIn);
			//query.setParameter("fechaFin", fechaFin);
			
			List<Object[]> resultado = (List<Object[]>) query.list();
			
			for(int i=0;i<resultado.size();i++) {
			//for(Object[] ob: resultado) {
				Object[] ob =  resultado.get(i);
				movimientos.append(ob[1]==null?"":(ob[1].toString()+".- " + (ob[2]==null?"":ob[2].toString()+"\n")));
			}
			
			return movimientos.toString();
		}catch(Exception e) {
			log.error("Error en DAOReporteSeguimiento.getMovimientosAcuerdo " + e.getMessage());
			e.printStackTrace();
			return "";
		}
		
	}
	

}
