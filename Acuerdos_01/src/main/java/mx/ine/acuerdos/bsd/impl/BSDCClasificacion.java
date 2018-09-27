package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASCClasificacionInterface;
import mx.ine.acuerdos.bsd.BSDCClasificacionInterface;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.helper.form.HLPFormCClasificaciones;
import mx.ine.acuerdos.util.Constantes;

/**
 * Funcionalidad de Catalogo de Clasificaciones Punto
 * @author José Hurtado
 * @since 04/12/2017
 */

@Component("bsdCClasificacion")
@Scope("prototype")
public class BSDCClasificacion  implements BSDCClasificacionInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8422536051602976973L;
	private static final Log log = LogFactory.getLog(BSDCClasificacion.class);
	
	Integer i = new Integer(0);
	boolean eliminaClas;
	boolean inserta;
	boolean errorClasifBD;
	Integer j = new Integer(0);
	
	
	@Autowired
	@Qualifier("asCClasificacion")
	private transient ASCClasificacionInterface asCClasificacionP;
	
	@Override
	public List<DTOCClasificaciones> recuperaClasficacionesPunto() {

		try{	
			return asCClasificacionP.recuperaClasificaciones();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.recuperaClasficacionesPunto :" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public void reordenarClasificaciones(HLPFormCClasificaciones form){
		
		try{	
			Integer otros = new Integer(0);
			
			
			if(form.getClasificacion().get(form.getClasificacion().size()-1).getDescripcion().equals("Otros")){
				for (i = 0; i < form.getClasificacion().size(); i++){
					form.getClasificacion().get(i).setOrden(i+1);
				}
			}else{
				
				form.setClasificacionTemp(new ArrayList<DTOCClasificaciones>());
				
				for (i = 0; i < form.getClasificacion().size(); i++){
					if(!(form.getClasificacion().get(i).getDescripcion().equals("Otros"))){
						form.getClasificacionTemp().add(form.getClasificacion().get(i));
					}else{
						otros = i;
					}
				}
	
				form.getClasificacionTemp().add(form.getClasificacion().get(otros));
				form.setClasificacion(form.getClasificacionTemp());
				for (i = 0; i < form.getClasificacion().size(); i++){
					form.getClasificacion().get(i).setOrden(i+1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.reordenarClasificaciones :" + e.getMessage());
		}
		
	}
	
	@Override
	public void agregaRenglon(HLPFormCClasificaciones form){

		try{	
			if (!(form.getClasificacionAdd().equals(""))){
				form.agregarRenglon(form.getClasificacionAdd());
				
				if(((String)(form.getClasificacion().get(form.getClasificacion().size()-2).getDescripcion())).equals("Otros")){
					reordenarClasificaciones( form);
					form.setClasificacionAdd(null);
				}else{
					reordenarClasificaciones( form);
					form.setClasificacionAdd(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.agregaRenglon :" + e.getMessage());
		}	
		
	}
	

	@Override
	public void actualizarClasif(HLPFormCClasificaciones form, HLPFormCClasificaciones formOrig) {

		try{
			for (j = 0; j < form.getTamOrigen(); j++){
				eliminaClas = true;
				for (i = 0; i < form.getClasificacion().size(); i++){
					if(form.getClasificacion().get(i).getIdClasificacion().equals(formOrig.getClasificacion().get(j).getIdClasificacion())){
						eliminaClas = false;
					}
					
				}
				if(eliminaClas == true){
					
					//System.out.println(formOrig.getClasificacion().get(j).getIdClasificacion() + " " + formOrig.getClasificacion().get(j).getDescripcion() + " " + formOrig.getClasificacion().get(j).getOrden() + " " + formOrig.getClasificacion().get(j).getTipoClasificacion() + " puntos - " + formOrig.getClasificacion().get(j).isTienePuntoED() + " Eliminable - " + formOrig.getClasificacion().get(j).isEliminable());
					if(asCClasificacionP.eliminarClasif(formOrig.getClasificacion().get(j)).equals(Constantes.NO_REGISTRO_RG)){
						 errorClasifBD = true;
					}
				}
			}
			
			
			for (i = 0; i < form.getClasificacion().size(); i++){
				inserta = true;
				for (j = 0; j < form.getTamOrigen(); j++){
					
					//if(form.getClasificacion().get(i).isTienePuntoED() == false){
						if(form.getClasificacion().get(i).getIdClasificacion().equals(formOrig.getClasificacion().get(j).getIdClasificacion())){
									//if(!(form.getClasificacion().get(i).getDescripcion().equals(formOrig.getClasificacion().get(j).getDescripcion())) || !(form.getClasificacion().get(i).getOrden().equals(formOrig.getClasificacion().get(j).getOrden())) ){
							if((form.getClasificacion().get(i).getDescripcion().equals(formOrig.getClasificacion().get(j).getDescripcion())) && (form.getClasificacion().get(i).getOrden().equals(formOrig.getClasificacion().get(j).getOrden())) ){
										
						}else{
							//System.out.println(form.getClasificacion().get(i).getIdClasificacion() + " " + form.getClasificacion().get(i).getDescripcion() + " " + form.getClasificacion().get(i).getOrden() + " " + form.getClasificacion().get(i).getTipoClasificacion() + " puntos - " + form.getClasificacion().get(i).isTienePuntoED() + " Actualizacion - " + form.getClasificacion().get(i).isEliminable());
							if(asCClasificacionP.actualizarClasif(form.getClasificacion().get(i)).equals(Constantes.NO_REGISTRO_RG)){
								 errorClasifBD = true;
							}							
						}
							inserta = false;
					}
						
				}
				if(inserta == true){
					
					//System.out.println(form.getClasificacion().get(i).getIdClasificacion() + " " + form.getClasificacion().get(i).getDescripcion() + " " + form.getClasificacion().get(i).getOrden() + " " + form.getClasificacion().get(i).getTipoClasificacion() + " puntos - " + form.getClasificacion().get(i).isTienePuntoED() + " Insercion - " + form.getClasificacion().get(i).isEliminable());
					if(asCClasificacionP.actualizarClasif(form.getClasificacion().get(i)).equals(Constantes.NO_REGISTRO_RG)){
						errorClasifBD = true;
					}
				}
				
			}
			
			if(errorClasifBD != true){
				form.setMovimientosRealizadosBD(true);
			}else{
				form.setMovimientosRealizadosBD(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.actualizarClasif :" + e.getMessage());
		}	
	
	}

	@Override
	public boolean verificaIdClasif(Integer idClasificacion) {
		try{	
			 if(asCClasificacionP.verificaIdClasif(idClasificacion) > 0){
				 return true;
			 } else{
				 return false;
			 }
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.actualizarClasif :" + e.getMessage());
			return false;
		}
	
	}
	
	

	@Override
	public void llenalistaDeControl(HLPFormCClasificaciones form ) {
	
		try{
			for (i = 0; i < form.getClasificacion().size(); i++){
				
				if(verificaIdClasif(form.getClasificacion().get(i).getIdClasificacion())){
					form.getClasificacion().get(i).setTienePuntoED(true);
					form.getClasificacion().get(i).setEliminable(false);
					//System.out.println(form.getClasificacion().get(i).getIdClasificacion() + " " + form.getClasificacion().get(i).getDescripcion() + " " + form.getClasificacion().get(i).getOrden() + " " + form.getClasificacion().get(i).getTipoClasificacion() + " puntos - " + form.getClasificacion().get(i).isTienePuntoED() + " eliminable - " + form.getClasificacion().get(i).isEliminable());
				}else{
					form.getClasificacion().get(i).setTienePuntoED(false);
					form.getClasificacion().get(i).setEliminable(true);
					//System.out.println(form.getClasificacion().get(i).getIdClasificacion() + " " + form.getClasificacion().get(i).getDescripcion() + " " + form.getClasificacion().get(i).getOrden() + " " + form.getClasificacion().get(i).getTipoClasificacion() + " puntos - " + form.getClasificacion().get(i).isTienePuntoED() + " eliminable - " + form.getClasificacion().get(i).isEliminable());
				}
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.llenalistaDeControl :" + e.getMessage());
		}	
	}
	
	/**
     * Estaclecer lista de nombres de imagenes de Infografías
     * @param void
     * @return List<String>
   */
	@Override
	public List<String> recuperaImgsInfografias(){
		
		try{
//		List<String> imgInf = new ArrayList<String>();
//		imgInf.add("registroAcuerdo/Infografia_Reg_Ac_01.png");
//		imgInf.add("registroAcuerdo/Infografia_Reg_Ac_02.png");
//		return  (List<String>) imgInf;
			return  null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDCClasificacion.recuperaImgsInfografias :" + e.getMessage());
			return null;
		}
		
	}	
	
	
	
}
