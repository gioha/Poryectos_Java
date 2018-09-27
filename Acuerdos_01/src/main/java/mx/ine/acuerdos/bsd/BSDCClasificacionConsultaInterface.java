package mx.ine.acuerdos.bsd;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCClasificaciones;

public interface BSDCClasificacionConsultaInterface {
	
	/**
     * Recuperar tipo de clasificaciones punto
     * @param void
     * @return List<DTOCClasificaciones>
   */
	public List<DTOCClasificaciones> recuperaClasficacionesPunto();
	
	/**
     * define imagenes de infografias
	 * @param List<String> 
     * @return void
   */
	public List<String> recuperaImgsInfografias();
	

}
