package mx.ine.computosINE.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.jboss.logging.Logger;
import org.jfree.util.Log;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.helper.HLPGeograficosInterface;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.computosINE.bsd.BSDCapturaVotosInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.bsd.BSDInformacionGeneralActaInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTOCandidato;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPDemarcacion;
import mx.ine.computosINE.helper.HLPDistribuciones;
import mx.ine.computosINE.helper.HLPGeneracionActasMensajes;
import mx.ine.computosINE.helper.HLPInfoGralComputos;
import mx.ine.computosINE.helper.HLPTipoCandidatura;
import mx.ine.computosINE.mb.MBGeneric.TipoMensaje;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.UtilUpload;
import mx.ine.computosINE.util.Utilidades;

/**
 * Clase que se encarga de realizar la distribución de votos
 * 
 * @author Geovanny Romero E.
 * @since 18/04/2017
 * @copyright INE
 */

// @Component("mbDist")
@Scope("session")
@Controller("mbDist")
public class MBDistribucion extends MBGeneric implements Serializable {

	/**
	 * Objeto par la serialización de esta clase.
	 */
	private static final long serialVersionUID = -2726897940363240755L;

	/**
	 * Objeto para el servicio de bitácora de mensajes.
	 */
	private static final Logger LOGGER = Logger.getLogger(MBDistribucion.class);

	/*
	 * Lista de distribuciones
	 */
	private List<DTODistribucionCandidatos> ldto;

	/*
	 * Lista de las demarcaciones (o regidurías) de una entidad
	 */
	private List<DTOListaRegiduriasWS> demarcaciones;

	/*
	 * Id de la demarcación (o regiduría) seleccionada
	 */
	private Integer idDemarcacionSeleccionada;

	/*
	 * Lista de los distrito de una entidad
	 */
	private List<DTODistritosWS> distritos;

	/*
	 * Id del distrito seleccionado
	 */
	private Integer idDistritoSeleccionado;

	/*
	 * Lista de asociaciones del acta
	 */
	private List<DTOActaCasillaVotosPK> asociaciones;

	/*
	 * Variable para habilitar/deshabilitar los paneles en pantalla
	 */
	private boolean panelesVisible;

	/*
	 * Variable para permitir el cálculo de la distribución (100% actas
	 * capturadas)
	 */
	private boolean permiteDistribucion;

	/*
	 * Variable para comprobar si ya están capturadas todas las actas (casillas)
	 * de una candidatura
	 */
	private boolean casillasCapturadas100;

	/*
	 * Variable para comprobar que las actas parciales ya fueron generadas
	 */
	private boolean actasParcialesGeneradas;

	/*
	 * Variable para validar si ya se ha generado una distribución para el tipo
	 * de candidatura
	 */
	private boolean distribucionGeneradaXCand;

	/*
	 * Variable para permitir guardar la distribución en BD
	 */
	private boolean permiteGuardar;

	/*
	 * Variable que tiene el id del cargo seleccionado
	 */
	private Integer cargoSeleccionado;

	private HLPTipoCandidatura tipoCandidatura;

	/**
	 * Bloquear botón de generar distribucion
	 */
	private boolean habilitaBoton;

	/*
	 * Variable que contiene los participantes de la elección (BD)
	 */
	List<DTOActaCasillaVotos> actaVotos;

	/*
	 * Variable que contiene la lista de las asociaciones base de la elección
	 * (WS)
	 */
	List<DTOCandidato> asociacionesBase;

	/*
	 * Variable que contiene la lista de los Partidos Políticos de la elección
	 */
	List<DTOActaCasillaVotos> partidos;

	/*
	 * Variable que contiene la lista de los Partidos Políticos y CI de la
	 * elección, con emblema
	 */
	List<DTOAsociacion> partidosCompletos;

	/*
	 * Variable que contiene la lista de las coaliciones de la elección
	 */
	List<DTOActaCasillaVotos> coaliciones;

	/*
	 * Variable que contiene la lista de las coaliciones de la elección, con
	 * emblema
	 */
	List<DTOAsociacion> coalicionesCompletas;

	/*
	 * Variable que contiene el valor de CNR
	 */
	DTOActaCasillaVotos cnr;

	/*
	 * Variable que contiene el valor de Votos nulos
	 */
	DTOActaCasillaVotos nulos;

	/*
	 * Variable que contiene el total de votos de la elección
	 */
	int totalVotos;

	/*
	 * Variable que contiene el porcentaje de votación de CNR
	 */
	String porcentajeCNR;

	/*
	 * Variable que contiene el porcentaje de votos nulos
	 */
	String porcentajeNulos;

	/*
	 * Variable que contiene la lista de los partidos y CI producto de la
	 * Distribución por PP
	 */
	List<DTOActaCasillaVotos> distribucionPP;

	/*
	 * Variable que contiene la lista de los partidos y CI producto de la
	 * Distribucion por PP, con emblema
	 */
	List<DTOAsociacion> distribucionPPcompleta;

	/*
	 * Variable que contiene la lista de los candidatos de una elección, con
	 * emblema
	 */
	List<DTOAsociacion> distribucionCandidato;

	/**
	 * Clase Helper para obtener mensajes de validaciones
	 */
	private HLPGeneracionActasMensajes hlpMsg;

	DTOUsuarioLogin usuario;

	@Autowired
	@Qualifier("bsdDistribucionVotos")
	private transient BSDDistribucionVotosInterface bsdAsociacionesParticipantes;

	@Autowired
	@Qualifier("bsdCapturaVotos")
	private transient BSDCapturaVotosInterface bsdCapturaVotos;

	@Autowired
	@Qualifier("hplDistribuciones")
	private transient HLPDistribuciones hlp;

	@Autowired
	@Qualifier("bsdCargaInformacion")
	private transient BSDCargaInformacionInterface bsdCargaInformacion;

	@Autowired
	@Qualifier("hlpGeograficos")
	private HLPGeograficosInterface hlpGeo;

	/**
	 * Helper para traer combos
	 */
	@Autowired
	@Qualifier("hplInfoGralComputos")
	private transient HLPInfoGralComputos hlpInfo;
	
	/*
	 * BSD de distribución de votos
	 */
	@Autowired
	@Qualifier("bsdDistribucionVotos")
	public transient BSDDistribucionVotosInterface bsdDistrVotos;

	/*
	 * BSD para obtener asociaciones
	 */
	@Autowired
	@Qualifier("bsdCargaInformacion")
	public transient BSDCargaInformacionInterface bsdInfo;

	/*
	 * BSD para obtener informacion general de computos
	 */
	@Autowired
	@Qualifier("bsdInfoGralActas")
	public transient BSDInformacionGeneralActaInterface bsdInfoGralActas;

	@Autowired
	@Qualifier("rutaGluster")
	private transient String rutaGluster;

	private StreamedContent imagen = new DefaultStreamedContent();

	/*
	 * Acciones a ejecutar al iniciar el módulo
	 */
	public void init(Integer candidatura) {
		cargoSeleccionado = candidatura;
		casillasCapturadas100 = false;
		actasParcialesGeneradas = false;
		distribucionGeneradaXCand = false;
		idDemarcacionSeleccionada = Constantes.SIN_REGIDURIA;
		idDistritoSeleccionado = Constantes.SIN_DISTRITO;
		tipoCandidatura = new HLPTipoCandidatura();
		panelesVisible = false;
		permiteDistribucion = false;
		habilitaBoton = false;
		permiteGuardar = false;
		usuario = obtenUsuario();
		if (usuario.getIdMunicipioSeleccionado() == null) {
			usuario.setIdMunicipioSeleccionado(0);
		}
		if (usuario.getIdEstado() == 0) {
			usuario.setIdEstado(usuario.getIdEstadoSeleccionado());
		}
		hlpMsg = new HLPGeneracionActasMensajes();
		// LOGGER.info("Entró al post MBDistribucion constructor");
		ldto = new ArrayList<>();
		demarcaciones = new ArrayList<>();
		distritos = new ArrayList<>();
		try {
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				demarcaciones = hlpGeo.regiduriasPorMunicipio(
						usuario.getIdEstado() != 0 ? usuario.getIdEstado()
								: usuario.getIdEstadoSeleccionado(), usuario
								.getIdMunicipioSeleccionado());
				demarcaciones = hlp.getDemarcacionesParaDistribucion(
						bsdAsociacionesParticipantes, demarcaciones, usuario,
						cargoSeleccionado);
				if (demarcaciones != null && demarcaciones.size() < 1) {
					// agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(),
					// "mensajesInfo",
					// Utilidades.mensajeProperties("validacion_mensaje_generales_distribucion_no_regidurias"));
					hlpMsg.mensajesValidacion(36);

				}
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				// distritos =
				// hlpGeo.obtenerCatalogoDistritos(usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(),
				// EnumAmbitoDetalleProceso.L);
				// distritos =
				// hlp.getDistritosParaDistribucion(bsdAsociacionesParticipantes,
				// distritos, usuario,
				// cargoSeleccionado);

				cargaDistritosPorGuardar();

				if (distritos != null && distritos.size() < 1) {
					// agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(),
					// "mensajesInfo",
					// Utilidades.mensajeProperties("validacion_mensaje_generales_distribucion_no_distritos"));
					hlpMsg.mensajesValidacion(41);
				}
			}
		} catch (ClienteWebServiceException e) {
			hlpMsg.mensajesValidacion(53);
			e.printStackTrace();
		}
	}

	public void cancelada() {
		panelesVisible = false;
		LOGGER.info("Entró a cancelada()");
	}

	@SuppressWarnings("unchecked")
	public void cargaAsociaciones() {
		// usuario = obtenUsuario();
		asociaciones = new ArrayList<>();
		// DTOActaCasillaVotosPK nc1 = new DTOActaCasillaVotosPK((Integer)3,
		// (Integer)4, (Integer)18, (Integer)(-1), (Integer)1, (Integer)2,
		// (Integer)100, (Integer)1, (Integer)1, 'B', (Integer)0, (Integer)3,
		// (Integer)4, cargoSeleccionado);
		// DTOActaCasillaVotosPK nc2 = new DTOActaCasillaVotosPK((Integer)3,
		// (Integer)4, (Integer)18, (Integer)(-5), (Integer)1, (Integer)(-7),
		// (Integer)1, (Integer)(-8), (Integer)1, 'B', (Integer)0, (Integer)9,
		// (Integer)9, cargoSeleccionado);
		// asociaciones.add(nc1);
		// asociaciones.add(nc2);
		LOGGER.info("Entró a cargaAsociaciones()");
		LOGGER.info("Valor de cargo seleccionado " + cargoSeleccionado);
		if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)
				|| cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)
				|| ((cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) && actasCapturadasCompletas())
				|| ((cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) /*
																	 * &&
																	 * actasCapturadasCompletas
																	 * ()
																	 */)) {
			panelesVisible = true;
		} else {
			panelesVisible = false;// false
			hlpMsg = new HLPGeneracionActasMensajes();
			hlpMsg.mensajesValidacion(33);
		}

		actaVotos = null;
		partidos = new ArrayList<>();
		cnr = null;
		nulos = null;
		partidosCompletos = new ArrayList<>();
		coaliciones = new ArrayList<>();
		coalicionesCompletas = new ArrayList<>();
		totalVotos = 0;
		distribucionPP = new ArrayList<>();
		distribucionPPcompleta = new ArrayList<>();
		distribucionCandidato = new ArrayList<>();
		try {
			DTOActaCasillaVotosPK dtoActaCasillaVotos = null;
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), usuario.getIdMunicipioSeleccionado(),
						(Integer) (-7), (Integer) 1, (Integer) (-8),
						(Integer) 1, "B", (Integer) 0, cargoSeleccionado,
						(Integer) 1, (Integer) 1);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), usuario.getIdMunicipioSeleccionado(),
						idDemarcacionSeleccionada, (Integer) 1, (Integer) (-8),
						(Integer) 1, "B", (Integer) 0, cargoSeleccionado,
						(Integer) 1, (Integer) 1);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						idDistritoSeleccionado, -6, (Integer) (-7),
						(Integer) 1, (Integer) (-8), (Integer) 1, "B",
						(Integer) 0, cargoSeleccionado, (Integer) 1,
						(Integer) 1);
				actaVotos = bsdAsociacionesParticipantes
						.consultarAsociacionesParticipantesDistrito(dtoActaCasillaVotos);
			}
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), -6, (Integer) (-7), (Integer) (-9),
						(Integer) (-8), (Integer) 1, "B", (Integer) 0,
						cargoSeleccionado, (Integer) 1, (Integer) 1);
				actaVotos = bsdAsociacionesParticipantes
						.consultarAsociacionesParticipantesEntidad(dtoActaCasillaVotos);
			} else if (!cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					&& !cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				actaVotos = bsdAsociacionesParticipantes
						.consultarAsociacionesParticipantes(dtoActaCasillaVotos);
			}
			if (actaVotos != null && actaVotos.size() > 0) {
				permiteGuardar = true;
			} else {
				permiteGuardar = false;
			}
			List<DTOCSubcoaliciones> coalicionesHijas = hlp
					.getVotosCoalicionesHijas(bsdAsociacionesParticipantes,
							actaVotos);
			List<DTOCDetalleSubcoaliciones> detalleHijas = hlp.getVotosHijas(
					bsdAsociacionesParticipantes, actaVotos, coalicionesHijas);
			asociacionesBase = hlp.cargaAsociaciones(
					bsdAsociacionesParticipantes, usuario, cargoSeleccionado);
			ArrayList<Object> distribucionGenerada = bsdAsociacionesParticipantes
					.generarDistribuciones(actaVotos, coalicionesHijas,
							detalleHijas, asociacionesBase);
			if (distribucionGenerada != null && distribucionGenerada.size() > 1) {
				partidos = (List<DTOActaCasillaVotos>) distribucionGenerada
						.get(0);
				coaliciones = (List<DTOActaCasillaVotos>) distribucionGenerada
						.get(1);
				cnr = (DTOActaCasillaVotos) distribucionGenerada.get(2);
				nulos = (DTOActaCasillaVotos) distribucionGenerada.get(3);
				totalVotos = (int) distribucionGenerada.get(4);
				distribucionPP = (List<DTOActaCasillaVotos>) distribucionGenerada
						.get(5);
				partidosCompletos = (List<DTOAsociacion>) distribucionGenerada
						.get(6);
				coalicionesCompletas = (List<DTOAsociacion>) distribucionGenerada
						.get(7);
				distribucionPPcompleta = (List<DTOAsociacion>) distribucionGenerada
						.get(8);
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				porcentajeCNR = df.format((float) (cnr.getVotos() * 100)
						/ totalVotos);
				porcentajeNulos = df.format((float) (nulos.getVotos() * 100)
						/ totalVotos);
				distribucionCandidato = (List<DTOAsociacion>) distribucionGenerada
						.get(9);
			}
			// asociacionesBase =
			// hlp.cargaAsociaciones(bsdAsociacionesParticipantes, usuario,
			// Constantes.ID_TIPO_CAND_AYUNTAMIENTO);
			// hlp.mergeAsociacionesEmblemas(partidos, asociacionesBase);
			/*
			 * for(DTOCandidato e : asociacionesBase) {
			 * System.out.println(e.getIdAsociacion() + " -> Emblema: " +
			 * e.getEmblema()); }
			 */

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(52);
			e.printStackTrace();
		}

	}
	
	public void cargaDistritosPorGuardar()
	{
		try {
			Map<String, String> mapDistritosDistPar = hlpInfo
					.getDistritosByActasGeneradas(bsdInfoGralActas,
							usuario, cargoSeleccionado);
			
			
			DTODistritosWS distriWS;
			for (Map.Entry<String, String> entry : mapDistritosDistPar.entrySet()) {
				distriWS = new DTODistritosWS();
				distriWS.setIdDistrito(Integer.parseInt(entry.getKey()));
				distriWS.setNombreDistrito(entry.getValue());
				boolean existe = false;
				for(DTODistritosWS dd : distritos)
				{
					if(dd.getIdDistrito().equals(distriWS.getIdDistrito()))
					{
						existe = true;
						break;
					}
				}
				if(!existe)
				{
					distritos.add(distriWS);
				}
			}
			
			List<Integer> distritosDistribucion = new ArrayList<>();
			distritosDistribucion = bsdDistrVotos
					.getDistribucionFinalByDistritos(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado(), cargoSeleccionado);
			List<DTODistritosWS> listaDis = new ArrayList<>();
			for(DTODistritosWS distr : distritos)
			{
				boolean existe = false;
				for(Integer distritoDistribucion : distritosDistribucion)
				{
					if(distritoDistribucion.equals(distr.getIdDistrito()))
					{
						existe = true;
						break;
					}
				}
				
				if(!existe)
				{
					listaDis.add(distr);
				}
			}
			
			distritos = listaDis;
			
		} catch (Exception e) {
			LOGGER.error("Error obteniendo distritos por distribucion parcial en cargadistritosPorGuardar()");
			LOGGER.error(e);
			e.printStackTrace();
		}
	}

	public void guardarDistribucion() {
		LOGGER.info("Entró a guardarDistribucion()");
		try {
			DTOActaCasillaVotosPK dtoActaCasillaVotos = null;
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), usuario.getIdMunicipioSeleccionado(),
						(Integer) (-7), (Integer) 1, (Integer) (-8),
						(Integer) 1, "B", (Integer) 0, cargoSeleccionado,
						(Integer) 1, (Integer) 1);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), (Integer) (-6), (Integer) (-7),
						(Integer) (-9), (Integer) (-8), (Integer) 1, "B", 0,
						cargoSeleccionado, 1, 1);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), usuario.getIdMunicipioSeleccionado(),
						idDemarcacionSeleccionada, (Integer) 1, (Integer) (-8),
						(Integer) 1, "B", (Integer) 0, cargoSeleccionado,
						(Integer) 1, (Integer) 1);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				dtoActaCasillaVotos = new DTOActaCasillaVotosPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						idDistritoSeleccionado, -6, -7, (Integer) 1,
						(Integer) (-8), (Integer) 1, "B", (Integer) 0,
						cargoSeleccionado, (Integer) 1, (Integer) 1);
			}
			List<DTODistribucionTotales> totales = hlp
					.asociacionesToDistribucion(dtoActaCasillaVotos,
							partidosCompletos, coalicionesCompletas, cnr,
							nulos, usuario.getUsuario());
			List<DTODistribucionPartidosCI> distPP = hlp
					.asociacionesToDistribucionPPCI(dtoActaCasillaVotos,
							distribucionPPcompleta, cnr, nulos,
							usuario.getUsuario());
			List<DTODistribucionCandidatos> distCand = hlp
					.asociacionesToDistribucionCand(dtoActaCasillaVotos,
							distribucionCandidato, cnr, nulos,
							usuario.getUsuario());
			bsdAsociacionesParticipantes.guardarDistribuciones(totales, distPP,
					distCand);
			// agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(),
			// "mensajesExito",
			// Utilidades.mensajeProperties("validacion_mensaje_generales_distribucion_guardada"));
			panelesVisible = false;
			habilitaBoton = true;
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)) {
				habilitaBoton = false;
			}
			RequestContext.getCurrentInstance()
					.execute("window.scrollTo(0,0);");

			hlpMsg.mensajesValidacion(37);

			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				demarcaciones = hlpGeo.regiduriasPorMunicipio(
						usuario.getIdEstado(),
						usuario.getIdMunicipioSeleccionado());
				demarcaciones = hlp.getDemarcacionesParaDistribucion(
						bsdAsociacionesParticipantes, demarcaciones, usuario,
						cargoSeleccionado);
				if (demarcaciones != null && demarcaciones.size() < 1) {
					hlpMsg.mensajesValidacion(36);

				}
			
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				cargaDistritosPorGuardar();
			
//				try {
//					Map<String, String> mapDistritosDistPar = hlpInfo
//							.getDistritosByActasGeneradas(bsdInfoGralActas,
//									usuario, cargoSeleccionado);
//					
//					
//					DTODistritosWS distriWS;
//					for (Map.Entry<String, String> entry : mapDistritosDistPar.entrySet()) {
//						distriWS = new DTODistritosWS();
//						distriWS.setIdDistrito(Integer.parseInt(entry.getKey()));
//						distriWS.setNombreDistrito(entry.getValue());
//						distritos.add(distriWS);
//					}
//					
//					List<Integer> distritosDistribucion = bsdDistrVotos
//							.getDistribucionFinalByDistritos(
//									usuario.getIdProcesoElectoral(),
//									usuario.getIdDetalleProceso(),
//									usuario.getIdEstadoSeleccionado(), cargoSeleccionado);
//					List<DTODistritosWS> listaDis = new ArrayList<>();
//					for(DTODistritosWS distr : distritos)
//					{
//						boolean existe = false;
//						for(Integer distritoDistribucion : distritosDistribucion)
//						{
//							if(distritoDistribucion.equals(distr.getIdDistrito()))
//							{
//								existe = true;
//								break;
//							}
//						}
//						
//						if(!existe)
//						{
//							listaDis.add(distr);
//						}
//					}
//					
//					distritos.clear();
//					for(DTODistritosWS distritosAGuardar : listaDis)
//					{
//						if(!distritos.contains(distritosAGuardar))
//						{
//							distritos.add(distritosAGuardar);
//						}
//					}
//					//distritos = listaDis;
//					idDistritoSeleccionado = Constantes.SIN_DISTRITO;
//					
//				} catch (Exception e) {
//					LOGGER.error("Error obteniendo distritos por distribucion parcial");
//					LOGGER.error(e);
//					e.printStackTrace();
//				}
//				distritos = hlpGeo.obtenerCatalogoDistritos(
//						usuario.getIdEstado(), EnumAmbitoDetalleProceso.L);
//				distritos = hlp.getDistritosParaDistribucion(
//						bsdAsociacionesParticipantes, distritos, usuario,
//						cargoSeleccionado);
				
				if (distritos != null && distritos.size() < 1) {
					hlpMsg.mensajesValidacion(41);
				}
			}
		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			e.printStackTrace();
			// agregaMensaje(TipoMensaje.ERROR_MENSAJE,
			// "Error al guardar la distribucion");
		}
	}

	public boolean enviaMensaje() {
		hlpMsg.mensajesValidacion(33);
		return true;
	}

	public boolean todasLasActasCapturadas() {
		System.out.println("Entro a todasLasActasCapturadas()");
		List<DTOCasillaWS> casillasService = new ArrayList<DTOCasillaWS>();
		int casillasEsperadas = 0;
		int casillasCapturadas = 0;
		boolean actasGeneradas = false;
		DTOActaCasillaVotosPK idActas = new DTOActaCasillaVotosPK();
		List<DTOActaCasillaVotos> casillasEnBD = new ArrayList<DTOActaCasillaVotos>();
		try {
			// Busca las casillas aprobadas
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
				casillasService = bsdCargaInformacion
						.casillasAprobadasPorMunicipioLocal(
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(),
								usuario.getIdMunicipioSeleccionado());
				idActas.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
				idActas.setIdDetalleProceso(usuario.getIdDetalleProceso());
				idActas.setIdEstado(usuario.getIdEstado());
				idActas.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
				idActas.setIdRegiduria(-7);
				idActas.setIdTipoCandidatura(cargoSeleccionado);
				casillasEnBD = bsdCapturaVotos
						.getActasMunicipioEnSeccionesParaDistribucion(idActas);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				actasGeneradas = hlpInfo.isTotalActasParcialesByEstado(
						bsdCargaInformacion, bsdInfoGralActas, usuario,
						cargoSeleccionado);
				return actasGeneradas;
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				casillasService = bsdCargaInformacion
						.obtenSecCasillasAprobadasPorRegiduria(
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(),
								usuario.getIdMunicipioSeleccionado(),
								idDemarcacionSeleccionada);
				idActas.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
				idActas.setIdDetalleProceso(usuario.getIdDetalleProceso());
				idActas.setIdEstado(usuario.getIdEstado());
				idActas.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
				idActas.setIdRegiduria(idDemarcacionSeleccionada);
				idActas.setIdTipoCandidatura(cargoSeleccionado);
				casillasEnBD = bsdCapturaVotos
						.getActasMunicipioEnRegidurias(idActas);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				casillasService = bsdCargaInformacion
						.casillasAprobadasPorDistritoLocal(
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(), idDistritoSeleccionado);
				idActas.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
				idActas.setIdDetalleProceso(usuario.getIdDetalleProceso());
				idActas.setIdEstado(usuario.getIdEstado());
				idActas.setIdDistrito(idDistritoSeleccionado);
				idActas.setIdRegiduria(-7);
				idActas.setIdTipoCandidatura(cargoSeleccionado);
				casillasEnBD = bsdCapturaVotos
						.getActasCapturadasEnDistrito(idActas);
			}
			if (casillasService != null && casillasService.size() > 0) {
				casillasEsperadas = casillasService.size();
				casillasCapturadas = casillasEnBD.size();
			}
			if (casillasCapturadas == casillasEsperadas) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean actasCapturadasCompletas() {
		System.out.println("Entro a actasCapturadas()");
		List<DTOCasillaWS> casillasService = new ArrayList<DTOCasillaWS>();
		int casillasEsperadas = 0;
		int casillasCapturadas = 0;
		boolean actasGeneradas = false;
		try {
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
				casillasService = bsdCargaInformacion
						.casillasAprobadasPorMunicipioLocal(
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(),
								usuario.getIdMunicipioSeleccionado());
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				// casillasService =
				// bsdCargaInformacion.casillasAprobadasPorEntidad(usuario.getIdEstado());
				actasGeneradas = hlpInfo.isTotalActasParcialesByEstado(
						bsdCargaInformacion, bsdInfoGralActas, usuario,
						cargoSeleccionado);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				// casillasService =
				// bsdCargaInformacion.obtenSecCasillasAprobadasPorRegiduria(usuario.getIdDetalleProceso(),
				// usuario.getIdEstado(), 17, 1);
				casillasService = bsdCargaInformacion
						.obtenSecCasillasAprobadasPorRegiduria(
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(),
								usuario.getIdMunicipioSeleccionado(),
								idDemarcacionSeleccionada);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				casillasService = bsdCargaInformacion
						.casillasAprobadasPorDistritoLocal(
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(), idDistritoSeleccionado);
			}

			// if(actasGeneradas==false)
			// {
			// casillasEsperadas = casillasService.size();
			// }

			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				if (actasGeneradas) {
					DTODistribucionTotalesPK buscarDistribucion = null;
					buscarDistribucion = new DTODistribucionTotalesPK(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstado(), (Integer) (-5), -6,
							(Integer) (-7), (Integer) (-9), (Integer) (-8),
							cargoSeleccionado, 0, 0);
					boolean distribucionCreada = bsdAsociacionesParticipantes
							.buscarDistribucionCreada(buscarDistribucion);

					if (!distribucionCreada) {
						System.out
								.println("Fin de metodo actasCapturadasCompletas con true");
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				casillasEsperadas = casillasService.size();
				List<DTOActaCasillaVotos> casillasEnBD = new ArrayList<DTOActaCasillaVotos>();
				DTOActaCasillaVotosPK idActas = new DTOActaCasillaVotosPK();
				idActas.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
				idActas.setIdDetalleProceso(usuario.getIdDetalleProceso());
				idActas.setIdEstado(usuario.getIdEstado());
				idActas.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
				idActas.setIdRegiduria(idDemarcacionSeleccionada);
				idActas.setIdTipoCandidatura(cargoSeleccionado);
				DTODistribucionTotalesPK buscarDistribucion = null;
				if (cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
					casillasEnBD = bsdCapturaVotos
							.getActasMunicipioEnSecciones(idActas);
					buscarDistribucion = new DTODistribucionTotalesPK(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstado(), (Integer) (-5),
							usuario.getIdMunicipioSeleccionado(),
							(Integer) (-7), (Integer) (-9), (Integer) (-8),
							cargoSeleccionado, 0, 0);
				} else if (cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
						|| cargoSeleccionado
								.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
					casillasEnBD = bsdCapturaVotos
							.getActasMunicipioEnRegidurias(idActas);
					buscarDistribucion = new DTODistribucionTotalesPK(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstado(), (Integer) (-5),
							usuario.getIdMunicipioSeleccionado(),
							idDemarcacionSeleccionada, (Integer) (-9),
							(Integer) (-8), cargoSeleccionado, 0, 0);
				} else if (cargoSeleccionado
						.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
						|| cargoSeleccionado
								.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
					idActas = new DTOActaCasillaVotosPK();
					idActas.setIdProcesoElectoral(usuario
							.getIdProcesoElectoral());
					idActas.setIdDetalleProceso(usuario.getIdDetalleProceso());
					idActas.setIdEstado(usuario.getIdEstado());
					idActas.setIdDistrito(idDistritoSeleccionado);
					idActas.setIdMunicipio(-6);
					idActas.setIdRegiduria(-7);
					idActas.setIdTipoCandidatura(cargoSeleccionado);
					casillasEnBD = bsdCapturaVotos
							.getActasCapturadasEnDistrito(idActas);
					buscarDistribucion = new DTODistribucionTotalesPK(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstado(), idDistritoSeleccionado, -6,
							-7, (Integer) (-9), (Integer) (-8),
							cargoSeleccionado, 0, 0);

				}
				casillasCapturadas = casillasEnBD.size();

				boolean distribucionCreada = bsdAsociacionesParticipantes
						.buscarDistribucionCreada(buscarDistribucion);

				if (casillasCapturadas >= casillasEsperadas
						&& !distribucionCreada) {
					System.out
							.println("Fin de metodo actasCapturadasCompletas con true");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Fin de metodo actasCapturadasCompletas con false");
		return false;// false
	}

	public void validaciones() {
		DTOActaCasillaVotosPK idActas = new DTOActaCasillaVotosPK();
		idActas.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
		idActas.setIdDetalleProceso(usuario.getIdDetalleProceso());
		idActas.setIdEstado(usuario.getIdEstado());
		idActas.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
		idActas.setIdDistrito(idDistritoSeleccionado);
		idActas.setIdRegiduria(idDemarcacionSeleccionada);
		idActas.setIdTipoCandidatura(cargoSeleccionado);

		DTODistribucionTotalesPK buscarDistribucion = null;

		if (usuario.getIdMunicipioSeleccionado() != null
				&& usuario.getIdMunicipioSeleccionado() > 0) {
			LOGGER.info("ENTRA EN CM");
			// nivel CM
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)) {
				if (todasLasActasCapturadas()) {
					buscarDistribucion = new DTODistribucionTotalesPK(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstado(), idDistritoSeleccionado,
							usuario.getIdMunicipioSeleccionado(),
							idDemarcacionSeleccionada, (Integer) (-9),
							(Integer) (-8), cargoSeleccionado, 0, 0);

					try {
						distribucionGeneradaXCand = bsdAsociacionesParticipantes
								.buscarDistribucionCreada(buscarDistribucion);
						if (distribucionGeneradaXCand) {
							hlpMsg.mensajesValidacion(38);
							distribucionGeneradaXCand = false;
						} else {
							distribucionGeneradaXCand = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					hlpMsg.mensajesValidacion(39);
					distribucionGeneradaXCand = false;
				}
			}
		} else {
			// nivel OPLE
			LOGGER.info("ENTRA EN OPLE");
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				try {
					actasParcialesGeneradas = hlpInfo
							.isTotalActasParcialesByEstado(bsdCargaInformacion,
									bsdInfoGralActas, usuario,
									cargoSeleccionado);

					if (actasParcialesGeneradas) {
						buscarDistribucion = new DTODistribucionTotalesPK(
								usuario.getIdProcesoElectoral(),
								usuario.getIdDetalleProceso(),
								usuario.getIdEstado(), idDistritoSeleccionado,
								Constantes.SIN_MUNICIPIO,
								idDemarcacionSeleccionada, (Integer) (-9),
								(Integer) (-8), cargoSeleccionado, 0, 0);

						distribucionGeneradaXCand = bsdAsociacionesParticipantes
								.buscarDistribucionCreada(buscarDistribucion);

						LOGGER.info("idDetalleProceso "
								+ buscarDistribucion.getIdDetalleProceso());
						LOGGER.info("idProceso "
								+ buscarDistribucion.getIdProcesoElectoral());
						LOGGER.info("idEstado "
								+ buscarDistribucion.getIdEstado());
						LOGGER.info("idMunicipio "
								+ buscarDistribucion.getIdMunicipio());
						LOGGER.info("idDistrito "
								+ buscarDistribucion.getIdDistrito());
						LOGGER.info("idDemarcacion "
								+ buscarDistribucion.getIdRegiduria());
						LOGGER.info("tipoCandidatura " + cargoSeleccionado);

						LOGGER.info("VALIDA DISTRIBUCION REALIZADA: "
								+ distribucionGeneradaXCand);

						if (distribucionGeneradaXCand) {
							hlpMsg.mensajesValidacion(38);
							distribucionGeneradaXCand = false;
						} else {
							distribucionGeneradaXCand = true;
						}

					} else {
						hlpMsg.mensajesValidacion(40);
						distribucionGeneradaXCand = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
				if (todasLasActasCapturadas()) {
					buscarDistribucion = new DTODistribucionTotalesPK(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstado(), idDistritoSeleccionado, -6,
							-7, (Integer) (-9), (Integer) (-8),
							cargoSeleccionado, 0, 0);
					try {
						distribucionGeneradaXCand = bsdAsociacionesParticipantes
								.buscarDistribucionCreada(buscarDistribucion);
						if (distribucionGeneradaXCand) {
							hlpMsg.mensajesValidacion(38);
							distribucionGeneradaXCand = false;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					hlpMsg.mensajesValidacion(39);
					distribucionGeneradaXCand = false;
				}
			}
		}
	}

	public boolean tieneDistribucion() {
		System.out.println("Entro a tieneDistribucion()");
		try {
			DTODistribucionTotalesPK buscarDistribucion = null;
			if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
				buscarDistribucion = new DTODistribucionTotalesPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), usuario.getIdMunicipioSeleccionado(),
						(Integer) (-7), (Integer) (-9), (Integer) (-8),
						cargoSeleccionado, 0, 0);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				buscarDistribucion = new DTODistribucionTotalesPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), -6, (Integer) (-7), (Integer) (-9),
						(Integer) (-8), cargoSeleccionado, 0, 0);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				buscarDistribucion = new DTODistribucionTotalesPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						(Integer) (-5), usuario.getIdMunicipioSeleccionado(),
						idDemarcacionSeleccionada, (Integer) (-9),
						(Integer) (-8), cargoSeleccionado, 0, 0);
			} else if (cargoSeleccionado
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| cargoSeleccionado
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
				buscarDistribucion = new DTODistribucionTotalesPK(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(), usuario.getIdEstado(),
						idDistritoSeleccionado, -6, -7, (Integer) (-9),
						(Integer) (-8), cargoSeleccionado, 0, 0);
			}

			boolean distribucionCreada = bsdAsociacionesParticipantes
					.buscarDistribucionCreada(buscarDistribucion);

			if (distribucionCreada) {
				System.out.println("Fin de metodo tiene distribucion con true");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Fin de metodo tiene distribucion con false");
		return false;
	}

	public void cargaAsociacionesDyn(String cargo) {
		LOGGER.info("Entró a cargaAsociacionesDyn()" + cargo);
	}

	public List<DTODistribucionCandidatos> getLdto() {
		return ldto;
	}

	public void setLdto(List<DTODistribucionCandidatos> ldto) {
		this.ldto = ldto;
	}

	public List<DTOListaRegiduriasWS> getDemarcaciones() {
		return demarcaciones;
	}

	public void setDemarcaciones(List<DTOListaRegiduriasWS> demarcaciones) {
		this.demarcaciones = demarcaciones;
	}

	public Integer getIdDemarcacionSeleccionada() {
		return idDemarcacionSeleccionada;
	}

	public void setIdDemarcacionSeleccionada(Integer idDemarcacionSeleccionada) {
		this.idDemarcacionSeleccionada = idDemarcacionSeleccionada;
	}

	public List<DTODistritosWS> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<DTODistritosWS> distritos) {
		this.distritos = distritos;
	}

	public Integer getIdDistritoSeleccionado() {
		return idDistritoSeleccionado;
	}

	public void setIdDistritoSeleccionado(Integer idDistritoSeleccionado) {
		this.idDistritoSeleccionado = idDistritoSeleccionado;
	}

	public boolean isPanelesVisible() {
		return panelesVisible;
	}

	public void setPanelesVisible(boolean panelesVisible) {
		this.panelesVisible = panelesVisible;
	}

	public boolean isPermiteDistribucion() {
		return permiteDistribucion;
	}

	public void setPermiteDistribucion(boolean permiteDistribucion) {
		System.out.println("Entró a settear variable ");
		if (cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)
				&& permiteDistribucion == false) {
			agregaMensaje(
					TipoMensaje.INFO_MENSAJE.getTipo(),
					"mensajesInfo",
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_no_posible"));
		}
		this.permiteDistribucion = permiteDistribucion;
	}

	public boolean isPermiteGuardar() {
		return permiteGuardar;
	}

	public void setPermiteGuardar(boolean permiteGuardar) {
		this.permiteGuardar = permiteGuardar;
	}

	public List<DTOActaCasillaVotosPK> getAsociaciones() {
		return asociaciones;
	}

	public void setAsociaciones(List<DTOActaCasillaVotosPK> asociaciones) {
		this.asociaciones = asociaciones;
	}

	public Integer getCargoSeleccionado() {
		return cargoSeleccionado;
	}

	public void setCargoSeleccionado(Integer cargoSeleccionado) {
		this.cargoSeleccionado = cargoSeleccionado;
	}

	public List<DTOActaCasillaVotos> getActaVotos() {
		return actaVotos;
	}

	public void setActaVotos(List<DTOActaCasillaVotos> actaVotos) {
		this.actaVotos = actaVotos;
	}

	public List<DTOCandidato> getAsociacionesBase() {
		return asociacionesBase;
	}

	public void setAsociacionesBase(List<DTOCandidato> asociacionesBase) {
		this.asociacionesBase = asociacionesBase;
	}

	public List<DTOActaCasillaVotos> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<DTOActaCasillaVotos> partidos) {
		this.partidos = partidos;
	}

	public List<DTOAsociacion> getPartidosCompletos() {
		return partidosCompletos;
	}

	public void setPartidosCompletos(List<DTOAsociacion> partidosCompletos) {
		this.partidosCompletos = partidosCompletos;
	}

	public List<DTOActaCasillaVotos> getCoaliciones() {
		return coaliciones;
	}

	public void setCoaliciones(List<DTOActaCasillaVotos> coaliciones) {
		this.coaliciones = coaliciones;
	}

	public List<DTOAsociacion> getCoalicionesCompletas() {
		return coalicionesCompletas;
	}

	public void setCoalicionesCompletas(List<DTOAsociacion> coalicionesCompletas) {
		this.coalicionesCompletas = coalicionesCompletas;
	}

	public DTOActaCasillaVotos getCnr() {
		return cnr;
	}

	public void setCnr(DTOActaCasillaVotos cnr) {
		this.cnr = cnr;
	}

	public String getPorcentajeCNR() {
		return porcentajeCNR;
	}

	public void setPorcentajeCNR(String porcentajeCNR) {
		this.porcentajeCNR = porcentajeCNR;
	}

	public String getPorcentajeNulos() {
		return porcentajeNulos;
	}

	public void setPorcentajeNulos(String porcentajeNulos) {
		this.porcentajeNulos = porcentajeNulos;
	}

	public DTOActaCasillaVotos getNulos() {
		return nulos;
	}

	public void setNulos(DTOActaCasillaVotos nulos) {
		this.nulos = nulos;
	}

	public int getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(int totalVotos) {
		this.totalVotos = totalVotos;
	}

	public List<DTOActaCasillaVotos> getDistribucionPP() {
		return distribucionPP;
	}

	public void setDistribucionPP(List<DTOActaCasillaVotos> distribucionPP) {
		this.distribucionPP = distribucionPP;
	}

	public List<DTOAsociacion> getDistribucionPPcompleta() {
		return distribucionPPcompleta;
	}

	public void setDistribucionPPcompleta(
			List<DTOAsociacion> distribucionPPcompleta) {
		this.distribucionPPcompleta = distribucionPPcompleta;
	}

	public List<DTOAsociacion> getDistribucionCandidato() {
		return distribucionCandidato;
	}

	public void setDistribucionCandidato(
			List<DTOAsociacion> distribucionCandidato) {
		this.distribucionCandidato = distribucionCandidato;
	}

	public HLPTipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(String tipo) {
		HLPTipoCandidatura tipoCand = new HLPTipoCandidatura(tipo);
		this.tipoCandidatura = tipoCand;
	}

	public boolean isOPLE() {
		usuario = obtenUsuario();
		if (usuario.getRolUsuario().equals("COMPUTOS.ADMIN.EXT.OPLE.JL")) {
			return true;
		}
		return false;
	}

	public boolean validarRolParaCaptura() {
		if (this.obtenUsuario().getRolUsuario()
				.equalsIgnoreCase(Constantes.ADMIN_PARAM_CAPTURA_OC)
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase(Constantes.ADMIN_CAPTURA_OC)
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.CAPTURA.JL")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.ADMIN.EXT.OPLE.JL")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.CAPTURA.JD")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.CAU.OC")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.CAPTURA.JL")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.CAPTURA.JM")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.ADMIN.JM")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validarRolParaElimina() {

		if (this.obtenUsuario().getRolUsuario()
				.equalsIgnoreCase("COMPUTOS.CONSULTA.JM")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.PARTIDO.CONSULTA.JL")
				|| this.obtenUsuario().getRolUsuario()
						.equalsIgnoreCase("COMPUTOS.PARTIDO.CONSULTA.JM")) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * @return the imagen
	 */
	public StreamedContent getImagen() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String rutaEmblema = externalContext.getRequestParameterMap().get(
				"emblema");

		try {
			this.imagen = UtilUpload.getImagenStreamedContent(rutaEmblema);
		} catch (Exception e) {
			LOGGER.error("Error obtenidno imag");
			LOGGER.error(e);
			return new DefaultStreamedContent();
		}

		return this.imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "MBDistribucion [ldto=" + ldto + ", asociaciones="
				+ asociaciones + ", panelesVisible=" + panelesVisible
				+ ", cargoSeleccionado=" + cargoSeleccionado + ", actaVotos="
				+ actaVotos + ", asociacionesBase=" + asociacionesBase
				+ ", partidos=" + partidos + ", partidosCompletos="
				+ partidosCompletos + ", coaliciones=" + coaliciones
				+ ", coalicionesCompletas=" + coalicionesCompletas + ", cnr="
				+ cnr + ", nulos=" + nulos + ", totalVotos=" + totalVotos
				+ ", porcentajeCNR=" + porcentajeCNR + ", porcentajeNulos="
				+ porcentajeNulos + ", distribucionPP=" + distribucionPP
				+ ", distribucionPPcompleta=" + distribucionPPcompleta
				+ ", distribucionCandidato=" + distribucionCandidato
				+ ", usuario=" + usuario + ", imagen=" + imagen + "]";
	}

	public boolean isCasillasCapturadas100() {
		return casillasCapturadas100;
	}

	public void setCasillasCapturadas100(boolean casillasCapturadas100) {
		this.casillasCapturadas100 = casillasCapturadas100;
	}

	public boolean isActasParcialesGeneradas() {
		return actasParcialesGeneradas;
	}

	public void setActasParcialesGeneradas(boolean actasParcialesGeneradas) {
		this.actasParcialesGeneradas = actasParcialesGeneradas;
	}

	public boolean isDistribucionGeneradaXCand() {
		return distribucionGeneradaXCand;
	}

	public void setDistribucionGeneradaXCand(boolean distribucionGeneradaXCand) {
		this.distribucionGeneradaXCand = distribucionGeneradaXCand;
	}

	public boolean isHabilitaBoton() {
		return habilitaBoton;
	}

	public void setHabilitaBoton(boolean habilitaBoton) {
		this.habilitaBoton = habilitaBoton;
	}

}
