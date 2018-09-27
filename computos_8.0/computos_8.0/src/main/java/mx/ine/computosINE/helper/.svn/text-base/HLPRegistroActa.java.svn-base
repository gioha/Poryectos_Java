/**
 * 
 */
package mx.ine.computosINE.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.computosINE.bo.BOCalculosComputosInterface;
import mx.ine.computosINE.bsd.BSDCapturaVotoRPInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.dto.DTOActa;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCEstatus;
import mx.ine.computosINE.dto.DTOCandidato;
import mx.ine.computosINE.dto.DTOCasillaAux;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.form.FormRegistroActa;
import mx.ine.computosINE.dto.helper.DTOClaveCasilla;
import mx.ine.computosINE.dto.helper.HLPCargaInformacion;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <code>HLPRegistroActa.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 24/04/2017 16:43:00
 */
@Component("hplRegistroActa")
@Scope ("prototype")
public class HLPRegistroActa implements Serializable {

	private static final long serialVersionUID = 6738765353317550597L;

	public static final String CONST_URL_IMG_GENERICA_PP = "/img_generica.png";
	public static final String CONST_URL_IMG_GENERICA_CI = "/id_CI.png";
	
	
	private static final Log log = LogFactory.getLog(HLPRegistroActa.class);

	@Autowired
	@Qualifier("boCalculos")
	private transient BOCalculosComputosInterface calculo;

	/**
	 * Carga inicial para obtener el tipo de candidatura
	 * @param bsd
	 * @return
	 * @throws Exception
	 */
	public HLPCargaInformacion getTipoCandidatura(BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario, String modulo) throws Exception {
		log.info("HLPRegistroActa.getTipoCandidatura");

		HLPCargaInformacion carga = new HLPCargaInformacion();
		List<DTOCandidatoWS> tiposCandidaturas = null;
		Object [] candidatura = null;

		// TODO Carga informacion de la candidatura
		//tiposCandidaturas = cargaTipoCandidatura(bsd, usuario);

		// TODO Se agrego por que el servicio de las candidaturas muestra demaciada intermitencia, por consiguiente se pasan en duro las cadidaturas
		tiposCandidaturas = new ArrayList<DTOCandidatoWS>();

		// TODO Nota: Esta condicion es utilizada para cuando la respuesta del servicio de candidaturas es exitosa
		if ( tiposCandidaturas != null && !tiposCandidaturas.isEmpty() ) {
			if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
				// TODO MODULO CANDIDATURA DIPUTADOS RP
				candidatura = getDatosCandidaturas(tiposCandidaturas, Utilidades.mensajeProperties("constante_nombreCortoTipoCand_DRP").trim());
			} else if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_regiduriasRP")) ) {
				// TODO MODULO CANDIDATURA REGIDURIA RP
				candidatura = getDatosCandidaturas(tiposCandidaturas, Utilidades.mensajeProperties("constante_nombreCortoTipoCand_RRP").trim());
			}
			carga.setIdTipoCandidatura((Integer) candidatura[0]);
			carga.setDescTipoCandidatura(String.valueOf(candidatura[1]));
		} else {
			// TODO Nota: Esta condicion es utilizada para cuando la respuesta del servicio de candidaturas falla
			if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
				// TODO MODULO CANDIDATURA DIPUTADOS RP
				carga.setIdTipoCandidatura(Integer.valueOf(Utilidades.mensajeProperties("constante_idTipoCandidatura_DRP")));
				carga.setDescTipoCandidatura(String.valueOf(Utilidades.mensajeProperties("constante_nombreCortoTipoCand_DRP")));

			} else if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_regiduriasRP")) ) {
				// TODO MODULO CANDIDATURA REGIDURIA RP
				carga.setIdTipoCandidatura(Integer.valueOf(Utilidades.mensajeProperties("constante_idTipoCandidatura_RRP")));
				carga.setDescTipoCandidatura(String.valueOf(Utilidades.mensajeProperties("constante_nombreCortoTipoCand_RRP")));
			}
		}

		// TODO Carga el tipo de casilla dependiendo el tipo de candidatura
		switch ( carga.getIdTipoCandidatura() ) {
			case 8: // TODO Diputados RP Nota: Este tipo de candidatura solo maneja las casillas S-Escpeciales
				carga.setTipoCasillas(Utilidades.mensajeProperties("constante_tipoCasilla_s"));
				break;
			case 16: // TODO Regidurias RP Nota: Este tipo de candidatura solo maneja las casillas S-Escpeciales
				carga.setTipoCasillas(Utilidades.mensajeProperties("constante_tipoCasilla_s"));
				break;
			default:
				break;
		}
		return carga;
	}

	/**
	 * Realiza la precarga de para Diputados RP.
	 * A) Cargar Secciones para el primer elemento de la lista de distritos. </BR>
	 * B) Carga Casillas para el primer elemento de la Secciones. </BR>
	 * C) Carga lista de estatus del acta. </BR>
	 * D) setIdDistrito, setSeccion, setCveCasilla, setIdEstatusActa
	 * @param bsd
	 * @param form
	 * @param carga
	 * @throws Exception
	 */
	public void precargaDeActaCapturaDipRP(BSDCargaInformacionInterface bsd, FormRegistroActa form, HLPCargaInformacion carga, String action) throws Exception {

		Map<Integer, Integer> secciones  	 = null;
		List<DTOCasillaAux>    casillas 	 = null;
		List<DTOCEstatus>     estatusCasilla = null;
		form.setExisteRecuentoTotal(false);
		form.getActa().setIdEstatusActa(null);

		// TODO Cargar Secciones para el primer elemento de la lista de distritos
		secciones = this.getCargaSeccionesXdistrito(carga.getCasillasCargaIni(), Integer.valueOf(carga.getDistritos().get(carga.getDistritos().keySet().toArray()[0])));

		// TODO Carga Casillas para el primer elemento de la Secciones
		casillas = this.getCargaCasillasXseccion(carga.getCasillasCargaIni(), secciones.get(secciones.keySet().toArray()[0]));

		// TODO Carga lista de estatus del acta
		// estatusCasilla = this.getEstatusActaFiltrado(bsd.estatusComputos());

		// TODO Fecha:06/06/2017. Verifica si agrega el estatus de recuento total al combo de estatus de acta.
		if ( getEstatusActaXdistrito(carga.getCasillasCargaIni(), Integer.valueOf(carga.getDistritos().get(carga.getDistritos().keySet().toArray()[0]))) ) {
			estatusCasilla = this.getEstatusRecuentoTotal(bsd.estatusComputos());
			form.setExisteRecuentoTotal(true);
			form.getActa().setIdEstatusActa(Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")));
		} else {
			// TODO Carga lista de estatus del acta sin el estatus de recuento total
			estatusCasilla = this.getEstatusActaFiltrado(bsd.estatusComputos());
		}

		carga.setSecciones(secciones);
		carga.setCasillas(casillas);
		carga.setEstatusCasilla(estatusCasilla);
		form.getActa().setIdDistrito(Integer.valueOf(carga.getDistritos().get(carga.getDistritos().keySet().toArray()[0])));
		form.getActa().setSeccion(Integer.valueOf(carga.getSecciones().get(carga.getSecciones().keySet().toArray()[0])));
		form.getActa().setCveCasilla(this.construyeCveCasilla(casillas.get(0).getSeccion(), casillas.get(0).getTipoCasilla(), casillas.get(0).getIdCasilla(), casillas.get(0).getExtContigua()));
		//form.getActa().setIdEstatusActa(action.equalsIgnoreCase("idCaptura") ? estatusCasilla.get(0).getIdEstatus() : null);
	}

	/**
	 * Realiza la precarga de para Diputados RP.
	 * A) Cargar Secciones para el primer elemento de la lista de distritos. </BR>
	 * B) Carga Casillas para el primer elemento de la Secciones. </BR>
	 * C) Carga lista de estatus del acta. </BR>
	 * D) setIdDistrito, setSeccion, setCveCasilla, setIdEstatusActa.
	 * @param bsd
	 * @param form
	 * @param carga
	 * @throws Exception
	 */
	public void precargaDeActaCapturaRegidRP(BSDCargaInformacionInterface bsd, FormRegistroActa form, HLPCargaInformacion carga, String action) throws Exception {

		Map<Integer, Integer> secciones  	 = null;
		List<DTOCasillaAux>    casillas 	 = null;
		List<DTOCEstatus>     estatusCasilla = null;
		form.setExisteRecuentoTotal(false);
		form.getActa().setIdEstatusActa(null);

		// TODO Cargar Secciones para el primer elemento de la lista de distritos
		secciones = this.getCargaSeccionesXdemarcaciones(carga.getCasillasCargaIni(), Integer.valueOf(carga.getDemarcaciones().get(carga.getDemarcaciones().keySet().toArray()[0])));

		// TODO Carga Casillas para el primer elemento de la Secciones
		casillas = this.getCargaCasillasXseccion(carga.getCasillasCargaIni(), secciones.get(secciones.keySet().toArray()[0]));

		// TODO Carga lista de estatus del acta
		// estatusCasilla = this.getEstatusActaFiltrado(bsd.estatusComputos());

		// TODO Fecha:06/06/2017. Verifica si agrega el estatus de recuento total al combo de estatus de acta.
		if ( getEstatusActaXdemarcacion(carga.getCasillasCargaIni(), Integer.valueOf(carga.getDemarcaciones().get(carga.getDemarcaciones().keySet().toArray()[0]))) ) {
			estatusCasilla = this.getEstatusRecuentoTotal(bsd.estatusComputos());
			form.setExisteRecuentoTotal(true);
			form.getActa().setIdEstatusActa(Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")));
		} else {
			// TODO Carga lista de estatus del acta sin el estatus de recuento total
			estatusCasilla = this.getEstatusActaFiltrado(bsd.estatusComputos());
		}

		carga.setSecciones(secciones);
		carga.setCasillas(casillas);
		carga.setEstatusCasilla(estatusCasilla);
		form.getActa().setIdRegiduria(Integer.valueOf(carga.getDemarcaciones().get(carga.getDemarcaciones().keySet().toArray()[0])));
		form.getActa().setSeccion(Integer.valueOf(carga.getSecciones().get(carga.getSecciones().keySet().toArray()[0])));
		form.getActa().setCveCasilla(this.construyeCveCasilla(casillas.get(0).getSeccion(), casillas.get(0).getTipoCasilla(), casillas.get(0).getIdCasilla(), casillas.get(0).getExtContigua()));
		//form.getActa().setIdEstatusActa(action.equalsIgnoreCase("idCaptura") ? estatusCasilla.get(0).getIdEstatus() : null);
	}

	/**
	 * Obtiene la carga inicial para mostrar en la vista. </BR>
	 * A) Casillas Carga Inicial. </BR>
	 * B) Casillas Carga con la informacion de casillas del proveedor de servicios dependiendo del tipo de candidatura.
	 * 
	 * carga inicial: Casillas del proveedor de servicio por candidatura y tipo casilla </BR>
	 * 1) Casillas Aprobadas Por Municipio Local para el tipo de candidatura de DIPUTADS RP. </BR>
	 * 2) Casillas Aprobadas Por Regiduria para el tipo de candidatura de REGIDURIAS RP
	 * 
	 * @param bsd
	 * @param bsdCaptura
	 * @param usuario
	 * @param tipoCasilla
	 * @param modulo
	 * @return
	 * @throws Exception
	 */
	public HLPCargaInformacion cargaInformacionInicialCaptura(BSDCargaInformacionInterface bsd, BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario, String tipoCasilla, String modulo) throws Exception {

		HLPCargaInformacion carga = new HLPCargaInformacion();

		// TODO Carga casillas
		List<DTOCasillaAux> casillasEsp = cargaCasillasXtipoCasilla(bsd, usuario, tipoCasilla, modulo);

		if ( casillasEsp == null || casillasEsp.isEmpty() ) {
			return null;
		}
		carga.setCasillasWS(casillasEsp);

		// TODO Nota: El universo ya no se calcula por Municipo, ahora se calcula en base al distrito en el 
		// TODO       caso de Diputados RP y para el caso de Regidurias RP por demarcacion, por lo cual ya no se calcula en este metodo
		// carga.setUniversoTotCasillas(new BigDecimal(casillasEsp.size()));
		return carga;
	}

	/**
	 * Consulta las actas capturadas a nivel de estado y municipio. </BR>
	 * A) Consulta las actas capturadas por estado y municipio. </BR>
	 * B) Deprecated--> Obtiene las casillas especiales del servicio web para obtener el universo total de casillas(actas).
	 * 
	 * @param bsd
	 * @param bsdCaptura
	 * @param usuario
	 * @param idTipoCandidatura
	 * @param tipoCasilla
	 * @param modulo
	 * @return
	 * @throws Exception
	 */
	public HLPCargaInformacion cargaInformacionInicialConsulta(BSDCargaInformacionInterface bsd, BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario, Integer idTipoCandidatura, String tipoCasilla, String modulo) throws Exception {
		HLPCargaInformacion carga = new HLPCargaInformacion();

		// List<DTOCasillaAux> casillasEsp = null;
		List<DTODistritosWS> distritosws = null;
		List<DTOListaRegiduriasWS>  demarcacionesws = null;
		DTOActaCasillaVotosPK id = new DTOActaCasillaVotosPK();
		id.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
		id.setIdDetalleProceso(usuario.getIdDetalleProceso());
		id.setIdEstado(usuario.getIdEstadoSeleccionado());
		id.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
		id.setTipoCasilla(tipoCasilla);
		id.setIdTipoCandidatura(idTipoCandidatura);

		// TODO Consulta las actas capturadas por estado y municipio y agrupadas por [ idProcesoElectoral, idDetalleProceso, idEstado, tipoCasilla, listaNominal].
		List<Object[]> casillas = bsdCaptura.consultaActasCapturadasXestado(id, modulo);

		if ( casillas == null || casillas.isEmpty() ) {
			return null;
		}

		// TODO Convertir y guardar la actas capturadas a un objeto de salida a nivel de la vista
		carga.setCasillasCargaIni(converterDTOCasillaWS(casillas, modulo));

		if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
			distritosws = bsd.obtenerCatalogoDistritos(usuario.getIdEstadoSeleccionado(), EnumAmbitoDetalleProceso.L);
			if ( distritosws == null || distritosws.isEmpty() ) {
				return null;
			}
		} else {
			demarcacionesws = bsd.consumeRegiduriasByMunicipio(usuario.getIdEstadoSeleccionado(), usuario.getIdMunicipioSeleccionado());
			if ( demarcacionesws == null || demarcacionesws.isEmpty()) {
				return null;
			}
		}

		// TODO CANDIDATURA DIPUTADOS RP
		if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
			// TODO Agregamos la cabecera distrital a la lista de casillasCargaIni.
			for (DTOCasillaAux it : carga.getCasillasCargaIni()) {
				for (DTODistritosWS d : distritosws) {
					if ( it.getIdDistritoLocal() == d.getIdDistrito() ) {
						 it.setCabeceraDistritoLocal(d.getNombreDistrito());
						break;
					}
				}
			}
		} else {
			// TODO Agregamos el nombre de la regiduria a la lista de casillasCargaIni.
			for (DTOCasillaAux it : carga.getCasillasCargaIni()) {
				for (DTOListaRegiduriasWS d : demarcacionesws) {
					if ( it.getIdRegiduria() == d.getIdRegiduria() ) {
						 it.setNombreRegiduria(d.getNombreRegiduria());
						break;
					}
				}
			}
		}

		// TODO Nota: El universo ya no se calcula por Municipo, ahora se calcula en base al distrito en el 
		// TODO       caso de Diputados RP y para el caso de Regidurias RP por demarcacion, por lo cual ya no se calcula en este metodo

		// TODO Obtener las casillas especiales del servicio web para obtener el universo total de casillas(actas)
		// casillasEsp = cargaCasillasXtipoCasilla(bsd, usuario, tipoCasilla, modulo);
		// carga.setUniversoTotCasillas(casillasEsp != null && !casillasEsp.isEmpty() ? new BigDecimal(casillasEsp.size()) : null);
		return carga;
	}

	/**
	 * Carga casillas no capturadas.
	 * List<DTOCasillaAux> casillas : Casillas del proveedor de servicios
	 * 
	 * @param bsdCaptura
	 * @param casillas
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaAux> cargaCasillasNoCaptura(BSDCapturaVotoRPInterface bsdCaptura, List<DTOCasillaAux> casillas, DTOUsuarioLogin usuario, Integer idTipoCandidatura) throws Exception {
		List<DTOCasillaAux> casillasNoCapturadas = new ArrayList<DTOCasillaAux>();
		DTOActaCasillaVotosPK id = null;

		for (DTOCasillaAux it : casillas) {
			id = new DTOActaCasillaVotosPK();
			id.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			id.setIdDetalleProceso(usuario.getIdDetalleProceso());
			id.setIdEstado(usuario.getIdEstadoSeleccionado());
			id.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			id.setSeccion(it.getSeccion());
			id.setIdCasilla(it.getIdCasilla());
			id.setTipoCasilla(it.getTipoCasilla());
			id.setExtContigua(it.getExtContigua());
			id.setIdTipoCandidatura(idTipoCandidatura);

			List<DTOActaCasillaVotos>  casillasCapturadas = bsdCaptura.getListActaCasillaVotos(id);
			// TODO Agrega las actas que aun no han sido capturadas
			if ( casillasCapturadas == null || casillasCapturadas.isEmpty() ) {
				it.setIdEstatus(null);
				it.setExisteRecuentoTotal(false);
				casillasNoCapturadas.add(it);
			}

			// TODO Fecha: 06/06/2017. Valida si el estatus es de recuento total, entonces tambien se agrega a la lista; 
			// TODO ya que se tienen que recapturar, obtengo solo la primera posicion ya que es la misma acta nada mas que por id asociacion, por lo tanto trae el mismo estatus
			if ( casillasCapturadas != null && !casillasCapturadas.isEmpty() && 
				 casillasCapturadas.get(0).getIdEstatus() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) && 
				 casillasCapturadas.get(0).getCapturada() == 0 ) {

				it.setIdEstatus(casillasCapturadas.get(0).getIdEstatus());
				it.setExisteRecuentoTotal(true);
				casillasNoCapturadas.add(it);
			}
		}
		return casillasNoCapturadas;
	}

	/**
	 * Realiza la carga del tipo de candidaturas
	 * @param bsd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<DTOCandidatoWS> cargaTipoCandidatura(BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario) throws Exception {
		// TODO Carga los tipo de candidaturas. [7.- DIPUTADO LOCAL MR, 8.- DIPUTADO LOCAL RP 6.-GOBERNADOR ESTATAL 9.-PRESIDENTE MUNICIPAL 15.- REGIDOR FISCALIZABLE MR 16.- REGIDOR FISCALIZABLE RP] 
		// TODO idDetalleProceso: 20, idEstado: 18, ambito: 1-Federal, 2-Local
		List<DTOCandidatoWS> candidaturas = bsd.consumeTiposCandidaturas(usuario.getIdDetalleProceso(), usuario.getIdEstadoSeleccionado(), (usuario.getAmbitoSistema().F.equals("F") ? 1 : 2));
		
		return candidaturas;
	}

	/**
	 * Obtiene la informacion del tipo de candidatura, en base a una lista de candidaturas y el nombre corto del tipo candidatura
	 * @param candidaturas
	 * @param tipoCandidatura
	 * @return
	 */
	public Object [] getDatosCandidaturas(List<DTOCandidatoWS> candidaturas, String tipoCandidatura) {

		Object [] datosTipoCand = new Object [2];

		if (candidaturas == null || candidaturas.isEmpty()) {
			return null;
		}
		for (DTOCandidatoWS it : candidaturas) {
			if ( tipoCandidatura.equalsIgnoreCase(it.getNombreCortoTipoCandidatura()) ) {
				datosTipoCand [0] = it.getIdTipoCandidatura();
				datosTipoCand [1] = it.getNombreCortoTipoCandidatura();
			}
		}
		return datosTipoCand;
	}

	/**
	 * Carga los distritos.
	 * @param casillasEsp
	 * @return
	 */
	public Map<String, String> cargaDistritos(List<DTOCasillaAux> casillasEsp) {
		// TODO Almacena los distritos
		Map<String, String> distritos = new LinkedHashMap<String, String>();

		if ( casillasEsp != null && !casillasEsp.isEmpty() ) {
			// TODO Cargar los distritos
			for (DTOCasillaAux it : casillasEsp) {
				distritos.put(String.valueOf(it.getIdDistritoLocal()).concat(" ").concat(it.getCabeceraDistritoLocal()), String.valueOf(it.getIdDistritoLocal()));
			}
		}
		return distritos;
	}

	/**
	 * Carga las demarcaciones.
	 * @param casillasEsp
	 * @return
	 */
	public Map<String, String> cargaDemarcaciones(List<DTOCasillaAux> casillasEsp) {
		// TODO Almacena demarcaciones
		Map<String, String> demarcaciones = new LinkedHashMap<String, String>();

		if ( casillasEsp != null && !casillasEsp.isEmpty()  ) {
			// TODO Cargar las demarcaciones
			for (DTOCasillaAux it : casillasEsp) {
				demarcaciones.put(it.getNombreRegiduria(), String.valueOf(it.getIdRegiduria()));
			}
		}
		return demarcaciones;
	}

	/**
	 * Carga las secciones especiales
	 * @param casillasEsp
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> cargaSeccionesEspeciales(List<DTOCasillaAux> casillasEsp) throws Exception {

		// TODO Almacena la secciones
		Map<Integer, Integer> secciones = new LinkedHashMap<Integer, Integer>();
		if ( casillasEsp != null && !casillasEsp.isEmpty()  ) {
			// TODO Cargar las secciones
			for (DTOCasillaAux it : casillasEsp) {
				secciones.put(it.getSeccion(), it.getSeccion());
			}
		}
		return secciones;
	}

	/**
	 * Carga casillas del proveedor de servicio por candidatura y tipo casilla </BR>
	 * A) Casillas Aprobadas Por Municipio Local para el tipo de candidatura de DIPUTADS RP. </BR>
	 * B) Casillas Aprobadas Por Regiduria para el tipo de candidatura de REGIDURIAS RP </BR>
	 * 
	 * @param bsd
	 * @param idProcesoElectoral
	 * @param idEstado
	 * @param idMunicipioLocal
	 * @return
	 * @throws Exception
	 */
	public List<DTOCasillaAux> cargaCasillasXtipoCasilla(BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario, String tipoCasilla, String modulo) throws Exception {

		// TODO Almacena las casillas especiales que serviran de salida en la vista
		List<DTOCasillaAux> casillasAuxEsp = new ArrayList<DTOCasillaAux>();
		// TODO Almacena solo las casillas especiales
		List<DTOCasillaWS> casillasEsp = new ArrayList<DTOCasillaWS>();
		// TODO Almacena todas las casillas aprobadas por el consejo
		List<DTOCasillaWS> casillasAC  = null;

		if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
			// TODO Consultamos la secciones casillas aprobadas por el consejo desde UBICAION_CASILLAS
			casillasAC  = bsd.casillasAprobadasPorMunicipioLocal(usuario.getIdProcesoElectoral(), usuario.getIdEstadoSeleccionado(), usuario.getIdMunicipioSeleccionado());
		} else {
			// TODO Consultamos la secciones casillas aprobadas por el consejo para regidurias
			casillasAC  = bsd.obtenSecCasillasAprobadasPorRegiduria(usuario.getIdProcesoElectoral(), usuario.getIdEstadoSeleccionado(), usuario.getIdMunicipioSeleccionado(), null);
		}

		// Filtro: Agregamos a la lista solo las casillas S-Especias
		if ( casillasAC != null && !casillasAC.isEmpty() ) {
			for (DTOCasillaWS it : casillasAC) {
				if ( it.getTipoCasilla().equalsIgnoreCase(tipoCasilla)) {
					casillasEsp.add(it);
				}
			}
			if ( casillasEsp != null && !casillasEsp.isEmpty() ) {
				casillasAuxEsp = this.converterDTOCasillaAux(casillasEsp);
			}
		}
		return casillasAuxEsp;
	}

	/**
	 * Carga Candidatos por el tipo de asociacion desde el proveedor de servicio
	 * @param bsd
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidato> cargaCandidatos(BSDCargaInformacionInterface bsd, DTOUsuarioLogin usuario, Integer idDistrito, Integer idRegiduria, Integer idTipoCandidatura) throws Exception {

		List<DTOCandidato> candidatos = null;
		DTOCandidato c = null;
		// TODO Consume informacion de asociaciones
		// Nota-1: Si es diputados RP el idDistrito debe se != null, y el idRegiduria debe se == null
		// Nota-2: Si es regidirías RP el idRegiduria debe se != null, y el idDistrito debe se == null
		// Nota-3: OJOOOOOOOOOOOO Por cuestiones de prueba se pasara el valor del idDetalleProceso, aun que debería ir el valor del idProcesoElectoral
		List<DTOCandidatoWS> candidatosAll = bsd.consumeAsociacionesCoaliciones(usuario.getIdDetalleProceso(), 
																				usuario.getIdEstadoSeleccionado(), 
																				idDistrito, 
																				idTipoCandidatura, 
																				usuario.getIdMunicipioSeleccionado(), 
																				idRegiduria);

		// TODO Validar que los candidatos del servicio no este nula o vacia
		if ( candidatosAll != null && !candidatosAll.isEmpty() ) {
			candidatos = new ArrayList<DTOCandidato>();
			// TODO Agregar todos los candidatos del servicio a un lista de objetos candidatos especializado
			for (DTOCandidatoWS it : candidatosAll) {
				// TODO Filtra candidatos por tipo asociacion
				if ( it.getTipoAsociacion() == 1 ) { // it.getTipoAsociacion() == 1 ||  it.getTipoAsociacion() == 4
					c = new DTOCandidato();
					c.setIdEstado(it.getIdEstado());
					c.setCveCandidato(getClaveCandidato(it.getIdAsociacion(), it.getTipoAsociacion()).trim());
					c.setIdAsociacion(it.getIdAsociacion());
					c.setTipoAsociacion(it.getTipoAsociacion());
					c.setIdTipoCandidatura(idTipoCandidatura);
					c.setNombreAsociacion(it.getNombreAsociacion());
					c.setEmblema(getEmblemaValido(it.getEmblema(), it.getTipoAsociacion()));
					c.setSiglas(it.getSiglas());
					c.setOrden(it.getOrden());
					c.setVotos(null);
					c.setVotosAux(null);
					c.setVotosAcumulados(new BigDecimal(0));

					// TODO Manipulacion de valores negativos
					c.setTipoAsociacionAux(getTipoAsociacionAux(it.getIdAsociacion(), it.getTipoAsociacion()));
					c.setIdAsociacionAux(getTipoAsociacionAux(it.getIdAsociacion(), it.getTipoAsociacion()));
					c.setOrdenAux(getOrdenAux(it.getIdAsociacion(), it.getTipoAsociacion(), it.getOrden()));
					candidatos.add(c);
				}
			}
		}
		return filtraCandidatosRP(candidatos);
	}

	/**
	 * Carga la votacion de candidatos
	 * @param bsd
	 * @param bsdCaptura
	 * @param usuario
	 * @param actaVotos
	 * @param cc
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidato> cargaCandidatosConsulta(BSDCargaInformacionInterface bsd, BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario, List<DTOActaCasillaVotos> actaVotos, DTOClaveCasilla cc) throws Exception {

		List<DTOCandidato> candidatos = null;
		List<DTOCandidato> asociaciones = null;

		// TODO Obtener los asociaciones de servicio web
		asociaciones = this.cargaCandidatos(bsd, usuario, cc.getIdDistrito(), cc.getIdRegiduria(), cc.getIdTipoCandidatura());

		// TODO Transformar al candidatosActa a DTOCandidato de salidas
		candidatos = converterDTOCandidatosConsulta(bsdCaptura, actaVotos, asociaciones);

		return candidatos;
	}

	/**
	 * Consulta el acta capturada respecto a los criterios seleccionados
	 * @param bsd
	 * @param bsdCaptura
	 * @param usuario
	 * @param cc
	 * @return
	 * @throws Exception 
	 */
	public List<DTOActaCasillaVotos> getActaCapturada(BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario, DTOClaveCasilla cc, String modulo) throws Exception {

		List<DTOActaCasillaVotos> actaVotos = null;
		DTOActaCasillaVotosPK id = null;

		id = new DTOActaCasillaVotosPK();
		id.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
		id.setIdDetalleProceso(usuario.getIdDetalleProceso());
		id.setIdEstado(usuario.getIdEstadoSeleccionado());
		if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
			// TODO CANDIDATURA DIPUTADOS RP
			id.setIdDistrito(cc.getIdDistrito());
		} else { 
			// TODO CANDIDATURA REGIDURIAS RP
			id.setIdRegiduria(cc.getIdRegiduria());
		}
		id.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
		id.setSeccion(cc.getSeccion());
		id.setIdCasilla(cc.getIdCasilla());
		id.setTipoCasilla(cc.getTipoCasilla());
		id.setExtContigua(cc.getExtContigua());
		id.setIdTipoCandidatura(cc.getIdTipoCandidatura());

		// TODO Consultar en base de datos a los candidatos del acta seleccionada
		actaVotos  = bsdCaptura.consultaCandidatosXacta(id, cc.getIdEstatus(), modulo);

		return actaVotos;
	}

	/**
	 * Calcula los votos acumulados por asociacion. </BR>
	 * Nota 2: La acumulacion de votos para Diputados RP se realiza a nivel de Distrito y la de Regidurias RP a nivel de Regiduria. 
	 * 
	 * @param bsdCaptura
	 * @param usuario
	 * @param candidatos
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidato> getVotosAcumuladosXasociacion(BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario, List<DTOCandidato> candidatos, DTOClaveCasilla cc) throws Exception {

		DTOActaCasillaVotosPK id = null;

		if ( candidatos != null && !candidatos.isEmpty() ) {
			for (DTOCandidato it : candidatos) {
				id = new DTOActaCasillaVotosPK();
				id.setIdProcesoElectoral(usuario.getIdProcesoElectoral()); 
				id.setIdDetalleProceso(usuario.getIdDetalleProceso());
				id.setIdEstado(usuario.getIdEstadoSeleccionado()); 
				id.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
				if ( cc.getIdTipoCandidatura() == Integer.valueOf(Utilidades.mensajeProperties("constante_idTipoCandidatura_DRP")) ) {
					// TODO CANDIDATURA DIPUTADOS RP
					id.setIdDistrito(cc.getIdDistrito());
				} else {
					// TODO CANDIDATURA REGIDURIAS RP
					id.setIdRegiduria(cc.getIdRegiduria());
				}
				id.setIdTipoCandidatura(cc.getIdTipoCandidatura());
				id.setTipoAsociacion(it.getTipoAsociacion());
				id.setIdAsociacion(it.getIdAsociacion());
				id.setTipoCasilla(cc.getTipoCasilla());

				// TODO Calcula los votos acumulados por los siguientes criterios:
				BigDecimal votosAcumulados =  bsdCaptura.getVotosAcumuladosDiputados(id);

				it.setVotosAcumulados(votosAcumulados);
			}
		}
		return candidatos;
	}

	/**
	 * Carga los calculos para el avance de las actas capturadas.</BR>
	 * Calcula: </BR>
	 * 1- Avance global del en % de Actas capturadas. </BR>
	 * 2- Actas capturadas. </BR>
	 * 3- Actas pendientes por capturar.
	 *  
	 * @param bsdCaptura
	 * @param universoTotCasillas
	 * @return
	 * @throws Exception 
	 */
	public Object [] cargaCalculos(BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario, BigDecimal universoTotCasillas, DTOClaveCasilla cc, String modulo) throws Exception {

		DTOActaCasillaVotosPK id = new DTOActaCasillaVotosPK();
		id.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
		id.setIdDetalleProceso(usuario.getIdDetalleProceso());
		id.setIdEstado(usuario.getIdEstadoSeleccionado());
		id.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
		id.setIdDistrito(cc.getIdDistrito());
		id.setIdRegiduria(cc.getIdRegiduria());
		id.setSeccion(cc.getSeccion());
		id.setIdCasilla(cc.getIdCasilla());
		id.setTipoCasilla(cc.getTipoCasilla());
		id.setIdTipoCandidatura(cc.getIdTipoCandidatura());

		// TODO Consultar el numero de casillas capturadas por municipio
		BigDecimal capturadas = bsdCaptura.getCountVotosCapturados(id);
		return getCalculosActas(capturadas, universoTotCasillas);
	}

	/**
	 * Realiza los calculos referentes al avance de las casillas. </BR>
	 * Calcula: </BR>
	 * 1- Avance global del en % de Actas capturadas. </BR>
	 * 2- Actas capturadas. </BR>
	 * 3- Actas pendientes por capturar.
	 * 
	 * Nota: El universoTotCasillas no puede ser menor al numero de casillas capturadas
	 * 
	 * @param capturadas numero de actas capturadas
	 * @param universoTotCasillas el universo total de actas a capturar
	 * @return
	 * @throws Exception
	 */
	public Object [] getCalculosActas(BigDecimal capturadas, BigDecimal universoTotCasillas) throws Exception {
		return calculo.getCalculosActas(capturadas, universoTotCasillas);
	}

	/**
	 * Obtiene las secciones por distrito
	 * @param casillasCargaIni
	 * @param idDistrito
	 * @return
	 */
	public Map<Integer, Integer> getCargaSeccionesXdistrito(List<DTOCasillaAux> casillasCargaIni, Integer idDistrito) {

		Map<Integer, Integer> secciones = new HashMap<Integer, Integer>();

		// TODO Busca las casillas especiales en base a la seccion
		for (DTOCasillaAux it : casillasCargaIni) {
			if ( it.getIdDistritoLocal().intValue() == idDistrito.intValue() ) {
				secciones.put(it.getSeccion(), it.getSeccion());
			}
		}
		return secciones;
	}

	/**
	 * Obtiene las secciones por demarcacion
	 * @param casillasCargaIni
	 * @param idDistrito
	 * @return
	 */
	public Map<Integer, Integer> getCargaSeccionesXdemarcaciones(List<DTOCasillaAux> casillasCargaIni, Integer idDemarcacion) {

		Map<Integer, Integer> secciones = new HashMap<Integer, Integer>();

		// TODO Busca las casillas especiales en base a la seccion
		for (DTOCasillaAux it : casillasCargaIni) {
			if ( it.getIdRegiduria().intValue() == idDemarcacion.intValue() ) {
				secciones.put(it.getSeccion(), it.getSeccion());
			}
		}
		return secciones;
	}

	/**
	 * Elimina del catalogo de C_ESTATUS la opcion {5-Recuento total}, ya que para las capturas </BR>
	 * normales este estatus no aplica, solo aplicara para cuando el OPLE determine que una casilla, </BR>
	 * se va a recuento total. </BR>
	 * REGLA 1.- (Deprecated) La opcion de {5-Recuento total}-No aplica para candidatura de Diputados RP y ni para Regidurias RP </BR>
	 * 
	 * @param estatusCasilla
	 * @return
	 */
	public List<DTOCEstatus> getEstatusActaFiltrado(List<DTOCEstatus> estatusCasilla) {

		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();

		for (DTOCEstatus it : estatusCasilla) {
			if ( it.getIdEstatus().intValue() != Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal"))) {
				estatus.add(it);
			}
		}
		return estatus;
	}

	/**
	 * Agrega al catalogo de C_ESTATUS la opcion {5-Recuento total}, </BR>
	 * Aplica para cuando el OPLE determine que una casilla se va a recuento total.
	 * REGLA 2.- La opcion de {5-Recuento total}-Aplica para candidatura de Diputados RP y para Regidurias RP, </BR>
	 * para el caso para cuando se envian a recuento las de diputado y regidias MR
	 * 
	 * @param estatusCasilla
	 * @return
	 */
	public List<DTOCEstatus> getEstatusRecuentoTotal(List<DTOCEstatus> estatusCasilla) {

		List<DTOCEstatus> estatus = new ArrayList<DTOCEstatus>();
		for (DTOCEstatus it : estatusCasilla) {
			if ( it.getIdEstatus().intValue() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) ) {
				estatus.add(it);
				break;
			}
		}
		return estatus;
	}

	/**
	 * Obtiene las casillas en base a una seccion
	 * @param casillasCargaIni
	 * @param seccion
	 * @return
	 */
	public List<DTOCasillaAux> getCargaCasillasXseccion(List<DTOCasillaAux> casillasCargaIni, Integer seccion) {

		List<DTOCasillaAux> casillas = new ArrayList<DTOCasillaAux>();

		// TODO Busca las casillas en base a la seccion
		for (DTOCasillaAux it : casillasCargaIni) {
			if ( it.getSeccion().intValue() == seccion.intValue() ) {
				casillas.add(it);
			}
		}
		return casillas;
	}

	/**
	 * Carga la informacion del candidato no registrado.
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 */
	public DTOCandidato cargaCandidatoNoRegidtrado(DTOUsuarioLogin usuario, Integer idTipoCandidatura) {
		DTOCandidato cnr = new DTOCandidato();
		cnr.setIdEstado(usuario.getIdEstadoSeleccionado());
		cnr.setIdAsociacion(Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_canNoRegistrado")));
		cnr.setTipoAsociacion(Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_canNoRegistrado")));
		cnr.setNombreAsociacion(Utilidades.mensajeProperties("etiqueta_regVotacion_candNoRegistrado"));
		cnr.setEmblema(null);
		cnr.setSiglas(null);
		cnr.setOrden(Integer.valueOf(Utilidades.mensajeProperties("constante_orden_canNoRegistrado")));
		cnr.setOrdenAux(getOrdenAux(cnr.getTipoAsociacion(), cnr.getTipoAsociacion(), cnr.getOrden()));
		cnr.setIdTipoCandidatura(idTipoCandidatura);
		cnr.setNombreCortoTipoCandidatura(null);
		cnr.setNombreLargoTipoCandidatura(null);
		cnr.setVotos(null);
		cnr.setVotosAux(null);
		cnr.setVotosAcumulados(new BigDecimal(0));

		// TODO Manipula los valores negativos
		cnr.setTipoAsociacionAux(getTipoAsociacionAux(cnr.getIdAsociacion(), cnr.getTipoAsociacion()));
		cnr.setIdAsociacionAux(getIdAsociacionAux(cnr.getIdAsociacion(), cnr.getTipoAsociacion()));
		cnr.setOrdenAux(getOrdenAux(cnr.getTipoAsociacion(), cnr.getTipoAsociacion(), cnr.getOrden()));

		cnr.setCveCandidato(getClaveCandidato(cnr.getIdAsociacionAux(), cnr.getTipoAsociacionAux()).trim());
		cnr.setCveCandidatoAux(getClaveCandidato(cnr.getIdAsociacionAux(), cnr.getTipoAsociacionAux()).trim());
		return cnr;
	}

	/**
	 * Carga la informacion para los votos nulos.
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 */
	public DTOCandidato cargaVotosNulos(DTOUsuarioLogin usuario, Integer idTipoCandidatura) {
		DTOCandidato vn = new DTOCandidato();
		vn.setIdEstado(usuario.getIdEstadoSeleccionado());
		vn.setIdAsociacion(Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_votosNulos")));
		vn.setTipoAsociacion(Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_votosNulos")));
		vn.setNombreAsociacion(Utilidades.mensajeProperties("etiqueta_regVotacion_votosNulos"));
		vn.setEmblema(null);
		vn.setSiglas(null);
		vn.setOrden(Integer.valueOf(Utilidades.mensajeProperties("constante_orden_votosNulos")));
		vn.setIdTipoCandidatura(idTipoCandidatura);
		vn.setNombreCortoTipoCandidatura(null);
		vn.setNombreLargoTipoCandidatura(null);
		vn.setVotos(null);
		vn.setVotosAux(null);
		vn.setVotosAcumulados(new BigDecimal(0));

		// TODO Manipula los valores negativos
		vn.setTipoAsociacionAux(getTipoAsociacionAux(vn.getIdAsociacion(), vn.getTipoAsociacion()));
		vn.setIdAsociacionAux(getIdAsociacionAux(vn.getIdAsociacion(), vn.getTipoAsociacion()));
		vn.setOrdenAux(getOrdenAux(vn.getTipoAsociacion(), vn.getTipoAsociacion(), vn.getOrden()));

		vn.setCveCandidato(getClaveCandidato(vn.getIdAsociacionAux(), vn.getTipoAsociacionAux()).trim());
		vn.setCveCandidatoAux(getClaveCandidato(vn.getIdAsociacionAux(), vn.getTipoAsociacionAux()).trim());
		return vn;
	}

	/**
	 * Genera la clave del candidato
	 * @param idAsociacion
	 * @param tipoAsociacion
	 * @return
	 */
	public String getClaveCandidato(Integer idAsociacion, Integer tipoAsociacion) {
		return idAsociacion.toString().concat("-").concat(tipoAsociacion.toString());
	}

	/**
	 * Obtiene el nombre emblema en base al tipo asociacion
	 * @param emblema
	 * @param tipoAsociacion
	 * @return
	 */
	public String getEmblemaValido(String emblema, Integer tipoAsociacion) {
		if ( emblema == null || emblema.isEmpty() ) {
			return CONST_URL_IMG_GENERICA_PP;
		}
		if ( emblema != null && !emblema.isEmpty() && tipoAsociacion == 1 ) {
			return limpiaRutaEmblema(emblema);
		} else {
			if ( tipoAsociacion == 4 ) {
				return CONST_URL_IMG_GENERICA_CI;
			}
		}
		return null;
	}

	/**
	 * Limpia la ruta emblema quitando la primera diagonal
	 * @param re
	 * @return
	 */
	public String limpiaRutaEmblema(String re) {
		
		if (re == null || re.isEmpty()) {
			return null;
		}
		char caracter  = re.charAt(0);
		char caracter2 = re.charAt(1);

		if ( String.valueOf(caracter).equalsIgnoreCase("/") && String.valueOf(caracter2).equalsIgnoreCase("G")) {
			return re.substring(11);
		}
		return re;
	}

	/**
	 * Obtiene la clave compuesta de la casilla. </BR>
	 * Nota: Estructura de la clave:[seccion+tipoCasilla+idCasilla+excontigua]
	 * @param cveCasilla
	 * @return
	 */
	public DTOClaveCasilla getCleveCompuestaCasilla(String cveCasilla) {

		DTOClaveCasilla cc = new DTOClaveCasilla();

		String [] arrayCasilla = new String[4];
		arrayCasilla = cveCasilla.split("-");

		cc.setSeccion(Integer.valueOf(arrayCasilla[0].trim()));
		cc.setTipoCasilla(arrayCasilla[1].trim());
		cc.setIdCasilla(Integer.valueOf(arrayCasilla[2].trim()));
		if ( arrayCasilla.length == 4 ) {
			// Nota: Esta posicion del arreglo se valida, por el caso de que la excontigua sea cero
			cc.setExtContigua(Integer.valueOf(arrayCasilla[3].trim()));
		} else {
			cc.setExtContigua(0);
		}
		return cc;
	}

	/**
	 * Construye la clave compuesta de la casilla. </BR>
	 * clave:[seccion+tipoCasilla+idCasilla+excontigua]
	 * 
	 * @param seccion
	 * @param tipoCasilla
	 * @param idCasilla
	 * @param excontigua
	 * @return
	 */
	public String construyeCveCasilla(Integer seccion, String tipoCasilla, Integer idCasilla, Integer excontigua) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(seccion).append("-").append(tipoCasilla).append("-").append(idCasilla);
		if ( excontigua > 0 ) {
			sb.append("-").append(excontigua);
		}
		return sb.toString().trim();
		
	}

	/**
	 * Transforma un lista de objetos del tipo DTOActaCasillaVotos a una de tipo DTOCandidato
	 * @param candidatosActa
	 * @param cws
	 * @return
	 * @throws Exception 
	 */
	public List<DTOCandidato> converterDTOCandidatosConsulta(BSDCapturaVotoRPInterface bsdCaptura, List<DTOActaCasillaVotos> candidatosActa, List<DTOCandidato> cws) throws Exception {

		List<DTOCandidato> candidatos = null;
		DTOCandidato c = null;
		DTOActaCasillaVotosPK id = null;

		if ( candidatosActa != null && !candidatosActa.isEmpty() ) {
			candidatos = new ArrayList<DTOCandidato>();
			// TODO Agregar todos los candidatos del servicio a un lista de objetos candidatos especializado
			for (DTOActaCasillaVotos it : candidatosActa) {

				id = new DTOActaCasillaVotosPK();
				id.setIdProcesoElectoral(it.getId().getIdProcesoElectoral());
				id.setIdDetalleProceso(it.getId().getIdDetalleProceso());
				id.setIdEstado(it.getId().getIdEstado());
				id.setIdMunicipio(it.getId().getIdMunicipio());	
				id.setIdDistrito(it.getId().getIdDistrito());
				id.setIdRegiduria(it.getId().getIdRegiduria());
				id.setTipoAsociacion(it.getId().getTipoAsociacion());
				id.setIdAsociacion(it.getId().getIdAsociacion());
				id.setTipoCasilla(it.getId().getTipoCasilla());
				id.setIdTipoCandidatura(it.getId().getIdTipoCandidatura());

				c = new DTOCandidato();
				c.setIdEstado(it.getId().getIdEstado());
				c.setCveCandidato(getClaveCandidato(it.getId().getIdAsociacion(), it.getId().getTipoAsociacion()));
				c.setIdAsociacion(it.getId().getIdAsociacion());
				c.setTipoAsociacion(it.getId().getTipoAsociacion());
				c.setIdTipoCandidatura(it.getId().getIdTipoCandidatura());
				c.setNombreAsociacion(getNombreAsociacion(cws, it.getId().getIdAsociacion(), it.getId().getTipoAsociacion(), it.getId().getIdTipoCandidatura()));
				c.setEmblema(getEmblemaValido(getEmblema(cws, it.getId().getIdAsociacion(), it.getId().getTipoAsociacion(), it.getId().getIdTipoCandidatura()), it.getId().getTipoAsociacion()));
				c.setSiglas(getSiglas(cws, it.getId().getIdAsociacion(), it.getId().getTipoAsociacion(), it.getId().getIdTipoCandidatura()));
				c.setOrden(it.getOrden());
				// TODO Valida si es 3-Casilla no instalada o 4-Paquete no entregado pone en nulo el voto de lo contrario le deja el voto que trae por defecto
				if ( it.getIdEstatus() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_CasillaNoInstalada")) || 
					 it.getIdEstatus() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_PaqueteNoEntregado"))) {
					c.setVotos(null);
					c.setVotosAux(null);
				} else {
					c.setVotos(new BigDecimal(it.getVotos()));
					c.setVotosAux(String.valueOf(it.getVotos()).trim());
				}				
				// TODO Manipulacion de valores negativos
				c.setTipoAsociacionAux(getTipoAsociacionAux(it.getId().getIdAsociacion(), it.getId().getTipoAsociacion()));
				c.setIdAsociacionAux(getTipoAsociacionAux(it.getId().getIdAsociacion(), it.getId().getTipoAsociacion()));
				c.setOrdenAux(getOrdenAux(it.getId().getIdAsociacion(), it.getId().getTipoAsociacion(), it.getOrden()));

				// TODO Consultar el acumulado por asociacion
				c.setVotosAcumulados(bsdCaptura.getVotosAcumuladosDiputados(id));
				candidatos.add(c);
			}
		}
		return candidatos;
	}

	/**
	 * Obtiene el Nombre de la Asociacion
	 * @param cws
	 * @param v1
	 * @param v2
	 * @param v3
	 * @return
	 */
	public String getNombreAsociacion(List<DTOCandidato> cws, Integer v1, Integer v2, Integer v3) {

		// TODO CANDIDATO NO REGISTRAO
		if ( v2 == Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_canNoRegistrado"))) {
			return Utilidades.mensajeProperties("etiqueta_regVotacion_candNoRegistrado");
		}
		// TODO VOTOS NULOS
		if ( v2 == Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_votosNulos")) ) {
			return Utilidades.mensajeProperties("etiqueta_regVotacion_votosNulos");
		}
		// TODO CANDIDATO INDEPENDIENTE
		if ( v2 == 4 ) {
			return Utilidades.mensajeProperties("etiqueta_regVotacion_canIndep");
		}
		for ( DTOCandidato it : cws ) {
			if (it.getIdAsociacion() == v1 && it.getTipoAsociacion() == v2 && it.getIdTipoCandidatura() == v3 ) {
				return it.getNombreAsociacion();
			}
		}
		return null;
	}

	/**
	 * Obtiene el Emblema
	 * @param cws	List<DTOCandidato>
	 * @param v1	IdAsociacion
	 * @param v2	TipoAsociacion
	 * @param v3	IdTipoCandidatura
	 * @return
	 */
	public String getEmblema(List<DTOCandidato> cws, Integer v1, Integer v2, Integer v3) {
		for ( DTOCandidato it : cws ) {
			if (it.getIdAsociacion() == v1 && it.getTipoAsociacion() == v2 && it.getIdTipoCandidatura() == v3 ) {
				return it.getEmblema();
			}
		}
		return null;
	}

	/**
	 * Obtiene las Siglas
	 * @param cws	List<DTOCandidato>
	 * @param v1	IdAsociacion
	 * @param v2	TipoAsociacion
	 * @param v3	IdTipoCandidatura
	 * @return
	 */
	public String getSiglas(List<DTOCandidato> cws, Integer v1, Integer v2, Integer v3) {
		for ( DTOCandidato it : cws ) {
			if (it.getIdAsociacion() == v1 && it.getTipoAsociacion() == v2 && it.getIdTipoCandidatura() == v3 ) {
				return it.getSiglas();
			}
		}
		return null;
	}

	/**
	 * Obtiene un casillas capturadas del tipo DTOCasillaAux
	 * @param casillas
	 * @return
	 */
	public List<DTOCasillaAux> converterDTOCasillaWS(List<Object[]> casillas, String modulo) {

		List<DTOCasillaAux> list = new ArrayList<DTOCasillaAux>();
		DTOCasillaAux c = null;

		for (Object[] o : casillas) {
			// TODO o=[idProcesoElectoral,idDetalleProceso,idEstado,idDistrito o idRegiduria,idMunicipio,seccion,idCasilla,tipoCasilla,extContigua, listaNominal]
			c = new DTOCasillaAux();

			if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
				// TODO CANDIDATURA DIPUTADOS RP
				c.setIdDistritoLocal(( (Integer) (o [3]) ).intValue());
			} else {
				// TODO CANDIDATURA REGIDURIAS RP
				c.setIdRegiduria(( (Integer) (o [3]) ).intValue());
			}
			c.setSeccion(( (Integer) (o [5]) ).intValue());
			c.setIdCasilla(( (Integer) (o [6]) ).intValue());
			c.setTipoCasilla( String.valueOf(o [7]) );
			c.setExtContigua(( (Integer) (o [8]) ).intValue());
			c.setListaNominalCasilla(( (Integer) (o [9]) ).intValue());
			c.setIdEstatus(( (Integer) (o [10]) ).intValue());
			c.setNombreCasilla(c.getTipoCasilla().concat(c.getIdCasilla().toString()));
			list.add(c);
		}
		return list;
	}

	/**
	 * Tranforma a una Entity valida para guardar en base
	 * @param acta
	 * @param candidatos
	 * @param usuario
	 * @return
	 */
	public List<DTOActaCasillaVotos> formatoEntityGuardar(DTOActa acta, List<DTOCandidato> candidatos, DTOUsuarioLogin usuario, String modulo) {

		DTOActaCasillaVotosPK pk = null;
		List<DTOActaCasillaVotos> votos = new ArrayList<DTOActaCasillaVotos>();
		DTOClaveCasilla cc  = getCleveCompuestaCasilla(acta.getCveCasilla());

		for (DTOCandidato it : candidatos) {
			DTOActaCasillaVotos v = new DTOActaCasillaVotos();
			pk = new DTOActaCasillaVotosPK();

			pk.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			pk.setIdDetalleProceso(usuario.getIdDetalleProceso());
			pk.setIdEstado(usuario.getIdEstadoSeleccionado());
			if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
				// TODO CANDIDATURA DIPUTADOS RP
				pk.setIdDistrito(acta.getIdDistrito());
				pk.setIdRegiduria(Integer.valueOf(Utilidades.mensajeProperties("constante_idRegiduria")));
			} else {
				// TODO CANDIDATURA REGIDURIAS RP
				pk.setIdDistrito(Integer.valueOf(Utilidades.mensajeProperties("constante_idDistrito")));
				pk.setIdRegiduria(acta.getIdRegiduria());
			}
			pk.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			pk.setSeccion(acta.getSeccion());
			pk.setIdComunidad(Integer.valueOf(Utilidades.mensajeProperties("constante_idComunidad")));
			pk.setIdCasilla(cc.getIdCasilla());
			pk.setTipoCasilla(cc.getTipoCasilla());
			pk.setExtContigua(cc.getExtContigua());
			pk.setIdTipoCandidatura(acta.getIdTipoCandidatura());
			pk.setIdAsociacion(it.getIdAsociacion());
			pk.setTipoAsociacion(it.getTipoAsociacion());
			pk.setIdCoalicion(Integer.valueOf(Utilidades.mensajeProperties("constante_idCoalicion")));
			v.setId(pk);
			v.setIdEstatus(acta.getIdEstatusActa());
			v.setOrden(it.getOrden() != null ? it.getOrden() : 0);
			if ( acta.getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_CasillaNoInstalada")) || 
				 acta.getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_PaqueteNoEntregado"))) {
				v.setVotos(0);
			} else {
				v.setVotos(Integer.valueOf(it.getVotosAux().trim()));
			}
			v.setCapturada(Integer.valueOf(Utilidades.mensajeProperties("constante_capturada_1")));
			
			// TODO Fecha: 06/06/2017 Todas se aplica como capturada
			/*
			if ( acta.getIdEstatusActa().intValue() != Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) ) {
				// TODO Significa que se contabiliza en el proceso de captura
				v.setCapturada(Integer.valueOf(Utilidades.mensajeProperties("constante_capturada_1")));
			}*/
			v.setListaNominal(acta.getListaNominal());
			v.setUsuario(usuario.getUsername());
			v.setFechaHora(new Date());
			votos.add(v);
		}
		return votos;
	}

	/**
	 * Manipula los Tipo Asociacion para cuando tienen un valor negativo
	 * @param idAsociacion
	 * @param tipoAsociacion
	 * @return
	 */
	public Integer getTipoAsociacionAux(Integer idAsociacion, Integer tipoAsociacion) {

		// TODO Orde auxiliar para los candidatos no registrados
		if ( idAsociacion   == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_canNoRegistrado"))  &&
			 tipoAsociacion == Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_canNoRegistrado"))) {
			return Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacionAux_canNoReg"));
		}

		// TODO Orde auxiliar para los votos nulos
		if ( idAsociacion   == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_votosNulos"))  &&
			 tipoAsociacion == Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_votosNulos"))) {
			return Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacionAux_votosNulos"));
		}
		return tipoAsociacion;
	}

	/**
	 * Manipula los Id Asociacion para cuando tienen un valor negativo
	 * @param idAsociacion
	 * @param tipoAsociacion
	 * @return
	 */
	public Integer getIdAsociacionAux(Integer idAsociacion, Integer tipoAsociacion) {

		// TODO Orde auxiliar para los candidatos no registrados
		if ( idAsociacion   == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_canNoRegistrado"))  &&
			 tipoAsociacion == Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_canNoRegistrado"))) {
			return Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacionAux_canNoReg"));
		}

		// TODO Orde auxiliar para los votos nulos
		if ( idAsociacion   == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_votosNulos"))  &&
			 tipoAsociacion == Integer.valueOf(Utilidades.mensajeProperties("constante_tipoAsociacion_votosNulos"))) {
			return Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacionAux_votosNulos"));
		}
		return idAsociacion;
	}

	/**
	 * Manipula el Orden para cuando tienen un valor negativo
	 * @param idAsociacion
	 * @param tipoAsociacion
	 * @param orden
	 * @return
	 */
	public Integer getOrdenAux(Integer idAsociacion, Integer tipoAsociacion, Integer orden) {

		// TODO Orde auxiliar para los candidatos no registrados
		if ( idAsociacion   == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_canNoRegistrado"))  &&
			 tipoAsociacion == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_canNoRegistrado"))) {
			return Integer.valueOf(Utilidades.mensajeProperties("constante_orden_canNoRegistrado"));
		}

		// TODO Orde auxiliar para los votos nulos
		if ( idAsociacion   == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_votosNulos"))  &&
			 tipoAsociacion == Integer.valueOf(Utilidades.mensajeProperties("constante_idAsociacion_votosNulos"))) {
			return Integer.valueOf(Utilidades.mensajeProperties("constante_orden_votosNulos"));
		}
		return orden;
	}

	/**
	 * Obtiene una lista unica de objetos candidato para el caso de Representacion Proporcional
	 * @param candidatos
	 * @return
	 */
	public List<DTOCandidato> filtraCandidatosRP(List<DTOCandidato> candidatos) {

		List<DTOCandidato> candidatosRP = new ArrayList<DTOCandidato>();
		Map<String, DTOCandidato> candidatosMap = new LinkedHashMap<String, DTOCandidato>();
		StringBuilder sb = null;

		// TODO Crear la clave auxiliar del candidato para posteriormente realizar el filtrado
		for (DTOCandidato it : candidatos) {
			sb = new StringBuilder();
			sb.append(it.getIdAsociacion()).append("-").append(it.getTipoAsociacion());
			it.setCveCandidatoAux(sb.toString().trim());
		}

		// TODO Realizar Filtrafo
		for (DTOCandidato it : candidatos) {
			candidatosMap.put(it.getCveCandidatoAux(), it);
		}

		// TODO Crear una lista de objeto candidato unica
		for (Entry<String, DTOCandidato> m : candidatosMap.entrySet()) {
			candidatosRP.add(m.getValue());
		}
		return candidatosRP;
	}

	/**
	 * Obtiene la descripcion del estatus de acta en base a su id estatus
	 * @param estatusActa
	 * @param idEstatus
	 * @return
	 */
	public String getDescEstausActa(List<DTOCEstatus> estatusActa, Integer idEstatus) {
		for (DTOCEstatus it : estatusActa) {
			if ( idEstatus.intValue() == it.getIdEstatus().intValue() ) {
				return it.getDescripcion();
			}
		}
		return null;
	}

	/**
	 * Limpia los votos para cuando sea estatus del acta: </BR>
	 * A) 3.- Casilla no instalada. </BR>
	 * B) 4.- Paquete no entregado. </BR>
	 * o  </BR>
	 * Limpia los votos para cuando el estatus del acta seleccionado es diferente al del acta que esta capturada </BR>
	 * esto aplica para el caso de modifica.
	 * 
	 * @param candidatos
	 * @param idEstatusActa
	 */
	public void limpiaVotoCandidato(List<DTOCandidato> candidatos) {
		for ( DTOCandidato it : candidatos ) {
			it.setVotos(null);
			it.setVotosAux(null);
		}
	}

	/**
	 * Valida que no exista distribucion o generacion de acta, </BR>
	 * true : si ya existe distribucion generada. </BR>
	 * false: No existe distribucion generada.
	 * @param bsdCaptura
	 * @param usuario
	 * @param cc
	 * @return
	 * @throws Exception 
	 */
	public boolean existeDistribucionOgeneracionActa(BSDCapturaVotoRPInterface bsdCaptura, DTOUsuarioLogin usuario,DTOClaveCasilla cc, String modulo) throws Exception {
		// TODO Verifica si ya existe una distribucion para la casilla
		DTOActaTipoCandidaturaPK    id0 = null;
		DTODistribucionTotalesPK    id1 = null;
		DTODistribucionPartidosCIPK id2 = null;
		DTODistribucionCandidatosPK id3 = null;

		if ( modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP")) ) {
			// TODO CANDIDATURA DIPUTADOS RP
			id0 = new DTOActaTipoCandidaturaPK();
			id0.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			id0.setIdDetalleProceso(usuario.getIdDetalleProceso());
			id0.setIdTipoCandidatura(cc.getIdTipoCandidatura());

			id0.setIdEstado(usuario.getIdEstadoSeleccionado());
			id0.setIdDistrito(cc.getIdDistrito());
			id0.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			return bsdCaptura.existeGeneracionActa(id0);
		} else {
			// TODO CANDIDATURA REGIDURIAS RP
			id1 = new DTODistribucionTotalesPK();
			id2 = new DTODistribucionPartidosCIPK();
			id3 = new DTODistribucionCandidatosPK();

			id1.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			id1.setIdDetalleProceso(usuario.getIdDetalleProceso());
			id1.setIdTipoCandidatura(cc.getIdTipoCandidatura());

			id1.setIdEstado(usuario.getIdEstadoSeleccionado());
			id1.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			id1.setIdRegiduria(cc.getIdRegiduria());

			id2.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			id2.setIdDetalleProceso(usuario.getIdDetalleProceso());
			id2.setIdTipoCandidatura(cc.getIdTipoCandidatura());

			id2.setIdEstado(usuario.getIdEstadoSeleccionado());
			id2.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			id2.setIdRegiduria(cc.getIdRegiduria());

			id3.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
			id3.setIdDetalleProceso(usuario.getIdDetalleProceso());
			id3.setIdTipoCandidatura(cc.getIdTipoCandidatura());

			id3.setIdEstado(usuario.getIdEstadoSeleccionado());
			id3.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
			id3.setIdRegiduria(cc.getIdRegiduria());
			
			return ( bsdCaptura.existeDistribucionTotales(id1) ||  bsdCaptura.existeDistribucionPartidosCI(id2) || bsdCaptura.existeDistribucionCandidatos(id3) ? true : false );
		}
	}

	/**
	 * Calcula el Universo total de casillas por candidatura.
	 * @param carga
	 * @param tipoCandidatura
	 * @param idDistrito
	 * @param idRegiduria
	 * @return
	 */
	public BigDecimal calculaUniversoTotal(HLPCargaInformacion carga, Integer tipoCandidatura, Integer idDistrito, Integer idRegiduria) {		
		List<DTOCasillaAux> resultado = new ArrayList<DTOCasillaAux>();
		if ( tipoCandidatura == Integer.valueOf(Utilidades.mensajeProperties("constante_idTipoCandidatura_DRP")) ) {
			// TODO DIPUTADOS RP
			for (DTOCasillaAux it : carga.getCasillasWS()) {
				if ( idDistrito == it.getIdDistritoLocal() ) {
					resultado.add(it);
				}
			}
			return new BigDecimal(resultado.size());
		} else {
			// TODO REGIDURIAS RP
			for (DTOCasillaAux it : carga.getCasillasWS()) {
				if ( idRegiduria == it.getIdRegiduria() ) {
					resultado.add(it);
				}
			}
			return new BigDecimal(resultado.size());
		}
	}

	/**
	 * Convierte a un objeto de salida para la vista
	 * @param casillasWS
	 * @return
	 */
	public List<DTOCasillaAux> converterDTOCasillaAux(List<DTOCasillaWS> casillasWS) {

		List<DTOCasillaAux> casillas = new ArrayList<DTOCasillaAux>();

		for (DTOCasillaWS it : casillasWS) {
			DTOCasillaAux c = new DTOCasillaAux();
			c.setSeccion(it.getSeccion());
			c.setIdCasilla(it.getIdCasilla());
			c.setTipoCasilla(it.getTipoCasilla());
			c.setExtContigua(it.getExtContigua());
			c.setNombreCasilla(it.getNombreCasilla());
			c.setCasilla(it.getCasilla());
			c.setIdDomicilio(it.getIdDomicilio());
			c.setDomicilio(it.getDomicilio());
			c.setPadronCasilla(it.getPadronCasilla());
			c.setListaNominalCasilla(it.getListaNominalCasilla());
			c.setAprobacionCasillaJunta(it.getAprobacionCasillaJunta());
			c.setAprobacionCasillaConsejo(it.getAprobacionCasillaConsejo());
			c.setFechaAprobacionCasillaJunta(it.getFechaAprobacionCasillaJunta());
			c.setIdDistritoLocal(it.getIdDistritoLocal());
			c.setCabeceraDistritoLocal(it.getCabeceraDistritoLocal());
			c.setIdRegiduria(it.getIdRegiduria());
			c.setNombreRegiduria(it.getNombreRegiduria());
			c.setIdMunicipioLocal(it.getIdMunicipioLocal());
			c.setNombreMunicipio(it.getNombreMunicipio());
			c.setExisteRecuentoTotal(false);
			casillas.add(c);
		}
		return casillas;
	}

	/**
	 * Obtiene el estatus de la casilla a nivel de distrito. </BR>
	 * 
	 * true : Si se va a recuento total. </BR>
	 * false: No se va a recuento total.
	 * 
	 * @param casillasCargaIni
	 * @param distrito
	 * @return
	 */
	public boolean getEstatusActaXdistrito(List<DTOCasillaAux> casillasCargaIni, Integer distrito) {
		// TODO Busca las casillas en base a la seccion
		for (DTOCasillaAux it : casillasCargaIni) {
			if ( it.getIdDistritoLocal() == distrito ) {
				return it.isExisteRecuentoTotal();
			}
		}
		return false;
	}

	/**
	 * Obtiene el estatus de la casilla a nivel demarcacion
	 * @param casillasCargaIni
	 * @param distrito
	 * @return
	 */
	public boolean getEstatusActaXdemarcacion(List<DTOCasillaAux> casillasCargaIni, Integer idRegiduria) {
		// TODO Busca las casillas en base a la seccion
		for (DTOCasillaAux it : casillasCargaIni) {
			if ( it.getIdRegiduria() == idRegiduria ) {
				return it.isExisteRecuentoTotal();
			}
		}
		return false;
	}

}
