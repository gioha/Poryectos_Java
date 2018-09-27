package mx.ine.acuerdos.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class ServletPdf
 */
@WebServlet(description = "Servlet encargado de poner a disposición los archivos pdf para su descarga.", urlPatterns = { "/pdf/*" })
public class ServletPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ServletPdf.class);
	
	@Resource(mappedName = "java:/util/glusterFS")
    private String rutaGluster;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String nombreArchivo, rutaArchivo, rutaAbsoluta;
		Path path;
		try {
			String archivoSolicitado = request.getPathInfo().substring(1);
			
			if (archivoSolicitado == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
				return;
			}
			
			archivoSolicitado = rutaGluster + File.separator + archivoSolicitado;
			path = Paths.get(archivoSolicitado);
			nombreArchivo = path.getFileName().toString();
			rutaAbsoluta = path.toAbsolutePath().toString();
			rutaArchivo = rutaAbsoluta.substring(0, rutaAbsoluta.lastIndexOf(File.separator));
			
			File file = new File(rutaArchivo, nombreArchivo);
			
			if (!file.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                return;
			}
			
			String contentType = getServletContext().getMimeType(file.getName());
			
			if (contentType == null || !contentType.startsWith("application")) {
                // Sí el contentType no es un pdf: application/pdf envia un 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
			}
			
			response.reset();
            response.setContentType(contentType);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            
            Files.copy(file.toPath(), response.getOutputStream());
		} catch (IOException e) {
			log.error("[Error] ServletPdf", e);
		}
	}

}
