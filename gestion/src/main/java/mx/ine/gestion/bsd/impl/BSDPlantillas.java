/**
 * @(#)BSDPlantillas.java 05/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASPlantillasInterface;
import mx.ine.gestion.bsd.inter.BSDPlantillasInterface;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Clase encargada de administrar el o los AS de las plantillas de documento del sistema de Gestión
 *
 * @author Luis Urrutia
 * @since 05/12/2017
 */
@Component("bsdPlantillas")
@Scope("prototype")
public class BSDPlantillas implements BSDPlantillasInterface, BSDPaginarLazyInterface<DTOPlantillaEntity, DTOFiltrosPlantillaHelper>{

	@Autowired
	@Qualifier("asPlantillas")
	private transient ASPlantillasInterface asPlantillas;

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOPlantillaEntity> obtenerListaDeDatos(DTOFiltrosPlantillaHelper filtros, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		return asPlantillas.consultarPlantillasLazy(filtros, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosColumna);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOFiltrosPlantillaHelper filtros) {
		
		return asPlantillas.consultarTotalPlantillasLazy(filtros);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDPlantillasInterface#obtenerTipoDocumentos()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> obtenerTipoDocumentos(){
		return asPlantillas.obtenerTipoDocumentos();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDPlantillasInterface#obtenerAcronimos(mx.ine.gestion.dto.helper.DTOPlantillaHelper)
	 */
	@Override
	public List<DTOAcronimoEntity> obtenerAcronimos(DTOFiltrosPlantillaHelper form){
		return asPlantillas.obtenerAcronimos(form);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDPlantillasInterface#validarNumeroPlantillas(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public Boolean validarNumeroPlantillas(DTOFiltrosPlantillaHelper filtros) {
		return asPlantillas.validarNumeroPlantillas(filtros);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDPlantillasInterface#agregarPlantilla(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public void agregarPlantilla(DTOFiltrosPlantillaHelper filtros) throws Exception {
		asPlantillas.agregarPlantilla(filtros);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDPlantillasInterface#eliminarPlantilla()
	 */
	@Override
	public void eliminarPlantilla(DTOPlantillaEntity plantilla) throws Exception{
		asPlantillas.eliminarPlantilla(plantilla);
	}

}
