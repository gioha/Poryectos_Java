 /**
 * @(#)BSDRecuentoTotalImpl.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.bsd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.as.ASRecuentoTotalInterface;
import mx.ine.computosINE.bsd.BSDRecuentoTotalInterface;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.dto.reportes.DTOReporteRecuento;
import mx.org.ine.servicios.exception.ApplicationException;

 /**
 * Clase que provee la implementación de la interface de manejo de negocio para el 
 * módulo de recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
@Component("bsdRecuentoTotal")
@Scope("prototype")
public class BSDRecuentoTotalImpl implements BSDRecuentoTotalInterface{
    
    @Autowired
    @Qualifier("asRecuentoTotal")
    private ASRecuentoTotalInterface as;

    /**
    * {@inheritDoc}
    */
    @Override
    public List<DTOCandidatoWS> obtenTipoCandidaturas(Integer idDetalle,
            Integer idEstado, Integer ambito) throws ApplicationException {
        return as.obtenTipoCandidaturas(idDetalle, idEstado, ambito);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<HLPEntornoGeografico> obtenGeografia(FormRecuentoTotal dto)
            throws ApplicationException {
        return as.obtenGeografia(dto);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void guardar(FormRecuentoTotal dto){
        as.guardar(dto);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<DTOCandidatoWS> obtenTipoCandidaturas(Integer idDetalle,
            Integer idEstado, Integer ambito, String version)
            throws ApplicationException {
        return as.obtenTipoCandidaturas(idDetalle, idEstado, ambito, version);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<DTOReporteRecuento> generaReporte(FormReporteRecuento dto)
            throws ApplicationException {
        return as.generaReporte(dto);
    }
}
