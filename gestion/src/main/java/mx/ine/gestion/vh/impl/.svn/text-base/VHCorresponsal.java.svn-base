/**
 * @(#)VHCorresponsal.java 04/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import java.util.Calendar;

import mx.ine.gestion.dto.helper.DTOCapturaCorresponsalForm;
import mx.ine.gestion.vh.inter.VHCorresponsalInterface;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Interfaz que contiene la firma de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el módulo de corresponsable.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 04/01/2018
 */
@Component("vhCorresponsal")
@Scope("prototype")
public class VHCorresponsal implements VHCorresponsalInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCorresponsalInterface#validaInicioBusquedaCorresponsal(mx.ine.gestion.dto.helper.DTOCapturaCorresponsalForm)
	 */
	@Override
	public String validaInicioBusquedaCorresponsal(DTOCapturaCorresponsalForm informacion) {

		//1.- Validamos si se ingresaron valores en las fechas (porque no es requerido)
		boolean ingreso = false;
		if ((informacion.getDiaInicio() != null && !informacion.getDiaInicio().isEmpty()) ||
			(informacion.getDiaFin() != null && !informacion.getDiaFin().isEmpty()) ||
			(informacion.getMesInicio() != null && !informacion.getMesInicio().isEmpty()) ||
			(informacion.getMesFin() != null && !informacion.getMesFin().isEmpty()) ||
			(informacion.getAnioInicio() != null && !informacion.getAnioInicio().isEmpty()) ||
			(informacion.getAnioFin() != null && !informacion.getAnioFin().isEmpty())) {
			
			ingreso = true;
		}
		
		//2.- Si se ingresaron valores se validan que esten todos y que la fecha de inicio no sea mayor a la fecha fin
		if (ingreso) {

			if (informacion.getDiaInicio() == null || informacion.getDiaInicio().isEmpty()) {
				return "Ingresaste valores en los campos de fechas, falta capturar el día en la fecha de inicio";
			}
			
			if (informacion.getMesInicio() == null || informacion.getMesInicio().isEmpty()) {
				return "Ingresaste valores en los campos de fechas, falta capturar el mes en la fecha de inicio";
			}
			
			if (informacion.getAnioInicio() == null || informacion.getAnioInicio().isEmpty()) {
				return "Ingresaste valores en los campos de fechas, falta capturar el año en la fecha de inicio";
			}
			
			if (informacion.getDiaFin() == null || informacion.getDiaFin().isEmpty()) {
				return "Ingresaste valores en los campos de fechas, falta capturar el día en la fecha fin";
			}
			
			if (informacion.getMesFin() == null || informacion.getMesFin().isEmpty()) {
				return "Ingresaste valores en los campos de fechas, falta capturar el mes en la fecha fin";
			}
			
			if (informacion.getAnioFin() == null || informacion.getAnioFin().isEmpty()) {
				return "Ingresaste valores en los campos de fechas, falta capturar el año en la fecha de fin";
			}

			Calendar calendarioInicio = Calendar.getInstance();
			calendarioInicio.set(Integer.valueOf(informacion.getAnioInicio()), Integer.valueOf(informacion.getMesInicio()), Integer.valueOf(informacion.getDiaInicio()));
			
			Calendar calendarioFin = Calendar.getInstance();
			calendarioFin.set(Integer.valueOf(informacion.getAnioFin()), Integer.valueOf(informacion.getMesFin()), Integer.valueOf(informacion.getDiaFin()));

			if (calendarioFin.getTime().compareTo(calendarioInicio.getTime()) < 0) {

				return "La fecha de inicio no debe ser mayor a la fecha fin";
			}

			informacion.setFechaInicio(calendarioInicio.getTime());
			informacion.setFechaFin(calendarioFin.getTime());
		}
		
		return "";
	}
}
