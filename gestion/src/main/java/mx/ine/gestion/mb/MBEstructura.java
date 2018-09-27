package mx.ine.gestion.mb;

/**
 * @(#)MBEstructura.java 12/07/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.as.inter.ASEstructuraInterface;
import mx.ine.gestion.bsd.inter.BSDEstructuraInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHEstructuraInterface;

import org.primefaces.context.RequestContext;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
//import mx.ine.comunicaciones.dto.DTOEstados;

/**
 * Clase de la capa de MB para el módulo personal.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 12/07/2017
 */
public class MBEstructura implements Serializable {

	///////////////////////////////////////////////
	//-----------------ATRIBUTOS-----------------//
	///////////////////////////////////////////////

	/**
	 * Serial generado
	 */
	private static final long serialVersionUID = 1814924479573281245L;

    /*
     * Árbol con la estructura
     */
    private TreeNode arbolEstructura;

    /*
     * DTO con la información del detalle para elementos de la estructura
     */
    private DTOEstructuraAreasEntity detalleEstructura;

    /*
     * DTO con la información del detalle para elementos de oficialía
     */
    private DTOOficialiaEntity detalleOficialia;
    
    /*
     * String con el tipo de área seleccionada
     */
    private Integer tipoAreaSeleccionado;
    
    /*
     * String con el área seleccionada
     */
    private String areaSeleccionada;
    
    /*
     * String con la entidad seleccionada
     */
    private String entidadSeleccionada;
    
    /*
     * String con el área seleccionada para oficialía para filtrar
     */
    private String areaSeleccionadaOficialia;
    
    /*
     * String con la cuenta del titular del organigrama
     */
    private String cuentaTitular;
    
    /*
     * String con las siglas del área seleccionada
     */
    private String siglas;
    
    /*
     * Lista con los tipos de áreas
     */
    private List<DTOCTipoAreaEntity> listaTiposAreas;
    
    /*
     * Lista con las áreas
     */
    private List<DTOCAreaEntity> listaAreas;
    
    /*
     * Lista con las áreas
     */
    private List<DTOCAreaEntity> listaAreasOficialia;
    
    /*
     * Lista de entidades
     */
    private List<DTOEstadosEntity> listaEstados;
    
	/*
	 * Lista con las personas de la búsqueda de estructura.
	 */
	private List<DTOEstructuraAreasEntity> listaBusquedaEstructura;
	
	/*
	 * Lista con los empleados de la búsqueda para agregar subnivel.
	 */
	private List<DTOEstructuraAreasEntity> listaBusquedaSubnivel;
	
	/*
	 * Persona seleccionada como titular.
	 */
    private DTOEstructuraAreasEntity titularSeleccionado;
    
    /*
     * Nombre del nivel agregado para el titular
     */
    private String nombreNivelAreaTitular;
    
    /*
     * Boolean para determinar si se agrega o no nombre al subnivel
     */
    private boolean mostrarSubarea;
    
    /*
     * Nombre del nivel agregado para el subnivel
     */
    private String nombreNivelAreaSubnivel;
    
	/*
	 * Persona padre para agregar al subnivel
	 */
    private DTOJerarquiaPersonasEntity padreSubnivelSeleccionado;
	
    /*
	 * Persona seleccionada para agregar subnivel.
	 */
    private DTOEstructuraAreasEntity subnivelSeleccionado;

	/*
	 * Persona seleccionada como objeto TreeNode
	 */
    private TreeNode nodoTitularSeleccionado;
    
    /*
     * String para el nombre en la búsqueda de titular
     */
    private String nombreTitular;
    
    /*
     * String para el apellido en la búsqueda de titular
     */
    private String apellidosTitular;
    
    /*
     * String para el nombre en la búsqueda de subnivel
     */
    private String nombreSubnivel;
    
    /*
     * String para el apellido en la búsqueda de subnivel
     */
    private String apellidosSubnivel;
    
    /*
     * Lisra con la oficialía
     */
    private List<DTOOficialiaEntity> listaOficialia;
    
    /*
     * Lista para la búsqueda de oficialía
     */
    private List<DTOOficialiaEntity> listaBusquedaOficialia;
    
    /*
     * Ofícialía seleccionada
     */
    private List<DTOOficialiaEntity> oficialiasSeleccionadas;
    
    /*
     * Nombre de la persona buscada para agregar a oficialía
     */
    private String nombreOficialia;

    /*
     * Apellidos de la persona buscada para agregar a oficialía 
     */
    private String apellidosOficialia;

    /*
     * Área de la persona buscada para agregar a oficialía
     */
    private String areaOficialia;
  
    /*
     * Boolean que indica si el usuario logeado es administrador de área.
     */
    private boolean conRolAdminArea;
    
    /*
     * Boolean que indica si se debe de mostrar el formulario en caso de que ya se hayan capturado las áreas.
     */
    private boolean mostrarFormulario; 

	/**
	 * DTO para crear, guardar y eliminar un empleado de gestión
	 */
	//private DTOEmpleadoGestionEntity empleadoGestion;

	@Autowired
	@Qualifier("bsdEstructura")
	private transient BSDEstructuraInterface bsdPersonalInterface;

	@Autowired
	@Qualifier("vhEstructura")
	private transient VHEstructuraInterface vhEstructuraInterface;
	
	@Autowired
    @Qualifier("asEstructura")
    private transient ASEstructuraInterface asEstructura;

	///////////////////////////////////////////////
	//-----------------MÉTODOS-------------------//
	///////////////////////////////////////////////

	/**
	 * Método para inicializar la lista para la consulta y el empleado de gestion
	 * 
	 * @param String tipo: para determinar si es consulta o estructura
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/07/2017
	 */
	public void inicializar(String tipo) {

		inicializarAtributos();
		limpiarDatosGenerales();
		limpiarBuscarTitular();
		limpiarBuscarOficialia();
		limpiarBuscarSubnivel();
		
		
		listaEstados = new ArrayList<DTOEstadosEntity>();
		
		try { 
			listaTiposAreas = new ArrayList<DTOCTipoAreaEntity>(bsdPersonalInterface.consultarTiposAreas());
		} catch (Exception e) {
			vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
					"Error al consultar entidades con organigrama en consulta.");
		}
		if (tipo.equals("consulta")) {
			try {
				listaEstados = new ArrayList<DTOEstadosEntity>(bsdPersonalInterface.consultarEstadosConOrganigrama());//vhEstructuraInterface.hacerMatchEntidades(listaEstadosCompleta, listaInt);
			} catch(Exception e) {				
				vhEstructuraInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

				vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
						"Error al consultar entidades con organigrama en consulta.");
			}
		} else if (tipo.equals("captura")) {
			try {
				listaEstados = new ArrayList<DTOEstadosEntity>(bsdPersonalInterface.consultarEstadosSinOrganigrama());
			} catch(Exception e) {

				vhEstructuraInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

				vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
						"Error al consultar entidades sin organigrama en captura.");
			}			
		}
		mostrarFormulario = true;
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal());

		tipoAreaSeleccionado = null;

		entidadSeleccionada = "";
		
		if(!usuarioLogueado.getRol().equals("GESTION4.ADMIN_AREA.OC")) {
			conRolAdminArea = false;
			if(tipo.equals("captura")) {
				try {
					listaAreas = null;//bsdPersonalInterface.consultarAreasSinOrganigrama(1, 0);
				} catch (Exception e) {
					mostrarFormulario = false;
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
							"Error al consultar áreas sin organigrama en captura.");
						
					listaAreas = null;
				}
			} else if(tipo.equals("consulta")) {
				try {
					listaAreas = bsdPersonalInterface.consultarAreasConOrganigrama(1, 0);
				} catch (Exception e) {
					mostrarFormulario = false;
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
							"Error al consultar áreas sin organigrama en consulta.");
					listaAreas = null;
				}		
			}
			try {
				listaAreasOficialia = bsdPersonalInterface.consultarAreas(1, 0);
			} catch (Exception e) {
				listaAreasOficialia = null;
				
				vhEstructuraInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

				vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
						"Error al consultar áreas para oficialía.");
				
			}
		} else {
			conRolAdminArea = true;
			if(tipo.equals("consulta")) {
				try {
					if(usuarioLogueado.getIdArea() != null && usuarioLogueado.getTipoArea() != null) {
						listaAreas = bsdPersonalInterface.consultarAreasConOrganigrama(usuarioLogueado.getTipoArea(), -1);
						listaAreas = vhEstructuraInterface.buscarAreaPorIdEnLista(listaAreas, usuarioLogueado.getIdArea());
						
					}	
				} catch(Exception e) {
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
							"Error al consultar áreas con organigrama en consulta.");
					
				}
			} else if(tipo.equals("captura")) {
				try {
					listaAreas = bsdPersonalInterface.consultarAreasSinOrganigrama(usuarioLogueado.getTipoArea(), -1);
					listaAreas = vhEstructuraInterface.buscarAreaPorIdEnLista(listaAreas, usuarioLogueado.getIdArea());
				} catch (Exception e) {
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));

					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
							"Error al consultar áreas sin organigrama en captura.");

				}
			}
			if (listaAreas == null) {
				mostrarFormulario = false;
				if (tipo.equals("captura")) {
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_organigrama_creado"));
					
				} else if(tipo.equals("consulta")) {
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_organigrama_no_creado"));

				}
			} else {
				areaSeleccionada = usuarioLogueado.getIdArea().toString();
				entidadSeleccionada = listaAreas.get(0).getIdEntidad().toString();
				tipoAreaSeleccionado = usuarioLogueado.getTipoArea();
				mostrarSiglas();
				if(tipo.equals("consulta")) {
					buscarEstructuraPorArea();
				}
				try {
					listaAreasOficialia = bsdPersonalInterface.consultarAreas(usuarioLogueado.getTipoArea().intValue(), 
							listaAreas.get(0).getIdEntidad().intValue());
				} catch (Exception e) {
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
					
					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
							"Error al consultar áreas para oficialía.");
					
				}
			}
		}
	}
	
	/**
	 * Método que inicializa los atributos principales del mb
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	void inicializarAtributos() {
		listaBusquedaEstructura = null;
		listaBusquedaSubnivel = null;
		listaBusquedaOficialia = null;
		arbolEstructura = vhEstructuraInterface.inicializarArbol();
		listaOficialia = new ArrayList<DTOOficialiaEntity>();
		titularSeleccionado = null;
		oficialiasSeleccionadas = null;
		subnivelSeleccionado = null;
		nodoTitularSeleccionado = null;
		detalleEstructura = null;
		cuentaTitular = "";
	}

	/**
	 * Método que inicializa los atributos de la parte de datos generales
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	void limpiarDatosGenerales() {
		areaSeleccionada = "";
		siglas = "";
	}
	
	/**
	 * Método que inicializa los atributos de buscar titular
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	void limpiarBuscarTitular() {
		nombreTitular = "";
		apellidosTitular = "";
	}
	
	/**
	 * Método que inicializa los atributos de buscar oficialia
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	void limpiarBuscarOficialia(){
		nombreOficialia = "";
		apellidosOficialia = "";
		areaSeleccionadaOficialia = "";
		mostrarSubarea = false;
	}

	/**
	 * Método que inicializa los atributos de subnivel
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	void limpiarBuscarSubnivel(){
		nombreSubnivel = "";
		apellidosSubnivel = "";
	}

	//TODO
	public String obtenerNombreArea() {
		return vhEstructuraInterface.obtenerDescripcionDeListaAreas(listaAreas, Integer.valueOf(areaSeleccionada));
	}

	//TODO
	public String obtenerNombreEntidad() {
		return vhEstructuraInterface.obtenerDescripcionDeEntidades(listaEstados, Integer.valueOf(entidadSeleccionada));
	}

	/**
	 * Método para buscar personas para agregar como titular dentro de LDAP
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/08/2017
	 */
	public void buscarTitular() {

		int entidad = 0;
		
		if(tipoAreaSeleccionado.intValue() == 2) {
			entidad = Integer.parseInt(entidadSeleccionada);
		}
		if(tipoAreaSeleccionado.intValue() != 0 && !areaSeleccionada.equals("")) {
			try {
				listaBusquedaEstructura = bsdPersonalInterface.buscarEstructuraLDAPPorNombreApellidoArea(
											nombreTitular, apellidosTitular,
											vhEstructuraInterface.obtenerDescripcionDeListaAreas(listaAreas, Integer.valueOf(areaSeleccionada)),
											entidad);
				titularSeleccionado = null;
				if(listaBusquedaEstructura.size() == 0 ) {
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_info"),
							Utilidades.mensajeProperties("titulo_growl_info"),
							Utilidades.mensajeProperties("mensaje_info_no_resultados"));
					
				} else {
					RequestContext.getCurrentInstance().execute("PF('dialog_buscar_titular').show()");
				}
			} catch (Exception e) {
				vhEstructuraInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_titular"));

				vhEstructuraInterface.enviarError(e, this.getClass().getName(), "inicializar()", 
						"Error al buscar titular");
			}
		}
	}

	/**
	 * Método para buscar personas para agregar como oficialía dentro de LDAP
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/09/2017
	 */
	public void buscarOficialia() {
		if(validarOficialia()) {
			int entidad = 0;
			if(tipoAreaSeleccionado.intValue() == 2) {
				entidad = Integer.parseInt(entidadSeleccionada);
			} 
			try {
				String area = "";
				if(!areaSeleccionadaOficialia.equals("")) {
					area = vhEstructuraInterface.obtenerDescripcionDeListaAreas(
							listaAreasOficialia, Integer.valueOf(areaSeleccionadaOficialia));
				}
				listaBusquedaOficialia = bsdPersonalInterface.buscarOficialiaLDAPPorNombreApellidoArea(
						nombreOficialia, apellidosOficialia, area, entidad);
				oficialiasSeleccionadas = null;
				if (listaBusquedaOficialia.size() == 0) {
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_info"),
							Utilidades.mensajeProperties("titulo_growl_info"),
							Utilidades.mensajeProperties("mensaje_info_no_resultados"));
				} else {
					RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').show()");
				}
			} catch (Exception e) {
				vhEstructuraInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_oficialias"));
				
				vhEstructuraInterface.enviarError(e, this.getClass().getName(), "buscarOficialia()", 
						"Error al buscar oficialías.");
			}
		}
	}

	/**
	 * Método para mostrar las áreas por tipo de área. Si es captura se traen las áreas que no cuentan con organigrama,
	 * si es consulta se traen las áreas que ya tienen organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/09/2017
	 */
	public void mostrarAreas(String tipo) {
		arbolEstructura = vhEstructuraInterface.inicializarArbol();
		listaOficialia = new ArrayList<DTOOficialiaEntity>();
		if(tipoAreaSeleccionado.intValue() == 0){
			areaSeleccionada = "";
			siglas = "";
			listaAreas = new ArrayList<DTOCAreaEntity>();
			inicializarAtributos();
		} else {
			if(tipoAreaSeleccionado.intValue() == 1) {
				areaSeleccionada = "";
	 			int entidad = 0;
				if(tipo.equals("captura")) {
					try {
						listaAreas = bsdPersonalInterface.consultarAreasSinOrganigrama(tipoAreaSeleccionado, entidad);				
						areaSeleccionada = "";
					} catch(Exception e) {
						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_advertencia"),
								Utilidades.mensajeProperties("titulo_growl_advertencia"),
								Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
						
						vhEstructuraInterface.enviarError(e, this.getClass().getName(), "mostrarAreas()", "Error al mostrar áreas en captura.");
						listaAreas = null;
					} finally {
						mostrarSiglas();
					}
				} else if(tipo.equals("consulta")) {
					try {
						listaAreas = bsdPersonalInterface.consultarAreasConOrganigrama(tipoAreaSeleccionado, entidad);				
						areaSeleccionada = "";
						mostrarSiglas();			
					} catch(Exception e) {
						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_advertencia"),
								Utilidades.mensajeProperties("titulo_growl_advertencia"),
								Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
						
						vhEstructuraInterface.enviarError(e, this.getClass().getName(), "mostrarAreas()", "Error al mostrar áreas con consulta.");
						listaAreas = null;
					} finally {
						mostrarSiglas();
					}
				}
				try {
					listaAreasOficialia = bsdPersonalInterface.consultarAreas(tipoAreaSeleccionado, entidad);
				} catch (Exception e) {
					listaAreasOficialia = null;
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
					
					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "mostrarAreas()", "Error al mostrar áreas para oficialía.");
				}
			} else if(tipoAreaSeleccionado.intValue() == 2) {
				listaAreas = null;
				listaAreasOficialia = null;
				siglas = "";
			} else if(tipoAreaSeleccionado.intValue() == 3) {
				listaAreas = null;
				listaAreasOficialia = null;
				siglas = "";
			}
		}
	}
	
	/**
	 * Método para mostrar las áreas por tipo de área. Si es captura se traen las áreas que no cuentan con organigrama,
	 * si es consulta se traen las áreas que ya tienen organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/09/2017
	 */
	public void mostrarAreasEntidad(String tipo) {
		if(tipoAreaSeleccionado.intValue() == 0){
			areaSeleccionada = "";
			inicializarAtributos();
		} else {
			if(tipoAreaSeleccionado.intValue() == 2) {
				int entidad = 0;
				entidad = Integer.parseInt(entidadSeleccionada);
				if(tipo.equals("captura")) {
					try {
						listaAreas = bsdPersonalInterface.consultarAreasSinOrganigrama(tipoAreaSeleccionado, entidad);				
						areaSeleccionada = "";
					} catch(Exception e) {

						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_advertencia"),
								Utilidades.mensajeProperties("titulo_growl_advertencia"),
								Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
						
						vhEstructuraInterface.enviarError(e, this.getClass().getName(), "mostrarAreasEntidad()", "Error al consultar áreas sin organigrama.");
						listaAreas = null;
					} finally {
						mostrarSiglas();
					}
				} else if(tipo.equals("consulta")) {
					try {
						listaAreas = bsdPersonalInterface.consultarAreasConOrganigrama(tipoAreaSeleccionado, entidad);				
						areaSeleccionada = "";
						mostrarSiglas();			
					} catch(Exception e) {
						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_advertencia"),
								Utilidades.mensajeProperties("titulo_growl_advertencia"),
								Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
						
						vhEstructuraInterface.enviarError(e, this.getClass().getName(), "mostrarAreasEntidad()", "Error al consultar áreas con organigrama.");
						listaAreas = null;
					} finally {
						mostrarSiglas();
					}
				}
				try {
					listaAreasOficialia = bsdPersonalInterface.consultarAreas(tipoAreaSeleccionado, entidad);
				} catch (Exception e) {
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_areas_organigrama"));
					
					listaAreasOficialia = null;
					vhEstructuraInterface.enviarError(e, this.getClass().getName(), "mostrarAreasEntidad()", "Error al consultar áreas para oficialía.");
				}
			}
		}
	}

	/**
	 * Método para mostrar dialog de confirmación para agregar titular.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/09/2017
	 */
	public boolean mostrarConfirmacionAgregarTitular() {
		if(arbolEstructura == null) {
			return false;
		}
		if(arbolEstructura.getChildCount()>0) {
			return true;
		}
		return false;
	}

	/**
	 * Método para mostrar las siglas del área dependiendo del área.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/09/2017
	 */
	public void mostrarSiglas() {
		inicializarAtributos();
		if(areaSeleccionada.equals("")) {
			siglas = "";
		} else {
			siglas = vhEstructuraInterface.obtenerSiglasDeListaAreas(listaAreas, Integer.valueOf(areaSeleccionada));
		}
	}
	
	/**
	 * Método que consulta en la bd el organigrama por el área seleccionada.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public void buscarEstructuraPorArea() {
		if(areaSeleccionada.equals("")) {
			siglas = "";
			arbolEstructura = vhEstructuraInterface.inicializarArbol();
			listaOficialia = new ArrayList<DTOOficialiaEntity>();
		} else {
			siglas = vhEstructuraInterface.obtenerSiglasDeListaAreas(listaAreas, Integer.valueOf(areaSeleccionada));
			listaOficialia = bsdPersonalInterface.consultarOficialiasPorArea(
					tipoAreaSeleccionado,Integer.valueOf(areaSeleccionada));
			arbolEstructura = bsdPersonalInterface.consultarArbolEstructura(
					tipoAreaSeleccionado, Integer.valueOf(areaSeleccionada));
			TreeNode titularNodo = vhEstructuraInterface.buscarNodoTitular(arbolEstructura);
			DTOJerarquiaPersonasEntity titularRelacion = (DTOJerarquiaPersonasEntity) titularNodo.getData();
			cuentaTitular = titularRelacion.getCuentaPersona();
		}
	}

	/**
	 * Método para validar los campos en la búsqueda de oficialía.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/09/2017
	 */
	public boolean validarOficialia() {
		boolean validado = true;
		if (nombreOficialia == null) nombreOficialia = "";
		if (apellidosOficialia == null) apellidosOficialia = "";
		if (areaSeleccionadaOficialia == null) areaSeleccionadaOficialia = "";
		if(nombreOficialia.trim().length() == 0 && apellidosOficialia.trim().length() == 0 && areaSeleccionadaOficialia.equals("")) {
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_info_requeridos_apellido_nombre"));

			RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').hide()");
			validado = false;
		}
		return validado;
	}

	/**
	 * Método para validar agregar titular.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public boolean validarAgregarTitular() {
		boolean validado = true;
		if(titularSeleccionado == null) {
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_info_requeridos_apellido_nombre"));
			
			vhEstructuraInterface.mostrarMensajeRequerido("datatable_buscar_titular", Utilidades.mensajeProperties("mensaje_requerido_titular"), "");
			RequestContext.getCurrentInstance().execute("PF('dialog_buscar_titular').show()");
			validado = false;
		} else if(titularSeleccionado.getCuentaLDAP().equals(cuentaTitular)) {
			
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_info"),
					Utilidades.mensajeProperties("titulo_growl_info"),
					Utilidades.mensajeProperties("mensaje_info_titular_repetido"));
			
			RequestContext.getCurrentInstance().execute("PF('dialog_buscar_titular').show()");
			validado = false;
		}
		
		return validado;
	}
	
	/**
	 * Método para validar agregar oficialía.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public boolean validarAgregarOficialia() {
		boolean validado = true;		
		if(oficialiasSeleccionadas == null) {
			RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').show()");
			
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_info"),
					Utilidades.mensajeProperties("titulo_growl_info"),
					Utilidades.mensajeProperties("mensaje_requerido_oficialia"));
			validado = false;
		} else {
			if(oficialiasSeleccionadas.size() > 0) {
				for (DTOOficialiaEntity oficialia : oficialiasSeleccionadas) {
					if(vhEstructuraInterface.existeOficialiaEnListaPorCuenta(listaOficialia, oficialia.getCuentaLDAP())) {
						RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').show()");
						
						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_info"),
								Utilidades.mensajeProperties("titulo_growl_info"),
								oficialia.getNombre() + " " + oficialia.getApellidos()
								+ " ya fue registrado(a) en esta Oficialía.");
						
						validado = false;
						return validado;
					}
				}
			} else {
				RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').show()");
				
				vhEstructuraInterface.mostrarMensajeRequerido("datatable_buscar_oficialia", Utilidades.mensajeProperties("mensaje_requerido_oficialia"), "");
				validado = false;
			}
		}
		return validado;
	}
	
	/**
	 * Método para agregar el titular en el organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/07/2017
	 */
	public void agregarTitular() {
		if(validarAgregarTitular()) {
			arbolEstructura = vhEstructuraInterface.inicializarArbol();			
			titularSeleccionado.setNombreNivelArea(vhEstructuraInterface.obtenerDescripcionDeListaAreas(listaAreas, Integer.valueOf(areaSeleccionada)));
			titularSeleccionado.setIdArea(Integer.valueOf(areaSeleccionada));
			titularSeleccionado.setTipoArea(tipoAreaSeleccionado);
			DTOJerarquiaPersonasEntity relacion = 
					new DTOJerarquiaPersonasEntity(
							null,
							Integer.valueOf(areaSeleccionada), 
							tipoAreaSeleccionado, 
							Integer.valueOf(1), 
							Integer.valueOf(0), 
							Integer.valueOf(0), 
							titularSeleccionado.getCuentaLDAP(),
							"");
			DTOEstructuraAreasEntity cuentaExistente = bsdPersonalInterface.consultarPersonaPorCuenta(titularSeleccionado.getCuentaLDAP());
			if (cuentaExistente!= null) {
				titularSeleccionado.setIdPersona(cuentaExistente.getIdPersona());
				titularSeleccionado.setEstatus(cuentaExistente.getEstatus());
				relacion.setIdPersona(cuentaExistente.getIdPersona());
			}
			RequestContext.getCurrentInstance().execute("PF('dialog_buscar_titular').hide()");
			relacion.setDtoEstructuraAreas(titularSeleccionado);
			vhEstructuraInterface.agregarEnNodo(arbolEstructura, relacion);	
			cuentaTitular = titularSeleccionado.getCuentaLDAP();
			titularSeleccionado = null;
			limpiarBuscarTitular();
		}
	}
	
	/**
	 * Método para eliminar un elemento de ofícialia
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/07/2017
	 */
	public void eliminarOficialia(DTOOficialiaEntity oficialia) {
		listaOficialia.remove(oficialia);
	}

	/**
	 * Método para eliminar un elemento del subnivel
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @throws Exception 
	 * @since 12/07/2017
	 */
	public void eliminarSubnivel(DTOJerarquiaPersonasEntity relacion) {
		TreeNode nodoEliminar = vhEstructuraInterface.buscarNodoPorCuentas(arbolEstructura, relacion.getCuentaPersona(), relacion.getCuentaPadre());
		if(nodoEliminar != null) {
			vhEstructuraInterface.eliminarNodoArbol(arbolEstructura, nodoEliminar);
		}
		if(arbolEstructura.getChildCount() == 0) {
			cuentaTitular = "";
		}
	}
	
	/**
	 * Método para ver el detalle de alguna persona dentro de la estructura.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */
	public void verDetalleEstructura(DTOJerarquiaPersonasEntity personaDetalle) {
		detalleEstructura = personaDetalle.getDtoEstructuraAreas();
		detalleEstructura.setNombreArea(vhEstructuraInterface.obtenerDescripcionDeListaAreas(
				listaAreas,  Integer.valueOf(areaSeleccionada)));
	}
	
	/**
	 * Método para ver el detalle de alguna persona dentro de la oficialía.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */
	public void verDetalleOficialia(DTOOficialiaEntity personaDetalle) {
		detalleOficialia = personaDetalle;
	}
	
	/**
	 * Método para validar si se abre la ventana de subnivel o no
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/07/2017
	 */
	public void abrirVentanaSubnivel(DTOJerarquiaPersonasEntity padre) {
		limpiarBuscarSubnivel();
		listaBusquedaSubnivel = null;
		nombreNivelAreaSubnivel = null;
		mostrarSubarea = false;
		padreSubnivelSeleccionado = padre;
		subnivelSeleccionado = null;
		RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').show()");					
	}
	
	/**
	 * Método para buscar una persona en LDAP para agregar en el organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 28/08/2017
	 */
	public void buscarSubnivel() {

		if(validarSubnivel()) {
			int entidad = 0;
			if(tipoAreaSeleccionado.intValue() == 2) {
				entidad = Integer.parseInt(entidadSeleccionada);
			} 
			try {
				listaBusquedaSubnivel = bsdPersonalInterface.buscarEstructuraLDAPPorNombreApellidoArea(
						nombreSubnivel, apellidosSubnivel,
						vhEstructuraInterface.obtenerDescripcionDeListaAreas(listaAreas, Integer.valueOf(areaSeleccionada)),
						entidad);
				subnivelSeleccionado = null;
				if (listaBusquedaSubnivel.size() == 0) {
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_info"),
							Utilidades.mensajeProperties("titulo_growl_info"),
							Utilidades.mensajeProperties("mensaje_info_no_resultados"));
				}
			} catch (Exception e) {
				vhEstructuraInterface.enviarError(e, "MBEstructura", "buscarSubnivel()", "Error al buscar subnivel.");
			}
		} else {
			listaBusquedaSubnivel = null;
		}
	}

	/**
	 * Método para validar la búsqueda del subnivel.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/09/2017
	 */
	public boolean validarSubnivel() {
		boolean validado = true;
		return validado;
	}
	
	/**
	 * Método para validar si se agrega una persona al organigrama
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/09/2017
	 */
	public boolean validarAgregarSubnivel() {
		boolean validado = true;
		if(subnivelSeleccionado == null) {
			RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').show()");
			vhEstructuraInterface.mostrarMensajeRequerido("datatable_buscar_subnivel", Utilidades.mensajeProperties("mensaje_requerido_subnivel"), "");
			validado = false;
		} else if(mostrarSubarea && nombreNivelAreaSubnivel != null && nombreNivelAreaSubnivel.trim().equals("")) {
			RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').show()");
			vhEstructuraInterface.mostrarMensajeRequerido("nombre_nivel_area_subnivel", Utilidades.mensajeProperties("mensaje_general_campoRequerido"), "");
			validado = false; 
		} else if(subnivelSeleccionado.getCuentaLDAP().equals(cuentaTitular)) {
			RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').show()");
			
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_info"),
					Utilidades.mensajeProperties("titulo_growl_info"),
					Utilidades.mensajeProperties("mensaje_advertencia_titular_subnivel"));
			
			validado = false;
		} else if(padreSubnivelSeleccionado.getCuentaPersona().equals(subnivelSeleccionado.getCuentaLDAP())) {
			RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').show()");
			
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_info"),
					Utilidades.mensajeProperties("titulo_growl_info"),
					Utilidades.mensajeProperties("mensaje_advertencia_padre_hijo_iguales"));
			
			validado = false;
		} else if(vhEstructuraInterface.buscarNodoPorCuentas(arbolEstructura, subnivelSeleccionado.getCuentaLDAP(), padreSubnivelSeleccionado.getCuentaPersona()) != null) {
			
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_info"),
					Utilidades.mensajeProperties("titulo_growl_info"),
					Utilidades.mensajeProperties("mensaje_advertencia_relacion_padre_hijo_existe"));
			
			RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').show()");

			validado = false;
		}
		return validado;
	}

	/**
	 * Método para validar si se guarda lo capturado dentro del módulo.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/09/2017
	 */
	public boolean validarCrearEstructura(List<DTOJerarquiaPersonasEntity> listaJerarquias, List<DTOOficialiaEntity> listaOficialias, String tipo) {
		boolean validado = true;
		String mensaje = "";
		if(tipo.equals("captura") && bsdPersonalInterface.consultarExisteEstructura(tipoAreaSeleccionado, Integer.valueOf(areaSeleccionada))) {
			validado = false;
			mensaje += "Ya se ha creado un organigrama con el área seleccionada.";
		} else if (listaJerarquias.size() == 0 ) {
			validado = false;
			mensaje += "Se requiere agregar por lo menos una persona al Organigrama.";
		} else if (listaOficialias.size() == 0 ) {
			validado = false;
			mensaje += "Se requiere agregar por lo menos una persona al Personal de Oficialía.";
		}
		if (!validado) {
			vhEstructuraInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					"No se pudo guardar el organigrama. " + mensaje);			
		}
		return validado;
	}
	
	/**
	 * Método para agregar una persona en el organigrama.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @throws Exception 
	 */
	public void agregarSubnivel() {
		if(validarAgregarSubnivel()) {
			TreeNode nodoPadre = vhEstructuraInterface.buscarNodoPorCuentas(
					arbolEstructura, padreSubnivelSeleccionado.getCuentaPersona(), padreSubnivelSeleccionado.getCuentaPadre());
			if(nodoPadre != null) {
				DTOJerarquiaPersonasEntity padreRelacion =(DTOJerarquiaPersonasEntity)nodoPadre.getData();
				subnivelSeleccionado.setNombreNivelArea(nombreNivelAreaSubnivel);
				subnivelSeleccionado.setIdArea(Integer.valueOf(areaSeleccionada));
				subnivelSeleccionado.setTipoArea(tipoAreaSeleccionado);
				DTOJerarquiaPersonasEntity relacion = new DTOJerarquiaPersonasEntity(
						subnivelSeleccionado.getIdPersona(), 
						Integer.valueOf(areaSeleccionada), 
						tipoAreaSeleccionado, 
						Integer.valueOf(padreRelacion.getIdNivel().intValue() + 1),
						null, 
						padreRelacion.getIdNivel(),
						subnivelSeleccionado.getCuentaLDAP(),
						padreRelacion.getCuentaPersona());

				DTOEstructuraAreasEntity cuentaExistente = bsdPersonalInterface.consultarPersonaPorCuenta(subnivelSeleccionado.getCuentaLDAP());
				if (cuentaExistente!= null) {
					subnivelSeleccionado.setIdPersona(cuentaExistente.getIdPersona());
					subnivelSeleccionado.setEstatus(cuentaExistente.getEstatus());
					relacion.setIdPersona(cuentaExistente.getIdPersona());
				}
				relacion.setDtoEstructuraAreas(subnivelSeleccionado);
				
				vhEstructuraInterface.agregarEnNodo(nodoPadre, relacion);
				RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').hide()");
				limpiarBuscarSubnivel();
			}
		}
	}
	
	/**
	 * Método para agregar una persona en la oficialía
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/09/2017
	 */
	public void agregarOficialia() {
		if(validarAgregarOficialia()) {
			for (DTOOficialiaEntity oficialia : oficialiasSeleccionadas) {
				DTOOficialiaEntity cuentaExistente = bsdPersonalInterface.consultarOficialiaPorCuenta(oficialia.getCuentaLDAP());
				if (cuentaExistente!= null) {
					oficialia.setIdOficialia(cuentaExistente.getIdOficialia());
					oficialia.setEstatus(cuentaExistente.getEstatus());
				}
				listaOficialia.add(oficialia);
			}
			oficialiasSeleccionadas = null;
			RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').hide()");
			limpiarBuscarOficialia();
		}
	}

	/**
	 * Método para guardar todo lo capturado.
	 * 
	 * @throws Exception: manda error para no continuar con el flujo
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public void guardarEstructura(String tipo) throws Exception {
		
		List<DTOEstructuraAreasEntity> listaEstructuras = new ArrayList<DTOEstructuraAreasEntity>();
		List<DTOJerarquiaPersonasEntity> listaJerarquias = new ArrayList<DTOJerarquiaPersonasEntity>();

		vhEstructuraInterface.crearListasEstructura(arbolEstructura, listaEstructuras, listaJerarquias);		
		
		if(validarCrearEstructura(listaJerarquias, listaOficialia, tipo)) {

			if (tipo.equals("captura") ) {
				
				try {

					bsdPersonalInterface.guardarEstructuraCompleta(Integer.valueOf(areaSeleccionada), tipoAreaSeleccionado, 
							listaEstructuras, listaJerarquias, null, listaOficialia, null);
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_exito"),
							Utilidades.mensajeProperties("titulo_growl_exito"),
							Utilidades.mensajeProperties("mensaje_exito_guardar_organigrama"));

				} catch (Exception e) {
					vhEstructuraInterface.enviarError(e, "MBEstructura", "guardarEstructura()", "Error al guardar estructura.");
					
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_guardar_organigrama"));

					throw new Exception("errorControlado");
				}
				
				this.vhEstructuraInterface.validacionesPosCapturaParaAdministrador(listaEstructuras);
				DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				if (!usuarioLogueado.getRol().toUpperCase().contains("ADMIN_AREA")) {
//				if(listaAreas.size() == 1) {
					RequestContext context = RequestContext.getCurrentInstance();
		        	context.execute("cambiarAConsulta()");
					inicializar("captura");
				}

			}  else if(tipo.equals("consulta")) {

				try {
	
					List<DTOJerarquiaPersonasEntity> listaJerarquiasBase= bsdPersonalInterface.consultarEstructuraPorArea(
							tipoAreaSeleccionado, Integer.valueOf(areaSeleccionada));
					List<DTOJerarquiaPersonasEntity> listaJerarquiasAgregar = new ArrayList<>();
					List<DTOJerarquiaPersonasEntity> listaJerarquiasEliminar = new ArrayList<>();
					
					List<DTOOficialiasAreasEntity> listaOficialiaAreasBase = bsdPersonalInterface.consultarOficialiasAreasPorArea(
							tipoAreaSeleccionado,Integer.valueOf(areaSeleccionada));
					List<DTOOficialiaEntity> listaOficialiaAgregar = new ArrayList<>();
					List<DTOOficialiasAreasEntity> listaOficialiaEliminar = new ArrayList<>();
					
					vhEstructuraInterface.procesarListasJerarquias(listaJerarquiasBase, listaJerarquias, listaJerarquiasAgregar, listaJerarquiasEliminar);

					vhEstructuraInterface.procesarListasOficialias(listaOficialiaAreasBase, listaOficialia, listaOficialiaAgregar, listaOficialiaEliminar);
					
					
					if(listaJerarquiasAgregar.size() == 0 && listaJerarquiasEliminar.size() == 0 &&
							listaOficialiaAgregar.size() == 0 && listaOficialiaEliminar.size() == 0) {
						
						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_info"),
								Utilidades.mensajeProperties("titulo_growl_info"),
								Utilidades.mensajeProperties("mensaje_info_no_modificaciones_organigrama"));
						
					} else {
						bsdPersonalInterface.guardarEstructuraCompleta(Integer.valueOf(areaSeleccionada), tipoAreaSeleccionado, 
								listaEstructuras, listaJerarquiasAgregar, listaJerarquiasEliminar, 
								listaOficialiaAgregar, listaOficialiaEliminar);
						
						vhEstructuraInterface.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_exito"),
								Utilidades.mensajeProperties("titulo_growl_exito"),
								Utilidades.mensajeProperties("mensaje_exito_modificar_organigrama"));
								
						buscarEstructuraPorArea();
					}
					
					this.vhEstructuraInterface.validacionesPosCapturaParaAdministrador(listaEstructuras);

				} catch(Exception e) {
					vhEstructuraInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("mensaje_advertencia_no_modificar_organigrama"));
					
					vhEstructuraInterface.enviarError(e, "MBEstructura", "guardarEstructura()", "Error al guardar estructura en consulta.");
					
					throw new Exception("errorControlado");
				}
			}
		}
	}
	
	/**
	 * Método para ocultar la ventana de agregar subnivel.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public void ocultarDialogSubnivel() {
		RequestContext.getCurrentInstance().execute("PF('agregar_subnivel').hide()");
		limpiarBuscarSubnivel();
	}
	
	///////////////////////////////////////////////
	//-------------GETTERS Y SETTERS-------------//
	///////////////////////////////////////////////
	
	/**
	 * Método para ocultar la ventana de agregar oficialía.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public void ocultarDialogOficialia() {
		RequestContext.getCurrentInstance().execute("PF('agregar_oficialia_form').hide()");
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable arbolEstructura
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public TreeNode getArbolEstructura() {
		return arbolEstructura;
	}

	/**
	 * @param arbolEstructura : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setArbolEstructura(TreeNode arbolEstructura) {
		this.arbolEstructura = arbolEstructura;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable detalleEstructura
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public DTOEstructuraAreasEntity getDetalleEstructura() {
		return detalleEstructura;
	}

	/**
	 * @param detalleEstructura : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setDetalleEstructura(DTOEstructuraAreasEntity detalleEstructura) {
		this.detalleEstructura = detalleEstructura;
	}

	/**
	 * @return valor de tipo DTOOficialiaEntity que esta contenido en la variable detalleOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public DTOOficialiaEntity getDetalleOficialia() {
		return detalleOficialia;
	}

	/**
	 * @param detalleOficialia : valor que se ingresa a la variable de tipo DTOOficialiaEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setDetalleOficialia(DTOOficialiaEntity detalleOficialia) {
		this.detalleOficialia = detalleOficialia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoAreaSeleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public Integer getTipoAreaSeleccionado() {
		return tipoAreaSeleccionado;
	}

	/**
	 * @param tipoAreaSeleccionado : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setTipoAreaSeleccionado(Integer tipoAreaSeleccionado) {
		this.tipoAreaSeleccionado = tipoAreaSeleccionado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable areaSeleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getAreaSeleccionada() {
		return areaSeleccionada;
	}

	/**
	 * @param areaSeleccionada : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setAreaSeleccionada(String areaSeleccionada) {
		this.areaSeleccionada = areaSeleccionada;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable entidadSeleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getEntidadSeleccionada() {
		return entidadSeleccionada;
	}

	/**
	 * @param entidadSeleccionada : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setEntidadSeleccionada(String entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable areaSeleccionadaOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getAreaSeleccionadaOficialia() {
		return areaSeleccionadaOficialia;
	}

	/**
	 * @param areaSeleccionadaOficialia : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setAreaSeleccionadaOficialia(String areaSeleccionadaOficialia) {
		this.areaSeleccionadaOficialia = areaSeleccionadaOficialia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable cuentaTitular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getCuentaTitular() {
		return cuentaTitular;
	}

	/**
	 * @param cuentaTitular : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setCuentaTitular(String cuentaTitular) {
		this.cuentaTitular = cuentaTitular;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable siglas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getSiglas() {
		return siglas;
	}

	/**
	 * @param siglas : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * @return valor de tipo List<DTOCAreas> que esta contenido en la variable listaAreasOficialia
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	public List<DTOCAreaEntity> getListaAreasOficialia() {
		return listaAreasOficialia;
	}

	/**
	 * @param listaAreasOficialia : valor que se ingresa a la variable de tipo List<DTOCAreas>
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	public void setListaAreasOficialia(List<DTOCAreaEntity> listaAreasOficialia) {
		this.listaAreasOficialia = listaAreasOficialia;
	}
	
	/**
	 * @return valor de tipo List<DTOCAreas> que esta contenido en la variable listaAreas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public List<DTOCAreaEntity> getListaAreas() {
		return listaAreas;
	}

	/**
	 * @param listaAreas : valor que se ingresa a la variable de tipo List<DTOCAreas>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setListaAreas(List<DTOCAreaEntity> listaAreas) {
		this.listaAreas = listaAreas;
	}

	/**
	 * @return valor de tipo List<DTOEntidad> que esta contenido en la variable listaEstados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	//public List<DTOEntidad> getListaEstados() {
	//	return listaEstados;
	//}

	/**
	 * @param listaEstados : valor que se ingresa a la variable de tipo List<DTOEntidad>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	//public void setListaEstados(List<DTOEntidad> listaEstados) {
	//	this.listaEstados = listaEstados;
	//}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaBusquedaEstructura
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaBusquedaEstructura() {
		return listaBusquedaEstructura;
	}

	/**
	 * @param listaBusquedaEstructura : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setListaBusquedaEstructura(
			List<DTOEstructuraAreasEntity> listaBusquedaEstructura) {
		this.listaBusquedaEstructura = listaBusquedaEstructura;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaBusquedaSubnivel
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaBusquedaSubnivel() {
		return listaBusquedaSubnivel;
	}

	/**
	 * @param listaBusquedaSubnivel : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setListaBusquedaSubnivel(
			List<DTOEstructuraAreasEntity> listaBusquedaSubnivel) {
		this.listaBusquedaSubnivel = listaBusquedaSubnivel;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable titularSeleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public DTOEstructuraAreasEntity getTitularSeleccionado() {
		return titularSeleccionado;
	}

	/**
	 * @param titularSeleccionado : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setTitularSeleccionado(DTOEstructuraAreasEntity titularSeleccionado) {
		this.titularSeleccionado = titularSeleccionado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreNivelAreaTitular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getNombreNivelAreaTitular() {
		return nombreNivelAreaTitular;
	}

	/**
	 * @param nombreNivelAreaTitular : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setNombreNivelAreaTitular(String nombreNivelAreaTitular) {
		this.nombreNivelAreaTitular = nombreNivelAreaTitular;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable mostrarSubarea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public boolean isMostrarSubarea() {
		return mostrarSubarea;
	}

	/**
	 * @param mostrarSubarea : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setMostrarSubarea(boolean mostrarSubarea) {
		this.mostrarSubarea = mostrarSubarea;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreNivelAreaSubnivel
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getNombreNivelAreaSubnivel() {
		return nombreNivelAreaSubnivel;
	}

	/**
	 * @param nombreNivelAreaSubnivel : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setNombreNivelAreaSubnivel(String nombreNivelAreaSubnivel) {
		this.nombreNivelAreaSubnivel = nombreNivelAreaSubnivel;
	}

	/**
	 * @return valor de tipo DTOJerarquiaPersonasEntity que esta contenido en la variable padreSubnivelSeleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public DTOJerarquiaPersonasEntity getPadreSubnivelSeleccionado() {
		return padreSubnivelSeleccionado;
	}

	/**
	 * @param padreSubnivelSeleccionado : valor que se ingresa a la variable de tipo DTOJerarquiaPersonasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setPadreSubnivelSeleccionado(
			DTOJerarquiaPersonasEntity padreSubnivelSeleccionado) {
		this.padreSubnivelSeleccionado = padreSubnivelSeleccionado;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable subnivelSeleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public DTOEstructuraAreasEntity getSubnivelSeleccionado() {
		return subnivelSeleccionado;
	}

	/**
	 * @param subnivelSeleccionado : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setSubnivelSeleccionado(
			DTOEstructuraAreasEntity subnivelSeleccionado) {
		this.subnivelSeleccionado = subnivelSeleccionado;
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable nodoTitularSeleccionado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public TreeNode getNodoTitularSeleccionado() {
		return nodoTitularSeleccionado;
	}

	/**
	 * @param nodoTitularSeleccionado : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setNodoTitularSeleccionado(TreeNode nodoTitularSeleccionado) {
		this.nodoTitularSeleccionado = nodoTitularSeleccionado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreTitular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidosTitular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getApellidosTitular() {
		return apellidosTitular;
	}

	/**
	 * @param apellidosTitular : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setApellidosTitular(String apellidosTitular) {
		this.apellidosTitular = apellidosTitular;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreSubnivel
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getNombreSubnivel() {
		return nombreSubnivel;
	}

	/**
	 * @param nombreSubnivel : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setNombreSubnivel(String nombreSubnivel) {
		this.nombreSubnivel = nombreSubnivel;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidosSubnivel
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getApellidosSubnivel() {
		return apellidosSubnivel;
	}

	/**
	 * @param apellidosSubnivel : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setApellidosSubnivel(String apellidosSubnivel) {
		this.apellidosSubnivel = apellidosSubnivel;
	}

	/**
	 * @return valor de tipo List<DTOOficialiaEntity> que esta contenido en la variable listaOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public List<DTOOficialiaEntity> getListaOficialia() {
		return listaOficialia;
	}

	/**
	 * @param listaOficialia : valor que se ingresa a la variable de tipo List<DTOOficialiaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setListaOficialia(List<DTOOficialiaEntity> listaOficialia) {
		this.listaOficialia = listaOficialia;
	}

	/**
	 * @return valor de tipo List<DTOOficialiaEntity> que esta contenido en la variable listaBusquedaOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public List<DTOOficialiaEntity> getListaBusquedaOficialia() {
		return listaBusquedaOficialia;
	}

	/**
	 * @param listaBusquedaOficialia : valor que se ingresa a la variable de tipo List<DTOOficialiaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setListaBusquedaOficialia(
			List<DTOOficialiaEntity> listaBusquedaOficialia) {
		this.listaBusquedaOficialia = listaBusquedaOficialia;
	}

	/**
	 * @return valor de tipo List<DTOOficialiaEntity> que esta contenido en la variable oficialiasSeleccionadas
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	public List<DTOOficialiaEntity> getOficialiasSeleccionadas() {
		return oficialiasSeleccionadas;
	}

	/**
	 * @param List<oficialiasSeleccionadas> : valor que se ingresa a la variable de tipo DTOOficialiaEntity
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	public void setOficialiasSeleccionadas(List<DTOOficialiaEntity> oficialiasSeleccionadas) {
		this.oficialiasSeleccionadas = oficialiasSeleccionadas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getNombreOficialia() {
		return nombreOficialia;
	}

	/**
	 * @param nombreOficialia : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setNombreOficialia(String nombreOficialia) {
		this.nombreOficialia = nombreOficialia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidosOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getApellidosOficialia() {
		return apellidosOficialia;
	}

	/**
	 * @param apellidosOficialia : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setApellidosOficialia(String apellidosOficialia) {
		this.apellidosOficialia = apellidosOficialia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable areaOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public String getAreaOficialia() {
		return areaOficialia;
	}

	/**
	 * @param areaOficialia : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setAreaOficialia(String areaOficialia) {
		this.areaOficialia = areaOficialia;
	}

	/**
	 * @return valor de tipo BSDEstructuraInterface que esta contenido en la variable bsdPersonalInterface
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public BSDEstructuraInterface getBsdPersonalInterface() {
		return bsdPersonalInterface;
	}

	/**
	 * @param bsdPersonalInterface : valor que se ingresa a la variable de tipo BSDEstructuraInterface
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setBsdPersonalInterface(BSDEstructuraInterface bsdPersonalInterface) {
		this.bsdPersonalInterface = bsdPersonalInterface;
	}

	/**
	 * @return valor de tipo VHEstructuraInterface que esta contenido en la variable vhEstructuraInterface
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public VHEstructuraInterface getVhEstructuraInterface() {
		return vhEstructuraInterface;
	}

	/**
	 * @param vhEstructuraInterface : valor que se ingresa a la variable de tipo VHEstructuraInterface
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/10/2017
	 */
	public void setVhEstructuraInterface(VHEstructuraInterface vhEstructuraInterface) {
		this.vhEstructuraInterface = vhEstructuraInterface;
	}

	public boolean isConRolAdminArea() {
		return conRolAdminArea;
	}

	public void setConRolAdminArea(boolean conRolAdminArea) {
		this.conRolAdminArea = conRolAdminArea;
	}

	public boolean isMostrarFormulario() {
		return mostrarFormulario;
	}

	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

	public List<DTOEstadosEntity> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<DTOEstadosEntity> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @return variable de tipo List<DTOCTipoAreaEntity> contenida en listaTiposAreas
	 * 
	 * @since 08/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<DTOCTipoAreaEntity> getListaTiposAreas() {
		return listaTiposAreas;
	}

	/**
	 * @param listaTiposAreas: variable de tipo List<DTOCTipoAreaEntity> contenida en listaTiposAreas
	 *
	 * @since 08/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setListaTiposAreas(List<DTOCTipoAreaEntity> listaTiposAreas) {
		this.listaTiposAreas = listaTiposAreas;
	}
	
	
}
