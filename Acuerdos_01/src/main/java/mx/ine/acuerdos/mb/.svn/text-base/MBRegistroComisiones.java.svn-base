package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.List;

import mx.ine.acuerdos.bsd.BSDRegistroComisionesInterface;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.util.Constantes;

import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mbComisiones")
@Scope("singleton")
public class MBRegistroComisiones extends MBGeneric implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7875884909090720016L;
	private static final Logger logger = Logger.getLogger(MBRegistroComisiones.class);
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	@Autowired
	@Qualifier("bsdRegComisiones")
	private transient BSDRegistroComisionesInterface bsdRegComisiones;
	
	private boolean rolValido;
	private List<DTOComisiones> comisionesActivas;	
	private List<DTOComisiones> comisionesInactivas;
	private DTOComisiones activoSeleccionado;
	private DTOComisiones inactivoSeleccionado;
	private DTOComisiones nuevaComision;
	private boolean guardado;
	
	
	
	public void init(){		
		this.rolValido = false;	
		this.comisionesActivas = bsdRegComisiones.obtenerTodasComisionesActivas();
		this.comisionesInactivas = bsdRegComisiones.obtenerTodasComisionesInactivas();
		
		activoSeleccionado 		= null;
		inactivoSeleccionado 	= null;
		nuevaComision			= new DTOComisiones();
		guardado				= false;
	}
	/**
	 * Metodo que recupera el Rol del usuario en sesion y valida si cuenta con el rol necesario para la pantalla de Home
	 * @author SampierCuevas
	 * @since 24/07/2017
	 * @param 
	 * @return boolean rolValido
	 * **/
	public boolean validaRoles(){	
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_CAU_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )			
    	   ){
			
			this.rolValido = true;			
    	}
    
		return this.rolValido;
	}
	
	public void onRowSelectActivo(SelectEvent event) {
		activoSeleccionado   = (DTOComisiones)event.getObject();		
		inactivoSeleccionado = null;
	}
	
	public void onRowSelectInactivo(SelectEvent event) {
		 inactivoSeleccionado = (DTOComisiones)event.getObject();		
		 activoSeleccionado   = null;
	}
	
	public void guardaComision(){		
		nuevaComision.setEstatus(1);
		if(bsdRegComisiones.guardarComision(nuevaComision,1) == false){
			guardado = true;
		}
		this.comisionesActivas = bsdRegComisiones.obtenerTodasComisionesActivas();
		
	}
	public void limpiaComision(){
		nuevaComision = new DTOComisiones();
	}
	
	public void activaComisionExistente(){	
		bsdRegComisiones.guardarComision(nuevaComision,2);
		
		guardado = false;
		
		//obtener comisiones activas e inactivas
		this.comisionesActivas = bsdRegComisiones.obtenerTodasComisionesActivas();
		this.comisionesInactivas = bsdRegComisiones.obtenerTodasComisionesInactivas();
	}
	
	
	
	
	
	
	
	
	public List<DTOComisiones> getComisionesActivas() {
		return comisionesActivas;
	}
	public void setComisionesActivas(List<DTOComisiones> comisionesActivas) {
		this.comisionesActivas = comisionesActivas;
	}
	public List<DTOComisiones> getComisionesInactivas() {
		return comisionesInactivas;
	}
	public void setComisionesInactivas(List<DTOComisiones> comisionesInactivas) {
		this.comisionesInactivas = comisionesInactivas;
	}
	public DTOComisiones getActivoSeleccionado() {
		return activoSeleccionado;
	}
	public void setActivoSeleccionado(DTOComisiones activoSeleccionado) {
		this.activoSeleccionado = activoSeleccionado;
	}
	public DTOComisiones getInactivoSeleccionado() {
		return inactivoSeleccionado;
	}
	public void setInactivoSeleccionado(DTOComisiones inactivoSeleccionado) {
		this.inactivoSeleccionado = inactivoSeleccionado;
	}
	public DTOComisiones getNuevaComision() {
		return nuevaComision;
	}
	public void setNuevaComision(DTOComisiones nuevaComision) {
		this.nuevaComision = nuevaComision;
	}
	public boolean getGuardado() {
		return guardado;
	}
	public void setGuardado(boolean guardado) {
		this.guardado = guardado;
	}
}
