package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import mx.ine.acuerdos.bsd.BSDConsultaConvocatoriaInterface;
import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;
import mx.ine.acuerdos.dto.DTOConvocatorias;
import mx.ine.acuerdos.dto.DTOConvocatoriasPK;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.HelperDTOMesesAnio;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;
import mx.ine.acuerdos.util.Constantes;

@Component("bsdConsultaConvoc")
@Scope("prototype")
public class BSDConsultaConvocatoria implements BSDConsultaConvocatoriaInterface, Serializable {
	private static final long serialVersionUID = -3829320762506387387L;
	private static final Log log = LogFactory.getLog(BSDConsultaConvocatoria.class);

	@Autowired
	@Qualifier("asConvocatoria")
	private transient ASConvocatoriaInterface asConvocatoria;

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
			  ) {
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

	public boolean validarMiembroComision(String rolUsuario, String nomUsuario) {
		boolean esMiembroComision = false;
		DTOResponsables responsComision = recuperarDtoResponsable(nomUsuario);

		if(rolUsuario.equalsIgnoreCase(Constantes.CAPTURA_SE_OC)) {
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

	public DTOResponsables recuperarDtoResponsable(String nomUsuario) {
		try {
			return asConvocatoria.recuperarDtoResponsable(nomUsuario);
		} catch (Exception e) {
			log.error("BSDConsultaConvocatoria.recuperarDtoResponsable: " + e.getMessage());
			return null;
		}
	}

	public DTOIntegrantesComision recuperarDtoIntegComision(Integer idResponsable) {
		try {
			return asConvocatoria.recuperarDtoIntegComision(idResponsable);
		} catch (Exception e) {
			log.error("BSDConsultaConvocatoria.recuperarDtoIntegComision: " + e.getMessage());
			return null;
		}
	}

	public List<DTOTipoSesiones> recuperarTiposDeSesiones() {
		try {
			return asConvocatoria.recuperarTiposDeSesiones();
		} catch (Exception e) {
			log.error("BSDConsultaConvocatoria.recuperarTiposDeSesiones: " + e.getMessage());
			return new ArrayList<DTOTipoSesiones>();
		}
	}

	public String recuperarDescTipoDeSesion(HelperConvocatoria helpConvocatoria, Integer tipoSesion) {
		String descTipoDeSesion = "";

		for(DTOTipoSesiones tipoDeSesion : helpConvocatoria.getTiposDeSesiones()) {
			if(tipoSesion.equals(tipoDeSesion.getIdTipoSesion())) {
				descTipoDeSesion = tipoDeSesion.getDescripcion();
				break;
			}
		}

		return descTipoDeSesion;
	}

	public List<DTOOrdenesDelDia> recuperarOrdenDelDia(DTOConvocatoriasPK dtoConvoctariaPK) {
		try {
			return asConvocatoria.recuperarOrdenDelDia(dtoConvoctariaPK);
		} catch (Exception e) {
			log.error("BSDConsultaConvocatoria.recuperarOrdenDelDia: " + e.getMessage());
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

	public List<DTOComisionesUnidas> recuperarComisionesUnidas(Integer idComisionPreside) {
		try {
			return asConvocatoria.recuperarComisionesUnidas(idComisionPreside);
		} catch (Exception e) {
			log.error("BSDConvocatoria.determinarComisionesInvoluc: " + e.getMessage());
			return null;
		}
	}

	public List<DTOComisiones> determinarComisionesFinal(HelperConvocatoria helpConvocatoria) {
		List<DTOComisiones> comisionesFinal = new ArrayList<DTOComisiones>();
		comisionesFinal.add(helpConvocatoria.getComision());

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
//			String cargoIntegrante = "";

			for(DTOComisiones comision : helpConvocatoria.getComisionesFinal()) {
				TreeNode nodoComision = new DefaultTreeNode(comision.getNomComision(), raizArbol);
				for(DTOCTipoIntegComision tipoInteg : helpConvocatoria.getTipoIntegComision()) {
//					if(tipoInteg.getCg().equals(1)) {		// Permite explorar sólo a los tipos de integrantes dentro del CG
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

//			for(DTOComisiones comision : helpConvocatoria.getComisionesFinal()) {
//				TreeNode nodoComision = new DefaultTreeNode(comision.getNomComision(), raizArbol);
//				for(DTOIntegrantesComision integrante : helpConvocatoria.getIntegComision()) {
//					if(comision.getIdComision().equals(integrante.getId().getIdComision())) {
//						for(DTOResponsables responsable : helpConvocatoria.getResponsComision()) {
//							if(integrante.getId().getIdResponsable().equals(responsable.getIdResponsable())) {
//								nomIntegrante = responsable.getNombre() + " " + responsable.getApellidos();
//								break;
//							}
//						}
//						for(DTOCTipoIntegComision tipoInteg : helpConvocatoria.getTipoIntegComision()) {
//							if(integrante.getIdTipoIntegrante().equals(tipoInteg.getIdTipoIntegrante())) {
//								cargoIntegrante = tipoInteg.getDescripcion();
//								break;
//							}
//						}
//						nodoComision.getChildren().add(new DefaultTreeNode(nomIntegrante + " (" + cargoIntegrante + ")"));
//					}
//				}
//				nodoComision.setExpanded(false);
//			}

			return raizArbol;
	}

	public List<DTOIntegrantesComision> recuperarIntegComision(Integer idComision) {
		try {
			return asConvocatoria.recuperarIntegComision(idComision);
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarIntegComision: " + e.getMessage());
			return null;
		}
	}

	public List<DTOConvocatorias> recuperarConvocatorias() {
		try {
			return asConvocatoria.recuperarConvocatorias();
		} catch (Exception e) {
			log.error("BSDConvocatoria.recuperarConvocatorias: " + e.getMessage());
			return new ArrayList<DTOConvocatorias>();
		}
	}

	public List<Integer> determinarListaAnios(List<DTOConvocatorias> listaConvocatorias) {
		SimpleDateFormat dateFormatA = new SimpleDateFormat("yyyy");
		List<Integer> listaAnios = new ArrayList<Integer>();
		Integer anio = new Integer(0);

		for(DTOConvocatorias dtoConvocatoria : listaConvocatorias) {
			anio = Integer.parseInt( dateFormatA.format(dtoConvocatoria.getFechaSesion()) );
			if(!listaAnios.contains(anio)) {
				listaAnios.add(anio);
			}
		}

		return listaAnios;
	}

	public List<HelperDTOMesesAnio> determinarMesesAnio(HelperConvocatoria helpConvocatoria, Integer idAnio) {
		SimpleDateFormat dateFormatF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		SimpleDateFormat dateFormatM = new SimpleDateFormat("MM");
		List<Integer>  listaMeses = new ArrayList<Integer>();
		List<HelperDTOMesesAnio> listaMesesAnio = new ArrayList<HelperDTOMesesAnio>();
		Date inicioAnio = new Date();
		Date finAnio = new Date();
		Integer mes = new Integer(0);

		try {
			inicioAnio = (Date) dateFormatF.parse("01/01/" + idAnio + " 00:00:00");
			finAnio = (Date) dateFormatF.parse("31/12/" + idAnio + " 23:59:59");
		} catch (ParseException e1) {
			log.error("BSDConsultaConvocatoria.determinarMesesAnio: " + e1.getMessage());
		}
		
		try {
			helpConvocatoria.setListaConvocatorias(asConvocatoria.recuperarConvocatoriasFormatAnio(inicioAnio, finAnio));
		} catch (Exception e) {
			log.error("BSDConvocatoria.determinarMesesAnio: " + e.getMessage());
		}

		for(DTOConvocatorias dtoConvocatoria : helpConvocatoria.getListaConvocatorias()) {
			mes = Integer.parseInt(dateFormatM.format(dtoConvocatoria.getFechaSesion()));
			if(!listaMeses.contains(mes)) {
				listaMeses.add(mes);
			}
		}

		for(Integer numMes : listaMeses) {
			switch(numMes.intValue()) {
				case 1:
					listaMesesAnio.add(new HelperDTOMesesAnio(1, "Enero"));
					break;
				case 2:
					listaMesesAnio.add(new HelperDTOMesesAnio(2, "Febrero"));
					break;
				case 3:
					listaMesesAnio.add(new HelperDTOMesesAnio(3, "Marzo"));
				break;
				case 4:
					listaMesesAnio.add(new HelperDTOMesesAnio(4, "Abril"));
				break;
				case 5:
					listaMesesAnio.add(new HelperDTOMesesAnio(5, "Mayo"));
				break;
				case 6:
					listaMesesAnio.add(new HelperDTOMesesAnio(6, "Junio"));
				break;
				case 7:
					listaMesesAnio.add(new HelperDTOMesesAnio(7, "Julio"));
				break;
				case 8:
					listaMesesAnio.add(new HelperDTOMesesAnio(8, "Agosto"));
				break;
				case 9:
					listaMesesAnio.add(new HelperDTOMesesAnio(9, "Septiembre"));
				break;
				case 10:
					listaMesesAnio.add(new HelperDTOMesesAnio(10, "Octubre"));
				break;
				case 11:
					listaMesesAnio.add(new HelperDTOMesesAnio(11, "Noviembre"));
				break;
				case 12:
					listaMesesAnio.add(new HelperDTOMesesAnio(12, "Diciembre"));
				break;
				default:
					listaMesesAnio.add(new HelperDTOMesesAnio(0, "Sin descripción"));
				break;
			}
		}

		return listaMesesAnio;
	}

	public List<DTOTipoSesiones> determinarTiposSesion(HelperConvocatoria helpConvocatoria, Integer mes) {
		SimpleDateFormat dateFormatM = new SimpleDateFormat("MM");
		List<Integer>  listaTiposSesion = new ArrayList<Integer>();
		List<DTOTipoSesiones> tiposDeSesionesAux = new ArrayList<DTOTipoSesiones>();
		Integer mesAux = new Integer(0);

		for(DTOConvocatorias dtoConvocatoria : helpConvocatoria.getListaConvocatorias()) {
			mesAux = Integer.parseInt(dateFormatM.format(dtoConvocatoria.getFechaSesion()));
			if(mesAux.equals(mes)) {
				if(!listaTiposSesion.contains(dtoConvocatoria.getTipoSesion())) {
					listaTiposSesion.add(dtoConvocatoria.getTipoSesion());
				}	
			}
		}

		for(Integer tipoSesion : listaTiposSesion) {
			for(DTOTipoSesiones dtoTipoSesion : helpConvocatoria.getTiposDeSesiones()) {
				if(tipoSesion.equals(dtoTipoSesion.getIdTipoSesion())) {
					tiposDeSesionesAux.add(dtoTipoSesion);
					break;
				}
			}
		}

		return tiposDeSesionesAux;
	}
	
	public List<DTOConvocatorias> recuperarConvocatoriasPorTipo(HelperConvocatoria helpConvocatoria, Integer tipoSesion) {
		SimpleDateFormat dateFormatM = new SimpleDateFormat("MM");
		List<DTOConvocatorias> listaConvocFiltro = new ArrayList<DTOConvocatorias>();
		Integer mesAux = new Integer(0);
		Integer tipoSesionAux = new Integer(0);

		for(DTOConvocatorias dtoConvocatoria : helpConvocatoria.getListaConvocatorias()) {
			mesAux = Integer.parseInt(dateFormatM.format(dtoConvocatoria.getFechaSesion()));
			tipoSesionAux = dtoConvocatoria.getTipoSesion();

			if(mesAux.equals(helpConvocatoria.getMes()) && tipoSesionAux.equals(helpConvocatoria.getTipoSesion())) {
				listaConvocFiltro.add(dtoConvocatoria);
			}
		}

		return listaConvocFiltro;
	}

}
