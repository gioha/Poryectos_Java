package mx.ine.gestion.mb;

/**
 * @(#)MBAcrónimos.java 01/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDAcronimosInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOAcronimoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper;
import mx.ine.gestion.util.Constantes;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHAcronimosInterface;
import mx.org.ine.servicios.utils.PaginarLazy;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
//import mx.ine.comunicaciones.dto.DTOEstados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase de la capa de MB para el módulo de Acrónimos.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 01/11/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
public class MBAcronimos implements Serializable{
	
	@Autowired
	@Qualifier("bsdAcronimos")
	private transient BSDAcronimosInterface bsdAcronimosInterface;

	@Autowired
	@Qualifier("vhAcronimos")
	private transient VHAcronimosInterface vhAcronimosInterface;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBAcronimos.class);

	// /////////////////////////////////////////////
	// -----------------ATRIBUTOS-----------------//
	// /////////////////////////////////////////////

	/*
	 * Constantes
	 */
	private Constantes constantes;
	
	/**
	 * Serial generado
	 */
	private static final long serialVersionUID = 7507048381873937918L;

	/**
	 * Lista con los registros de los acronimos, principal
	 */
	private PaginarLazy<DTOAcronimoEntity, DTOFiltrosAcronimosHelper> listaAcronimos;

	/**
	 * Objeto que contiene los filtros que se utilizan en la pantalla para crear acronimos
	 * SOLAMENTE LOS QUE SE UTILIZAN PARA CREAR ACRONIMOS NO LOS QUE ESTAN EN LA TABLA CON LOS RESULTADOS
	 */
	private DTOFiltrosAcronimosHelper filtros;
	
	/**
	 * Lista con la lista de tipos de documentos que sí tienen documentos
	 */
	private List<DTOCTipoDocumentoEntity> listaTipoDocConDocs; 

	/**
	 * Lista con la lista de tipos de área que sí tienen documentos
	 */
	private List<DTOCTipoAreaEntity> listaTipoAreaConDocs;

	/*
	 * Atributo que guarda el acrónimo seleccionado
	 */
	private DTOAcronimoEntity acronimoSeleccionado;

	/*
	 * DTO de ayuda para acróninmos
	 */
	private DTOAcronimoHelper acronimoCreacionHelper;

	/*
	 * DTO de ayuda para acróninmos
	 */
	private DTOAcronimoHelper acronimoModificacionHelper;
	
	/*
	 * DTO de ayuda para acróninmos
	 */
	private DTOAcronimoEntity acronimoModificacionEntity;

	// /////////////////////////////////////////////
	// -----------------MÉTODOS-------------------//
	// /////////////////////////////////////////////
	/**
	 * Método para inicializar las cosas necesarias del módulo de acrónimos
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public void inicializar() {
		
		this.filtros = new DTOFiltrosAcronimosHelper();
		this.filtros.setListaTiposAreas(bsdAcronimosInterface.consultarTiposAreas());
		this.filtros.setListaEstados(bsdAcronimosInterface.consultarEstados());
		this.filtros.setListaTiposDocumentos(bsdAcronimosInterface.consultarTiposDocumentos());
		this.filtros.setFormatosAnio(vhAcronimosInterface.obtenerFormatosAnio());
		
		acronimoCreacionHelper = new DTOAcronimoHelper();
		acronimoModificacionHelper = new DTOAcronimoHelper();
		vhAcronimosInterface.inicializarAcronimo(acronimoCreacionHelper);

		listaTipoDocConDocs = bsdAcronimosInterface.consultarTipoDocumentoConDocumento();

		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		this.listaAcronimos = new PaginarLazy<DTOAcronimoEntity, DTOFiltrosAcronimosHelper>("bsdAcronimos", this.filtros);

		if (!usuarioLogueado.getRol().equals("GESTION4.ADMIN_AREA.OC")) {
			listaTipoAreaConDocs = bsdAcronimosInterface.consultarTipoAreaConDocumento();
			this.filtros.setTipoAreaSeleccionada(Integer.valueOf(1));
			this.filtros.setListaAreas(bsdAcronimosInterface.consultarAreas(Integer.valueOf(1), Integer.valueOf(0)));
			this.filtros.setIdEntidadSeleccionada(usuarioLogueado.getIdEstado());
		} else {
			this.filtros.setTipoAreaSeleccionada(usuarioLogueado.getTipoArea());
			this.filtros.setIdAreaSeleccionada(usuarioLogueado.getIdArea());
			this.filtros.setListaAreas(bsdAcronimosInterface.consultarAreas(usuarioLogueado.getTipoArea(), Integer.valueOf(-1)));

			recuperarAcronimosPorArea(filtros.getIdAreaSeleccionada());
		}

		// Actualización de la tabla de acrónimos
		filtros.setListaAreasAcronimos( bsdAcronimosInterface.recuperarAreasAcronimos() );
	}

	/**
	 * 
	 * @return
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/01/2018
	 */
	public String obtenerNombreArea() {
		return vhAcronimosInterface.obtenerDescripcionDeListaAreas(this.filtros.getListaAreas(), this.filtros.getIdAreaSeleccionada());
	}

	/**
	 * 
	 * @return
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/01/2018
	 */
	public String obtenerNombreEntidad() {
		return vhAcronimosInterface.obtenerDescripcionDeEntidades(this.filtros.getListaEstados(), this.filtros.getIdEntidadSeleccionada());
	}
	
	/**
	 * Método para mostrar áreas para órganos centrales
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public void mostrarAreas() {
		this.filtros.setIdEntidadSeleccionada(null);
		this.filtros.setIdAreaSeleccionada(null);
		if (this.filtros.getTipoAreaSeleccionada() != null) {
			if (this.filtros.getTipoAreaSeleccionada().intValue() == 1 || this.filtros.getTipoAreaSeleccionada().intValue() == 3) {

				this.filtros.setListaAreas(bsdAcronimosInterface.consultarAreas(this.filtros.getTipoAreaSeleccionada(), Integer.valueOf(0)));

			} else {
				this.filtros.setListaAreas(null);
			}
		}
	}

	/**
	 * Método para método para mostrar áreas para órganos desconcentrados
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public void mostrarAreasEntidad() {
		if (this.filtros.getTipoAreaSeleccionada() != null) {
			if (this.filtros.getTipoAreaSeleccionada().intValue() == 2) {

				this.filtros.setListaAreas(bsdAcronimosInterface.consultarAreas(this.filtros.getTipoAreaSeleccionada(), this.filtros.getIdEntidadSeleccionada()));

			}
		}
	}

	/**
	 * Función para obtener el tipo de área
	 * 
	 * @param Integer con el tipo de área
	 * @return String con la cadena
	 * 
	 * @since 13/11/2017
	 */
	public String obtenerTipoArea(Integer id) {
		try {
			return vhAcronimosInterface.obtenerDescripcionTipoArea(this.filtros.getListaTiposAreas(), id);
		} catch(Exception e) {
			return "";
		}
	}

	/**
	 * Método para generar el acrónimo 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void generarAcronimo() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String valor = params.get("valor");
		int coor1 = Integer.parseInt(params.get("coordenadas1"));
		int coor2 = Integer.parseInt(params.get("coordenadas2"));

		vhAcronimosInterface.generarAcronimo(acronimoCreacionHelper, coor1,	coor2, valor);
	}

	/**
	 * Método para generar el acrónimo en la edición
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void generarAcronimoEdicion() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String valor = params.get("valor");
		int coor1 = Integer.parseInt(params.get("coordenadas1"));
		int coor2 = Integer.parseInt(params.get("coordenadas2"));

		vhAcronimosInterface.generarAcronimo(acronimoModificacionHelper, coor1,
				coor2, valor);
	}

	/**
	 * Método para generar la vista previa 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void generarVistaPrevia() {
		vhAcronimosInterface.generarVistaPrevia(acronimoCreacionHelper);
	}
	
	/**
	 * Método para generar la vista previa en edición 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void generarVistaPreviaEdicion() {
		vhAcronimosInterface.generarVistaPrevia(acronimoModificacionHelper);
	}

	/**
	 * Método para validar que se pueda guardar el acrónimo 
	 * 
	 * @return boolean: si se valido o no el acrónimo.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public boolean validarGuardarAcronimo() {
		String[][] estructura = acronimoCreacionHelper.getEstructuraAcronimos();
		if (estructura[1][0] == null || estructura[1][2] == null
				|| estructura[1][4] == null) {
			vhAcronimosInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_validacion_cajas"));
			return false;
		}
		return true;
	}

	/**
	 * Método para guardar un acrónimo nuevo
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public void guardarAcronimo() {
		if (validarGuardarAcronimo()) {

			String acronimoBase = vhAcronimosInterface
					.generarAcronimoBase(acronimoCreacionHelper);

			DTOAcronimoEntity acronimoGuardar = new DTOAcronimoEntity();
			acronimoGuardar.setIdArea(this.filtros.getIdAreaSeleccionada());
			acronimoGuardar.setTipoArea(this.filtros.getTipoAreaSeleccionada());
			acronimoGuardar.setIdTipoDocumento(acronimoCreacionHelper
					.getIdTipoDocumentoSeleccionado());
			acronimoGuardar
					.setDescripcion(acronimoCreacionHelper.getAcronimo());
			acronimoGuardar.setAcronimoAgrupacion(acronimoBase);

			try {
				
				bsdAcronimosInterface.guardarAcronimo(acronimoGuardar);
				vhAcronimosInterface
						.inicializarAcronimo(acronimoCreacionHelper);

				listaTipoDocConDocs = bsdAcronimosInterface.consultarTipoDocumentoConDocumento();
				listaTipoAreaConDocs = bsdAcronimosInterface.consultarTipoAreaConDocumento();

				if(filtros.getIdAreaSeleccionada().equals(filtros.getIdAreaAcronimos())) {
					RequestContext.getCurrentInstance().execute("PF('tablaAcronimos').clearFilters();");
					filtros.setListaAcronimosArea( bsdAcronimosInterface.recuperarAcronimosPorArea(filtros.getIdAreaAcronimos()) );
				}

				vhAcronimosInterface.mostrarMensajeGrowl("exito", "EXITO",
						"Se guardo correctamente el acrónimo.");
				vhAcronimosInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_exito"),
						Utilidades.mensajeProperties("titulo_growl_exito"),
						Utilidades.mensajeProperties("mensaje_exito_guardar_acronimo"));			
				

			} catch (Exception e) {
				vhAcronimosInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_guardar_acronimo"));
			}
		}
	}
	
	/**
	 * Método para validar que se puede editar un acrónimo
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public boolean validarGuardarAcronimoEdicion() {
		String[][] estructura = acronimoModificacionHelper.getEstructuraAcronimos();
		if (estructura[1][0] == null || estructura[1][2] == null || estructura[1][4] == null) {
			vhAcronimosInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_validacion_cajas"));
			return false;
		}
		return true;
	}

	/**
	 * Método para modificar un acrónimo en edición
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void guardarAcronimoEdicion() {

		if (validarGuardarAcronimoEdicion()) {

			String acronimoBase = vhAcronimosInterface.generarAcronimoBase(acronimoModificacionHelper);

			DTOAcronimoEntity acronimoGuardar = acronimoModificacionEntity;
			
			acronimoGuardar.setAcronimoAgrupacion(acronimoBase);
			acronimoGuardar.setDescripcion(acronimoModificacionHelper.getAcronimo());
			acronimoGuardar.setIdTipoDocumento(acronimoModificacionHelper.getIdTipoDocumentoSeleccionado());

			try {
				bsdAcronimosInterface.guardarAcronimo(acronimoGuardar);
				
				vhAcronimosInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_exito"),
						Utilidades.mensajeProperties("titulo_growl_exito"),
						Utilidades.mensajeProperties("mensaje_exito_editar_acronimo"));
				
				RequestContext.getCurrentInstance().execute(
						"PF('edicion-acronimos').hide()");

			} catch (Exception e) {
				vhAcronimosInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_editar_acronimo"));
			}
		}
	}
	
	/**
	 * Método para eliminar el acrónimo
	 * @param DTOAcronimoEntity acronimo: Objeto con el acrónimo que será eliminado
	 * @return void
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public void eliminarAcronimo(DTOAcronimoEntity acronimo) {
		try {
			if(!bsdAcronimosInterface.consultarAcronimoEnUso(acronimo)) {
				bsdAcronimosInterface.eliminarAcronimo(acronimo);
				listaTipoDocConDocs = bsdAcronimosInterface.consultarTipoDocumentoConDocumento();
				listaTipoAreaConDocs = bsdAcronimosInterface.consultarTipoAreaConDocumento();

				RequestContext.getCurrentInstance().execute("PF('tablaAcronimos').clearFilters();");
				filtros.setListaAcronimosArea( bsdAcronimosInterface.recuperarAcronimosPorArea(filtros.getIdAreaAcronimos()) );
			} else {
				vhAcronimosInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						"El acrónimo está siendo utilizado por una plantilla");
//						Utilidades.mensajeProperties("mensaje_no_eliminar_acronimo_en_uso"));
			}
		} catch (Exception e) {
			vhAcronimosInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					"Ha ocurrido un error al intentar eliminar el acrónimo");
//					Utilidades.mensajeProperties("mensaje_advertencia_no_eliminar_acronimo"));

			log.error("<===================== ERROR!! al eliminar el acronimo");
			log.error("<=================== Clase: MBAcronimos , Método: eliminarAcronimo");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	/**
	 * Método para abrir la ventana de edición con los datos del acrónimo
	 * 
	 * @param acronimo: Objeto DTOAcronimoEntity con el acrónimo que se va a editar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void abrirEditarAcronimo(DTOAcronimoEntity acronimo, boolean soloLectura) {

		try {
			
			acronimoModificacionHelper = new DTOAcronimoHelper();
			acronimoModificacionEntity = acronimo;
			vhAcronimosInterface.inicializarAcronimoGuardado(acronimo, acronimoModificacionHelper);

			if (!soloLectura) {
				RequestContext.getCurrentInstance().execute("PF('edicion-acronimos').show()");
			} else {
				RequestContext.getCurrentInstance().execute("PF('visualizar-acronimos').show()");
			}
			
		} catch (Exception e) {
			vhAcronimosInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_advertencia_no_acronimo"));
			
			log.error("<===================== ERROR!! al eliminar el acronimo");
			log.error("<=================== Clase: MBAcronimos , Método: abrirEditarAcronimo");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	// /////////////////////////////////////////////
	// ---------------GETTERS Y SETTERS-----------//
	// /////////////////////////////////////////////

	/**
	 * @return valor de tipo PaginarLazy<DTOAcronimoEntity,DTOFiltrosAcronimosHelper> que esta contenido en la variable listaAcronimos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public PaginarLazy<DTOAcronimoEntity, DTOFiltrosAcronimosHelper> getListaAcronimos() {
		return listaAcronimos;
	}

	/**
	 * @param listaAcronimos : valor que se ingresa a la variable de tipo PaginarLazy<DTOAcronimoEntity,DTOFiltrosAcronimosHelper>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setListaAcronimos(
			PaginarLazy<DTOAcronimoEntity, DTOFiltrosAcronimosHelper> listaAcronimos) {
		this.listaAcronimos = listaAcronimos;
	}

	/**
	 * @return variable de tipo DTOAcronimoEntity que está contenido en la variable acronimoSeleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public DTOAcronimoEntity getAcronimoSeleccionado() {
		return acronimoSeleccionado;
	}

	/**
	 * @param acronimoSeleccionad: valor que se ingresa a la variable acronimoSeleccionado de tipo DTOAcronimoEntity
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setAcronimoSeleccionado(DTOAcronimoEntity acronimoSeleccionado) {
		this.acronimoSeleccionado = acronimoSeleccionado;
	}

	/**
	 * @return helper para el MB de tipo DTOAcronimoHelper que está contenido en la variable acronimoCreacionHelper
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public DTOAcronimoHelper getAcronimoCreacionHelper() {
		return acronimoCreacionHelper;
	}

	/**
	 * @param acronimoCreacionHelper: valor que se ingresa a la variable acronimoCreacionHelper de tipo DTOAcronimoHelper
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setAcronimoCreacionHelper(
			DTOAcronimoHelper acronimoCreacionHelper) {
		this.acronimoCreacionHelper = acronimoCreacionHelper;
	}

	/**
	 * @return helper para el MB de tipo DTOAcronimoHelper que está contenido en la variable acronimoModificacionHelper
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public DTOAcronimoHelper getAcronimoModificacionHelper() {
		return acronimoModificacionHelper;
	}

	/**
	 * @param acronimoModificacionHelper: valor que se ingresa a la variable acronimoModificacionHelper de tipo DTOAcronimoHelper
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setAcronimoModificacionHelper(
			DTOAcronimoHelper acronimoModificacionHelper) {
		this.acronimoModificacionHelper = acronimoModificacionHelper;
	}

	/**
	 * @return variable de tipo DTOAcronimoEntity que está contenido en la variable acronimoModificacionEntity
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public DTOAcronimoEntity getAcronimoModificacionEntity() {
		return acronimoModificacionEntity;
	}

	/**
	 * @param acronimoModificacionEntity: valor que se ingresa a la variable acronimoModificacionEntity de tipo DTOAcronimoEntity
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void setAcronimoModificacionEntity(
			DTOAcronimoEntity acronimoModificacionEntity) {
		this.acronimoModificacionEntity = acronimoModificacionEntity;
	}

	/**
	 * @return the constantes
	 */
	public Constantes getConstantes() {
		return constantes;
	}

	/**
	 * @param constantes the constantes to set
	 */
	public void setConstantes(Constantes constantes) {
		this.constantes = constantes;
	}

	/**
	 * @return variable de tipo List<DTOCTipoDocumentoEntity> contenida en listaTipoDocConDocs
	 * 
	 * @since 12/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOCTipoDocumentoEntity> getListaTipoDocConDocs() {
		return listaTipoDocConDocs;
	}

	/**
	 * @param listaTipoDocConDocs: variable de tipo List<DTOCTipoDocumentoEntity> contenida en listaTipoDocConDocs
	 *
	 * @since 12/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaTipoDocConDocs(
			List<DTOCTipoDocumentoEntity> listaTipoDocConDocs) {
		this.listaTipoDocConDocs = listaTipoDocConDocs;
	}

	/**
	 * @return variable de tipo List<DTOCTipoAreaEntity> contenida en listaTipoAreaConDocs
	 * 
	 * @since 12/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOCTipoAreaEntity> getListaTipoAreaConDocs() {
		return listaTipoAreaConDocs;
	}

	/**
	 * @param listaTipoAreaConDocs: variable de tipo List<DTOCTipoAreaEntity> contenida en listaTipoAreaConDocs
	 *
	 * @since 12/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaTipoAreaConDocs(
			List<DTOCTipoAreaEntity> listaTipoAreaConDocs) {
		this.listaTipoAreaConDocs = listaTipoAreaConDocs;
	}

	/**
	 * @return valor de tipo DTOFiltrosAcronimosHelper que esta contenido en la variable filtros
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public DTOFiltrosAcronimosHelper getFiltros() {
		return filtros;
	}

	/**
	 * @param filtros : valor que se ingresa a la variable de tipo DTOFiltrosAcronimosHelper
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setFiltros(DTOFiltrosAcronimosHelper filtros) {
		this.filtros = filtros;
	}

	/**
	 * Método encargado de recuperar una lista de acrónimos con base en el área del
	 * usuario logueado o bien, con base en el área seleccionada por dicho usuario.
	 * @return List<DTOAcronimoEntity>: Lista de acrónimos recuperados
	 * @param Integer idArea
	 * 
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public void recuperarAcronimosPorArea(Integer idArea) {
		RequestContext.getCurrentInstance().execute("PF('tablaAcronimos').clearFilters();");
		filtros.setListaAcronimosArea( bsdAcronimosInterface.recuperarAcronimosPorArea(idArea) );
	}

}
