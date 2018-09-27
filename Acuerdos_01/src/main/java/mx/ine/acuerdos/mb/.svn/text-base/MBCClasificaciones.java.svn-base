package mx.ine.acuerdos.mb;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import mx.ine.acuerdos.bsd.BSDCClasificacionInterface;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.helper.form.HLPFormCClasificaciones;
import mx.ine.acuerdos.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBCClasificaciones extends MBGeneric implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -57221985172132399L;
	
	@Autowired
	@Qualifier("bsdCClasificacion")
	private transient BSDCClasificacionInterface bsdCClasificacion;
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	private boolean rolValido;
	private HLPFormCClasificaciones form;
	private HLPFormCClasificaciones formOriginal;
	private boolean existe ;
	
	
	public void init(){
		
		try{	
			form                     = new HLPFormCClasificaciones();
			formOriginal             = new HLPFormCClasificaciones();
			form.setClasificacion(bsdCClasificacion.recuperaClasficacionesPunto());
			formOriginal.setClasificacion(bsdCClasificacion.recuperaClasficacionesPunto());
			rolValido = false;
			bsdCClasificacion.llenalistaDeControl(form);
			form.setTamOrigen(form.getClasificacion().size());
			
			//lista de nombres de imagenes de inforgrafias
			form.setListaImgsInfografias(bsdCClasificacion.recuperaImgsInfografias());
			mbAdmin.setImgsInfografias(form.getListaImgsInfografias());
			
			existe = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void agregaRenglon(){
		try{
			existe = false;
			
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("subFomularioCatalogoCP:casificacionCC");
			
			value = value.replace('á', 'a');
			value = value.replace('Á', 'A');
			value = value.replace('é', 'e');
			value = value.replace('É', 'E');
			value = value.replace('í', 'i');
			value = value.replace('Í', 'I');
			value = value.replace('Ó', 'O');
			value = value.replace('ó', 'o');
			value = value.replace('ú', 'u');
			value = value.replace('Ú', 'U');
			
			value = value.toUpperCase();
			value = value.trim();
	
			
			for (DTOCClasificaciones c : form.getClasificacion()) {
				String clasificacion="";
				clasificacion = c.getDescripcion();
		
				clasificacion = clasificacion.replace('á', 'a');
				clasificacion = clasificacion.replace('Á', 'A');
				clasificacion = clasificacion.replace('é', 'e');
				clasificacion = clasificacion.replace('É', 'E');
				clasificacion = clasificacion.replace('í', 'i');
				clasificacion = clasificacion.replace('Í', 'I');
				clasificacion = clasificacion.replace('Ó', 'O');
				clasificacion = clasificacion.replace('ó', 'o');
				clasificacion = clasificacion.replace('ú', 'u');
				clasificacion = clasificacion.replace('Ú', 'U');
				
				clasificacion = clasificacion.toUpperCase();
				clasificacion = clasificacion.trim();
		
				if( clasificacion.equalsIgnoreCase(value) ){
					existe = true;
					break;
				}
				
			}
			
			// no existe	
			if(!existe){
				if (value.equals("OTROS") || value.equals("SANCION ECONOMICA") || value.equals("")){
					
				}else{
					bsdCClasificacion.agregaRenglon(form);
				}
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	
	public String eliminarRenglonOK(){
		
		try{
			form.getClasificacion().remove(form.getClasificacionPreElim());
			reordenarClasificaciones();
	    	return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
	}
	
	public String eliminarRenglon(DTOCClasificaciones clasif){
	
		try{	
				form.setClasificacionPreElim(clasif);
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
			
	}
	
	
	public void actualizarClasif(){
	
		try{	
			bsdCClasificacion.actualizarClasif(form, formOriginal);
			
			formOriginal.setClasificacion(bsdCClasificacion.recuperaClasficacionesPunto());
			form.setTamOrigen(formOriginal.getClasificacion().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean validaRoles() {

		boolean rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )					
    	   ){
			
			rolValido = true;			
    	}
    
		return rolValido;
	}

	public boolean validarRolCaptura() {
		
		boolean esCaptura = false;
		
		if (  
				this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 			
    	   ){
			
			esCaptura = true;			
    	}
    
		return esCaptura;
		
	}
	
	public void reordenarClasificaciones(){
		bsdCClasificacion.reordenarClasificaciones(form);
	}

	
	
	public HLPFormCClasificaciones getForm() {
		return form;
	}


	public void setForm(HLPFormCClasificaciones form) {
		this.form = form;
	}


	public BSDCClasificacionInterface getBsdCClasificacion() {
		return bsdCClasificacion;
	}


	public void setBsdCClasificacion(BSDCClasificacionInterface bsdCClasificacion) {
		this.bsdCClasificacion = bsdCClasificacion;
	}


	public boolean isExiste() {
		return existe;
	}


	public void setExiste(boolean existe) {
		this.existe = existe;
	}


}
