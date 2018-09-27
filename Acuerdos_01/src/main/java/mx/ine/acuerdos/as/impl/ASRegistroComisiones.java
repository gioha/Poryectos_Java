package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASRegistroComisionesInterface;
import mx.ine.acuerdos.dao.DAOComisionesInterface;
import mx.ine.acuerdos.dto.DTOComisiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service("asRegComisiones")
public class ASRegistroComisiones implements ASRegistroComisionesInterface{
	
	@Autowired
	@Qualifier("daoComisiones")
	private transient DAOComisionesInterface daoComisiones;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOComisiones> obtenerTodasComisionesActivas() throws Exception {		
		return daoComisiones.obtenerTodasComisionesActivas();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOComisiones>obtenerTodasComisionesInactivas() throws Exception {		
		return daoComisiones.obtenerTodasComisionesInactivas();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public boolean guardarComision(DTOComisiones comision, int motivo) throws Exception {		
		return daoComisiones.guardarComision(comision, motivo);
	}

}
