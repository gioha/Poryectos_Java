package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.dto.helper.form.HelperFormConformacionComision;
import mx.ine.acuerdos.util.Constantes;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mbConformacion")
@Scope("singleton")
public class MBRegistroConfComision extends MBGeneric implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9183560278177684044L;
	private static final Logger logger = Logger.getLogger(MBRegistroConfComision.class);

	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	private HelperFormConformacionComision form;
	private boolean rolValido;
	
	public void init(){
		this.rolValido = false;
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

	public boolean getRolValido() {
		return rolValido;
	}

	public void setRolValido(boolean rolValido) {
		this.rolValido = rolValido;
	}

	public HelperFormConformacionComision getForm() {
		return form;
	}

	public void setForm(HelperFormConformacionComision form) {
		this.form = form;
	}
	
	
	
	
}
