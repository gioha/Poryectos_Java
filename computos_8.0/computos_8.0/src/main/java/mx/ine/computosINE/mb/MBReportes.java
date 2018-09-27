package mx.ine.computosINE.mb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidaturaWS;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDInformacionGeneralActaInterface;
import mx.ine.computosINE.dto.DTODistrito;
import mx.ine.computosINE.dto.DTORegiduria;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPComputoPorCasilla;
import mx.ine.computosINE.dto.helper.HLPComputoPorDemarcacion;
import mx.ine.computosINE.dto.helper.HLPComputoPorDistrito;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorEstado;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorMunicipio;
import mx.ine.computosINE.dto.helper.HLPEstatusDeCasillas;
import mx.ine.computosINE.helper.HLPInfoGralComputos;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;
import mx.ine.historicos.api.lazy.LazyGenericArrayDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("mbReporte")
@Scope("session")
public class MBReportes extends MBReportesMenu {

	private static final long serialVersionUID = 070420171237L;
	private static final Log logger = LogFactory.getLog(MBReportes.class);

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
	/**
	 * Helper para traer combos
	 */
	@Autowired
	@Qualifier("hplInfoGralComputos")
	private transient HLPInfoGralComputos hlpInfo;

	/**
	 * Datos del usuario en la sesion
	 */
	private DTOUsuarioLogin usuario;

	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;

	public void init() {
		super.init();
		inicializaComboTipoCandidatura();
		if (this.numReporte.equals(REPORTE_POR_CASILLA)) {
			inicializaComboEstatuscasilla();
		} else if (this.numReporte.equals(REPORTE_POR_DEMARCACIONES)) {
			inicializaComboDemarcaciones();
		}
	}

	/**
	 * Se consulta la tabla COMPUTOSINE.C_ESTATUS para llenar combo de estatus
	 * de casillas
	 */
	private void inicializaComboEstatuscasilla() {
		try {
			this.lstEstatusCasilla = bsdCargaInformacion.estatusComputos();
		} catch (Exception e) {
			logger.error("Error al cargar lista de estatus casilla . . ."
					+ e.getMessage());
		}
	}

	/**
	 * Se inicializa el combo de demarcaciones o distritos, dependiendo del tipo
	 * de candidatura seleccionada Se consulta en el esquema de GEOGRAFICOS para
	 * cargar la información.
	 */
	public void consultaReporte_cargaDistritoDemarcacion() {
		limpiaLazyData();
		this.dtoFiltros.setIdDemarcacion(null);
		this.dtoFiltros.setIdDistrito(null);
		this.dtoFiltros.setIdFiltroEstatusCasilla(null);
		this.dtoFiltros.setNombreDemarcacion(null);
		this.dtoFiltros.setNombreDistrito(null);

		// Si la candidatura seleccionada es diputados MR o diputados RP, se
		// carga el combo de distritos
		if (dtoFiltros.getIdTipoCandidatura().equals(
				Constantes.ID_TIPO_CAND_DIPUTADO_MR)
				|| dtoFiltros.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
			inicializaComboDistritos();
			// Si la candidatura seleccionada es regidurias MR o regidurias
			// RP, se carga el combo de demarcaciones
		} else if (dtoFiltros.getIdTipoCandidatura().equals(
				Constantes.ID_TIPO_CAND_REGIDURIA_MR)
				|| dtoFiltros.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
			inicializaComboDemarcaciones();
			// Si la candidatura seleccionada es gobernador o presidente
			// municipal
			// realiza la consulta
		} else {
			consultaReporte();
		}

	}

	private void inicializaComboDemarcaciones() {
		try {
			this.lstDemarcaciones = bsdDemarcaciones.cargaDemarcaciones(
					this.dtoFiltros.getIdEstadoSeleccionado(),
					this.dtoFiltros.getIdMunicipioElectoralSeleccionado());
		} catch (Exception e) {
			logger.error("Error al consultar Geograficos  . . . "
					+ e.getMessage());
		}
	}

	private void inicializaComboDistritos() {
		try {
			this.lstDistritos = bsdDistritos.cargaDistritos(
					this.dtoFiltros.getIdEstadoSeleccionado(),
					this.dtoFiltros.getIdMunicipioElectoralSeleccionado());
		} catch (Exception e) {
			logger.error("Error al consultar Geograficos  . . . "
					+ e.getMessage());
		}
	}

	/**
	 * Se inicializa el combo de tipo candidaturas
	 */
	private void inicializaComboTipoCandidatura() {

		/*
		 * Prueba fallida al consumir el servicio
		 * SNR/servicioCandidatos/obtenTiposCandidaturas try {
		 * List<DTOCandidatoWS> tipos =
		 * hlpCandidatos.consultarTiposCandidatura(20, 18, 2); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
		this.lstTipoCandidatura = new ArrayList<DTOCandidaturaWS>();

		switch (this.numReporte) {
		case 1: // por casillas
			cargaTiposCandidatura(Constantes.ID_TIPO_CAND_AYUNTAMIENTO,
					Constantes.ID_TIPO_CAND_REGIDURIA_MR,
					Constantes.ID_TIPO_CAND_REGIDURIA_RP,
					Constantes.ID_TIPO_CAND_GOBERNADOR,
					Constantes.ID_TIPO_CAND_DIPUTADO_MR,
					Constantes.ID_TIPO_CAND_DIPUTADO_RP);
			break;

		case 2: // por distrito
			cargaTiposCandidatura(Constantes.ID_TIPO_CAND_DIPUTADO_MR,
					Constantes.ID_TIPO_CAND_DIPUTADO_RP);
			break;

		case 3: // por municipio
			cargaTiposCandidatura(Constantes.ID_TIPO_CAND_AYUNTAMIENTO,
					Constantes.ID_TIPO_CAND_GOBERNADOR);
			break;

		case 5: // por estado
			cargaTiposCandidatura(Constantes.ID_TIPO_CAND_DIPUTADO_RP_MR_ESTATAL);
			break;
		
		case 6: // estatus de casillas a nivel municipal
			cargaTiposCandidatura(Constantes.ID_TIPO_CAND_AYUNTAMIENTO,
					Constantes.ID_TIPO_CAND_REGIDURIA_MR,
					Constantes.ID_TIPO_CAND_REGIDURIA_RP,
					Constantes.ID_TIPO_CAND_GOBERNADOR,
					Constantes.ID_TIPO_CAND_DIPUTADO_MR,
					Constantes.ID_TIPO_CAND_DIPUTADO_RP);
			break;

		case 7:
			cargaTiposCandidatura(Constantes.ID_TIPO_CAND_GOBERNADOR,
					Constantes.ID_TIPO_CAND_DIPUTADO_MR);
			break;
		default:
			break;
		}

		// Se ordenan las candidaturas según el idTipoCandidatura
		Collections.sort(this.lstTipoCandidatura,
				new Comparator<DTOCandidaturaWS>() {
					@Override
					public int compare(DTOCandidaturaWS o1, DTOCandidaturaWS o2) {
						return o1.getIdTipoCandidatura()
								- o2.getIdTipoCandidatura();
					}
				});
	}

	/**
	 * Carga la lista de candidaturas por medio de la clase Constantes
	 * 
	 * @param idTiposCandidaturas
	 */
	private void cargaTiposCandidatura(Integer... idTiposCandidaturas) {
		DTOCandidaturaWS candidaturaWS;
		for (Integer idTipoCandidatura : idTiposCandidaturas) {
			candidaturaWS = new DTOCandidaturaWS();
			candidaturaWS.setIdTipoCandidatura(idTipoCandidatura);
			switch (idTipoCandidatura) {
			case 7: // ID_TIPO_CAND_DIPUTADO_MR:
				candidaturaWS.setNombreCorto(Constantes.NOM_DIPUTADO_MR);
				break;
			case 8: // ID_TIPO_CAND_DIPUTADO_RP:
				candidaturaWS.setNombreCorto(Constantes.NOM_DIPUTADO_RP);
				break;
			case 6: // ID_TIPO_CAND_GOBERNADOR:
				candidaturaWS.setNombreCorto(Constantes.NOM_GOBERNADOR);
				break;
			case 9: // ID_TIPO_CAND_AYUNTAMIENTO:
				candidaturaWS.setNombreCorto(Constantes.NOM_AYUNTAMIENTO);
				break;
			case 15: // ID_TIPO_CAND_REGIDURIA_MR:
				candidaturaWS.setNombreCorto(Constantes.NOM_REGIDOR_MR);
				break;
			case 16: // ID_TIPO_CAND_REGIDURIA_RP:
				candidaturaWS.setNombreCorto(Constantes.NOM_REGIDOR_RP);
				break;

			case 0: // ID_TIPO_CAND_REGIDURIA_RP:
				candidaturaWS
						.setNombreCorto(Constantes.NOM_CORTO_DIPUTADO_RP_MR_ESTATAL);
				break;
			default:
				break;
			}
			this.lstTipoCandidatura.add(candidaturaWS);
		}
	}

	/**
	 * Método encargado de generar el reporte en HTML. Se conecta con el WS de
	 * ComputosINEReportes para obtener el detalle de los votos
	 */
	public void consultaReporte() {
		// Objeto para guardar los filtros a mandar al WS
		Map<String, Object> filtrosSistema = new HashMap<String, Object>();
		String urlWebServiceSistemas = null;

		limpiaLazyData();
		filtrosSistema.put("idProceso", this.dtoFiltros.getIdProceso());
		filtrosSistema.put("idDetalleProceso",
				this.dtoFiltros.getIdDetalleProceso());
		filtrosSistema.put("idEstado",
				this.dtoFiltros.getIdEstadoSeleccionado());
		filtrosSistema.put("idTipoCandidatura",
				this.dtoFiltros.getIdTipoCandidatura());

		inicializaParametrosEncabezado();

		try {

			switch (this.numReporte) {
			case 1: // Reporte por casillas
				HLPComputoPorCasilla hlpComputoPorCasilla;

				// Se inicializan valores para el titulo del reporte
				obtenNombreCandidaturaSeleccionado();
				obtenNombreDistritoSeleccionado();
				obtenNombreDemarcacionSeleccionado();

				validaEstatusCasilla();
				filtrosSistema.put("idMunicipio",
						this.dtoFiltros.getIdMunicipioElectoralSeleccionado());
				filtrosSistema.put("idDistritoLocal",
						this.dtoFiltros.getIdDistrito());
				filtrosSistema.put("idDemarcacion",
						this.dtoFiltros.getIdDemarcacion());
				filtrosSistema.put("idEstatus",
						this.dtoFiltros.getIdFiltroEstatusCasilla());

				hlpComputoPorCasilla = bsdReportes
						.inicializaEncabezadoReportePorCasilla(dtoFiltros);

				hlpComputoPorCasilla.iniciaReporte(dtoFiltros);

				this.dtoParametros.setTituloReporte(hlpComputoPorCasilla
						.getTituloReporte());
				this.dtoParametros.setColumnas(hlpComputoPorCasilla
						.getColumnas());
				this.dtoParametros.setEncabezado(hlpComputoPorCasilla
						.getListaEncabezados());

				urlWebServiceSistemas = urlRestWs
						+ "ComputosINEReportes/servicios/reportes/computosPorCasilla";
				// urlWebServiceSistemas =
				// "http://localhost:8081/ComputosINEReportes/servicios/reportes/computosPorCasilla";
				break;

			case 2: // Reporte a nivel distrito

				HLPComputoPorDistrito hlpComputoPorDistrito = bsdReportes
						.inicializaEncabezadoReportePorDistrito(dtoFiltros);

				// Se inicializan valores para el titulo del reporte
				obtenNombreCandidaturaSeleccionado();

				hlpComputoPorDistrito.iniciaReporte(dtoFiltros);
				this.dtoParametros.setTituloReporte(hlpComputoPorDistrito
						.getTituloReporte());
				this.dtoParametros.setColumnas(hlpComputoPorDistrito
						.getColumnas());
				this.dtoParametros.setEncabezado(hlpComputoPorDistrito
						.getListaEncabezados());

				urlWebServiceSistemas = urlRestWs
						+ "ComputosINEReportes/servicios/reportes/computosPorDistrito";
				// urlWebServiceSistemas =
				// "http://localhost:8081/ComputosINEReportes/servicios/reportes/computosPorDistrito";
				break;

			case 3: {// Reporte Concentrado Por Municipio
				logger.info("Entra reporte 4");

				HLPConcentradoPorMunicipio hlpConcentradoPorMunicipio = bsdReportes
						.inicializaEncabezadosReportesConcentradoPorMunicipio(dtoFiltros);

				// Se inicializan valores para el titulo del reporte
				obtenNombreCandidaturaSeleccionado();

				hlpConcentradoPorMunicipio.iniciaReporte(dtoFiltros);

				this.dtoParametros.setTituloReporte(hlpConcentradoPorMunicipio
						.getTituloReporte());
				logger.info("   TITULO REPORTE :"
						+ this.dtoParametros.getTituloReporte());
				this.dtoParametros.setColumnas(hlpConcentradoPorMunicipio
						.getColumnas());

				logger.info("   COLUMNAS :" + this.dtoParametros.getColumnas());
				this.dtoParametros.setEncabezado(hlpConcentradoPorMunicipio
						.getListaEncabezados());
				logger.info(" ENCABEZADO : "
						+ this.dtoParametros.getEncabezado());

				urlWebServiceSistemas = urlRestWs
						+ "ComputosINEReportes/servicios/reportes/concentradoPorMunicipio";
				// urlWebServiceSistemas =
				// "http://localhost:8081/ComputosINEReportes/servicios/reportes/concentradoPorMunicipio";

				break;
			}

			case 4: {// Reporte por Demarcacion

				HLPComputoPorDemarcacion hlpComputoPorDemarcacion;

				// Se inicializan valores para el titulo del reporte
				obtenNombreDemarcacionSeleccionado();

				filtrosSistema.put("idMunicipio",
						this.dtoFiltros.getIdMunicipioElectoralSeleccionado());
				filtrosSistema.put("idDemarcacion",
						this.dtoFiltros.getIdDemarcacion());

				hlpComputoPorDemarcacion = bsdReportes
						.inicializaEncabezadoReportePorDemarcacion(dtoFiltros);
				hlpComputoPorDemarcacion.iniciaReporte(dtoFiltros);

				this.dtoParametros.setTituloReporte(hlpComputoPorDemarcacion
						.getTituloReporte());
				this.dtoParametros.setColumnas(hlpComputoPorDemarcacion
						.getColumnas());
				this.dtoParametros.setEncabezado(hlpComputoPorDemarcacion
						.getListaEncabezados());

				// Se agrega mensaje de información para cuando no existen
				// casillas especiales
				// de la demarcacion seleccionada
				if (!hlpComputoPorDemarcacion.getAsociaciones().isEmpty()
						&& !hlpComputoPorDemarcacion.hayCasillasEspeciales()) {
					agregaMensaje(
							TipoMensaje.INFO_MENSAJE.getTipo(),
							"mensaje",
							Utilidades
									.mensajeProperties("validacion_mensaje_reporte_demarcacion_casillas_especiales"));
				}

				urlWebServiceSistemas = urlRestWs
						+ "ComputosINEReportes/servicios/reportes/computosPorDemarcacion";
				// urlWebServiceSistemas =
				// "http://localhost:8081/ComputosINEReportes/servicios/reportes/computosPorDemarcacion";

				break;

			}

			case 5: { // Reporte por estado
				logger.info("Entro al reporte 5");

				HLPConcentradoPorEstado hlpConcentradoPorEstado = bsdReportes
						.inicializaEncabezadosReportesConcentradoPorEstado(dtoFiltros);

				hlpConcentradoPorEstado.iniciaReporte();

				this.dtoParametros.setTituloReporte(hlpConcentradoPorEstado
						.getTituloReporte());
				logger.info("   TITULO REPORTE :"
						+ this.dtoParametros.getTituloReporte());
				this.dtoParametros.setColumnas(hlpConcentradoPorEstado
						.getColumnas());

				logger.info("   COLUMNAS :" + this.dtoParametros.getColumnas());
				this.dtoParametros.setEncabezado(hlpConcentradoPorEstado
						.getListaEncabezados());
				logger.info(" ENCABEZADO : "
						+ this.dtoParametros.getEncabezado());

				urlWebServiceSistemas = urlRestWs
						+ "ComputosINEReportes/servicios/reportes/computosPorEstado";
				// urlWebServiceSistemas =
				// "http://localhost:8080/ComputosINEReportes/servicios/reportes/computosPorEstado";

				break;
			}
			
			case 6: // estatus de casillas a nivel municipio (regidurias, distritos, municipio)
				HLPEstatusDeCasillas hlpEstatusDeCasillas = new HLPEstatusDeCasillas();				
				
				Integer idTipoCandidatura = this.dtoFiltros.getIdTipoCandidatura();
				
				//Se inicializan valores para el titulo del reporte
				obtenNombreCandidaturaSeleccionado();
				
				//Se agregan filtros para consulta al WS
				filtrosSistema.put("idMunicipio", this.dtoFiltros.getIdMunicipioElectoralSeleccionado());
				//Valido el nivel de consulta. 1. regidurias 2. distritos 3. municipio
				if (idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
						|| idTipoCandidatura
								.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
					filtrosSistema.put("nivelConsulta", 1);
				} else if (idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
						|| idTipoCandidatura
								.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
					filtrosSistema.put("nivelConsulta", 2);
				} else if (idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)
						|| idTipoCandidatura
								.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
					filtrosSistema.put("nivelConsulta", 3);
				}
				
				hlpEstatusDeCasillas.iniciaReporte(dtoFiltros);
				this.dtoParametros.setTituloReporte(hlpEstatusDeCasillas
						.getTituloReporte());
				this.dtoParametros.setColumnas(hlpEstatusDeCasillas
						.getColumnas());
				this.dtoParametros.setEncabezado(hlpEstatusDeCasillas
						.getListaEncabezados());

				 urlWebServiceSistemas = urlRestWs + "ComputosINEReportes/servicios/reportes/estatusCasillasMunicipal";
//				urlWebServiceSistemas = "http://localhost:8081/ComputosINEReportes/servicios/reportes/estatusCasillasMunicipal";

				break;
				
			case 7:

				logger.info("Reporte 7");
				HLPEstatusDeCasillas hlpComputoPorDistrit = new HLPEstatusDeCasillas();

				// Se inicializan valores para el titulo del reporte
				obtenNombreCandidaturaSeleccionado();

				hlpComputoPorDistrit.iniciaReporte(dtoFiltros);
				this.dtoParametros.setTituloReporte(hlpComputoPorDistrit
						.getTituloReporte());
				this.dtoParametros.setColumnas(hlpComputoPorDistrit
						.getColumnas());
				this.dtoParametros.setEncabezado(hlpComputoPorDistrit
						.getListaEncabezados());

				urlWebServiceSistemas = urlRestWs + "ComputosINEReportes/servicios/reportes/estatusCasillasGobDipMr";
				//urlWebServiceSistemas = "https://localhost:8443/ComputosINEReportes/servicios/reportes/estatusCasillasGobDipMr";
				break;

			default:
				break;
			}
			dtoParametros.obtenMedidasEncabezado();
			this.lazyData = new LazyGenericArrayDataModel(
					urlWebServiceSistemas, filtrosSistema, true) {
				private static final long serialVersionUID = 260420170129L;
			};
		} catch (Exception ex) {
			logger.error("Error al generar reporte . . .", ex);
		}

	}

	private void validaEstatusCasilla() {
		if (this.dtoFiltros.getIdFiltroEstatusCasilla() == null) {
			this.dtoFiltros.setIdFiltroEstatusCasilla(0);
		}
	}

	private void obtenNombreDemarcacionSeleccionado() {
		Integer idDemarcacion = this.dtoFiltros.getIdDemarcacion();
		switch (this.numReporte) {
		case 1: // Por casilla
			Integer idTipoCandidatura = this.dtoFiltros.getIdTipoCandidatura();
			boolean esTipoCandidaturaRegiduria = (idTipoCandidatura
					.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR))
					|| (idTipoCandidatura
							.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP));
			if (esTipoCandidaturaRegiduria && this.lstDemarcaciones != null
					&& !this.lstDemarcaciones.isEmpty()) {
				for (DTORegiduria dtoRegiduria : this.lstDemarcaciones) {
					if (idDemarcacion.equals(dtoRegiduria.getIdRegiduria())) {
						this.dtoFiltros.setNombreDemarcacion(dtoRegiduria
								.getNombreRegiduria());
					}
				}
			}
			break;

		case 4: // Por demarcacion
			if (this.lstDemarcaciones != null
					&& !this.lstDemarcaciones.isEmpty()) {
				for (DTORegiduria dtoRegiduria : this.lstDemarcaciones) {
					if (idDemarcacion.equals(dtoRegiduria.getIdRegiduria())) {
						this.dtoFiltros.setNombreDemarcacion(dtoRegiduria
								.getNombreRegiduria());
					}
				}
			}
			break;

		default:
			break;
		}
	}

	private void obtenNombreDistritoSeleccionado() {
		Integer idDistrito = this.dtoFiltros.getIdDistrito();
		switch (this.numReporte) {
		case 1:// por casilla
			Integer idTipoCandidatura = this.dtoFiltros.getIdTipoCandidatura();
			boolean esTipoCandidaturaDiputado = (idTipoCandidatura
					.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR))
					|| (idTipoCandidatura
							.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP));
			if (esTipoCandidaturaDiputado && this.lstDistritos != null
					&& !this.lstDistritos.isEmpty()) {
				for (DTODistrito dtoDistrito : this.lstDistritos) {
					if (idDistrito.equals(dtoDistrito.getDistrito())) {
						this.dtoFiltros.setNombreDistrito(dtoDistrito
								.getCabeceraDistrital());
					}
				}
			}
			break;
		default:
			break;
		}

	}

	private void obtenNombreCandidaturaSeleccionado() {
		if (this.lstTipoCandidatura != null
				&& !this.lstTipoCandidatura.isEmpty()) {
			for (DTOCandidaturaWS dtoCandidatura : this.lstTipoCandidatura) {
				if (this.dtoFiltros.getIdTipoCandidatura().equals(
						dtoCandidatura.getIdTipoCandidatura())) {
					this.dtoFiltros.setTipoCandidatura(dtoCandidatura
							.getNombreCorto());
				}
			}
		}
	}

	public boolean verificaDisDiputadoMR(Integer idTipoCandidatura) {
	    usuario = mbAdmin.getDto().getUsuario();
		  //  boolean bandera = false;
		    boolean bandera = false;
		    try {
			bandera = hlpInfo.isDistribucionCompletaDiputadosMR(bsdInfo,
					bsdInfoGralActas, usuario, idTipoCandidatura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bandera;

	}

	public void postProcessXLS(Object document) {
		//Si el reporte es de consulta de estatus por casilla a nivel ople 
		//o a nivel municipal, la última fila del reporte la coloca con
		//estilo normal
		if (this.numReporte.equals(6) || this.numReporte.equals(7)) {
			dtoParametros.setEsReporteEstatusCasilla(Boolean.TRUE);
		} else {
			dtoParametros.setEsReporteEstatusCasilla(Boolean.FALSE);
		}
		dtoParametros.crearEncabezadoXLS((HSSFWorkbook) document);
	}

}
