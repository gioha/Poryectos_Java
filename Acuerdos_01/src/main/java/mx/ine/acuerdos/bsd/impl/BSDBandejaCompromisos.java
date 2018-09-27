package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.as.ASBandejaCompromisosInterface;
import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDBandejaCompromisosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.util.CorreoAcuerdos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS correspondientes a la Bandeja de compromisos
 *
 * @author Sampier Cuevas Flores
 * @updatedBy 
 * @since 13/10/2017
 */
@Component("bsdBandejaCompromisos")
@Scope("prototype")
public class BSDBandejaCompromisos implements BSDBandejaCompromisosInterface, Serializable {
	private static final long serialVersionUID = -5554604478626360477L;
	
	////////////////////////////////VARIABLES///////////////////////////////////	
	private static final Log log = LogFactory.getLog(BSDBandejaCompromisos.class);
	
	@Autowired
	@Qualifier("asBandejaCompromisos")
	private transient ASBandejaCompromisosInterface asBandeja;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	DTOResponsables responsable;

	////////////////////////////////METODOS////////////////////////////////////
	@Override
	public List<DTOAcuerdos> recuperaAcuerdos(DTOUsuarioLogin usuario, Integer idTipoDocumento, Integer idNegocio) {
		try{	//si tiene rol de captura o consulta area se obtiene su area para mostra solo los acuerdos que le pertenecen
			if(usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC") ||
			   usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC") ||
			   usuario.getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC")|| 
			   usuario.getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC"))
			{				
				responsable= asBandeja.obtenerResponsableSegunLdap(usuario.getUsername());
				return asBandeja.consultaAcuerdosPorArea(responsable.getIdArea(), idTipoDocumento, idNegocio);
			}else{
				return asBandeja.recuperaAcuerdos(idTipoDocumento, idNegocio);
			}
			
		}catch(Exception e){
			log.info("BSDBandejaCompromisos.recuperaAcuerdos :" + e.getMessage());
			return null;
		}
		
	}
	
	/**
     * recupera la lista de los Puntos del Acuerdo de los parametros enviados
     * @param String idNumAcuerdo, Integer numeralia
     * @return Lista de Acuerdos
   */
	@Override
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo, DTOUsuarioLogin usuario ){
		try{			
			if(usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC") ||
					   usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC") ||
					   usuario.getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC")|| 
					   usuario.getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC"))
			{
				responsable= asBandeja.obtenerResponsableSegunLdap(usuario.getUsername());				
				
				return asBandeja.recuperaPuntosAcuerdos(idNumAcuerdo,responsable.getIdArea());
			}else{
			
			return asBandeja.recuperaPuntosAcuerdos(idNumAcuerdo);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("BSDBandejaCompromisos.recuperaPuntosAcuerdo :" + e.getMessage());
			return null;
		}
	}
	
	/**
     * cambia el estado del Acuerdo (eliminacion logica)
     * @param DTOAcuerdos
     * @return void
   */
	@Override
	public void eliminacionLogicaAcuerdo(DTOAcuerdos acuerdo){
		try{
			
			boolean hacerBajaLogica = false;
			
			if( acuerdo.getAcuerdoAdjunto() == null && acuerdo.getEngroseAdjunto() == null )
				hacerBajaLogica = true;
				
			// se elimina el pdf del acuerdo adjunto, si es que se le adjunto uno
			if( acuerdo.getAcuerdoAdjunto() != null ){
				if (boArchivos.eliminaArchivoEnGluster( acuerdo.getAcuerdoAdjunto() ) )
					hacerBajaLogica = true;
				else
					log.info("No se pudo eliminar el archivo adjunto del acuerdo en glsuter - BSDBandejaCompromisos.eliminacionLogicaAcuerdo");
			}
			
			
			// se elimina el pdf del engrose adjunto, si es que se le adjunto uno
			if( acuerdo.getEngroseAdjunto() != null ){
				hacerBajaLogica = false;
				if (boArchivos.eliminaArchivoEnGluster( acuerdo.getEngroseAdjunto() ) )
					hacerBajaLogica = true;
				else
					log.info("No se pudo eliminar el archivo adjunto del engrose en glsuter - BSDBandejaCompromisos.eliminacionLogicaAcuerdo");
			}
			
			if(hacerBajaLogica){
				asBandeja.eliminacionLogicaAcuerdo(acuerdo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("BSDBandejaCompromisos.eliminacionLogicaAcuerdo :" + e.getMessage());			
		}
	}
	
	/**
     * cambia el estado de cada punto de un Acuerdo (eliminacion logica)
     * @param List<DTOPuntosAcuerdo> 
     * @return void
   */
	@Override
	public void eliminacionLogicaPuntos(DTOAcuerdos acuerdo,List<DTOPuntosAcuerdo> puntos){
		try{
			 asBandeja.eliminacionLogicaPuntos(puntos);
			 
				for (DTOPuntosAcuerdo punto : puntos) {
					//se obtienen los responsables del puntp
					List<DTOSeguimientosCG> responsablesPunto = asBandeja.obtenerResponsablesDelPunto(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia());
					//se recorrer los responsables del punto para enviarles correo 
					Integer areaNotificada = 0;
					for (DTOSeguimientosCG responsablePunto : responsablesPunto) {
						 if(!areaNotificada.equals(responsablePunto.getId().getIdArea())){   	
							List<DTOResponsables> responsablesArea= asBandeja.obtenerResponsable(responsablePunto.getId().getIdArea());
							
								for (DTOResponsables responsable : responsablesArea) {
									if(responsable.getIdArea().equals(responsablePunto.getId().getIdArea())){
										if(responsable.getTipoResponsable().equals(1)){
											List<String> correos = new ArrayList<String>();
										    correos.add(responsable.getCorreo());
											/*correos.add("jorge.luna@ine.mx");
											correos.add("lizbeth.vargasl@ine.mx");
											correos.add("xochiquetzal.hernand@ine.mx");*/
											
											CorreoAcuerdos correo =  new CorreoAcuerdos(punto, acuerdo, 3, null);
											correo.setCorreos(correos);
											correo.enviaNotification();
										}
									}
								}
						}	
					}
					
				} 
			 
			 
		}catch(Exception e){
			e.printStackTrace();
			log.info("BSDBandejaCompromisos.eliminacionLogicaPuntos :" + e.getMessage());			
		}
	}
	
	/**
     * cambia el estado del punto selecccionado (eliminacion logica)
     * @param DTOPuntosAcuerdo 
     * @return void
   */
	@Override
	public void eliminacionLogicaPunto(DTOPuntosAcuerdo punto, DTOAcuerdos acuerdo){
		try{
			
			// se elimina el pdf del oficio de notificación, adjunto en el punto, si es que se le adjunto el oficio
			if( punto.getUrlArchivoAdj() != null ){
				if (boArchivos.eliminaArchivoEnGluster(punto.getUrlArchivoAdj()) )
					asBandeja.eliminacionLogicaPunto(punto);
				else
					log.info("No se pudo eliminar el archivo adjunto del punto en gluster - BSDBandejaCompromisos.eliminacionLogicaPunto");
			}
			else{
				asBandeja.eliminacionLogicaPunto(punto);
				//obtener responsables del punto
					List<DTOSeguimientosCG> responsablesDelPunto =asBandeja.obtenerResponsablesDelPunto(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia());
				//envio de de correo a las areas responsables del punto	para notificarles que el punto fue eliminado				
					for (DTOSeguimientosCG responsablePunto : responsablesDelPunto) {
						List<DTOResponsables> responsablesArea= asBandeja.obtenerResponsable(responsablePunto.getId().getIdArea());
						
							for (DTOResponsables responsable : responsablesArea) {
								if(responsable.getIdArea().equals(responsablePunto.getId().getIdArea())){
									if(responsable.getTipoResponsable().equals(1)){
										List<String> correos = new ArrayList<String>();
										correos.add(responsable.getCorreo());
										/*correos.add("jorge.luna@ine.mx");
										correos.add("lizbeth.vargasl@ine.mx");
										correos.add("xochiquetzal.hernand@ine.mx");*/
										CorreoAcuerdos correo =  new CorreoAcuerdos(punto, acuerdo,1, null);
										correo.setCorreos(correos);
										correo.enviaNotification();
									}
								}
								}
							}					

								//eliminar el seguimiento del punto en todas sus areas
							Integer areaNotificada =0;
							for (DTOSeguimientosCG responsablePunto : responsablesDelPunto) {
								if(!responsablePunto.getId().getIdArea().equals(areaNotificada)){
									areaNotificada = responsablePunto.getId().getIdArea();
									asBandeja.eliminarSeguimientoArea(responsablePunto);
								}								
							}
					}			
						
		}catch(Exception e){
			e.printStackTrace();
			log.info("BSDBandejaCompromisos.eliminacionLogicaPunto :" + e.getMessage());			
		}
	}

	/**
     * recupera el Acuerdo coincidente con el id de parametro
     * @param String
     * @return Lista de Acuerdos
   */
	@Override
	public List<DTOAcuerdos> consultaAcuerdo(String idAcuerdo){
		try{
			return asBandeja.consultaAcuerdo(idAcuerdo);
		}catch(Exception e){
			e.printStackTrace();
			log.info("BSDBandejaCompromisos.consultaAcuerdo :" + e.getMessage());
			return null;
		}
		
	}

	@Override
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, DTOUsuarioLogin usuario, Integer idTipoDocumento, Integer idNegocio) {
		try {
			if(usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC") ||
			   usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC") ||
			   usuario.getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC") ||
			   usuario.getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC")) {
				responsable = asBandeja.obtenerResponsableSegunLdap(usuario.getUsername());
				return asBandeja.busquedaAcuerdo(idAcuerdo, responsable.getIdArea(), idTipoDocumento, idNegocio);
			} else {
				return asBandeja.busquedaAcuerdo(idAcuerdo, idTipoDocumento, idNegocio);
			}
		} catch(Exception e) {
			log.info("BSDBandejaCompromisos.busquedaAcuerdo : " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, DTOUsuarioLogin usuario, Integer idTipoDocumento, Integer idNegocio) {
		try {
			if(usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC") ||
			   usuario.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC") ||
			   usuario.getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC") ||
			   usuario.getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC")) {
				responsable = asBandeja.obtenerResponsableSegunLdap(usuario.getUsername());
				return asBandeja.busquedaAcuerdoPorFecha(fechaSesion, responsable.getIdArea(), idTipoDocumento, idNegocio);
			} else {
				return asBandeja.busquedaAcuerdoPorFecha(fechaSesion, idTipoDocumento, idNegocio);
			}
		} catch(Exception e) {
			log.info("BSDBandejaCompromisos.busquedaAcuerdoPorFecha : " + e.getMessage());
			return null;
		}
	}
	
	/**
     * recupera una lista con el seguimiento de un punto
     * @param DTOPuntosAcuerdo
     * @return List<DTOSeguimientosCG>
   */
	public List<DTOSeguimientosCG> consultaPuntoEnSeguimiento(DTOPuntosAcuerdo punto){
		try{
			return asBandeja.consultaPuntoEnSeguimiento(punto);
		}catch(Exception e){
			e.printStackTrace();
			log.info("BSDBandejaCompromisos.consultaPuntoEnSeguimiento :" + e.getMessage());
			return null;
		}
		
	}
}
