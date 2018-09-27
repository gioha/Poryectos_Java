/**
 * 
 */
package mx.ine.acuerdos.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;

import org.jboss.logging.Logger;

/**
 * 
 * Clase que maneja el envío de mensajes a spring kafka
 * Se maneja en ApplicationScoped para que solo exista una clase de este tipo para toda la aplicación
 * 
 * @author INE
 *
 */
@ApplicationScoped
public class MBAplicacion implements Serializable{

	
	private static Logger log = Logger.getLogger(MBAplicacion.class);
	private String group;
	
	/**
	 * Objeto de serialización
	 */
	private static final long serialVersionUID = 5507555608867261625L;

	@PostConstruct
	public void init(){
		try{
			
			
		}catch(Exception e){
			log.error("Error al inicializar el MBAplicacion ",e);
		}
	}
	
}
