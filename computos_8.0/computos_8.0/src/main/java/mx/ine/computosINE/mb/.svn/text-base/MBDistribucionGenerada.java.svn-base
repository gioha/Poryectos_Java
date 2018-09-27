package mx.ine.computosINE.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.helper.HLPGeograficosInterface;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.computosINE.bsd.BSDCapturaVotosInterface;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTOCandidato;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.helper.HLPDistribuciones;
import mx.ine.computosINE.helper.HLPGeneracionActasMensajes;
import mx.ine.computosINE.helper.HLPInfoGralComputos;
import mx.ine.computosINE.helper.HLPTipoCandidatura;
import mx.ine.computosINE.mb.MBGeneric.TipoMensaje;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.UtilUpload;
import mx.ine.computosINE.util.Utilidades;


/**
 * Clase que se encarga de consultar la distribución de votos
 * 
 * @author Geovanny Romero E.
 * @since 20/05/2017
 * @copyright INE
 */


//@Component("mbDist")
@Scope("session")
@Controller("mbDistGen")
public class MBDistribucionGenerada extends MBGeneric implements Serializable {

    /**
     * Objeto par la serialización de esta clase.
     */
	private static final long serialVersionUID = -220126795076155405L;

	

	/**
     * Objeto para el servicio de bitácora de mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(MBDistribucionGenerada.class);
    
    /*
     * Lista de distribuciones
     */
    private List<DTODistribucionCandidatos> ldto;
    
    /*
     * Lista de las demarcaciones (o regidurías) de una entidad
     */
    private List<DTOListaRegiduriasWS> demarcaciones;
    
    /*
     * Id de la demarcación (o regiduría) seleccionada
     */
    private Integer idDemarcacionSeleccionada;
    
    /*
     * Lista de los distrito de una entidad
     */
    private List<DTODistritosWS> distritos;
    
    /*
     * Id del distrito seleccionado
     */
    private Integer idDistritoSeleccionado;
    
    /*
     * Lista de asociaciones del acta
     */
    private List<DTOActaCasillaVotosPK> asociaciones;
    
    /*
     * Variable para habilitar/deshabilitar los paneles en pantalla
     */
    private boolean panelesVisible;
    
    /*
     * Variable para permitir el cálculo de la distribución (100% actas capturadas)
     */
    private boolean permiteConsultarDistribucion;
    
    /*
     * Variable para permitir guardar la distribución en BD
     */
    private boolean permiteGuardar;
    
    /*
     * Variable para permitir eliminar la distribución en BD
     */
    private boolean permiteEliminar;
    
    /*
     * Variable que tiene el id del cargo seleccionado
     */
    private Integer cargoSeleccionado;
    
    private HLPTipoCandidatura tipoCandidatura;
    
    /*
     * Variable que contiene los participantes de la elección (BD)
     */
    List<DTODistribucionTotales> actaVotos;
    
    /*
     *  Variable que contiene la lista de las asociaciones base de la elección (WS)
     */
    List<DTOCandidato> asociacionesBase;
    
    /*
     * Variable que contiene la lista de los Partidos Políticos de la elección    
     */
    List<DTOActaCasillaVotos> partidos;
    
    /*
     * Variable que contiene la lista de los Partidos Políticos y CI de la elección, con emblema
     */
    List<DTOAsociacion> partidosCompletos;
    
    /*
     * Variable que contiene la lista de las coaliciones de la elección    
     */
    List<DTOActaCasillaVotos> coaliciones;
    
    /*
     * Variable que contiene la lista de las coaliciones de la elección, con emblema
     */
    List<DTOAsociacion> coalicionesCompletas;
    
    /*
     * Variable que contiene el valor de CNR
     */
    DTOActaCasillaVotos cnr;
    DTODistribucionTotales cnrT;
    
    /*
     * Variable que contiene el valor de Votos nulos
     */
    DTOActaCasillaVotos nulos;
    DTODistribucionTotales nulosT;
    
   /*
    * Variable que contiene el total de votos de la elección
    */
    int totalVotos;
    
    /*
     * Variable que contiene el porcentaje de votación de CNR
     */
    String porcentajeCNR;
    
    /*
     * Variable que contiene el porcentaje de votos nulos
     */
    String porcentajeNulos;
    
    /*
     * Variable que contiene la lista de los partidos y CI producto de la Distribución por PP   
     */
    List<DTOActaCasillaVotos> distribucionPP;
    
    /*
     * Variable que contiene la lista de los partidos y CI producto de la Distribucion por PP, con emblema
     */
    List<DTOAsociacion> distribucionPPcompleta;
    
    /*
     * Variable que contiene la lista de los candidatos de una elección, con emblema
     */
    List<DTOAsociacion> distribucionCandidato;
    
    /**
	 * Clase Helper para obtener mensajes de validaciones
	 */
	private HLPGeneracionActasMensajes hlpMsg;
    
    
    DTOUsuarioLogin usuario;
    
    boolean calculoDistribucion = false;
    
    
    
    @Autowired
	@Qualifier("bsdDistribucionVotos")
	private transient BSDDistribucionVotosInterface bsdAsociacionesParticipantes;
    
    @Autowired
    @Qualifier("bsdCapturaVotos")
    private transient BSDCapturaVotosInterface bsdCapturaVotos;
    
    @Autowired
    @Qualifier("hplDistribuciones")
    private transient HLPDistribuciones hlp;
    
    @Autowired
    @Qualifier("hplInfoGralComputos")
    private transient HLPInfoGralComputos hlpInfoGral;
    
    @Autowired
    @Qualifier("bsdCargaInformacion")
    private transient BSDCargaInformacionInterface bsdCargaInformacion;
    
    @Autowired
    @Qualifier("hlpGeograficos")
    private HLPGeograficosInterface hlpGeo;
    
    @Autowired
	@Qualifier("rutaGluster")
	private transient String rutaGluster;
    
    private StreamedContent imagen = new DefaultStreamedContent();
    

    /*
     * Acciones a ejecutar al iniciar el módulo
     */
    public void init(Integer candidatura){
    	cargoSeleccionado = candidatura;
    	idDemarcacionSeleccionada = Constantes.SIN_REGIDURIA;
		idDistritoSeleccionado = Constantes.SIN_DISTRITO;
    	tipoCandidatura = new HLPTipoCandidatura();
    	panelesVisible = false;
    	permiteConsultarDistribucion = false;
    	permiteGuardar = false;
    	permiteEliminar = false;
    	usuario = obtenUsuario();
    	if(usuario.getIdMunicipioSeleccionado()==null)
    	{
    		usuario.setIdMunicipioSeleccionado(0);
    	}
    	if(usuario.getIdEstado()==0)
    	{
    		usuario.setIdEstado(usuario.getIdEstadoSeleccionado());
    	}
    	hlpMsg = new HLPGeneracionActasMensajes();
    	ldto = new ArrayList<>();
    	demarcaciones = new ArrayList<>();
    	distritos = new ArrayList<>();
    	try {
    		if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP))
    		{
    			demarcaciones = hlpGeo.regiduriasPorMunicipio(usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), usuario.getIdMunicipioSeleccionado());
    			demarcaciones = hlp.getDemarcacionesParaConsultarDistribucion(bsdAsociacionesParticipantes, demarcaciones, usuario, cargoSeleccionado);
    			if(demarcaciones != null && demarcaciones.size()<=0)
    			{
    				//agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("validacion_mensaje_generales_distribucion_no_regidurias"));
    				hlpMsg.mensajesValidacion(36);
    			}
    		}
    		else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP))
    		{
    			distritos = hlpGeo.obtenerCatalogoDistritos(usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), EnumAmbitoDetalleProceso.L);
    			distritos = hlp.getDistritosParaConsultarDistribucion(bsdAsociacionesParticipantes, distritos, usuario, cargoSeleccionado);
    			System.out.println("");
    		}
		} catch (ClienteWebServiceException e) {
			hlpMsg.mensajesValidacion(53);
			e.printStackTrace();
		}
    }
    
    public void cancelada()
    {
    	panelesVisible = false;
    	LOGGER.info("Entró a cancelada()");
    }
    
    @SuppressWarnings("unchecked")
	public void cargaDistribucion()
    {
    	LOGGER.info("Entró a cargaDistribucion()");
    	//init(cargoSeleccionado);
    	//permiteConsultarDistribucion = true;
    	//setTipoCandidatura(Constantes.REGIDURIA_MR);
//    	if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR) || 
//    			((cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP))) //&& actasCapturadasCompletas()) 
//    					|| 
//    			((cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP))) //&& actasCapturadasCompletas()//)
//    			)
//    	{
//    		panelesVisible = true;
//    	}
//    	else
//    	{
//    		panelesVisible = false;
//    		//hlpMsg = new HLPGeneracionActasMensajes();
//    		hlpMsg.mensajesValidacion(33);
//    	}
    	if(usuario.getRolUsuario().equals("COMPUTOS.ADMIN.EXT.OPLE.JL") || usuario.getRolUsuario().equals("COMPUTOS.ADMIN.PARAM.CAPTURA.OC") 
    			|| usuario.getRolUsuario().equals("COMPUTOS.ADMIN.CAPTURA.OC"))
		{
			permiteEliminar = true;
		}
    	actaVotos = null;

    	List<DTODistribucionPartidosCI> actaVotosPartidos = null;
    	List<DTODistribucionCandidatos> actaVotosCandidato = null;
    	cnrT = null;
    	nulosT = null;
    	partidosCompletos = new ArrayList<>();
    	coalicionesCompletas = new ArrayList<>();
    	totalVotos = 0;
    	distribucionPPcompleta = new ArrayList<>();
    	distribucionCandidato = new ArrayList<>();
		try {
			DTODistribucionCandidatosPK dtoTotalesPK = null;
			if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO))
			{
				dtoTotalesPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, usuario.getIdMunicipioSeleccionado(), -7, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_CAND_NO_REG, 1);
				DTODistribucionCandidatosPK dtoTotalesnulosPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, usuario.getIdMunicipioSeleccionado(), -7, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_VOTOS_NULOS, 1);
				actaVotos = bsdAsociacionesParticipantes.consultarDistribucionTotales(dtoTotalesPK);
				actaVotosPartidos = bsdAsociacionesParticipantes.consultarDistribucionPartidosCI(dtoTotalesPK);
				actaVotosCandidato = bsdAsociacionesParticipantes.consultarDistribucionFinal(dtoTotalesPK);
				cnrT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesPK);
				nulosT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesnulosPK);
			}
			else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP))
			{
				dtoTotalesPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, usuario.getIdMunicipioSeleccionado(), idDemarcacionSeleccionada, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_CAND_NO_REG, 1);
				DTODistribucionCandidatosPK dtoTotalesnulosPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, usuario.getIdMunicipioSeleccionado(), idDemarcacionSeleccionada, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_VOTOS_NULOS, 1);
				actaVotos = bsdAsociacionesParticipantes.consultarDistribucionTotales(dtoTotalesPK);
				actaVotosPartidos = bsdAsociacionesParticipantes.consultarDistribucionPartidosCI(dtoTotalesPK);
				actaVotosCandidato = bsdAsociacionesParticipantes.consultarDistribucionFinal(dtoTotalesPK);
				cnrT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesPK);
				nulosT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesnulosPK);
			}
			else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP))
			{
				dtoTotalesPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), idDistritoSeleccionado, -6, -7, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_CAND_NO_REG, 1);
				DTODistribucionCandidatosPK dtoTotalesnulosPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), idDistritoSeleccionado, -6, -7, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_VOTOS_NULOS, 1);
				actaVotos = bsdAsociacionesParticipantes.consultarDistribucionTotales(dtoTotalesPK);
				actaVotosPartidos = bsdAsociacionesParticipantes.consultarDistribucionPartidosCI(dtoTotalesPK);
				actaVotosCandidato = bsdAsociacionesParticipantes.consultarDistribucionFinal(dtoTotalesPK);
				cnrT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesPK);
				nulosT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesnulosPK);
				//dtoActaCasillaVotos = new DTOActaCasillaVotosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), idDistritoSeleccionado, -6, (Integer)(-7), (Integer)1, (Integer)(-8), (Integer)1, "B", (Integer)0, cargoSeleccionado, (Integer)1, (Integer)1);
				//actaVotos = bsdAsociacionesParticipantes.consultarAsociacionesParticipantesDistrito(dtoActaCasillaVotos);
			}
			else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR))
			{
				dtoTotalesPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, -6, -7, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_CAND_NO_REG, 1);
				DTODistribucionCandidatosPK dtoTotalesnulosPK = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, -6, -7, -9, -8, cargoSeleccionado, Constantes.TIPO_ASOCIACION_VOTOS_NULOS, 1);
				actaVotos = bsdAsociacionesParticipantes.consultarDistribucionTotales(dtoTotalesPK);
				actaVotosPartidos = bsdAsociacionesParticipantes.consultarDistribucionPartidosCI(dtoTotalesPK);
				actaVotosCandidato = bsdAsociacionesParticipantes.consultarDistribucionFinal(dtoTotalesPK);
				cnrT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesPK);
				nulosT = bsdAsociacionesParticipantes.getVotosNulosCNRDistribucionTotal(dtoTotalesnulosPK);
				//dtoActaCasillaVotos = new DTOActaCasillaVotosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), idDistritoSeleccionado, -6, (Integer)(-7), (Integer)1, (Integer)(-8), (Integer)1, "B", (Integer)0, cargoSeleccionado, (Integer)1, (Integer)1);
				//actaVotos = bsdAsociacionesParticipantes.consultarAsociacionesParticipantesDistrito(dtoActaCasillaVotos);
			}
			/*if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR))
			{
				actaVotos = bsdAsociacionesParticipantes.consultarAsociacionesParticipantesEntidad(dtoActaCasillaVotos);
			}
			else if(!cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) && !cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)){
				actaVotos = bsdAsociacionesParticipantes.consultarAsociacionesParticipantes(dtoActaCasillaVotos);
			}*/
			/*if(actaVotos!=null && actaVotos.size()>0)
			{
				permiteGuardar = true;
			}
			else
			{
				permiteGuardar = false;
			}*/
			//List<DTOCSubcoaliciones> coalicionesHijas = hlp.getVotosCoalicionesHijas(bsdAsociacionesParticipantes, actaVotos);
			//List<DTOCDetalleSubcoaliciones> detalleHijas = hlp.getVotosHijas(bsdAsociacionesParticipantes, actaVotos, coalicionesHijas);
			asociacionesBase = hlp.cargaAsociaciones(bsdAsociacionesParticipantes, usuario, cargoSeleccionado);
			//ArrayList<Object> distribucionGenerada = bsdAsociacionesParticipantes.generarDistribuciones(actaVotos, coalicionesHijas, detalleHijas, asociacionesBase);
			ArrayList<Object> distribucionGenerada = hlp.construirConsultaDistribuciones(actaVotos, actaVotosPartidos, actaVotosCandidato, cnrT, nulosT, asociacionesBase); 
			if(distribucionGenerada != null && distribucionGenerada.size()>1)
			{
				partidosCompletos = (List<DTOAsociacion>) distribucionGenerada.get(0);
				coalicionesCompletas = (List<DTOAsociacion>) distribucionGenerada.get(1);
				distribucionPPcompleta = (List<DTOAsociacion>) distribucionGenerada.get(2);
				distribucionCandidato = (List<DTOAsociacion>) distribucionGenerada.get(3);
				cnr = (DTOActaCasillaVotos) distribucionGenerada.get(4);
				nulos = (DTOActaCasillaVotos) distribucionGenerada.get(5);
				totalVotos = (int) distribucionGenerada.get(6);

				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				porcentajeCNR = df.format((float)(cnr.getVotos()*100)/totalVotos);
				porcentajeNulos = df.format((float)(nulos.getVotos()*100)/totalVotos);
				

			}
			
		} catch (Exception e) {
			hlpMsg.mensajesValidacion(52);
			e.printStackTrace();
		}
		
		if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR) || 
    			((cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP))) //&& actasCapturadasCompletas()) 
    					|| 
    			((cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP))) //&& actasCapturadasCompletas()//)
    			)
    	{
    		panelesVisible = true;
    	}
    	else
    	{
    		panelesVisible = false;
    		//hlpMsg = new HLPGeneracionActasMensajes();
    		hlpMsg.mensajesValidacion(33);
    	}
    	
    }
    
    public boolean distribucionRealizada()
    {
    	boolean tieneDistribucion = false;
		try {
			DTODistribucionTotalesPK pk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
	    			usuario.getIdDetalleProceso(), usuario.getIdEstadoSeleccionado(), (Integer)(-5), 
	    			usuario.getIdMunicipioSeleccionado(), (Integer)(-7), (Integer)(-9), (Integer)(-8), 
	    			cargoSeleccionado, 1, 1);
			if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR))
			{
				pk.setIdMunicipio(Constantes.SIN_MUNICIPIO);
			}
			tieneDistribucion = bsdAsociacionesParticipantes.buscarDistribucionCreada(pk);
			LOGGER.info("distribucionRealizzada(): " + tieneDistribucion);
			return tieneDistribucion;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("distribucionRealizzada() catch: " + tieneDistribucion);
			return tieneDistribucion;
		}
    }
    
    
    
    public String guardarDistribucion()
    {
    	LOGGER.info("Entró a guardarDistribucion()");
    	try {
    		DTOActaCasillaVotosPK dtoActaCasillaVotos = null;
    		if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO))
    		{
    			dtoActaCasillaVotos = new DTOActaCasillaVotosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), (Integer)(-5), usuario.getIdMunicipioSeleccionado(), (Integer)(-7), (Integer)1, (Integer)(-8), (Integer)1, "B", (Integer)0, cargoSeleccionado, (Integer)1, (Integer)1);
    		}
    		else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR))
    		{
    			dtoActaCasillaVotos = new DTOActaCasillaVotosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), (Integer)(-5), (Integer)(-6), (Integer)(-7), (Integer)(-9), (Integer)(-8), (Integer)1, "B", 0, cargoSeleccionado, 1 ,1);
    		}
    		else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP))
    		{
    			dtoActaCasillaVotos = new DTOActaCasillaVotosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), (Integer)(-5), usuario.getIdMunicipioSeleccionado(), idDemarcacionSeleccionada, (Integer)1, (Integer)(-8), (Integer)1, "B", (Integer)0, cargoSeleccionado, (Integer)1, (Integer)1);
    		}
    		else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP))
    		{
    			dtoActaCasillaVotos = new DTOActaCasillaVotosPK(usuario.getIdProcesoElectoral(), usuario.getIdDetalleProceso(), usuario.getIdEstado(), idDistritoSeleccionado, -6, -7, (Integer)1, (Integer)(-8), (Integer)1, "B", (Integer)0, cargoSeleccionado, (Integer)1, (Integer)1);
    		}
			List<DTODistribucionTotales> totales = hlp.asociacionesToDistribucion(dtoActaCasillaVotos, partidosCompletos, coalicionesCompletas, cnr, nulos, usuario.getUsuario());
    		List<DTODistribucionPartidosCI> distPP = hlp.asociacionesToDistribucionPPCI(dtoActaCasillaVotos, distribucionPPcompleta, cnr, nulos, usuario.getUsuario());
    		List<DTODistribucionCandidatos> distCand = hlp.asociacionesToDistribucionCand(dtoActaCasillaVotos, distribucionCandidato, cnr, nulos, usuario.getUsuario());
			bsdAsociacionesParticipantes.guardarDistribuciones(totales, distPP, distCand);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
    }
    
    public String eliminarDistribucion()
    {
    	LOGGER.info("Entró a eliminarDistribucion()");
    	Integer tipoActa = 1;
    	DTODistribucionTotalesPK pk = null;
    	if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO))
    	{
    		tipoActa = Constantes.TIPO_ACTA_FINAL;
    		pk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
        			usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, usuario.getIdMunicipioSeleccionado(), 
        			-7, -9, -8, cargoSeleccionado, 0, 0);
    	}
    	else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP))
    	{
    		tipoActa = Constantes.TIPO_ACTA_FINAL;
    		pk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
        			usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, usuario.getIdMunicipioSeleccionado(), 
        			idDemarcacionSeleccionada, -9, -8, cargoSeleccionado, 0, 0);
    	}
    	else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR) || cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP))
    	{
    		tipoActa = Constantes.TIPO_ACTA_FINAL;
    		pk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
        			usuario.getIdDetalleProceso(), usuario.getIdEstado(), idDistritoSeleccionado, -6, 
        			-7, -9, -8, cargoSeleccionado, 0, 0);
    	}
    	else if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_GOBERNADOR))
    	{
    		tipoActa = Constantes.TIPO_ACTA_FINAL;
    		pk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
        			usuario.getIdDetalleProceso(), usuario.getIdEstado(), -5, -6, 
        			-7, -9, -8, cargoSeleccionado, 0, 0);
    	}
    	if(!hlpInfoGral.existeActaTipoCandidaturaDistribucion(pk.getIdMunicipio(), pk.getIdDistrito(), pk.getIdRegiduria(), 
    			pk.getIdTipoCandidatura(), usuario, tipoActa))
    	{
    		try {
    			bsdAsociacionesParticipantes.eliminarDistribuciones(pk);
    			hlpMsg.mensajesValidacion(43);
    			return "eliminadas";
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else
    	{
    		hlpMsg.mensajesValidacion(42);
    	}
    	return "noEliminadas";
    }
    
    public boolean enviaMensaje()
    {
    	hlpMsg.mensajesValidacion(33);
    	return true;
    }
    
    
    
    public void cargaAsociacionesDyn(String cargo)
    {
    	LOGGER.info("Entró a cargaAsociacionesDyn()" + cargo);
    }


	public List<DTODistribucionCandidatos> getLdto() {
		return ldto;
	}


	public void setLdto(List<DTODistribucionCandidatos> ldto) {
		this.ldto = ldto;
	}

	
	public List<DTOListaRegiduriasWS> getDemarcaciones() {
		return demarcaciones;
	}

	public void setDemarcaciones(List<DTOListaRegiduriasWS> demarcaciones) {
		this.demarcaciones = demarcaciones;
	}

	
	public Integer getIdDemarcacionSeleccionada() {
		return idDemarcacionSeleccionada;
	}

	public void setIdDemarcacionSeleccionada(Integer idDemarcacionSeleccionada) {
		this.idDemarcacionSeleccionada = idDemarcacionSeleccionada;
	}

	
	public List<DTODistritosWS> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<DTODistritosWS> distritos) {
		this.distritos = distritos;
	}

	public Integer getIdDistritoSeleccionado() {
		return idDistritoSeleccionado;
	}

	public void setIdDistritoSeleccionado(Integer idDistritoSeleccionado) {
		this.idDistritoSeleccionado = idDistritoSeleccionado;
	}

	public boolean isPanelesVisible() {
		return panelesVisible;
	}

	public void setPanelesVisible(boolean panelesVisible) {
		this.panelesVisible = panelesVisible;
	}

	public boolean isPermiteConsultarDistribucion() {
		return permiteConsultarDistribucion;
	}

	public void setPermiteConsultarDistribucion(boolean permiteConsultarDistribucion) {
		if(cargoSeleccionado.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO) && permiteConsultarDistribucion==true)
		{
			//agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "mensajesInfo", Utilidades.mensajeProperties("" + "validacion_mensaje_generales_distribucion_realizada"));
			//hlpMsg.mensajesValidacion(35);
		}		
		this.permiteConsultarDistribucion = permiteConsultarDistribucion;
	}

	public boolean isPermiteGuardar() {
		return permiteGuardar;
	}

	public void setPermiteGuardar(boolean permiteGuardar) {
		this.permiteGuardar = permiteGuardar;
	}

	public boolean isPermiteEliminar() {
		return permiteEliminar;
	}

	public void setPermiteEliminar(boolean permiteEliminar) {
		this.permiteEliminar = permiteEliminar;
	}

	public List<DTOActaCasillaVotosPK> getAsociaciones() {
		return asociaciones;
	}


	public void setAsociaciones(List<DTOActaCasillaVotosPK> asociaciones) {
		this.asociaciones = asociaciones;
	}


	public Integer getCargoSeleccionado() {
		return cargoSeleccionado;
	}


	public void setCargoSeleccionado(Integer cargoSeleccionado) {
		this.cargoSeleccionado = cargoSeleccionado;
	}

	public List<DTODistribucionTotales> getActaVotos() {
		return actaVotos;
	}

	public void setActaVotos(List<DTODistribucionTotales> actaVotos) {
		this.actaVotos = actaVotos;
	}

	public List<DTOCandidato> getAsociacionesBase() {
		return asociacionesBase;
	}

	public void setAsociacionesBase(List<DTOCandidato> asociacionesBase) {
		this.asociacionesBase = asociacionesBase;
	}

	public List<DTOActaCasillaVotos> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<DTOActaCasillaVotos> partidos) {
		this.partidos = partidos;
	}

	public List<DTOAsociacion> getPartidosCompletos() {
		return partidosCompletos;
	}

	public void setPartidosCompletos(List<DTOAsociacion> partidosCompletos) {
		this.partidosCompletos = partidosCompletos;
	}

	public List<DTOActaCasillaVotos> getCoaliciones() {
		return coaliciones;
	}

	public void setCoaliciones(List<DTOActaCasillaVotos> coaliciones) {
		this.coaliciones = coaliciones;
	}

	public List<DTOAsociacion> getCoalicionesCompletas() {
		return coalicionesCompletas;
	}

	public void setCoalicionesCompletas(List<DTOAsociacion> coalicionesCompletas) {
		this.coalicionesCompletas = coalicionesCompletas;
	}

	public DTOActaCasillaVotos getCnr() {
		return cnr;
	}

	public void setCnr(DTOActaCasillaVotos cnr) {
		this.cnr = cnr;
	}

	public String getPorcentajeCNR() {
		return porcentajeCNR;
	}

	public void setPorcentajeCNR(String porcentajeCNR) {
		this.porcentajeCNR = porcentajeCNR;
	}

	public String getPorcentajeNulos() {
		return porcentajeNulos;
	}

	public void setPorcentajeNulos(String porcentajeNulos) {
		this.porcentajeNulos = porcentajeNulos;
	}

	public DTOActaCasillaVotos getNulos() {
		return nulos;
	}

	public void setNulos(DTOActaCasillaVotos nulos) {
		this.nulos = nulos;
	}

	public int getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(int totalVotos) {
		this.totalVotos = totalVotos;
	}

	public List<DTOActaCasillaVotos> getDistribucionPP() {
		return distribucionPP;
	}

	public void setDistribucionPP(List<DTOActaCasillaVotos> distribucionPP) {
		this.distribucionPP = distribucionPP;
	}
	
	public List<DTOAsociacion> getDistribucionPPcompleta() {
		return distribucionPPcompleta;
	}

	public void setDistribucionPPcompleta(List<DTOAsociacion> distribucionPPcompleta) {
		this.distribucionPPcompleta = distribucionPPcompleta;
	}

	public List<DTOAsociacion> getDistribucionCandidato() {
		return distribucionCandidato;
	}

	public void setDistribucionCandidato(List<DTOAsociacion> distribucionCandidato) {
		this.distribucionCandidato = distribucionCandidato;
	}
	
	public HLPTipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(String tipo) {
		HLPTipoCandidatura tipoCand = new HLPTipoCandidatura(tipo);
		this.tipoCandidatura = tipoCand;
	}
	
	public boolean isOPLE()
	{
		if(usuario.getRolUsuario().equals("COMPUTOS.ADMIN.EXT.OPLE.JL"))
		{
			return true;
		}
		return false;
	}

	/**
	 * @return the imagen
	 */
	public StreamedContent getImagen() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String rutaEmblema = externalContext.getRequestParameterMap().get("emblema");

		try{
			this.imagen = UtilUpload.getImagenStreamedContent(rutaEmblema);
		} catch (Exception e){
			LOGGER.error("Error obtenidno imag");
			LOGGER.error(e);
			return new DefaultStreamedContent();
		}
					
		return this.imagen;
	}
	
	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

    
}
