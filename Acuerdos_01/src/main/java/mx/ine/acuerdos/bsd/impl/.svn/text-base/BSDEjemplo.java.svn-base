package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.bsd.BSDEjemploInterface;
import mx.ine.acuerdos.dto.helper.form.HLPFormEjemplo;

@Component("bsdEjemplo")
@Scope("prototype")
public class BSDEjemplo implements BSDEjemploInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void esPar(HLPFormEjemplo form) {
		
		if(form.getNumPrueba() == 0){
			form.setMuestra(false);
		}
		
		else{
		
			// es par
			if( form.getNumPrueba() % 2 == 0){			
				form.setMuestra(true);
			}
			else{
				form.setMuestra(false);
			}
			
		}
		
		
	}

	@Override
	public void dondeEstas() {
		//System.out.println("Estas en BSDEjemplo!!!!!!!!");
		
	}
	
	@Override
	public void mostrarOcultar(HLPFormEjemplo form) {
        if (form.isInvalidarEtiqueta()) {
        	form.setMostrar(true);
        } else {
        	form.setMostrar(false);
        }
//        System.out.println("inv: " + form.isInvalidarEtiqueta());
//        System.out.println("most: " + form.isMostrar()); 
    }
	
	
  
    
}
