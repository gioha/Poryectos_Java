package mx.ine.acuerdos.mb;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDModificarPuntosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
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

public class MBModificarPuntos extends MBGeneric implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8842957550852924318L;
	
	private static final Log log = LogFactory.getLog(MBModificarPuntos.class);

	@Autowired
	@Qualifier("bsdModPuntos")
	private transient BSDModificarPuntosInterface bsdModPuntos;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	private HLPFormPuntos form;	
	
	private Integer numeraliaInicial;
	private String puntosPrueba;
	private List<DTOCAreas> areas;
	private List<DTOSeguimientosCG> responsableDelPunto;
	private List<DTOCAreas> seleccionados;
	private List<DTOResponsables> responsables;	
	private List<DTOSeguimientosCG> responsablesTodoSeg;
	private boolean rolValido;
	private List<DTOCAreas> seleccionadosAux;	
	private List<DTOResponsables> responsablesAux;
	private DTOAcuerdos acuerdo;
	private boolean responsConjunta;
	private boolean nuevaResponsConjunta;
	
	private List<String> respConjunta;
	private List<String> respSuelta;
	private List<String> selecAuxSource;
	private List<String> selecAuxTarget;
	private DualListModel<String> seleccionables;
	private Integer contadorResponsabilidad;
	private boolean fueElimSeguim;
	
	public void init(String idAcuerdo, Integer numeralia) {
		fueElimSeguim = false;
		responsConjunta = false;
		nuevaResponsConjunta = false;
		this.rolValido = false;
		this.form = new HLPFormPuntos();
		
		this.numeraliaInicial = numeralia;
		this.acuerdo = bsdModPuntos.obtenerAcuerdo(idAcuerdo);
		this.form=(this.bsdModPuntos.obtenerPunto(idAcuerdo, numeralia));
		this.form.setClasificaciones(this.bsdModPuntos
				.recuperaClasificaciones());
		this.form.setPkPunto(this.form.getPunto().getId());
		obtenerNumeraliaCardinal();
		
		responsablesAux  	= new ArrayList<DTOResponsables>();
		selecAuxSource = new ArrayList<String>();
		selecAuxTarget = new ArrayList<String>();
		seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
		contadorResponsabilidad = new Integer(0);
		
		respConjunta = new ArrayList<String>();
		respSuelta   = new ArrayList<String>();
		//se obtienen todas las areas del catalogo
		areas = bsdModPuntos.obtenerTodasLasAreas();
		//se obtienen los responsables de cada area del punto
			responsableDelPunto = bsdModPuntos. obtenerResponsablesDelPunto(idAcuerdo, numeralia);	
			responsablesTodoSeg = bsdModPuntos. obtenerResponsablesDelPunto(idAcuerdo, numeralia);
			seleccionados	= new ArrayList<DTOCAreas>();					
		//se pasan a la lista de seleccionados para que aparezcan preseleccionados 
			for (DTOSeguimientosCG res : responsableDelPunto) {
				DTOCAreas area = bsdModPuntos.obtenerArea(res.getId().getIdArea());					
				   seleccionados.add(area);		  
			}	
		seleccionadosAux = seleccionados;	
		responsables = bsdModPuntos.obtenerResponsablesArea(seleccionados);	
		
		//establecer nombres de archivos precargados
		if (form.getPunto().getUrlArchivoAdj()!=null){
			form.setNomArch(form.getPunto().getUrlArchivoAdj());
			form.setNomArch(bsdModPuntos.extraerNomArchivo(form.getNomArch()));
			form.setRutaPrevisualizacionOficio(boArchivos.getRutaPrevisualizacion(form.getPunto().getUrlArchivoAdj()));
		}
		
		//lista de nombres de imagenes de inforgrafias
		form.setListaImgsInfografias(bsdModPuntos.recuperaImgsInfografias());
		
		mbAdmin.setImgsInfografias(form.getListaImgsInfografias());
		
		
		
		//se recorren las areas asignadas al punto para evaluar si hay responsabilidad conjunta
		for (DTOSeguimientosCG responsPunto : responsableDelPunto) {
			if(responsPunto.getResponsabilidadConjunta() >=1){
				responsConjunta = true;
				break;
			}
		}
		
		if(responsConjunta){//si hay responsabilidad conjunta se agrupan 
			Integer contadorResponsabilidad = -1;
			for (DTOSeguimientosCG responsPunto : responsableDelPunto) {
				if(responsPunto.getResponsabilidadConjunta() == 0){//si es 0 no es de responsabilidad conjunta
					for(DTOCAreas a: areas){
						if(a.getId().getIdArea().equals(responsPunto.getId().getIdArea())){
							respSuelta.add( a.getDescripcion() );
							break;
						}
					}
				}
				
				if(responsPunto.getResponsabilidadConjunta() >=1){//si es mayor a 1 es responsabilidad conjunta y el numero indica su grupo
					if(!responsPunto.getResponsabilidadConjunta().equals(contadorResponsabilidad)){//si es diferente es un nuevo grupo
						contadorResponsabilidad = responsPunto.getResponsabilidadConjunta();
						respConjunta.add("Responsabilidad Conjunta. "+contadorResponsabilidad );//se asigna cabecera de numero de responsabilidad
						
						for(DTOCAreas a: areas){
							if(a.getId().getIdArea().equals(responsPunto.getId().getIdArea())){
								respConjunta.add( a.getDescripcion() );
								break;
							}
						}
					}else{//si no es un nuevo grupo de responsabilidad solo se asigna a lo ultimo ya que el padre fue asignado en alguna iteracion anterior
						for(DTOCAreas a: areas){
							if(a.getId().getIdArea().equals(responsPunto.getId().getIdArea())){
								respConjunta.add( a.getDescripcion() );
								break;
							}
						}
					}				
				}
			}
			
		}
		
		
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
	
	public String actualizarPunto() {
		form.getPunto().setTextoPunto(reemplazaComillasEstilizadas(form.getPunto().getTextoPunto()));
		if(seleccionados.size() == 0){
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,"Debe seleccionar al menos un responsable");	
			return"";
		}
		else{		
			// agregamos las rutas del archivo adjuntos al punto a actualizar
			if(form.isOficioAdjunto()){
				
				form.getPunto().setUrlArchivoAdj( form.getRutaOficioNotificacionFile() + File.separator + form.getRenameOficioNotificacionFile() );	
			}
			if(responsConjunta){
				if(fueElimSeguim){//si fue eliminado el seguimiento se elimina hasta este momento ya que si eliminan el seguimiento y dan cancelar se pierde el seguimiento
				bsdModPuntos.eliminarSeguimiento(responsablesTodoSeg);
				}
				bsdModPuntos.actualizarPunto(form, responsablesAux, form.getClasificaciones(), acuerdo, seleccionados,areas, respConjunta, respSuelta);
			}else{
				if(fueElimSeguim){
					bsdModPuntos.eliminarSeguimiento(responsablesTodoSeg);
					}
				bsdModPuntos.actualizarPunto(form, responsablesAux, form.getClasificaciones(), acuerdo, seleccionados,areas);
					
			}
			puntosPrueba = "modPunto";
			
			return "irLista";
		}
		
	}

	public String obtenerNumeraliaCardinal() {	
		this.form.setNumOrdinal(bsdModPuntos.obtenerNumeraliCardinal(
				this.numeraliaInicial,
				this.form.getPkPunto().getIdNumAcuerdo(), this.form
						.getPkPunto().getNumeralia()));		
		return form.getNumOrdinal();
	}

	public void limpiarInputsSanciones() {

		if (!bsdModPuntos.obtenerSiPPN(form)) {
			this.form.setEsPPN(false);
			this.form.setEsFechSesion(true);
		}

	}
	
	public void agregaAdjuntos(ActionEvent actionEvent) {
		
		try {
			
			// si hay un PDF del oficio de notificacion que se haya adjuntado, lo guardamos en el gluster
			if (  form.getOficioNotificacionFile() != null && !form.getPkPunto().getIdNumAcuerdo().equals("") && !form.getPkPunto().getNumeralia().equals(new Integer(0)) ) {
				
				boolean actualizaEnGluster = false;
				
				//primero si ya tenia un archivo asociado en el gluster lo eliminamos para luego escribir en gluster el nuevo adjunto
				if( form.getPunto().getUrlArchivoAdj() != null ){
					actualizaEnGluster = boArchivos.eliminaArchivoEnGluster(  form.getPunto().getUrlArchivoAdj() );
				}
				else
					actualizaEnGluster = true;
				
				// guardamos el nuevo acuerdo adjunto
				if (actualizaEnGluster){
					// guardamos el acuerdo adjunto
					form.setRutaOficioNotificacionFile( boArchivos.getRutaBaseGluster() );
					form.setRutaOficioNotificacionFile( boArchivos.getSufijoRutaGluster( moduloArchivo.PUNTOS_ARCHIVO , form.getRutaOficioNotificacionFile()));
					
					form.setRenameOficioNotificacionFile( boArchivos.getSufijoNombreOficioNotificacionFile(tipoArchivo.OFICIONOTIFICACIONPUNTO, form.getPkPunto().getIdNumAcuerdo(), form.getPkPunto().getNumeralia(), form.getOficioNotificacionFile().getFileName()  ) );
							
					
					form.setOficioAdjunto(
												boArchivos.almacenarArchivoEnGluster( form.getOficioNotificacionFile() , form.getRutaOficioNotificacionFile() , form.getRenameOficioNotificacionFile())
										  );
				}
				else{					
					log.error("MBModificarPuntos.agregaAdjuntos : No se pudo borrar el archivo adjunto original");
				}

			}

		} catch (Exception e) {			
			log.error("MBModificarPuntos.agregaAdjuntos :"+ e.getMessage());		
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
			log.error("MBModificarPuntos.agregaOficioNotificacionAdjunto :"+ e.getMessage());			
		}
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
	
	public void rowSelected(SelectEvent event){			
		seleccionados.add((DTOCAreas) event.getObject());		
		seleccionados.remove(seleccionados.size()-1);
			responsables = bsdModPuntos.obtenerResponsablesArea(seleccionados);
		//se guardan en un auxiliar por que al seleccionar una fila se eliminan los originales (defecto de primefaces)	
		seleccionadosAux = seleccionados;
		responsablesAux = responsables;

		form.setArbolRespons( bsdModPuntos.construirArbolRespons(seleccionados, responsables) );
	}
	
	public boolean getClasificacionValida() {
		return bsdModPuntos.obtenerSiPPN(form);
	}
	
	public boolean esResponsableArea(int a, int b){
		if (a == b){
			return true;
		}
		return false;
	}
		
	public void rowUnselected(UnselectEvent event){		
		seleccionados.remove((DTOCAreas) event.getObject());
		responsables = bsdModPuntos.obtenerResponsablesArea(seleccionados);
			
			seleccionadosAux = seleccionados;
			responsablesAux = responsables;

		form.setArbolRespons( bsdModPuntos.construirArbolRespons(seleccionados, responsables) );
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
			responsables = bsdModPuntos.obtenerResponsablesArea(seleccionados);

		form.setArbolRespons( bsdModPuntos.construirArbolRespons(seleccionados, responsables) );
	}
	
	public void modResponsabilidad(){
		responsConjunta = false;
		nuevaResponsConjunta = false;
		respSuelta.clear();
		respConjunta.clear();
		contadorResponsabilidad = 0;
		selecAuxSource = new ArrayList<String>();
		selecAuxTarget = new ArrayList<String>();
		seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
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
	
	public void responsabilidadNueva(){
		
		if(nuevaResponsConjunta){
			
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
			responsConjunta = true;
		}else{
			contadorResponsabilidad = 0;
			selecAuxSource = new ArrayList<String>();
			selecAuxTarget = new ArrayList<String>();
			seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
			
			respConjunta.clear();
			
			responsConjunta = true;
		}
	}
	
	public void handleTransfer(TransferEvent event) {
		event.getItems(); // List of items transferred
		event.isAdd(); // Is transfer from source to target
		event.isRemove(); // Is transfer from target to source
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
	
	public void guardaResponsabilidadConjunta(){
	
		if(seleccionables.getTarget().size() <= 1){
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					"Selecciona al menos 2 áreas para asignar Responsabilidad Conjunta");
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
	public void eliminarSeguimiento(){
		responsConjunta = false;
		nuevaResponsConjunta = false;
		respSuelta.clear();
		respConjunta.clear();
		contadorResponsabilidad = 0;
		selecAuxSource = new ArrayList<String>();
		selecAuxTarget = new ArrayList<String>();
		seleccionables = new DualListModel<String>(selecAuxSource, selecAuxTarget);
		fueElimSeguim = true;
	//	bsdModPuntos.eliminarSeguimiento(responsablesTodoSeg);
	}

	public HLPFormPuntos getForm() {
		return this.form;
	}

	public void setForm(HLPFormPuntos form) {
		this.form = form;
	}

	public BSDModificarPuntosInterface getBsdRegPuntos() {
		return this.bsdModPuntos;
	}

	public void setBsdRegPuntos(BSDModificarPuntosInterface bsdRegPuntos) {
		this.bsdModPuntos = bsdRegPuntos;
	}

	public String getPuntosPrueba() {
		return puntosPrueba;
	}

	public void setPuntosPrueba(String puntosPrueba) {
		this.puntosPrueba = puntosPrueba;
	}

	public List<DTOCAreas> getAreas() {
		return areas;
	}

	public void setAreas(List<DTOCAreas> areas) {
		this.areas = areas;
	}

	public List<DTOSeguimientosCG> getResponsableDelPunto() {
		return responsableDelPunto;
	}

	public void setResponsableDelPunto(List<DTOSeguimientosCG> responsableDelPunto) {
		this.responsableDelPunto = responsableDelPunto;
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

	public boolean getResponsConjunta() {
		return responsConjunta;
	}

	public void setResponsConjunta(boolean responsConjunta) {
		this.responsConjunta = responsConjunta;
	}

	public List<String> getRespSuelta() {
		return respSuelta;
	}

	public void setRespSuelta(List<String> respSuelta) {
		this.respSuelta = respSuelta;
	}

	public List<String> getRespConjunta() {
		return respConjunta;
	}

	public void setRespConjunta(List<String> respConjunta) {
		this.respConjunta = respConjunta;
	}

	public List<String> getSelecAuxSource() {
		return selecAuxSource;
	}

	public void setSelecAuxSource(List<String> selecAuxSource) {
		this.selecAuxSource = selecAuxSource;
	}

	public List<String> getSelecAuxTarget() {
		return selecAuxTarget;
	}

	public void setSelecAuxTarget(List<String> selecAuxTarget) {
		this.selecAuxTarget = selecAuxTarget;
	}

	public DualListModel<String> getSeleccionables() {
		return seleccionables;
	}

	public void setSeleccionables(DualListModel<String> seleccionables) {
		this.seleccionables = seleccionables;
	}

	public boolean getNuevaResponsConjunta() {
		return nuevaResponsConjunta;
	}

	public void setNuevaResponsConjunta(boolean nuevaResponsConjunta) {
		this.nuevaResponsConjunta = nuevaResponsConjunta;
	}
}
