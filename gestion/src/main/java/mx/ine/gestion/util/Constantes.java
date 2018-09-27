package mx.ine.gestion.util;

import java.io.File;

/**
 * @(#)Constantes.java 04/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de Utilidad que contendra constantes del sistema.
 * 
 * @author Sergio Ley Garcia
 * @since 04/09/2017
 */
public interface Constantes {

	/******************************************************** ARCHIVOS *************************************************/
	/**
	 * Ruta base a partir de la cual se almacenarán los archivos de usuario.
	 */
	public final String			PATH_UPLOADS								= "uploads" + File.separator;
	/**
	 * Ruta base a partir de la cual se almacenarán los archivos de usuario.
	 */
	public final String			PATH_DOWNLOADS								= System.getProperty("export.home.gestion") + "/downloads/";
	/*
	 * -----------------------------------------------------OCUPADOS HASTA
	 * AHORA---------------------------------------------------------
	 */
	/**
	 * Ruta relativa a partir de la cual se almacenan los archivos del usuario.
	 */
	public final String			PATH_RELATIVE_DOWNLOADS						= System.getProperty("export.relative.gestion") + "/downloads/";
	/**
	 * Ruta relativa a partir de la cual se almacenan los archivos del usuario.
	 */
	public final String			PATH_RELATIVE_UPLOADS						= System.getProperty("export.relative.gestion") + "/uploads/";
	public final String			PATH_RELATIVE_GLUSTER						= System.getProperty("export.relative.gestion");
	/**
	 * Ruta relativa donde se almaceran los archivos anexos.
	 */
	public final String			PATH_ARCHIVOS_ANEXOS_UPLOAD					= PATH_UPLOADS + "anexos/";
	/**
	 * Ruta relativa donde se almaceran los archivos anexos.
	 */
	public final String			PATH_RELATIVE_ANEXOS_UPLOAD					= PATH_RELATIVE_UPLOADS + "anexos/";
	/**
	 * Ruta absoluta donde se almacenaran los archivos que sube el usuario.
	 */
	public final String			PATH_FILES_UPLOAD							= PATH_UPLOADS + "IFE" + File.separator;
	/**
	 * Ruta relativa donde se almacenaran los archivos que sube el usuario.
	 */
	public final String			PATH_RELATIVE_FILES_UPLOAD					= PATH_RELATIVE_UPLOADS + "archivos/";
	/**
	 * Ruta absoluta donde se almacenarán los archivos que definene el cuerpo de
	 * los documentos de tipo Anexo.
	 */
	public final String			PATH_CONTENT_UPLOAD							= PATH_UPLOADS + "cuerpoAnexos/";
	/**
	 * Ruta relativa donde se almacenarán los archivos que definene el cuerpo de
	 * los documentos de tipo Anexo.
	 */
	public final String			PATH_RELATIVE_CONTENT_UPLOAD				= PATH_RELATIVE_UPLOADS + "cuerpoAnexos/";
	/**
	 * Ruta absoluta donde se almacenarán los archivos que contienen las firmas
	 * de un documento (CMS).
	 */
	public final String			PATH_SIGN_UPLOAD							= PATH_UPLOADS + "CMS/";
	/**
	 * Ruta del servidor webdav donde se almacenaran los archivos temporales del
	 * modulo de documentos
	 */
	public final String			RUTA_CARPETA_TEMPORAL_WEBDAV				= "temporalwebdav/";
	public static final String	DAVSIDJ_NOMBRE_SERVLET_DESPACHADOR			= "sidjwebdav";
	public final String			RUTA_RELATIVA_TEMPORAL_WEBDAV				= System.getProperty("export.relative.gestion") + "/temporalwebdav/";
	public final String			RUTA_RELATIVA_TEMPORAL_WEBDAV_SIDJ			= "gestion4/temporalwebdav/";
	public final String			RUTA_RELATIVA_WEBDAV_SIDJ					= "gestion4/";
	public final String			RUTA_REPOSITORIO_WEBDAV						= "modeshape-webdav/federated-repository-temporal/default/glusterTemporalwebdav/";
	public final String			RUTA_REPOSITORIO_WEBDAV_SIDJ				= System.getProperty("export.relative.gestion") + "/"
																					+ DAVSIDJ_NOMBRE_SERVLET_DESPACHADOR;
	public final String			RUTA_REPOSITORIO_WEBDAV_FINAL				= "modeshape-webdav/federated-repository/default/gluster/";
	public final String			RUTA_RELATIVA_PLANTILLA_AN					= "templates/";
	public final String			NOMBRE_PLANTILLA_AN							= "PLANTILLA_AN_2016.docx";
	public final String			EXTENSION_DOCX_ARCHIVO_WEBDAV				= ".docx";
	/**
	 * Tamaño máximo para las imagenes. 50 KB.
	 */
	public final int			MAX_SIZE_IMAGE_FILE							= 51200;
	/**
	 * Tamaño máximo para archivos planos. 4 MB.
	 */
	public final int			MAX_SIZE_PLAIN_FILE							= 4194304;
	/**
	 * Tamaño máximo para archivos 50 MG
	 */
	public final int			MAX_SIZE_FILE								= 52428800;
	/**
	 * Extensiones permitidas para las imagenes.
	 */
	public final String[]		ALLOWED_EXTENSIONS_IMAGE_FILE				= {".jpg", ".jpg", ".gif", ".png", ".png"};
	/**
	 * Extensiones permitidas para archivos planos.
	 */
	public final String[]		ALLOWED_EXTENSIONS_PLAIN_FILE				= {".txt"};
	/**
	 * Mime types permitidos para archivos planos.
	 */
	public final String[]		ALLOWED_PLAIN_FILE_MIME_TYPES				= {"text/plain"};
	/**
	 * Mime types permitidos para las imagenes.
	 */
	public final String[]		ALLOWED_IMAGE_FILE_MIME_TYPES				= {"image/jpeg", "image/pjpeg", "image/gif", "image/png", "image/x-png"};
	public String[]				EXTENSIONS_ALLOWDED_FILE					= {".doc", ".xls", ".pdf", ".txt", ".zip", ".zip", ".zip", ".ppt",
			".pps", ".docx", ".xlsx", ".pptx", ".ppsx"						};
	public String[]				EXTENSIONS_ALLOWDED_FILE_SIGNED				= {".pdf"};
	/**
	 * <code>MIME_TYPE_ALLOWDED</code> El conjunto de mime-type permitidos para
	 * ajuntar archivos al cuestionario.
	 */
	public String[]				MIME_TYPE_ALLOWDED_FILE						= {"application/msword", "application/vnd.ms-excel", "application/pdf",
			"text/plain", "application/x-zip-compressed", "application/zip", "application/octet-stream", "application/vnd.ms-powerpoint",
			"application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.openxmlformats-officedocument.presentationml.presentation",
			"application/vnd.openxmlformats-officedocument.presentationml.slideshow"};
	/**
	 * <code>MIME_TYPE_ALLOWDED</code> El conjunto de mime-type permitidos para
	 * ajuntar archivos al cuestionario.
	 */
	public String[]				MIME_TYPE_ALLOWDED_FILE_SIGNED				= {"application/pdf"};
	/**
	 * Extensiones de los archivos permitidos en el sistema sin considerar los
	 * archivos comprimidos. Archivos de word (doc y docx), excel (xls y xlsx),
	 * PowerPoint(ppt, pptx y ppsx), Adobe Reader (pdf) y de Texto (txt).
	 */
	public String[]				ALLOWED_EXTENSIONS_FILE_WITHOUT_COMPRESSED	= {".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pps", ".pptx", ".ppsx",
			".pdf", ".txt"													};
	/**
	 * Tipos de archivos permitidos en el sistema sin considerar los archivos
	 * comprimidos.
	 * 
	 */
	public String[]				ALLOWED_TYPE_FILE_WITHOUT_COMPRESSED		= {"application/msword",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-powerpoint", "application/vnd.ms-powerpoint",
			"application/vnd.openxmlformats-officedocument.presentationml.presentation",
			"application/vnd.openxmlformats-officedocument.presentationml.slideshow", "application/pdf", "text/plain"};
	/******************************************************** Fin ARCHIVOS *************************************************/
}
