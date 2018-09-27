/**
 * @(#)MBSeguimiento
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 * 
 * @author  Angel Omar Medel Hernandez	
 * @since   26/10/2017
 * 
 * MB para el modulo de Reportes de Seguimiento
 */

package mx.ine.acuerdos.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.bsd.impl.BSDConvocatoria;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.admin.DTOModulo;
import mx.ine.acuerdos.dto.helper.HelperReporteSeguimiento;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@ManagedBean(name="mbReporteSeguimiento")
@ViewScoped
public class MBSeguimiento extends MBGeneric implements Serializable{	
	
	private static final long serialVersionUID = -527044921901045692L;
	private static final Log log = LogFactory.getLog(MBSeguimiento.class);
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	@Autowired
	@Qualifier("asConvocatoria")
	private transient ASConvocatoriaInterface asConvocatoria;
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	private String menu;
	HelperReporteSeguimiento pagiselect;
	
	List<DTOModulo> modulosReportes;
	
	/**
	 * Metodo  para inicializar   el arreglo de menus
	 * @throws ClienteWebServiceException 
	 * */
	@PostConstruct
	public void init() {
		// recuperamos el menu del sistema y con ello los modulos validos de reportes para el usuario logeado	
		try {
			mbAdmin.obtenMenuAcuerdos();
		} catch (ClienteWebServiceException e) {
			log.error("MBSeguimiento.init: " + e.getMessage());
		}

		mbAdmin.setImgsInfografias(null);
		modulosReportes = mbAdmin.getReportesAcuerdos();
	}
	
	/***
	 * METODO PARA IR POR LAS  PAGINAS
	 * 
	 * */
	public void seleccionarReporte() throws IOException {
	
		if (!menu.equals("0")){
			
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    ec.redirect(ec.getRequestContextPath() + menu);	
		
		}
	}
	
	// metodo para validar los roles permitidos
	public boolean validaRoles(){	
		
		boolean rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )				
    	   ){
			
			rolValido = true;			
    	}
    
		return rolValido;
	}
	
	
	/**
	 * Metodo que recupera el Rol del usuario en sesion y ve si es de tipo titular ara o area
	 * @author Giovanni Hernández
	 * @since 10/12/2017
	 * @param 
	 * @return boolean es de tipo titular area o area
	 * **/
	public boolean esTitularArea(){
		
		boolean rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )			
    			
    	   ){
			
			rolValido = true;			
    	}
    
		return rolValido;
		
	}
	
	public boolean tieneArea(){
		
		boolean tieneArea = false;
		
		try {
		
			DTOResponsables responsArea = asConvocatoria.recuperarDtoResponsable(this.obtenUsuario().getUsername());
		
			if(!(responsArea == null)) {
				tieneArea = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tieneArea;
	}
	
	/**
	 * Metodos GET Y SET 
	 * */
	public String getMenu() {
		return menu;
	}


	public void setMenu(String menu) {
		this.menu = menu;
	}

	public HelperReporteSeguimiento getPagiselect() {
		return pagiselect;
	}

	public void setPagiselect(HelperReporteSeguimiento pagiselect) {
		this.pagiselect = pagiselect;
	}

	public List<DTOModulo> getModulosReportes() {
		return modulosReportes;
	}

	public void setModulosReportes(List<DTOModulo> modulosReportes) {
		this.modulosReportes = modulosReportes;
	}
}

