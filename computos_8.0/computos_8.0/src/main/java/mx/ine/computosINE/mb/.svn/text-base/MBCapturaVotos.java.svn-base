package mx.ine.computosINE.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.computosINE.bsd.BSDCapturaVotosInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.bsd.BSDGeneracionActasInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOAgrupacionCasillas;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCEstatus;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistrito;
import mx.ine.computosINE.dto.DTORegiduria;
import mx.ine.computosINE.dto.DTOSeccion;
import mx.ine.computosINE.dto.DTOSubcoalicion;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.form.FormCapturaActas;
import mx.ine.computosINE.helper.HLPInfoGralComputos;
import mx.ine.computosINE.mb.MBGeneric.TipoMensaje;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.UtilUpload;
import mx.ine.computosINE.util.Utilidades;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase encargada de interactuar con las pantallas de captura de votos y el negocio de ellas.
 * 
 * @author Giovanni Hernández Alonso
 * @since 12/04/2017
 * @copyright INE
 */

@Scope("session")
@Controller("mbCapturaVotos")
public class MBCapturaVotos extends MBGeneric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061659233861583758L;
	
    /**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
    private static final Logger LOGGER = Logger.getLogger(MBCapturaVotos.class);

    /**
	 *	DECLARACION DE VARIABLES 
	 */
    
    @Autowired
    @Qualifier("bsdCargaInformacion")
    private transient BSDCargaInformacionInterface bsdCargaInformacion;
    
    @Autowired
    @Qualifier("bsdCapturaVotos")
    private transient BSDCapturaVotosInterface bsdCapturaVotos;
    
    // BSD para invocar el metodo de los votos acumulados de los partidos en un detalle geografico y lo de las validaciones de las distribuciones
    @Autowired
	@Qualifier("bsdDistribucionVotos")
	private transient BSDDistribucionVotosInterface bsdDistribucionVotos;
    
    // se ocupa para verificar si un modulo esta abierto o no, esto se gestiona desde el sistema del admin
	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema admin;
    
	// se ocupa para verificar si alguna de las 4 candidaturas en un detalle geografico cuenta con un acta final
	@Autowired
	@Qualifier("bsdGeneracionActas")
	public transient BSDGeneracionActasInterface bsdActas;
	
    @Autowired
    @Qualifier("constante")
    private transient Constantes constantes;
    
    private FormCapturaActas dto;
    
    private String candidatura;
    
    private String accion;
    
    private StreamedContent imagen = new DefaultStreamedContent();
    
    private DTOCEstatus estatusRecuentoTotal;
    
    private DTOAgrupacionCasillas casillasParaConsultarRespaldtoTotal;
    
    
	// solo los roles de 
	// "COMPUTOS.ADMIN.PARAM.CAPTURA.OC"
	// "COMPUTOS.ADMIN.CAPTURA.OC"
	// "COMPUTOS.CAPTURA.JM"
	// tienen acceso a la captura en los modulos de: Captura de Ayuntamiento, Captura de Gobernador, Captura de Diputados MR y Captura de Regidurias MR
    public boolean validarRolParaCaptura(){
    	
    	if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_MUNICIPAL ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.ADMIN_CAPTURA_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.ADMIN_PARAM_CAPTURA_OC )
    	   )
    	{
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
    
	// solo los roles de 
	// "COMPUTOS.ADMIN.PARAM.CAPTURA.OC"
	// "COMPUTOS.ADMIN.CAPTURA.OC"
	// "COMPUTOS.CAPTURA.JM"
	// tienen acceso al modifica en los modulos de: Captura de Ayuntamiento, Captura de Gobernador, Captura de Diputados MR y Captura de Regidurias MR
    public boolean validarRolParaModifica(){
    	
    	if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_MUNICIPAL ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.ADMIN_CAPTURA_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.ADMIN_PARAM_CAPTURA_OC )
    	   )
    	{
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
    
    
	// solo los roles de 
	// "COMPUTOS.ADMIN.PARAM.CAPTURA.OC"
	// "COMPUTOS.ADMIN.CAPTURA.OC"
	// "COMPUTOS.CAPTURA.JM"
	// tienen acceso a la función de eliminar actas en los modulos de: Captura de Ayuntamiento, Captura de Gobernador, Captura de Diputados MR y Captura de Regidurias MR
    public boolean deshabilitaEliminarPorRol(){
    	
    	if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_MUNICIPAL ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.ADMIN_CAPTURA_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.ADMIN_PARAM_CAPTURA_OC )
    	   )
    	{
    		return false;
    	}
    	else{
    		return true;
    	}
    	
    }
    
    
    // metodo que evalua las actas de casilla existentes para la demarcación geografica seleccionada en los combos de navegación laterales, y verifica
    // si existen aun actas de casillas por capturar
    public boolean hayActasPorCapturar(){
    	
    	// incializamos variables (candidatura y accion ya fueron seteadas en el flow antes de llegar a esta ejecución)
    	dto = new FormCapturaActas(this.obtenUsuario(), candidatura );
    	
    	// se recuperan las casillas, secciones, distritos, demarcaciones y estatus segun lo requerido por la pantalla
    	recuperaCatalogosCalculados();
    	
    	if(esTipoGobernadorAyuntamiento()){
    		if( dto.getCasillasPorCapturar().getSecciones().size()>0 ){
    			
    			return true;
    		}
    	}
    	
    	else if(esTipoDiputado()){
    		if( dto.getCasillasPorCapturar().getDistritos().size() > 0 ){
    			return true;
    		}
    	}
    	
    	else if( esTipoRegiduria() ){
    		if( dto.getCasillasPorCapturar().getRegidurias().size() > 0 ){
    			return true;
    		}
    	}
    	
    	agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("validacion_mensaje_generales_no_hay_actas_por_capturar"));
    	return false;
    }
    
    
    // metodo que inicializa las precondiciones de la pantalla para dejarla en un estado inicial para modificar
    public void initModificar(){
    	
    	// incializamos variables (candidatura y accion ya fueron seteadas en el flow antes de llegar a esta ejecución)
    	dto = new FormCapturaActas(this.obtenUsuario(), candidatura );
    	
    	recuperaCatalogosCalculados();
    	
    	// si no hay actas a modificar, no poblamos el combo de estatus 
    	if( dto.getCasillasEnPantalla().size() < 1 ){
    		dto.getListaEstatus().clear();
    		agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("validacion_mensaje_generales_no_hay_actas_capturadas"));
    	}
    }
    
    
    // metodo que nos inicializa las precondiciones de la pantalla para dejarla en un estado inicial para la consulta
    public void initConsultar(){
    	
    	// incializamos variables (candidatura y accion ya fueron seteadas en el flow antes de llegar a esta ejecución)
    	dto = new FormCapturaActas(this.obtenUsuario(), candidatura );

    	recuperaCatalogosCalculados();
    	
    	// si no hay actas en pantalla 
    	if( dto.getCasillasEnPantalla().size() < 1 ){
    		agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("validacion_mensaje_generales_no_hay_actas_capturadas"));
    	}    		
    }

    
    
   	// se recuperan las casillas, secciones, distritos, demarcaciones y estatus segun lo requerido por la pantalla
    public void recuperaCatalogosCalculados(){
    	
    	
    	this.estatusRecuentoTotal = new DTOCEstatus();
    	
    	// recuperamos los estatus, los almacena en "dto.listaEstatus", pero quitamos el de "RECUENTO TOTAL"
    	try {
    		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();
    		estatus.addAll( bsdCargaInformacion.estatusComputos() );
    		
    		estatusRecuentoTotal = estatus.get( estatus.size()-1 );
    		
    		// Quitamos el estatus de recuento total solo si no se trata de una pantalla de consulta
    		if( !this.accion.equals(constantes.ACCION_CONSULTA) ){
    			estatus.remove(estatus.size()-1);
    		}
    		
    		dto.setListaEstatus( estatus );			
		} catch (Exception e) {
			LOGGER.error("Error MBCapturaVotos - recuperaCatalogos() - recuperamos los estatus ", e);
		}
    	
    	// recuperamos el total de las casillas (capturadas y no capturadas) y las agrupamos segun el detalle geografico
    	// las almacenamos en "dto.TotalDeCasillas.Secciones.Casillas" 				o 
    	//                	  "dto.TotalDeCasillas.distritos.secciones.casillas" 	o 
    	//				   	  "dto.TotalDeCasillas.regidurias.secciones.casillas"
      	try {
      		
      		List<DTOCasillaWS> casillasService = new ArrayList<DTOCasillaWS>();
      		
      		if( esTipoGobernadorAyuntamiento() || esTipoDiputado() ){
      				casillasService = bsdCargaInformacion.casillasAprobadasPorMunicipioLocal(	dto.getDetalleProcesoElectoral(), 
																								dto.getIdEdoSeleccionado(), 
																								dto.getIdMunSeleccionado());
      		}
      		
      		else if( esTipoRegiduria() ){
      			casillasService = bsdCargaInformacion.obtenSecCasillasAprobadasPorRegiduria(
																								dto.getDetalleProcesoElectoral(),
																								dto.getIdEdoSeleccionado(),
																								dto.getIdMunSeleccionado(),
																								null
																							);
      		}
      	
      		// agrupamos el total de casillas existentes en el objeto dto.TotalDeCasillas
      		if( casillasService != null && casillasService.size() > 0 ){
      			agrupaTotalCasillas(casillasService);      			
      		}
		} catch (Exception e) {
			LOGGER.error("Error MBCapturaVotos - recuperaCatalogos() - recuperamos el total de las casillas ", e);
//			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al momento de cargar la información de casillas");
		}
      	
      	
      	// recuperamos las casillas capturadas en BD para este nivel geografico dado
      	// las almacena en "dto.casillasCapturadas"
      	recuperaCasillasCapturadas();
      	
      	
    	// identifica y agrupa las casillas faltantes por capturar y las ya capturadas
        // las No Capturadas se guardan en:
        // para Gob y Ayuntamiento las ordena por secciones en 				"dto.casillasPorCapturar.secciones"
        // para Diputado MR y RP las ordena por Distritos, Secciones en 	"dto.casillasPorCapturar.distritos"
        // para Regiduria MR y RP las ordena por Demarcación, Secciones en 	"dto.casillasPorCapturar.regidurias"
        // analogamente lo mismo para las capturadas, pero en el objeto 	"dto.casillasParaConsultar"
      	identificaAgrupaCasillaPorCapturarYConsultar();
      	
      	
      	// solo si es ayuntamiento o gobernador y es captura o modificación se toma en cuenta el recuento total globalmente para el municipio seleccionado
      	if( 	
      			esTipoGobernadorAyuntamiento() &&
      			( this.accion.equals(constantes.ACCION_CAPTURA) || this.accion.equals(constantes.ACCION_MODIFICA) )
      	  )
      	{
      		//evaluamos si hay recuento total (esto se verifica en el metodo anterior de "identificaAgrupaCasillaPorCapturar" )
          	if(dto.isEsRecuentoTotal()){
        		dto.getListaEstatus().clear();
        		dto.getListaEstatus().add(estatusRecuentoTotal);
          	}
      	}
      	
      	
      	// calculamos las actas capturadas, las por ser capturadas y el porcentaje de avance, el avance de actas capturadas
      	// para gobernador y ayuntamiento es por municipio, para diputados es por el distrito parcial seleccionadp y para regidurias por la regiduria seleccionada
      	if(  esTipoGobernadorAyuntamiento()  ){
      		calculaAvanceCapturaPorMunicipio();
      	}
      	
      	
      	// cargamos los objetos para ser mostrados en pantalla
      	if(this.accion.equals(constantes.ACCION_CAPTURA)){

          	if(esTipoGobernadorAyuntamiento() && dto.getCasillasPorCapturar().getSecciones() != null && dto.getCasillasPorCapturar().getSecciones().size() > 0){
          		
          		dto.setSeccionesEnPantalla( dto.getCasillasPorCapturar().getSecciones());
          		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas());
          		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
          		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
        											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
        											 dto.getCasillasEnPantalla().get(0).getExtContigua()
        										  );
          	}
          	
          	else if(esTipoDiputado() && dto.getCasillasPorCapturar().getDistritos() != null && dto.getCasillasPorCapturar().getDistritos().size() > 0){
          		
          		dto.setSeccionesEnPantalla( dto.getCasillasPorCapturar().getDistritos().get(0).getSecciones());
          		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas());
          		
          		// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
          		dto.getActaCasilla().getId().setIdDistrito(  dto.getCasillasPorCapturar().getDistritos().get(0).getDistrito() );
          		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
          		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
        											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
        											 dto.getCasillasEnPantalla().get(0).getExtContigua()
        										  );
          		
          		calculaAvanceCapturaRegDistrito();
          		
          		evaluaRecuentoTotalPorDisReg();
              	if(dto.isEsRecuentoTotal()){
            		dto.getListaEstatus().clear();
            		dto.getListaEstatus().add(estatusRecuentoTotal);
              	}
          	}
          	
          	else if( esTipoRegiduria() && dto.getCasillasPorCapturar().getRegidurias() != null && dto.getCasillasPorCapturar().getRegidurias().size() > 0 ){
          		
          		dto.setSeccionesEnPantalla( dto.getCasillasPorCapturar().getRegidurias().get(0).getSecciones() );
          		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas() );
          		
          		// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista	
          		dto.getActaCasilla().getId().setIdRegiduria( dto.getCasillasPorCapturar().getRegidurias().get(0).getIdRegiduria() );
          		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
          		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
        											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
        											 dto.getCasillasEnPantalla().get(0).getExtContigua()
        										  );
          		
          		calculaAvanceCapturaRegDistrito();
          		
          		evaluaRecuentoTotalPorDisReg();
              	if(dto.isEsRecuentoTotal()){
            		dto.getListaEstatus().clear();
            		dto.getListaEstatus().add(estatusRecuentoTotal);
              	}
          	}     			
      	}
      	
      	else if( this.accion.equals(constantes.ACCION_CONSULTA)  ||  this.accion.equals(constantes.ACCION_MODIFICA) ){
          	
      		if(esTipoGobernadorAyuntamiento() && dto.getCasillasParaConsultar().getSecciones() != null && dto.getCasillasParaConsultar().getSecciones().size() > 0){
          		
          		// para la pantalla de modifica, vemos si se trata de un modifica de recuento total, 
          		if( this.accion.equals(constantes.ACCION_MODIFICA) && dto.isEsRecuentoTotal()  ){

              		if(dto.getCasillasParaConsultarEnRTotal().getSecciones() != null && dto.getCasillasParaConsultarEnRTotal().getSecciones().size() > 0){

	      		  		// si es recuento total, al modificar solo consideramos las actas capturadas en recuento total
	              		dto.setSeccionesEnPantalla( dto.getCasillasParaConsultarEnRTotal().getSecciones());
	              		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas());
	              		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
	              		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
	            											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
	            											 dto.getCasillasEnPantalla().get(0).getExtContigua()
	            										  );
	              	
	              		if( dto.getCasillasEnPantalla().size() > 0 ){
	              			reiniciaEstatusAsociacionesYCargaCaptura();
	              		}
              		}
          		}
          		
          		// si es una consulta o modifica pero no de recuento total, sino un modifica de captura normal
          		else{
          			dto.setSeccionesEnPantalla( dto.getCasillasParaConsultar().getSecciones());
              		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas());
              		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
              		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
            											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
            											 dto.getCasillasEnPantalla().get(0).getExtContigua()
            										  );
              	
              		if( dto.getCasillasEnPantalla().size() > 0 ){
              			reiniciaEstatusAsociacionesYCargaCaptura();
              		}
          		}
          		
      		}
      		
          	else if(esTipoDiputado() && dto.getCasillasParaConsultar().getDistritos() != null && dto.getCasillasParaConsultar().getDistritos().size() > 0){
          			
          		// para las pantallas de modifica en la lista de distritos, si uno esta en recuento total se carga la versión de ese distrito en recuento total
          		// para las pantallas de consulta en la lista de distritos, si uno esta en recuento total se mantiene el distrito normal pero se le habilita su bandera de recuento total
          		//if( dto.getCasillasParaConsultarEnRTotal().getDistritos().size() > 0 ){
          			
          			List<DTODistrito> temp = new ArrayList<DTODistrito>();
          			
          			for (DTODistrito dis : dto.getCasillasParaConsultar().getDistritos() ) {
					
          				DTODistrito disRTotal 	= new DTODistrito();
          				boolean enRTotal		= false;
          				
          				for ( DTODistrito disRT : dto.getCasillasParaConsultarEnRTotal().getDistritos()  ) {
							if( disRT.getDistrito().equals( dis.getDistrito() ) ){
								enRTotal = true;
								disRTotal = disRT;
							}
						}
          			
          				//if( this.accion.equals(constantes.ACCION_MODIFICA) ){
          					if(enRTotal){
          						disRTotal.setEsRecuentoTotal(true);
              					temp.add(disRTotal);
              				}
              				else{
              					
              					for ( DTODistrito disCap :  dto.getCasillasPorCapturar().getDistritos()  ) {
									if( disCap.getDistrito().equals( dis.getDistrito() ) && disCap.isEsRecuentoTotal()  ){
										enRTotal = true;
									}
								}
              					
              					
              					if( !enRTotal ){
              						temp.add(dis);
              					}
              					
              				}
          				//}
          				//else{
          					dis.setEsRecuentoTotal( enRTotal );
          				//}
          				
          			}
          			
          			if( this.accion.equals(constantes.ACCION_MODIFICA) ){
          				casillasParaConsultarRespaldtoTotal = new DTOAgrupacionCasillas();
          				casillasParaConsultarRespaldtoTotal.setDistritos( dto.getCasillasParaConsultar().getDistritos() );
          				dto.getCasillasParaConsultar().setDistritos( temp );
          			}
          			
          		//}
          		
          		if( dto.getCasillasParaConsultar().getDistritos() != null && dto.getCasillasParaConsultar().getDistritos().size() > 0 ){
          			
	          		dto.setEsRecuentoTotal( false );
	          		dto.setEsRecuentoTotal( dto.getCasillasParaConsultar().getDistritos().get(0).isEsRecuentoTotal() );
	          		
	          		dto.setSeccionesEnPantalla( dto.getCasillasParaConsultar().getDistritos().get(0).getSecciones());
	          		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas());
	          		
	          		// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
	          		dto.getActaCasilla().getId().setIdDistrito(  dto.getCasillasParaConsultar().getDistritos().get(0).getDistrito() );
	          		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
	          		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
	        											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
	        											 dto.getCasillasEnPantalla().get(0).getExtContigua()
	        										  );
	          		
	          		if( dto.getCasillasEnPantalla().size() > 0 ){
	          			reiniciaEstatusAsociacionesYCargaCaptura();
	          		}
	      		
	          		calculaAvanceCapturaRegDistrito();
	          		
	          		if( this.accion.equals(constantes.ACCION_MODIFICA) ){	
	          			
	          			evaluaRecuentoTotalPorDisReg();
	          			if(dto.isEsRecuentoTotal()){
	                		dto.getListaEstatus().clear();
	                		dto.getListaEstatus().add(estatusRecuentoTotal);
	                  	}
	          		}
          		
          		}
          	}
      		
          	else if( esTipoRegiduria() && dto.getCasillasParaConsultar().getRegidurias() != null && dto.getCasillasParaConsultar().getRegidurias().size() > 0 ){
          		
          		// para las pantallas de modifica en la lista de regidurias, si una esta en recuento total se carga la versión de esa regiduria en recuento total
          		// para las pantallas de consulta en la lista de regidurias, si una esta en recuento total se mantiene la regiduria normal pero se le habilita su bandera de recuento total
          		//if( dto.getCasillasParaConsultarEnRTotal().getRegidurias().size() > 0 ){
          			
          			List<DTORegiduria> temp = new ArrayList<DTORegiduria>();
          			
          			for (DTORegiduria reg : dto.getCasillasParaConsultar().getRegidurias() ) {
					
          				DTORegiduria regRTotal 	= new DTORegiduria();
          				boolean enRTotal		= false;
          				
          				for ( DTORegiduria regRT : dto.getCasillasParaConsultarEnRTotal().getRegidurias()  ) {
							if( regRT.getIdRegiduria().equals( reg.getIdRegiduria() ) ){
								enRTotal = true;
								regRTotal = regRT;
							}
						}
          			
          				//if( this.accion.equals(constantes.ACCION_MODIFICA) ){
          					// si esta en recuento total y almenos una acta ya se recapturo en recuento total
          					if(enRTotal){
          						regRTotal.setEsRecuentoTotal(true);
              					temp.add(regRTotal);
              				}
          					// si esta en casillas para consultar pero no se encontro en las consultas de recuento total, vemos si esta en recuento total pero no se ha capturado nada, en dado caso no deberia aprecer en la pantalla de modifica
              				else{
              					
              					for ( DTORegiduria regCap :  dto.getCasillasPorCapturar().getRegidurias()  ) {
									if( regCap.getIdRegiduria().equals( reg.getIdRegiduria() )  && regCap.isEsRecuentoTotal() ){
										enRTotal = true;
									}
								}
              					
              					
              					if( !enRTotal ){
              						temp.add(reg);
              					}
              				}
          				//}
          				//else{
          					reg.setEsRecuentoTotal( enRTotal );
          				//}
          				
          			}
          			
          			if( this.accion.equals(constantes.ACCION_MODIFICA) ){
          				casillasParaConsultarRespaldtoTotal = new DTOAgrupacionCasillas();
          				casillasParaConsultarRespaldtoTotal.setRegidurias( dto.getCasillasParaConsultar().getRegidurias() );
          				dto.getCasillasParaConsultar().setRegidurias( temp );
          			}
          			
          		//}
          		
          		if(  dto.getCasillasParaConsultar().getRegidurias() != null && dto.getCasillasParaConsultar().getRegidurias().size() > 0 ){
          			
	          		dto.setEsRecuentoTotal( false );
	          		dto.setEsRecuentoTotal( dto.getCasillasParaConsultar().getRegidurias().get(0).isEsRecuentoTotal() );
	          		
	          		dto.setSeccionesEnPantalla( dto.getCasillasParaConsultar().getRegidurias().get(0).getSecciones() );
	          		dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas() );
	          		
	          		// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista	
	          		dto.getActaCasilla().getId().setIdRegiduria( dto.getCasillasParaConsultar().getRegidurias().get(0).getIdRegiduria() );
	          		dto.getActaCasilla().getId().setSeccion( dto.getSeccionesEnPantalla().get(0).getSeccion() );
	          		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
	        											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
	        											 dto.getCasillasEnPantalla().get(0).getExtContigua()
	        										  );
	          		
	          		if( dto.getCasillasEnPantalla().size() > 0 ){
	          			reiniciaEstatusAsociacionesYCargaCaptura();
	          		}
	          		
	          		calculaAvanceCapturaRegDistrito();
	          		
	          		if( this.accion.equals(constantes.ACCION_MODIFICA) ){
	          		
	          			evaluaRecuentoTotalPorDisReg();
	          			if(dto.isEsRecuentoTotal()){
	                		dto.getListaEstatus().clear();
	                		dto.getListaEstatus().add(estatusRecuentoTotal);
	                  	}
	          		}
          		}
          	}    		
      	}
    }
    
    
    // esta función se dispara cada vez que cambia el select de Distritos o Regidurias en la pantalla, para recargar las secciones pertenecientes al distrito o regiduria seleccionada
    public void cambiaSecciones(){
    	
    	// inicializa al estatus de "Selecciona" y limpia las asociaciones cargadas
    	reiniciaEstatusAsociaciones();
    	
    	if( this.accion.equals(constantes.ACCION_CAPTURA) ){
    		
    		// Para Diputados, cargamos las secciones a ser mostradas en pantalla 
    		if( esTipoDiputado() && dto.getCasillasPorCapturar().getDistritos() != null &&  dto.getCasillasPorCapturar().getDistritos().size() > 0  ){
          		
          		for ( DTODistrito dis : dto.getCasillasPorCapturar().getDistritos()) {
    				
          			if( dis.getDistrito().equals(  dto.getActaCasilla().getId().getIdDistrito() ) ){
          				
          				dto.setSeccionesEnPantalla( dis.getSecciones() );
          				dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas() );
          				// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
          				dto.getActaCasilla().getId().setSeccion(dto.getSeccionesEnPantalla().get(0).getSeccion());
          				dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getExtContigua()
    													  );
          			}
    			}

          		calculaAvanceCapturaRegDistrito();
          		
          		evaluaRecuentoTotalPorDisReg();
              	if(dto.isEsRecuentoTotal()){
            		dto.getListaEstatus().clear();
            		dto.getListaEstatus().add(estatusRecuentoTotal);
              	}
              	else{
              		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();
            		try {
						estatus.addAll( bsdCargaInformacion.estatusComputos() );
					} catch (Exception e) {
						e.printStackTrace();
					}
            		estatus.remove(estatus.size()-1);
              		dto.getListaEstatus().clear();
            		dto.setListaEstatus( estatus );	
              	}
          	}
    	
            // Para REGIDURIAS, cargamos las secciones a ser mostradas en pantalla 
          	else if( esTipoRegiduria() && dto.getCasillasPorCapturar().getRegidurias() != null && dto.getCasillasPorCapturar().getRegidurias().size() > 0 ){
          		
          		for ( DTORegiduria reg : dto.getCasillasPorCapturar().getRegidurias() ) {
    				
          			if( reg.getIdRegiduria().equals( dto.getActaCasilla().getId().getIdRegiduria() ) ){
          				
          				dto.setSeccionesEnPantalla( reg.getSecciones() );
          				dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas() );
         				// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
          				dto.getActaCasilla().getId().setSeccion(dto.getSeccionesEnPantalla().get(0).getSeccion());
          				dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getExtContigua()
    													  );
          			}
          			
    			}
          		
          		calculaAvanceCapturaRegDistrito();
          		
          		evaluaRecuentoTotalPorDisReg();
              	if(dto.isEsRecuentoTotal()){
            		dto.getListaEstatus().clear();
            		dto.getListaEstatus().add(estatusRecuentoTotal);
              	}
              	else{
              		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();
            		try {
						estatus.addAll( bsdCargaInformacion.estatusComputos() );
					} catch (Exception e) {
						e.printStackTrace();
					}
            		estatus.remove(estatus.size()-1);
              		dto.getListaEstatus().clear();
            		dto.setListaEstatus( estatus );	
              	}
          	}
    	}
      	
      	
    	else if( this.accion.equals(constantes.ACCION_CONSULTA)  ||  this.accion.equals(constantes.ACCION_MODIFICA) ){

    		// Para Diputados, cargamos las secciones a ser mostradas en pantalla 
    		if( esTipoDiputado() && dto.getCasillasParaConsultar().getDistritos() != null &&  dto.getCasillasParaConsultar().getDistritos().size() > 0  ){
          		
          		for ( DTODistrito dis : dto.getCasillasParaConsultar().getDistritos()) {
    				
          			if( dis.getDistrito().equals(  dto.getActaCasilla().getId().getIdDistrito() ) ){
          				
          				dto.setSeccionesEnPantalla( dis.getSecciones() );
          				dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas() );
          				// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
          				dto.getActaCasilla().getId().setSeccion(dto.getSeccionesEnPantalla().get(0).getSeccion());
          				dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getExtContigua()
    													  );
          				
          				if( dto.getCasillasEnPantalla().size() > 0 ){
                  			reiniciaEstatusAsociacionesYCargaCaptura();
                  		}
          			}
    			}
          		
          		calculaAvanceCapturaRegDistrito();
          	
      			evaluaRecuentoTotalPorDisReg();
      		
      			if( this.accion.equals(constantes.ACCION_MODIFICA) ){
	      			if(dto.isEsRecuentoTotal()){
	            		dto.getListaEstatus().clear();
	            		dto.getListaEstatus().add(estatusRecuentoTotal);
	              	}
	              	else{
	              		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();
	            		try {
							estatus.addAll( bsdCargaInformacion.estatusComputos() );
						} catch (Exception e) {
							e.printStackTrace();
						}
	            		estatus.remove(estatus.size()-1);
	              		dto.getListaEstatus().clear();
	            		dto.setListaEstatus( estatus );	
	              	}
          		}
          	}
    		
            // Para REGIDURIAS, cargamos las secciones a ser mostradas en pantalla 
          	else if( esTipoRegiduria() && dto.getCasillasParaConsultar().getRegidurias() != null && dto.getCasillasParaConsultar().getRegidurias().size() > 0 ){
          		
          		for ( DTORegiduria reg : dto.getCasillasParaConsultar().getRegidurias() ) {
    				
          			if( reg.getIdRegiduria().equals( dto.getActaCasilla().getId().getIdRegiduria() ) ){
          				
          				dto.setSeccionesEnPantalla( reg.getSecciones() );
          				dto.setCasillasEnPantalla( dto.getSeccionesEnPantalla().get(0).getCasillas() );
         				// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
          				dto.getActaCasilla().getId().setSeccion(dto.getSeccionesEnPantalla().get(0).getSeccion());
          				dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
    														 dto.getCasillasEnPantalla().get(0).getExtContigua()
    													  );
          				
          				if( dto.getCasillasEnPantalla().size() > 0 ){
                  			reiniciaEstatusAsociacionesYCargaCaptura();
                  		}
          			}
          			
    			}
          		
          		calculaAvanceCapturaRegDistrito();
          		
          		evaluaRecuentoTotalPorDisReg();
              	
          		if( this.accion.equals(constantes.ACCION_MODIFICA) ){
		  			if(dto.isEsRecuentoTotal()){
		        		dto.getListaEstatus().clear();
		        		dto.getListaEstatus().add(estatusRecuentoTotal);
		          	}
		          	else{
		          		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();
		        		try {
							estatus.addAll( bsdCargaInformacion.estatusComputos() );
						} catch (Exception e) {
							e.printStackTrace();
						}
		        		estatus.remove(estatus.size()-1);
		          		dto.getListaEstatus().clear();
		        		dto.setListaEstatus( estatus );	
		          	}
          		}
          	}
    	}
    	scrollTop();
    }
    /**
	 * Método para subir el scroll en la vista y que el usuario pueda observar
	 * los mensajes de acuerdo a la acción.
	 */
	public void scrollTop() {
		RequestContext.getCurrentInstance().scrollTo("formCapturaActa:msj");
	}
    
    // esta función se dispara cada vez que cambia el select de secciones en la pantalla, para recargar las casillas pertenecientes a la sección seleccionada
    public void cambiaCasillas(){
    	
    	// inicializa al estatus de "Selecciona" y limpia las asociaciones cargadas
    	reiniciaEstatusAsociaciones();
    	
      	if(this.accion.equals(constantes.ACCION_CAPTURA)){

        	// Para Gobernador y Ayuntamiento,cargamos las casillas a ser mostrados en pantalla
          	if(esTipoGobernadorAyuntamiento() && dto.getCasillasPorCapturar().getSecciones() != null && dto.getCasillasPorCapturar().getSecciones().size() > 0){
          		
          		for (DTOSeccion sec : dto.getCasillasPorCapturar().getSecciones()) {
            		if(sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() )){
            		
            			dto.setCasillasEnPantalla(sec.getCasillas());
            			// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
            	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
            	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
            	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
            	  										  );
            		}
        		}
          		
          	}
          	
          	// Para Diputados, cargamos las casillas a ser mostrados en pantalla
          	else if( esTipoDiputado() && dto.getCasillasPorCapturar().getDistritos() != null && dto.getCasillasPorCapturar().getDistritos().size() > 0 ){
          		
          		for ( DTODistrito dis : dto.getCasillasPorCapturar().getDistritos() ) {
          			
          			for ( DTOSeccion sec : dis.getSecciones() ) {
    					
          				if( sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() ) ){
          					
          					dto.setCasillasEnPantalla(sec.getCasillas());
          					// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
                	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
                	  										  );
          				}
    				}
    			}
          	}
          	
          	// Para Regiduria, cargamos las casillas a ser mostrados en pantalla
          	else if( esTipoRegiduria() && dto.getCasillasPorCapturar().getRegidurias() != null && dto.getCasillasPorCapturar().getRegidurias().size() > 0 ) {
    			
          		for ( DTORegiduria reg :  dto.getCasillasPorCapturar().getRegidurias() ) {
    			
          			for (DTOSeccion sec : reg.getSecciones()) {
         				
          				if( sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() ) ){
          					
          					dto.setCasillasEnPantalla(sec.getCasillas());
          					// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
                	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
                	  										  );
          				}
    				}
    			}
    		}      		
      	}
    	
    	else if( this.accion.equals(constantes.ACCION_CONSULTA) ||  this.accion.equals(constantes.ACCION_MODIFICA) ){
    		
        	// Para Gobernador y Ayuntamiento,cargamos las casillas a ser mostrados en pantalla
          	if(esTipoGobernadorAyuntamiento() && dto.getCasillasParaConsultar().getSecciones() != null && dto.getCasillasParaConsultar().getSecciones().size() > 0){
          		
          		
          		if( dto.isEsRecuentoTotal() && this.accion.equals(constantes.ACCION_MODIFICA)  ){
          		
          			for (DTOSeccion sec : dto.getCasillasParaConsultarEnRTotal().getSecciones()) {
                		if(sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() )){
                		
                			dto.setCasillasEnPantalla(sec.getCasillas());
                			// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
                	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
                	  										  );
                	  		
                	  		if( dto.getCasillasEnPantalla().size() > 0 ){
                      			reiniciaEstatusAsociacionesYCargaCaptura();
                      		}
                	  		
                		}
            		}
          		}
          		
          		else{
          			
          			for (DTOSeccion sec : dto.getCasillasParaConsultar().getSecciones()) {
                		if(sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() )){
                		
                			dto.setCasillasEnPantalla(sec.getCasillas());
                			// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
                	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
                	  										  );
                	  		
                	  		if( dto.getCasillasEnPantalla().size() > 0 ){
                      			reiniciaEstatusAsociacionesYCargaCaptura();
                      		}
                	  		
                		}
            		}
          		}
          	}
          	
          	// Para Diputados, cargamos las casillas a ser mostrados en pantalla
          	else if( esTipoDiputado() && dto.getCasillasParaConsultar().getDistritos() != null && dto.getCasillasParaConsultar().getDistritos().size() > 0 ){
          		
          		for ( DTODistrito dis : dto.getCasillasParaConsultar().getDistritos() ) {
          			
          			for ( DTOSeccion sec : dis.getSecciones() ) {
    					
          				if( sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() ) ){
          					
          					dto.setCasillasEnPantalla(sec.getCasillas());
          					// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
                	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
                	  										  );
                	  		
                      		if( dto.getCasillasEnPantalla().size() > 0 ){
                      			reiniciaEstatusAsociacionesYCargaCaptura();
                      		}
                	  		
          				}
    				}
    			}
          	}
          	
          	// Para Regiduria, cargamos las casillas a ser mostrados en pantalla
          	else if( esTipoRegiduria() && dto.getCasillasParaConsultar().getRegidurias() != null && dto.getCasillasParaConsultar().getRegidurias().size() > 0 ) {
    			
          		for ( DTORegiduria reg :  dto.getCasillasParaConsultar().getRegidurias() ) {
    			
          			for (DTOSeccion sec : reg.getSecciones()) {
         				
          				if( sec.getSeccion().equals( dto.getActaCasilla().getId().getSeccion() ) ){
          					
          					dto.setCasillasEnPantalla(sec.getCasillas());
          					// para que el objeto que lee los combos de selección traiga ya precargados los primeros de la lista
                	  		dto.getActaCasilla().setCveCasilla(  dto.getCasillasEnPantalla().get(0).getTipoCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getIdCasilla() +"-"+
                	  											 dto.getCasillasEnPantalla().get(0).getExtContigua()
                	  										  );
                	  		
                	  		if( dto.getCasillasEnPantalla().size() > 0 ){
                      			reiniciaEstatusAsociacionesYCargaCaptura();
                      		}
                	  		
          				}
    				}
    			}
    		} 
    	}
      	scrollTop();
    }
    
    // inicializa al estatus de "Selecciona" y limpia las asociaciones cargadas
    public void reiniciaEstatusAsociaciones(){
    	
    	// inicializamos el estatus a la opción de "Selecciona"
		dto.getActaCasilla().setIdEstatus(0);
    	
    	// incializamos las asociaciones, estas se cargaran hasta que escoja un estatus para alguna de las casillas
    	dto.setAsociaciones( new ArrayList<DTOAsociacion>() );
    	dto.setHayAsociaciones(false);
    	
    	scrollTop();
    }
    
    // inicializa al estatus de "Selecciona" y limpia las asociaciones cargadas
    public void reiniciaEstatusAsociacionesYCargaCaptura(){
    	
    	reiniciaEstatusAsociaciones();
    	
    	cargaAsociaciones();
    	
    	scrollTop();
    }
    
    // esta función agrupa todas las casillas existentes en el detalle geografico en el objeto "dto.getTotalDeCasillas"
    public void agrupaTotalCasillas( List<DTOCasillaWS> casillasService ){
    	
    		// seteamos el atributo "dto.numActasTotal"
    		dto.setNumActasTotal( new BigDecimal( casillasService.size() ) );
    	
			// Para Gob y Ayuntamiento
			// agrupamos las casillas en el objeto "dto.TotalDeCasillas.secciones"
			if(esTipoGobernadorAyuntamiento()){
				
				// se identifican y agregan las secciones al objeto "TotalDeCasillas.Secciones"
				for (DTOCasillaWS casilla : casillasService) {
					
					Integer numSec = new Integer(0);
					numSec = casilla.getSeccion();
					
					DTOSeccion seccion = new DTOSeccion();
					seccion.setSeccion(numSec);
					
					if( dto.getTotalDeCasillas().getSecciones().size() < 1 ){
						dto.getTotalDeCasillas().getSecciones().add(seccion);
					}
					else{
						boolean existe = false;
						for (DTOSeccion s : dto.getTotalDeCasillas().getSecciones()) {
							if(s.getSeccion().equals(seccion.getSeccion())){
								existe = true;
							}
						}
						if(!existe){
							dto.getTotalDeCasillas().getSecciones().add(seccion);
						}
					}
				}
				
				// se agrupan las casillas en las secciones identificadas en el objeto "TotalDeCasillas.Secciones.Casillas"
				for (DTOCasillaWS casilla : casillasService) {
					for (DTOSeccion s : dto.getTotalDeCasillas().getSecciones()) {
						if(s.getSeccion().equals(casilla.getSeccion())){
							s.getCasillas().add(casilla);
						}
					}
				}
				
			}
			
			// Para DiputadoMR y RP
			// agrupamos las casillas en el objeto "TotalDeCasillas.distritos.secciones.casillas" 
			if(esTipoDiputado()){
				
				// se identifican y agregan los distritos al objeto "TotalDeCasillas.distritos" y dentro de cada distrito se agrupan sus secciones correspondientes tambien
				for (DTOCasillaWS casilla : casillasService) {
					
					DTOSeccion seccion = new DTOSeccion();
					seccion.setSeccion(casilla.getSeccion());
					
					DTODistrito distrito = new DTODistrito();
					distrito.setDistrito(casilla.getIdDistritoLocal());
					distrito.setCabeceraDistrital( casilla.getCabeceraDistritoLocal() );
					distrito.getSecciones().add(seccion);

					
					if( dto.getTotalDeCasillas().getDistritos().size() < 1 ){
						dto.getTotalDeCasillas().getDistritos().add(distrito);
					}
					else{
						
						boolean existeDistrito = false;
						for (DTODistrito d : dto.getTotalDeCasillas().getDistritos()) {
							if( d.getDistrito().equals(distrito.getDistrito()) ){
								// agregamos las secciones aun no existentes del distrito
								boolean existeSeccion = false;
								for (DTOSeccion s : d.getSecciones()) {
									if(s.getSeccion().equals( distrito.getSecciones().get(0).getSeccion() ) ){
										existeSeccion = true;
									}
								}
								if(!existeSeccion){
									d.getSecciones().add( distrito.getSecciones().get(0) );
								}

								existeDistrito = true;
							}
						}
						if(!existeDistrito){
							dto.getTotalDeCasillas().getDistritos().add(distrito);
						}
					}
				}
				
				// se agrupan las casillas en las secciones de los distritos identificados en el objeto "TotalDeCasillas.distritos.Secciones.Casillas"
				for (DTOCasillaWS casilla : casillasService) {
					for (DTODistrito d : dto.getTotalDeCasillas().getDistritos()) {
						for (DTOSeccion s : d.getSecciones()) {
							if(  casilla.getIdDistritoLocal().equals( d.getDistrito() )  && casilla.getSeccion().equals( s.getSeccion() )  ){
								s.getCasillas().add(casilla);
							}
						}
					}
				}
				
				// ordenamos los distritos de menor a mayor
				
				int disArray[] = new int[ dto.getTotalDeCasillas().getDistritos().size() ];
				int count = 0;
				for (DTODistrito d : dto.getTotalDeCasillas().getDistritos()) {
					disArray[count] = d.getDistrito().intValue();
					count ++;
				}
				
				Arrays.sort(disArray);
				
				List<DTODistrito> temp = new ArrayList<DTODistrito>();
				for (int i : disArray) {
					for ( DTODistrito distrito : dto.getTotalDeCasillas().getDistritos() ) {
						if( distrito.getDistrito().intValue() == i ){
							temp.add(distrito);
						}
					}
				}

				dto.getTotalDeCasillas().setDistritos(temp);				
			}
			
			// Para RegiduriaMR y RP
			// agrupamos las casillas en el objeto "TotalDeCasillas.regidurias.secciones.casillas" 
			if(esTipoRegiduria()){
							
				// se identifican y agregan los distritos al objeto "TotalDeCasillas.regidurias" y dentro de cada regiduria se agrupan sus secciones correspondientes tambien
				for (DTOCasillaWS casilla : casillasService) {
					
					DTOSeccion seccion = new DTOSeccion();
					seccion.setSeccion(casilla.getSeccion());
					
					DTORegiduria regiduria = new DTORegiduria();
					regiduria.setIdRegiduria( casilla.getIdRegiduria() );
					regiduria.setNombreRegiduria( casilla.getNombreRegiduria() );
					regiduria.getSecciones().add( seccion );
					
					if( dto.getTotalDeCasillas().getRegidurias().size() < 1 ){
						dto.getTotalDeCasillas().getRegidurias().add(regiduria);
					}
					else{
						
						boolean existeRegiduria = false;
						for (DTORegiduria r : dto.getTotalDeCasillas().getRegidurias()) {
							
							if( r.getIdRegiduria().equals( regiduria.getIdRegiduria() ) ){
								// agregamos las secciones aun no existentes de la regiduria
								boolean existeSeccion = false;
								for (DTOSeccion s : r.getSecciones()) {
									if(s.getSeccion().equals( regiduria.getSecciones().get(0).getSeccion() ) ){
										existeSeccion = true;
									}
								}
								if(!existeSeccion){
									r.getSecciones().add( regiduria.getSecciones().get(0) );
								}

								existeRegiduria = true;
							}
						}
						if(!existeRegiduria){
							dto.getTotalDeCasillas().getRegidurias().add(regiduria);
						}
					}
				}
				
				// se agrupan las casillas en las secciones de las regidurias identificadas en el objeto "TotalDeCasillas.regidurias.Secciones.Casillas"
				for (DTOCasillaWS casilla : casillasService) {
					for (DTORegiduria r : dto.getTotalDeCasillas().getRegidurias()) {
						for (DTOSeccion s : r.getSecciones()) {
							if(  casilla.getIdRegiduria().equals( r.getIdRegiduria() )  && casilla.getSeccion().equals( s.getSeccion() )  ){
								s.getCasillas().add(casilla);
							}
						}
					}
				}
			}
    }
    
    
	// recuperamos las casillas capturadas en BD, pero sin información de sus votos
    // para Gob y Ayuntamiento las ordena por secciones
    // para Diputado MR y RP las ordena por Distritos, Secciones
    // para Regiduria MR y RP las ordena por Demarcación, Secciones
    public void recuperaCasillasCapturadas(){
    	
      	try {
      		List<DTOActaCasillaVotos> casillasEnBD = new ArrayList<DTOActaCasillaVotos>();
      		
      		DTOActaCasillaVotosPK idActas = new DTOActaCasillaVotosPK();
      			idActas.setIdProcesoElectoral(dto.getProcesoElectoral());
      			idActas.setIdDetalleProceso(dto.getDetalleProcesoElectoral());
      			idActas.setIdEstado(dto.getIdEdoSeleccionado());
      			idActas.setIdMunicipio(dto.getIdMunSeleccionado());
      			idActas.setIdTipoCandidatura(dto.getIdCandidatura());
      				
  			// Para Gob y Ayuntamiento
			if(esTipoGobernadorAyuntamiento()){
				casillasEnBD = bsdCapturaVotos.getActasMunicipioEnSecciones(idActas);		
			}
			
			// Para DiputadoMR y RP
			else if(esTipoDiputado()){
				casillasEnBD = bsdCapturaVotos.getActasMunicipioEnDistritosSecciones(idActas);
			}
			
			// Para RegiduriaMR y RP
			else if(esTipoRegiduria()){
				casillasEnBD = bsdCapturaVotos.getActasMunicipioEnRegiduriasSecciones(idActas);			
			}
      		
			// seteamos las casillas capturadas en "dto.CasillasCapturadas" 
      		if( casillasEnBD != null && casillasEnBD.size() > 0 ){
      			dto.setCasillasCapturadas(casillasEnBD);
      		}
      		
		} catch (Exception e) {
			LOGGER.error("Error MBCapturaVotos - recuperaCasillasCapturadas()", e);
		}
    	
    }
    
	// identifica y agrupa las casillas faltantes por capturar y las ya capturadas
    // las No Capturadas se guardan en:
    // para Gob y Ayuntamiento las ordena por secciones en 				"dto.casillasPorCapturar.secciones"
    // para Diputado MR y RP las ordena por Distritos, Secciones en 	"dto.casillasPorCapturar.distritos"
    // para Regiduria MR y RP las ordena por Demarcación, Secciones en 	"dto.casillasPorCapturar.regidurias"
    // analogamente lo mismo para las capturadas, pero en el objeto 	"dto.casillasParaConsultar"
    public void identificaAgrupaCasillaPorCapturarYConsultar(){
    	
    	int actasCap = 0;
    	
		// Para Gob y Ayuntamiento
		// agrupamos las casillas en por capturar en el objeto "casillasPorCapturar.secciones" 
		if(esTipoGobernadorAyuntamiento()){
			
			if( dto.getTotalDeCasillas().getSecciones() != null && dto.getTotalDeCasillas().getSecciones().size() > 0 ){
				
				// Verificamos para Gober y Ayuntamiento si hay alguna acta capturada, con estatus 5 y el campo de capturada 1, si si esto nos indicaria que ha habido 
				// una captura de recuento total y que ya existen actas que se han capturado en recuento total y pueden ser consultadas, si este es el caso entonces en 
				// las pantallas de Consulta y Modifica, solo habria que mostrar las actas en base de datos que fuero recapturadas en el recuento total y ya no todo el universo de actas capturadas existentes en base de datos
				
				for (DTOSeccion seccion : dto.getTotalDeCasillas().getSecciones()) {
					
					DTOSeccion addSeccionNoCap = new DTOSeccion();
					addSeccionNoCap.setSeccion(seccion.getSeccion());
					
					DTOSeccion addSeccionSiCap = new DTOSeccion();
					addSeccionSiCap.setSeccion(seccion.getSeccion());
					
					DTOSeccion addSeccionSiCapRTotal = new DTOSeccion();
					addSeccionSiCapRTotal.setSeccion(seccion.getSeccion());
					
					
					for (DTOCasillaWS casillaMun : seccion.getCasillas()) {
						
						boolean esCapturada 		= false;
						boolean esCapturadaEnRTotal = false;
						
						for (DTOActaCasillaVotos casillaCap : dto.getCasillasCapturadas()) {
							
							if ( casillaMun.getSeccion().equals( casillaCap.getId().getSeccion()) 			&& 
								 casillaMun.getIdCasilla().equals( casillaCap.getId().getIdCasilla()) 		&&
								 casillaMun.getTipoCasilla().equals( casillaCap.getId().getTipoCasilla())	&&
								 casillaMun.getExtContigua().equals( casillaCap.getId().getExtContigua())	
							   )
							{
								esCapturada = true;
								
								// si esta en la condiciones de recuento total se deja para ser capturada 
								if(  casillaCap.getIdEstatus().equals(new Integer(5))  && casillaCap.getCapturada().equals(new Integer(0))   ){
									esCapturada = false;
									dto.setEsRecuentoTotal(true);
								}
								
								// si esta capturada, verificamos si se trata de un acta ya capturada en recuento total
								else if(casillaCap.getIdEstatus().equals(new Integer(5)) && casillaCap.getCapturada().equals(new Integer(1))  ){
									esCapturadaEnRTotal = true;
									dto.setEsRecuentoTotal(true);
								}
							}
							
						}
						
						// si la casilla no esta capturada, se toma en cuenta para ser capturada
						if(!esCapturada){
							addSeccionNoCap.getCasillas().add(casillaMun);
						}	
						// si la casilla Si esta capturada se toma en cuenta para ser consultada
						else{							
							addSeccionSiCap.getCasillas().add(casillaMun);
							actasCap ++;
							
							// si hay actas capturadas en recuento total, se agregan para la consulta en recuento total
							if( esCapturadaEnRTotal ){
								addSeccionSiCapRTotal.getCasillas().add(casillaMun);
							}
						}
					}
					
					// agregamos las casillas a capturar
					if( addSeccionNoCap.getCasillas() != null && addSeccionNoCap.getCasillas().size() > 0){
						dto.getCasillasPorCapturar().getSecciones().add(addSeccionNoCap);
					}
					
					// agregamos las casillas a Consultar
					if( addSeccionSiCap.getCasillas() != null && addSeccionSiCap.getCasillas().size() > 0 ){
						dto.getCasillasParaConsultar().getSecciones().add(addSeccionSiCap);
					}
					
					// agregamos las casillas a Consultar en recuento total
					if( addSeccionSiCapRTotal.getCasillas() != null && addSeccionSiCapRTotal.getCasillas().size() > 0 ){
						dto.getCasillasParaConsultarEnRTotal().getSecciones().add( addSeccionSiCapRTotal );
					}
				}
			}
		} // fin del "IF" de agrupamos las casillas por cactiurar en el objeto "casillasPorCapturar.secciones" 
		
		
		// Para Diputado MR
		// agrupamos las casillas por capturar en el objeto "casillasPorCapturar.distritos" 
		else if( esTipoDiputado() ){
			if( dto.getTotalDeCasillas().getDistritos() != null && dto.getTotalDeCasillas().getDistritos().size() > 0 ){
				
				// Verificamos para Diputado si hay alguna acta capturada, con estatus 5 y el campo de capturada 1, si si esto nos indicaria que ha habido
				// una captura de recuento total y que ya existen actas que se han capturado en recuento total y pueden ser consultadas, si este es el caso entonces en 
				// las pantallas de Consulta y Modifica, solo habria que mostrar las actas en base de datos que fuero recapturadas en el recuento total y ya no todo el universo de actas capturadas existentes en base de datos
				
				for (DTODistrito distrito : dto.getTotalDeCasillas().getDistritos()) {
					
					DTODistrito addDistritoNoCap = new DTODistrito();
					addDistritoNoCap.setCabeceraDistrital(distrito.getCabeceraDistrital());
					addDistritoNoCap.setDistrito(distrito.getDistrito());
					
					DTODistrito addDistritoSiCap = new DTODistrito();
					addDistritoSiCap.setCabeceraDistrital(distrito.getCabeceraDistrital());
					addDistritoSiCap.setDistrito(distrito.getDistrito());
					
					DTODistrito addDistritoSiCapRTotal = new DTODistrito();
					addDistritoSiCapRTotal.setCabeceraDistrital(distrito.getCabeceraDistrital());
					addDistritoSiCapRTotal.setDistrito(distrito.getDistrito());
					
					
					for (DTOSeccion seccion : distrito.getSecciones()) {
						
						DTOSeccion secNoCap = new DTOSeccion();
						secNoCap.setSeccion(seccion.getSeccion());
						
						DTOSeccion secSiCap = new DTOSeccion();
						secSiCap.setSeccion(seccion.getSeccion());
						
						DTOSeccion secSiCapRTotal = new DTOSeccion();
						secSiCapRTotal.setSeccion(seccion.getSeccion());
						
						for (DTOCasillaWS casilla : seccion.getCasillas()) {
							
							boolean esCapturada 		= false;
							boolean esCapturadaEnRTotal = false;
							
							for (DTOActaCasillaVotos casillaCap : dto.getCasillasCapturadas()) {
								
								if ( 
									 casilla.getIdDistritoLocal().equals( casillaCap.getId().getIdDistrito() ) &&
									 casilla.getSeccion().equals( casillaCap.getId().getSeccion()) 			   && 
									 casilla.getIdCasilla().equals( casillaCap.getId().getIdCasilla()) 		   &&
									 casilla.getTipoCasilla().equals( casillaCap.getId().getTipoCasilla())	   &&
									 casilla.getExtContigua().equals( casillaCap.getId().getExtContigua())	
								   )
								{
									esCapturada = true;
									
									// si esta en la condiciones de recuento total se deja para ser capturada 
									if(  casillaCap.getIdEstatus().equals(new Integer(5))  && casillaCap.getCapturada().equals(new Integer(0))   ){
										esCapturada = false;
										//dto.setEsRecuentoTotal(true);
										addDistritoNoCap.setEsRecuentoTotal(true);
									}
									
									// si esta capturada, verificamos si se trata de un acta ya capturada en recuento total
									else if(casillaCap.getIdEstatus().equals(new Integer(5)) && casillaCap.getCapturada().equals(new Integer(1))  ){
										esCapturadaEnRTotal = true;
									}
								}								
							}
							
							// si la casilla no esta capturada, se toma en cuanta para ser capturada
							if(!esCapturada){								
								secNoCap.getCasillas().add(casilla);
							}		
							// si la casilla Si esta capturada se toma en cuenta para ser consultada
							else{
								secSiCap.getCasillas().add(casilla);
								actasCap ++;
								
								// si hay actas capturadas en recuento total, se agregan para la consulta en recuento total
								if( esCapturadaEnRTotal ){
									secSiCapRTotal.getCasillas().add(casilla);
								}
							}							
						}
						
						// agregamos las secciones a capturar
						if(secNoCap.getCasillas() != null && secNoCap.getCasillas().size() > 0){
							addDistritoNoCap.getSecciones().add(secNoCap);
						}
						
						// agregamos las secciones a consultar
						if(secSiCap.getCasillas() != null && secSiCap.getCasillas().size() > 0){
							addDistritoSiCap.getSecciones().add(secSiCap);
						}
						
						// agregamos las secciones a consultar en recuento total
						if(secSiCapRTotal.getCasillas() != null && secSiCapRTotal.getCasillas().size() > 0){
							addDistritoSiCapRTotal.getSecciones().add(secSiCapRTotal);
						}
					}
					
					// agregamos las casillas a capturar
					if( addDistritoNoCap.getSecciones() != null && addDistritoNoCap.getSecciones().size() > 0 ){
						dto.getCasillasPorCapturar().getDistritos().add(addDistritoNoCap);
					}
					
					// agregamos las casillas a consultar
					if( addDistritoSiCap.getSecciones() != null && addDistritoSiCap.getSecciones().size() > 0 ){
						dto.getCasillasParaConsultar().getDistritos().add(addDistritoSiCap);
					}
					
					// agregamos las casillas a consultar en recuento total
					if( addDistritoSiCapRTotal.getSecciones() != null && addDistritoSiCapRTotal.getSecciones().size() > 0 ){
						dto.getCasillasParaConsultarEnRTotal().getDistritos().add(addDistritoSiCapRTotal);
					}
				}
				
			}
		} // fin del "IF" de agrupamos las casillas por cactiurar en el objeto "casillasPorCapturar.distritos"
		
		// Para Regiduria MR
		// agrupamos las casillas por capturar en el objeto "casillasPorCapturar.regidurias" 
		else if( esTipoRegiduria() ){
			
			if( dto.getTotalDeCasillas().getRegidurias() != null && dto.getTotalDeCasillas().getRegidurias().size() > 0 ){
				
				// Verificamos para Regiduria si hay alguna acta capturada, con estatus 5 y el campo de capturada 1, si si esto nos indicaria que ha habido
				// una captura de recuento total y que ya existen actas que se han capturado en recuento total y pueden ser consultadas, si este es el caso entonces en 
				// las pantallas de Consulta y Modifica, solo habria que mostrar las actas en base de datos que fuero recapturadas en el recuento total y ya no todo el universo de actas capturadas existentes en base de datos
				
				for (DTORegiduria regiduria : dto.getTotalDeCasillas().getRegidurias()) {
					
					DTORegiduria addRegiduriaNoCap = new DTORegiduria();
					addRegiduriaNoCap.setNombreRegiduria(regiduria.getNombreRegiduria());
					addRegiduriaNoCap.setIdRegiduria(regiduria.getIdRegiduria());
					
					DTORegiduria addRegiduriaSiCap = new DTORegiduria();
					addRegiduriaSiCap.setNombreRegiduria(regiduria.getNombreRegiduria());
					addRegiduriaSiCap.setIdRegiduria(regiduria.getIdRegiduria());
					
					DTORegiduria addRegiduriaSiCapRTotal = new DTORegiduria();
					addRegiduriaSiCapRTotal.setNombreRegiduria(regiduria.getNombreRegiduria());
					addRegiduriaSiCapRTotal.setIdRegiduria(regiduria.getIdRegiduria());
					
					
					for (DTOSeccion seccion : regiduria.getSecciones()) {
						
						DTOSeccion secNoCap = new DTOSeccion();
						secNoCap.setSeccion(seccion.getSeccion());
						
						DTOSeccion secSiCap = new DTOSeccion();
						secSiCap.setSeccion(seccion.getSeccion());
						
						DTOSeccion secSiCapRTotal = new DTOSeccion();
						secSiCapRTotal.setSeccion(seccion.getSeccion());
						
						for (DTOCasillaWS casilla : seccion.getCasillas()) {
							
							boolean esCapturada 		= false;
							boolean esCapturadaEnRTotal = false;
							
							for (DTOActaCasillaVotos casillaCap : dto.getCasillasCapturadas()) {
								
								if ( 
									 casilla.getIdRegiduria().equals( casillaCap.getId().getIdRegiduria() ) &&
									 casilla.getSeccion().equals( casillaCap.getId().getSeccion()) 			   && 
									 casilla.getIdCasilla().equals( casillaCap.getId().getIdCasilla()) 		   &&
									 casilla.getTipoCasilla().equals( casillaCap.getId().getTipoCasilla())	   &&
									 casilla.getExtContigua().equals( casillaCap.getId().getExtContigua())	
								   )
								{
									esCapturada = true;
									
									// si esta en la condiciones de recuento total se deja para ser capturada 
									if(  casillaCap.getIdEstatus().equals(new Integer(5))  && casillaCap.getCapturada().equals(new Integer(0))   ){
										esCapturada = false;
										//dto.setEsRecuentoTotal(true);
										addRegiduriaNoCap.setEsRecuentoTotal(true);
									}
									
									// si esta capturada, verificamos si se trata de un acta ya capturada en recuento total
									else if(casillaCap.getIdEstatus().equals(new Integer(5)) && casillaCap.getCapturada().equals(new Integer(1))  ){
										esCapturadaEnRTotal = true;
									}
								}								
							}
							
							// si la casilla no esta capturada, se toma en cuanta para ser capturada
							if(!esCapturada){								
								secNoCap.getCasillas().add(casilla);
							}		
							// si la casilla Si esta capturada se toma en cuenta para ser consultada
							else{
								secSiCap.getCasillas().add(casilla);
								actasCap ++;
								
								// si hay actas capturadas en recuento total, se agregan para la consulta en recuento total
								if( esCapturadaEnRTotal ){
									secSiCapRTotal.getCasillas().add(casilla);
								}
							}							
						}
						
						// agregamos las secciones a capturar
						if(secNoCap.getCasillas() != null && secNoCap.getCasillas().size() > 0){
							addRegiduriaNoCap.getSecciones().add(secNoCap);
						}
						
						// agregamos las secciones a consultar
						if(secSiCap.getCasillas() != null && secSiCap.getCasillas().size() > 0){
							addRegiduriaSiCap.getSecciones().add(secSiCap);
						}
						
						// agregamos las secciones a consultar en recuento total
						if(secSiCapRTotal.getCasillas() != null && secSiCapRTotal.getCasillas().size() > 0){
							addRegiduriaSiCapRTotal.getSecciones().add(secSiCapRTotal);
						}
					}
					
					// agregamos las casillas a capturar
					if( addRegiduriaNoCap.getSecciones() != null && addRegiduriaNoCap.getSecciones().size() > 0 ){
						dto.getCasillasPorCapturar().getRegidurias().add(addRegiduriaNoCap);
					}
					
					// agregamos las casillas a consultar
					if( addRegiduriaSiCap.getSecciones() != null && addRegiduriaSiCap.getSecciones().size() > 0 ){
						dto.getCasillasParaConsultar().getRegidurias().add(addRegiduriaSiCap);
					}
					
					// agregamos las casillas a consultar en recuento total
					if( addRegiduriaSiCapRTotal.getSecciones() != null && addRegiduriaSiCapRTotal.getSecciones().size() > 0 ){
						dto.getCasillasParaConsultarEnRTotal().getRegidurias().add(addRegiduriaSiCapRTotal);
					}
				}
				
			}
		} // fin del "IF" de agrupamos las casillas por cactiurar en el objeto "casillasPorCapturar.regidurias"
		
		
		// seteamos el atributo "dto.numActasCapturadas"
		dto.setNumActasCapturadas( new BigDecimal( actasCap ) );
    	
    }
    
    
    // metodo que calcula el avance de la captura de las casillas
    // almacena la información en:
	//	"dto.avanceCaptura"
    public void calculaAvanceCapturaRegDistrito(){
    
    	// calculamos el avence de actas segun el distrito seleccionado
    	if( esTipoDiputado() ){
    		
    		Integer dSeleccionado = new Integer(0);
    		dSeleccionado = dto.getActaCasilla().getId().getIdDistrito();
    		
    		if( dSeleccionado != null && dSeleccionado > 0 ){
	    	
    			// rastreamos la carga total del distrito
    			DTODistrito dTotal = new DTODistrito();
    			for ( DTODistrito  d : dto.getTotalDeCasillas().getDistritos() ) {
	    			if( d.getDistrito().equals( dSeleccionado ) ){
	    				dTotal = d;
	    			}
				}
    			
    			// rastreamos la carga capturada del distrito
    			DTODistrito dCapturado = new DTODistrito();
    			
    			if( this.accion.equals(constantes.ACCION_MODIFICA) && casillasParaConsultarRespaldtoTotal!=null ){
    				for ( DTODistrito  d : casillasParaConsultarRespaldtoTotal.getDistritos() ) {
    	    			if( d.getDistrito().equals( dSeleccionado ) ){
    	    				dCapturado = d;
    	    			}
    				}
    			}
    			else{
    				for ( DTODistrito  d : dto.getCasillasParaConsultar().getDistritos() ) {
    	    			if( d.getDistrito().equals( dSeleccionado ) ){
    	    				dCapturado = d;
    	    			}
    				}
    			}
    			
    			// seteamos las variables que calculan el avance en la captura de actas
    			int capturadas = 0;
    			for (DTOSeccion sec : dCapturado.getSecciones()) {
    				capturadas += sec.getCasillas().size();
				}
    			
    			int totales = 0;
    			for (DTOSeccion sec : dTotal.getSecciones()) {
    				totales += sec.getCasillas().size();
				}
    			
    			dto.setNumActasCapturadas( new BigDecimal( new Integer( capturadas ) )   );
    			dto.setNumActasTotal( new BigDecimal( new Integer( totales ) ) );
    		}
    	}
    
    	
    	// calculamos el avence de actas segun la regiduria seleccionada
    	if( esTipoRegiduria() ){
    	
    		
    		Integer rSeleccionada = new Integer(0);
    		rSeleccionada = dto.getActaCasilla().getId().getIdRegiduria();
    		
    		if( rSeleccionada != null && rSeleccionada > 0 ){
    	    	
    			// rastreamos la carga total de la regiduria
    			DTORegiduria rTotal = new DTORegiduria();
    			for ( DTORegiduria  r : dto.getTotalDeCasillas().getRegidurias() ) {
	    			if( r.getIdRegiduria().equals( rSeleccionada ) ){
	    				rTotal = r;
	    			}
				}
    			
    			// rastreamos la carga capturada del distrito
    			DTORegiduria rCapturada = new DTORegiduria();
    			
    			if( this.accion.equals(constantes.ACCION_MODIFICA) && casillasParaConsultarRespaldtoTotal!=null){
    				for ( DTORegiduria  r : casillasParaConsultarRespaldtoTotal.getRegidurias() ) {
    	    			if( r.getIdRegiduria().equals( rSeleccionada ) ){
    	    				rCapturada = r;
    	    			}
    				}
    			}
    			else{
    				for ( DTORegiduria  r : dto.getCasillasParaConsultar().getRegidurias() ) {
    	    			if( r.getIdRegiduria().equals( rSeleccionada ) ){
    	    				rCapturada = r;
    	    			}
    				}
    			}
    			
    			// seteamos las variables que calculan el avance en la captura de actas
    			int capturadas = 0;
    			for (DTOSeccion sec : rCapturada.getSecciones()) {
    				capturadas += sec.getCasillas().size();
				}
    			
    			int totales = 0;
    			for (DTOSeccion sec : rTotal.getSecciones()) {
    				totales += sec.getCasillas().size();
				}
    			
    			dto.setNumActasCapturadas( new BigDecimal( new Integer( capturadas ) )   );
    			dto.setNumActasTotal( new BigDecimal( new Integer( totales ) ) );
    		}
    		
    	}
    	
    	
    	double porcentaje = 0;
    	porcentaje = ( dto.getNumActasCapturadas().doubleValue() / dto.getNumActasTotal().doubleValue() ) * 100;
    	
    	dto.setAvanceCaptura(
								new BigDecimal(String.valueOf(porcentaje)).setScale(2, RoundingMode.UP)
    			            );
    	
    	// si el porcentaje supera el 100% truncamos a 100% solamente
    	BigDecimal cien = new BigDecimal(100);
    	if( dto.getAvanceCaptura().doubleValue() > cien.doubleValue() ){
    		dto.setAvanceCaptura( new BigDecimal(100) );
    	}
    	
    }
    
    // metodo que calcula el avance de la captura de las casillas
    // almacena la información en:
	//	"dto.avanceCaptura"
    public void calculaAvanceCapturaPorMunicipio(){
    	
    	double porcentaje = 0;
    	porcentaje = ( dto.getNumActasCapturadas().doubleValue() / dto.getNumActasTotal().doubleValue() ) * 100;
    	
    	dto.setAvanceCaptura(
								new BigDecimal(String.valueOf(porcentaje)).setScale(2, RoundingMode.UP)
    			            );
    	
    	// si el porcentaje supera el 100% truncamos a 100% solamente
    	BigDecimal cien = new BigDecimal(100);
    	if( dto.getAvanceCaptura().doubleValue() > cien.doubleValue() ){
    		dto.setAvanceCaptura( new BigDecimal(100) );
    	}
    	
    }
    
    // Este metodo evalua en las candidaturas de REGIDURIAS MR y DIPUTADOS MR, si la regiduria o distrito parcial seleccionado esta en recuento total o no
    public void evaluaRecuentoTotalPorDisReg(){
    	
    	dto.setEsRecuentoTotal(false);
    	
    	if( esTipoDiputado() ){
    		
    		Integer dSeleccionado = new Integer(0);
    		dSeleccionado = dto.getActaCasilla().getId().getIdDistrito();
    		
    		if( dSeleccionado != null && dSeleccionado > 0 ){
	    	
    			// rastreamos la carga capturada del distrito
    			DTODistrito dCapturado = new DTODistrito();
    			if( this.accion.equals(constantes.ACCION_CAPTURA) ){
    				for ( DTODistrito  d : dto.getCasillasPorCapturar().getDistritos() ) {
    	    			if( d.getDistrito().equals( dSeleccionado ) ){
    	    				dCapturado = d;
    	    				break;
    	    			}
    				}
    			}
    			else{
    				for ( DTODistrito  d : dto.getCasillasParaConsultar().getDistritos() ) {
    	    			if( d.getDistrito().equals( dSeleccionado ) ){
    	    				dCapturado = d;
    	    				break;
    	    			}
    				}
    			}
    			

    			// evaluamos si para el distrito seleccionado hay recuento total
    			if( dCapturado.isEsRecuentoTotal() )
    				dto.setEsRecuentoTotal( dCapturado.isEsRecuentoTotal() );
    		}
    	}
    
    	
    	// calculamos el avence de actas segun la regiduria seleccionada
    	else if( esTipoRegiduria() ){
    	
    		Integer rSeleccionada = new Integer(0);
    		rSeleccionada = dto.getActaCasilla().getId().getIdRegiduria();
    		
    		if( rSeleccionada != null && rSeleccionada > 0 ){
    	    	
    			// rastreamos la carga capturada de la regiduria
    			DTORegiduria rCapturada = new DTORegiduria();
    			if( this.accion.equals(constantes.ACCION_CAPTURA) ){
    				for ( DTORegiduria  r : dto.getCasillasPorCapturar().getRegidurias() ) {
    	    			if( r.getIdRegiduria().equals( rSeleccionada ) ){
    	    				rCapturada = r;
    	    				break;
    	    			}
    				}
    			}
    			else{
    				for ( DTORegiduria  r : dto.getCasillasParaConsultar().getRegidurias() ) {
    	    			if( r.getIdRegiduria().equals( rSeleccionada ) ){
    	    				rCapturada = r;
    	    				break;
    	    			}
    				}
    			}
    			
    			
    			// evaluamos si para el distrito seleccionado hay recuento total
    			if( rCapturada.isEsRecuentoTotal() )
    				dto.setEsRecuentoTotal( rCapturada.isEsRecuentoTotal() );
    		}
    	}
    }
    
    // metodo que verifica si es apropiado cargar las asociaciones de un detalle geografico y si si, cargarlas
    public void cargaAsociaciones(){
    	
    	System.out.println("entro a cargaAsociaciones !!");
    	
    	// reiniciamos las asociaciones en pantalla
    	dto.setAsociaciones( new ArrayList<DTOAsociacion>() );
    	dto.setHayAsociaciones(false);


    	// si ya hay una sucesion de filtros de casilla seleccionados consultamos las asociaciones o si esta en recuento total tambien
    	if(	
    		// para captura
    		(	
				this.accion.equals(constantes.ACCION_CAPTURA) &&	
	    		!dto.getActaCasilla().getIdEstatus().equals(new Integer(0)) && 
	    		dto.getActaCasilla().getId().getSeccion() != 0 && 
	    		!dto.getActaCasilla().getCveCasilla().equals("")
    		)
    		
    		||
    		
    		// para consulta
    		(
				this.accion.equals(constantes.ACCION_CONSULTA) &&
				dto.getActaCasilla().getId().getSeccion() != 0 && 
	    		!dto.getActaCasilla().getCveCasilla().equals("")
    		)
    		
    		// para modificar
    		||
    		(
    				this.accion.equals(constantes.ACCION_MODIFICA) &&
    				dto.getActaCasilla().getId().getSeccion() != 0 && 
    	    		!dto.getActaCasilla().getCveCasilla().equals("")
        	)
    		
    	  )
    	{
    		
        	// seteamos algunos datos de la casilla seleccionada rastreandola por su cve
        	String cve = dto.getActaCasilla().getCveCasilla(); // se compone de tipoCasilla-idCasilla-extContigua   {casilla.tipoCasilla}-#{casilla.idCasilla}-#{casilla.extContigua}
        	String[] elementosClave = cve.split("-");
        	
        	for (DTOCasillaWS casillaEnPantalla : dto.getCasillasEnPantalla()) {
        		
        		if( casillaEnPantalla.getTipoCasilla().equals(elementosClave[0]) && 
        			casillaEnPantalla.getIdCasilla().equals( new Integer(elementosClave[1] ) ) && 
        		    casillaEnPantalla.getExtContigua().equals( new Integer(elementosClave[2]) )
        		  ){
                	dto.getActaCasilla().getId().setTipoCasilla( casillaEnPantalla.getTipoCasilla() );
                	dto.getActaCasilla().getId().setIdCasilla(  casillaEnPantalla.getIdCasilla()  );
                	dto.getActaCasilla().getId().setExtContigua( casillaEnPantalla.getExtContigua() );
                	dto.getActaCasilla().setListaNominal( casillaEnPantalla.getListaNominalCasilla() );
        			dto.getActaCasilla().setCabeceraDistrital( casillaEnPantalla.getCabeceraDistritoLocal() );	
        			dto.getActaCasilla().setNombreCasilla( casillaEnPantalla.getNombreCasilla() );
        		}
        		
        		
			}

        	
    		// inicializamos las asocioaciones
    		dto.getAsociaciones().clear();
    		
          	// recuperamos las asociaciones politicas del escenario del detalle geografico dado
          	try {
          		
          		List<DTOCandidatoWS> asociacionesService = new ArrayList<DTOCandidatoWS>();
          		
          		if(esTipoGobernadorAyuntamiento()){
          			asociacionesService = bsdCargaInformacion.consumeAsociacionesCoaliciones(	dto.getDetalleProcesoElectoral(), 
																								dto.getIdEdoSeleccionado(), 
																								null, 
																								dto.getIdCandidatura(), 
																								dto.getIdMunSeleccionado(), 
																								null);
          		}
          		
          		else if(esTipoDiputado()){
          			asociacionesService = bsdCargaInformacion.consumeAsociacionesCoaliciones(	dto.getDetalleProcesoElectoral(), 
																								dto.getIdEdoSeleccionado(), 
																								dto.getActaCasilla().getId().getIdDistrito(), 
																								dto.getIdCandidatura(), 
																								dto.getIdMunSeleccionado(), 
																								null);
          		}
          		
          		else if(esTipoRegiduria()){
          			asociacionesService = bsdCargaInformacion.consumeAsociacionesCoaliciones(	dto.getDetalleProcesoElectoral(), 
																								dto.getIdEdoSeleccionado(), 
																								null, 
																								dto.getIdCandidatura(), 
																								dto.getIdMunSeleccionado(), 
																								dto.getActaCasilla().getId().getIdRegiduria());
          		}
          		
          		
    			if(asociacionesService != null && asociacionesService.size() > 0){
    				
    				List<DTOAsociacion> partidos 		= new ArrayList<DTOAsociacion>();
    				List<DTOAsociacion> coaliciones 	= new ArrayList<DTOAsociacion>();
    				List<DTOAsociacion> independientes 	= new ArrayList<DTOAsociacion>();
    				
    				for (DTOCandidatoWS a : asociacionesService) {
    					DTOAsociacion asociacion = new DTOAsociacion(a);
    					
    					// Partido Politico (1)
    					if( asociacion.getTipoAsociacion().equals(new Integer(1)) ){
    						asociacion.setIdCoalicion( new Integer(-10) );
    						partidos.add(asociacion);
    					}
    					//Candidato Independiente (4)
    					else if(  asociacion.getTipoAsociacion().equals(new Integer(4)) ){
    						asociacion.setIdCoalicion( new Integer(-10) );
    						independientes.add(asociacion);
    					}
    						
    					// Coalicion (3)
    					else if(asociacion.getTipoAsociacion().equals(new Integer(3))){
    						asociacion.setIdCoalicion( new Integer(-10) );
    						coaliciones.add(asociacion);
    					}
    					    					
    				}
    				
    				// si hay coalciones, traemos sus subcoaliciones
    				if( coaliciones != null && coaliciones.size()>0 ){
    					
    					List<DTOSubcoalicion> subcoaliciones = new ArrayList<DTOSubcoalicion>();
    					subcoaliciones = bsdCapturaVotos.recuperaSubcoaliciones(coaliciones);
    					
    					for (DTOSubcoalicion sub : subcoaliciones) {
    						for (DTOAsociacion coal : coaliciones) {
    							if(sub.getIdCoalicion().equals(coal.getIdAsociacion())){
    								coal.getSubcoaliciones().add(sub);
    							}
    						}
    					}
    				}
    				
    				// creamos candidatos no registrados y votos nulos
    				DTOAsociacion noReg = new DTOAsociacion();
    					noReg.setCveAsociacion( -1 + "_" + -1 );
    					noReg.setIdEstado(dto.getIdEdoSeleccionado());
	    				noReg.setIdAsociacion(new Integer(-1));
	    				noReg.setTipoAsociacion(new Integer(-1));
	    				noReg.setNombreCandidato(constantes.TITULO_CAND_NO_REGISTRADO);
	    				noReg.setNombreAsociacion(constantes.TITULO_CAND_NO_REGISTRADO);
	    				noReg.setIdTipoCandidatura(dto.getIdCandidatura());
	    				noReg.setOrden( new Integer(1) );
	    				noReg.setIdCoalicion( new Integer(-10) );
	    				
    				DTOAsociacion vNullos = new DTOAsociacion();
    					vNullos.setCveAsociacion( -2 + "_" + -2 );
    					vNullos.setIdEstado(dto.getIdEdoSeleccionado());
    					vNullos.setIdAsociacion(new Integer(-2));
    					vNullos.setTipoAsociacion(new Integer(-2));
    					vNullos.setNombreCandidato(constantes.TITULO_VOTOS_NULOS);
    					vNullos.setNombreAsociacion(constantes.TITULO_VOTOS_NULOS);
    					vNullos.setIdTipoCandidatura(dto.getIdCandidatura());
    					vNullos.setOrden( new Integer(2) );
    					vNullos.setIdCoalicion( new Integer(-10) );
    					
					
					// agregamos los votos acumulados de las asociaciones en todo el municipio seleccionado para un tipo de candidatura

					List<DTOActaCasillaVotos> votosAcumulados = new ArrayList<DTOActaCasillaVotos>();	
					DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
					
					if(esTipoGobernadorAyuntamiento()){

						key.setIdProcesoElectoral(dto.getProcesoElectoral());
						key.setIdDetalleProceso(dto.getDetalleProcesoElectoral());
						key.setIdEstado(dto.getIdEdoSeleccionado());
						key.setIdDistrito(new Integer(-5)); 						// no lleva Distrito
						key.setIdMunicipio(dto.getIdMunSeleccionado()); 			// municipio
						key.setIdRegiduria(new Integer(-7));						// no lleva Regiduria
						key.setIdComunidad(new Integer(-8)); 						// no lleva Comunidad
						key.setIdTipoCandidatura(dto.getIdCandidatura());			// id Candidatura	
						
						votosAcumulados = bsdDistribucionVotos.consultarAsociacionesParticipantes(key);
					}
					
					else if( esTipoDiputado() || esTipoRegiduria() ){
						
						key.setIdProcesoElectoral(dto.getProcesoElectoral());
						key.setIdDetalleProceso(dto.getDetalleProcesoElectoral());
						key.setIdEstado(dto.getIdEdoSeleccionado());				
						
						if(esTipoDiputado())												// distrito
							key.setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
						else
							key.setIdDistrito(new Integer(-5));
						
						key.setIdMunicipio(dto.getIdMunSeleccionado()); 					// municipio
		
						if( esTipoRegiduria() )												// regidria
							key.setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
						else
							key.setIdRegiduria(new Integer(-7));
						
						key.setIdComunidad(new Integer(-8)); 								// no lleva Comunidad
						key.setIdTipoCandidatura(dto.getIdCandidatura());					// id Candidatura
						
						votosAcumulados = bsdDistribucionVotos.consultarAsociacionesParticipantes(key);
						//votosAcumulados = bsdDistribucionVotos.consultarVotosAcumuladosAsociacionesDipAyun(key);
					}
					
					
					if( votosAcumulados!=null && votosAcumulados.size()>0 ){
						
						for (DTOActaCasillaVotos votoAcumulado : votosAcumulados) {
							
							// es Partido
							if( votoAcumulado.getId().getTipoAsociacion().equals(new Integer(1)) && votoAcumulado.getId().getIdCoalicion().equals(new Integer(-10)) ){
								for (DTOAsociacion partido : partidos) {
									if( votoAcumulado.getId().getIdAsociacion().equals( partido.getIdAsociacion() ) ){
										partido.setVotosAcumulados( new BigDecimal(votoAcumulado.getVotos()) );
									}
								}
							}
							
							// es Independiente
							else if( votoAcumulado.getId().getTipoAsociacion().equals(new Integer(4)) && votoAcumulado.getId().getIdCoalicion().equals(new Integer(-10)) ){
								for (DTOAsociacion independiente : independientes) {
									if( votoAcumulado.getId().getIdAsociacion().equals( independiente.getIdAsociacion() ) ){
										independiente.setVotosAcumulados( new BigDecimal(votoAcumulado.getVotos()) );
									}
								}
							}
							
							// es coalicion o subcoalicion
							else if( votoAcumulado.getId().getTipoAsociacion().equals(new Integer(3)) ){
								boolean esCoalicion = false;
								
								for (DTOAsociacion coalicion : coaliciones) {
									if( votoAcumulado.getId().getIdAsociacion().equals( coalicion.getIdAsociacion() ) ){
										coalicion.setVotosAcumulados( new BigDecimal(votoAcumulado.getVotos()) );
										esCoalicion = true;
									}
								}
								// es una subcoalicion
								if(!esCoalicion){
									for (DTOAsociacion coalicion : coaliciones) {
										for (DTOSubcoalicion subcoalicion : coalicion.getSubcoaliciones()) {
											if( votoAcumulado.getId().getIdAsociacion().equals( subcoalicion.getIdSubcoalicion() ) && 
												votoAcumulado.getId().getIdCoalicion().equals( coalicion.getIdAsociacion() )          
											   )
											{
												subcoalicion.setVotosAcumulados( new BigDecimal(votoAcumulado.getVotos()) );
											}
										}
									}
								}
							}
							
							// es No Reg
							else if( votoAcumulado.getId().getTipoAsociacion().equals(new Integer(-1)) && votoAcumulado.getId().getIdCoalicion().equals(new Integer(-10)) ){
								noReg.setVotosAcumulados( new BigDecimal(votoAcumulado.getVotos()) );	
							}
							
							// es Nullos
							else if( votoAcumulado.getId().getTipoAsociacion().equals(new Integer(-2)) && votoAcumulado.getId().getIdCoalicion().equals(new Integer(-10)) ){
								vNullos.setVotosAcumulados( new BigDecimal(votoAcumulado.getVotos()) );	
							}	
						}
					}

															
					// seteamos las asociaciones a la pantalla
					dto.getAsociaciones().addAll(partidos);
    				dto.getAsociaciones().addAll(coaliciones);
    				dto.getAsociaciones().addAll(independientes);
    				dto.getAsociaciones().add(noReg);
					dto.getAsociaciones().add(vNullos);

		        	// si es recuento total o es una conulta del acta o modificación del acta, recuperamos los votos que se habian capturado previamente a el acta de la casilla seleccionada
		        	if(  dto.isEsRecuentoTotal() || this.accion.equals(constantes.ACCION_CONSULTA) || this.accion.equals(constantes.ACCION_MODIFICA)  ){
		        		
		        		List<DTOActaCasillaVotos> votos = new ArrayList<DTOActaCasillaVotos>();
						DTOActaCasillaVotosPK key2 = new DTOActaCasillaVotosPK();
						
						if(esTipoGobernadorAyuntamiento()){
					
							key2.setIdProcesoElectoral(dto.getProcesoElectoral());
							key2.setIdDetalleProceso(dto.getDetalleProcesoElectoral());
							key2.setIdEstado(dto.getIdEdoSeleccionado());
							key2.setIdDistrito(new Integer(-5)); 									// no lleva Distrito
							key2.setIdMunicipio(dto.getIdMunSeleccionado()); 						// municipio
							key2.setIdRegiduria(new Integer(-7));									// no lleva Regiduria
							key2.setIdComunidad(new Integer(-8)); 									// no lleva Comunidad
							key2.setIdTipoCandidatura(dto.getIdCandidatura());						// id Candidatura	
							key2.setSeccion( dto.getActaCasilla().getId().getSeccion()); 			// seccion del acta
							key2.setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );		// id de la casilla
							key2.setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla());	// tipo de la casilla
							key2.setExtContigua( dto.getActaCasilla().getId().getExtContigua() );   // extcontigüa
						}
						
						else if( esTipoDiputado() ){
							
							key2.setIdProcesoElectoral(dto.getProcesoElectoral());
							key2.setIdDetalleProceso(dto.getDetalleProcesoElectoral());
							key2.setIdEstado(dto.getIdEdoSeleccionado());
							key2.setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() ); 	// Distrito
							key2.setIdMunicipio(dto.getIdMunSeleccionado()); 						// municipio
							key2.setIdRegiduria(new Integer(-7));									// no lleva Regiduria
							key2.setIdComunidad(new Integer(-8)); 									// no lleva Comunidad
							key2.setIdTipoCandidatura(dto.getIdCandidatura());						// id Candidatura	
							key2.setSeccion( dto.getActaCasilla().getId().getSeccion()); 			// seccion del acta
							key2.setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );		// id de la casilla
							key2.setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla());	// tipo de la casilla
							key2.setExtContigua( dto.getActaCasilla().getId().getExtContigua() );   // extcontigüa
						}
						
						else if( esTipoRegiduria() ){
							
							key2.setIdProcesoElectoral(dto.getProcesoElectoral());
							key2.setIdDetalleProceso(dto.getDetalleProcesoElectoral());
							key2.setIdEstado(dto.getIdEdoSeleccionado());
							key2.setIdDistrito( new Integer(-5) ); 									// no lleva Distrito
							key2.setIdMunicipio(dto.getIdMunSeleccionado()); 						// municipio
							key2.setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );	// Regiduria
							key2.setIdComunidad(new Integer(-8)); 									// no lleva Comunidad
							key2.setIdTipoCandidatura(dto.getIdCandidatura());						// id Candidatura	
							key2.setSeccion( dto.getActaCasilla().getId().getSeccion()); 			// seccion del acta
							key2.setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );		// id de la casilla
							key2.setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla());	// tipo de la casilla
							key2.setExtContigua( dto.getActaCasilla().getId().getExtContigua() );   // extcontigüa
						}
						
		        		votos = bsdCapturaVotos.getvotosAsociacionesActa(key2);	

		        		// seteamos el estatus si se trata de una consulta o modificación
						if( this.accion.equals(constantes.ACCION_CONSULTA) || this.accion.equals(constantes.ACCION_MODIFICA) ){
							if( votos != null && votos.size() > 0 ){
								dto.getActaCasilla().setIdEstatus(  votos.get(0).getIdEstatus()  );
							} 
						}
						
	
		        		// identificamos las asociaciones por estos campos   id_asociacion, tipo_asociacion, id_coalicion
		        		for (DTOActaCasillaVotos voto : votos) {
		        			for (DTOAsociacion asociacion : dto.getAsociaciones()) {
		        				
		        				// se agregan votos de Partidos politicos, independientes y coaliciones padre
		        				if(asociacion.getIdAsociacion().equals(voto.getId().getIdAsociacion()) && 
		        				   asociacion.getTipoAsociacion().equals(voto.getId().getTipoAsociacion()) && 
		        				   asociacion.getIdCoalicion().equals(voto.getId().getIdCoalicion()) ){
		        					
		        					// mostramos los votos vacios en pantalla para los estatus de casilla no instalada y paquete no entregado para captura o modifica
		        					if( 
	        							( this.accion.equals(constantes.ACCION_CONSULTA) || this.accion.equals(constantes.ACCION_MODIFICA) ) &&
		        						( 
		        						dto.getActaCasilla().getIdEstatus().equals( new Integer(3) )  || 
		        						dto.getActaCasilla().getIdEstatus().equals( new Integer(4) )  
		        						)
		        					 )
		        					{
		        						asociacion.setVotos(  null );
		        					}
		        					else{
		        						asociacion.setVotos(  new BigDecimal(voto.getVotos()) );
		        					}
		        					
		        				}
		        				
		        				// se agregan votos de subcoaliciones si la asociacion es una coalicion
		        				else{
		        					if(asociacion.getTipoAsociacion().equals( new Integer(3)) && asociacion.getIdCoalicion().equals( new Integer(-10) ) ){
			        					for (DTOSubcoalicion subcoalicion : asociacion.getSubcoaliciones()) {
											if(subcoalicion.getIdSubcoalicion().equals(voto.getId().getIdAsociacion()) &&
										       asociacion.getTipoAsociacion().equals(voto.getId().getTipoAsociacion()) &&
										       subcoalicion.getIdCoalicion().equals(voto.getId().getIdCoalicion()) ){
												
												// mostramos los votos vacios en pantalla para los estatus de casilla no instalada y paquete no entregado para captura o modifica
					        					if( 
				        							( this.accion.equals(constantes.ACCION_CONSULTA) || this.accion.equals(constantes.ACCION_MODIFICA) ) &&
					        						( 
					        						dto.getActaCasilla().getIdEstatus().equals( new Integer(3) )  || 
					        						dto.getActaCasilla().getIdEstatus().equals( new Integer(4) )  
					        						)
					        					 )
					        					{
					        						subcoalicion.setVotos( null );
					        					}
					        					else{
					        						subcoalicion.setVotos( new BigDecimal(new Integer(voto.getVotos()) ) );
					        					}
												
											}
										}
		        					}
		        				}
							}
						}
		        	}
					
					
					// seteamos la variable de que si hay asociaciones cargadas ya 
    				dto.setHayAsociaciones(true);
    			}
    			
    		} catch (Exception e) {
    			LOGGER.error("Error MBCapturaVotos - cargaAsociaciones()", e);
    		}
    		
    	}
    	scrollTop();
    }
    
    
    // metodo usado en la modificación de las actas, donse al asignar un nuevo estatus valida los casos de los estatus de casilla no instalada y paquete no entregado
    public void validaEstatusModificado(){
    	
    	//mbCapturaVotos.dto.actaCasilla.idEstatus
    	if( dto.getActaCasilla().getIdEstatus().equals( new Integer(0) )){
    		agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Al modificar, elija un estatus");
    	}
    	else{
    	
    		// se ha elejido el estatus de "casilla no instalada" o "paquete no entregado": ponemos los votos en null para mostrar los campos de votos vacios en pantalla, y al guardar el acta los votos se guardan en 0 para estos estatus
    		if(  dto.getActaCasilla().getIdEstatus().equals( new Integer(3) )  || dto.getActaCasilla().getIdEstatus().equals( new Integer(4) )   ){
    		
    			// ponemos los votos en NULL
    			for ( DTOAsociacion  asociacion :  dto.getAsociaciones() ) {
    				
    				asociacion.setVotos( null );		
    				// es una coalicion
    				if(asociacion.getTipoAsociacion().equals( new Integer(3)) && asociacion.getIdCoalicion().equals( new Integer(-10) ) ){
    									
    					for (DTOSubcoalicion subcoalicion : asociacion.getSubcoaliciones()) {
    						subcoalicion.setVotos( null );
						}
    				}    				
				}    			
    		}
    		
    		// si reseleccionan un estaus de acta de "cotejo" o "recuento parcial" y aun no ha modificado el acta, se muestran de nuevo los votos originales del acta
    		else if(  dto.getActaCasilla().getIdEstatus().equals( new Integer(1) )  || dto.getActaCasilla().getIdEstatus().equals( new Integer(2) ) ){

    			Integer estatusTemp = new Integer(dto.getActaCasilla().getIdEstatus());
    			
    			reiniciaEstatusAsociacionesYCargaCaptura();
    			
    			// solo recuperamos los votos originales pero el estatus es el que esta en pantalla
    			dto.getActaCasilla().setIdEstatus(estatusTemp);
    		}
    	}
    }
    
    
    // metodo encargado de guardar un acta capturada en pantalla
    public String guardaActa(){
    	
    	System.out.println(" entro a guardaActa! ");
    	
    	List<DTOActaCasillaVotos> votosAGuardar = new ArrayList<DTOActaCasillaVotos>();
    	
    	for (DTOAsociacion asociacion : dto.getAsociaciones()) {
    		
    		// guardamos el voto de una asociacion de tipo partido, candidato independiente o coalicion
    		DTOActaCasillaVotos votoPartidoIndCoal = new DTOActaCasillaVotos();
    		
    			votoPartidoIndCoal.getId().setIdProcesoElectoral( dto.getProcesoElectoral() );
    			votoPartidoIndCoal.getId().setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
    			votoPartidoIndCoal.getId().setIdEstado( dto.getIdEdoSeleccionado() );
    			
    			if( esTipoGobernadorAyuntamiento() ){
    				votoPartidoIndCoal.getId().setIdDistrito( new Integer(-5) );
    				votoPartidoIndCoal.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
    				votoPartidoIndCoal.getId().setIdRegiduria( new Integer(-7) );
    			}
    			
    			else if( esTipoDiputado()  ){
    				votoPartidoIndCoal.getId().setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
    				votoPartidoIndCoal.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
    				votoPartidoIndCoal.getId().setIdRegiduria( new Integer(-7) );
    			}
    			
    			else if( esTipoRegiduria() ){
    				votoPartidoIndCoal.getId().setIdDistrito( new Integer(-5) );
    				votoPartidoIndCoal.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
    				votoPartidoIndCoal.getId().setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
    			}
    			
    			votoPartidoIndCoal.getId().setSeccion( dto.getActaCasilla().getId().getSeccion() );
    			votoPartidoIndCoal.getId().setIdComunidad( new Integer(-8) );
    			votoPartidoIndCoal.getId().setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );
    			votoPartidoIndCoal.getId().setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla() );
    			votoPartidoIndCoal.getId().setExtContigua( dto.getActaCasilla().getId().getExtContigua() );
    			votoPartidoIndCoal.getId().setIdTipoCandidatura( dto.getIdCandidatura() );
    			votoPartidoIndCoal.getId().setIdAsociacion( asociacion.getIdAsociacion() );
    			votoPartidoIndCoal.getId().setTipoAsociacion( asociacion.getTipoAsociacion() );
    			votoPartidoIndCoal.getId().setIdCoalicion( asociacion.getIdCoalicion() );
    		
    			votoPartidoIndCoal.setIdEstatus( dto.getActaCasilla().getIdEstatus() );
    			votoPartidoIndCoal.setOrden( asociacion.getOrden() );
    			
    			if( dto.getActaCasilla().getIdEstatus().equals( new Integer(1) ) || dto.getActaCasilla().getIdEstatus().equals( new Integer(2) ) || dto.getActaCasilla().getIdEstatus().equals( new Integer(5) ) ){
    				votoPartidoIndCoal.setVotos( new Integer ( asociacion.getVotos().intValue() ) );
    			}
    			else if ( dto.getActaCasilla().getIdEstatus().equals( new Integer(3) ) || dto.getActaCasilla().getIdEstatus().equals( new Integer(4) ) ){
    				votoPartidoIndCoal.setVotos( new Integer ( 0 ) );
    			}
    			
    			votoPartidoIndCoal.setCapturada( new Integer(1) );
    			votoPartidoIndCoal.setListaNominal( dto.getActaCasilla().getListaNominal() );
    			votoPartidoIndCoal.setUsuario( dto.getUserLogin().getUsuario() );
    			votoPartidoIndCoal.setFechaHora( new Date() );
    			votoPartidoIndCoal.setCabeceraDistrital( dto.getActaCasilla().getCabeceraDistrital() );
    			
			// agregamos la asociacion
			votosAGuardar.add(votoPartidoIndCoal);
    			
			// si la asociacion es una coalicion, entonces agregamos los votos de sus subcoaliciones
			if( asociacion.getTipoAsociacion().equals( new Integer(3) ) ){
				
				for (DTOSubcoalicion subcoalicion : asociacion.getSubcoaliciones()) {
						
						DTOActaCasillaVotos votoSubcoalicion = new DTOActaCasillaVotos();
						
							votoSubcoalicion.getId().setIdProcesoElectoral( dto.getProcesoElectoral() );
							votoSubcoalicion.getId().setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
							votoSubcoalicion.getId().setIdEstado( dto.getIdEdoSeleccionado() );
				    			
			    			if( esTipoGobernadorAyuntamiento() ){
			    				votoSubcoalicion.getId().setIdDistrito( new Integer(-5) );
			    				votoSubcoalicion.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
			    				votoSubcoalicion.getId().setIdRegiduria( new Integer(-7) );
			    			}
			    			
			    			else if( esTipoDiputado() ){
			    				votoSubcoalicion.getId().setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
			    				votoSubcoalicion.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
			    				votoSubcoalicion.getId().setIdRegiduria( new Integer(-7) );
			    			}
			    			
			    			else if( esTipoRegiduria() ){
			    				votoSubcoalicion.getId().setIdDistrito( new Integer(-5) );
			    				votoSubcoalicion.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
			    				votoSubcoalicion.getId().setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
			    			}
			    			
			    			votoSubcoalicion.getId().setSeccion( dto.getActaCasilla().getId().getSeccion() );
			    			votoSubcoalicion.getId().setIdComunidad( new Integer(-8) );
			    			votoSubcoalicion.getId().setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );
			    			votoSubcoalicion.getId().setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla() );
			    			votoSubcoalicion.getId().setExtContigua( dto.getActaCasilla().getId().getExtContigua() );
			    			votoSubcoalicion.getId().setIdTipoCandidatura( dto.getIdCandidatura() );
			    			votoSubcoalicion.getId().setTipoAsociacion( asociacion.getTipoAsociacion() );
			    		
			    			votoSubcoalicion.setIdEstatus( dto.getActaCasilla().getIdEstatus() );
			    			votoSubcoalicion.setCapturada( new Integer(1) );
			    			votoSubcoalicion.setListaNominal( dto.getActaCasilla().getListaNominal() );
			    			votoSubcoalicion.setUsuario( dto.getUserLogin().getUsuario() );
			    			votoSubcoalicion.setFechaHora( new Date() );
			    			votoSubcoalicion.setCabeceraDistrital( dto.getActaCasilla().getCabeceraDistrital() );
	
			    			
			    			if( dto.getActaCasilla().getIdEstatus().equals( new Integer(1) ) || dto.getActaCasilla().getIdEstatus().equals( new Integer(2) ) || dto.getActaCasilla().getIdEstatus().equals( new Integer(5) ) ){
			    				votoSubcoalicion.setVotos( new Integer( subcoalicion.getVotos().intValue() ) );
			    			}
			    			else if ( dto.getActaCasilla().getIdEstatus().equals( new Integer(3) ) || dto.getActaCasilla().getIdEstatus().equals( new Integer(4) ) ){
			    				votoSubcoalicion.setVotos( new Integer ( 0 ) );
			    			}
							
							votoSubcoalicion.setOrden( subcoalicion.getOrden() );
							votoSubcoalicion.getId().setIdAsociacion( subcoalicion.getIdSubcoalicion() );
							votoSubcoalicion.getId().setIdCoalicion( subcoalicion.getIdCoalicion() );

							
						votosAGuardar.add(votoSubcoalicion);	
				}
			}
		
    	} // fin de for de la iteración de las asociaciones
    	
    	
    	// mandamos los votos del acta para ser insertados en BD
    	try {
			bsdCapturaVotos.guardaVotosDeActa(votosAGuardar);
			agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_guarda"));
			return "success";
		} catch (Exception e) {
			LOGGER.error("Error MBCapturaVotos - guardaActa() - guardaVotosDeActa()", e);
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al guardar el acta");
			return "error";
		}
    	
    }
    
    // metodo encargado de eliminar un acta consultada en pantalla
    public String eliminaActa(){
    	
    	System.out.println(" entro a eliminaActa ");
    	
    	List<DTOActaCasillaVotos> acta = new ArrayList<DTOActaCasillaVotos>();
    	DTOActaCasillaVotos actaAEliminar = new DTOActaCasillaVotos();
		
    	// construimos la llaver del acta a consultar
    	actaAEliminar.getId().setIdProcesoElectoral( dto.getProcesoElectoral() );
    	actaAEliminar.getId().setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
    	actaAEliminar.getId().setIdEstado( dto.getIdEdoSeleccionado() );
		
		if( esTipoGobernadorAyuntamiento() ){
			actaAEliminar.getId().setIdDistrito( new Integer(-5) );
			actaAEliminar.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
			actaAEliminar.getId().setIdRegiduria( new Integer(-7) );
		}
		
		else if( esTipoDiputado()  ){
			actaAEliminar.getId().setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
			actaAEliminar.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
			actaAEliminar.getId().setIdRegiduria( new Integer(-7) );
		}
		
		else if( esTipoRegiduria() ){
			actaAEliminar.getId().setIdDistrito( new Integer(-5) );
			actaAEliminar.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
			actaAEliminar.getId().setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
		}
		
		actaAEliminar.getId().setSeccion( dto.getActaCasilla().getId().getSeccion() );
		actaAEliminar.getId().setIdComunidad( new Integer(-8) );
		actaAEliminar.getId().setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );
		actaAEliminar.getId().setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla() );
		actaAEliminar.getId().setExtContigua( dto.getActaCasilla().getId().getExtContigua() );
		actaAEliminar.getId().setIdTipoCandidatura( dto.getIdCandidatura() );
		
		// consultamos que existan registros de votos para el acta a eliminar
		try {
			acta = bsdCapturaVotos.consultaActa(actaAEliminar.getId());
		
			if ( acta == null || acta.isEmpty() ) {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_registro_no_existe"));
				return "error";
			}
			
			// si hay registros del acta
			else{
				
				// para las 4 candidaturas validamos que no existan Actas finales antes de evaluar si hay distribuciones finales
				DTOActaTipoCandidaturaPK pkActa = new DTOActaTipoCandidaturaPK();
				pkActa.setIdProcesoElectoral( dto.getProcesoElectoral() );
				pkActa.setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
				pkActa.setIdEstado( dto.getIdEdoSeleccionado() );
				
				if( candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO) || candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) || candidatura.equalsIgnoreCase(constantes.GOBERNADOR)  ){
					pkActa.setIdDistrito( new Integer(-5) );
				}
				else {
					pkActa.setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
				}
				
				if( candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO) || candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) ){
					pkActa.setIdMunicipio( dto.getIdMunSeleccionado() );
				}
				else{
					pkActa.setIdMunicipio( new Integer(-6) );
				}
				
				if( candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) ){
					pkActa.setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
				}
				else{
					pkActa.setIdRegiduria( new Integer(-7) );
				}
				
				pkActa.setIdComunidad( new Integer(-8) );
				pkActa.setIdTipoCandidatura( dto.getIdCandidatura() );
				pkActa.setTipoActa( new Integer(1) );
				
				DTOBase actaFinal = bsdActas.getActa(pkActa);
				
				// si existe acta final
				if (actaFinal != null) {
					//agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_ActaEliminar"));
					agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_ActaFinalEliminar"));
					return "error";
				}
				// no existe acta final
				else {
					
					// validamos que no tenga distribución total o parcial, segun sea el caso
					
					DTODistribucionCandidatosPK pkDistribucion = new DTODistribucionCandidatosPK();
						pkDistribucion.setIdProcesoElectoral( dto.getProcesoElectoral() );
						pkDistribucion.setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
						pkDistribucion.setIdEstado( dto.getIdEdoSeleccionado() );
						pkDistribucion.setIdDistrito( actaAEliminar.getId().getIdDistrito() );
						pkDistribucion.setIdMunicipio( actaAEliminar.getId().getIdMunicipio() );
						pkDistribucion.setIdRegiduria( actaAEliminar.getId().getIdRegiduria() );
						pkDistribucion.setIdTipoCandidatura( dto.getIdCandidatura() );
					
					if( esTipoRegiduria() || candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO)  ){

						try {
							
							List<DTODistribucionTotales> distribucionTotal = new ArrayList<DTODistribucionTotales>();
							distribucionTotal = bsdDistribucionVotos.consultarDistribucionTotales(pkDistribucion);
							if(distribucionTotal != null && distribucionTotal.size() > 0){
								agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_distribucionFinalEliminar"));
								return "error";
							}
							// si no tiene una distribución, borramos el acta
							else{
								
								try {
									bsdCapturaVotos.eliminarActa(acta);
									agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_elimina"));
									return "success";
								} catch (Exception e) {
									LOGGER.error("Error MBCapturaVotos - eliminaActa() - eliminarActa()", e);
									agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al eliminar el acta");
									return "error";
								}
							}
							
						} catch (Exception e) {
							LOGGER.error("Error MBCapturaVotos - eliminaActa() - consultarDistribucionTotales()", e);
							agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al eliminar el acta");
							return "error";
						}
					}
					
					// es Diputado o Gobernador
					else{
						
						// validamos si hay distribución total
						
						// gober
						if( candidatura.equalsIgnoreCase(constantes.GOBERNADOR) ){
							pkDistribucion.setIdDistrito( new Integer(-5) );
							pkDistribucion.setIdMunicipio( new Integer(-6) );
							pkDistribucion.setIdRegiduria( new Integer(-7) );
						}
						
						// dip MR
						else{
							pkDistribucion.setIdMunicipio( new Integer(-6) );
							pkDistribucion.setIdRegiduria( new Integer(-7) );
						}
						
						List<DTODistribucionTotales> distribucionTotal = new ArrayList<DTODistribucionTotales>();
						//En actas finales no nos interesa el municipio
						distribucionTotal = bsdDistribucionVotos.consultarDistribucionTotales(pkDistribucion);
						if(distribucionTotal != null && distribucionTotal.size() > 0){
							agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_distribucionFinalEliminar"));
							return "error";
						}
						
						// si no tiene una distribución Final, ahora verificamos si tiene distribución parcial
						else{
						
							try {
							
								List<DTODistribucionTotParcial> distribucionParcial = new ArrayList<DTODistribucionTotParcial>();
								//En actas parciales si nos interesa el municipio
								pkDistribucion.setIdMunicipio(dto.getIdMunSeleccionado());
								distribucionParcial = bsdDistribucionVotos.consultarDistribucionTotalParcial(pkDistribucion);
								
								if(distribucionParcial != null && distribucionParcial.size() > 0){
									agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_distribucionParcialEliminar"));
									return "error";
								}
								
								// si no tiene una distribución, borramos el acta
								else{
									
									try {
										bsdCapturaVotos.eliminarActa(acta);
										agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_elimina"));
										return "success";
									} catch (Exception e) {
										LOGGER.error("Error MBCapturaVotos - eliminaActa() - eliminarActa()", e);
										agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al eliminar el acta");
										return "error";
									}
								}
							
							} catch (Exception e) {
								LOGGER.error("Error MBCapturaVotos - eliminaActa() - consultarDistribucionTotalParcial()", e);
								agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al eliminar el acta");
								return "error";
							}
						}
					}
					
				}
				
			}
			
		} catch (Exception e) {
			LOGGER.error("Error MBCapturaVotos - eliminaActa() - consultaActa()", e);
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al eliminar el acta");
			return "error";
		}		
    }
    
    
    // metodo encargado de modificar un acta consultada en pantalla
    public String modificaActa(){

    	System.out.println(" entro a modificarActa ");
    	
    	if( !dto.getActaCasilla().getIdEstatus().equals( new Integer(0) )  ){
        	List<DTOActaCasillaVotos> acta = new ArrayList<DTOActaCasillaVotos>();
        	DTOActaCasillaVotos actaAConsultar = new DTOActaCasillaVotos();
    		
        	// construimos la llaver del acta a consultar
        	actaAConsultar.getId().setIdProcesoElectoral( dto.getProcesoElectoral() );
        	actaAConsultar.getId().setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
        	actaAConsultar.getId().setIdEstado( dto.getIdEdoSeleccionado() );
    		
    		if( esTipoGobernadorAyuntamiento() ){
    			actaAConsultar.getId().setIdDistrito( new Integer(-5) );
    			actaAConsultar.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
    			actaAConsultar.getId().setIdRegiduria( new Integer(-7) );
    		}
    		
    		else if( esTipoDiputado()  ){
    			actaAConsultar.getId().setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
    			actaAConsultar.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
    			actaAConsultar.getId().setIdRegiduria( new Integer(-7) );
    		}
    		
    		else if( esTipoRegiduria() ){
    			actaAConsultar.getId().setIdDistrito( new Integer(-5) );
    			actaAConsultar.getId().setIdMunicipio( dto.getIdMunSeleccionado() );
    			actaAConsultar.getId().setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
    		}
    		
    		actaAConsultar.getId().setSeccion( dto.getActaCasilla().getId().getSeccion() );
    		actaAConsultar.getId().setIdComunidad( new Integer(-8) );
    		actaAConsultar.getId().setIdCasilla( dto.getActaCasilla().getId().getIdCasilla() );
    		actaAConsultar.getId().setTipoCasilla( dto.getActaCasilla().getId().getTipoCasilla() );
    		actaAConsultar.getId().setExtContigua( dto.getActaCasilla().getId().getExtContigua() );
    		actaAConsultar.getId().setIdTipoCandidatura( dto.getIdCandidatura() );
    		
    		// consultamos que existan registros de votos para el acta a modificar
    		try {
    			acta = bsdCapturaVotos.consultaActa(actaAConsultar.getId());
    		
    			if ( acta == null || acta.isEmpty() ) {
    				agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_registro_no_existe"));
    				return "error";
    			}
    			
    			// si hay registros del acta
    			else{
    				
    				// para las 4 candidaturas validamos que no existan Actas finales antes de evaluar si hay distribuciones finales
    				DTOActaTipoCandidaturaPK pkActa = new DTOActaTipoCandidaturaPK();
    				pkActa.setIdProcesoElectoral( dto.getProcesoElectoral() );
    				pkActa.setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
    				pkActa.setIdEstado( dto.getIdEdoSeleccionado() );
    				
    				if( candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO) || candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) || candidatura.equalsIgnoreCase(constantes.GOBERNADOR)  ){
    					pkActa.setIdDistrito( new Integer(-5) );
    				}
    				else {
    					pkActa.setIdDistrito( dto.getActaCasilla().getId().getIdDistrito() );
    				}
    				
    				if( candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO) || candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) ){
    					pkActa.setIdMunicipio( dto.getIdMunSeleccionado() );
    				}
    				else{
    					pkActa.setIdMunicipio( new Integer(-6) );
    				}
    				
    				if( candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) ){
    					pkActa.setIdRegiduria( dto.getActaCasilla().getId().getIdRegiduria() );
    				}
    				else{
    					pkActa.setIdRegiduria( new Integer(-7) );
    				}
    				
    				pkActa.setIdComunidad( new Integer(-8) );
    				pkActa.setIdTipoCandidatura( dto.getIdCandidatura() );
    				pkActa.setTipoActa( new Integer(1) );
    				
    				DTOBase actaFinal = bsdActas.getActa(pkActa);
    				
    				// si existe acta final
    				if (actaFinal != null) {
    					agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_ActaFinalModificar"));
    					return "error";
    				}
    				// no existe acta final
    				else {
    					
        				// validamos que no tenga distribución total o parcial, segun sea el caso
        				
        				DTODistribucionCandidatosPK pkDistribucion = new DTODistribucionCandidatosPK();
        					pkDistribucion.setIdProcesoElectoral( dto.getProcesoElectoral() );
        					pkDistribucion.setIdDetalleProceso( dto.getDetalleProcesoElectoral() );
        					pkDistribucion.setIdEstado( dto.getIdEdoSeleccionado() );
        					pkDistribucion.setIdDistrito( actaAConsultar.getId().getIdDistrito() );
        					pkDistribucion.setIdMunicipio( actaAConsultar.getId().getIdMunicipio() );
        					pkDistribucion.setIdRegiduria( actaAConsultar.getId().getIdRegiduria() );
        					pkDistribucion.setIdTipoCandidatura( dto.getIdCandidatura() );
        				
        				if( esTipoRegiduria() || candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO)  ){

        					try {
        						
        						// Para estas candidaturas solo se valida la distribución final
        						List<DTODistribucionTotales> distribucionTotal = new ArrayList<DTODistribucionTotales>();
        						distribucionTotal = bsdDistribucionVotos.consultarDistribucionTotales(pkDistribucion);
        						if(distribucionTotal != null && distribucionTotal.size() > 0){
        							agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_distribucionFinalModificar"));
        							return "error";
        						}
        						// si no tiene una distribución, modificamos el acta
        						else{
        							
        							try {
        								
        								boolean esEstatus3_4 = false;
        								// verificamos si se le ha asignado un estatus de casilla no instalada o paquete no entregado al acta
        					    		if(  dto.getActaCasilla().getIdEstatus().equals( new Integer(3) )  || dto.getActaCasilla().getIdEstatus().equals( new Integer(4) )   ){
        					    			esEstatus3_4 = true;
        					    		}
        								
        					    		// recuperamos el estatus y votos de la pantalla, si hay un estatus de acta 3 o 4 se guardan los votos en 0
        								for ( DTOActaCasillaVotos regActa : acta) {
        									
        									regActa.setIdEstatus( dto.getActaCasilla().getIdEstatus() );

        					        		// identificamos las asociaciones por estos campos   id_asociacion, tipo_asociacion, id_coalicion
        				        			for (DTOAsociacion asociacion : dto.getAsociaciones()) {
        				        				
        				        				// se agregan votos de Partidos politicos, independientes y coaliciones padre
        				        				if(asociacion.getIdAsociacion().equals(regActa.getId().getIdAsociacion()) && 
        				        				   asociacion.getTipoAsociacion().equals(regActa.getId().getTipoAsociacion()) && 
        				        				   asociacion.getIdCoalicion().equals(regActa.getId().getIdCoalicion()) ){
        				  
    					        						if(esEstatus3_4){
    					        							regActa.setVotos(  new Integer( 0 )  );
    					        						}
    					        						else{
    					        							regActa.setVotos(  new Integer( asociacion.getVotos().intValue() )  );
    					        						}
        				        				}
        				        				
        				        				// se agregan votos de subcoaliciones 
        				        				else{
        				        					if(asociacion.getTipoAsociacion().equals( new Integer(3)) && asociacion.getIdCoalicion().equals( new Integer(-10) ) ){
        					        					for (DTOSubcoalicion subcoalicion : asociacion.getSubcoaliciones()) {
        					        						//countAsociasiones ++;
        													if(subcoalicion.getIdSubcoalicion().equals(regActa.getId().getIdAsociacion()) &&
        												       asociacion.getTipoAsociacion().equals(regActa.getId().getTipoAsociacion()) &&
        												       subcoalicion.getIdCoalicion().equals(regActa.getId().getIdCoalicion()) ){
        														
        														if(esEstatus3_4){
        															regActa.setVotos( new Integer(  0  ) );
        														}
        														else{
        															regActa.setVotos( new Integer(  subcoalicion.getVotos().intValue()  ) );
        														}
        													}
        												}
        				        					}
        				        				}
        									}
        																
        								}// fin de asignación de estatus y votos
        								
        								
        								// se valida si en la apantalla se muestran asociaciones que en base no se tienen previamente capturadas
        								List<DTOActaCasillaVotos> faltantes = new ArrayList<DTOActaCasillaVotos>();
    									for (DTOAsociacion asociacion : dto.getAsociaciones()) {
											boolean existeRegistro = false;
    										for (DTOActaCasillaVotos regEnBD : acta) {
												if( asociacion.getIdAsociacion().equals(regEnBD.getId().getIdAsociacion()) && 
        				        				    asociacion.getTipoAsociacion().equals(regEnBD.getId().getTipoAsociacion()) && 
		        				        		    asociacion.getIdCoalicion().equals(regEnBD.getId().getIdCoalicion()) )
												{
													existeRegistro = true;
													break;
												}
											}
    										if(!existeRegistro){
    											// copiamos todos los valores en comun y cambiamos solo los particulares
    											DTOActaCasillaVotos noreg = new DTOActaCasillaVotos( acta.get(0) );
    											noreg.getId().setIdAsociacion( asociacion.getIdAsociacion() );
    											noreg.getId().setTipoAsociacion( asociacion.getTipoAsociacion() );
    											noreg.getId().setIdCoalicion( asociacion.getIdCoalicion() );
    											noreg.setOrden( asociacion.getOrden() );
    											noreg.setVotos( new Integer( asociacion.getVotos().intValue() )  );
    											faltantes.add( noreg );
    										}
										}
    									if( faltantes.size() > 0 ){
    										for (DTOActaCasillaVotos faltante : faltantes) {
												acta.add( faltante );
											}
    									}
        								
        								// modificamos el acta
        								bsdCapturaVotos.guardaVotosDeActa(acta);
        								agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_modifica"));
        								return "success";
        							} catch (Exception e) {
        								LOGGER.error("Error MBCapturaVotos - modificaActa() - modifica", e);
        								agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al modificar el acta");
        								return "error";
        							}
        						}
        						
        					} catch (Exception e) {
        						LOGGER.error("Error MBCapturaVotos - modificaActa() - consultarDistribucionTotales()", e);
        						agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al modificar el acta");
        						return "error";
        					}
        				}
        				
        				// es Diputado o Gobernador
        				else{
        					
        					// validamos si hay distribución total
    						
        					// gober
        					if( candidatura.equalsIgnoreCase(constantes.GOBERNADOR) ){
            					pkDistribucion.setIdDistrito( new Integer(-5) );
            					pkDistribucion.setIdMunicipio( new Integer(-6) );
            					pkDistribucion.setIdRegiduria( new Integer(-7) );
        					}
        					
        					// dip MR
        					else{
            					pkDistribucion.setIdMunicipio( new Integer(-6) );
            					pkDistribucion.setIdRegiduria( new Integer(-7) );
        					}
        					
        					List<DTODistribucionTotales> distribucionTotal = new ArrayList<DTODistribucionTotales>();
        					//En actas finales no nos interesa el municipio
    						distribucionTotal = bsdDistribucionVotos.consultarDistribucionTotales(pkDistribucion);
    						if(distribucionTotal != null && distribucionTotal.size() > 0){
    							agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_distribucionFinalModificar"));
    							return "error";
    						}
    						
    						// si no tiene una distribución Final, ahora verificamos si tiene distribución parcial
    						else{
        					
    	    					try {
    	    					
    	    						// validamos si es que hay distribución parcial
    	    						//En actas parciales si nos interesa el municipio
    	    						pkDistribucion.setIdMunicipio(dto.getIdMunSeleccionado());
    	    						List<DTODistribucionTotParcial> distribucionParcial = new ArrayList<DTODistribucionTotParcial>();
    	    						distribucionParcial = bsdDistribucionVotos.consultarDistribucionTotalParcial(pkDistribucion);
    	    						
    	    						if(distribucionParcial != null && distribucionParcial.size() > 0){
    	    							agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_acta_con_distribucionParcialModificar"));
    	    							return "error";
    	    						}
    	    						
    	    						// si no tiene una distribución, modificamos el acta
    	    						else{
    	    							
    	    							try {
    	    								
    	    								boolean esEstatus3_4 = false;
    	    								// verificamos si se le ha asignado un estatus de casilla no instalada o paquete no entregado al acta
    	    					    		if(  dto.getActaCasilla().getIdEstatus().equals( new Integer(3) )  || dto.getActaCasilla().getIdEstatus().equals( new Integer(4) )   ){
    	    					    			esEstatus3_4 = true;
    	    					    		}
    	    								
    	    								// recuperamos el estatus y votos de la pantalla
    	    								for ( DTOActaCasillaVotos regActa : acta) {
    	    									
    	    									regActa.setIdEstatus( dto.getActaCasilla().getIdEstatus() );
    	
    	    					        		// identificamos las asociaciones por estos campos   id_asociacion, tipo_asociacion, id_coalicion
    	    				        			for (DTOAsociacion asociacion : dto.getAsociaciones()) {
    	    				        				
    	    				        				// se agregan votos de Partidos politicos, independientes y coaliciones padre
    	    				        				if(asociacion.getIdAsociacion().equals(regActa.getId().getIdAsociacion()) && 
    	    				        				   asociacion.getTipoAsociacion().equals(regActa.getId().getTipoAsociacion()) && 
    	    				        				   asociacion.getIdCoalicion().equals(regActa.getId().getIdCoalicion()) ){
    	    				  
    						        					if(esEstatus3_4){
    					        							regActa.setVotos(  new Integer( 0 )  );
    					        						}
    					        						else{
    					        							regActa.setVotos(  new Integer( asociacion.getVotos().intValue() )  );
    					        						}
    	    				        					
    	    				        				}
    	    				        				
    	    				        				// se agregan votos de subcoaliciones
    	    				        				else{
    	    				        					if(asociacion.getTipoAsociacion().equals( new Integer(3)) && asociacion.getIdCoalicion().equals( new Integer(-10) ) ){
    	    					        					for (DTOSubcoalicion subcoalicion : asociacion.getSubcoaliciones()) {
    	    													if(subcoalicion.getIdSubcoalicion().equals(regActa.getId().getIdAsociacion()) &&
    	    												       asociacion.getTipoAsociacion().equals(regActa.getId().getTipoAsociacion()) &&
    	    												       subcoalicion.getIdCoalicion().equals(regActa.getId().getIdCoalicion()) ){
    	    														
    	    														if(esEstatus3_4){
    	    															regActa.setVotos( new Integer(  0  ) );
    	    														}
    	    														else{
    	    															regActa.setVotos( new Integer(  subcoalicion.getVotos().intValue()  ) );
    	    														}
    	    														
    	    													}
    	    												}
    	    				        					}
    	    				        				}
    	    									}
    	    																
    	    								}// fin de asignación de estatus y votos
    	    								
    	    								// se valida si en la apantalla se muestran asociaciones que en base no se tienen previamente capturadas
            								List<DTOActaCasillaVotos> faltantes = new ArrayList<DTOActaCasillaVotos>();
        									for (DTOAsociacion asociacion : dto.getAsociaciones()) {
    											boolean existeRegistro = false;
        										for (DTOActaCasillaVotos regEnBD : acta) {
    												if( asociacion.getIdAsociacion().equals(regEnBD.getId().getIdAsociacion()) && 
            				        				    asociacion.getTipoAsociacion().equals(regEnBD.getId().getTipoAsociacion()) && 
    		        				        		    asociacion.getIdCoalicion().equals(regEnBD.getId().getIdCoalicion()) )
    												{
    													existeRegistro = true;
    													break;
    												}
    											}
        										if(!existeRegistro){
        											// copiamos todos los valores en comun y cambiamos solo los particulares
        											DTOActaCasillaVotos noreg = new DTOActaCasillaVotos( acta.get(0) );
        											noreg.getId().setIdAsociacion( asociacion.getIdAsociacion() );
        											noreg.getId().setTipoAsociacion( asociacion.getTipoAsociacion() );
        											noreg.getId().setIdCoalicion( asociacion.getIdCoalicion() );
        											noreg.setOrden( asociacion.getOrden() );
        											noreg.setVotos( new Integer( asociacion.getVotos().intValue() )  );
        											faltantes.add( noreg );
        										}
    										}
        									if( faltantes.size() > 0 ){
        										for (DTOActaCasillaVotos faltante : faltantes) {
    												acta.add( faltante );
    											}
        									}
    	    								
    	    								// modificamos el acta
    	    								bsdCapturaVotos.guardaVotosDeActa(acta);
    	    								agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_modifica"));
    	    								return "success";
    	    							} catch (Exception e) {
    	    								LOGGER.error("Error MBCapturaVotos - modificaActa() - modifica", e);
    	    								agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al modificar el acta");
    	    								return "error";
    	    							}
    	    						}
    	    					
    	    					} catch (Exception e) {
    	    						LOGGER.error("Error MBCapturaVotos - modificaActa() - consultarDistribucionTotalParcial()", e);
    	    						agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al modificar el acta");
    	    						return "error";
    	    					}
    						}
        				}
    					
    				}    				
    			}
    			
    		} catch (Exception e) {
    			LOGGER.error("Error MBCapturaVotos - eliminaActa() - consultaActa()", e);
    			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al modificar el acta");
    			return "error";
    		}
    	}
    	
    	else{
    		agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Al modificar, elija un estatus");
    		return "error";
    	}
		
    }
    
    // evalua si el modulo actual esta abierto o cerrado desde el admin
    public boolean validaModuloAbierto(){
    	
    	boolean regresa = !admin.validaModuloAbierto( admin.getDto().getIdModulo() ); // negamos la respuesta para cuando el modulo este abierno, no deshabilite el boton de elimnar, y cuando este cerrado si lo deshabilite
    	//System.out.println(" VALIDA MODULO ABIERTO: " + regresa);
    	
    	return regresa;
    }
    
    // metodo que evalua si la pantalla ha sido inicalizada para alguna de las dos candidaturas de Diputado
    public boolean esTipoDiputado(){
    
    	boolean siEs = false;
    	
    	if( candidatura.equalsIgnoreCase(constantes.DIPUTADO_MR) || candidatura.equalsIgnoreCase(constantes.DIPUTADO_RP) )
    		siEs = true;
    	
    	return siEs;
    } 
    
    // metodo que evalua si la pantalla ha sido inicalizada para alguna de las dos candidaturas de Regiduria
    public boolean esTipoRegiduria(){
    
    	boolean siEs = false;
    	
    	if( candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR) || candidatura.equalsIgnoreCase(constantes.REGIDURIA_RP) )
    		siEs = true;
    	
    	return siEs;
    }
    
    // metodo que evalua si la pantalla ha sido inicalizada para alguna de las candidaturas de Gobernador o Ayuntamiento
    public boolean esTipoGobernadorAyuntamiento(){
    
    	boolean siEs = false;
    	
    	if( candidatura.equalsIgnoreCase(constantes.GOBERNADOR) || candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO) )
    		siEs = true;
    	
    	return siEs;
    }
	
    
	public FormCapturaActas getDto() {
		return dto;
	}

	public void setDto(FormCapturaActas dto) {
		this.dto = dto;
	}

	public String getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(String candidatura) {
		this.candidatura = candidatura;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}


	public StreamedContent getImagen() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String rutaEmblema = externalContext.getRequestParameterMap().get("emblema");

		try{
			this.imagen = UtilUpload.getImagenStreamedContent(rutaEmblema);
		} catch (Exception e){
			LOGGER.error("Error obteniendo imagen");
			LOGGER.error(e);
			return new DefaultStreamedContent();
		}
					
		return this.imagen;
	}


	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	public DTOCEstatus getEstatusRecuentoTotal() {
		return estatusRecuentoTotal;
	}

	public void setEstatusRecuentoTotal(DTOCEstatus estatusRecuentoTotal) {
		this.estatusRecuentoTotal = estatusRecuentoTotal;
	}

	public DTOAgrupacionCasillas getCasillasParaConsultarRespaldtoTotal() {
		return casillasParaConsultarRespaldtoTotal;
	}

	public void setCasillasParaConsultarRespaldtoTotal(DTOAgrupacionCasillas casillasParaConsultarRespaldtoTotal) {
		this.casillasParaConsultarRespaldtoTotal = casillasParaConsultarRespaldtoTotal;
	}

}
