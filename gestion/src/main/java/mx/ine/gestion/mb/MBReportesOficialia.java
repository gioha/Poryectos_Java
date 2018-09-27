/**
 * @(#)MBReportesOficialia.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import mx.ine.gestion.bsd.inter.BSDReportesOficialiaInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;

/**
 * Clase que recibe todas las peticiones que se ejecutan en la vista de Reportes de Oficialía.
 * @author Homero Fidel Villanueva
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public class MBReportesOficialia implements Serializable {
	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = -3251664000157733487L;

	@Autowired
	@Qualifier("bsdReportesOficialia")
	private transient BSDReportesOficialiaInterface bsdReportesOf;

	/**
	 * Objeto de ayuda que contiene los atributos que guardan la información capturada
	 * y/o seleccionada por el usuario en la vista de Reportes de la Oficialía.
	 */
	private DTOReportesOficialiaHelper filtrosReportesOf;

	/* ------------------------------ Atributos generales ------------------------------ */
	/**
	 * Atributo que contiene el objetos del usaurio logeado
	 */
	private DTOUsuarioLogin usuarioLogueado;

	/**
	 * Objeto que contiene el formato de las fechas de creación y recepción del documento)
	 */
	String fechaFormat = (new SimpleDateFormat("dd/MMMM/yyyy")).format(new Date());

	/**
	 * Método de carga al entrar a la vista de Reportes de la Oficialía.
	 * @author Homero Fidel Villanueva
	 * @update José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void init() {
		filtrosReportesOf = new DTOReportesOficialiaHelper();
		usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		bsdReportesOf.determinarCatalogosDeFolios(filtrosReportesOf);		// Esto puede ser optimizado
		filtrosReportesOf.setCatalogoTiposDoc( bsdReportesOf.recuperarTiposDocumento() );
		filtrosReportesOf.setCatalogoSecciones( bsdReportesOf.recuperarSecciones() );
		filtrosReportesOf.setCatalogoMeses( bsdReportesOf.determinarMesesAnio() );
		filtrosReportesOf.setCatalogoOrdenacion( bsdReportesOf.determinarParametrosOrden() );
		filtrosReportesOf.setTipoRemitente("Interno");
		filtrosReportesOf.setAreasDestinatario( bsdReportesOf.recuperarAreas(usuarioLogueado.getRol(), usuarioLogueado.getIdOficialia()));
		filtrosReportesOf.setCatalogoTiposArea( bsdReportesOf.recuperarTiposDeArea() );
		filtrosReportesOf.setCatalogoFiltrosReporte( bsdReportesOf.determinarFiltrosReporte() );
	}

	/**
	 * Método que obtiene un catálogo de categorías (List<DTOCCategoriasEntity>) y lo inserta en
	 * DTOFiltrosReportesOficialiaHelper para su posterior selección en la vista de Reportes de Oficialía.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void recuperarCategorias() {
		filtrosReportesOf.setCatalogoCategorias( bsdReportesOf.recuperarCategorias(filtrosReportesOf.getIdSeccion()) );
	}

	/**
	 * Método que obtiene un listado de entidades y un listado de áreas con base en el tipo de área
	 * seleccionado por el usuario en la vista de Reportes de Oficialía.
	 * @param String tipoBusqueda
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */	
	public void recuperarEntidadesAreas(String tipoBusqueda) {
		// 1. La búsqueda realizada viene desde la sección de Remitente (s)
		if(tipoBusqueda.equals("Remitente")) {
			filtrosReportesOf.setIdEntidadRemitente(null);
			// 1.1. Se ha seleccionado la opción de Órganos Desconcentrados (ID - 2)
			if(filtrosReportesOf.getIdTipoAreaRemitente().equals(2)) {
				filtrosReportesOf.setEntidadesRemitente( bsdReportesOf.recuperarEntidades() );
				filtrosReportesOf.setAreasRemitente( new ArrayList<DTOCAreaEntity>() );
			} else {
				filtrosReportesOf.setAreasRemitente( bsdReportesOf.recuperarAreas(filtrosReportesOf.getIdTipoAreaRemitente(),
						  							 							  filtrosReportesOf.getIdEntidadRemitente()) );
			}
		}

		// 2. La búsqueda realizada viene desde la sección de Destinatario (s)
		if(tipoBusqueda.equals("Destinatario")) {
			filtrosReportesOf.setIdEntidadDestinatario(null);
			// 2.1. Se ha seleccionado la opción de Órganos Desconcentrados (ID - 2)
			if(filtrosReportesOf.getIdTipoAreaDestinatario().equals(2)) {
				filtrosReportesOf.setEntidadesDestinatario( bsdReportesOf.recuperarEntidades() );
				filtrosReportesOf.setAreasDestinatario( new ArrayList<DTOCAreaEntity>() );
			} else {
				filtrosReportesOf.setAreasDestinatario( bsdReportesOf.recuperarAreas(filtrosReportesOf.getIdTipoAreaDestinatario(),
																					 filtrosReportesOf.getIdEntidadDestinatario()) );
			}
		}
	}

	/**
	 * Método que obtiene un catálogo de áreas (List<DTOCAreaEntity>) y lo inserta en
	 * DTOFiltrosReportesOficialiaHelper para su posterior selección en la vista de Reportes de Oficialía.
	 * @param String tipoBusqueda
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void recuperarAreas(String tipoBusqueda) {
		if(tipoBusqueda.equals("Remitente")) {
			filtrosReportesOf.setAreasRemitente( bsdReportesOf.recuperarAreas(filtrosReportesOf.getIdTipoAreaRemitente(),
																			  filtrosReportesOf.getIdEntidadRemitente()) );
		}
		if(tipoBusqueda.equals("Destinatario")) {
			filtrosReportesOf.setAreasDestinatario( bsdReportesOf.recuperarAreas(filtrosReportesOf.getIdTipoAreaDestinatario(),
																				 filtrosReportesOf.getIdEntidadDestinatario()) );
		}
	}

	/**
	 * Método que obtiene una lista de personas encontradas por la búsqueda del usuario
	 * en la vista de Reportes de Oficialía.
	 * @return void
	 * @param String tipoBusqueda
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void recuperarPersonas(String tipoBusqueda) {
		filtrosReportesOf.setTipoBusqueda(tipoBusqueda);

		// Se validan los campos de búsqueda de la sección de Remitente (s) y/o Destinatario (s).
		String msjSinFiltros = bsdReportesOf.validarFiltrosBusqueda(tipoBusqueda, filtrosReportesOf);

		if(msjSinFiltros != "") {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjSinFiltros, ""));
			return;
		}

		// 1. La búsqueda realizada viene desde la sección de Remitente (s)
		if(tipoBusqueda.equals("Remitente")) {
			filtrosReportesOf.setPersonasEncontradas( bsdReportesOf.recuperarPersonas( filtrosReportesOf.getNombreRemitente(),
																					   filtrosReportesOf.getApellidoRemitente(),
																					   filtrosReportesOf.getIdTipoAreaRemitente(),
																					   filtrosReportesOf.getIdAreaRemitente() ) );
		}

		// 1. La búsqueda realizada viene desde la sección de Destinatario (s)
		if(tipoBusqueda.equals("Destinatario")) {
			for(DTOCAreaEntity dtoAreaD : filtrosReportesOf.getAreasDestinatario()) {
				if(dtoAreaD.getIdArea().equals(filtrosReportesOf.getIdAreaDestinatario())) {
					filtrosReportesOf.setIdTipoAreaDestinatario(dtoAreaD.getTipoArea());
					filtrosReportesOf.setPersonasEncontradas( bsdReportesOf.recuperarPersonas( filtrosReportesOf.getNombreDestinatario(),
																							   filtrosReportesOf.getApellidoDestinatario(),
																							   filtrosReportesOf.getIdTipoAreaDestinatario(),
																							   filtrosReportesOf.getIdAreaDestinatario() ) );
					break;
				}
			}
		}

		if(filtrosReportesOf.getPersonasEncontradas().size() > 0) {
			RequestContext.getCurrentInstance().execute("PF('dialogPersonasEncontradas').show()");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
		}

	}

	/**
	 * Método encargado de agregar una o varias personas a la lista de personas encontradas con base
	 * en la o las personas seleccionadas por el usuario.
	 * @return String: Resultado de la validación
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void agregarPersonas() {
		// 1. Se  valida la correcta selección de personas una vez concluida la búsqueda de Remitente (s) o Destinatario (s).
		String mjsErrorValidacion = bsdReportesOf.validarPersonasSeleccionadas(filtrosReportesOf);
		
		// 2. Se agrega una lista de personas y se limpian sus respectivos campos de búsqueda
		if(mjsErrorValidacion != "") {
			if(filtrosReportesOf.getTipoBusqueda().equals("Remitente")) {
				filtrosReportesOf.setNombreRemitente("");
				filtrosReportesOf.setApellidoRemitente("");
				filtrosReportesOf.setIdTipoAreaRemitente(new Integer(0));
				filtrosReportesOf.setIdEntidadRemitente(null);
				filtrosReportesOf.setIdAreaRemitente(new Integer(0));
				filtrosReportesOf.setAreasRemitente(new ArrayList<DTOCAreaEntity>());
				filtrosReportesOf.setEntidadesRemitente(new ArrayList<DTOEstadosEntity>());
			}
			if(filtrosReportesOf.getTipoBusqueda().equals("Destinatario")) {
				filtrosReportesOf.setNombreDestinatario("");
				filtrosReportesOf.setApellidoDestinatario("");
				filtrosReportesOf.setIdTipoAreaDestinatario(new Integer(0));
				filtrosReportesOf.setIdEntidadDestinatario(null);
				filtrosReportesOf.setIdAreaDestinatario(new Integer(0));
//				filtrosReportesOf.setAreasDestinatario(new ArrayList<DTOCAreaEntity>());
				filtrosReportesOf.setEntidadesDestinatario(new ArrayList<DTOEstadosEntity>());
			}

			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mjsErrorValidacion, ""));
	        return;
		} else {
			bsdReportesOf.agregarPersonas(filtrosReportesOf);
			RequestContext.getCurrentInstance().execute("PF('dialogPersonasEncontradas').hide()");
		}
	}

	/**
	 * Método encargado de limpiar la lista de personas encontradas (remitentes o destinatarios) y
	 * la lista de personas seleccionadas por el usuario en la vista de Reportes de Oficialía.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void limpiarCampos() {
		filtrosReportesOf.setPersonasEncontradas(new ArrayList<DTOEstructuraAreasEntity>());
		filtrosReportesOf.setPersonasSeleccionadas(new ArrayList<DTOEstructuraAreasEntity>());
	}

	/**
	 * Método encargado de eliminar una persona (remitente o destinatario) de la lista de
	 * la lista de personas seleccionadas por el usuario en la vista de Reportes de Oficialía.
	 * @param String tipoBusqueda, DTOEstructuraAreasEntity persona
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void eliminarPersona(String tipoBusqueda, DTOEstructuraAreasEntity persona) {
		if(tipoBusqueda.equals("Remitente")) {
			filtrosReportesOf.getRemitentes().remove(persona);
		}
		if(tipoBusqueda.equals("Destinatario")) {
			filtrosReportesOf.getDestinatarios().remove(persona);
		}
	}

	/**
	 * Método encargado de proporcionar una correcta funcionalidad de las casillas de verificación o
	 * filtros para la creación del reporte.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public void actualizarChecks() {
		if(filtrosReportesOf.getFiltrosSeleccionados().length != 0) {
			// 1. Se ha seleccionado la casilla "Todos" y automáticamente se seleccionan todos los filtros
			if(filtrosReportesOf.getFiltrosSeleccionados()[0].equals("Todos") &&
			   !filtrosReportesOf.getTodosFiltros()) {
				String filtrosSeleccionados[] = {"Todos", "Tipo de área", "Folios", "Área", "Remitente", "Destinatario",
												 "Copia de conocimiento", "Sección", "Categoría"};
				filtrosReportesOf.setFiltrosSeleccionados(filtrosSeleccionados);
				filtrosReportesOf.setTodosFiltros(true);
				return;
			}

			// 2. La casilla de "Todos" se encontraba seleccionada pero el usuario desmarcó algún otro filtro
			if(filtrosReportesOf.getFiltrosSeleccionados()[0].equals("Todos") &&
			   filtrosReportesOf.getFiltrosSeleccionados().length == filtrosReportesOf.getCatalogoFiltrosReporte().size()-1) {
				String[] filtrosSeleccionados = new String[filtrosReportesOf.getFiltrosSeleccionados().length-1];
				for(int pos = 1; pos < filtrosReportesOf.getFiltrosSeleccionados().length; pos++) {
					filtrosSeleccionados[pos-1] = filtrosReportesOf.getFiltrosSeleccionados()[pos];
				}
				filtrosReportesOf.setFiltrosSeleccionados(filtrosSeleccionados);
				filtrosReportesOf.setTodosFiltros(false);
				return;
			}

			// 3. La casilla de "Todos" no estaba seleccionada pero el usuario ya ha seleccionado todos los filtros
			if(!filtrosReportesOf.getFiltrosSeleccionados()[0].equals("Todos") &&
				filtrosReportesOf.getFiltrosSeleccionados().length == filtrosReportesOf.getCatalogoFiltrosReporte().size()-1) {
				String[] filtrosSeleccionados = null;
				if(filtrosReportesOf.getTodosFiltros()) {
					filtrosReportesOf.setFiltrosSeleccionados(filtrosSeleccionados);
					filtrosReportesOf.setTodosFiltros(false);
					return;
				} else {
					filtrosSeleccionados = new String[filtrosReportesOf.getFiltrosSeleccionados().length+1];
					filtrosSeleccionados[0] = "Todos";
					for(int pos = 0; pos < filtrosReportesOf.getFiltrosSeleccionados().length; pos++) {
						filtrosSeleccionados[pos+1] = filtrosReportesOf.getFiltrosSeleccionados()[pos];
					}
					filtrosReportesOf.setFiltrosSeleccionados(filtrosSeleccionados);
					filtrosReportesOf.setTodosFiltros(true);
					return;
				}
			}
		}
	}

	/**
	 * Método encargado de generar el reporte para la vista de Reportes de Oficialía (versión de prueba).
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 17/03/2018
	 */
	public String recuperarReporte() {
		filtrosReportesOf.setReporteOficialia( bsdReportesOf.recuperarReporte(usuarioLogueado.getRol(), filtrosReportesOf) );

		if(filtrosReportesOf.getReporteOficialia() == null) {
			return "";
		} else {
			filtrosReportesOf.setFiltrosReporte( bsdReportesOf.generarFiltrosReporte(filtrosReportesOf) );
			bsdReportesOf.generarListasPersonas(filtrosReportesOf.getReporteOficialia());
			return "btnRecuperarReporte";
		}
	}

	/**
	 * Método que transforma generar el Reporte de Oficialía de acuerdo a los filtros introducidos
	 * y/o seleccionados en la vista de Reportes de Oficialía.
	 * @return StreamedContent: flujo de bytes del reporte
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public StreamedContent generarReporte() {
		return bsdReportesOf.generarReporte(filtrosReportesOf);
	}

	/**
	 * Método que controla el despliegue de los filtros de búsqueda de remitentes y remitentes externos en la vista de Reportes de Oficialía.
	 * @return boolean
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public boolean esRemitenteExterno() {
		if(filtrosReportesOf.getTipoRemitente().equals("Interno")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método que recupera una lista de remitentes externos encontrados por la búsqueda
	 * del usuario en la vista de Reporte de Oficialía.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void recuperarRemitentesExt() {
		// 1. Se validan los campos de búsqueda de la sección de Remitente (s) y/o Destinatario (s).
		String msjSinFiltros = bsdReportesOf.validarFiltrosRemitentesExt(filtrosReportesOf);

		if(!msjSinFiltros.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjSinFiltros, ""));
			return;
		} else {	// 2. Se realiza la consulta de remitentes externos en BD
			filtrosReportesOf.setRemitentesExtEncontrados(bsdReportesOf.recuperarRemitentesExt(filtrosReportesOf));		
		}

		if(filtrosReportesOf.getRemitentesExtEncontrados().size() > 0) {
			RequestContext.getCurrentInstance().execute("PF('dialogRemitentesExtEncontrados').show()");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", ""));
	        return;
		}
	}

	/**
	 * Método encargado de agregar uno o varios remitentes externos en la lista de remitentes externos agregados.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void agregarRemitenteExt() {
		// 1. Él o los remitentes externos seleccionados por el usuario son validados
		String msjValidacion = bsdReportesOf.validarRemitenteExtSeleccionado(filtrosReportesOf);

		if(!msjValidacion.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msjValidacion, ""));
	        return;
		}

		bsdReportesOf.agregarRemitentesExt(filtrosReportesOf);
		RequestContext.getCurrentInstance().execute("PF('dialogRemitentesExtEncontrados').hide()");
	}

	/**
	 * Método encargado de eliminar un remitente externo de la lista de remitentes externos agregados.
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void eliminarRemitenteExt(DTORemitentesExternosOfEntity remitenteExt) {
		this.filtrosReportesOf.getRemitentesExtAgregados().remove(remitenteExt);
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public DTOReportesOficialiaHelper getFiltrosReportesOf() {
		return filtrosReportesOf;
	}

	public void setFiltrosReportesOf(DTOReportesOficialiaHelper filtrosReportesOf) {
		this.filtrosReportesOf = filtrosReportesOf;
	}

	public String getFechaFormat() {
		return fechaFormat;
	}

}
