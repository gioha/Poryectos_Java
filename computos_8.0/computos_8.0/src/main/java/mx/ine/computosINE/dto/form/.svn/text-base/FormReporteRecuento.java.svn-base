 /**
 * @(#)FormReporteRecuento.java 22/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.dto.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import mx.ine.computosINE.dto.reportes.DTOReporteRecuento;

 /**
 * 
 * @author José Antonio López Torres
 * @since 22/05/2017
 * @copyright Direccion de sistemas - INE
 */
public class FormReporteRecuento extends FormRecuentoTotal implements Serializable{

    /**
     * Elemento para la serialización de los objetos generados por esta clase
     */
    private static final long serialVersionUID = -4366797101746776960L;
    /**
     * Versión
     */
    private String version;
    /**
     * Lista con información del reporte
     */
    private List<DTOReporteRecuento> listaReporte;
    /**
     * Parámetros para manipular excel y pdf
     */
    private Map<String, Serializable> parametros;
    /**
     * Encabezado de la columna dependiendo el entorno
     */
    private String encabezadoColumn;
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void validateCandidatura(FacesContext context,
            UIComponent component, Object value) {
        Integer a = (Integer) value;
        if(a.intValue() < 0){
            setIdCandidatura(null);
            listaReporte = null;
            // Mensaje
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Dato requerido");
            context.addMessage(component.getClientId(), message);
            context.renderResponse();
        }
    }

    /**
     * Método que obtiene el valor de el atributo version
     *
     * @return String : valor que tiene el atributo version
     * 
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public String getVersion() {
        return version;
    }

    /**
     * Método que ingresa el valor de el atributo version
     *
     * @param version : valor que ingresa a el atributo version
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Método que obtiene el valor de el atributo listaReporte
     *
     * @return List<DTOReporteRecuento> : valor que tiene el atributo listaReporte
     * 
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public List<DTOReporteRecuento> getListaReporte() {
        return listaReporte;
    }

    /**
     * Método que ingresa el valor de el atributo listaReporte
     *
     * @param listaReporte : valor que ingresa a el atributo listaReporte
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void setListaReporte(List<DTOReporteRecuento> listaReporte) {
        this.listaReporte = listaReporte;
    }

    /**
     * Método que obtiene el valor de el atributo parametros
     *
     * @return Map<String,Serializable> : valor que tiene el atributo parametros
     * 
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public Map<String, Serializable> getParametros() {
        return parametros;
    }

    /**
     * Método que ingresa el valor de el atributo parametros
     *
     * @param parametros : valor que ingresa a el atributo parametros
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void setParametros(Map<String, Serializable> parametros) {
        this.parametros = parametros;
    }

    /**
     * Método que obtiene el valor de el atributo encabezadoColumn
     *
     * @return String : valor que tiene el atributo encabezadoColumn
     * 
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public String getEncabezadoColumn() {
        return encabezadoColumn;
    }

    /**
     * Método que ingresa el valor de el atributo encabezadoColumn
     *
     * @param encabezadoColumn : valor que ingresa a el atributo encabezadoColumn
     *
     * @author José Antonio López Torres
     * @since 22/05/2017
     */
    public void setEncabezadoColumn(String encabezadoColumn) {
        this.encabezadoColumn = encabezadoColumn;
    }

}
