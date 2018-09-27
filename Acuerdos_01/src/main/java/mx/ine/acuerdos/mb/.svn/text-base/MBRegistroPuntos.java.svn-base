package mx.ine.acuerdos.mb;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroPuntosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.helper.form.HLPFormPuntos;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBRegistroPuntos extends MBGeneric implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4260633055930688484L;
	
	private static final Log log = LogFactory.getLog(MBRegistroPuntos.class);

	@Autowired
	@Qualifier("bsdRegPuntos")
	private transient BSDRegistroPuntosInterface bsdRegPuntos;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	private HLPFormPuntos form;
	
	private String parametroFlujo;
	
	private List<DTOCAreas> areas;
	
	private List<DTOCAreas> seleccionados;
	
	private DualListModel<String> seleccionables;
	
	private List<DTOResponsables> responsables;
	
	private List<DTOCAreas> seleccionadosAux;
	
	private List<DTOResponsables> responsablesAux;
	
	private DTOAcuerdos acuerdo;
	
	private boolean rolValido;
	
	private boolean responsConjunta;
	
	private List<String> selecAuxTarget;
	private List<String> selecAuxSource;
	
	private List<String> respConjunta;
	
    private Integer contadorResponsabilidad;
    
    private List<String> respSuelta;
    
    private List<DTOCAreas> areasSinResponsable;
	
	public void init(String idNumAcuerdo) {
		this.rolValido = false;	
		form = new HLPFormPuntos();
		this.acuerdo = bsdRegPuntos.obtenerAcuerdo(idNumAcuerdo);
		form.getPkPunto().setIdNumAcuerdo(idNumAcuerdo);
		form.setClasificaciones(bsdRegPuntos.recuperaClasificaciones());
		obtenerFecha();
		areas = bsdRegPuntos.obtenerTodasLasAreas();
		//areasSinResponsable = bsdRegPuntos.obtenerTodasLasAreas();
		seleccionados	= new ArrayList<DTOCAreas>();		
		responsables  	= new ArrayList<DTOResponsables>();
		seleccionadosAux	= new ArrayList<DTOCAreas>();	
		responsablesAux  	= new ArrayList<DTOResponsables>();
		
		//lista de nombres de imagenes de inforgrafias
		form.setListaImgsInfografias(bsdRegPuntos.recuperaImgsInfografias());
		//System.out.println(form.getListaImgsInfografias().toString());
		mbAdmin.setImgsInfografias(form.getListaImgsInfografias());
		
		selecAuxSource = new ArrayList<String>();
		selecAuxTarget = new ArrayList<String>();
		
		responsConjunta = false;
		
		seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
		
		respConjunta = new ArrayList<String>();
		respSuelta = new ArrayList<String>();
		 
		contadorResponsabilidad = new Integer(0);
	}
	
	//metodo para reemplazar las comillas estilizadas y no se guarden como signos de interrogacion
	public String reemplazaComillasEstilizadas(String cadena){
		char comillaIzq = "“".charAt(0);					
		char comillaDer = "”".charAt(0);			
		char c = (char)34; //codigo ascii de las comillas normales
			cadena = cadena.replace(comillaIzq, c);//se reemplazan las comillas estilizadas izq
			cadena = cadena.replace(comillaDer, c);//	se reemplazan las comillas estilizadas der	
		return cadena;	
	}

	public void registrarPunto() {
		form.getPunto().setTextoPunto(reemplazaComillasEstilizadas(form.getPunto().getTextoPunto()));
		if(seleccionados.size() == 0){
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,"Debe seleccionar al menos un responsable");	
			return;
		}
			
		if(form.isOficioAdjunto()){
		
			form.getPunto().setUrlArchivoAdj( form.getRutaOficioNotificacionFile() + File.separator + form.getRenameOficioNotificacionFile() );
			
		}
		if(!responsConjunta){
			if (bsdRegPuntos.guardarPunto(form, responsables, form.getClasificaciones(), acuerdo, seleccionados)) {
				agregaMensaje(TipoMensaje.INFO_MENSAJE,
						"¡Punto registrado correctamente!");
				init(form.getPkPunto().getIdNumAcuerdo());
			}
			else {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE,
						"Ocurrio un error, revisé los datos ingresados");				
			}
		}else{
			if (bsdRegPuntos.guardarPunto(form, responsables, form.getClasificaciones(), acuerdo, seleccionados, respConjunta,respSuelta)) {
				agregaMensaje(TipoMensaje.INFO_MENSAJE,
						"¡Punto registrado correctamente!");
				init(form.getPkPunto().getIdNumAcuerdo());
			}
			else {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE,
						"Ocurrio un error, revisé los datos ingresados");				
			}
		}

	}

	public String obtenerNumeraliaCardinal() {
		String numeraliaTemp = bsdRegPuntos.obtenerNumeraliCardinal(form
				.getPkPunto());
		form.setNumOrdinal(numeraliaTemp);
		return form.getNumOrdinal();
	}

	public void limpiarInputsSanciones() {
		if (!bsdRegPuntos.obtenerSiPPN(form)) {
			form.setEsPPN(false);
			form.setEsFechSesion(true);
		}
	}
	public boolean getClasificacionValida() {
		
		return bsdRegPuntos.obtenerSiPPN(form);
	}
	
	public Date	 obtenerFecha(){
		Date fecha = bsdRegPuntos.obtenerFechaSesion(form.getPkPunto().getIdNumAcuerdo());
		form.setFechaSesion(fecha);
		return form.getFechaSesion(); 
	}

	public void agregaAdjuntos(ActionEvent actionEvent) {
		
		try {
			
			// si hay un PDF del oficio de notificacion que se haya adjuntado, lo guardamos en el gluster
			if (  form.getOficioNotificacionFile() != null && !form.getPkPunto().getIdNumAcuerdo().equals("") && !form.getPkPunto().getNumeralia().equals(new Integer(0)) ) {
				
				// guardamos el acuerdo adjunto
				
				form.setRutaOficioNotificacionFile( boArchivos.getRutaBaseGluster() );
				form.setRutaOficioNotificacionFile( boArchivos.getSufijoRutaGluster( moduloArchivo.PUNTOS_ARCHIVO , form.getRutaOficioNotificacionFile()));
				
				form.setRenameOficioNotificacionFile( boArchivos.getSufijoNombreOficioNotificacionFile(tipoArchivo.OFICIONOTIFICACIONPUNTO, form.getPkPunto().getIdNumAcuerdo(), form.getPkPunto().getNumeralia(), form.getOficioNotificacionFile().getFileName()  ) );
						
				
				form.setOficioAdjunto(
											boArchivos.almacenarArchivoEnGluster( form.getOficioNotificacionFile() , form.getRutaOficioNotificacionFile() , form.getRenameOficioNotificacionFile())
									  );

			}

		} catch (Exception e) {
			e.printStackTrace();			
			log.error(" MBRegistroPuntos.agregaAdjuntos :" + e.getMessage());
		}

	}
	
	
	public void agregaOficioNotificacionAdjunto(FileUploadEvent event) {
		try {			
				//form.setOficioNotificacionFile( event.getFile() ) ;
				
			     UploadedFile file = event.getFile();
			     form.setOficioNotificacionFile( file ) ;
			     form.setNomArch("Archivo precargado: " + file.getFileName());
				
		}
		catch(Exception e){			
			log.error(" MBRegistroPuntos.gregaOficioNotificacionAdjunto :" + e.getMessage());
		}
	}
	
	public void rowSelected(SelectEvent event){	
		seleccionados.add((DTOCAreas) event.getObject());		
		seleccionados.remove(seleccionados.size()-1);
			responsables = bsdRegPuntos.obtenerResponsablesArea(seleccionados);
		//se guardan en un auxiliar por que al seleccionar una fila se eliminan los originales (defecto de primefaces)	
		seleccionadosAux = seleccionados;
		responsablesAux = responsables;

		form.setArbolRespons( bsdRegPuntos.construirArbolRespons(seleccionados, responsables) );
	}
	
	public void rowUnselected(UnselectEvent event){	
		seleccionados.remove((DTOCAreas) event.getObject());
		responsables = bsdRegPuntos.obtenerResponsablesArea(seleccionados);
			
			seleccionadosAux = seleccionados;
			responsablesAux = responsables;

		form.setArbolRespons( bsdRegPuntos.construirArbolRespons(seleccionados, responsables) );
	}
	
	public void rowSelect(SelectEvent event){		
			seleccionados.add((DTOCAreas) event.getObject());		
			//seleccionados.remove(seleccionados.size()-1);
				seleccionadosAux.add(seleccionados.get(0));
				seleccionados = seleccionadosAux;
				 HashSet hs = new HashSet();
				 		hs.addAll(seleccionados);
				 		seleccionados.clear();
				 		seleccionados.addAll(hs);
				 		
				seleccionadosAux = seleccionados; 		
				responsables = bsdRegPuntos.obtenerResponsablesArea(seleccionados);

		form.setArbolRespons( bsdRegPuntos.construirArbolRespons(seleccionados, responsables) );
	}	

	public boolean esResponsableArea(int a, int b){
		if (a == b){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Metodo que recupera el Rol del usuario en sesion y valida si cuenta con el rol necesario para la pantalla de Home
	 * @author Giovanni Hernández Alonso
	 * @since 24/07/2017
	 * @param 
	 * @return boolean rolValido
	 * **/
	public boolean validaRoles(){
		
		this.rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( Constantes.CAPTURA_ADMIN_OC )	||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( Constantes.CAPTURA_SE_OC )			
    	   ){
				this.rolValido = true;
    		}
		return this.rolValido;
	}
	
	public void responsabilidad(){
		if(responsConjunta){
			 selecAuxSource = new ArrayList<String>();
			 selecAuxTarget = new ArrayList<String>();	
				
			 	int contadorSeleccionados = 0;
				for (DTOCAreas sel : seleccionadosAux) {
					contadorSeleccionados++;
					selecAuxSource.add(sel.getDescripcion());		
				}
				if(contadorSeleccionados == 2){//si solo tiene dos areas seleccionadas
					selecAuxSource = new ArrayList<String>();
					respConjunta.add("Responsabilidad Conjunta. 1" );						
					for (DTOCAreas sel : seleccionadosAux) {											
						respConjunta.add(sel.getDescripcion());
					}	
				}
				
			seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
		}else{
			contadorResponsabilidad = 0;
			selecAuxSource = new ArrayList<String>();
			selecAuxTarget = new ArrayList<String>();
			seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
			
			respConjunta.clear();
		}
	}
	
	public void guardaResponsabilidadConjunta(){
	
		if(seleccionables.getTarget().size() <= 1){
			agregaMensaje(TipoMensaje.INFO_MENSAJE,"Selecciona al menos 2 áreas para asignar Responsabilidad Conjunta");
			return;
		}
		contadorResponsabilidad ++;	
		
			respConjunta.add("Responsabilidad Conjunta. "+contadorResponsabilidad );
			
				for (String target : seleccionables.getTarget()) {					
					
					respConjunta.add(target);					
				}
				//se remueven del target los asignados a la lista de responsabilidad conjunta				
				for (String respElim : respConjunta) {
					if(seleccionables.getTarget().contains(respElim)){
						seleccionables.getTarget().remove(respElim);
					}
				}
		if(seleccionables.getSource().size() == 1){
			for (String source : seleccionables.getSource()) {
				respSuelta.add(source);
			}	
			//se remueven del source los asignados a la lista de responsabilidad suelta	
			for (String respElim : respSuelta) {
				if(seleccionables.getSource().contains(respElim)){
					seleccionables.getSource().remove(respElim);
				}
			}
		}		
		
	}
	
	public void guardaResponsabilidadSuelta(){
		if(seleccionables.getTarget().size()==1){
			agregaMensaje(TipoMensaje.INFO_MENSAJE,"Selecciona al menos 2 áreas para asignar Responsabilidad Conjunta");
			return;
		}
		if(seleccionables.getTarget().size()>=2){
			contadorResponsabilidad ++;	
			
			respConjunta.add("Responsabilidad Conjunta. "+contadorResponsabilidad );
			
				for (String target : seleccionables.getTarget()) {					
					
					respConjunta.add(target);					
				}
				//se remueven del target los asignados a la lista de responsabilidad conjunta				
				for (String respElim : respConjunta) {
					if(seleccionables.getTarget().contains(respElim)){
						seleccionables.getTarget().remove(respElim);
					}
				}
		}
		for (String source : seleccionables.getSource()) {
			respSuelta.add(source);
		}	
		//se remueven del source los asignados a la lista de responsabilidad suelta	
		for (String respElim : respSuelta) {
			if(seleccionables.getSource().contains(respElim)){
				seleccionables.getSource().remove(respElim);
			}
		}		
		
	}
	
	public void handleTransfer(TransferEvent event) {
		event.getItems(); // List of items transferred
		event.isAdd(); // Is transfer from source to target
		event.isRemove(); // Is transfer from target to source
	}
	
	public void modResponsabilidad(){
		contadorResponsabilidad = 0;
		responsConjunta = false;		
		respSuelta.clear();
		respConjunta.clear();		
	}

	
	public String getParametroFlujo() {
		return parametroFlujo;
	}

	public void setParametroFlujo(String parametroFlujo) {
		this.parametroFlujo = parametroFlujo;
	}

	public HLPFormPuntos getForm() {
		return form;
	}

	public void setForm(HLPFormPuntos form) {
		this.form = form;
	}

	public BSDRegistroPuntosInterface getBsdRegPuntos() {
		return bsdRegPuntos;
	}

	public void setBsdRegPuntos(BSDRegistroPuntosInterface bsdRegPuntos) {
		this.bsdRegPuntos = bsdRegPuntos;
	}

	public List<DTOCAreas> getareas() {
		return areas;
	}

	public void setareas(List<DTOCAreas> areas) {
		this.areas = areas;
	}

	public List<DTOCAreas> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<DTOCAreas> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public List<DTOResponsables> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<DTOResponsables> responsables) {
		this.responsables = responsables;
	}

	public DTOAcuerdos getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(DTOAcuerdos acuerdo) {
		this.acuerdo = acuerdo;
	}

	public DualListModel<String> getSeleccionables() {
		return seleccionables;
	}

	public void setSeleccionables(DualListModel<String> seleccionables) {
		this.seleccionables = seleccionables;
	}

	public boolean getResponsConjunta() {
		return responsConjunta;
	}

	public void setResponsConjunta(boolean responsConjunta) {
		this.responsConjunta = responsConjunta;
	}

	public List<String> getRespConjunta() {
		return respConjunta;
	}

	public void setRespConjunta(List<String> respConjunta) {
		this.respConjunta = respConjunta;
	}

	public List<String> getRespSuelta() {
		return respSuelta;
	}

	public void setRespSuelta(List<String> respSuelta) {
		this.respSuelta = respSuelta;
	}

	public List<DTOCAreas> getAreasSinResponsable() {
		return areasSinResponsable;
	}

	public void setAreasSinResponsable(List<DTOCAreas> areasSinResponsable) {
		this.areasSinResponsable = areasSinResponsable;
	}

}
