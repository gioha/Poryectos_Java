package mx.ine.acuerdos.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.acuerdos.dto.DTOUsuarioLogin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class MBGeneric implements Serializable{

	private static final Log LOGGER= LogFactory.getLog(MBGeneric.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3618899413097169401L;

	
    /**
     * Me falta definir el bean del gluster
     * */
;
	
    /**
     * Para el manejo de los mensajes que se le envian al usuario 
     */
    protected enum TipoMensaje {
        ERROR_MENSAJE("mensajesError", 1), 
        INFO_MENSAJE("mensajesInfo", 2),
        ADVERTENCIA_MENSAJE("mensajesAdvertencia", 3);
        
        private TipoMensaje(String nombreMensaje, int tipo) {
            this.nombreMensaje = nombreMensaje;
            this.tipo = tipo;
        }
        
        private String nombreMensaje;
        private final int tipo;

        public String getNombreMensaje() {
            return nombreMensaje;
        }

        public int getTipo() {
            return tipo;
        }
    }
    
    /**
     * MÃ©todo encargado de obtener al usuario logueado
     * 
     * @author JosÃ© Antonio LÃ³pez Torres
     * @return DTOUsuarioLogin : usuario
     * @since 8/11/2016
     */
    public DTOUsuarioLogin obtenUsuario(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        return (DTOUsuarioLogin) auth.getPrincipal();
    }
    
    /**
     * Metodo auxiliar para mostrar un mensaje de error o de informacion
     */
    protected void agregaMensaje(TipoMensaje tipoMensaje, String mensaje) {
        agregaMensaje(tipoMensaje.getTipo(), tipoMensaje.getNombreMensaje(), mensaje);
    }
    
    /**
     * Metodo auxiliar para mostrar un mensaje de error o de informacion
     */
    protected void agregaMensaje(int tipoMensaje, String nombreMensaje, String mensaje) {
        FacesMessage message = new FacesMessage();
        if(TipoMensaje.ERROR_MENSAJE.getTipo() == tipoMensaje) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
        } else if(TipoMensaje.INFO_MENSAJE.getTipo() == tipoMensaje) {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
        } else {
                message.setSeverity(FacesMessage.SEVERITY_WARN);
        }
        message.setSummary(mensaje);
        FacesContext.getCurrentInstance().addMessage(nombreMensaje, message);
    }

    /**
     * Funci&oacute;n que agrega mensaje de notificaci&oacute;n
     *
     * @param mensaje  texto del mensaje de notificaci&oacute;n
     * @param severity tipo de mensaje de notificaci&oacute;n
     *
     * @author Jos&eacute; Carlos Ortega Romano
     * @since 08/09/2016
     */
    protected void agregaMensajeNotificacion(String mensaje, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(severity, mensaje, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
	
}

