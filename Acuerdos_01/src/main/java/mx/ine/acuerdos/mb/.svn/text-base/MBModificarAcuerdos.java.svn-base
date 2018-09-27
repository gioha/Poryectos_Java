package mx.ine.acuerdos.mb;

import java.io.File;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDModificarAcuerdosInterface;
import mx.ine.acuerdos.dto.helper.form.HLPFormModificarAcuerdos;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBModificarAcuerdos extends MBGeneric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821641741260329241L;
	
	private static final Log log = LogFactory.getLog(MBModificarAcuerdos.class);
	

	// Método que incializa los elementos de la pantalla .xhtml
	@Autowired
	@Qualifier("bsdModAc")
	private transient BSDModificarAcuerdosInterface bsdModAc;
	
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	//Variables
	private HLPFormModificarAcuerdos form;
	//cadena para flujo exitoso
	private String acuerdosPrueba;
	
	private boolean rolValido;
	
	//metodo de inicio
	public void init(String idAcuerdo){
		try{
			this.rolValido = false;
			this.form = new HLPFormModificarAcuerdos();
			this.form.setTiposSesiones( this.bsdModAc.recuperaTiposSesiones());
			this.form.setAcuerdo(this.bsdModAc.obtenerAcuerdo(idAcuerdo));
			
//			if(form.getAcuerdo().getNumOficioEngrose()!=null){
//				form.setHayEngrose(true);
//				
//				if(form.getAcuerdo().getEngroseAdjunto()!=null){
//				form.setNomArchEn(form.getAcuerdo().getEngroseAdjunto());
//				form.setNomArchEn(bsdModAc.extraerNomArchivo(form.getNomArchEn()));
//				}
//				
//			}
			
			//muestra el archivo adjunto
			if (form.getAcuerdo().getAcuerdoAdjunto()!=null){
				form.setNomArch(form.getAcuerdo().getAcuerdoAdjunto());
				form.setNomArch(boArchivos.getNombreArchivo(form.getNomArch()));
				form.setRutaPrevisualizacion(boArchivos.getRutaPrevisualizacion(form.getAcuerdo().getAcuerdoAdjunto()));
			}
			
			//selecciona control check en caso de tener engose
			bsdModAc.seleccionarConEngrose(form);
			
			//Muestra si es acuerdo o resolucion
			bsdModAc.mostrarTipoDocumento(form);
			
			//establece etuiquetas a mostrar
			form.setLblIniciales(form.getAcuerdo().getIdTipoDocumento());
			
			//lista de nombres de imagenes de inforgrafias
			form.setListaImgsInfografias(bsdModAc.recuperaImgsInfografias());
			//System.out.println(form.getListaImgsInfografias().toString());
			mbAdmin.setImgsInfografias(form.getListaImgsInfografias());

			form.setNomArchExt(" ");
			
		} catch (Exception e) {
			log.error( " MBModificarAcuerdos - init() - Ocurrio un error: " + e.getMessage()  );
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
	
	public String modificarAcuerdo() {		
		try{				   
			form.getAcuerdo().setNombre(reemplazaComillasEstilizadas(form.getAcuerdo().getNombre()));// se manda a reemplazar las comillas estilizadas
			// agregamos las rutas de los archivos adjuntos al acuerdo a actualizar
			if( form.isAcuerdoAdjunto() ){
				
				form.getAcuerdo().setAcuerdoAdjunto( form.getRutaAcuerdoFile() + File.separator + form.getRenameAcuerdoFile() );
			}
			
			if ( form.isEngroseAdjunto() ){
				
				form.getAcuerdo().setEngroseAdjunto( form.getRutaEngroseFile() + File.separator + form.getRenameEngroseFile() );
			}
//			bsdModAc.actualizarAcuerdo(form.getAcuerdo());
//			acuerdosPrueba = "modAcuerdo";
//			return "irLista";
			
			
			if (bsdModAc.actualizarAcuerdo(form.getAcuerdo()) == Constantes.REGISTRO_RG){
				//form.setAcuerdosPrueba("modAcuerdo");
				
				if(form.getAcuerdo().getIdTipoDocumento()==1)
				    form.setAcuerdosPrueba("modAcuerdo");
				
				if(form.getAcuerdo().getIdTipoDocumento()==6)
					form.setAcuerdosPrueba("modResolucion");
				
				
				
				form.setNoRegistroRG(false);
				return Constantes.IRLISTA;
			}else{
				form.setNoRegistroRG(true);
				return null;
			}
			
		} catch (Exception e) {
				log.error( " MBModificarAcuerdos - modificarAcuerdo() - Ocurrio un error: " + e.getMessage()  );
				return null;
	   }
		
	}
	
	//Método para agregar los adjuntos actualizados
	public void agregaAdjuntos(ActionEvent actionEvent) {
		try {
			
			// si hay un PDF del acuerdo que se haya adjuntado, lo guardamos en el gluster
			if (  form.getAcuerdoFile() != null && !form.getAcuerdo().getIdNumAcuerdo().equals("") ) {
				
				boolean actualizaEnGluster = false;
				
				//primero si ya tenia un archivo asociado en el gluster lo eliminamos para luego escribir en gluster el nuevo adjunto
				if( form.getAcuerdo().getAcuerdoAdjunto() != null ){
					actualizaEnGluster = boArchivos.eliminaArchivoEnGluster(  form.getAcuerdo().getAcuerdoAdjunto() );
				}
				else
					actualizaEnGluster = true;
				
				// guardamos el nuevo acuerdo adjunto
				if (actualizaEnGluster){
					form.setRutaAcuerdoFile( boArchivos.getRutaBaseGluster() );
					form.setRutaAcuerdoFile( boArchivos.getSufijoRutaGluster( moduloArchivo.ACUERDOS_ARCHIVO , form.getRutaAcuerdoFile()));
					
					form.setRenameAcuerdoFile( boArchivos.getSufijoNombreAcuerdoEngroseFile(tipoArchivo.ACUERDOS, form.getAcuerdo().getIdNumAcuerdo(), form.getAcuerdoFile().getFileName()) ) ;
					
					form.setAcuerdoAdjunto(
												boArchivos.almacenarArchivoEnGluster( form.getAcuerdoFile() , form.getRutaAcuerdoFile() , form.getRenameAcuerdoFile())
										  );
				}
				else{
					log.error( " MBModificarAcuerdos - agregaAdjuntos() - No se pudo borrar el archivo adjunto original " );
				}
			}
			
			
			// si hay un PDF del engrose que se haya adjuntado, lo guardamos en el gluster
			if (  form.getEngroseFile() != null && !form.getAcuerdo().getIdNumAcuerdo().equals("")  ) {
				
				boolean actualizaEnGluster = false;
				
				//primero si ya tenia un archivo asociado en el gluster lo eliminamos para luego escribir en gluster el nuevo adjunto
				if( form.getAcuerdo().getEngroseAdjunto() != null ){
					actualizaEnGluster = boArchivos.eliminaArchivoEnGluster(  form.getAcuerdo().getEngroseAdjunto() );
				}
				else
					actualizaEnGluster = true;
				
				// guardamos el nuevo engrose adjunto
				if (actualizaEnGluster){
					form.setRutaEngroseFile( boArchivos.getRutaBaseGluster() );
					form.setRutaEngroseFile( boArchivos.getSufijoRutaGluster( moduloArchivo.ACUERDOS_ARCHIVO , form.getRutaEngroseFile()));
					
					form.setRenameEngroseFile( boArchivos.getSufijoNombreAcuerdoEngroseFile(tipoArchivo.ENGROSE, form.getAcuerdo().getIdNumAcuerdo(), form.getEngroseFile().getFileName()) ) ;
					
					form.setEngroseAdjunto(
											boArchivos.almacenarArchivoEnGluster( form.getEngroseFile() , form.getRutaEngroseFile() , form.getRenameEngroseFile())
										  );
				}
				else{
					log.error( " MBModificarAcuerdos - agregaAdjuntos() - No se pudo borrar el archivo adjunto original");
				}
			}
			

		} catch (Exception e) {
			log.error( " MBModificarAcuerdos - agregaAdjuntos() - Ocurrio un error: " + e.getMessage()  );
		}

	}
	
	
	//Método para subir Acuerdo adjunto
	public void agregaAcuerdoAdjunto(FileUploadEvent event) {
		try {
			if (event.getFile().getSize() < 419430400) {
				// form.setAcuerdoFile( event.getFile() ) ;
				form.setNomArchExt("");
				UploadedFile file = event.getFile();

				if (file.getFileName()
						.substring(file.getFileName().length() - 4,
								file.getFileName().length()).toUpperCase()
						.equals(".PDF")) {
					form.setAcuerdoFile(file);
					form.setNomArch("Archivo precargado: " + file.getFileName());
					form.setNomArchExt(file
							.getFileName()
							.substring(file.getFileName().length() - 3,
									file.getFileName().length()).toUpperCase());
				} else {
					form.setAcuerdoFile(null);
					form.setNomArch("");
					form.setNomArchExt("");
				}
				form.setTamNoPermitido(false);
			} else {
				form.setTamNoPermitido(true);
			}

		} catch (Exception e) {
			log.error( " MBModificarAcuerdos - agregaAcuerdoAdjunto() - Ocurrio un error: " + e.getMessage()  );
		}
	}
	
	public void agregaEngroseAdjunto(FileUploadEvent event) {
		try {			
				 UploadedFile file = event.getFile();
			     form.setEngroseFile( file ) ;
       	      	 form.setNomArchEn("Archivo precargado: " + file.getFileName());
		}
		catch(Exception e){
			log.error( " MBModificarAcuerdos - agregaEngroseAdjunto() - Ocurrio un error: " + e.getMessage()  );
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
	
	/**
	 * Metodo para validar URL
	 * @param void
	 * @return void
	 * **/	
	public void validaURL(){
		
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form_modificar_acuerdo:ligaAcuerdo").trim();
		
		if (!(value.equals("")))
			if (value.substring(value.length() - 1).equals("/")) {
				form.setLigaValida(true);
			} else {
				form.setLigaValida(false);
			}
		else {
			form.setLigaValida(false);
		}

	}
	
	
	public HLPFormModificarAcuerdos getForm() {
		return form;
	}

	public void setForm(HLPFormModificarAcuerdos form) {
		this.form = form;
	}

	public BSDModificarAcuerdosInterface getBsdModAc() {
		return bsdModAc;
	}

	public void setBsdModAc(BSDModificarAcuerdosInterface bsdModAc) {
		this.bsdModAc = bsdModAc;
	}
	
	public String getAcuerdosPrueba() {
		return acuerdosPrueba;
	}

	public void setAcuerdosPrueba(String acuerdosPrueba) {
		this.acuerdosPrueba = acuerdosPrueba;
	}
	
	
}
