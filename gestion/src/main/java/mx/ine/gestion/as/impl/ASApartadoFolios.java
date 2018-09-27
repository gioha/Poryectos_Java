/**
 * @(#)ASApartadoFolios.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASApartadoFoliosInterface;
import mx.ine.gestion.bo.inter.BOApartadoFoliosInterface;
import mx.ine.gestion.dao.inter.DAOAcronimoInterface;
import mx.ine.gestion.dao.inter.DAOApartadoFoliosInterface;
import mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOEstadosInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase que contiene las llamadas a los DAO´s que se utilizan en el módulo de Apartado de Folios
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
@Scope("prototype")
@Component("asApartadoFolios")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASApartadoFolios implements ASApartadoFoliosInterface {

	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(ASApartadoFoliosInterface.class);

	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daocTipoAreaInterface;

	@Autowired
	@Qualifier("daoApartadoFolios")
	private DAOApartadoFoliosInterface daoApartadoFoliosInterface;

	@Autowired
	@Qualifier("daoAcronimo")
	private DAOAcronimoInterface daoAcronimoInterface;
	
	@Autowired
	@Qualifier("daoApartadoNumDoc")
	private DAOApartadoNumDocInterface daoApartadoNumDocInterface;

	@Autowired
	@Qualifier("boApartadoFolios")
	private BOApartadoFoliosInterface boApartadoFoliosInterface;

	@Autowired
	@Qualifier("daoEstados")
	private DAOEstadosInterface daoEstadosInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerListaDeFoliosApartadosLazy(
	 * mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOApartadoNumDocEntity> obtenerListaDeFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros, 
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		return this.daoApartadoFoliosInterface.consultarFoliosApartadosLazy(filtros, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosColumna);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerTotalFoliosApartadosLazy(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public Integer obtenerTotalFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros) {

		return this.daoApartadoFoliosInterface.consultarTotalFoliosApartadosLazy(filtros);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerInfoTipoAreas(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoTipoAreas(DTOFiltrosApartadoFolioHelper filtros) {

		List<DTOCTipoAreaEntity> listaTipoAreas = this.daocTipoAreaInterface.consultarTodosOrdenadosAscPor("descripcion");
		filtros.setListaTiposArea(listaTipoAreas);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerInfoAreas(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoAreas(DTOFiltrosApartadoFolioHelper filtros) {

		Integer idEntidad = filtros.getIdEstado() == null ? 0 : filtros.getIdEstado();
		
		List<DTOCAreaEntity> listaAreas = this.daoApartadoFoliosInterface.consultarAreasParaApartado(filtros.getTipoArea(), idEntidad);
		filtros.setListaAreas(listaAreas);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerInfoTipoDocumentos(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoTipoDocumentos(DTOFiltrosApartadoFolioHelper filtros) {

		List<DTOCTipoDocumentoEntity> listaTipoDocumentos = this.daoApartadoFoliosInterface.consultarTiposDocumentoParaApartado(filtros.getIdArea(), filtros.getTipoArea());
		filtros.setListaTiposDocumento(listaTipoDocumentos);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerInfoEntidades(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoEntidades(DTOFiltrosApartadoFolioHelper filtros) {

		List<DTOEstadosEntity> listaEstados = this.daoEstadosInterface.consultarEstadosSinOC();
		filtros.setListaEstados(listaEstados);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#obtenerInfoActonimos(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoActonimos(DTOFiltrosApartadoFolioHelper filtros) {

		DTOCAreaEntity area = new DTOCAreaEntity();
		area.setIdArea(filtros.getIdArea());
		area.setTipoArea(filtros.getTipoArea());
		
		DTOCTipoDocumentoEntity tipo = new DTOCTipoDocumentoEntity();
		tipo.setIdTipoDocumento(filtros.getIdTipoDocumento());
		
		List<DTOAcronimoEntity> listaAcronimos = this.daoAcronimoInterface.consultarAcronimosXAreaXTipoDocumento(area.getTipoArea(), area.getIdArea(), tipo.getIdTipoDocumento());
		filtros.setListaAcronimos(listaAcronimos);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#capturarApartadoFolios(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void capturarApartadoFolios(DTOFiltrosApartadoFolioHelper filtrosSeleccionados) throws Exception {

		List<DTOApartadoNumDocEntity> listaFoliosApartados = new ArrayList<DTOApartadoNumDocEntity>();
		
		for (int contadorFolios = 0; contadorFolios < Integer.valueOf(filtrosSeleccionados.getNumeroFoliosAApartar()); contadorFolios++) {

			//1- Insertamos los registros dependiendo del número de folios que se desee apartar
			this.daoApartadoNumDocInterface.capturarApartadoFolios(filtrosSeleccionados.getTipoArea(), filtrosSeleccionados.getIdArea(), 
				filtrosSeleccionados.getIdTipoDocumento(), filtrosSeleccionados.getIdAcronimo(), Utilidades.mensajeProperties("constante_tipoApartado_fisico"));
			
			//2.- Consultamos el registro insertado
			DTOApartadoNumDocEntity registroGenerado = this.daoApartadoNumDocInterface.consultarUltimoRegistroUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
			
			//3.- Buscamos la información completa del acronimo
			DTOAcronimoID idAcronimo = new DTOAcronimoID();
			idAcronimo.setTipoArea(filtrosSeleccionados.getTipoArea());
			idAcronimo.setIdArea(filtrosSeleccionados.getIdArea());
			idAcronimo.setIdTipoDocumento(filtrosSeleccionados.getIdTipoDocumento());
			idAcronimo.setIdAcronimo(filtrosSeleccionados.getIdAcronimo());
			DTOAcronimoEntity acronimo = this.daoAcronimoInterface.buscarPorId(idAcronimo);

			//4.- Procesamos el acronimo para generar el número de documento
			String numeroDocumento = this.boApartadoFoliosInterface.procesarInfoParaCrearNumeroDocumento(acronimo.getAcronimoAgrupacion(), registroGenerado.getIdNumeroDocumento());
			log.info("NUMERO GENERADO: " + numeroDocumento + " CONTADOR: " + contadorFolios);

			//5.- Actualizamos el número de documento pues este se muestra en pantalla y se utilizara en el documento que se cree
			registroGenerado.setNumDocumento(numeroDocumento);
			this.daoApartadoNumDocInterface.modificar(registroGenerado);
			
			//6.-Guardamos el folio apartado (lo vamos a ocupar para mostrar en pantalla quienes son los recien apartados)
			listaFoliosApartados.add(registroGenerado);
		}
		
		filtrosSeleccionados.setListaApartadosRecientes(listaFoliosApartados);
		
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASApartadoFoliosInterface#deshabilitarFoliosApartados(mx.ine.gestion.dto.db.DTOApartadoNumDocEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void deshabilitarFoliosApartados(DTOApartadoNumDocEntity folioApartado) throws Exception {

		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		folioApartado.setIdPersonaElimino(usuarioLogueado.getIdPersona());
		folioApartado.setFechaEliminacion(new Date());

		this.daoApartadoNumDocInterface.modificar(folioApartado);
	}
}
