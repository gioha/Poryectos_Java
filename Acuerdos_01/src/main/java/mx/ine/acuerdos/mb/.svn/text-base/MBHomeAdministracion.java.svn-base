package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.ine.acuerdos.dto.admin.DTOModulo;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBHomeAdministracion extends MBGeneric implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2055230102370945104L;
	
	private static Logger log = Logger.getLogger(MBHomeAdministracion.class);
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	List<DTOModulo> modulosCatalogos;
	private List<String> listaImgsInfografias;
	
	private String menu;
	private boolean rolValido;
	
	@PostConstruct
	public void init() throws ClienteWebServiceException {
		
		rolValido = false;
		this.listaImgsInfografias = new ArrayList<String>();
		
		// recuperamos el menu del sistema y con ello los modulos validos de reportes para el usuario logeado	
		mbAdmin.obtenMenuAcuerdos();
		
		modulosCatalogos = mbAdmin.getCatalogosAcuerdos();
		
		//lista de nombres de imagenes de inforgrafias
		listaImgsInfografias = null;
		mbAdmin.setImgsInfografias(listaImgsInfografias);
		
	}
	
	/***
	 * METODO PARA IR POR LAS  PAGINAS
	 * 
	 * */
	public void seleccionarCatalogo() {
		
		try{
	
			if (!menu.equals("0")){
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			    ec.redirect(ec.getRequestContextPath() + menu);
			}
			
		} catch (Exception e) {
			log.error(" Error al seleccionar catálogo " + e.getMessage());			
		}
		    
	}
	
	/**
	 * Metodo que recupera el Rol del usuario en sesion y valida si cuenta con el rol necesario para la pantalla de Home
	 * @author Giovanni Hernández Alonso
	 * @since 24/07/2017
	 * @param 
	 * @return boolean rolValido
	 * **/
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

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<DTOModulo> getModulosCatalogos() {
		return modulosCatalogos;
	}

	public void setModulosCatalogos(List<DTOModulo> modulosCatalogos) {
		this.modulosCatalogos = modulosCatalogos;
	}

}
