package mx.ine.acuerdos.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDBandejaCompromisosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroAcuerdosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HelperBandejaSeguimiento;
import mx.ine.acuerdos.util.Constantes;

import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@Component("mbBandeja")
//@Scope("singleton")
public class MBBandejaCompromisos extends MBGeneric implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4129138744507117026L;
	
	private static final Logger logger = Logger.getLogger(MBBandejaCompromisos.class);
	
////////////////////////////////VARIABLES///////////////////////////////////
	
	@Autowired
	@Qualifier("bsdBandejaCompromisos")
	private transient BSDBandejaCompromisosInterface bsdBandeja;
	
	@Autowired
	@Qualifier("bsdRegAc")
	private transient BSDRegistroAcuerdosInterface bsdRegAc;
	
	@Autowired
	@Qualifier("asConvocatoria")
	private transient ASConvocatoriaInterface asConvocatoria;
	
	
	private List<DTOAcuerdos> listaAcuerdos;
	
	private DTOAcuerdos acuerdoSeleccionado;
	
	private List<DTOPuntosAcuerdo> puntosAcuerdoSeleccionado;
	
	private DTOPuntosAcuerdo puntoSeleccionado;
	
	private List<DTOTipoSesiones> tipoSesiones;
	
	private String idBusqueda;
	
	private List<DTOAcuerdos> busquedaListaAcuerdosAux;
	
	private String buscarPor;
	
	private String anioBusqueda;
	
	private List<DTOSeguimientosCG> puntoEnSeguimiento;
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	private boolean rolValido;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	private String rutaPrevisualizacionAcuerdo;
	
	private boolean sinAreas;
	
	private String idNumAcuerdoSeg;
	
	private String numeraliaSeg;

	// Actualización de funcionalidad para Junta General Ejecutiva
	private HelperBandejaSeguimiento helpBandejaSeg;
	
//////////////////////////////CONSTRUCTORES////////////////////////////////
	
	 /**
	 *	Constructordel MBBandejaCompromisos recibe una cadena para validar de que vista viene y mostrar un mensaje correspondiente
	 *
	 * @author Sampier Cuevas Flores
	 * @since 13/10/2017
	 */

	public void init(String parametro){		

		helpBandejaSeg 				= new HelperBandejaSeguimiento();
		helpBandejaSeg.setIdTipoDocumento(1);	// Tipo de documento por default: Acuerdo
		helpBandejaSeg.setIdNegocio(1);			// Negocio por default: Consejo General
		puntosAcuerdoSeleccionado 	= null;
		acuerdoSeleccionado 	  	= null;
		listaAcuerdos 			  	= null;
		puntoSeleccionado			= null;
		listaAcuerdos 				= bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());		
		tipoSesiones				= bsdRegAc.recuperaTiposSesiones();
		idBusqueda					= "";		
		busquedaListaAcuerdosAux    = null;
		buscarPor					= "Acuerdo";
		anioBusqueda 				= "";
		puntoEnSeguimiento          = null;
		rolValido 					= false;
		rutaPrevisualizacionAcuerdo = null;
		sinAreas					= false;
		idNumAcuerdoSeg             = "";
		numeraliaSeg				= "";
		
		if(parametro != null){
			switch (parametro) {
				case "modPunto" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "Punto modificado correctamente");			
					break;
					
				case "regPunto" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "Punto agregado correctamente");			
					break;		
				
				case "regAcuerdo" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "El Documento se guardó correctamente");			
					break;		
				
				case "modAcuerdo" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "Documento modificado correctamente");			
					break;
					
				case "regResolucion" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "El Documento se guardó correctamente");			
					break;		
			
				case "modResolucion" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "Documento modificado correctamente");			
					break;	
				
				case "regInsertMovimiento" : agregaMensaje(TipoMensaje.INFO_MENSAJE, "Movimiento agregado correctamente");			
					break;
			}
		}		
	}
	
////////////////////////////////METODOS////////////////////////////////////
	
	 /**
		 *	metodo que se ejecuta al seleccionar un acuerdo, obtine los puntos del acuerdo
		 *
		 * @author Sampier Cuevas Flores
		 * @since 13/10/2017
		 */
	public void onRowSelect(SelectEvent event) {
		
		acuerdoSeleccionado = (DTOAcuerdos) event.getObject();	   
	    puntosAcuerdoSeleccionado = bsdBandeja.recuperaPuntosAcuerdos(acuerdoSeleccionado.getIdNumAcuerdo(),this.obtenUsuario());
	    puntoSeleccionado = null;
//	    if (!acuerdoSeleccionado.getAcuerdoAdjunto().isEmpty()) {
//	    	rutaPrevisualizacionAcuerdo = boArchivos.getRutaPrevisualizacion(acuerdoSeleccionado.getAcuerdoAdjunto());
//		}	   
	    if (acuerdoSeleccionado.getAcuerdoAdjunto() != null && !acuerdoSeleccionado.getAcuerdoAdjunto().isEmpty()) {
	    	rutaPrevisualizacionAcuerdo = boArchivos.getRutaPrevisualizacion(acuerdoSeleccionado.getAcuerdoAdjunto());
		}
	    
	    if(puntosAcuerdoSeleccionado.size() >=1){
	    	List<DTOPuntosAcuerdo> puntosSinArea    =  new ArrayList<DTOPuntosAcuerdo>();	
	    	List<DTOPuntosAcuerdo> puntosRechazado  =  new ArrayList<DTOPuntosAcuerdo>();
	    	List<DTOPuntosAcuerdo> puntosCampana    =  new ArrayList<DTOPuntosAcuerdo>();
	    	List<DTOPuntosAcuerdo> puntosRojo       =  new ArrayList<DTOPuntosAcuerdo>();	
	    	List<DTOPuntosAcuerdo> puntosAmarillo   =  new ArrayList<DTOPuntosAcuerdo>();	
	    	List<DTOPuntosAcuerdo> puntosVerde      =  new ArrayList<DTOPuntosAcuerdo>();
	    	List<DTOPuntosAcuerdo> puntosGris       =  new ArrayList<DTOPuntosAcuerdo>();
	    	List<DTOPuntosAcuerdo> puntosFinalizado =  new ArrayList<DTOPuntosAcuerdo>();	
	    	
	    	for (DTOPuntosAcuerdo punto : puntosAcuerdoSeleccionado) {
					if(punto.getSemaforo().endsWith("gris")){//si esta en gris se evalua si esta sin area
						if(punto.getEstatusGlobal().equals("Sin Áreas")){
							puntosSinArea.add(punto);
							continue;
						}else{
							puntosGris.add(punto);
							continue;
						}
					}
					if(punto.getSemaforo().endsWith("equis")){// si esta rechazado
						puntosRechazado.add(punto);
					}
					if(punto.getSemaforo().endsWith("campana")){//si esta por confirmar validacion
						puntosCampana.add(punto);
					}
					if(punto.getSemaforo().endsWith("rojo")){
						puntosRojo.add(punto);
					}
					if(punto.getSemaforo().endsWith("amarillo")){
						puntosAmarillo.add(punto);
					}
					if(punto.getSemaforo().endsWith("verde")){
						puntosVerde.add(punto);
					}
					if(punto.getSemaforo().endsWith("palomita")){// si esta finalizado
						puntosFinalizado.add(punto);
					}			
			}
	    	
	    	List<DTOPuntosAcuerdo> puntosOrdenados    =  new ArrayList<DTOPuntosAcuerdo>();	
	    		if(puntosSinArea.size() >= 1){
	    			for (DTOPuntosAcuerdo sinArea : puntosSinArea) {
	    				puntosOrdenados.add(sinArea);
					}	    			
	    		}
	    		if(puntosRechazado.size() >= 1){
	    			for (DTOPuntosAcuerdo rechazado : puntosRechazado) {
	    				puntosOrdenados.add(rechazado);
					}	    			
	    		}
	    		if(puntosCampana.size() >= 1){
	    			for (DTOPuntosAcuerdo campana : puntosCampana) {
	    				puntosOrdenados.add(campana);
					}	    			
	    		}
	    		if(puntosRojo.size() >= 1){
	    			for (DTOPuntosAcuerdo rojo : puntosRojo) {
	    				puntosOrdenados.add(rojo);
					}	    			
	    		}
	    		if(puntosAmarillo.size() >= 1){
	    			for (DTOPuntosAcuerdo amarillo : puntosAmarillo) {
	    				puntosOrdenados.add(amarillo);
					}	    			
	    		}
	    		if(puntosVerde.size() >= 1){
	    			for (DTOPuntosAcuerdo verde : puntosVerde) {
	    				puntosOrdenados.add(verde);
					}	    			
	    		}
	    		if(puntosGris.size() >= 1){
	    			for (DTOPuntosAcuerdo gris : puntosGris) {
	    				puntosOrdenados.add(gris);
					}	    			
	    		}
	    		if(puntosFinalizado.size() >= 1){
	    			for (DTOPuntosAcuerdo finalizado : puntosFinalizado) {
	    				puntosOrdenados.add(finalizado);
					}	    			
	    		}
	    		
	    		puntosAcuerdoSeleccionado = puntosOrdenados;
	    }
    }
	
	/**
	 *	metodo que se ejecuta al seleccionar un punto
	 *
	 * @author Sampier Cuevas Flores
	 * @since 13/10/2017
	 */
	public void onRowSelectPunto(SelectEvent event) {
		puntoSeleccionado = (DTOPuntosAcuerdo) event.getObject();
		idNumAcuerdoSeg = puntoSeleccionado.getId().getIdNumAcuerdo();
		numeraliaSeg = ""+puntoSeleccionado.getId().getNumeralia();
		puntoEnSeguimiento = bsdBandeja.consultaPuntoEnSeguimiento(puntoSeleccionado);
			
	}
	
	/**
	 *	realiza la baja logica de un Acuerdo y sus Puntos 
	 *
	 * @author Sampier Cuevas Flores
	 * @since 19/10/2017
	 */
	public String eliminaAcuerdo(){
		//pone en 0 el Activo del Acuerdo
		acuerdoSeleccionado.setActivo(0);
		// A consecuencia de setear un 2 en DAOAcuerdos, el Estatus debe volver a su valor original
		if(acuerdoSeleccionado.getEstatus().equals(2)) {
			acuerdoSeleccionado.setEstatus(0);
		}
		  try {			   
			  	bsdBandeja.eliminacionLogicaAcuerdo( acuerdoSeleccionado );
				  	//si el acuerdo tiene puntos pone su Activo en 0 de cada punto
				  	if(puntosAcuerdoSeleccionado != null){
				  		bsdBandeja.eliminacionLogicaPuntos(acuerdoSeleccionado,puntosAcuerdoSeleccionado);
				  	}
			  	
			  	//vuelve a consultar la tabla de Acuerdo para visualizar los cambios
			  	listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());
			  	
			  	puntosAcuerdoSeleccionado 	= null;
			  	acuerdoSeleccionado 	  	= null;
			  	
				agregaMensaje(TipoMensaje.INFO_MENSAJE, "Documento eliminado correctamente");
			  	return "";
			} catch (Exception e) {
				 logger.error("<=================== Clase: MBBandejaCompromisos , Método: eliminaAcuerdo");				
				return "";
			}	
	}
	
	/**
	 *	realiza la baja logica de un Punto 
	 *
	 * @author Sampier Cuevas Flores
	 * @since 19/10/2017
	 */
	public String eliminaPunto(){		
		//pone en 0 el Activo del Punto
		puntoSeleccionado.setActivo(0);	
		
		  try {			
			  	bsdBandeja.eliminacionLogicaPunto(puntoSeleccionado, acuerdoSeleccionado);			  
			  	
			  	//vuelve a consultar la tabla de Acuerdo para visualizar los cambios
			  	puntosAcuerdoSeleccionado = bsdBandeja.recuperaPuntosAcuerdos(acuerdoSeleccionado.getIdNumAcuerdo(),this.obtenUsuario());		  
			  	
				agregaMensaje(TipoMensaje.INFO_MENSAJE, "Punto eliminado correctamente");
			  	return "";
			} catch (Exception e) {
				 logger.error("<=================== Clase: MBBandejaCompromisos , Método: eliminaPunto");				
				return "";
			}
		
	
	}
	
	/**
	 *	retorna la descripcion correspondiente del tipo de sesion
	 *
	 * @author Sampier Cuevas Flores
	 * @since 24/10/2017
	 */
	public String tipoSesion(){
		if(acuerdoSeleccionado!=null){
			for (DTOTipoSesiones sesion : tipoSesiones) {
				if(acuerdoSeleccionado.getIdTipoSesion() == sesion.getIdTipoSesion()){
					 return sesion.getDescripcion();
				}					
			}
		}
		return "";
	}
	
	/**
	 *	busca el acuerdo seleccionado en la busqueda
	 *
	 * @author Sampier Cuevas Flores
	 * @since 26/10/2017
	 */
	public void onItemSelect(SelectEvent event) {
		 anioBusqueda = "";
		 try {
			 listaAcuerdos 				= bsdBandeja.consultaAcuerdo(idBusqueda);
			 acuerdoSeleccionado		= listaAcuerdos.get(0);
			 puntosAcuerdoSeleccionado  = bsdBandeja.recuperaPuntosAcuerdos(acuerdoSeleccionado.getIdNumAcuerdo(),this.obtenUsuario());
			 

			    if(puntosAcuerdoSeleccionado.size() >=1){
			    	List<DTOPuntosAcuerdo> puntosSinArea    =  new ArrayList<DTOPuntosAcuerdo>();	
			    	List<DTOPuntosAcuerdo> puntosRechazado  =  new ArrayList<DTOPuntosAcuerdo>();
			    	List<DTOPuntosAcuerdo> puntosCampana    =  new ArrayList<DTOPuntosAcuerdo>();
			    	List<DTOPuntosAcuerdo> puntosRojo       =  new ArrayList<DTOPuntosAcuerdo>();	
			    	List<DTOPuntosAcuerdo> puntosAmarillo   =  new ArrayList<DTOPuntosAcuerdo>();	
			    	List<DTOPuntosAcuerdo> puntosVerde      =  new ArrayList<DTOPuntosAcuerdo>();
			    	List<DTOPuntosAcuerdo> puntosGris       =  new ArrayList<DTOPuntosAcuerdo>();
			    	List<DTOPuntosAcuerdo> puntosFinalizado =  new ArrayList<DTOPuntosAcuerdo>();	
			    	
			    	for (DTOPuntosAcuerdo punto : puntosAcuerdoSeleccionado) {
							if(punto.getSemaforo().endsWith("gris")){//si esta en gris se evalua si esta sin area
								if(punto.getEstatusGlobal().equals("Sin Áreas")){
									puntosSinArea.add(punto);
									continue;
								}else{
									puntosGris.add(punto);
									continue;
								}
							}
							if(punto.getSemaforo().endsWith("equis")){// si esta rechazado
								puntosRechazado.add(punto);
							}
							if(punto.getSemaforo().endsWith("campana")){//si esta por confirmar validacion
								puntosCampana.add(punto);
							}
							if(punto.getSemaforo().endsWith("rojo")){
								puntosRojo.add(punto);
							}
							if(punto.getSemaforo().endsWith("amarillo")){
								puntosAmarillo.add(punto);
							}
							if(punto.getSemaforo().endsWith("verde")){
								puntosVerde.add(punto);
							}
							if(punto.getSemaforo().endsWith("palomita")){// si esta finalizado
								puntosFinalizado.add(punto);
							}			
					}
			    	
			    	List<DTOPuntosAcuerdo> puntosOrdenados    =  new ArrayList<DTOPuntosAcuerdo>();	
			    		if(puntosSinArea.size() >= 1){
			    			for (DTOPuntosAcuerdo sinArea : puntosSinArea) {
			    				puntosOrdenados.add(sinArea);
							}	    			
			    		}
			    		if(puntosRechazado.size() >= 1){
			    			for (DTOPuntosAcuerdo rechazado : puntosRechazado) {
			    				puntosOrdenados.add(rechazado);
							}	    			
			    		}
			    		if(puntosCampana.size() >= 1){
			    			for (DTOPuntosAcuerdo campana : puntosCampana) {
			    				puntosOrdenados.add(campana);
							}	    			
			    		}
			    		if(puntosRojo.size() >= 1){
			    			for (DTOPuntosAcuerdo rojo : puntosRojo) {
			    				puntosOrdenados.add(rojo);
							}	    			
			    		}
			    		if(puntosAmarillo.size() >= 1){
			    			for (DTOPuntosAcuerdo amarillo : puntosAmarillo) {
			    				puntosOrdenados.add(amarillo);
							}	    			
			    		}
			    		if(puntosVerde.size() >= 1){
			    			for (DTOPuntosAcuerdo verde : puntosVerde) {
			    				puntosOrdenados.add(verde);
							}	    			
			    		}
			    		if(puntosGris.size() >= 1){
			    			for (DTOPuntosAcuerdo gris : puntosGris) {
			    				puntosOrdenados.add(gris);
							}	    			
			    		}
			    		if(puntosFinalizado.size() >= 1){
			    			for (DTOPuntosAcuerdo finalizado : puntosFinalizado) {
			    				puntosOrdenados.add(finalizado);
							}	    			
			    		}
			    		
			    		puntosAcuerdoSeleccionado = puntosOrdenados;
			   }
			 
		 } catch (Exception e) {
			 logger.error("<=================== Clase: MBBandejaCompromisos , Método: onItemSelect");							
		}	 
    }

	/**
	 * Busca el acuerdo seleccionado en la búsqueda
	 * @author Sampier Cuevas Flores
	 * @update Miguel Ortiz
	 * @return void
	 * @since 23/02/2018
	 **/
	public void cambio() {
		anioBusqueda = "";

		if(idBusqueda != null) {
			if(idBusqueda.equals("")) {
				try {
					listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(),
									helpBandejaSeg.getIdTipoDocumento(),
									helpBandejaSeg.getIdNegocio());
				} catch (Exception e) {
					logger.error("Clase: MBBandejaCompromisos, Método: cambio");
				}
			}
		}
	}

	/**
	 *	retorna block si tiene engrose y none si no
	 *
	 * @author Sampier Cuevas Flores
	 * @since 26/10/2017
	 */
	public String tieneEngrose(){
		if(acuerdoSeleccionado.getNumOficioEngrose() != null)
			return "block";
		else
			return "none";
		
	}
	
	/**
	 *	identifica en cuales roles se pueden motrar los botones con privilegios de consulta
	 *
	 * @author Sampier Cuevas Flores
	 * @since 26/10/2017
	 */
	public boolean mostrarBotonesConsulta(){
		if(obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_SE.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_ADMIN.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_ADMIN.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_SE.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_AREA.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_AREA.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_CAU.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC"))
			return true;
		else
			return false;
	}
	/**
	 *	identifica en cuales roles se pueden motrar los botones con priviligios de captura
	 *
	 * @author Sampier Cuevas Flores
	 * @since 26/10/2017
	 */
	public boolean mostrarBotonesCapturaAcuerdos(){
		if(obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_ADMIN.OC")		
			|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_SE.OC"))	{
			if(acuerdoSeleccionado != null && acuerdoSeleccionado.getEstatus().equals(1)){// si el Acuerdo esta completado ya no se puede modificar o eliminar
				return false;
			}
		return true;
		}		
		else
			return false;
	}
	/**
	 *	identifica en cuales roles se pueden motrar los botones con priviligios de captura
	 *
	 * @author Sampier Cuevas Flores
	 * @since 26/10/2017
	 */
	public boolean mostrarBotonesCapturaPuntos(){
		if(obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_SE.OC")
				|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_ADMIN.OC")	){
			if(puntoSeleccionado != null && puntoSeleccionado.getEstatusGlobal().equalsIgnoreCase("Cumplido")){// si el Punto esta completado ya no se puede modificar o eliminar
				
				return false;
			}
		return true;
		}
		if(  obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_AREA.OC")
			|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC")
			|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC"))
		{
			return false;
		}
		
		else
			return false;
	}
	
	/**
	 *	identifica en cuales roles se pueden motrar los semaforos
	 *
	 * @author Sampier Cuevas Flores
	 * @since 26/10/2017
	 */
	public boolean getMostrarSemaforos(){
		if(obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_AREA.OC")
			|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_AREA.OC")
			|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC")
			|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC"))
		{
			return true;
		}		
		else
			return false;
	}
	/**
	 *	identifica en cuales roles se pueden agregar acuerdos y puntos
	 *
	 * @author Sampier Cuevas Flores
	 * @since 15/11/2017
	 */
	public boolean mostrarBotonesAgregarAcuerdo(){
		if(obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_SE.OC")
		|| obtenUsuario().getRolUsuario().equals("ACUERDOS.CAPTURA_ADMIN.OC"))
			return true;
		else
			return false;
	}

	/**
	 * Retorna una lista de cadenas de coincidencia con la búsqueda
	 * @author Sampier Cuevas Flores
	 * @update Miguel Ortiz
	 * @param cadena
	 * @return List<String>
	 * @since 23/02/2018
	 **/
	public List<String> busquedaAcuerdo(String cadena) {
		List<String> results = new ArrayList<String>();

		try {
			if(cadena.length() != 103) {
				acuerdoSeleccionado = null;
				puntosAcuerdoSeleccionado = null;
				busquedaListaAcuerdosAux = bsdBandeja.busquedaAcuerdo( cadena, this.obtenUsuario(),
						  											   helpBandejaSeg.getIdTipoDocumento(),
						  											   helpBandejaSeg.getIdNegocio() );
				for(DTOAcuerdos acuerdo: busquedaListaAcuerdosAux) {
					results.add(acuerdo.getIdNumAcuerdo());
				}
			}
		} catch (Exception e) {
			logger.error("Clase: MBBandejaCompromisos, Método: busquedaAcuerdo");
		}
		
		return results;
	}
	
	/**
	 * Retorna una lista de cadenas de coincidencia con la búsqueda
	 * @author Sampier Cuevas Flores
	 * @update Miguel Ortiz
	 * @return void
	 * @since 23/02/2018
	 **/
	public void busquedaAcuerdoPorFecha() {
		acuerdoSeleccionado = null;
		puntoSeleccionado = null;
		puntosAcuerdoSeleccionado = null;

		if(anioBusqueda != null) {
			if(anioBusqueda.length() < 4) {
				try {
					listaAcuerdos = bsdBandeja.recuperaAcuerdos( this.obtenUsuario(),
																 helpBandejaSeg.getIdTipoDocumento(),
																 helpBandejaSeg.getIdNegocio() );
				 } catch (Exception e) {
					 logger.error("Clase: MBBandejaCompromisos, Método: busquedaAcuerdoPorFecha");
				}
			}

			// Sólo empieza a buscar si se introducen 4 dígitos
			if(anioBusqueda.length() == 4) {
				Calendar cal= Calendar.getInstance();
				int year= cal.get(Calendar.YEAR);

				 try {
					 if(Integer.parseInt(anioBusqueda) <= year) {
						 listaAcuerdos = bsdBandeja.busquedaAcuerdoPorFecha( anioBusqueda, this.obtenUsuario(),
								 											 helpBandejaSeg.getIdTipoDocumento(),
								 											 helpBandejaSeg.getIdNegocio());
					 } else {
						 agregaMensaje(TipoMensaje.INFO_MENSAJE, "El año de búsqueda es mayor al actual");
					 }
				 } catch (Exception e) {
					 logger.error("Clase: MBBandejaCompromisos, Método: busquedaAcuerdoPorFecha");
				}
			}
		}
	}

	/**
	 * Metodo que recupera el Rol del usuario en sesion y valida si cuenta con el rol necesario para la pantalla de Home
	 * @author SampierCuevas
	 * @since 24/07/2017
	 * @param 
	 * @return boolean rolValido
	 * **/
	public boolean validaRoles(){	
		
		this.rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )			
    			
    			// el rol cau quedo como un rol sin uso, al momento
    			//this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_CAU_OC )			||
    			
    	   ){
			
			this.rolValido = true;			
    	}
    
		return this.rolValido;
	}
	
	/**
	 * Metodo que recupera el Rol del usuario en sesion y ve si es de tipo titular ara o area
	 * @author Giovanni Hernández
	 * @since 10/12/2017
	 * @param 
	 * @return boolean es de tipo titular area o area
	 * **/
	public boolean esTitularArea(){
		
		this.rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )			
    			
    	   ){
			
			this.rolValido = true;			
    	}
    
		return this.rolValido;
		
	}
	
	public boolean tieneArea(){
		
		boolean tieneArea = false;
		
		try {
		
			DTOResponsables responsArea = asConvocatoria.recuperarDtoResponsable(this.obtenUsuario().getUsername());
		
			if(!(responsArea == null)) {
				tieneArea = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tieneArea;
	}
	
	
	/**
     * Estaclecer lista de nombres de imagenes de Infografías
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return String
   */
	public List<String> recuperaImgsInfografias(){
		List<String> imgInf = new ArrayList<String>();
		imgInf.add("bandejaAcuerdos/Infografia_bandeja_01.png");
		imgInf.add("bandejaAcuerdos/Infografia_bandeja_02.png");
		return imgInf;
	}
	
	public String obtenNombreAdjunto(String ruta) {
		return boArchivos.getNombreArchivo(ruta);
	}

	public String mensajesTablaPuntos() {
		String etiqueta = "";
		if(helpBandejaSeg.getIdTipoDocumento().equals(1)) {		// Si el documento es un Acuerdo - ROQUE TEMPORAL (CORREGIR)
			if(acuerdoSeleccionado == null)
				etiqueta = "Selecciona un Acuerdo para visualizar la información";
			else
				etiqueta = "El Acuerdo no tiene Puntos";
		}

		if(helpBandejaSeg.getIdTipoDocumento().equals(6)) {		// Si el documento es una Resolución - ROQUE TEMPORAL (CORREGIR)
			if(acuerdoSeleccionado == null)
				etiqueta = "Selecciona una Resolución para visualizar la información";
			else
				etiqueta = "La Resolución no tiene Puntos";
		}

		if(helpBandejaSeg.getIdTipoDocumento().equals(7)) {		// Si el documento es un Dictámen - ROQUE TEMPORAL (CORREGIR)
			if(acuerdoSeleccionado == null)
				etiqueta = "Selecciona un Dictámen para visualizar la información";
			else
				etiqueta = "El Dictámen no tiene Puntos";
		}

		return etiqueta;
	}

	public boolean getMostrarAgregarPunto(){
		if(acuerdoSeleccionado == null){
			return false;
		}else{
			if(acuerdoSeleccionado.getEstatus().equals(1))
				return false;
		}
		return true;		
	}
	
	public void redirect() throws IOException {
	  
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    if(acuerdoSeleccionado.getLigaAcuerdo().indexOf("http") > -1){
	    	  externalContext.redirect(acuerdoSeleccionado.getLigaAcuerdo());	
	    }else{
	    	externalContext.redirect("http://"+acuerdoSeleccionado.getLigaAcuerdo());
	    }
	}

		
//////////////////////////GETTERS AND SETTERS/////////////////////////////
	
	public List<DTOAcuerdos> getListaAcuerdos() {
		return listaAcuerdos;
	}

	public void setListaAcuerdos(List<DTOAcuerdos> listaAcuerdos) {
		this.listaAcuerdos = listaAcuerdos;
	}

	public DTOAcuerdos getAcuerdoSeleccionado() {
		return acuerdoSeleccionado;
	}

	public void setAcuerdoSeleccionado(DTOAcuerdos acuerdoSeleccionado) {
		this.acuerdoSeleccionado = acuerdoSeleccionado;
	}

	public List<DTOPuntosAcuerdo> getPuntosAcuerdoSeleccionado() {
		return puntosAcuerdoSeleccionado;
	}

	public void setPuntosAcuerdoSeleccionado(
			List<DTOPuntosAcuerdo> puntosAcuerdoSeleccionado) {
		this.puntosAcuerdoSeleccionado = puntosAcuerdoSeleccionado;
	}

	public DTOPuntosAcuerdo getPuntoSeleccionado() {
		return puntoSeleccionado;
	}

	public void setPuntoSeleccionado(DTOPuntosAcuerdo puntoSeleccionado) {
		this.puntoSeleccionado = puntoSeleccionado;
	}

	public List<DTOTipoSesiones> getTipoSesiones() {
		return tipoSesiones;
	}

	public void setTipoSesiones(List<DTOTipoSesiones> tipoSesiones) {
		this.tipoSesiones = tipoSesiones;
	}

	public String getIdBusqueda() {
		return idBusqueda;
	}

	public void setIdBusqueda(String idBusqueda) {
		this.idBusqueda = idBusqueda;
	}

	public List<DTOAcuerdos> getBusquedaListaAcuerdosAux() {
		return busquedaListaAcuerdosAux;
	}

	public void setBusquedaListaAcuerdosAux(
			List<DTOAcuerdos> busquedaListaAcuerdosAux) {
		this.busquedaListaAcuerdosAux = busquedaListaAcuerdosAux;
	}

	public String getBuscarPor() {
		return buscarPor;
	}

	public void setBuscarPor(String buscarPor) {
		if(buscarPor.equals("Acuerdo")){
			anioBusqueda 				= "";
			acuerdoSeleccionado 	  	= null;
			puntoSeleccionado			= null;
			puntosAcuerdoSeleccionado 	= null;
			listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());
		}
		if(buscarPor.equals("Fecha")){
			idBusqueda = "";
			acuerdoSeleccionado 	  	= null;
			puntoSeleccionado			= null;
			puntosAcuerdoSeleccionado 	= null;
			listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());
		}
		this.buscarPor = buscarPor;
	}

//	public void selectRadioItemTipo(){
//		idBusqueda					= "";
//		acuerdoSeleccionado 	  	= null;
//		puntoSeleccionado			= null;
//		puntosAcuerdoSeleccionado	= null;
//		listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());
//	}

	public void recuperarDocumentosPorNegocio(Integer idNegocio) {
		idBusqueda 					= "";
		anioBusqueda				= "";
		acuerdoSeleccionado 	  	= null;
		puntoSeleccionado			= null;
		puntosAcuerdoSeleccionado	= null;
		buscarPor					= "Acuerdo";	// Búsqueda por default: Número de documento
		helpBandejaSeg.setIdNegocio(idNegocio);
		helpBandejaSeg.setIdTipoDocumento(1);		// Tipo de documento por default: Acuerdo
		listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());
	}

	public void recuperarDocumentosPorTipo(Integer idTipoDocumento) {
		idBusqueda 					= "";
		anioBusqueda				= "";
		acuerdoSeleccionado 	  	= null;
		puntoSeleccionado			= null;
		puntosAcuerdoSeleccionado	= null;
		buscarPor					= "Acuerdo";	// Búsqueda por default: Número de documento
		helpBandejaSeg.setIdTipoDocumento(idTipoDocumento);
		listaAcuerdos = bsdBandeja.recuperaAcuerdos(this.obtenUsuario(), helpBandejaSeg.getIdTipoDocumento(), helpBandejaSeg.getIdNegocio());
	}

	public String getAnioBusqueda() {
		return anioBusqueda;
	}

	public void setAnioBusqueda(String anioBusqueda) {
		this.anioBusqueda = anioBusqueda;
	}

	public List<DTOSeguimientosCG> getPuntoEnSeguimiento() {
		return puntoEnSeguimiento;
	}

	public void setPuntoEnSeguimiento(List<DTOSeguimientosCG> puntoEnSeguimiento) {
		this.puntoEnSeguimiento = puntoEnSeguimiento;
	}

	public String getRutaPrevisualizacionAcuerdo() {
		return rutaPrevisualizacionAcuerdo;
	}

	public void setRutaPrevisualizacionAcuerdo(String rutaPrevisualizacionAcuerdo) {
		this.rutaPrevisualizacionAcuerdo = rutaPrevisualizacionAcuerdo;
	}

	public boolean getSinAreas() {
		if(puntoSeleccionado != null && puntoSeleccionado.getEstatusGlobal().equals("Sin Áreas")){
			return false;
		}else
		    return true;
	}

	public void setSinAreas(boolean sinAreas) {
		this.sinAreas = sinAreas;
	}

	public String getIdNumAcuerdoSeg() {
		return idNumAcuerdoSeg;
	}

	public void setIdNumAcuerdoSeg(String idNumAcuerdoSeg) {
		this.idNumAcuerdoSeg = idNumAcuerdoSeg;
	}

	public String getNumeraliaSeg() {
		return numeraliaSeg;
	}

	public void setNumeraliaSeg(String numeraliaSeg) {
		this.numeraliaSeg = numeraliaSeg;
	}

	public HelperBandejaSeguimiento getHelpBandejaSeg() {
		return helpBandejaSeg;
	}

	public void setHelpBandejaSeg(HelperBandejaSeguimiento helpBandejaSeg) {
		this.helpBandejaSeg = helpBandejaSeg;
	}

}
