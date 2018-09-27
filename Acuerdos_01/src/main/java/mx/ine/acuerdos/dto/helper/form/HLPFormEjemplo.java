package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.util.ArrayList;

public class HLPFormEjemplo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630979307871159872L;

	
	private ArrayList<String> listaDatos;
	private String persona;
	private Integer numPrueba;
	private boolean muestra;
	private boolean invalidarEtiqueta;
	private boolean mostrar1;
	
	
	public HLPFormEjemplo(){
		
		this.listaDatos = new ArrayList<String>();
		this.persona = "";
		this.numPrueba= 0;
		this.muestra = false;
		this.invalidarEtiqueta = false;
		this.mostrar1 = false;
		
	}	
	
	public ArrayList<String> getListaDatos() {
		return listaDatos;
	}
	public void setListaDatos(ArrayList<String> listaDatos) {
		this.listaDatos = listaDatos;
	}
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public Integer getNumPrueba() {
		return numPrueba;
	}
	public void setNumPrueba(Integer numPrueba) {
		this.numPrueba = numPrueba;
	}

	public boolean isMuestra() {
		return muestra;
	}

	public void setMuestra(boolean muestra) {
		this.muestra = muestra;
	}

	public boolean isInvalidarEtiqueta() {
		return invalidarEtiqueta;
	}

	public void setInvalidarEtiqueta(boolean invalidarEtiqueta) {
		this.invalidarEtiqueta = invalidarEtiqueta;
	}

	public boolean isMostrar() {
		return mostrar1;
	}

	public void setMostrar(boolean mostrar1) {
		this.mostrar1 = mostrar1;
	}
	
	
	
}
