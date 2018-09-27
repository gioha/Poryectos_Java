package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDEjemploInterface;
import mx.ine.acuerdos.dto.helper.form.HLPFormEjemplo;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * <code>MBCapturaVotoDiputadoRP.java</code>Descripcion de la clase
 *
 * @author Giovanni Hernandez
 * @version 1.0
 * @since 07/09/2017 
 */

public class MBEjemploTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2953381655970480267L;
	
	
	// Variables
	
	private HLPFormEjemplo form; 
	
	@Autowired
	@Qualifier("bsdEjemplo")
	private BSDEjemploInterface bsdEjemplo;
	
	// carga archivo
	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	
	// carga archivo
	private UploadedFile file; // acuerdo
	
	
	// metodo que incializa los elementos de la pantalla ejemplo.xhtml
	public void init(){
		
		//System.out.println("Entramos al metodo Init!!!!!!!!!!!!!!!");
		
		form = new HLPFormEjemplo();
		
		form.getListaDatos().add("Jose");
		form.getListaDatos().add("Jair");
		form.getListaDatos().add("Omar");
		form.getListaDatos().add("Sampier");
		
		bsdEjemplo.dondeEstas();
		bsdEjemplo.esPar(form);

		
		String ruta = "";
		
		// carga archivo
		ruta = boArchivos.getRutaBaseGluster();
		ruta = boArchivos.getSufijoRutaGluster( moduloArchivo.ACUERDOS_ARCHIVO , ruta);
		
	
	}

	public void saluda(){
		
		//System.out.println("Hola desde el MB!!!!!!!!!!!!!!!");
	}
	
	// carga archivo
	public void capturarAcuerdo(FileUploadEvent event) {
//		try {
//			System.out.println("Subiendo archivo a memoria");
//			
//			file = event.getFile();
//			
//			imagenURL = map.get("url") + file.getFileName();  //ruta de gluster +  "/Sanciones/constancias/" + "nombreArchivo"
//			sancionSeleccionada.setUrlConstancia(imagenURL);  //Agregamos la url de la constancia al path
//			crearNombreCortoConstancia();   //JBJ 31/10
//			imagen = file.getFileName();
//		
//		}catch(Exception e){
//			System.out.println("Error en MBSeguimientos --> subirArchivoTemporal() " + e.getMessage());
//		}
	}
	
	
	public boolean esElRol(){
		return true;
	}

	public HLPFormEjemplo getForm() {
		return form;
	}

	public void setForm(HLPFormEjemplo form) {
		this.form = form;
	}

	public BSDEjemploInterface getBsdEjemplo() {
		return bsdEjemplo;
	}

	public void setBsdEjemplo(BSDEjemploInterface bsdEjemplo) {
		this.bsdEjemplo = bsdEjemplo;
	}
	

}
