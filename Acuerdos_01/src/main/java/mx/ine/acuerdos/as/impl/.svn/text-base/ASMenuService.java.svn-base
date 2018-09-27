/**
 * 
 */
package mx.ine.acuerdos.as.impl;

import mx.ine.acuerdos.as.ASMenuServiceInterface;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author INE
 *
 */
@Scope("prototype")
@Component("asMenu")
public class ASMenuService implements ASMenuServiceInterface{

    public static final Logger log = Logger.getLogger(ASMenuService.class);

    @Override
    public String generaMenuLateral(Integer idProceso, Integer idDetalle, Integer idSistema,
        Integer idEstado, Integer idMunDto, String grupoSistema, String ambitoCaptura, String porSeccion) {
        log.info("Iniciando consulta del men�");
        /** Generamos par�metros a enviar al servicio **/
        String menuJSON = "";
        String params = "{";
        if (idProceso != null) {
            params += "\"idProceso\":" + idProceso + ",";
        }
        if (idDetalle != null) {
            params += "\"idDetalle\":" + idDetalle + ",";
        }
        if (idSistema != null) {
            params += "\"idSistema\":" + idSistema + ",";
        }
        if (idEstado != null) {
            params += "\"idEstado\":" + idEstado + ",";
        }

        if (idMunDto != null && ambitoCaptura !=null) {
        	
        	if(ambitoCaptura.equals("D")){
        		params += "\"idDistrito\":" + idMunDto + ",";
        		params += "\"idMunicipio\": " + null + ",";
        	}else {
        		params += "\"idDistrito\":" + null + ",";
        		params += "\"idMunicipio\":" + idMunDto + ",";
        	}
        }else  if (ambitoCaptura !=null) {
        	if(ambitoCaptura.equals("D")){
        		params += "\"idDistrito\":" + 0 + ",";
        		params += "\"idMunicipio\": " + null + ",";
        	}else {
        		params += "\"idDistrito\":" + null + ",";
        		params += "\"idMunicipio\":" + 0 + ",";
        	}
        }
        
        if(porSeccion!=null){
			params+="\"porSeccion\":\""+porSeccion+"\",";
        }
        
        if (grupoSistema != null && !grupoSistema.trim().equals("")) {
            params += "\"grupo\":\"" + grupoSistema + "\"";
        }

        params += "}";
        log.info("Consultando servicio con par�metros " + params);
        try {
            // Iniciamos llamada al servicio
            menuJSON = Utilidades.consultaWebService(Constantes.RUTA_WS_MENU_LATERAL, params);
            if (menuJSON != null) {
                log.info("Se obtuvo " + menuJSON);
            }
        } catch (Exception e) {
            log.error("Error al consultar el servicio web del men� lateral ", e);
        }
        return menuJSON;
    }

    /* (non-Javadoc)
     * @see mx.ine.supycap.as.ASMenuServiceInterface#generaMenuAcciones(int, java.lang.Integer, int, int, java.lang.String)
     */
    @Override
    public String generaMenuAcciones(Integer idProceso, Integer idSistema,  Integer idEstado, 
     		 Integer idDistrito, Integer idMunicipio, String grupo, Integer idDetalle, String porSeccion) {
        log.info("Iniciando consulta del men�");
        /** Generamos par�metros a enviar al servicio **/
        String menuJSON = "";
        String params = "{";
        
            params += "\"idProceso\":" + idProceso + ",";
            params += "\"idSistema\":" + idSistema + ",";
            params += "\"idEstado\":" + idEstado + ",";
            params += "\"idDistrito\":" + idDistrito + ",";
            params += "\"idMunicipio\":" + idMunicipio + ",";////      
            params += "\"grupo\":\"" + grupo + "\",";
            params += "\"idDetalle\":" + idDetalle + ",";
            params += "\"porSeccion\":\"" + porSeccion + "\"";
        

        params += "}";
        log.info("Consultando servicio con par�metros " + params);
        try {
            // Iniciamos llamada al servicio
            menuJSON = Utilidades.consultaWebService(Constantes.RUTA_WS_MENU_LATERAL, params);
            if (menuJSON != null) {
                log.info("Se obtuvo " + menuJSON);
            }
        } catch (Exception e) {
            log.error("Error al consultar el servicio web del men� lateral ", e);
        }
        return menuJSON;
    }
}
