/**
 * 
 */
package mx.ine.computosINE.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.ine.common.helper.HLPSecCasillasInterface;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.common.ws.geografico.dto.response.DTODistritosWS;
import mx.ine.common.ws.geografico.dto.response.DTOListaRegiduriasWS;
import mx.ine.computosINE.bsd.BSDCapturaVotosInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTOCandidato;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.collections.ComparatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Helper para el cálculo de las distribuciones
 *
 * @author Geovanny Romero
 * @since 05/05/2017
 */
@Component("hplDistribuciones")
@Scope ("prototype")
public class HLPDistribuciones implements Serializable {


	@Autowired
    @Qualifier("bsdCapturaVotos")
    private transient BSDCapturaVotosInterface bsdCapturaVotos;
	
	@Autowired
	@Qualifier("hlpSecCasillas")
	private transient HLPSecCasillasInterface hlpSecCasillas;
	
	public static final String CONST_URL_IMG_GENERICA_PP = "/img_generica.png";
	public static final String CONST_URL_IMG_GENERICA_CI = "/id_CI.png";
	
	private static final long serialVersionUID = -7402580072667800327L;
	
	/**
	 * Carga asociaciones de una elección
	 * @param bsd
	 * @param usuario
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<DTOCandidato> cargaAsociaciones(BSDDistribucionVotosInterface bsd, DTOUsuarioLogin usuario, Integer idTipoCandidatura) throws Exception {

		List<DTOCandidato> candidatos = null;
		DTOCandidato c = null;
		
		List<DTOCandidatoWS> asociacionesBase = bsd.consumeAsociacionesCoaliciones(usuario.getIdDetalleProceso(), usuario.getIdEstado(), usuario.getIdMunicipioSeleccionado(), idTipoCandidatura);

		if ( asociacionesBase != null && !asociacionesBase.isEmpty() ) {
			candidatos = new ArrayList<DTOCandidato>();
			for (DTOCandidatoWS it : asociacionesBase) {
				//Partidos y CI (Id:1, 4)
				if ( !it.getTipoAsociacion().equals(2)) {
					c = new DTOCandidato();
					c.setIdEstado(it.getIdEstado());
					c.setCveCandidato(getClaveCandidato(it.getIdAsociacion(), it.getTipoAsociacion()));
					c.setIdAsociacion(it.getIdAsociacion());
					c.setTipoAsociacion(it.getTipoAsociacion());
					c.setIdTipoCandidatura(idTipoCandidatura);
					c.setNombreAsociacion(it.getNombreAsociacion());
					c.setEmblema(getEmblemaValido(it.getEmblema(), it.getTipoAsociacion()));
					c.setSiglas(it.getSiglas());
					c.setOrden(it.getOrden());
					c.setVotos(null);
					c.setVotosAcumulados(new BigDecimal(0));
					c.setNombreCandidato(it.getNombreCandidato());
					candidatos.add(c);
				}
			}
		}
		return candidatos;
	}
	
	
	public String getClaveCandidato(Integer idAsociacion, Integer tipoAsociacion) {
		return idAsociacion.toString().concat("-").concat(tipoAsociacion.toString());
	}
	
	public String getEmblemaValido(String emblema, Integer tipoAsociacion) {
		
		if ( emblema == null || emblema.isEmpty() ) {
			//return CONST_URL_IMG_GENERICA_PP;
			return null;
		}
		if ( emblema != null && !emblema.isEmpty() && tipoAsociacion == 1 ) {
			return limpiaRutaEmblema(emblema);
		} else {
			if ( tipoAsociacion == 3 ) {
				return limpiaRutaEmblema(emblema);
			}
			else if ( tipoAsociacion == 4 ) {
				return CONST_URL_IMG_GENERICA_CI;
			}
		}
		return null;
	}

public String limpiaRutaEmblema(String re) {
		
		if (re == null || re.isEmpty()) {
			return null;
		}
		char caracter  = re.charAt(0);
		char caracter2 = re.charAt(1);

		if ( String.valueOf(caracter).equalsIgnoreCase("/") && String.valueOf(caracter2).equalsIgnoreCase("G")) {
			return re.substring(11);
		}
		return re;
	}

	public ArrayList<Object> construirConsultaDistribuciones(List<DTODistribucionTotales> totales, 
			List<DTODistribucionPartidosCI> partidosci, List<DTODistribucionCandidatos> candidatos, 
			DTODistribucionTotales cnrInput, DTODistribucionTotales nulosInput, List<DTOCandidato> candidatosWS)
	{
		ArrayList<Object> distribucion = new ArrayList<>();
		List<DTOAsociacion> partidosCompleto = new ArrayList<>();
		List<DTOAsociacion> coalicionesCompleto = new ArrayList<>();
		DTOActaCasillaVotos cnr = null;
		DTOActaCasillaVotos nulos = null;
		int totalVotos = 0;
		List<DTOAsociacion> distribucionPartidosCompleto = new ArrayList<>();
		List<DTOAsociacion> distribucionCandidato = new ArrayList<>();
		if(totales != null && totales.size()>0)
		{
			//Distribución Total de votos (PP, CI y Coaliciones)
			for (DTODistribucionTotales e : totales) {
				if(e.getId().getIdAsociacion()>0 && !e.getTipoAsociacion().equals(3))
				{
					DTOAsociacion asc = new DTOAsociacion();
					asc.setIdAsociacion(e.getId().getIdAsociacion());
					asc.setIdTipoCandidatura(e.getId().getIdTipoCandidatura());
					asc.setTipoAsociacion(e.getTipoAsociacion());
					asc.setIdCoalicion(e.getId().getIdCoalicion());
					asc.setOrden(e.getOrden());
					asc.setVotos(new BigDecimal(e.getVotos()));
					asc.setEmblema(e.getEmblema());
					for(DTOCandidato embcand : candidatosWS)
					{
						if(embcand.getIdAsociacion().equals(e.getId().getIdAsociacion()))
						{
							asc.setNombreCandidato(embcand.getNombreCandidato());
							break;
						}
					}
					partidosCompleto.add(asc);
					totalVotos += e.getVotos();
				}
				else if(e.getTipoAsociacion().equals(3))
				{
					DTOAsociacion asc = new DTOAsociacion();
					asc.setIdAsociacion(e.getId().getIdAsociacion());
					asc.setIdTipoCandidatura(e.getId().getIdTipoCandidatura());
					asc.setTipoAsociacion(e.getTipoAsociacion());
					asc.setIdCoalicion(e.getId().getIdCoalicion());
					asc.setOrden(e.getOrden());
					asc.setVotos(new BigDecimal(e.getVotos()));
					asc.setEmblema(e.getEmblema());
					coalicionesCompleto.add(asc);
					totalVotos += e.getVotos();
				}
			}
			coalicionesCompleto = ordenaCoaliciones(coalicionesCompleto);
			if(nulosInput!=null)
			{
				DTOActaCasillaVotos acv = new DTOActaCasillaVotos();
				DTOActaCasillaVotosPK acvpk = new DTOActaCasillaVotosPK();
				acvpk.setIdAsociacion(nulosInput.getId().getIdAsociacion());
				acvpk.setIdTipoCandidatura(nulosInput.getId().getIdTipoCandidatura());
				acvpk.setTipoAsociacion(nulosInput.getTipoAsociacion());
				acvpk.setIdCoalicion(nulosInput.getId().getIdCoalicion());
				acv.setId(acvpk);
				acv.setOrden(nulosInput.getOrden());
				acv.setVotos(nulosInput.getVotos());
				acv.setEmblema(nulosInput.getEmblema());
				nulos = acv;
				totalVotos += nulosInput.getVotos();
			}
			if(cnrInput!=null)
			{
				DTOActaCasillaVotos acv = new DTOActaCasillaVotos();
				DTOActaCasillaVotosPK acvpk = new DTOActaCasillaVotosPK();
				acvpk.setIdAsociacion(cnrInput.getId().getIdAsociacion());
				acvpk.setIdTipoCandidatura(cnrInput.getId().getIdTipoCandidatura());
				acvpk.setTipoAsociacion(cnrInput.getTipoAsociacion());
				acvpk.setIdCoalicion(cnrInput.getId().getIdCoalicion());
				acv.setId(acvpk);
				acv.setOrden(cnrInput.getOrden());
				acv.setVotos(cnrInput.getVotos());
				acv.setEmblema(cnrInput.getEmblema());
				cnr = acv;
				totalVotos += cnrInput.getVotos();
			}
			// Fin Distribución Total de votos (PP, CI y Coaliciones)
			
			//Distribucion PP_CI
			for (DTODistribucionPartidosCI e : partidosci)
			{
				DTOAsociacion asc = new DTOAsociacion();
				asc.setIdAsociacion(e.getId().getIdAsociacion());
				asc.setIdTipoCandidatura(e.getId().getIdTipoCandidatura());
				asc.setTipoAsociacion(e.getTipoAsociacion());
				asc.setOrden(e.getOrden());
				asc.setVotos(new BigDecimal(e.getVotos()));
				asc.setEmblema(e.getEmblema());
				for(DTOCandidato embcand : candidatosWS)
				{
					if(embcand.getIdAsociacion().equals(e.getId().getIdAsociacion()))
					{
						asc.setNombreCandidato(embcand.getNombreCandidato());
						break;
					}
				}
				distribucionPartidosCompleto.add(asc);
			}
			//Fin Distribucion PP_CI
			
			//Distribucion Candidatos
			for (DTODistribucionCandidatos e : candidatos)
			{
				DTOAsociacion asc = new DTOAsociacion();
				asc.setIdAsociacion(e.getId().getIdAsociacion());
				asc.setIdTipoCandidatura(e.getId().getIdTipoCandidatura());
				asc.setTipoAsociacion(e.getTipoAsociacion());
				asc.setIdCoalicion(e.getId().getIdCoalicion());
				asc.setOrden(e.getOrden());
				asc.setVotos(new BigDecimal(e.getVotos()));
				asc.setEmblema(e.getEmblema());
				for(DTOCandidato embcand : candidatosWS)
				{
					if(embcand.getIdAsociacion().equals(e.getId().getIdAsociacion()))
					{
						asc.setNombreCandidato(embcand.getNombreCandidato());
						break;
					}
				}
				distribucionCandidato.add(asc);
			}
			//Fin Distribucion Candidatos
			
			distribucion.add(partidosCompleto);
			distribucion.add(coalicionesCompleto);
			distribucion.add(distribucionPartidosCompleto);
			distribucion.add(distribucionCandidato);
			distribucion.add(cnr);
			distribucion.add(nulos);
			distribucion.add(totalVotos);

		}
		return distribucion;
	}
	
	public List<DTODistritosWS> getDistritosParaConsultarDistribucion(BSDDistribucionVotosInterface bsd, List<DTODistritosWS> distritos, DTOUsuarioLogin usuario, Integer cargo)
	{
		List<DTODistritosWS> distritosParaConsultarDistribucion = new ArrayList<>();
		if(distritos != null && distritos.size()>0)
		{
			for(DTODistritosWS dis : distritos)
			{
				DTODistribucionTotalesPK dpk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
						usuario.getIdDetalleProceso(), usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), 
								dis.getIdDistrito(), 
						-6, 
						-7, (Integer)(-9), (Integer)(-8), cargo, 0, 0);
				try {
					boolean distribucionCreada = false;
					distribucionCreada = bsd.buscarDistribucionCreada(dpk);
					if(distribucionCreada==true)
					{
						distritosParaConsultarDistribucion.add(dis);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return distritosParaConsultarDistribucion;
	}
	
	public List<DTOListaRegiduriasWS> getDemarcacionesParaConsultarDistribucion(BSDDistribucionVotosInterface bsd, List<DTOListaRegiduriasWS> regidurias, DTOUsuarioLogin usuario, Integer cargo)
	{
		List<DTOListaRegiduriasWS> regiduriasParaConsultarDistribucion = new ArrayList<>();
		if(regidurias != null && regidurias.size()>0)
		{
			for(DTOListaRegiduriasWS reg : regidurias)
			{
				DTODistribucionTotalesPK dpk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
						usuario.getIdDetalleProceso(), usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), 
								(Integer)(-5), 
						usuario.getIdMunicipioSeleccionado(), 
						reg.getIdRegiduria(), (Integer)(-9), (Integer)(-8), cargo, 0, 0);
				try {
					boolean distribucionCreada = false;
					distribucionCreada = bsd.buscarDistribucionCreada(dpk);
					if(distribucionCreada==true)
					{
						regiduriasParaConsultarDistribucion.add(reg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return regiduriasParaConsultarDistribucion;
	}
	
	public List<DTOListaRegiduriasWS> getDemarcacionesParaDistribucion(BSDDistribucionVotosInterface bsd, List<DTOListaRegiduriasWS> regidurias, DTOUsuarioLogin usuario, Integer cargo)
	{
		List<DTOListaRegiduriasWS> regiduriasListasParaDistribucion = new ArrayList<>();
		List<DTOListaRegiduriasWS> regiduriasParaDistribucion = new ArrayList<>();
		if(regidurias != null && regidurias.size()>0)
		{
			for(DTOListaRegiduriasWS regiduria: regidurias)
			{
				try {
					List<DTOCasillaWS> casillasAprobadas = hlpSecCasillas.obtenSecCasillasAprobadasPorRegiduria(
							usuario.getIdDetalleProceso(), usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), 
							usuario.getIdMunicipioSeleccionado(), regiduria.getIdRegiduria());
					DTOActaCasillaVotosPK pk = new DTOActaCasillaVotosPK();
					pk.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
					pk.setIdDetalleProceso(usuario.getIdDetalleProceso());
					pk.setIdEstado(usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado());
					pk.setIdDistrito(-5);
					pk.setIdMunicipio(usuario.getIdMunicipioSeleccionado());
					pk.setIdRegiduria(regiduria.getIdRegiduria());
					pk.setSeccion(-9);
					pk.setIdComunidad(-8);
					pk.setIdTipoCandidatura(cargo);
					if(casillasAprobadas != null && casillasAprobadas.size()>0)
					{
						List<DTOActaCasillaVotos> actasEnBD = null;
						try {
							actasEnBD = bsdCapturaVotos.getActasMunicipioEnRegiduriasParaDistribucion(pk);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(actasEnBD != null && actasEnBD.size()>0)
						{
							if(actasEnBD.size()>=casillasAprobadas.size())
							{
								regiduriasParaDistribucion.add(regiduria);
							}
						}
					}
					

				} catch (ClienteWebServiceException e) {
					e.printStackTrace();
				}
			}
		}
		if(regiduriasParaDistribucion.size()>0)
		{
			for(DTOListaRegiduriasWS reg : regiduriasParaDistribucion)
			{
				DTODistribucionTotalesPK dpk = new DTODistribucionTotalesPK(usuario.getIdProcesoElectoral(), 
						usuario.getIdDetalleProceso(), usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), (Integer)(-5), 
						usuario.getIdMunicipioSeleccionado(), 
						reg.getIdRegiduria(), (Integer)(-9), (Integer)(-8), cargo, 0, 0);
				try {
					boolean distribucionCreada = false;
					distribucionCreada = bsd.buscarDistribucionCreada(dpk);
					if(distribucionCreada==false)
					{
						regiduriasListasParaDistribucion.add(reg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return regiduriasListasParaDistribucion;
	}
	
	public List<DTODistritosWS> getDistritosParaDistribucion(BSDDistribucionVotosInterface bsd, List<DTODistritosWS> distritos, DTOUsuarioLogin usuario, Integer cargo)
	{
		List<DTODistritosWS> distritosListosParaDistribucion = new ArrayList<>();
		List<DTODistritosWS> distritosParaDistribucion = new ArrayList<>();
		if(distritos != null && distritos.size()>0)
		{
			for(DTODistritosWS distrito: distritos)
			{
				try
				{
					List<DTOCasillaWS> casillasAprobadas = hlpSecCasillas.casillasAprobadasPorDistritoLocal(
							usuario.getIdDetalleProceso(), usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), distrito.getIdDistrito());
					DTODistribucionCandParcialPK pk = new DTODistribucionCandParcialPK();
					pk.setIdProcesoElectoral(usuario.getIdProcesoElectoral());
					pk.setIdDetalleProceso(usuario.getIdDetalleProceso());
					pk.setIdEstado(usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado());
					pk.setIdDistrito(distrito.getIdDistrito());
					pk.setIdTipoCandidatura(cargo);
					
					if(casillasAprobadas != null && casillasAprobadas.size()>0)
					{
						List<DTODistribucionCandParcial> actasEnBD = null;
						Integer actasParcialesEnBD = 0;
						try {
							actasParcialesEnBD = bsdCapturaVotos.getActasParcialesCapturadasEnDistrito(pk);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(actasParcialesEnBD>=casillasAprobadas.size())
						{
							distritosParaDistribucion.add(distrito);
						}
					}
				} catch (ClienteWebServiceException e) {
					e.printStackTrace();
				}
			}
		}
		if(distritosParaDistribucion.size()>0)
		{
			for(DTODistritosWS dist : distritosParaDistribucion)
			{
				DTODistribucionCandidatosPK dpk = new DTODistribucionCandidatosPK(usuario.getIdProcesoElectoral(), 
						usuario.getIdDetalleProceso(), usuario.getIdEstado()!=0?usuario.getIdEstado():usuario.getIdEstadoSeleccionado(), 
								dist.getIdDistrito(), -6, -7, -9, -8, cargo, 
						1, 1);
				boolean distribucionCreada = false;
				try {
					distribucionCreada = bsd.buscarDistribucionParcialCreada(dpk);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(distribucionCreada==false)
				{
					distritosListosParaDistribucion.add(dist);
				}
			}
		}
		return distritosListosParaDistribucion;
	}

	public ArrayList<Object> generarDistribuciones(List<DTOActaCasillaVotos> actaVotos, List<DTOCSubcoaliciones> subcoaliciones, List<DTOCDetalleSubcoaliciones> detallesubcoaliciones, List<DTOCandidato> candidatosWS)
	{
		ArrayList<Object> distribucion = new ArrayList<>();
		List<DTOActaCasillaVotos> partidos = new ArrayList<>();
		List<DTOAsociacion> partidosCompleto = new ArrayList<>();
		List<DTOActaCasillaVotos> coaliciones = new ArrayList<>();
		List<DTOAsociacion> coalicionesCompleto = new ArrayList<>();
		List<DTOAsociacion> coalicionesCompletoOrdenado = new ArrayList<>();
		DTOActaCasillaVotos cnr = null;
		DTOActaCasillaVotos nulos = null;
		int totalVotos = 0;
		List<DTOActaCasillaVotos> distribucionPartidos = new ArrayList<>();
		List<DTOAsociacion> distribucionPartidosCompleto = new ArrayList<>();
		List<DTOAsociacion> distribucionCandidato = new ArrayList<>();
		List<DTOActaCasillaVotos> actaVotos2 = actaVotos;
		
		if(actaVotos!= null && actaVotos.size()>0)
		{
			//Distribución Total de votos (PP, CI y Coaliciones)
			for (DTOActaCasillaVotos e : actaVotos) {
				//System.out.println(e.getId().getIdAsociacion() + " -> Votos: " + e.getVotos());
				//System.out.println(bsdAsociacionesParticipantes.getInfoFromAsociacion((Integer)3, e.getId().getIdAsociacion(), (Integer)18).getNombreAsociacion());
				if(e.getId().getIdAsociacion()>0 && !e.getId().getTipoAsociacion().equals(3))
				{
					DTOAsociacion asc = new DTOAsociacion();
					asc.setIdAsociacion(e.getId().getIdAsociacion());
					asc.setIdTipoCandidatura(e.getId().getIdTipoCandidatura());
					asc.setTipoAsociacion(e.getId().getTipoAsociacion());
					asc.setIdCoalicion(e.getId().getIdCoalicion());
					asc.setOrden(e.getOrden());
					asc.setVotos(new BigDecimal(e.getVotos()));
					for(DTOCandidato embcand : candidatosWS)
					{
						if(embcand.getIdAsociacion().equals(e.getId().getIdAsociacion()))
						{
							asc.setEmblema(embcand.getEmblema());
							asc.setNombreCandidato(embcand.getNombreCandidato());
							break;
						}
					}
					partidos.add(e);
					partidosCompleto.add(asc);
					try {
						distribucionPartidos.add((DTOActaCasillaVotos)e.clone());
						distribucionPartidosCompleto.add((DTOAsociacion)asc.clone());
						distribucionCandidato.add((DTOAsociacion)asc.clone());
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
					totalVotos += e.getVotos();
				}
				else if(e.getId().getIdAsociacion()<0 && (""+e.getId().getIdAsociacion()).equals(Utilidades.mensajeProperties("constante_idAsociacion_votosNulos")))
				{
					nulos = e;
					totalVotos += e.getVotos();
				}
				else if(e.getId().getIdAsociacion()<0 && (""+e.getId().getIdAsociacion()).equals(Utilidades.mensajeProperties("constante_idAsociacion_canNoRegistrado")))
				{
					cnr = e;
					totalVotos += e.getVotos();
				}
				else if(e.getId().getTipoAsociacion().equals(3))
				{
					DTOAsociacion asc = new DTOAsociacion();
					asc.setIdAsociacion(e.getId().getIdAsociacion());
					asc.setIdTipoCandidatura(e.getId().getIdTipoCandidatura());
					asc.setTipoAsociacion(e.getId().getTipoAsociacion());
					asc.setIdCoalicion(e.getId().getIdCoalicion());
					asc.setOrden(e.getOrden());
					asc.setVotos(new BigDecimal(e.getVotos()));
					for(DTOCandidato embcand : candidatosWS)
					{
						if(embcand.getIdAsociacion().equals(e.getId().getIdAsociacion()))
						{
							asc.setEmblema(embcand.getEmblema());
							break;
						}
					}
					List<DTOSubcoalicion> subc = new ArrayList<>();
					try {
						subc = bsdCapturaVotos.recuperaSubcoaliciones(coalicionesCompleto);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(DTOSubcoalicion ts : subc)
					{
						if(ts.getIdSubcoalicion().equals(asc.getIdAsociacion()))
						{
							asc.setEmblema(ts.getEmblema());
						}
					}
					coaliciones.add(e);
					coalicionesCompleto.add(asc);
					try {
						distribucionCandidato.add((DTOAsociacion)asc.clone());
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
					/*try {
						List<DTOSubcoalicion> subc = bsdCapturaVotos.recuperaSubcoaliciones(coalicionesCompleto);
						System.out.println("");
					} catch (Exception e1) {
						e1.printStackTrace();
					}*/
					totalVotos += e.getVotos();
				}
			}
			coalicionesCompleto = ordenaCoaliciones(coalicionesCompleto);

			System.out.println("PArtidos1: " + partidos.get(0).getVotos());
			// Fin Distribución Total de votos (PP, CI y Coaliciones)
			
			
			//Distribución PP - CI
			List<DTOActaCasillaVotos> distribucionPartidos_coaliciones = coaliciones;
			List<DTOActaCasillaVotos> distribucionPartidos_coaliciones_padres = new ArrayList<>();
			List<Map<String, Object>> padres = new ArrayList<>();
			for(DTOActaCasillaVotos coa : distribucionPartidos_coaliciones)
			{
				if(coa.getId().getTipoAsociacion().equals(3))// && coa.getId().getIdCoalicion().equals(-10)
				{
					distribucionPartidos_coaliciones_padres.add(coa);
				}
			}
			try {
				//distribuyeVotos(distribucionPartidos, padres);
				distribucionPartidosCompleto = distribuyeVotosCoalicionEstatico(distribucionPartidosCompleto, distribucionPartidos_coaliciones_padres, padres, detallesubcoaliciones);
				distribucionCandidato = distribucionXCandidato(distribucionCandidato, detallesubcoaliciones);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//Fin Distribución PP - CI
			
			
			
			//Distribución Candidato
			/*distribucionPartidos = partidos;
			Integer padreIndex;
			DTOActaCasillaVotos padre;
			int indexPadre;
			for(DTOActaCasillaVotos p : coaliciones)
			{
				padreIndex = p.getId().getIdCoalicion();
				for(DTOActaCasillaVotos pp : distribucionPartidos)
				{
					if(pp.getId().getIdAsociacion().equals(padreIndex))
					{
						indexPadre = distribucionPartidos.indexOf(pp);
						padre = (DTOActaCasillaVotos) distribucionPartidos.get(indexPadre);
						padre.setVotos(padre.getVotos()+p.getVotos());
						distribucionPartidos.set(indexPadre, padre);
					}
				}
			}*/
			//Fin Distribución Candidato
			//distribucionCandidato = actaVotos;
			//Distribución por candidato
			
			//Fin Distribución por candidato
			
			System.out.println("PArtidos4: " + partidos.get(0).getVotos());
			System.out.println("Dist.Partidos4: " + distribucionPartidos.get(0).getVotos());
			
			distribucion.add(partidos);
			distribucion.add(coaliciones);
			distribucion.add(cnr);
			distribucion.add(nulos);
			distribucion.add(totalVotos);
			distribucion.add(distribucionPartidos);
			distribucion.add(partidosCompleto);
			distribucion.add(coalicionesCompleto);
			distribucion.add(distribucionPartidosCompleto);
			distribucion.add(distribucionCandidato);
		}
		return distribucion;
	}
	
	public List<DTOAsociacion> ordenaCoaliciones(List<DTOAsociacion> coaliciones)
	{
		List<DTOAsociacion> listaPadres  = new ArrayList<>();
		List<DTOAsociacion> listaOrdenada = new ArrayList<>();
		for(DTOAsociacion da : coaliciones)
		{
			if(da.getIdAsociacion()>0)
			{
				listaPadres.add(da);
			}
		}
		for(DTOAsociacion padre : listaPadres)
		{
			if(!listaOrdenada.contains(padre))
			{
				listaOrdenada.add(padre);
			}
			for(DTOAsociacion asocComp : coaliciones)
			{
				if(asocComp.getIdCoalicion().equals(padre.getIdAsociacion()) && !listaOrdenada.contains(asocComp))
				{
					listaOrdenada.add(asocComp);
				}
			}
		}
		return listaOrdenada;
	}
	
	public List<DTOActaCasillaVotos> distribuyeVotos(List<DTOActaCasillaVotos> distribucionPartidos, List<Map<String, Object>> padres) throws Exception {
		
		if(padres!= null && padres.size()>0)
		{
			
			for(Map<String, Object> padre : padres)
			{
				DTOActaCasillaVotos coalicion = (DTOActaCasillaVotos) padre.get("PADRE");
				List<Integer> hijosAsociaciones = (List<Integer>) padre.get("ASOCIACIONES"); 
				int numAsociacionesCoalicion = hijosAsociaciones.size();
				Integer votosCoalicion = coalicion.getVotos();
				int cociente = votosCoalicion/numAsociacionesCoalicion;
				int residuo = votosCoalicion%numAsociacionesCoalicion;
				List<DTOActaCasillaVotos> vasoc = new ArrayList<>();
				if(cociente>0)
				{
					for(DTOActaCasillaVotos asoc : distribucionPartidos)
					{
						for(Integer idAsoc : hijosAsociaciones)
						{
							if(idAsoc.equals(asoc.getId().getIdAsociacion()))
							{
								int ind = distribucionPartidos.indexOf(asoc);
								Integer votos = asoc.getVotos();
								vasoc.add(asoc);
								votos += cociente;
								asoc.setVotos(votos);
								distribucionPartidos.set(ind, asoc);
							}
						}
					}
				}
				if(residuo>0)
				{
					if(vasoc != null && vasoc.size()>0)
					{
						List<DTOActaCasillaVotos> vasocOrdenado = new ArrayList<>();
						DTOActaCasillaVotos temp = null;
						for(int i = 0; i < vasoc.size(); i++)
						{
							for(int j = 0; j < vasoc.size(); j++)
							{
								temp = vasoc.get(i).getVotos()>=vasoc.get(j).getVotos()?vasoc.get(i):vasoc.get(j);
							}
							vasocOrdenado.add(temp);
							vasoc.remove(temp);
						}
						for(int disv = 0; disv < residuo; residuo--)
						{
							Integer tvotos = vasocOrdenado.get(disv).getVotos();
							tvotos += 1;
							vasocOrdenado.get(disv).setVotos(tvotos);
							int indi = distribucionPartidos.indexOf(vasocOrdenado.get(disv));
							distribucionPartidos.set(indi, vasocOrdenado.get(disv));
						}
					}
				}
			}
		}
		return distribucionPartidos;
	}
	
	@SuppressWarnings("unchecked")
	public List<DTOAsociacion> distribuyeVotosCoalicionEstatico(List<DTOAsociacion> distribucionPartidos, 
			List<DTOActaCasillaVotos> coaliciones, List<Map<String, Object>> padres, 
			List<DTOCDetalleSubcoaliciones> detalles) throws Exception {
		
		List<DTOAsociacion> distribucionPartidosOriginal = new ArrayList<>();
		boolean hasBeenOrdered = false;
		
		if(distribucionPartidos != null && distribucionPartidos.size()>0)
		{
			for(DTOAsociacion dtoc : distribucionPartidos){
				distribucionPartidosOriginal.add((DTOAsociacion)dtoc.clone());
			}
		}
		
		if(distribucionPartidos != null && distribucionPartidos.size()>0)
		{
			Collections.sort(distribucionPartidos, ComparatorUtils.chainedComparator(Arrays.asList(new VotosComparator(), 
					new PrelacionComparator())));
			hasBeenOrdered = true;
		}
		
		Integer totalVotosaDistribuir = 0;
		
		for(DTOActaCasillaVotos coalicion : coaliciones)
		{
			List<DTOAsociacion> asociacionesConformantes = new ArrayList<>();
			List<Integer> indicesAsociacionesConformantes = new ArrayList<>();
			totalVotosaDistribuir += coalicion.getVotos();
			for(DTOCDetalleSubcoaliciones detalle : detalles)
			{
				if(coalicion.getId().getIdCoalicion().equals(-10))
				{
					for(DTOCDetalleSubcoaliciones detTemp : detalles)
					{
						if(detTemp.getIdCoalicion().equals(coalicion.getId().getIdAsociacion()))
						{
							DTOAsociacion asociacionRelacionada = new DTOAsociacion();
							asociacionRelacionada.setIdAsociacion(detTemp.getIdAsociacion());
							if(!indicesAsociacionesConformantes.contains(detTemp.getIdAsociacion()))
							{
								indicesAsociacionesConformantes.add(detTemp.getIdAsociacion());
								asociacionesConformantes.add(asociacionRelacionada);
							}
						}
					}
				}
				else
				{
					if(coalicion.getId().getIdAsociacion().equals(detalle.getIdSubcoalicion()))
					{
						DTOAsociacion asociacionRelacionada = new DTOAsociacion();
						asociacionRelacionada.setIdAsociacion(detalle.getIdAsociacion());
						asociacionesConformantes.add(asociacionRelacionada);
					}
				}
				
			}
			int numAsociacionesCoalicion = asociacionesConformantes.size();
			Integer votosCoalicion = coalicion.getVotos();
			int cociente = numAsociacionesCoalicion>0?votosCoalicion/numAsociacionesCoalicion: 0;
			int residuo = numAsociacionesCoalicion>0?votosCoalicion%numAsociacionesCoalicion:0;
			
			List<DTOAsociacion> copiaAsociacionesConformantes = new ArrayList<>();
			List<DTOAsociacion> copiaPartidos = new ArrayList<>();
			for(DTOAsociacion cc : asociacionesConformantes)
			{
				copiaAsociacionesConformantes.add((DTOAsociacion)cc.clone());
			}
			for(DTOAsociacion aso : copiaAsociacionesConformantes)
			{
				for(DTOAsociacion partido : distribucionPartidos)
				{
					if(aso.getIdAsociacion().equals(partido.getIdAsociacion()))
					{
						copiaPartidos.add((DTOAsociacion) partido.clone());
					}
				}
			}
			
			
			if(cociente > 0)
			{
				for(DTOAsociacion asociacion : asociacionesConformantes)//DTOAsociacion partido : distribucionPartidos
				{
					for(DTOAsociacion partido : distribucionPartidos)//DTOAsociacion asociacion : asociacionesConformantes
					{
						if(partido.getIdAsociacion().equals(asociacion.getIdAsociacion()))
						{
							//System.out.println("VOTOSANTERIOR: " + partido.getIdAsociacion() + "--> " + partido.getVotos().intValueExact());
							int ind = distribucionPartidos.indexOf(partido);
							Integer votos = partido.getVotos().intValueExact();
							votos += cociente;
							partido.setVotos(new BigDecimal(votos));
							distribucionPartidos.set(ind, partido);
							System.out.println("VOTOSDISTRIBUIDOS: " + partido.getIdAsociacion() + "--> " + votos);
						}
					}
				}
				//System.out.println("----");
			}
			
			if(residuo > 0)
			{

				//for(DTOAsociacion cop : copiaPartidos)
				for(DTOAsociacion partido : distribucionPartidos)
				{
					//for(DTOAsociacion partido : distribucionPartidos)
					for(DTOAsociacion cop : copiaPartidos)
					{
						if(cop.getIdAsociacion().equals(partido.getIdAsociacion()))
						{
							if(residuo>0)
							{
								Integer tvotos = partido.getVotos().intValueExact();
								tvotos += 1;
								partido.setVotos(new BigDecimal(tvotos));
								int indi = distribucionPartidos.indexOf(partido);
								distribucionPartidos.set(indi, partido);
								System.out.println("VOTOSDISTRIBUIDOS: " + partido.getIdAsociacion() + "--> " + tvotos);
								residuo--;
							}
						}
					}
				}
			}
		}
		
		
		//ORDENAMIENTO
		List<DTOAsociacion> finalDistribucionPP = new ArrayList<>();
		for(DTOAsociacion oldA : distribucionPartidosOriginal)
		{
			for(DTOAsociacion newA : distribucionPartidos)
			{
				if(oldA.getIdAsociacion().equals(newA.getIdAsociacion()))
				{
					finalDistribucionPP.add(newA);
				}
			}
		}
		//distribucionPartidos = null;
		//distribucionPartidos.addAll(finalDistribucionPP);
		
		return finalDistribucionPP;
	}
	
	@SuppressWarnings("unchecked")
	public List<DTOAsociacion> distribuyeVotosCoalicion(List<DTOAsociacion> distribucionPartidos, List<DTOActaCasillaVotos> coaliciones, List<Map<String, Object>> padres, List<DTOCDetalleSubcoaliciones> detalles) throws Exception {
			
		
		Integer totalVotosaDistribuir = 0;
		for(DTOActaCasillaVotos coalicion : coaliciones)
		{
			List<DTOAsociacion> asociacionesConformantes = new ArrayList<>();
			List<Integer> indicesAsociacionesConformantes = new ArrayList<>();
			totalVotosaDistribuir += coalicion.getVotos();
			for(DTOCDetalleSubcoaliciones detalle : detalles)
			{
				if(coalicion.getId().getIdCoalicion().equals(-10))
				{
					for(DTOCDetalleSubcoaliciones detTemp : detalles)
					{
						if(detTemp.getIdCoalicion().equals(coalicion.getId().getIdAsociacion()))
						{
							DTOAsociacion asociacionRelacionada = new DTOAsociacion();
							asociacionRelacionada.setIdAsociacion(detTemp.getIdAsociacion());
							if(!indicesAsociacionesConformantes.contains(detTemp.getIdAsociacion()))
							{
								indicesAsociacionesConformantes.add(detTemp.getIdAsociacion());
								asociacionesConformantes.add(asociacionRelacionada);
							}
						}
					}
				}
				else
				{
					if(coalicion.getId().getIdAsociacion().equals(detalle.getIdSubcoalicion()))
					{
						DTOAsociacion asociacionRelacionada = new DTOAsociacion();
						asociacionRelacionada.setIdAsociacion(detalle.getIdAsociacion());
						asociacionesConformantes.add(asociacionRelacionada);
					}
				}
				
			}
			int numAsociacionesCoalicion = asociacionesConformantes.size();
			Integer votosCoalicion = coalicion.getVotos();
			int cociente = numAsociacionesCoalicion>0?votosCoalicion/numAsociacionesCoalicion: 0;
			int residuo = numAsociacionesCoalicion>0?votosCoalicion%numAsociacionesCoalicion:0;
			if(cociente > 0)
			{
				for(DTOAsociacion asociacion : asociacionesConformantes)//DTOAsociacion partido : distribucionPartidos
				{
					for(DTOAsociacion partido : distribucionPartidos)//DTOAsociacion asociacion : asociacionesConformantes
					{
						if(partido.getIdAsociacion().equals(asociacion.getIdAsociacion()))
						{
							//System.out.println("VOTOSANTERIOR: " + partido.getIdAsociacion() + "--> " + partido.getVotos().intValueExact());
							int ind = distribucionPartidos.indexOf(partido);
							Integer votos = partido.getVotos().intValueExact();
							votos += cociente;
							partido.setVotos(new BigDecimal(votos));
							distribucionPartidos.set(ind, partido);
							System.out.println("VOTOSDISTRIBUIDOS: " + partido.getIdAsociacion() + "--> " + votos);
						}
					}
				}
				//System.out.println("----");
			}
			if(residuo > 0)
			{
				List<DTOAsociacion> copiaAsociacionesConformantes = new ArrayList<>();
				List<DTOAsociacion> copiaPartidos = new ArrayList<>();
				for(DTOAsociacion cc : asociacionesConformantes)
				{
					copiaAsociacionesConformantes.add((DTOAsociacion)cc.clone());
				}
				for(DTOAsociacion aso : copiaAsociacionesConformantes)
				{
					for(DTOAsociacion partido : distribucionPartidos)
					{
						if(aso.getIdAsociacion().equals(partido.getIdAsociacion()))
						{
							copiaPartidos.add((DTOAsociacion) partido.clone());
						}
					}
				}

				Collections.sort(copiaPartidos, ComparatorUtils.chainedComparator(Arrays.asList(new VotosComparator(), 
						new PrelacionComparator())));
				

				for(DTOAsociacion cop : copiaPartidos)
				{
					for(DTOAsociacion partido : distribucionPartidos)
					{
						if(cop.getIdAsociacion().equals(partido.getIdAsociacion()))
						{
							if(residuo>0)
							{
								Integer tvotos = partido.getVotos().intValueExact();
								tvotos += 1;
								partido.setVotos(new BigDecimal(tvotos));
								int indi = distribucionPartidos.indexOf(partido);
								distribucionPartidos.set(indi, partido);
								System.out.println("VOTOSDISTRIBUIDOS: " + partido.getIdAsociacion() + "--> " + tvotos);
								residuo--;
							}
						}
					}
				}
				

			}
		}
		return distribucionPartidos;
	}
	
	public List<DTOAsociacion> distribucionXCandidato(List<DTOAsociacion> votosADistribuir, List<DTOCDetalleSubcoaliciones> detalles) throws Exception {
		List<DTOAsociacion> partidos = new ArrayList<>();
		List<DTOAsociacion> coaliciones = new ArrayList<>();
		List<DTOAsociacion> coalicionesPadre = new ArrayList<>();
		for(DTOAsociacion asociacion : votosADistribuir)
		{
			if(asociacion.getTipoAsociacion().equals(3))
			{
				coaliciones.add(asociacion);
			}
			else
			{
				partidos.add(asociacion);
			}
		}
		//for(DTOAsociacion coalicion : coaliciones)
		List<DTOAsociacion> copiaCoaliciones = new ArrayList<>();
		for(DTOAsociacion copc : coaliciones)
		{
			copiaCoaliciones.add((DTOAsociacion)copc.clone());
		}
		//for(int i = 0; i<coaliciones.size(); i++)
		for(DTOAsociacion di : coaliciones)
		{
			if(di.getIdAsociacion()>0)
			{
				coalicionesPadre.add(di);
				copiaCoaliciones.remove(di);
			}

		}
		/*for(int i = 0; i<coaliciones.size(); i++)
		{
			if(coaliciones.get(i).getIdAsociacion()>0)
			{
				coalicionesPadre.add(coaliciones.get(i));
				copiaCoaliciones.remove(copiaCoaliciones.get(i));
			}

		}*/
		//coaliciones = copiaCoaliciones;
		/*for(int i = 0; i<coaliciones.size(); i++)
		{
			if(coaliciones.get(0).getIdAsociacion()>0)
			{
				coalicionesPadre.add(coaliciones.get(i));
				coaliciones.remove(coaliciones.get(i));
			}
			else
			{
				if(coaliciones.get(i).getIdAsociacion()>0)
				{
					coalicionesPadre.add(coaliciones.get(i));
					coaliciones.remove(coaliciones.get(i));
				}
			}
		}*/
		for(DTOAsociacion coalicionPadre : coalicionesPadre)
		{
			//int votosParaElPadre = 0;
			for(DTOAsociacion coalicion : copiaCoaliciones)
			{
				for(DTOCDetalleSubcoaliciones detalle : detalles)
				{
					if(coalicion.getIdAsociacion().equals(detalle.getIdSubcoalicion()) && 
							detalle.getIdCoalicion().equals(coalicionPadre.getIdAsociacion()) 
							&& !coalicion.getIdAsociacion().equals(coalicionPadre.getIdAsociacion()))
					{
						int votosParaElPadre = 0;
						int oldVotos = 0;
						int nuevosVotos = 0;
						votosParaElPadre = coalicion.getVotos().intValueExact();
						oldVotos = coalicionPadre.getVotos().intValueExact();
						nuevosVotos = oldVotos + votosParaElPadre;
						coalicionPadre.setVotos(new BigDecimal(nuevosVotos));
						break;
						//coaliciones.remove(coalicion);
					}
				}
				//int votosAgregar = coalicionPadre.getVotos().intValueExact()+votosParaElPadre;
				//coalicionPadre.setVotos(new BigDecimal(votosAgregar));
			}
			//coalicionPadre.setVotos(new BigDecimal(votosParaElPadre));
		}
		for(DTOAsociacion coalicionPadre : coalicionesPadre)
		{
			List<DTOAsociacion> asociacionesConformantes = new ArrayList<>();
			List<Integer> indicesAsociacionesConformantes = new ArrayList<>();
			for(DTOCDetalleSubcoaliciones detTemp : detalles)
			{
				if(detTemp.getIdCoalicion().equals(coalicionPadre.getIdAsociacion()))
				{
					DTOAsociacion asociacionRelacionada = new DTOAsociacion();
					asociacionRelacionada.setIdAsociacion(detTemp.getIdAsociacion());
					if(!indicesAsociacionesConformantes.contains(detTemp.getIdAsociacion()))
					{
						indicesAsociacionesConformantes.add(detTemp.getIdAsociacion());
						asociacionesConformantes.add(asociacionRelacionada);
					}
				}
			}
			boolean reemplazada = false;
			List<DTOAsociacion> asociacionesDistribucion = new ArrayList<>();
			//List<DTOAsociacion> distribucionTemp = new ArrayList<>();
			/*for(DTOAsociacion partido : partidos)
			{
				asociacionesDistribucion.add((DTOAsociacion)partido.clone());
			}*/
			for(DTOAsociacion partido : partidos)
			{
				DTOAsociacion tempAsoc = (DTOAsociacion)partido.clone();
				asociacionesDistribucion.add(tempAsoc);
				for(DTOAsociacion relacionada : asociacionesConformantes)
				{
					if(relacionada.getIdAsociacion().equals(partido.getIdAsociacion()))
					{
						if(!reemplazada)
						{
							int indexpartido = asociacionesDistribucion.indexOf(tempAsoc);
							int votosOld = coalicionPadre.getVotos().intValueExact();
							int votosAgregar = tempAsoc.getVotos().intValueExact();
							int nuevosVotos = votosOld + votosAgregar;
							coalicionPadre.setVotos(new BigDecimal(nuevosVotos));
							asociacionesDistribucion.set(indexpartido, coalicionPadre);
							reemplazada = true;
						}
						else
						{
							int votosOld = coalicionPadre.getVotos().intValueExact();
							int votosAgregar = tempAsoc.getVotos().intValueExact();
							int nuevosVotos = votosOld + votosAgregar;
							coalicionPadre.setVotos(new BigDecimal(nuevosVotos));
							asociacionesDistribucion.remove(tempAsoc);
						}
					}
				}
			}
			partidos = asociacionesDistribucion;
		}
		System.out.println("");
		return partidos;
	}
	
	public List<DTOCSubcoaliciones> getVotosCoalicionesHijas(BSDDistribucionVotosInterface bsd, List<DTOActaCasillaVotos> asociaciones) throws Exception {
		List<DTOCSubcoaliciones> subcoaliciones = bsd.getVotosCoalicionesHijas(asociaciones);
		return subcoaliciones;
	}
	
	public List<DTOCDetalleSubcoaliciones> getVotosHijas(BSDDistribucionVotosInterface bsd, List<DTOActaCasillaVotos> asociaciones, List<DTOCSubcoaliciones> coalicionesHijas) throws Exception
	{
		List<DTOCDetalleSubcoaliciones> detalle = bsd.getVotosHijas(asociaciones, coalicionesHijas);
		return detalle;
	}
	
	public List<DTODistribucionTotales> asociacionesToDistribucion(DTOActaCasillaVotosPK pk, List<DTOAsociacion>partidosCompletos, List<DTOAsociacion>coalicionesCompletas, DTOActaCasillaVotos cnr, DTOActaCasillaVotos nulos, String usuario) throws Exception
	{
		List<DTODistribucionTotales> totales = new ArrayList<>();
		int i=1;
		for(DTOAsociacion partido : partidosCompletos)
		{
			DTODistribucionTotales total = new DTODistribucionTotales();
			DTODistribucionTotalesPK totalpk = new DTODistribucionTotalesPK();
			totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
			totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
			totalpk.setIdEstado(pk.getIdEstado());
			totalpk.setIdDistrito(pk.getIdDistrito());
			totalpk.setIdMunicipio(pk.getIdMunicipio());
			totalpk.setIdRegiduria(pk.getIdRegiduria());
			totalpk.setSeccion(-9);
			totalpk.setIdComunidad(pk.getIdComunidad());
			totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
			totalpk.setIdAsociacion(partido.getIdAsociacion());
			totalpk.setIdCoalicion(partido.getIdCoalicion()!=null&&partido.getIdCoalicion()>0?partido.getIdCoalicion():-10);
			total.setId(totalpk);
			total.setTipoAsociacion(partido.getTipoAsociacion());
			total.setOrden(partido.getOrden()!=null&&partido.getOrden()>0?partido.getOrden():i);
			i++;
			total.setVotos(partido.getVotos().intValueExact());
			total.setEmblema(partido.getEmblema()!=null?partido.getEmblema():partido.getNombreCandidato()!=null?partido.getNombreCandidato():"default");
			total.setUsuario(usuario);
			total.setFechaHora(new Date());
			
			totales.add(total);
		}
		int j=1;
		for(DTOAsociacion coalicion : coalicionesCompletas)
		{
			DTODistribucionTotales total = new DTODistribucionTotales();
			DTODistribucionTotalesPK totalpk = new DTODistribucionTotalesPK();
			totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
			totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
			totalpk.setIdEstado(pk.getIdEstado());
			totalpk.setIdDistrito(pk.getIdDistrito());
			totalpk.setIdMunicipio(pk.getIdMunicipio());
			totalpk.setIdRegiduria(pk.getIdRegiduria());
			totalpk.setSeccion(-9);
			totalpk.setIdComunidad(pk.getIdComunidad());
			totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
			totalpk.setIdAsociacion(coalicion.getIdAsociacion());
			totalpk.setIdCoalicion(coalicion.getIdCoalicion()!=null&&coalicion.getIdCoalicion()>0?coalicion.getIdCoalicion():-10);
			total.setId(totalpk);
			total.setTipoAsociacion(coalicion.getTipoAsociacion());
			total.setOrden(coalicion.getOrden()!=null&&coalicion.getOrden()>0?coalicion.getOrden():j);
			j++;
			total.setVotos(coalicion.getVotos().intValueExact());
			total.setEmblema(coalicion.getEmblema()!=null?coalicion.getEmblema():"a");
			total.setUsuario(usuario);
			total.setFechaHora(new Date());
			
			totales.add(total);
		}
		DTODistribucionTotales total = new DTODistribucionTotales();
		DTODistribucionTotalesPK totalpk = new DTODistribucionTotalesPK();
		totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
		totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
		totalpk.setIdEstado(pk.getIdEstado());
		totalpk.setIdDistrito(pk.getIdDistrito());
		totalpk.setIdMunicipio(pk.getIdMunicipio());
		totalpk.setIdRegiduria(pk.getIdRegiduria());
		totalpk.setSeccion(-9);
		totalpk.setIdComunidad(pk.getIdComunidad());
		totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
		totalpk.setIdAsociacion(cnr.getId().getIdAsociacion());
		totalpk.setIdCoalicion(cnr.getId().getIdCoalicion()!=null?cnr.getId().getIdCoalicion():-10);
		total.setId(totalpk);
		total.setTipoAsociacion(cnr.getId().getTipoAsociacion());
		total.setOrden(1);
		total.setVotos(cnr.getVotos());
		total.setEmblema("CNREG");
		total.setUsuario(usuario);
		total.setFechaHora(new Date());
		
		totales.add(total);
		
		DTODistribucionTotales total2 = new DTODistribucionTotales();
		DTODistribucionTotalesPK totalpk2 = new DTODistribucionTotalesPK();
		totalpk2.setIdProcesoElectoral(pk.getIdProcesoElectoral());
		totalpk2.setIdDetalleProceso(pk.getIdDetalleProceso());
		totalpk2.setIdEstado(pk.getIdEstado());
		totalpk2.setIdDistrito(pk.getIdDistrito());
		totalpk2.setIdMunicipio(pk.getIdMunicipio());
		totalpk2.setIdRegiduria(pk.getIdRegiduria());
		totalpk2.setSeccion(-9);
		totalpk2.setIdComunidad(pk.getIdComunidad());
		totalpk2.setIdTipoCandidatura(pk.getIdTipoCandidatura());
		totalpk2.setIdAsociacion(nulos.getId().getIdAsociacion());
		totalpk2.setIdCoalicion(nulos.getId().getIdCoalicion()!=null?nulos.getId().getIdCoalicion():-10);
		total2.setId(totalpk2);
		total2.setTipoAsociacion(nulos.getId().getTipoAsociacion());
		total2.setOrden(2);
		total2.setVotos(nulos.getVotos());
		total2.setEmblema("NULOS");
		total2.setUsuario(usuario);
		total2.setFechaHora(new Date());
		
		totales.add(total2);
		
		return totales;
	}
	
	public List<DTODistribucionPartidosCI> asociacionesToDistribucionPPCI(DTOActaCasillaVotosPK pk, List<DTOAsociacion>partidosCompletos, DTOActaCasillaVotos cnr, DTOActaCasillaVotos nulos, String usuario) throws Exception
	{
		List<DTODistribucionPartidosCI> totales = new ArrayList<>();
		int i=1;
		for(DTOAsociacion partido : partidosCompletos)
		{
			DTODistribucionPartidosCI total = new DTODistribucionPartidosCI();
			DTODistribucionPartidosCIPK totalpk = new DTODistribucionPartidosCIPK();
			totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
			totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
			totalpk.setIdEstado(pk.getIdEstado());
			totalpk.setIdDistrito(pk.getIdDistrito());
			totalpk.setIdMunicipio(pk.getIdMunicipio());
			totalpk.setIdRegiduria(pk.getIdRegiduria());
			totalpk.setSeccion(-9);
			totalpk.setIdComunidad(pk.getIdComunidad());
			totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
			totalpk.setIdAsociacion(partido.getIdAsociacion());
			total.setId(totalpk);
			total.setTipoAsociacion(partido.getTipoAsociacion());
			total.setOrden(partido.getOrden()!=null&&partido.getOrden()>0?partido.getOrden():i);
			i++;
			total.setVotos(partido.getVotos().intValueExact());
			total.setEmblema(partido.getEmblema()!=null?partido.getEmblema():partido.getNombreCandidato()!=null?partido.getNombreCandidato():"default");
			//total.setEmblema(partido.getEmblema()!=null?partido.getEmblema():"CIL_"+partido.getIdAsociacion());
			total.setUsuario(usuario);
			total.setFechaHora(new Date());
			
			totales.add(total);
		}
		DTODistribucionPartidosCI total = new DTODistribucionPartidosCI();
		DTODistribucionPartidosCIPK totalpk = new DTODistribucionPartidosCIPK();
		totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
		totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
		totalpk.setIdEstado(pk.getIdEstado());
		totalpk.setIdDistrito(pk.getIdDistrito());
		totalpk.setIdMunicipio(pk.getIdMunicipio());
		totalpk.setIdRegiduria(pk.getIdRegiduria());
		totalpk.setSeccion(-9);
		totalpk.setIdComunidad(pk.getIdComunidad());
		totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
		totalpk.setIdAsociacion(cnr.getId().getIdAsociacion());
		total.setId(totalpk);
		total.setTipoAsociacion(cnr.getId().getTipoAsociacion());
		total.setOrden(1);
		total.setVotos(cnr.getVotos());
		total.setEmblema("CNREG");
		total.setUsuario(usuario);
		total.setFechaHora(new Date());
		
		totales.add(total);
		
		DTODistribucionPartidosCI total2 = new DTODistribucionPartidosCI();
		DTODistribucionPartidosCIPK totalpk2 = new DTODistribucionPartidosCIPK();
		totalpk2.setIdProcesoElectoral(pk.getIdProcesoElectoral());
		totalpk2.setIdDetalleProceso(pk.getIdDetalleProceso());
		totalpk2.setIdEstado(pk.getIdEstado());
		totalpk2.setIdDistrito(pk.getIdDistrito());
		totalpk2.setIdMunicipio(pk.getIdMunicipio());
		totalpk2.setIdRegiduria(pk.getIdRegiduria());
		totalpk2.setSeccion(-9);
		totalpk2.setIdComunidad(pk.getIdComunidad());
		totalpk2.setIdTipoCandidatura(pk.getIdTipoCandidatura());
		totalpk2.setIdAsociacion(nulos.getId().getIdAsociacion());
		total2.setId(totalpk2);
		total2.setTipoAsociacion(nulos.getId().getTipoAsociacion());
		total2.setOrden(2);
		total2.setVotos(nulos.getVotos());
		total2.setEmblema("NULOS");
		total2.setUsuario(usuario);
		total2.setFechaHora(new Date());
		
		totales.add(total2);
		
		return totales;
	}
	
	public List<DTODistribucionCandidatos> asociacionesToDistribucionCand(DTOActaCasillaVotosPK pk, List<DTOAsociacion>partidosCompletos, DTOActaCasillaVotos cnr, DTOActaCasillaVotos nulos, String usuario) throws Exception
	{
		List<DTODistribucionCandidatos> totales = new ArrayList<>();
		int i=1;
		for(DTOAsociacion partido : partidosCompletos)
		{
			DTODistribucionCandidatos total = new DTODistribucionCandidatos();
			DTODistribucionCandidatosPK totalpk = new DTODistribucionCandidatosPK();
			totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
			totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
			totalpk.setIdEstado(pk.getIdEstado());
			totalpk.setIdDistrito(pk.getIdDistrito());
			totalpk.setIdMunicipio(pk.getIdMunicipio());
			totalpk.setIdRegiduria(pk.getIdRegiduria());
			totalpk.setSeccion(-9);
			totalpk.setIdComunidad(pk.getIdComunidad());
			totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
			totalpk.setIdAsociacion(partido.getIdAsociacion());
			totalpk.setIdCoalicion(partido.getIdCoalicion()!=null&&partido.getIdCoalicion()>0?partido.getIdCoalicion():-10);
			total.setId(totalpk);
			total.setTipoAsociacion(partido.getTipoAsociacion());
			total.setOrden(partido.getOrden()!=null&&partido.getOrden()>0?partido.getOrden():i);
			i++;
			total.setVotos(partido.getVotos().intValueExact());
			total.setEmblema(partido.getEmblema()!=null?partido.getEmblema():partido.getNombreCandidato()!=null?partido.getNombreCandidato():"default");
			//total.setEmblema(partido.getEmblema()!=null?partido.getEmblema():"CIL_"+partido.getIdAsociacion());
			total.setUsuario(usuario);
			total.setFechaHora(new Date());
			
			totales.add(total);
		}
		DTODistribucionCandidatos total = new DTODistribucionCandidatos();
		DTODistribucionCandidatosPK totalpk = new DTODistribucionCandidatosPK();
		totalpk.setIdProcesoElectoral(pk.getIdProcesoElectoral());
		totalpk.setIdDetalleProceso(pk.getIdDetalleProceso());
		totalpk.setIdEstado(pk.getIdEstado());
		totalpk.setIdDistrito(pk.getIdDistrito());
		totalpk.setIdMunicipio(pk.getIdMunicipio());
		totalpk.setIdRegiduria(pk.getIdRegiduria());
		totalpk.setSeccion(-9);
		totalpk.setIdComunidad(pk.getIdComunidad());
		totalpk.setIdTipoCandidatura(pk.getIdTipoCandidatura());
		totalpk.setIdAsociacion(cnr.getId().getIdAsociacion());
		totalpk.setIdCoalicion(cnr.getId().getIdCoalicion()!=null?cnr.getId().getIdCoalicion():-10);
		total.setId(totalpk);
		total.setTipoAsociacion(cnr.getId().getTipoAsociacion());
		total.setOrden(1);
		total.setVotos(cnr.getVotos());
		total.setEmblema("CNREG");
		total.setUsuario(usuario);
		total.setFechaHora(new Date());
		
		totales.add(total);
		
		DTODistribucionCandidatos total2 = new DTODistribucionCandidatos();
		DTODistribucionCandidatosPK totalpk2 = new DTODistribucionCandidatosPK();
		totalpk2.setIdProcesoElectoral(pk.getIdProcesoElectoral());
		totalpk2.setIdDetalleProceso(pk.getIdDetalleProceso());
		totalpk2.setIdEstado(pk.getIdEstado());
		totalpk2.setIdDistrito(pk.getIdDistrito());
		totalpk2.setIdMunicipio(pk.getIdMunicipio());
		totalpk2.setIdRegiduria(pk.getIdRegiduria());
		totalpk2.setSeccion(-9);
		totalpk2.setIdComunidad(pk.getIdComunidad());
		totalpk2.setIdTipoCandidatura(pk.getIdTipoCandidatura());
		totalpk2.setIdAsociacion(nulos.getId().getIdAsociacion());
		totalpk2.setIdCoalicion(nulos.getId().getIdCoalicion()!=null?nulos.getId().getIdCoalicion():-10);
		total2.setId(totalpk2);
		total2.setTipoAsociacion(nulos.getId().getTipoAsociacion());
		total2.setOrden(2);
		total2.setVotos(nulos.getVotos());
		total2.setEmblema("NULOS");
		total2.setUsuario(usuario);
		total2.setFechaHora(new Date());
		
		totales.add(total2);
		
		return totales;
	}

	
	public class VotosComparator implements Comparator<DTOAsociacion> {
		 
	    @Override
	    public int compare(DTOAsociacion emp1, DTOAsociacion emp2) {
	        return emp2.getVotos().compareTo(emp1.getVotos());
	    }
	}
	
	public class PrelacionComparator implements Comparator<DTOAsociacion> {
		 
	    @Override
	    public int compare(DTOAsociacion emp1, DTOAsociacion emp2) {
	        return emp1.getOrden().compareTo(emp2.getOrden());
	    }
	}
	
}