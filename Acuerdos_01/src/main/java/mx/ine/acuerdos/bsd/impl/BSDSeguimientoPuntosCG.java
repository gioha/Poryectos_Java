package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASSeguimientoPuntosInterface;
import mx.ine.acuerdos.bsd.BSDSeguimientoPuntosCGInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOCEstatusPuntos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.HelperDTOTipoMov;
import mx.ine.acuerdos.dto.helper.form.HelperSeguimientoPunto;
import mx.ine.acuerdos.util.Constantes;

@Component("bsdSegPuntosCG")
@Scope("prototype")
public class BSDSeguimientoPuntosCG implements BSDSeguimientoPuntosCGInterface, Serializable {
	private static final long serialVersionUID = -5974751018494675849L;
	private static final Log log = LogFactory.getLog(BSDSeguimientoPuntosCG.class);
	
	@Autowired
	@Qualifier("asSegPuntos")
	private transient ASSeguimientoPuntosInterface asSegPuntos;

	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;

	public DTOAcuerdos recuperarAcuerdo(String idNumAcuerdo) {
		try {
			return asSegPuntos.recuperarAcuerdo(idNumAcuerdo);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarAcuerdo: " + e.getMessage());
			return null;
		}
	}

	public DTOPuntosAcuerdo recuperarPunto(String idNumAcuerdo, Integer numeralia) {
		try {
			return asSegPuntos.recuperarPunto(idNumAcuerdo, numeralia);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarPunto: " + e.getMessage());
			return null;
		}
	}

	public DTOCClasificaciones recuperarClasificacion(Integer idClasificacion) {
		try {
			return asSegPuntos.recuperarClasificacion(idClasificacion);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarClasificacion: " + e.getMessage());
			return null;
		}
	}

	public List<DTOSeguimientosCG> recuperarSeguimiento(DTOPuntosAcuerdo dtoPunto) {
		try {
			return asSegPuntos.recuperarSeguimiento(dtoPunto);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarSeguimiento: " + e.getMessage());
			return null;
		}
	}

	public List<DTOSeguimientosCG> recuperarSeguimientoArea(String idNumAcuerdo, Integer numeralia, Integer idArea) {
		try {
			return asSegPuntos.recuperarSeguimientoArea(idNumAcuerdo, numeralia, idArea);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarSeguimientoArea: " + e.getMessage());
			return null;
		}
	}
	
	public List<Integer> determinarAreasInvoluc(List<DTOSeguimientosCG> accionesSegPunto) {
		List<Integer> areasInvolucradas = new ArrayList<Integer>();
		
		for (DTOSeguimientosCG dtoSeguimiento : accionesSegPunto) {
			if(!areasInvolucradas.contains(dtoSeguimiento.getId().getIdArea())) {
				areasInvolucradas.add(dtoSeguimiento.getId().getIdArea());
			}
		}
		
		return areasInvolucradas;
	}

	public List<Integer> determinarResponsConjInvoluc(List<DTOSeguimientosCG> accionesSegPunto) {
		List<Integer> responsConjInvoluc = new ArrayList<Integer>();
		
		for (DTOSeguimientosCG dtoSeguimiento : accionesSegPunto) {
			if(!responsConjInvoluc.contains(dtoSeguimiento.getResponsabilidadConjunta())) {
				responsConjInvoluc.add(dtoSeguimiento.getResponsabilidadConjunta());
			}
		}
		
		return responsConjInvoluc;
	}

	public List<DTOCAreas> recuperarAreasInvolucPunto(List<Integer> areasInvolucradas) {
		List<DTOCAreas> areasInvolucPunto = new ArrayList<DTOCAreas>();
		
		for (Integer idArea : areasInvolucradas) {
			try {
				areasInvolucPunto.add(asSegPuntos.recuperarAreaInvolucrada(idArea));
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.recuperarAreasInvolucPunto: " + e.getMessage());
				return null;
			}
		}
		
		return areasInvolucPunto;
	}

	public List<DTOResponsables> recuperarResponsInvolucPunto(List<Integer> areasInvolucradas) {
		List<DTOResponsables> responsInvolucPunto = new ArrayList<DTOResponsables>();
		for (Integer idArea : areasInvolucradas) {
			try {
				responsInvolucPunto.addAll(asSegPuntos.recuperarResponsInvolucrado(idArea));
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.recuperarResponsInvolucPunto: " + e.getMessage());
				return null;
			}
		}
		
		return responsInvolucPunto;
	}

	public TreeNode construirArbolRespons(HelperSeguimientoPunto helpSegPunto) {
		int regMasActual = 0;
		List<Integer> areasConjunta;
		TreeNode raizArbol = new DefaultTreeNode("Raiz del Arbol", null);
		Integer responsConjAux;
		
		if(helpSegPunto.getResponsConjInvoluc().size() > 1) {
			Collections.sort(helpSegPunto.getResponsConjInvoluc());
			if(helpSegPunto.getResponsConjInvoluc().get(regMasActual).equals(0)) {
				responsConjAux = helpSegPunto.getResponsConjInvoluc().remove(0);
				helpSegPunto.getResponsConjInvoluc().add(responsConjAux);
			}
		}

		for(Integer responsConjunta : helpSegPunto.getResponsConjInvoluc()) {
			areasConjunta = new ArrayList<Integer>();

			for(DTOSeguimientosCG dtoSeguimiento : helpSegPunto.getAccionesSegPunto()) {
				if(responsConjunta.equals(dtoSeguimiento.getResponsabilidadConjunta()) &&
					!areasConjunta.contains(dtoSeguimiento.getId().getIdArea())) {
					areasConjunta.add(dtoSeguimiento.getId().getIdArea());
				}
			}

			if(responsConjunta.equals(0)) {

				for(Integer areaConjunta : areasConjunta) {
					for(DTOCAreas area : helpSegPunto.getAreasInvolucPunto()) {
						if(areaConjunta.equals(area.getId().getIdArea())) {
							TreeNode nodoArea = new DefaultTreeNode(area.getDescripcion(), raizArbol);
							for(DTOResponsables responsable : helpSegPunto.getResposInvolucPunto()) {
								if(area.getId().getIdArea().equals(responsable.getIdArea())) {
									nodoArea.getChildren().add(new DefaultTreeNode(responsable.getNombre() +
																				   " " + responsable.getApellidos() +
																				   " (" + responsable.getPuesto() + ")", nodoArea));
								}
							}
							nodoArea.setExpanded(false);
						}
					}
				}

			} else {

				TreeNode nodoRespons = new DefaultTreeNode("Responsabilidad Conjunta " + responsConjunta, raizArbol);
				for(Integer areaConjunta : areasConjunta) {
					for(DTOCAreas area : helpSegPunto.getAreasInvolucPunto()) {
						if(areaConjunta.equals(area.getId().getIdArea())) {
							TreeNode nodoArea = new DefaultTreeNode(area.getDescripcion(), nodoRespons);
							for(DTOResponsables responsable : helpSegPunto.getResposInvolucPunto()) {
								if(area.getId().getIdArea().equals(responsable.getIdArea())) {
									nodoArea.getChildren().add(new DefaultTreeNode(responsable.getNombre() +
																				   " " + responsable.getApellidos() +
																				   " (" + responsable.getPuesto() + ")", nodoArea));
								}
							}
							nodoArea.setExpanded(false);
						}
					}
				}
				nodoRespons.setExpanded(true);

			}
		}

		return raizArbol;
	}

	public List<DTOSeguimientosCG> determinarSegActualPorArea(HelperSeguimientoPunto helpSegPunto) {
		List<DTOSeguimientosCG> accionesSegPorArea = new ArrayList<DTOSeguimientosCG>();
		
		for(Integer idArea : helpSegPunto.getAreasInvolucradas()) {
			for(DTOSeguimientosCG movimiento : helpSegPunto.getAccionesSegPunto()) {
				if(idArea.equals(movimiento.getId().getIdArea())) {
					accionesSegPorArea.add(movimiento);
					break;
				}
			}
		}
		
		return accionesSegPorArea;
	}

	public List<DTOCEstatusPuntos> recuperarEstatusPuntos() {
		try {
			return asSegPuntos.recuperarEstatusPuntos();
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarEstatusPuntos: " + e.getMessage());
			return null;
		}
	}

	public String recuperarDescEstatus(HelperSeguimientoPunto helpSegPunto, Integer idEstatusPunto) {
		String descEstatus = "";

		for(DTOCEstatusPuntos estatusPunto : helpSegPunto.getEstatusPuntos()) {
			if(idEstatusPunto.equals(estatusPunto.getIdEstatusPunto())) {
				descEstatus = estatusPunto.getDescripcion();
				break;
			}
		}
		
		return descEstatus;
	}

	public String recuperarDescSemaforo(DTOPuntosAcuerdo dtoPunto, DTOSeguimientosCG ultimoMov) {
		List<DTOSeguimientosCG> segPorArea = new ArrayList<DTOSeguimientosCG>();
		String descSemaforo = "";
		Date fechaMovimiento;
		Date fechaActual = new Date();
		double diasNaturales = 30.4;
		double diasTranscurridos;

		try {
			segPorArea = recuperarSeguimientoArea(ultimoMov.getId().getIdNumAcuerdos(),
												  ultimoMov.getId().getNumeralia(),
												  ultimoMov.getId().getIdArea());
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarDescSemaforo: " + e.getMessage());
		}

		fechaMovimiento = segPorArea.get(segPorArea.size()-1).getId().getFechaMovimiento();
		diasTranscurridos = (double) ((fechaActual.getTime()-fechaMovimiento.getTime()) / (24*60*60*1000));

		if(ultimoMov.getId().getIdEstatusPunto().equals(1) ||
		   ultimoMov.getId().getIdEstatusPunto().equals(3) ||
		   ultimoMov.getId().getIdEstatusPunto().equals(4) ||
		   ultimoMov.getId().getIdEstatusPunto().equals(6)) {

			if(dtoPunto.getNotificacion().intValue() == 0) {
        		descSemaforo = "Asignado_MesN";
        	} else {
        		if(diasTranscurridos >= 0 && diasTranscurridos <= diasNaturales*1) {
        			descSemaforo = "Asignado_Mes1";
        		}
        		if(diasTranscurridos > diasNaturales*1 && diasTranscurridos <= diasNaturales*2) {
        			descSemaforo = "Asignado_Mes2";
        		}
        		if(diasTranscurridos > diasNaturales*2) {
        			descSemaforo = "Asignado_Mes3";
        		}
        	}
		}

		if(ultimoMov.getId().getIdEstatusPunto().equals(2)) {
			descSemaforo = "Rechazado_MesN";
		}

		if(ultimoMov.getId().getIdEstatusPunto().equals(5)) {
			descSemaforo = "Finalizado_MesN";
		}

		if(ultimoMov.getId().getIdEstatusPunto().equals(7)) {
			descSemaforo = "Validado_MesN";
		}
		
		return descSemaforo;
	}

	public String recuperarDescArea(Integer idArea) {
		try {
			return asSegPuntos.recuperarArea(idArea).getDescripcion();
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarDescArea: " + e.getMessage());
			return null;
		}
	}

	public void insertarMovReasignacion(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movModificable) {
		Integer idRespConjunta = movModificable.getResponsabilidadConjunta();
		List<DTOSeguimientosCG> segRespConjunta = new ArrayList<DTOSeguimientosCG>();
		List<Integer> areasRespConjunta = new ArrayList<Integer>();
		Integer idEstatusPunto = 3;		// Estatus reasignado (ID 3)

		helpSegPunto.setDescMovimiento(reemplazarComillasEstilizadas(helpSegPunto.getDescMovimiento()));

		if(idRespConjunta.equals(0)) {
			segRespConjunta.add(movModificable);
		} else {
			try {
				segRespConjunta =  asSegPuntos.recuperarRespConj( movModificable.getId().getIdNumAcuerdos(),
																  movModificable.getId().getNumeralia(),
																  idRespConjunta );
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntosCG.insertarMovReasignacion: " + e.getMessage());
			}
		}

		for(DTOSeguimientosCG movimiento : segRespConjunta) {
			if(!areasRespConjunta.contains(movimiento.getId().getIdArea())) {
				areasRespConjunta.add(movimiento.getId().getIdArea());
				movimiento.getId().setFechaMovimiento(new Date());
				movimiento.getId().setIdEstatusPunto(idEstatusPunto);
				movimiento.setTipoMovimiento(0);	// Ya no importa el tipo de movimiento
				movimiento.setDescripcion(helpSegPunto.getDescMovimiento());
				try {
					asSegPuntos.insertarMovimiento(movimiento);
					helpSegPunto.setMsjMovimiento("regInsertMovimiento");
				} catch (Exception e) {
					log.error("BSDSeguimientoPuntos.insertarMovReasignacion: " + e.getMessage());
				}
			}
		}
	}

	public void eliminarSegPunto(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movEliminable) {
		Integer idRespConjunta = movEliminable.getResponsabilidadConjunta();
		List<DTOSeguimientosCG> segRespConjunta = new ArrayList<DTOSeguimientosCG>();

		if(idRespConjunta.equals(0)) {
			try {
				segRespConjunta =  asSegPuntos.recuperarSeguimientoArea(movEliminable.getId().getIdNumAcuerdos(),
																		movEliminable.getId().getNumeralia(),
																		movEliminable.getId().getIdArea()); 
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntosCG.eliminarSegPunto: " + e.getMessage());
			}
		} else {
			try {
				segRespConjunta =  asSegPuntos.recuperarRespConj( movEliminable.getId().getIdNumAcuerdos(),
																  movEliminable.getId().getNumeralia(),
																  idRespConjunta );
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntosCG.eliminarSegPunto: " + e.getMessage());
			}
		}

		for(DTOSeguimientosCG movimiento : segRespConjunta) {
			try {
				asSegPuntos.eliminarSegPunto(movimiento);
				helpSegPunto.setMsjMovimiento("regDeleteSeguimiento");
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.eliminarSegPunto: " + e.getMessage());
			}
		}
	}

	public void insertarMovAceptacion(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movModificable) {
		Integer idRespConjunta = movModificable.getResponsabilidadConjunta();
		List<DTOSeguimientosCG> segRespConjunta = new ArrayList<DTOSeguimientosCG>();
		List<Integer> areasRespConjunta = new ArrayList<Integer>();
		Integer idEstatusPunto = 7;		// Estatus validados (ID 7)

		if(idRespConjunta.equals(0)) {
			segRespConjunta.add(movModificable);
		} else {
			try {
				segRespConjunta =  asSegPuntos.recuperarRespConj( movModificable.getId().getIdNumAcuerdos(),
																  movModificable.getId().getNumeralia(),
																  idRespConjunta );
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntosCG.insertarMovAceptacion: " + e.getMessage());
			}
		}

		for(DTOSeguimientosCG movimiento : segRespConjunta) {
			if(!areasRespConjunta.contains(movimiento.getId().getIdArea())) {
				areasRespConjunta.add(movimiento.getId().getIdArea());
				movimiento.getId().setFechaMovimiento(new Date());
				movimiento.getId().setIdEstatusPunto(idEstatusPunto);
				movimiento.setTipoMovimiento(0);	// Ya no importa el tipo de movimiento
				movimiento.setDescripcion("El punto ha sido validado");
				try {
					asSegPuntos.insertarMovimiento(movimiento);
					helpSegPunto.setMsjMovimiento("regInsertMovimiento");
				} catch (Exception e) {
					log.error("BSDSeguimientoPuntosCG.insertarMovAceptacion: " + e.getMessage());
				}
			}
		}

		helpSegPunto.setStatusInsert("btnGuardarRegresar");
	}

	public void insertarMovRechazo(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movModificable) {
		Integer idRespConjunta = movModificable.getResponsabilidadConjunta();
		List<DTOSeguimientosCG> segRespConjunta = new ArrayList<DTOSeguimientosCG>();
		List<Integer> areasRespConjunta = new ArrayList<Integer>();
		Integer idEstatusPunto = 6;		// Estatus Reanudado (ID 6)

		helpSegPunto.setDescRechazoCierre(reemplazarComillasEstilizadas(helpSegPunto.getDescRechazoCierre()));

		if(idRespConjunta.equals(0)) {
			segRespConjunta.add(movModificable);
		} else {
			try {
				segRespConjunta =  asSegPuntos.recuperarRespConj( movModificable.getId().getIdNumAcuerdos(),
																  movModificable.getId().getNumeralia(),
																  idRespConjunta );
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntosCG.insertarMovAceptacion: " + e.getMessage());
			}
		}

		for(DTOSeguimientosCG movimiento : segRespConjunta) {
			if(!areasRespConjunta.contains(movimiento.getId().getIdArea())) {
				areasRespConjunta.add(movimiento.getId().getIdArea());
				movimiento.getId().setFechaMovimiento(new Date());
				movimiento.getId().setIdEstatusPunto(idEstatusPunto);
				movimiento.setTipoMovimiento(0);	// Ya no importa el tipo de movimiento
				movimiento.setDescripcion(helpSegPunto.getDescRechazoCierre());
				try {
					asSegPuntos.insertarMovimiento(movimiento);
					helpSegPunto.setMsjMovimiento("regInsertMovimiento");
				} catch (Exception e) {
					log.error("BSDSeguimientoPuntosCG.insertarMovRechazo: " + e.getMessage());
				}
			}
		}
	}

	public void actualizarEstatusPunto(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG ultimoMov) {
		DTOPuntosAcuerdo puntoPorActualizar = new DTOPuntosAcuerdo();

		// Si en el seguimiento sólo quedaba un área reportante
		if(helpSegPunto.getAccionesSegPorArea().size() == 0) {
			try {
				puntoPorActualizar = asSegPuntos.recuperarPuntoAcuerdo(ultimoMov.getId().getIdNumAcuerdos(),
																	   ultimoMov.getId().getNumeralia());
//				puntoPorActualizar.setActivo(0);	// Inserta 0 en caso de una baja lógica
				puntoPorActualizar.setNotificacion(0);		// Inserta 0 para indicar una limpieza en el campo
				asSegPuntos.actualizarPuntoAcuerdo(puntoPorActualizar);
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.actualizarEstatusPunto: " + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unused")
	public void actualizarEstatusAcuerdo(HelperSeguimientoPunto helpSegPunto, String idNumAcuerdo) {
		List<DTOPuntosAcuerdo> puntosAcuerdo = new ArrayList<DTOPuntosAcuerdo>();
		List<DTOSeguimientosCG> segNivelPunto = new ArrayList<DTOSeguimientosCG>();
		List<DTOSeguimientosCG> segNivelArea;
		List<Integer> areasInvolucradas = new ArrayList<Integer>();
		DTOAcuerdos acuerdo = new DTOAcuerdos();
		boolean cambioEstatus = false;

		try {
			puntosAcuerdo = asSegPuntos.recuperarPuntosAcuerdo(idNumAcuerdo);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.actualizarEstatusAcuerdo: " + e.getMessage());
		}

		for(DTOPuntosAcuerdo punto: puntosAcuerdo) {
			try {
				segNivelPunto = asSegPuntos.recuperarSeguimiento(punto);
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.actualizarEstatusAcuerdo: " + e.getMessage());
			}

			areasInvolucradas = determinarAreasInvoluc(segNivelPunto);
			segNivelArea = new ArrayList<DTOSeguimientosCG>();
			for(Integer idArea: areasInvolucradas) {
				for(DTOSeguimientosCG movimiento : segNivelPunto) {
					if(idArea.equals(movimiento.getId().getIdArea())) {
						if(movimiento.getId().getIdEstatusPunto().equals(7)) {
							cambioEstatus = true;
							break;
						} else {
							cambioEstatus = false;
							break;
						}
					}
				}

				if(!cambioEstatus) {
					break;
				}
			}

			if(!cambioEstatus) {
				break;
			}
		}

		if(cambioEstatus) {		// Si todos los puntos se encuentran validados
			acuerdo = helpSegPunto.getAcuerdo();
			acuerdo.setEstatus(1);	// Inserta 1 para indicar que el acuerdo es validado

			try {
				asSegPuntos.actualizarAcuerdo(acuerdo);
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.actualizarEstatusAcuerdo: " + e.getMessage());
			}
		}
	}

	public boolean validarRolCaptura(String rolUsuario) {
		boolean esRolValidoCaptura = false;

		if(
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC)
		  ) {
			esRolValidoCaptura = true;
    	}
    
		return esRolValidoCaptura;
	}



	public DTOResponsables recuperarDtoResponsable(String nomUsuario) {
		try {
			return asSegPuntos.recuperarDtoResponsable(nomUsuario);
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarDtoResponsable: " + e.getMessage());
			return null;
		}
	}

	public boolean recuperarVistaCompartida(HelperSeguimientoPunto helpSegPunto, String rolUsuario) {
		boolean esVistaCompartida = false;

		if(rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC) &&
		   helpSegPunto.getDtoResponsable() != null) {
			for(DTOCAreas dtoArea: helpSegPunto.getAreasInvolucPunto()) {
				if(helpSegPunto.getDtoResponsable().getIdArea().equals(dtoArea.getId().getIdArea())) {
					esVistaCompartida = true;
					helpSegPunto.setAccionesSegPorArea(recuperarSeguimientoArea( helpSegPunto.getAcuerdo().getIdNumAcuerdo(),
																				 helpSegPunto.getPunto().getId().getNumeralia(),
																				 helpSegPunto.getDtoResponsable().getIdArea()) );
					break;
				}
	    	}
		}

		return esVistaCompartida;
	}

	public List<HelperDTOTipoMov> generarTiposMovimiento(List<DTOSeguimientosCG> accionesSegPorArea) {
		int regMasActual = 0;
		List<HelperDTOTipoMov> tiposMovimiento = new ArrayList<HelperDTOTipoMov>();;

		if(accionesSegPorArea.size() > 0) {
			if(accionesSegPorArea.get(regMasActual).getId().getIdEstatusPunto().equals(1) ||
			   accionesSegPorArea.get(regMasActual).getId().getIdEstatusPunto().equals(3)) {
				tiposMovimiento.add(new HelperDTOTipoMov(0, "Rechazar"));
				tiposMovimiento.add(new HelperDTOTipoMov(1, "Avanzar"));
				tiposMovimiento.add(new HelperDTOTipoMov(2, "Finalizar"));
			}
			if(accionesSegPorArea.get(regMasActual).getId().getIdEstatusPunto().equals(4) ||
			   accionesSegPorArea.get(regMasActual).getId().getIdEstatusPunto().equals(6)) {
				tiposMovimiento.add(new HelperDTOTipoMov(1, "Avanzar"));
				tiposMovimiento.add(new HelperDTOTipoMov(2, "Finalizar"));
			}
		}

		return tiposMovimiento;
	}

	public void insertarMovimiento(HelperSeguimientoPunto helpSegPunto) {
		int regMasActual = 0;
		List<DTOSeguimientosCG> segRespConjunta = new ArrayList<DTOSeguimientosCG>();
		List<Integer> areasRespConjunta = new ArrayList<Integer>();
		Integer idRespConjunta = helpSegPunto.getAccionesSegPorArea().get(regMasActual).getResponsabilidadConjunta();
		Integer idArea = helpSegPunto.getAccionesSegPorArea().get(regMasActual).getId().getIdArea();
		String siglasArea = "";
		Integer idEstatusPunto = 0;

		helpSegPunto.setDescMovimiento(reemplazarComillasEstilizadas(helpSegPunto.getDescMovimiento()));

		if(helpSegPunto.getTipoMovimiento().equals(0)) {		// Movimiento de rechazo
			idEstatusPunto = new Integer(2);					// Estatus rechazado (ID 2)
		}
		if(helpSegPunto.getTipoMovimiento().equals(1)) {		// Movimiento de avance
			idEstatusPunto = new Integer(4);					// Estatus avanzando (ID 4)
		}
		if(helpSegPunto.getTipoMovimiento().equals(2)) {		// Movimiento de finalización
			idEstatusPunto = new Integer(5);					// Estatus avanzando (ID 5)
		}

		for(DTOCAreas area : helpSegPunto.getAreasInvolucPunto()) {
			if(idArea.equals(area.getId().getIdArea())) {
				siglasArea = area.getSiglas();
				break;
			}
		}

		if(idRespConjunta.equals(0)) {
			segRespConjunta.add(helpSegPunto.getAccionesSegPorArea().get(regMasActual));		// Recupera el último movimiento
		} else {
			try {
				segRespConjunta =  asSegPuntos.recuperarRespConj( helpSegPunto.getPunto().getId().getIdNumAcuerdo(),
																  helpSegPunto.getPunto().getId().getNumeralia(),
																  idRespConjunta );
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.insertarMovimiento: " + e.getMessage());
			}
		}

		for(DTOSeguimientosCG movimiento : segRespConjunta) {
			if(!areasRespConjunta.contains(movimiento.getId().getIdArea())) {
				areasRespConjunta.add(movimiento.getId().getIdArea());
				movimiento.getId().setFechaMovimiento(new Date());
				movimiento.getId().setIdEstatusPunto(idEstatusPunto);
				movimiento.setTipoMovimiento(0);	// Ya no importa el tipo de movimiento

				if(movimiento.getResponsabilidadConjunta().equals(0)) {		// Si el área no tiene responsabilidad conjunta
					movimiento.setDescripcion(helpSegPunto.getDescMovimiento());
				} else {
					movimiento.setDescripcion(helpSegPunto.getDescMovimiento() + " (Hecho por " + siglasArea + ")");
				}

				try {
					asSegPuntos.insertarMovimiento(movimiento);
				} catch (Exception e) {
					log.error("BSDSeguimientoPuntos.insertarMovimiento: " + e.getMessage());
				}
			}
		}

		helpSegPunto.setMsjMovimiento("regInsertMovimiento");
		helpSegPunto.setStatusInsert("btnGuardarRegresar");
	}

	public List<String> recuperaImgsInfografias() {
		try{
			List<String> imgInf = new ArrayList<String>();
			imgInf.add("seguimiento/Infografia_seguimiento_01.png");
			return  (List<String>) imgInf;
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperaImgsInfografias: " + e.getMessage());
			return null;
		}
	}

	public String reemplazarComillasEstilizadas(String cadena) {
		char comillaIzq = "“".charAt(0);
		char comillaDer = "”".charAt(0);
		char codASCII = (char) 34;	// Código ASCII de las comillas normales
		cadena = cadena.replace(comillaIzq, codASCII);	// Se reemplazan las comillas estilizadas izquierdas
		cadena = cadena.replace(comillaDer, codASCII);	// Se reemplazan las comillas estilizadas derechas
		return cadena;    
	}

}
