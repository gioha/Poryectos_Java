/**
 * BOBorradorDocumentos.java 06/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.helper.DTOFiltroEstructuraAreaHelper;
import mx.ine.gestion.dto.helper.DTOPersonaBorradoresHelper;
import mx.ine.gestion.util.Utilidades;

/**
 * @author Homero Villanueva Dominguez
 * @since 06/10/2017
 */

@Component("boBandejaBorradores")
@Scope("prototype")
public class BOBandejaBorradores implements BOBandejaBorradoresInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOBorradorDocumentosInterface#revisarFimaValidacion
	 * (mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	public int revisarFimaEnBorrador(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, List<DTOFirmaDocumentosEntity> listaFirmaDocumentos) {
		return (listaFirmaDocumentos.size() > 0) ? 0 : listaFirmaDocumentos.size();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.bo.inter.BOBorradorDocumentosInterface#
	 * revisarValidacionEnBorrador()
	 */
	@Override
	public int revisarValidacionEnBorrador() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBorradorDocumentosInterface#revisarPersonaEstaLista(java.util.List, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public boolean revisarPersonaEstaLista(List<DTOEstructuraAreasEntity> lista, DTOEstructuraAreasEntity persona) {
		boolean res = false;
		if(lista != null){
			for (DTOEstructuraAreasEntity elemento : lista) {
				if(elemento.equals(persona)){
					res= true;
					break;
				}
			}
		}
		return res;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBorradorDocumentosInterface#obtenerBorrador(java.util.List, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	public DTOBorradorDocumentosEntity obtenerBorrador(List<DTOBorradorDocumentosEntity> listaBorradores, DTOBorradorDocumentosEntity borrador) {
		DTOBorradorDocumentosEntity borradorRes = null;
		if(listaBorradores != null && borrador != null){
			for (DTOBorradorDocumentosEntity elemento : listaBorradores) {
				if(elemento.equals(borrador)){
					borradorRes = borrador;
					break;
				}
			}
		}
		return borradorRes;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface#obtenerFiltro(java.util.List, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String)
	 */
	@Override
	public DTOFiltroEstructuraAreaHelper obtenerFiltro(List<String> personasNoIncluidas, DTOEstructuraAreasEntity persona, String coincidencia) {
		DTOFiltroEstructuraAreaHelper filtro = new DTOFiltroEstructuraAreaHelper();
		
		if (persona != null) {
			filtro.setIdArea(persona.getIdArea());
			filtro.setTipoArea(persona.getTipoArea());
		}

		if (personasNoIncluidas != null) {
			filtro.setCuentasLDAP(personasNoIncluidas);
		}

		if (coincidencia != null) {
			filtro.setCoincidencia(coincidencia);
		}

		return filtro;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface#crearDocumentoRecibido(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.Integer)
	 */
	@Override
	public DTOBandejaERecibidosEntity crearDocumentoRecibido(DTOEstructuraAreasEntity personaRecepecion, DTODocumentoEntity documento, Integer idHistoricoRecep) {
		DTOBandejaERecibidosEntity recibido = null;
		if(personaRecepecion != null && documento != null && idHistoricoRecep != null){
			recibido = new DTOBandejaERecibidosEntity();
			recibido.setIdDocumento(documento.getIdDocumento());
			recibido.setIdPersona(personaRecepecion.getIdPersona());
			recibido.setAnio(documento.getAnio());
			recibido.setIdArea(personaRecepecion.getIdArea());
			recibido.setTipoArea(personaRecepecion.getTipoArea());
			recibido.setFechaRecepcion(new Date());
			recibido.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			recibido.setIdHistoricoRecep(idHistoricoRecep);
			recibido.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));
			recibido.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_sin_respuesta")));
		}
		return recibido;
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface#obtenerPersonas(java.util.List)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> obtenerPersonas(List<DTOPersonaBorradoresHelper> personasHelper) {
		List<DTOEstructuraAreasEntity> listaPersonas = null;
		if(personasHelper != null){
			listaPersonas = new ArrayList<DTOEstructuraAreasEntity>();
			for (DTOPersonaBorradoresHelper persona : personasHelper) {
				listaPersonas.add(persona.getPersona());
			}
		}
		return listaPersonas;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface#obtenerPersonasBorradorHelper(java.util.List)
	 */
	@Override
	public List<DTOPersonaBorradoresHelper> obtenerPersonasBorradorHelper(List<DTOEstructuraAreasEntity> personas) {
		List<DTOPersonaBorradoresHelper> listaPersonasHelper = null;
		if(personas != null){
			listaPersonasHelper = new ArrayList<DTOPersonaBorradoresHelper>();
			for(DTOEstructuraAreasEntity persona : personas){
				listaPersonasHelper.add(new DTOPersonaBorradoresHelper(persona));
			}
		}
		return listaPersonasHelper;
	}
}