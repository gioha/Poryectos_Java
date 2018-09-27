package mx.ine.acuerdos.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.logging.Logger;

@FacesValidator(value="validadorSancion")
public class ValidadorSancion implements Validator{

	private static final Logger log = Logger.getLogger(ValidadorSancion.class);
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    //Pattern pat = Pattern.compile(Constantes.getConstanteString("REGEX_FORMATO_NOMBRES"));	    
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			  /**Autoridad Impositiva **/
			  UIInput uiInputAutoridadImpositora = (UIInput) component.getAttributes().get("monto");
			  String autoridadImpositiva = uiInputAutoridadImpositora.getLocalValue() == null ? ""
				: uiInputAutoridadImpositora.getLocalValue().toString();
			  String nombreMigranteId = uiInputAutoridadImpositora.getClientId();
			  
			  /**Número de resolución**/
			  UIInput uiInputResolucion = (UIInput) component.getAttributes().get("validadorResolucion");
			  String resolucion = uiInputResolucion.getLocalValue() == null ? ""
				: uiInputResolucion.getLocalValue().toString();
			  String apellidoPaternoMilitanteId = uiInputResolucion.getClientId();
			  
			  /**Apellido Materno Militante**/
			  UIInput uiInputAMM = (UIInput) component.getAttributes().get("apellidoMaternoMilitanteValidator");
			  String apellidoMaternoMilitante = uiInputAMM.getLocalValue() == null ? ""
				: uiInputAMM.getLocalValue().toString();
			  String apellidoMaternoMilitanteId = uiInputAMM.getClientId();
			  
			  FacesMessage msg = new FacesMessage(Utilidades.mensajeProperties("lbl_requerido"));
			  /**Sin capturar nombre y que no contenga números **/
			  if(autoridadImpositiva.equals("Selecciona...")){
				  msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				  fc.addMessage(nombreMigranteId, msg);
				  fc.renderResponse();			  
			  }
		}catch(Exception e){
			log.error("Error en la validacion de sanciones", e);
			return;
		}
	}
	
	public static void enviaMensaje(String target,FacesMessage.Severity severity,String encabezado,String mensaje){
		FacesContext.getCurrentInstance().addMessage(target, new FacesMessage(severity, encabezado,mensaje));
	}

}
