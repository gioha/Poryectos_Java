package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASCClasificacionConsultaInterface;
import mx.ine.acuerdos.dao.DAOCClasificacionesInterface;
import mx.ine.acuerdos.dto.DTOCClasificaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Scope("prototype")
@Service("asCClasificacionConsulta")
public class ASCClasificacionConsulta implements ASCClasificacionConsultaInterface{
	
	@Autowired
	@Qualifier("daoCClasifiaciones")
	private transient DAOCClasificacionesInterface daoClasificaciones;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCClasificaciones> recuperaClasificaciones() throws Exception {
		return daoClasificaciones.consultaClasificaiones();
	}
	
	
}
