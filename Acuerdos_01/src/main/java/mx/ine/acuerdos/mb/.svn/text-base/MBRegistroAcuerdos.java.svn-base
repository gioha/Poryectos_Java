package mx.ine.acuerdos.mb;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroAcuerdosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.helper.form.HLPFormRegistroAcuerdos;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Esta clase provee la funcionalidad para cargar todos los parametros, listas
 * y constantes que se usaran de manera generica en registro acuerdos
 * @author José Hurtado
 * @update Miguel Ortiz
 * @since 26/02/2018
 **/
public class MBRegistroAcuerdos extends MBGeneric implements Serializable {
	private static final long serialVersionUID = -4921082723351999531L;
	private static final Log log = LogFactory.getLog(MBRegistroAcuerdos.class);

	// Variables
	private HLPFormRegistroAcuerdos form;
	private boolean rolValido;

	@Autowired
	@Qualifier("bsdRegAc")
	private transient BSDRegistroAcuerdosInterface bsdRegAc;

	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;

	@Autowired
	@Qualifier("mbAdmin")
	private  MBAdministradorSistema mbAdmin;
	
	/**
	 * Método que incializa los elementos de la pantalla
	 * @update Miguel Ortiz
	 * @param idNegocio 
	 * @return void
	 * @since 26/02/2018
	 **/
	public void init(Integer idNegocio, Integer idTipoDocumento) {
		try {
			this.rolValido = false;

			form = new HLPFormRegistroAcuerdos();
			form.setTiposSesiones( bsdRegAc.recuperaTiposSesiones() );
			form.setTiposDocumentos(bsdRegAc.recuperaTiposDocumentos());
			form.setLblIniciales(form.getTiposDocumentos().get(0).getIdTipoDocumento());

			// Lista de nombres de imagenes de inforgrafias
			form.setListaImgsInfografias(bsdRegAc.recuperaImgsInfografias());
			mbAdmin.setImgsInfografias(form.getListaImgsInfografias());

			form.setNomArchExt(" ");
		} catch (Exception e) {
			log.error("MBRegistroAcuerdos - init() - Ocurrio un error: " + e.getMessage());
		}
	}

	/**
	 * Método para verificar la existencia del Acuerdo
	 * @param String
	 * @return void
	 * **/
	public void existeAc(String idNum){
		try{
		DTOAcuerdos dtoAcuerdos = this.bsdRegAc.obtenerAcuerdo(idNum);
		bsdRegAc.existeAc(dtoAcuerdos, form);
		
		} catch (Exception e) {
			log.error( " MBRegistroAcuerdos - existeAc() - Ocurrio un error: " + e.getMessage()  );

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
	
	/**
	 * Método para guardar un Acuerdo
	 * @param String
	 * @return String
	 * **/
	public String guardaAcuerdo(String idNum){
		try{
		DTOAcuerdos dtoAcuerdos = this.bsdRegAc.obtenerAcuerdo(idNum);	
		form.getAcuerdo().setNombre(reemplazaComillasEstilizadas(form.getAcuerdo().getNombre()));// se manda a reemplazar las comillas estilizadas
		return (bsdRegAc.guardaAcuerdo(dtoAcuerdos, form));
		} catch (Exception e) {
			log.error( " MBRegistroAcuerdos - guardaAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
		
	}
	
	/**
	 * Método para agregar adjuntos
	 * @param ActionEvent
	 * @return void
	 * **/
	public void agregaAdjuntos(ActionEvent actionEvent) {
		try {
			
			// si hay un PDF del acuerdo que se haya adjuntado, lo guardamos en el gluster
			if (  form.getAcuerdoFile() != null && !form.getAcuerdo().getIdNumAcuerdo().equals("") ) {
				
				// guardamos el acuerdo adjunto
				
				form.setRutaAcuerdoFile( boArchivos.getRutaBaseGluster() );
				form.setRutaAcuerdoFile( boArchivos.getSufijoRutaGluster( moduloArchivo.ACUERDOS_ARCHIVO , form.getRutaAcuerdoFile()));
				
				form.setRenameAcuerdoFile( boArchivos.getSufijoNombreAcuerdoEngroseFile(tipoArchivo.ACUERDOS, form.getAcuerdo().getIdNumAcuerdo(), form.getAcuerdoFile().getFileName()) ) ;
				
				form.setAcuerdoAdjunto(
											boArchivos.almacenarArchivoEnGluster( form.getAcuerdoFile() , form.getRutaAcuerdoFile() , form.getRenameAcuerdoFile())
									  );

			}
			
			
			// si hay un PDF del engrose que se haya adjuntado, lo guardamos en el gluster
			if (  form.getEngroseFile() != null && !form.getAcuerdo().getIdNumAcuerdo().equals("")  ) {
				
				// guardamos el engrose adjunto
				
				form.setRutaEngroseFile( boArchivos.getRutaBaseGluster() );
				form.setRutaEngroseFile( boArchivos.getSufijoRutaGluster( moduloArchivo.ACUERDOS_ARCHIVO , form.getRutaEngroseFile()));
				
				form.setRenameEngroseFile( boArchivos.getSufijoNombreAcuerdoEngroseFile(tipoArchivo.ENGROSE, form.getAcuerdo().getIdNumAcuerdo(), form.getEngroseFile().getFileName()) ) ;
				
				form.setEngroseAdjunto(
										boArchivos.almacenarArchivoEnGluster( form.getEngroseFile() , form.getRutaEngroseFile() , form.getRenameEngroseFile())
									  );
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			log.error( " MBRegistroAcuerdos - agregaAdjuntos() - Ocurrio un error: " + e.getMessage()  );
		}

	}
	
	/**
	 * Método para subir Acuerdo adjunto
	 * @param FileUploadEvent
	 * @return void
	 * **/
	public void agregaAcuerdoAdjunto(FileUploadEvent event) {
		try {			
			
			if(event.getFile().getSize() < 419430400){
				//form.setAcuerdoFile( event.getFile() ) ;
				form.setNomArchExt("");
			     UploadedFile file = event.getFile();

        	     if(file.getFileName().substring(file.getFileName().length()-4, file.getFileName().length()).toUpperCase().equals(".PDF")){
    			     form.setAcuerdoFile( file ) ;
            	     form.setNomArch("Archivo precargado: " + file.getFileName());
        	    	 form.setNomArchExt(file.getFileName().substring(file.getFileName().length()-3, file.getFileName().length()).toUpperCase());
        	     }else{
    			     form.setAcuerdoFile( null ) ;
            	     form.setNomArch("");
        	    	 form.setNomArchExt("");
        	     }
        	     form.setTamNoPermitido(false);
			}else{
				form.setTamNoPermitido(true);
			}     
				
		}
		catch(Exception e){
			log.error( " MBRegistroAcuerdos - agregaAcuerdoAdjunto() - Ocurrio un error: " + e.getMessage()  );
		}
	}
	
	/**
	 * Método para subir engrose adjunto
	 * @param FileUploadEvent
	 * @return void
	 * **/
	public void agregaEngroseAdjunto(FileUploadEvent event) {
		try {			
				//form.setEngroseFile( event.getFile() ) ;
				
			     UploadedFile file = event.getFile();
			     form.setEngroseFile( file ) ;
       	      	 form.setNomArchEn("Archivo precargado: " + file.getFileName());
				
				
		}
		catch(Exception e){
			log.error( " MBRegistroAcuerdos - agregaEngroseAdjunto() - Ocurrio un error: " + e.getMessage()  );
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
	 * Metodo que recupera el tipo de etiuetas a mostrar, Acuerdo o Resolucion
	 * @param void 
	 * @return void
	 * **/	
	public void mostratLbls(){
		if(form.getAcuerdo().getIdTipoDocumento()==1){
			form.setLblIniciales(1);
		}
		if(form.getAcuerdo().getIdTipoDocumento()==6){
			form.setLblIniciales(6);
		}
	}	
	
	/**
	 * Metodo para recuperar el Número de Acuerdo
	 * @param void
	 * @return void
	 * **/	
	public void pasarVal(){
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form_registro_acuerdo:numeroAcuerdo");
			form.getAcuerdo().setIdNumAcuerdo(value);
	}

	/**
	 * Metodo para validar URL
	 * @param void
	 * @return void
	 * **/	
	public void validaURL(){
		
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form_registro_acuerdo:ligaAcuerdo").trim();
		
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
	
	public HLPFormRegistroAcuerdos getForm() {
		return form;
	}

	public void setForm(HLPFormRegistroAcuerdos form) {
		this.form = form;
	}

	public BSDRegistroAcuerdosInterface getBsdRegAc() {
		return bsdRegAc;
	}

	public void setBsdRegAc(BSDRegistroAcuerdosInterface bsdRegAc) {
		this.bsdRegAc = bsdRegAc;
	}

		


	
}
