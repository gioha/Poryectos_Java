/**
 * @(#)BOBandejaSeguimiento.java 02/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.helper.DTOPersonaBorradoresHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("boBandejaSeguimiento")
@Scope("prototype")
public class BOBandejaSeguimiento implements BOBandejaSeguimientoInterface{
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#unirListas(java.util.List, java.util.List)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> unirListas(List<DTOEstructuraAreasEntity> areas, List<DTOEstructuraAreasEntity> personas) {
		List<DTOEstructuraAreasEntity> lista;
		
		if((areas != null && areas.size()>0) &&(personas != null && personas.size()>0)){
			lista = personas;
			for (DTOEstructuraAreasEntity area : areas) {
				if(!personas.contains(area)){
					lista.add(area);
				}
			}
		}else if(areas != null && areas.size()>0){
			lista = areas;
		}else if(personas != null && personas.size()>0){
			lista = personas;
		}else{
			lista=null;
		}
		
		return lista;
	}
	
//	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
//	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#unirListasBOrradorHelper(java.util.List, java.util.List)
//	 */
//	@Override
//	public List<DTOEstructuraAreasEntity> unirListasBOrradorHelper(List<DTOPersonaBorradoresHelper> areas, List<DTOPersonaBorradoresHelper> personas) {
//		List<DTOEstructuraAreasEntity> lista;
//		
////		if((areas != null && areas.size()>0) &&(personas != null && personas.size()>0)){
////			lista = personas;
////			for (DTOEstructuraAreasEntity area : areas) {
////				if(!personas.contains(area)){
////					lista.add(area);
////				}
////			}
////		}else if(areas != null && areas.size()>0){
////			lista = areas;
////		}else if(personas != null && personas.size()>0){
////			lista = personas;
////		}else{
////			lista=null;
////		}
////		
//		return lista;
//	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#obtenerHistoricoCreacion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer)
	 */
	@Override
	public DTOHistDocCreacionEntity crearHistoricoCreacion(DTODocumentoEntity documento, DTOEstructuraAreasEntity dtoEstructuraAreasEntity, Integer estatus) {
		DTOHistDocCreacionEntity histDoc = null;
		if(documento != null && dtoEstructuraAreasEntity != null && estatus != null){
			histDoc = new DTOHistDocCreacionEntity();
			
			histDoc.setIdDocumento(documento.getIdDocumento());
			histDoc.setAnio(documento.getAnio());
			histDoc.setIdPersonaHist(dtoEstructuraAreasEntity.getIdPersona());
			histDoc.setIdEstatus(estatus);
		}
		return histDoc;
	}
	

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#crearHistoricoCreacion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity, java.lang.Integer)
	 */
	@Override
	public DTOHistDocCreacionEntity crearHistoricoCreacion(DTODocumentoEntity documento, DTODocumentoDestinatarioEntity destinatario, Integer estatus) {
		DTOHistDocCreacionEntity histDoc = null;
		if(documento != null && destinatario != null && estatus != null){
			histDoc = new DTOHistDocCreacionEntity();
			
			histDoc.setIdDocumento(documento.getIdDocumento());
			histDoc.setAnio(documento.getAnio());
			histDoc.setIdPersonaHist(destinatario.getIdPersonaDestinataria());
			histDoc.setIdEstatus(estatus);
		}
		return histDoc;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#obtenerHistoricoRecep(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.Integer)
	 */
	@Override
	public DTOHistDocRecepEntity crearHistoricoRecep(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, Integer idHistoricoPadre, Integer estatusRecep) {
		DTOHistDocRecepEntity dtoHistRecep = new DTOHistDocRecepEntity();
		dtoHistRecep.setIdArea(dtoEstructuraAreasEntity.getIdArea());
		dtoHistRecep.setTipoArea(dtoEstructuraAreasEntity.getTipoArea());
		dtoHistRecep.setIdDocumento(documento.getIdDocumento());
		dtoHistRecep.setAnio(documento.getAnio());
		dtoHistRecep.setIdHistoricoPadre(idHistoricoPadre);;
		dtoHistRecep.setIdPersonaHist(dtoEstructuraAreasEntity.getIdPersona());
		dtoHistRecep.setIdEstatusRecep(estatusRecep);
		return dtoHistRecep;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#obtenerHistoricoTurno(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOHistDocTurnoEntity crearHistoricoTurno(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, Integer idHistoricoRecep, Integer estatusTurno,String comentario) {
		
		DTOHistDocTurnoEntity dtoHistTurno = new DTOHistDocTurnoEntity();
		dtoHistTurno.setIdArea(dtoEstructuraAreasEntity.getIdArea());
		dtoHistTurno.setTipoArea(dtoEstructuraAreasEntity.getTipoArea());
		dtoHistTurno.setIdDocumento(documento.getIdDocumento());
		dtoHistTurno.setAnio(documento.getAnio());
		dtoHistTurno.setIdHistoricoRecep(idHistoricoRecep);
		dtoHistTurno.setIdPersonaHist(dtoEstructuraAreasEntity.getIdPersona());
		dtoHistTurno.setIdEstatusTurno(estatusTurno);
		dtoHistTurno.setComentarioGrl(comentario);
		
		return dtoHistTurno;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#obtenerDiferenciaRegistrosComentarios(java.util.List, java.util.List)
	 */
	@Override
	public int obtenerDiferenciaRegistrosComentarios(List<DTOComentariosDocumentoEntity> comentarios, List<DTOComentariosNoLeidos> comentariosNoLeidos) {
		int res = -1;
		if(comentarios != null && comentariosNoLeidos != null){
			res = comentarios.size() - comentariosNoLeidos.size();
		}
		return res;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#crearComentarioNoLeido(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity)
	 */
	@Override
	public DTOComentariosNoLeidos crearComentarioNoLeido(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona, DTOComentariosDocumentoEntity comentario) {
		DTOComentariosNoLeidos comentarioEstatus = null;
		if(documento != null && persona != null && comentario != null){
			comentarioEstatus = new DTOComentariosNoLeidos();
			comentarioEstatus.setIdDocumento(documento.getIdDocumento());
			comentarioEstatus.setIdComentario(comentario.getIdComentario());
			comentarioEstatus.setIdPersona(persona.getIdPersona());
			comentarioEstatus.setIdArea(persona.getIdArea());
			comentarioEstatus.setTipoArea(persona.getTipoArea());
			comentarioEstatus.setNoLeido(1);
		}
		return comentarioEstatus;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface#tieneRespuesta(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper)
	 */
	@Override
	public boolean tieneRespuesta(DTOResponderTurnadoHelper helperRespuesta) {
		boolean res = false;
		if(helperRespuesta != null	&& ( (helperRespuesta.getComentario() != null && !helperRespuesta.getComentario().equals("") )|| helperRespuesta.getIdDocumentoRespondido() != null || helperRespuesta.getNombreAdjuntoTemporal() != null )){
			res = true;
		}
		return res;
	}

	
}