package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.List;

import mx.ine.acuerdos.bsd.BSDSeguimientoPuntosCGInterface;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.form.HelperSeguimientoPunto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBSeguimientoPuntosCG extends MBGeneric implements Serializable {
	private static final long serialVersionUID = 7939981974628787174L;

	@Autowired
	@Qualifier("bsdSegPuntosCG")
	private transient BSDSeguimientoPuntosCGInterface bsdSegPuntosCG;

	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;
	
	private HelperSeguimientoPunto helpSegPunto;
	
	public void init(String idNumAcuerdo, String numeralia) {
		helpSegPunto = new HelperSeguimientoPunto();
		Integer numeraliaAux = Integer.parseInt(numeralia);

		helpSegPunto.setAcuerdo( bsdSegPuntosCG.recuperarAcuerdo(idNumAcuerdo) );
		helpSegPunto.setPunto( bsdSegPuntosCG.recuperarPunto(idNumAcuerdo, numeraliaAux) );
		helpSegPunto.setClasificacion( bsdSegPuntosCG.recuperarClasificacion(
									   helpSegPunto.getPunto().getIdClasificacion()) );
		helpSegPunto.setAccionesSegPunto( bsdSegPuntosCG.recuperarSeguimiento(helpSegPunto.getPunto()) );
		helpSegPunto.setAreasInvolucradas( bsdSegPuntosCG.determinarAreasInvoluc(helpSegPunto.getAccionesSegPunto()) );
		helpSegPunto.setResponsConjInvoluc( bsdSegPuntosCG.determinarResponsConjInvoluc(helpSegPunto.getAccionesSegPunto()) );
		helpSegPunto.setAreasInvolucPunto( bsdSegPuntosCG.recuperarAreasInvolucPunto(helpSegPunto.getAreasInvolucradas()) );
		helpSegPunto.setResposInvolucPunto( bsdSegPuntosCG.recuperarResponsInvolucPunto(helpSegPunto.getAreasInvolucradas()) );
		helpSegPunto.setArbolResponsables( bsdSegPuntosCG.construirArbolRespons(helpSegPunto));
		helpSegPunto.setEstatusPuntos( bsdSegPuntosCG.recuperarEstatusPuntos() );

		// Para soportar la vista compartida (usuario CG, responsable de área
		helpSegPunto.setDtoResponsable( bsdSegPuntosCG.recuperarDtoResponsable(obtenUsuario().getUsuario()) );
		helpSegPunto.setEsVistaCompartida( bsdSegPuntosCG.recuperarVistaCompartida(helpSegPunto,
																				   obtenUsuario().getRolUsuario()) );
		helpSegPunto.setTiposMovimiento( bsdSegPuntosCG.generarTiposMovimiento(helpSegPunto.getAccionesSegPorArea()) );

		mbAdmin.setImgsInfografias(bsdSegPuntosCG.recuperaImgsInfografias());
	}

	public boolean esRolCaptura() {
		return bsdSegPuntosCG.validarRolCaptura(obtenUsuario().getRolUsuario());
	}

	public HelperSeguimientoPunto getHelpSegPunto() {
		return helpSegPunto;
	}

	public List<DTOSeguimientosCG> getSegActualPorArea() {
		return bsdSegPuntosCG.determinarSegActualPorArea(helpSegPunto);
	}
	
	public List<DTOSeguimientosCG> getSegPorArea(String idNumAcuerdo, Integer numeralia, Integer idArea) {
		return bsdSegPuntosCG.recuperarSeguimientoArea(idNumAcuerdo, numeralia, idArea);
	}
	
	public String getDescEstatus(Integer idEstatusPunto) {
		return bsdSegPuntosCG.recuperarDescEstatus(helpSegPunto, idEstatusPunto);
	}

	public String getDescSemaforo(DTOSeguimientosCG ultimoMov) {
		return bsdSegPuntosCG.recuperarDescSemaforo(helpSegPunto.getPunto(), ultimoMov);
	}

	public String getDescArea(Integer idArea) {
		return bsdSegPuntosCG.recuperarDescArea(idArea);
	}

	public void setMovModificable(DTOSeguimientosCG movModificable) {
		helpSegPunto.setMovModificable(movModificable);
	}

	public String reasignarPunto(DTOSeguimientosCG movModificable) {
		bsdSegPuntosCG.insertarMovReasignacion(helpSegPunto, movModificable);
		return "btnReasignarPuntoCG";
	}

	public String eliminarSegPunto(DTOSeguimientosCG movEliminable) {
		bsdSegPuntosCG.eliminarSegPunto(helpSegPunto, movEliminable);
		helpSegPunto.setAccionesSegPunto( bsdSegPuntosCG.recuperarSeguimiento(helpSegPunto.getPunto()) );
		helpSegPunto.setAccionesSegPorArea(bsdSegPuntosCG.determinarSegActualPorArea(helpSegPunto));
		bsdSegPuntosCG.actualizarEstatusPunto(helpSegPunto, movEliminable);
		bsdSegPuntosCG.actualizarEstatusAcuerdo(helpSegPunto,
												helpSegPunto.getAcuerdo().getIdNumAcuerdo());
		return "btnAceptarRechazoCG";
	}

	public String aceptarCierrePunto(DTOSeguimientosCG movModificable) {
		bsdSegPuntosCG.insertarMovAceptacion(helpSegPunto, movModificable);
		bsdSegPuntosCG.actualizarEstatusAcuerdo(helpSegPunto,
												helpSegPunto.getAcuerdo().getIdNumAcuerdo());
		return "btnAceptarCierreCG";
	}

	public String rechazarCierrePunto(DTOSeguimientosCG movModificable) {
		bsdSegPuntosCG.insertarMovRechazo(helpSegPunto, movModificable);
		return "btnRechazarCierreCG";
	}

	public void limpiarDialogs() {
		helpSegPunto.setDescMovimiento("");
		helpSegPunto.setDescRechazoCierre("");
	}

	public String insertarMovimiento() {
		bsdSegPuntosCG.insertarMovimiento(helpSegPunto);
		return "btnGuardarRefrescar";
	}

}
