package mx.ine.gestion.bsd.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.ine.gestion.as.inter.ASCapturarDocumentoInterface;
import mx.ine.gestion.as.inter.ASEstructuraInterface;
import mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @(#)BSDCapturarDocumento.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase encargada de administrar el o los AS relacionados para la pantalla de
 * capturar documento.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
@Component("bsdCapturarDocumentos")
@Scope("prototype")
public class BSDCapturarDocumento implements BSDCapturarDocumentoInterface {

	@Autowired
	@Qualifier("asCapturarDocumento")
	private transient ASCapturarDocumentoInterface	asCapturarDocumento;
	
	@Autowired
	@Qualifier("asEstructura")
	private ASEstructuraInterface asEstructuraInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarTiposDocumentos()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos() {

		return asCapturarDocumento.consultarTiposDocumentos();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarAcronimos(java.lang.Integer)
	 */
	@Override
	public List<DTOAcronimoEntity> consultarAcronimos(Integer idTipoDocumento) {
		
		return asCapturarDocumento.consultarAcronimos(idTipoDocumento);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarPlantillas(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOPlantillaEntity> consultarPlantillas(Integer idTipoDocumento, Integer idAcronimo) {

		return asCapturarDocumento.consultarPlantillas(idTipoDocumento, idAcronimo);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarTiposArea()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTiposArea() {

		return asCapturarDocumento.consultarTiposArea();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarEntidades()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEntidades() {

		return asCapturarDocumento.consultarEntidades();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarAreas(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreas(Integer tipoArea, Integer idEstado) {

		return asCapturarDocumento.consultarAreas(tipoArea, idEstado);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarAreasDestinatarias(java.lang.String)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasDestinatarias(String nombreArea){
		
		String areaName = "";
		
		// Procesamos la cadena del nombre del área para evitar errores de busqueda a causa de los espacios en blanco
		
		String [] nombres = nombreArea.split(" ");
		
		int countAreaName = 0; 
		for (String n : nombres) {
			
			if( !n.equalsIgnoreCase(" ") && !n.equalsIgnoreCase("") ){
				String temp = n.trim();
				
				if ( countAreaName == 0 )
					areaName = temp;
				else
					areaName = areaName + " " + temp;
						
				countAreaName ++;
			}
		}
		
		return  asCapturarDocumento.consultarAreasDestinatarias(areaName);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#consultarPersonas(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonas(Integer tipoArea, Integer idArea, String nombre, String apellidos) {
		
		String name = "";
		String lastName = "";
		
		// Procesamos la cadena del nombre para evitar errores de busqueda a causa de los espacios en blanco
		
		String [] nombres = nombre.split(" ");
		
		int countName = 0; 
		for (String n : nombres) {
			
			if( !n.equalsIgnoreCase(" ") && !n.equalsIgnoreCase("") ){
				String temp = n.trim();
				
				if ( countName == 0 )
					name = temp;
				else
					name = name + " " + temp;
						
				countName ++;
			}
		}

		// Procesamos la cadena de los apellidos para evitar errores de busqueda a causa de los espacios en blanco 
		
		String [] apellidosA = apellidos.split(" ");
		
		int countLastName = 0;
		for (String a : apellidosA) {
			
			if( !a.equalsIgnoreCase(" ") && !a.equalsIgnoreCase("") ){
				String temp = a.trim();
				
				if ( countLastName == 0 )
					lastName = temp;
				else
					lastName = lastName + " " + temp;
						
				countLastName ++;
			}
		}
		
		
		return asCapturarDocumento.consultarPersonas(tipoArea, idArea, name, lastName);		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#guardarDocumento(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public Integer guardarDocumento(DTOFiltrosCapturaDocumentoHelper filtros) throws Exception {
		
		List<DTOJerarquiaPersonasEntity> destinatariosArea = new ArrayList<DTOJerarquiaPersonasEntity>(); 
		
		// revisamos si el documento tiene destinatarios como áreas, y si si, traemos a los titulares de esas áreas
		if( filtros.getDestinatariosComoArea() != null && filtros.getDestinatariosComoArea().size() > 0 ){
			
			List<DTOJerarquiaPersonasEntity> titularesBD = new ArrayList<DTOJerarquiaPersonasEntity>(); 
			titularesBD = asEstructuraInterface.consultarTitulares();
			
			for (DTOCAreaEntity destinatarioArea : filtros.getDestinatariosComoArea()){
				for (DTOJerarquiaPersonasEntity titular : titularesBD) {

					if( destinatarioArea.getIdArea().equals( titular.getIdArea() ) ){
						destinatariosArea.add(titular);
						break;
					}
				}
			}
		}
		

		return asCapturarDocumento.guardarDocumento(filtros, destinatariosArea);
	}

	@Override
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOBandejaEAtencionEntity atencion)
			throws Exception {
		asCapturarDocumento.guardaRespuestaTurnado(filtroResopnder,atencion);
	}

	@Override
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOBandejaERecibidosEntity recibido) throws Exception {
		asCapturarDocumento.guardaRespuestaTurnado(filtroResopnder,recibido);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#guardaRespuestaTurnado(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity)
	 */
	@Override
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOHBandejaEAtencionEntity atencion) throws Exception {
		asCapturarDocumento.guardaRespuestaTurnado(filtroResopnder, atencion);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface#guardaRespuestaTurnado(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity)
	 */
	@Override
	public void guardaRespuestaTurnado(DTOResponderTurnadoHelper filtroResopnder, DTOHBandejaERecibidosEntity recibido) throws Exception {
		asCapturarDocumento.guardaRespuestaTurnado(filtroResopnder, recibido);
		
	}
}
