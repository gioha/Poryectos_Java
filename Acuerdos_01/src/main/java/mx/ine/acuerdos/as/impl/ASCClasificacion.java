package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASCClasificacionInterface;
import mx.ine.acuerdos.dao.DAOCClasificacionesInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Scope("prototype")
@Service("asCClasificacion")
public class ASCClasificacion implements ASCClasificacionInterface{

	@Autowired
	@Qualifier("daoCClasifiaciones")
	private transient DAOCClasificacionesInterface daoClasificaciones;
	
	
	@Autowired
	@Qualifier("daoPuntos")
	private transient DAOPuntosInterface daoPunto;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCClasificaciones> recuperaClasificaciones() throws Exception {
		return daoClasificaciones.consultaClasificaiones();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public String actualizarClasif(DTOCClasificaciones dtoClasif){
		
		try{
			return daoClasificaciones.actualizarClasif(dtoClasif);
	
		} catch (Exception e) {
			
			e.printStackTrace();
			return Constantes.NO_REGISTRO_RG;
			
		}
		
	}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public String eliminarClasif(DTOCClasificaciones dtoClasif){
		try{
			return daoClasificaciones.eliminarClasif(dtoClasif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constantes.NO_REGISTRO_RG;
		}
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public Integer verificaIdClasif(Integer idClasificacion) {

		try{
			return daoPunto.obtenerPuntosAcuerdoPorIDClasif(idClasificacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	
}
