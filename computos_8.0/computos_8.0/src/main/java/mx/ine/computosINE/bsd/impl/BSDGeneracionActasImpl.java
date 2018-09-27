package mx.ine.computosINE.bsd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.computosINE.as.ASGeneracionActasInterface;
import mx.ine.computosINE.bsd.BSDGeneracionActasInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCConsejo;
import mx.ine.computosINE.dto.DTOConsejero;
import mx.ine.computosINE.dto.DTORepresentante;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.org.ine.servicios.dto.DTOBase;

@Component("bsdGeneracionActas")
@Scope("prototype")
public class BSDGeneracionActasImpl implements BSDGeneracionActasInterface {

	@Autowired
	@Qualifier("asGeneracionActas")
	ASGeneracionActasInterface asActas;

	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public void guardaActa(DTOBase acta) throws Exception{
		asActas.guardaActualizaActa(acta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminaActa(DTOActaTipoCandidatura acta) {
		asActas.eliminaActa(acta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardaRepresentante(DTORepresentante representante) throws Exception{
		asActas.guardaRepresentante(representante);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardaConsejero(DTOConsejero consejero) throws Exception{
		asActas.guardaConsejero(consejero);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DTOCConsejo> getCatalogoConsejero() {
		return asActas.getCatalogoConsejeros();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DTOBase getActa(Object pkActa) {
		return asActas.getActa(pkActa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DTOConsejero> getConsejeros(Object pkActa){
		return asActas.getConsejeros(pkActa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DTORepresentante> getRepresentantes(
			Object pkActa) {
		return  asActas.getRepresentantes(pkActa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(DTOActaTipoCandidaturaPK pkActa) throws Exception {
		asActas.setEditable(pkActa);
	}

	@Override
	public void eliminandoActa(DTOActaTipoCandidaturaPK pkActa) {
		asActas.eliminandoActa(pkActa);
		
	}

	@Override
	public void eliminaConsejeros(List<DTOConsejero> actaConsejeros) {
		asActas.eliminaConsejeros(actaConsejeros);
		
	}

	@Override
	public void eliminaRepresentantes(List<DTORepresentante> actaRepresentante) {
		asActas.eliminaRepresentantes(actaRepresentante);
		
	}

	@Override
	public List<Object[]> consultaDistritosConSusMunicipios(
			Integer idTipoCandidatura) throws Exception {
		// TODO Auto-generated method stub
		return asActas.consultaDistritosConSusMunicipios(idTipoCandidatura);
	}

	@Override
	public List<Integer> consultaIdAsociacionesDipRPEstatal() throws Exception {
		return asActas.consultaIdAsociacionesDipRPEstatal();
	}

	@Override
	public List<Integer> consultaDistritosLocalesPorMunicipio(
			DTOUsuarioLogin usuario) throws Exception {
		return asActas.consultaDistritosLocalesPorMunicipio(usuario);
	}

	@Override
	public List<Integer> consultaDemarcacionesPorMR(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer idSeleccion)
			throws Exception {
		return asActas.consultaDemarcacionesPorMR(idTipoCandidatura, usuario, idSeleccion);
	}

	@Override
	public List<Integer> consultaDistritosPorMR(Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer idSeleccion) throws Exception {
		return asActas.consultaDistritosPorMR(idTipoCandidatura, usuario, idSeleccion);
	}

	@Override
	public boolean tieneEspeciales(Integer idTipoCandidatura,
			Integer idSeleccion) {
		return asActas.tieneEspeciales(idTipoCandidatura, idSeleccion);
	}

	@Override
	public boolean isGeneradasDiputadosRPFinal() {
		return asActas.isGeneradasDiputadosRPFinal();
	}
	
	
	
	
	
}