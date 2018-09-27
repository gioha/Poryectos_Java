package mx.ine.acuerdos.dto.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se usara como nodo modulo para manejar la estructura de un ménu: Menu-Submenu-Modulo-Acción
 *
 * @author Giovanni Hernández Alonso
 * @since 08/12/2017
 */
public class DTOModulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643848836751969522L;

	
	// atributos
	
	private Integer id;
	private String tipo;
	private String nombre;
	private List<DTOAccion> acciones;
	
	
	// constructor por default
	public DTOModulo(){
		this.id = new Integer(0);
		this.tipo = "";
		this.nombre = "";
		this.acciones = new ArrayList<DTOAccion>();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTOAccion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<DTOAccion> acciones) {
		this.acciones = acciones;
	}
}
