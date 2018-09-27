package mx.ine.acuerdos.as.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.as.ASGraphicsIndexInterface;
import mx.ine.acuerdos.dao.DAOGraphicsIndexInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dto.DTOGraphicAcuerdos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Scope("prototype")
@Service("asGraphicsIndex")
public class ASGraphicsIndex  implements ASGraphicsIndexInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -301283567461703659L;

	@Autowired
	@Qualifier("daoPuntos")
	private transient DAOPuntosInterface daoPuntos;
	
	@Autowired
	@Qualifier("daoGraphicsIndex")
	private transient DAOGraphicsIndexInterface daoGraphicsIndex;

	/*@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOPuntosAcuerdo> consultaPuntosAcuerdo(String idNumAcuerdo) throws Exception{			
		List<DTOPuntosAcuerdo> resultado = new ArrayList<DTOPuntosAcuerdo>();
		resultado = daoPuntos.consultaPuntosAcuerdo(idNumAcuerdo);	
		return resultado;
		}*/
	
	
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOAcuerdos> AcuerdosAnio(int tipoDocumento) throws Exception{		
		List<DTOAcuerdos> acuerdos = new ArrayList<DTOAcuerdos>();
		return acuerdos;
		
		
	}*/
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOGraphicAcuerdos> acuerdosPuntosEstatus(int tipoDocumento) throws Exception{
		
		
		List<DTOGraphicAcuerdos> acuerdos = new ArrayList<DTOGraphicAcuerdos>();
		acuerdos=  daoGraphicsIndex.acuerdosPuntosEstatus(tipoDocumento);
		
		
		return acuerdos;
		
		
	}
}


