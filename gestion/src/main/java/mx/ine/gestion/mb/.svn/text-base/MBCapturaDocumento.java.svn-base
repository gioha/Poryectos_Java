/**
 * @(#)MBCapturaDocumento.java 25/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

/**
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDCapturarDocumentoInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase que recibe todas las peticiones que se ejecutan en el módulo de captura de documentos.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 25/08/2017
 */
public class MBCapturaDocumento implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 6015964141701398825L;

	/**
	 * Objeto para generar el log de la clase.
	 */
	private static Logger logger= Logger.getLogger(MBCapturaDocumento.class);

	@Autowired
	@Qualifier("bsdCapturarDocumentos")
	private transient BSDCapturarDocumentoInterface bsdCapturarDocumentoInterface;

	@Autowired
	@Qualifier("vhCapturaDocumento")
	private transient VHCapturaDocumentoInterface vhCapturaDocumentoInterface;
	
	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Objeto que contiene todos los filtros y los valores seleccionados por el usuario
	 * que se utilizaran para capturar el documento
	 */
	private DTOFiltrosCapturaDocumentoHelper filtros;

	/**
	 * Objeto que contendrá a la persona de la cual se esta viendo el detalle
	 */
	private DTOEstructuraAreasEntity personaDetalle;
	
	/**
	 * Objeto que contendrá a el área de la cual se esta viendo el detalle
	 */
	private DTOCAreaEntity areaDetalle;
	
	/**
	 * Atributo que guarda el id del documento que ha sido creado
	 */
	private Integer idDocumento;
	/**
	 * Atributo donde se guarda el número del documento que al que se está respondiendo
	 */
	private Integer idDocumentoRespondido;
	
	/**
	 * Atributo donde se guarda el idHistoricoRecepcion del documento que se quiere responder 
	 */
	private Integer idHistoricoRecepcion;
	
	/**
	 * Atributo donde se guarda el comentario con el cual será respondido el documento turnado
	 */
	private String comentarioRespondido;
	
	private DTOResponderTurnadoHelper filtroResponder;
	
	private DTOBandejaEAtencionEntity atencion;
	
	private DTOBandejaERecibidosEntity recibido;
	
	private DTOHBandejaEAtencionEntity hatencion;
	
	private DTOHBandejaERecibidosEntity hrecibido;
	
	/**
	 * Atributo que nos ayudara a dar formato a los valores con decimales
	 */
	private DecimalFormat df;
	
	/* ---------------------------------------- Métodos ------------------------------------------ */

	/**
	 * Método que se manda llamar al entrar a la pantalla de captura
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 07/03/2018
	 * 
	 */
	public void inicializaPantalla() {

		filtros = new DTOFiltrosCapturaDocumentoHelper();
		filtros.setListaTiposDocumentos(bsdCapturarDocumentoInterface.consultarTiposDocumentos());
		//Nota: estos 3 proximos se consultan 3 veces para que no tengan la misma referencia en memoria
		filtros.setListaTiposAreaDestinatarios(bsdCapturarDocumentoInterface.consultarTiposArea());
		filtros.setListaTiposAreaRemitentes(bsdCapturarDocumentoInterface.consultarTiposArea());
		filtros.setListaTiposAreaCCP(bsdCapturarDocumentoInterface.consultarTiposArea());
		
		filtros.setRemitentes(new ArrayList<DTOEstructuraAreasEntity>());
		filtros.setDestinatarios(new ArrayList<DTOEstructuraAreasEntity>());
		filtros.setDestinatariosComoArea(new ArrayList<DTOCAreaEntity>());
		filtros.setCcp(new ArrayList<DTOEstructuraAreasEntity>());
		filtros.setAnexos(new ArrayList<DTODocumentoAnexoHelper>());
	
		filtros.setBusquedaPorArea(false);
		
		if(!idDocumentoRespondido.equals(0)){
			filtros.setIdDocumentoRespondido(idDocumentoRespondido);
			filtroResponder = new DTOResponderTurnadoHelper();
			
			filtroResponder.setIdDocumentoRespondido(idDocumentoRespondido);
			filtroResponder.setIdHistoricoRecep(idHistoricoRecepcion);
			filtroResponder.setComentario(comentarioRespondido);
		}
		
		// si no existe la carpeta temporal de primefaces la creamos
		File carpetaTemporalPrime = new File(	Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") 
												+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmpPrimefaces") + File.separator
											 );
		if(!carpetaTemporalPrime.getAbsoluteFile().exists()){
			carpetaTemporalPrime.mkdirs();
		}
		
		df = new DecimalFormat("#.##");
	}

	/**
	 * Método que consulta los acrónimos dependiendo del tipo de documento seleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarAcronimos() {

		vhCapturaDocumentoInterface.reiniciaFiltros(1, filtros);
		filtros.setListaAcronimos(bsdCapturarDocumentoInterface.consultarAcronimos(filtros.getIdTipoDocumento()));
	}

	/**
	 * Método que consulta las plantillas dependiendo del tipo de documento y acronimo seleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarPlantillas() {

		vhCapturaDocumentoInterface.reiniciaFiltros(2, filtros);
		filtros.setListaPlantillas(bsdCapturarDocumentoInterface.consultarPlantillas(filtros.getIdTipoDocumento(), filtros.getIdAcronimo()));
	}

	/* ---------------------------------------- Remitente ------------------------------------------ */

	/**
	 * Método que se manda llamar al seleccionar el tipo de área en la pantalla de captura de documento
	 * para el remitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void seleccionarTipoAreaRemitente() {

		vhCapturaDocumentoInterface.reiniciaFiltros(3, filtros);

		if (this.filtros.getTipoAreaRemitente() == 2) {
			
			this.consultarEntidadesRemitente();
			
		} else if (this.filtros.getTipoAreaRemitente() != null && this.filtros.getTipoAreaRemitente() > 0) {

			this.consultarAreasRemitente();
		}
	}

	/**
	 * Método que consulta las entidades una vez que el usuario selecciona el tipo de área "organo desconcentrado"
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarEntidadesRemitente() {

		filtros.setListaEstadosRemitentes(bsdCapturarDocumentoInterface.consultarEntidades());
	}

	/**
	 * Método que consulta las áreas una vez que el usuario selecciona la entidad o el tipo de área del remitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarAreasRemitente() {

		filtros.setListaAreasRemitentes(bsdCapturarDocumentoInterface.consultarAreas(filtros.getTipoAreaRemitente(), filtros.getIdEntidadRemitente()));
	}

	/**
	 * Método que obtiene las personas que pueden ser remitentes para que las seleccione el usuario
	 * en base a los filtros previamente seleccionados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarRemitentes() {
		
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		String mensajeError = this.vhCapturaDocumentoInterface.validarConsultaRemitentes(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		//2.- Hacemos la consulta
		List<DTOEstructuraAreasEntity> posiblesPersonasRemitentes = this.bsdCapturarDocumentoInterface.consultarPersonas(
				filtros.getTipoAreaRemitente(), filtros.getIdAreaRemitente(), filtros.getNombreRemitente(), filtros.getApellidoRemitente());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		if (posiblesPersonasRemitentes.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			filtros.setPersonasBusquedasEncontradas(posiblesPersonasRemitentes);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaRemitente').show()");
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
		String mensajeError = this.vhCapturaDocumentoInterface.validarAgregarRemitente(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhCapturaDocumentoInterface.agregarRemitentes(filtros);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaRemitente').hide()");
	}

	/**
	 * Método para eliminar a un remitente que se selecciono
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void eliminarRemitente(DTOEstructuraAreasEntity remitenteAEliminar) {

		filtros.getRemitentes().remove(remitenteAEliminar);
	}
	
	/* ---------------------------------------- Destinatario ------------------------------------------ */
	
	/**
	 * Método que se manda llamar al seleccionar el tipo de área en la pantalla de captura de documento
	 * para el destinatario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void seleccionarTipoAreaDestinatario() {

		vhCapturaDocumentoInterface.reiniciaFiltros(4, filtros);

		if (this.filtros.getTipoAreaDestinatario() == 2) {
			
			this.consultarEntidadesDestinatario();
			
		} else if (this.filtros.getTipoAreaDestinatario() != null && this.filtros.getTipoAreaDestinatario() > 0) {

			this.consultarAreasDestinatario();
		}
	}
	
	/**
	 * Método que consulta las entidades una vez que el usuario selecciona el tipo de área "organo desconcentrado"
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarEntidadesDestinatario() {

		filtros.setListaEstadosDestinatarios(bsdCapturarDocumentoInterface.consultarEntidades());
	}
	
	/**
	 * Método que consulta las áreas una vez que el usuario selecciona la entidad o el tipo de área del remitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarAreasDestinatario() {

		filtros.setListaAreasDestinatarios(bsdCapturarDocumentoInterface.consultarAreas(filtros.getTipoAreaDestinatario(), filtros.getIdEntidadDestinatario()));
	}
	
	/**
	 * Método que consulta las áreas que coinciden con la que el usuario ha ingresado, en el modo de destinatario por área
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public void consultarDestinatarioPorArea() {
	
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		String mensajeError = this.vhCapturaDocumentoInterface.validarConsultaDestinatariosPorArea(filtros);
	
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		//2.- Hacemos la consulta
		List<DTOCAreaEntity> posiblesAreasDestinatarias = this.bsdCapturarDocumentoInterface.consultarAreasDestinatarias( filtros.getNombreAreaDestinatario());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		if (posiblesAreasDestinatarias.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			filtros.setAreasBusquedaEncontradas(posiblesAreasDestinatarias);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarAreasDestinatario').show()");
		}
	
	}
	
	/**
	 * Método que obtiene las personas que pueden ser destinatarios para que las seleccione el usuario
	 * en base a los filtros previamente seleccionados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarDestinatarios() {
		
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		String mensajeError = this.vhCapturaDocumentoInterface.validarConsultaDestinatarios(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		//2.- Hacemos la consulta
		List<DTOEstructuraAreasEntity> posiblesPersonasDestinatarios = this.bsdCapturarDocumentoInterface.consultarPersonas(
				filtros.getTipoAreaDestinatario(), filtros.getIdAreaDestinatario(), filtros.getNombreDestinatario(), filtros.getApellidoDestinatario());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		if (posiblesPersonasDestinatarios.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			filtros.setPersonasBusquedasEncontradas(posiblesPersonasDestinatarios);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaDestinatario').show()");
		}
	}

	/**
	 * Método que agrega a una área destinataria una vez seleccionado en la ventana emergente
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 09/03/2018
	 */
	public void agregarAreaDestinatario() {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhCapturaDocumentoInterface.validarAgregarAreaDestinatario(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhCapturaDocumentoInterface.agregarAreasDestinatarios(filtros);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarAreasDestinatario').hide()");
	}
	
	/**
	 * Método que agrega a un destinatario una vez seleccionado en la ventana emergente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void agregarDestinatario() {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhCapturaDocumentoInterface.validarAgregarDestinatario(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhCapturaDocumentoInterface.agregarDestinatarios(filtros);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaDestinatario').hide()");
	}

	/**
	 * Método para eliminar a un destinatario que se selecciono
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void eliminarDestinatario(DTOEstructuraAreasEntity destinatarioAEliminar) {

		filtros.getDestinatarios().remove(destinatarioAEliminar);
	}
	
	/**
	 * Método para eliminar a una área como destinatario que se selecciono
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 25/08/2017
	 */
	public void eliminarAreaDestinatario(DTOCAreaEntity destinatarioAEliminar) {

		filtros.getDestinatariosComoArea().remove(destinatarioAEliminar);
	}

	/* ---------------------------------------- CCP ------------------------------------------ */
	
	/**
	 * Método que se manda llamar al seleccionar el tipo de área en la pantalla de captura de documento
	 * para ccp
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void seleccionarTipoAreaCCP() {

		vhCapturaDocumentoInterface.reiniciaFiltros(5, filtros);

		if (this.filtros.getTipoAreaCCP() == 2) {
			
			this.consultarEntidadesCCP();
			
		} else if (this.filtros.getTipoAreaCCP() != null && this.filtros.getTipoAreaCCP() > 0) {

			this.consultarAreasCCP();
		}
	}

	/**
	 * Método que consulta las entidades una vez que el usuario selecciona el tipo de área "organo desconcentrado"
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarEntidadesCCP() {

		filtros.setListaEstadosCCP(bsdCapturarDocumentoInterface.consultarEntidades());
	}
	
	/**
	 * Método que consulta las áreas una vez que el usuario selecciona la entidad o el tipo de área del remitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarAreasCCP() {

		filtros.setListaAreasCCP(bsdCapturarDocumentoInterface.consultarAreas(filtros.getTipoAreaCCP(), filtros.getIdEntidadCCP()));
	}

	/**
	 * Método que obtiene las personas que pueden ser CCP para que las seleccione el usuario
	 * en base a los filtros previamente seleccionados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void consultarCCP() {
		
		//1.-Validamos que la búsqueda este correcta (que haya llenado correctamente los parametros
		String mensajeError = this.vhCapturaDocumentoInterface.validarConsultaCCP(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		//2.- Hacemos la consulta
		List<DTOEstructuraAreasEntity> posiblesPersonasCCP = this.bsdCapturarDocumentoInterface.consultarPersonas(
				filtros.getTipoAreaCCP(), filtros.getIdAreaCCP(), filtros.getNombreCCP(), filtros.getApellidoCCP());
		
		//3.- Validamos si trae resultados y que hacer en caso de que traiga y de que no traiaga
		if (posiblesPersonasCCP.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
			
		} else {
			
			filtros.setPersonasBusquedasEncontradas(posiblesPersonasCCP);
			RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaCCP').show()");
		}
	}

	/**
	 * Método que agrega a un CCP una vez seleccionado en la ventana emergente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void agregarCCP() {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhCapturaDocumentoInterface.validarAgregarCCP(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        return;
		}
		
		vhCapturaDocumentoInterface.agregarCCP(filtros);
		RequestContext.getCurrentInstance().execute("PF('dlgbuscarPersonaCCP').hide()");
	}

	/**
	 * Método para eliminar a un CCP que se selecciono
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void eliminarCCP(DTOEstructuraAreasEntity ccpAEliminar) {

		filtros.getCcp().remove(ccpAEliminar);
	}

	/**
	 * Método que se manda llamar una vez que el usuario selecciona el tipo de documeno que quiere abrir
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void seleccionarOpcionTipoDocumento() {
		
		try {
			
			vhCapturaDocumentoInterface.reiniciaFiltros(6, filtros);
			
			//1.- Documentos blancos
			if (this.filtros.getTipoCapturaDocumento().equals(1)) {
				vhCapturaDocumentoInterface.generarHyperLinkDocumentoBlanco(filtros);
				RequestContext.getCurrentInstance().execute("abreWord();");
			}
		
		} catch (Exception e) {
			
			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! al seleccionar el tipo de captura que usuara el usuario (documento blanco, adjuntar, plantilla)");
			logger.error("<=================== Clase: MBCapturaDocumento , Método: seleccionarOpcionTipoDocumento");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}
	}

	/**
	 * Método que se manda llamar cundo el usuario selecciona la opción "plantilla" 
	 * para capturar un documento
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void seleccionarTipoDocumentoPlantilla() {
		if(filtros.getIdPlantilla() != null) {
			try {

				vhCapturaDocumentoInterface.generarHyperLinkPlantilla(filtros);
				RequestContext.getCurrentInstance().execute("abreWord();");

			} catch(Exception e) {
				Utilidades.enviaMensajeGeneral(null, 
						"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
						FacesMessage.SEVERITY_WARN);
				
				logger.error("<===================== ERROR!! al generar el hyperlink de la plantilla!!!");
				logger.error("<=================== Clase: MBCapturaDocumento , Método: seleccionarTipoDocumentoPlantilla");
				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				logger.error("<=================== ID_PLANTILLA: " + filtros.getIdPlantilla().getIdPlantilla());
				logger.error("<=================== NOMBRE DE LA PLANTILLA: " + filtros.getIdPlantilla().getNombrePlantilla());
				logger.error("", e);
			}
		}
	}

	/**
	 * Método que se manda llamar cuando se quiere abrir el word que se va a editar
	 * (previamente ya se debio crear el hyperlink
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 */
	public void abrirWord() {

		try {

			FacesContext.getCurrentInstance().getExternalContext().redirect(this.filtros.getHyperLinkWord());

		} catch (Exception e) {

			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! al abrir el word!!! (documento blanco, plantilla)");
			logger.error("<=================== Clase: MBCapturaDocumento , Método: abrirWord");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("<=================== TIPO CAPTURA DE DOCUMENTO: " + filtros.getTipoCapturaDocumento());
			logger.error("", e);
		}
	}

	/**
	 * Método que se manda llamar al adjuntar el documento, una vez
	 * que termina de generar el temporal de primefaces se hace la llamada a este.
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 25/08/2017
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 03/04/2018
	 * 
	 */
	public void adjuntarDocumento(FileUploadEvent archivo) {
		
		try {

			vhCapturaDocumentoInterface.reiniciaFiltros(6, filtros);
			
			//1.- validamos los metadatos del archivo adjunto
			boolean archivoValido = false;
			archivoValido = vhCapturaDocumentoInterface.validaMetadatosArchivoAnexo(archivo, "documento");

			//2.- validamos si el archivo que se esta agregando esta o no repetido
			boolean adjuntoRepetido = filtros.getNombreOriginalPdfAux() == null ? false :
										filtros.getNombreOriginalPdfAux().equalsIgnoreCase(archivo.getFile().getFileName() ) ? true: false;
			
			if( !adjuntoRepetido ){
			
				//3.- si el tipo del archivo es valido, guardamos en archivo en una carpeta temporal
				if(archivoValido)
					vhCapturaDocumentoInterface.generarTemporalArchivoAdjunto(filtros, archivo, false, true);
				
				//4.- si el tipo del archivo no es valido, mandamos mensaje a la pantalla informando esto
				else{	
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Formato inválido, favor de verificar el archivo", ""));
				}
			}
			
			//4.- si el archivo esta repetido, mandamos mensaje a la pantalla informando esto
			else{
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este archivo ya ha sido adjuntado, favor de verificar el archivo", ""));
			}
			
		} catch (Exception e) {

			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! adjuntar el documento!!!");
			logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}
	}

	/**
	 * Método que se manda llamar al adjuntar un anexo , una vez
	 * que termina de generar el temporal de primefaces se hace la llamada a este.
	 * 
	 * @author Giovanni Hernández Alonso
	 * @since 22/03/2018
	 * 
	 */
	public void adjuntarAnexo(FileUploadEvent archivo) {
		
		try {	
			
			//1.- validamos los metadatos del archivo adjunto
			boolean archivoValido = false;
			archivoValido = vhCapturaDocumentoInterface.validaMetadatosArchivoAnexo(archivo, "anexo");

			//2.- validamos si el adjunto que se esta agregando esta o no repetido
			boolean adjuntoRepetido = false;
			adjuntoRepetido = vhCapturaDocumentoInterface.evaluarAdjuntoRepetidos( filtros.getAnexos() , archivo.getFile().getFileName()  );
			 
			if( !adjuntoRepetido ){

				//3.- si el tipo del archivo es valido y no esta repetido guardamos el archivo en una carpeta temporal
				if(archivoValido)
					vhCapturaDocumentoInterface.generarTemporalArchivoAdjunto(filtros, archivo, true, false);

				//4.- si el tipo del archivo no es valido, mandamos mensaje a la pantalla informando esto
				else{
					
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Formato inválido, favor de verificar el archivo", ""));
				}
			}
			
			//4.- si el adjunto esta repetido, mandamos mensaje a la pantalla informando esto
			else{
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este anexo ya ha sido adjuntado, favor de verificar el anexo", ""));
			}
 
		} catch (Exception e) {

			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
			logger.error("<===================== ERROR!! adjuntar el anexo!!!");
			logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarAnexo");
			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			logger.error("", e);
		}
	}
	
	/**
	 * Método final que guarda el documento creado por el usuario
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @throws Exception 
	 * @since 25/08/2017 
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 26/03/2018
	 */
	public void guadarDocumento() throws Exception {

		//1.-Validamos los datos que se van a agregar
		String mensajeError = this.vhCapturaDocumentoInterface.validaCapturaDocumento(filtros);
		
		if (!mensajeError.isEmpty()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, ""));
	        throw new Exception("excepcion controlada");
		}
	
		try {
	
			idDocumento = this.bsdCapturarDocumentoInterface.guardarDocumento(filtros);
			
			if(filtroResponder != null){
				filtroResponder.setIdDocumentoRespuesta(idDocumento);
				if(atencion != null){
					this.bsdCapturarDocumentoInterface.guardaRespuestaTurnado(filtroResponder, atencion);
				}else if(recibido != null){
					this.bsdCapturarDocumentoInterface.guardaRespuestaTurnado(filtroResponder, recibido);
				}else if(hatencion != null){
					this.bsdCapturarDocumentoInterface.guardaRespuestaTurnado(filtroResponder, hatencion);
				}else if(hrecibido != null){
					this.bsdCapturarDocumentoInterface.guardaRespuestaTurnado(filtroResponder, hrecibido);
				}
				
			}
			
			
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
	 * Elimina un documento Anexo que se haya agregado en pantalla
	 * 
	 * @author Giovanni Hernández Alonso 
	 * @since 23/03/2018 
	 */
	public void eliminarAnexo( DTODocumentoAnexoHelper anexo ) {
	
		if( anexo != null ){
			
			filtros.getAnexos().remove(anexo);	 
		}	
	}
	
	/**
	 * Valida que en una cadena de texto, solo vengan letras, acentos y espacios
	 * 
	 * @author Giovanni Hernández Alonso 
	 * @since 03/05/2018 
	 */
	public void validaLetrasAcentosEsp(String cadena, Integer reinicio){
		
		boolean cadenaValida = false;
		
		Pattern p = Pattern.compile( Utilidades.mensajeProperties("regex_solo_letras_acentos_espacios_numeros")  );
		Matcher m = p.matcher(  cadena  );
		cadenaValida = m.matches();
		
		// si no es una cadena valida, mostramos un mensaje en pantalla
		if( !cadenaValida ){
		
			// reiniciamos valores de las variables segun sea el caso
			if( reinicio.equals( new Integer(1)) ){
				filtros.setNombreRemitente("");
			}
			
			else if( reinicio.equals( new Integer(2)) ){
				filtros.setApellidoRemitente("");
			}
			
			else if( reinicio.equals( new Integer(3)) ){
				filtros.setNombreDestinatario("");
			}
			
			else if( reinicio.equals( new Integer(4)) ){
				filtros.setApellidoDestinatario("");
			}
			
			Utilidades.enviaMensajeGeneral(null, 
					Utilidades.mensajeProperties("mensaje_regex_solo_letras_acentos_espacios"),"",
					FacesMessage.SEVERITY_WARN);
		}
		
	}
	
	
	/* ------------------------------------ Getters & Setters ------------------------------------ */

	/**
	 * @return valor de tipo DTOFiltrosCapturaDocumentoHelper que esta contenido en la variable filtros
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public DTOFiltrosCapturaDocumentoHelper getFiltros() {
		return filtros;
	}


	/**
	 * @param filtros : valor que se ingresa a la variable de tipo DTOFiltrosCapturaDocumentoHelper
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setFiltros(DTOFiltrosCapturaDocumentoHelper filtros) {
		this.filtros = filtros;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaDetalle
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public DTOEstructuraAreasEntity getPersonaDetalle() {
		return personaDetalle;
	}

	/**
	 * @param personaDetalle : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setPersonaDetalle(DTOEstructuraAreasEntity personaDetalle) {
		this.personaDetalle = personaDetalle;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable areaDeralle
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 13/03/2018
	 */
	public DTOCAreaEntity getAreaDetalle() {
		return areaDetalle;
	}

	/**
	 * @param areaDeralle : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 13/03/2018
	 */
	public void setAreaDetalle(DTOCAreaEntity areaDetalle) {
		
		List<DTOCTipoAreaEntity> tiposArea = filtros.getListaTiposAreaDestinatarios();
		
		if( tiposArea != null && tiposArea.size() > 0 ){
			for (DTOCTipoAreaEntity tArea : tiposArea) {
				
				if( tArea.getIdTipoArea().equals( areaDetalle.getTipoArea() ) )
					areaDetalle.setDtoTipoArea(tArea);
			}
		}
		
		
		this.areaDetalle = areaDetalle;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumentoRespondido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public Integer getIdDocumentoRespondido() {
		return idDocumentoRespondido;
	}

	/**
	 * @param idDocumentoRespondido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setIdDocumentoRespondido(Integer idDocumentoRespondido) {
		this.idDocumentoRespondido = idDocumentoRespondido;
	}

	/**
	 * @return the idHistoricoRecepcion
	 */
	public Integer getIdHistoricoRecepcion() {
		return idHistoricoRecepcion;
	}

	/**
	 * @param idHistoricoRecepcion the idHistoricoRecepcion to set
	 */
	public void setIdHistoricoRecepcion(Integer idHistoricoRecepcion) {
		this.idHistoricoRecepcion = idHistoricoRecepcion;
	}

	/**
	 * @return the comentarioRespondido
	 */
	public String getComentarioRespondido() {
		return comentarioRespondido;
	}

	/**
	 * @param comentarioRespondido the comentarioRespondido to set
	 */
	public void setComentarioRespondido(String comentarioRespondido) {
		this.comentarioRespondido = comentarioRespondido;
	}

	/**
	 * @return the atencion
	 */
	public DTOBandejaEAtencionEntity getAtencion() {
		return atencion;
	}

	/**
	 * @param atencion the atencion to set
	 */
	public void setAtencion(DTOBandejaEAtencionEntity atencion) {
		this.atencion = atencion;
	}

	/**
	 * @return the recibido
	 */
	public DTOBandejaERecibidosEntity getRecibido() {
		return recibido;
	}

	/**
	 * @param recibido the recibido to set
	 */
	public void setRecibido(DTOBandejaERecibidosEntity recibido) {
		this.recibido = recibido;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo DTOHBandejaEAtencionEntity que esta contenido en la variable hatencion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public DTOHBandejaEAtencionEntity getHatencion() {
		return hatencion;
	}

	/**
	 * @param hatencion : valor que se ingresa a la variable de tipo DTOHBandejaEAtencionEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public void setHatencion(DTOHBandejaEAtencionEntity hatencion) {
		this.hatencion = hatencion;
	}

	/**
	 * @return valor de tipo DTOHBandejaERecibidosEntity que esta contenido en la variable hrecibido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public DTOHBandejaERecibidosEntity getHrecibido() {
		return hrecibido;
	}

	/**
	 * @param hrecibido : valor que se ingresa a la variable de tipo DTOHBandejaERecibidosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public void setHrecibido(DTOHBandejaERecibidosEntity hrecibido) {
		this.hrecibido = hrecibido;
	}
	
	/**
	 * @return valor de tipo DecimalFormat que esta contenido en la variable df
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 02/04/2018
	 */	
	public DecimalFormat getDf() {
		return df;
	}

	/**
	 * @param df : valor que se ingresa a la variable de tipo DecimalFormat
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 02/04/2018
	 */
	public void setDf(DecimalFormat df) {
		this.df = df;
	}
}