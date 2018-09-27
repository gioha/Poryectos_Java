/**
 * @(#)BSDOficialiaPartes.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.as.inter.AsOficialiaPartesInterface;
import mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoCcpEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoFoliosEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesExtEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOCapturaOficialiaHelper;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.util.Utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS relacionados para la pantalla de
 * Oficialia de partes.
 * 
 * @author Sergio Ley Garcia
 * @since 02/11/2017
 * @update David Rodríguez Corral
 * @since 06/12/2017
 * @update José Miguel Ortiz
 * @since 02/04/2018
 */
@Component("bsdOficialiaPartes")
@Scope("prototype")
public class BSDOficialiaPartes implements BSDOficialiaPartesInterface {

	@Autowired
	@Qualifier("asOficialiaPartes")
	private transient AsOficialiaPartesInterface asOficialiaInterface;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarSecciones()
	 */
	@Override
	public List<DTOCSeccionesEntity> consultarSecciones() {
		return asOficialiaInterface.consultarSecciones();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarCategorias(java.lang.Integer)
	 */
	@Override
	public List<DTOCCategoriasEntity> consultarCategorias(Integer idSeccion) {
		return asOficialiaInterface.consultarCategorias(idSeccion);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarDatosClasificacion(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper consultarDatosClasificacion(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String tipoApartado) {
		return asOficialiaInterface.consultarDatosClasificacion(dtoBandejaEntrada, tipoApartado);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#cancelarClasificarDocumento(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void cancelarClasificarDocumento(Integer idDocumento, Integer idArea, Integer tipoArea, String folio) {
		asOficialiaInterface.cancelarClasificarDocumento(idDocumento, idArea, tipoArea, folio);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#clasificarDocumento(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void clasificarDocumento(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String seccionSeleccionada, 
			String categoriaSeleccionada, String folio) {
		asOficialiaInterface.clasificarDocumento(dtoBandejaEntrada, seccionSeleccionada, categoriaSeleccionada, folio);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarAreasPorOficialia(java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasPorOficialia(Integer idOficialia) {
		return asOficialiaInterface.consultarAreasPorOficialia(idOficialia);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#obtenerFolioDocFisico(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DTOApartadosNumDocOfEntity obtenerFolioDocFisico(Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, DTOApartadosNumDocOfEntity folioAnterior, String tipoApartado) {
		return asOficialiaInterface.obtenerFolioDocFisico(idOficialia, idArea, tipoArea, siglas, folioAnterior, tipoApartado);
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarCombosIniciales(String rolUsuario, Integer idOficialia)
	 */
	@Override
	public DTOCapturaOficialiaHelper consultarCombosIniciales(String rolUsuario, Integer idOficialia) {
		return asOficialiaInterface.consultarCombosIniciales(rolUsuario, idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarAreas(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreas(Integer tipoArea, Integer idEntidad) {
		return asOficialiaInterface.consultarAreas(tipoArea, idEntidad);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarEstados()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstados() {
		return asOficialiaInterface.consultarEstados();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#capturarRemitenteExterno(mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity)
	 */
	@Override
	public void capturarRemitenteExterno(DTORemitentesExternosOfEntity dtoRemitenteExterno) {
		asOficialiaInterface.capturarRemitenteExterno(dtoRemitenteExterno);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarFoliosPendientes(java.lang.Integer, char)
	 */
	@Override
	public DTOBandejaEntradasOficialiaEntity consultarFoliosPendientes(Integer idOficialia, char estatus) {
		return asOficialiaInterface.consultarFoliosPendientes(idOficialia, estatus);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#liberarFolio(mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity)
	 */
	@Override
	public void liberarFolio(DTOApartadosNumDocOfEntity dtoApartado) {
		asOficialiaInterface.liberarFolio(dtoApartado);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#consultarPersonas(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonas(Integer idArea, Integer tipoArea, String nombre, String apellidos) {
		return asOficialiaInterface.consultarPersonas(idArea, tipoArea, nombre, apellidos);
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#recuperarRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf, DTOApartadosNumDocOfEntity dtoApartadoOf)
	 */
	@Override
	public void recuperarRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf, DTOApartadosNumDocOfEntity dtoApartadoOf) {
		dtoCapturaOf.setRemitentesExtEncontrados( asOficialiaInterface.recuperarRemitentesExt( dtoApartadoOf.getIdArea(),
																							   dtoApartadoOf.getTipoArea(),
																							   dtoCapturaOf.getNombreRemitenteExt(),
																							   dtoCapturaOf.getDependenciaRemitenteExt() ));
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#cargarDocumento(DTOUsuarioLogin usuarioLogueado, DTOCapturaOficialiaHelper dtoCapturaOf,
																				 DTODocumentoEntity dtoDocumentoOf, DTOApartadosNumDocOfEntity dtoApartadoOf,
																				 List<DTOEstructuraAreasEntity> listaRemitentesDoc,
																				 List<DTOEstructuraAreasEntity> listaDestinatariosDoc,
																				 List<DTOEstructuraAreasEntity> listaCcpDoc)
	 */
	@Override
	public void cargarDocumento(DTOUsuarioLogin usuarioLogueado, DTOCapturaOficialiaHelper dtoCapturaOf,
								DTODocumentoEntity dtoDocumentoOf, DTOApartadosNumDocOfEntity dtoApartadoOf,
								List<DTOEstructuraAreasEntity> listaRemitentesDoc,
								List<DTOEstructuraAreasEntity> listaDestinatariosDoc,
								List<DTOEstructuraAreasEntity> listaCcpDoc) {

		// 1. El estatus del folio apartado es puesto en concluido
		dtoApartadoOf.setEstatus(Utilidades.mensajeProperties("constante_OficialiaPartes_concluir").charAt(0));

		// 2. Se construye el documento de oficialía a insertar en BD
		dtoDocumentoOf.setIdPersona(usuarioLogueado.getIdPersona());
		dtoDocumentoOf.setIdOficialia(usuarioLogueado.getIdOficialia());
		dtoDocumentoOf.setAnio(Integer.parseInt((new SimpleDateFormat("yyyy")).format(dtoDocumentoOf.getFechaCreacion())));
		dtoDocumentoOf.setIdArea(dtoApartadoOf.getIdArea());
		dtoDocumentoOf.setTipoArea(dtoApartadoOf.getTipoArea());
		dtoDocumentoOf.setNombreDocumento(dtoCapturaOf.getNombreDocumento());
		dtoDocumentoOf.setEstatus('A');
		dtoDocumentoOf.setFirmado(0);	// Valores inicializados con valor 0 por default
		dtoDocumentoOf.setValidado(0);
		dtoDocumentoOf.setEditado(0);
		dtoDocumentoOf.setFechaCreacion(dtoDocumentoOf.getFechaCreacion());
		dtoDocumentoOf.setFechaRecepcionOf(dtoCapturaOf.getFechaRecepcion());
		dtoDocumentoOf.setTipoCaptura(3);	// Tipo 3 - Adjunto
		dtoDocumentoOf.setProcedenciaDocumento('S');	// ROQUE TEMPORAL (CORREGIR)
		dtoDocumentoOf.setContieneAnexos(dtoCapturaOf.getDocsAnexos().size() > 0 ? 1 : 0);

		// 3. Se construye la clasificación del documento a insertar en BD
		DTODocumentosClasifAreaEntity dtoDocClasifArea = new DTODocumentosClasifAreaEntity();
		dtoDocClasifArea.setIdAreaClasifica(dtoApartadoOf.getIdArea());
		dtoDocClasifArea.setTipoAreaClasifica(dtoApartadoOf.getTipoArea());
		dtoDocClasifArea.setIdOficialiaClasifica(usuarioLogueado.getIdOficialia());
		dtoDocClasifArea.setIdSeccion(dtoCapturaOf.getSeccionSeleccionada());
		dtoDocClasifArea.setIdCategoria(dtoCapturaOf.getCategoriaSeleccionada());

		// 4. Se construye el folio del documento a insertar en BD
		DTODocumentoFoliosEntity dtoDocFolio = new DTODocumentoFoliosEntity();
		dtoDocFolio.setIdAreaClasifica(dtoApartadoOf.getIdArea());
		dtoDocFolio.setTipoAreaClasifica(dtoApartadoOf.getTipoArea());
		dtoDocFolio.setIdFolioOficialia(dtoApartadoOf.getIdFolioOf());

		// 5. Se construye la lista de documentos anexos a insertar en BD
		DTODocumentoAnexoEntity dtoDocAnexo;
		List<DTODocumentoAnexoEntity> listaAnexos = new ArrayList<DTODocumentoAnexoEntity>();
		int idAnexo = 1;

		for(DTODocumentoAnexoHelper docAnexo : dtoCapturaOf.getDocsAnexos()) {
			dtoDocAnexo = new DTODocumentoAnexoEntity();
			dtoDocAnexo.setIdAnexo(idAnexo++);
			dtoDocAnexo.setAnio(dtoDocumentoOf.getAnio());
			dtoDocAnexo.setNombreDocAnexo(docAnexo.getNombreOriginal());
			dtoDocAnexo.setTamanio((double) docAnexo.getSize());
			dtoDocAnexo.setNombreAnexo(docAnexo.getNombreTemporal());
			listaAnexos.add(dtoDocAnexo);
		}

		// 6. Se construye la lista de remitentes a insertar en BD
		DTODocumentosRemitentesEntity dtoRemitenteDoc;
		List<DTODocumentosRemitentesEntity> listaRemitentes = new ArrayList<DTODocumentosRemitentesEntity>();

		for(DTOEstructuraAreasEntity remitenteDoc : listaRemitentesDoc) {
			dtoRemitenteDoc = new DTODocumentosRemitentesEntity();
			dtoRemitenteDoc.setIdPersonaRemitente(remitenteDoc.getIdPersona());
			dtoRemitenteDoc.setAnio(dtoDocumentoOf.getAnio());
			listaRemitentes.add(dtoRemitenteDoc);
		}

		// 7. Se construye la lista de remitentes externos a insertar en BD
		DTODocumentosRemitentesExtEntity dtoRemitenteExtDoc;
		List<DTODocumentosRemitentesExtEntity> listaRemitentesExt = new ArrayList<DTODocumentosRemitentesExtEntity>();

		for(DTORemitentesExternosOfEntity remitenteExt : dtoCapturaOf.getRemitentesExtAgregados()) {
			dtoRemitenteExtDoc = new DTODocumentosRemitentesExtEntity();
			dtoRemitenteExtDoc.setIdRemitente(remitenteExt.getIdRemitente());
			dtoRemitenteExtDoc.setIdArea(remitenteExt.getIdArea());
			dtoRemitenteExtDoc.setTipoArea(remitenteExt.getTipoArea());
			listaRemitentesExt.add(dtoRemitenteExtDoc);
		}

		// 8. Se construye la lista de destinatarios a insertar en BD
		DTODocumentoDestinatarioEntity dtoDestinatarioDoc;
		List<DTODocumentoDestinatarioEntity> listaDestinatarios = new ArrayList<DTODocumentoDestinatarioEntity>();

		for(DTOEstructuraAreasEntity destinatarioDoc : listaDestinatariosDoc) {
			dtoDestinatarioDoc = new DTODocumentoDestinatarioEntity();
			dtoDestinatarioDoc.setAnio(dtoDocumentoOf.getAnio());
			dtoDestinatarioDoc.setIdPersonaDestinataria(destinatarioDoc.getIdPersona());
			dtoDestinatarioDoc.setTipoDestinatario(1);	// Siempre son tratados como personas (Tipo Destinatario - 1)
			listaDestinatarios.add(dtoDestinatarioDoc);
		}

		// 9. Se construye la lista de copias de conocimiento a insertar en BD
		DTODocumentoCcpEntity dtoCcpDoc;
		List<DTODocumentoCcpEntity> listaCcp = new ArrayList<DTODocumentoCcpEntity>();
		int idCcp = 1;

		for(DTOEstructuraAreasEntity ccpDoc : listaCcpDoc) {
			dtoCcpDoc = new DTODocumentoCcpEntity();
			dtoCcpDoc.setAnio(dtoDocumentoOf.getAnio());
			dtoCcpDoc.setIdCcp(idCcp++);
			dtoCcpDoc.setIdPersona(ccpDoc.getIdPersona());
			listaCcp.add(dtoCcpDoc);
		}

		// 10. El documento es cargado en BD
		asOficialiaInterface.cargarDocumento(dtoApartadoOf, dtoDocumentoOf, dtoDocClasifArea, dtoDocFolio,
											 listaAnexos, listaRemitentes, listaRemitentesExt, listaDestinatarios, listaCcp);
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface#recuperarDocumento(Integer idDocumento)
	 */
	@Override
	public DTODocumentoEntity recuperarDocumento(Integer idDocumento) {
		return asOficialiaInterface.recuperarDocumento(idDocumento);
	}

}
