package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASCClasificacionConsultaInterface;
import mx.ine.acuerdos.bsd.BSDCClasificacionConsultaInterface;
import mx.ine.acuerdos.dto.DTOCClasificaciones;


@Component("bsdCClasificacionConsulta")
@Scope("prototype")
public class BSDCClasificacionConsulta implements BSDCClasificacionConsultaInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2727639002272485935L;

	private static final Log log = LogFactory.getLog(BSDCClasificacionConsulta.class);
	
	@Autowired
	@Qualifier("asCClasificacionConsulta")
	private transient ASCClasificacionConsultaInterface asCClasificacionConsulta;
	
	
	@Override
	public List<DTOCClasificaciones> recuperaClasficacionesPunto() {
		try{	
			return asCClasificacionConsulta.recuperaClasificaciones();
		} catch (Exception e) {
			e.printStackTrace();
			log.info(" BSDCClasificacion.asCClasificacionConsulta :" + e.getMessage());
			return null;
		}
	}
	
	
	/**
     * Estaclecer lista de nombres de imagenes de Infografías
     * @param void
     * @return List<String>
   */
	@Override
	public List<String> recuperaImgsInfografias(){
		
		try{
//		List<String> imgInf = new ArrayList<String>();
//		imgInf.add("registroAcuerdo/Infografia_Reg_Ac_01.png");
//		imgInf.add("registroAcuerdo/Infografia_Reg_Ac_02.png");
//		return  (List<String>) imgInf;
			return  null;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(" BSDCClasificacion.recuperaImgsInfografias :" + e.getMessage());
			return null;
		}
		
	}	

}
