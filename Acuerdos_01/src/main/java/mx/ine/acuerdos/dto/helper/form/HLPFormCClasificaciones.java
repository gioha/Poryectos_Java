package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCClasificaciones;

public class HLPFormCClasificaciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1789986410892296616L;
	
	private List<DTOCClasificaciones> clasificacion;
	
	private List<DTOCClasificaciones> clasificacionTemp;
	
	private DTOCClasificaciones clasificacionP;
	
	private String clasificacionAdd;

	private Integer tamOrigen;
	
	private DTOCClasificaciones clasificacionPreElim;
	
	boolean okElim;
	
	boolean movimientosRealizadosBD;
	
	private List<String> listaImgsInfografias;
	
	
	public HLPFormCClasificaciones(){
		
		this.clasificacion         = new ArrayList<DTOCClasificaciones>();
		this.clasificacionTemp     = new ArrayList<DTOCClasificaciones>();
		this.clasificacionP        = new DTOCClasificaciones();
		this.clasificacionPreElim  = new DTOCClasificaciones();
		this.clasificacionAdd      = "";
		this.tamOrigen             = new Integer(0);
		this.okElim                = false;
		this.movimientosRealizadosBD = false;
		this.listaImgsInfografias    = new ArrayList<String>();
	}

	
	public void agregarRenglon(String Descp){
		
		this.clasificacionP        = new DTOCClasificaciones();
		
		Integer i = new Integer(0);
		Integer id = new Integer(0);
		
		for (i = 0; i < clasificacion.size(); i++){
			if(id < clasificacion.get(i).getIdClasificacion()){
				id = clasificacion.get(i).getIdClasificacion();
			}
		}
		
		clasificacionP.setDescripcion(Descp);
		clasificacionP.setIdClasificacion(id+1);
		clasificacionP.setOrden(clasificacion.size());
		clasificacionP.setTipoClasificacion(0);
		clasificacionP.setEliminable(false);
		clasificacionP.setTienePuntoED(false);
		clasificacion.add(clasificacionP);
		
	}
	
	
	public List<DTOCClasificaciones> getClasificacion() {
		return clasificacion;
	}

	
	public void setClasificacion(List<DTOCClasificaciones> clasificacion) {
		this.clasificacion = clasificacion;
	}


	public String getClasificacionAdd() {
		return clasificacionAdd;
	}


	public void setClasificacionAdd(String clasificacionAdd) {
		this.clasificacionAdd = clasificacionAdd;
	}



	public DTOCClasificaciones getClasificacionP() {
		return clasificacionP;
	}



	public void setClasificacionP(DTOCClasificaciones clasificacionP) {
		this.clasificacionP = clasificacionP;
	}
	
	public Integer getTamOrigen() {
		return tamOrigen;
	}


	public void setTamOrigen(Integer tamOrigen) {
		this.tamOrigen = tamOrigen;
	}


	public DTOCClasificaciones getClasificacionPreElim() {
		return clasificacionPreElim;
	}


	public void setClasificacionPreElim(DTOCClasificaciones clasificacionPreElim) {
		this.clasificacionPreElim = clasificacionPreElim;
	}


//	public DTOCClasificaciones getClasificacionProtegida() {
//		return clasificacionProtegida;
//	}
//
//
//	public void setClasificacionProtegida(DTOCClasificaciones clasificacionProtegida) {
//		this.clasificacionProtegida = clasificacionProtegida;
//	}


//	public boolean isRenglonEditable() {
//		return renglonEditable;
//	}
//
//
//	public void setRenglonEditable(boolean renglonEditable) {
//		this.renglonEditable = renglonEditable;
//	}


	public List<DTOCClasificaciones> getClasificacionTemp() {
		return clasificacionTemp;
	}


	public void setClasificacionTemp(List<DTOCClasificaciones> clasificacionTemp) {
		this.clasificacionTemp = clasificacionTemp;
	}


//	public boolean isDesabilitado() {
//		return desabilitado;
//	}
//
//
//	public void setDesabilitado(boolean desabilitado) {
//		this.desabilitado = desabilitado;
//	}


	public boolean isOkElim() {
		return okElim;
	}


	public void setOkElim(boolean okElim) {
		this.okElim = okElim;
	}


	public boolean isMovimientosRealizadosBD() {
		return movimientosRealizadosBD;
	}


	public void setMovimientosRealizadosBD(boolean movimientosRealizadosBD) {
		this.movimientosRealizadosBD = movimientosRealizadosBD;
	}


	public List<String> getListaImgsInfografias() {
		return listaImgsInfografias;
	}


	public void setListaImgsInfografias(List<String> listaImgsInfografias) {
		this.listaImgsInfografias = listaImgsInfografias;
	}
	
}
