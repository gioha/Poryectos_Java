/**
 * @(#)BOEstructura.java 16/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.ine.gestion.bo.inter.BOEstructuraInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.mb.MBEstructura;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que tendrá la lóogica y procedimientos del módulo de Estructura.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 16/09/2017
 */
@Component("boEstructura")
@Scope("prototype")
public class BOEstructura implements BOEstructuraInterface{

	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBEstructura.class);	

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#oficialiasAresaAOficialia(java.util.List)
	 */
	@Override
	public List<DTOOficialiaEntity> oficialiasAresaAOficialia(List<DTOOficialiasAreasEntity> listaOficialiasAreas) {
		List<DTOOficialiaEntity> lista = new ArrayList<DTOOficialiaEntity>();
		for (DTOOficialiasAreasEntity oficialiaArea : listaOficialiasAreas) {
			lista.add(oficialiaArea.getDtoOficialia());
		}
		return lista;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#inicializarArbol()
	 */
	@Override
	public TreeNode inicializarArbol() {
		DTOJerarquiaPersonasEntity raiz = new DTOJerarquiaPersonasEntity(Integer.valueOf(0),Integer.valueOf(0),Integer.valueOf(0),Integer.valueOf(0),Integer.valueOf(0),Integer.valueOf(0));
		DTOEstructuraAreasEntity raizPersona = 	new DTOEstructuraAreasEntity("root", "root", null, null, null, "root", null);
		raiz.setDtoEstructuraAreas(raizPersona);
        TreeNode root = new DefaultTreeNode("root", raiz, null);
        return root;
    }
	

	public void agregarEnNodo(TreeNode nodo, DTOJerarquiaPersonasEntity relacion) {
		TreeNode arbol = new DefaultTreeNode(relacion.getDtoEstructuraAreas().getCuentaLDAP(), relacion, nodo);
		arbol.setExpanded(true);
	}
	
    public TreeNode buscarNodoPorCuentaNivel(TreeNode nodoRaiz, Integer idPersona, Integer nivel) {
    	if (nodoRaiz != null) {

    		DTOJerarquiaPersonasEntity relacion = (DTOJerarquiaPersonasEntity)nodoRaiz.getData();
    		
    		boolean igual = true;
    		if(relacion.getIdPersona().compareTo(idPersona) != 0) {
    			igual = false;
    		}
    		if(relacion.getIdNivel().compareTo(nivel) != 0) {
    			igual = false;
    		}
            if(igual) {
               return nodoRaiz;
            } else {
                TreeNode nodoEncontrado = null;
                for (TreeNode nodoHijo : nodoRaiz.getChildren()) {
                    nodoEncontrado = buscarNodoPorCuentaNivel(nodoHijo, idPersona, nivel);
                    if (nodoEncontrado != null) {
                    	return nodoEncontrado;
                    }
                }
                return nodoEncontrado;
             }
        } else {
            return null;
        }
    }
 
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#listaJerarquiaAArbol(java.util.List)
	 */
	@Override
	public TreeNode listaJerarquiaAArbol(List<DTOJerarquiaPersonasEntity> listaJerarquia) {
		TreeNode arbolJerarquia = inicializarArbol();
		for (DTOJerarquiaPersonasEntity jerarquia : listaJerarquia) {
			if(jerarquia.getIdNivel() == Integer.valueOf(1)) {
				agregarEnNodo(arbolJerarquia, jerarquia);
			} else {
				TreeNode padre = buscarNodoPorCuentaNivel(arbolJerarquia, jerarquia.getIdPersonaPadre(), jerarquia.getNivelPadre());
				if (padre != null) {
					agregarEnNodo(padre, jerarquia);
				}
			}
		}
		return arbolJerarquia;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#agregarCuentasALista(java.util.List)
	 */
	@Override
	public void agregarCuentasALista(List<DTOJerarquiaPersonasEntity> lista) {
		for (DTOJerarquiaPersonasEntity relacion : lista) {
			if(relacion.getDtoEstructuraAreas().getCuentaLDAP() != null) {
				log.info(relacion.getDtoEstructuraAreas().getCuentaLDAP());
				relacion.setCuentaPersona(relacion.getDtoEstructuraAreas().getCuentaLDAP());
			}
			if(relacion.getDtoEstructuraAreasPadre() != null) {
				relacion.setCuentaPadre(relacion.getDtoEstructuraAreasPadre().getCuentaLDAP());
				log.info(relacion.getDtoEstructuraAreasPadre().getCuentaLDAP());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#quitarOtrosCaracteres(java.lang.String)
	 */
	@Override
	public String quitarOtrosCaracteres(String cadena) {
		if(cadena == null) return null;
		cadena = cadena.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑÜü ]+", "");
		return cadena;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#sustituirAcentos(java.lang.String)
	 */
	@Override
	public String sustituirAcentos(String cadena) {
		if(cadena == null) return null;
		cadena = cadena.replaceAll("[áéíóúÁÉÍÓÚ]+", "*");
		return cadena;
	}
	
	@Override
	public List<Integer> pasarListaAreasAEntidades(List<DTOCAreaEntity> listaAreas) {
		List<Integer> listaEntidades = new ArrayList<Integer>();
		for (DTOCAreaEntity area : listaAreas) {
			listaEntidades.add(area.getIdEntidad());
		}
		return listaEntidades;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#obtenerIdsJerarquia(java.util.List, java.util.HashMap)
	 */
	@Override
	public void obtenerIdsJerarquia(List<DTOJerarquiaPersonasEntity> lista,	HashMap<String, Integer> mapa) {
		for (DTOJerarquiaPersonasEntity jerarquia : lista) {
			if (jerarquia.getIdPersona() == null) {
				jerarquia.setIdPersona(mapa.get(jerarquia.getCuentaPersona()));
			}
			if (jerarquia.getIdPersonaPadre() == null) {
				jerarquia
						.setIdPersonaPadre(mapa.get(jerarquia.getCuentaPadre()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#inicializarNotificaciones(java.lang.Integer)
	 */
	@Override
	public List<DTONotificacionesEntity> inicializarNotificaciones(Integer idPersona) {
		List<DTONotificacionesEntity> notificaciones = new ArrayList<DTONotificacionesEntity>();
		
		notificaciones.add(construirNotificacion(idPersona, Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")),
				Integer.valueOf(Utilidades.mensajeProperties("id_apartado_bandeja_entrada")), Integer.valueOf(0),
				Integer.valueOf(Utilidades.mensajeProperties("id_apartado_bandeja_borradores")), Integer.valueOf(0), 
				Integer.valueOf(Utilidades.mensajeProperties("id_apartado_bandeja_enviados")),	Integer.valueOf(0), 
				Integer.valueOf(0), Integer.valueOf(0)));

		notificaciones.add(construirNotificacion(idPersona, Integer.valueOf(Utilidades.mensajeProperties("id_modulo_validacion")),
				Integer.valueOf(Utilidades.mensajeProperties("id_apartado_validacion")), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0), 
				Integer.valueOf(0),	Integer.valueOf(0), 
				Integer.valueOf(0), Integer.valueOf(0)));

		notificaciones.add(construirNotificacion(idPersona, Integer.valueOf(Utilidades.mensajeProperties("id_modulo_firma")), 
				Integer.valueOf(Utilidades.mensajeProperties("id_apartado_firma")), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0), 
				Integer.valueOf(0), Integer.valueOf(0)));

		return notificaciones;
	}


	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOEstructuraInterface#inicializarNotificacionesOF(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTONotificacionesOFEntity> inicializarNotificacionesOF(Integer idArea, Integer tipoArea) {
		List<DTONotificacionesOFEntity> notificaciones = new ArrayList<DTONotificacionesOFEntity>();
		
		notificaciones.add(construirNotificacionOF(idArea, tipoArea, Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja_oficialia")),
				Integer.valueOf(Utilidades.mensajeProperties("id_apartado_bandeja_oficialia")), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0),
				Integer.valueOf(0), Integer.valueOf(0)));

		return notificaciones;
	}

	/**
	 * 
	 * @param idPersona: Integer con el id de la persona que acaba de ser insertada
	 * @param idModulo: Integer con el id del módulo
	 * @param idApartado: Integer con el id del apartado 1
	 * @param documentosPendientes: Integer con el número de documentos pendientes en 0
	 * @param idApartado2: Integer con el id del apartado 2
	 * @param documentosPendientes2: Integer con el número de documentos pendientes en 0
	 * @param idApartado3: Integer con el id del apartado 3
	 * @param documentosPendientes3: Integer con el número de documentos pendientes en 0
	 * @param idApartado4: Integer con el id del apartado 4
	 * @param documentosPendientes4: Integer con el número de documentos pendientes en 0
	 * 
	 * @return DTONotificacionesEntity objeto nuevo creado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/12/2017
	 */
	public DTONotificacionesEntity construirNotificacion(Integer idPersona, Integer idModulo, 
			Integer idApartado,  Integer documentosPendientes,  Integer idApartado2, Integer documentosPendientes2,
			Integer idApartado3, Integer documentosPendientes3, Integer idApartado4, Integer documentosPendientes4) {
		DTONotificacionesEntity notificacion = new DTONotificacionesEntity();
		notificacion.setIdPersona(idPersona);
		notificacion.setIdModulo(idModulo);
		notificacion.setDocumentosPendientes(documentosPendientes);
		notificacion.setIdApartado(idApartado);
		notificacion.setDocumentosPendientes2(documentosPendientes2);
		notificacion.setIdApartado2(idApartado2);
		notificacion.setDocumentosPendientes3(documentosPendientes3);
		notificacion.setIdApartado3(idApartado3);
		notificacion.setDocumentosPendientes4(documentosPendientes4);
		notificacion.setIdApartado4(idApartado4);
		return notificacion;
	}
	
	/**
	 * 
	 * @param idArea: Integer con el id del área que acaba de ser insertada
	 * @param tipoArea: Integer con el tipo de área que acaba de ser insertada
	 * @param idModulo: Integer con el id del módulo
	 * @param idApartado: Integer con el id del apartado 1
	 * @param documentosPendientes: Integer con el número de documentos pendientes en 0
	 * @param idApartado2: Integer con el id del apartado 2
	 * @param documentosPendientes2: Integer con el número de documentos pendientes en 0
	 * @param idApartado3: Integer con el id del apartado 3
	 * @param documentosPendientes3: Integer con el número de documentos pendientes en 0
	 * @param idApartado4: Integer con el id del apartado 4
	 * @param documentosPendientes4: Integer con el número de documentos pendientes en 0
	 * 
	 * @return DTONotificacionesEntity objeto nuevo creado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/12/2017
	 */
	public DTONotificacionesOFEntity construirNotificacionOF(Integer idArea, Integer tipoArea, Integer idModulo, 
			Integer idApartado,  Integer documentosPendientes,  Integer idApartado2, Integer documentosPendientes2,
			Integer idApartado3, Integer documentosPendientes3, Integer idApartado4, Integer documentosPendientes4) {
		DTONotificacionesOFEntity notificacion = new DTONotificacionesOFEntity();
		notificacion.setIdArea(idArea);
		notificacion.setTipoArea(tipoArea);
		notificacion.setIdModulo(idModulo);
		notificacion.setDocumentosPendientes(documentosPendientes);
		notificacion.setIdApartado(idApartado);
		notificacion.setDocumentosPendientes2(documentosPendientes2);
		notificacion.setIdApartado2(idApartado2);
		notificacion.setDocumentosPendientes3(documentosPendientes3);
		notificacion.setIdApartado3(idApartado3);
		notificacion.setDocumentosPendientes4(documentosPendientes4);
		notificacion.setIdApartado4(idApartado4);
		return notificacion;
	}
	
	
}
