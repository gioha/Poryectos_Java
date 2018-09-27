/**
 * @(#)MBOficialiaPartes.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOAreasOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOCapturaOficialiaHelper;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoOfHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHOficialiaPartesInterface;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase de la capa de MB para el módulo Oficialia de partes.
 * 
 * @author Sergio Ley Garcia
 * @since 02/11/2017
 * @update David Rodríguez Corral
 * @since 05/12/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public class MBOficialiaPartesFisicos implements Serializable {
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 4715704720474546267L;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger	logger	= Logger.getLogger(MBOficialiaPartesFisicos.class);
	
	/**
	 * Objeto helper para cargar los combos iniciales
	 */
	private DTOCapturaOficialiaHelper dtoCaptura;
	
	/**
	 * String que guarda el area seleccionada cuando la oficialia está registrada en más de un área
	 */
	private String areaSeleccionada;
	
	/**
	 * String que guarda el area seleccionada cuando la oficialia está registrada en una sola área
	 */
	private DTOCAreaEntity areaSel;
	
	/**
	 * Objeto que guarda el folio a apartar
	 */
	private DTOApartadosNumDocOfEntity dtoApartado;
	
	/**
	 * Objeto que guarda el folio que se liberó al elegir otra área en el combo de áreas
	 */
	private DTOApartadosNumDocOfEntity folioAnterior;
	
	/**
	 * Integer que contiene el número de área a la que está registrada la oficialía.
	 */
	private Integer totalAreas;
	
	/**
	 * Integer que guarda el folio del documento que se va a capturar
	 */
	private String folio;
	
	/**
	 * Objeto que guarda los datos para un documento
	 */
	private DTODocumentoEntity dtoDocumento;
	
	/**
	 * Objeto que guarda las listas para los combos de remitentes
	 */
	private DTOAreasOficialiaHelper dtoCombosRemitentes;

	/**
	 * Objeto que guarda las listas para los combos de destinatarios
	 */
	private DTOAreasOficialiaHelper dtoCombosDestinatarios;
	
	/**
	 * Objeto que guarda las listas para los combos de Ccp
	 */
	private DTOAreasOficialiaHelper dtoCombosCcp;
	
	/**
	 * Boolean para bloquear el número de documento
	 */
	private Boolean conNumero;
	
	/**
	 * Objeto que guarda un remitente externo
	 */
	private DTORemitentesExternosOfEntity dtoRemitenteExterno;
	
	/**
	 * Objeto que contendrá a la persona de la cual se esta viendo el detalle
	 */
	private DTOEstructuraAreasEntity personaDetalle;
	
	/**
	 * Objeto que contendrá a la persona de la cual se esta viendo el detalle
	 */
	private Date hoy;

	private Integer documentoCargado;
	/**
	 * Objeto que contiene el formato del tamaño de los archivos (en MB)
	 */
	DecimalFormat decimalFormat = new DecimalFormat("#.00");

	/**
	 * Objeto que contiene el formato de las fechas de creación y recepción del documento)
	 */
	String fechaFormat = (new SimpleDateFormat("dd/MMMM/yyyy")).format(new Date());

	/**
	 * Objeto que contiene los datos asociados al usuario logueado
	 */
	private DTOUsuarioLogin usuarioLogueado;
	
	@SuppressWarnings("unused")
	private DTODocumentoEntity documentoAdjunto;
	
	@SuppressWarnings("unused")
	private DTOFiltrosCapturaDocumentoOfHelper filtros;
	
	@Autowired
	@Qualifier("bsdOficialiaPartes")
	private transient BSDOficialiaPartesInterface bsdOficialiaPartesInterface;
	
	@Autowired
	@Qualifier("vhOficialiaPartes")
	private transient VHOficialiaPartesInterface vhOficialiaPartesInterface;
	
	/**
	 * Método de carga inicial de la pantalla
	 * 
	 * @author David Rodríguez Corral
	 * @since 14/12/2017
	 * @update José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void iniciar() {
		logger.info("<============== INICIANDO CLASIFICACIÓN DE DOCUMENTOS FÍSICOS ==================>");
		usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		try {
			this.dtoCombosRemitentes = new DTOAreasOficialiaHelper();
			this.dtoCombosRemitentes.setHabilitarEstados(true);
			this.dtoCombosRemitentes.setTipo("I");
			this.dtoCombosRemitentes.setMostrarInternoExterno(false);
			this.dtoCombosDestinatarios = new DTOAreasOficialiaHelper();
			this.dtoCombosDestinatarios.setHabilitarEstados(true);
			this.dtoCombosCcp = new DTOAreasOficialiaHelper();
			this.dtoCombosCcp.setHabilitarEstados(true);
			this.conNumero = false;
			this.dtoRemitenteExterno = new DTORemitentesExternosOfEntity();
			this.dtoCombosRemitentes.setPersonas(new ArrayList<DTOEstructuraAreasEntity>());
			this.dtoCombosDestinatarios.setPersonas(new ArrayList<DTOEstructuraAreasEntity>());
			this.dtoCombosCcp.setPersonas(new ArrayList<DTOEstructuraAreasEntity>());
			this.folioAnterior=null;
			this.dtoCaptura = bsdOficialiaPartesInterface.consultarCombosIniciales(usuarioLogueado.getRol(), usuarioLogueado.getIdOficialia());
			this.dtoCaptura.setHabilitarComponente(true);
			this.totalAreas = this.dtoCaptura.getListaDTOAreas().size();
			this.dtoDocumento = new DTODocumentoEntity();
			dtoCaptura.setRemitentesExtAgregados(new ArrayList<DTORemitentesExternosOfEntity>());
			dtoApartado = new DTOApartadosNumDocOfEntity();
			areaSeleccionada = "SinArea";
			
			if(this.totalAreas.intValue() == 1) {
				apartarFolioUnaArea();
				this.areaSel = this.dtoCaptura.getListaDTOAreas().get(0);
				this.dtoCaptura.setHabilitarComponente(false);
				this.dtoCombosDestinatarios.setNombreAreaDestinatario(this.dtoCaptura.getListaDTOAreas().get(0).getSiglas());
			}

			dtoCaptura.setDocsAnexos(new ArrayList<DTODocumentoAnexoHelper>());
			
		} catch(Exception e) {
			logger.error("<=================== Clase: MBOficialiaPartesFisicos , Método: iniciar");
			logger.error("<=================== Ocurrió un error al cargar, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al cargar los datos, por favor intentelo de nuevo"));
		}
	}
	
	/**
	 * Método aparta un folio para capturar un documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/12/2017
	 */
	public void apartarFolio() {
		
		logger.info("<============== APARTANDO FOLIO DOCUMENTO FÍSICO ==================>");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
			
			if(this.areaSeleccionada.equals("0")){
				
				this.dtoCaptura.setHabilitarComponente(true);
				this.dtoApartado.setEstatus('L');
				bsdOficialiaPartesInterface.liberarFolio(this.folioAnterior);
				this.dtoApartado.setFolioOficialia(null);
				this.folioAnterior = null;
				this.dtoCombosDestinatarios.setNombreAreaDestinatario(null);
				
			}else{
		
				String area[] = areaSeleccionada.split("-");
				Integer idArea = Integer.parseInt(area[0]);
				Integer tipoArea = Integer.parseInt(area[1]);
				String siglas = area[2];
				String tipoApartado = Utilidades.mensajeProperties("constante_tipoApartado_fisico");
				
				this.dtoApartado = bsdOficialiaPartesInterface.obtenerFolioDocFisico(usuarioLogueado.getIdOficialia(), idArea, tipoArea, siglas, this.folioAnterior, tipoApartado);	
				this.folioAnterior = this.dtoApartado;
				this.dtoCaptura.setHabilitarComponente(false);
				this.dtoCombosDestinatarios.setNombreAreaDestinatario(siglas);
				
			}
				
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartesFisicos , Método: apartarFolio");
			logger.error("<=================== Ocurrió un error al apartar un folio");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al apartar un folio, por favor intentelo de nuevo"));
		}
			
	}
	
	/**
	 * Método aparta un folio para capturar un documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/12/2017
	 */
	public void apartarFolioUnaArea() {
		
		logger.info("<============== APARTANDO FOLIO DOCUMENTO FÍSICO ==================>");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
			
			String tipoApartado = Utilidades.mensajeProperties("constante_tipoApartado_fisico");
			this.dtoApartado = bsdOficialiaPartesInterface.obtenerFolioDocFisico(usuarioLogueado.getIdOficialia(), this.dtoCaptura.getListaDTOAreas().get(0).getIdArea(), this.dtoCaptura.getListaDTOAreas().get(0).getTipoArea(), this.dtoCaptura.getListaDTOAreas().get(0).getSiglas(), this.folioAnterior, tipoApartado);	
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartesFisicos , Método: apartarFolio");
			logger.error("<=================== Ocurrió un error al apartar un folio");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al apartar un folio, por favor intentelo de nuevo"));
		}
			
	}
	
	/**
	 * Método que implementa la funcionalidad para los combos de remitentes, destinatarios y ccp
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void seleccionarArea(Integer tipo) {
		
		logger.info("<============== SELECCIONANDO ÁREA REMITENTE ==================>");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
			
			switch (tipo) {
			
			case 0:
				
				if(this.dtoCombosRemitentes.getTipoAreaSeleccionada().equals("0")){
					this.dtoCombosRemitentes.setListaAreas(null);
					this.dtoCombosRemitentes.setHabilitarEstados(true);
				}else{
					if(this.dtoCombosRemitentes.getTipoAreaSeleccionada().equals("1")){
						this.dtoCombosRemitentes.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(1,0));
						this.dtoCombosRemitentes.setHabilitarEstados(true);
					}else{
						if(this.dtoCombosRemitentes.getTipoAreaSeleccionada().equals("2")){
							this.dtoCombosRemitentes.setHabilitarEstados(false);
							this.dtoCombosRemitentes.setListaEstados(bsdOficialiaPartesInterface.consultarEstados());
							this.dtoCombosRemitentes.setListaAreas(null);
						}else{
							this.dtoCombosRemitentes.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(3,0));
							this.dtoCombosRemitentes.setHabilitarEstados(true);
						}
					}
				}
				
				break;
				
			case 1:
				
				if(this.dtoCombosDestinatarios.getTipoAreaSeleccionada().equals("0")){
					this.dtoCombosDestinatarios.setListaAreas(null);
					this.dtoCombosDestinatarios.setHabilitarEstados(true);
				}else{
					if(this.dtoCombosDestinatarios.getTipoAreaSeleccionada().equals("1")){
						this.dtoCombosDestinatarios.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(1,0));
						this.dtoCombosDestinatarios.setHabilitarEstados(true);
					}else{
						if(this.dtoCombosDestinatarios.getTipoAreaSeleccionada().equals("2")){
							this.dtoCombosDestinatarios.setHabilitarEstados(false);
							this.dtoCombosDestinatarios.setListaEstados(bsdOficialiaPartesInterface.consultarEstados());
							this.dtoCombosDestinatarios.setListaAreas(null);
						}else{
							this.dtoCombosDestinatarios.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(3,0));
							this.dtoCombosDestinatarios.setHabilitarEstados(true);
						}
					}
				}
				
				break;
				
			case 2:
				
				if(this.dtoCombosCcp.getTipoAreaSeleccionada().equals("0")){
					this.dtoCombosCcp.setListaAreas(null);
					this.dtoCombosCcp.setHabilitarEstados(true);
				}else{
					if(this.dtoCombosCcp.getTipoAreaSeleccionada().equals("1")){
						this.dtoCombosCcp.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(1,0));
						this.dtoCombosCcp.setHabilitarEstados(true);
					}else{
						if(this.dtoCombosCcp.getTipoAreaSeleccionada().equals("2")){
							this.dtoCombosCcp.setHabilitarEstados(false);
							this.dtoCombosCcp.setListaEstados(bsdOficialiaPartesInterface.consultarEstados());
							this.dtoCombosCcp.setListaAreas(null);
						}else{
							this.dtoCombosCcp.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(3,0));
							this.dtoCombosCcp.setHabilitarEstados(true);
						}
					}
				}
				
				break;
				
			}
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartesFisicos , Método: apartarFolio");
			logger.error("<=================== Ocurrió un error al apartar un folio");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al apartar un folio, por favor intentelo de nuevo"));
		}
			
	}
	
	/**
	 * Método que obtiene los organos desconcentrados de un estado
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void seleccionarEstado(Integer tipo) {
		
		logger.info("<============== SELECCIONANDO ÁREA REMITENTE ==================>");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
			
			switch (tipo) {
			
				case 0:
					this.dtoCombosRemitentes.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(
							2,Integer.parseInt(this.dtoCombosRemitentes.getEntidadSeleccionada())));
				break;
				
				case 1:
					this.dtoCombosDestinatarios.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(
							2,Integer.parseInt(this.dtoCombosDestinatarios.getEntidadSeleccionada())));
				break;
				
				case 2:
					this.dtoCombosCcp.setListaAreas(bsdOficialiaPartesInterface.consultarAreas(
							2,Integer.parseInt(this.dtoCombosCcp.getEntidadSeleccionada())));
				break;
			
			}
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartesFisicos , Método: apartarFolio");
			logger.error("<=================== Ocurrió un error al apartar un folio");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al apartar un folio, por favor intentelo de nuevo"));
		}
			
	}
	
	/**
	 * Método que busca una persona 
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/12/2017
	 */
	public void buscarPersonas() {
		
		if((this.dtoCombosRemitentes.getNombre() == null || this.dtoCombosRemitentes.getNombre().equals("")) 
				&& (this.dtoCombosRemitentes.getApellidos() == null || this.dtoCombosRemitentes.getApellidos().equals(""))
					&&(this.dtoCombosRemitentes.getAreaSeleccionada().equals("0"))){
		
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ingrese por lo menos un valor en Nombre(s), Apellidos o Áreas"));
			
		}
		
	}
	
	/**
	 * Método que captura un remitente externo 
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/01/2018
	 */
	public void capturarRemitenteExterno() {
		
		logger.info("<============== CAPTURANDO REMITENTE EXTERNO ==================>");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		Integer idArea;
		Integer tipoArea;
		
		try{
			if(this.totalAreas.intValue()==1){
				
				idArea=this.dtoCaptura.getListaDTOAreas().get(0).getIdArea();
				tipoArea=this.dtoCaptura.getListaDTOAreas().get(0).getTipoArea();
				
			}else{
				
				String area[] = areaSeleccionada.split("-");
				idArea = Integer.parseInt(area[0]);
				tipoArea = Integer.parseInt(area[1]);
				
			}
			this.dtoRemitenteExterno.setIdArea(idArea);
			this.dtoRemitenteExterno.setTipoArea(tipoArea);
			
			bsdOficialiaPartesInterface.capturarRemitenteExterno(this.dtoRemitenteExterno);
			
			this.dtoRemitenteExterno.setNombreRemitente(null);
			this.dtoRemitenteExterno.setDependencia(null);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), "Se ha capturado el remitente exitosamente"));
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartesFisicos , Método: apartarFolio");
			logger.error("<=================== Ocurrió un error al apartar un folio");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al apartar un folio, por favor intentelo de nuevo"));
		}
		
	}
	
	/**
	 * Evento para el bloqueo de numero de documento en caso de que se active el checkbox
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void bloquearNumeroDocumento() {
		if(this.conNumero){
			this.dtoDocumento.setNoDocumento(null);
		}	
	}
	
	/**
	 * Evento para mostrar los remitentes internos o externos
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void mostrarCamposRemitentes() {
		logger.info("Cambiando remitentes");
		if(this.dtoCombosRemitentes.getTipo().equals("I")){
			this.dtoCombosRemitentes.setMostrarInternoExterno(false);
		}else{
			this.dtoCombosRemitentes.setMostrarInternoExterno(true);
		}
	}
	
	/**
	 * Método que verifica si hay un folio pendiente electrónico, si es cierto se redirige a la pantalla
	 * de folios electrónicos
	 * 
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public Boolean consultarFolioPendiente() {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		DTOBandejaEntradasOficialiaEntity folioPendientes = new DTOBandejaEntradasOficialiaEntity();
		folioPendientes = bsdOficialiaPartesInterface.consultarFoliosPendientes(usuarioLogueado.getIdOficialia(), 'A');
		
		if(folioPendientes!=null){
			
			return false;
		
		}else{
			
			return true;
		}
	
	}
	
	/**
	 * Método que obtiene las personas que pueden ser remitentes para que las seleccione el usuario
	 * en base a los filtros previamente seleccionados
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void consultarRemitentes() {
		
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		String mensajeError = this.vhOficialiaPartesInterface.validarConsultaRemitentes(this.dtoCombosRemitentes);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		Integer idArea = Integer.parseInt(this.dtoCombosRemitentes.getAreaSeleccionada());
		Integer tipoArea = Integer.parseInt(this.dtoCombosRemitentes.getTipoAreaSeleccionada());
		logger.info(idArea);
		logger.info(tipoArea);
		
		//2.- Hacemos la consulta
		List<DTOEstructuraAreasEntity> posiblesPersonasRemitentes = this.bsdOficialiaPartesInterface.consultarPersonas(
				idArea, tipoArea, this.dtoCombosRemitentes.getNombre(), this.dtoCombosRemitentes.getApellidos());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		logger.info(posiblesPersonasRemitentes.size());
		if (posiblesPersonasRemitentes.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			this.dtoCombosRemitentes.setPersonasBusquedasEncontradas(posiblesPersonasRemitentes);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaRemitente').show()");
		}
	}
	
	/**
	 * Método que obtiene las personas que pueden ser ccp para que las seleccione el usuario
	 * en base a los filtros previamente seleccionados
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void consultarCcp() {
		
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		String mensajeError = this.vhOficialiaPartesInterface.validarConsultaRemitentes(this.dtoCombosCcp);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		Integer idArea = Integer.parseInt(this.dtoCombosCcp.getAreaSeleccionada());
		Integer tipoArea = Integer.parseInt(this.dtoCombosCcp.getTipoAreaSeleccionada());
		logger.info(idArea);
		logger.info(tipoArea);
		
		//2.- Hacemos la consulta
		List<DTOEstructuraAreasEntity> posiblesPersonasRemitentes = this.bsdOficialiaPartesInterface.consultarPersonas(
				idArea, tipoArea, this.dtoCombosCcp.getNombre(), this.dtoCombosCcp.getApellidos());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		logger.info(posiblesPersonasRemitentes.size());
		if (posiblesPersonasRemitentes.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			this.dtoCombosCcp.setPersonasBusquedasEncontradas(posiblesPersonasRemitentes);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaCCP').show()");
		}
	}
	
	/**
	 * Método que obtiene las personas que pueden ser ccp para que las seleccione el usuario
	 * en base a los filtros previamente seleccionados
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void consultarDestinatarios() {
		
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		/*String mensajeError = this.vhOficialiaPartesInterface.validarConsultaRemitentes(this.dtoCombosCcp);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}*/
		String area[];
		Integer idArea;
		Integer tipoArea;
		
		if(this.totalAreas.intValue() == 1){
			
			idArea = this.areaSel.getIdArea();
			tipoArea = this.areaSel.getTipoArea();
		
		}else{
			area = areaSeleccionada.split("-");
			idArea = Integer.parseInt(area[0]);
			tipoArea = Integer.parseInt(area[1]);
			
		}
		
		//2.- Hacemos la consulta
		List<DTOEstructuraAreasEntity> posiblesPersonasRemitentes = this.bsdOficialiaPartesInterface.consultarPersonas(
				idArea, tipoArea, this.dtoCombosDestinatarios.getNombre(), this.dtoCombosDestinatarios.getApellidos());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		logger.info(posiblesPersonasRemitentes.size());
		if (posiblesPersonasRemitentes.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			this.dtoCombosDestinatarios.setPersonasBusquedasEncontradas(posiblesPersonasRemitentes);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaDestinatario').show()");
		}
	}
	
	/**
	 * Método que agrega a un remitente una vez seleccionado en la ventana emergente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void agregarRemitente() {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhOficialiaPartesInterface.validarAgregarRemitente(this.dtoCombosRemitentes, this.dtoCombosDestinatarios, this.dtoCombosCcp);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhOficialiaPartesInterface.agregarRemitentes(this.dtoCombosRemitentes);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaRemitente').hide()");
	}

	/**
	 * Método para eliminar a un remitente que se selecciono
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void eliminarRemitente(DTOEstructuraAreasEntity remitenteAEliminar) {

		this.dtoCombosRemitentes.getPersonas().remove(remitenteAEliminar);
	}
	
	/**
	 * Método que agrega a un destinatario una vez seleccionado en la ventana emergente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void agregarDestinatario() {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhOficialiaPartesInterface.validarAgregarDestinatario(this.dtoCombosRemitentes, this.dtoCombosDestinatarios, this.dtoCombosCcp);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhOficialiaPartesInterface.agregarDestinatarios(this.dtoCombosDestinatarios);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaDestinatario').hide()");
	}

	/**
	 * Método para eliminar a un destinatario que se selecciono
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void eliminarDestinatario(DTOEstructuraAreasEntity destinatarioAEliminar) {

		this.dtoCombosDestinatarios.getPersonas().remove(destinatarioAEliminar);
	}
	
	/**
	 * Método que agrega a un CCP una vez seleccionado en la ventana emergente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void agregarCCP() {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhOficialiaPartesInterface.validarAgregarCCP(this.dtoCombosRemitentes, this.dtoCombosDestinatarios, this.dtoCombosCcp);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhOficialiaPartesInterface.agregarCCP(this.dtoCombosCcp);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaCCP').hide()");
	}

	/**
	 * Método para eliminar a un CCP que se selecciono
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void eliminarCCP(DTOEstructuraAreasEntity ccpAEliminar) {

		this.dtoCombosCcp.getPersonas().remove(ccpAEliminar);
	}
	
	/**
	 * Método que obtiene la lista de categorias a partir de una sección
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/11/2017
	 */
	public void consultarCategoria() {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		try{
		
			this.dtoCaptura.setCategoriaSeleccionada(0);
			this.dtoCaptura.setListaDTOCategorias(bsdOficialiaPartesInterface.consultarCategorias(this.dtoCaptura.getSeccionSeleccionada()));
			
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartesFisicas , Método: consultarCategoria");
			logger.error("<=================== Ocurrió un error al cargar las categorías, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al cargar los datos, por favor intentelo de nuevo"));
		}
	}
	
	/**
	 * Método final que guarda el documento creado por el usuario
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @throws Exception 
	 * @since 25/08/2017 
	 */
	public void guadarDocumento() throws Exception {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = "";//this.vhOficialiaPartesInterface.validaCapturaDocumento(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        throw new Exception("excepcion controlada");
		}
	
		try {
	
			//this.bsdOficialiaPartesInterface.guardarDocumento(filtros);
			
		} catch (Exception e) {

			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! guardar toda la información del documento!!!");
			logger.error("<=================== Clase: MBCapturaDocumento , Método: guadarDocumento");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
			throw new Exception(e); //Manda esta excepción para que no continue con el flujo
		}
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable totalAreas
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/12/2017
	 */
	public Integer getTotalAreas() {
		return totalAreas;
	}

	/**
	 * @param totalAreas : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 15/12/2017
	 */
	public void setTotalAreas(Integer totalAreas) {
		this.totalAreas = totalAreas;
	}
	
	/**
	 * @return valor de tipo String que esta contenido en la variable folio
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/01/2018
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 15/01/2018
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable areaSeleccionada
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public String getAreaSeleccionada() {
		return areaSeleccionada;
	}

	/**
	 * @param areaSeleccionada : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public void setAreaSeleccionada(String areaSeleccionada) {
		this.areaSeleccionada = areaSeleccionada;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable areaSel
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public DTOCAreaEntity getAreaSel() {
		return areaSel;
	}

	/**
	 * @param areaSel : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public void setAreaSel(DTOCAreaEntity areaSel) {
		this.areaSel = areaSel;
	}

	/**
	 * @return valor de tipo DTOApartadosNumDocOfEntity que esta contenido en la variable dtoApartado
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public DTOApartadosNumDocOfEntity getDtoApartado() {
		return dtoApartado;
	}

	/**
	 * @param dtoApartado : valor que se ingresa a la variable de tipo DTOApartadosNumDocOfEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public void setDtoApartado(DTOApartadosNumDocOfEntity dtoApartado) {
		this.dtoApartado = dtoApartado;
	}

	/**
	 * @return valor de tipo DTOApartadosNumDocOfEntity que esta contenido en la variable folioAnterior
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public DTOApartadosNumDocOfEntity getFolioAnterior() {
		return folioAnterior;
	}

	/**
	 * @param folioAnterior : valor que se ingresa a la variable de tipo DTOApartadosNumDocOfEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public void setFolioAnterior(DTOApartadosNumDocOfEntity folioAnterior) {
		this.folioAnterior = folioAnterior;
	}

	/**
	 * @return the dtoDocumento
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento the dtoDocumento to set
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo DTOCapturaOficialiaHelper que esta contenido en la variable dtoCaptura
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public DTOCapturaOficialiaHelper getDtoCaptura() {
		return dtoCaptura;
	}

	/**
	 * @param dtoCaptura: valor que se ingresa a la variable de tipo DTOCapturaOficialiaHelper 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setDtoCaptura(DTOCapturaOficialiaHelper dtoCaptura) {
		this.dtoCaptura = dtoCaptura;
	}

	/**
	 * @return valor de tipo DTOAreasOficialiaHelper que esta contenido en la variable dtoCombosRemitentes
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public DTOAreasOficialiaHelper getDtoCombosRemitentes() {
		return dtoCombosRemitentes;
	}

	/**
	 * @param dtoCombosRemitentes: valor que se ingresa a la variable de tipo DTOAreasOficialiaHelper 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setDtoCombosRemitentes(DTOAreasOficialiaHelper dtoCombosRemitentes) {
		this.dtoCombosRemitentes = dtoCombosRemitentes;
	}

	/**
	 * @return valor de tipo DTOAreasOficialiaHelper que esta contenido en la variable dtoCombosDestinatarios
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public DTOAreasOficialiaHelper getDtoCombosDestinatarios() {
		return dtoCombosDestinatarios;
	}

	/**
	 * @param dtoCombosDestinatarios: valor que se ingresa a la variable de tipo DTOAreasOficialiaHelper 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setDtoCombosDestinatarios(
			DTOAreasOficialiaHelper dtoCombosDestinatarios) {
		this.dtoCombosDestinatarios = dtoCombosDestinatarios;
	}

	/**
	 * @return valor de tipo DTOAreasOficialiaHelper que esta contenido en la variable dtoCombosCcp
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public DTOAreasOficialiaHelper getDtoCombosCcp() {
		return dtoCombosCcp;
	}

	/**
	 * @param dtoCombosCcp: valor que se ingresa a la variable de tipo DTOAreasOficialiaHelper 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setDtoCombosCcp(DTOAreasOficialiaHelper dtoCombosCcp) {
		this.dtoCombosCcp = dtoCombosCcp;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable conNumero
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Boolean getConNumero() {
		return conNumero;
	}

	/**
	 * @param conNumero: valor que se ingresa a la variable de tipo Boolean 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setConNumero(Boolean conNumero) {
		this.conNumero = conNumero;
	}

	/**
	 * @return valor de tipo DTORemitentesExternosOfEntity que esta contenido en la variable dtoRemitenteExterno
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public DTORemitentesExternosOfEntity getDtoRemitenteExterno() {
		return dtoRemitenteExterno;
	}

	/**
	 * @param dtoRemitenteExterno: valor que se ingresa a la variable de tipo DTORemitentesExternosOfEntity 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setDtoRemitenteExterno(
			DTORemitentesExternosOfEntity dtoRemitenteExterno) {
		this.dtoRemitenteExterno = dtoRemitenteExterno;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaDetalle
	 *
	 * @author David Rodríguez Corral
	 * @since 24/01/2018
	 */
	public DTOEstructuraAreasEntity getPersonaDetalle() {
		return personaDetalle;
	}

	/**
	 * @param personaDetalle: valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity 
	 *
	 * @author David Rodríguez Corral
	 * @since 24/01/2018
	 */
	public void setPersonaDetalle(DTOEstructuraAreasEntity personaDetalle) {
		this.personaDetalle = personaDetalle;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable hoy
	 *
	 * @author David Rodríguez Corral
	 * @since 25/01/2018
	 */
	public Date getHoy() {
		return hoy;
	}

	/**
	 * @param hoy: valor que se ingresa a la variable de tipo Date 
	 *
	 * @author David Rodríguez Corral
	 * @since 25/01/2018
	 */
	public void setHoy(Date hoy) {
		this.hoy = hoy;
	}

	/**
	 * Método encargado de agregar uno o varios remitentes externos en la lista de remitentes externos agregados.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void agregarRemitenteExt() {
		// 1. Él o los remitentes externos seleccionados por el usuario son validados
		String msjValidacion = vhOficialiaPartesInterface.validarAgregarRemitenteExt(dtoCaptura);

		if(!msjValidacion.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjValidacion, ""));
	        return;
		}

		vhOficialiaPartesInterface.agregarRemitentesExt(dtoCaptura);
		RequestContext.getCurrentInstance().execute("PF('dialogRemitentesExtEncontrados').hide()");
	}

	/**
	 * Método encargado de eliminar un remitente externo de la lista de remitentes externos agregados.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void eliminarRemitenteExt(DTORemitentesExternosOfEntity remitenteExt) {
		this.dtoCaptura.getRemitentesExtAgregados().remove(remitenteExt);
	}

	/**
	 * Método que obtiene una lista de remitentes externos encontrados por la búsqueda
	 * del usuario en la vista de Folio y Carga de Documento.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 05/04/2018
	 */
	public void recuperarRemitentesExt() {
		// 1. Se validan los campos de búsqueda de la sección de Remitente (s) y/o Destinatario (s).
		String msjSinFiltros = vhOficialiaPartesInterface.validarConsultaRemitentesExt(dtoCaptura);

		if(!msjSinFiltros.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjSinFiltros, ""));
			return;
		} else {	// 2. Se realiza la consulta de remitentes externos en BD
			bsdOficialiaPartesInterface.recuperarRemitentesExt(dtoCaptura, dtoApartado);		
		}

		if(dtoCaptura.getRemitentesExtEncontrados().size() > 0) {
			RequestContext.getCurrentInstance().execute("PF('dialogRemitentesExtEncontrados').show()");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
		}
	}

	/**
	 * Método encargado de adjuntar el documento físico de oficialía desde el cual se se extrae la
	 * información introducida en la vista de Folio y Carga de Documento.
	 * @param FileUploadEvent archivo
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void adjuntarDocumento(FileUploadEvent archivo) {
		try {
			// 1. Los metadatos del archivo son validados
			boolean esArchivoValido = vhOficialiaPartesInterface.validarMetadatosArchivo(archivo, "Documento");

			// 2. El archivo es guardado en una carpeta temporal
			if(esArchivoValido) {
				vhOficialiaPartesInterface.generarTemporalArchivoAdjunto(dtoCaptura, archivo, false, true);
			} else {	// 3. El archivo no es un archivo válido
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Archivo inválido, favor de verificar.", "Sección Folio y carga de documento"));
			}
		} catch(Exception e) {
			logger.error("MBOficialiaPartesFisicos.adjuntarDocumento: " + e.getMessage());
		}
	}

	/**
	 * Método encargado de adjuntar uno o varios documentos anexos relacionados al registro de un
	 * nuevo documento físico de oficialía en la vista de Folio y Carga de Documento.
	 * @param FileUploadEvent archivo
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void adjuntarAnexo(FileUploadEvent archivo) {
		try {
			// 1. Los metadatos del archivo son validados
			boolean esArchivoValido = vhOficialiaPartesInterface.validarMetadatosArchivo(archivo, "Anexo");

			// 2. El archivo es guardado en una carpeta temporal
			if(esArchivoValido) {
				vhOficialiaPartesInterface.generarTemporalArchivoAdjunto(dtoCaptura, archivo, true, false);
			} else {	// 3. El archivo no es un archivo válido
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Archivo inválido, favor de verificar.", "Sección Anexos"));
			}
		} catch(Exception e) {
			logger.error("MBOficialiaPartesFisicos.adjuntarAnexo: " + e.getMessage());
		}
	}

	/**
	 * Método encargado de eliminar un documento físico de oficialía en la vista de Folio y Carga de Documento.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void eliminarDocumento() {
		dtoCaptura.setNombreTemporalDoc(null);
		dtoCaptura.setNombreDocumento(null);
		dtoCaptura.setTamanioDocumento(0);
	}

	/**
	 * Método encargado de eliminar un documento anexo de la lista de anexos dentro de la
	 * vista de Folio y Carga de Documento.
	 * @param DTODocumentoAnexoHelper docAnexo
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void eliminarAnexo(DTODocumentoAnexoHelper docAnexo) {
		dtoCaptura.getDocsAnexos().remove(docAnexo);
	}

	/**
	 * Método encargado de cargar toda la información relacionada a un nuevo documento de oficialía de tipo físico.
	 * @return String: para control del flujo
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public String cargarDocumento() {
		// 1. Los datos introducidos por el usuario son validados
		String msjValidacion = vhOficialiaPartesInterface.validarFiltrosCaptura(dtoCaptura, dtoDocumento, areaSeleccionada, conNumero,
																				dtoCombosRemitentes.getPersonas(),
																				dtoCombosDestinatarios.getPersonas(),
																				dtoCombosCcp.getPersonas());

		// 2. La información introducida por el usuario es enviada a la capa BSD para su posterior carga
		if(msjValidacion.isEmpty()) {
			bsdOficialiaPartesInterface.cargarDocumento(usuarioLogueado, dtoCaptura, dtoDocumento, dtoApartado,
														dtoCombosRemitentes.getPersonas(),
														dtoCombosDestinatarios.getPersonas(),
														dtoCombosCcp.getPersonas());

			if(bsdOficialiaPartesInterface.recuperarDocumento(dtoDocumento.getIdDocumento()) != null) {
				documentoCargado = 1;
				RequestContext.getCurrentInstance().execute("regresar()");
				return "regresarBandeja";
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ha ocurrido un error al cargar el documento.", ""));
				return "";
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjValidacion, ""));
			return "";
		}
	}

	public int getDeterminarMaxHoras() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public int getDeterminarMaxMinutos() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public DTOUsuarioLogin getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public String getFechaFormat() {
		return fechaFormat;
	}

	public Integer getDocumentoCargado() {
		return documentoCargado;
	}

}
