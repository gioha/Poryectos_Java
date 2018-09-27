/**
 * @(#)MBTurnado.java 08/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.bo.inter.BOTurnadoInterface;
import mx.ine.gestion.bsd.inter.BSDTurnadoInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHTurnadoInterface;
import mx.org.ine.servicios.dto.DTOBase;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase que recibe todas las peticiones que se ejecutan en el módulo de apartado de folios.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 08/01/2018
 * @update José Miguel Ortiz
 * @since 20/04/2018
 */
public class MBTurnado implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -6355511234625994019L;

	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBTurnado.class);

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	@Autowired
	@Qualifier("vhTurnado")
	private transient VHTurnadoInterface vhTurnadoInterface;

	@Autowired
	@Qualifier("bsdTurnado")
	private transient BSDTurnadoInterface bsdTurnadoInterface;
	
	@Autowired
	@Qualifier("boTurnado")
	private transient BOTurnadoInterface boTurnadoInterface;

	/**
	 * DTO con la persona que está registrada en el momento
	 */
	private DTOEstructuraAreasEntity personaLogueada;
	
	/**
	 * Lista que contiene las personas posibles para el turnado
	 */
	private List<DTOEstructuraAreasEntity> listaPersonasTurnado;
	
	/**
	 * Lista con las instrucciones de atención del usuario en sesión
	 */
	private List<DTOTurnadoInstruHelper> listaInstruccionesRealizadas;
	
	/**
	 * Lista con las instrucciones de atención del usuario en sesión
	 */
	private List<DTOInstruccionesEntity> listaInstruccionesAtencion;
	
	/**
	 * Lista con las instrucciones informartivas del usuario en sesión
	 */
	private List<DTOInstruccionesEntity> listaInstruccionesInformativas;

	/**
	 * Lista de helper para turnado
	 */
	private List<DTOTurnadoHelper> listaTurnado;
	
	/**
	 * 
	 */
	private int instruccionesAtencionTamanio;

	/**
	 * Valor entero con el tamaño de la lista de las instrucciones informativas
	 */
	private int instruccionesInformativasTamanio;

	/**
	 * Documento seleccionado para la acción de turnar
	 */
	private DTOBase bandejaDocumento;
	
	/**
	 * Observaciones que se quieran agregar sobre el documento
	 */
	private String observaciones;
	
	/**
	 * Observaciones que ya han sido guardadas en la BD
	 */
	private String observacionesHistorico;
	
	/**
	 * String con el tipo de bandeja que se está turnando
	 */
	private String tipoBandeja;
	
	/**
	 * 
	 */
	private boolean esCCP;
	
	/**
	 * 
	 */
	private boolean tieneCambios;

	/**
	 * Documento a turnar por el usuario logueado
	 */
	private DTODocumentoEntity documentoTurnado;

	/**
	 * 
	 */
	private DTOUsuarioLogin usuarioLogueado;

	/* ------------------------------------------ Métodos --------------------------------------- */
	/**
	 * Método que inicializa las variables que se utilizan en la pantalla
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/01/2018
	 * @update José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public void iniciar() {
		usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		try {
			personaLogueada = new DTOEstructuraAreasEntity();
			personaLogueada.setIdPersona(usuarioLogueado.getIdPersona());
			personaLogueada.setIdArea(usuarioLogueado.getIdArea());
			personaLogueada.setTipoArea(usuarioLogueado.getTipoArea());
			
			listaPersonasTurnado = bsdTurnadoInterface.consultarPersonasTurnado(personaLogueada);

			listaInstruccionesAtencion = bsdTurnadoInterface.consultarInstruccionesAtencion(personaLogueada);
			listaInstruccionesInformativas = bsdTurnadoInterface.consultarInstruccionesInformativas(personaLogueada);

			instruccionesInformativasTamanio = listaInstruccionesInformativas.size();
			instruccionesAtencionTamanio = listaInstruccionesAtencion.size();
			
			listaTurnado = vhTurnadoInterface.inicializar(listaPersonasTurnado, listaInstruccionesAtencion.size(), listaInstruccionesInformativas.size());
		} catch (Exception e){
			vhTurnadoInterface.enviarError(e, "MBTurnado", "iniciar()", "Error al inicializar");
			vhTurnadoInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					"Ocurrió un error.");
		}
	}
	
	/**
	 * Método para abrir el dialog de turnado
	 *
	 * @param documentoBandeja: dto con el objeto de la bandeja seleccionado
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 22/01/2018
	 * @update José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public void abrirTurnado(DTOBase documentoBandeja) {
		tieneCambios = false;
		
		if(documentoBandeja != null){
			bandejaDocumento = documentoBandeja;
			
			//Se inicializan las variables necesarias para la pantalla de Turnado 
			if(bandejaDocumento instanceof DTOBandejaERecibidosEntity) {

				documentoTurnado = ((DTOBandejaERecibidosEntity) bandejaDocumento).getDocumento();
				tipoBandeja = "recibidos";
				instruccionesInformativasTamanio = listaInstruccionesInformativas.size();
				instruccionesAtencionTamanio = listaInstruccionesAtencion.size();

			} else if(bandejaDocumento instanceof DTOBandejaEAtencionEntity) {

				documentoTurnado = ((DTOBandejaEAtencionEntity) bandejaDocumento).getDocumento();
				tipoBandeja = "atencion";
				instruccionesInformativasTamanio = listaInstruccionesInformativas.size();
				instruccionesAtencionTamanio = listaInstruccionesAtencion.size();

			} else if(bandejaDocumento instanceof DTOBandejaECCPEntity) {

				documentoTurnado = ((DTOBandejaECCPEntity) bandejaDocumento).getDocumento();
				tipoBandeja = "ccp";
				instruccionesInformativasTamanio = listaInstruccionesInformativas.size();
				instruccionesAtencionTamanio = 0;

			} else if(bandejaDocumento instanceof DTOBandejaEInfoEntity) {

				documentoTurnado = ((DTOBandejaEInfoEntity) bandejaDocumento).getDocumento();
				tipoBandeja = "info";
				instruccionesInformativasTamanio = listaInstruccionesInformativas.size();
				instruccionesAtencionTamanio = 0;

			}
			
			try {
				//Da el tratamiento necesario a las variables inicializadas
				listaTurnado = vhTurnadoInterface.inicializar(listaPersonasTurnado, listaInstruccionesAtencion.size(), listaInstruccionesInformativas.size());
				observaciones = null;
				
				RequestContext.getCurrentInstance().execute("PF('dialogTurnado').show()");
				
			} catch (Exception e) {
				
				if(bandejaDocumento instanceof DTOBandejaERecibidosEntity) {
				
					log.error("<=================== Documento Recibido: "+((DTOBandejaERecibidosEntity) bandejaDocumento).toString()+". ");

				} else if(bandejaDocumento instanceof DTOBandejaEAtencionEntity) {

					log.error("<=================== Documento Atención: "+((DTOBandejaEAtencionEntity) bandejaDocumento).toString()+". ");

				} else if(bandejaDocumento instanceof DTOBandejaECCPEntity) {

					log.error("<=================== Documento CCP: "+((DTOBandejaECCPEntity) bandejaDocumento).toString()+". ");
					
				} else if(bandejaDocumento instanceof DTOBandejaEInfoEntity) {

					log.error("<=================== Documento Info: "+((DTOBandejaEInfoEntity) bandejaDocumento).toString()+". ");
				}
				
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnado()");
				log.error("<=================== Error al tratar de abrir un dialog de turnado. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}
	
	/**
	 * Método para abrir el dialog Turnado Historico, desde la bandeja del Historico
	 * 
	 * @param documentoBandeja
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/03/2018
	 */
	public void abrirTurnadoHistorico(DTOBase documentoBandeja) {
		DTOHBandejaERecibidosEntity recibido;
		DTOHBandejaEAtencionEntity atencion;
		DTOBandejaECCPEntity ccp;
		DTOHBandejaECCPEntity hccp;
		DTOHBandejaEInfoEntity info; 
		
		tieneCambios = false;
		
		if(documentoBandeja != null){
			
			bandejaDocumento = documentoBandeja;
			
			if(bandejaDocumento instanceof DTOHBandejaERecibidosEntity) {
				tipoBandeja = "recibidos";
				recibido = (DTOHBandejaERecibidosEntity)bandejaDocumento;
				documentoTurnado = recibido.getDocumento();
				
				try {	
					
					listaInstruccionesRealizadas = bsdTurnadoInterface.obtenerInstruccionesRealizadas(personaLogueada, recibido.getDocumento());
					observacionesHistorico = bsdTurnadoInterface.obtenerComentarioTurnado(recibido.getDocumento(), personaLogueada);
					
				}catch(Exception e){
					
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnadoHistorico()");
					log.error("<=================== Error al tratar de consultar las instrucciones realizadas. ");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("<=================== Documento Recibido:"+recibido.toString());
					log.error("", e);
				}
	
			} else if(bandejaDocumento instanceof DTOHBandejaEAtencionEntity) {
				
				tipoBandeja = "atencion";
				atencion = (DTOHBandejaEAtencionEntity) bandejaDocumento;
				documentoTurnado = atencion.getDocumento();
				
				try{
					
					listaInstruccionesRealizadas = bsdTurnadoInterface.obtenerInstruccionesRealizadas(personaLogueada, atencion.getDocumento());
					observacionesHistorico = bsdTurnadoInterface.obtenerComentarioTurnado(atencion.getDocumento(), personaLogueada);
					
				}catch(Exception e){
					
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnadoHistorico()");
					log.error("<=================== Error al tratar de consultar las instrucciones realizadas. ");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("<=================== Documento Atención:" + atencion.toString());
					log.error("", e);
				}
	
			} else if(bandejaDocumento instanceof DTOHBandejaECCPEntity) {
				
				tipoBandeja = "ccp";
				hccp = (DTOHBandejaECCPEntity)bandejaDocumento;
				documentoTurnado = hccp.getDocumento();
				
				try{
					
					listaInstruccionesRealizadas = bsdTurnadoInterface.obtenerInstruccionesRealizadas(personaLogueada, hccp.getDocumento());
					observacionesHistorico = bsdTurnadoInterface.obtenerComentarioTurnado(hccp.getDocumento(), personaLogueada);
					
				}catch(Exception e){
					
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnadoHistorico()");
					log.error("<=================== Error al tratar de consultar las instrucciones realizadas. ");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("<=================== Documento CCP:" + hccp.toString());
					log.error("", e);
				}
	
			}else if(bandejaDocumento instanceof DTOBandejaECCPEntity) {
				
				tipoBandeja = "ccp";
				ccp = (DTOBandejaECCPEntity)bandejaDocumento;
				documentoTurnado = ccp.getDocumento();
				
				try{
					
					listaInstruccionesRealizadas = bsdTurnadoInterface.obtenerInstruccionesRealizadas(personaLogueada, ccp.getDocumento());
					observacionesHistorico = bsdTurnadoInterface.obtenerComentarioTurnado(ccp.getDocumento(), personaLogueada);
					
				}catch(Exception e){
					
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnadoHistorico()");
					log.error("<=================== Error al tratar de consultar las instrucciones realizadas. ");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("<=================== Documento CCP:" + ccp.toString());
					log.error("", e);
				}
	
			}else if(bandejaDocumento instanceof DTOHBandejaEInfoEntity) {
				
				tipoBandeja = "info";
				info = (DTOHBandejaEInfoEntity)bandejaDocumento;
				documentoTurnado = info.getDocumento();
				
				try{
					
					listaInstruccionesRealizadas = bsdTurnadoInterface.obtenerInstruccionesRealizadas(personaLogueada, info.getDocumento());
					observacionesHistorico = bsdTurnadoInterface.obtenerComentarioTurnado(info.getDocumento(), personaLogueada);

				}catch(Exception e){
						
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnadoHistorico()");
					log.error("<=================== Error al tratar de consultar las instrucciones realizadas. ");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("<=================== Documento Info:" + info.toString());
					log.error("", e);
				}
			}
			
			//Se inicializa el tamaño de las instrucciones
			instruccionesInformativasTamanio = listaInstruccionesInformativas.size();
			instruccionesAtencionTamanio = listaInstruccionesAtencion.size();
			
			if(tipoBandeja.equals("ccp") || tipoBandeja.equals("info")){
				instruccionesAtencionTamanio = 0;
			}
	
			//Se le da el tratamiento a los valores recibidos
			try{
				
				listaTurnado = vhTurnadoInterface.inicializarHistoricos(listaPersonasTurnado, listaInstruccionesRealizadas, listaInstruccionesAtencion.size(), listaInstruccionesInformativas.size());
				observaciones = observacionesHistorico;
				
				RequestContext.getCurrentInstance().execute("PF('dialogTurnadoHistorico').show()");
				
			} catch (Exception e) {
				
				if(bandejaDocumento instanceof DTOHBandejaERecibidosEntity) {
				
					log.error("<=================== Documento Historico Recibido: "+((DTOHBandejaERecibidosEntity) bandejaDocumento).toString()+". ");

				} else if(bandejaDocumento instanceof DTOHBandejaEAtencionEntity) {

					log.error("<=================== Documento Historico Atención: "+((DTOHBandejaEAtencionEntity) bandejaDocumento).toString()+". ");

				} else if(bandejaDocumento instanceof DTOHBandejaECCPEntity) {

					log.error("<=================== Documento Historico CCP: "+((DTOHBandejaECCPEntity) bandejaDocumento).toString()+". ");
					
				} else if(bandejaDocumento instanceof DTOBandejaECCPEntity) {

					log.error("<=================== Documento CCP: "+((DTOBandejaECCPEntity) bandejaDocumento).toString()+". ");
					
				} else if(bandejaDocumento instanceof DTOHBandejaEInfoEntity) {

					log.error("<=================== Documento Historico Info: "+((DTOHBandejaEInfoEntity) bandejaDocumento).toString()+". ");
				}
				
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirTurnado()");
				log.error("<=================== Error al tratar de abrir un dialog de turnado. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}
	
	/**
	 * Método para enviar a turnar un documento
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 22/01/2018
	 * @update José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public void turnar() {
		
		if(validarTurnar()) {
			
			vhTurnadoInterface.procesarPersonasTurnado(listaTurnado, listaInstruccionesAtencion, listaInstruccionesInformativas);
			
			try {
				bsdTurnadoInterface.turnar(listaTurnado, bandejaDocumento, personaLogueada, observaciones);
				
				RequestContext.getCurrentInstance().execute("PF('dialogTurnado').hide()");
				
				vhTurnadoInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_exito"),
						Utilidades.mensajeProperties("titulo_growl_exito"),
						"El documento se envió a turnar correctamente.");

			} catch(Exception e) {
				log.error("<=================== Documento: "+(documentoTurnado).toString()+". ");
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: turnar()");
				log.error("<=================== Error al tratar de enviar a turnar un documento. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
				
				vhTurnadoInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						"No se pudo enviar a turnar.");
			}
			
			try{
				bsdTurnadoInterface.enviarCorreoNotificacion(usuarioLogueado, listaTurnado, documentoTurnado, listaInstruccionesAtencion, listaInstruccionesInformativas);
			} catch(Exception e) {
				log.error("<=================== Documento: "+(documentoTurnado).toString()+". ");
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: turnar()");
				log.error("<=================== Error al tratar de enviar los correos del turnado. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
				
				vhTurnadoInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						"No se pudo enviar los corres correspondientes.");
			}
		}
	}
	
	/**
	 * Método para enviar a turnar un documento que se encuentra en el historico
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 * @update José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public void turnarHistorico() {
		
		if(validarTurnar()) {
			
			vhTurnadoInterface.procesarPersonasTurnadoHistorico(listaTurnado, listaInstruccionesAtencion, listaInstruccionesInformativas);
			
			try {
				
				bsdTurnadoInterface.turnarHistorico(listaTurnado, bandejaDocumento, personaLogueada, observacionesHistorico	, listaInstruccionesRealizadas);
				
				RequestContext.getCurrentInstance().execute("PF('dialogTurnadoHistorico').hide()");
				vhTurnadoInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_exito"),
						Utilidades.mensajeProperties("titulo_growl_exito"),
						"El documento se envió a turnar correctamente.");
				
			} catch(Exception e) {
				log.error("<=================== Documento: "+(documentoTurnado).toString()+". ");
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: turnar()");
				log.error("<=================== Error al tratar de enviar a turnar un documento. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
				
				vhTurnadoInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						"No se pudo enviar a turnar.");
			}
			
			try {
				
				bsdTurnadoInterface.enviarCorreoNotificacion(usuarioLogueado, listaTurnado, documentoTurnado, listaInstruccionesAtencion, listaInstruccionesInformativas);
				
			} catch(Exception e) {
				log.error("<=================== Documento: "+(documentoTurnado).toString()+". ");
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: turnar()");
				log.error("<=================== Error al tratar de enviar los correos del turnado. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
				
				vhTurnadoInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						"No se pudo enviar los corres correspondientes.");
			}
		}
	}

	/**
	 * Método para validar que un documento se pueda enviar a turnar
	 *
	 * @return boolean: true si es posible turnar el documento. false si no se puede turnar.
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 22/01/2018
	 * @update José Miguel Ortiz
	 * @since 20/04/2018
	 */
	private boolean validarTurnar() {
		boolean validado = true;
		boolean unaInst = false;
		for (DTOTurnadoHelper personaTurnado : listaTurnado) {
			boolean instAten = false;
			boolean instInfo = false;
			for(int i = 0; i < instruccionesAtencionTamanio; i++) {
				if (personaTurnado.getInstruccionesAtencion()[i]) {
					instAten = true;
					break;
				}
			}
			for(int i = 0; i < instruccionesInformativasTamanio; i++) {
				if(personaTurnado.getInstruccionesInformativas()[i]) {
					if(instAten == true) {
						vhTurnadoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_advertencia"),
															   Utilidades.mensajeProperties("titulo_growl_advertencia"),
															   "No es posible ingresar instrucciones de dos tipos - " +
															   personaTurnado.getPersona().getNombreCompleto());
						return false;
					} else {
						instInfo = true;
						break;
					}
				}
			}
			if(!unaInst) {
				if(instAten || instInfo) {
					unaInst = true;
				}
			}
		}
		if(!unaInst) {
			vhTurnadoInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					"No se ha seleccionado nada.");
			return false;
		}
		return validado;
	}

	/**
	 * Método utilizado cuando se ha confirmado el cerrado del dialog de Turnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/04/2018
	 */
	public void confirmarCerrarTurnado(){
		RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionCerrarTurnado').hide()");
		RequestContext.getCurrentInstance().execute("PF('dialogTurnado').hide()");
	}
	
	/**
	 * Método utilizado cuando se ha decidido no cerrar el dialog de Turnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/04/2018
	 */
	public void anularCerradoTurnado(){
		RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionCerrarTurnado').hide()");
		RequestContext.getCurrentInstance().execute("PF('dialogTurnado').show()");
	}
	
	/**
	 * Método utilizado cuando se ha confirmado el cerrado del dialog de Turnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/04/2018
	 */
	public void confirmarCerrarTurnadoHistorico(){
		RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionCerrarTurnado').hide()");
		RequestContext.getCurrentInstance().execute("PF('dialogTurnadoHistorico').hide()");
	}
	
	/**
	 * Método utilizado cuando se ha decidido no cerrar el dialog de Turnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/04/2018
	 */
	public void anularCerradoTurnadoHistorico(){
		RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionCerrarTurnado').hide()");
		RequestContext.getCurrentInstance().execute("PF('dialogTurnadoHistorico').show()");
	}

	/**
	 * Método para actualizar las listas de instrucciones del mb porque no se pudieron actualizar de la forma normal
	 *
	 * @return boolean: true si es posible turnar el documento. false si no se puede turnar.
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 22/01/2018
	 */
	public void cambio(DTOTurnadoHelper turnado, String tipo, int num) {
		if(!tieneCambios){
			tieneCambios = true;
		}
		vhTurnadoInterface.mostrarMensajeGrowl(
				Utilidades.mensajeProperties("constante_growl_advertencia"),
				Utilidades.mensajeProperties("titulo_growl_advertencia"),
				"Ya ha seleccionado instrucciones de atención, no puede seleccionar los dos tipos a la misma persona.");
		int indice = listaTurnado.indexOf(turnado);
		if(indice >= 0){
			listaTurnado.get( indice   ).setEsPersonaCorrecta(!listaTurnado.get( indice  ).isEsPersonaCorrecta());
			//listaTurnado.get(  listaTurnado.indexOf(turnado)  ).isEsPersonaCorrecta();
		switch (tipo) {
			case "info":
				//listaTurnado.get(num).setEsPersonaCorrecta(!listaTurnado.get(num).isEsPersonaCorrecta());
//				if(turnado.getInstruccionesInformativas()[num] == false ){
//					if(boTurnadoInterface.revisarArreglo(turnado.getInstruccionesAtencion())){
//						vhTurnadoInterface.mostrarMensajeGrowl(
//								Utilidades.mensajeProperties("constante_growl_advertencia"),
//								Utilidades.mensajeProperties("titulo_growl_advertencia"),
//								"Ya ha seleccionado instrucciones de atención, no puede seleccionar los dos tipos a la misma persona.");
//					}else{
//						turnado.getInstruccionesInformativas()[num] = !turnado.getInstruccionesInformativas()[num];
//					}
//				}else{
//					turnado.getInstruccionesInformativas()[num] = !turnado.getInstruccionesInformativas()[num];
//				}
				turnado.getInstruccionesInformativas()[num] = !turnado.getInstruccionesInformativas()[num];
				break;
			case "aten":
				//listaTurnado.get(num).setEsPersonaCorrecta(!listaTurnado.get(num).isEsPersonaCorrecta());
//				if(turnado.getInstruccionesAtencion()[num] == false ){
//					if(boTurnadoInterface.revisarArreglo(turnado.getInstruccionesInformativas())){
//						vhTurnadoInterface.mostrarMensajeGrowl(
//								Utilidades.mensajeProperties("constante_growl_advertencia"),
//								Utilidades.mensajeProperties("titulo_growl_advertencia"),
//								"Ya ha seleccionado instrucciones de información, no puede seleccionar los dos tipos a la misma persona.");
//					}else{
//						turnado.getInstruccionesAtencion()[num] = !turnado.getInstruccionesAtencion()[num];
//					}
//				}else{
//					turnado.getInstruccionesAtencion()[num] = !turnado.getInstruccionesAtencion()[num];
//				}
				turnado.getInstruccionesAtencion()[num] = !turnado.getInstruccionesAtencion()[num];
				break;
			default:
				break;
		}
		}
	}

	//--------------------- GETTERS & SETTERS -------------------------- //
	/**
	 * @return variable de tipo List<DTOEstructuraAreasEntity> contenida en listaPersonasTurnado
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOEstructuraAreasEntity> getListaPersonasTurnado() {
		return listaPersonasTurnado;
	}

	/**
	 * @param listaPersonasTurnado: variable de tipo List<DTOEstructuraAreasEntity> contenida en listaPersonasTurnado
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaPersonasTurnado(
			List<DTOEstructuraAreasEntity> listaPersonasTurnado) {
		this.listaPersonasTurnado = listaPersonasTurnado;
	}

	/**
	 * @return variable de tipo List<DTOInstruccionesEntity> contenida en listaInstruccionesAtencion
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOInstruccionesEntity> getListaInstruccionesAtencion() {
		return listaInstruccionesAtencion;
	}

	/**
	 * @param listaInstruccionesAtencion: variable de tipo List<DTOInstruccionesEntity> contenida en listaInstruccionesAtencion
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaInstruccionesAtencion(
			List<DTOInstruccionesEntity> listaInstruccionesAtencion) {
		this.listaInstruccionesAtencion = listaInstruccionesAtencion;
	}

	/**
	 * @return variable de tipo List<DTOInstruccionesEntity> contenida en listaInstruccionesInformativas
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOInstruccionesEntity> getListaInstruccionesInformativas() {
		return listaInstruccionesInformativas;
	}

	/**
	 * @param listaInstruccionesInformativas: variable de tipo List<DTOInstruccionesEntity> contenida en listaInstruccionesInformativas
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaInstruccionesInformativas(
			List<DTOInstruccionesEntity> listaInstruccionesInformativas) {
		this.listaInstruccionesInformativas = listaInstruccionesInformativas;
	}

	/**
	 * @return variable de tipo List<DTOTurnadoHelper> contenida en listaTurnado
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOTurnadoHelper> getListaTurnado() {
		return listaTurnado;
	}

	/**
	 * @param listaTurnado: variable de tipo List<DTOTurnadoHelper> contenida en listaTurnado
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaTurnado(List<DTOTurnadoHelper> listaTurnado) {
		this.listaTurnado = listaTurnado;
	}

	/**
	 * @return variable de tipo int contenida en intruccionesAtencionTamanio
	 * 
	 * @since 15/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public int getInstruccionesAtencionTamanio() {
		return instruccionesAtencionTamanio;
	}

	/**
	 * @param intruccionesAtencionTamanio: variable de tipo int contenida en intruccionesAtencionTamanio
	 *
	 * @since 15/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setInstruccionesAtencionTamanio(int intruccionesAtencionTamanio) {
		this.instruccionesAtencionTamanio = intruccionesAtencionTamanio;
	}

	/**
	 * @return variable de tipo int contenida en instruccionesInformativasTamanio
	 * 
	 * @since 15/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public int getInstruccionesInformativasTamanio() {
		return instruccionesInformativasTamanio;
	}

	/**
	 * @param instruccionesInformativasTamanio: variable de tipo int contenida en instruccionesInformativasTamanio
	 *
	 * @since 15/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setInstruccionesInformativasTamanio(
			int instruccionesInformativasTamanio) {
		this.instruccionesInformativasTamanio = instruccionesInformativasTamanio;
	}

	/**
	 * @return variable de tipo DTOBandejaERecibidosEntity contenida en bandejaDocumento
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOBase getBandejaDocumento() {
		return bandejaDocumento;
	}

	/**
	 * @param bandejaDocumento: variable de tipo DTOBandejaERecibidosEntity contenida en bandejaDocumento
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setBandejaDocumento(DTOBase bandejaDocumento) {
		this.bandejaDocumento = bandejaDocumento;
	}

	/**
	 * @return variable de tipo String contenida en observaciones
	 * 
	 * @since 22/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones: variable de tipo String contenida en observaciones
	 *
	 * @since 22/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return variable de tipo String contenida en tipoBandeja
	 * 
	 * @since 24/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getTipoBandeja() {
		return tipoBandeja;
	}

	/**
	 * @param tipoBandeja: variable de tipo String contenida en tipoBandeja
	 *
	 * @since 24/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setTipoBandeja(String tipoBandeja) {
		this.tipoBandeja = tipoBandeja;
	}

	/**
	 * @return valor de tipo List<DTOTurnadoInstruHelper> que esta contenido en la variable listaInstruccionesRealizadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public List<DTOTurnadoInstruHelper> getListaInstruccionesRealizadas() {
		return listaInstruccionesRealizadas;
	}

	/**
	 * @param listaInstruccionesRealizadas : valor que se ingresa a la variable de tipo List<DTOTurnadoInstruHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setListaInstruccionesRealizadas(
			List<DTOTurnadoInstruHelper> listaInstruccionesRealizadas) {
		this.listaInstruccionesRealizadas = listaInstruccionesRealizadas;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable esCCP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/04/2018
	 */
	public boolean isEsCCP() {
		return esCCP;
	}

	/**
	 * @param esCCP : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/04/2018
	 */
	public void setEsCCP(boolean esCCP) {
		this.esCCP = esCCP;
	}

//	public void validarSeleccion(DTOTurnadoHelper personaTurnado) {
//		if(personaTurnado.getInstruccionesAtencionSel() != null) {
//			if(personaTurnado.getInstruccionesAtencionSel().length == 0) {
//				personaTurnado.setInstruccionesAtencionSel(null);
//			}
//		}
//
//		if(personaTurnado.getInstruccionesInformativasSel() != null) {
//			if(personaTurnado.getInstruccionesInformativasSel().length == 0) {
//				personaTurnado.setInstruccionesInformativasSel(null);
//			}
//		}
//		
//		if(personaTurnado.getInstruccionesAtencionSel() == null) {
//			personaTurnado.setTieneAtencionesSel(false);
//		} else {
//			if(personaTurnado.getTieneInformativasSel()) {
//				personaTurnado.setInstruccionesAtencionSel(null);
//				FacesContext context = FacesContext.getCurrentInstance();
//				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Instrucciones de atención no se pueden agregar", ""));
//			} else {
//				personaTurnado.setTieneAtencionesSel(true);
//			}
//		}
//
//		if(personaTurnado.getInstruccionesInformativasSel() == null) {
//			personaTurnado.setTieneInformativasSel(false);
//		} else {
//			if(personaTurnado.getTieneAtencionesSel()) {
//				personaTurnado.setInstruccionesInformativasSel(null);
//				FacesContext context = FacesContext.getCurrentInstance();
//				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Instrucciones informativas no se pueden agregar", ""));
//			} else {
//				personaTurnado.setTieneInformativasSel(true);
//			}
//		}
//	}

//	public boolean validarInstruccionesSel() {
//		for(DTOTurnadoHelper personaTurnado : listaTurnado) {
//			if(personaTurnado.getInstruccionesAtencionSel() != null) {
//				if(personaTurnado.getInstruccionesAtencionSel().length != 0) {
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}

	public boolean getTieneCambios() {
		return tieneCambios;
	}

	public void setTieneCambios(boolean tieneCambios) {
		this.tieneCambios = tieneCambios;
	}

}
