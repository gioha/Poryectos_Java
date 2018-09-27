package mx.ine.computosINE.helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.computosINE.util.Utilidades;

public class HLPGeneracionActasMensajes {

	/**
	 * Método para mensajes de validación
	 */
	public void mensajesValidacion(Integer idMensaje) {
		switch (idMensaje) {
		// Mensaje de seleccionar distrito, para combo de distritos en DIPUTADOS
		case 1:
			FacesMessage msjseleccionaDistrito = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_seleccionar_distrito"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjseleccionaDistrito);
			break;
		// Mensaje de seleccionar demarcación, para combo de demarcaciones en
		// REGIDURIAS
		case 2:
			FacesMessage msjseleccionaDemarcacion = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_seleccionar_demarcacion"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjseleccionaDemarcacion);
			break;
		// Mensaje para vista capturar cuando el acta ha sido generada
		case 3:
			FacesMessage msjGeneradoAyunGob = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_captura"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjGeneradoAyunGob);
			break;
		// Mensaje para vista consultar y modificar, cuando el acta no ha sido
		// generada
		case 4:
			FacesMessage msjNoGeneradoAyuGob = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_no_generada_con_mod"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjNoGeneradoAyuGob);
			break;
		// Mensaje de validacion para cuando aún no haya distritos disponibles
		// para la generación del acta parcial
		case 5:
			FacesMessage msjDistritoMunCap = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_distritos_cap_votos"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistritoMunCap);
			break;
		// Mensaje de validacion cuando los ditritos ya fueron capturados
		case 6:
			FacesMessage msjDistritoCon = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_distritos_cap_con"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistritoCon);
			break;
		// Mensaje de validacion cuando no hay distritos por consultar o
		// modificar
		case 7:
			FacesMessage msjDistritoConCap = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_distritos_con_cap"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistritoConCap);
			break;
		// Mensaje de validacion cuando no hay demarcaciones disponibles, hasta
		// que la distribucion de votos se haya realizado
		case 8:
			FacesMessage msjDemarcacionCap = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_demarcaciones_distri"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDemarcacionCap);
			break;
		// Mensaje de validacion cuando todas las demarcaciones ya fueron
		// capturadas
		case 9:
			FacesMessage msjDemarcacionCon = new FacesMessage(
					Utilidades
							.mensajeProperties("valdiacion_mensaje_generales_acta_demarcaciones_cap_con"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDemarcacionCon);
			break;
		// Mensaje de validacion cuando no hay demarcaciones por consultar
		case 10:
			FacesMessage msjDemarcacionConCap = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_demarcaciones_con_cap"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDemarcacionConCap);
			break;
		// Mensaje de validacion para OPLE cuando entra a la captura de un acta
		// parcial generada por capturista municipal
		case 11:
			FacesMessage msjGeneradoParcial = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_generada_cap"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjGeneradoParcial);
			break;
		// Mensaje de validacion cuando el acta parcial ha sido generada, y vaya
		// a consultar. Sólo el CAPTURISTA MUNICIPAL puede modificarla
		case 12:
			FacesMessage msjGeneradoModAyuGob = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_generada_mod_ople"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjGeneradoModAyuGob);
			break;
		// Mensaje de validacion, el acta parcial no ha sido generada, sólo el
		// CAPTURISTA MUNICIPAL puede generarla
		case 13:
			FacesMessage msjNoGenerado = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_no_generada_ople"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjNoGenerado);
			break;
		// Mensaje cuando no se ha generado ningún acta parcial, para diputados
		// y regidurias
		case 14:
			FacesMessage msjDisDemNoCap = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_no_generada_dist_reg"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDisDemNoCap);
			break;
		// Mensaje para el OPLE al ir a vista capturar de actas parciales
		case 15:
			FacesMessage msjCapParcial = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_cap_mun_genera"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjCapParcial);
			break;
		// Mensaje para el OPLE al ir a vista consultar de actas parciales
		case 16:
			FacesMessage msjModParcial = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_cap_mun_modifica"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjModParcial);
			break;
		// Mensaje de validacion cuando no hay distritos disponibles en la
		// generacion de acta final
		case 17:
			FacesMessage msjDistritoDistCap = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_ditritos_distribucion"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistritoDistCap);
			break;
		// Mensaje de validacion para OPLE cuando entra a un acta final de
		// ayuntamiento
		case 18:
			FacesMessage msjGeneradoFinalAyu = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_final_no_generada_cap_mun"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjGeneradoFinalAyu);
			break;
		// Mensaje de validacion para OPLE en vista modificar de acta final de
		// ayuntamiento
		case 19:
			FacesMessage msjGeneradoModFinalAyu = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_final_generada_cap_mun_mod"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjGeneradoModFinalAyu);
			break;
		// Acta guardada exitosamente
		case 20:
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"growl",
							new FacesMessage(
									"",
									Utilidades
											.mensajeProperties("valdiacion_mensaje_generales_acta_guardada_exitosamente")));
			break;
		// Validacion de imprimir si la modificacion del acta no ha sido
		// guardada CM
		case 21:
			FacesMessage msjValidaImprimir = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_imprimir_valida"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjValidaImprimir);
			break;
		// Mensaje cuando el acta fue eliminada exitosamente
		case 22:
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"growl",
							new FacesMessage(
									"",
									Utilidades
											.mensajeProperties("validacion_mensaje_generales_acta_eliminada_exitosamente")));
			break;
		// Mensaje de habilitar edición correctamente
		case 23:
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"growl",
							new FacesMessage(
									"",
									Utilidades
											.mensajeProperties("validacion_mensaje_generales_acta_edicion_habilitada")));
			break;
		// Validacion de imprimir si la modificacion del acta no ha sido
		// guardada OPLE
		case 24:
			FacesMessage msjValidaImprimirOPLE = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_imprimir_valida_ople"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjValidaImprimirOPLE);
			break;
		// Mensaje informativo para vista modificar para el usuario CM, cuando
		// requiere permisos de OPLE para modificar
		case 25:
			FacesMessage msjPermisosOPLEModificar = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_modificar_permisos_ople"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjPermisosOPLEModificar);
			break;
		// Mensaje de validacion para generar actas finales, requiere
		// distribucion
		case 26:
			FacesMessage msjDistribucionRequerida = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_final_requiere_distribucion"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionRequerida);
			break;
		// Mensaje de validacion para generar actas parciales, requiere captura
		// 100/100
		case 27:
			FacesMessage msjCapturaRequerida = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_parcial_requiere_captura_100"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjCapturaRequerida);
			break;
		// Mensaje de validacion para generar actas finales, requiere actas
		// parciales generadas por todos los municipios
		case 28:
			FacesMessage msjParcialesRequeridas = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_final_requiere_parciales"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjParcialesRequeridas);
			break;
		case 29:
			FacesMessage msjPdfFallo = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_fallo_exportar_pdf"));
			FacesContext.getCurrentInstance()
					.addMessage("mensaje", msjPdfFallo);
			break;
		case 30:
			FacesMessage msjPdfFalloListas = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_fallo_datos_pdf"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjPdfFalloListas);
			break;
		case 31:
			FacesMessage msjPermisosConsulta = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_usuario_consulta"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjPermisosConsulta);
			break;
		case 32:
			FacesMessage msjPermisosConsultaNoGen = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_usuario_consulta_no_generada"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjPermisosConsultaNoGen);
			break;
		case 33:
			FacesMessage msjDistribucionNoPosible = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_no_posible"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionNoPosible);
			break;
		case 34:
			FacesMessage msjNoEliminaParcial = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_informa_no_elimina_parcial"), "");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjNoEliminaParcial);
			break;
		case 35:
			FacesMessage msjDistribucionConsulta = new FacesMessage("",
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_realizada"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionConsulta);
			break;
		case 36:
			/*FacesContext
			.getCurrentInstance()
			.addMessage(
					"mensajesExito",
					new FacesMessage(
							"",
							Utilidades
									.mensajeProperties("validacion_mensaje_generales_distribucion_no_regidurias")));
			break;*/
			FacesMessage msjDistribucionNoReg = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_no_regidurias"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionNoReg);
			break;
		case 37:
			FacesContext
			.getCurrentInstance()
			.addMessage(
					"mensajesExito",
					new FacesMessage(
							"",
							Utilidades
									.mensajeProperties("validacion_mensaje_generales_distribucion_guardada")));
			break;
		case 38:
			FacesMessage msjDistribucionGenerada = new FacesMessage(Utilidades.mensajeProperties("validacion_mensaje_generales_distribucion_generada"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionGenerada);
			break;
		case 39:
			FacesMessage msjDistribucionActasNoCapturadas = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_no_posible"),
					"");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionActasNoCapturadas);
			break;
		case 40:
			FacesMessage msjDistribucionNoParciales = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_no_parciales"),
					"");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionNoParciales);
			break;
		case 41:
			FacesMessage msjDistribucionNoDis = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_no_distritos"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionNoDis);
			break;
//			FacesContext
//			.getCurrentInstance()
//			.addMessage(
//					"mensajesExito",
//					new FacesMessage(
//							"",
//							Utilidades
//									.mensajeProperties("validacion_mensaje_generales_distribucion_no_distritos")));
//			break;
		case 42:
			FacesMessage msjDistribucionExisteActa = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_distribucion_hay_acta"),
					"");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistribucionExisteActa);
			break;
		case 43:
			FacesContext
			.getCurrentInstance()
			.addMessage(
					"mensajesExito",
					new FacesMessage(
							"",
							Utilidades
									.mensajeProperties("validacion_mensaje_generales_distribucion_eliminada")));
			break;
		case 50:
			FacesMessage msjDistParcGobNo = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_dist_parcial_falla"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistParcGobNo);
			break;

		case 51:
			FacesMessage msjCapDemar = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_demarcaciones_cap_votos"));
			FacesContext.getCurrentInstance()
					.addMessage("mensaje", msjCapDemar);
			break;

		case 52:
			FacesMessage msjErrorAsoc = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_error_obteniendo_asociaciones"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjErrorAsoc);
			break;

		case 53:
			FacesMessage msjErrorInitDatos = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_error_inicializando_datos"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjErrorInitDatos);
			break;

		case 54:
			FacesMessage msjErrorEliminaActa = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_error_eliminando_acta"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjErrorEliminaActa);
			break;
		case 55:
			FacesMessage msjFaltanDatos = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_datos_requeridos_faltantes"),
					"");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjFaltanDatos);
			break;
		case 56:
			FacesMessage msjFaltanDistribucionesMR = new FacesMessage(
					
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_no_distribuciones_diputados_rp_estatal"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjFaltanDistribucionesMR);
			break;	
		case 57:
			FacesMessage msjNoEliminaPorDistGen = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_no_elimina_por_dist_generada"),
					"");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjNoEliminaPorDistGen);
			break;
		case 58:
			FacesMessage msjDistritoRPFaltaMR = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_distritos_mr_necesarios"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDistritoRPFaltaMR);
			break;
		case 59:
			FacesMessage msjDemarcacionesRPFaltaMR = new FacesMessage(
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_acta_regidurias_mr_necesarios"));
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjDemarcacionesRPFaltaMR);
			break;
		case 60:
			FacesMessage msjErrorGuardaActa = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Utilidades
							.mensajeProperties("validacion_mensaje_generales_error_guarda_acta"),
					"");
			FacesContext.getCurrentInstance().addMessage("mensaje",
					msjErrorGuardaActa);
			break;
		case 61:
		FacesMessage msjErrorObtenActa = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				Utilidades
						.mensajeProperties("validacion_mensaje_generales_error_obtener_acta"),
				"");
		FacesContext.getCurrentInstance().addMessage("mensaje",
				msjErrorObtenActa);
		break;
		// Mensaje de validacion para cuando no existen casillas especiales en el municipio
		// para la generación del acta parcial de diputados rp
		case 62:
		FacesMessage msjMunSinCasillas = new FacesMessage(
				Utilidades
						 .mensajeProperties("validacion_mensaje_generales_acta_distritos_acta_sin_especial_dip_rp"));
				FacesContext.getCurrentInstance().addMessage("mensaje",
						msjMunSinCasillas);
		break;
		// Mensaje de validacion para cuando no existen casillas especiales en el municipio
	    // para la generación del acta parcial de REGIDURIAS rp
		case 63:
		FacesMessage msjMunSinCasillasRegRP = new FacesMessage(
				Utilidades
						  .mensajeProperties("validacion_mensaje_generales_acta_distritos_acta_sin_especial_reg_rp"));
				FacesContext.getCurrentInstance().addMessage("mensaje",
						msjMunSinCasillasRegRP);
		break;
		// Mensaje de validacion para actas generadas RP Final
		case 64:
		FacesMessage msjDiputadoRPFinalValidar = new FacesMessage(
				Utilidades
						  .mensajeProperties("validacion_mensaje_generales_no_generadas_diputados_rp_estatal"));
				FacesContext.getCurrentInstance().addMessage("mensaje",
						msjDiputadoRPFinalValidar);
		break;
		// Defualt - nada
		default:
			break;
		}
	}

}
