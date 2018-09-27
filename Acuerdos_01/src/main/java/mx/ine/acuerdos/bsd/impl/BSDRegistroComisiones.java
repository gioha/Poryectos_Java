package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASRegistroComisionesInterface;
import mx.ine.acuerdos.bsd.BSDRegistroComisionesInterface;
import mx.ine.acuerdos.dto.DTOComisiones;

@Component("bsdRegComisiones")
@Scope("prototype")
public class BSDRegistroComisiones implements BSDRegistroComisionesInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8184171268648668755L;
	private static final Log log = LogFactory.getLog(BSDRegistroComisiones.class);
	
	@Autowired
	@Qualifier("asRegComisiones")
	private transient ASRegistroComisionesInterface asRegComisiones;

	@Override
	public List<DTOComisiones> obtenerTodasComisionesActivas() {	
		try {
			return asRegComisiones.obtenerTodasComisionesActivas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<DTOComisiones> obtenerTodasComisionesInactivas() {		
		try {
			return asRegComisiones.obtenerTodasComisionesInactivas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean guardarComision(DTOComisiones comision, int motivo) {		
		try {
			return asRegComisiones.guardarComision(comision, motivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
