package mx.ine.computosINE.mb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.computosINE.bsd.BSDCapturaVotoRPInterface;
//import mx.ine.computosINE.bsd.BSDCapturaVotoRPInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.bsd.BSDGeneracionActasInterface;
import mx.ine.computosINE.bsd.BSDInformacionGeneralActaInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCConsejo;
import mx.ine.computosINE.dto.DTOConsejero;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTODistribucionTotParcialPK;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTORepresentante;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.form.FormGeneracionActas;
import mx.ine.computosINE.dto.helper.HLPConsejero;
import mx.ine.computosINE.dto.helper.HLPDatosActa;
import mx.ine.computosINE.dto.helper.HLPDemarcacion;
import mx.ine.computosINE.dto.helper.HLPDistrito;
import mx.ine.computosINE.dto.helper.HLPRepresentante;
import mx.ine.computosINE.dto.helper.HLPValidacionesDatosActa;
import mx.ine.computosINE.helper.HLPGeneracionActasMensajes;
import mx.ine.computosINE.helper.HLPGeneradorActas;
import mx.ine.computosINE.helper.HLPInfoGralComputos;
import mx.ine.computosINE.helper.HLPTipoCandidatura;
import mx.ine.computosINE.helper.HLPValidacionesGeneracionActas;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.UtilUpload;
import mx.ine.computosINE.util.Utilidades;
import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.utils.Mensajero;
import opennlp.tools.parser.Cons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Locale;

/**
 * Clase que se encarga de capturar, consultar y modificar los diferentes tipos
 * de actas según el tipo de candidatura.
 * 
 * @author Jean Pierre Pacheco Avila
 * @version 1.0
 * @since 27/04/2017
 *
 */
@Controller("mbGeneradorActa")
@Scope("session")
public class MBGeneradorActas extends MBGeneric implements Serializable {

	/*
	 * Serial
	 */
	private static final long serialVersionUID = -387148031421640711L;

	/*
	 * LOGGER
	 */
	private static final Log log = LogFactory.getLog(MBGeneradorActas.class);

	/*
	 * BSD de Actas
	 */
	@Autowired
	@Qualifier("bsdGeneracionActas")
	public transient BSDGeneracionActasInterface bsdActas;

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
	@Qualifier("bsdDistribucionVotos")
	public transient BSDDistribucionVotosInterface bsdDist;

	/*
	 * Tipo accion capturar
	 */
	private final String accionCapturar = Constantes.ACCION_CAPTURAR;

	/*
	 * Tipo accion modificar
	 */
	private final String accionModificar = Constantes.ACCION_MODIFICAR;

	/*
	 * Tipo accion consultar
	 */
	private final String accionConsultar = Constantes.ACCION_CONSULTAR;

	/*
	 * EL tipo de acta que se va a generar
	 */
	private String tipoActa;

	/*
	 * El tipo accion. Capturar, consultar, modificar.
	 */
	private String tipoAccion;

	/*
	 * Para guardar los datos de captura, consulta y modificacion de un acta
	 */
	private FormGeneracionActas datos;

	/**
	 * Para guardar los datos del tipo de candidatura
	 */
	private HLPTipoCandidatura tipoCandidatura;

	/**
	 * Datos del usuario en la sesion
	 */
	private DTOUsuarioLogin usuario;

	/**
	 * Validador de las reglas
	 */
	@Autowired
	@Qualifier("hplValidacionesGeneraActa")
	private transient HLPValidacionesGeneracionActas validador;

	/**
	 * Validaciones
	 */
	private transient HLPValidacionesDatosActa validaciones;

	/**
	 * Helper para traer combos
	 */
	@Autowired
	@Qualifier("hplInfoGralComputos")
	private transient HLPInfoGralComputos hlpInfo;

	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;

	/**
	 * Ruta de gluster
	 */
	@Autowired
	@Qualifier("rutaGluster")
	protected String rutaGluster;

	/**
	 * Exportacion a PDF
	 */
	@Autowired
	@Qualifier("hlpGeneracionActas")
	private transient HLPGeneradorActas exporter;

	/**
	 * Datos necesarios para exportar a PDF
	 */
	private DTOActaTipoCandidatura dtoActa;

	/**
	 * Variable para validar campos de vista
	 */
	private boolean valido;

	/**
	 * Variable para obtener la bandera EDITABLE
	 */
	private Integer editable;

	/**
	 * Lista para distritos
	 */
	private List<HLPDistrito> distritos;

	/**
	 * Lista para combo de distritos en captura
	 */
	private List<HLPDistrito> distritoCaptura;

	/**
	 * Lista para combo de distritos en consula
	 */
	private List<HLPDistrito> distritoConsulta;

	/**
	 * Distrito seleccionado en el combo de distritos
	 */
	private Integer distritoSeleccionado;

	/**
	 * Lista para demarcaciones
	 */
	private List<HLPDemarcacion> demarcaciones;

	/**
	 * Lista de demarcaciones para vista capturar
	 */
	private List<HLPDemarcacion> demarcacionCaptura;

	/**
	 * Lista de demarcaciones para vista consultar
	 */
	private List<HLPDemarcacion> demarcacionConsulta;

	/**
	 * Demarcación seleccionada
	 */
	private Integer demarcacionSeleccionada;

	/**
	 * Valdidacion para mostrar formulario
	 */
	private boolean muestraFormulario;

	/**
	 * Imagen para emblemas de asociaciones
	 */
	private StreamedContent imagen = new DefaultStreamedContent();

	/**
	 * Bandera para bloquear combos de distritos y demarcacion
	 */
	private Integer bloqueCombo;

	/**
	 * Clase Helper para obtener mensajes de validaciones
	 */
	private HLPGeneracionActasMensajes hlpMsg;

	/**
	 * Variable para renderizar botón imprimir OPLE sólo puede imprimir actas
	 * finales.
	 */
	private Integer validaImprimir;

	/**
	 * Definicion de la internacionalización en español
	 */
	private String locale = "es";

	/**
	 * Variable para renderizar boton de Guardar
	 */
	private boolean botonGuardar;

	/**
	 * Variable para renderizae boton de Editar
	 */
	private boolean botonEditar;

	/**
	 * Variable para renderizar boton de Imprimir
	 */
	private boolean botonImprimir;

	/**
	 * Variable para renderizar boton de Eliminar
	 */
	private boolean botonEliminar;

	/**
	 * Varibale para renderizar botonn de Modificar
	 */
	private boolean botonModificar;

	/**
	 * Variable para identificar roles de usuario 1 = ADMIN.PARAM y ADMIN.OC 2 =
	 * OPLE 3 = CM 4 = ROLES DE CONSULTA
	 * 
	 */
	private Integer tipoUsuario;

	/**
	 * variable para deshabilitar boton de eliminado
	 */
	private boolean disableElimina;

	/**
	 * Variable para renderizar combo de presidente
	 */
	private boolean calidadPresidente;

	/**
	 * Variable para renderizar combo de secretario
	 */
	private boolean calidadSecretario;

	/**
	 * Variable para renderizar combos de consejeros
	 */
	private boolean calidadConsejeros;

	/**
	 * Variable para tipo de acta estatal
	 */
	private boolean actaEstatal = false;

	/**
	 * Variable para quitar el combo si el municipio no tiene casillas
	 * especiales
	 */
	private boolean rpConEspeciales = false;

	/**
	 * Init general.
	 * 
	 * Construye el objeto FormGeneracionActas que es el que servirá para
	 * guardar los datos del formulario de captura y también para mostrar los
	 * datos en la vista de consulta.
	 * 
	 * Obtiene el DTOUsuarioLogin y lo asigna.
	 * 
	 * Crear el objeto HLPTipoCandidatura usando el valor tipoActa. El valor de
	 * tipoActa en este punto es != de null porque este valor es asignado desde
	 * el FLOW antes de llamar al initCapturar, initConsultar o initModificar.
	 * 
	 * Se incializan todos los atributos necesarios para la generacion de actas
	 * por cada tipo de candidatura y rol de usuario
	 * 
	 */
	public void init() {
		datos = new FormGeneracionActas();
		usuario = mbAdmin.getDto().getUsuario();

		log.error("USUARIO: ID_PROCESO: " + usuario.getIdProcesoElectoral());
		log.error("USUARIO: ID_DETALLE: " + usuario.getIdDetalleProceso());
		log.error("USUARIO: ID_ESTADOSELECCIONADO: "
				+ usuario.getIdEstadoSeleccionado());
		log.error("USUARIO: ID_MUNICIPIOSELECCIONADO: "
				+ usuario.getIdMunicipioSeleccionado());
		log.error("USUARIO: ID_: " + usuario.getRolUsuario());
		log.error("USUARIO: AMBITODETALLEPROCESO: "
				+ usuario.getAmbitoDetalleProceso());
		log.error("USUARIO: GRUPO: " + usuario.getRolesLdap());
		log.error("usuarioLogin.toString");
		log.error(usuario.toString());

		log.info("usuario es null: " + usuario == null);
		tipoCandidatura = new HLPTipoCandidatura(tipoActa);
		log.info("Antes de hacer datos.setTextoActa(textoActa())");
		datos.setTextoActa(textoActa());
		validador = new HLPValidacionesGeneracionActas();
		hlpMsg = new HLPGeneracionActasMensajes();
		valido = false;
		validaImprimir = null;
		editable = null;
		botonGuardar = true;
		botonImprimir = true;
		botonEditar = true;
		botonEliminar = true;
		botonModificar = true;
		disableElimina = false;
		calidadPresidente = false;
		calidadSecretario = false;
		calidadConsejeros = false;

		// actaEstatal = -5;

		// Variable que muestra el formulario de acuerdo a cada escenario
		muestraFormulario = false;

		// variable que bloquea el combo en diferentes escenarios
		bloqueCombo = null;

		// variables que almacenan el valor que se selecciona
		// en los combos de distritos y demarcaciones
		// para candidatura de DIPUTADOS & REGIDURIAS
		distritoSeleccionado = null;
		demarcacionSeleccionada = null;

		// Se inicilizan las listas de distritos y demarcaciones para construir
		// las listas de captura y consulta de las mismas
		distritos = new ArrayList<HLPDistrito>();
		demarcaciones = new ArrayList<HLPDemarcacion>();

		// Se inicializan las listas para Captura y Consulta de demarcaciones y
		// distritos.
		demarcacionCaptura = new ArrayList<HLPDemarcacion>();
		demarcacionConsulta = new ArrayList<HLPDemarcacion>();
		distritoCaptura = new ArrayList<HLPDistrito>();
		distritoConsulta = new ArrayList<HLPDistrito>();

		tipoUsuario = -1;

		if (usuario.getRolUsuario().equals(Constantes.CONSULTA_CONSEJERO_OC)
				|| usuario.getRolUsuario().equals(Constantes.CONSULTA_0C)
				|| usuario.getRolUsuario().equals(
						Constantes.ADMIN_LECTURA_CAU_OC)
				|| usuario.getRolUsuario().equals(Constantes.CONSULTA_JL)
				|| usuario.getRolUsuario().equals(
						Constantes.CONSULTA_PARTIDO_JL)
				|| usuario.getRolUsuario().equals(
						Constantes.CONSULTA_PARTIDO_JM)
				|| usuario.getRolUsuario()
						.equals(Constantes.CONSULTA_MUNICIPAL)) {
			tipoUsuario = 4;
		}

		obtenCombosDistritosDemarcaciones();

	}

	/**
	 * Método init para capturar un acta.
	 * 
	 * Contruye los objetos necesarios para realizar una captura de un acta.
	 * 
	 * Hace una llamada a init()
	 * 
	 * Crea las listas de consejeros y representantes para ser capturados en la
	 * vista de captura.
	 * 
	 */
	public void initCapturar() {
		log.info("Entro al metodo()");
		init();

		// Valor de vista, renderizar componentes Captura
		tipoAccion = accionCapturar;
		log.info("Pasa init");

		asignaMunicipioOple();

		// Validaciones por cada tipo de candidatura
		validacionesGeneracionActas();

		validaBotonesActas();

		// Obtenemos valores del usuario, cuando entra en la vista Captura
		Integer idDetalle = usuario.getIdDetalleProceso();
		Integer idEstado = usuario.getIdEstadoSeleccionado();
		Integer idMunicipio = null;

		if (usuario.getIdMunicipioSeleccionado() != null
				&& usuario.getIdMunicipioSeleccionado() > 0) {
			// Si el municipio es diferente de null, seteamos lo que trae el
			// usuario
			idMunicipio = usuario.getIdMunicipioSeleccionado();
		} else {
			// Valor de default para idMunicipio - Archivo, REGLAS
			// idMunicipio = -6;
			idMunicipio = null;
		}

		log.info("NO ENTRA EN CONDICION DE DIPRPESTATAL");
		// Traer consejeros de C_CONSEJEROS
		List<DTOCConsejo> tiposConsejeros = new ArrayList<DTOCConsejo>();
		try {

			if (usuario.getIdMunicipioSeleccionado() > 0) {
				List<DTOCConsejo> tiposConsejeross = bsdActas.getCatalogoConsejero();
				for(int i = 0; i < tiposConsejeross.size()-2; i++){
					tiposConsejeros.add(tiposConsejeross.get(i));
				}
			} else {
				tiposConsejeros = bsdActas.getCatalogoConsejero();
				for (DTOCConsejo dtoConsejeros : tiposConsejeros) {
					log.info("Lista de consejeros: "
							+ dtoConsejeros.getTituloConsejero());
					;
				}
			}
			// Se guarda la lista de consejeros, que se genera en metodo --
			// generaListaConsejeros
			datos.setConsejeros(generaListaConsejeros(tiposConsejeros));
		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error obteniendo los tipos de consejeros");
			log.error(e);

			e.printStackTrace();
			return;

		}

		// Traer asociaciones
		List<DTOCandidatoWS> asociacioness = null;
		List<DTOCandidatoWS> asociaciones = null;
		try {

			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_AYUNTAMIENTO)
					|| tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_GOBERNADOR)) {

				// No necesitamos distrito ni demarcacion asi que pasamos
				// null
				asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
						idDetalle, idEstado, null,
						tipoCandidatura.getIdTipoCandidatura(), idMunicipio,
						null);

				asociaciones = hlpInfo
						.cargaCandidatosUnicosByCandidatura(asociacioness);

				for (DTOCandidatoWS dtoAsociacion : asociaciones) {
					log.info("Aso - idEstado: " + dtoAsociacion.getIdEstado());
					log.info("Aso-idAsociacion: "
							+ dtoAsociacion.getIdAsociacion());
					log.info("Aso-tipoAsociacion: "
							+ dtoAsociacion.getTipoAsociacion());
					log.info("Aso-nombreAsociacion: "
							+ dtoAsociacion.getNombreAsociacion());
					log.info("Aso-emblema: " + dtoAsociacion.getEmblema());
					log.info("Aso-siglas: " + dtoAsociacion.getSiglas());
					log.info("Orden: " + dtoAsociacion.getOrden());
				}
				// Se guarda la lista de representantes, que se genera en
				// metodo
				// --
				// generaListaRepresentantes
				datos.setRepresentantes(generaListaRepresentantes(asociaciones));

			}

			// Aqui va lo de la ESTATAL
			List<Integer> idAsociacionesDipRP = null;
			List<DTOCandidatoWS> asocDipRPEstatal = new ArrayList<DTOCandidatoWS>();
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_RP)
					&& actaEstatal) {
				log.info("INICIO EL SUPLICIO");
				idAsociacionesDipRP = bsdActas
						.consultaIdAsociacionesDipRPEstatal();

				asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
						idDetalle, idEstado, null,
						tipoCandidatura.getIdTipoCandidatura(), null, null);

				asociaciones = hlpInfo
						.cargaCandidatosUnicosByCandidatura(asociacioness);

				for (DTOCandidatoWS asoc : asociaciones) {
					for (Integer idAs : idAsociacionesDipRP) {
						if (idAs.equals(asoc.getIdAsociacion())
								&& asoc.getTipoAsociacion().equals(
										Constantes.TIPO_ASOCIACION_PARTIDO)) {
							asocDipRPEstatal.add(asoc);
							break;
						}
					}
				}

				datos.setRepresentantes(generaListaRepresentantes(asocDipRPEstatal));
				log.info("ACABO EL SUPLICIO");
			}

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(52);
			log.error("Error obteniendo asociaciones");
			log.error(e);
			e.printStackTrace();
		}

		// }

	}

	/**
	 * Método init para consultar actas. Hace una llamada a init() Obtiene los
	 * datos del acta que ya ha sido guardada. Obtiene los consejeros y
	 * representantes asociados a el acta.
	 */
	public void initConsultar() {
		init();
		tipoAccion = accionConsultar;
		log.info("init Consultar");

		// if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)
		// || usuario.getRolUsuario().equals(
		// Constantes.ADMIN_PARAM_CAPTURA_OC)
		// || usuario.getRolUsuario().equals(Constantes.ADMIN_CAPTURA_OC)
		// || usuario.getRolUsuario().equals(Constantes.CONSULTA_JL)
		// || usuario.getRolUsuario().equals(
		// Constantes.CONSULTA_PARTIDO_JL)
		// || usuario.getRolUsuario().equals(
		// Constantes.CONSULTA_CONSEJERO_OC)
		// || usuario.getRolUsuario().equals(Constantes.CONSULTA_0C)
		// || usuario.getRolUsuario().equals(
		// Constantes.ADMIN_LECTURA_CAU_OC)) {
		//
		// if(!(usuario.getIdMunicipioSeleccionado() > 0)){
		// usuario.setIdMunicipioSeleccionado(Constantes.SIN_MUNICIPIO);
		// }
		// }

		asignaMunicipioOple();

		// Validaciones por cada tipo de candidatura
		validacionesGeneracionActas();
		validaBotonesActas();

		try {

			log.info("init consultar inicio");
			Object pkActa = generaPkActa(usuario);
			DTOBase acta = bsdActas.getActa(pkActa);

			log.info("init consultat medio");
			if (acta != null) {
				log.info("init consultar inicia if");

				// Objeto necesario para exportar a pdf.
				dtoActa = (DTOActaTipoCandidatura) acta;

				// Setear datos de acta en FormGeneracionActas
				datos.setCampos(acta);

				// Obtener repres y consejeros de esta acta
				List<DTOConsejero> consejeros = bsdActas.getConsejeros(pkActa);
				List<DTORepresentante> repres = bsdActas
						.getRepresentantes(pkActa);

				// Setear listas para mostrarlas en la vista
				datos.setConsejeros(obtenListaConsejeros(consejeros));

				// Obtenemos valores del usuario, cuando entra en la vista
				// Captura
				Integer idDetalle = usuario.getIdDetalleProceso();
				Integer idEstado = usuario.getIdEstadoSeleccionado();
				Integer idMunicipio = null;

				if (usuario.getIdMunicipioSeleccionado() != null
						&& usuario.getIdMunicipioSeleccionado() > 0) {
					// Si el municipio es diferente de null, seteamos lo que
					// trae el
					// usuario
					idMunicipio = usuario.getIdMunicipioSeleccionado();
				} else {
					// Valor de default para idMunicipio - Archivo, REGLAS
					// idMunicipio = -6;
					idMunicipio = null;
				}
				// TODO
				// Traer asociaciones
				List<DTOCandidatoWS> asociaciones = null;
				List<DTOCandidatoWS> asociacioness = null;
				List<DTOCandidatoWS> listaPartidoRP = null;
				try {

					if (tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_AYUNTAMIENTO)
							|| tipoCandidatura.getIdTipoCandidatura().equals(
									Constantes.ID_TIPO_CAND_GOBERNADOR)) {

						// No necesitamos distrito ni demarcacion asi que
						// pasamos
						// null
						asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
								idDetalle, idEstado, null,
								tipoCandidatura.getIdTipoCandidatura(),
								idMunicipio, null);

						asociaciones = hlpInfo
								.cargaCandidatosUnicosByCandidatura(asociacioness);
					}

					// Asociaciones para candidatura Diputados RP Estatal
					List<Integer> idAsociacionesDipRP = null;
					List<DTOCandidatoWS> asocDipRPEstatal = new ArrayList<DTOCandidatoWS>();
					if (tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_DIPUTADO_RP)
							&& actaEstatal) {
						log.info("INICIO EL SUPLICIO");
						idAsociacionesDipRP = bsdActas
								.consultaIdAsociacionesDipRPEstatal();

						asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
								idDetalle, idEstado, null,
								tipoCandidatura.getIdTipoCandidatura(), null,
								null);

						asociaciones = hlpInfo
								.cargaCandidatosUnicosByCandidatura(asociacioness);

						for (DTOCandidatoWS asoc : asociaciones) {
							for (Integer idAs : idAsociacionesDipRP) {
								if (idAs.equals(asoc.getIdAsociacion())
										&& asoc.getTipoAsociacion()
												.equals(Constantes.TIPO_ASOCIACION_PARTIDO)) {
									asocDipRPEstatal.add(asoc);
									break;
								}
							}
						}

						log.info("ACABO EL SUPLICIO");
					}

					// Obtener representantes para Diputados RP Estatal
					if (tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_DIPUTADO_RP)
							&& actaEstatal) {
						List<DTORepresentante> representantes = llenaEspaciosRepresentantesNoCapturados(
								bsdActas.getRepresentantes(dtoActa.getPk()),
								asocDipRPEstatal);

						datos.setRepresentantes(obtenListaRepresentantes(representantes));
						// Otros tipos de candidaturas - Gobernador y
						// Ayuntamiento
					} else {
						List<DTORepresentante> representantes = llenaEspaciosRepresentantesNoCapturados(
								bsdActas.getRepresentantes(dtoActa.getPk()),
								asociaciones);

						datos.setRepresentantes(obtenListaRepresentantes(representantes));

					}

				} catch (Exception e) {
					hlpMsg.mensajesValidacion(52);
					log.error("Error obteniendo asociaciones");
					log.error(e);
					e.printStackTrace();
					return;
				}

				log.info("init consultar termina if");

				// Validar si el acta final ya fue generada, sólo para consultar
				// actas parciales
				if (usuario.getIdMunicipioSeleccionado() > 0) {
					if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)) {
						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_GOBERNADOR)) {
							if (actaFinalGenerada(
									(DTOActaTipoCandidaturaPK) pkActa, null)) {
								disableElimina = true;
								hlpMsg.mensajesValidacion(34);
							}
							if (hlpInfo.getVerificarDistribucionFinal(bsdInfo,
									usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								disableElimina = true;
								hlpMsg.mensajesValidacion(57);
							}
						}
					}
				}

				// Guarda editable de acuerdo a valor que trae el acta
				editable = ((DTOActaTipoCandidatura) acta).getEditable();

			}
			log.info("init consultar termina");

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error en initConsultar");
			log.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Metodo init para modificar actas
	 * 
	 */
	public void initModificar() {
		init();
		tipoAccion = accionModificar;

		// if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)
		// || usuario.getRolUsuario().equals(
		// Constantes.ADMIN_PARAM_CAPTURA_OC)
		// || usuario.getRolUsuario().equals(Constantes.ADMIN_CAPTURA_OC)
		// || usuario.getRolUsuario().equals(Constantes.CONSULTA_JL)
		// || usuario.getRolUsuario().equals(
		// Constantes.CONSULTA_PARTIDO_JL)
		// || usuario.getRolUsuario().equals(
		// Constantes.CONSULTA_CONSEJERO_OC)
		// || usuario.getRolUsuario().equals(Constantes.CONSULTA_0C)
		// || usuario.getRolUsuario().equals(
		// Constantes.ADMIN_LECTURA_CAU_OC)) {
		//
		// if(!(usuario.getIdMunicipioSeleccionado() > 0)){
		// usuario.setIdMunicipioSeleccionado(Constantes.SIN_MUNICIPIO);
		// }
		//
		// }

		asignaMunicipioOple();

		// Validaciones para cada tipo de candidatura
		validacionesGeneracionActas();
		validaBotonesActas();

		try {
			log.info("init modificar inicio");
			Object pkActa = generaPkActa(usuario);
			DTOBase acta = bsdActas.getActa(pkActa);

			log.info("init modificar medio");
			if (acta != null) {
				log.info("init modificar inicia if");

				// Objeto necesario para exportar a pdf.
				dtoActa = (DTOActaTipoCandidatura) acta;

				// Setear datos de acta en FormGeneracionActas
				datos.setCampos(acta);

				// Obtener repres y consejeros de esta acta
				List<DTOConsejero> consejeros = bsdActas.getConsejeros(pkActa);
				List<DTORepresentante> repres = bsdActas
						.getRepresentantes(pkActa);

				// Obtenemos catalogo de consejeros
				List<DTOCConsejo> catCons = new ArrayList<DTOCConsejo>();
				
				if (usuario.getIdMunicipioSeleccionado() > 0) {
					List<DTOCConsejo> tiposConsejeross = bsdActas.getCatalogoConsejero();
					for(int i = 0; i < tiposConsejeross.size()-2; i++){
						catCons.add(tiposConsejeross.get(i));
					}
				} else {
					catCons = bsdActas.getCatalogoConsejero();
					for (DTOCConsejo dtoConsejeros : catCons) {
						log.info("Lista de consejeros: "
								+ dtoConsejeros.getTituloConsejero());
						;
					}
				}
				
				List<DTOConsejero> consejerosC = llenaEspaciosConsejerosNoCapturados(
						consejeros, catCons);

				// Obtenemos valores del usuario, cuando entra en la vista
				// Captura
				Integer idDetalle = usuario.getIdDetalleProceso();
				Integer idEstado = usuario.getIdEstadoSeleccionado();
				Integer idMunicipio = null;

				if (usuario.getIdMunicipioSeleccionado() != null
						&& usuario.getIdMunicipioSeleccionado() > 0) {
					// Si el municipio es diferente de null, seteamos lo que
					// trae el
					// usuario
					idMunicipio = usuario.getIdMunicipioSeleccionado();
				} else {
					// Valor de default para idMunicipio - Archivo, REGLAS
					// idMunicipio = -6;
					idMunicipio = null;
				}
				// Obetenmos las asociaciones coaliciones
				List<DTOCandidatoWS> asociaciones = null;
				List<DTOCandidatoWS> asociacioness = null;
				try {
					// Aqui va lo de la ESTATAL
					List<Integer> idAsociacionesDipRP = null;
					List<DTOCandidatoWS> asocDipRPEstatal = new ArrayList<DTOCandidatoWS>();
					if (tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_DIPUTADO_RP)
							&& actaEstatal) {
						log.info("INICIO EL SUPLICIO");
						idAsociacionesDipRP = bsdActas
								.consultaIdAsociacionesDipRPEstatal();

						asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
								idDetalle, idEstado, null,
								tipoCandidatura.getIdTipoCandidatura(), null,
								null);

						asociaciones = hlpInfo
								.cargaCandidatosUnicosByCandidatura(asociacioness);

						for (DTOCandidatoWS asoc : asociaciones) {
							for (Integer idAs : idAsociacionesDipRP) {
								if (idAs.equals(asoc.getIdAsociacion())
										&& asoc.getTipoAsociacion()
												.equals(Constantes.TIPO_ASOCIACION_PARTIDO)) {
									asocDipRPEstatal.add(asoc);
									break;
								}
							}
						}

						List<DTORepresentante> representantes = llenaEspaciosRepresentantesNoCapturados(
								bsdActas.getRepresentantes(dtoActa.getPk()),
								asocDipRPEstatal);

						datos.setRepresentantes(obtenListaRepresentantes(representantes));
						log.info("ACABO EL SUPLICIO");
					} else {
						asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
								idDetalle, idEstado, null,
								tipoCandidatura.getIdTipoCandidatura(),
								idMunicipio, null);

						asociaciones = hlpInfo
								.cargaCandidatosUnicosByCandidatura(asociacioness);

						List<DTORepresentante> representantes = llenaEspaciosRepresentantesNoCapturados(
								bsdActas.getRepresentantes(dtoActa.getPk()),
								asociaciones);

						datos.setRepresentantes(obtenListaRepresentantes(representantes));
					}
				} catch (Exception e) {
					hlpMsg.mensajesValidacion(52);
					log.error(e);
					log.error("Error obteniendo asociaciones");
					e.printStackTrace();
					return;
				}
				log.info("asociaciones mod ");
				for (DTOCandidatoWS cand : asociaciones) {
					log.info("tipoAsociacion: " + cand.getTipoAsociacion());
					log.info("idAsociacion: " + cand.getIdAsociacion());
					log.info("nombreAsociacion: " + cand.getNombreAsociacion());
					log.info("Emblema: " + cand.getEmblema());
					log.info("nombreCandidato: " + cand.getNombreCandidato());

				}

				// Setear listas para mostrarlas en la vista
				datos.setConsejeros(obtenListaConsejeros(consejerosC));
				log.info("init modificar termina if");

			}
			log.info("init modificar termina");

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error en initModificar");
			log.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Metodo que valida si el acta final ya fue generada para no eliminar actas
	 * parciales
	 * 
	 */
	public boolean actaFinalGenerada(DTOActaTipoCandidaturaPK pkActa,
			Integer idDistrito) {
		boolean actaGenerada = false;
		log.info("actaFinalGenerada");

		asignaMunicipioOple();

		try {
			// Validar si esta el acta final
			if (usuario.getIdMunicipioSeleccionado().equals(
					Constantes.SIN_MUNICIPIO)) {
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						|| tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
					log.info("Entra en validacion...");
					pkActa.setTipoActa(Constantes.TIPO_ACTA_ESTATAL);
					pkActa.setIdDistrito(Constantes.SIN_DISTRITO);
					pkActa.setIdTipoCandidatura(Constantes.ID_TIPO_CAND_DIPUTADO_RP);
				}
			} else {

				pkActa.setTipoActa(Constantes.TIPO_ACTA_FINAL);
				if (idDistrito == null) {
					pkActa.setIdDistrito(Constantes.SIN_DISTRITO);
				} else {
					pkActa.setIdDistrito(idDistrito);
				}

			}

			pkActa.setIdMunicipio(Constantes.SIN_MUNICIPIO);

			log.info(pkActa.getIdProcesoElectoral());
			log.info(pkActa.getIdDetalleProceso());
			log.info(pkActa.getIdEstado());
			log.info(pkActa.getIdDistrito());
			log.info(pkActa.getIdMunicipio());
			log.info(pkActa.getIdRegiduria());
			log.info(pkActa.getIdComunidad());
			log.info(pkActa.getIdTipoCandidatura());
			log.info(pkActa.getTipoActa());

			DTOBase acta = bsdActas.getActa(pkActa);
			if (acta != null) {
				log.info("acta != null"
						+ ((DTOActaTipoCandidatura) acta).getUbicacionComputo());
				actaGenerada = true;
			}
		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error en validar si el acta final ya fue generada");
			log.error(e);
			e.printStackTrace();
			return false;
		}

		return actaGenerada;
	}

	/**
	 * 
	 * Validacion de botones, de acuerdo al nivel en que se encuentre el usuario
	 * NIVEL MUNICIPAL = idMunicipioSeleccionado > 0; NIVEL ESTADO =
	 * idMunicipioSeleccionado = 0;
	 * 
	 */
	public void validaBotonesActas() {
		if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)) {
			if (usuario.getIdMunicipioSeleccionado() > 0) {
				// Botones
				botonGuardar = false;
				botonModificar = false;
				botonImprimir = false;
				botonEliminar = true;
			} else {
				botonGuardar = true;
				botonModificar = true;
				botonEditar = false;
				botonImprimir = true;
				botonEliminar = true;
				editable = 1;
			}

		}
		if (usuario.getRolUsuario().equals(Constantes.ADMIN_PARAM_CAPTURA_OC)
				|| usuario.getRolUsuario().equals(Constantes.ADMIN_CAPTURA_OC)
				|| usuario.getRolUsuario().equals(Constantes.CAPTURA_JL)) {
			editable = 1;
		}
		if (tipoUsuario.equals(4)) {
			log.info("ENVALIDABOTONES");
			botonImprimir = true;
			botonGuardar = false;
			botonModificar = false;
			botonEditar = false;
			botonEliminar = false;
		}
		/*
		 * if (usuario.getRolUsuario().equals(Constantes.CAPTURA_MUNICIPAL) &&
		 * editable == 0) { botonImprimir = true; botonGuardar = true;
		 * botonModificar = false; botonEditar = false; botonEliminar = false; }
		 */
	}

	/**
	 * Validaciones para todos los usuarios, excepto OPLE
	 */
	public void validaGeneric() {

		asignaMunicipioOple();

		/**
		 * Validaciones genericas para Actas a nivel Municipal
		 * idMunicipioSeleccionado() != 0
		 */
		try {
			if (usuario.getIdMunicipioSeleccionado().intValue() > 0) {

				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {

					try {
						// Verificamos que se encuentre la distribucion
						if (hlpInfo
								.getVerificarDistribucionFinal(bsdInfo,
										usuario,
										tipoCandidatura.getIdTipoCandidatura())) {
							if (isGenerada()) {
								if (tipoAccion.equals(accionCapturar)) {

									// Usuario de consulta
									if (tipoUsuario.equals(4)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(31);
									} else {
										// Otros usuarios
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(3);

									}

								} else {
									muestraFormulario = true;
									// Calidad
									calidadPresidente = true;
									calidadSecretario = false;
									calidadConsejeros = true;
									// Requiere permisos OPLE
									if (tipoAccion.equals(accionModificar)) {
										if (usuario.getRolUsuario().equals(
												Constantes.CAPTURA_MUNICIPAL)) {
											if (editable.intValue() == 0) {
												hlpMsg.mensajesValidacion(25);
											}
										}
										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											if (tipoAccion
													.equals(accionModificar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(31);
											}
											if (tipoAccion
													.equals(accionConsultar)) {
												muestraFormulario = true;
											}

										}
									}
								}
							} else {
								if (tipoAccion.equals(accionCapturar)) {
									muestraFormulario = true;
									// Calidad
									calidadPresidente = true;
									calidadSecretario = false;
									calidadConsejeros = true;
									// Usuario de consulta
									if (tipoUsuario.equals(4)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(31);
									}
								} else {

									// Usuario de consulta
									if (tipoUsuario.equals(4)) {
										if (tipoAccion.equals(accionModificar)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(31);
										}
										if (tipoAccion.equals(accionConsultar)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(32);
										}
									} else {
										// otros usuarios
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(4);
									}
								}
							}
						} else {
							// Mensaje de distribucion requerida
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(26);
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion CM AYUDANTAMIENTO");
						log.error(e);
						e.printStackTrace();
						return;
					}
				}

				// GOBERNADOR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_GOBERNADOR)) {

					try {
						// Si no esta capturada verificamos la captura
						// 100/100
						if (hlpInfo.isCapturaActas100Municipio(bsdInfo,
								bsdInfoGralActas, usuario,
								tipoCandidatura.getIdTipoCandidatura())) {
							// if(true){
							log.info("CM GOBER ENTRA");

							// Consulta sí está generada
							if (isGenerada()) {
								if (tipoAccion.equals(accionCapturar)) {
									log.info("GOB A");
									// Usuario de consulta
									if (tipoUsuario.equals(4)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(31);
									} else {
										log.info("GOB B");
										// otros usuarios
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(3);
									}
								} else {
									muestraFormulario = true;
									// Calidad
									calidadPresidente = true;
									calidadSecretario = false;
									calidadConsejeros = true;

									// Requiere permisos OPLE
									if (tipoAccion.equals(accionModificar)) {
										if (usuario.getRolUsuario().equals(
												Constantes.CAPTURA_MUNICIPAL)) {
											if (editable.intValue() == 0) {
												hlpMsg.mensajesValidacion(25);
											}
										}
										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											if (tipoAccion
													.equals(accionModificar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(31);
											}
											if (tipoAccion
													.equals(accionConsultar)) {
												muestraFormulario = true;
											}

										}
									}
								}
								// Si no está generada
							} else {
								if (tipoAccion.equals(accionCapturar)) {
									muestraFormulario = true;
									// Calidad
									calidadPresidente = true;
									calidadSecretario = false;
									calidadConsejeros = true;

									// Usuario de consulta
									if (tipoUsuario.equals(4)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(31);
									}
								} else {

									// Usuario de consulta
									if (tipoUsuario.equals(4)) {
										if (tipoAccion.equals(accionModificar)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(31);
										}
										if (tipoAccion.equals(accionConsultar)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(32);
										}
									} else {
										// Otros usuarios
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(4);
									}
								}

							}
							// Si no está la captura 100/100
						} else {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(27);
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion CM GOBERNADOR");
						log.error(e);
						e.printStackTrace();
						return;
					}

				}

				// DIPUTADO MR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {

					if (tipoAccion.equals(accionCapturar)) {
						// Calidad
						calidadPresidente = true;
						calidadSecretario = false;
						calidadConsejeros = true;

						if (tipoUsuario.equals(4)) {
							bloqueCombo = 1;
							hlpMsg.mensajesValidacion(31);
						} else {

							if (distritoCaptura.isEmpty()
									&& distritoConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(5);
							}

							if (!distritoConsulta.isEmpty()
									&& distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(6);
							}

						}
					}
					if (tipoAccion.equals(accionConsultar)
							|| tipoAccion.equals(accionModificar)) {

						if (tipoUsuario.equals(4)) {
							if (tipoAccion.equals(accionModificar)) {
								bloqueCombo = 1;
								hlpMsg.mensajesValidacion(31);
							}
							if (tipoAccion.equals(accionConsultar)) {
								if (distritoCaptura.isEmpty()
										&& distritoConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}

								if (distritoConsulta.isEmpty()
										&& !distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}
							}

						} else {

							if (distritoCaptura.isEmpty()
									&& distritoConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(5);
							}

							if (distritoConsulta.isEmpty()
									&& !distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(7);
							}
						}
					}
				}

				// DIPUTADO RP
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& !actaEstatal) {

					if (tipoAccion.equals(accionCapturar)) {
						if (tipoUsuario.equals(4)) {
							bloqueCombo = 1;
							hlpMsg.mensajesValidacion(31);
						} else {

							if (!hlpInfo.existenCasillasEspecialesByMunicipio(
									bsdInfo, bsdInfoGralActas, usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								hlpMsg.mensajesValidacion(62);
							} else if (distritoCaptura.isEmpty()
									&& distritoConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(5);
							}

							else if (!distritoConsulta.isEmpty()
									&& distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(6);
							}

						}
					}
					if (tipoAccion.equals(accionConsultar)
							|| tipoAccion.equals(accionModificar)) {

						if (tipoUsuario.equals(4)) {
							if (tipoAccion.equals(accionModificar)) {
								bloqueCombo = 1;
								hlpMsg.mensajesValidacion(31);
							}
							if (tipoAccion.equals(accionConsultar)) {

								if (!hlpInfo
										.existenCasillasEspecialesByMunicipio(
												bsdInfo, bsdInfoGralActas,
												usuario, tipoCandidatura
														.getIdTipoCandidatura())) {
									hlpMsg.mensajesValidacion(62);
								}

								else if (distritoCaptura.isEmpty()
										&& distritoConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}

								else if (distritoConsulta.isEmpty()
										&& !distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}
							}

						} else {

							if (!hlpInfo.existenCasillasEspecialesByMunicipio(
									bsdInfo, bsdInfoGralActas, usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								hlpMsg.mensajesValidacion(62);
							}

							else if (distritoCaptura.isEmpty()
									&& distritoConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(5);
							}

							else if (distritoConsulta.isEmpty()
									&& !distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(7);
							}
						}
					}
				}

				// REGIDURIA MR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_MR)) {

					if (tipoAccion.equals(accionCapturar)) {
						// Calidad
						calidadPresidente = true;
						calidadSecretario = false;
						calidadConsejeros = true;

						if (tipoUsuario.equals(4)) {
							bloqueCombo = 1;
							hlpMsg.mensajesValidacion(31);
						} else {
							if (demarcacionCaptura.isEmpty()
									&& demarcacionConsulta.isEmpty()) {

								hlpMsg.mensajesValidacion(8);
							}
							if (!demarcacionConsulta.isEmpty()
									&& demarcacionCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(9);
							}

						}
					}

					if (tipoAccion.equals(accionConsultar)
							|| tipoAccion.equals(accionModificar)) {

						if (tipoUsuario.equals(4)) {

							if (tipoAccion.equals(accionModificar)) {
								bloqueCombo = 1;
								hlpMsg.mensajesValidacion(31);
							}
							if (tipoAccion.equals(accionConsultar)) {
								if (demarcacionCaptura.isEmpty()
										&& demarcacionConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}

								if (demarcacionConsulta.isEmpty()
										&& !demarcacionCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}
							}

						} else {
							if (demarcacionCaptura.isEmpty()
									&& demarcacionConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(8);
							}

							if (demarcacionConsulta.isEmpty()
									&& !demarcacionCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(10);
							}

						}

					}
				}

				// REGIDURIA RP
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
					if (tipoAccion.equals(accionCapturar)) {

						// Calidad
						calidadPresidente = true;
						calidadSecretario = false;
						calidadConsejeros = true;

						if (tipoUsuario.equals(4)) {
							bloqueCombo = 1;
							hlpMsg.mensajesValidacion(31);
						} else {

							// Se agrega la validacion para cuando no existen
							// casillas especiales para regiduria rp
							if (!hlpInfo.existenCasillasEspecialesByMunicipio(
									bsdInfo, bsdInfoGralActas, usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								hlpMsg.mensajesValidacion(63);
							}

							else if (demarcacionCaptura.isEmpty()
									&& demarcacionConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(59);
							} else if (!demarcacionConsulta.isEmpty()
									&& demarcacionCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(9);
							}

						}
					}

					if (tipoAccion.equals(accionConsultar)
							|| tipoAccion.equals(accionModificar)) {

						if (tipoUsuario.equals(4)) {

							if (tipoAccion.equals(accionModificar)) {
								bloqueCombo = 1;
								hlpMsg.mensajesValidacion(31);
							}
							if (tipoAccion.equals(accionConsultar)) {
								// Se agrega la validacion para cuando no
								// existen casillas especiales para regiduria rp
								if (!hlpInfo
										.existenCasillasEspecialesByMunicipio(
												bsdInfo, bsdInfoGralActas,
												usuario, tipoCandidatura
														.getIdTipoCandidatura())) {
									hlpMsg.mensajesValidacion(63);
								} else if (demarcacionCaptura.isEmpty()
										&& demarcacionConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}

								else if (demarcacionConsulta.isEmpty()
										&& !demarcacionCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(32);
								}
							}

						} else {

							// Se agrega la validacion para cuando no existen
							// casillas especiales para regiduria rp
							if (!hlpInfo.existenCasillasEspecialesByMunicipio(
									bsdInfo, bsdInfoGralActas, usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								hlpMsg.mensajesValidacion(63);
							}

							else if (demarcacionCaptura.isEmpty()
									&& demarcacionConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(32);
							}

							else if (demarcacionConsulta.isEmpty()
									&& !demarcacionCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(10);
							}

						}

					}
				}
			}

			/**
			 * ACTAS FINALES PARA ADMIN:PARAM: GOBERNADOR, DIPUTADO MR,
			 * DIPUTADOR RP
			 */
			else {

				// Acta de GOBERNADOR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_GOBERNADOR)) {
					log.info("Entra en ayuntamiento de muestraFormularioGobAyu");

					try {
						// Verificamos que todas las actas parciales fueron
						// generadas
						if (hlpInfo.isTotalActasParcialesByEstado(bsdInfo,
								bsdInfoGralActas, usuario,
								tipoCandidatura.getIdTipoCandidatura())) {
							// Verificamos que se encuentre la distribucion
							if (hlpInfo.getVerificarDistribucionFinal(bsdInfo,
									usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								if (isGenerada()) {
									if (tipoAccion.equals(accionCapturar)) {

										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(31);
										} else {
											// otros usaurios
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(3);
										}
									} else {
										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											if (tipoAccion
													.equals(accionModificar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(31);
											}
											if (tipoAccion
													.equals(accionConsultar)) {
												muestraFormulario = true;
											}
											// Otro usuarios
										} else {
											muestraFormulario = true;

											// Calidad
											calidadPresidente = true;
											calidadSecretario = true;
											calidadConsejeros = false;
										}
									}
								} else {
									if (tipoAccion.equals(accionCapturar)) {

										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(31);
										} else {
											// otros usuarios
											muestraFormulario = true;

											// Calidad
											calidadPresidente = true;
											calidadSecretario = true;
											calidadConsejeros = false;
										}
									} else {
										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											if (tipoAccion
													.equals(accionModificar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(31);
											}
											if (tipoAccion
													.equals(accionConsultar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(32);
											}
										} else {
											// otros usuarios
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(4);

										}
									}
								}
							} else {
								// usuario de consulta
								if (tipoUsuario.equals(4)) {
									if (tipoAccion.equals(accionConsultar)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(32);
									} else {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(31);
									}
								} else {
									// otros usuarios
									// Mensaje de distribucion requerida
									muestraFormulario = false;
									hlpMsg.mensajesValidacion(26);

								}
							}

						} else {

							// usuario de consulta
							if (tipoUsuario.equals(4)) {
								if (tipoAccion.equals(accionConsultar)) {
									muestraFormulario = false;
									hlpMsg.mensajesValidacion(32);
								} else {
									muestraFormulario = false;
									hlpMsg.mensajesValidacion(31);
								}
							} else {
								// Mensaje de que no se han generado todas las
								// actas
								// parciales para gobernador a nivel municipio

								// otros usuarios
								muestraFormulario = false;
								hlpMsg.mensajesValidacion(28);
							}
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion OPLE GOBERNADOR");
						log.error(e);
						e.printStackTrace();
						return;
					}

				}

				// DIPUTADO MR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {

					try {

						if (hlpInfo.isTotalActasParcialesByMunicipioDistrito(
								bsdInfo, bsdInfoGralActas, usuario,
								tipoCandidatura.getIdTipoCandidatura())) {
							if (tipoAccion.equals(accionCapturar)) {
								if (distritoCaptura.isEmpty()
										&& !distritoConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(6);
								}
								if (distritoConsulta.isEmpty()
										&& distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(17);
								}
							}
							if (tipoAccion.equals(accionConsultar)
									|| tipoAccion.equals(accionModificar)) {
								if (distritoConsulta.isEmpty()
										&& !distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(7);
								}
								if (distritoConsulta.isEmpty()
										&& distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(17);
								}
							}
							// Mensaje sí no se han generado todas las actas
							// parciales para diputados MR a nivel municipio
						} else {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(28);
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion OPLE DIPUTADO MR");
						log.error(e);
						e.printStackTrace();
						return;
					}

				}

				// DIPUTADOS RP
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& !actaEstatal) {

					try {

						Map<String, String> mapMuestra = hlpInfo
								.getDistritosByActasGeneradas(bsdInfoGralActas,
										usuario,
										tipoCandidatura.getIdTipoCandidatura());

						if (mapMuestra != null && !mapMuestra.isEmpty()) {
							if (tipoAccion.equals(accionCapturar)) {
								if (distritoCaptura.isEmpty()
										&& !distritoConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(6);
								}
								if (distritoConsulta.isEmpty()
										&& distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(17);
								}
							}
							if (tipoAccion.equals(accionConsultar)
									|| tipoAccion.equals(accionModificar)) {
								if (distritoConsulta.isEmpty()
										&& !distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(7);
								}
								if (distritoConsulta.isEmpty()
										&& distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(17);
								}
							}
							// Mensaje sí no se han generado todas las actas
							// parciales para diputados MR a nivel municipio
						} else {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(28);
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion OPLE DIPUTADO RP");
						log.error(e);
						e.printStackTrace();
						return;
					}
				}

				// *************************************************
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& actaEstatal) {

					log.info("ESTATAL RP");

					try {

						// Verificar que esten capturadas todas
						// las actas finales de dip mr y rp
						if (hlpInfo.isDistribucionCompletaDiputadosMR(bsdInfo,
								bsdInfoGralActas, usuario,
								tipoCandidatura.getIdTipoCandidatura())) {
							if (bsdActas.isGeneradasDiputadosRPFinal()) {
								if (isGenerada()) {
									if (tipoAccion.equals(accionCapturar)) {

										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(31);
										} else {
											// otros usaurios
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(3);
										}
									} else {
										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											if (tipoAccion
													.equals(accionModificar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(31);
											}
											if (tipoAccion
													.equals(accionConsultar)) {
												muestraFormulario = true;
											}
											// Otro usuarios
										} else {
											muestraFormulario = true;

											// Calidad
											calidadPresidente = true;
											calidadSecretario = true;
											calidadConsejeros = false;
										}
									}
								} else {
									if (tipoAccion.equals(accionCapturar)) {

										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(31);
										} else {
											// otros usuarios
											muestraFormulario = true;

											// Calidad
											calidadPresidente = true;
											calidadSecretario = true;
											calidadConsejeros = false;
										}
									} else {
										// Usuario de consulta
										if (tipoUsuario.equals(4)) {
											if (tipoAccion
													.equals(accionModificar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(31);
											}
											if (tipoAccion
													.equals(accionConsultar)) {
												muestraFormulario = false;
												hlpMsg.mensajesValidacion(32);
											}
										} else {
											// otros usuarios
											muestraFormulario = false;
											hlpMsg.mensajesValidacion(4);

										}
									}
								}

							} else {
								muestraFormulario = false;
								hlpMsg.mensajesValidacion(64);
							}
						} else {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(56);
						}

					} catch (Exception e) {
						log.error("Error en validacion OPLE DIPUTADO RP ESTATAL");
						log.error(e);
					}
				}

			}

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error en validacionesGeneracionActas()");
			log.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Validacion para usuario OPLE
	 */
	public void validaOPLE() {
		/**
		 * ************ Usuario: OPLE
		 * *********************************************
		 */
		asignaMunicipioOple();
		// // Si los valores del usuario vienen nulos... Servidor pruebas
		// if(usuario.getIdProcesoElectoral() == null){
		// usuario.setIdProcesoElectoral(3);
		// }
		// if(usuario.getIdDetalleProceso() == null){
		// usuario.setIdDetalleProceso(20);
		// }
		// if(usuario.getIdEstadoSeleccionado() == null){
		// usuario.setIdEstadoSeleccionado(18);
		// }
		// if(usuario.getIdMunicipioSeleccionado() == null){
		// usuario.setIdMunicipioSeleccionado(0);
		// }

		/**
		 * ACTAS PARCIALES OPLE: SELECCIONA MUNICIPIO DE MENU
		 */
		try {
			if (usuario.getIdMunicipioSeleccionado().intValue() > 0) {

				// GOBERNADOR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_GOBERNADOR)) {
					if (isGenerada()) {
						if (tipoAccion.equals(accionCapturar)) {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(11);
						}
						if (tipoAccion.equals(accionModificar)) {
							muestraFormulario = false;
							// Calidad
							calidadPresidente = true;
							calidadSecretario = false;
							calidadConsejeros = true;

							hlpMsg.mensajesValidacion(12);
						}
						if (tipoAccion.equals(accionConsultar)) {
							muestraFormulario = true;
							validaImprimir = 1;
						}
					} else {
						muestraFormulario = false;
						hlpMsg.mensajesValidacion(13);
					}
				}

				// AYUNTAMIENTO
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
					if (isGenerada()) {
						if (tipoAccion.equals(accionCapturar)) {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(3);
						}
						if (tipoAccion.equals(accionModificar)) {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(19);
						}
						if (tipoAccion.equals(accionConsultar)) {
							muestraFormulario = true;
							validaImprimir = 1;
						}
					} else {
						muestraFormulario = false;
						hlpMsg.mensajesValidacion(18);
					}
				}

				// DIPUTADOR MR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
					if (tipoAccion.equals(accionConsultar)) {
						validaImprimir = 1;
						if (distritoConsulta.isEmpty()) {
							hlpMsg.mensajesValidacion(14);
						}
					}
					if (tipoAccion.equals(accionCapturar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(15);
					}
					if (tipoAccion.equals(accionModificar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(16);
					}
				}

				// DIPUTADOR RP
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& !actaEstatal) {
					if (tipoAccion.equals(accionConsultar)) {
						validaImprimir = 1;
						if (distritoConsulta.isEmpty()) {
							hlpMsg.mensajesValidacion(14);
						}
					}
					if (tipoAccion.equals(accionCapturar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(15);
					}
					if (tipoAccion.equals(accionModificar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(16);
					}
				}

				// REGIDURIA MR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_MR)) {
					if (tipoAccion.equals(accionConsultar)) {
						validaImprimir = 1;
						if (demarcacionConsulta.isEmpty()) {
							hlpMsg.mensajesValidacion(14);
						}
					}
					if (tipoAccion.equals(accionCapturar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(15);
					}
					if (tipoAccion.equals(accionModificar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(16);
					}
				}
				// REGIDURIA RP
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
					if (tipoAccion.equals(accionConsultar)) {
						validaImprimir = 1;
						if (demarcacionConsulta.isEmpty()) {
							hlpMsg.mensajesValidacion(14);
						}
					}
					if (tipoAccion.equals(accionCapturar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(15);
					}
					if (tipoAccion.equals(accionModificar)) {
						bloqueCombo = 1;
						hlpMsg.mensajesValidacion(16);
					}
				}
			}

			/**
			 * ACTAS FINALES PARA OPLE: GOBERNADOR, DIPUTADO MR, DIPUTADOR RP
			 */
			else {

				// Acta de GOBERNADOR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_GOBERNADOR)) {
					log.info("Entra en Gobernador OPLE ESTADO de muestraFormularioGobAyu");

					try {
						log.info(calidadPresidente);
						// Verificamos que todas las actas parciales fueron
						// generadas
						if (hlpInfo.isTotalActasParcialesByEstado(bsdInfo,
								bsdInfoGralActas, usuario,
								tipoCandidatura.getIdTipoCandidatura())) {
							// Verificamos que se encuentre la distribucion
							if (hlpInfo.getVerificarDistribucionFinal(bsdInfo,
									usuario,
									tipoCandidatura.getIdTipoCandidatura())) {
								if (isGenerada()) {
									if (tipoAccion.equals(accionCapturar)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(3);
									} else {
										if (tipoAccion.equals(accionModificar)
												|| tipoAccion
														.equals(accionConsultar)) {
											editable = 1;
										}
										muestraFormulario = true;

										// Calidad
										calidadPresidente = true;
										calidadSecretario = true;
										calidadConsejeros = false;
									}
								} else {
									if (tipoAccion.equals(accionCapturar)) {
										muestraFormulario = true;

										// Calidad
										calidadPresidente = true;
										calidadSecretario = true;
										calidadConsejeros = false;

									} else {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(4);
									}
								}
							} else {
								// Mensaje de distribucion requerida
								log.info("No esta completa la dist gober");
								muestraFormulario = false;
								hlpMsg.mensajesValidacion(26);
							}
							// Mensaje de que no se han generado todas las
							// actas
							// parciales para gobernador a nivel municipio
						} else {

							log.info("no estan todas las parciales gober");
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(28);
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion OPLE GOBERNADOR");
						log.error(e);
						e.printStackTrace();
						return;
					}

				}

				// DIPUTADO MR
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {

					try {

						// if (hlpInfo.isTotalActasParcialesByMunicipioDistrito(
						// bsdInfo, bsdInfoGralActas, usuario,
						// tipoCandidatura.getIdTipoCandidatura())) {
						if (tipoAccion.equals(accionCapturar)) {

							// Calidad
							calidadPresidente = true;
							calidadSecretario = true;
							calidadConsejeros = false;

							if (distritoCaptura.isEmpty()
									&& !distritoConsulta.isEmpty()) {
								hlpMsg.mensajesValidacion(6);
							}
							if (distritoConsulta.isEmpty()
									&& distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(17);
							}
						}
						if (tipoAccion.equals(accionConsultar)
								|| tipoAccion.equals(accionModificar)) {
							if (distritoConsulta.isEmpty()
									&& !distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(7);
							}
							if (distritoConsulta.isEmpty()
									&& distritoCaptura.isEmpty()) {
								hlpMsg.mensajesValidacion(17);
							}

							editable = 1;
						}
						// Mensaje sí no se han generado todas las actas
						// parciales para diputados MR a nivel municipio
						// } else {
						// muestraFormulario = false;
						// hlpMsg.mensajesValidacion(28);
						// }

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion OPLE DIPUTADO MR");
						log.error(e);
						e.printStackTrace();
						return;
					}

				}

				// DIPUTADOS RP
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& !actaEstatal) {

					try {

						Map<String, String> mapMuestra = hlpInfo
								.getDistritosByActasGeneradas(bsdInfoGralActas,
										usuario,
										tipoCandidatura.getIdTipoCandidatura());

						for (Map.Entry<String, String> key : mapMuestra
								.entrySet()) {
							log.info("Iteracion map RP : "
									+ mapMuestra.get(key));
						}

						if (mapMuestra != null && !mapMuestra.isEmpty()) {
							if (tipoAccion.equals(accionCapturar)) {
								if (distritoCaptura.isEmpty()
										&& !distritoConsulta.isEmpty()) {
									hlpMsg.mensajesValidacion(6);
								}
								if (distritoConsulta.isEmpty()
										&& distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(17);
								}
							}
							if (tipoAccion.equals(accionConsultar)
									|| tipoAccion.equals(accionModificar)) {
								if (distritoConsulta.isEmpty()
										&& !distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(7);
								}
								if (distritoConsulta.isEmpty()
										&& distritoCaptura.isEmpty()) {
									hlpMsg.mensajesValidacion(17);
								}
								editable = 1;
							}
							// Mensaje sí no se han generado todas las actas
							// parciales para diputados MR a nivel municipio
						} else {
							muestraFormulario = false;
							// Corte y queda
							hlpMsg.mensajesValidacion(28);
						}

					} catch (Exception e) {
						hlpMsg.mensajesValidacion(53);
						log.error("Error en validacion OPLE DIPUTADO RP");
						log.error(e);
						e.printStackTrace();
						return;
					}
				}

				log.info("actaEstatal : " + actaEstatal);

				/**
				 * ACTA DIPUTADPOS RP ESTATAL
				 */
				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& actaEstatal) {

					log.info("ESTATAL RP");

					try {

						// Verificar que esten capturadas todas
						// las actas finales de dip mr y rp
						if (hlpInfo.isDistribucionCompletaDiputadosMR(bsdInfo,
								bsdInfoGralActas, usuario,
								Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
							if (bsdActas.isGeneradasDiputadosRPFinal()) {
								if (isGenerada()) {
									if (tipoAccion.equals(accionCapturar)) {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(3);
									} else {
										if (tipoAccion.equals(accionModificar)
												|| tipoAccion
														.equals(accionConsultar)) {
											editable = 1;
										}
										muestraFormulario = true;

										// Calidad
										calidadPresidente = true;
										calidadSecretario = true;
										calidadConsejeros = false;
									}
								} else {
									if (tipoAccion.equals(accionCapturar)) {
										muestraFormulario = true;

										// Calidad
										calidadPresidente = true;
										calidadSecretario = true;
										calidadConsejeros = false;

									} else {
										muestraFormulario = false;
										hlpMsg.mensajesValidacion(4);
									}
								}

							} else {
								// Falta generar actas de Diputados RP
								muestraFormulario = false;
								hlpMsg.mensajesValidacion(64);
							}

						} else {
							// Falta realizar las distribuciones de Diputados MR
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(56);
						}

					} catch (Exception e) {
						log.error("Error en validacion OPLE DIPUTADO RP ESTATAL");
						log.error(e);
					}
				}

			}

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error en validaciones de OPLE");
			log.error(e);
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 
	 * Metodo para validaciones por cada tipo de candidatura, actas parciales,
	 * actas finales, para OPLE y CAPTURISTA MUNICIPAL envía mensajes
	 * informativos (HLPGeneracionActasMensajes) de acuerdo a cada escenario.
	 * 
	 */
	public void validacionesGeneracionActas() {

		if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)) {
			validaOPLE();
		} else {
			validaGeneric();
		}
	}

	/**
	 * Metodo que valida si el acta ya fue generada.
	 */
	public boolean isGenerada() {
		log.info("Entra en isGenerada()");
		init();
		boolean actaGenerada = false;

		asignaMunicipioOple();
		//
		try {
			DTOActaTipoCandidaturaPK pkActa = (DTOActaTipoCandidaturaPK) generaPkActa(usuario);
			DTOBase acta = null;
			acta = bsdActas.getActa(pkActa);

			if (acta != null) {
				actaGenerada = true;

				// Obtenemos el valor de editable para controlar la modificación
				// del acta
				editable = ((DTOActaTipoCandidatura) acta).getEditable();
				log.info("EDITABLE VALOR: " + editable);
			}
		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error(e);
			e.printStackTrace();
			return actaGenerada;
		}
		log.info("Que envia: " + actaGenerada);
		return actaGenerada;
	}

	/**
	 * 
	 * Método llamado desde la vista al seleccionar un distrito o demarcacion
	 * desde los combos.
	 * 
	 * @param idSeleccion
	 * @throws Exception
	 */
	public void obtenActa(Integer idSeleccion) throws Exception {

		try {
			asignaMunicipioOple();

			log.info("obtenActa");
			muestraFormularioDipReg();
			validaBotonesActas();

			DTOActaTipoCandidaturaPK pkActa = (DTOActaTipoCandidaturaPK) generaPkActa(usuario);
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_REGIDURIA_MR)
					|| tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				pkActa.setIdRegiduria(idSeleccion);
			} else {
				pkActa.setIdDistrito(idSeleccion);
			}
			DTOBase acta = null;
			acta = bsdActas.getActa(pkActa);

			// Obtenemos valores del usuario, cuando entra en la vista
			// Captura
			Integer idDetalle = usuario.getIdDetalleProceso();
			Integer idEstado = usuario.getIdEstadoSeleccionado();
			Integer idMunicipio = null;

			if (usuario.getIdMunicipioSeleccionado() != null
					&& usuario.getIdMunicipioSeleccionado() > 0) {
				// Si el municipio es diferente de null, seteamos lo que
				// trae el
				// usuario
				idMunicipio = usuario.getIdMunicipioSeleccionado();
			} else {
				// Valor de default para idMunicipio - Archivo, REGLAS
				// idMunicipio = -6;
				idMunicipio = null;
			}

			if (acta != null) {

				log.info("Existe Acta obtenActa()");
				// Objeto necesario para exportar a pdf.
				dtoActa = (DTOActaTipoCandidatura) acta;

				// Setear datos de acta en FormGeneracionActas
				datos.setCampos(acta);

				// Obtener repres y consejeros de esta acta
				List<DTOConsejero> consejeros = bsdActas.getConsejeros(pkActa);
				List<DTORepresentante> repres = bsdActas
						.getRepresentantes(pkActa);
				
				
				List<DTOCConsejo> tiposConsejeros = new ArrayList<DTOCConsejo>();
				try {

					if (usuario.getIdMunicipioSeleccionado() > 0) {
						List<DTOCConsejo> tiposConsejeross = bsdActas.getCatalogoConsejero();
						for(int i = 0; i < tiposConsejeross.size()-2; i++){
							tiposConsejeros.add(tiposConsejeross.get(i));
						}
					} else {
						tiposConsejeros = bsdActas.getCatalogoConsejero();
						for (DTOCConsejo dtoConsejeros : tiposConsejeros) {
							log.info("Lista de consejeros: "
									+ dtoConsejeros.getTituloConsejero());
							;
						}
					}

				if (tipoAccion.equals(accionModificar)) {
					List<DTOConsejero> consejerosC = llenaEspaciosConsejerosNoCapturados(
							consejeros, tiposConsejeros);
					datos.setConsejeros(obtenListaConsejeros(consejerosC));

				}
				if (tipoAccion.equals(accionConsultar)) {
					datos.setConsejeros(obtenListaConsejeros(consejeros));
				}
				
				}catch(Exception e){
					log.error("Error en obtenActa al llenar espacios de consejeros para vista modificar");
					log.error(e);
					e.printStackTrace();
				}
				
				// Obetenmos las asociaciones coaliciones
				List<DTOCandidatoWS> asociaciones = null;
				List<DTOCandidatoWS> asociacioness = null;
				if (tipoCandidatura.getIdTipoCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR
						|| tipoCandidatura.getIdTipoCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_RP) {
					asociaciones = bsdInfo.consumeAsociacionesCoaliciones(
							idDetalle, idEstado, distritoSeleccionado,
							tipoCandidatura.getIdTipoCandidatura(),
							idMunicipio, null);

					// asociaciones =
					// hlpInfo.cargaCandidatosUnicosByCandidatura(asociacioness);
				}
				if (tipoCandidatura.getIdTipoCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_MR
						|| tipoCandidatura.getIdTipoCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_RP) {
					asociaciones = bsdInfo.consumeAsociacionesCoaliciones(
							idDetalle, idEstado, null,
							tipoCandidatura.getIdTipoCandidatura(),
							idMunicipio, demarcacionSeleccionada);

					// asociaciones =
					// hlpInfo.cargaCandidatosUnicosByCandidatura(asociacioness);
				}

				List<DTORepresentante> representantes = llenaEspaciosRepresentantesNoCapturados(
						bsdActas.getRepresentantes(dtoActa.getPk()),
						asociaciones);

				// Setear listas para mostrarlas en la vista
				datos.setRepresentantes(obtenListaRepresentantes(representantes));

				editable = ((DTOActaTipoCandidatura) acta).getEditable();

				// Validar si el acta final ya fue generada, sólo para consultar
				// actas parciales
				if (usuario.getIdMunicipioSeleccionado() > 0) {
					if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)) {
						Map<String, String> mapMuestra = hlpInfo
								.getDistritosByDist(bsdInfo, usuario,
										tipoCandidatura.getIdTipoCandidatura());

						for (Map.Entry<String, String> key : mapMuestra
								.entrySet()) {
							if (distritoSeleccionado.equals(Integer
									.parseInt(key.getKey()))) {
								disableElimina = true;
								hlpMsg.mensajesValidacion(57);
							}
						}
						if (actaFinalGenerada(
								(DTOActaTipoCandidaturaPK) pkActa,
								distritoSeleccionado)) {
							log.info("ENTRA EN VALIDACION DE OBTEN ACTA PARA ACTAFINALGENERADA");
							disableElimina = true;
							botonEditar = false;
							hlpMsg.mensajesValidacion(34);
						}

					}
				} else {
					if (actaFinalGenerada((DTOActaTipoCandidaturaPK) pkActa,
							distritoSeleccionado)) {
						log.info("ENTRA EN VALIDACION DE OBTEN ACTA PARA ACTAFINALGENERADA");
						disableElimina = true;
						botonModificar = false;
						hlpMsg.mensajesValidacion(34);
					}
				}
				muestraFormulario = true;
				if (usuario.getRolUsuario()
						.equals(Constantes.CAPTURA_MUNICIPAL)) {
					if (editable.intValue() == 0) {
						if (tipoAccion.equals(accionModificar)) {
							hlpMsg.mensajesValidacion(25);
						}
					}
				} else {
					editable = 1;
				}
				}
		} catch (Exception e) {
			log.error("Hubo error al obtener datos de acta");
			log.error(e);
			hlpMsg.mensajesValidacion(61);
			return;
		}

	}

	/**
	 * Genera un objeto que represtna un actra usando los datos del formulario.
	 * 
	 * Puede revolver dos tipos de objetos: 1. DTOActaTipoCandidatura 2.
	 * DTOActaTipoCandParcial
	 * 
	 * @param datos
	 *            Los datos guardados del formulario y la session actual.
	 * @return acta. El objeto que representa un acta.
	 */
	public DTOBase generaDtoActa(FormGeneracionActas datos) {

		asignaMunicipioOple();

		Object pkActa = generaPkActa(usuario);
		if (pkActa instanceof DTOActaTipoCandidaturaPK) {
			DTOActaTipoCandidatura acta = new DTOActaTipoCandidatura(
					(DTOActaTipoCandidaturaPK) pkActa);
			acta.setFechaHoraActa(datos.getFechaHora());
			acta.setUbicacionComputo(datos.getUbicacionComputo());
			acta.setEditable(0);
			return acta;

		}

		return null;

	}

	/**
	 * Obtiene la pk del acta correpondiente.
	 * 
	 * Puede revolver dos tipos de objetos: 1. DTOActaTipoCandidaturaPK 2.
	 * DTOActaTipoCandParcialPK
	 * 
	 * Depende del rol del usuario y del tipo de candidatura.
	 * 
	 * @param usuario
	 *            : Los datos del usuario en la sesion.
	 * @return Object : Alguna de las PK de actas (Parcial o final)
	 */
	public Object generaPkActa(DTOUsuarioLogin usuario) {

		asignaMunicipioOple();

		log.info("GENERA PK ACTA");

		Integer idProceso = usuario.getIdProcesoElectoral();
		Integer idDetalle = usuario.getIdDetalleProceso();
		Integer idEstado = usuario.getIdEstadoSeleccionado();

		Integer idDistrito = null;
		if (distritoSeleccionado != null) {
			// Toma el valor de la demarcacion seleccionada por el usuario en
			// tipo candidatura DIPUTADO_MR o RP
			idDistrito = distritoSeleccionado;
		} else {
			// Valor agregado a Distrito por default en archivo Reglas
			idDistrito = Constantes.SIN_DISTRITO;
		}

		// idMunicipio del usuario
		Integer idMunicipio = null;
		if (usuario.getIdMunicipioSeleccionado() != null
				&& usuario.getIdMunicipioSeleccionado().intValue() > 0) {
			// Toma el valor del rol del usuario
			idMunicipio = usuario.getIdMunicipioSeleccionado();
		} else {
			// Toma el valor por default de municipio en archivo Reglas
			idMunicipio = Constantes.SIN_MUNICIPIO;
		}

		Integer idRegiduria = null;
		if (demarcacionSeleccionada != null) {
			// Toma el valor de la demarcacion seleccionada por el usuario en
			// tipo candidatura REGIDURIA_MR o RP
			idRegiduria = demarcacionSeleccionada;
		} else {
			// Valor agregado a Regiduria por default en archivo Reglas
			idRegiduria = Constantes.SIN_REGIDURIA;
		}

		// usuarioLogin no trae idComunidad
		Integer idComunidad = Constantes.SIN_COMUNIDAD;

		log.info("GENERA PK ACTA");
		log.info("IdProceso: " + idProceso);

		DTOActaTipoCandidaturaPK actaPk = new DTOActaTipoCandidaturaPK();
		actaPk.setIdProcesoElectoral(idProceso);
		actaPk.setIdDetalleProceso(idDetalle);
		actaPk.setIdEstado(idEstado);
		actaPk.setIdDistrito(idDistrito);
		actaPk.setIdMunicipio(idMunicipio);
		actaPk.setIdComunidad(idComunidad);
		actaPk.setIdRegiduria(idRegiduria);
		actaPk.setIdTipoCandidatura(tipoCandidatura.getIdTipoCandidatura());

		// Usuario en nivel municipal: CM
		if (usuario.getIdMunicipioSeleccionado() > 0) {

			// EN CM estas ACTAS son PARCIALES
			// Entonces generamos PK para registro de tabla
			// ACTA_TIPO_CAND_PARCIAL
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_MR)
					|| (tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_DIPUTADO_RP) && !actaEstatal)
					|| tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				actaPk.setTipoActa(Constantes.TIPO_ACTA_PARCIAL);
			} else {
				// Acta final para ayuntamiento, regidurias MR y RP
				actaPk.setTipoActa(Constantes.TIPO_ACTA_FINAL);
			}
		} else {
			// Validamos para acta DiputadosRP Estatal
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_RP)
					&& actaEstatal) {
				actaPk.setTipoActa(Constantes.TIPO_ACTA_ESTATAL);
			} else {
				// Tipo de acta final para nivel estado : OPLE
				actaPk.setTipoActa(Constantes.TIPO_ACTA_FINAL);
			}
		}

		log.info("getIdProcesoElectoral  : " + actaPk.getIdProcesoElectoral());
		log.info("getIdDetalleProceso    : " + actaPk.getIdDetalleProceso());
		log.info("getIdEstado            : " + actaPk.getIdEstado());
		log.info("getIdMunicipio         : " + actaPk.getIdMunicipio());
		log.info("getIdDistrito          : " + actaPk.getIdDistrito());
		log.info("getIdRegiduria         : " + actaPk.getIdRegiduria());
		log.info("getIdComunidad         : " + actaPk.getIdComunidad());
		log.info("getIdTipoCandidatura   : " + actaPk.getIdTipoCandidatura());

		return actaPk;
	}

	/**
	 * Toma una lista de DTOCandidatosWS y construye una lista de
	 * HLPRepresentante
	 * 
	 * @param asociaciones
	 * @return
	 */
	public List<HLPRepresentante> generaListaRepresentantes(
			List<DTOCandidatoWS> asociaciones) {

		asignaMunicipioOple();
		List<HLPRepresentante> lista = new ArrayList<HLPRepresentante>();
		HLPRepresentante crt;
		log.info("generaListaRepre");
		log.info(asociaciones.size());
		for (int i = 0; i < asociaciones.size(); i++) {
			crt = new HLPRepresentante();
			crt.setIdAsociacion(asociaciones.get(i).getIdAsociacion());
			crt.setTipoAsociacion(asociaciones.get(i).getTipoAsociacion());

			if (asociaciones.get(i).getTipoAsociacion().intValue() == 1
					|| asociaciones.get(i).getTipoAsociacion().intValue() == 4) {

				// Partidos politicos
				if (asociaciones.get(i).getTipoAsociacion().intValue() == 1) {
					if (asociaciones.get(i).getNombreAsociacion() == null) {
						crt.setNombreAsociacion("NOMBRE ASOCIACION NULA");
					} else {
						crt.setNombreAsociacion(asociaciones.get(i)
								.getNombreAsociacion());
					}
				}

				// Candidatos independientes
				if (asociaciones.get(i).getTipoAsociacion().intValue() == 4) {
					if (asociaciones.get(i).getNombreCandidato() != null) {
						crt.setNombreAsociacion(asociaciones.get(i)
								.getNombreCandidato());
					} else {
						crt.setNombreAsociacion("NOMBRE NULO");
					}
				}

				if (asociaciones.get(i).getEmblema() == null) {
					crt.setEmblemaAsociacion("EMBLEMA NULO");
				} else {
					crt.setEmblemaAsociacion(asociaciones.get(i).getEmblema());
					// crt.setEmblemaAsociacion("/logo_pri.jpg");
				}
				if (asociaciones.get(i).getSiglas() != null) {
					crt.setSiglasAsociacion(asociaciones.get(i).getSiglas());
				} else {
					crt.setSiglasAsociacion("SIGLAS NULAS");
				}

				crt.setOrden(asociaciones.get(i).getOrden());

				lista.add(crt);

			}

		}
		return lista;
	}

	/**
	 * Genera una lista de HLPConsejero necesaria para guardar los datos de la
	 * vista
	 * 
	 * @param catalogo
	 *            - La lista de DTOCConsejo, El catalogo de tipos de consejeros.
	 * @return consejeros- Una lista como se necesita para capturar datos
	 */
	public List<HLPConsejero> generaListaConsejeros(List<DTOCConsejo> catalogo) {
		List<HLPConsejero> consejeros = new ArrayList<HLPConsejero>();
		HLPConsejero crt;
		for (int i = 0; i < catalogo.size(); i++) {
			crt = new HLPConsejero();
			crt.setIdTipoConsejero(catalogo.get(i).getIdPuestoFuncionario());
			crt.setTituloConsejero(catalogo.get(i).getTituloConsejero());
			consejeros.add(crt);
		}
		return consejeros;
	}

	/**
	 * Mapea una lista de objetos tipo DTOCOnsejero a una lista de objetos tipo
	 * HLPConsejero para visualizarlos en la vista.
	 * 
	 * @param consejeros
	 *            - La lista de consejeros obtenida desde la BD.
	 * @return consejerosH - La lista de consejeros modificada para la vista.
	 */
	public List<HLPConsejero> obtenListaConsejeros(List<DTOConsejero> consejeros) {
		List<HLPConsejero> consejerosH = new ArrayList<HLPConsejero>();
		HLPConsejero crt;
		for (DTOConsejero cons : consejeros) {
			crt = new HLPConsejero();
			crt.setIdTipoConsejero(cons.getTipoConsejero()
					.getIdPuestoFuncionario());
			crt.setTituloConsejero(cons.getTipoConsejero().getTituloConsejero());
			crt.setCalidad(cons.getCalidadConsejero() + "");
			crt.setNombre(cons.getNombre());
			crt.setaPaterno(cons.getApellidoPaterno());
			crt.setaMaterno(cons.getApellidoMaterno());
			consejerosH.add(crt);
		}
		return consejerosH;
	}

	/**
	 * Mapea una lista de objetos tipo DTOCOnsejero a una lista de objetos tipo
	 * HLPConsejero para visualizarlos en la vista.
	 * 
	 * @param consejeros
	 *            - La lista de consejeros obtenida desde la BD.
	 * @return consejerosH - La lista de consejeros modificada para la vista.
	 */
	public List<HLPRepresentante> obtenListaRepresentantes(
			List<DTORepresentante> repres) {
		List<HLPRepresentante> represH = new ArrayList<HLPRepresentante>();
		HLPRepresentante crt;
		for (DTORepresentante cons : repres) {
			crt = new HLPRepresentante();
			crt.setIdAsociacion(cons.getIdAsociacion());
			crt.setTipoAsociacion(cons.getTipoAsociacion());
			crt.setNombreAsociacion(cons.getNombreAsociacion());
			crt.setEmblemaAsociacion(cons.getEmblemaAsociacion());
			crt.setSiglasAsociacion(cons.getSiglasAsociacion());
			crt.setNombre(cons.getNombre());
			crt.setaPaterno(cons.getApellidoPaterno());
			crt.setaMaterno(cons.getApellidoMaterno());
			crt.setCalidad(cons.getCalidadRepresentante() + "");
			represH.add(crt);
		}
		return represH;
	}

	/**
	 * Decide si un componente es mostrado dependiento del tipoAccion actual
	 * 
	 * @param accion
	 * @return True - Si el parametro coincide con el tipo de accion actual.
	 *         False en otro caso.
	 */
	public boolean isRendered(String accion) {
		return accion.equals(tipoAccion);
	}

	/**
	 * Metodo para validar campos requeridos en apellido paterno y materno para
	 * consejeros y representantes de partidos politicos.
	 */
	public void validaCampos() {
		log.info("Entra en validaCampos");
		this.valido = true;
		int contador = 0;
		int contadorConsejeros = 0;
		int contadorRepresentantes = 0;
		boolean entraValida = false;
		boolean entraValidaRep = false;

		try {
			// TODO se comenta este apartado ya que las validaciones de los
			// campos de ubicacion y fecha hora estan del lado de la vista
			// Revisar como quedaran para poder mandar el scroll a la parte
			// donde se encuentran las validaciones
			/*
			 * if(datos.getUbicacionComputo() != null || datos.getFechaHora() !=
			 * null){ log.info("valor de ubicacion "+datos.getUbicacionComputo()
			 * + " valor de fecha "+datos.getFechaHora()); }
			 */

			// Informaciòn computo
			if (datos.getFechaHora() == null || datos.getFechaHora().equals("")) {
				log.info("entro a cuando la fecha hora es nula");
				this.valido = false;
				FacesMessage fechaHoraRequerido = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Utilidades
								.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
						"");
				FacesContext.getCurrentInstance().addMessage("fechaHora",
						fechaHoraRequerido);
				contador = 1;
				RequestContext.getCurrentInstance().scrollTo("formActa");
			} else {
				contador = 0;
			}

			if (datos.getUbicacionComputo() == null
					|| datos.getUbicacionComputo().isEmpty()) {
				log.info("entro a cuando la ubicacion de computo es nula");
				this.valido = false;
				FacesMessage lugarRequerido = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Utilidades
								.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
						"");
				FacesContext.getCurrentInstance().addMessage("lugarActa",
						lugarRequerido);
				contador = 1;
				RequestContext.getCurrentInstance().scrollTo("formActa");
			} else {
				contador = 0;
			}

			// Consejeros
			if (datos.getConsejeros() != null
					|| !datos.getConsejeros().isEmpty()) {

				for (HLPConsejero hlpConsejero : datos.getConsejeros()) {

					if ((hlpConsejero.getNombre() != null && !hlpConsejero
							.getNombre().equals(""))
							|| (hlpConsejero.getaPaterno() != null && !hlpConsejero
									.getaPaterno().equals(""))
							|| (hlpConsejero.getaMaterno() != null && !hlpConsejero
									.getaMaterno().equals(""))
							|| hlpConsejero.getCalidad() != null
							&& !hlpConsejero.getCalidad().equals("")) {

						log.info("O nombre o apellidos son nulos");

						if (hlpConsejero.getNombre() == null
								|| hlpConsejero.getNombre().equals("")) {
							log.info("PASA POR AQUI: "
									+ hlpConsejero.getTituloConsejero());
							log.info("El nombre es nulo o vacio");
							this.valido = false;
							entraValida = true;
							String idMensajeNombreCon = "informacionConsejerosNombreCap"
									+ hlpConsejero.getIdTipoConsejero();
							FacesMessage nombreRequeridoConsejero = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Utilidades
											.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
									"");
							FacesContext.getCurrentInstance().addMessage(
									idMensajeNombreCon,
									nombreRequeridoConsejero);
							// RequestContext.getCurrentInstance().execute("document.getElementById('"+idMensajeNombreCon+"').focus();");
						}

						if ((hlpConsejero.getaPaterno() == null || hlpConsejero
								.getaPaterno().equals(""))
								&& (hlpConsejero.getaMaterno() == null || hlpConsejero
										.getaMaterno().equals(""))) {

							log.info("Algun apellido es nulo o vacio");
							this.valido = false;
							entraValida = true;
							String idMensajeApellidoPaternoCon = "informacionConsejerosAPaternoCap"
									+ hlpConsejero.getIdTipoConsejero();
							String idMensajeApellidoMaternoCon = "informacionConsejerosAMaternoCap"
									+ hlpConsejero.getIdTipoConsejero();
							FacesMessage apellidoRequeridoConsejero = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Utilidades
											.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
									"");
							FacesContext.getCurrentInstance().addMessage(
									idMensajeApellidoPaternoCon,
									apellidoRequeridoConsejero);
							FacesContext.getCurrentInstance().addMessage(
									idMensajeApellidoMaternoCon,
									apellidoRequeridoConsejero);
						}

						if (hlpConsejero.getCalidad() == null
								|| hlpConsejero.getCalidad().equals("")) {
							this.valido = false;
							entraValida = true;
							String idMensajeCalidad = "informacionConsejerosRolCap"
									+ hlpConsejero.getIdTipoConsejero();
							FacesMessage mensajeCalidad = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Utilidades
											.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
									"");
							FacesContext.getCurrentInstance().addMessage(
									idMensajeCalidad, mensajeCalidad);
						}

						if (entraValida) {
							contadorConsejeros = 1;
							if (contador == 0 && contadorConsejeros != 0) {
								RequestContext.getCurrentInstance().scrollTo(
										"formActa:infConsejerosCap");
							}
						}

					}
				}
			} else {
				contadorConsejeros = 0;
			}

			// Representantes
			if (datos.getRepresentantes() != null
					|| !datos.getRepresentantes().isEmpty()) {
				for (HLPRepresentante hlpRepresentante : datos
						.getRepresentantes()) {

					if (hlpRepresentante.getNombre() != null
							&& !hlpRepresentante.getNombre().equals("")
							|| (hlpRepresentante.getaPaterno() != null && !hlpRepresentante
									.getaPaterno().equals(""))
							|| (hlpRepresentante.getaMaterno() != null && !hlpRepresentante
									.getaMaterno().equals(""))
							|| hlpRepresentante.getCalidad() != null
							&& !hlpRepresentante.getCalidad().equals("")) {

						if (hlpRepresentante.getNombre() == null
								|| hlpRepresentante.getNombre().equals("")) {
							log.info("El nombre es nulo o vacio");
							this.valido = false;
							contadorRepresentantes = 1;
							entraValidaRep = true;
							String idMensajeNombreRep = "informacionRepresentanteNombreCap"
									+ hlpRepresentante.getIdAsociacion();
							FacesMessage nombreRequeridoRepresentante = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Utilidades
											.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
									"");
							FacesContext.getCurrentInstance().addMessage(
									idMensajeNombreRep,
									nombreRequeridoRepresentante);
						}

						if ((hlpRepresentante.getaPaterno() == null || hlpRepresentante
								.getaPaterno().equals(""))
								&& (hlpRepresentante.getaMaterno() == null || hlpRepresentante
										.getaMaterno().equals(""))) {
							this.valido = false;
							contadorRepresentantes = 1;
							entraValidaRep = true;
							String idMensajeApellidoPaternoRep = "informacionRepresentanteAPaternoCap"
									+ hlpRepresentante.getIdAsociacion();
							String idMensajeApellidoMaternoRep = "informacionRepresentanteAMaternoCap"
									+ hlpRepresentante.getIdAsociacion();
							FacesMessage apellidoRequeridoRepresentante = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Utilidades
											.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
									"");
							FacesContext.getCurrentInstance().addMessage(
									idMensajeApellidoPaternoRep,
									apellidoRequeridoRepresentante);
							FacesContext.getCurrentInstance().addMessage(
									idMensajeApellidoMaternoRep,
									apellidoRequeridoRepresentante);

						}

						if (hlpRepresentante.getCalidad() == null
								|| hlpRepresentante.getCalidad().equals("")) {
							this.valido = false;
							contadorRepresentantes = 1;
							entraValidaRep = true;
							String idMensajeCalidad = "informacionRepresentanteRolCap"
									+ hlpRepresentante.getIdAsociacion();
							FacesMessage mensajeCalidad = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Utilidades
											.mensajeProperties("validacion_mensajes_generales_dato_requerido"),
									"");
							FacesContext.getCurrentInstance().addMessage(
									idMensajeCalidad, mensajeCalidad);

						}

						if (entraValidaRep) {
							contadorRepresentantes = 1;
							if (contador == 0 && contadorConsejeros == 0
									&& contadorRepresentantes != 0) {
								RequestContext.getCurrentInstance().scrollTo(
										"formActa:panelRepCapMod");
							}
						}

					}
				}

			} else {
				contadorRepresentantes = 0;
			}

		} catch (Exception e) {
			log.error("Error validando datos");
			e.printStackTrace();
		}
	}

	// }

	/**
	 * Tiempo de espera para bloquear pantalla durante descarga de acta
	 */
	public void bloqueaPantalla() {
		try {
			Thread.currentThread().sleep(4000);
		} catch (Exception e) {
			log.error("Error bloqueando pantalla");
			log.error(e);
		}
	}

	/**
	 * 
	 * Metodo llamado desde la vista de consulta para imprimir un acta.
	 * 
	 */
	public StreamedContent generaPdfActa() {
		try {

			// Validación por si el usuario CM no ha do la edición del
			// acta
			// Aseguramos que la información sea la correcta
			log.info("Valor de editable: " + editable.intValue());

			// Usuarios ADMIN siempre imprimen
			if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)
					|| usuario.getRolUsuario().equals(
							Constantes.ADMIN_PARAM_CAPTURA_OC)
					|| usuario.getRolUsuario().equals(
							Constantes.ADMIN_CAPTURA_OC)
					|| usuario.getRolUsuario().equals(Constantes.CONSULTA_JL)
					|| usuario.getRolUsuario().equals(
							Constantes.CONSULTA_PARTIDO_JL)
					|| usuario.getRolUsuario().equals(
							Constantes.CONSULTA_CONSEJERO_OC)
					|| usuario.getRolUsuario().equals(Constantes.CONSULTA_0C)
					|| usuario.getRolUsuario().equals(
							Constantes.ADMIN_LECTURA_CAU_OC)) {

				editable = 0;

				if (usuario.getIdMunicipioSeleccionado() == null
						|| !(usuario.getIdMunicipioSeleccionado() > 0)) {
					usuario.setIdMunicipioSeleccionado(Constantes.SIN_MUNICIPIO);
				}

			}
			if (editable.intValue() != 0
					&& usuario.getRolUsuario().equals(
							Constantes.CAPTURA_MUNICIPAL)) {
				// Mensaje para usuario OPLE
				// if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE))
				// {
				// hlpMsg.mensajesValidacion(24);
				// Mensaje para usuario CM
				// } else {
				hlpMsg.mensajesValidacion(21);
				// }
				// Cuando la edición fue guardada
			}
			if (editable == 0) {

				HLPDatosActa hlpDatosActa = null;
				try {

					// Construimos PK genérica de distribución
					// para obtener listas de distribución para el acta.

					DTODistribucionCandidatosPK pkDistribucion = null;

					pkDistribucion = new DTODistribucionCandidatosPK();
					((DTODistribucionCandidatosPK) pkDistribucion)
							.setIdProcesoElectoral(usuario
									.getIdProcesoElectoral());
					((DTODistribucionCandidatosPK) pkDistribucion)
							.setIdDetalleProceso(usuario.getIdDetalleProceso());
					((DTODistribucionCandidatosPK) pkDistribucion)
							.setIdEstado(usuario.getIdEstadoSeleccionado());

					if (distritoSeleccionado != null
							&& distritoSeleccionado > 0) {
						((DTODistribucionCandidatosPK) pkDistribucion)
								.setIdDistrito(distritoSeleccionado);
					} else {
						((DTODistribucionCandidatosPK) pkDistribucion)
								.setIdDistrito(-5);
					}
					if (usuario.getIdMunicipioSeleccionado() > 0) {
						((DTODistribucionCandidatosPK) pkDistribucion)
								.setIdMunicipio(usuario
										.getIdMunicipioSeleccionado());
					} else {
						((DTODistribucionCandidatosPK) pkDistribucion)
								.setIdMunicipio(-6);
					}
					if (demarcacionSeleccionada != null
							&& demarcacionSeleccionada > 0) {
						((DTODistribucionCandidatosPK) pkDistribucion)
								.setIdRegiduria(demarcacionSeleccionada);
					} else {
						((DTODistribucionCandidatosPK) pkDistribucion)
								.setIdRegiduria(-7);
					}
					((DTODistribucionCandidatosPK) pkDistribucion)
							.setIdTipoCandidatura(tipoCandidatura
									.getIdTipoCandidatura());
					log.info("PK GENERICA DE DISTRIBUCION CREADA");

					// Obtenemos lista de distribucion de candidatos, pasando la
					// pk
					// construida de DTODistribucionCandidatos
					List<DTODistribucionCandidatos> listDistFinalCandidatos = bsdDist
							.consultarDistribucionFinal(((DTODistribucionCandidatosPK) pkDistribucion));

					log.info("Lista listDistFinalCandidatos creada");

					// Contruir pk de DTODistribucionTotParcial
					List<DTODistribucionTotParcial> listTotalVotosParcial = bsdDist
							.consultarDistribucionTotalParcial(((DTODistribucionCandidatosPK) pkDistribucion));

					// Ordenamos listTotalVotosParcial : 1.- Partidos, 3.-
					// Coalicion (Primero la más antaña) 4.- Candidatos
					// Independientes
					listTotalVotosParcial = hlpInfo
							.ordenaCoaliciones(listTotalVotosParcial);

					log.info("Lista listTotalVotosParcial creada");
					// Obtenemos lista de total de votos
					List<DTODistribucionTotales> listTotalVotos = bsdDist
							.consultarDistribucionTotales((DTODistribucionCandidatosPK) pkDistribucion);

					// Ordenamos listTotalVotos : 1.- Partidos, 3.- Coalicion
					// (Primero la más antaña) 4.- Candidatos Independientes
					listTotalVotos = hlpInfo
							.ordenaCoalicionesTotalFinal(listTotalVotos);

					log.info("Lista listTotalVotos creada");

					// Lista de distribución de Partidos y Candidatos
					// Independientes.
					List<DTODistribucionPartidosCI> listDistribucionPPCI = bsdDist
							.consultarDistribucionPartidosCI((DTODistribucionCandidatosPK) pkDistribucion);

					log.info("Lista listDistribucionPPCI creada");

					// Lista de distribución parcial de candidatos.
					List<DTODistribucionCandParcial> listDistParcialCandidatos = bsdDist
							.consultarDistribucionParcial((DTODistribucionCandidatosPK) pkDistribucion);

					log.info("Lista listDistParcialCandidatos creada");

					log.info("TIPO DE ACTA : " + dtoActa.getPk().getTipoActa());

					Integer actaFinal = dtoActa.getPk().getTipoActa();

					// Acta final
					if (actaFinal.intValue() == 1) {

						// Agregamos para los votos de CNR
						pkDistribucion.setIdAsociacion(-1);

						// listDistFinalCandidatos

						// listDistFinalCandidatos
						listDistFinalCandidatos
								.add(bsdDist
										.getVotosNulosCNRDistribucionCandidatos((DTODistribucionCandidatosPK) pkDistribucion));

						// listTotalVotos
						listTotalVotos
								.add(bsdDist
										.getVotosNulosCNRDistribucionTotal((DTODistribucionCandidatosPK) pkDistribucion));

						// listDistribucionPPCI
						listDistribucionPPCI
								.add(bsdDist
										.getVotosNulosCNRDistribucionPartidosCI(((DTODistribucionCandidatosPK) pkDistribucion)));

						// Agregamos para votos nulos
						pkDistribucion.setIdAsociacion(-2);

						// listDistFinalCandidatos
						listDistFinalCandidatos
								.add(bsdDist
										.getVotosNulosCNRDistribucionCandidatos((DTODistribucionCandidatosPK) pkDistribucion));

						// listTotalVotos
						listTotalVotos
								.add(bsdDist
										.getVotosNulosCNRDistribucionTotal((DTODistribucionCandidatosPK) pkDistribucion));

						// listDistribucionPPCI
						listDistribucionPPCI
								.add(bsdDist
										.getVotosNulosCNRDistribucionPartidosCI(((DTODistribucionCandidatosPK) pkDistribucion)));

					} else {
						// Acta Parcial

						// Agregamos para los votos de CNR
						pkDistribucion.setIdAsociacion(-1);

						// listTotalVotosParcial
						listTotalVotosParcial
								.add(bsdDist
										.getVotosNulosCNRDistribucionTotalParcial(pkDistribucion));

						// listDistParcialCandidatos
						listDistParcialCandidatos
								.add(bsdDist
										.getVotosNulosCNRDistribucionCandParcial(pkDistribucion));

						pkDistribucion.setIdAsociacion(-2);

						// listTotalVotosParcial
						listTotalVotosParcial
								.add(bsdDist
										.getVotosNulosCNRDistribucionTotalParcial(pkDistribucion));

						// listDistParcialCandidatos
						listDistParcialCandidatos
								.add(bsdDist
										.getVotosNulosCNRDistribucionCandParcial(pkDistribucion));

					}

					String nombreEstado = bsdInfo.obtenDetalleEstado(
							usuario.getIdEstadoSeleccionado())
							.getNombreEstado();
					String nombreMunicipio = null;
					if (usuario.getIdMunicipioSeleccionado() > 0) {
						nombreMunicipio = bsdInfo.obtenDetalleMunicipio(
								usuario.getIdEstadoSeleccionado(),
								usuario.getIdMunicipioSeleccionado(),
								EnumAmbitoDetalleProceso.L)
								.getNombreMunicipio();

					}

					// Objeto que contiene todos los datos para exportar el acta
					// en PDF.
					hlpDatosActa = new HLPDatosActa(datos,
							usuario.getIdEstadoSeleccionado(), nombreEstado,
							distritoSeleccionado,
							obtenNombreDistrito(distritoSeleccionado),
							usuario.getIdMunicipioSeleccionado(),
							nombreMunicipio, demarcacionSeleccionada,
							obtenNombreDemarcacion(demarcacionSeleccionada),
							listTotalVotosParcial, listTotalVotos,
							listDistParcialCandidatos, listDistFinalCandidatos,
							listDistribucionPPCI,
							tipoCandidatura.getIdTipoCandidatura(),
							bsdInfoGralActas.esRecuentoTotal(
									tipoCandidatura.getIdTipoCandidatura(),
									usuario, distritoSeleccionado,
									demarcacionSeleccionada));

					log.info("Objeto hlpDatosActa generado");
					log.info("Texto: " + datos.getTextoActa());
					log.info("idEstadoSeleccionado : "
							+ usuario.getIdEstadoSeleccionado());
					log.info("nombreEstado         : " + nombreEstado);
					log.info("distritoSeleccionado : " + distritoSeleccionado);
					log.info("nombreDistritoSelec  : "
							+ obtenNombreDistrito(distritoSeleccionado));
					log.info("idMunicipioSeleccci  : "
							+ usuario.getIdMunicipioSeleccionado());
					log.info("nombreMunicipioSele  : " + nombreMunicipio);
					log.info("demarcacionSeleccio  : "
							+ demarcacionSeleccionada);
					log.info("nombreDemarcacionS   : "
							+ obtenNombreDemarcacion(demarcacionSeleccionada));
					log.info("listTotalVotosParcial     size: "
							+ listTotalVotosParcial.size());
					log.info("listTotalVotos            size: "
							+ listTotalVotos.size());
					log.info("listDistParcialCandidatos size: "
							+ listDistParcialCandidatos.size());
					log.info("listDistFinalCandidatos   size: "
							+ listDistFinalCandidatos.size());
					log.info("listDistribucionPPCI      size: "
							+ listDistribucionPPCI.size());
					log.info("idTipoCandidatura    : "
							+ tipoCandidatura.getIdTipoCandidatura());

				} catch (Exception e) {
					log.error("Error obteniendo alguna lista");
					log.error(e);
					hlpMsg.mensajesValidacion(30);
					e.printStackTrace();
					return null;
				}

				byte[] bytesActa = exporter.generarActa(hlpDatosActa);

				InputStream stream = new ByteArrayInputStream(bytesActa);
				String contentType = "application/pdf";

				return new DefaultStreamedContent(stream, contentType,
						"actaGenerada_"
								+ tipoCandidatura.getNombreCorto().replace(" ",
										"_") + ".pdf");

			}
		} catch (Exception e) {
			log.error("Error al generar el acta");
			log.error(e);
			hlpMsg.mensajesValidacion(29);
			e.printStackTrace();
			return null;

		}
		return null;
	}

	/**
	 * Método que construye lista de distritos para combo en DIPUTADO MR &
	 * DIPUTADO RP
	 * 
	 * @param mapDemarcaciones
	 * @return lista
	 */
	private List<HLPDistrito> obtenDistritos(Map<String, String> mapDistritos) {
		List<HLPDistrito> lista = new ArrayList<HLPDistrito>();
		HLPDistrito crt;
		for (Map.Entry<String, String> entry : mapDistritos.entrySet()) {
			crt = new HLPDistrito();
			crt.setIdDistrito(Integer.parseInt(entry.getKey()));
			crt.setCabecera(entry.getValue());
			lista.add(crt);

		}
		// for (int i = 1; i < 10; i++) {
		// crt = new HLPDistrito();
		// crt.setIdDistrito(i);
		// crt.setCabecera("Distrito " + i);
		// lista.add(crt);
		// log.info("obtenDistritos " + crt.getIdDistrito());
		// }

		return lista;
	}

	/**
	 * Método que construye lista de demarcaciones para combo en REGIDURIA MR &
	 * REGIDURIA RP
	 * 
	 * @param mapDemarcaciones
	 * @return lista
	 */
	private List<HLPDemarcacion> obtenDemarcaciones(
			Map<String, String> mapDemarcaciones) {
		List<HLPDemarcacion> lista = new ArrayList<HLPDemarcacion>();
		HLPDemarcacion crt;
		for (Map.Entry<String, String> entry : mapDemarcaciones.entrySet()) {
			crt = new HLPDemarcacion();
			crt.setIdDemarcacion(Integer.parseInt(entry.getKey()));
			crt.setNombreDemarcacion(entry.getValue());
			lista.add(crt);

		}
		// for (int i = 1; i < 10; i++) {
		// crt = new HLPDemarcacion();
		// crt.setIdDemarcacion(i);
		// crt.setNombreDemarcacion("Demarcacion " + i);
		// lista.add(crt);
		// log.info("obtenDemarcaciones " + crt.getIdDemarcacion());
		// }
		return lista;
	}

	/**
	 * Metodo para validar distrito o demarcacion seleccionado y renderizar
	 * vista: True = Mostrar formulario para captura False = Mostrar mensaje
	 * informativo
	 * 
	 */
	public void muestraFormularioDipReg() {
		asignaMunicipioOple();
		rpConEspeciales = false;
		datos.setTextoActa(textoActa());
		if (tipoCandidatura.getIdTipoCandidatura().equals(
				Constantes.ID_TIPO_CAND_DIPUTADO_MR)
				|| tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
			try {

				// Traer consejeros de C_CONSEJEROS
				List<DTOCConsejo> tiposConsejeros = new ArrayList<DTOCConsejo>();
				try {
					tiposConsejeros = bsdActas.getCatalogoConsejero();
					
					if (usuario.getIdMunicipioSeleccionado() > 0) {
						List<DTOCConsejo> tiposConsejeross = bsdActas.getCatalogoConsejero();
						for(int i = 0; i < tiposConsejeross.size()-2; i++){
							tiposConsejeros.add(tiposConsejeross.get(i));
						}
					} else {
						tiposConsejeros = bsdActas.getCatalogoConsejero();
						for (DTOCConsejo dtoConsejeros : tiposConsejeros) {
							log.info("Lista de consejeros: "
									+ dtoConsejeros.getTituloConsejero());
							;
						}
					}
					// Se guarda la lista de consejeros, que se genera en metodo
					// --
					// generaListaConsejeros
					datos.setConsejeros(generaListaConsejeros(tiposConsejeros));
				} catch (Exception e) {
					hlpMsg.mensajesValidacion(53);
					log.error("Error obteniendo los tipos de consejeros");
					log.error(e);

					e.printStackTrace();
					return;

				}

				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)
						&& usuario.getIdMunicipioSeleccionado() != 0) {
					log.info("Entra en validacion de regiduria RP");

					if (hlpInfo.existenCasillasEspeciales(bsdInfo,
							bsdInfoGralActas, usuario,
							tipoCandidatura.getIdTipoCandidatura(),
							distritoSeleccionado)) {
						log.info("Verifica que tiene especiales");
						List<Integer> listaDistritosMR = null;

						listaDistritosMR = bsdActas.consultaDistritosPorMR(
								Constantes.ID_TIPO_CAND_DIPUTADO_MR, usuario,
								distritoSeleccionado);

						if (listaDistritosMR != null
								&& !listaDistritosMR.isEmpty()) {
							log.info("Ya fue capturada por MR");

							rpConEspeciales = true;

							if (distritoSeleccionado != null) {
								if (distritoSeleccionado.intValue() == -1) {
									muestraFormulario = false;
									hlpMsg.mensajesValidacion(1);
								} else {
									if (tipoAccion.equals(accionCapturar)) {
										List<DTOCandidatoWS> asociacioness = obtenCoalicionesAsociaciones(tipoCandidatura
												.getIdTipoCandidatura());

										List<DTOCandidatoWS> asociaciones = hlpInfo
												.cargaCandidatosUnicosByCandidatura(asociacioness);
										datos.setRepresentantes(generaListaRepresentantes(asociaciones));
										muestraFormulario = true;
									}
								}
							} else {
								muestraFormulario = false;

							}

						} else {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(58);
						}

						// Diputados RP OPLE sin especiales
					} else {
						if (distritoSeleccionado != null) {
							if (distritoSeleccionado.intValue() == -1) {
								muestraFormulario = false;
								hlpMsg.mensajesValidacion(1);
							} else {
								if (tipoAccion.equals(accionCapturar)) {
									List<DTOCandidatoWS> asociacioness = obtenCoalicionesAsociaciones(tipoCandidatura
											.getIdTipoCandidatura());

									List<DTOCandidatoWS> asociaciones = hlpInfo
											.cargaCandidatosUnicosByCandidatura(asociacioness);

									datos.setRepresentantes(generaListaRepresentantes(asociaciones));
									muestraFormulario = true;
								}
							}
						} else {
							muestraFormulario = false;
						}
					}

					// Diputados MR y Diputados RP para municipio
				} else {
					if (distritoSeleccionado != null) {
						if (distritoSeleccionado.intValue() == -1) {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(1);
						} else {
							if (tipoAccion.equals(accionCapturar)) {
								List<DTOCandidatoWS> asociacioness = obtenCoalicionesAsociaciones(tipoCandidatura
										.getIdTipoCandidatura());

								List<DTOCandidatoWS> asociaciones = hlpInfo
										.cargaCandidatosUnicosByCandidatura(asociacioness);
								datos.setRepresentantes(generaListaRepresentantes(asociaciones));
								muestraFormulario = true;
							}
						}
					} else {
						muestraFormulario = false;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // Acaba DIPutados

		// Regidurias
		if (tipoCandidatura.getIdTipoCandidatura().equals(
				Constantes.ID_TIPO_CAND_REGIDURIA_MR)
				|| tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {

			try {
				// Traer consejeros de C_CONSEJEROS
				List<DTOCConsejo> tiposConsejeros = new ArrayList<DTOCConsejo>();
				try {

					if (usuario.getIdMunicipioSeleccionado() > 0) {
						List<DTOCConsejo> tiposConsejeross = bsdActas.getCatalogoConsejero();
						for(int i = 0; i < tiposConsejeross.size()-2; i++){
							tiposConsejeros.add(tiposConsejeross.get(i));
						}
					} else {
						tiposConsejeros = bsdActas.getCatalogoConsejero();
						for (DTOCConsejo dtoConsejeros : tiposConsejeros) {
							log.info("Lista de consejeros: "
									+ dtoConsejeros.getTituloConsejero());
							;
						}
					}
					// Se guarda la lista de consejeros, que se genera en metodo
					// --
					// generaListaConsejeros
					datos.setConsejeros(generaListaConsejeros(tiposConsejeros));
				} catch (Exception e) {
					hlpMsg.mensajesValidacion(53);
					log.error("Error obteniendo los tipos de consejeros");
					log.error(e);

					e.printStackTrace();
					return;

				}

				if (tipoCandidatura.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {

					log.info("Entra en validacion de regiduria RP");

					log.info("Demarcacion Seleccionada: "
							+ demarcacionSeleccionada);
					if (hlpInfo.existenCasillasEspeciales(bsdInfo,
							bsdInfoGralActas, usuario,
							tipoCandidatura.getIdTipoCandidatura(),
							demarcacionSeleccionada)) {
						log.info("Verifica que tiene especiales");
						List<Integer> listaDemarcacionesMR = null;

						listaDemarcacionesMR = bsdActas
								.consultaDemarcacionesPorMR(
										Constantes.ID_TIPO_CAND_REGIDURIA_MR,
										usuario, demarcacionSeleccionada);

						if (listaDemarcacionesMR != null
								&& !listaDemarcacionesMR.isEmpty()) {

							log.info("Ya fue capturada por MR");

							rpConEspeciales = true;

							log.info("demarcacion seleccionada es igual: "
									+ demarcacionSeleccionada);
							if (demarcacionSeleccionada != null) {
								if (demarcacionSeleccionada.intValue() == -1) {
									muestraFormulario = false;
									hlpMsg.mensajesValidacion(2);
								} else {
									if (tipoAccion.equals(accionCapturar)) {

										List<DTOCandidatoWS> asociacioness = obtenCoalicionesAsociaciones(tipoCandidatura
												.getIdTipoCandidatura());

										List<DTOCandidatoWS> asociaciones = hlpInfo
												.cargaCandidatosUnicosByCandidatura(asociacioness);

										datos.setRepresentantes(generaListaRepresentantes(asociaciones));
										muestraFormulario = true;
									}
								}
							} else {
								muestraFormulario = false;
							}
						} else {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(59);

						}

						// Regiduria RP no tiene especial
					} else {

						log.info("demarcacion seleccionada es igual: "
								+ demarcacionSeleccionada);
						if (demarcacionSeleccionada != null) {
							if (demarcacionSeleccionada.intValue() == -1) {
								muestraFormulario = false;
								hlpMsg.mensajesValidacion(2);
							} else {
								if (tipoAccion.equals(accionCapturar)) {
									List<DTOCandidatoWS> asociacioness = obtenCoalicionesAsociaciones(tipoCandidatura
											.getIdTipoCandidatura());

									List<DTOCandidatoWS> asociaciones = hlpInfo
											.cargaCandidatosUnicosByCandidatura(asociacioness);

									datos.setRepresentantes(generaListaRepresentantes(asociaciones));
									log.info("MUESTRA FORMULARIO TRUE, SI NO ES ESPECIAL");
									muestraFormulario = true;
								}
							}
						} else {
							log.info("MUESTRA FORMULARIO ENTRA AL ELSE, FALSE SI NO ES ESPECIAL");
							muestraFormulario = false;
						}

					}

					// Regidurias MR
				} else {
					log.info("ENTRA PARA MR");
					log.info("demarcacion seleccionada es igual: "
							+ demarcacionSeleccionada);
					if (demarcacionSeleccionada != null) {
						if (demarcacionSeleccionada.intValue() == -1) {
							muestraFormulario = false;
							hlpMsg.mensajesValidacion(2);
						} else {
							if (tipoAccion.equals(accionCapturar)) {
								List<DTOCandidatoWS> asociacioness = obtenCoalicionesAsociaciones(tipoCandidatura
										.getIdTipoCandidatura());

								List<DTOCandidatoWS> asociaciones = hlpInfo
										.cargaCandidatosUnicosByCandidatura(asociacioness);

								datos.setRepresentantes(generaListaRepresentantes(asociaciones));
								muestraFormulario = true;
							}
						}
					} else {
						muestraFormulario = false;
					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // Aqui termina Regidurias

	}

	/**
	 * Obtiene las asociaciones y coaliciones cuando el tipo de candidatura es
	 * diputados o regidurias.
	 * 
	 * @param candidatura
	 * @return Lista de asociaciones y coaliciones
	 */
	public List<DTOCandidatoWS> obtenCoalicionesAsociaciones(Integer candidatura) {

		log.info("obtenCoalicionesAsociaciones");

		if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)
				|| usuario.getRolUsuario().equals(
						Constantes.ADMIN_PARAM_CAPTURA_OC)
				|| usuario.getRolUsuario().equals(Constantes.ADMIN_CAPTURA_OC)
				|| usuario.getRolUsuario().equals(Constantes.CONSULTA_JL)
				|| usuario.getRolUsuario().equals(
						Constantes.CONSULTA_PARTIDO_JL)
				|| usuario.getRolUsuario().equals(
						Constantes.CONSULTA_CONSEJERO_OC)
				|| usuario.getRolUsuario().equals(Constantes.CONSULTA_0C)
				|| usuario.getRolUsuario().equals(
						Constantes.ADMIN_LECTURA_CAU_OC)) {

			if (usuario.getIdMunicipioSeleccionado() == null
					|| !(usuario.getIdMunicipioSeleccionado() > 0)) {
				usuario.setIdMunicipioSeleccionado(Constantes.SIN_MUNICIPIO);
			}

		}

		// Obtenemos valores de la session del usuario
		Integer idDetalle = usuario.getIdDetalleProceso();
		Integer idEstado = usuario.getIdEstadoSeleccionado();
		Integer idDistrito = null;
		Integer idMunicipio = null;
		Integer idDemarcacion = null;

		if (usuario.getIdMunicipioSeleccionado() != null
				&& usuario.getIdMunicipioSeleccionado() > 0) {
			// Si el municipio es diferente de null, seteamos lo que trae el
			// usuario
			idMunicipio = usuario.getIdMunicipioSeleccionado();
		} else {
			idMunicipio = null;
		}

		// Traer asociaciones
		List<DTOCandidatoWS> asociacioness = null;
		List<DTOCandidatoWS> asociaciones = null;

		if (candidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
			try {
				asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
						idDetalle, idEstado, distritoSeleccionado, candidatura,
						idMunicipio, idDemarcacion);

				asociaciones = hlpInfo
						.cargaCandidatosUnicosByCandidatura(asociacioness);

				for (DTOCandidatoWS dtoAsociacion : asociaciones) {
					log.info("Aso - idEstado: " + dtoAsociacion.getIdEstado());
					log.info("Aso-idAsociacion: "
							+ dtoAsociacion.getIdAsociacion());
					log.info("Aso-tipoAsociacion: "
							+ dtoAsociacion.getTipoAsociacion());
					log.info("Aso-nombreAsociacion: "
							+ dtoAsociacion.getNombreAsociacion());
					log.info("Aso-nombreCandidato: "
							+ dtoAsociacion.getNombreCandidato());
					log.info("Aso-emblema: " + dtoAsociacion.getEmblema());
					log.info("Aso-siglas: " + dtoAsociacion.getSiglas());
					log.info("Orden: " + dtoAsociacion.getOrden());
				}
				return asociaciones;
			} catch (Exception e) {
				hlpMsg.mensajesValidacion(52);
				log.error("Error obteniendo asociaciones");
				log.error(e);
				e.printStackTrace();
				return null;
			}

		}

		if (candidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {

			log.info("DIPUTADOS");

			// necesitamos distrito y el municipio segun el caso
			try {
				asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
						idDetalle, idEstado, distritoSeleccionado, candidatura,
						idMunicipio, idDemarcacion);

				asociaciones = hlpInfo.cargaCandidatos(asociacioness);

				for (DTOCandidatoWS dtoAsociacion : asociaciones) {
					log.info("Aso - idEstado: " + dtoAsociacion.getIdEstado());
					log.info("Aso-idAsociacion: "
							+ dtoAsociacion.getIdAsociacion());
					log.info("Aso-tipoAsociacion: "
							+ dtoAsociacion.getTipoAsociacion());
					log.info("Aso-nombreAsociacion: "
							+ dtoAsociacion.getNombreAsociacion());
					log.info("Aso-nombreCandidato: "
							+ dtoAsociacion.getNombreCandidato());
					log.info("Aso-emblema: " + dtoAsociacion.getEmblema());
					log.info("Aso-siglas: " + dtoAsociacion.getSiglas());
					log.info("Orden: " + dtoAsociacion.getOrden());
				}
				return asociaciones;
			} catch (Exception e) {
				hlpMsg.mensajesValidacion(52);
				log.error("Error obteniendo asociaciones");
				log.error(e);
				e.printStackTrace();
				return null;
			}

		}

		if (candidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)) {
			try {
				asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
						idDetalle, idEstado, idDistrito, candidatura,
						idMunicipio, demarcacionSeleccionada);

				asociaciones = hlpInfo
						.cargaCandidatosRegiduriasMR(asociacioness);

				for (DTOCandidatoWS dtoAsociacion : asociaciones) {
					log.info("Aso - idEstado: " + dtoAsociacion.getIdEstado());
					log.info("Aso-idAsociacion: "
							+ dtoAsociacion.getIdAsociacion());
					log.info("Aso-tipoAsociacion: "
							+ dtoAsociacion.getTipoAsociacion());
					log.info("Aso-nombreAsociacion: "
							+ dtoAsociacion.getNombreAsociacion());
					log.info("Aso-emblema: " + dtoAsociacion.getEmblema());
					log.info("Aso-siglas: " + dtoAsociacion.getSiglas());
					log.info("Orden: " + dtoAsociacion.getOrden());
				}
				return asociaciones;
			} catch (Exception e) {
				hlpMsg.mensajesValidacion(52);
				log.error("Error obteniendo asociaciones");
				log.error(e);
				e.printStackTrace();
				return null;
			}
		}

		if (candidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
			log.info("REGIDURIAS");

			try {

				asociacioness = bsdInfo.consumeAsociacionesCoaliciones(
						idDetalle, idEstado, idDistrito, candidatura,
						idMunicipio, demarcacionSeleccionada);

				asociaciones = hlpInfo.cargaCandidatos(asociacioness);

				for (DTOCandidatoWS dtoAsociacion : asociaciones) {
					log.info("Aso - idEstado: " + dtoAsociacion.getIdEstado());
					log.info("Aso-idAsociacion: "
							+ dtoAsociacion.getIdAsociacion());
					log.info("Aso-tipoAsociacion: "
							+ dtoAsociacion.getTipoAsociacion());
					log.info("Aso-nombreAsociacion: "
							+ dtoAsociacion.getNombreAsociacion());
					log.info("Aso-emblema: " + dtoAsociacion.getEmblema());
					log.info("Aso-siglas: " + dtoAsociacion.getSiglas());
					log.info("Orden: " + dtoAsociacion.getOrden());
				}
				return asociaciones;
			} catch (Exception e) {
				hlpMsg.mensajesValidacion(52);
				log.error("Error obteniendo asociaciones");
				log.error(e);
				e.printStackTrace();
				return null;
			}

		}

		return asociaciones;
	}

	/**
	 * 
	 * Método que obtiene combos de distritos para candidatura: DIPUTADOS MR &
	 * DIPUTADOS RP y demarcaciones para candidatura: REGIDURIA MR & REGIDURIA
	 * RP
	 * 
	 */
	public void obtenCombosDistritosDemarcaciones() {

		try {
			asignaMunicipioOple();
			// NIVEL CM
			if (usuario.getIdMunicipioSeleccionado() > 0) {

				log.info("Entra en los obtenCombosDistritosDemarcaciones "
						+ usuario.getRolUsuario());
				log.info("IdDetalle: " + usuario.getIdDetalleProceso());
				log.info("IdProcesoElectoral: "
						+ usuario.getIdProcesoElectoral());
				log.info("idEstado: " + usuario.getIdEstadoSeleccionado());
				log.info("IdMunicipio:_" + usuario.getIdMunicipioSeleccionado());

				// Crea lista de demarcación por candidatura REGIDURIA_MR o
				// REGIDURIA_RP
				if (tipoActa.equals(Constantes.REGIDURIA_MR)) {
					demarcaciones = obtenDemarcaciones(hlpInfo
							.getDemarcacionByMun(bsdInfo, usuario,
									tipoCandidatura.getIdTipoCandidatura()));

					crearListasDemarcacion(demarcaciones);

				}

				if (tipoActa.equals(Constantes.REGIDURIA_RP)) {
					log.info("ENTRA EN REGIDURIAS RP EN COMBO");
					// Nuevo servicio
					// if (!hlpInfo.existenCasillasEspeciales(bsdInfo,
					// bsdInfoGralActas, usuario,
					// tipoCandidatura.getIdTipoCandidatura())) {
					// log.info("ENTRA EN REGIDURIAS SIN CASILLA ESPECIAL");
					// regiduriasSinEspeciales = true;
					// muestraFormulario = true;
					// } else {
					// log.info("ENTRA EN REGIDURIAS CON CASILLA ESPECIAL");
					// demarcaciones = obtenDemarcaciones(hlpInfo
					// .getDemarcacionesByCaptura100(bsdInfo,
					// bsdInfoGralActas, usuario,
					// tipoCandidatura.getIdTipoCandidatura()));

					demarcaciones = obtenDemarcaciones(hlpInfo
							.getDemarcacionByMun(bsdInfo, usuario,
									Constantes.ID_TIPO_CAND_REGIDURIA_MR));
					// }

					crearListasDemarcacion(demarcaciones);

				}

				// Crea lista de Distritos por candidatura DIPUTADOS_MR o
				// DIPUTADOS_RP
				if (tipoActa.equals(Constantes.DIPUTADO_MR)) {
					log.info("DIPUTADO EN INIT");

					distritos = obtenDistritos(hlpInfo
							.getDistritosByCaptura100(bsdInfo,
									bsdInfoGralActas, usuario,
									tipoCandidatura.getIdTipoCandidatura()));

					// Crea lista de distritos para mostrarlo en la vista
					crearListasDistritos(distritos);
				}

				if (tipoActa.equals(Constantes.DIPUTADO_RP)) {
					log.info("DIPUTADO EN INIT");

					distritos = obtenDistritos(hlpInfo
							.getDistritosByCaptura100(bsdInfo,
									bsdInfoGralActas, usuario,
									tipoCandidatura.getIdTipoCandidatura()));

					// Crea lista de distritos para mostrarlo en la vista
					crearListasDistritos(distritos);
				}

				// NIVEL OPLE
			} else {

				if (tipoActa.equals(Constantes.REGIDURIA_MR)) {

					demarcaciones = obtenDemarcaciones(hlpInfo
							.getDemarcacionByMun(bsdInfo, usuario,
									tipoCandidatura.getIdTipoCandidatura()));

					crearListasDemarcacion(demarcaciones);

				}

				if (tipoActa.equals(Constantes.REGIDURIA_RP)) {

					// demarcaciones = obtenDemarcaciones(hlpInfo
					// .getDemarcacionesByCaptura100(bsdInfo,
					// bsdInfoGralActas, usuario,
					// tipoCandidatura.getIdTipoCandidatura()));

					demarcaciones = obtenDemarcaciones(hlpInfo
							.getDemarcacionByMun(bsdInfo, usuario,
									Constantes.ID_TIPO_CAND_REGIDURIA_MR));

					crearListasDemarcacion(demarcaciones);

				}
				// Actas finales de diputados para OPLE
				if (tipoActa.equals(Constantes.DIPUTADO_MR)) {
					log.info("ENTRA EN DIPUTADOS MR - VALIDACION TRAS VALIDACION TRAS VALIDACION");
					distritos = obtenDistritos(hlpInfo.getDistritosByDist(
							bsdInfo, usuario,
							tipoCandidatura.getIdTipoCandidatura()));

					crearListasDistritos(distritos);
				}

				// Actas finales para diputados RP OPLE
				if (tipoActa.equals(Constantes.DIPUTADO_RP)) {
					try {
						// distritos = obtenDistritos(hlpInfo
						// .getDistritosByActasGeneradas(bsdInfoGralActas,
						// usuario,
						// tipoCandidatura.getIdTipoCandidatura()));

						distritos = obtenDistritos(hlpInfo.getDistritosByDist(
								bsdInfo, usuario,
								Constantes.ID_TIPO_CAND_DIPUTADO_MR));

						crearListasDistritos(distritos);
					} catch (Exception e) {
						log.error(e);
						log.error("FATAL ERROR EN OBTENER DISTRITOS POR ACTAS GENERADAS");
						;
					}
				}

			}

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(53);
			log.error("Error obteniendo combo de distritos y demarcaciones");
			log.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para crear listas de demarcaciones de acuerdo a las que ya fueron
	 * capturadas
	 */
	@SuppressWarnings("unchecked")
	public void crearListasDemarcacion(List<HLPDemarcacion> demarcaciones) {

		Collections.sort(demarcaciones);

		DTOActaTipoCandidaturaPK pkActa = (DTOActaTipoCandidaturaPK) generaPkActa(usuario);

		demarcacionCaptura = new ArrayList<HLPDemarcacion>();
		demarcacionConsulta = new ArrayList<HLPDemarcacion>();

		for (HLPDemarcacion demarcacion : demarcaciones) {
			pkActa.setIdRegiduria(demarcacion.getIdDemarcacion());
			DTOBase acta = null;
			acta = bsdActas.getActa(pkActa);
			if (acta != null) {
				demarcacionConsulta.add(demarcacion);
			} else {
				demarcacionCaptura.add(demarcacion);
			}

		}

	}

	/**
	 * Metodo para crear listas de distritos de acuerdo a las que ya fueron
	 * capturadas
	 */
	@SuppressWarnings("unchecked")
	public void crearListasDistritos(List<HLPDistrito> distritos) {

		Collections.sort(distritos);
		DTOActaTipoCandidaturaPK pkActa = (DTOActaTipoCandidaturaPK) generaPkActa(usuario);

		distritoCaptura = new ArrayList<HLPDistrito>();
		distritoConsulta = new ArrayList<HLPDistrito>();

		for (HLPDistrito distrito : distritos) {
			pkActa.setIdDistrito(distrito.getIdDistrito());
			DTOBase acta = null;
			acta = bsdActas.getActa(pkActa);
			if (acta != null) {
				log.info("Distritos consulta");
				distritoConsulta.add(distrito);
			} else {
				distritoCaptura.add(distrito);
			}

		}
	}

	/**
	 * Obtener nombre del distrito
	 */
	private String obtenNombreDistrito(Integer idDistrito) {
		for (HLPDistrito hlp : distritos) {
			if (hlp.getIdDistrito().equals(idDistrito)) {
				return hlp.getCabecera();
			}
		}
		return null;
	}

	/**
	 * Obtener nombre de la demarcacion
	 */
	private String obtenNombreDemarcacion(Integer idDemarcacion) {
		for (HLPDemarcacion hlp : demarcaciones) {
			if (hlp.getIdDemarcacion().equals(idDemarcacion)) {
				return hlp.getNombreDemarcacion();
			}
		}
		return null;
	}

	/**
	 * 
	 * Método para mostrar texto oficial y guardarlo para la vista y la
	 * generacion del acta.
	 * 
	 */
	public String textoActa() {
		log.info("entra a textoActa()");
		String texto = null;

		// ACTAS PARCIALES OPLE

		// if (usuario != null) {
		// if (usuario.getIdMunicipioSeleccionado() == null) {
		// log.info("usuario.getIdMunicipioSeleccionado es null");
		// usuario.setIdMunicipioSeleccionado(new Integer(-5));
		// }
		// }
		boolean recuentoTotal = false;

		asignaMunicipioOple();

		try {
			recuentoTotal = bsdInfoGralActas.esRecuentoTotal(
					tipoCandidatura.getIdTipoCandidatura(), usuario,
					distritoSeleccionado, demarcacionSeleccionada);
		} catch (Exception e) {
			log.error("ERROR OBTENIENDO LA BANDERA DE RECUENTO EN textoActa(");
			log.error(e);
		}

		String textoRecuento = "";
		if (recuentoTotal) {
			textoRecuento = "_recuento";
		}

		if (usuario.getIdMunicipioSeleccionado() > 0) {

			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_parcial_diputados_mr"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_RP)
					&& !actaEstatal) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_parcial_diputados_rp"
								+ textoRecuento);
			}

			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_ayuntamientos"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_parcial_gobernador"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_REGIDURIA_MR)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_regidor_mr"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_regidor_rp"
								+ textoRecuento);
			}
			// ACTAS FINALES OPLE
		} else {
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_diputados_mr"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_RP)
					&& !actaEstatal) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_diputados_rp"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_GOBERNADOR)) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_gobernador"
								+ textoRecuento);
			}
			if (tipoCandidatura.getIdTipoCandidatura().equals(
					Constantes.ID_TIPO_CAND_DIPUTADO_RP)
					&& actaEstatal) {
				texto = Utilidades
						.mensajeProperties("etiqueta_acta_texto_diputados_rp_estatal"
								+ textoRecuento);
			}
		}

		return texto;
	}

	/**
	 * valida
	 */
	// public void validaCamp(){
	// for(HLPConsejero hlp : datos.getConsejeros()){
	//
	// }
	// }

	/**
	 * Método que valida en modificar
	 */
	public void validaCam() {
		for (HLPConsejero hlp : datos.getConsejeros()) {
			if (hlp.getNombre() == null && hlp.getNombre().equals("")) {
				hlp.setNombre(null);
			}
			if (hlp.getaPaterno() == null && hlp.getaPaterno().equals("")) {
				hlp.setaPaterno(null);
			}
			if (hlp.getaMaterno() == null && hlp.getaMaterno().equals("")) {
				hlp.setaMaterno(null);
			}
			if (hlp.getCalidad() == null && hlp.getCalidad().equals("")) {
				hlp.setCalidad(null);
			}
		}
	}

	/**
	 * 
	 * Método llamado al guardar un acta. Primero guarda los datos del acta.
	 * Luego guarda representantes y consejeros asociados a esa acta en sus
	 * respectivas tablas.
	 * 
	 * @throws Exception
	 * 
	 */
	public void guardaDatos() throws Exception {

		log.info(" ID MODULO PRE : " + mbAdmin.getDto().getIdModulo());
		log.info(" ID ACCION PRE : " + mbAdmin.getDto().getIdAccion());

		asignaMunicipioOple();

		// throw new Exception();

		// if (this.valido == true) {
		try {

			log.info("guardaDatos");
			DTOBase acta = generaDtoActa(datos);
			if (acta != null) {

				// Guardamos distribucion parcial para gobernador y
				// diputados MR o RP
				// Solo los capturistas municipales y admins pueden hacerla

				// Solo validamos que el idMunicipioSeleccionado sea > 0
				// ya que este método guardaDatos() sólo puede ser llamado
				// por los usuarios que tienen permisos
				validaCampos();
				if (this.valido) {
					if (usuario.getIdMunicipioSeleccionado() > 0) {
						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_GOBERNADOR)) {

							log.info("Guarda datos gobernador");

							for (HLPRepresentante hlpRp : datos
									.getRepresentantes()) {
								log.info("TIPO ASOCIACION: "
										+ hlpRp.getTipoAsociacion());
								if (hlpRp.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE) {
									log.info("NOMBRE COMPLETO DEL CANDIDATO: "
											+ hlpRp.getNombreCompleto());
									log.info("EMBLEMA: "
											+ hlpRp.getEmblemaAsociacion());
									hlpRp.setEmblemaAsociacion(hlpRp
											.getNombreAsociacion());
								}
							}
							try {
								if (!hlpInfo
										.guardaDistribucionParcialGobernador(
												bsdInfoGralActas,
												bsdDist,
												usuario,
												tipoCandidatura
														.getIdTipoCandidatura(),
												datos.getRepresentantes())) {
									hlpMsg.mensajesValidacion(50);
									log.info("No se pudo guardar la distribucion parcial para gobernador");
									return;
								}

							} catch (Exception e) {
								hlpMsg.mensajesValidacion(50);
								log.error(e);
								log.info("No se pudo guardar la distribucion parcial para gobernador");
								return;
							}
						}
						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_DIPUTADO_MR)) {

							log.info("a diputado MR");

							for (HLPRepresentante hlpRp : datos
									.getRepresentantes()) {
								log.info("TIPO ASOCIACION: "
										+ hlpRp.getTipoAsociacion());
								if (hlpRp.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE) {
									log.info("NOMBRE COMPLETO DEL CANDIDATO: "
											+ hlpRp.getNombreAsociacion());
									log.info("EMBLEMA: "
											+ hlpRp.getEmblemaAsociacion());
									hlpRp.setEmblemaAsociacion(hlpRp
											.getNombreAsociacion());
								}
							}
							try {
								if (!hlpInfo
										.guardarDistribucionParcialDiputados(
												bsdInfoGralActas,
												bsdDist,
												usuario,
												tipoCandidatura
														.getIdTipoCandidatura(),
												datos.getRepresentantes(),
												distritoSeleccionado)) {
									hlpMsg.mensajesValidacion(50);
									log.info("No se pudo guardar la distribucion parcial para diputados MR");
									return;
								}

							} catch (Exception e) {
								hlpMsg.mensajesValidacion(50);
								log.error(e);
								log.info("No se pudo guardar la distribucion parcial para diputados MR");
								return;
							}
						}
						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_DIPUTADO_RP)
								&& !actaEstatal) {

							for (HLPRepresentante hlpRp : datos
									.getRepresentantes()) {
								log.info("TIPO ASOCIACION: "
										+ hlpRp.getTipoAsociacion());
								if (hlpRp.getTipoAsociacion().intValue() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE
										.intValue()) {
									log.info("NOMBRE COMPLETO DEL CANDIDATO: "
											+ hlpRp.getNombreCompleto());
									log.info("EMBLEMA: "
											+ hlpRp.getEmblemaAsociacion());
									hlpRp.setEmblemaAsociacion(hlpRp
											.getNombreAsociacion());
								}
							}

							try {
								if (!hlpInfo
										.guardarDistribucionParcialDiputados(
												bsdInfoGralActas,
												bsdDist,
												usuario,
												tipoCandidatura
														.getIdTipoCandidatura(),
												datos.getRepresentantes(),
												distritoSeleccionado)) {
									hlpMsg.mensajesValidacion(50);
									log.info("No se pudo guardar la distribucion parcial para diputados RP");
									return;
								}

							} catch (Exception e) {
								hlpMsg.mensajesValidacion(50);
								log.info("No se pudo guardar la distribucion parcial para diputados RP");
								return;
							}

						}

						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {

							for (HLPRepresentante hlpRp : datos
									.getRepresentantes()) {
								log.info("TIPO ASOCIACION: "
										+ hlpRp.getTipoAsociacion());
								if (hlpRp.getTipoAsociacion().intValue() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE
										.intValue()) {
									log.info("NOMBRE COMPLETO DEL CANDIDATO: "
											+ hlpRp.getNombreCompleto());
									log.info("EMBLEMA: "
											+ hlpRp.getEmblemaAsociacion());
									hlpRp.setEmblemaAsociacion(hlpRp
											.getNombreAsociacion());
								}
							}
							log.info("VALOR DE RP CON ESPECIALES EN REGIDURIA RP "
									+ rpConEspeciales);
							try {
								if (!hlpInfo
										.guardarDistribucionFinalRegidurias(
												bsdInfo,
												bsdInfoGralActas,
												bsdDist,
												usuario,
												tipoCandidatura
														.getIdTipoCandidatura(),
												datos.getRepresentantes(),
												demarcacionSeleccionada,
												rpConEspeciales)) {
									hlpMsg.mensajesValidacion(50);
									log.info("No se pudo guardar la distribucion final para regidurias RP");
									return;
								}
							} catch (Exception e) {
								hlpMsg.mensajesValidacion(50);
								log.info("No se pudo guardar la distribucion final para regidurias RP");
								log.error(e);
								return;
							}

						}

						// *************** OPLE
						// ************************************************************
					} else {

						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_DIPUTADO_RP)
								&& !actaEstatal) {

							for (HLPRepresentante hlpRp : datos
									.getRepresentantes()) {
								log.info("TIPO ASOCIACION: "
										+ hlpRp.getTipoAsociacion());
								if (hlpRp.getTipoAsociacion().intValue() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE
										.intValue()) {
									log.info("NOMBRE COMPLETO DEL CANDIDATO: "
											+ hlpRp.getNombreCompleto());
									log.info("EMBLEMA: "
											+ hlpRp.getEmblemaAsociacion());
									hlpRp.setEmblemaAsociacion(hlpRp
											.getNombreAsociacion());
								}
							}

							log.info("VALOR DE RP CON ESPECIALES EN DISTRITO RP"
									+ rpConEspeciales);
							try {
								if (!hlpInfo
										.guardarDistribucionxDistritoDiputadosRP(
												bsdInfoGralActas,
												bsdDist,
												usuario,
												tipoCandidatura
														.getIdTipoCandidatura(),
												distritoSeleccionado,
												Constantes.TIPO_ACTA_FINAL,
												rpConEspeciales)) {
									hlpMsg.mensajesValidacion(50);
									log.info("No se pudo guardar la distribucion parcial para diputados RP FINAL OPLE");
									return;
								}
							} catch (Exception e) {
								hlpMsg.mensajesValidacion(50);
								log.info("No se pudo guardar la distribucion parcial para diputados RP FINAL OPLE");
								log.error(e);
								return;
							}

						}

						// ACTA ESTATAL DIPUTADOS RP
						if (tipoCandidatura.getIdTipoCandidatura().equals(
								Constantes.ID_TIPO_CAND_DIPUTADO_RP)
								&& actaEstatal) {

							for (HLPRepresentante hlpRp : datos
									.getRepresentantes()) {
								log.info("TIPO ASOCIACION: "
										+ hlpRp.getTipoAsociacion());
								if (hlpRp.getTipoAsociacion().intValue() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE
										.intValue()) {
									log.info("NOMBRE COMPLETO DEL CANDIDATO: "
											+ hlpRp.getNombreCompleto());
									log.info("EMBLEMA: "
											+ hlpRp.getEmblemaAsociacion());
									hlpRp.setEmblemaAsociacion(hlpRp
											.getNombreAsociacion());
								}
							}
							try {

								if (!hlpInfo
										.guardarDistribucionxEstadoDiputadosRP(
												bsdInfoGralActas,
												bsdDist,
												usuario,
												tipoCandidatura
														.getIdTipoCandidatura(),
												Constantes.TIPO_ACTA_ESTATAL)) {
									hlpMsg.mensajesValidacion(50);
									log.info("No se pudo guardar la distribucion parcial para diputados RP ESTATAL");
									return;
								}
							} catch (Exception e) {
								log.error(e);
								log.error("ERROR EN LA DISTRIBUCION PARA DIPUTADOS RP ESTATAL");
								e.printStackTrace();
							}
						}

					}

					// Guardamos acta
					try {
						bsdActas.guardaActa(acta);
					} catch (Exception e) {
						log.error("ERROR  AL GUARDAR LOS DATOS DEL ACTA");
						log.error(e);
						e.printStackTrace();
					}

					// Eliminarmos los represenantes que haya
					List<DTORepresentante> repres = new ArrayList<DTORepresentante>();

					repres.addAll(bsdActas
							.getRepresentantes(((DTOActaTipoCandidatura) acta)
									.getPk()));
					bsdActas.eliminaRepresentantes(repres);

					// Guardamos representantes
					try {
						guardaRepresentantes(acta);
					} catch (Exception e) {
						log.error("ERROR EN REPRESENTANTES AL GUARDAR");
						log.error(e);
						e.printStackTrace();
					}

					// Eliminamos los que haya
					List<DTOConsejero> consejeros = bsdActas
							.getConsejeros(((DTOActaTipoCandidatura) acta)
									.getPk());
					bsdActas.eliminaConsejeros(consejeros);

					// Guardamos consejeros
					try {
						guardaConsejeros(acta);
					} catch (Exception e) {
						log.error("ERROR EN CONSEJEROS AL GUARDAR");
						log.error(e);
						e.printStackTrace();
					}

					log.info("termina guarda datos");

					log.info("DATOS FECHA Y HORA");
					log.info("TIEMPO  : " + ((datos.hourIsAM()) ? "AM" : "PM"));
					log.info("HORA    : " + datos.getHoras());
					log.info("MINUTOS : " + datos.getMinutos());
					log.info("DIA     : " + datos.getDay());

					// Resetaeamos valores
					init();
					// Ocultamos formulario
					muestraFormulario = false;
					obtenCombosDistritosDemarcaciones();
					scrollTop();
					hlpMsg.mensajesValidacion(20);
					RequestContext.getCurrentInstance().reset("formActa");
					log.info("termina guardado de datos");

				}
			}

			log.info(" ID MODULO POST : " + mbAdmin.getDto().getIdModulo());
			log.info(" ID ACCION POST : " + mbAdmin.getDto().getIdAccion());

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(60);
			log.error("Error guardando datos de acta", e);
			log.error(e);
			e.printStackTrace();
			return;

		}
		// }

	}

	/**
	 * Delega el guardado de representantes para esta acta.
	 * 
	 * @param acta
	 *            : Un objeto que solo puede ser de dos tipos: 1.
	 *            DTOActaTipoCandidatura 2. DTOActaTipoCandParcial
	 */
	public void guardaRepresentantes(DTOBase acta) throws Exception {
		DTOActaTipoCandidatura actaT = (DTOActaTipoCandidatura) acta;
		DTORepresentante dtoR;

		if (datos.getRepresentantes() != null
				&& !datos.getRepresentantes().isEmpty()) {
			log.info("datos.representantes.size() : "
					+ datos.getRepresentantes().size());

		} else {
			log.info("Lista de representantes es vacia");
			return;
		}

		for (int i = 0; i < datos.getRepresentantes().size(); i++) {
			HLPRepresentante hlp = datos.getRepresentantes().get(i);
			//
			// if (hlp.getNombre().equals("") || hlp.getNombre() == null
			// && hlp.getaPaterno() == null
			// || hlp.getaPaterno().equals("")
			// || hlp.getaMaterno() == null
			// || hlp.getaMaterno().equals("")) {
			//
			// }
			//
			if (hlp.getNombre() != null && !hlp.getNombre().equals("")) {
				dtoR = hlp.getDtoRepresentante(acta);
				dtoR.getPk().setIdRepresentante(i + 1);
				dtoR.getPk().setTipoActa(actaT.getPk().getTipoActa());
				bsdActas.guardaRepresentante(dtoR);
			}

		}
	}

	/**
	 * Delega el guardado de consejeros para esta acta.
	 * 
	 * @param acta
	 *            : Un objeto que solo puede ser de dos tipos: 1.
	 *            DTOActaTipoCandidatura 2. DTOActaTipoCandParcial
	 */
	public void guardaConsejeros(DTOBase acta) throws Exception {
		log.info("Guarda Consejeros");
		System.out.println("Guarda Consejeros");
		DTOActaTipoCandidatura actaT = (DTOActaTipoCandidatura) acta;
		DTOConsejero dtoC;
		int i = 0;

		log.info("datos.getConsejeros() " + datos.getConsejeros());
		for (HLPConsejero con : datos.getConsejeros()) {
			log.info("Tipo consejero: " + con.getTituloConsejero());

			if (con.getNombre() != null && !con.getNombre().equals("")) {

				dtoC = con.getDtoConsejero(acta);
				dtoC.getPk().setIdConsejero(i + 1);
				dtoC.getPk().setTipoActa(actaT.getPk().getTipoActa());
				bsdActas.guardaConsejero(dtoC);
				i++;
			}

		}
		log.info("fin guarda consejeros");
	}

	/**
	 * 
	 * Método para cambiar estatus de edición para el acta -Cambia valor de
	 * editable en el acta y habilita botón de guardar en vista modificar para
	 * usuario: CAPTURISTA MUNICIPAL
	 * 
	 */
	public void editaActa() {
		try {
			Object pkActa = generaPkActa(usuario);
			bsdActas.setEditable((DTOActaTipoCandidaturaPK) pkActa);
			log.info("DESPUES DE EDITABLE...");
			DTOBase acta = null;
			acta = bsdActas.getActa(pkActa);
			// Obtenemos el valor de editable
			editable = ((DTOActaTipoCandidatura) acta).getEditable();
			// Agregamos mensaje informativo al OPLE de que habilito la edición
			hlpMsg.mensajesValidacion(23);
			scrollTop();
		} catch (Exception e) {
			log.error("Error en editaActa()");
			log.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Metodo para eliminar el acta, opción de OPLE Elimina primero tablas hijas
	 * - FK (CONSEJEROS - REPRESENTANTES) Y elimina por último tabla padre - PK
	 * (ACTA_TIPO_CANDIDATURA)
	 * 
	 */
	public void eliminandoActa() {
		try {
			log.info("eliminandoActa");
			asignaMunicipioOple();
			Object pkActa = generaPkActa(usuario);

			DTODistribucionPartidosCIPK pkDistPart = new DTODistribucionPartidosCIPK();
			pkDistPart.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pkDistPart.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pkDistPart.setIdEstado(usuario.getIdEstadoSeleccionado());
			pkDistPart.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pkDistPart.setIdRegiduria(demarcacionSeleccionada);
			pkDistPart.setIdTipoCandidatura(tipoCandidatura
					.getIdTipoCandidatura());

			// Obtener representantes y consejeros de esta acta
			List<DTOConsejero> consejeros = bsdActas.getConsejeros(pkActa);
			List<DTORepresentante> representantes = bsdActas
					.getRepresentantes(pkActa);

			// Eliminar Consejeros
			bsdActas.eliminaConsejeros(consejeros);

			// Eliminar Representantes
			bsdActas.eliminaRepresentantes(representantes);

			log.info("despues de eliminar repres");
			// Eliminar Acta
			// Se elimina DISTRIBUCION EN TIPO DE ACTA PARCIAL
			if (((DTOActaTipoCandidaturaPK) pkActa).getTipoActa().equals(
					Constantes.TIPO_ACTA_PARCIAL)) {
				log.info("Antes de eliminar distribucion");
				eliminarDistribucionParcial(tipoCandidatura
						.getIdTipoCandidatura());
			}

			if (((DTOActaTipoCandidaturaPK) pkActa).getTipoActa().equals(
					Constantes.TIPO_ACTA_FINAL)
					&& tipoCandidatura.getIdTipoCandidatura().equals(
							Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
				bsdDist.eliminaDistribucionPartidosCI(pkDistPart);

			}

			bsdActas.eliminandoActa((DTOActaTipoCandidaturaPK) pkActa);
			log.info("DESPUES DE ELIMINAR EL ACTA...");
			hlpMsg.mensajesValidacion(22);
			// Ocultamos formulario
			muestraFormulario = false;

			obtenCombosDistritosDemarcaciones();
			scrollTop();

		} catch (Exception e) {
			hlpMsg.mensajesValidacion(54);
			log.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * Eliminar Distribucion Parcial Cuando se elimina el acta parcial para
	 * Gobernador, Diputados MR, Diputado RP
	 * 
	 * @throws Exception
	 */
	public void eliminarDistribucionParcial(Integer idTipoCandidatura)
			throws Exception {

		asignaMunicipioOple();

		DTODistribucionCandParcialPK pkDistCandPar = new DTODistribucionCandParcialPK();
		DTODistribucionTotParcialPK pkDistTotPar = new DTODistribucionTotParcialPK();
		if (idTipoCandidatura.intValue() == Constantes.ID_TIPO_CAND_GOBERNADOR
				.intValue()) {
			pkDistCandPar.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pkDistCandPar.setIdDistrito(-5);
			pkDistCandPar.setIdTipoCandidatura(idTipoCandidatura);
			pkDistCandPar
					.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pkDistCandPar.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pkDistCandPar.setIdEstado(usuario.getIdEstadoSeleccionado());
			// Elimina distribucionCanParcial
			bsdDist.eliminaDistribucionCandParcial(pkDistCandPar);

			pkDistTotPar.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pkDistTotPar.setIdDistrito(-5);
			pkDistTotPar.setIdTipoCandidatura(idTipoCandidatura);
			pkDistTotPar.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pkDistTotPar.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pkDistTotPar.setIdEstado(usuario.getIdEstadoSeleccionado());

			// Elimina distribucionTotParcial
			bsdDist.eliminaDistribucionTotParcial(pkDistTotPar);
		}
		if (idTipoCandidatura.intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR
				.intValue()) {
			pkDistCandPar.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pkDistCandPar.setIdDistrito(distritoSeleccionado);
			pkDistCandPar.setIdTipoCandidatura(idTipoCandidatura);
			pkDistCandPar
					.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pkDistCandPar.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pkDistCandPar.setIdEstado(usuario.getIdEstadoSeleccionado());
			// Elimina distribucionCanParcial
			bsdDist.eliminaDistribucionCandParcial(pkDistCandPar);

			pkDistTotPar.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pkDistTotPar.setIdDistrito(distritoSeleccionado);
			pkDistTotPar.setIdTipoCandidatura(idTipoCandidatura);
			pkDistTotPar.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pkDistTotPar.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pkDistTotPar.setIdEstado(usuario.getIdEstadoSeleccionado());

			// Elimina distribucionTotParcial
			bsdDist.eliminaDistribucionTotParcial(pkDistTotPar);
		}
		if (idTipoCandidatura.intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_RP
				.intValue()) {
			pkDistCandPar.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pkDistCandPar.setIdDistrito(distritoSeleccionado);
			pkDistCandPar.setIdTipoCandidatura(idTipoCandidatura);
			pkDistCandPar
					.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pkDistCandPar.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pkDistCandPar.setIdEstado(usuario.getIdEstadoSeleccionado());
			// Elimina distribucionCanParcial
			bsdDist.eliminaDistribucionCandParcial(pkDistCandPar);
		}
	}

	/**
	 * Devuele una lista de todos los consejeros.
	 * 
	 * @param capturados
	 * @param todos
	 * @return
	 */
	public List<DTOConsejero> llenaEspaciosConsejerosNoCapturados(
			List<DTOConsejero> capturados, List<DTOCConsejo> todos) {

		Map<DTOCConsejo, DTOConsejero> consejeros = new LinkedHashMap<DTOCConsejo, DTOConsejero>();
		for (DTOCConsejo c : todos) {
			DTOConsejero co = new DTOConsejero();
			co.setTipoConsejero(c);
			consejeros.put(c, co);
		}
		for (DTOConsejero c : capturados) {
			DTOCConsejo cn = c.getTipoConsejero();
			consejeros.put(cn, c);
		}

		List<DTOConsejero> cons = new ArrayList<DTOConsejero>(
				consejeros.values());

		return cons;
	}

	public List<DTORepresentante> llenaEspaciosRepresentantesNoCapturados(
			List<DTORepresentante> capturados, List<DTOCandidatoWS> asociaciones) {
		Map<Integer, DTORepresentante> repres = new LinkedHashMap<Integer, DTORepresentante>();
		for (DTOCandidatoWS cand : asociaciones) {

			log.info("asociaciones mod ");
			log.info("tipoAsociacion: " + cand.getTipoAsociacion());
			log.info("idAsociacion: " + cand.getIdAsociacion());
			log.info("nombreAsociacion: " + cand.getNombreAsociacion());
			log.info("Emblema: " + cand.getEmblema());
			log.info("nombreCandidato: " + cand.getNombreCandidato());

			if (cand.getTipoAsociacion().intValue() == 1
					|| cand.getTipoAsociacion().intValue() == 4) {
				DTORepresentante r = new DTORepresentante();
				r.setTipoAsociacion(cand.getTipoAsociacion());
				r.setIdAsociacion(cand.getIdAsociacion());
				r.setEmblemaAsociacion(cand.getEmblema());

				if (cand.getTipoAsociacion().intValue() == 4) {
					r.setNombreAsociacion(cand.getNombreCandidato());
				}
				if (cand.getTipoAsociacion().intValue() == 1) {
					r.setNombreAsociacion(cand.getNombreAsociacion());
				}
				if (cand.getSiglas() == null) {
					r.setSiglasAsociacion("SIGLAS NULAS");
				}
				repres.put(r.getIdAsociacion(), r);

			}

		}

		for (DTORepresentante r : capturados) {
			repres.put(r.getIdAsociacion(), r);
		}
		List<DTORepresentante> representantes = new ArrayList<DTORepresentante>(
				repres.values());

		return representantes;
	}

	/**
	 * Método para subir el scroll en la vista y que el usuario pueda observar
	 * los mensajes de acuerdo a la acción.
	 */
	public void scrollTop() {
		RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
	}

	/**
	 * Método para limpiar los valores del formulario
	 */
	public void limpiaFormulario() {
		log.info("LimpiaFormulario");
		RequestContext.getCurrentInstance().reset("formActa:panelGeneral");
		init();
	}

	/**
	 * Método que retorna emblemas para los representantes de partidos políticos
	 * 
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
			log.error("Error obtenidno imag");
			log.error(e);
			e.printStackTrace();
			return new DefaultStreamedContent();
		}

		return this.imagen;
	}

	public void asignaMunicipioOple() {
		if (usuario.getRolUsuario().equals(Constantes.CAPTURA_OPLE)
				|| usuario.getRolUsuario().equals(
						Constantes.ADMIN_PARAM_CAPTURA_OC)
				|| usuario.getRolUsuario().equals(Constantes.ADMIN_CAPTURA_OC)
				|| usuario.getRolUsuario().equals(Constantes.CONSULTA_JL)
				|| usuario.getRolUsuario().equals(
						Constantes.CONSULTA_PARTIDO_JL)
				|| usuario.getRolUsuario().equals(
						Constantes.CONSULTA_CONSEJERO_OC)
				|| usuario.getRolUsuario().equals(Constantes.CONSULTA_0C)
				|| usuario.getRolUsuario().equals(
						Constantes.ADMIN_LECTURA_CAU_OC)) {

			if (usuario.getIdMunicipioSeleccionado() == null
					|| !(usuario.getIdMunicipioSeleccionado() > 0)) {
				usuario.setIdMunicipioSeleccionado(Constantes.SIN_MUNICIPIO);
			}

		}
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public FormGeneracionActas getDatos() {
		return datos;
	}

	/**
	 * Método setter
	 * 
	 * @param datos
	 */
	public void setDatos(FormGeneracionActas datos) {
		this.datos = datos;
	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public String getTipoActa() {
		return tipoActa;
	}

	/**
	 * Método Setter
	 * 
	 * @param tipoActa
	 */
	public void setTipoActa(String tipoActa) {
		this.tipoActa = tipoActa;

	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * Método Setter
	 * 
	 * @param tipoAccion
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public String getAccionCapturar() {
		return accionCapturar;
	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public String getAccionModificar() {
		return accionModificar;
	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public String getAccionConsultar() {
		return accionConsultar;
	}

	/**
	 * Método getter
	 * 
	 * @return
	 */
	public HLPTipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	/**
	 * Método Setter
	 * 
	 * @param tipoCandidatura
	 */
	public void setTipoCandidatura(HLPTipoCandidatura tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

	/**
	 * Método getter
	 * 
	 * @return editable
	 */
	public Integer getEditable() {
		return editable;
	}

	/**
	 * Método Setter
	 * 
	 * @param editable
	 */
	public void setEditable(Integer editable) {
		this.editable = editable;
	}

	/**
	 * Método getter
	 * 
	 * @return usuario
	 */
	public DTOUsuarioLogin getUsuario() {
		return usuario;
	}

	/**
	 * Método setter
	 * 
	 * @param usuario
	 */
	public void setUsuario(DTOUsuarioLogin usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método getter
	 * 
	 * @return distritoSeleccionado
	 */
	public Integer getDistritoSeleccionado() {
		return distritoSeleccionado;
	}

	/**
	 * Método setter
	 * 
	 * @param distritoSeleccionado
	 */
	public void setDistritoSeleccionado(Integer distritoSeleccionado) {
		this.distritoSeleccionado = distritoSeleccionado;
	}

	/**
	 * Método getter
	 * 
	 * @return distritoCaptura
	 */
	public List<HLPDistrito> getDistritoCaptura() {
		return distritoCaptura;
	}

	/**
	 * Método setter
	 * 
	 * @param distritoCaptura
	 */
	public void setDistritoCaptura(List<HLPDistrito> distritoCaptura) {
		this.distritoCaptura = distritoCaptura;
	}

	/**
	 * Método getter
	 * 
	 * @return distritoConsulta
	 */
	public List<HLPDistrito> getDistritoConsulta() {
		return distritoConsulta;
	}

	/**
	 * Método setter
	 * 
	 * @param distritoConsulta
	 */
	public void setDistritoConsulta(List<HLPDistrito> distritoConsulta) {
		this.distritoConsulta = distritoConsulta;
	}

	/**
	 * Método getter
	 * 
	 * @return demarcacionSeleccionada
	 */
	public Integer getDemarcacionSeleccionada() {
		return demarcacionSeleccionada;
	}

	/**
	 * Método setter
	 * 
	 * @param demarcacionSeleccionada
	 */
	public void setDemarcacionSeleccionada(Integer demarcacionSeleccionada) {
		this.demarcacionSeleccionada = demarcacionSeleccionada;
	}

	/**
	 * Método getter
	 * 
	 * @return demarcacionCaptura
	 */
	public List<HLPDemarcacion> getDemarcacionCaptura() {
		return demarcacionCaptura;
	}

	/**
	 * Método setter
	 * 
	 * @param demarcacionCaptura
	 */
	public void setDemarcacionCaptura(List<HLPDemarcacion> demarcacionCaptura) {
		this.demarcacionCaptura = demarcacionCaptura;
	}

	/**
	 * Método getter
	 * 
	 * @return demarcacionSeleccionada
	 */
	public List<HLPDemarcacion> getDemarcacionConsulta() {
		return demarcacionConsulta;
	}

	/**
	 * Método setter
	 * 
	 * @param demarcacionConsulta
	 */
	public void setDemarcacionConsulta(List<HLPDemarcacion> demarcacionConsulta) {
		this.demarcacionConsulta = demarcacionConsulta;
	}

	/**
	 * Método getter
	 * 
	 * @return muestraFormulario
	 */
	public boolean isMuestraFormulario() {
		return muestraFormulario;
	}

	/**
	 * Método setter
	 * 
	 * @param muestraFormulario
	 */
	public void setMuestraFormulario(boolean muestraFormulario) {
		this.muestraFormulario = muestraFormulario;
	}

	/**
	 * Método getter
	 * 
	 * @return bloqueCombo
	 */
	public Integer getBloqueCombo() {
		return bloqueCombo;
	}

	/**
	 * Método setter
	 * 
	 * @param bloqueCombo
	 */
	public void setBloqueCombo(Integer bloqueCombo) {
		this.bloqueCombo = bloqueCombo;
	}

	/**
	 * Método getter
	 * 
	 * @return validaImprimir
	 */
	public Integer getValidaImprimir() {
		return validaImprimir;
	}

	/**
	 * Método setter
	 * 
	 * @param validaImprimir
	 */
	public void setValidaImprimir(Integer validaImprimir) {
		this.validaImprimir = validaImprimir;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public boolean isBotonEditar() {
		return botonEditar;
	}

	public void setBotonEditar(boolean botonEditar) {
		this.botonEditar = botonEditar;
	}

	public boolean isBotonImprimir() {
		return botonImprimir;
	}

	public void setBotonImprimir(boolean botonImprimir) {
		this.botonImprimir = botonImprimir;
	}

	public boolean isBotonEliminar() {
		return botonEliminar;
	}

	public void setBotonEliminar(boolean botonEliminar) {
		this.botonEliminar = botonEliminar;
	}

	public boolean isBotonModificar() {
		return botonModificar;
	}

	public void setBotonModificar(boolean botonModificar) {
		this.botonModificar = botonModificar;
	}

	public boolean isDisableElimina() {
		return disableElimina;
	}

	public void setDisableElimina(boolean disableElimina) {
		this.disableElimina = disableElimina;
	}

	public boolean isCalidadPresidente() {
		return calidadPresidente;
	}

	public void setCalidadPresidente(boolean calidadPresidente) {
		this.calidadPresidente = calidadPresidente;
	}

	public boolean isCalidadSecretario() {
		return calidadSecretario;
	}

	public void setCalidadSecretario(boolean calidadSecretario) {
		this.calidadSecretario = calidadSecretario;
	}

	public boolean isCalidadConsejeros() {
		return calidadConsejeros;
	}

	public void setCalidadConsejeros(boolean calidadConsejeros) {
		this.calidadConsejeros = calidadConsejeros;
	}

	public boolean isActaEstatal() {
		return actaEstatal;
	}

	public void setActaEstatal(boolean actaEstatal) {
		this.actaEstatal = actaEstatal;
	}

}