package mx.ine.acuerdos.dto.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se usara como nodo sub menu para manejar la estructura de un ménu: Menu-Submenu-Modulo-Acción
 *
 * @author Giovanni Hernández Alonso
 * @since 08/12/2017
 */
public class DTOSubMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 618953231878629241L;
	
	// atributos
	
	private Integer id;
	private String descripcion;
	private List<DTOModulo> modulos;
	
	
	// constructor por default
	public DTOSubMenu(){
		this.id = new Integer(0);
		this.descripcion = "";
		this.modulos = new ArrayList<DTOModulo>();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<DTOModulo> getModulos() {
		return modulos;
	}


	public void setModulos(List<DTOModulo> modulos) {
		this.modulos = modulos;
	}
}
