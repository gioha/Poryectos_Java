package mx.ine.computosINE.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidaturaWS;
import mx.ine.computosINE.bsd.BSDCargaInformacionInterface;
import mx.ine.computosINE.bsd.BSDDemarcacionesInterface;
import mx.ine.computosINE.bsd.BSDDistritosInterface;
import mx.ine.computosINE.bsd.BSDReportesInterface;
import mx.ine.computosINE.dto.DTOCEstatus;
import mx.ine.computosINE.dto.DTODistrito;
import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.DTORegiduria;
import mx.ine.computosINE.dto.DTOReportesParametros;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.util.ApplicationContextUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBReportesMenu extends MBGeneric implements Serializable {

	@Autowired
	@Qualifier("bsdReportes")
	protected transient BSDReportesInterface bsdReportes;

	@Autowired
	@Qualifier("bsdCargaInformacion")
	protected transient BSDCargaInformacionInterface bsdCargaInformacion;

	@Autowired
	@Qualifier("bsdDistritos")
	protected transient BSDDistritosInterface bsdDistritos;

	@Autowired
	@Qualifier("bsdDemarcaciones")
	protected transient BSDDemarcacionesInterface bsdDemarcaciones;

	@Autowired
	@Qualifier("urlRestWs")
	protected String urlRestWs;

	private static final long serialVersionUID = 070420171234L;
	private static final Log LOGGER = LogFactory.getLog(MBReportesMenu.class);
	protected static final Integer REPORTE_POR_CASILLA = 1;
	protected static final Integer REPORTE_POR_DEMARCACIONES = 4;
	private static final String DATE_TIME_FORMAT = "dd/MMMM/yyyy HH:mm";

	protected DTOFiltroReportes dtoFiltros;
	protected DTOReportesParametros dtoParametros;
	protected LazyDataModel<String[]> lazyData;
	protected Integer numReporte;
	protected List<DTOCEstatus> lstEstatusCasilla = new ArrayList<>();
	protected List<DTOCandidaturaWS> lstTipoCandidatura = new ArrayList<>();
	protected List<DTODistrito> lstDistritos = new ArrayList<>();
	protected List<DTORegiduria> lstDemarcaciones = new ArrayList<>();
	
	private static Locale MEX = new Locale("es", "MX");

	protected void limpiaFiltros() {
		limpiaLazyData();
		this.dtoParametros = new DTOReportesParametros();
		this.dtoFiltros = new DTOFiltroReportes();
	}

	protected void limpiaLazyData() {
		this.lazyData = null;
	}

	public void init() {
		limpiaFiltros();
		MBAdministradorSistema mbAdmin = (MBAdministradorSistema) ApplicationContextUtils
				.getApplicationContext().getBean("mbAdmin");
		DTOUsuarioLogin usuarioLogin = mbAdmin.getDto().getUsuario();

		this.dtoFiltros = new DTOFiltroReportes();
		this.dtoFiltros.setUsuario(usuarioLogin);
		this.dtoFiltros.setIdEstadoSeleccionado(usuarioLogin.getIdEstadoSeleccionado());
		this.dtoFiltros.setIdMunicipioElectoralSeleccionado(usuarioLogin.getIdMunicipioSeleccionado());
		this.dtoFiltros.setIdProceso(usuarioLogin.getIdProcesoElectoral());
		this.dtoFiltros.setIdDetalleProceso(usuarioLogin.getIdDetalleProceso());
	}

	/**
	 * Se inicializan parametros del encabezado para el reporte en HTML
	 */
	protected void inicializaParametrosEncabezado() {
		this.dtoParametros = new DTOReportesParametros();
		int idDetalleProceso = dtoFiltros.getIdDetalleProceso();

		// Se obtiene fecha
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat(DATE_TIME_FORMAT, MEX);
		this.dtoParametros.setFechaHora(formateador.format(fecha));

		// Se obtiene el nombre del estado
		localizaEstado(obtenLstEstadosPorDetalleProceso(idDetalleProceso));

		if (this.numReporte.equals(REPORTE_POR_CASILLA)) {
			// Se obtiene el id y nombre del municipio
			int idMunicipioElectoral = dtoFiltros.getIdMunicipioElectoralSeleccionado();
			this.dtoParametros.setIdMunicipioElectoral(idMunicipioElectoral);
			localizaMunicipio(obtenLstMunicipiosPorDetalleProceso(idDetalleProceso));
		}
	}

	/**
	 * Se consume parametrizacion/ws/estadosEleccion para obtener la lista de
	 * estados a partir del idDetalleProceso
	 * 
	 * @param idDetalleProceso
	 * @return
	 */
	protected List<DTODetalleEstadoProcesoWS> obtenLstEstadosPorDetalleProceso(
			Integer idDetalleProceso) {
		List<DTODetalleEstadoProcesoWS> estadoProcesoWS = null;
		try {
			estadoProcesoWS = bsdReportes.obtenEstadosPorProcesoDetalle(
					dtoFiltros.getUsuario().getIdSistema(), dtoFiltros
							.getUsuario().getIdProcesoElectoral(),
					idDetalleProceso);

		} catch (ClienteWebServiceException e) {
			LOGGER.error(
					"se genero un error en obtenLstEstadosPorDetalleProceso :",
					e);
		}
		return estadoProcesoWS;
	}

	/**
	 * Se consulta parametrizacion/ws/municipiosEleccion para obtener la lista
	 * de municipios a partir del idDetalleProceso
	 * 
	 * @param idDetalleProceso
	 * @return
	 */
	protected List<DTODetalleMunicipioProcesoWS> obtenLstMunicipiosPorDetalleProceso(
			Integer idDetalleProceso) {
		List<DTODetalleMunicipioProcesoWS> municipioProcesoWS = null;
		try {
			municipioProcesoWS = bsdReportes.obtenMunicipiosPorProcesoDetalle(
					dtoFiltros.getUsuario().getIdSistema(), dtoFiltros
							.getUsuario().getIdProcesoElectoral(),
					idDetalleProceso, dtoFiltros.getIdEstadoSeleccionado());

		} catch (ClienteWebServiceException e) {
			LOGGER.error(
					"Se genero un error en obtenLstMunicipiosPorDetalleProceso:",
					e);
		}
		return municipioProcesoWS;
	}

	private void localizaMunicipio(
			List<DTODetalleMunicipioProcesoWS> lstMunicipios) {
		for (DTODetalleMunicipioProcesoWS municipioProcesoWS : lstMunicipios) {
			if (this.dtoFiltros.getIdMunicipioElectoralSeleccionado().equals(
					municipioProcesoWS.getIdMunicipio())) {
				this.dtoParametros.setDescMunicipioElectoral(municipioProcesoWS
						.getNombreMunicipio().replace("�", "Ñ"));
			}
		}
	}
	
	private void localizaEstado(List<DTODetalleEstadoProcesoWS> lstEstados) {
		for (DTODetalleEstadoProcesoWS estadoProcesoWS : lstEstados) {
			if (this.dtoFiltros.getIdEstadoSeleccionado().equals(
					estadoProcesoWS.getIdEstado())) {
				this.dtoParametros.setDescEntidad(estadoProcesoWS
						.getNombreEstado());
			}
		}
	}

	public List<DTOCEstatus> getLstEstatusCasilla() {
		return lstEstatusCasilla;
	}

	public void setLstEstatusCasilla(List<DTOCEstatus> lstEstatusCasilla) {
		this.lstEstatusCasilla = lstEstatusCasilla;
	}

	public List<DTOCandidaturaWS> getLstTipoCandidatura() {
		return lstTipoCandidatura;
	}

	public void setLstTipoCandidatura(List<DTOCandidaturaWS> lstTipoCandidatura) {
		this.lstTipoCandidatura = lstTipoCandidatura;
	}

	public DTOReportesParametros getDtoParametros() {
		return dtoParametros;
	}

	public void setDtoParametros(DTOReportesParametros dtoParametros) {
		this.dtoParametros = dtoParametros;
	}

	public LazyDataModel<String[]> getLazyData() {
		return lazyData;
	}

	public void setLazyData(LazyDataModel<String[]> lazyData) {
		this.lazyData = lazyData;
	}

	public DTOFiltroReportes getDtoFiltros() {
		return dtoFiltros;
	}

	public void setDtoFiltros(DTOFiltroReportes dtoFiltros) {
		this.dtoFiltros = dtoFiltros;
	}

	public Integer getNumReporte() {
		return numReporte;
	}

	public void setNumReporte(Integer numReporte) {
		this.numReporte = numReporte;
	}

	public List<DTODistrito> getLstDistritos() {
		return lstDistritos;
	}

	public void setLstDistritos(List<DTODistrito> lstDistritos) {
		this.lstDistritos = lstDistritos;
	}

	public List<DTORegiduria> getLstDemarcaciones() {
		return lstDemarcaciones;
	}

	public void setLstDemarcaciones(List<DTORegiduria> lstDemarcaciones) {
		this.lstDemarcaciones = lstDemarcaciones;
	}

}
