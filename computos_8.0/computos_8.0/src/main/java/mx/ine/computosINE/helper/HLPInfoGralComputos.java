package mx.ine.computosINE.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.enums.EnumEstatusModulo;
import mx.ine.common.helper.HLPAdministracionInterface;
import mx.ine.common.helper.HLPGeograficosInterface;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.common.ws.geografico.dto.response.DTOMunicipiosWS;
import mx.ine.computosINE.bsd.BSDCapturaVotosInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.bsd.BSDGeneracionActasInterface;
import mx.ine.computosINE.bsd.BSDInformacionGeneralActaInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTODistribucionTotParcialPK;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;
import mx.ine.computosINE.dto.helper.HLPDemarcacion;
import mx.ine.computosINE.dto.helper.HLPDistribucionVotos;
import mx.ine.computosINE.dto.helper.HLPMunicipiosDistritos;
import mx.ine.computosINE.dto.helper.HLPRepresentante;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <code>HLPRegistroActaDiputadoRP.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 02/05/2017 16:43:00
 */
@Component("hplInfoGralComputos")
@Scope("prototype")
public class HLPInfoGralComputos implements Serializable {

	/*
	 * LOGGER
	 */
	private static final Log log = LogFactory.getLog(HLPInfoGralComputos.class);

	private static final long serialVersionUID = -2762811288156957554L;

	public static final String CONST_URL_IMG_GENERICA_PP = "/img_generica.png";
	public static final String CONST_URL_IMG_GENERICA_CI = "/id_CI.png";

	@Autowired
	@Qualifier("hlpAdministracion")
	private HLPAdministracionInterface hlpAdministracion;

	@Autowired
	@Qualifier("hlpGeograficos")
	private HLPGeograficosInterface hlpGeograficos;

	/*
	 * BSD de distribución de votos
	 */
	@Autowired
	@Qualifier("bsdDistribucionVotos")
	public transient BSDDistribucionVotosInterface bsdDistrVotos;

	/*
	 * BSD de carga de información
	 */
	@Autowired
	@Qualifier("bsdCargaInformacion")
	public transient BSDCargaInformacionInterface bsdCargaInformacion;

	@Autowired
	@Qualifier("bsdCapturaVotos")
	private transient BSDCapturaVotosInterface bsdCapturaVotos;

	/*
	 * BSD de Actas
	 */
	@Autowired
	@Qualifier("bsdGeneracionActas")
	public transient BSDGeneracionActasInterface bsdActas;

	/**
	 * Carga todas las casillas de acuerdo al municipio seleccionado
	 * 
	 * @param bsd
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipioLocal
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaWS> cargaCasillasByMunicipio(
			BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario)
			throws Exception {

		// TODO Almacena las casillas tanto Basica, contigua, extraordinaria,
		// especial
		List<DTOCasillaWS> casillas = new ArrayList<DTOCasillaWS>();

		// TODO Consultamos la secciones casillas aprobadas por el consejo desde
		// UBICACION_CASILLAS
		List<DTOCasillaWS> casillasAC = bsd.casillasAprobadasPorMunicipioLocal(
				usuario.getIdProcesoElectoral(),
				usuario.getIdEstadoSeleccionado(),
				usuario.getIdMunicipioSeleccionado());

		// Filtro: Agregamos a la lista solo las casillas S-Especiales
		if (casillasAC != null && !casillasAC.isEmpty()) {
			for (DTOCasillaWS it : casillasAC) {
				casillas.add(it);
			}
		}
		return casillas;
	}

	/**
	 * Carga las casillas Especiales
	 * 
	 * @param bsd
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipioLocal
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaWS> cargaCasillasEspecialesByMunicipio(
			BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario)
			throws Exception {

		// TODO Almacena solo casillas Especiales
		List<DTOCasillaWS> casillasEsp = new ArrayList<DTOCasillaWS>();

		// TODO Consultamos la secciones casillas aprobadas por el consejo desde
		// UBICACION_CASILLAS
		List<DTOCasillaWS> casillasAC = bsd.casillasAprobadasPorMunicipioLocal(
				usuario.getIdProcesoElectoral(),
				usuario.getIdEstadoSeleccionado(),
				usuario.getIdMunicipioSeleccionado());

		// Filtro: Agregamos a la lista solo las casillas S-Especias
		if (casillasAC != null && !casillasAC.isEmpty()) {
			for (DTOCasillaWS it : casillasAC) {
				if (it.getTipoCasilla().equalsIgnoreCase(
						String.valueOf(Utilidades.mensajeProperties(
								"constante_tipoCasilla_s").charAt(0)))) {
					casillasEsp.add(it);
				}
			}
		}
		return casillasEsp;
	}

	/**
	 * Carga casillas por candidatura y tipo casilla
	 * 
	 * @param bsd
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipioLocal
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaWS> cargaCasillasEspecialesXtipoCasilla(
			BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario,
			String tipoCasilla, String modulo) throws Exception {

		// TODO Almacena casillas
		List<DTOCasillaWS> casillasEsp = new ArrayList<DTOCasillaWS>();
		List<DTOCasillaWS> casillasAC = null;

		if (modulo.equalsIgnoreCase(Utilidades
				.mensajeProperties("constante_modulo_diputadosRP"))) {
			// TODO Consultamos la secciones casillas aprobadas por el consejo
			// desde UBICAION_CASILLAS
			casillasAC = bsd.casillasAprobadasPorMunicipioLocal(
					usuario.getIdProcesoElectoral(),
					usuario.getIdEstadoSeleccionado(),
					usuario.getIdMunicipioSeleccionado());
		} else {
			// TODO Consultamos la secciones casillas aprobadas por el consejo
			// para regidurias
			casillasAC = bsd.obtenSecCasillasAprobadasPorRegiduria(
					usuario.getIdProcesoElectoral(),
					usuario.getIdEstadoSeleccionado(),
					usuario.getIdMunicipioSeleccionado(), null);
		}

		// Filtro: Agregamos a la lista solo las casillas S-Especias
		if (casillasAC != null && !casillasAC.isEmpty()) {
			for (DTOCasillaWS it : casillasAC) {
				if (it.getTipoCasilla().equalsIgnoreCase(tipoCasilla)) {
					casillasEsp.add(it);
				}
			}
		}
		return casillasEsp;
	}

	/**
	 * Carga las secciones, dependiendo del listado de casillas
	 * 
	 * @param casillas
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> cargaSeccionesByCasillas(
			List<DTOCasillaWS> casillas) throws Exception {

		// TODO Almacena la secciones
		Map<Integer, Integer> secciones = new LinkedHashMap<Integer, Integer>();
		if (casillas != null && !casillas.isEmpty()) {
			// TODO Recorrer la lista de casillas y agregar al mapa las
			// secciones
			for (DTOCasillaWS it : casillas) {
				secciones.put(it.getSeccion(), it.getSeccion());
			}
		}
		return secciones;
	}

	/**
	 * Carga las secciones especiales
	 * 
	 * @param casillasEsp
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> cargaSeccionesEspecialesByCasillas(
			List<DTOCasillaWS> casillasEsp) throws Exception {

		// TODO Almacena la secciones
		Map<Integer, Integer> secciones = new LinkedHashMap<Integer, Integer>();
		if (casillasEsp != null && !casillasEsp.isEmpty()) {
			// TODO Cargar las secciones
			for (DTOCasillaWS it : casillasEsp) {
				secciones.put(it.getSeccion(), it.getSeccion());
			}
		}
		return secciones;
	}

	/**
	 * Carga los distritos para las secciones de las casillas
	 * 
	 * @param casillas
	 * @return
	 */
	public Map<String, String> cargaDistritosByCasillas(
			List<DTOCasillaWS> casillas) {
		// TODO Almacena los distritos
		Map<String, String> distritos = new LinkedHashMap<String, String>();

		if (casillas != null && !casillas.isEmpty()) {
			// TODO Se recorre la lista de las casillas para obtener los
			// distritos e ir almacenandolos
			for (DTOCasillaWS it : casillas) {
				/* Almacena la descripcion y el id de la cabecera */
				distritos.put(it.getCabeceraDistritoLocal(),
						String.valueOf(it.getIdDistritoLocal()));
			}
		}
		return distritos;
	}

	/**
	 * Carga los distritos para las secciones S-Especiales.
	 * 
	 * @param casillasEsp
	 * @return
	 */
	public Map<String, String> cargaDistritosByCasillasEspeciales(
			List<DTOCasillaWS> casillasEsp) {
		// TODO Almacena la secciones
		Map<String, String> distritos = new LinkedHashMap<String, String>();

		if (casillasEsp != null && !casillasEsp.isEmpty()) {
			// TODO Cargar los distritos
			for (DTOCasillaWS it : casillasEsp) {
				distritos.put(it.getCabeceraDistritoLocal(),
						String.valueOf(it.getIdDistritoLocal()));
			}
		}
		return distritos;
	}

	/**
	 * Carga los calculos para el avance de las actas capturadas.</BR> Calcula:
	 * </BR> 1- Avance global del en % de Actas capturadas. </BR> 2- Actas
	 * capturadas. </BR> 3- Actas pendientes por capturar.
	 * 
	 * @param bsdRegistro
	 * @param universoTotCasillas
	 * @return
	 * @throws Exception
	 */
	public Object[] cargaCalculosxMunicipio(
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, BigDecimal universoTotCasillas,
			Integer idTipoCandidatura) throws Exception {

		// TODO Consultar el numero de casillas capturadas --> new Object [] {3,
		// 15, 1}
		// TODO Object [idProcesoElectoral, idDetalleProceso, idEstado,
		// idMunicipio]
		BigDecimal capturadas = bsdActa
				.getActasCapturadasxMunicipioCandidatura(new Object[] {
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(),
						usuario.getIdEstadoSeleccionado(),
						usuario.getIdMunicipioSeleccionado(), idTipoCandidatura });

		log.info("total capturadas: " + capturadas);
		log.info("total universo  : " + universoTotCasillas);
		return getCalculosActas(capturadas, universoTotCasillas);
	}

	/**
	 * Carga los calculos para el avance de las actas capturadas.</BR> Calcula:
	 * </BR> 1- Avance global del en % de Actas capturadas. </BR> 2- Actas
	 * capturadas. </BR> 3- Actas pendientes por capturar.
	 * 
	 * @param bsdRegistro
	 * @param universoTotCasillas
	 * @return
	 * @throws Exception
	 */
	public Object[] cargaCalculosxDemarcacion(
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, BigDecimal universoTotCasillas,
			Integer idDemarcacion, Integer idTipoCandidatura) throws Exception {

		// TODO Consultar el numero de casillas capturadas --> new Object [] {3,
		// 15, 1}
		// TODO Object [idProcesoElectoral, idDetalleProceso, idEstado,
		// idMunicipio]
		BigDecimal capturadas = bsdActa
				.getCountActasCapturadasByDemarcacion(new Object[] {
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(),
						usuario.getIdEstadoSeleccionado(),
						usuario.getIdMunicipioSeleccionado(), idDemarcacion,
						idTipoCandidatura });

		log.info("ACTAS CAPTURADAS: " + capturadas);
		log.info("ACTAS UNIVERSO: " + universoTotCasillas);
		return getCalculosActas(capturadas, universoTotCasillas);
	}

	/**
	 * Carga los calculos para el avance de las actas capturadas.</BR> Calcula:
	 * </BR> 1- Avance global del en % de Actas capturadas. </BR> 2- Actas
	 * capturadas. </BR> 3- Actas pendientes por capturar.
	 * 
	 * @param bsdRegistro
	 * @param universoTotCasillas
	 * @return
	 * @throws Exception
	 */
	public Object[] cargaCalculos(BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, BigDecimal universoTotCasillas,
			Integer idDistrito, Integer idMunicipio, Integer idTipoCandidatura)
			throws Exception {

		// TODO Consultar el numero de casillas capturadas --> new Object [] {3,
		// 15, 1}
		// TODO Object [idProcesoElectoral, idDetalleProceso, idEstado,
		// idDistrito, tipoCasilla]
		BigDecimal capturadas = bsdActa
				.getCountActasCapturadasByCandidatura(new Object[] {
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(),
						usuario.getIdEstadoSeleccionado(), idDistrito,
						usuario.getIdMunicipioSeleccionado(), idTipoCandidatura });

		log.info("total capturadas: " + capturadas);
		log.info("total universo total  : " + universoTotCasillas);
		return getCalculosActas(capturadas, universoTotCasillas);
	}

	/**
	 * Realiza los calculos referentes al avance de las casillas. </BR> Calcula:
	 * </BR> 1- Avance global del en % de Actas capturadas. </BR> 2- Actas
	 * capturadas. </BR> 3- Actas pendientes por capturar.
	 * 
	 * Nota: El universoTotCasillas no puede ser menor al numero de casillas
	 * capturadas
	 * 
	 * @param capturadas
	 *            numero de actas capturadas
	 * @param universoTotCasillas
	 *            el universo total de actas a capturar
	 * @return
	 * @throws Exception
	 */
	public Object[] getCalculosActas(BigDecimal capturadas,
			BigDecimal universoTotCasillas) throws Exception {

		// TODO Objeto que almacena los calculos del acta
		Object[] calculos = new Object[3];

		// TODO Avance global de actas capturadas
		BigDecimal avanGlobalActasCapt = new BigDecimal(0);
		// TODO Actas capturadas
		BigDecimal actasCapturadas = new BigDecimal(0);
		// TODO Actas pendientes por capturar
		BigDecimal actasPendXcapturadas = universoTotCasillas;
		if (capturadas.compareTo(BigDecimal.ZERO) != 0) {
			if (universoTotCasillas.intValue() <= capturadas.intValue()) {
				avanGlobalActasCapt = capturadas.multiply(new BigDecimal(100))
						.divide(universoTotCasillas, RoundingMode.HALF_DOWN);
				actasCapturadas = capturadas;
				actasPendXcapturadas = universoTotCasillas
						.subtract(actasCapturadas);
			}
		}

		calculos[0] = avanGlobalActasCapt;
		calculos[1] = actasCapturadas;
		calculos[2] = actasPendXcapturadas;
		return calculos;
	}

	/**
	 * Método que verifica que ya se encuentra la captura 100 de 100 concluida
	 * para Gobernador
	 * 
	 * @param bsd
	 * @param bsdActa
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public boolean isCapturaActas100Municipio(BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {

		// Variable para guardar el universoTotal de casillas
		BigDecimal universoTotCasillas = new BigDecimal(0);
		// Variable para guardar el porcentaje de captura de actas
		BigDecimal porcentajeCaptura = new BigDecimal(0);
		// Variable para definir el porcentaje total de captura
		BigDecimal porcentajeTotal = new BigDecimal(100);
		// Variable que permite saber si la captura por municipio ya se
		// encuentra completa
		boolean isActasCompletas = false;
		/**
		 * Se manda a llamar el metodo para consultar las casillas por municipio
		 * del helper de casillas, que trae el universo total de casillas por
		 * municipio
		 */
		List<DTOCasillaWS> casillasMunicipio = bsd
				.casillasAprobadasPorMunicipioLocal(
						usuario.getIdProcesoElectoral(),
						usuario.getIdEstadoSeleccionado(),
						usuario.getIdMunicipioSeleccionado());
		// Se define el universo total de casillas, pasandole el
		// tamaño de la lista que se recupero de la consulta de casillas
		// aprobadas por municipio
		universoTotCasillas = (new BigDecimal(casillasMunicipio.size()));

		/**
		 * Se manda a llamar el metodo para consultar el conteo de cuantas actas
		 * hay sido capturadas por el tipo de Candidatura pasandole el universo
		 * total de casillas, y el tipo de Candidatura
		 */
		Object[] calculos = cargaCalculosxMunicipio(bsdActa, usuario,
				universoTotCasillas, idTipoCandidatura);
		// Se guarda el porcentaje de captura
		porcentajeCaptura = (BigDecimal) calculos[0];
		// Se comparara si el porcentaje de captura es igual al porcentaje de
		// 100%
		if (porcentajeCaptura.compareTo(porcentajeTotal) == 0) {
			// Si ya se cumplio el 100 % entonces se regresa
			isActasCompletas = true;
		} else {
			isActasCompletas = false;
		}

		return isActasCompletas;

	}

	/**
	 * Método que verifica que ya se encuentra la captura 100 de 100 concluida
	 * para Diputados MR
	 * 
	 * @param bsd
	 * @param bsdActa
	 * @param usuario
	 * @param idDistrito
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public boolean isCapturaActas100Distrito(BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idDistrito,
			Integer idTipoCandidatura) throws Exception {

		// Variable para guardar el universoTotal de casillas
		BigDecimal universoTotCasillas = new BigDecimal(0);
		// Variable para guardar el porcentaje de captura de actas
		BigDecimal porcentajeCaptura = new BigDecimal(0);
		// Variable para definir el porcentaje total de captura
		BigDecimal porcentajeTotal = new BigDecimal(100);
		// Variable que permite saber si la captura por municipio ya se
		// encuentra completa
		boolean isActasCompletas = false;
		/**
		 * Se manda a llamar el metodo para consultar las casillas por municipio
		 * del helper de casillas, que trae el universo total de casillas por
		 * distrito
		 */
		List<DTOCasillaWS> casillasMunicipio = bsd
				.casillasAprobadasPorDistritoLocal(
						usuario.getIdProcesoElectoral(),
						usuario.getIdEstadoSeleccionado(), idDistrito);
		// Se define el universo total de casillas, pasandole el
		// tamaño de la lista que se recupero de la consulta de casillas
		// aprobadas por distrito
		universoTotCasillas = (new BigDecimal(casillasMunicipio.size()));

		/**
		 * Se manda a llamar el metodo para consultar el conteo de cuantas actas
		 * hay sido capturadas por el tipo de Candidatura pasandole el universo
		 * total de casillas, y el tipo de Candidatura
		 */
		Object[] calculos = cargaCalculos(bsdActa, usuario,
				universoTotCasillas, idDistrito,
				usuario.getIdMunicipioSeleccionado(), idTipoCandidatura);
		// Se guarda el porcentaje de captura
		porcentajeCaptura = (BigDecimal) calculos[0];
		// Se comparara si el porcentaje de captura es igual al porcentaje de
		// 100%
		if (porcentajeCaptura.compareTo(porcentajeTotal) == 0) {
			// Si ya se cumplio el 100 % entonces se regresa
			isActasCompletas = true;
		} else {
			isActasCompletas = false;
		}

		return isActasCompletas;

	}
	
	
	/**
	 * Verifica que el municipio en el cual se encuentra el capturista municipal
	 * no tenga casillas especiales esto para poder generar el acta de
	 * Regidurias RP a nivel municipio pero tomando en sin información de votos,
	 * solo con los datos generales del acta
	 * 
	 * @return
	 */
	public boolean existenCasillasEspeciales(BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura, Integer idSeleccion)
			throws Exception {

		// Se define una variable de tipo boolean para almacenar el resultado
		// del método
		boolean existenEspeciales = true;
		// Se define una lista para almacenar las casillas de un municipio
		List<DTOCasillaWS> casillasMunicipio = new ArrayList<DTOCasillaWS>();
		// Se define una lista para almacenar las casillas especiales por
		// municipio
		List<DTOCasillaWS> casillasEspeciales = new ArrayList<DTOCasillaWS>();

		log.info("obtiene casillasMunicipio");
		// Se obtienen las casillas aprobadas por municipio
		if(idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)){
		casillasMunicipio = bsd.obtenSecCasillasAprobadasPorRegiduria(
				usuario.getIdProcesoElectoral(),
				usuario.getIdEstadoSeleccionado(),
				usuario.getIdMunicipioSeleccionado(), idSeleccion);

		}else{
			casillasMunicipio = bsd.casillasAprobadasPorDistritoLocal(
					usuario.getIdProcesoElectoral(),
					usuario.getIdEstadoSeleccionado(),
					idSeleccion);
		}
		// Filtro: Agregamos a la lista solo las casillas especiales
		if (casillasMunicipio != null && !casillasMunicipio.isEmpty()) {
			for (DTOCasillaWS ce : casillasMunicipio) {
				if (ce.getTipoCasilla().equalsIgnoreCase(
						Constantes.TIPO_CASILLA_ESPECIAL)) {

					casillasEspeciales.add(ce);
				}
			}
		}

		if (casillasEspeciales.size() == 0) {
			existenEspeciales = false;
		} else {
			existenEspeciales = true;
		}

		return existenEspeciales;
	}
	
	
	
	
	/**
	 * Verifica que el municipio en el cual se encuentra el capturista municipal
	 * no tenga casillas especiales esto para poder generar el acta de
	 * Regidurias RP a nivel municipio pero tomando en sin información de votos,
	 * solo con los datos generales del acta
	 * 
	 * @return
	 */
	public boolean existenCasillasEspecialesByMunicipio(BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {

		// Se define una variable de tipo boolean para almacenar el resultado
		// del método
		boolean existenEspeciales = true;
		// Se define una lista para almacenar las casillas de un municipio
		List<DTOCasillaWS> casillasMunicipio = new ArrayList<DTOCasillaWS>();
		// Se define una lista para almacenar las casillas especiales por
		// municipio
		List<DTOCasillaWS> casillasEspeciales = new ArrayList<DTOCasillaWS>();

		log.info("obtiene casillasMunicipio");
		// Se obtienen las casillas aprobadas por municipio
		casillasMunicipio = bsd.casillasAprobadasPorMunicipioLocal(
				usuario.getIdProcesoElectoral(),
				usuario.getIdEstadoSeleccionado(),
				usuario.getIdMunicipioSeleccionado());

		// Filtro: Agregamos a la lista solo las casillas especiales
		if (casillasMunicipio != null && !casillasMunicipio.isEmpty()) {
			for (DTOCasillaWS ce : casillasMunicipio) {
				if (ce.getTipoCasilla().equalsIgnoreCase(
						Constantes.TIPO_CASILLA_ESPECIAL)) {

					casillasEspeciales.add(ce);
				}
			}
		}

		if (casillasEspeciales.size() == 0) {
			existenEspeciales = false;
		} else {
			existenEspeciales = true;
		}

		return existenEspeciales;
	}
	
	


	/**
	 * Obtiene los distritos de la entidad del usuario y a su vez los municipios
	 * que pertenecen a ese distrito compara el idmunicipio seleccionado del
	 * usuario con el del servicio web, para mostrarle el distrito que
	 * corresponde al municipio en el combo de un capturista municipal, siempre
	 * y cuando el tipo de acta requiera consultar la captura de actas 100 de
	 * 100 y el resultado ya se encuentre en su totalidad
	 */
	public Map<String, String> getDistritosByCaptura100(
			BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {

		log.info("DISTRITOS DISTR, idProcesoElectoral "
				+ usuario.getIdProcesoElectoral());
		log.info("DISTRITOS DISTR, idDetalleProceso "
				+ usuario.getIdDetalleProceso());
		log.info("DISTRITOS DISTR, idEstadoSeleccionado "
				+ usuario.getIdEstadoSeleccionado());
		log.info("DISTRITOS DISTR, idMunicipioSeleccionado "
				+ usuario.getIdMunicipioSeleccionado());
		log.info("DISTRITOS DISTR, idTipoCandidatura " + idTipoCandidatura);

		List<DTOCasillaWS> casillasDistrito = new ArrayList<DTOCasillaWS>();

		// Se define el mapa para almacenar los distritos
		Map<String, String> distritos = new LinkedHashMap<String, String>();
		// Variable para guardar el universoTotal de casillas
		BigDecimal universoTotCasillas = new BigDecimal(0);
		// Variable para guardar el porcentaje de captura de actas
		BigDecimal porcentajeCaptura = new BigDecimal(0);
		// Variable para definir el porcentaje total de captura
		BigDecimal porcentajeTotal = new BigDecimal(100);
		// Se define una lista para almacenar las casillas especiales por
		// distrito
		List<DTOCasillaWS> casillasEspeciales = new ArrayList<DTOCasillaWS>();
		// Se define la lista para obtener los municipios del distrito que se le
		// define como parametro
		List<DTOMunicipiosWS> listMunicipios = new ArrayList<DTOMunicipiosWS>();
		// Se valida que la lista de distritos que se recupero del servicio no
		// este vacia
		log.info("obtiene casillasDistrito");
		casillasDistrito = bsd.casillasAprobadasPorMunicipioLocal(
				usuario.getIdProcesoElectoral(),
				usuario.getIdEstadoSeleccionado(),
				usuario.getIdMunicipioSeleccionado());

		// Filtro: Agregamos a la lista solo las casillas
		// S-Especias
		if (casillasDistrito != null && !casillasDistrito.isEmpty()) {
			for (DTOCasillaWS ce : casillasDistrito) {
				if (ce.getTipoCasilla().equalsIgnoreCase(
						Constantes.TIPO_CASILLA_ESPECIAL)) {

					casillasEspeciales.add(ce);
				}
			}
		}

		List<DTODistritosWS> temp = new ArrayList<DTODistritosWS>();
		for (DTOCasillaWS cEspecial : casillasEspeciales) {

			DTODistritosWS distrito = new DTODistritosWS();
			distrito.setIdDistrito(cEspecial.getIdDistritoLocal());
			distrito.setNombreDistrito(cEspecial.getCabeceraDistritoLocal());

			if (temp.size() == 0) {

				temp.add(distrito);
			} else {

				boolean existe = false;
				for (DTODistritosWS rTemp : temp) {
					if (rTemp.getIdDistrito().equals(distrito.getIdDistrito())) {
						existe = true;
						break;
					}
				}
				if (!existe)
					temp.add(distrito);
			}

		}

		log.info("Casilla especiales size: " + casillasEspeciales.size());

		/**
		 * Se manda a llamar el metodo para consultar las casillas por distrito
		 * del helper de geografico, que traer el universo total de casillas por
		 * distrito
		 */

		List<Integer> listaDistritosConsulta = bsdActas
				.consultaDistritosLocalesPorMunicipio(usuario);

		if (idTipoCandidatura == Constantes.ID_TIPO_CAND_DIPUTADO_MR) {

			List<DTODistritosWS> listDistritos = bsd.obtenerCatalogoDistritos(
					usuario.getIdEstadoSeleccionado(),
					EnumAmbitoDetalleProceso.L);
			// if (casillasDistrito != null && !casillasDistrito.isEmpty()) {
			// for (DTOCasillaWS cm : casillasDistrito) {
			for (DTODistritosWS cm : listDistritos) {
				for (int i = 0; i < listaDistritosConsulta.size(); i++) {

					if (cm.getIdDistrito()
							.equals(listaDistritosConsulta.get(i))) {

						// En caso contrario si es diputados RP se tomaran
						// como
						// el universo solo las casillas especiales

						universoTotCasillas = (new BigDecimal(
								casillasDistrito.size()));

						/**
						 * Se manda a llamar el metodo para consultar el conteo
						 * de cuantas actas hay sido capturadas por el tipo de
						 * Candidatura pasandole el universo total de casillas,
						 * el idDistrito y el tipo de Candidatura
						 */
						Object[] calculos = cargaCalculos(bsdActa, usuario,
								universoTotCasillas,
								listaDistritosConsulta.get(i),
								usuario.getIdMunicipioSeleccionado(),
								idTipoCandidatura);

						// Se guarda el porcentaje de captura
						porcentajeCaptura = (BigDecimal) calculos[0];
						// Se comparara si el porcentaje de captura es igual
						// al
						// porcentaje de 100%
						if (porcentajeCaptura.compareTo(porcentajeTotal) == 0) {
							// Si ya se cumplio el 100 % entonces se agrega
							// el
							// distrito al mapa distritos
							// Con el nombre de la cabecera distrital y el
							// idDistrito
							distritos.put(String.valueOf(cm.getIdDistrito()),
									cm.getNombreDistrito());
							break;
						}
					}
				}
				// }
				// }
			}

		}
		if (idTipoCandidatura == Constantes.ID_TIPO_CAND_DIPUTADO_RP) {
			if (temp != null && !temp.isEmpty()) {
				// Se itera la lista de municipios que se obtubo con cada
				// distrito de la lista de distritos
				for (DTODistritosWS it : temp) {

					// En caso contrario si es diputados RP se tomaran como
					// el universo solo las casillas especiales

					universoTotCasillas = (new BigDecimal(
							casillasEspeciales.size()));

					// Se define el universo total de casillas, pasandole el
					// tamaño de la lista que se recupero de la consulta de
					// casillas aprobadas por distrito
					// Si el tipo de candidatura es DiputadosMR se toman
					// todos los tipos de casilla

					/**
					 * Se manda a llamar el metodo para consultar el conteo de
					 * cuantas actas hay sido capturadas por el tipo de
					 * Candidatura pasandole el universo total de casillas, el
					 * idDistrito y el tipo de Candidatura
					 */
					Object[] calculos = cargaCalculos(bsdActa, usuario,
							universoTotCasillas, it.getIdDistrito(),
							usuario.getIdMunicipioSeleccionado(),
							idTipoCandidatura);

					// Se guarda el porcentaje de captura
					porcentajeCaptura = (BigDecimal) calculos[0];
					// Se comparara si el porcentaje de captura es igual al
					// porcentaje de 100%
					if (porcentajeCaptura.compareTo(porcentajeTotal) == 0) {
						// Si ya se cumplio el 100 % entonces se agrega el
						// distrito al mapa distritos
						// Con el nombre de la cabecera distrital y el
						// idDistrito
						distritos.put(String.valueOf(it.getIdDistrito()),
								it.getNombreDistrito());
					}

				}
			}
			
		}
		log.info("Casillas totales: " + casillasDistrito.size());
		log.info("Casilla especial: " + casillasEspeciales.size());	
		

		log.info("TAMAÑO DE DISTRITOS: " + distritos.size());
		return distritos;
	}

	/**
	 * Obtiene las demarcaciones del municipio del usuario siempre y cuando el
	 * tipo de acta requiera consultar la captura de actas 100 de 100 y el
	 * resultado ya se encuentre en su totalidad
	 */
	public Map<String, String> getDemarcacionesByCaptura100(
			BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura, Integer idSeleccion)
			throws Exception {

		// Se define el mapa para almacenar las demarcaciones
		Map<String, String> demarcaciones = new LinkedHashMap<String, String>();
		// Variable para guardar el universoTotal de casillas
		BigDecimal universoTotCasillas = new BigDecimal(0);
		// Variable para guardar el porcentaje de captura de actas
		BigDecimal porcentajeCaptura = new BigDecimal(0);
		// Variable para definir el porcentaje total de captura
		BigDecimal porcentajeTotal = new BigDecimal(100);
		// Se define una lista para almacenar las casillas especiales por
		// distrito
		List<DTOCasillaWS> casillasEspeciales = new ArrayList<DTOCasillaWS>();
		List<DTOCasillaWS> casillasMunicipio = new ArrayList<DTOCasillaWS>();
		// Se define la lista para obtener los municipios del distrito que se le
		// define como parametro
		/**
		 * Se manda a llamar el metodo para consultar las casillas por municipio
		 * del helper de geografico, que traer el universo total de casillas por
		 * municipio
		 */

		casillasMunicipio = bsd.obtenSecCasillasAprobadasPorRegiduria(
				usuario.getIdProcesoElectoral(),
				usuario.getIdEstadoSeleccionado(),
				usuario.getIdMunicipioSeleccionado(), null);

		// Filtro: Agregamos a la lista solo las casillas especiales
		if (casillasMunicipio != null && !casillasMunicipio.isEmpty()) {
			for (DTOCasillaWS ce : casillasMunicipio) {
				if (ce.getTipoCasilla().equalsIgnoreCase(
						Constantes.TIPO_CASILLA_ESPECIAL)) {
					casillasEspeciales.add(ce);
				}
			}
		}

		List<DTOListaRegiduriasWS> temp = new ArrayList<DTOListaRegiduriasWS>();
		for (DTOCasillaWS cEspecial : casillasEspeciales) {

			DTOListaRegiduriasWS reg = new DTOListaRegiduriasWS();
			reg.setIdRegiduria(cEspecial.getIdRegiduria());
			reg.setNombreRegiduria(cEspecial.getNombreRegiduria());

			if (temp.size() == 0) {

				temp.add(reg);
			} else {

				boolean existe = false;
				for (DTOListaRegiduriasWS rTemp : temp) {
					if (rTemp.getIdRegiduria().equals(reg.getIdRegiduria())) {
						existe = true;
						break;
					}
				}
				if (!existe)
					temp.add(reg);
			}

		}

		// Se valida que la lista de demarcaciones de geografico no sea nula o
		// venga vacia
		if (temp != null && !temp.isEmpty()) {
			// Se recorre la lista de Regidurias
			for (DTOListaRegiduriasWS it : temp) {

				// Si es regiduria RP se tomaran como
				// el universo solo las casillas especiales
				universoTotCasillas = (new BigDecimal(casillasEspeciales.size()));

				/**
				 * Se manda a llamar el metodo para consultar el conteo de
				 * cuantas actas hay sido capturadas por el tipo de Candidatura
				 * pasandole el universo total de casillas, el idRegiduria y el
				 * tipo de Candidatura
				 */
				Object[] calculos = cargaCalculosxDemarcacion(bsdActa, usuario,
						universoTotCasillas, it.getIdRegiduria(),
						idTipoCandidatura);

				// Se guarda el porcentaje de captura
				porcentajeCaptura = (BigDecimal) calculos[0];
				// Se comparara si el porcentaje de captura es igual al
				// porcentaje de 100%
				if (porcentajeCaptura.compareTo(porcentajeTotal) == 0) {
					// Si ya se cumplio el 100 % entonces se agrega el
					// distrito al mapa distritos
					// Con el nombre de la cabecera distrital y el
					// idDistrito
					demarcaciones.put(String.valueOf(it.getIdRegiduria()),
							it.getNombreRegiduria());
				}

			}
		}

		Map<String, String> demarcacionesFiltradas = new LinkedHashMap<String, String>();

		// TODO Traer las demarcaciones donde ya esten generadas las actas de MR
		List<Integer> listaDemarcacionesMR = bsdActas
				.consultaDemarcacionesPorMR(Constantes.ID_TIPO_CAND_REGIDURIA_MR, usuario, idSeleccion);

		log.info("TAMAÑO DE DEMARCACIONES en 1: " + listaDemarcacionesMR.size());
	if(listaDemarcacionesMR != null || listaDemarcacionesMR.isEmpty()){
		for (Integer dem : listaDemarcacionesMR) {
			for(Map.Entry<String, String> entry : demarcaciones.entrySet()){
				if(dem.equals(Integer.parseInt(entry.getKey()))){
					demarcacionesFiltradas.put(entry.getKey(), entry.getValue());
					break;
				}
			}
		}
	}

		log.info("TAMAÑO DE DEMARCACIONES en 2: " + demarcaciones.size());
		return demarcacionesFiltradas;
	}

	/**
	 * Obtiene los distritos que ya tienen una distribución final, tento para
	 * diputados MR como para Diputados RP
	 * 
	 * @param DTOUsuarioLogin
	 *            usuario
	 * @return Mapa que contiene el id y descripcion del distrito
	 */
	public Map<String, String> getDistritosByDist(
			BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario,
			Integer idTipoCandidatura) throws Exception {

		// Se define el mapa para almacenar los distritos
		Map<String, String> distritos = new LinkedHashMap<String, String>();
		String nomDistrito;
		// Se define la lista que contendra los distritos de acuerdo al
		// municipio que se le pase
		log.info("DISTRITOS DISTR, idProcesoElectoral "
				+ usuario.getIdProcesoElectoral());
		log.info("DISTRITOS DISTR, idDetalleProceso "
				+ usuario.getIdDetalleProceso());
		log.info("DISTRITOS DISTR, idEstadoSeleccionado "
				+ usuario.getIdEstadoSeleccionado());
		log.info("DISTRITOS DISTR, idMunicipioSeleccionado "
				+ usuario.getIdMunicipioSeleccionado());
		log.info("DISTRITOS DISTR, idTipoCandidatura " + idTipoCandidatura);
		List<Integer> lstDistritos = new ArrayList<Integer>();
		if (idTipoCandidatura == Constantes.ID_TIPO_CAND_DIPUTADO_RP) {
			lstDistritos = bsdDistrVotos.getDistritosWithDistribucionRP(
					usuario.getIdProcesoElectoral(),
					usuario.getIdDetalleProceso(),
					usuario.getIdEstadoSeleccionado(),
					usuario.getIdMunicipioSeleccionado(), idTipoCandidatura);
		} else {
			lstDistritos = bsdDistrVotos.getDistritosWithDistribucion(
					usuario.getIdProcesoElectoral(),
					usuario.getIdDetalleProceso(),
					usuario.getIdEstadoSeleccionado(),
					usuario.getIdMunicipioSeleccionado(), idTipoCandidatura);
		}
		// Se valida que la lista de distritos no sea nula o vacia
		if (lstDistritos != null && !lstDistritos.isEmpty()) {
			for (Integer id : lstDistritos) {
				nomDistrito = bsd.obtenerNombreDistrito(
						usuario.getIdEstadoSeleccionado(), id,
						EnumAmbitoDetalleProceso.L);
				distritos.put(String.valueOf(id), nomDistrito);
			}
		}
		log.info("Resultado de distritos: " + distritos.size());
		return distritos;
	}

	/**
	 * Obtiene las demarcaciones del municipio al que pertenece el usuario y que
	 * ya este la distribución final hecha a este nivel para poder mostrarse en
	 * el combo
	 * 
	 * @param DTOUsuarioLogin
	 *            usuario
	 * @return Mapa que contiene el id y descripcion de la demarcacion
	 */
	public Map<String, String> getDemarcacionByMun(
			BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario,
			Integer idTipoCandidatura) throws Exception {

		// Se define el mapa para almacenar las demarcaciones
		Map<String, String> demarcaciones = new LinkedHashMap<String, String>();
		// Se define la lista del objeto DTOListaRegiduriasWS
		log.info("DATOS PARA DEMARCACION, procesoElectoral: "
				+ usuario.getIdProcesoElectoral());
		log.info("DATOS PARA DEMARCACION, detalleProceso: "
				+ usuario.getIdDetalleProceso());
		log.info("DATOS PARA DEMARCACION, estado: "
				+ usuario.getIdEstadoSeleccionado());
		log.info("DATOS PARA DEMARCACION, municipio: "
				+ usuario.getIdMunicipioSeleccionado());
		log.info("DATOS PARA DEMARCACION, tipoCandidatura: "
				+ idTipoCandidatura);

		List<Integer> lstDemarcaciones = new ArrayList<Integer>();
		if (idTipoCandidatura == Constantes.ID_TIPO_CAND_REGIDURIA_MR) {
			lstDemarcaciones = bsdDistrVotos.getDemarcacionesWithDistribucion(
					usuario.getIdProcesoElectoral(),
					usuario.getIdDetalleProceso(),
					usuario.getIdEstadoSeleccionado(),
					usuario.getIdMunicipioSeleccionado(), idTipoCandidatura);
		} else {
			lstDemarcaciones = bsdDistrVotos
					.getDemarcacionesWithDistribucionRP(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado(),
							usuario.getIdMunicipioSeleccionado(),
							idTipoCandidatura);
		}
		List<DTOListaRegiduriasWS> listDemarcGeografico = bsd
				.consumeRegiduriasByMunicipio(
						usuario.getIdEstadoSeleccionado(),
						usuario.getIdMunicipioSeleccionado());
		// Se valida que la lista de demarcaciones no sea nula o venga vacia
		if (lstDemarcaciones != null && !lstDemarcaciones.isEmpty()) {
			for (Integer id : lstDemarcaciones) {
				for (DTOListaRegiduriasWS it : listDemarcGeografico) {
					// Si el id de la demarcacion coincide con el id de
					// demarcacion de la lista original, toma el id y el nombre
					// y se agregan al mapa resultante
					if (id.intValue() == it.getIdRegiduria()) {
						demarcaciones.put(String.valueOf(it.getIdRegiduria()),
								it.getNombreRegiduria());
					}
				}
			}
		}

		return demarcaciones;
	}

	/**
	 * Metodo que tiene la finalidad de comprobar si el total de actas parciales
	 * a nivel de todos los municipios ya se encuentran generadas para generar
	 * el acta de gobernador a nivel estatal
	 * 
	 * @param bsd
	 * @param bsdActa
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public boolean isTotalActasParcialesByEstado(
			BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {
		boolean isActasCompletas = false;
		// Se define la lista para obtener los municipios del estado y ambito de
		// proceso que se le define como parametro
		List<DTOMunicipiosWS> listMunicipios = new ArrayList<DTOMunicipiosWS>();
		// Se obtiene la lista del catalogo de municipios por entidad y ambito
		// de la elección
		listMunicipios = bsd.obtenerCatalogoMunicipios(
				usuario.getIdEstadoSeleccionado(), EnumAmbitoDetalleProceso.L);
		// Se asigna a la variable el tamaño de la lista de municipios que fue
		// recuperada del web service
		Integer totalMunicipiosWS = listMunicipios.size();
		// Se asigna el total de municipios que se recuperan de base de datos y
		// que tienen actas capturadas
		Integer totMuniCaptura = bsdActa.getTotalMunicipiosActasCapturadas(
				usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(),
				usuario.getIdEstadoSeleccionado(), idTipoCandidatura);
		// Se valida si el total de municipios que se recupero del servicio
		// es igual al total de municipios que tienen captura de actas para el
		// tipo de candidatura
		if (totalMunicipiosWS == totMuniCaptura) {
			// se asigna true cuando la cifra es igual
			isActasCompletas = true;
		} else {
			// se asigna false cuando la cifra no es igual
			isActasCompletas = false;
		}
		return isActasCompletas;
	}

	/**
	 * Método que tiene la finalidad de comprobar si las actas parciales a nivel
	 * municipio-distrito ya se encuentran generadas para poder generar el acta
	 * a nivel OPLE
	 * 
	 * @param bsd
	 * @param bsdActa
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public boolean isTotalActasParcialesByMunicipioDistrito(
			BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {
		// Se define la variable que permitira indicarnos si la captura de actas
		// a nivel distrito ya se completo
		boolean isActasCompletas = false;
		// Se define la lista para obtener los municipios y sus distritos y se
		// guardan en lista la combinación
		List<HLPMunicipiosDistritos> listMunDist = new ArrayList<HLPMunicipiosDistritos>();
		// Se inicializa el objeto de municipios distritos
		HLPMunicipiosDistritos hlpMunDist = new HLPMunicipiosDistritos();
		// Se define la lista para obtener los municipios del distrito que se le
		// define como parametro
		List<DTOMunicipiosWS> listMunicipios = new ArrayList<DTOMunicipiosWS>();
		// Se define la lista para obtener los municipios distritos que ya
		// tienen actas capturadas
		List<Object[]> lstActasCap = new ArrayList<Object[]>();
		// Se define la lista que contendra los distritos del estado del usuario
		// y en el ambito local
		List<DTODistritosWS> listDistritos = bsd.obtenerCatalogoDistritos(
				usuario.getIdEstadoSeleccionado(), EnumAmbitoDetalleProceso.L);

		// Se valida que la lista de distritos que se recupero del servicio no
		// este vacia para poder iterarla
		if (listDistritos != null && !listDistritos.isEmpty()) {

			// Se itera la lista y por cada distrito se obtiene su lista de
			// municipios
			for (DTODistritosWS it : listDistritos) {
				listMunicipios = bsd.obtenerMunicipiosPorDistritoLocal(
						usuario.getIdEstadoSeleccionado(), it.getIdDistrito());
				// Se itera la lista de municipios que se obtubo con cada
				// distrito de la lista de distritos
				for (DTOMunicipiosWS mun : listMunicipios) {
					// Se asignan los valores de distrito y municipio a la lista
					// que nos permitira validar
					// con los registros que se encuentran en la base
					hlpMunDist.setIdDistrito(it.getIdDistrito());
					hlpMunDist.setIdMunicipio(mun.getIdMunicipio());
					listMunDist.add(hlpMunDist);
				}
			}// fin del for de lista de municipios

			/*
			 * Se asigna el valor de la lista actas capturadas asignando el
			 * resultado del metodo que recupera la informacion de las actas,
			 * que han sido capturadas por municipio y distrito
			 */
			lstActasCap = bsdActa.consultaActasCaptByMunDistrito(
					usuario.getIdProcesoElectoral(),
					usuario.getIdDetalleProceso(),
					usuario.getIdEstadoSeleccionado(), idTipoCandidatura);
			// se valida si el tamaño de la la lista de municipios distritos
			// y el tamañode la lsita del total de actas capturadas por
			// municipio y distrito ya son iguales
			if (listMunDist.size() == lstActasCap.size()) {
				isActasCompletas = true;
			} else {
				isActasCompletas = false;
			}

		}

		return isActasCompletas;

	}

	/**
	 * Método que permite verificar si la distribución final ya esta hecha para
	 * los tipos de candidatura de Ayuntamiento (Captura Municipal) y Gobernador
	 * (OPLE)
	 * 
	 * @param bsd
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public boolean getVerificarDistribucionFinal(
			BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario,
			Integer idTipoCandidatura) throws Exception {
		// Se define la variable que permitira indicarnos si la captura de actas
		// a nivel distrito ya se completo
		boolean isDistribucionCompleta = false;
		// Si el tipo de candidatura es ayuntamiento, se verifica la
		// distribucion por municipio
		if (idTipoCandidatura == Constantes.ID_TIPO_CAND_AYUNTAMIENTO) {
			// Se define la lista que contendra los ayuntamientos
			List<Integer> lstAyuntamiento = bsdDistrVotos
					.getAyuntamientoWithDistribucion(
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado(),
							usuario.getIdMunicipioSeleccionado(),
							idTipoCandidatura);
			// Se valida que la lista de distritos no sea nula o vacia
			if (lstAyuntamiento != null && !lstAyuntamiento.isEmpty()) {
				isDistribucionCompleta = true;
			} else {
				isDistribucionCompleta = false;
			}
		}

		// Si el tipo de candidatura es gobernador, se verifica la distribución
		// por Gobernador
		if (idTipoCandidatura == Constantes.ID_TIPO_CAND_GOBERNADOR) {
			// Se define la lista que contendra el estado
			List<Integer> lstEstado = bsdDistrVotos.getEstadoWithDistribucion(
					usuario.getIdProcesoElectoral(),
					usuario.getIdDetalleProceso(),
					usuario.getIdEstadoSeleccionado(), idTipoCandidatura);
			// Se valida que la lista de distritos no sea nula o vacia
			if (lstEstado != null && !lstEstado.isEmpty()) {
				isDistribucionCompleta = true;
			} else {
				isDistribucionCompleta = false;
			}
		}

		return isDistribucionCompleta;

	}

	/**
	 * Método que tiene la finalidad de obtener el total de votos nulos o numero
	 * de votos de candidatos no registrados
	 * 
	 * @param bsdActa
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getTotalVotosNulosCNR(
			BSDInformacionGeneralActaInterface bsdActa, DTOActaCasillaVotosPK id)
			throws Exception {
		// Variable para guardar el numero de votos cnr o votos nulos
		BigDecimal totVotos = new BigDecimal(0);
		totVotos = bsdActa.getTotalVotosNulosCNR(id);

		return totVotos;
	}

	/**
	 * Genera la clave del candidato
	 * 
	 * @param idAsociacion
	 * @param tipoAsociacion
	 * @return
	 */
	public String getClaveCandidato(Integer idAsociacion, Integer tipoAsociacion) {
		return idAsociacion.toString().concat("-")
				.concat(tipoAsociacion.toString());
	}

	/**
	 * Obtiene el nombre emblema en base al tipo asociacion
	 * 
	 * @param emblema
	 * @param tipoAsociacion
	 * @return
	 */
	public String getEmblemaValido(String emblema, Integer tipoAsociacion) {
		if (emblema == null || emblema.isEmpty()) {
			return CONST_URL_IMG_GENERICA_PP;
		}
		if (emblema != null && !emblema.isEmpty() && tipoAsociacion == 1) {
			return limpiaRutaEmblema(emblema);
		} else {
			if (tipoAsociacion == 4) {
				return CONST_URL_IMG_GENERICA_CI;
			}
		}
		return null;
	}
	

	/**
	 * Limpia la ruta emblema quitando la primera diagonal
	 * 
	 * @param re
	 * @return
	 */
	public String limpiaRutaEmblema(String re) {

		if (re == null || re.isEmpty()) {
			return null;
		}
		char caracter = re.charAt(0);
		char caracter2 = re.charAt(1);

		if (String.valueOf(caracter).equalsIgnoreCase("/")
				&& String.valueOf(caracter2).equalsIgnoreCase("G")) {
			return re.substring(11);
		}
		return re;
	}

	
	
	
	/**
	 * Este métódo tiene la finalidad de mostrar los registros unicos de las asociaciones
	 * para ser mostrados en la vista de la captura de cada una de las generaciones de acta
	 * Carga Candidatos por el tipo de asociacion, 
	 * 
	 * @param bsd
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidatoWS> cargaCandidatosUnicosByCandidatura(List<DTOCandidatoWS> lstAsociaciones) {

		List<DTOCandidatoWS> asociaciones = new ArrayList<DTOCandidatoWS>();
		DTOCandidatoWS c = null;

		// TODO Validar que los candidatos del servicio no este nula o vacia
		if (lstAsociaciones != null && !lstAsociaciones.isEmpty()) {
			// TODO Agregar todos los candidatos del servicio a un lista de
			// objetos candidatos especializado
			for (DTOCandidatoWS it : lstAsociaciones) {
				// TODO Filtra candidatos por tipo asociacion partidos politicos y candidatos independientes
				//ya que esos se deben mostrar en ese apartado
				if (it.getTipoAsociacion() == 1 || it.getTipoAsociacion() == 4) {
					c = new DTOCandidatoWS();
					c.setIdEstado(it.getIdEstado());
					c.setCveCandidato(getClaveCandidato(it.getIdAsociacion(),
							it.getTipoAsociacion()));
					c.setIdAsociacion(it.getIdAsociacion());
					c.setTipoAsociacion(it.getTipoAsociacion());
					c.setIdTipoCandidatura(it.getIdTipoCandidatura());
					c.setNombreAsociacion(it.getNombreAsociacion());
					c.setNombreCandidato(it.getNombreCandidato());
					c.setEmblema(getEmblemaValido(it.getEmblema(),
							it.getTipoAsociacion()));
					c.setSiglas(it.getSiglas());
					c.setOrden(it.getOrden());
					asociaciones.add(c);
				}
			}
		}
		log.info("asociaciones size en el metodo de cargaCandidatosUnicosByCandidatura" + asociaciones.size());
		return filtraCandidatosByCandidatura(asociaciones);
	}
	
	
	/**
	 * Carga Candidatos por el tipo de asociacion
	 * 
	 * @param bsd
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidatoWS> cargaCandidatosRegiduriasMR(
			List<DTOCandidatoWS> lstAsociaciones) {

		List<DTOCandidatoWS> asociaciones = new ArrayList<DTOCandidatoWS>();
		DTOCandidatoWS c = null;

		// TODO Validar que los candidatos del servicio no este nula o vacia
		if (lstAsociaciones != null && !lstAsociaciones.isEmpty()) {
			// TODO Agregar todos los candidatos del servicio a un lista de
			// objetos candidatos especializado
			for (DTOCandidatoWS it : lstAsociaciones) {
				// TODO Filtra candidatos por tipo asociacion partidos politicos y candidatos independientes
				if (it.getTipoAsociacion() == 1 || it.getTipoAsociacion() == 4) {
					c = new DTOCandidatoWS();
					c.setIdEstado(it.getIdEstado());
					c.setCveCandidato(getClaveCandidato(it.getIdAsociacion(),
							it.getTipoAsociacion()));
					c.setIdAsociacion(it.getIdAsociacion());
					c.setTipoAsociacion(it.getTipoAsociacion());
					c.setIdTipoCandidatura(it.getIdTipoCandidatura());
					c.setNombreAsociacion(it.getNombreAsociacion());
					c.setNombreCandidato(it.getNombreCandidato());
					c.setEmblema(getEmblemaValido(it.getEmblema(),
							it.getTipoAsociacion()));
					c.setSiglas(it.getSiglas());
					c.setOrden(it.getOrden());
					asociaciones.add(c);
				}
			}
		}
		log.info("asociaciones size en el metodo de cargaCandidatosRegiduriasMR" + asociaciones.size());
		return filtraCandidatosRP(asociaciones);
	}
	
	/**
	 * Lista que permite filtrar los registros de los candidatos por partido
	 * politico para cuando se recupera mas de un registro del servicio web
	 * esto para no generar problemas en la vista en el apartado de partidos
	 * y candidatos independientes
	 * Carga Candidatos por el tipo de asociacion
	 * 
	 * @param bsd
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidatoWS> cargaCandidatos(
			List<DTOCandidatoWS> lstAsociaciones) {

		List<DTOCandidatoWS> asociaciones = new ArrayList<DTOCandidatoWS>();
		DTOCandidatoWS c = null;

		// TODO Validar que los candidatos del servicio no este nula o vacia
		if (lstAsociaciones != null && !lstAsociaciones.isEmpty()) {
			// TODO Agregar todos los candidatos del servicio a un lista de
			// objetos candidatos especializado
			for (DTOCandidatoWS it : lstAsociaciones) {
				// TODO Filtra candidatos por tipo asociacion pero solo partidos
				// politicos
				if (it.getTipoAsociacion() == 1) {
					c = new DTOCandidatoWS();
					c.setIdEstado(it.getIdEstado());
					c.setCveCandidato(getClaveCandidato(it.getIdAsociacion(),
							it.getTipoAsociacion()));
					c.setIdAsociacion(it.getIdAsociacion());
					c.setTipoAsociacion(it.getTipoAsociacion());
					c.setIdTipoCandidatura(it.getIdTipoCandidatura());
					c.setNombreAsociacion(it.getNombreAsociacion());
					c.setNombreCandidato(it.getNombreCandidato());
					c.setEmblema(getEmblemaValido(it.getEmblema(),
							it.getTipoAsociacion()));
					c.setSiglas(it.getSiglas());
					c.setOrden(it.getOrden());
					asociaciones.add(c);
				}
			}
		}
		log.info("asociaciones size " + asociaciones.size());
		return filtraCandidatosRP(asociaciones);
	}
	
	
	/**
	 * Obtiene una lista unica de objetos candidatows para el caso de
	 * cualquier tipo de candidatura
	 * 
	 * @param candidatos
	 * @return
	 */
	public List<DTOCandidatoWS> filtraCandidatosByCandidatura(
			List<DTOCandidatoWS> candidatos) {

		List<DTOCandidatoWS> candidatosCandidatura = new ArrayList<DTOCandidatoWS>();
		Map<String, DTOCandidatoWS> candidatosMap = new LinkedHashMap<String, DTOCandidatoWS>();
		StringBuilder sb = null;

		// TODO Crear la clave auxiliar del candidato para posteriormente
		// realizar el filtrado
		for (DTOCandidatoWS it : candidatos) {
			sb = new StringBuilder();
			sb.append(it.getIdAsociacion()).append("-")
					.append(it.getTipoAsociacion());
			it.setCveCandidato(sb.toString());
		}

		// TODO Realizar Filtrafo
		for (DTOCandidatoWS it : candidatos) {
			candidatosMap.put(it.getCveCandidato(), it);
		}

		// TODO Crear una lista de objeto candidato unica
		for (Entry<String, DTOCandidatoWS> m : candidatosMap.entrySet()) {
			candidatosCandidatura.add(m.getValue());
		}
		log.info("valor de candidatos por cada candidatura " + candidatosCandidatura.size());
		return candidatosCandidatura;
	}

	/**
	 * Obtiene una lista unica de objetos candidatows para el caso de
	 * Representacion Proporcional
	 * 
	 * @param candidatos
	 * @return
	 */
	public List<DTOCandidatoWS> filtraCandidatosRP(
			List<DTOCandidatoWS> candidatos) {

		List<DTOCandidatoWS> candidatosRP = new ArrayList<DTOCandidatoWS>();
		Map<String, DTOCandidatoWS> candidatosMap = new LinkedHashMap<String, DTOCandidatoWS>();
		StringBuilder sb = null;

		// TODO Crear la clave auxiliar del candidato para posteriormente
		// realizar el filtrado
		for (DTOCandidatoWS it : candidatos) {
			sb = new StringBuilder();
			sb.append(it.getIdAsociacion()).append("-")
					.append(it.getTipoAsociacion());
			it.setCveCandidato(sb.toString());
		}

		// TODO Realizar Filtrafo
		for (DTOCandidatoWS it : candidatos) {
			candidatosMap.put(it.getCveCandidato(), it);
		}

		// TODO Crear una lista de objeto candidato unica
		for (Entry<String, DTOCandidatoWS> m : candidatosMap.entrySet()) {
			candidatosRP.add(m.getValue());
		}
		log.info("valor de candidatos rp " + candidatosRP.size());
		return candidatosRP;
	}

	/**
	 * Verifica el estatus del modulo. </BR> true : El modulo esta abierto.
	 * </BR> false : El modulo esta cerrado.
	 * 
	 * @param idProceso
	 * @param idDetalle
	 * @param idSistema
	 * @param idEstado
	 * @param idMunicipio
	 * @param grupo
	 * @param idModulo
	 * @return
	 * @throws Exception
	 */
	public boolean validaModuloAbierto(Integer idProceso, Integer idDetalle,
			Integer idSistema, Integer idEstado, Integer idMunicipio,
			String grupo, Integer idModulo) throws Exception {
		EnumEstatusModulo estatusModulo = hlpAdministracion
				.obtenEstatusModuloPorMunicipio(idProceso, idDetalle,
						idSistema, idEstado, idMunicipio, grupo, idModulo);
		if (estatusModulo.equals(EnumEstatusModulo.A)) {
			return true;
		}
		return false;
	}

	/**
	 * Transforma un lista de objetos del tipo DTOActaCasillaVotos a una de tipo
	 * DTODistribucionTotParcial para guardar en las tablas parciales de
	 * distribución
	 * 
	 * @param votosActa
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionTotParcial> converterDTOActaToDistribucionTotParcial(
			List<HLPActaCasillaVotos> votosActa) throws Exception {

		List<DTODistribucionTotParcial> distribucion = null;
		DTODistribucionTotParcial c = null;
		DTODistribucionTotParcialPK id = null;

		if (votosActa != null && !votosActa.isEmpty()) {
			distribucion = new ArrayList<DTODistribucionTotParcial>();
			// TODO Agregar todos los registros del total de votos por partido
			// politico a una lista de distribucion total parcial
			for (HLPActaCasillaVotos it : votosActa) {

				// Se crea la instancia del objeto y se asignan los valores de
				// la pk de la tabla
				id = new DTODistribucionTotParcialPK();
				id.setIdProcesoElectoral(it.getIdProcesoElectoral());
				id.setIdDetalleProceso(it.getIdDetalleProceso());
				id.setIdEstado(it.getIdEstado());
				// Si el tipo de candidatura es diputado MR o diputado RP se
				// asigna el distrito tomado de la tabla acta casilla votos
				if (it.getIdTipoCandidatura() == Constantes.ID_TIPO_CAND_DIPUTADO_MR
						|| it.getIdTipoCandidatura() == Constantes.ID_TIPO_CAND_DIPUTADO_MR) {
					id.setIdDistrito(it.getIdDistrito());
				} else {
					id.setIdDistrito(Constantes.SIN_DISTRITO);
				}
				id.setIdMunicipio(it.getIdMunicipio());
				id.setIdRegiduria(Constantes.SIN_REGIDURIA);
				id.setSeccion(Constantes.SIN_SECCION);
				id.setIdComunidad(Constantes.SIN_COMUNIDAD);
				id.setIdTipoCandidatura(it.getIdTipoCandidatura());
				id.setIdAsociacion(it.getIdAsociacion());
				id.setIdCoalicion(it.getIdCoalicion());

				// Se asigna la pk y los demas valores
				c = new DTODistribucionTotParcial();
				c.setId(id);
				c.setTipoAsociacion(it.getTipoAsociacion());
				c.setOrden(it.getOrden());
				c.setVotos(it.getVotos());
				c.setEmblema(it.getEmblema());
				c.setFechaHora(new Date());
				c.setUsuario(it.getUsuario());

				distribucion.add(c);
			}
		}
		return distribucion;
	}

	/**
	 * Transforma un lista de objetos del tipo DTOActaCasillaVotos a una de tipo
	 * DTODistribucionTotParcial para guardar en las tablas parciales de
	 * distribución
	 * 
	 * @param votosActa
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionPartidosCI> converterDTOActaToDistribucionPPCI(
			List<HLPActaCasillaVotos> votosActa, Integer idTipoCandidatura) throws Exception {

		List<DTODistribucionPartidosCI> distribucion = null;
		DTODistribucionPartidosCI p = null;
		DTODistribucionPartidosCIPK id = null;

	   
		if (votosActa != null && !votosActa.isEmpty()) {
			distribucion = new ArrayList<DTODistribucionPartidosCI>();
			// TODO Agregar todos los registros del total de votos por partido
			// politico a una lista de distribucion total parcial
				if(idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)){
						for (HLPActaCasillaVotos it : votosActa) {
			
							// Se crea la instancia del objeto y se asignan los valores de
							// la pk de la tabla
							id = new DTODistribucionPartidosCIPK();
							id.setIdProcesoElectoral(it.getIdProcesoElectoral());
							id.setIdDetalleProceso(it.getIdDetalleProceso());
							id.setIdEstado(it.getIdEstado());
							id.setIdDistrito(Constantes.SIN_DISTRITO);
							id.setIdMunicipio(it.getIdMunicipio());
							id.setIdRegiduria(it.getIdRegiduria());
							id.setSeccion(Constantes.SIN_SECCION);
							id.setIdComunidad(Constantes.SIN_COMUNIDAD);
							id.setIdTipoCandidatura(it.getIdTipoCandidatura());
							id.setIdAsociacion(it.getIdAsociacion());
			
							// Se asigna la pk y los demas valores
							p = new DTODistribucionPartidosCI();
							p.setId(id);
							p.setTipoAsociacion(it.getTipoAsociacion());
							p.setOrden(it.getOrden());
							p.setVotos(it.getVotos());
							p.setEmblema(it.getEmblema());
							p.setFechaHora(new Date());
							p.setUsuario(it.getUsuario());
			
							distribucion.add(p);
						}
				 }
				 if(idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)){
					 for (HLPActaCasillaVotos it : votosActa) {
							
							// Se crea la instancia del objeto y se asignan los valores de
							// la pk de la tabla
							id = new DTODistribucionPartidosCIPK();
							id.setIdProcesoElectoral(it.getIdProcesoElectoral());
							id.setIdDetalleProceso(it.getIdDetalleProceso());
							id.setIdEstado(it.getIdEstado());
							id.setIdDistrito(it.getIdDistrito());
							id.setIdMunicipio(Constantes.SIN_MUNICIPIO);
							id.setIdRegiduria(Constantes.SIN_REGIDURIA);
							id.setSeccion(Constantes.SIN_SECCION);
							id.setIdComunidad(Constantes.SIN_COMUNIDAD);
							id.setIdTipoCandidatura(it.getIdTipoCandidatura());
							id.setIdAsociacion(it.getIdAsociacion());
			
							// Se asigna la pk y los demas valores
							p = new DTODistribucionPartidosCI();
							p.setId(id);
							p.setTipoAsociacion(it.getTipoAsociacion());
							p.setOrden(it.getOrden());
							p.setVotos(it.getVotos());
							p.setEmblema(it.getEmblema());
							p.setFechaHora(new Date());
							p.setUsuario(it.getUsuario());
			
							distribucion.add(p);
						}
				 }
		 
		 }
		return distribucion;
	}

	/**
	 * Transforma un lista de objetos del tipo DTOActaCasillaVotos a una de tipo
	 * DTODistribucionTotParcial para guardar en las tablas parciales de
	 * distribución
	 * 
	 * @param votosActa
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionPartidosCI> converterHLPDistribucionToDistribucionPPCI(
			List<HLPDistribucionVotos> votosActa, Integer tipoActa)
			throws Exception {

		List<DTODistribucionPartidosCI> distribucion = null;
		DTODistribucionPartidosCI p = null;
		DTODistribucionPartidosCIPK id = null;

		if (votosActa != null && !votosActa.isEmpty()) {
			distribucion = new ArrayList<DTODistribucionPartidosCI>();
			// TODO Agregar todos los registros del total de votos por partido
			// politico a una lista de distribucion total parcial
			for (HLPDistribucionVotos it : votosActa) {

				// Se crea la instancia del objeto y se asignan los valores de
				// la pk de la tabla
				id = new DTODistribucionPartidosCIPK();
				id.setIdProcesoElectoral(it.getIdProcesoElectoral());
				id.setIdDetalleProceso(it.getIdDetalleProceso());
				id.setIdEstado(it.getIdEstado());
				// Se realiza la validacion
				if (tipoActa == Constantes.TIPO_ACTA_FINAL) {

					id.setIdDistrito(it.getIdDistrito());
				} else {
					id.setIdDistrito(Constantes.SIN_DISTRITO);
				}
				id.setIdMunicipio(Constantes.SIN_MUNICIPIO);
				id.setIdRegiduria(Constantes.SIN_REGIDURIA);
				id.setSeccion(Constantes.SIN_SECCION);
				id.setIdComunidad(Constantes.SIN_COMUNIDAD);
				id.setIdTipoCandidatura(Constantes.ID_TIPO_CAND_DIPUTADO_RP);
				id.setIdAsociacion(it.getIdAsociacion());

				// Se asigna la pk y los demas valores
				p = new DTODistribucionPartidosCI();
				p.setId(id);
				p.setTipoAsociacion(it.getTipoAsociacion());
				p.setOrden(it.getOrden());
				p.setVotos(it.getVotos());
				p.setEmblema(it.getEmblema());
				p.setFechaHora(new Date());
				p.setUsuario(it.getUsuario());

				distribucion.add(p);
			}
		}
		return distribucion;
	}

	/**
	 * Transforma un lista de objetos del tipo DTOActaCasillaVotos a una de tipo
	 * DTODistribucionCandParcial para guardar en las tablas parciales de
	 * distribución
	 * 
	 * @param votosActa
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionCandParcial> converterDTOActaToDistribucionCandParcial(
			List<HLPActaCasillaVotos> votosActa) throws Exception {

		List<DTODistribucionCandParcial> distribucion = null;
		DTODistribucionCandParcial c = null;
		DTODistribucionCandParcialPK id = null;

		if (votosActa != null && !votosActa.isEmpty()) {
			distribucion = new ArrayList<DTODistribucionCandParcial>();
			// TODO Agregar todos los registros del total de votos por partido
			// politico a una lista de distribucion candidatos parcial
			for (HLPActaCasillaVotos it : votosActa) {

				// Se crea la instancia del objeto y se asignan los valores de
				// la pk de la tabla
				id = new DTODistribucionCandParcialPK();
				id.setIdProcesoElectoral(it.getIdProcesoElectoral());
				id.setIdDetalleProceso(it.getIdDetalleProceso());
				id.setIdEstado(it.getIdEstado());
				// Si el tipo de candidatura es diputado MR o diputado RP se
				// asigna el distrito tomado de la tabla acta casilla votos
				if (it.getIdTipoCandidatura() == Constantes.ID_TIPO_CAND_DIPUTADO_RP
						|| it.getIdTipoCandidatura() == Constantes.ID_TIPO_CAND_DIPUTADO_MR) {
					id.setIdDistrito(it.getIdDistrito());
				} else {
					id.setIdDistrito(Constantes.SIN_DISTRITO);
				}
				id.setIdMunicipio(it.getIdMunicipio());
				id.setIdRegiduria(Constantes.SIN_REGIDURIA);
				id.setSeccion(Constantes.SIN_SECCION);
				id.setIdComunidad(Constantes.SIN_COMUNIDAD);
				id.setIdTipoCandidatura(it.getIdTipoCandidatura());
				id.setIdAsociacion(it.getIdAsociacion());
				id.setIdCoalicion(it.getIdCoalicion());

				// Se asigna la pk y los demas valores
				c = new DTODistribucionCandParcial();
				c.setId(id);
				c.setTipoAsociacion(it.getTipoAsociacion());
				c.setOrden(it.getOrden());
				c.setVotos(it.getVotos());
				c.setEmblema(it.getEmblema());
				c.setFechaHora(new Date());
				c.setUsuario(it.getUsuario());

				distribucion.add(c);
			}
		}
		return distribucion;
	}

	/**
	 * Transforma un lista de objetos del tipo DTODistribucionTotParcial a una
	 * de tipo DTODistribucionCandParcial para guardar en las tablas parciales
	 * de distribución
	 * 
	 * @param votosActa
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionCandParcial> converterDTODistTotalToDistribucionCandParcial(
			List<DTODistribucionTotParcial> votosActa) throws Exception {

		List<DTODistribucionCandParcial> distribucion = null;
		DTODistribucionCandParcial c = null;
		DTODistribucionCandParcialPK id = null;

		if (votosActa != null && !votosActa.isEmpty()) {
			distribucion = new ArrayList<DTODistribucionCandParcial>();
			// TODO Agregar todos los registros del total de votos por partido
			// politico a una lista de distribucion candidatos parcial
			for (DTODistribucionTotParcial it : votosActa) {

				// Se crea la instancia del objeto y se asignan los valores de
				// la pk de la tabla
				id = new DTODistribucionCandParcialPK();
				id.setIdProcesoElectoral(it.getId().getIdProcesoElectoral());
				id.setIdDetalleProceso(it.getId().getIdDetalleProceso());
				id.setIdEstado(it.getId().getIdEstado());
				// Se valida el tipo de candidatura por si se requiere asignar
				// el valor del distrito
				if (it.getId().getIdTipoCandidatura() == Constantes.ID_TIPO_CAND_GOBERNADOR) {
					id.setIdDistrito(Constantes.SIN_DISTRITO);
				} else {
					id.setIdDistrito(it.getId().getIdDistrito());
				}

				id.setIdMunicipio(it.getId().getIdMunicipio());
				id.setIdRegiduria(Constantes.SIN_REGIDURIA);
				id.setSeccion(Constantes.SIN_SECCION);
				id.setIdComunidad(Constantes.SIN_COMUNIDAD);
				id.setIdTipoCandidatura(it.getId().getIdTipoCandidatura());
				id.setIdAsociacion(it.getId().getIdAsociacion());
				id.setIdCoalicion(it.getId().getIdCoalicion());

				// Se asigna la pk y los demas valores
				c = new DTODistribucionCandParcial();
				c.setId(id);
				c.setTipoAsociacion(it.getTipoAsociacion());
				c.setOrden(it.getOrden());
				c.setVotos(it.getVotos());
				c.setEmblema(it.getEmblema());
				c.setFechaHora(new Date());
				c.setUsuario(it.getUsuario());

				distribucion.add(c);
			}
		}
		return distribucion;
	}

	/**
	 * Método que permite guardar la distribución final del regiduria rp en este
	 * caso como es rp solo es por partidos politicos, entonces se guarda la
	 * distribucion final por regiduria de acuerdo a si se tienen casillas especiales
	 * se tienen casillas especiales
	 * @param bsdInfo
	 * @param bsdDistribucion
	 * @param usuario
	 * @param idTipoCandidatura
	 * @param emblemas
	 * @param idRegiduria
	 * @return
	 * @throws Exception
	 */
	public boolean guardarDistribucionFinalRegidurias(
			BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdInfo,
			BSDDistribucionVotosInterface bsdDistribucion,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura,
			List<HLPRepresentante> emblemas, Integer idRegiduria,
			boolean isCasillaEspecial)
			throws Exception {
		
		
			log.info("Entró a guardarDistribucionFinalRegidurias()");
		
		try {		
			//Si No tiene casilla especial la regiduria entonces se toman los votos de mr de esa regiduria y se guardan
			//pero con el tipo de candidatura de regidurias rp y los datos de la pk correspondientes
			if(!isCasillaEspecial){
				List<DTODistribucionPartidosCI> listDistPPCI = new ArrayList<DTODistribucionPartidosCI>();
				List<HLPActaCasillaVotos> totalVotos = bsdInfo.getTotalVotosRegiduriaMR(new Object[] {
																	usuario.getIdProcesoElectoral(),
																	usuario.getIdDetalleProceso(),
																	usuario.getIdEstadoSeleccionado(),
																	usuario.getIdMunicipioSeleccionado(),
																	idRegiduria});
				
				
				if(totalVotos != null || !totalVotos.isEmpty()){
					// Se itera la lista de partidos politicos con sus votos
					for (HLPActaCasillaVotos it : totalVotos) {
						// se asigna el usuario que realizara la operacion de guardado
						it.setIdTipoCandidatura(idTipoCandidatura);
						it.setUsuario(usuario.getUsername());
					}
				
					/****
					 * Se realiza la asignación de los datos de Acta casilla votos a las
					 * tablas de distribución parcial
					 ****/
					// Una vez que ya se asignarón todos los valores necesarios se hace
					// la conversión a un List<DTODistribucionPartidosCI>
					listDistPPCI = converterDTOActaToDistribucionPPCI(totalVotos, idTipoCandidatura);
		
					/*
					 * Se manda a llamar el método para guardar en la tabla de
					 * distribución de partidos y candidatos
					 */
					bsdDistribucion.guardarDistribucionPartidosCI(listDistPPCI);
				}
			}else{
				//Si si tiene casilla especial se realiza la suma de Regidurias MR y Regidurias RP y ese concentrado se guarda
				List<DTODistribucionPartidosCI> listDistPPCI = new ArrayList<DTODistribucionPartidosCI>();
				// Si el tipo de candidatura es Regiduria RP
				
					List<HLPActaCasillaVotos> totalVotos = bsdInfo
							.getTotalVotosRegiduriaRP(new Object[] {
									usuario.getIdProcesoElectoral(),
									usuario.getIdDetalleProceso(),
									usuario.getIdEstadoSeleccionado(),
									usuario.getIdMunicipioSeleccionado(), idRegiduria,
									idTipoCandidatura });
		
				if(totalVotos != null || !totalVotos.isEmpty()){
					// Se itera la lista de partidos politicos con sus votos
					for (HLPActaCasillaVotos it : totalVotos) {
						// Se itera la lista de representantes para obtener los emblemas
						// y asignarlos a la lista de partidos con sus votos
						for (HLPRepresentante e : emblemas) {
							// Si el idAsociacion y el tipoAsociacion coincide en ambas
							// listas se toma el emblema y se asigna a la asociacion
							if (it.getIdAsociacion() == e.getIdAsociacion()
									&& it.getTipoAsociacion() == e.getTipoAsociacion()) {
								it.setEmblema(e.getEmblemaAsociacion());
		
							}
							// Se valida si el idAsociacion es Candidatos no Registrados
							if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG
									&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG) {
								it.setEmblema(Constantes.EMBLEMA_CNREG);
		
							}
		
							// Se valida si el idAsociacion es votos nulos se asigna un
							// valor estatico al valor del emblema
							if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS
									&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS) {
								it.setEmblema(Constantes.EMBLEMA_NULOS);
							}
		
						}
						// se asigna el usuario que realizara la operacion de guardado
						it.setIdTipoCandidatura(idTipoCandidatura);
						it.setUsuario(usuario.getUsername());
					}
					/****
					 * Se realiza la asignación de los datos de Acta casilla votos a las
					 * tablas de distribución parcial
					 ****/
					// Una vez que ya se asignarón todos los valores necesarios se hace
					// la conversión a un List<DTODistribucionPartidosCI>
					listDistPPCI = converterDTOActaToDistribucionPPCI(totalVotos, idTipoCandidatura);
		
					/*
					 * Se manda a llamar el método para guardar en la tabla de
					 * distribución de partidos y candidatos
					 */
					bsdDistribucion.guardarDistribucionPartidosCI(listDistPPCI);
			  }
		   }
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Método que permite guardar la distribucion Parcial para el tipo de
	 * candidatura de Diputados RP, que en este caso se va sobre casillas
	 * especiales y solo guarda partidos politicos, votos nulos y candidatos no
	 * registrados Adicionalmente para guardar la distribución parcial para el
	 * tipo de candidatura de diputados MR
	 * 
	 * @param bsdInfo
	 * @param bsdDistribucion
	 * @param usuario
	 * @param idTipoCandidatura
	 * @param emblemas
	 * @param idDistrito
	 * @return
	 * @throws Exception
	 */
	public boolean guardarDistribucionParcialDiputados(
			BSDInformacionGeneralActaInterface bsdInfo,
			BSDDistribucionVotosInterface bsdDistribucion,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura,
			List<HLPRepresentante> emblemas, Integer idDistrito)
			throws Exception {
		log.info("Entró a guardarDistribucionParcialDiputados()");
		List<DTODistribucionTotParcial> listDistTotal = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionCandParcial> listDistCand = new ArrayList<DTODistribucionCandParcial>();
		List<DTODistribucionCandParcial> distribucionParcial = new ArrayList<DTODistribucionCandParcial>();
		List<DTODistribucionTotParcial> listDistTotalOrdenada = new ArrayList<DTODistribucionTotParcial>();
		// Lista para guardar las coaliciones que tuvieron votos en ese tipo de
		// candidatura
		List<Integer> coaliciones = new ArrayList<Integer>();
		// Lista para guardar el detalle de las subcoaliciones para traer las
		// asociaciones que forman parte de una coalicion
		List<Integer> partidos = new ArrayList<Integer>();
		List<Object[]> partidosCoaliciones = new ArrayList<Object[]>();
		// Lista para guardar los partidos que no tienen una coalicion
		List<Integer> partidosSinCoaliciones = new ArrayList<Integer>();
		// Se obtiene la informacion del total de votos por partido politico
		// para el tipo de candidatura de Diputados RP
		try {
			// Si el tipo de candidatura es Diputado RP
			if (idTipoCandidatura == Constantes.ID_TIPO_CAND_DIPUTADO_RP) {
				List<HLPActaCasillaVotos> totalVotos = bsdInfo
						.getTotalVotosActaDiputados(new Object[] {
								usuario.getIdProcesoElectoral(),
								usuario.getIdDetalleProceso(),
								usuario.getIdEstadoSeleccionado(), idDistrito,
								usuario.getIdMunicipioSeleccionado(),
								idTipoCandidatura });

				// Se itera la lista de partidos politicos con sus votos
				for (HLPActaCasillaVotos it : totalVotos) {
					// Se itera la lista de representantes para obtener los
					// emblemas y asignarlos a la lista de partidos con sus
					// votos
					for (HLPRepresentante e : emblemas) {
						// Si el idAsociacion y el tipoAsociacion coincide en
						// ambas listas se toma el emblema y se asigna a la
						// asociacion
						if (it.getIdAsociacion() == e.getIdAsociacion()
								&& it.getTipoAsociacion() == e
										.getTipoAsociacion()) {
							it.setEmblema(e.getEmblemaAsociacion());

						}
						// Se valida si el idAsociacion es Candidatos no
						// Registrados
						if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG) {
							it.setEmblema(Constantes.EMBLEMA_CNREG);

						}

						
						// Se valida si el idAsociacion es votos nulos se asigna
						// un valor estatico al valor del emblema
						if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS) {
							it.setEmblema(Constantes.EMBLEMA_NULOS);
						}
						
						/*if(it.getEmblema() == null){
							it.setEmblema("emblema.jpg");
						}*/
							

					}
					// se asigna el usuario que realizara la operacion de
					// guardado
					it.setUsuario(usuario.getUsername());
				}
				/****
				 * Se realiza la asignación de los datos de Acta casilla votos a
				 * las tablas de distribución parcial
				 ****/
				// Una vez que ya se asignarón todos los valores necesarios se
				// hace la conversión a un List<DTODistribucionTotParcial>
				listDistTotal = converterDTOActaToDistribucionTotParcial(totalVotos);

				// Se hace otra conversión a un List<DTODistribucionCandParcial>
				listDistCand = converterDTOActaToDistribucionCandParcial(totalVotos);

				/*
				 * Se manda a llamar el método para guardar en la tabla de
				 * distribucion candidatos parcial, TODO al ser los mismos datos
				 * es decir partidos politicos y no haber coaliciones, solo debe
				 * guargar en la tabla de distribucioncandParcial
				 */
				// bsdDistribucion.guardarDistribucionTotalParcial(listDistTotal);
			 try{
				//Si es diputados RP solo guarda en la tabla de distribucion Cand Parcial
				bsdDistribucion.guardarDistribucionCandParcial(listDistCand);
			 }catch (Exception e) {
					log.error(e);
					log.error("Error guardando distribucion parcial para diputados mr");
					e.printStackTrace();
			 }
		  }

			// Si el tipo de candidatura es Diputados MR
			if (idTipoCandidatura == Constantes.ID_TIPO_CAND_DIPUTADO_MR) {
				// Se obtiene la informacion del total de votos por partido
				// politico para el tipo de candidatura Gobernador
				List<HLPActaCasillaVotos> totalVotos = null;
				try {
					totalVotos = bsdInfo
							.getTotalVotosActaDiputados(new Object[] {
									usuario.getIdProcesoElectoral(),
									usuario.getIdDetalleProceso(),
									usuario.getIdEstadoSeleccionado(),
									idDistrito,
									usuario.getIdMunicipioSeleccionado(),
									idTipoCandidatura });

				} catch (Exception e) {
					log.error(e);
					log.error("Error al obtene votos de acta diputados.");
					e.printStackTrace();
					return false;
				}

				try {
					// Se obtienen las coaliciones con votos registrados por el
					// tipo
					// de candidatura
					coaliciones = bsdDistribucion.getCoaliciones(
							new Object[] { usuario.getIdProcesoElectoral(),
									usuario.getIdDetalleProceso(),
									usuario.getIdEstadoSeleccionado(),
									usuario.getIdMunicipioSeleccionado(),
									idTipoCandidatura }, idDistrito);

				} catch (Exception e) {
					log.error(e);
					log.error("Error al obtenr coaliciones.");
					e.printStackTrace();
					return false;
				}

				if (coaliciones != null && !coaliciones.isEmpty()) {

					List<DTOCSubcoaliciones> listEmblemasSub = null;

					try {
						// Se obtiene la informacion de las subcoaliciones de
						// las
						// coaliciones con votos registrados, y obtenemos sus
						// emblemas
						listEmblemasSub = bsdDistribucion
								.getSubcoaliciones(coaliciones);

					} catch (Exception e) {
						log.error(e);
						log.error("Error al obtenr subcoalciones.");
						e.printStackTrace();
						return false;
					}

					// Se itera la lista de asociaciones que se recupero de acta
					// casilla votos con sus votos
					for (HLPActaCasillaVotos it : totalVotos) {
						log.info("Nombre completo de candidato en it: "
								+ it.getIdAsociacion()
								+ " tipo_asociacion en it "
								+ it.getTipoAsociacion());
						// Se itera la lista de representantes para obtener los
						// emblemas y asignarlos a la lista de partidos con sus
						// votos
						for (HLPRepresentante e : emblemas) {

							log.info("Nombre completo de candidato en e: "
									+ e.getIdAsociacion()
									+ " tipo_asociacion en e "
									+ e.getTipoAsociacion());
							/*************************************************
							 * ASIGNACIÓN DE EMBLEMAS A LOS REGISTROS DE LA
							 * DISTRIBUCIÓN
							 ***********************************************/
							// Si el idAsociacion es partido politico y el
							// tipoAsociacion coincide en ambas listas se toma
							// el
							// emblema y se asigna a la asociacion
							if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_PARTIDO
									&& it.getIdAsociacion() == e
											.getIdAsociacion()) {
								it.setEmblema(e.getEmblemaAsociacion());
							}
							// Si el idAsociacion es un candidatos independiente
							// se
							// le asignan sus siglas al emblema
							if (it.getTipoAsociacion()
									.equals(Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE)
									&& it.getIdAsociacion().equals(
											e.getIdAsociacion())) {
								// it.setEmblema(e.getNombreCompleto());
								log.info("Nombre completo de candidato: "
										+ e.getNombreCompleto() + "longitud: "
										+ e.getNombreCompleto().length());
								it.setEmblema(e.getEmblemaAsociacion());
							}

							// if(it.getEmblema() == null &&
							// it.getTipoAsociacion()
							// ==
							// Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE){
							// it.setEmblema(e.getEmblemaAsociacion());
							// }

							// Si el idAsociacion es coalicion y el id
							// asociacion
							// coincide con el de subcoalicion y la coalicion es
							// igual en ambas listas se asigna el emblema de
							// subcoaliciones
							for (DTOCSubcoaliciones sub : listEmblemasSub) {
								// Se asignan los emblemas de las subcoaliciones
								// hijas
								if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_COALICIONES
										&& it.getIdAsociacion() == sub
												.getIdSubcoalicion()
										&& it.getIdCoalicion() == sub
												.getIdCoalicion()) {
									it.setEmblema(sub.getEmblema());
								}
								// Se asignan los emblemas de las coaliciones
								// papas
								if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_COALICIONES
										&& it.getIdAsociacion() == sub
												.getIdSubcoalicion()) {
									it.setEmblema(sub.getEmblema());
								}

							}

						}// Cierre del for de representantes

						// Se valida si el idAsociacion es Candidatos no
						// Registrados
						if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG) {
							it.setEmblema(Constantes.EMBLEMA_CNREG);

						}

						// Se valida si el idAsociacion es votos nulos se asigna
						// un
						// valor estatico al valor del emblema
						if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS) {
							it.setEmblema(Constantes.EMBLEMA_NULOS);
						}

						if (it.getEmblema() == null) {
							it.setEmblema("CI");
						}

						// se asigna el usuario que realizara la operacion de
						// guardado
						it.setUsuario(usuario.getUsername());

					}// cierre del primer for

					// Una vez que ya se asignarón todos los valores necesarios
					// se
					// hace la conversión a un List<DTODistribucionTotParcial>
					listDistTotal = converterDTOActaToDistribucionTotParcial(totalVotos);

					/*********************************
					 * PROCEDIMIENTO PARA ASIGNACIÓN DE VOTOS Y GUARDADO EN
					 * TABLAS DE DISTRIBUCIÓN CANDIDATOS PARCIAL
					 *******************************/
					/**
					 * 1.- Para el guardado de la distribucion parcial
					 * candidatos, se guardan las coaliciones y a los votos que
					 * ya tenian asignados, se le suman los votos de las
					 * subcoaliaciones y de los partidos que forman parte de la
					 * coalición para dar una suma completa. 2.- Adicionalmente
					 * se guardan los partidos no coaligados, es decir los
					 * partidos que no tienen coalicion con sus votos obtenidos
					 * 3.- Se guardan los candidatos independientes con sus
					 * votos obtenidos 4.- Se guardan los candidatos no
					 * Registrados con sus votos obtenidos 5.- Se guardan los
					 * votos nulos con sus votos obtenidos
					 **/

					try {
						/***
						 * Se obtienen os partidos que forman parte de una
						 * coalición, pasandole la lista de coaliciones que tuvo
						 * votos en el tipo de candidatura
						 ***/
						partidosCoaliciones = bsdDistribucion
								.getPartidosCoalicion(coaliciones);

					} catch (Exception e) {
						log.error(e);
						log.error("Error al obtenr partidos con coalicion.");
						e.printStackTrace();
						return false;
					}

					for (Object[] o : partidosCoaliciones) {
						partidos.add((Integer) o[0]);
					}

					try {
						partidosSinCoaliciones = bsdDistribucion
								.getPartidosSinCoalicion(partidos);

					} catch (Exception e) {
						log.error(e);
						log.error("Error al obtener partidos sin coalicion.");
						e.printStackTrace();
						return false;
					}

					try {
						/**
						 * Se asigna a la lista de Distribucion Candidatos
						 * Parcial para enviar a guardar la informacion en las
						 * tablas de distribución
						 **/
						listDistCand = generarDistribucionCandParcial(
								listDistTotal, coaliciones,
								partidosCoaliciones, partidosSinCoaliciones);

					} catch (Exception e) {
						log.error(e);
						log.error("Error al generar la distribución.");
						e.printStackTrace();
						return false;
					}

					/**
					 * Se asigna a la lista de Distribucion Candidatos Parcial
					 * para enviar a guardar la informacion en las tablas de
					 * distribución
					 **/
					/*
					 * listDistCand =
					 * generarDistribucionCandParcial(listDistTotal,
					 * coaliciones, partidosCoaliciones,
					 * partidosSinCoaliciones);
					 */

					/*
					 * Se manda a llamar el método para guardar en la tabla de
					 * distribución total parcial y en la tabla de distribucion
					 * candidatos parcial
					 */
					try {
						bsdDistribucion
								.guardarDistribucionTotalParcial(listDistTotal);

					} catch (Exception e) {
						log.error(e);
						log.error("Error guardando distribucion total");
						e.printStackTrace();
						return false;
					}
					try {
						bsdDistribucion
								.guardarDistribucionCandParcial(listDistCand);

					} catch (Exception e) {
						log.error(e);
						log.error("Error guardando distribucion parcial");
						e.printStackTrace();
						return false;
					}

					// *******************************************************************************************************************
					// **** NO HAY COALICIONES *****
				} else {

					try {
						totalVotos = bsdInfo
								.getTotalVotosActaDiputados(new Object[] {
										usuario.getIdProcesoElectoral(),
										usuario.getIdDetalleProceso(),
										usuario.getIdEstadoSeleccionado(),
										idDistrito,
										usuario.getIdMunicipioSeleccionado(),
										idTipoCandidatura });

					} catch (Exception e) {
						log.error(e);
						log.error("Error al obtene votos de acta diputados.");
						e.printStackTrace();
						return false;
					}

					// Se itera la lista de asociaciones que se recupero de acta
					// casilla votos con sus votos
					for (HLPActaCasillaVotos it : totalVotos) {
						log.info("Nombre completo de candidato en it: "
								+ it.getIdAsociacion()
								+ " tipo_asociacion en it "
								+ it.getTipoAsociacion());
						// Se itera la lista de representantes para obtener los
						// emblemas y asignarlos a la lista de partidos con sus
						// votos
						for (HLPRepresentante e : emblemas) {

							log.info("Nombre completo de candidato en e: "
									+ e.getIdAsociacion()
									+ " tipo_asociacion en e "
									+ e.getTipoAsociacion());
							/*************************************************
							 * ASIGNACIÓN DE EMBLEMAS A LOS REGISTROS DE LA
							 * DISTRIBUCIÓN
							 ***********************************************/
							// Si el idAsociacion es partido politico y el
							// tipoAsociacion coincide en ambas listas se toma
							// el
							// emblema y se asigna a la asociacion
							if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_PARTIDO
									&& it.getIdAsociacion() == e
											.getIdAsociacion()) {
								it.setEmblema(e.getEmblemaAsociacion());
							}
							// Si el idAsociacion es un candidatos independiente
							// se
							// le asignan sus siglas al emblema
							if (it.getTipoAsociacion()
									.equals(Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE)
									&& it.getIdAsociacion().equals(
											e.getIdAsociacion())) {
								// it.setEmblema(e.getNombreCompleto());
								log.info("Nombre completo de candidato: "
										+ e.getNombreCompleto() + "longitud: "
										+ e.getNombreCompleto().length());
								it.setEmblema(e.getEmblemaAsociacion());
							}

						}// Cierre del for de representantes

						// Se valida si el idAsociacion es Candidatos no
						// Registrados
						if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG) {
							it.setEmblema(Constantes.EMBLEMA_CNREG);

						}

						// Se valida si el idAsociacion es votos nulos se asigna
						// un
						// valor estatico al valor del emblema
						if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS) {
							it.setEmblema(Constantes.EMBLEMA_NULOS);
						}

						if (it.getEmblema() == null) {
							it.setEmblema("CI");
						}

						// se asigna el usuario que realizara la operacion de
						// guardado
						it.setUsuario(usuario.getUsername());

					}// cierre del primer for

					// Una vez que ya se asignarón todos los valores necesarios
					// se
					// hace la conversión a un List<DTODistribucionTotParcial>
					listDistTotal = converterDTOActaToDistribucionTotParcial(totalVotos);

					/*********************************
					 * PROCEDIMIENTO PARA ASIGNACIÓN DE VOTOS Y GUARDADO EN
					 * TABLAS DE DISTRIBUCIÓN CANDIDATOS PARCIAL
					 *******************************/
					try {
						/**
						 * Se asigna a la lista de Distribucion Candidatos
						 * Parcial para enviar a guardar la informacion en las
						 * tablas de distribución
						 **/
						listDistCand = converterDTODistTotalToDistribucionCandParcial(listDistTotal);

					} catch (Exception e) {
						log.error(e);
						log.error("Error al generar la distribución.");
						e.printStackTrace();
						return false;
					}

					/*
					 * Se manda a llamar el método para guardar en la tabla de
					 * distribución total parcial y en la tabla de distribucion
					 * candidatos parcial
					 */
					try {
						bsdDistribucion
								.guardarDistribucionTotalParcial(listDistTotal);

					} catch (Exception e) {
						log.error(e);
						log.error("Error guardando distribucion total");
						e.printStackTrace();
						return false;
					}
					try {
						bsdDistribucion
								.guardarDistribucionCandParcial(listDistCand);

					} catch (Exception e) {
						log.error(e);
						log.error("Error guardando distribucion parcial");
						e.printStackTrace();
						return false;
					}

				}

			}
			return true;
		} catch (Exception e) {
			log.error(e);
			log.error("Error inesperado.");
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * Método que permite guardar la distribución Parcial de Gobernador, guarda
	 * en las tablas de DTODistribucionTotParcial y DTODistribucionCandParcial,
	 * pero aqui si se contemplan todos los tipos de asociacion como: partidos
	 * politicos, coaliciones y candidatos independientes
	 * 
	 * @param bsdInfo
	 * @param bsdDistribucion
	 * @param usuario
	 * @param idTipoCandidatura
	 * @param emblemas
	 * @return
	 * @throws Exception
	 */
	public boolean guardaDistribucionParcialGobernador(
			BSDInformacionGeneralActaInterface bsdInfo,
			BSDDistribucionVotosInterface bsdDistribucion,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura,
			List<HLPRepresentante> emblemas) throws Exception {
		log.info("Entró a guardaDistribucionParcialGobernador()");
		try {
			// Lista para el guardado de la distribución total parcial
			List<DTODistribucionTotParcial> listDistTotal = new ArrayList<DTODistribucionTotParcial>();
			// Lista para el gaurdado de la distribución candidadatos parcial
			List<DTODistribucionCandParcial> listDistCand = new ArrayList<DTODistribucionCandParcial>();
			// Lista para guardar las coaliciones que tuvieron votos en ese tipo
			// de candidatura
			List<Integer> coaliciones = new ArrayList<Integer>();
			// Lista para guardar el detalle de las subcoaliciones para traer
			// las asociaciones que forman parte de una coalicion
			List<Integer> partidos = new ArrayList<Integer>();
			List<Object[]> partidosCoaliciones = new ArrayList<Object[]>();
			// Lista para guardar los partidos que no tienen una coalicion
			List<Integer> partidosSinCoaliciones = new ArrayList<Integer>();
			Integer idDistrito = null;
			// Se obtiene la informacion del total de votos por partido politico
			// para el tipo de candidatura Gobernador
			List<HLPActaCasillaVotos> totalVotos = bsdInfo
					.getTotalVotosActaGobernador(new Object[] {
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado(),
							usuario.getIdMunicipioSeleccionado(),
							idTipoCandidatura });

			// Se obtienen las coaliciones con votos registrados por el tipo de
			// candidatura
			coaliciones = bsdDistribucion.getCoaliciones(
					new Object[] { usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado(),
							usuario.getIdMunicipioSeleccionado(),
							idTipoCandidatura }, idDistrito);

			// Se obtiene la informacion de las subcoaliciones de las
			// coaliciones con votos registrados, y obtenemos sus emblemas
			List<DTOCSubcoaliciones> listEmblemasSub = bsdDistribucion
					.getSubcoaliciones(coaliciones);

			// Se itera la lista de asociaciones que se recupero de acta casilla
			// votos con sus votos
			for (HLPActaCasillaVotos it : totalVotos) {
				// Se itera la lista de representantes para obtener los emblemas
				// y asignarlos a la lista de partidos con sus votos
				for (HLPRepresentante e : emblemas) {

					log.info("HLPRepresentante, idAsociacion: "
							+ it.getIdAsociacion() + ", emblema: "
							+ e.getEmblemaAsociacion());

					/*************************************************
					 * ASIGNACIÓN DE EMBLEMAS A LOS REGISTROS DE LA DISTRIBUCIÓN
					 ***********************************************/
					// Si el idAsociacion es partido politico y el
					// tipoAsociacion coincide en ambas listas se toma el
					// emblema y se asigna a la asociacion
					if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_PARTIDO
							&& it.getIdAsociacion() == e.getIdAsociacion()
							&& it.getTipoAsociacion() == e.getTipoAsociacion()) {
						it.setEmblema(e.getEmblemaAsociacion());
					}
					// Si el idAsociacion es un candidatos independiente se le
					// asignan sus siglas al emblema
					if (it.getTipoAsociacion().equals(
							Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE)
							&& it.getIdAsociacion().equals(e.getIdAsociacion())) {
						it.setEmblema(e.getEmblemaAsociacion());
					}

					// Si el idAsociacion es coalicion y el id asociacion
					// coincide con el de subcoalicion y la coalicion es igual
					// en ambas listas se asigna el emblema de subcoaliciones
					for (DTOCSubcoaliciones sub : listEmblemasSub) {

						log.info("DTOCSubcoaliciones, idAsociacion: "
								+ it.getIdAsociacion() + ", emblema: "
								+ sub.getEmblema());
						// Se asignan los emblemas de las subcoaliciones hijas
						if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_COALICIONES
								&& it.getIdAsociacion() == sub
										.getIdSubcoalicion()
								&& it.getIdCoalicion() == sub.getIdCoalicion()) {
							it.setEmblema(sub.getEmblema());
						}
						// Se asignan los emblemas de las coaliciones papas
						if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_COALICIONES
								&& it.getIdAsociacion() == sub
										.getIdSubcoalicion()) {
							it.setEmblema(sub.getEmblema());
						}

					}

				}// Cierre del for de representantes

				// Se valida si el idAsociacion es Candidatos no Registrados
				if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG
						&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG) {
					it.setEmblema(Constantes.EMBLEMA_CNREG);

				}

				// Se valida si el idAsociacion es votos nulos se asigna un
				// valor estatico al valor del emblema
				if (it.getIdAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS
						&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS) {
					it.setEmblema(Constantes.EMBLEMA_NULOS);
				}

				if (it.getEmblema() == null) {
					it.setEmblema("CI");
				}

				// se asigna el usuario que realizara la operacion de guardado
				it.setUsuario(usuario.getUsername());

			}// cierre del primer for

			// Una vez que ya se asignarón todos los valores necesarios se hace
			// la conversión a un List<DTODistribucionTotParcial>
			listDistTotal = converterDTOActaToDistribucionTotParcial(totalVotos);

			/*********************************
			 * PROCEDIMIENTO PARA ASIGNACIÓN DE VOTOS Y GUARDADO EN TABLAS DE
			 * DISTRIBUCIÓN CANDIDATOS PARCIAL
			 *******************************/
			/**
			 * 1.- Para el guardado de la distribucion parcial candidatos, se
			 * guardan las coaliciones y a los votos que ya tenian asignados, se
			 * le suman los votos de las subcoaliaciones y de los partidos que
			 * forman parte de la coalición para dar una suma completa. 2.-
			 * Adicionalmente se guardan los partidos no coaligados, es decir
			 * los partidos que no tienen coalicion con sus votos obtenidos 3.-
			 * Se guardan los candidatos independientes con sus votos obtenidos
			 * 4.- Se guardan los candidatos no Registrados con sus votos
			 * obtenidos 5.- Se guardan los votos nulos con sus votos obtenidos
			 **/

			/***
			 * Se obtienen os partidos que forman parte de una coalición,
			 * pasandole la lista de coaliciones que tuvo votos en el tipo de
			 * candidatura
			 ***/
			partidosCoaliciones = bsdDistribucion
					.getPartidosCoalicion(coaliciones);

			for (Object[] o : partidosCoaliciones) {
				partidos.add((Integer) o[0]);
			}

			partidosSinCoaliciones = bsdDistribucion
					.getPartidosSinCoalicion(partidos);

			/**
			 * Se asigna a la lista de Distribucion Candidatos Parcial para
			 * enviar a guardar la informacion en las tablas de distribución
			 **/
			listDistCand = generarDistribucionCandParcial(listDistTotal,
					coaliciones, partidosCoaliciones, partidosSinCoaliciones);

			/*
			 * Se manda a llamar el método para guardar en la tabla de
			 * distribución total parcial y en la tabla de distribucion
			 * candidatos parcial
			 */
			bsdDistribucion.guardarDistribucionTotalParcial(listDistTotal);
			bsdDistribucion.guardarDistribucionCandParcial(listDistCand);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que permite ordenar la lista de distribucion de los candidatos que
	 * participan en una eleccion
	 * 
	 * @param listaCompleta
	 * @return
	 */
	public List<DTODistribucionCandParcial> ordenaListaDistCandParcial(
			List<DTODistribucionCandParcial> listaCompleta) {
		return null;
	}

	/***
	 * Método que ordena las coaliciones para ser mostradas en el acta pdf se le
	 * pasa la lista de coaliciones
	 * 
	 * @param coaliciones
	 * @return
	 */
	public List<DTODistribucionTotales> ordenaCoalicionesTotalFinal(
			List<DTODistribucionTotales> listaCompleta) {

		List<DTODistribucionTotales> listaPartidos = new ArrayList<DTODistribucionTotales>();
		List<DTODistribucionTotales> listaCoaliciones = new ArrayList<DTODistribucionTotales>();
		List<DTODistribucionTotales> listaSubcoaliciones = new ArrayList<DTODistribucionTotales>();
		List<DTODistribucionTotales> listaCI = new ArrayList<DTODistribucionTotales>();
		List<DTODistribucionTotales> listaFinal = new ArrayList<DTODistribucionTotales>();
		List<DTODistribucionTotales> listaOrdenada = new ArrayList<DTODistribucionTotales>();

		for (DTODistribucionTotales it : listaCompleta) {

			if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_COALICIONES)
					&& it.getId().getIdAsociacion() > 0) {
				listaCoaliciones.add(it);
			}

			else if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_COALICIONES)
					&& it.getId().getIdAsociacion() < 0) {
				listaSubcoaliciones.add(it);
			}

			else if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_PARTIDO)
					&& it.getId().getIdAsociacion() > 0) {
				listaPartidos.add(it);
			}

			else if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE)) {
				listaCI.add(it);
			}

		}

		for (DTODistribucionTotales cp : listaCoaliciones) {

			if (!listaOrdenada.contains(cp)) {
				listaOrdenada.add(cp);
			}

			for (DTODistribucionTotales sub : listaSubcoaliciones) {

				if (cp.getId().getIdAsociacion()
						.equals(sub.getId().getIdCoalicion())) {

					if (!listaOrdenada.contains(sub)) {
						listaOrdenada.add(sub);
					}
				}
			}

		}

		listaFinal.addAll(listaPartidos);
		listaFinal.addAll(listaOrdenada);
		listaFinal.addAll(listaCI);

		return listaFinal;
	}

	/***
	 * Método que ordena las coaliciones para ser mostradas en el acta pdf se le
	 * pasa la lista de coaliciones
	 * 
	 * @param coaliciones
	 * @return
	 */
	public List<DTODistribucionTotParcial> ordenaCoaliciones(
			List<DTODistribucionTotParcial> listaCompleta) {

		List<DTODistribucionTotParcial> listaPartidos = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionTotParcial> listaCoaliciones = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionTotParcial> listaSubcoaliciones = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionTotParcial> listaCI = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionTotParcial> listaFinal = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionTotParcial> listaOrdenada = new ArrayList<DTODistribucionTotParcial>();

		for (DTODistribucionTotParcial it : listaCompleta) {

			if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_COALICIONES)
					&& it.getId().getIdAsociacion() > 0) {
				listaCoaliciones.add(it);
			}

			else if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_COALICIONES)
					&& it.getId().getIdAsociacion() < 0) {
				listaSubcoaliciones.add(it);
			}

			else if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_PARTIDO)
					&& it.getId().getIdAsociacion() > 0) {
				listaPartidos.add(it);
			}

			else if (it.getTipoAsociacion().equals(
					Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE)) {
				listaCI.add(it);
			}

		}

		for (DTODistribucionTotParcial cp : listaCoaliciones) {

			if (!listaOrdenada.contains(cp)) {
				listaOrdenada.add(cp);
			}

			for (DTODistribucionTotParcial sub : listaSubcoaliciones) {

				if (cp.getId().getIdAsociacion()
						.equals(sub.getId().getIdCoalicion())) {

					if (!listaOrdenada.contains(sub)) {
						listaOrdenada.add(sub);
					}
				}
			}

		}

		listaFinal.addAll(listaPartidos);
		listaFinal.addAll(listaOrdenada);
		listaFinal.addAll(listaCI);

		return listaFinal;
	}

	/**
	 * Método que permite generar la distribución parcial de la tabla de
	 * candidatos
	 * 
	 * @param listDistTotal
	 * @param coaliciones
	 * @param partidosCoaliciones
	 * @return
	 */
	public List<DTODistribucionCandParcial> generarDistribucionCandParcial(
			List<DTODistribucionTotParcial> listDistTotal,
			List<Integer> coaliciones, List<Object[]> partidosCoaliciones,
			List<Integer> partidosSinCoalicion) {
		Map<Integer, List<DTODistribucionTotParcial>> coalicion = null;
		Map<Integer, DTODistribucionTotParcial> candidatosIndependientes = new LinkedHashMap<Integer, DTODistribucionTotParcial>();
		Map<Integer, DTODistribucionTotParcial> partidosNoCoaligados = new LinkedHashMap<Integer, DTODistribucionTotParcial>();
		DTODistribucionTotParcial cnreg = new DTODistribucionTotParcial();
		DTODistribucionTotParcial votosNulos = new DTODistribucionTotParcial();
		List<DTODistribucionTotParcial> distribucionCandParcial = new ArrayList<DTODistribucionTotParcial>();
		List<DTODistribucionCandParcial> distribucionParcial = new ArrayList<DTODistribucionCandParcial>();
		List<DTODistribucionTotParcial> coalicionCompleta = new ArrayList<DTODistribucionTotParcial>();

		try {
			log.info("valor de coaliciones " + coaliciones.size()
					+ " valor de partidoscoaliciones "
					+ partidosCoaliciones.size());
			/**
			 * Se itera la lista de la distribución total la cual ya tiene toda
			 * la información recuperada de la tabla acta casilla votos, tanto
			 * para partidos, candidatos independientes y coaliciones
			 */
			for (DTODistribucionTotParcial it : listDistTotal) {
				List<DTODistribucionTotParcial> lstCoalicion = new ArrayList<DTODistribucionTotParcial>();

				// Se itera la lista de coaliciones
				for (Integer c : coaliciones) {

					// Se valida si la coalicion de la lista de coaliciones es
					// igual al idcoalicion de la coalicion padre y su tipo de
					// asociacion es coalicion
					if (it.getId().getIdAsociacion().equals(c.intValue())
							&& it.getTipoAsociacion().equals(
									Constantes.TIPO_ASOCIACION_COALICIONES)) {
						// Si no se aguega a una lista y la lista se arega al
						// mapa con la llave unica
						it.getId().setIdCoalicion(c);
						coalicionCompleta.add(it);
						break;

					}

					// si la coalición de la lista de coaliciones es igual al
					// idCoalicion de la lista de subcoaliciones, se agrega al
					// mapa
					if (it.getId().getIdCoalicion().equals(c.intValue())
							&& it.getTipoAsociacion().equals(
									Constantes.TIPO_ASOCIACION_COALICIONES)) {
						// Se agrega el registro con el identificador de la
						// coalicion y su registro correspondiente
						it.getId().setIdCoalicion(c);
						coalicionCompleta.add(it);
						break;

					}

					for (Object[] o : partidosCoaliciones) {
						// si la asociacion de la lista inicial es igual al
						// idAsociacion de la lista de partidoscoaliciones y
						// ademas es partido politico se agrega el registro al
						// mapa
						if (it.getId().getIdAsociacion()
								.equals(((Integer) o[0]).intValue())
								&& c.intValue() == ((Integer) o[1]).intValue()
								&& it.getTipoAsociacion().equals(
										Constantes.TIPO_ASOCIACION_PARTIDO)) {
							it.getId().setIdCoalicion(c);
							coalicionCompleta.add(it);

						}

					}

					for (Integer ps : partidosSinCoalicion) {
						// si la asociacion de la lista inicial es diferente a
						// la de la lista de partidos coaligados y ademas es
						// partido politico se agrega el registro al mapa de
						// partidos no coaligados
						if (it.getId().getIdAsociacion() == ps.intValue()
								&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_PARTIDO) {
							partidosNoCoaligados.put(it.getId()
									.getIdAsociacion(), it);

						}
					}

					/**
					 * Si el tipo asociacion es candidato independiente se
					 * agregan a la lista de CandidatoInd para recorrerla y
					 * asignar los valores a un mapa con el identificador unico
					 * del idAsociacion del candidato independiente
					 */
					if (it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE) {
						// Se verifica si el mapa contiene el id de la
						// asociacion que lo identifica como candidato
						// independiente
						candidatosIndependientes.put(it.getId()
								.getIdAsociacion(), it);

					}

					/**
					 * Se valida si el idAsociacion es Candidatos no Registrados
					 * y el tipo asociacion
					 **/
					if (it.getId().getIdAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG
							&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_CAND_NO_REG) {
						cnreg = it;

					}

					/**
					 * Se valida si el idAsociacion es votos nulos se asigna un
					 * valor estatico al valor del emblema
					 **/
					if (it.getId().getIdAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS
							&& it.getTipoAsociacion() == Constantes.TIPO_ASOCIACION_VOTOS_NULOS) {
						votosNulos = it;
					}

				}
			}

			Integer coalicion1 = 49;
			Integer coalicion2 = 50;
			DTODistribucionTotParcial dist = null;
			DTODistribucionTotParcialPK pk = null;
			Integer votosTotalesCoalicion = 0;

			for (Integer c : coaliciones) {
				votosTotalesCoalicion = 0;
				DTOCSubcoaliciones emblemaCoalicion = bsdDistrVotos
						.getEmblemaCoalicion(c);
				for (DTODistribucionTotParcial it : coalicionCompleta) {

					// se hace la suma de los votos y se guarda en la variable
					// votosTotalesCoalicion votos

					if (it.getId().getIdCoalicion() == c) {

						if (it.getId().getIdAsociacion().equals(c)) {
							log.info("valor de idcoalicion "
									+ it.getId().getIdAsociacion());
						}
						votosTotalesCoalicion += it.getVotos();
						// Se aplica magia de Jean
						pk = new DTODistribucionTotParcialPK();
						pk.setIdProcesoElectoral(it.getId()
								.getIdProcesoElectoral());
						pk.setIdDetalleProceso(it.getId().getIdDetalleProceso());
						pk.setIdEstado(it.getId().getIdEstado());
						pk.setIdDistrito(it.getId().getIdDistrito());
						pk.setIdMunicipio(it.getId().getIdMunicipio());
						pk.setIdRegiduria(it.getId().getIdRegiduria());
						pk.setSeccion(it.getId().getSeccion());
						pk.setIdComunidad(it.getId().getIdComunidad());
						pk.setIdTipoCandidatura(it.getId()
								.getIdTipoCandidatura());
						pk.setIdAsociacion(c);
						pk.setIdCoalicion(Constantes.SIN_COALICION);
						// Se aplica magia de Jean
						dist = new DTODistribucionTotParcial();
						dist.setId(pk);
						dist.setUsuario(it.getUsuario());
						dist.setVotos(votosTotalesCoalicion);
					}

				}

				dist.setTipoAsociacion(Constantes.TIPO_ASOCIACION_COALICIONES);
				if (c.intValue() == coalicion1) {
					dist.setOrden(1);
				}
				if (c.intValue() == coalicion2) {
					dist.setOrden(2);
				}

				dist.setEmblema(emblemaCoalicion.getEmblema());
				distribucionCandParcial.add(dist);
			}

			/** Se itera el mapa de los partidosNoCoaligados **/
			for (Map.Entry<Integer, DTODistribucionTotParcial> p : partidosNoCoaligados
					.entrySet()) {
				DTODistribucionTotParcial partido = new DTODistribucionTotParcial();
				Integer votosTotalesPartidos = 0;
				votosTotalesPartidos = p.getValue().getVotos();
				partido.setId(p.getValue().getId());
				partido.setTipoAsociacion(p.getValue().getTipoAsociacion());
				partido.setOrden(p.getValue().getOrden());
				partido.setVotos(votosTotalesPartidos);
				partido.setEmblema(p.getValue().getEmblema());
				partido.setUsuario(p.getValue().getUsuario());
				distribucionCandParcial.add(partido);
			}

			/** Se itera el mapa de los candidatosIndependientes **/
			for (Map.Entry<Integer, DTODistribucionTotParcial> c : candidatosIndependientes
					.entrySet()) {
				DTODistribucionTotParcial candidato = new DTODistribucionTotParcial();
				Integer VotosCandidatosInd = 0;
				VotosCandidatosInd = c.getValue().getVotos();
				candidato.setId(c.getValue().getId());
				candidato.setTipoAsociacion(c.getValue().getTipoAsociacion());
				candidato.setOrden(c.getValue().getOrden());
				candidato.setVotos(VotosCandidatosInd);
				candidato.setEmblema(c.getValue().getEmblema());
				candidato.setUsuario(c.getValue().getUsuario());
				distribucionCandParcial.add(candidato);
			}

			/** Se agrega el objeto candidatos no registrados a la lista **/
			distribucionCandParcial.add(cnreg);

			/** Se agrega el objeto de votos nulos a la lista **/
			distribucionCandParcial.add(votosNulos);

			distribucionParcial = converterDTODistTotalToDistribucionCandParcial(distribucionCandParcial);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return distribucionParcial;

	}

	/**
	 * Método que permite guardar la distribución final de diputados rp, a nivel
	 * estatal, en este caso como es rp solo se guarda la distribución por
	 * partidos politicos y en este caso de acuerdo al requerimiento del usuario
	 * es la suma de las distribuciones de diputados mr y diputados rp
	 * 
	 * @param bsdInfo
	 * @param bsdDistribucion
	 * @param usuario
	 * @param idTipoCandidatura
	 * @param emblemas
	 * @param idRegiduria
	 * @return
	 * @throws Exception
	 */
	public boolean guardarDistribucionxEstadoDiputadosRP(
			BSDInformacionGeneralActaInterface bsdInfo,
			BSDDistribucionVotosInterface bsdDistribucion,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura, Integer tipoActa)
			throws Exception {

		log.info("Entró a guardarDistribucionxEstadoDiputadosRP()");
		List<DTODistribucionPartidosCI> listDistPPCI = null;
		// Si el tipo de candidatura es Diputados RP y el acta a generar es a
		// nivel estatal
		try {
			List<HLPDistribucionVotos> totalVotos = bsdDistribucion
					.getTotalVotosPPCIMRyRPFinal(new Object[] {
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado() });

			// Se itera la lista de partidos politicos con sus votos
			for (HLPDistribucionVotos it : totalVotos) {
				// se asigna el usuario que realizara la operacion de guardado
				it.setUsuario(usuario.getUsername());
			}
			/****
			 * Se realiza la asignación de los datos de la tabla de distribucion
			 * partidos a la tablas de distribución final de partidos
			 ****/
			// Una vez que ya se asignarón todos los valores necesarios se hace
			// la conversión a un List<DTODistribucionPartidosCI>
			listDistPPCI = new ArrayList<DTODistribucionPartidosCI>();
			listDistPPCI = converterHLPDistribucionToDistribucionPPCI(
					totalVotos, tipoActa);

			/*
			 * Se manda a llamar el método para guardar en la tabla de
			 * distribución de partidos y candidatos
			 */
			bsdDistribucion.guardarDistribucionPartidosCI(listDistPPCI);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Método que permite guardar la distribución final de diputados rp, a nivel
	 * distrito, en este caso como es rp solo se guarda la distribución por
	 * partidos politicos y esto se hace cuando se termina de generar el acta
	 * 
	 * @param bsdInfo
	 * @param bsdDistribucion
	 * @param usuario
	 * @param idTipoCandidatura
	 * @param idDistrito
	 * @return
	 * @throws Exception
	 */
	public boolean guardarDistribucionxDistritoDiputadosRP(
			BSDInformacionGeneralActaInterface bsdInfo,
			BSDDistribucionVotosInterface bsdDistribucion,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura,
			Integer idDistrito, Integer tipoActa, boolean isCasillaEspecial) throws Exception {

		log.info("Entró a guardarDistribucionxDistritoDiputadosRP");
		
		try {		
			//Si No tiene casilla especial el distrito entonces se toman los votos de mr de ese distrito y se guardan
			//pero con el tipo de candidatura de diputados rp y los datos de la pk correspondientes
			if(!isCasillaEspecial){
				List<DTODistribucionPartidosCI> listDistPPCI = new ArrayList<DTODistribucionPartidosCI>();
				List<HLPActaCasillaVotos> totalVotos = bsdInfo.getTotalVotosDiputadosMR(new Object[] {
																	usuario.getIdProcesoElectoral(),
																	usuario.getIdDetalleProceso(),
																	usuario.getIdEstadoSeleccionado(),
																	usuario.getIdMunicipioSeleccionado(),
																	idDistrito});
				
				
				if(totalVotos != null || !totalVotos.isEmpty()){
					// Se itera la lista de partidos politicos con sus votos
					for (HLPActaCasillaVotos it : totalVotos) {
						// se asigna el usuario que realizara la operacion de guardado y el tipo de candidatura
						it.setIdTipoCandidatura(idTipoCandidatura);
						it.setUsuario(usuario.getUsername());
					}
				
					/****
					 * Se realiza la asignación de los datos de Acta casilla votos a las
					 * tablas de distribucion partidos y candidatos
					 ****/
					// Una vez que ya se asignarón todos los valores necesarios se hace
					// la conversión a un List<DTODistribucionPartidosCI>
					listDistPPCI = converterDTOActaToDistribucionPPCI(totalVotos, idTipoCandidatura);
		
					/*
					 * Se manda a llamar el método para guardar en la tabla de
					 * distribución de partidos y candidatos
					 */
					bsdDistribucion.guardarDistribucionPartidosCI(listDistPPCI);
				}
			}else{
				//Si si tiene casilla especial se realiza la suma de Regidurias MR y Regidurias RP y ese concentrado se guarda
				// Se inicializa la lista de distribucion Partidos Politicos y
				// Candidatos Independientes
				List<DTODistribucionPartidosCI> listDistPPCI = null;
				// Si el tipo de candidatura es Diputados RP y el acta a generada es a
				// nivel distrito
				// Obtiene la distribucion parcial concentradra por distrito y tipo de
				// candidatura
		
				List<HLPDistribucionVotos> distribucionPP = bsdDistribucion
					.getDistribucionParcialByDistritoRP(new Object[] {
							usuario.getIdProcesoElectoral(),
							usuario.getIdDetalleProceso(),
							usuario.getIdEstadoSeleccionado(), idDistrito,
							idTipoCandidatura });

				if(distribucionPP != null || !distribucionPP.isEmpty()){
					// Se itera la lista de partidos politicos con sus votos
					for (HLPDistribucionVotos it : distribucionPP) {
						// se asigna el usuario que realizara la operacion de guardado
						it.setUsuario(usuario.getUsername());
					}
					/****
					 * Se realiza la asignación de los datos de la tabla de distribucion
					 * partidos parcial a la tabla de distribución final de partidos
					 ****/
					// Una vez que ya se asignarón todos los valores necesarios se hace
					// la conversión a un List<DTODistribucionPartidosCI>
					listDistPPCI = new ArrayList<DTODistribucionPartidosCI>();
					listDistPPCI = converterHLPDistribucionToDistribucionPPCI(
							distribucionPP, tipoActa);
	
				/*
				 * Se manda a llamar el método para guardar en la tabla de
				 * distribución de partidos y candidatos
				 */
				try{
				bsdDistribucion.guardarDistribucionPartidosCI(listDistPPCI);
				}catch(Exception e){
					e.printStackTrace();
					log.error("error al guardar la distribucion en la tabla de partidos ci");
				}
		    }
		 }//cIERRE DEL ELSE
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Método que tiene la finalidad de comprobar si las distribuciones finales
	 * a nivel de Diputados MR ya estan generadas , esto para poder generar el
	 * acta de Diputados RP a nivel estatal
	 * 
	 * @param bsd
	 * @param bsdActa
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public boolean isDistribucionCompletaDiputadosMR(
			BSDCargaInformacionInterface bsd,
			BSDInformacionGeneralActaInterface bsdActa,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {
		// Se define la variable que permitira indicarnos si la distribución
		// final
		// a nivel de tipo de candidatura de diputados mr ya se completo
		boolean isDistribucionCompleta = false;
		// Se define la lista que contendra los distritos del estado del usuario
		// y en el ambito local
		List<DTODistritosWS> listDistritos = bsd.obtenerCatalogoDistritos(
				usuario.getIdEstadoSeleccionado(), EnumAmbitoDetalleProceso.L);

		// Se recupera la información de los distritos que tienen distribucion
		// final de diputados mr
		List<Integer> distritosDistribucion = bsdDistrVotos
				.getDistribucionFinalByDistritos(
						usuario.getIdProcesoElectoral(),
						usuario.getIdDetalleProceso(),
						usuario.getIdEstadoSeleccionado(), idTipoCandidatura);

		// se valida si el tamaño de la lista de distritos y la de los distritos
		// que ya tienen
		// distribucion final de diputados mr tienen el mismo tamaño
		// Si es asi entonces la captura de la distribucion final de los
		// distritos del estado ya esta completa
		if (listDistritos.size() == distritosDistribucion.size()) {

			isDistribucionCompleta = true;

		} else {

			isDistribucionCompleta = false;
		}

		return isDistribucionCompleta;

	}

	/**
	 * Obtiene los distritos para los cuales cada uno de sus municipios tiene
	 * actas parciales generadas.
	 * 
	 * @param bsdInfo
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getDistritosByActasGeneradas(
			BSDInformacionGeneralActaInterface bsdInfo,
			DTOUsuarioLogin usuario, Integer idTipoCandidatura)
			throws Exception {

		Map<String, String> distritosCombo = new LinkedHashMap<String, String>();

		List<DTODistritosWS> distritosCabeceras = bsdCargaInformacion
				.obtenerCatalogoDistritos(usuario.getIdEstadoSeleccionado(),
						EnumAmbitoDetalleProceso.L);

		List<Object[]> distritos = bsdActas
				.consultaDistritosConSusMunicipios(idTipoCandidatura);

		Map<Integer, List<Integer>> mapaDist = new LinkedHashMap<Integer, List<Integer>>();

		for (Object[] distrito : distritos) {

			log.info("Distrito: " + distrito[0]);

			// Obtenemos los municipios para el distrito dado.

			log.info("mapaDist containskey: " + (Integer) distrito[0] + " es: "
					+ mapaDist.containsKey((Integer) distrito[0]));
			if (mapaDist.containsKey((Integer) distrito[0])) {
				mapaDist.get((Integer) distrito[0]).add((Integer) distrito[1]);
			} else {
				mapaDist.put((Integer) distrito[0], new ArrayList<Integer>());
				mapaDist.get((Integer) distrito[0]).add((Integer) distrito[1]);
			}

		}

		// Mapa creado

		List<Integer> idsMunicipios = null;
		for (Entry<Integer, List<Integer>> dist : mapaDist.entrySet()) {
			idsMunicipios = dist.getValue();

			log.info("Size " + idsMunicipios.size());

			boolean estanTodas = true;

			for (Integer idM : idsMunicipios) {
				log.info("IdDistrito:  " + dist.getKey() + " IdMunicipio : "
						+ idM);
				estanTodas = estanTodas
						&& existeActaTipoCandidatura(idM, dist.getKey(),
								idTipoCandidatura, usuario);
			}

			if (estanTodas) {
				log.info("Sí estan todas");
				for (DTODistritosWS dto : distritosCabeceras) {
					if (dto.getIdDistrito().equals(dist.getKey())) {
						log.info("Sí entra");
						distritosCombo.put(dist.getKey().toString(),
								dto.getNombreDistrito());
					}
				}
			}
		}

		return distritosCombo;
	}

	/**
	 * Método que verifica que existe un acta parcial
	 * 
	 * @param idMunicipio
	 * @param idDistrito
	 * @param idTipoCandidatura
	 * @param usuario
	 * @return
	 */
	private boolean existeActaTipoCandidatura(Integer idMunicipio,
			Integer idDistrito, Integer idTipoCandidatura,
			DTOUsuarioLogin usuario) {

		DTOActaTipoCandidaturaPK pkActa = new DTOActaTipoCandidaturaPK();
		pkActa.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
		pkActa.setIdDetalleProceso(usuario.getIdDetalleProceso());
		pkActa.setIdEstado(usuario.getIdEstadoSeleccionado());
		pkActa.setIdDistrito(idDistrito);
		pkActa.setIdMunicipio(idMunicipio);
		pkActa.setIdComunidad(-8);
		pkActa.setIdRegiduria(-7);
		pkActa.setIdTipoCandidatura(idTipoCandidatura);
		pkActa.setTipoActa(Constantes.TIPO_ACTA_PARCIAL);

		log.info("existeActa " + bsdActas.getActa(pkActa) != null);
		return bsdActas.getActa(pkActa) != null;

	}

	/**
	 * Método que verifica que existe un acta
	 * 
	 * @param idMunicipio
	 * @param idDistrito
	 * @param idRegiduria
	 * @param idTipoCandidatura
	 * @param usuario
	 * @param tipoActa
	 * @return
	 */
	public boolean existeActaTipoCandidaturaDistribucion(Integer idMunicipio,
			Integer idDistrito, Integer idRegiduria, Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer tipoActa) {

		DTOActaTipoCandidaturaPK pkActa = new DTOActaTipoCandidaturaPK();
		pkActa.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
		pkActa.setIdDetalleProceso(usuario.getIdDetalleProceso());
		pkActa.setIdEstado(usuario.getIdEstadoSeleccionado());
		pkActa.setIdDistrito(idDistrito);
		pkActa.setIdMunicipio(idMunicipio);
		pkActa.setIdComunidad(-8);
		pkActa.setIdRegiduria(idRegiduria);
		pkActa.setIdTipoCandidatura(idTipoCandidatura);
		pkActa.setTipoActa(tipoActa);

		log.info("existeActa " + bsdActas.getActa(pkActa) != null);
		return bsdActas.getActa(pkActa) != null;

	}

}
