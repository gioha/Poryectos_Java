package mx.ine.computosINE.dto.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.computosINE.dto.DTOActaCap;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOAgrupacionCasillas;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCEstatus;
import mx.ine.computosINE.dto.DTODistrito;
import mx.ine.computosINE.dto.DTORegiduria;
import mx.ine.computosINE.dto.DTOSeccion;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.util.Constantes;

/**
 * 
 * Clase Form que mapea los campos y variables usadas en las pantallas de captura de actas 
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 28/04/2017
 * @copyright INE
 *
 */
public class FormCapturaActas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 215102688367776257L;
	
    @Autowired
    @Qualifier("constante")
    private transient Constantes constantes;
	
	private Date fechaHora;
	
    private DTOUsuarioLogin userLogin;
    
    private Integer procesoElectoral;
    
    private Integer detalleProcesoElectoral;
    
    private String candidatura;
    
    private Integer idCandidatura;
    
    private Integer idEdoSeleccionado;
    
    private Integer idMunSeleccionado;
    
    private List<DTOCEstatus> listaEstatus;

    private DTOAgrupacionCasillas totalDeCasillas;
    
    private List<DTOActaCasillaVotos> casillasCapturadas;
    
    private DTOAgrupacionCasillas casillasPorCapturar;
    
    private DTOAgrupacionCasillas casillasParaConsultar;
    
    private DTOAgrupacionCasillas casillasParaConsultarEnRTotal;
    
    private DTOActaCap actaCasilla;	
    
    private List<DTOSeccion> seccionesEnPantalla;
    
    private List<DTOCasillaWS> casillasEnPantalla;
    
    private List<DTOAsociacion> asociaciones;
    
    private BigDecimal avanceCaptura;
    
    private BigDecimal numActasCapturadas;

    private BigDecimal numActasTotal;
        
    private boolean hayAsociaciones;
    
    private boolean esRecuentoTotal;
    
    public FormCapturaActas(DTOUsuarioLogin userLogin, String candidatura){
    	
    	this.userLogin 				= userLogin;
    	this.procesoElectoral 		= this.userLogin.getIdProcesoElectoral();
    	this.detalleProcesoElectoral= this.userLogin.getIdDetalleProceso();
    	this.candidatura			= candidatura;
    	this.idEdoSeleccionado 		= this.userLogin.getIdEstadoSeleccionado();
    	this.idMunSeleccionado 		= this.userLogin.getIdMunicipioSeleccionado();
    	
    	if(candidatura.equalsIgnoreCase(constantes.AYUNTAMIENTO)){
    		this.idCandidatura = constantes.ID_TIPO_CAND_AYUNTAMIENTO;
    	} 
    	else if(candidatura.equalsIgnoreCase(constantes.GOBERNADOR)){
    		this.idCandidatura = constantes.ID_TIPO_CAND_GOBERNADOR;
    	}
    	else if(candidatura.equalsIgnoreCase(constantes.DIPUTADO_MR)){
    		this.idCandidatura = constantes.ID_TIPO_CAND_DIPUTADO_MR;
    	}
    	else if(candidatura.equalsIgnoreCase(constantes.DIPUTADO_RP)){
    		this.idCandidatura = constantes.ID_TIPO_CAND_DIPUTADO_RP;
    	}
    	else if(candidatura.equalsIgnoreCase(constantes.REGIDURIA_MR)){
    		this.idCandidatura = constantes.ID_TIPO_CAND_REGIDURIA_MR;
    	}
    	else if(candidatura.equalsIgnoreCase(constantes.REGIDURIA_RP)){
    		this.idCandidatura = constantes.ID_TIPO_CAND_REGIDURIA_RP;
    	}
    	else{
    		this.idCandidatura = constantes.ID_TIPO_CANDIDATURA_INICIAL;
    	}

    	listaEstatus	 			= new ArrayList<DTOCEstatus>();
    	
    	totalDeCasillas 			= new DTOAgrupacionCasillas();
	    	totalDeCasillas.setIdEstado(this.idEdoSeleccionado);
	    	totalDeCasillas.setIdMunicipio(this.idMunSeleccionado);
	    	totalDeCasillas.setNombreTipoCandidatura(this.candidatura);
    	
    	casillasCapturadas 				= new ArrayList<DTOActaCasillaVotos>();
	    casillasPorCapturar 			= new DTOAgrupacionCasillas();
	    casillasParaConsultar			= new DTOAgrupacionCasillas();
	    casillasParaConsultarEnRTotal	= new DTOAgrupacionCasillas(); 
    	actaCasilla 					= new DTOActaCap();
    	
    	seccionesEnPantalla 			= new ArrayList<DTOSeccion>();    	
    	casillasEnPantalla 				= new ArrayList<DTOCasillaWS>();
    	
    	asociaciones 					= new ArrayList<DTOAsociacion>();
    	
    	avanceCaptura 					= new BigDecimal(0);
	    numActasCapturadas				= new BigDecimal(0);
	    numActasTotal					= new BigDecimal(0);
	    
	    hayAsociaciones 				= false;
	    esRecuentoTotal					= false;
    	
    }

    
	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public DTOUsuarioLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(DTOUsuarioLogin userLogin) {
		this.userLogin = userLogin;
	}

	public Integer getProcesoElectoral() {
		return procesoElectoral;
	}

	public void setProcesoElectoral(Integer procesoElectoral) {
		this.procesoElectoral = procesoElectoral;
	}

	public Integer getDetalleProcesoElectoral() {
		return detalleProcesoElectoral;
	}


	public void setDetalleProcesoElectoral(Integer detalleProcesoElectoral) {
		this.detalleProcesoElectoral = detalleProcesoElectoral;
	}


	public String getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(String candidatura) {
		this.candidatura = candidatura;
	}

	public Integer getIdCandidatura() {
		return idCandidatura;
	}


	public void setIdCandidatura(Integer idCandidatura) {
		this.idCandidatura = idCandidatura;
	}


	public Integer getIdEdoSeleccionado() {
		return idEdoSeleccionado;
	}

	public void setIdEdoSeleccionado(Integer idEdoSeleccionado) {
		this.idEdoSeleccionado = idEdoSeleccionado;
	}

	public Integer getIdMunSeleccionado() {
		return idMunSeleccionado;
	}

	public void setIdMunSeleccionado(Integer idMunSeleccionado) {
		this.idMunSeleccionado = idMunSeleccionado;
	}

	public List<DTOCEstatus> getListaEstatus() {
		return listaEstatus;
	}

	public void setListaEstatus(List<DTOCEstatus> listaEstatus) {
		this.listaEstatus = listaEstatus;
	}

	public DTOAgrupacionCasillas getTotalDeCasillas() {
		return totalDeCasillas;
	}

	public void setTotalDeCasillas(DTOAgrupacionCasillas totalDeCasillas) {
		this.totalDeCasillas = totalDeCasillas;
	}

	public List<DTOActaCasillaVotos> getCasillasCapturadas() {
		return casillasCapturadas;
	}

	public void setCasillasCapturadas(List<DTOActaCasillaVotos> casillasCapturadas) {
		this.casillasCapturadas = casillasCapturadas;
	}

	public DTOAgrupacionCasillas getCasillasPorCapturar() {
		return casillasPorCapturar;
	}

	public void setCasillasPorCapturar(DTOAgrupacionCasillas casillasPorCapturar) {
		this.casillasPorCapturar = casillasPorCapturar;
	}

	public DTOActaCap getActaCasilla() {
		return actaCasilla;
	}

	public void setActaCasilla(DTOActaCap actaCasilla) {
		this.actaCasilla = actaCasilla;
	}

	public List<DTOSeccion> getSeccionesEnPantalla() {
		return seccionesEnPantalla;
	}

	public void setSeccionesEnPantalla(List<DTOSeccion> seccionesEnPantalla) {
		this.seccionesEnPantalla = seccionesEnPantalla;
	}

	public List<DTOCasillaWS> getCasillasEnPantalla() {
		return casillasEnPantalla;
	}

	public void setCasillasEnPantalla(List<DTOCasillaWS> casillasEnPantalla) {
		this.casillasEnPantalla = casillasEnPantalla;
	}


	public BigDecimal getAvanceCaptura() {
		return avanceCaptura;
	}


	public void setAvanceCaptura(BigDecimal avanceCaptura) {
		this.avanceCaptura = avanceCaptura;
	}


	public BigDecimal getNumActasCapturadas() {
		return numActasCapturadas;
	}


	public void setNumActasCapturadas(BigDecimal numActasCapturadas) {
		this.numActasCapturadas = numActasCapturadas;
	}


	public BigDecimal getNumActasTotal() {
		return numActasTotal;
	}


	public void setNumActasTotal(BigDecimal numActasTotal) {
		this.numActasTotal = numActasTotal;
	}

	public List<DTOAsociacion> getAsociaciones() {
		return asociaciones;
	}

	public void setAsociaciones(List<DTOAsociacion> asociaciones) {
		this.asociaciones = asociaciones;
	}


	public boolean isHayAsociaciones() {
		return hayAsociaciones;
	}


	public void setHayAsociaciones(boolean hayAsociaciones) {
		this.hayAsociaciones = hayAsociaciones;
	}


	public boolean isEsRecuentoTotal() {
		return esRecuentoTotal;
	}


	public void setEsRecuentoTotal(boolean esRecuentoTotal) {
		this.esRecuentoTotal = esRecuentoTotal;
	}


	public DTOAgrupacionCasillas getCasillasParaConsultar() {
		return casillasParaConsultar;
	}


	public void setCasillasParaConsultar(DTOAgrupacionCasillas casillasParaConsultar) {
		this.casillasParaConsultar = casillasParaConsultar;
	}


	public DTOAgrupacionCasillas getCasillasParaConsultarEnRTotal() {
		return casillasParaConsultarEnRTotal;
	}


	public void setCasillasParaConsultarEnRTotal(DTOAgrupacionCasillas casillasParaConsultarEnRTotal) {
		this.casillasParaConsultarEnRTotal = casillasParaConsultarEnRTotal;
	}

}
