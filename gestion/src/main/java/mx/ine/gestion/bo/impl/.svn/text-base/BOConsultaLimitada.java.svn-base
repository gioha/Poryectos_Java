/**
 * @(#)BOConsultaLimitada.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.bo.inter.BOConsultaLimitadaInterface;
import mx.ine.gestion.dto.helper.DTODocumentoGlusterHelper;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que guarda negocio
 *
 * @autor INE
 * @copy MAVO
 * @since 12/07/2016
 */
@Component("boConsultaLimitada")
@Scope("prototype")
public class BOConsultaLimitada implements BOConsultaLimitadaInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.bo.BOConsultaLimitadaInterface#validarContrasenia(java.lang.String, java.lang.String)
	 */
	@Override
	public String validarContraseniaYPermisosOperacion(String operacion, String contrasenia) {

		String mensajeError = "";
		String contraseniaDefinida = "";
		
		String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
		
			contraseniaDefinida = Utilidades.mensajeProperties(usuario);
		
		} catch (Exception e) {

			return mensajeError = "No permitido.";
		}
		
		if (!contraseniaDefinida.equalsIgnoreCase(contrasenia)) {
			
			return mensajeError = "Contrase\u00F1a incorrecta.";
		}

		if (operacion.contains("update") || operacion.contains("delete") || operacion.contains("insert")
		 || operacion.contains("UPDATE") || operacion.contains("DELETE") || operacion.contains("INSERT")) {
			
			if(!validarRolIlimitado(usuario)) {
				
				return mensajeError = "Operaci\u00F3n no permitida.";
			}
		}
		
		return mensajeError;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.bo.BOConsultaLimitadaInterface#obtenerArchivosGluster(java.lang.String)
	 */
	@Override
	public List<DTODocumentoGlusterHelper> obtenerArchivosGluster(String ruta) {
		
		String rutaCompleta = Utilidades.getRutaGlusterFS() + ruta;
		File carpetaRutaCompleta = new File(rutaCompleta);
		
		if (carpetaRutaCompleta.exists()) {

			List<DTODocumentoGlusterHelper> listaArchivosCarpetas = new ArrayList<DTODocumentoGlusterHelper>();
	
			for (File archivo: carpetaRutaCompleta.listFiles()) {
				
				DTODocumentoGlusterHelper nuevoRegistro = new DTODocumentoGlusterHelper();
				nuevoRegistro.setNombreDocumento(archivo.getName());
				nuevoRegistro.setDocumento(archivo);
				nuevoRegistro.setFolder(archivo.isDirectory());

				if (nuevoRegistro.isFolder()) {

					nuevoRegistro.setSize(BigDecimal.valueOf(FileUtils.sizeOfDirectory(archivo)/1048576.000).setScale(3, RoundingMode.HALF_UP).doubleValue());

				} else {

					nuevoRegistro.setSize(BigDecimal.valueOf(FileUtils.sizeOf(archivo)/1048576.000).setScale(3, RoundingMode.HALF_UP).doubleValue());
				}

				listaArchivosCarpetas.add(nuevoRegistro);
			}

			return listaArchivosCarpetas;
		}
		
		return new ArrayList<DTODocumentoGlusterHelper>();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.bo.BOConsultaLimitadaInterface#eliminarArchivosGluster(java.util.List, mx.ine.pautas.dto.helper.HLPDocumentoGluster)
	 */
	@Override
	public boolean eliminarArchivosGluster(List<DTODocumentoGlusterHelper> listaArchivos, DTODocumentoGlusterHelper archivo) {
		
		int indiceArchivo = listaArchivos.indexOf(archivo);
		boolean seBorro = archivo.getDocumento().delete();
		
		if (seBorro) {

			listaArchivos.remove(indiceArchivo);
		}
		
		return seBorro;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.bo.BOConsultaLimitadaInterface#eliminarContenidoCarpeta(mx.ine.pautas.dto.helper.HLPDocumentoGluster)
	 */
	@Override
	public boolean eliminarContenidoCarpeta(DTODocumentoGlusterHelper carpeta) {

		File[] contenidoCarpeta = carpeta.getDocumento().listFiles(); 
		boolean seBorro = true;
		
		for(int indice = 0; indice < contenidoCarpeta.length; indice++) {

			if (contenidoCarpeta[indice].isFile()) {
				
				seBorro = contenidoCarpeta[indice].delete();
			}
		}
		
		return seBorro;
	}

	/**
	 * Método valida usuarios
	 * 
	 * @param usuario: contiene el usuario logueado
	 * @return boolean: si tiene o no el poder
	 *
	 * @autor INE
	 * @since 12/07/2016
	 */
	private boolean validarRolIlimitado(String usuario) {
		
		String usuariosPoderesIlimitados = Utilidades.mensajeProperties("con_poder_ilimitado");
		String[] usuariosPoderesIlimitadosArray = usuariosPoderesIlimitados.split("\\|");
		
		for (int indice = 0; indice < usuariosPoderesIlimitadosArray.length; indice++) {
			
			if(usuariosPoderesIlimitadosArray[indice].equalsIgnoreCase(usuario)) {
				
				return true;
			}
		}
		
		return false;
	}

}
