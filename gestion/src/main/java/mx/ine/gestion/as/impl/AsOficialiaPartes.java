/**
 * @(#)AsOficialiaPartes.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.AsOficialiaPartesInterface;
import mx.ine.gestion.bo.inter.BOOficialiaPartesInterface;
import mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCCategoriasInterface;
import mx.ine.gestion.dao.inter.DAOCSeccionesInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOCTipoDocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentosCCPInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface;
import mx.ine.gestion.dao.inter.DAODocumentoFoliosInterface;
import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dao.inter.DAODocumentosClasifAreaInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesExtInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface;
import mx.ine.gestion.dao.inter.DAOEstadosInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface;
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
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase AsOficialiaPartes, encargada de administrar los objetos BO y DAO para
 * el modulo de oficialia de partes ademas de generar funciones de sistema
 * relacionados a este.
 * @author Sergio Ley Garcia
 * @since 02/11/2017
 * @update David Rodríguez Corral
 * @since 06/12/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
@Service("asOficialiaPartes")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class AsOficialiaPartes implements AsOficialiaPartesInterface {
	private static final Logger	logger	= Logger.getLogger(AsOficialiaPartes.class);
	
	@Autowired
	@Qualifier("daoDestinatario")
	private DAODocumentoDestinatarioInterface daoDestinatarioInterface;
	
	@Autowired
	@Qualifier("daoDocumentosCCP")
	private DAODocumentosCCPInterface daoDocumentoCcpInterface;
	
	@Autowired
	@Qualifier("daoCSecciones")
	private DAOCSeccionesInterface daoCSeccionesInterface;
	
	@Autowired
	@Qualifier("daoCCategorias")
	private DAOCCategoriasInterface daoCCategoriasInterface;
	
	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoDocumentoAnexoInterface;
	
	@Autowired
	@Qualifier("daoDocumentosRemitentes")
	private DAODocumentosRemitentesInterface daoDocumentosRemitentesInterface;
	
	@Autowired
	@Qualifier("daoDocumentosRemitentesExt")
	private DAODocumentosRemitentesExtInterface daoDocumentosRemitentesExtInterface;
	
	@Autowired
	@Qualifier("daoDocumento")
	private DAODocumentoInterface daoDocumentoInterface;
	
	@Autowired
	@Qualifier("daoApartadosNumDocOf")
	private DAOApartadosNumDocOfInterface daoApartadosNumDocOfInterface;
	
	@Autowired
	@Qualifier("boOficialiaPartes")
	private BOOficialiaPartesInterface boOficialiaPartesInterface;
	
	@Autowired
	@Qualifier("daoBandejaEntradasOficialia")
	private DAOBandejaEntradasOficialiaInterface daoBandejaEntradasOficialiaInterface;
	
	@Autowired
	@Qualifier("daoDocumentosClasifArea")
	private DAODocumentosClasifAreaInterface daoDocumentosClasifAreaInterface;
	
	@Autowired
	@Qualifier("daoDocumentoFolios")
	private DAODocumentoFoliosInterface daoDocumentoFoliosInterface;
	
	@Autowired
	@Qualifier("daoOficialiasAreas")
	private DAOOficialiasAreasInterface daoOficialiasAreasInterface;
	
	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daoCTipoAreaInterface;
	
	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daoCAreasInterface;
	
	@Autowired
	@Qualifier("daoCTipoDocumento")
	private DAOCTipoDocumentoInterface daoCTipoDocumentoInterface;
	
	@Autowired
	@Qualifier("daoEstados")
	private DAOEstadosInterface daoEstadosInterface;
	
	@Autowired
	@Qualifier("daoRemitentesExternosOf")
	private DAORemitentesExternosOfInterface daoRemitentesExternosOfInterface;
	
	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarSecciones()
	 */
	@Override
	public List<DTOCSeccionesEntity> consultarSecciones() {
		return daoCSeccionesInterface.consultarSecciones();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarCategorias(java.lang.Integer)
	 */
	@Override
	public List<DTOCCategoriasEntity> consultarCategorias(Integer idSeccion) {
		return daoCCategoriasInterface.consultarCategorias(idSeccion);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarDatosClasificacion(java.lang.Integer)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public DTODocumentoOficialiaHelper consultarDatosClasificacion(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String tipoApartado) {
		DTODocumentoOficialiaHelper dtoOficialia = new DTODocumentoOficialiaHelper();
		
		dtoOficialia.setListaDTODestinatarios(daoDestinatarioInterface.consutarDestinatarios(dtoBandejaEntrada.getIdDocumento()));
		dtoOficialia.setListaDTOCcp(daoDocumentoCcpInterface.consultarCCP(dtoBandejaEntrada.getIdDocumento()));
		dtoOficialia.setListaDTOAnexos(daoDocumentoAnexoInterface.consultarAnexos(dtoBandejaEntrada.getIdDocumento()));
		dtoOficialia.setListaDTORemitentes(daoDocumentosRemitentesInterface.consultarRemitentes(dtoBandejaEntrada.getIdDocumento()));
		dtoOficialia.setTipoDocumento(daoDocumentoInterface.obtenerTipoDocumento(dtoBandejaEntrada.getIdDocumento()));
		
		//VALIDACION PARA RECUPERAR EL FOLIO PENDIENTE
		if(dtoBandejaEntrada.getFolio()==null){
		
			DTOApartadosNumDocOfEntity dtoApartarFolio = daoApartadosNumDocOfInterface.consultarFolioDisponible(dtoBandejaEntrada.getIdAreaDestinatario(), dtoBandejaEntrada.getTipoAreaDestinatario());
			
			if(dtoApartarFolio != null){
				
				logger.info("Encontré un folio disponible");
				dtoOficialia.setFolio(dtoApartarFolio.getFolioOficialia());
				dtoApartarFolio.setEstatus(Utilidades.mensajeProperties("constante_OficialiaPartes_apartar").charAt(0));
				dtoApartarFolio.setTipoApartado(tipoApartado);
				dtoApartarFolio.setIdOficialiaAparto(dtoBandejaEntrada.getIdOficialia());
				daoApartadosNumDocOfInterface.modificar(dtoApartarFolio);
			
			}else{
				
				logger.info("No hay folios disponibles, crearé uno nuevo");
				Integer ultimoFolio = daoApartadosNumDocOfInterface.obtenerUltimoFolio(dtoBandejaEntrada.getIdAreaDestinatario(), dtoBandejaEntrada.getTipoAreaDestinatario());
				DTOApartadosNumDocOfEntity apartadoFolioOf = new DTOApartadosNumDocOfEntity();
				
				if(ultimoFolio != null){
					
					apartadoFolioOf = boOficialiaPartesInterface.crearApartadoFolioSiguienteOf(ultimoFolio, dtoBandejaEntrada.getIdOficialia(), dtoBandejaEntrada.getIdAreaDestinatario(), dtoBandejaEntrada.getTipoAreaDestinatario(), dtoBandejaEntrada.getDtoAreasDestinatario().getSiglas(), tipoApartado);
				
				}else{
					
					apartadoFolioOf = boOficialiaPartesInterface.crearPrimerFolioOf(dtoBandejaEntrada.getIdOficialia(), dtoBandejaEntrada.getIdAreaDestinatario(), dtoBandejaEntrada.getTipoAreaDestinatario(), dtoBandejaEntrada.getDtoAreasDestinatario().getSiglas(), tipoApartado);
				
				}
				
				dtoOficialia.setFolio(apartadoFolioOf.getFolioOficialia());
				daoApartadosNumDocOfInterface.guardar(apartadoFolioOf);
			}
			
		}
		
		return dtoOficialia;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#cancelarClasificarDocumento(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void cancelarClasificarDocumento(Integer idDocumento, Integer idArea, Integer tipoArea, String folio) {
		
		//SE LIBERA EL FOLIO PARA QUE LO PUEDA USAR EL MISMO U OTRO DOCUMENTO
		daoApartadosNumDocOfInterface.modificarEstatus(idArea, tipoArea, folio, Utilidades.mensajeProperties("constante_OficialiaPartes_liberar").charAt(0));
		
		//SE INDICA EN BANDEJA QUE YA NO ESTÁ SIENDO CLASIFICADO
		daoBandejaEntradasOficialiaInterface.cancelarClasificarDocumento(idDocumento, idArea, tipoArea);
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#clasificarDocumento(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void clasificarDocumento(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String seccionSeleccionada, 
			String categoriaSeleccionada, String folio) {
		
		//SE LLAMA AL BO PARA EL SETEO DE LOS OBJETOS
		DTODocumentosClasifAreaEntity dtoClasif = boOficialiaPartesInterface.creaDocumentoClasifArea(dtoBandejaEntrada, seccionSeleccionada, categoriaSeleccionada);
		DTODocumentoFoliosEntity dtoFolios = boOficialiaPartesInterface.creaDocumentoFolios(dtoBandejaEntrada, folio);
		
		//SE GUARDA EL DOCUMENTO CLASIFICADO
		daoDocumentosClasifAreaInterface.guardar(dtoClasif);
		
		//SE GUARDA EL FOLIO
		daoDocumentoFoliosInterface.guardar(dtoFolios);
		
		//SE ELIMINA DE LA BANDEJA DE OFICIALIA
		daoBandejaEntradasOficialiaInterface.eliminarDocumentoPorArea(dtoBandejaEntrada);
		
		//QUEDA EL ESTATUS EN EL APARTADO COMO "CONCLUIDO"
		daoApartadosNumDocOfInterface.modificarEstatus(dtoBandejaEntrada.getIdAreaDestinatario(), dtoBandejaEntrada.getTipoAreaDestinatario(), folio, 
				Utilidades.mensajeProperties("constante_OficialiaPartes_concluir").charAt(0));
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarAreasPorOficialia(java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasPorOficialia(Integer idOficialia) {
		return daoOficialiasAreasInterface.consultarAreasOficialia(idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#obtenerFolioDocFisico(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public DTOApartadosNumDocOfEntity obtenerFolioDocFisico(Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, DTOApartadosNumDocOfEntity folioAnterior, String tipoApartado) {
		
		DTOApartadosNumDocOfEntity folio;
		
		DTOApartadosNumDocOfEntity dtoApartarFolio = daoApartadosNumDocOfInterface.consultarFolioDisponible(idArea, tipoArea);
		
		if(dtoApartarFolio != null){
			
			logger.info("Encontré un folio disponible");
			dtoApartarFolio.setEstatus(Utilidades.mensajeProperties("constante_OficialiaPartes_apartar").charAt(0));
			dtoApartarFolio.setTipoApartado(tipoApartado);
			dtoApartarFolio.setIdOficialiaAparto(idOficialia);
			daoApartadosNumDocOfInterface.modificar(dtoApartarFolio);
			folio = dtoApartarFolio;
		
		}else{
			
			logger.info("No hay folios disponibles, crearé uno nuevo");
			Integer ultimoFolio = daoApartadosNumDocOfInterface.obtenerUltimoFolio(idArea, tipoArea);
			DTOApartadosNumDocOfEntity apartadoFolioOf = new DTOApartadosNumDocOfEntity();
			
			if(ultimoFolio != null){
				
				apartadoFolioOf = boOficialiaPartesInterface.crearApartadoFolioSiguienteOf(ultimoFolio, idOficialia, idArea, tipoArea, siglas, tipoApartado);
				
			
			}else{
				
				apartadoFolioOf = boOficialiaPartesInterface.crearPrimerFolioOf(idOficialia, idArea, tipoArea, siglas, tipoApartado);
			
			}
			
			folio = apartadoFolioOf;
			
			
			
			daoApartadosNumDocOfInterface.guardar(apartadoFolioOf);
		}
		
		if(folioAnterior!=null){
			
			folioAnterior.setEstatus('L');
			daoApartadosNumDocOfInterface.modificar(folioAnterior);
			
		}
		
		return folio;
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarCombosIniciales(String rolUsuario, Integer idOficialia)
	 */
	@Override
	public DTOCapturaOficialiaHelper consultarCombosIniciales(String rolUsuario, Integer idOficialia) {
		DTOCapturaOficialiaHelper dtoCapturaCombos = new DTOCapturaOficialiaHelper();
		dtoCapturaCombos.setListaDTOSecciones(daoCSeccionesInterface.consultarSecciones());
		dtoCapturaCombos.setListaDTOTipoArea(daoCTipoAreaInterface.consultarTodosOrdenadosAscPor("idTipoArea"));
		dtoCapturaCombos.setListaDTOTipoDocumento(daoCTipoDocumentoInterface.consultarTodosOrdenadosAscPor("descripcion"));

		if(rolUsuario.equals("GESTION4.OFICIALIA.OC")) {
			dtoCapturaCombos.setListaDTOAreas(daoOficialiasAreasInterface.consultarAreasOficialia(idOficialia));
		} else {
			dtoCapturaCombos.setListaDTOAreas(daoOficialiasAreasInterface.consultarTodasLasAreasOficialia());
		}

		return dtoCapturaCombos;		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarAreas(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreas(Integer tipoArea, Integer idEntidad) {
		return daoCAreasInterface.consultarAreas(tipoArea, idEntidad);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarEstados()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstados() {
		return daoEstadosInterface.consultarEstadosSinOC();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#capturarRemitenteExterno(mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void capturarRemitenteExterno(DTORemitentesExternosOfEntity dtoRemitenteExterno) {
		
		Integer idRemitente = daoRemitentesExternosOfInterface.consultarUltimoRemitente(dtoRemitenteExterno.getIdArea(), dtoRemitenteExterno.getTipoArea());
		
		if(idRemitente==null){
			
			idRemitente = 1;
			
		}else{
			
			idRemitente = idRemitente + 1;
		}
		
		dtoRemitenteExterno.setIdRemitente(idRemitente);
		daoRemitentesExternosOfInterface.guardar(dtoRemitenteExterno);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarFoliosPendientes(java.lang.Integer, char)
	 */
	@Override
	public DTOBandejaEntradasOficialiaEntity consultarFoliosPendientes(Integer idOficialia, char estatus) {
		
		DTOBandejaEntradasOficialiaEntity dtoBandeja = daoBandejaEntradasOficialiaInterface.consultarFoliosPendientes(idOficialia);
		
		if(dtoBandeja!=null){
		
			dtoBandeja.setFolio(daoApartadosNumDocOfInterface.consultarFolioPendiente(dtoBandeja.getIdAreaDestinatario(), dtoBandeja.getTipoAreaDestinatario(), idOficialia, estatus, Utilidades.mensajeProperties("constante_tipoApartado_electronico")).getFolioOficialia());
		}
		
		return dtoBandeja;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#liberarFolio(mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity)
	 */
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	@Override
	public void liberarFolio(DTOApartadosNumDocOfEntity dtoApartado) {
		daoApartadosNumDocOfInterface.modificar(dtoApartado);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#consultarPersonas(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonas(Integer idArea, Integer tipoArea, String nombre, String apellidos) {
		return daoEstructuraAreaInterface.consultarPersonasXNombreXApellidosXArea(nombre, apellidos, tipoArea, idArea);
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#cargarDocumento(DTOApartadosNumDocOfEntity dtoApartadoOf, DTODocumentoEntity dtoDocumentoOf,
	 * 																		   DTODocumentosClasifAreaEntity dtoDocClasifArea, DTODocumentoFoliosEntity dtoDocFolio,
	 * 																		   List<DTODocumentoAnexoEntity> listaAnexos, List<DTODocumentosRemitentesEntity> listaRemitentes,
	 * 																		   List<DTODocumentoDestinatarioEntity> listaDestinatarios, List<DTODocumentoCcpEntity> listaCcp)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void cargarDocumento(DTOApartadosNumDocOfEntity dtoApartadoOf, DTODocumentoEntity dtoDocumentoOf,
			DTODocumentosClasifAreaEntity dtoDocClasifArea, DTODocumentoFoliosEntity dtoDocFolio,
			List<DTODocumentoAnexoEntity> listaAnexos, List<DTODocumentosRemitentesEntity> listaRemitentes,
			List<DTODocumentosRemitentesExtEntity> listaRemitentesExt,
			List<DTODocumentoDestinatarioEntity> listaDestinatarios, List<DTODocumentoCcpEntity> listaCcp) {

		// 1. Se construyen cada uno de los DTO's que involucran el folio y carga de un documento físico de oficialía 
		daoApartadosNumDocOfInterface.modificar(dtoApartadoOf);
		dtoDocumentoOf.setIdDocumento(daoDocumentoInterface.guardar(dtoDocumentoOf));
		dtoDocClasifArea.setIdDocumento(dtoDocumentoOf.getIdDocumento());
		daoDocumentosClasifAreaInterface.guardar(dtoDocClasifArea);
		dtoDocFolio.setIdDocumento(dtoDocumentoOf.getIdDocumento());
		daoDocumentoFoliosInterface.guardar(dtoDocFolio);

		for(DTODocumentoAnexoEntity docAnexo : listaAnexos) {
			docAnexo.setIdDocumento(dtoDocumentoOf.getIdDocumento());
			daoDocumentoAnexoInterface.guardar(docAnexo);
		}

		for(DTODocumentosRemitentesEntity remitenteDoc : listaRemitentes) {
			remitenteDoc.setIdDocumento(dtoDocumentoOf.getIdDocumento());
			daoDocumentosRemitentesInterface.guardar(remitenteDoc);
		}

		for(DTODocumentosRemitentesExtEntity remitenteExtDoc : listaRemitentesExt) {
			remitenteExtDoc.setIdDocumento(dtoDocumentoOf.getIdDocumento());
			daoDocumentosRemitentesExtInterface.guardar(remitenteExtDoc);
		}

		for(DTODocumentoDestinatarioEntity destinatarioDoc : listaDestinatarios) {
			destinatarioDoc.setIdDocumento(dtoDocumentoOf.getIdDocumento());
			daoDestinatarioInterface.guardar(destinatarioDoc);
		}

		for(DTODocumentoCcpEntity ccpDoc : listaCcp) {
			ccpDoc.setIdDocumento(dtoDocumentoOf.getIdDocumento());
			daoDocumentoCcpInterface.guardar(ccpDoc);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#recuperarRemitentesExt(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia)
	 */
	@Override
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExt(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia) {
		return daoRemitentesExternosOfInterface.consultarRemitenteAreaNombreDependencia(idArea, tipoArea, nombreRemitente, dependencia);
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.AsOficialiaPartesInterface#recuperarDocumento(Integer idDocumento)
	 */	@Override
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	public DTODocumentoEntity recuperarDocumento(Integer idDocumento) {
		return daoDocumentoInterface.consultarDocumento(idDocumento);
	}

}
