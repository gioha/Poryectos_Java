package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASCapturaCorresponsalInterface;
import mx.ine.gestion.as.inter.ASCapturarDocumentoInterface;
import mx.ine.gestion.bsd.inter.BSDCapturaCorresponsalInterface;
import mx.ine.gestion.dto.db.DTOCorresponsales;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS de lo corresponsales del sistema de Gestión
 *
 * @author Luis Urrutia
 * @since 16/11/2017
 */
@Component("bsdCapturaCorresponsal")
@Scope("prototype")
public class BSDCapturaCorresponsal implements BSDCapturaCorresponsalInterface {
	
	@Autowired
	@Qualifier("asCapturarDocumento")
	private ASCapturarDocumentoInterface	asCapturarDocumento;
	
	@Autowired
	@Qualifier("asCapturaCorresponsal")
	private ASCapturaCorresponsalInterface asCapturaCorresponsal;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturaCorresponsalInterface#buscarCorresponsalesCandidatos(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> buscarCorresponsalesCandidatos(String nombre, String apellido, Integer idPersona, Integer idArea, Integer tipoArea) {
		return asCapturaCorresponsal.buscarCorresponsalesCandidatos(nombre, apellido, idPersona, idArea, tipoArea);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturaCorresponsalInterface#buscarCorresponsales(java.lang.Integer)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> buscarCorresponsales(Integer idPersona){
		return asCapturaCorresponsal.buscarCorresponsales(idPersona);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturaCorresponsalInterface#eliminarCorresponsal(java.lang.Integer)
	 */
	@Override
	public void eliminarCorresponsal(Integer idCorresponsal) throws Exception {
		asCapturaCorresponsal.eliminarCorresponsal(idCorresponsal);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDCapturaCorresponsalInterface#agregarCorresponsal(DTOCorresponsales)
	 */
	@Override
	public void agregarCorresponsal(DTOCorresponsales corresponsal) throws Exception {
		asCapturaCorresponsal.agregarCorresponsal(corresponsal);
	}

}
