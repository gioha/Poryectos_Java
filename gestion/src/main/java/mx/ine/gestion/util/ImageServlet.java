package mx.ine.gestion.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

/**
 * The Image servlet for serving from absolute path.
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/04/imageservlet.html
 */
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 805178628955823588L;

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(ImageServlet.class);
    // Constants ----------------------------------------------------------------------------------


	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	// Properties ---------------------------------------------------------------------------------

    @SuppressWarnings("unused")
	private String imagePath;
    // Actions ------------------------------------------------------------------------------------

    public void init() throws ServletException {
        this.imagePath = "";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Get requested image by path info.
        String requestedImage = request.getPathInfo();

       log.info("requestedImage : " + requestedImage);
        
        // Check if file name is actually supplied to the request URI.
        if (requestedImage == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = new File(requestedImage);
        
        log.info("image image: " + image );
        
//		  LOGS PARA PRUEBAS, DESCOMENTAR CUANDO SE NECESITE        
//        log.error("<================================= IMAGENNNNNNNNNNNNNNNNNNNNNNNNNNNNN" );
//        String[] cadena = requestedImage.split("/");
//        
//        for (int i = 0; i < cadena.length; i++) {
//            
//        	log.error("QUEDO ASÍ, CARPETA" + i + " :" + cadena[i] );
//        }
//        
//        String ruta = "";
//        
//        for (int i = 1; i < cadena.length; i++) {
//        	
//        	ruta = ruta + "/" + cadena[i];
//        	
//        	File carpetas = new File(ruta);
//        	
//        	log.error("CARPETA2: " + ruta);
//        	log.error("EXISTE2?: " + carpetas.exists() );
//        }
        
        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            log.info("Error, la imagen no existe");
            return;
        }

        // Get content type by filename.
        String contentType = getServletContext().getMimeType(image.getName());
        
        log.error("CONTENTYPE, ¿CUAL ES?: " + contentType);

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            log.info("Error, tipo de imagen no valida");
            return;
        }

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open streams.
            input = new BufferedInputStream(new FileInputStream(image), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }
    }

    // Helpers (can be refactored to public utility class) ----------------------------------------

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }

}