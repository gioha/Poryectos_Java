/**
 * @(#)VHPlantillas.java 14/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHPlantillasInterface;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAV;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAVImpl;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos que tienen la logica de negocio 
 * que únicamente llega a la primera capa la cual tiene que ver con el módulo de Apartado de Folios.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
@Scope("prototype")
@Component("vhPlantillas")
public class VHPlantillas implements VHPlantillasInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHPlantillasInterface#crearTemporalPlantilla()
	 */
	@Override
	public String crearTemporalPlantilla() throws IOException {

		//1.- Obtenemos la ruta de las plantillas
		String rutaPlantillas = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
								+ File.separator + Utilidades.mensajeProperties("constante_generica_plantillas");
		String rutaPlantillasUsuarioTmp = rutaPlantillas + File.separator +  SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + 
										  Utilidades.mensajeProperties("contsante_generica_plantillas_tmp");
		String rutaDocumentoBlanco = rutaPlantillas + File.separator + Utilidades.mensajeProperties("constante_documento_blanco");
		String rutaDocumentoTmpPlantillas = rutaPlantillasUsuarioTmp + File.separator + "tmp_plantilla.docx";
		
		//2.- Obtenemos la carpeta del usuario en plantillas y verificamos que exista, si no existe creamos una
		File carpetaUsuario = new File(rutaPlantillasUsuarioTmp);
		
		if (!carpetaUsuario.exists()) {
			carpetaUsuario.mkdirs();
		}
		
		//3.- Copiamos el archivo blanco a la carpeta del usuario
		File archivoBlanco = new File(rutaDocumentoBlanco);
		File archivoTmpPlantilla = new File(rutaDocumentoTmpPlantillas);
		
		FileUtils.copyFile(archivoBlanco, archivoTmpPlantilla);
		
		return rutaDocumentoTmpPlantillas;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHPlantillasInterface#generarHyperLinkPlantillaTmp()
	 */
	@Override
	public String generarHyperLinkPlantillaTmp() throws GeneralSecurityException, IOException {

		ASConversorURLparaWEBDAV asConversorURL;
		String hiperlink = "";
		final boolean mostrarSoloLectura = false;
		String rutaTemporalPlantilla = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")+ File.separator +
									   Utilidades.mensajeProperties("constante_generica_plantillas") + File.separator + 
									   SecurityContextHolder.getContext().getAuthentication().getName() + File.separator +
									   Utilidades.mensajeProperties("contsante_generica_plantillas_tmp") + File.separator + "tmp_plantilla.docx";
		
		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();

		File archivo = new File(rutaTemporalPlantilla);
		String rutaDoc = archivo.getParent() +  File.separator;
		rutaDoc =  rutaDoc.replaceAll("\\\\", "/");

		hiperlink = asConversorURL.crearHiperLinkWebDav(
				archivo.getName(), 
				rutaDoc, 
				SecurityContextHolder.getContext().getAuthentication().getName(), 
				mostrarSoloLectura, 
				"sidjwebdav", 
				(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
		
		return hiperlink;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHPlantillasInterface#generarHyperLinkPlantilla(mx.ine.gestion.dto.db.DTOPlantillaEntity)
	 */
	@Override
	public String generarHyperLinkPlantilla(DTOPlantillaEntity plantilla) throws GeneralSecurityException, IOException {

		ASConversorURLparaWEBDAV asConversorURL;
		String hiperlink = "";
		final boolean mostrarSoloLectura = false;
		String rutaTemporalPlantilla = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")+ File.separator +
									   Utilidades.mensajeProperties("constante_generica_plantillas") + File.separator + 
									   SecurityContextHolder.getContext().getAuthentication().getName() + File.separator +
									   plantilla.getIdTipoDocumento() + "_" + plantilla.getIdAcronimo() + "_" + plantilla.getIdPlantilla() + ".docx";
		
		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();

		File archivo = new File(rutaTemporalPlantilla);
		String rutaDoc = archivo.getParent() +  File.separator;
		rutaDoc =  rutaDoc.replaceAll("\\\\", "/");

		hiperlink = asConversorURL.crearHiperLinkWebDav(
				archivo.getName(), 
				rutaDoc, 
				SecurityContextHolder.getContext().getAuthentication().getName(), 
				mostrarSoloLectura, 
				"sidjwebdav", 
				(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
		
		return hiperlink;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHPlantillasInterface#eliminarTemporalPlantilla()
	 */
	@Override
	public void eliminarTemporalPlantilla() {

		//1.- Obtenemos la ruta de las plantillas
		String rutaPlantillas = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
								+ File.separator + Utilidades.mensajeProperties("constante_generica_plantillas");
		String rutaPlantillasUsuarioTmp = rutaPlantillas + File.separator +  SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + 
				  						  Utilidades.mensajeProperties("contsante_generica_plantillas_tmp");
		String rutaDocumentoTmpPlantillas = rutaPlantillasUsuarioTmp + File.separator + "tmp_plantilla.docx";
		
		File archivoTmpPlantilla = new File(rutaDocumentoTmpPlantillas);
		
		archivoTmpPlantilla.delete();
	}
}
