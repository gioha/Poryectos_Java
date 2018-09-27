 /**
 * @(#)FormRecuentoTotal.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.dto.form;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;

 /**
 * Objeto de Transferencia de Datos propio del módulo de recuento total que contiene
 * los valores del formulario y variables requeridad para el negocio del módulo
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
public class FormRecuentoTotal implements Serializable{
    /**
     * Elemento para la serialización de los objetos generados por esta clase
     */
    private static final long serialVersionUID = -1855738439290552123L;
    /**
     * Usuario logueado
     */
    private DTOUsuarioLogin usuario;
    /**
     * Ambito del detalle
     */
    private EnumAmbitoDetalleProceso ambito;
    /**
     * Lista de candidaturas
     */
    private List<DTOCandidatoWS> listaCandidaturas;
    /**
     * Id candidatura seleccionada
     */
    private Integer idCandidatura;
    /**
     * Candidatura seleccionada
     */
    private DTOCandidatoWS candidatura;
    /**
     * Lista de municipios
     */
    private List<HLPEntornoGeografico> listaMunicipio;
    /**
     * Id municipio seleccionado
     */
    private Integer idMunicipio;
    /**
     * Título de la tabla
     */
    private String tituloTabla;
    /**
     * Lista de geografía dependiendo la candidatura
     */
    private List<HLPEntornoGeografico> listaGeografia;
    /**
     * Lista de registros seleccionados
     */
    private List<HLPEntornoGeografico> listaGeografiaSelect;
    
    /**
     * Método encargado de validar dato requerido en el combo de candidaturas
     *
     * @param context   el contexto de la aplicación.
     * @param component componente de la pantalla (combo).
     * @param value     valor del componente seleccionado.
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void validateCandidatura(FacesContext context, UIComponent component, Object value) {
        Integer a = (Integer) value;
        if(a.intValue() < 0){
            idCandidatura = null;
            candidatura = null;
            listaMunicipio = null;
            idMunicipio = null;
            listaGeografia = null;
            listaGeografiaSelect = null;
            // Mensaje
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Dato requerido");
            context.addMessage(component.getClientId(), message);
            context.renderResponse();
        }
    }
    
    /**
     * Método encargado de validar dato requerido en el combo de candidaturas
     *
     * @param context   el contexto de la aplicación.
     * @param component componente de la pantalla (combo).
     * @param value     valor del componente seleccionado.
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void validateMunicipio(FacesContext context, UIComponent component, Object value) {
        Integer a = (Integer) value;
        if(a.intValue() < 0){
            idMunicipio = null;
            listaGeografia = null;
            listaGeografiaSelect = null;
            // Mensaje
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Dato requerido");
            context.addMessage(component.getClientId(), message);
            context.renderResponse();
        }
    }

    /**
     * Método que obtiene el valor de el atributo usuario
     *
     * @return DTOUsuarioLogin : valor que tiene el atributo usuario
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public DTOUsuarioLogin getUsuario() {
        return usuario;
    }

    /**
     * Método que ingresa el valor de el atributo usuario
     *
     * @param usuario : valor que ingresa a el atributo usuario
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setUsuario(DTOUsuarioLogin usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que obtiene el valor de el atributo listaCandidaturas
     *
     * @return List<DTOCandidatoWS> : valor que tiene el atributo listaCandidaturas
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public List<DTOCandidatoWS> getListaCandidaturas() {
        return listaCandidaturas;
    }

    /**
     * Método que ingresa el valor de el atributo listaCandidaturas
     *
     * @param listaCandidaturas : valor que ingresa a el atributo listaCandidaturas
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setListaCandidaturas(List<DTOCandidatoWS> listaCandidaturas) {
        this.listaCandidaturas = listaCandidaturas;
    }

    /**
     * Método que obtiene el valor de el atributo candidatura
     *
     * @return DTOCandidatoWS : valor que tiene el atributo candidatura
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public DTOCandidatoWS getCandidatura() {
        return candidatura;
    }

    /**
     * Método que ingresa el valor de el atributo candidatura
     *
     * @param candidatura : valor que ingresa a el atributo candidatura
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setCandidatura(DTOCandidatoWS candidatura) {
        this.candidatura = candidatura;
    }

    /**
     * Método que obtiene el valor de el atributo idCandidatura
     *
     * @return Integer : valor que tiene el atributo idCandidatura
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public Integer getIdCandidatura() {
        return idCandidatura;
    }

    /**
     * Método que ingresa el valor de el atributo idCandidatura
     *
     * @param idCandidatura : valor que ingresa a el atributo idCandidatura
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setIdCandidatura(Integer idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    /**
     * Método que obtiene el valor de el atributo listaMunicipio
     *
     * @return List<HLPEntornoGeografico> : valor que tiene el atributo listaMunicipio
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public List<HLPEntornoGeografico> getListaMunicipio() {
        return listaMunicipio;
    }

    /**
     * Método que ingresa el valor de el atributo listaMunicipio
     *
     * @param listaMunicipio : valor que ingresa a el atributo listaMunicipio
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setListaMunicipio(List<HLPEntornoGeografico> listaMunicipio) {
        this.listaMunicipio = listaMunicipio;
    }

    /**
     * Método que obtiene el valor de el atributo idMunicipio
     *
     * @return Integer : valor que tiene el atributo idMunicipio
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * Método que ingresa el valor de el atributo idMunicipio
     *
     * @param idMunicipio : valor que ingresa a el atributo idMunicipio
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    /**
     * Método que obtiene el valor de el atributo listaGeografia
     *
     * @return List<HLPEntornoGeografico> : valor que tiene el atributo listaGeografia
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public List<HLPEntornoGeografico> getListaGeografia() {
        return listaGeografia;
    }

    /**
     * Método que ingresa el valor de el atributo listaGeografia
     *
     * @param listaGeografia : valor que ingresa a el atributo listaGeografia
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setListaGeografia(List<HLPEntornoGeografico> listaGeografia) {
        this.listaGeografia = listaGeografia;
    }

    /**
     * Método que obtiene el valor de el atributo listaGeografiaSelect
     *
     * @return List<HLPEntornoGeografico> : valor que tiene el atributo listaGeografiaSelect
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public List<HLPEntornoGeografico> getListaGeografiaSelect() {
        return listaGeografiaSelect;
    }

    /**
     * Método que ingresa el valor de el atributo listaGeografiaSelect
     *
     * @param listaGeografiaSelect : valor que ingresa a el atributo listaGeografiaSelect
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setListaGeografiaSelect(
            List<HLPEntornoGeografico> listaGeografiaSelect) {
        this.listaGeografiaSelect = listaGeografiaSelect;
    }

    /**
     * Método que obtiene el valor de el atributo ambito
     *
     * @return EnumAmbitoDetalleProceso : valor que tiene el atributo ambito
     * 
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public EnumAmbitoDetalleProceso getAmbito() {
        return ambito;
    }

    /**
     * Método que ingresa el valor de el atributo ambito
     *
     * @param ambito : valor que ingresa a el atributo ambito
     *
     * @author José Antonio López Torres
     * @since 12/05/2017
     */
    public void setAmbito(EnumAmbitoDetalleProceso ambito) {
        this.ambito = ambito;
    }

    /**
     * Método que obtiene el valor de el atributo tituloTabla
     *
     * @return String : valor que tiene el atributo tituloTabla
     * 
     * @author José Antonio López Torres
     * @since 15/05/2017
     */
    public String getTituloTabla() {
        return tituloTabla;
    }

    /**
     * Método que ingresa el valor de el atributo tituloTabla
     *
     * @param tituloTabla : valor que ingresa a el atributo tituloTabla
     *
     * @author José Antonio López Torres
     * @since 15/05/2017
     */
    public void setTituloTabla(String tituloTabla) {
        this.tituloTabla = tituloTabla;
    } 
}
