package mx.ine.acuerdos.bsd.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import mx.ine.acuerdos.as.ASRegistroAcuerdosInterface;
import mx.ine.acuerdos.bsd.BSDModificarAcuerdosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroAcuerdosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HLPFormRegistroAcuerdos;
import mx.ine.acuerdos.util.Constantes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Funcionalidad de Registro de Acuerdos
 * @author José Hurtado
 * @since 09/10/2017
 *
 *
 */
@Component("bsdRegAc")
@Scope("prototype")
public class BSDRegistroAcuerdos implements BSDRegistroAcuerdosInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6438238061988086528L;

	private static final Log log = LogFactory.getLog(BSDRegistroAcuerdos.class);
	
	@Autowired
	@Qualifier("asRegistroAcuerdos")
	private transient ASRegistroAcuerdosInterface asRegAcuerdos;
	
	
	@Autowired
	@Qualifier("bsdModAc")
	private transient BSDModificarAcuerdosInterface bsdModAc;
	
	/**
     * Muestra u oculta panel de Engrose
     * @param form: Elementos de la pantalla registro acuerdos
     * @return regresa true: Mostrar o false: ocultar
   */
	@Override
	public void hayEngrose(HLPFormRegistroAcuerdos form) {
		try{
	      if (form.isHayEngrose()) {
	            form.setHayEngrose(true);
	            form.getAcuerdo().setEngrose(1);
	        } else {
	        	form.setHayEngrose(false);
	        	form.getAcuerdo().setEngrose(0);
	        }
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - hayEngrose() - Ocurrio un error: " + e.getMessage()  );
		}
	  }
	

	@Override
	public void evaluaRequeridos() {
		//validar campos
	}
	
	/**
     * Recupera sesiones
     * @param void
     * @return List<DTOTipoSesiones>
   */
	@Override
	public List<DTOTipoSesiones> recuperaTiposSesiones() {

		try {
			
			return asRegAcuerdos.recuperaTiposSesiones();
			
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - recuperaTiposSesiones() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
		
	}
	
	/**
     * Recupera tipo de documentos
     * @param void
     * @return List<DTOCTipoDocumento>
   */	
	@Override
	public List<DTOCTipoDocumento> recuperaTiposDocumentos() {

		try {
			
			return asRegAcuerdos.recuperaTiposDocumentos();
			
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - recuperaTiposDocumentos() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
		
	}
	
	/**
     * Obtencion del Acuerdo
     * @param form: elementos de la pantalla Modificar Acuerdos
     * @return Datos del Acuerdo
   */	
	@Override
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo) {
		try {
			DTOAcuerdos acuerdoemp = asRegAcuerdos.obtenerAcuerdo(idAcuerdo);
			return acuerdoemp;
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - obtenerAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}

	}	
	
	
	/**
     * Eliminar seguimiento
     * @param String: idNUmAcuerdo
     * @return void
   */	
	@Override
	public void eliminarSeguimiento(String idAcuerdo) {
		try {
			asRegAcuerdos.eliminarSeguimiento(idAcuerdo);			
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - eliminarSeguimiento() - Ocurrio un error: " + e.getMessage()  );
		}

	}
	
	/**
     * Eliminar puntos
     * @param String: idNUmAcuerdo
     * @return void
   */	
	@Override
	public void eliminarPuntosAc(String idAcuerdo) {
		try {
			asRegAcuerdos.eliminarPuntosAc(idAcuerdo);			
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - eliminarPuntosAc() - Ocurrio un error: " + e.getMessage()  );
		}

	}
	
	/**
     * Verificar existencia del acuerdo
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return void
   */
	@Override
	public void existeAc (DTOAcuerdos dtoAcuerdos, HLPFormRegistroAcuerdos form){
		try{
			if (dtoAcuerdos != null){
					//si existe el acuerdo verificar si esta activo
					
					if(dtoAcuerdos.getActivo()==1){
						//si el acuerdo esta activo establecer intento de duplicidad
						
						form.setExisteAcuerdo(true);
						
						//System.out.println("Registro existente");
						//acuerdosPrueba="regAcuerdo";
						//agregaMensaje(TipoMensaje.INFO_MENSAJE, "¡Acuerdo existente!");
						
					} else {
						// si el acuerdo no esta activo eliminar puntos del acuerdo para su alta
						form.setExisteAcuerdo(false);
					}
					
			}else{
					form.setExisteAcuerdo(false);
				}
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - existeAc() - Ocurrio un error: " + e.getMessage()  );
		}
	}

	/**
     * Guardar Acuerdo
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return String
   */
	@Override
	public String guardaAcuerdo(DTOAcuerdos dtoAcuerdos, HLPFormRegistroAcuerdos form){
		
		try{
		
		boolean bandera = true;
		if (bandera) {
		
			if (dtoAcuerdos != null){
					//si existe el acuerdo verificar si esta activo
					
					if(dtoAcuerdos.getActivo()==1){
						//Acuerdo existe y activo -> notificar
						return null;
					} else {
						// Acuerdo existente y no activo -> modificarlo
						
						//eliminar seguimiento
						eliminarSeguimiento(dtoAcuerdos.getIdNumAcuerdo());
						
						//eliminar puntos
						eliminarPuntosAc(dtoAcuerdos.getIdNumAcuerdo());
						
						
						//actualizar punto
						if( form.isAcuerdoAdjunto() ){
							
							form.getAcuerdo().setAcuerdoAdjunto( form.getRutaAcuerdoFile() + File.separator + form.getRenameAcuerdoFile() );
						}
						
						if ( form.isEngroseAdjunto() ){
							
							form.getAcuerdo().setEngroseAdjunto( form.getRutaEngroseFile() + File.separator + form.getRenameEngroseFile() );
						}
						
						if (bsdModAc.actualizarAcuerdo(form.getAcuerdo()) == Constantes.REGISTRO_RG){

							if(form.getAcuerdo().getIdTipoDocumento()==1)
							    form.setAcuerdosPrueba("regAcuerdo");
							
							if(form.getAcuerdo().getIdTipoDocumento()==6)
								form.setAcuerdosPrueba("regResolucion");
							
							form.setNoRegistroRG(false);
							return Constantes.IRLISTA;
						}else{
							form.setNoRegistroRG(true);
							return null;
						}						
						
					}
			}else{
				// Acuerdo no existe -> registrarlo
				
				if( form.isAcuerdoAdjunto() ){
					
					form.getAcuerdo().setAcuerdoAdjunto( form.getRutaAcuerdoFile() + File.separator + form.getRenameAcuerdoFile() );
				}
				
				if ( form.isEngroseAdjunto() ){
					
					form.getAcuerdo().setEngroseAdjunto( form.getRutaEngroseFile() + File.separator + form.getRenameEngroseFile() );
				}
				
				
				if (asRegAcuerdos.guardaAcuerdo(form.getAcuerdo()) == Constantes.REGISTRO_RG){
					
					if(form.getAcuerdo().getIdTipoDocumento()==1)
					    form.setAcuerdosPrueba("regAcuerdo");
					
					if(form.getAcuerdo().getIdTipoDocumento()==6)
						form.setAcuerdosPrueba("regResolucion");
					
					form.setNoRegistroRG(false);
					return Constantes.IRLISTA;
				}else{
					form.setNoRegistroRG(true);
					return null;
				}
			}

		}else{
			return null;
		}
		
		} catch (Exception e) {
			log.error( " BSDRegistroAcuerdos - guardaAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
		
	}
	
	
	/**
     * Estaclecer lista de nombres de imagenes de Infografías
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return String
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
			log.error( " BSDRegistroAcuerdos - recuperaImgsInfografias() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
		
	}
		
}
