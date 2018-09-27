/**
 * 
 */
package mx.ine.computosINE.mb;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.ine.computosINE.bsd.BSDCapturaVotoRPInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.dto.DTOActa;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOCandidato;
import mx.ine.computosINE.dto.DTOCasillaAux;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.form.FormRegistroActa;
import mx.ine.computosINE.dto.helper.DTOClaveCasilla;
import mx.ine.computosINE.dto.helper.HLPCargaInformacion;
import mx.ine.computosINE.helper.HLPInfoGralComputos;
import mx.ine.computosINE.helper.HLPRegistroActa;
import mx.ine.computosINE.util.ComparatorCandidato;
import mx.ine.computosINE.util.UtilUpload;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <code>MBCapturaVotoRegiduriaRP.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 09/05/2017 09:20:00
 */
@Controller("mbCapturaRP")
@Scope("session")
public class MBCapturaVotoRegiduriaRP extends MBGeneric implements Serializable {

	private static final long serialVersionUID = -4146616517120095216L;

	private static final Log log = LogFactory.getLog(MBCapturaVotoRegiduriaRP.class);

	@Autowired
	@Qualifier("hplInfoGralComputos")
	private transient HLPInfoGralComputos hlpComputos;

	@Autowired
	@Qualifier("hplRegistroActa")
	private transient HLPRegistroActa hlp;

	@Autowired
	@Qualifier("bsdCargaInformacion")
	private transient BSDCargaInformacionInterface bsd;

	@Autowired
	@Qualifier("bsdCapturaVotoRP")
	private transient BSDCapturaVotoRPInterface bsdCaptura;

	DTOUsuarioLogin usuario;
	DTOClaveCasilla cc = null;
	private FormRegistroActa form;
	private HLPCargaInformacion carga;
	private HLPCargaInformacion candidatura;
	private StreamedContent imagen = new DefaultStreamedContent();
	private String  action;
	private boolean isModuloAbierto;
	private boolean exitoCaptura = false;

	/**
	 * Valida si el modulo esta cerrado o abierto
	 * @param idModulo
	 * @return
	 * @throws IOException
	 */
	public boolean validaModuloAbierto(String idModulo) throws IOException {
		usuario = obtenUsuario();
		isModuloAbierto = true;
		try {
			isModuloAbierto = hlpComputos.validaModuloAbierto(
					usuario.getIdProcesoElectoral(), 
					usuario.getIdDetalleProceso(), 
					usuario.getIdSistema(), 
					(usuario.getIdEstadoSeleccionado()    != null ? usuario.getIdEstadoSeleccionado()    : 0), 
					(usuario.getIdMunicipioSeleccionado() != null ? usuario.getIdMunicipioSeleccionado() : 0),
					usuario.getRolUsuario(), 
					Integer.valueOf(idModulo));
			return isModuloAbierto;
		} catch( Exception e ){
			return false;
		}
	}

	/**
	 * Inicializa con captura
	 */
	public void initCaptura() {
		action  = "idCaptura";
	}

	/**
	 * Inicializa con consulta
	 */
	public void initConsulta() {
		action  = "idConsulta";
	}

	/**
	 * Inicializa con modifica
	 */
	public void initModifica() {
		action  = "idModifica";
	}

	public void constructorForm() {
		log.info("Entro a MBCapturaVotoRegiduriaRP.constructorForm" );
		try {
			form = new FormRegistroActa();
			form.setActa(new DTOActa());
			candidatura = hlp.getTipoCandidatura(bsd, usuario, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Verifica si aun existen actas pendientes por capturar, si existen lo envia a captura y si no existe lo envia a consulta
	 * @return
	 */
	public boolean existeActaPorCapturar() {
		log.info("Entro a MBCapturaVotoRegiduriaRP.existeActaPorCapturar" );

		try {
			form.setExisteActasXcapturar(false);
			// TODO Carga de informacion inicial
			carga = hlp.cargaInformacionInicialCaptura(bsd, bsdCaptura, usuario, candidatura.getTipoCasillas(), Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
			if ( carga == null ) {
				// TODO Si la carga es nula, indica que no existen casillas especiales para los criterios de busqueda. El proveedor de casillas no devuelve informacion para este tipo de casillas
				return false;
			}
			// TODO Carga las casillas que aun no estan capturadas, las cuales seran las que son suceptibles hacer capturadas
			carga.setCasillasCargaIni(hlp.cargaCasillasNoCaptura(bsdCaptura, carga.getCasillasWS(), usuario, candidatura.getIdTipoCandidatura()));
			if ( carga.getCasillasCargaIni() != null && !carga.getCasillasCargaIni().isEmpty()) {
				// TODO Existen casillas por capturar
				form.setExisteActasXcapturar(true);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al momento de cargar la información de casillas");
			form.setActaValida(false);
			return false;
		}
	}

	/**
	 * Inicializa formulario de registro de acta
	 * @param actionInit
	 */
	public void inicializaForm() {
		log.info("Entro a MBCapturaVotoRegiduriaRP.inicializaForm: " );

		form = new FormRegistroActa();
		form.setActa(new DTOActa());

		try {
			setRequeridosFormIni();
			showBotonAceptar();
			form.setShowPanelVotos(false);

			candidatura = hlp.getTipoCandidatura(bsd, usuario, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
			this.existeActaPorCapturar();
			List<DTOCasillaAux> casillasws = carga != null && carga.getCasillasWS() != null && !carga.getCasillasWS().isEmpty() ? carga.getCasillasWS() : null;

			if ( action.equalsIgnoreCase("idCaptura") ) {
				// TODO Verificar que exista informacion de casillas, las cuales se extraen de servicio de seccionesYcasillasAprobadasPorRegiduria
				if ( carga == null ) {
					agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("mensaje_regVotacion_noExistenCasEspeciales"));
				} else {
					// TODO Carga las casillas que aun no estan capturadas, las cuales seran las que son suceptibles hacer capturadas
					carga.setCasillasCargaIni(hlp.cargaCasillasNoCaptura(bsdCaptura, carga.getCasillasWS(), usuario, candidatura.getIdTipoCandidatura()));
					if ( carga.getCasillasCargaIni() != null && !carga.getCasillasCargaIni().isEmpty()) {
						// TODO Carga las demarcaciones
						carga.setDemarcaciones(hlp.cargaDemarcaciones(carga.getCasillasCargaIni()));

						// TODO Precarga la informacion de idRegidurias, la seccion, la casilla
						hlp.precargaDeActaCapturaRegidRP(bsd, form, carga, action);

						// TODO Calcula Universo Total, para la precarga del avance de casillas
						carga.setUniversoTotCasillas(hlp.calculaUniversoTotal(carga, candidatura.getIdTipoCandidatura(), form.getActa().getIdDistrito(), form.getActa().getIdRegiduria()));

						// TODO Carga el avance de casillas
						this.precargaAvanceCasillas();

						// TODO Fecha 06/06/2017: Para cuando es recuento total se carga los votos del acta tal y cual vienen en la tabla de base de datos,
						// TODO validando que el estatus no sea nulo y se ademas el estatus sea recuento total
						if ( isCampoEstatusActaValido() && form.getActa().getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) ) {
							this.cargaInformacionCasillaConsultaModifica();
						}
					} else {
						carga.setCasillasCargaIni(null);
						Object [] calculos = hlp.getCalculosActas(carga.getUniversoTotCasillas(), carga.getUniversoTotCasillas());
						carga.setAvanceGlobalActasCapt((BigDecimal)calculos[0]);
						carga.setActasCapturadas((BigDecimal)calculos[1]);
						carga.setActasPendPorCapturar((BigDecimal)calculos[2]);
					}
					log.info("lista: " + carga.getCasillasCargaIni());
				}
			} else {
				// TODO Consultar las actas o casillas capturadas
				carga = hlp.cargaInformacionInicialConsulta(bsd, bsdCaptura, usuario, candidatura.getIdTipoCandidatura(), candidatura.getTipoCasillas(), Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
				if ( carga == null ) {
					agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("mensaje_regVotacion_noExistenCasEspeciales"));
				} else {
					if ( carga.getCasillasCargaIni() != null && !carga.getCasillasCargaIni().isEmpty()) {
						// TODO Carga la casillas del proveedor de servicios
						carga.setCasillasWS(casillasws);

						// TODO Cargamos el combo de demarcaciones
						carga.setDemarcaciones(hlp.cargaDemarcaciones(carga.getCasillasCargaIni()));

						// TODO Precarga informacion del acta
						hlp.precargaDeActaCapturaRegidRP(bsd, form, carga, action);

						// TODO Calcula Universo Total, para la precarga del avance de casillas
						carga.setUniversoTotCasillas(hlp.calculaUniversoTotal(carga, candidatura.getIdTipoCandidatura(), form.getActa().getIdDistrito(), form.getActa().getIdRegiduria()));

						// TODO Carga la informacion de casilla para consulta
						this.cargaInformacionCasillaConsultaModifica();
					}
					if ( !form.isExisteActasXcapturar() && action.equalsIgnoreCase("idConsulta")) {
						agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("mensaje_regVotacion_noExistenActasXcapturar"));
					}
				}

				if ( exitoCaptura ) {
					agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_guarda"));
					exitoCaptura = false;
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			form.setActaValida(false);
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al momento de cargar la información de casillas");
		}
	}

	/**
	 * Realiza la carga de secciones en base a una demarcacion seleccionada
	 */
	public void cargaSecciones() {
		log.info("Entro a MBCapturaVotoRegiduriaRP.cargaSecciones: " );
		carga.setSecciones(null);
		carga.setCasillas(null);
		carga.setEstatusCasilla(null);
		form.getActa().setSeccion(null);
		form.getActa().setDesEstatusActa(null);
		try {
			showBotonAceptar();
			form.setShowPanelVotos(false);
			setRequeridosForm();

			if ( isCampoRegiduriaValido() ) {
				carga.setSecciones(hlp.getCargaSeccionesXdemarcaciones(carga.getCasillasCargaIni(), form.getActa().getIdRegiduria()));
			} else {
				form.setRequiredRegiduria(true);
				form.getActa().setIdRegiduria(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			form.setActaValida(false);
			e.printStackTrace();
		}
	}

	/**
	 * Realiza la carga e casillas, dependiendo de la seccion seleccionada
	 */
	public void cargaCasillas() {
		log.info("Entro a MBCapturaVotoRegiduriaRP.cargaCasillas " );
		carga.setCasillas(null);
		form.getActa().setIdEstatusActa(null);
		form.getActa().setDesEstatusActa(null);
		form.setShowPanelVotos(false);

		try {
			showBotonAceptar();
			setRequeridosForm();
			if ( isCampoSeccionValido() ) {
				carga.setCasillas(hlp.getCargaCasillasXseccion(carga.getCasillasCargaIni(), form.getActa().getSeccion()));
			} else {
				form.setRequiredSeccion(true);
				form.getActa().setSeccion(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			form.setActaValida(false);
			e.printStackTrace();
		}
	}

	/**
	 * Carga el catalogo de estatus del acta
	 * @throws Exception 
	 */
	public void cargaEstatusActa() throws Exception {
		// TODO Carga catalogo de estatus de la casilla
		carga.setEstatusCasilla(null);
		form.getActa().setIdEstatusActa(null);
		form.getActa().setDesEstatusActa(null);
		form.setShowPanelVotos(false);
		showBotonAceptar();
		setRequeridosForm();
		obtenSelectItemCasilla();
		if ( isCampoCveCasillaValido() ) {
			carga.setEstatusCasilla(hlp.getEstatusActaFiltrado(bsd.estatusComputos()));
		}
	}

	/**
	 * Cuando es captura debe de cargar los datos del combo de estatus
	 * si es consulta carga la etiqueta del estatus y la informacion del acta consultada,
	 * si es modifica debe cargar la informacion del acta y el combo de estatus,
	 * precargando el estatus seleccionado del acta consultada
	 */
	public void cargaInformacionEnCasilla() {
		try {
			if ( action.equalsIgnoreCase("idCaptura") ) {
				cargaEstatusActa();
			} else {
				this.cargaInformacionCasillaConsultaModifica();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			form.setActaValida(false);
			e.printStackTrace();
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al momento de cargar la información de candidatos");
		}
	}

	/**
	 * Si es captura carga la informacion de los candidatos,
	 * si es consulta o modifica solo valida el boton de aceptar
	 */
	public void cargaInformacionEnEstatus() {
		try {
			if ( action.equalsIgnoreCase("idCaptura") ) {
				this.cargaInformacionCasillaCaptura();
			} else {
				showBotonAceptar();
				if ( isCampoEstatusActaValido() && carga.getCandidatos() != null && !carga.getCandidatos().isEmpty() ) {
					if ( form.getActa().getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_CasillaNoInstalada")) ||
						 form.getActa().getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_PaqueteNoEntregado")) ) {
						// TODO Cuando seleccionamos el estatus de casilla no instalado y paquete no entregado limpia los votos
						hlp.limpiaVotoCandidato(carga.getCandidatos());
					} else {
						// TODO Carga la informacion normal de consulta o modifica
						this.cargaInformacionCasillaConsultaModifica();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			form.setActaValida(false);
			e.printStackTrace();
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, "Error al momento de cargar la información de candidatos");
		}
	}

	/**
	 * Carga informacion de la casilla.
	 * @throws Exception 
	 */
	public void cargaInformacionCasillaCaptura() throws Exception {
		log.info("Entro a MBCapturaVotoRegiduriaRP.cargaInformacionCasillaCaptura " );
		form.setShowPanelVotos(false);
		carga.setCandidatos(null);
		showBotonAceptar();
		setRequeridosForm();

		if ( isCampoRegiduriaValido() && isCampoSeccionValido() && isCampoCveCasillaValido() && isCampoEstatusActaValido() ) {
			obtenSelectItemCasilla();
			cc = new DTOClaveCasilla();
			// TODO Extraemos la clave de la casilla
			// TODO cc = [Seccion, TipoCasilla, IdCasilla, ExtContigua]
			cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla().trim());
			// TODO Agregar IdTipoCandidatura y IdRegiduria como datos extra
			cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
			cc.setIdRegiduria(form.getActa().getIdRegiduria());

			// TODO Realizar Calculo del Avance de las casillas
			Object [] calculos = hlp.cargaCalculos(bsdCaptura, usuario, carga.getUniversoTotCasillas(), cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
			carga.setAvanceGlobalActasCapt((BigDecimal)calculos[0]);
			carga.setActasCapturadas((BigDecimal)calculos[1]);
			carga.setActasPendPorCapturar((BigDecimal)calculos[2]);

			// TODO Carga los candidatos
			carga.setCandidatos(hlp.cargaCandidatos(bsd, usuario, null, form.getActa().getIdRegiduria(), candidatura.getIdTipoCandidatura()));

			// TODO Precarga Candidatos No Registrados y Votos nulos
			carga.getCandidatos().add(hlp.cargaCandidatoNoRegidtrado(usuario, candidatura.getIdTipoCandidatura()));
			carga.getCandidatos().add(hlp.cargaVotosNulos(usuario, candidatura.getIdTipoCandidatura()));

			// TODO Cacula votos acumulados por asociacion
			hlp.getVotosAcumuladosXasociacion(bsdCaptura, usuario, carga.getCandidatos(), cc);

			if ( carga.getCandidatos() != null && !carga.getCandidatos().isEmpty() ) {
				// TODO Ordena candidatos
				Collections.sort(carga.getCandidatos(), new ComparatorCandidato());
			}
			// TODO Carga la informacion de la casilla
			form.getActa().setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
			form.getActa().setDescTipoCandidatura(candidatura.getDescTipoCandidatura());
			form.setShowPanelVotos(true);
		}
	}

	/**
	 * Carga informacion de la casilla.
	 * @throws Exception 
	 */
	public void cargaInformacionCasillaConsultaModifica() throws Exception {
		log.info("Entro a MBCapturaVotoRegiduriaRP.cargaInformacionCasillaConsultaModifica" );
		Integer idEstatusSelected = null;
		form.setShowPanelVotos(false);
		carga.setCandidatos(null);
		carga.setEstatusCasilla(null);
		showBotonAceptar();
		showBotonEliminar();
		setRequeridosForm();
		form.getActa().setDesEstatusActa(null);

		if ( isCampoRegiduriaValido() && isCampoSeccionValido() && isCampoCveCasillaValido() ) {

			if ( action.equalsIgnoreCase("idModifica") && this.isCampoEstatusActaValido()) {
				// TODO Identificador del estatus de la casilla provisional, se ocupara solo para comparar contra el estatus que trae la consulta
				idEstatusSelected = form.getActa().getIdEstatusActa();
			}
			
			obtenSelectItemCasilla();

			cc = new DTOClaveCasilla();
			cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla());
			cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
			cc.setIdRegiduria(form.getActa().getIdRegiduria());

			// TODO Realizar Calculo del Avance de las casillas
			Object [] calculos = hlp.cargaCalculos(bsdCaptura, usuario, carga.getUniversoTotCasillas(), cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
			carga.setAvanceGlobalActasCapt((BigDecimal)calculos[0]);
			carga.setActasCapturadas((BigDecimal)calculos[1]);
			carga.setActasPendPorCapturar((BigDecimal)calculos[2]);

			// TODO Consulta de acta votos capturada
			List<DTOActaCasillaVotos> actaVotos = hlp.getActaCapturada(bsdCaptura, usuario, cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));

			// TODO Carga catalogo estatus de la casilla, dependiendo el estatus que traiga el acta.
			if ( actaVotos != null && !actaVotos.isEmpty() && actaVotos.get(0).getIdEstatus().intValue() != Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) ) {
				carga.setEstatusCasilla(hlp.getEstatusActaFiltrado(bsd.estatusComputos()));
			} else {
				carga.setEstatusCasilla(hlp.getEstatusRecuentoTotal(bsd.estatusComputos()));
				form.getActa().setIdEstatusActa(actaVotos.get(0).getIdEstatus().intValue());
			}

			// TODO Valida el estatus seleccionado para el caso de modifica
			if ( idEstatusSelected != null && idEstatusSelected != actaVotos.get(0).getIdEstatus() ) {
				// TODO Aplica para cuando es modifica
				form.getActa().setIdEstatusActa(idEstatusSelected);
				// TODO Set de la descripcion del estatus del acta que se mostrara como una etiqueta en la vista de consulta
				form.getActa().setDesEstatusActa(hlp.getDescEstausActa(carga.getEstatusCasilla(), idEstatusSelected));
			} else {
				form.getActa().setIdEstatusActa(actaVotos.get(0).getIdEstatus());
				// TODO Set de la descripcion del estatus del acta que se mostrara como una etiqueta en la vista de consulta
				form.getActa().setDesEstatusActa(hlp.getDescEstausActa(carga.getEstatusCasilla(), actaVotos.get(0).getIdEstatus()));
			}
			form.getActa().setListaNominal(actaVotos.get(0).getListaNominal());

			// TODO Carga los candidatos
			carga.setCandidatos(hlp.cargaCandidatosConsulta(bsd, bsdCaptura, usuario, actaVotos, cc));

			if ( idEstatusSelected != null && idEstatusSelected != actaVotos.get(0).getIdEstatus() && action.equalsIgnoreCase("idModifica") ) {
				// TODO Aplica para cuando es modifica
				hlp.limpiaVotoCandidato(carga.getCandidatos());
			}

			if ( carga.getCandidatos() != null && !carga.getCandidatos().isEmpty() ) {
				// TODO Ordena candidatos
				Collections.sort(carga.getCandidatos(), new ComparatorCandidato());
			}

			// TODO Carga la informacion de la casilla
			form.getActa().setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
			form.getActa().setDescTipoCandidatura(candidatura.getDescTipoCandidatura());
			form.setShowPanelVotos(true);
			showBotonAceptar();
			showBotonEliminar();
			if ( form.isActaBloqueada() && form.getActa().getIdEstatusActa().intValue() != Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal"))) {
				agregaMensaje(
						TipoMensaje.INFO_MENSAJE.getTipo(),
						"idMsj", 
						(action.equalsIgnoreCase("idConsulta") ?  
						Utilidades.mensajeProperties("mensaje_regVotacion_msjActaConDistElim") : 
						Utilidades.mensajeProperties("mensaje_regVotacion_msjActaConDistMod")));
			} else if ( isCampoEstatusActaValido() && action.equalsIgnoreCase("idConsulta") && form.getActa().getIdEstatusActa().intValue() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) ) {
				if ( form.isActaBloqueada() ) {
					agregaMensaje(
							TipoMensaje.INFO_MENSAJE.getTipo(),
							"idMsj", 
							(action.equalsIgnoreCase("idConsulta") ?  
							Utilidades.mensajeProperties("mensaje_regVotacion_msjActaGenerElim") : 
							Utilidades.mensajeProperties("mensaje_regVotacion_msjActaGenerMod")));
				}
				agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "idMsj", Utilidades.mensajeProperties("mensaje_regVotacion_msjActaRecuento"));
			}
		}
	}

	/**
	 * Realiza la precarga del avance de casillas
	 */
	public void precargaAvanceCasillas() {
		try {
			if ( isCampoRegiduriaValido() && isCampoSeccionValido() && isCampoCveCasillaValido() ) {
				obtenSelectItemCasilla();
				cc = new DTOClaveCasilla();
				// TODO Extraemos la clave de la casilla
				// TODO cc = [Seccion, TipoCasilla, IdCasilla, ExtContigua]
				cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla().trim());
				// TODO Agregar IdTipoCandidatura y IdRegiduria
				cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
				cc.setIdRegiduria(form.getActa().getIdRegiduria());
				// TODO Realizar Calculo del Avance de las casillas
				Object [] calculos = hlp.cargaCalculos(bsdCaptura, usuario, carga.getUniversoTotCasillas(), cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
				carga.setAvanceGlobalActasCapt((BigDecimal)calculos[0]);
				carga.setActasCapturadas((BigDecimal)calculos[1]);
				carga.setActasPendPorCapturar((BigDecimal)calculos[2]);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			form.setActaValida(false);
			log.info("Error al carga el avance de casillas: " + e.getMessage());
		}
	}

	/**
	 * Registra acta
	 * @return
	 */
	public String guardar() {
		log.info("Entro a guardar ");
		try {
			if ( !isValidaListaCandidato() ) {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "idMsjReq", Utilidades.mensajeProperties("validacion_mensajes_generales_dato_requerido"));
				return "";
			}
			//TODO Guarda acta
			bsdCaptura.guardar(hlp.formatoEntityGuardar(form.getActa(), carga.getCandidatos(), usuario, Utilidades.mensajeProperties("constante_modulo_regiduriasRP")));
			exitoCaptura = true;
			action  = "idConsulta";
			return "consulta";
		} catch ( Exception e ) {
			log.info("Error al guardar registro: " + e.getMessage());
			exitoCaptura = false;
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_no_confirmacion_guarda"));
			return "";
		}
	}

	/**
	 * Actualiza acta
	 * @return
	 */
	public String modificar() {
		log.info("Entro a modificar ");
		try {
			if ( !isValidaListaCandidato() ) {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "idMsjReq", Utilidades.mensajeProperties("validacion_mensajes_generales_dato_requerido"));
				return "";
			}
			//TODO Modifica acta
			bsdCaptura.guardar(hlp.formatoEntityGuardar(form.getActa(), carga.getCandidatos(), usuario, Utilidades.mensajeProperties("constante_modulo_regiduriasRP")));
			agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_modifica"));
			carga.setSecciones(null);
			carga.setCasillas(null);
			carga.setEstatusCasilla(null);
			form = new FormRegistroActa();
			form.setActa(new DTOActa());
			carga.setCandidatos(new ArrayList<DTOCandidato>());
			return "";
		} catch ( Exception e ) {
			log.info("Error al modificar el registro: " + e.getMessage());
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_no_confirmacion_modifica"));
			return "";
		}
	}

	/**
	 * Elimina registro de acta
	 * Regla: Solo se puede eliminar cuando el acta no tiene Distribucion, una vez generada la distribucion ya no es posible eliminar el acta
	 * @return
	 */
	public String eliminar() {

		log.info("Entro a eliminar ");
		try {
			// TODO Valida que exista la acta a eliminar en la base de datos
			cc = new DTOClaveCasilla();
			cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla());
			cc.setIdRegiduria(form.getActa().getIdRegiduria());
			cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());

			List<DTOActaCasillaVotos> actaVotos  = hlp.getActaCapturada(bsdCaptura, usuario, cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP"));
			if ( actaVotos == null || actaVotos.isEmpty() ) {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_registro_no_existe"));
				return "";
			}

			// TODO Valida que no se haya realizado la distribucion para esa acta.
			if ( hlp.existeDistribucionOgeneracionActa(bsdCaptura, usuario, cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP")) ) {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("mensaje_regVotacion_msjActaGenerElim"));
				return "";
			}
			bsdCaptura.eliminar(actaVotos);
			agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesExito", Utilidades.mensajeProperties("validacion_mensaje_generales_confirmacion_elimina"));
			return "consulta";
		} catch ( Exception e ) {
			log.info("Error al modificar registro: " + e.getMessage());
			agregaMensaje(TipoMensaje.ERROR_MENSAJE, Utilidades.mensajeProperties("validacion_mensaje_generales_no_confirmacion_elimina"));
			return "";
		}
	}

	/**
	 * Obtener itemLabel del selectItems para el selectOneMenu del combo de casillas
	 * @param event
	 */
	public void obtenSelectItemCasilla() {
		if ( carga.getCasillasCargaIni() != null && !carga.getCasillasCargaIni().isEmpty() ) {
			for (DTOCasillaAux it : carga.getCasillas() ) {
				if (form.getActa().getCveCasilla().toString().trim().equalsIgnoreCase(hlp.construyeCveCasilla(it.getSeccion(), it.getTipoCasilla(), it.getIdCasilla(), it.getExtContigua())) ) {
					form.getActa().setNombreCasilla(it.getNombreCasilla());
					form.getActa().setListaNominal(it.getListaNominalCasilla());
					break;
				}
			}
		}
	}

	/**
	 * Set de los valores que son requeridos dentro del formulario
	 */
	public void setRequeridosForm() {
		if ( !isCampoRegiduriaValido() ) {
			form.setRequiredRegiduria(true);
		}
		if ( !isCampoSeccionValido() ) {
			form.setRequiredSeccion(true);
		}
		if ( !isCampoCveCasillaValido() ) {
			form.setRequiredCasilla(true);
		}
		if ( !isCampoEstatusActaValido() ) {
			form.setRequiredEstatusActa(true);
		}
	}

	/**
	 * Habilita campos requeridos en la vista
	 */
	public void setRequeridosFormIni() {
		form.setRequiredRegiduria(true);
		form.setRequiredSeccion(true);
		form.setRequiredCasilla(true);
		form.setRequiredEstatusActa(true);
	}

	/**
	 * Set del acta valida
	 * @throws Exception 
	 */
	public void showBotonAceptar() throws Exception {
		form.setActaValida(false);
		form.setActaBloqueada(false);
		form.setDisabledEstatus(false);
		if ( action.equalsIgnoreCase("idCaptura") ) {
			if ( isCampoRegiduriaValido()  && isCampoSeccionValido() && isCampoCveCasillaValido() && isCampoEstatusActaValido() ) {
				form.setActaValida(true);
			}
		} else if ( action.equalsIgnoreCase("idConsulta") ) {
			if ( isCampoRegiduriaValido()  && isCampoSeccionValido() && isCampoCveCasillaValido() ) {
				form.setActaValida(true);
				cc = new DTOClaveCasilla();
				cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla());
				cc.setIdRegiduria(form.getActa().getIdRegiduria());
				cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
				// TODO Valida que no se haya realizado la Distribucion para esa acta.
				if ( hlp.existeDistribucionOgeneracionActa(bsdCaptura, usuario, cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP")) ) {
					form.setActaValida(false);
					form.setActaBloqueada(true);
				}
				if ( !isModuloAbierto ) {
					form.setActaValida(false);
					form.setActaBloqueada(true);
				}
			}
		} else {
			if ( isCampoRegiduriaValido()  && isCampoSeccionValido() && isCampoCveCasillaValido() && isCampoEstatusActaValido() ) {
				form.setActaValida(true);
				cc = new DTOClaveCasilla();
				cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla());
				cc.setIdRegiduria(form.getActa().getIdRegiduria());
				cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
				// TODO Valida que no se haya realizado la distribucion para esa acta.
				if ( hlp.existeDistribucionOgeneracionActa(bsdCaptura, usuario, cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP")) ) {
					form.setActaValida(false);
					form.setDisabledEstatus(true);
					form.setActaBloqueada(true);
				}
				if ( !isModuloAbierto ) {
					form.setActaValida(false);
					form.setDisabledEstatus(true);
					form.setActaBloqueada(true);
				}
			} else {
				form.getActa().setIdEstatusActa(0);
			}
		}
	}

	/**
	 * Set del acta valida
	 * @throws Exception 
	 */
	public void showBotonEliminar() throws Exception {
		if ( action.equalsIgnoreCase("idConsulta") ) {
			form.setActaValida(false);
			form.setActaBloqueada(false);
			if ( isCampoRegiduriaValido()  && isCampoSeccionValido() && isCampoCveCasillaValido() ) {
				form.setActaValida(true);
				cc = new DTOClaveCasilla();
				cc = hlp.getCleveCompuestaCasilla(form.getActa().getCveCasilla());
				cc.setIdRegiduria(form.getActa().getIdRegiduria());
				cc.setIdTipoCandidatura(candidatura.getIdTipoCandidatura());
				// TODO Valida que no se haya realizado la distribucion para esa acta.
				if ( hlp.existeDistribucionOgeneracionActa(bsdCaptura, usuario, cc, Utilidades.mensajeProperties("constante_modulo_regiduriasRP")) ) {
					form.setActaValida(false);
					form.setActaBloqueada(true);
				}
				if ( !isModuloAbierto ) {
					form.setActaValida(false);
					form.setActaBloqueada(true);
				}
				// TODO Regla: Las casillas con estatus de recuento total ya no se pueden eliminar
				if ( isCampoEstatusActaValido() && form.getActa().getIdEstatusActa().intValue() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_recuentoTotal")) ) {
					form.setActaValida(false);
					form.setActaBloqueada(true);
				}
			}
		}
	}

	/**
	 * Valida que en la lista de votos por candidato no se vaya ni un valor nulo
	 */
	public boolean isValidaListaCandidato() {

		if ( form.getActa().getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_CasillaNoInstalada")) || 
			 form.getActa().getIdEstatusActa() == Integer.valueOf(Utilidades.mensajeProperties("constante_estatus_PaqueteNoEntregado"))) {
			return true;
		}

		for (DTOCandidato it : carga.getCandidatos()) {
			if ( it.getVotosAux() == null || it.getVotosAux().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Valida que el campo regiduria tenga un valor valido. </BR>
	 * true  = si es valido,
	 * false = No es valido
	 * @return
	 */
	public boolean isCampoRegiduriaValido() {

		if ( form.getActa().getIdRegiduria() != null && form.getActa().getIdRegiduria() > 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * Valida que el campo seccion tenga un valor valido. </BR>
	 * true  = si es valido,
	 * false = No es valido
	 * @return
	 */
	public boolean isCampoSeccionValido() {
		if ( form.getActa().getSeccion() != null && form.getActa().getSeccion() > 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * Valida que el campo casilla tenga un valor valido. </BR>
	 * true  = si es valido,
	 * false = No es valido
	 * @return
	 */
	public boolean isCampoCveCasillaValido() {

		if ( form.getActa().getCveCasilla() != null && 
			!form.getActa().getCveCasilla().trim().isEmpty() && 
			!form.getActa().getCveCasilla().trim().equalsIgnoreCase("0") ) {
			return true;
		}
		return false;
	}

	/**
	 * Valida que el campo estatus del acta tenga un valor valido. </BR>
	 * true  = si es valido,
	 * false = No es valido
	 * @return
	 */
	public boolean isCampoEstatusActaValido() {
		if ( form.getActa().getIdEstatusActa() != null && form.getActa().getIdEstatusActa() > 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * Elimina ceros a la izquierda.
	 * @param c
	 */
	public void quitaCerosLeft(DTOCandidato c) {
		Integer v = null;

		if (c.getVotosAux() != null && !c.getVotosAux().trim().isEmpty()) {
			v = Integer.valueOf(c.getVotosAux());
			c.setVotosAux(v.toString());
		}
	}
	/* ******************	set/get	**********************************	*/

	/**
	 * @return the form
	 */
	public FormRegistroActa getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(FormRegistroActa form) {
		this.form = form;
	}

	/**
	 * @return the carga
	 */
	public HLPCargaInformacion getCarga() {
		return carga;
	}

	/**
	 * @param carga the carga to set
	 */
	public void setCarga(HLPCargaInformacion carga) {
		this.carga = carga;
	}

	/**
	 * @return the candidatura
	 */
	public HLPCargaInformacion getCandidatura() {
		return candidatura;
	}

	/**
	 * @param candidatura the candidatura to set
	 */
	public void setCandidatura(HLPCargaInformacion candidatura) {
		this.candidatura = candidatura;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the isModuloAbierto
	 */
	public boolean isModuloAbierto() {
		return isModuloAbierto;
	}

	/**
	 * @param isModuloAbierto the isModuloAbierto to set
	 */
	public void setModuloAbierto(boolean isModuloAbierto) {
		this.isModuloAbierto = isModuloAbierto;
	}

	/**
	 * @return the exitoCaptura
	 */
	public boolean isExitoCaptura() {
		return exitoCaptura;
	}

	/**
	 * @param exitoCaptura the exitoCaptura to set
	 */
	public void setExitoCaptura(boolean exitoCaptura) {
		this.exitoCaptura = exitoCaptura;
	}

	/**
	 * @return the imagen
	 */
	public StreamedContent getImagen() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String idCandidato = externalContext.getRequestParameterMap().get("id_candidato"); 
		String rutaEmblema = externalContext.getRequestParameterMap().get("emblema");

		if ( idCandidato == null ) {
            return new DefaultStreamedContent();
        } else {
			this.imagen = UtilUpload.getImagenStreamedContent(rutaEmblema);
			return this.imagen;
        }
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}
}
