package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
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

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.as.ASSeguimientoPuntosInterface;
import mx.ine.acuerdos.bsd.BSDSeguimientoPuntosInterface;
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

@Component("bsdSegPuntos")
@Scope("prototype")
public class BSDSeguimientoPuntos implements BSDSeguimientoPuntosInterface, Serializable {
	private static final long serialVersionUID = -5974751018494675849L;
	private static final Log log = LogFactory.getLog(BSDSeguimientoPuntos.class);
	
	@Autowired
	@Qualifier("asSegPuntos")
	private transient ASSeguimientoPuntosInterface asSegPuntos;

	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	@Autowired
	@Qualifier("asConvocatoria")
	private transient ASConvocatoriaInterface asConvocatoria;
	

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

	public Integer determinarIdAreaEnResponsables(String nomUsuario) {
		try {
			return asSegPuntos.recuperarAreaUsuarioResponsable(nomUsuario).getIdArea();
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.determinarIdAreaPorUser: " + e.getMessage());
			return null;
		}
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
		TreeNode raizArbol = new DefaultTreeNode("Raiz del Arbol", null);
		List<DTOSeguimientosCG> segPorResponsConj = new ArrayList<DTOSeguimientosCG>();
		List<Integer> areasConjuntas = new ArrayList<Integer>();

		if(helpSegPunto.getAccionesSegPunto().get(regMasActual).getResponsabilidadConjunta().equals(0)) {

			for(DTOCAreas area : helpSegPunto.getAreasInvolucPunto()) {
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

		} else {

			try {
				segPorResponsConj = asSegPuntos.recuperarRespConj(helpSegPunto.getAcuerdo().getIdNumAcuerdo(),
																  helpSegPunto.getPunto().getId().getNumeralia(),
																  helpSegPunto.getAccionesSegPunto().get(regMasActual).getResponsabilidadConjunta());
			} catch (Exception e) {
				log.error("BSDSeguimientoPuntos.construirArbolRespons: " + e.getMessage());
			}

			for(DTOSeguimientosCG dtoSeguimiento : segPorResponsConj) {
				if(!areasConjuntas.contains(dtoSeguimiento.getId().getIdArea())) {
					areasConjuntas.add(dtoSeguimiento.getId().getIdArea());
				}
			}

			helpSegPunto.setAreasInvolucPunto(recuperarAreasInvolucPunto(areasConjuntas));
			helpSegPunto.setResposInvolucPunto(recuperarResponsInvolucPunto(areasConjuntas));

			TreeNode nodoRespons = new DefaultTreeNode("Responsabilidad Conjunta", raizArbol);
			for(Integer areaConjunta : areasConjuntas) {
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

		return raizArbol;
	}

	public List<HelperDTOTipoMov> generarTiposMovimiento(List<DTOSeguimientosCG> accionesSegPunto) {
		int regMasActual = 0;
		List<HelperDTOTipoMov> tiposMovimiento = new ArrayList<HelperDTOTipoMov>();;

		if(accionesSegPunto.size() > 0) {
			if(accionesSegPunto.get(regMasActual).getId().getIdEstatusPunto().equals(1) ||
			   accionesSegPunto.get(regMasActual).getId().getIdEstatusPunto().equals(3)) {
				tiposMovimiento.add(new HelperDTOTipoMov(0, "Rechazar"));
				tiposMovimiento.add(new HelperDTOTipoMov(1, "Avanzar"));
				tiposMovimiento.add(new HelperDTOTipoMov(2, "Finalizar"));
			}
			if(accionesSegPunto.get(regMasActual).getId().getIdEstatusPunto().equals(4) ||
			   accionesSegPunto.get(regMasActual).getId().getIdEstatusPunto().equals(6)) {
				tiposMovimiento.add(new HelperDTOTipoMov(1, "Avanzar"));
				tiposMovimiento.add(new HelperDTOTipoMov(2, "Finalizar"));
			}
		}

		return tiposMovimiento;
	}

	public List<DTOCEstatusPuntos> recuperarEstatusPuntos() {
		try {
			return asSegPuntos.recuperarEstatusPuntos();
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.recuperarEstatusPuntos: " + e.getMessage());
			return null;
		}
	}

	public void activarDesactivarCierre(HelperSeguimientoPunto helpSegPunto) {
		if (helpSegPunto.getCerrarPunto()) {
			helpSegPunto.setCerrarPunto(true);
		} else {
			helpSegPunto.setCerrarPunto(false);
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

	public void insertarMovimiento(HelperSeguimientoPunto helpSegPunto) {
		int regMasActual = 0;
		List<DTOSeguimientosCG> segRespConjunta = new ArrayList<DTOSeguimientosCG>();
		List<Integer> areasRespConjunta = new ArrayList<Integer>();
		Integer idRespConjunta = helpSegPunto.getAccionesSegPunto().get(regMasActual).getResponsabilidadConjunta();
		Integer idArea = helpSegPunto.getAccionesSegPunto().get(regMasActual).getId().getIdArea();
		String siglasArea = "";
		Integer idEstatusPunto = 0;

		helpSegPunto.setDescMovimiento(reemplazarComillasEstilizadas(helpSegPunto.getDescMovimiento()));

		if(helpSegPunto.getTipoMovimiento().equals(0)) {		// Movimiento de rechazo
			idEstatusPunto = new Integer(2);						// Estatus rechazado (ID 2)
		}
		if(helpSegPunto.getTipoMovimiento().equals(1)) {		// Movimiento de avance
			idEstatusPunto = new Integer(4);						// Estatus avanzando (ID 4)
		}
		if(helpSegPunto.getTipoMovimiento().equals(2)) {		// Movimiento de finalización
			idEstatusPunto = new Integer(5);						// Estatus avanzando (ID 5)
		}

		for(DTOCAreas area : helpSegPunto.getAreasInvolucPunto()) {
			if(idArea.equals(area.getId().getIdArea())) {
				siglasArea = area.getSiglas();
				break;
			}
		}

		if(idRespConjunta.equals(0)) {
			segRespConjunta.add(helpSegPunto.getAccionesSegPunto().get(regMasActual));		// Recupera el último movimiento
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

	public boolean validarRolUsuario(String rolUsuario) {
		boolean esRolValido = false;

		if(
				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_ADMIN_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_SE_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_TITULAR_AREA_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_TITULAR_AREA_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_AREA_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_AREA_OC) 
			  ) {
				esRolValido = true;
	    	}

		return esRolValido;
	}

	public boolean validarRolCG(String rolUsuario) {
		boolean esRolValido = false;

		if(
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_ADMIN_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_SE_OC) 
		  ) {
			esRolValido = true;
    	}
    
		return esRolValido;
	}

	@Override
	public boolean bsdSegPuntos(String rolUsuario) {
		
		boolean tieneArea = false;
		
		try {
		
			DTOResponsables responsArea = asConvocatoria.recuperarDtoResponsable(rolUsuario);
		
			if(!(responsArea == null)) {
				tieneArea = true;
			}
			
		} catch (Exception e) {
			log.error("BSDSeguimientoPuntos.bsdSegPuntos: " + e.getMessage());
		}
		
		return tieneArea;
	}
	
	public boolean validarRolCaptura(String rolUsuario) {
		boolean esRolValido = false;

		if(
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_TITULAR_AREA_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_AREA_OC)
		  ) {
			esRolValido = true;
    	}
    
		return esRolValido;
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
