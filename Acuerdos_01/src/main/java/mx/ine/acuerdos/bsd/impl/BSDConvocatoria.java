package mx.ine.acuerdos.bsd.impl;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDConvocatoriaInterface;
import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;
import mx.ine.acuerdos.dto.DTOConvocatorias;
import mx.ine.acuerdos.dto.DTOConvocatoriasPK;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOOrdenesDelDiaPK;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.CorreoAcuerdos;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;
import mx.org.ine.servicios.exception.CorreoNoEnviadoException;

@Component("bsdConvocatoria")
@Scope("prototype")
public class BSDConvocatoria implements BSDConvocatoriaInterface, Serializable {
	private static final long serialVersionUID = 8301418511583967259L;
	private static final Log log = LogFactory.getLog(BSDConvocatoria.class);

	@Autowired
	@Qualifier("asConvocatoria")
	private transient ASConvocatoriaInterface asConvocatoria;

	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;

	public boolean validarRolUsuario(String rolUsuario) {
		boolean esRolValido = false;

			if(
				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_ADMIN_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC) ||
				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_SE_OC)		// En primera versión, se omiten las comisiones
//				||
//				rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_TITULAR_AREA_OC) ||
//				rolUsuario.equalsIgnoreCase(Constantes.CONSULTA_TITULAR_AREA_OC)
			  ) 
			{
				esRolValido = true;
	    	}

		return esRolValido;
	}

	@Override
	public boolean validarRolCapConsultaUsuario(String rolUsuario) {
		
		boolean esRolValido = false;

		if(
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC)		// En primera versión, se omiten las comisiones
//			||
//			rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_TITULAR_AREA_OC)
		  ) 
		{
			esRolValido = true;
    	}

		return esRolValido;
	}
	
	public boolean validarRolComision(String rolUsuario) {
		boolean esRolValido = false;

		if(rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_TITULAR_AREA_OC)) {
			esRolValido = true;
    	}
    
		return esRolValido;
	}

	public DTOResponsables recuperarDtoResponsable(String nomUsuario) {
		try {
			return asConvocatoria.recuperarDtoResponsable(nomUsuario);
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarDtoResponsable: " + e.getMessage());
			return null;
		}
	}

	public DTOIntegrantesComision recuperarDtoIntegComision(Integer idResponsable) {
		try {
			return asConvocatoria.recuperarDtoIntegComision(idResponsable);
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarDtoIntegComision: " + e.getMessage());
			return null;
		}
	}

	public DTOComisiones recuperarComision(Integer idComision) {
		try {
			return asConvocatoria.recuperarComision(idComision);
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarComision: " + e.getMessage());
			return null;
		}
	}

	public List<DTOIntegrantesComision> recuperarIntegComision(Integer idComision) {
		try {
			return asConvocatoria.recuperarIntegComision(idComision);
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarIntegComision: " + e.getMessage());
			return null;
		}
	}

	public List<DTOCTipoIntegComision> recuperarTipoIntegComision() {
		try {
			return asConvocatoria.recuperarTipoIntegComision();
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarTipoIntegComision: " + e.getMessage());
			return null;
		}
	}

	public List<DTOResponsables> recuperarResponsComision(List<DTOIntegrantesComision> integComision) {
		List<DTOResponsables> responsComision = new ArrayList<DTOResponsables>();

		for(DTOIntegrantesComision integrante : integComision) {
			try {
				responsComision.add(asConvocatoria.recuperarResponsComision(integrante.getId().getIdResponsable()));
			} catch (Exception e) {
				log.error("BSDConvocatoria.insertarOrdenDelDia: " + e.getMessage());
			}
		}

		return responsComision;
	}

	public TreeNode construirArbolComisiones(HelperConvocatoria helpConvocatoria) {
		TreeNode raizArbol = new DefaultTreeNode("Raiz del Arbol", null);
		String nomIntegrante = "";
//		String cargoIntegrante = "";

		for(DTOComisiones comision : helpConvocatoria.getComisionesFinal()) {
			TreeNode nodoComision = new DefaultTreeNode(comision.getNomComision(), raizArbol);
			for(DTOCTipoIntegComision tipoInteg : helpConvocatoria.getTipoIntegComision()) {
//				if(tipoInteg.getCg().equals(1)) {		// Permite explorar sólo a los tipos de integrantes dentro del CG
				if(true) {		// ROQUE TEMPORAL (CORREGIR)
					TreeNode nodoTipoInteg = new DefaultTreeNode(tipoInteg.getDescripcion(), nodoComision);
					for(DTOIntegrantesComision integrante : helpConvocatoria.getIntegComision()) {
						if(comision.getIdComision().equals(integrante.getId().getIdComision()) &&
						   tipoInteg.getIdTipoIntegrante().equals(integrante.getIdTipoIntegrante())) {
							for(DTOResponsables responsable : helpConvocatoria.getResponsComision()) {
								if(integrante.getId().getIdResponsable().equals(responsable.getIdResponsable())) {
									nomIntegrante = responsable.getNombre() + " " + responsable.getApellidos();
									break;
								}
							}
							nodoTipoInteg.getChildren().add(new DefaultTreeNode(nomIntegrante));
						}
					}
				}
			}
			nodoComision.setExpanded(false);
		}

//		for(DTOComisiones comision : helpConvocatoria.getComisionesFinal()) {
//			TreeNode nodoComision = new DefaultTreeNode(comision.getNomComision(), raizArbol);
//			for(DTOIntegrantesComision integrante : helpConvocatoria.getIntegComision()) {
//				if(comision.getIdComision().equals(integrante.getId().getIdComision())) {
//					for(DTOResponsables responsable : helpConvocatoria.getResponsComision()) {
//						if(integrante.getId().getIdResponsable().equals(responsable.getIdResponsable())) {
//							nomIntegrante = responsable.getNombre() + " " + responsable.getApellidos();
//							break;
//						}
//					}
//					for(DTOCTipoIntegComision tipoInteg : helpConvocatoria.getTipoIntegComision()) {
//						if(integrante.getIdTipoIntegrante().equals(tipoInteg.getIdTipoIntegrante())) {
//							cargoIntegrante = tipoInteg.getDescripcion();
//							break;
//						}
//					}
//					nodoComision.getChildren().add(new DefaultTreeNode(nomIntegrante + " (" + cargoIntegrante + ")"));
//				}
//			}
//			nodoComision.setExpanded(false);
//		}

		return raizArbol;
	}

	public List<DTOTipoSesiones> recuperarTiposDeSesiones() {
		try {
			return asConvocatoria.recuperarTiposDeSesiones();
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarTiposDeSesiones: " + e.getMessage());
			return null;
		}
	}

	public Integer determinarNumSesionSig(HelperConvocatoria helpConvocatoria) {
		List<DTOConvocatorias> convocsPorAnio = new ArrayList<DTOConvocatorias>();
		Integer numSesionSig = new Integer(1);		// Número de sesión por default 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Integer anio = Integer.parseInt( dateFormat.format(helpConvocatoria.getFechaSesion()) );
		int regMasActual = 0;
//		Integer anio = Integer.parseInt( dateFormat.format(new Date()) );

		try {
			convocsPorAnio = asConvocatoria.recuperarConvocatoriasPorAnio(anio);
		} catch (Exception e) {
			log.error("BSDConvocatoria.determinarNumSesionSig: " + e.getMessage());
		}

		if(convocsPorAnio.size() > 0) {
			numSesionSig = convocsPorAnio.get(regMasActual).getId().getNumSesion() + 1;
		}

		helpConvocatoria.setDescNumSesion("Número de sesión sugerida: " + numSesionSig);
		return numSesionSig;
	}

	public void comprobarCamposSegunRol(HelperConvocatoria helpConvocatoria, String rolUsuario) {
		if(!validarRolComision(rolUsuario)) {
			helpConvocatoria.setNumSesion(helpConvocatoria.getNumSesionSig());
//			helpConvocatoria.setCaracter("Sin carácter");	// CONVOCATORIAS permite nulo en este campo
		}
	}

	public void validarNumSesion(HelperConvocatoria helpConvocatoria) {
		if(helpConvocatoria.getNumSesion().intValue() < helpConvocatoria.getNumSesionSig().intValue()) {
			helpConvocatoria.setDescNumSesion("El número de sesión ya existe");
		} else {
			helpConvocatoria.setDescNumSesion("");
		}
	}

	public void actDesactComConjunta(HelperConvocatoria helpConvocatoria, boolean comisionConjunta) {
		helpConvocatoria.setComisionConjunta(comisionConjunta);
	}

	public List<DTOComisiones> recuperarComisionesFiltradas(Integer idComision) {
		try {
			return asConvocatoria.recuperarComisionesFiltradas(idComision);
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarComisionesFiltradas: " + e.getMessage());
			return null;
		}
	}

	public void agregarComisionIntegrantes(HelperConvocatoria helpConvocatoria, DTOComisiones dtoComision) {
		List<DTOIntegrantesComision> integComision = recuperarIntegComision(dtoComision.getIdComision());
		helpConvocatoria.getComisionesFinal().add(dtoComision);
		helpConvocatoria.getIntegComision().addAll(integComision);
		helpConvocatoria.getResponsComision().addAll(recuperarResponsComision(integComision));
	}

	public void eliminarComisionIntegrantes(HelperConvocatoria helpConvocatoria, DTOComisiones dtoComision) {
		List<DTOIntegrantesComision> integComision = new ArrayList<DTOIntegrantesComision>();

		for(DTOIntegrantesComision integrante : helpConvocatoria.getIntegComision()) {
			if(!integrante.getId().getIdComision().equals(dtoComision.getIdComision())) {
				integComision.add(integrante);
			}
		}

		helpConvocatoria.getComisionesFinal().remove(dtoComision);
		helpConvocatoria.setIntegComision(integComision);
		helpConvocatoria.setResponsComision(recuperarResponsComision(integComision));
	}

	public void agregarFilaComision(HelperConvocatoria helpConvocatoria, DTOComisiones dtoComision) {
		List<DTOComisiones> comisionesFinal = new ArrayList<DTOComisiones>();
		List<DTOIntegrantesComision> integComision = new ArrayList<DTOIntegrantesComision>();

		comisionesFinal.add(helpConvocatoria.getComision());
		comisionesFinal.add(dtoComision);
		integComision.addAll(recuperarIntegComision(helpConvocatoria.getComision().getIdComision()));
		integComision.addAll(recuperarIntegComision(dtoComision.getIdComision()));
		
		helpConvocatoria.setComisionesFinal(comisionesFinal);
		helpConvocatoria.setIntegComision(integComision);
		helpConvocatoria.setResponsComision(recuperarResponsComision(integComision));
	}

	public void insertarPuntoConvoc(HelperConvocatoria helpConvocatoria) {
		DTOOrdenesDelDia puntoOrden = new DTOOrdenesDelDia();
		DTOOrdenesDelDiaPK puntoOrdenPK = new DTOOrdenesDelDiaPK();

		helpConvocatoria.setDescPunto(reemplazarComillasEstilizadas(helpConvocatoria.getDescPunto()));

		puntoOrdenPK.setNumPunto(helpConvocatoria.getOrdenDelDia().size()+1);   // Asigna el ordenamiento consecutivo

		if((helpConvocatoria.getDescPunto()).trim().equals("")) {	// Quita espacios en blanco del principio y del final
			puntoOrden.setDescripcionPunto("Sin descripción");	// Campo temporal para soportar la validación
		} else {
			puntoOrden.setDescripcionPunto(helpConvocatoria.getDescPunto());
		}

		puntoOrden.setId(puntoOrdenPK);

		helpConvocatoria.getOrdenDelDia().add(puntoOrden);
		helpConvocatoria.setDescPunto("");

		RequestContext.getCurrentInstance().execute("PF('muestraDialogAgregar').hide()");
	}

	public void eliminarPuntoConvoc(HelperConvocatoria helpConvocatoria, DTOOrdenesDelDia ordenModificable) {
		int numPunto = 1;
		helpConvocatoria.getOrdenDelDia().remove(ordenModificable);

		for(DTOOrdenesDelDia puntoOrden : helpConvocatoria.getOrdenDelDia()) {
			puntoOrden.getId().setNumPunto(numPunto++);
		}
	}

	public void moverPuntoConvoc(HelperConvocatoria helpConvocatoria, ReorderEvent event) {
		int numPunto = 1;

		for(DTOOrdenesDelDia puntoOrden : helpConvocatoria.getOrdenDelDia()) {
			puntoOrden.getId().setNumPunto(numPunto++);
		}
	}

	public void insertarConvocatoria(HelperConvocatoria helpConvocatoria) {
		DTOConvocatorias convocatoria = new DTOConvocatorias();
		DTOConvocatoriasPK convocatoriaPK = new DTOConvocatoriasPK();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

		helpConvocatoria.setDescLugar(reemplazarComillasEstilizadas(helpConvocatoria.getDescLugar()));
		helpConvocatoria.setAsuntoRelevante(reemplazarComillasEstilizadas(helpConvocatoria.getAsuntoRelevante()));
		helpConvocatoria.setIdAnio(Integer.parseInt(
				   				   dateFormat.format(helpConvocatoria.getFechaSesion()) ));

		try {
			helpConvocatoria.setInicioPeriodo(asConvocatoria.recuperarConformComisionActual(
											  helpConvocatoria.getIdComision())
											  .getId().getInicioPeriodo());
		} catch (Exception e) {
			log.error("BSDConvocatoria.insertarConvocatoria: " + e.getMessage());
		}

		convocatoriaPK.setAnio(helpConvocatoria.getIdAnio());
		convocatoriaPK.setIdComision(helpConvocatoria.getIdComision());
		convocatoriaPK.setNumSesion(helpConvocatoria.getNumSesion());
		convocatoriaPK.setInicioPeriodo(helpConvocatoria.getInicioPeriodo());
		convocatoria.setId(convocatoriaPK);

		convocatoria.setTipoSesion(helpConvocatoria.getTipoSesion());
		convocatoria.setCaracter(helpConvocatoria.getCaracter());
		convocatoria.setFechaSesion(helpConvocatoria.getFechaSesion());
		convocatoria.setHora(helpConvocatoria.getHoraSesion());

		if(helpConvocatoria.getLugarConvoc().equals("Otro")) {
			convocatoria.setLugar(helpConvocatoria.getDescLugar());
		} else {
			convocatoria.setLugar(helpConvocatoria.getLugarConvoc());
		}
		convocatoria.setAsunto(helpConvocatoria.getAsuntoRelevante());

		if(!helpConvocatoria.getRutaAcuerdoFile().equals("") &&
		   !helpConvocatoria.getRenameAcuerdoFile().equals("")) {
			convocatoria.setOrdenDiaAdj(helpConvocatoria.getRutaAcuerdoFile() +
										File.separator +
										helpConvocatoria.getRenameAcuerdoFile());
		} else {
			convocatoria.setOrdenDiaAdj("");
		}

		if(!helpConvocatoria.getRutaFileZIP().equals("") &&
		   !helpConvocatoria.getRenombreFileZIP().equals("")) {
			convocatoria.setDocAnexoAdj(helpConvocatoria.getRutaFileZIP() +
										File.separator +
										helpConvocatoria.getRenombreFileZIP());
		} else {
			convocatoria.setDocAnexoAdj("");
		}

		try {
			asConvocatoria.insertarConvocatoria(convocatoria);
			helpConvocatoria.setExitoInsercion(true);
		} catch (Exception e) {
			log.error("BSDConvocatoria.insertarConvocatoria: " + e.getMessage());
		}
	}

	public void insertarOrdenDelDia(HelperConvocatoria helpConvocatoria) {
		for(DTOOrdenesDelDia puntoOrdenDia : helpConvocatoria.getOrdenDelDia()) {
			puntoOrdenDia.getId().setAnio(helpConvocatoria.getIdAnio());
			puntoOrdenDia.getId().setIdComision(helpConvocatoria.getIdComision());
			puntoOrdenDia.getId().setInicioPeriodo(helpConvocatoria.getInicioPeriodo());
			puntoOrdenDia.getId().setNumSesion(helpConvocatoria.getNumSesion());

			try {
				asConvocatoria.insertarPuntoOrdenDia(puntoOrdenDia);
			} catch (Exception e) {
				log.error("BSDConvocatoria.insertarOrdenDelDia: " + e.getMessage());
			}
		}
	}

	public void enviarCorreoInformativo(HelperConvocatoria helpConvocatoria) {
		List<String> listaCorreos = new ArrayList<String>();

		for(DTOResponsables responComision : helpConvocatoria.getResponsComision()) {
			listaCorreos.add(responComision.getCorreo());
		}
//		listaCorreos.add("josemiguel.ortiz@ine.mx");
//		listaCorreos.add("miguelortizroque@gmail.com");

		CorreoAcuerdos correo =  new CorreoAcuerdos(helpConvocatoria);
		correo.setCorreos(listaCorreos);

		try {
			correo.enviarNotificacionConAdjunto(helpConvocatoria.getAcuerdoFile());
		} catch (CorreoNoEnviadoException e) {
			log.error("BSDConvocatoria.enviarCorreoInformativo: " + e.getMessage());
		}
	}

	public List<DTOComisionesUnidas> recuperarComisionesUnidas(Integer idComisionPreside) {
		try {
			return asConvocatoria.recuperarComisionesUnidas(idComisionPreside);
		} catch (Exception e) {
			log.error("BSDConvocatoria.determinarComisionesInvoluc: " + e.getMessage());
			return new ArrayList<DTOComisionesUnidas>();
		}
	}

	public List<DTOComisiones> determinarComisionesFinal(HelperConvocatoria helpConvocatoria) {
		List<DTOComisiones> comisionesFinal = new ArrayList<DTOComisiones>();

		if(helpConvocatoria.getComision() != null) {
			comisionesFinal.add(helpConvocatoria.getComision());
		}

		for(DTOComisionesUnidas comisionUnida : helpConvocatoria.getComisionesUnidas()) {
			comisionesFinal.add(recuperarComision(comisionUnida.getId().getIdComisionInvitado()));
		}

		return comisionesFinal;
	}

	public List<DTOIntegrantesComision> determinarIntegComision(List<DTOComisiones> comisionesFinal) {
		List<DTOIntegrantesComision> integComision = new ArrayList<DTOIntegrantesComision>();

		for(DTOComisiones comision : comisionesFinal) {
			integComision.addAll(recuperarIntegComision(comision.getIdComision()));
		}

		return integComision;
	}

	public boolean validarMiembroComision(String rolUsuario, String nomUsuario) {
		boolean esMiembroComision = false;
		DTOResponsables responsComision = recuperarDtoResponsable(nomUsuario);
		
		if(rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
		   rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC)) {
			esMiembroComision = true;
		} else {
			if(!(responsComision == null)) {
				if(!(recuperarDtoIntegComision(responsComision.getIdResponsable()) == null)) {
					esMiembroComision = true;
				}
			}
		}

		return esMiembroComision;
	
	}

	public String reemplazarComillasEstilizadas(String cadena) {
		char comillaIzq = "“".charAt(0);
		char comillaDer = "”".charAt(0);
		char codASCII = (char) 34;	// Código ASCII de las comillas normales
		cadena = cadena.replace(comillaIzq, codASCII);	// Se reemplazan las comillas estilizadas izquierdas
		cadena = cadena.replace(comillaDer, codASCII);	// Se reemplazan las comillas estilizadas derechas
		return cadena;    
	}

	public void precargarOrdenDelDia(HelperConvocatoria helpConvocatoria, UploadedFile filePDF) {
		helpConvocatoria.setAcuerdoFile(null);
		helpConvocatoria.setNomArch("");
		helpConvocatoria.setEsTamNoPermitido(false);
		helpConvocatoria.setEsExtNoPermitida(false);

		if(filePDF.getSize() < 31457280) {
			helpConvocatoria.setEsTamNoPermitido(false);
			try {
				if((filePDF.getFileName().substring(filePDF.getFileName().length()-4, filePDF.getFileName().length()).toUpperCase()).equals(".PDF")) {
					helpConvocatoria.setEsExtNoPermitida(false);
					helpConvocatoria.setAcuerdoFile(filePDF);
					helpConvocatoria.setNomArch("Archivo precargado: " + filePDF.getFileName());
				} else {
					helpConvocatoria.setEsExtNoPermitida(true);
				}
			}
			catch(Exception e) {
				log.error("BSDConvocatoria.precargarDocumentacion: " + e.getMessage());
			}
		} else {
			helpConvocatoria.setEsTamNoPermitido(true);
		}
	}

	public void precargarDocumentacion(HelperConvocatoria helpConvocatoria, UploadedFile fileZIP) {
		helpConvocatoria.setAdjuntoFileZIP(null);
		helpConvocatoria.setNombreZIP("");
		helpConvocatoria.setEsTamNoPermitidoZIP(false);
		helpConvocatoria.setEsExtNoPermitidaZIP(false);

		if(fileZIP.getSize() < 419430400) {
			helpConvocatoria.setEsTamNoPermitidoZIP(false);
			try {
				if((fileZIP.getFileName().substring(fileZIP.getFileName().length()-4, fileZIP.getFileName().length()).toUpperCase()).equals(".ZIP")) {
					helpConvocatoria.setEsExtNoPermitidaZIP(false);
					helpConvocatoria.setAdjuntoFileZIP(fileZIP);
					helpConvocatoria.setNombreZIP("Archivo precargado: " + fileZIP.getFileName());
				} else {
					helpConvocatoria.setEsExtNoPermitidaZIP(true);
				}
			}
			catch(Exception e) {
				log.error("BSDConvocatoria.precargarDocumentacion: " + e.getMessage());
			}
		} else {
			helpConvocatoria.setEsTamNoPermitidoZIP(true);
		}
	}

	public void guardarOrdenDelDia(HelperConvocatoria helpConvocatoria) {
		try {
			// Si hay un PDF del acuerdo que se haya adjuntado, lo guardamos en el gluster
			if (  helpConvocatoria.getAcuerdoFile() != null && !helpConvocatoria.getTipoSesion().equals(null) ) {
				String formato="yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
				String f = dateFormat.format(helpConvocatoria.getFechaSesion());
				helpConvocatoria.setRutaAcuerdoFile( boArchivos.getRutaBaseGluster() );
				helpConvocatoria.setRutaAcuerdoFile( boArchivos.getSufijoRutaGluster( moduloArchivo.ORDEN_ARCHIVO, helpConvocatoria.getRutaAcuerdoFile() ) );
				helpConvocatoria.setRenameAcuerdoFile( boArchivos.getSufijoNombreAcuerdoEngroseFile(tipoArchivo.ORDENDELDIA, "" + f + "_" +
																									helpConvocatoria.getNumSesionSig().intValue(),
																									helpConvocatoria.getAcuerdoFile().getFileName()) ) ;
				helpConvocatoria.setAcuerdoAdjunto( boArchivos.almacenarArchivoEnGluster( helpConvocatoria.getAcuerdoFile(),
																						  helpConvocatoria.getRutaAcuerdoFile(),
																						  helpConvocatoria.getRenameAcuerdoFile() ) );
			}
		} catch (Exception e) {
			log.error("BSDConvocatoria.guardarOrdenDelDia: " + e.getMessage());
		}
	}

	public void guardarDocumentacion(HelperConvocatoria helpConvocatoria) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

		try {
			if(helpConvocatoria.getAdjuntoFileZIP() != null) {
				helpConvocatoria.setRutaFileZIP( boArchivos.getRutaBaseGluster() );
				helpConvocatoria.setRutaFileZIP( boArchivos.getSufijoRutaGluster( moduloArchivo.ANEXOS_ARCHIVO, helpConvocatoria.getRutaFileZIP() ) );
				
				helpConvocatoria.setRenombreFileZIP( boArchivos.getSufijoNombreAcuerdoEngroseFile( tipoArchivo.DOC_ORDENDELDIA,
																								   dateFormat.format(helpConvocatoria.getFechaSesion()) + "_" +
																								   helpConvocatoria.getNumSesionSig().intValue(),
																								   helpConvocatoria.getAdjuntoFileZIP().getFileName() ) ) ;
				helpConvocatoria.setGuardaFileZIP( boArchivos.almacenarArchivoEnGluster( helpConvocatoria.getAdjuntoFileZIP(),
																						 helpConvocatoria.getRutaFileZIP(),
																						 helpConvocatoria.getRenombreFileZIP() ) );
			}
		} catch(Exception e) {
			log.error("BSDConvocatoria.guardarDocumentacion: " + e.getMessage());
		}
	}

	@Override
	public List<String> recuperaImgsInfografias() {
		try{
			List<String> imgInf = new ArrayList<String>();
			imgInf.add("convocatoria/Infografia_convo-06.png");
			return  (List<String>) imgInf;
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperaImgsInfografias: " + e.getMessage());
			return null;
		}
	}

}
