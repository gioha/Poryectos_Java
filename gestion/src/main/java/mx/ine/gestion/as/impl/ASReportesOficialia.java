/**
 * @(#)ASReportesOficialia.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.as.inter.ASReportesOficialiaInterface;
import mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCCategoriasInterface;
import mx.ine.gestion.dao.inter.DAOCSeccionesInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOCTipoDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOCapturaDocumentosInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface;
import mx.ine.gestion.dao.inter.DAOReportesOficialiaInterface;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOReportesOficialia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de gestionar los objetos DAO utilizados para soportar la
 * funcionalidad de la vista de Reportes de Oficialía.
 * 
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
@Service("asReportesOficialia")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASReportesOficialia implements ASReportesOficialiaInterface {

	@Autowired
	@Qualifier("daoApartadosNumDocOf")
	private DAOApartadosNumDocOfInterface daoApartadosNumDocOf;

	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daoCTipoArea;

	@Autowired
	@Qualifier("daoCapturaDocumentos")
	private DAOCapturaDocumentosInterface daoCapturaDocs;

	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daoCAreas;

	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreas;

	@Autowired
	@Qualifier("daoCTipoDocumento")
	private DAOCTipoDocumentoInterface daoCTipoDoc;

	@Autowired
	@Qualifier("daoCSecciones")
	private DAOCSeccionesInterface daoCSecciones;

	@Autowired
	@Qualifier("daoCCategorias")
	private DAOCCategoriasInterface daoCCategorias;

	@Autowired
	@Qualifier("daoReportesOficialia")
	private DAOReportesOficialiaInterface daoReportesOficialia;
	
	@Autowired
	@Qualifier("daoRemitentesExternosOf")
	private DAORemitentesExternosOfInterface daoRemitentesExternos;

	@Autowired
	@Qualifier("daoOficialiasAreas")
	private DAOOficialiasAreasInterface daoOficialiasAreas;

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarFoliosDocOf()
	 */
	@Override
	public List<DTOApartadosNumDocOfEntity> recuperarFoliosDocOf() {
		return daoApartadosNumDocOf.consultarFoliosDocOf();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarTiposDocumento()
	 */
	public List<DTOCTipoDocumentoEntity> recuperarTiposDocumento() {
		return daoCTipoDoc.consultarTipoDocumentoConDocumento();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarSecciones()
	 */
	public List<DTOCSeccionesEntity> recuperarSecciones() {
		return daoCSecciones.consultarSecciones();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarCategorias(Integer idSeccion)
	 */
	public List<DTOCCategoriasEntity> recuperarCategorias(Integer idSeccion) {
		return daoCCategorias.consultarCategorias(idSeccion);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarTiposDeArea()
	 */
	@Override
	public List<DTOCTipoAreaEntity> recuperarTiposDeArea() {
		return daoCTipoArea.consultarTodosOrdenadosAscPor("descripcion");
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarEntidades()
	 */
	@Override
	public List<DTOEstadosEntity> recuperarEntidades() {
		return daoCapturaDocs.consultarEntidadesCapturadasEnGestion();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarAreas(Integer idTipoArea, Integer idEstado)
	 */
	@Override
	public List<DTOCAreaEntity> recuperarAreas(Integer idTipoArea, Integer idEstado) {
		int idEntidad = idEstado == null ? -1 : idEstado;
		return daoCAreas.consultarAreasConOrganigrama(idTipoArea, idEntidad);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarPersonas(String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> recuperarPersonas(String nombreRemitente, String apellidoRemitente,
															Integer idTipoAreaRemitente, Integer idAreaRemitente) {
		return daoEstructuraAreas.consultarPersonasXNombreXApellidosXArea(nombreRemitente, apellidoRemitente,
																		  idTipoAreaRemitente, idAreaRemitente);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarReporte(String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public List<DTOReportesOficialia> recuperarReporte(String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf) {
		if(rolUsuario.equals("GESTION4.OFICIALIA.OC") || rolUsuario.equals("ROLE_GESTION4.ADMIN.OC")) {
			return daoReportesOficialia.consultarReporteOficialia(filtrosReportesOf);
		} else {
			return new ArrayList<DTOReportesOficialia>();
		}
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarRemitentesExt(String nombreRemitenteExt, String dependenciaRemitenteExt)
	 */
	@Override
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExt(String nombreRemitenteExt, String dependenciaRemitenteExt) {
		return daoRemitentesExternos.consultarRemitenteNombreDependencia(nombreRemitenteExt, dependenciaRemitenteExt);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.ASReportesOficialiaInterface#recuperarAreas(String rolUsuario, Integer idOficialia)
	 */
	@Override
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia) {
		if(rolUsuario.equals("GESTION4.OFICIALIA.OC")) {
			return daoOficialiasAreas.consultarAreasOficialia(idOficialia);
		} else {
			return daoOficialiasAreas.consultarTodasLasAreasOficialia();
		}
	}

}
