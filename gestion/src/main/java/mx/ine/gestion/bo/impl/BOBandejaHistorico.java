/**
 * @(#)BOBandejaHistorico.java 12/03/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.Utilidades;

/**
 * @author Homero Fidel Villanuevav
 * @since 12/03/2018
 *
 */
@Component("boBandejaHistorico")
@Scope("prototype")
public class BOBandejaHistorico implements BOBandejaHistoricoInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface#crearHRecibido(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity)
	 */
	@Override
	public DTOHBandejaERecibidosEntity crearHRecibido(DTOBandejaERecibidosEntity recibido) {
		DTOHBandejaERecibidosEntity historico = null;
		if(recibido != null){
			historico = new DTOHBandejaERecibidosEntity();
			historico.setIdDocumento(recibido.getIdDocumento());
			historico.setIdPersona(recibido.getIdPersona());
			historico.setAnio(recibido.getAnio());
			historico.setIdArea(recibido.getIdArea());
			historico.setTipoArea(recibido.getTipoArea());
			historico.setFechaRecepcion(new Date());
			historico.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			historico.setEnAtencion(recibido.getEnAtencion());
			historico.setTieneRespuesta(recibido.getTieneRespuesta());
			
			historico.setUsuario(recibido.getUsuario());
			historico.setFechaHora(new Date());
		}
		return historico;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface#crearHAtencion(mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity)
	 */
	@Override
	public DTOHBandejaEAtencionEntity crearHAtencion(DTOBandejaEAtencionEntity atencion) {
		DTOHBandejaEAtencionEntity historico = null;
		if(atencion != null){
			historico = new DTOHBandejaEAtencionEntity();
			historico.setIdDocumento(atencion.getIdDocumento());
			historico.setIdPersona(atencion.getIdPersona());
			historico.setAnio(atencion.getAnio());
			historico.setIdArea(atencion.getIdArea());
			historico.setTipoArea(atencion.getTipoArea());
			historico.setFechaRecepcion(new Date());
			historico.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			historico.setIdHistoricoRecep(atencion.getIdHistoricoRecep());
			historico.setIdHistoricoPadre(atencion.getIdHistoricoPadre());
			historico.setEnAtencion(atencion.getEnAtencion());
			historico.setTieneRespuesta(atencion.getTieneRespuesta());
			
			historico.setUsuario(atencion.getUsuario());
			historico.setFechaHora(new Date());
		}
		return historico;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface#crearHCCP(mx.ine.gestion.dto.db.DTOBandejaECCPEntity)
	 */
	@Override
	public DTOHBandejaECCPEntity crearHCCP(DTOBandejaECCPEntity ccp) {
		DTOHBandejaECCPEntity historico = null;
		if(ccp != null){
			historico = new DTOHBandejaECCPEntity();
			historico.setIdDocumento(ccp.getIdDocumento());
			historico.setIdPersona(ccp.getIdPersona());
			historico.setAnio(ccp.getAnio());
			historico.setIdArea(ccp.getIdArea());
			historico.setTipoArea(ccp.getTipoArea());
			historico.setFechaRecepcion(new Date());
			historico.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			
			historico.setUsuario(ccp.getUsuario());
			historico.setFechaHora(new Date());
		}
		return historico;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface#crearHInfo(mx.ine.gestion.dto.db.DTOBandejaEInfoEntity)
	 */
	@Override
	public DTOHBandejaEInfoEntity crearHInfo(DTOBandejaEInfoEntity info) {
		DTOHBandejaEInfoEntity historico = null;
		if(info != null){
			historico = new DTOHBandejaEInfoEntity();
			historico.setIdDocumento(info.getIdDocumento());
			historico.setIdPersona(info.getIdPersona());
			historico.setAnio(info.getAnio());
			historico.setIdArea(info.getIdArea());
			historico.setTipoArea(info.getTipoArea());
			historico.setFechaRecepcion(new Date());
			historico.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
			historico.setIdHistoricoRecep(info.getIdHistoricoRecep());
			historico.setIdHistoricoPadre(info.getIdHistoricoPadre());
			
			historico.setUsuario(info.getUsuario());
			historico.setFechaHora(new Date());
		}
		return historico;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface#guardarAdjuntoGlusterPrincipal(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity)
	 */
	@Override
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOHBandejaERecibidosEntity recibido) throws Exception {
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

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface#guardarAdjuntoGlusterPrincipal(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity)
	 */
	@Override
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOHBandejaEAtencionEntity atencion) throws Exception {
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
