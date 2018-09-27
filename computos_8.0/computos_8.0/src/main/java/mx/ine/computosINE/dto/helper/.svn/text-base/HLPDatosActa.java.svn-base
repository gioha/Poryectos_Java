package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.form.FormGeneracionActas;

/**
 * Clase auxiliar para el manejo de datos dentro de los modelos jasper para la
 * generacion de actas
 * 
 * @author INE
 * @since Abril, 2017
 * @copyright Instituto Nacional Electoral (INE). Todos los derechos reservados.
 */
public class HLPDatosActa implements Serializable {
	/**
	 * Numero de version que posee cada clase Serializable, el cual es usado en
	 * la deserializacion para verificar que la compatibilidad
	 */
	private static final long serialVersionUID = -7647199839414404238L;

	// Información a enviar para generar el Acta en formato pdf
	private FormGeneracionActas datosGenerales;

	// Identificador del id de estado
	private Integer idEstado;

	// Nombre de estado
	private String nomEstado;

	// Identificador del id de distrito
	private Integer idDistrito;

	// Nombre de distrito
	private String nomDistrito;

	// Identificador del id de municipio
	private Integer idMunicipio;

	// Nombre de municipio
	private String nomMunicipio;

	// Identificador del id de demarcación
	private Integer idDemarcacion;

	// Nombre de la demarcación
	private String nomDemarcacion;

	// Información que contiene la distribución total parcial de votos
	private List<DTODistribucionTotParcial> listTotalVotosParcial;

	// Información que contiene la distribucion total de votos;
	private List<DTODistribucionTotales> listTotalVotos;

	// Información que contiene la distribución parcial de votos por candidatos
	private List<DTODistribucionCandParcial> listDistParcialCandidatos;

	// Información que contiene la distribución final de candidatos
	private List<DTODistribucionCandidatos> listDistFinalCandidatos;

	// Información que contiene la distribución de partidos y candidatos
	// independientes
	private List<DTODistribucionPartidosCI> listDistribucionPPCI;

	// Total de votos de candidatos no registrados
	private BigDecimal totVotosCandNoRegistrados;

	// Total de votos nulos
	private BigDecimal totVotosNulos;

	// Id tipo candidatura
	private Integer idTipoCandidatura;
	
	/**
	 * Bandera que indica si los votos de las casillas consideradas se fueron a recuento
	 */
	private boolean actaRecuento;
	// Informacion para los resultados totales por partido (para rps)
	/** Nombre del candidato independiente */
	public String nombreAsociacion;
	/**
	 * Ruta en servidor donde se ubicara la imagen con el logo de partido (si se
	 * trata de un funcionario que es representante de partido, sino tendra
	 * valor nulo)
	 */
	public String emblemaAsociacion;
	/** Indica si se trata de una coalicion */
	public Integer tipoAsociacion;
	/** Total de votos en letra de un partido o candidato independiente */
	public String totalVotosLetra;
	/** Total de votos en numero de un partido o candidato independiente */
	public Integer totalVotosNumero;

	// Informacion para los resultados totales, por partido o por candidato
	// (casos que no son rp)
	/** Listado de nombres de candidatos independientes */
	public List<String> nombresAsociaciones;
	/**
	 * Listado de rutas en servidor con los logos de cada partido Es no nulo si
	 * se trata de informacion sobre partidos politicos
	 */
	public List<String> emblemasAsociaciones;
	/**
	 * Lista con los tipos de Asociaciones Politicas a las que se presentaran
	 * sus totales
	 */
	public List<Integer> tiposAsociaciones;
	/**
	 * Listado de totales de votos, ya sea de partidos politicos o de candidatos
	 * independientes
	 */
	public List<Integer> totalesVotos;

	/**
	 * Constructor con valores por omision
	 */
	public HLPDatosActa() {
		this.nombresAsociaciones = new ArrayList<String>();
		this.tiposAsociaciones = new ArrayList<Integer>();
		this.emblemasAsociaciones = new ArrayList<String>();
		this.totalesVotos = new ArrayList<Integer>();
	}

	public HLPDatosActa(FormGeneracionActas datosGenerales, Integer idEstado,
			String nomEstado, Integer idDistrito, String nomDistrito,
			Integer idMunicipio, String nomMunicipio, Integer idDemarcacion,
			String nomDemarcacion,
			List<DTODistribucionTotParcial> listTotalVotosParcial,
			List<DTODistribucionTotales> listTotalVotos,
			List<DTODistribucionCandParcial> listDistParcialCandidatos,
			List<DTODistribucionCandidatos> listDistFinalCandidatos,
			List<DTODistribucionPartidosCI> listDistribucionPPCI,Integer idTipoCandidatura, boolean actaRecuento) {
		super();
		this.datosGenerales = datosGenerales;
		this.idEstado = idEstado;
		this.nomEstado = nomEstado;
		this.idDistrito = idDistrito;
		this.nomDistrito = nomDistrito;
		this.idMunicipio = idMunicipio;
		this.nomMunicipio = nomMunicipio;
		this.idDemarcacion = idDemarcacion;
		this.nomDemarcacion = nomDemarcacion;
		this.listTotalVotosParcial = listTotalVotosParcial;
		this.listTotalVotos = listTotalVotos;
		this.listDistParcialCandidatos = listDistParcialCandidatos;
		this.listDistFinalCandidatos = listDistFinalCandidatos;
		this.listDistribucionPPCI = listDistribucionPPCI;
		this.totVotosCandNoRegistrados = totVotosCandNoRegistrados;
		this.totVotosNulos = totVotosNulos;
		this.idTipoCandidatura = idTipoCandidatura;
		this.actaRecuento = actaRecuento;
	}

	public FormGeneracionActas getDatosGenerales() {
		return datosGenerales;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getNomEstado() {
		return nomEstado;
	}

	public void setNomEstado(String nomEstado) {
		this.nomEstado = nomEstado;
	}

	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNomDistrito() {
		return nomDistrito;
	}

	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomMunicipio() {
		return nomMunicipio;
	}

	public void setNomMunicipio(String nomMunicipio) {
		this.nomMunicipio = nomMunicipio;
	}

	public Integer getIdDemarcacion() {
		return idDemarcacion;
	}

	public void setIdDemarcacion(Integer idDemarcacion) {
		this.idDemarcacion = idDemarcacion;
	}

	public String getNomDemarcacion() {
		return nomDemarcacion;
	}

	public void setNomDemarcacion(String nomDemarcacion) {
		this.nomDemarcacion = nomDemarcacion;
	}

	public void setDatosGenerales(FormGeneracionActas datosGenerales) {
		this.datosGenerales = datosGenerales;
	}

	public List<DTODistribucionTotParcial> getListTotalVotosParcial() {
		return listTotalVotosParcial;
	}

	public void setListTotalVotosParcial(
			List<DTODistribucionTotParcial> listTotalVotosParcial) {
		this.listTotalVotosParcial = listTotalVotosParcial;
	}

	public List<DTODistribucionTotales> getListTotalVotos() {
		return listTotalVotos;
	}

	public void setListTotalVotos(List<DTODistribucionTotales> listTotalVotos) {
		this.listTotalVotos = listTotalVotos;
	}

	public List<DTODistribucionCandParcial> getListDistParcialCandidatos() {
		return listDistParcialCandidatos;
	}

	public void setListDistParcialCandidatos(
			List<DTODistribucionCandParcial> listDistParcialCandidatos) {
		this.listDistParcialCandidatos = listDistParcialCandidatos;
	}

	public List<DTODistribucionCandidatos> getListDistFinalCandidatos() {
		return listDistFinalCandidatos;
	}

	public void setListDistFinalCandidatos(
			List<DTODistribucionCandidatos> listDistFinalCandidatos) {
		this.listDistFinalCandidatos = listDistFinalCandidatos;
	}

	public List<DTODistribucionPartidosCI> getListDistribucionPPCI() {
		return listDistribucionPPCI;
	}

	public void setListDistribucionPPCI(
			List<DTODistribucionPartidosCI> listDistribucionPPCI) {
		this.listDistribucionPPCI = listDistribucionPPCI;
	}

	public BigDecimal getTotVotosCandNoRegistrados() {
		return totVotosCandNoRegistrados;
	}

	public void setTotVotosCandNoRegistrados(
			BigDecimal totVotosCandNoRegistrados) {
		this.totVotosCandNoRegistrados = totVotosCandNoRegistrados;
	}

	public BigDecimal getTotVotosNulos() {
		return totVotosNulos;
	}

	public void setTotVotosNulos(BigDecimal totVotosNulos) {
		this.totVotosNulos = totVotosNulos;
	}

	public List<String> getNombresAsociaciones() {
		return nombresAsociaciones;
	}

	public void setNombresAsociaciones(List<String> nombresAsociaciones) {
		this.nombresAsociaciones = nombresAsociaciones;
	}

	public List<String> getEmblemasAsociaciones() {
		return emblemasAsociaciones;
	}

	public void setEmblemasAsociaciones(List<String> emblemasAsociaciones) {
		this.emblemasAsociaciones = emblemasAsociaciones;
	}

	public List<Integer> getTiposAsociaciones() {
		return tiposAsociaciones;
	}

	public void setTiposAsociaciones(List<Integer> tiposAsociaciones) {
		this.tiposAsociaciones = tiposAsociaciones;
	}

	public List<Integer> getTotalesVotos() {
		return totalesVotos;
	}

	public void setTotalesVotos(List<Integer> totalesVotos) {
		this.totalesVotos = totalesVotos;
	}

	public String getNombreAsociacion() {
		return nombreAsociacion;
	}

	public void setNombreAsociacion(String nombreAsociacion) {
		this.nombreAsociacion = nombreAsociacion;
	}

	public String getEmblemaAsociacion() {
		return emblemaAsociacion;
	}

	public void setEmblemaAsociacion(String emblemaAsociacion) {
		this.emblemaAsociacion = emblemaAsociacion;
	}

	public Integer getTipoAsociacion() {
		return tipoAsociacion;
	}

	public void setTipoAsociacion(Integer tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}

	public String getTotalVotosLetra() {
		return totalVotosLetra;
	}

	public void setTotalVotosLetra(String totalVotosLetra) {
		this.totalVotosLetra = totalVotosLetra;
	}

	public Integer getTotalVotosNumero() {
		return totalVotosNumero;
	}

	public void setTotalVotosNumero(Integer totalVotosNumero) {
		this.totalVotosNumero = totalVotosNumero;
	}

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	public boolean isActaRecuento() {
		return actaRecuento;
	}

	public void setActaRecuento(boolean actaRecuento) {
		this.actaRecuento = actaRecuento;
	}

}
