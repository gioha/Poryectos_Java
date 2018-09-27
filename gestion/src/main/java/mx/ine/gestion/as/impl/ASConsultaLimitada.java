/**
 * @(#)ASConsultaLimitada.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASConsultaLimitadaInterface;
import mx.ine.gestion.dao.inter.DAOConsultaLimitadaInterface;
import mx.ine.gestion.dto.helper.DTOResultadoConsultaHelper;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de consulta
 *
 * @autor INE
 * @copy MAVO
 * @since 12/07/2016
 */
@Component("asConsultaLimitada")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASConsultaLimitada implements ASConsultaLimitadaInterface {

	/**
	 * Objeto que se utiliza para imprimir mensajes de la aplicación 
	 */
	private static final Logger log = Logger.getLogger(ASConsultaLimitada.class);
	
	@Autowired
	@Qualifier("daoConsultaLimitada")
	private DAOConsultaLimitadaInterface daoConsultaLimitadaInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.as.ASConsultaLimitadaInterface#ejecutaConsulta(java.lang.String)
	 */
	@Override
	public List<DTOResultadoConsultaHelper> ejecutaConsulta(String consulta) {

		List<DTOResultadoConsultaHelper> tabla;
		DTOResultadoConsultaHelper dtoMa = new DTOResultadoConsultaHelper();
		
		try {
			
			tabla = new ArrayList<DTOResultadoConsultaHelper>();
			List<Map<String,Object>> aliasToValueMapList = daoConsultaLimitadaInterface.haciendoMagia(consulta);

			//Para el caso en que este vacia la tabla
			if (aliasToValueMapList.isEmpty()) {
			
				tabla = new ArrayList <DTOResultadoConsultaHelper>();				
				List<String> lista = new ArrayList <String>();
				dtoMa = new DTOResultadoConsultaHelper();
				String msj = "No existen datos";
				lista.add(msj);
				dtoMa.setCmps(lista);
				tabla.add(dtoMa);
				
				return tabla;
			}
			
			//obtenemos headers
			List<String> listaHeader =  new ArrayList <String>();
			DTOResultadoConsultaHelper dtoHeader = new DTOResultadoConsultaHelper();
			Map<String,Object> mapaHeader = aliasToValueMapList.get(0);
			for (Map.Entry<String,Object> entry : mapaHeader.entrySet()){
				listaHeader.add(entry.getKey().toString());
			}
			dtoHeader.setCmps(listaHeader);
			tabla.add(dtoHeader);
	
			//obtenemos valores
			for (Map<String,Object> map : aliasToValueMapList){
				List<String> listaValue =  new ArrayList <String>();
				DTOResultadoConsultaHelper dtoValue = new DTOResultadoConsultaHelper();
			    for (Map.Entry<String,Object> entry : map.entrySet()){
			    	if (entry.getValue() != null)
			    		listaValue.add(entry.getValue().toString());
			    	else
			    		listaValue.add(null);
			    }
			    dtoValue.setCmps(listaValue);
			    tabla.add(dtoValue);
			}
			
			return tabla;
			
		} catch (HibernateException he) {
			
			SessionFactoryUtils.convertHibernateAccessException(he);
            log.error("Error al usar la magia",he);
			tabla = new ArrayList <DTOResultadoConsultaHelper>();				
			List<String> lista = new ArrayList <String>();
			dtoMa = new DTOResultadoConsultaHelper();
			String msj = "Error en el query HibernateException--->"+he.getCause() + "--"+he.getMessage();
			lista.add(msj);
			dtoMa.setCmps(lista);
			tabla.add(dtoMa);
			
			return tabla;
			
		} catch (Exception ex) {
		
			log.error("Error al hacer magia",ex);
			tabla = new ArrayList <DTOResultadoConsultaHelper>();				
			List<String> lista =  new ArrayList <String>();
			dtoMa= new DTOResultadoConsultaHelper();
			String msj= "Error en el query --->"+ex.getMessage();
			lista.add(msj);
			dtoMa.setCmps(lista);
			tabla.add(dtoMa);

			return tabla;
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.as.ASConsultaLimitadaInterface#ejecutaOperacion(java.lang.String)
	 */
	@Override
	public List<DTOResultadoConsultaHelper> ejecutaOperacion(String query) {

		List<DTOResultadoConsultaHelper> tabla;
		DTOResultadoConsultaHelper dtoValue;
		int resultado;
		List<String> listaResultado;
		String operacion;
		
		try {

			resultado = daoConsultaLimitadaInterface.usandoElPoder(query);
			operacion = new Integer(resultado).toString();
			if (resultado==0){
				operacion="Operación fallida";
			}else{
				operacion="Operación exitosa: "+resultado+" filas afectadas";
			}
			
			listaResultado = new ArrayList<>();
			dtoValue = new DTOResultadoConsultaHelper();
			
			
			tabla = new ArrayList<>();
			listaResultado.add(operacion);
			dtoValue.setCmps(listaResultado);
			tabla.add(dtoValue);	
			
			return tabla;
			
		} catch (HibernateException he) {
			
			SessionFactoryUtils.convertHibernateAccessException(he);
            log.error("Error al usar el poder",he);
			tabla = new ArrayList <DTOResultadoConsultaHelper>();				
			List<String> lista =  new ArrayList <String>();
			dtoValue= new DTOResultadoConsultaHelper();
			String msj= "Error en el query HibernateException--->"+he.getCause() + "--"+he.getMessage();
			lista.add(msj);
			dtoValue.setCmps(lista);
			tabla.add(dtoValue);

			return tabla;

		} catch (SQLException sqle) {
			
			SessionFactoryUtils.convertHibernateAccessException(new JDBCException(sqle.toString(), sqle));
            log.error("Error al usar el poder",sqle);
			tabla = new ArrayList <DTOResultadoConsultaHelper>();				
			List<String> lista =  new ArrayList <String>();
			dtoValue= new DTOResultadoConsultaHelper();
			String msj= "Error en el query SQLException--->"+sqle.getErrorCode() + "->"+sqle.getMessage() ;
			lista.add(msj);
			dtoValue.setCmps(lista);
			tabla.add(dtoValue);

			return tabla;

		} catch(Exception ex){
			
			log.error("Error al usar el poder",ex);
			tabla = new ArrayList <DTOResultadoConsultaHelper>();				
			List<String> lista =  new ArrayList <String>();
			dtoValue= new DTOResultadoConsultaHelper();
			String msj= "Error en el query--->"+ex.getMessage();
			lista.add(msj);
			dtoValue.setCmps(lista);
			tabla.add(dtoValue);

			return tabla;

		}
		
	}
}
