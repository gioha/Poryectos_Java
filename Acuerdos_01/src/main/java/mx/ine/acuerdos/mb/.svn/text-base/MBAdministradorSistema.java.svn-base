package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import mx.ine.acuerdos.bsd.BSDAdministradorAcuerdosInterface;
import mx.ine.acuerdos.dto.admin.DTOMenu;
import mx.ine.acuerdos.dto.admin.DTOModulo;
import mx.ine.acuerdos.dto.helper.DTOMenuHelper;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * Clase que se genera en sesión encargada de manejar el menú del sistema
 * 
 * @author Robert S.
 * @updatedBy Sampier
 * @updatedBy Giovanni Hernández Alonso
 * @since 09/12/2017
 * @copyright INE
 */
@ManagedBean(name = "mbAdmin")
public class MBAdministradorSistema implements Serializable {
	
    /**
     * Objeto par la serialización de esta clase.
     */

    private static final long serialVersionUID = -7521482166103525475L;

    /**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
    private static final Logger logger = Logger.getLogger(MBAdministradorSistema.class);
    
    /**
	 * Lista que contiene la opciones del menÃº que se utilizan en el sistema.
	 */
    
    private List<DTOMenuHelper> menuAcuerdos;
    
	private List<DTOMenu> menuGralAcuerdos;
	
	private List<DTOMenu> menuHeaderAcuerdos;
	
	private List<DTOModulo> reportesAcuerdos;
	
	private List<DTOModulo> catalogosAcuerdos;
	
    /**
	 * Lista de nombres de archivos de infografías
	 */
	private List<String> imgsInfografias;
	private String rutaVideo;
	
    
   @Autowired ///////////
	@Qualifier("bsdAdministradorAcuerdos")
	private transient BSDAdministradorAcuerdosInterface bsdAdministradorAcuerdos;

    /*
     * --------------------------------------------------------------------------
     * ---------------
     */
    /*
     * --------------------------------- INICIO - EVENTOS
     * --------------------------------------
     */
    /*
     * --------------------------------------------------------------------------
     * ---------------
     */
    
    /**
	 *	Método que hace la llamada para obtener el menú que se visualizara en el sistema de Acuerdos
	 *
	 * @author Sampier Cuevas Flores
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 15/09/2017
	 */
	public void obtenMenuAcuerdos()throws ClienteWebServiceException{
		   
			menuAcuerdos = new ArrayList<DTOMenuHelper>();
		
			menuGralAcuerdos = new ArrayList<DTOMenu>();
			reportesAcuerdos = new ArrayList<DTOModulo>();
			catalogosAcuerdos= new ArrayList<DTOModulo>();
			
			try {	 
				
				// recuperamos el ménu del sistema, descrito en las tablas de Admin
				menuGralAcuerdos = bsdAdministradorAcuerdos.obtenMenuAcuerdos();
			
				// recuperamos el ménu para el header del sistema
				if( menuGralAcuerdos.size() > 0 ){
					menuHeaderAcuerdos = bsdAdministradorAcuerdos.obtenMenuCustomizadoHeader(menuGralAcuerdos);
				}
				
				// recuperamos los modulos de los reportes, en función del ménu recuperado
				if( menuGralAcuerdos.size() > 0 ){
					reportesAcuerdos = bsdAdministradorAcuerdos.obtenModulosReportes(menuGralAcuerdos);
				}
				
				// recuperamos los modulos de los catalogos, en función del ménu recuperado
				if( menuGralAcuerdos.size() > 0 ){
					catalogosAcuerdos = bsdAdministradorAcuerdos.obtenModulosCatalogos(menuGralAcuerdos);
				}
				
				 
			} catch (Exception e) {
				 logger.error("<=================== Clase: MBAdministradorAcuerdos , Método: obtenMenuAcuerdos");
				 logger.error("<=================== ocurrio un error al obtener la información DEL MENÚ!!! del webservices");
				 logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				 logger.error("",e);
			}
	

	}  
	public String getRecuperaRutaVideo(){
		
	try {
		return bsdAdministradorAcuerdos.rutaVideo();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rutaVideo;	
	}
    
    /*
     * --------------------------------------------------------------------------
     * ---------------
     */
    /*
     * ---------------------------------- FIN - EVENTOS
     * ---------------------------------------
     */
    /*
     * --------------------------------------------------------------------------
     * ---------------
     */

 

	public List<String> getImgsInfografias() {
		return imgsInfografias;
	}

	public void setImgsInfografias(List<String> imgsInfografias) {
		this.imgsInfografias = imgsInfografias;
	}

	public List<DTOMenu> getMenuGralAcuerdos() {
		return menuGralAcuerdos;
	}

	public void setMenuGralAcuerdos(List<DTOMenu> menuGralAcuerdos) {
		this.menuGralAcuerdos = menuGralAcuerdos;
	}

	public List<DTOMenu> getMenuHeaderAcuerdos() {
		return menuHeaderAcuerdos;
	}

	public void setMenuHeaderAcuerdos(List<DTOMenu> menuHeaderAcuerdos) {
		this.menuHeaderAcuerdos = menuHeaderAcuerdos;
	}

	public List<DTOModulo> getReportesAcuerdos() {
		return reportesAcuerdos;
	}

	public void setReportesAcuerdos(List<DTOModulo> reportesAcuerdos) {
		this.reportesAcuerdos = reportesAcuerdos;
	}

	public List<DTOModulo> getCatalogosAcuerdos() {
		return catalogosAcuerdos;
	}

	public void setCatalogosAcuerdos(List<DTOModulo> catalogosAcuerdos) {
		this.catalogosAcuerdos = catalogosAcuerdos;
	}

	public List<DTOMenuHelper> getMenuAcuerdos() {
		return menuAcuerdos;
	}

	public void setMenuAcuerdos(List<DTOMenuHelper> menuAcuerdos) {
		this.menuAcuerdos = menuAcuerdos;
	}

	public String getRutaVideo() {
		return rutaVideo;
	}

	public void setRutaVideo(String rutaVideo) {
		this.rutaVideo = rutaVideo;
	}

}
