package mx.ine.computosINE.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.enums.EnumEstatusModulo;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.bsd.BSDAdministradorSistemaInterface;
import mx.ine.computosINE.dto.form.FormAdministrador;
import mx.ine.computosINE.helper.HLPTransformadorMenu;
import mx.ine.computosINE.util.Utilidades;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que se genera en sesión encargada de manejar el menú del sistema
 * 
 * @author Robert S.
 * @updatedBy José Antonio López Torres
 * @since 25/10/2014
 * @copyright INE
 */
@Component("mbAdmin")
@Scope("prototype")
public class MBAdministradorSistema extends MBGeneric implements Serializable {

    /**
     * Objeto par la serialización de esta clase.
     */
    private static final long serialVersionUID = -7362510999437643253L;

    /**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
    private static final Logger LOGGER = Logger.getLogger(MBAdministradorSistema.class);
    
    @Autowired
    @Qualifier("bsdAdmin")
    private transient BSDAdministradorSistemaInterface bsdAdmin;    
    /**
     * DTO que contiene los datos necesarios para manipular el home
     * y el menú
     */
    private FormAdministrador dto;
    /**
     * Helper para tranformar el menú
     */
    private HLPTransformadorMenu hlpTransfMenu;
    
    private boolean verMenu;
    
    /**
     * SobreConstrucción de la clase para inicializar variables
     * 
     * @author Pablo Zuñiga Mata
     * @since 21/02/2017
     */
    public MBAdministradorSistema() {
        dto = new FormAdministrador();
        hlpTransfMenu = new HLPTransformadorMenu();
        this.verMenu = false;
    }
    
    /**
     * Método encargado de realizar validaciones una vez la
     * clase ha sido inicializada
     * 
     * @author José Antonio López Torres 
     * @since 20/02/2017
     */
    @PostConstruct
    public void inicializa(){
        //Usuario autenticado
        dto.setUsuario(obtenUsuario());
        if(dto.getUsuario().getListaMunicipios() != null)
        {
        	for (DTODetalleMunicipioProcesoWS municipio : dto.getUsuario().getListaMunicipios()) {
    			String nombreSinSimbolos = municipio.getNombreMunicipio().replace("�", "Ñ");
    			municipio.setNombreMunicipio(nombreSinSimbolos);
    		}
        }
        
        //Valida usuario
        validaUsuario();
    }
    
    /**
     * Método encargado de iniciar menú lateral
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void inicializaMenu() {
      //Si solo es un detalle 
    	if(dto.getUsuario().getIdProcesoElectoral() != null){
            //Genera menus
            generaMenus();
        }
    }
    
    /**
     * Método encargado de validar que combos deshabilitar dependiendo
     * de los datos del usuario
     * 
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private void validaUsuario(){
      //Si solo es un proceso 
        if(dto.getUsuario().getDetalleSeleccionado() != null
        		&& dto.getUsuario().getDetalleSeleccionado().getIdProcesoElectoral() != null){
            dto.setIdProceso(dto.getUsuario().getIdProcesoElectoral());
            dto.setIdDetalleProceso(dto.getUsuario().getIdDetalleProceso());
            dto.setProceso(dto.getUsuario().getDetalleSeleccionado());
        }
        String version = dto.getUsuario().getVersion();
        //Si solo es un proceso 
        if(dto.getUsuario().getListaDetalles().size() == 1){
            dto.setDisableDetalle(true);
        }
        if(dto.getIdDetalleProceso() != null){
            //Si es OC
            if(version.equals(Utilidades.mensajeProperties("constante_version_rol_oc"))){
                dto.setDisableEstado(false);
                dto.setDisableMunicipio(false);
            }
            //Si es JL y se obtuvo el estado deshabilita combo estado
            if(version.equals(Utilidades.mensajeProperties("constante_version_rol_jl")) &&
                    dto.getUsuario().getIdEstadoSeleccionado() != null){
                dto.setDisableEstado(true);
                dto.setDisableMunicipio(false);
            }
            //Si es JD y se obtuvo el distrito deshabilita combo municipio
            if(version.equals(Utilidades.mensajeProperties("constante_version_rol_jm")) &&
                    dto.getUsuario().getIdMunicipioSeleccionado() != null){
                dto.setDisableEstado(true);
                dto.setDisableMunicipio(true);
            }
        }
    }
    
    /**
     * Método llamado desde la vista (menu.xhtml) al seleccionar un proceso
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void cambiaDetalle(){
        limpiaEstado();
        limpiaMunicipio();
        //Obten info proceso
        dto.setIdProceso(null);
        dto.setProceso(null);
        if(dto.getIdDetalleProceso() != null && dto.getIdDetalleProceso() > 0){
            localizaDetalle();
            cargaEstados();
            generaMenus();
            validaUsuario();
        }else{
            //Limpia menu lateral
            dto.setJsonMenuLateral(null);
            dto.setJsonMenuAcciones(null);
            renderMenuLateral();
        }
        //Llamadas javascript
        limpiaMenuLateral();
        redirectHome();
    }
    
    /**
     * Método llamado desde la vista (menu.xhtml) al seleccionar un estado
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void cambiaEstado(){
        //Obten info estado
        limpiaMunicipio();
        dto.getUsuario().setEstadoSeleccionado(null);
        if(dto.getUsuario().getIdEstadoSeleccionado() != null){
            localizaEstado();
            cargaMunicipios();
        }
        //Llamadas javascript
        limpiaMenuLateral();
        redirectHome();
        generaMenus();
    }
    
    /**
     * Método llamado desde la vista (menu.xhtml) al seleccionar un distrito
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void cambiaMunicipio(){
        //Obten info distrito
        dto.getUsuario().setMunicipioSeleccionado(null);
        if(dto.getUsuario().getIdMunicipioSeleccionado() != null){
            localizaMunicipio();
        }
        //Llamadas javascript
        limpiaMenuLateral();
        redirectHome();
        generaMenus();
    }
    
    /**
     * Método encargado de obtener los estados dependiendo del proceso electoral
     * seleccionado
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void cargaEstados(){
        limpiaEstado();
        try{
        	List<DTODetalleEstadoProcesoWS> lista = bsdAdmin.obtenerEstadosDetalle(
        			dto.getUsuario().getIdSistema(), 
        			dto.getUsuario().getDetalleSeleccionado().getIdProcesoElectoral(), 
        			dto.getUsuario().getDetalleSeleccionado().getIdDetalleProceso());
//        	Si es OC
            if(dto.getUsuario().getVersion().equals(
                    Utilidades.mensajeProperties("constante_version_rol_oc")) && dto.getUsuario().getIdEstado() == 0
                    && dto.getUsuario().getIdMunicipio() == 0){
                dto.getUsuario().setListaEstados(lista);
//          Si es JL o JD
            }else{
            	dto.getUsuario().setListaEstados(localizaEstadoUsuario(lista));
                dto.getUsuario().setIdEstadoSeleccionado(dto.getUsuario().getIdEstado());
                cargaMunicipios();
            }
        }catch(ClienteWebServiceException e){
            LOGGER.error("Error MBAdministradorSistema - cargaEstados()", e);
        }catch(Exception e){
            LOGGER.error("Error MBAdministradorSistema - cargaEstados()", e);
        }
    }
    
    /**
     * Método encargado de obtener los municipios dependiendo del estado
     * seleccionado
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void cargaMunicipios(){
        limpiaMunicipio();
        try{
        	List<DTODetalleMunicipioProcesoWS> lista = bsdAdmin.obtenerMunicipiosDetalle(
                    dto.getUsuario().getIdSistema(), 
                    dto.getUsuario().getDetalleSeleccionado().getIdProcesoElectoral(), 
                    dto.getUsuario().getDetalleSeleccionado().getIdDetalleProceso(),
                    dto.getUsuario().getIdEstadoSeleccionado());
          //Si es JD obten unico municipio
            if(dto.getUsuario().getVersion().equals(
                    Utilidades.mensajeProperties("constante_version_rol_jm")) && dto.getUsuario().getIdEstado() > 0
                    && dto.getUsuario().getIdMunicipio() > 0){
                dto.getUsuario().setListaMunicipios(localizaMunicipioUsuario(lista));
                dto.getUsuario().setIdMunicipioSeleccionado(dto.getUsuario().getIdMunicipio());
            }else{
            	if(lista != null)
                {
                	for (DTODetalleMunicipioProcesoWS municipio : lista) {
            			String nombreSinSimbolos = municipio.getNombreMunicipio().replace("�", "Ñ");
            			municipio.setNombreMunicipio(nombreSinSimbolos);
            		}
                }
                dto.getUsuario().setListaMunicipios(lista);
            }
        }catch(ClienteWebServiceException e){
            LOGGER.error("Error MBAdministradorSistema - cargaEstados()", e);
        }catch(Exception e){
            LOGGER.error("Error MBAdministradorSistema - cargaDistritos()", e);
        }
    }
    
    /**
     * Método encargado de limpiar los valores del estado
     * 
     * @author José Antonio López Torres 
     * @since 09/02/2017
     */
    private void limpiaEstado(){
        dto.getUsuario().setListaEstados(null);
        dto.getUsuario().setEstadoSeleccionado(null);
        dto.getUsuario().setIdEstadoSeleccionado(null);
        dto.setDisableEstado(false);
    }
    
    /**
     * Método encargado de limpiar los valores del distrito
     * 
     * @author José Antonio López Torres 
     * @since 09/02/2017
     */
    private void limpiaMunicipio(){
        dto.getUsuario().setListaMunicipios(null);
        dto.getUsuario().setMunicipioSeleccionado(null);
        dto.getUsuario().setIdMunicipioSeleccionado(null);
        dto.setDisableDistrito(false);
    }
    
    /**
     * Método encargado de consultar el menú a través de un servicio web Para
     * que funcione deben existir las siguientes variables
     * <code>idProceso</code>; Proceso electoral, en sistemas institucionales
     * este no aplica, quizás deba agregarse una variable de tipoSistema para
     * determinar si se consulta o no <code>idSistema</code>; Identificador del
     * sistema, debe estar definido en algún lado <code>idEstado</code>;
     * Identificador del estado con que entró el usuario o ha sido cambiado del
     * combo, nunca es nulo, 0 se toma para OC <code>idDistrito</code>;
     * Identificador del distrito con que entró el usuario, o ha sido cambiado
     * del combo, puede ser nulo, 0 se toma para cabecera distrital
     * <code>grupo</code>
     * **/
    public void generaMenus() {
    	this.verMenu = false;
    	String version = dto.getUsuario().getVersion();
    	if(version.equals(Utilidades.mensajeProperties("constante_version_rol_oc")) &&
    			dto.getIdDetalleProceso() != null && dto.getIdDetalleProceso() > 0){
            verMenu = true;
        }
        //Si es JL
        if(version.equals(Utilidades.mensajeProperties("constante_version_rol_jl")) &&
                dto.getUsuario().getIdEstadoSeleccionado() != null && dto.getUsuario().getIdEstadoSeleccionado() > 0){
            verMenu = true;
        }
        //Si es JD
        if(version.equals(Utilidades.mensajeProperties("constante_version_rol_jm")) &&
                dto.getUsuario().getIdMunicipioSeleccionado() != null && dto.getUsuario().getIdMunicipioSeleccionado() > 0){
            verMenu = true;
        }
    	if(verMenu){
	        //Menu lateral
	        generaMenuLateral();
	        //Menu acciones
	        generaMenuAcciones();
    	}
    }
    
    /**
     * Genera menú lateral
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void generaMenuLateral() {
        dto.setJsonMenuLateral(null);
        dto.setJsonMenuAcciones(null);
        try {
            dto.setJsonMenuLateral(bsdAdmin.generaMenuLateral(dto.getIdProceso(),
            		dto.getIdDetalleProceso(),
                    dto.getUsuario().getIdSistema(), 
                    obtenEstadoMenu(), 
                    obtenMunicipioMenu(), 
                    dto.getUsuario().getRolUsuario()));
            if (dto.getJsonMenuLateral() == null || dto.getJsonMenuLateral().trim().isEmpty()) {
                LOGGER.error("No se obtuvieron datos del menú");
            }
            renderMenuLateral();
        } catch (ClienteWebServiceException e) {
            if(e.getCode().intValue() == 404){
                LOGGER.info("No existe menú para los datos enviados");
            }else{
                LOGGER.error("Error MBAdministradorSistema - generaMenuLateral()", e);
            }
        }catch(Exception e){
            LOGGER.error("Error MBAdministradorSistema - generaMenuLateral()", e);
        }
    }
    
    /**
     * Método encargado de generar el menú de módulos cada que cambia el
     * proceso, el estado o el distrito Para que funcione debe haberse asignado
     * los datos del usuario y haber elegido el proceso electoral
     * 
     * @author Mayra Victoria
     * @since 09/09/2016
     */
    public void generaMenuAcciones() {
        dto.setJsonMenuAcciones(null);
        try {
            dto.setJsonMenuAcciones(bsdAdmin.generaMenuAcciones(
                    dto.getIdProceso(), 
                    dto.getIdDetalleProceso(),
                    dto.getUsuario().getIdSistema(), 
                    obtenEstadoMenu(), 
                    obtenMunicipioMenu(),
                    dto.getUsuario().getRolUsuario()));
            if (dto.getJsonMenuAcciones() == null || dto .getJsonMenuAcciones().trim().isEmpty()) {
                LOGGER.error("No se obtuvieron datos del menú de acciones");
            }
            renderMenuAcciones();
        } catch (ClienteWebServiceException e) {
            if(e.getCode().intValue() == 404){
                LOGGER.info("No existe menú para los datos enviados");
            }else{
                LOGGER.error("Error MBAdministradorSistema - generaMenuAcciones()", e);
            }
        } catch (Exception e) {
            LOGGER.error("Error MBAdministradorSistema - generaMenuAcciones()", e);
        }
    }

    
    /**
     * Obten id estado para enviar a menu
     * 
     * @return Integer : idEstado
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private Integer obtenEstadoMenu(){
        Integer idEstado = dto.getUsuario().getIdEstadoSeleccionado() == null ? 0 : 
            dto.getUsuario().getIdEstadoSeleccionado().intValue();
        return idEstado;
    }
    
    /**
     * Obten id distrito para enviar a menu
     * 
     * @return Integer : idDistrito
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private Integer obtenMunicipioMenu(){
        Integer idDistrito = dto.getUsuario().getIdMunicipioSeleccionado() == null ? 0 : 
            dto.getUsuario().getIdMunicipioSeleccionado().intValue();
        return idDistrito;
    }
    /**
     * Método encargado de renderizar el menú lateral desde el mbAdmin
     * 
     *
     */
    public void renderMenuLateral() {
        LOGGER.info("generando menú lateral");
        Integer code = 500;
        //String jsonTems = "{\"wsrest\":{\"fecha\":\"09-03-2017  6:20 p.m.\",\"version\":\"1.6\",\"descripcion\":\"Consulta los menú\",\"actualizacion\":\"Cambio de versión\"},\"code\":200,\"status\":\"Éxito\",\"message\":\"La consulta se realizó con éxito\",\"causa\":null,\"listMenu\":[{\"idMenu\":5,\"nombreMenu\":\"Captura\",\"subMenus\":[{\"idSubMenu\":1,\"nombreSubMenu\":\"Votos\",\"modulos\":[{\"idModulo\":7,\"nombreModulo\":\"Ayuntamiento\",\"urlModulo\":\"/app/mapas/mapas\",\"idAccion\":2,\"accionDescrip\":\"Consultar\",\"tipoJunta\":\"OC\",\"estatus\":\"A\"}],\"moduloslist\":[]},{\"idSubMenu\":2,\"nombreSubMenu\":\"Distribucion\",\"modulos\":[{\"idModulo\":8,\"nombreModulo\":\"Ayuntamiento\",\"urlModulo\":\"/app/mapas/mapas\",\"idAccion\":2,\"accionDescrip\":\"Consultar\",\"tipoJunta\":\"OC\",\"estatus\":\"A\"}],\"moduloslist\":[]}]},{\"idMenu\":6,\"nombreMenu\":\"Generación\",\"subMenus\":[{\"idSubMenu\":1,\"nombreSubMenu\":\"Actas\",\"modulos\":[{\"idModulo\":7,\"nombreModulo\":\"Ayuntamiento\",\"urlModulo\":\"/app/modulos/generacionActaAyuntamiento/capturar\",\"idAccion\":2,\"accionDescrip\":\"Consultar\",\"tipoJunta\":\"OC\",\"estatus\":\"A\"}],\"moduloslist\":[]},{\"idSubMenu\":2,\"nombreSubMenu\":\"Reportes\",\"modulos\":[{\"idModulo\":8,\"nombreModulo\":\"Casillas\",\"urlModulo\":\"/app/mapas/mapas\",\"idAccion\":2,\"accionDescrip\":\"Consultar\",\"tipoJunta\":\"OC\",\"estatus\":\"A\"}],\"moduloslist\":[]}]}]}";
        if(this.verMenu){
            code = Utilidades.validaDatosMenu(dto.getJsonMenuLateral());
            //code = Utilidades.validaDatosMenu(jsonTems);
    	}
        if(code.intValue()==200){
            if(dto.getJsonMenuLateral()!=null && !dto.getJsonMenuLateral().trim().isEmpty()){
                StringBuilder sb = new StringBuilder();
                sb.append("generaMenuLateral('").append(dto.getJsonMenuLateral()).
                   append("','").append(dto.getIdModulo()).append("')");
                RequestContext.getCurrentInstance().execute(sb.toString());                   
            }
            /*if(jsonTems!=null && !jsonTems.trim().isEmpty()){
                StringBuilder sb = new StringBuilder();
                sb.append("generaMenuLateral('").append(jsonTems).
                   append("','").append(dto.getIdModulo()).append("')");
                RequestContext.getCurrentInstance().execute(sb.toString());                   
            }*/
        }else if(code.intValue()==404 || code.intValue()==500){
            Utilidades.enviaMensajeMenuLateral(Utilidades.mensajeProperties("mensaje_menu_no_datos"));
        }
    }

    /**
     * Método encargado de renderizar el menú de acciones desde el mbAdmin Debe
     * existir el idModulo para ser capaz de mostrar algo
     * **/
    public void renderMenuAcciones() {
        LOGGER.info("generando menú de acciones");
        dto.setListaAcciones(null);
        /** Comenzando generación dinámica de componentes del menú **/
        if (dto.getJsonMenuAcciones() != null
                        && !dto.getJsonMenuAcciones().trim().isEmpty() 
                        && dto.getIdModulo() != null) {
                LOGGER.info("Menú Acciones : " + dto.getJsonMenuAcciones());
                LOGGER.info(dto.getIdModulo());
                // Construimos el menú de acciones
                dto.setListaAcciones(hlpTransfMenu.construyeMenuAcciones(
                                dto.getJsonMenuAcciones(), dto.getIdModulo()
                                                .intValue()));
        }
    }
    
    /**
     * Método encargado de localizar el detalle seleccionado 
     * de una lista
     * 
     * @author José Antonio López Torres 
     * @since 09/02/2017
     */
    private void localizaDetalle(){
        for(DTODetalleProcesoWS detalle : dto.getUsuario().getListaDetalles()){
            if(detalle.getIdDetalleProceso().intValue() == dto.getIdDetalleProceso().intValue()){
            	dto.getUsuario().setDetalleSeleccionado(detalle);
            	dto.getUsuario().setIdDetalleProceso(detalle.getIdDetalleProceso());
            	dto.getUsuario().setIdProcesoElectoral(detalle.getIdProcesoElectoral());
            	dto.setIdProceso(detalle.getIdProcesoElectoral());
            	dto.setProceso(detalle);
                if("F".equalsIgnoreCase(detalle.getAmbitoDetalle())){
                	dto.getUsuario().setAmbitoDetalleProceso(EnumAmbitoDetalleProceso.F);
                }else{
                	dto.getUsuario().setAmbitoDetalleProceso(EnumAmbitoDetalleProceso.L);
                }
                break;
            }
        }
    }
 
    /**
     * Método encargado de validar si los datos del menú lateral
     * corresponden con la versión del sistema
     * 
     * @param version : OC,JL o JD
     * @return true : corresponde, false : no corresponde
     *
     * @author José Antonio López Torres 
     * @since 16/02/2017
     */
    public boolean validaVersion(String version){
        boolean respuesta = false;
        Integer idEstado = dto.getUsuario().getIdEstadoSeleccionado() == null ? 0 : 
        	dto.getUsuario().getIdEstadoSeleccionado();
        Integer idMunicipio = dto.getUsuario().getIdMunicipioSeleccionado() == null ? 0 : 
        	dto.getUsuario().getIdMunicipioSeleccionado();
        if(dto.getUsuario().getIdDetalleProceso() != null){
            if(version.equalsIgnoreCase(
            		Utilidades.mensajeProperties("constante_version_rol_oc")) &&
                    idEstado == 0 && idMunicipio == 0){
                respuesta = true;
            }else if(version.equalsIgnoreCase(
            		Utilidades.mensajeProperties("constante_version_rol_jl")) &&
                    idEstado > 0 && idMunicipio == 0){
                respuesta = true;
            }else if(version.equalsIgnoreCase(
            		Utilidades.mensajeProperties("constante_version_rol_jm")) &&
                    idEstado > 0 && idMunicipio > 0){
                respuesta = true;
            }
        }
        return respuesta;
    }
    
    /**
     * Método encargado de validar si los datos del menú lateral </BR>
     * corresponden con la versión del sistema </BR>
     * true   : Corresponde a la version. </BR>
     * false  : No corresponde a la version
     * 
     * @param version : OC, JL o JM
     * @return true
     * @author Clemencia Cuellar
     * @since 19/05/2017
     */
    public boolean validaVersionRolUsuario(String rolUsuario) {

    	Integer idEstado    = null;
    	Integer idMunicipio = null;
    	String  version     = null;

		if ( rolUsuario == null || rolUsuario.isEmpty() ) {
			return false;
		}

		version = getSufijoRolUsuario(rolUsuario).trim();

        idEstado    = dto.getUsuario().getIdEstadoSeleccionado()    == null ? 0 : dto.getUsuario().getIdEstadoSeleccionado();
        idMunicipio = dto.getUsuario().getIdMunicipioSeleccionado() == null ? 0 : dto.getUsuario().getIdMunicipioSeleccionado();

        if ( dto.getUsuario().getIdDetalleProceso() != null ) {
            if ( version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_oc") ) &&
                 idEstado == 0 && idMunicipio == 0 ) {
            	return true;
            } else if ( version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_jl")) &&
                        idEstado > 0 ) {
            	return true;
            } else if ( version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_jm")) &&
                    	idEstado > 0 && idMunicipio > 0 ) {
            	return true;
            }
        }
        return false;
    }

    /**
     * Método encargado de validar si el módulo se encuentra abierto
     * 
     * @param idModulo :Id modulo
     * 
     * @return true : abierto, false : cerrado o deshabiltado
     *
     * @author Pablo Zuñiga Mata
     * @since 16/03/2017
     */
    public boolean validaModuloAbierto(Integer idModulo){
        boolean respuesta = false;
        try{
            EnumEstatusModulo estatus = bsdAdmin.obtenEstatusModuloPorMunicipio(
                    dto.getUsuario().getIdProcesoElectoral(), 
                    dto.getUsuario().getIdDetalleProceso(), 
                    dto.getUsuario().getIdSistema(), 
                    obtenEstadoMenu(), 
                    obtenMunicipioMenu(), 
                    dto.getUsuario().getRolUsuario(), 
                    idModulo);
            if(estatus.equals(EnumEstatusModulo.A)){
                respuesta = true;
            }
        }catch(ClienteWebServiceException e){
            LOGGER.error("Error MBAdministradorSistema - validaModuloAbierto()", e);
        }
        return respuesta;
    }
    
    
    /**
     * Método encargado de localizar el estado seleccionado 
     * de una lista
     * 
     * @author Pablo Zuñiga Mata
     * @since 21/02/2017
     */
    private void localizaEstado(){
        for(DTODetalleEstadoProcesoWS estado : dto.getUsuario().getListaEstados()){
            if(estado.getIdEstado().intValue() == 
                    dto.getUsuario().getIdEstadoSeleccionado().intValue()){
                dto.getUsuario().setEstadoSeleccionado(estado);
                break;
            }
        }
    }
    /**
     * Método encargado de localizar el estado del usuario
     * 
     * @return List<DTODetalleEstadoProcesoWS> : Estado
     * 
     * @author Pablo Zuñiga Mata
     * @since 21/02/2017
     */
    private List<DTODetalleEstadoProcesoWS> localizaEstadoUsuario(List<DTODetalleEstadoProcesoWS> lista){
        List<DTODetalleEstadoProcesoWS> estados = new ArrayList<>();
        for(DTODetalleEstadoProcesoWS a : lista){
            if(a.getIdEstado().intValue() == 
                    dto.getUsuario().getIdEstado().intValue()){
                estados.add(a);
                break;
            }
        }
        return estados;
    }
    
    /**
     * Método encargado de localizar el municipio seleccionado 
     * de una lista
     * 
     * @author Pablo Zuñiga Mata
     * @since 21/02/2017
     */
    private void localizaMunicipio(){
        for(DTODetalleMunicipioProcesoWS municpio : dto.getUsuario().getListaMunicipios()){
            if(municpio.getIdMunicipio().intValue() == 
                    dto.getUsuario().getIdMunicipioSeleccionado().intValue()){
                dto.getUsuario().setMunicipioSeleccionado(municpio);
                break;
            }
        }
    }
    
    /**
     * Método encargado de localizar el municipio del usuario
     * 
     * @return List<DTODetalleDistritoProcesoWS> : Distrito
     * 
     * @author Pablo Zuñiga Mata
     * @since 21/02/2017
     */
    private List<DTODetalleMunicipioProcesoWS> localizaMunicipioUsuario(List<DTODetalleMunicipioProcesoWS> lista){
        List<DTODetalleMunicipioProcesoWS> municipios = new ArrayList<>();
        for(DTODetalleMunicipioProcesoWS a : lista){
            if(a.getIdMunicipio().intValue() == 
                    dto.getUsuario().getIdMunicipio().intValue()){
            	municipios.add(a);
                break;
            }
        }
        return municipios;
    }

    /**
     * Obtiene el sufijo o version del rol de usuario. </BR>
     * Ejemplo: rolUsuario = "COMPUTOS.CAPTURA.JM" el sufijo del rol de usuario que se obtendria seria "JM". </BR>
     * @param rolUsuario
     * @return
     */
    private String getSufijoRolUsuario(String rolUsuario) {
    	return rolUsuario.substring(rolUsuario.length()-2);
    }

    /**
     * Método encargado de limpiar el menú lateral
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void limpiaMenuLateral() {
        RequestContext.getCurrentInstance().execute("limpiaMenuLateral()");
    }
    
    /**
     * Método encargado de mandar a home mediante una llama javascript
     * 
     * @author José Antonio López Torres 
     * @since 15/02/2017
     */
    public void redirectHome(){
        RequestContext.getCurrentInstance().execute("llamaHome()");
    }

    /**
     * Método que obtiene el valor de el atributo dto
     *
     * @return FormAdministrador : valor que tiene el atributo dto
     * 
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public FormAdministrador getDto() {
        return dto;
    }

    /**
     * Método que ingresa el valor de el atributo dto
     *
     * @param dto : valor que ingresa a el atributo dto
     *
     * @author José Antonio López Torres
     * @since 8/11/2016
     */
    public void setDto(FormAdministrador dto) {
        this.dto = dto;
    }

	/**
	 * Método que obtiene el valor de el atributo verMenu
	 *
	 * @return Date : valor que tiene el atributo verMenu
	 * 
	 * @author Pablo Zuñiga Mata
	 * @since 17/03/2017
	 */
	public boolean isVerMenu() {
		return verMenu;
	}

	/**
	 * Método que ingresa el valor de el atributo verMenu
	 *
	 * @param fechaAprobacionCasillaJunta : valor que ingresa a el atributo verMenu
	 *
	 * @author Pablo Zuñiga Mata
	 * @since 17/03/2017
	 */
	public void setVerMenu(boolean verMenu) {
		this.verMenu = verMenu;
	}
    
}
