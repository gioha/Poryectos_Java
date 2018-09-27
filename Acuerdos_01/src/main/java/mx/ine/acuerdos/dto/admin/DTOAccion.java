package mx.ine.acuerdos.dto.admin;

import java.io.Serializable;

/**
 * Clase que se usara como nodo acción para manejar la estructura de un ménu: Menu-Submenu-Modulo-Acción
 *
 * @author Giovanni Hernández Alonso
 * @since 08/12/2017
 */
public class DTOAccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 341993574137223850L;
	
	// atributos
	
	private Integer id;
	private String url;
	
	// constructor por default de la clase
	public DTOAccion(){
		this.id = new Integer(0);
		this.url = "";
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
