/**
 * @(#)MBHomeDirectorioDis.java 06/06/2016
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.dto.helper.form.HLPFormHome;
import mx.ine.acuerdos.util.Constantes;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * Esta clase provee la funcionalidad de validaciones del home
 * 
 * @author  Giovanni Hernández Alonso
 * @since   08/10/2017
 */
//@Component("mbHome")
//@Scope("singleton")
public class MBHomeAcuerdos extends MBGeneric implements Serializable {

	/**
	 * Elemento necesario para la serializaci�n de los objetos generados de esta clase.
	*/
	private static final long serialVersionUID = 2442464004299422525L;
	
	
	/** 
	 * Objeto para el servicio de bitacora de mensajes de la aplicacion. 
	 */
	public static final Logger logger = Logger.getLogger(MBHomeAcuerdos.class);	

	
//	/* ----------------------------------------------------------------------------------------- */
//	/* ------------------------------------- ATRIBUTOS ----------------------------------------- */
//	/* ----------------------------------------------------------------------------------------- */		

	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	private boolean rolValido;
	private boolean bloquerPantallaPorRol;

	private HLPFormHome form;
	
	
//	/* ----------------------------------------------------------------------------------------- */
//	/* ------------------------------------- METODOS ----------------------------------------- */
//	/* ----------------------------------------------------------------------------------------- */		
	
	
	/**
	 * Metodo que se ejecutara justo al instancear esta clase del lado de la pantalla, aqui incializamos variables en caso de tenerlas
	 * @author Giovanni Hernández Alonso
	 * @since 24/07/2017
	 * @param 
	 * @return Void
	 * **/
	public void inti(){
		form = new HLPFormHome();

		//se establecen los botones para cada roll
		establecerBotones();
		
		//System.out.println("estas en: MBHomeAcuerdos.init");
		
		// incializamos variables
		rolValido = false;
		bloquerPantallaPorRol = false;
		
	
		
	}
	
	/**
	 * Metodo para establecer los botones que se van a mostrar segun el rol logueado
	 * @param  void 
	 * @return Void
	 * **/
	public void establecerBotones(){
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(4);
		}
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(4);
		}	
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(4);
		}
	
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(4);
		}
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_CAU_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(4);
		}
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )){
			form.setBtn_sesion(false);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(3);
		}

		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )){
			form.setBtn_sesion(false);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(3);
		}

		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SIVOPLE_OC )){
			form.setBtn_sesion(false);
			form.setBtn_reportes(false);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(2);
		}

		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SIVOPLE_OC )){
			form.setBtn_sesion(false);
			form.setBtn_reportes(false);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(2);
		}
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )){
			//form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(3);
		}
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )){
			//form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
			form.setNumBtn(3);
		}
		
		
		/*			
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_COM_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
		}
		
		if(this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_COM_OC )){
			form.setBtn_sesion(true);
			form.setBtn_reportes(true);
			form.setBtn_admin(true);
			form.setBtn_seguimiento(true);
		}
*/
		
	}
	
	
	/**
	 * Metodo que recupera el Rol del usuario en sesion y valida si cuenta con el rol necesario para la pantalla de Home
	 * @author Giovanni Hernández Alonso
	 * @since 24/07/2017
	 * @param 
	 * @return boolean rolValido
	 * **/
	public boolean validaRoles(){
		
		this.rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC ) 			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )				||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )				||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )				||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SIVOPLE_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SIVOPLE_OC )			||
//    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_COM_OC )		||
//    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_COM_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_CAU_OC )			
    	   )
			
//			if (  
//	    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( "Rol_inexistente" )		
//	    	   )
    	{
			this.rolValido = true;

    	}
    
		return this.rolValido;
	}
	
	/**
	 * Metodo que imprime en la pantalla del Home el resultado de la evalucaion del Rol del usuario
	 * @author Giovanni Hernández Alonso
	 * @since 24/07/2017
	 * @param 
	 * @return Void
	 * **/
	public void imprimeValidacion(){
		
		// en este caso imprimimos en pantalla que no cuenta con los permisos necesarios para entrar a este modulos
			
			 agregaMensaje(TipoMensaje.ERROR_MENSAJE, "No cuenta con las credenciales necesarias");
             agregaMensaje(TipoMensaje.INFO_MENSAJE, "No cuenta con las credenciales necesarias");
		
	}
	
	public boolean isRolValido() {
		return rolValido;
	}

	public void setRolValido(boolean rolValido) {
		this.rolValido = rolValido;
	}

	public boolean isBloquerPantallaPorRol() {
		return bloquerPantallaPorRol;
	}

	public void setBloquerPantallaPorRol(boolean bloquerPantallaPorRol) {
		this.bloquerPantallaPorRol = bloquerPantallaPorRol;
	}

	public HLPFormHome getForm() {
		return form;
	}

	public void setForm(HLPFormHome form) {
		this.form = form;
	}
	
}
