package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;


/**
 * Clase Helper en donde se definen las diferentes validaciones
 * según el tipo de candidatura que se obtenga y el rol del usuario
 * @author Alejandra Gómez Ruiz
 *
 */
@Component("hplObjectValidaciones")
@Scope ("prototype")
public class HLPValidacionesDatosActa implements Serializable{
	
	
	/**
	 * Identificador unico de la clase
	 */
	private static final long serialVersionUID = -3173629749200563839L;
	
	
	
	/**
	 * Listado que devuelve la distribución paarcial cuando el tipo de candidatura
	 * asi lo requiere
	 */
	public List<DTODistribucionCandParcial> listDistribucionParcial;
	
	
	/**
	 * Listado que devuelve la distribución final, cuando el tipo de candidatura
	 * asi lo requiere
	 */
	 public List<DTODistribucionCandidatos> listDistribucionFinal;
	 
	 
	 /**
	  * Listado que devuelve la distribución de partidos y candidatos,
	  * cuando el tipo de candidatura asi lo requiere
	  */
	 public List<DTODistribucionPartidosCI> listDistribucionPPCI;
	 
	 
	 /**
	  * propiedad que devuelve si la captura ya fue hecha en un 100%
	  */
	  public boolean captura100de100;

	  
	  
	 

	 public HLPValidacionesDatosActa() {
		this.listDistribucionParcial = new ArrayList<DTODistribucionCandParcial>();
		this.listDistribucionFinal = new ArrayList<DTODistribucionCandidatos>();
		this.listDistribucionPPCI = new ArrayList<DTODistribucionPartidosCI>();
	}
	 

	public HLPValidacionesDatosActa(
			List<DTODistribucionCandParcial> listDistribucionParcial,
			List<DTODistribucionCandidatos> listDistribucionFinal,
			List<DTODistribucionPartidosCI> listDistribucionPPCI,
			boolean captura100de100) {
		super();
		this.listDistribucionParcial = listDistribucionParcial;
		this.listDistribucionFinal = listDistribucionFinal;
		this.listDistribucionPPCI = listDistribucionPPCI;
		this.captura100de100 = captura100de100;
	}





	public List<DTODistribucionCandParcial> getListDistribucionParcial() {
			return listDistribucionParcial;
	 }
	
	
	 public void setListDistribucionParcial(
		List<DTODistribucionCandParcial> listDistribucionParcial) {
		this.listDistribucionParcial = listDistribucionParcial;
	 }
	
	
	 public List<DTODistribucionCandidatos> getListDistribucionFinal() {
		return listDistribucionFinal;
	 }
	
	
	 public void setListDistribucionFinal(
		 List<DTODistribucionCandidatos> listDistribucionFinal) {
		 this.listDistribucionFinal = listDistribucionFinal;
	 }
	
	
	 public List<DTODistribucionPartidosCI> getListDistribucionPPCI() {
		return listDistribucionPPCI;
	 }
	
	
	 public void setListDistribucionPPCI(
		List<DTODistribucionPartidosCI> listDistribucionPPCI) {
		this.listDistribucionPPCI = listDistribucionPPCI;
	 }
	
	
	 public boolean isCaptura100de100() {
		return captura100de100;
	 }
	
	
	 public void setCaptura100de100(boolean captura100de100) {
		 this.captura100de100 = captura100de100;
	 }
		  
	  
	
	  

}
