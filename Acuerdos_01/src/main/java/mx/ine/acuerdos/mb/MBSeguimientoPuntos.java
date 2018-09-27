package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.bsd.BSDSeguimientoPuntosInterface;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.form.HelperSeguimientoPunto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBSeguimientoPuntos extends MBGeneric implements Serializable {
	private static final long serialVersionUID = 7939981974628787174L;

	@Autowired
	@Qualifier("bsdSegPuntos")
	private transient BSDSeguimientoPuntosInterface bsdSegPuntos;

	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;

	private HelperSeguimientoPunto helpSegPunto;
	
	public void init(String idNumAcuerdo, String numeralia) {
		helpSegPunto = new HelperSeguimientoPunto();
		Integer numeraliaAux = Integer.parseInt(numeralia);

		helpSegPunto.setAcuerdo( bsdSegPuntos.recuperarAcuerdo(idNumAcuerdo) );
		helpSegPunto.setPunto( bsdSegPuntos.recuperarPunto(idNumAcuerdo, numeraliaAux) );
		helpSegPunto.setClasificacion( bsdSegPuntos.recuperarClasificacion(
									   helpSegPunto.getPunto().getIdClasificacion()) );
		helpSegPunto.setAccionesSegPunto( bsdSegPuntos.recuperarSeguimientoArea(idNumAcuerdo, numeraliaAux,
				  						  bsdSegPuntos.determinarIdAreaEnResponsables(obtenUsuario().getUsuario())) );
		helpSegPunto.setAreasInvolucradas( bsdSegPuntos.determinarAreasInvoluc(helpSegPunto.getAccionesSegPunto()) );
		helpSegPunto.setAreasInvolucPunto( bsdSegPuntos.recuperarAreasInvolucPunto(helpSegPunto.getAreasInvolucradas()) );
		helpSegPunto.setResposInvolucPunto( bsdSegPuntos.recuperarResponsInvolucPunto(helpSegPunto.getAreasInvolucradas()) );
		helpSegPunto.setArbolResponsables( bsdSegPuntos.construirArbolRespons(helpSegPunto));
		helpSegPunto.setTiposMovimiento( bsdSegPuntos.generarTiposMovimiento(helpSegPunto.getAccionesSegPunto()) );
		helpSegPunto.setEstatusPuntos( bsdSegPuntos.recuperarEstatusPuntos() );

		mbAdmin.setImgsInfografias(bsdSegPuntos.recuperaImgsInfografias());
	}

	public boolean esRolValido() {
		return bsdSegPuntos.validarRolUsuario(obtenUsuario().getRolUsuario());
	}

	public boolean validarRolCG() {
		return bsdSegPuntos.validarRolCG(obtenUsuario().getRolUsuario());
	}
	
	public boolean tieneArea(){
		return bsdSegPuntos.bsdSegPuntos(obtenUsuario().getRolUsuario());
	}

	public boolean esRolCaptura() {
		return bsdSegPuntos.validarRolCaptura(obtenUsuario().getRolUsuario());
	}

	public HelperSeguimientoPunto getHelpSegPunto() {
		return helpSegPunto;
	}
	
	public String getDescEstatus(Integer idEstatusPunto) {
		return bsdSegPuntos.recuperarDescEstatus(helpSegPunto, idEstatusPunto);
	}

	public String getDescSemaforo(DTOSeguimientosCG ultimoMov) {
		return bsdSegPuntos.recuperarDescSemaforo(helpSegPunto.getPunto(), ultimoMov);
	}

	public String getDescArea(Integer idArea) {
		return bsdSegPuntos.recuperarDescArea(idArea);
	}

	public void activarDesactivarCheck(HelperSeguimientoPunto helpSegPunto) {
		bsdSegPuntos.activarDesactivarCierre(helpSegPunto);
	}

	public String insertarMovimiento() {
		bsdSegPuntos.insertarMovimiento(helpSegPunto);
		return "btnGuardarRegresar";
	}

}
