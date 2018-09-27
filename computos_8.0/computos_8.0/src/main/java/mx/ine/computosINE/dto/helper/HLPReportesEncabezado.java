package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HLPReportesEncabezado implements Serializable{

	private static final long serialVersionUID = 210420170111L;
	
	private List<Integer> index;
    private List<Integer> ancho;
    private List<Integer> alto;
    private List<String> descEncabezado;
    private List<Integer> tipoFila;
    
    public HLPReportesEncabezado(){
        this.ancho = new ArrayList<Integer>();
        this.alto = new ArrayList<Integer>();
        this.descEncabezado = new ArrayList<String>();
        this.index = new ArrayList<Integer>();
        this.tipoFila = new ArrayList<Integer>();
    }
    
    public void ingresarEncabezado(Integer index, Integer ancho, Integer alto, String descripcion, Integer tipoFila){
        this.index.add(index);
        this.ancho.add(ancho);
        this.alto.add(alto);
        this.descEncabezado.add(descripcion);
        this.tipoFila.add(tipoFila);
    }

	public List<Integer> getIndex() {
		return index;
	}

	public void setIndex(List<Integer> index) {
		this.index = index;
	}

	public List<Integer> getAncho() {
		return ancho;
	}

	public void setAncho(List<Integer> ancho) {
		this.ancho = ancho;
	}

	public List<Integer> getAlto() {
		return alto;
	}

	public void setAlto(List<Integer> alto) {
		this.alto = alto;
	}

	public List<String> getDescEncabezado() {
		return descEncabezado;
	}

	public void setDescEncabezado(List<String> descEncabezado) {
		this.descEncabezado = descEncabezado;
	}

	public List<Integer> getTipoFila() {
		return tipoFila;
	}

	public void setTipoFila(List<Integer> tipoFila) {
		this.tipoFila = tipoFila;
	}
    
}
