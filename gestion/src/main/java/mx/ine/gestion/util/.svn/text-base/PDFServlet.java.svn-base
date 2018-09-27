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
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The Image servlet for serving from absolute path.
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/04/imageservlet.html
 */
@WebServlet("/filePDF/*")
public class PDFServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(PDFServlet.class);
    // Constants ----------------------------------------------------------------------------------


	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	// Properties ---------------------------------------------------------------------------------

    @SuppressWarnings("unused")
	private String pdfPath;
    // Actions ------------------------------------------------------------------------------------

    public void init() throws ServletException {
        this.pdfPath = "";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Get requested image by path info.
        String requestedPDF = request.getPathInfo();

       log.info("requestedPDF : " + requestedPDF);
        
        // Check if file name is actually supplied to the request URI.
        if (requestedPDF == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            log.error("Error, el request del PDF viene nulo");
            log.error("Ruta del documento con error:" + requestedPDF);
            
            return;
        }

        // Decode the file name (might contain spaces and on) and prepare file object.
        File pdf = new File(requestedPDF);

        log.info("PDF Ruta PDF: " + pdf );
        
        
        // Check if file actually exists in filesystem.
        if (!pdf.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.

            log.error("Ruta del documento con error:" + requestedPDF);
            log.error("<=================== Error al intentar cargar el PDF. El documento "+requestedPDF+" no existe");
			log.error("<=================== Clase: PDFServlet , Método: doGet");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
            
            return;
        }
        
        // Get content type by filename.
        String contentType = "application/pdf";

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(pdf.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + pdf.getName() + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open streams.
            input = new BufferedInputStream(new FileInputStream(pdf), DEFAULT_BUFFER_SIZE);
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