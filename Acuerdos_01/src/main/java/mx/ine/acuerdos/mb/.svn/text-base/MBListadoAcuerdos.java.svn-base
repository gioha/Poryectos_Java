package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.bsd.BSDEjemploInterface;
import mx.ine.acuerdos.dto.helper.form.HLPFormEjemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *MB para el listado de Acuerdos
 *
 * @author Sampier Cuevas
 * @version 1.0
 * @since 05/10/2017 
 */

public class MBListadoAcuerdos implements Serializable {	
	 /**
     * Objeto par la serialización de esta clase.
     */
	private static final long serialVersionUID = -6066575804234579931L;

	private HLPFormEjemplo form; 
	
	@Autowired
	@Qualifier("bsdEjemplo")
	private BSDEjemploInterface bsdEjemplo;
	
	// metodo que incializa los elementos de la pantalla ejemplo.xhtml
	public void init(){
		
		//System.out.println("Entramos al metodo Init!!!!!!!!!!!!!!!");
		
		form = new HLPFormEjemplo();
		
		form.getListaDatos().add("Jose");
		form.getListaDatos().add("Jair");
		form.getListaDatos().add("Omar");
		form.getListaDatos().add("Sampier");
		
		bsdEjemplo.dondeEstas();
		bsdEjemplo.esPar(form);
		
	}

	public void saluda(){
		
		//System.out.println("Hola desde el MB!!!!!!!!!!!!!!!");
	}
	
	public boolean esElRol(){
		return true;
	}

	public HLPFormEjemplo getForm() {
		return form;
	}

	public void setForm(HLPFormEjemplo form) {
		this.form = form;
	}


	

}

