package mx.ine.gestion.vh.impl;

/**
 * @(#)VHAcronimos.java 09/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOAcronimoHelper;
import mx.ine.gestion.mb.MBAcronimos;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHAcronimosInterface;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que tendran la lógica y procedimientos del módulo Acrónimos.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 09/11/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
@Component("vhAcronimos")
@Scope("prototype")
public class VHAcronimos implements VHAcronimosInterface {
	
	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBAcronimos.class);
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#obtenerFormatosAnio()
	 */
	@Override
	public HashMap<String, String> obtenerFormatosAnio() {
		HashMap<String,String> formatoAnio = new HashMap<String,String>();
		formatoAnio.put("00", "2");
		formatoAnio.put("0000", "4");
		return formatoAnio;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#generarAcronimo(mx.ine.gestion.dto.helper.DTOAcronimoHelper, int, int, java.lang.String)
	 */
	@Override
	public void generarAcronimo(DTOAcronimoHelper acronimo, int coor1, int coor2, String valor) {
		String[][] estructura = acronimo.getEstructuraAcronimos();
		
        for(int i = 0; i < 2; i++) {
        	for(int j = 0; j < 5; j++) {
        		if(estructura[i][j] != null && estructura[i][j].equals(valor)){
        			estructura[i][j] = null;
        			break;
        		} 
        	} 
        }
        estructura[coor1][coor2] = valor;
		
        if (valor.equals("s1")) {
    		Map <String, String> relacion = acronimo.getRelacionAcronimos();
    		relacion.put("s1", acronimo.getSepa1());
        }
        if (valor.equals("s2")) {
    		Map <String, String> relacion = acronimo.getRelacionAcronimos();
    		relacion.put("s2", acronimo.getSepa2());
        }
        
        generarVistaPrevia(acronimo);
   
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#generarAcronimoBase(mx.ine.gestion.dto.helper.DTOAcronimoHelper)
	 */
	@Override
	public String generarAcronimoBase(DTOAcronimoHelper acronimo) {
		String[][] estructura = acronimo.getEstructuraAcronimos();
		String acronimoBase = "";

    	for(int j = 0; j < 5; j++) {
    		log.info("[" + 1 + "]" + "[" + j + "]" + estructura[1][j]);
    		if(estructura[1][j] != null){
    			if(estructura[1][j].equals("acr")) {
    				acronimoBase += "acr=" + acronimo.getAcronimo();
    			} else if(estructura[1][j].equals("s1")) {
    				acronimoBase += "sep=" + acronimo.getSepa1();
    			} else if(estructura[1][j].equals("ani")) {
    				acronimoBase += "ani=" + acronimo.getFormatoAnioSeleccionado();
    			} else if(estructura[1][j].equals("s2")) {
    				acronimoBase += "sep=" + acronimo.getSepa2();
    			} else if(estructura[1][j].equals("num")) {
    				acronimoBase += "num=" + acronimo.getFormatoNumeroSeleccionado();
    			}
     		} else {
     			acronimoBase += "sep=*";
     		}
    		if(j != 4) acronimoBase += "|";
        }
    	log.error(acronimoBase);
    	return acronimoBase;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#generarVistaPrevia(mx.ine.gestion.dto.helper.DTOAcronimoHelper)
	 */
	@Override
	public void generarVistaPrevia(DTOAcronimoHelper acronimo) {
		String[][] estructura = acronimo.getEstructuraAcronimos();
		String vistaPrevia = "";

    	for(int j = 0; j < 5; j++) {
    		log.info("[" + 1 + "]" + "[" + j + "]" + estructura[1][j]);
    		if(estructura[1][j] != null){
    			if(estructura[1][j].equals("acr")) {
    				vistaPrevia += acronimo.getAcronimo();
    			} else if(estructura[1][j].equals("s1")) {
    				vistaPrevia += acronimo.getSepa1();
    			} else if(estructura[1][j].equals("ani")) {
    				vistaPrevia += obtenerAnio(acronimo.getFormatoAnioSeleccionado());
    			} else if(estructura[1][j].equals("s2")) {
    				vistaPrevia += acronimo.getSepa2();
    			} else if(estructura[1][j].equals("num")) {
    				vistaPrevia += obtenerNumero(acronimo.getFormatoNumeroSeleccionado());
    			}
     		} 
        }
        
        acronimo.setVistaPreviaAcronimo(vistaPrevia);
	}

	public String obtenerAnio(String posAnio) {
		if(posAnio == null ) {
			return posAnio;
		}
		String anio = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		if(posAnio.equals("00")) {
			return anio.substring(2,4);
		} else if(posAnio.equals("0000")) {
			return anio;
		}
		return anio;
	}
	
	public String obtenerNumero(String numero) {
		if(numero == null) {
			return "";
		} else if (numero.equals("*")) {
			return "1";			
		} else if (numero.equals("0")) {
			return "00001";
		} else {
			return "";
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#inicializarAcronimo(mx.ine.gestion.dto.helper.DTOAcronimoHelper)
	 */
	@Override
	public void inicializarAcronimo(DTOAcronimoHelper acronimo) {
		
		acronimo.setIdTipoDocumentoSeleccionado(null);
		acronimo.setFormatoNumeroSeleccionado(null);
		acronimo.setFormatoAnioSeleccionado(null);
			
		String[][] estructura = new String[2][5];
		
		estructura[0][0] = "acr";
		estructura[0][1] = "s1";
		estructura[0][2] = "ani";
		estructura[0][3] = "s2";
		estructura[0][4] = "num";
		estructura[1][0] = null;
		estructura[1][1] = null;
		estructura[1][2] = null;
		estructura[1][3] = null;
		estructura[1][4] = null;
		
		acronimo.setEstructuraAcronimos(estructura);
		
		Map <String, String> relacion = new HashMap<String, String>();
		relacion.put("acr", "Acrónimo");
		relacion.put("s1", "/");
		relacion.put("num", "Posición número");
		relacion.put("s2", "-");
		relacion.put("ani", "Posición año");

		// Evita que el valor de la caja de texto sea seteado por default
		acronimo.setSepa1("-");
		acronimo.setSepa2("-");
		
		acronimo.setRelacionAcronimos(relacion);
		
		acronimo.setVistaPreviaAcronimo("");
		
		acronimo.setAcronimo("");
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#inicializarAcronimoGuardado(mx.ine.gestion.dto.db.DTOAcronimoEntity, mx.ine.gestion.dto.helper.DTOAcronimoHelper)
	 */
	@Override
	public void inicializarAcronimoGuardado(DTOAcronimoEntity acronimo, DTOAcronimoHelper acronimoHelper) {
		String agrupacion = acronimo.getAcronimoAgrupacion();
		String[][] estructura = new String[2][5];
		
		Map <String, String> relacion = new HashMap<String, String>();
		relacion.put("acr", "ACRÓNIMO DEL DOCUMENTO");
		relacion.put("s1", "");
		relacion.put("ani", "AÑO");
		relacion.put("s2", "");
		relacion.put("num", "NUMERACIÓN");
		
		String[] aux1 = agrupacion.split("\\|");
		int contSep = 1;
		for (int i = 0; i < 5; i++) {
			String[] aux2 = aux1[i].split("=");
			if(aux2[0].equals("acr")) {
				estructura[1][i] = aux2[0];
				acronimoHelper.setAcronimo(aux2[1]);
			} else if(aux2[0].equals("ani")) {
				estructura[1][i] = aux2[0];
				acronimoHelper.setFormatoAnioSeleccionado(aux2[1]);
			} else if(aux2[0].equals("num")) {
				estructura[1][i] = aux2[0];
				acronimoHelper.setFormatoNumeroSeleccionado(aux2[1]);
			} else if(aux2[0].equals("sep")) {
				if(contSep == 1) {
					if (aux2[1].equals("*")) {
						estructura[0][1] = "s1";
						acronimoHelper.setSepa1("");	
					} else {
						estructura[1][i] = "s1";
						relacion.put("s1", aux2[1]);
						acronimoHelper.setSepa1(aux2[1]);					
					}
				} else if(contSep == 2) {
					if (aux2[1].equals("*")) {
						estructura[0][3] = "s2";
						acronimoHelper.setSepa2("");	
					} else {
						estructura[1][i] = "s2";
						relacion.put("s2", aux2[1]);
						acronimoHelper.setSepa2(aux2[1]);
					}
				}
				contSep += contSep;
			}
		}
		
		acronimoHelper.setRelacionAcronimos(relacion);
		acronimoHelper.setEstructuraAcronimos(estructura);
		acronimoHelper.setIdTipoDocumentoSeleccionado(acronimo.getIdTipoDocumento());
		
		generarVistaPrevia(acronimoHelper);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#mostrarMensajeGrowl(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto) {
		if(tipo.equals(Utilidades.mensajeProperties("constante_growl_info"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_advertencia"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_exito"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, texto));			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHValidacionInterface#enviarError(java.lang.Exception, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void enviarError(Exception e, String clase, String metodo, String mensaje) {
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		log.error("<=================== Clase: " + clase + " , Método: " + metodo);
		if(!mensaje.equals("")) {
			log.error(mensaje);			
		}
		if (usuarioLogueado != null) {
			log.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());			
		}
		log.error("",e);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#obtenerDescripcionDeEntidades(java.util.List, java.lang.Integer)
	 */
	@Override
	public String obtenerDescripcionDeEntidades(List<DTOEstadosEntity> estados, Integer idEstado) {
		for (DTOEstadosEntity estado : estados) {
			if (estado.getIdEstado().equals(idEstado)) {
				return estado.getNombreEstado();
			}
		}
		return "";
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#obtenerDescripcionTipoArea(java.util.List, java.lang.Integer)
	 */
	@Override
	public String obtenerDescripcionTipoArea(List<DTOCTipoAreaEntity> tiposArea, Integer idTipoArea) {
		for (DTOCTipoAreaEntity tipoArea : tiposArea) {
			if (tipoArea.getIdTipoArea().equals(idTipoArea)) {
				return tipoArea.getDescripcion();
			}
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHAcronimosInterface#obtenerDescripcionDeListaAreas(java.util.List, java.lang.Integer)
	 */
	@Override
	public String obtenerDescripcionDeListaAreas(List<DTOCAreaEntity> areas, Integer idArea) {
		for (DTOCAreaEntity area : areas) {
			if (area.getIdArea().equals(idArea)) {
				return area.getDescripcion();
			}
		}
		return "";
	}

}