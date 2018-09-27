/**
 * @(#)BOBandejaEntrada.java 02/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.ine.gestion.bo.inter.BOBandejaEntradaInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.Utilidades;
import mx.org.ine.servicios.dto.DTOBase;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("boBandejaEntrada")
@Scope("prototype")
public class BOBandejaEntrada implements BOBandejaEntradaInterface{

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaEntradaInterface#crearHistDocTurno(mx.ine.gestion.dto.db.DTOHistDocRecepEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DTOHistDocTurnoEntity crearHistDocTurno( DTOHistDocRecepEntity histRecep, Integer estatus, String comentario) {
		
		DTOHistDocTurnoEntity histTurno = null;
		
		if(histRecep != null && estatus != null){
			
			histTurno = new DTOHistDocTurnoEntity();
			
			histTurno.setIdArea(histRecep.getIdArea());
			histTurno.setTipoArea(histRecep.getTipoArea());
			histTurno.setIdDocumento(histRecep.getIdDocumento());
			histTurno.setAnio(histRecep.getAnio());
			histTurno.setIdHistoricoRecep(histRecep.getIdHistoricoRecep());
			histTurno.setIdPersonaHist(histRecep.getIdPersonaHist());
			histTurno.setIdEstatusTurno(estatus);
			histTurno.setComentarioGrl(comentario);
		}
		
		return histTurno;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaEntradaInterface#crearArbolHistoricoTurnado(java.util.List, java.util.List)
	 */
	@Override
	public TreeNode crearArbolHistoricoTurnado(List<DTOHistDocRecepEntity> listaPersonasTurnaron, List<DTOHistDocRecepEntity> listaPersonasTurnados) {
		TreeNode root = new DefaultTreeNode(new DTOHistDocRecepEntity(), null );
		if(listaPersonasTurnaron != null && listaPersonasTurnados != null){
			for (DTOHistDocRecepEntity personaTurno : listaPersonasTurnaron) {
				
				TreeNode rama = new DefaultTreeNode(personaTurno, root);
				for (DTOHistDocRecepEntity personaTurnada : listaPersonasTurnados) {
					if(personaTurnada.getIdHistoricoPadre().equals(personaTurno.getIdPersonaHist())){
						TreeNode hoja = new DefaultTreeNode(personaTurnada, rama);
					}
				}
			}
		}
		return root;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaEntradaInterface#revisaRepetidosLista(java.util.List, java.util.List)
	 */
	@Override
	public List<DTOHistDocRecepEntity> revisarRepetidosLista(List<DTOHistDocRecepEntity> listaPersonas, List<DTOHistDocRecepEntity> listaPersonasUnicas) {
		List<DTOHistDocRecepEntity> listaAuxiliar = null;  
		if(listaPersonas != null && listaPersonasUnicas != null){
			 listaAuxiliar = new ArrayList<DTOHistDocRecepEntity>();
			for (DTOHistDocRecepEntity personaUnica : listaPersonasUnicas) {
				for (DTOHistDocRecepEntity persona : listaPersonas) {
					if(persona.getIdPersonaHist().equals(personaUnica.getIdPersonaHist())){
						listaAuxiliar.add(persona);
						listaPersonas.remove(persona);
						break;
					}
				}
			}
		}else if(listaPersonasUnicas == null){
			listaAuxiliar = listaPersonas;
		}
		return listaAuxiliar;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaEntradaInterface#creaRecibido(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void crearRecibido(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento) {
		DTOBandejaERecibidosEntity recibido;
		recibido = new DTOBandejaERecibidosEntity();
		recibido.setIdDocumento(documento.getIdDocumento());
		recibido.setIdPersona(persona.getIdPersona());
		recibido.setAnio(documento.getAnio());
		recibido.setIdArea(persona.getIdArea());
		recibido.setTipoArea(persona.getTipoArea());
//		recibido.setContieneAnexos(0);
		recibido.setFechaRecepcion(new Date());
		recibido.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
		recibido.setEnAtencion(Integer.parseInt(Utilidades.mensajeProperties("estatus_no_atencion")));
		recibido.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_sin_respuesta")));
	}

	@Override
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOBandejaERecibidosEntity recibido)
			throws Exception {
		Calendar calendario = Calendar.getInstance();
		String nombreDocumentoGluster = recibido.getIdDocumento()+ "_" + calendario.get(Calendar.YEAR) + "_" + recibido.getIdHistoricoRecep() +".pdf";
		String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
				  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + filtros.getNombreAdjuntoTemporal();

		String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreAdjuntosTurnado");

		File archivoTemporal = new File(rutaTemporalDocumento);
		File rutaPrincipal = new File(rutaDocumentoPrincipal);
		File archivoTmpPrincipal = new File(rutaDocumentoPrincipal + File.separator + filtros.getNombreAdjuntoTemporal());
		File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + nombreDocumentoGluster);

		if (!rutaPrincipal.exists()) {
			rutaPrincipal.mkdirs();
		}

		FileUtils.moveToDirectory(archivoTemporal, rutaPrincipal, false);
		archivoTmpPrincipal.renameTo(archivoPrincipal);
		
		filtros.setNombreAdjuntoGluster(nombreDocumentoGluster);
	}

	@Override
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOBandejaEAtencionEntity atencion) throws Exception {
		
		Calendar calendario = Calendar.getInstance();
		String nombreDocumentoGluster = atencion.getIdDocumento()+ "_" + calendario.get(Calendar.YEAR) + "_" + atencion.getIdHistoricoRecep() +".pdf";
		String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
				  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + filtros.getNombreAdjuntoTemporal();

		String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreAdjuntosTurnado");

		File archivoTemporal = new File(rutaTemporalDocumento);
		File rutaPrincipal = new File(rutaDocumentoPrincipal);
		File archivoTmpPrincipal = new File(rutaDocumentoPrincipal + File.separator + filtros.getNombreAdjuntoTemporal());
		File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + nombreDocumentoGluster);

		if (!rutaPrincipal.exists()) {
			rutaPrincipal.mkdirs();
		}

		FileUtils.moveToDirectory(archivoTemporal, rutaPrincipal, false);
		archivoTmpPrincipal.renameTo(archivoPrincipal);
		
		filtros.setNombreAdjuntoGluster(nombreDocumentoGluster);
	}
}