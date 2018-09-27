package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.as.ASModificarAcuerdosInterface;
import mx.ine.acuerdos.bsd.BSDModificarAcuerdosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HLPFormModificarAcuerdos;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.CorreoAcuerdos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Funcionalidad de Modificar Acuerdos
 * @author José Hurtado
 * @since 31/10/2017
 */
@Component("bsdModAc")
@Scope("prototype")
public class BSDModificarAcuerdos implements BSDModificarAcuerdosInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2026174496512910766L;
	
	private static final Log log = LogFactory.getLog(BSDModificarAcuerdos.class);
	
	@Autowired
	@Qualifier("asModificarAcuerdos")
	private transient ASModificarAcuerdosInterface asModAcuerdos;
	
	/**
     * Muestra u combo con tipo de sesiones
     * @param form: elementos de la pantalla Modificar Acuerdos
     * @return lista sesiones
   */
	@Override
	public List<DTOTipoSesiones> recuperaTiposSesiones() {

		try {
			
			return asModAcuerdos.recuperaTiposSesiones();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(" BSDModificarAcuerdos.recuperaTiposSesiones :" + e.getMessage());
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
		DTOAcuerdos acuerdoemp = asModAcuerdos.obtenerAcuerdo(idAcuerdo);
			
			return acuerdoemp;
		} catch (Exception e) {
			log.info(" BSDModificarAcuerdos.obtenerAcuerdo :" + e.getMessage());
			//System.out.println("El Acuerdo no existe.");
			return null;
		}

	}
	

	
//	/**
//     * Muestra u oculta panel de Engrose
//     * @param form: Elementos de la pantalla registro acuerdos
//     * @return regresa true: Mostrar o false: ocultar
//   */
//	@Override
//	public void mostrarOcultar(HLPFormModificarAcuerdos form) {
//	      if (form.isHayEngrose()) {
//	            form.setHayEngrose(true);
//	        } else {
//	        	form.setHayEngrose(false);
//	        }
//	    }
	
	
	/**
     * ESTABLECE SI CUENTA CON ENGROSE
     * @param HLPFormRegistroAcuerdos
     * @return VOID
   */
	@Override
	public void hayEngrose(HLPFormModificarAcuerdos form) {
	      if (form.isHayEngrose()) {
	            form.setHayEngrose(true);
	            form.getAcuerdo().setEngrose(1);
	        } else {
	        	form.setHayEngrose(false);
	        	form.getAcuerdo().setEngrose(0);
	        }
	    }	
	
	/**
     * Actiualizar Acuerdo
     * @param form: Elementos de la pantalla registro acuerdos
     * @return regresa true: Mostrar o false: ocultar
   */	
	@Override
	public String actualizarAcuerdo(DTOAcuerdos dtoAcuerdo) {
//		try {
//			asModAcuerdos.actualizarAcuerdo(dtoAcuerdo);
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
		
	try {			
		//obtener los puntos del acuerdo
		List<DTOPuntosAcuerdo> puntos = asModAcuerdos.recuperaPuntosAcuerdos(dtoAcuerdo.getIdNumAcuerdo());
		List<DTOTipoSesiones> tipoSesiones = recuperaTiposSesiones();
		//se recorre la lista de puntos
			for (DTOPuntosAcuerdo punto : puntos) {
				//se obtienen los responsables del puntp
				List<DTOSeguimientosCG> responsablesPunto = asModAcuerdos.obtenerResponsablesDelPunto(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia());
				//se recorrer los responsables del punto para enviarles correo 
				Integer areaNotificada = 0;
				for (DTOSeguimientosCG responsablePunto : responsablesPunto) {
					 if(!areaNotificada.equals(responsablePunto.getId().getIdArea())){  
						 areaNotificada = responsablePunto.getId().getIdArea();
						List<DTOResponsables> responsablesArea= asModAcuerdos.obtenerResponsable(responsablePunto.getId().getIdArea());
							//System.out.println("responsables de area"+responsablesArea.size());
							for (DTOResponsables responsable : responsablesArea) {
								if(responsable.getIdArea().equals(responsablePunto.getId().getIdArea())){
									//System.out.println("se envia correo de acuerdo modificado:"+responsable.getCorreo());
									if(responsable.getTipoResponsable().equals(1)){
										List<String> correos = new ArrayList<String>();
										correos.add(responsable.getCorreo());
										/*correos.add("jorge.luna@ine.mx");
										correos.add("lizbeth.vargasl@ine.mx");
										correos.add("xochiquetzal.hernand@ine.mx");*/
										CorreoAcuerdos correo =  new CorreoAcuerdos(punto, dtoAcuerdo,2,tipoSesiones);
										correo.setCorreos(correos);
										correo.enviaNotification();
									}
								}
							}
					}	
				}
				
			}
		
		
		return asModAcuerdos.actualizarAcuerdo(dtoAcuerdo);
		
	} catch (Exception e) {
		log.info("Error al actualizar el acuerdo. bsd:" + e.getMessage());		
		return Constantes.NO_REGISTRO_RG;
	}
		
		

	}
	
	

	@Override
	public void evaluaRequeridos() {
		
	}


	@Override
	public String extraerNomArchivo(String str){
		
		str = str.replace("\\", ";");
		str = str.replace("/", ",");
        String[] cadenas = str.split(";");
        //System.out.println(cadenas[cadenas.length-1]);
		return (cadenas[cadenas.length-1]);
		
	}
	
	/**
     * Estaclecer lista de nombres de imagenes de Infografías
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return String
   */
	@Override
	public List<String> recuperaImgsInfografias(){
//		List<String> imgInf = new ArrayList<String>();
//		imgInf.add("registroAcuerdo/Infografia_Mod_Ac_01.png");
//		imgInf.add("registroAcuerdo/Infografia_Mod_Ac_02.png");
//		return  (List<String>) imgInf;
		return  null;
	}


	
	/**
     * ESTABLECE SI CUENTA CON ENGROSE
     * @param HLPFormRegistroAcuerdos
     * @return VOID
   */
	@Override
	public void seleccionarConEngrose(HLPFormModificarAcuerdos form) {
		
		if (form.getAcuerdo().getEngrose()==0){
        	form.setHayEngrose(false);
        	form.getAcuerdo().setEngrose(0);
		}else{
            form.setHayEngrose(true);
            form.getAcuerdo().setEngrose(1);
		}
			
	}
	
	
	
	/**
     * MOSTRAR SI ES ACUERDO O RESOLUCION
     * @param HLPFormRegistroAcuerdos
     * @return VOID
   */
	@Override
	public void mostrarTipoDocumento(HLPFormModificarAcuerdos form) {
	
		form.setAcuerdoOreolucion(asModAcuerdos.recuperaTipoDocumentoS(form.getAcuerdo().getIdTipoDocumento()));
			
	}
	

}
