package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.bsd.BSDCClasificacionConsultaInterface;
import mx.ine.acuerdos.dto.helper.form.HLPFormCClasificaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class MBCClasificacionesConsulta extends MBGeneric implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2038871833319205313L;


	@Autowired
	@Qualifier("bsdCClasificacionConsulta")
	private transient BSDCClasificacionConsultaInterface bsdCClasificacionCon;
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	
	private HLPFormCClasificaciones form;
	
	public void init(){
		
		try{	
			form = new HLPFormCClasificaciones();
			form.setClasificacion(bsdCClasificacionCon.recuperaClasficacionesPunto());
			
			//lista de nombres de imagenes de inforgrafias
			form.setListaImgsInfografias(bsdCClasificacionCon.recuperaImgsInfografias());
			mbAdmin.setImgsInfografias(form.getListaImgsInfografias());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public HLPFormCClasificaciones getForm() {
		return form;
	}


	public void setForm(HLPFormCClasificaciones form) {
		this.form = form;
	}


	public BSDCClasificacionConsultaInterface getBsdCClasificacionCon() {
		return bsdCClasificacionCon;
	}


	public void setBsdCClasificacionCon(
			BSDCClasificacionConsultaInterface bsdCClasificacionCon) {
		this.bsdCClasificacionCon = bsdCClasificacionCon;
	}




}
