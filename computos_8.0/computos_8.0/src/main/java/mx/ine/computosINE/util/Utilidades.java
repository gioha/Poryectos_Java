/**
 * @(#)Utilidades.java 06/06/2016
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.ine.common.ws.administracion.dto.response.DTODetalleProcesoWS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("util")
public class Utilidades implements Serializable
{
	/**
	 * Elemento necesario para la serialización de los objetos generados de esta
	 * clase.
	 */
	private static final long serialVersionUID = 1686800470041866721L;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Log LOGGER = LogFactory.getLog(Utilidades.class);
	
	private int flag;
	
	// PAra utlidades en español
	public static final Locale MEX = new Locale("es", "MX");
	
	/**
	 * Método que extrae una variable de properties para mostrar los mensajes
	 *
	 * @param String : Identificador del valor a extraer
	 * @return String : Mensaje en forma de cadena
	 *
	 * @author Israel Vazquez Jimenez
	 * @since 06/06/2016
	 */
	public static String mensajeProperties(String llave) {
		ResourceBundleMessageSource messageSource = null;
		try {
			messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils.getApplicationContext().getBean("messageSource"));
			return messageSource.getMessage(llave, null, null);  
		} catch (Exception e) {
			LOGGER.error("Error Utilidades -  mensajeProperties()", e);
			return null;
		}
	}

	/**
	 * Método que filtra los detalles del proceso de acuerdo al identificador de
	 * proceso enviado.
	 *
	 * @param listDetalleProcesoWS
	 * @param idProceso
	 * @return Lista : lista filtrada por proceso
	 *
	 * @author Helaine Flores Cervantes
	 * @since 01/12/2016
	 */
	public List<DTODetalleProcesoWS> filtrarDetalleProceso(List<DTODetalleProcesoWS> listDetalleProcesoWS, Integer idProceso) {
		List<DTODetalleProcesoWS> removedList;
		if (listDetalleProcesoWS != null) {
			removedList = new ArrayList();

			for (DTODetalleProcesoWS detalleProcesoWS : listDetalleProcesoWS) {

				if (!detalleProcesoWS.getIdProcesoElectoral().equals(idProceso)) {

					removedList.add(detalleProcesoWS);
				}
			}
			listDetalleProcesoWS.removeAll(removedList);
		}

		return listDetalleProcesoWS;
	}

	/**
	 * Método encargado de validar los datos del menú lateral
	 * 
	 * @param json : json obtenido del ws
	 * @return Integer : codigo
	 *
	 * @author José Antonio López Torres 
	 * @since 09/02/2017
	 */
	public static Integer validaDatosMenu(String json) {
		try {
			JSONObject menuJson = new JSONObject(json);
			// /Obtenemos la lista principal
			Integer code = (Integer) menuJson.getInt("code");
			return code;
		} catch (Exception e) {
			LOGGER.debug("Se obtuvo la siguiente observacion al obtner el menu", e);
			return 500;
		}
	}

	/**
	 * Método encargado de enviar un mensaje al menú lateral mediante
	 * una llamada a una función javascript
	 * 
	 * @param mensaje : Mensaje a enviar
	 *
	 * @author José Antonio López Torres
	 * @since 09/02/2017
	 */
	public static void enviaMensajeMenuLateral(String mensaje) {
		try {
			RequestContext.getCurrentInstance().execute(
					"enviaMensajeMenu(\"" + mensaje + "\")");
		} catch (Exception e) {
			LOGGER.error("Error al contactar al menú lateral ", e);
		}
	}
	/**
	 * Metodo encargado de transformar un numero a su version en letras
	 * -numeros menores a mil millones (1,000,000,000)-
	 * @param numero El numero a convertir
	 * @param mayusculas Bandera que indica si se desea en mayusculas
	 * @return La cadena con el numero en su version en letras
	 */
	public static String convertirNumeroALetra(int numero, boolean mayusculas)
	{
		LOGGER.error("-> convertirNumeroALetra: " + numero);
		String numeroEnLetras = "";
		if( numero == 0 )
			numeroEnLetras = "cero";
		else
			numeroEnLetras = obtenerCentenasDeMillones(numero);

		if( mayusculas )
			numeroEnLetras = numeroEnLetras.toUpperCase();
		else
		{
			//Solo la primer letra ira en mayusculas, el resto, en minuculas
			numeroEnLetras = numeroEnLetras.substring(0,1).toUpperCase() + numeroEnLetras.substring(1, numeroEnLetras.length());
			return numeroEnLetras;
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 999,999,999
	 * @param numero El numero a convertir a cadena
	 * @return El numero en letras
	 */
	private static String obtenerCentenasDeMillones(int numero)
	{
		String numeroEnLetras = null;
		if( numero < 100000000 ) //0 <= numero < 100,000,000
			numeroEnLetras = obtenerDecenasDeMillones(numero);
		else
		{
			int millones = numero - (numero%1000000);//1,000,000
			numeroEnLetras = obtenerCentenas(millones/1000000, true) + " millones " + obtenerCentenasDeMiles(numero-millones, false);
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 99,999,999
	 * @param numero El numero a convertir a cadena
	 * @return El numero en letras
	 */
	private static String obtenerDecenasDeMillones(int numero)
	{
		String numeroEnLetras = null;
		if( numero < 10000000 ) //0 <= numero < 10,000,000
			numeroEnLetras = obtenerMillones(numero);
		else
		{
			int decenasDeMillon = numero - (numero%1000000);//1,000,000
			numeroEnLetras = obtenerDecenas(decenasDeMillon/1000000, true) + " millones " + obtenerCentenasDeMiles(numero%1000000, false);
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 9,999,999
	 * @param numero El numero a convertir a cadena
	 * @return El numero en letras
	 */
	private static String obtenerMillones(int numero)
	{
		String numeroEnLetras = null;
		if (numero < 1000000) //0 <= numero < 1,000,000
			numeroEnLetras = obtenerCentenasDeMiles(numero, false);
		else
		{
			if( 1000000 <= numero && numero < 2000000) //1,000,000 <= numero < 2,000,000
				numeroEnLetras = "un millón " + obtenerCentenasDeMiles(numero%1000000, false);
			else
			{
				int unidadesDeMillon = numero - (numero%1000000);
				numeroEnLetras = obtenerUnidades(unidadesDeMillon/1000000, false) + " millones " + obtenerCentenasDeMiles(numero%1000000, false);
			}
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 999,999
	 * @param numero El numero a convertir a cadena
	 * @param limitarUno Bandera que indica si se debe limitar la escritura del numero 1
	 * en caso de estar al final (para diferenciar cuando escribir un o uno)
	 * @return El numero en letras
	 */
	private static String obtenerCentenasDeMiles(int numero, boolean limitarUno)
	{
		String numeroEnLetras = null;
		if( numero < 100000 ) //0 <= numero < 100,000
			numeroEnLetras = obtenerDecenasDeMiles(numero, limitarUno);
		else
		{
			//Limitamos el UNO en los miles para que diga veintiun mil, treinta y un mil, cuarenta y un mil etc
			numeroEnLetras = obtenerCentenas(numero/1000, true) + " mil " + obtenerCentenas(numero%1000, limitarUno);
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 99,999
	 * @param numero El numero a convertir a cadena
	 * @param limitarUno Bandera que indica si se debe limitar la escritura del numero 1
	 * en caso de estar al final (para diferenciar cuando escribir un o uno)
	 * @return El numero en letras
	 */
	private static String obtenerDecenasDeMiles(int numero, boolean limitarUno)
	{
		String numeroEnLetras = null;
		if( numero < 10000 ) //numero < 10,000
			numeroEnLetras = obtenerMiles(numero, limitarUno);
		else
		{
			//Limitamos el UNO en los miles para que diga veintiun mil, treinta y un mil, cuarenta y un mil etc
			int decenas = numero - numero%1000;
			numeroEnLetras = obtenerDecenas(decenas/1000, true) + " mil " + obtenerCentenas(numero%1000, limitarUno);
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 9,999
	 * @param numero El numero a convertir a cadena
	 * @param limitarUno Bandera que indica si se debe limitar la escritura del numero 1
	 * en caso de estar al final (para diferenciar cuando escribir un o uno)
	 * @return El numero en letras
	 */
	private static String obtenerMiles(int numero, boolean limitarUno)
	{
		String numeroEnLetras = null;
		if (numero < 1000)
			numeroEnLetras = obtenerCentenas(numero, limitarUno);
		else
		{
			if( numero<2000 )
				numeroEnLetras = "mil " + obtenerCentenas(numero%1000, limitarUno);
			else
			{
				if( numero<10000 ) //menor a 10,000
					numeroEnLetras = obtenerDecenas(numero/1000, true) + " mil " + obtenerCentenas(numero%1000, limitarUno);
			}
		}
		
		return numeroEnLetras;
	}	
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 999
	 * @param numero El numero a convertir a cadena
	 * @param limitarUno Bandera que indica si se debe limitar la escritura del numero 1
	 * en caso de estar al final (para diferenciar cuando escribir un o uno)
	 * @return El numero en letras
	 */
	private static String obtenerCentenas(int numero, boolean limitarUno)
	{
		String numeroEnLetras = null;
		if (numero < 100)
			numeroEnLetras = obtenerDecenas(numero, limitarUno);
		else
		{
			if( numero%100==0 )
			{
				String[] letras = {"", "cien", "doscientos", "trescientos", "cuatrocientos", "quinientos", 
									"seiscientos", "setecientos", "ochocientos", "novecientos"};
				numeroEnLetras = letras[numero/100];
			}else
			{
				if( numero<200 )
					numeroEnLetras = "ciento " + obtenerDecenas(numero - 100, limitarUno);
				else
				{
					if( numero<300 )
						numeroEnLetras = "doscientos " + obtenerDecenas(numero - 200, limitarUno);
					else
					{
						if( numero<400 )
							numeroEnLetras = "trescientos " + obtenerDecenas(numero - 300, limitarUno);
						else
						{
							if( numero <= 500)
								numeroEnLetras = "cuatrocientos " + obtenerDecenas(numero - 400, limitarUno);
							else
							{
								if( numero<=600 )
									numeroEnLetras = "quinientos " + obtenerDecenas(numero - 500, limitarUno);
								else
								{
									if( numero<700 )
										numeroEnLetras = "seiscientos " + obtenerDecenas(numero - 600, limitarUno);
									else
									{
										if( numero<800 )
											numeroEnLetras = "setecientos " + obtenerDecenas(numero - 700, limitarUno);
										else
										{
											if( numero<900 )
												numeroEnLetras = "ochocientos " + obtenerDecenas(numero - 800, limitarUno);
											else
												numeroEnLetras = "novecientos " + obtenerDecenas(numero - 900, limitarUno);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return numeroEnLetras;	
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 99
	 * @param numero El numero a convertir a cadena
	 * @param limitarUno Bandera que indica si se debe limitar la escritura del numero 1
	 * en caso de estar al final (para diferenciar cuando escribir un o uno)
	 * @return El numero en letras
	 */
	private static String obtenerDecenas(int numero, boolean limitarUno)
	{
		String numeroEnLetras = null;
		if( numero<10 )
			numeroEnLetras = obtenerUnidades(numero, limitarUno);
		else
		{
			if( 10<=numero && numero<=19 )
			{
				String[] letras = {"diez","once","doce","trece","catorce","quince",
					"dieciseis","diecisiete","dieciocho","diecinueve"};
				numeroEnLetras = letras[numero%10];
			}else
			{
				String[] letras = {"","","veinte","treinta","cuarenta","cincuenta","sesenta","setenta",
					"ochenta","noventa"};
				if( numero%10==0 )
					numeroEnLetras = letras[(int)numero/10];
				else
				{
					if( numero<=29 )
						numeroEnLetras = "veinti" + obtenerUnidades(numero - 20, limitarUno);
					else
					{
						if( numero<=39 )
							numeroEnLetras = "treinta " + "y " + obtenerUnidades(numero - 30, limitarUno);
						else
						{
							if( numero <= 49)
								numeroEnLetras = "cuarenta " + "y " + obtenerUnidades(numero - 40, limitarUno);
							else
							{
								if( numero<=59 )
									numeroEnLetras = "cincuenta " + "y " + obtenerUnidades(numero - 50, limitarUno);
								else
								{
									if( numero<=69 )
										numeroEnLetras = "sesenta " + "y " + obtenerUnidades(numero - 60, limitarUno);
									else
									{
										if( numero<=79 )
											numeroEnLetras = "setenta " + "y " + obtenerUnidades(numero - 70, limitarUno);
										else
										{
											if( numero<=89 )
												numeroEnLetras = "ochenta " + "y " + obtenerUnidades(numero - 80, limitarUno);
											else
											{
												numeroEnLetras = "noventa " + "y " + obtenerUnidades(numero - 90, limitarUno);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return numeroEnLetras;
	}
	/**
	 * Metodo que transforma un numero a letras, entre 0 y 9
	 * @param numero El numero a convertir a cadena
	 * @param limitarUno Bandera que indica si se debe limitar la escritura del numero 1
	 * en caso de estar al final (para diferenciar cuando escribir un o uno)
	 * @return El numero en letras
	 */
	private static String obtenerUnidades(int numero, boolean limitarUno)
	{
		String numeroEnLetras = null;
		
		String[] letras = {"","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};
		numeroEnLetras = letras[numero];
		if( numero==1 && limitarUno )
			numeroEnLetras = "un";
		
		return numeroEnLetras;
	}
}
