package mx.ine.acuerdos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dao.DAOMenuAdminInterface;
import mx.ine.acuerdos.dto.admin.DTOAccion;
import mx.ine.acuerdos.dto.admin.DTOMenu;
import mx.ine.acuerdos.dto.admin.DTOModulo;
import mx.ine.acuerdos.dto.admin.DTOSubMenu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoMenuAdmin")
public class DAOMenuAdmin extends DAOGeneric<DTOMenu, Integer> implements DAOMenuAdminInterface{

	private static final Log log = LogFactory.getLog(DAOMenuAdmin.class);
	
	@Override
	public List<DTOMenu> obtenMenuSistema(Integer idSistema, String rolUsuario) {
		
		List<DTOMenu> menus = new ArrayList<DTOMenu>();
		
		try {
	
			// construimos el query 
			SQLQuery consulta = getSession().createSQLQuery(this.getContainer().getQuery("query.menu.admin"));
			consulta.setParameter("IDSISTEMA", idSistema.toString());
			consulta.setParameter("ROLUSER", "%" + rolUsuario.toUpperCase() + "%");
	
			// ejecutamos la consulta
			List<Object[]> rows = consulta.list();
		
			// agrupamos la información obtenida
			int count = 0;
			DTOMenu menu = new DTOMenu();
			DTOSubMenu subMenu = new DTOSubMenu();
			DTOModulo modulo = new DTOModulo();
			DTOAccion accion = new DTOAccion();
			
			for(Object[] ob: rows) {
				
				// primera iteración
				if ( count == 0 ){
					// menu
					menu.setIdSistema(  ob[0]==null? new Integer(0) :  new Integer( ob[0].toString() )  );
					menu.setIdMenu(  ob[1]==null? new Integer(0) :  new Integer( ob[1].toString() )  );
					menu.setDescripcion( ob[2]==null? "" : ob[2].toString() );
					
					// submenu
					subMenu.setId(  ob[3]==null? new Integer(0) :  new Integer( ob[3].toString() )  );
					subMenu.setDescripcion(  ob[4]==null? "" : ob[4].toString()  );
					
					// modulo
					modulo.setId(  ob[5]==null? new Integer(0) :  new Integer( ob[5].toString() )  );
					modulo.setTipo(  ob[6]==null? "" : ob[6].toString()  );
					modulo.setNombre(  ob[7]==null? "" : ob[7].toString()  );
					
					// acción
					accion.setId(  ob[9]==null? new Integer(0) :  new Integer( ob[9].toString() )  );
					accion.setUrl(  ob[10]==null? "" : ob[10].toString()  );
					
					modulo.getAcciones().add(accion);
					subMenu.getModulos().add(modulo);
					menu.getSubmenus().add(subMenu);
					
					menus.add(menu);
				}
				
				// las otras iteraciones
				else{
				
					// es un menu nuevo
					if (  !menu.getIdMenu().equals(   new Integer( ob[1].toString() )   )   ){
						
						// evitamos duplicados
						if(  menus.size()>0 &&  !menu.getIdMenu().equals(     menus.get(   menus.size() -1  ).getIdMenu()   )  ){
							menus.add(menu);
						}
						
						menu = new DTOMenu();
					    subMenu = new DTOSubMenu();
						modulo = new DTOModulo();
						accion = new DTOAccion();
						
						// menu
						menu.setIdSistema(  ob[0]==null? new Integer(0) :  new Integer( ob[0].toString() )  );
						menu.setIdMenu(  ob[1]==null? new Integer(0) :  new Integer( ob[1].toString() )  );
						menu.setDescripcion( ob[2]==null? "" : ob[2].toString() );
						
						// submenu
						subMenu.setId(  ob[3]==null? new Integer(0) :  new Integer( ob[3].toString() )  );
						subMenu.setDescripcion(  ob[4]==null? "" : ob[4].toString()  );
						
						// modulo
						modulo.setId(  ob[5]==null? new Integer(0) :  new Integer( ob[5].toString() )  );
						modulo.setTipo(  ob[6]==null? "" : ob[6].toString()  );
						modulo.setNombre(  ob[7]==null? "" : ob[7].toString()  );
						
						// acción
						accion.setId(  ob[9]==null? new Integer(0) :  new Integer( ob[9].toString() )  );
						accion.setUrl(  ob[10]==null? "" : ob[10].toString()  );
						
						modulo.getAcciones().add(accion);
						subMenu.getModulos().add(modulo);
						menu.getSubmenus().add(subMenu);
					}
					
					// es un submenu nuevo
					else if ( !subMenu.getId().equals(  new Integer( ob[3].toString() )  ) ){
					
						// evitamos duplicados
						if(  menu.getSubmenus().size()>0 &&  !subMenu.getId().equals(     menu.getSubmenus().get(   menu.getSubmenus().size() -1  ).getId()       )  ){
							menu.getSubmenus().add( subMenu );
						}
						
						subMenu = new DTOSubMenu();
						modulo = new DTOModulo();
						accion = new DTOAccion();
						
						// submenu
						subMenu.setId(  ob[3]==null? new Integer(0) :  new Integer( ob[3].toString() )  );
						subMenu.setDescripcion(  ob[4]==null? "" : ob[4].toString()  );
						
						// modulo
						modulo.setId(  ob[5]==null? new Integer(0) :  new Integer( ob[5].toString() )  );
						modulo.setTipo(  ob[6]==null? "" : ob[6].toString()  );
						modulo.setNombre(  ob[7]==null? "" : ob[7].toString()  );
						
						// acción
						accion.setId(  ob[9]==null? new Integer(0) :  new Integer( ob[9].toString() )  );
						accion.setUrl(  ob[10]==null? "" : ob[10].toString()  );
						
						modulo.getAcciones().add(accion);
						subMenu.getModulos().add(modulo);
					}
					
					// es un modulo nuevo
					else if (  !modulo.getId().equals(  new Integer( ob[5].toString() )  )  ){
						
						// evitamos duplicados
						if(  subMenu.getModulos().size()>0 &&  !modulo.getId().equals(     subMenu.getModulos().get(   subMenu.getModulos().size() -1  ).getId()       )  ){
							subMenu.getModulos().add(modulo);
						}
						
						modulo = new DTOModulo();
						accion = new DTOAccion();
						
						// modulo
						modulo.setId(  ob[5]==null? new Integer(0) :  new Integer( ob[5].toString() )  );
						modulo.setTipo(  ob[6]==null? "" : ob[6].toString()  );
						modulo.setNombre(  ob[7]==null? "" : ob[7].toString()  );
						
						// acción
						accion.setId(  ob[9]==null? new Integer(0) :  new Integer( ob[9].toString() )  );
						accion.setUrl(  ob[10]==null? "" : ob[10].toString()  );
						
						modulo.getAcciones().add(accion);
					}
					
					// es una acción nueva
					else if( !accion.getId().equals(  new Integer( ob[9].toString() )  ) ){
						
						// evitamos duplicados
						if(  modulo.getAcciones().size()>0 &&  !accion.equals(     modulo.getAcciones().get(   modulo.getAcciones().size() -1  )       )  ){
							modulo.getAcciones().add(accion);
						}
				
						accion = new DTOAccion();
						
						// acción
						accion.setId(  ob[9]==null? new Integer(0) :  new Integer( ob[9].toString() )  );
						accion.setUrl(  ob[10]==null? "" : ob[10].toString()  );
					}
					
				}
				
				// añadimos la ultima acción recuperada si no se habia añadido antes
				if(  modulo.getAcciones().size()>0 &&  !accion.equals(     modulo.getAcciones().get(   modulo.getAcciones().size() -1  )       )  ){
					modulo.getAcciones().add(accion);
				}
				
				// añadimos el ultimo modulo recuperado si no se habia añadido antes
				if(  subMenu.getModulos().size()>0 &&  !modulo.getId().equals(     subMenu.getModulos().get(   subMenu.getModulos().size() -1  ).getId()       )  ){
					subMenu.getModulos().add(modulo);
				}
				
				// añadimos el ultimo submenu recuperado si no se habia añadido antes
				if(  menu.getSubmenus().size()>0 &&  !subMenu.getId().equals(     menu.getSubmenus().get(   menu.getSubmenus().size() -1  ).getId()       )  ){
					menu.getSubmenus().add( subMenu );
				}
				
				// añadimos el ultimo menu recuperado si no se habia añadido antes
				if(  menus.size()>0 &&  !menu.getIdMenu().equals(     menus.get(   menus.size() -1  ).getIdMenu()   )  ){
					menus.add(menu);
				}
				
				count ++;
			}
			
			// añadimos el ultimo menu recuperado si no se habia añadido antes
			if(  menus.size()>0 &&  !menu.getIdMenu().equals(     menus.get(   menus.size() -1  ).getIdMenu()   )  ){
				menus.add(menu);
			}
			
			return menus;	
		}
		
		catch(Exception e) {
			log.error( " DAOMenuAdmin - obtenMenuSistema() - Ocurrio un error al recuperar el menu: " + e.getMessage()  );
			return menus;
		}	
	}

}
