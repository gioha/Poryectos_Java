package mx.ine.computosINE.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * <code>UtilUpload</code> Clase para manejo de archivos y Gluster
 *
 * @author Clemencia Cuellar Martinez
 * @version 0.0.1
 * @since Agosto 24, 2016
 */
public class UtilUpload implements Serializable {

	private static final long serialVersionUID = -7411502426035239912L;

	private static final Logger logger = Logger.getLogger(UtilUpload.class);

	private static Context  context = null;
	private static StreamedContent imagen;
	
	//constantes que definen la imagen por defecto que se mostrará si no fué posible cachar una imagen del gluster, ws o base de datos
	public static final int IMAGEN_TIPO_GENERICA = 1;
	public static final int IMAGEN_TIPO_CANDIDATO_INDEPENDIENTE = 2;
	
	private static String URL_IMG_GENERICA_CI 		= File.separator + "Ord" + File.separator + "emblemasPartidos" + File.separator + "EMBLEMA_AVE_ESTADO_30.jpg";

	public static boolean isWindows(String OS) {
		return (OS.indexOf("win") >= 0);
	}

	/**
	 * Obtiene la ruta del gluster
	 * @return
	 */
	public static String getGluster() {

    	String path = null;
    	String OS = System.getProperty("os.name").toLowerCase();
    	try {
    		context   = new InitialContext ();
    		if ( isWindows(OS) ) {
    			//path = "C:/GlusterFS";
    			path = (String) context.lookup("java:/util/glusterFS");
    			//logger.info("UtilUpload.getGluster, es Windows: " + path);
    		} else {
    			path = (String) context.lookup("util/glusterFS");
        		//logger.info("UtilUpload.getGluster, no es Windows: " + path);
    		}

	    	if ( path != null && path.trim().length() > 0 ) {
	    		return path.trim();
	    	}
	    	return null;
    	} catch (Exception e) {
    		logger.error("UtilUpload.getGluster, Error: " + e);
    		return null;
    	}
	}

	/**
	 * Convierte una imagen en un arreglo de byte
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static byte [] getImgArrayByte(String path) throws IOException {

		if ( path != null && !path.isEmpty()) {
			File f = new File(path);
			if ( f.exists() && f.length() > 0 ) {
				FileInputStream fis = new FileInputStream(f);
				byte contenido[] = new byte[(int)f.length()];
				fis.read(contenido);
				//logger.info("UtilUpload.getImgArrayByte, : " + contenido.length );
				fis.close();
				return contenido;
			}
		}
		return null;
	}

	/**
	 * Convierte un arreglo de byte a imagen, y la guarda e un directorio destino
	 * @param imageInByte
	 * @param nameImg
	 * @param pathDestino
	 */
	public static void writeToFile(byte [] imageInByte, String nameImg, String pathDestino) {

		// convert byte array back to BufferedImage
		InputStream in = new ByteArrayInputStream(imageInByte);
		BufferedImage bImageFromConvert;
		try {
			bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "jpg", new File(pathDestino + File.separator + nameImg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Genera imagen a partir de un arreglo byte
	 * @param imgArrayByte
	 * @return
	 */
	public static StreamedContent getImagenStreamedContent() {

		try {
			byte [] imgArrayByte;
			imgArrayByte = getImgArrayByte(getGluster() + URL_IMG_GENERICA_CI);
			imagen = new DefaultStreamedContent(new ByteArrayInputStream(imgArrayByte), "image/jpeg");
			return imagen;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Obtiene imagen en StreamedContent
	 * @param imgArrayByte
	 * @return
	 */
	public static StreamedContent getImagenStreamedContent(byte [] imgArrayByte) {

		try {
			imagen = new DefaultStreamedContent(new ByteArrayInputStream(imgArrayByte), "image/jpeg");
			return imagen;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Obtiene imagen en StreamedContent de una ruta, 
	 * si no existe una imagen con esa ruta, busca una 
	 * por default en Gluster o dentro del proyecto
	 * @param imgArrayByte
	 * @return
	 */
	public static StreamedContent getImagenStreamedContent(String rutaImagen) {
		String ruta = null;
		byte[] imagenByte = null;
		StreamedContent content = null;
		try {
			ruta = getGluster() + rutaImagen;
			imagenByte = UtilUpload.getImgArrayByte(ruta);
			if ( imagenByte == null )
				throw new Exception();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagenByte), "image/jpeg");
		} catch( Exception e1 ) {
			//logger.info("No se pudo generar la imagen con la ruta: "+ruta);
			try {
				ruta = getGluster() + "/img_generica.png";
				imagenByte = getImgArrayByte(ruta);
				if (imagenByte == null )
					throw new Exception();
				content = new DefaultStreamedContent(new ByteArrayInputStream(imagenByte), "image/jpeg");
			} catch( Exception e2 ){
				//logger.info("No se pudo generar la imagen con la ruta: "+ruta);
				try {
					ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/image/");
					ruta += "/img_generica.png";
					imagenByte = getImgArrayByte(ruta);
					if(imagenByte == null)
						throw new Exception();
					content = new DefaultStreamedContent(new ByteArrayInputStream(imagenByte), "image/jpeg");
				} catch( Exception e3 ) { 
					//logger.info("No se pudo generar la imagen con la ruta: "+ruta);
					content = new DefaultStreamedContent();
				}
			}
		}
		return content;
	}
	
	/**
	 * Obtiene una imagen en StreamedContent de un arreglo de bytes o de una ruta, 
	 * si no existe una imagen con esa ruta, busca una por default en Gluster 
	 * o dentro del proyecto
	 * Si imagenByte es nulo en automatico busca por ruta
	 * Si la ruta biene en nulo busca en gluster o dentro del sistema
	 * @param imgArrayByte
	 * @return
	 */
	public static StreamedContent getImagenStreamedContent(byte[] imagenByte,String rutaImagen) {

		StreamedContent content = null;
			try {
				if(imagenByte==null)
					throw new Exception();
				content = new DefaultStreamedContent(new ByteArrayInputStream(imagenByte), "image/jpeg");
			} catch(Exception e1) {
				logger.info("No se pudo generar la imagen con los bytes enviados, se usará la ruta por default "+rutaImagen);
				content = getImagenStreamedContent(rutaImagen);
			}
		return content;
	}

	public static String contextGetRealPath(String urlImg) {
		try {
			return FacesContext.getCurrentInstance().getExternalContext().getRealPath(urlImg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
