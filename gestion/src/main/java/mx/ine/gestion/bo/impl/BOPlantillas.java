/**
 * @(#)BOPlantillas.java 15/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.File;

import mx.ine.gestion.bo.inter.BOPlantillasInterface;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos que tienen la lógica, procesos y reglas de negocio que se utiliza en el módulo de
 * plantillas para las capas de BSD, AS Y DAO
 * 
 * @author Roberto Shirásago Domínguez
 * @since 15/01/2018
 */
@Component("boPlantillas")
@Scope("prototype")
public class BOPlantillas implements BOPlantillasInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOPlantillasInterface#guardarPlantilla(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public void guardarPlantilla(DTOFiltrosPlantillaHelper filtros, Integer idPlantilla) throws Exception {

		//1.- Obtenemos la ruta de las plantillas
		String rutaPlantillas = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_plantillas");
		String rutaPlantillasUsuarioTmp = rutaPlantillas + File.separator +  SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + 
				Utilidades.mensajeProperties("contsante_generica_plantillas_tmp");
		String rutaDocumentoTmpPlantillas = rutaPlantillasUsuarioTmp + File.separator + "tmp_plantilla.docx";
		String rutaDocumentoPlantilla = rutaPlantillas + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator +
				filtros.getIdTipoDocumentoSeleccionado() + "_" + filtros.getAcronimoSeleccionado().getIdAcronimo() + "_" + idPlantilla + ".docx";
		
		File archivoTmpPlantilla = new File(rutaDocumentoTmpPlantillas);
		File archivoPlantilla = new File(rutaDocumentoPlantilla);
		
		FileUtils.copyFile(archivoTmpPlantilla, archivoPlantilla);
		archivoTmpPlantilla.delete();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOPlantillasInterface#eliminarPlantilla(mx.ine.gestion.dto.db.DTOPlantillaEntity)
	 */
	@Override
	public void eliminarPlantilla(DTOPlantillaEntity plantilla) {

		String rutaPlantilla = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")+ File.separator +
				   Utilidades.mensajeProperties("constante_generica_plantillas") + File.separator + 
				   SecurityContextHolder.getContext().getAuthentication().getName() + File.separator +
				   plantilla.getIdTipoDocumento() + "_" + plantilla.getIdAcronimo() + "_" + plantilla.getIdPlantilla() + ".docx";
		
		File archivoPlantilla = new File(rutaPlantilla);
		archivoPlantilla.delete();
	}
}
