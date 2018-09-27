/**
 * @(#)BOOficialiaPartes.java 10/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.Calendar;
import java.util.Date;

import mx.ine.gestion.bo.inter.BOOficialiaPartesInterface;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoFoliosEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaEntity;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Clase que tendrá la lóogica y procedimientos del módulo de Oficialia de partes.
 * 
 * @author David Rodríguez Corral
 * @since 10/12/2017
 */
@Component("boOficialiaPartes")
@Scope("prototype")
public class BOOficialiaPartes implements BOOficialiaPartesInterface{
	
	private static final Logger logger = Logger.getLogger(BOOficialiaPartes.class);
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOOficialiaPartesInterface#crearApartadoFolioSiguienteOf(java.lang.Integer, mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity)
	 */
	@Override
public DTOApartadosNumDocOfEntity crearApartadoFolioSiguienteOf(Integer folioApartado, Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, String tipoApartado) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOApartadosNumDocOfEntity apartado = new DTOApartadosNumDocOfEntity();
		
		logger.info("Encontré el último folio");
		Integer idFolio = folioApartado + 1;
		apartado.setIdFolioOf(idFolio);
		apartado.setFolioOficialia(siglas + "-" +
				String.format("%05d",idFolio) + "-" + anio);
		apartado.setIdArea(idArea);
		apartado.setTipoArea(tipoArea);
		apartado.setEstatus(Utilidades.mensajeProperties("constante_OficialiaPartes_apartar").charAt(0));
		apartado.setIdOficialiaAparto(idOficialia);
		apartado.setAnio(anio);
		apartado.setTipoApartado(tipoApartado);
		
		return apartado;
	
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOOficialiaPartesInterface#crearPrimerFolioOf(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity)
	 */
	@Override
	public DTOApartadosNumDocOfEntity crearPrimerFolioOf(Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, String tipoApartado) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		logger.info("No existen folios creados para esta oficialia, crearé el primero");
		DTOApartadosNumDocOfEntity apartado = new DTOApartadosNumDocOfEntity();
		String folio = siglas + "-" + 
				Utilidades.mensajeProperties("constante_oficialiaPartes_primerFolio") + "-" + anio;
		
		apartado.setIdFolioOf(1);
		apartado.setFolioOficialia(folio);
		apartado.setIdArea(idArea);
		apartado.setTipoArea(tipoArea);
		apartado.setEstatus(Utilidades.mensajeProperties("constante_OficialiaPartes_apartar").charAt(0));
		apartado.setIdOficialiaAparto(idOficialia);
		apartado.setTipoApartado(tipoApartado);
		apartado.setAnio(anio);
		
		return apartado;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOOficialiaPartesInterface#creaDocumentoClasifArea(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity, java.lang.String, java.lang.String)
	 */
	@Override
	public DTODocumentosClasifAreaEntity creaDocumentoClasifArea(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String seccionSeleccionada, 
			String categoriaSeleccionada) {
		DTODocumentosClasifAreaEntity dtoClasif = new DTODocumentosClasifAreaEntity();
		dtoClasif.setIdDocumento(dtoBandejaEntrada.getIdDocumento());
		dtoClasif.setIdAreaClasifica(dtoBandejaEntrada.getIdAreaDestinatario());
		dtoClasif.setTipoAreaClasifica(dtoBandejaEntrada.getTipoAreaDestinatario());
		dtoClasif.setIdOficialiaClasifica(dtoBandejaEntrada.getIdOficialia());
		dtoClasif.setIdSeccion(Integer.parseInt(seccionSeleccionada));
		dtoClasif.setIdCategoria(Integer.parseInt(categoriaSeleccionada));
		
		return dtoClasif;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOOficialiaPartesInterface#creaDocumentoFolios(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity, java.lang.String)
	 */
	@Override
	public DTODocumentoFoliosEntity creaDocumentoFolios(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String folio) {
		DTODocumentoFoliosEntity dtoFolio = new DTODocumentoFoliosEntity();
		String folioCompuesto[] = folio.split("-"); 
		
		dtoFolio.setIdDocumento(dtoBandejaEntrada.getIdDocumento());
		dtoFolio.setIdAreaClasifica(dtoBandejaEntrada.getIdAreaDestinatario());
		dtoFolio.setTipoAreaClasifica(dtoBandejaEntrada.getTipoAreaDestinatario());
		dtoFolio.setIdFolioOficialia(Integer.parseInt(folioCompuesto[1]));
		
		return dtoFolio;
	}

	

}
