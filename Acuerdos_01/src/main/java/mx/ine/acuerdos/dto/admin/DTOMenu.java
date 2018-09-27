package mx.ine.acuerdos.dto.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se usara como nodo padre para manejar la estructura de un ménu: Menu-Submenu-Modulo-Acción
 *
 * @author Giovanni Hernández Alonso
 * @since 08/12/2017
 */
public class DTOMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2033742475464343064L;
	
	
	// atributos
	
	private Integer idSistema;
	private Integer idMenu;
	private String descripcion;
	private List<DTOSubMenu> submenus;
	
	
	// contructor por default
	public DTOMenu(){
		this.idSistema = new Integer(0);
		this.idMenu = new Integer(0);
		this.descripcion = "";
		this.submenus = new ArrayList<DTOSubMenu>();
	}


	public Integer getIdSistema() {
		return idSistema;
	}


	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}


	public Integer getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<DTOSubMenu> getSubmenus() {
		return submenus;
	}


	public void setSubmenus(List<DTOSubMenu> submenus) {
		this.submenus = submenus;
	}
}
