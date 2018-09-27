/**
 * @(#)Utilidades.java 06/06/2016
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.math.RoundingMode;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;

import mx.ine.acuerdos.seguridad.SSLUtilidades;
import mx.org.ine.servicios.utils.ApplicationContextUtils;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.sun.faces.component.visit.FullVisitContext;

@Scope("prototype")
@Component("util")
public class Utilidades implements Serializable {

	/**
	 * Elemento necesario para la serializaci�n de los objetos generados de esta
	 * clase.
	 */
	private static final long serialVersionUID = 3607151473229631131L;

	/**
	 * Objeto para el servicio de bit�cora de mensajes de la aplicaci�n.
	 */
	public static final Logger logger = Logger.getLogger(Utilidades.class);
	// el default locale de Mexico
	private static final Locale DEFAULT_LOCALE = new Locale("es", "MX");

	/*
	 * --------------------------------------------------------------------------
	 * ---------------
	 */
	/*
	 * ------------------------------------- ATRIBUTOS
	 * -----------------------------------------
	 */
	/*
	 * --------------------------------------------------------------------------
	 * ---------------
	 */

	private static Locale mex = new Locale("es", "MX");

	private static LinkedHashMap<String, String> tipoSistema = new LinkedHashMap<String, String>();
	
	/**
	 * Ruta del gluster que viene por JNDI
	 */
	private static String		rutaGlusterFS;

	/*
	 * --------------------------------------------------------------------------
	 * ---------------
	 */
	/*
	 * --------------------------------------- METODOS
	 * ----------------------------------------
	 */
	/*
	 * --------------------------------------------------------------------------
	 * ---------------
	 */

	
	public String ordinalesToCardinales(int num) {

		String Unidad[] = { "", "primero", "segundo", "tercero", "cuarto",
				"quinto", "sexto", "séptimo", "octavo", "noveno" };
		String Decena[] = { "", "décimo", "vigésimo", "trigésimo",
				"cuadragésimo", "quincuagésimo", "sexagésimo", "septuagésimo",
				"octogésimo", "nonaésimo" };
		String Centena[] = { "", "centésimo", "ducentésimo", "tricentésimo",
				" cuadringentésimo", " quingentésimo", " sexcentésimo",
				" septingentésimo", " octingentésimo", " noningentésimo" };

		int N = num;
		int u = N % 10;
		int d = (N / 10) % 10;
		int c = N / 100;
		String ordinario = "";

		if (N >= 100) {
			ordinario = Centena[c]+" "+Decena[d] + " " + Unidad[u];
		} else {
			if (N >= 10) {
				ordinario = Decena[d] + " " + Unidad[u] + " ";
			} else {
				ordinario = Unidad[N] + " ";
			}
		}
		return  ordinario.toUpperCase();

	}
	
	
	/**
	 * M�todo que obtene el listado de a�os para mostrar en los combos.
	 * 
	 * @param
	 * @return List<Integer> : listado de a�os
	 * @author Israel V�zquez Jim�nez
	 * @since 06/06/2016
	 */
	public static List<Integer> listaAnios() {

		/* Instancias */
		List<Integer> listaAnio = new ArrayList<Integer>();
		Date date = new Date();
		Calendar cal = null;
		Integer year = 0;
		Integer yearBase = 0;

		try {
			// Se obtiene el calendario
			cal = Calendar.getInstance();
			// Se agrega la fecha obtenida al calendario
			cal.setTime(date);
			// Se obtiene el a�o actual
			year = cal.get(Calendar.YEAR);

			// Se obtiene el a�o base
			yearBase = Integer
					.parseInt(mensajeProperties("constante_generica_year_base"));

			// Se genera la lista
			for (int i = yearBase; i <= year; i++) {
				listaAnio.add(i);
			}

			return listaAnio;
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-listaAnios =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que obtiene el listado de meses
	 * 
	 * @param
	 * @return List<SelectItem> : listado de meses
	 * @author Israel V�zquez jim�nez
	 * @since 06/06/2016 *
	 */
	public static List<SelectItem> listaMeses() {

		/* Instancias */
		List<SelectItem> listaMeses = new ArrayList<SelectItem>();

		try {
			// Se genera listado
			listaMeses.add(new SelectItem("1",
					mensajeProperties("etiqueta_mes_enero")));
			listaMeses.add(new SelectItem("2",
					mensajeProperties("etiqueta_mes_febrero")));
			listaMeses.add(new SelectItem("3",
					mensajeProperties("etiqueta_mes_marzo")));
			listaMeses.add(new SelectItem("4",
					mensajeProperties("etiqueta_mes_abril")));
			listaMeses.add(new SelectItem("5",
					mensajeProperties("etiqueta_mes_mayo")));
			listaMeses.add(new SelectItem("6",
					mensajeProperties("etiqueta_mes_junio")));
			listaMeses.add(new SelectItem("7",
					mensajeProperties("etiqueta_mes_julio")));
			listaMeses.add(new SelectItem("8",
					mensajeProperties("etiqueta_mes_agosto")));
			listaMeses.add(new SelectItem("9",
					mensajeProperties("etiqueta_mes_septiembre")));
			listaMeses.add(new SelectItem("10",
					mensajeProperties("etiqueta_mes_octubre")));
			listaMeses.add(new SelectItem("11",
					mensajeProperties("etiqueta_mes_noviembre")));
			listaMeses.add(new SelectItem("12",
					mensajeProperties("etiqueta_mes_diciembre")));

			return listaMeses;
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-listaMeses =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que valida una fecha
	 * 
	 * @param Integer
	 *            : D�a
	 * @param Integer
	 *            : Mes
	 * @param Integer
	 *            : A�o
	 * @return Integer : 0 - fecha correcta
	 * @author Israel V�zquez jim�nez
	 * @since 06/06/2016 *
	 */
	public static Integer validaFecha(Integer dia, Integer mes, Integer anio) {
		Integer valido = 0;
		try {
			if (dia > 31) {
				valido = 1;
			} else {
				if ((mes == 4 || mes == 6 || mes == 9 || mes == 11)
						&& (dia > 30)) {
					valido = 2;
				} else {
					if (mes == 2 && bisiesto(anio) && dia > 29) {
						valido = 3;
					} else if (mes == 2 && !bisiesto(anio) && dia > 28) {
						valido = 4;
					}
				}
			}
			return valido;
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-validaFecha =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return valido;
		}
	}

	/**
	 * M�todo que indica si un a�o es bisiesto
	 * 
	 * @param Integer
	 *            : A�o
	 * @return Boolean : true - es bisiesto false - no es bisiesto
	 * @author Israel V�zquez jim�nez
	 * @since 06/06/2016
	 */
	private static Boolean bisiesto(Integer anio) {
		try {
			if (anio % 400 == 0) {
				return true;
			} else {
				if (anio % 4 == 0 && anio % 100 != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-validaFecha =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que devuele un java.util.Date desde un String en formato
	 * dd-MM-yyyy hh:
	 * 
	 * @param String
	 *            : D�a
	 * @param String
	 *            : Mes
	 * @param String
	 *            : A�o
	 * @param String
	 *            : Hora
	 * @param String
	 *            : Minuto
	 * @return Date : Fecha
	 * @author Israel V�zquez jim�nez
	 * @since 06/06/2016
	 */
	public static Date stringToDate(String dia, String mes, String anio,
			String hora, String minutos) throws ParseException {

		/* Instancias */
		SimpleDateFormat formatoDelTexto;
		StringBuffer fechaString = new StringBuffer();
		Date fechaResultado = null;

		try {
			if (hora != null && minutos != null) {
				formatoDelTexto = new SimpleDateFormat("dd/MM/yy HH:mm", mex);
			} else {
				formatoDelTexto = new SimpleDateFormat("dd/MM/yy", mex);
			}

			fechaString.append(dia);
			fechaString.append("/");
			fechaString.append(mes);
			fechaString.append("/");
			fechaString.append(anio);

			if (hora != null && minutos != null) {
				fechaString.append(" ");
				fechaString.append(hora);
				fechaString.append(":");
				fechaString.append(minutos);
			}

			fechaResultado = formatoDelTexto.parse(fechaString.toString());
			return fechaResultado;

		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-stringToDate =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que devuele un java.util.Calendar desde un String en formato
	 * dd-MM-yyyy hh:
	 * 
	 * @param String
	 *            : D�a
	 * @param String
	 *            : Mes
	 * @param String
	 *            : A�o
	 * @param String
	 *            : Hora
	 * @param String
	 *            : Minuto
	 * @return Date : Fecha
	 * @author Israel V�zquez jim�nez
	 * @since 06/06/2016
	 */
	public static Calendar stringToCalendar(String dia, String mes,
			String anio, String hora, String minutos) throws ParseException {

		/* Instancias */
		SimpleDateFormat formatoDelTexto;
		StringBuffer fechaString = new StringBuffer();
		Calendar fechaResultado = Calendar.getInstance();

		try {
			if (hora != null && minutos != null) {
				formatoDelTexto = new SimpleDateFormat("dd/MM/yy HH:mm", mex);
			} else {
				formatoDelTexto = new SimpleDateFormat("dd/MM/yy", mex);
			}

			fechaString.append(dia);
			fechaString.append("/");
			fechaString.append(mes);
			fechaString.append("/");
			fechaString.append(anio);

			if (hora != null && minutos != null) {
				fechaString.append(" ");
				fechaString.append(hora);
				fechaString.append(":");
				fechaString.append(minutos);
			}

			fechaResultado
					.setTime(formatoDelTexto.parse(fechaString.toString()));
			return fechaResultado;

		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-stringToDate =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que extrae una variable de properties para mostrar los mensajes
	 * 
	 * @param String
	 *            : Identificador del valor a extraer
	 * @return String : Mensaje en forma de cadena
	 * @author Israel V�zquez Jim�nez
	 * @since 06/06/2016
	 */
	public static String mensajeProperties(String llave) {

		/* Instancias */
		ResourceBundleMessageSource messageSource = null;

		try {
			messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils
					.getApplicationContext().getBean("messageSource"));

			return messageSource.getMessage(llave, null, null);
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-mensajeProperties =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}
	
	
	
	
	
	

	/**
	 * Método para extraer el valor de una variable de properties, regresar un
	 * integers
	 * 
	 * @param String
	 *            llave
	 * @return Integer valor
	 */
	public static Integer valorConstanteProperties(String llave) {
		Integer valor = 0;
		String ValorString;
		ValorString = mensajeProperties(llave);
		try {
			valor = Integer.parseInt(ValorString);
		} catch (Exception e) {
			valor = 0;
		}
		return valor;
	}

	/**
	 * Este m�todo conviete un Date a un String de acuerdo al formato
	 * especificado. -Date= Thu Apr 24 14:29:41 CDT 2008 . -Formato=dd/MM/yyyy .
	 * -Fecha Formateada = 24/04/2008 . Los par�metros utilizados son: . 1.-
	 * Date fechaEnDate= Fecha obtenida de tipo Date . 2.- String formato=
	 * Formato que se le va a dar a la fecha . Ejemplo, de llamada al m�todo: .
	 * FormatoDateFecha(Date fecha, "dd/MMMM/yyyy"); . EL formato se debe dar en
	 * forma de cadena, con las siguientes claves. LETRA REPRESENTA EJEMPLOS y
	 * a�o yy -> 80 yyyy -> 1980 M mes MM -> 10 MMM -> oct MMMM -> octubre d dia
	 * d -> 3 dd -> 03 E Dia de la semana(Texto) E -> v EEE -> vie EEEE ->
	 * viernes a Am/pm a -> AM H hora (0-23) HH -> 00 h hora am/pm (1-12) hh ->
	 * 12 m minutos mm -> 00 s segundos ss -> 00 Otros menos usados. z Zona
	 * horaria 1 Z Zona horaria 2 w Semana del a�o W Semana del mes D Dia del
	 * a�o F Dia de la semana(Numero) k Hora (1-24) K Hora am/pm (0-11) S
	 * Milisegundos EJEMPLOS DE FECHAS (Se pueden insertar palabras entre '')
	 * "dd/MM/yy" -> 03/10/80 "dd.MM.yy" -> 03.10.80 "dd MM   yy" -> 03 10 80
	 * "dd/MM/yy HH:mm:ss" -> 03/10/80 00:30:00 "EEEE d 'de' MMMM" -> viernes 3
	 * de octube "d 'de' MMMM 'de' yyyy" -> 3 de Octubre de 2008
	 * "'Hoy es' EEEE 'y son las' KK:mm a" -> Hoy es viernes y son las 12:30 AM
	 * 
	 * @author Israel V�zquez Jim�nez.
	 * @param (Date fechaEnDate, String formato) .
	 * @return String.
	 * @throws IllegalArgumentException.
	 */
	public static String FormatoDateFecha(Date fechaEnDate, String formato)
			throws IllegalArgumentException {

		/* Instancias */
		Locale mex = new Locale("es", "MX");

		try {
			String fecha = "";

			if (fechaEnDate != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(formato, mex);

				fecha = formatter.format(fechaEnDate);
			}
			return fecha;
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-FormatoDateFecha =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que le quita los ceros a la izquierda de un n�mero
	 * 
	 * @param String
	 *            : N�mero
	 * @return Integer : N�mero formateado
	 * @author Israel V�zquez Jim�nez
	 * @since 06/06/2016
	 */
	public static Integer quitarCerosIzquierdaInteger(String numero) {

		/* instancias */
		Integer cadenaResultadoInt;

		try {
			if (numero != null) {
				cadenaResultadoInt = Integer.parseInt(numero);
				return cadenaResultadoInt;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-quitarCerosIzquierdaInteger =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que le quita los ceros a la izquierda de un n�mero
	 * 
	 * @param String
	 *            : N�mero
	 * @return String : N�mero formateado
	 * @author Israel V�zquez Jim�nez
	 * @since 06/06/2016
	 */
	public static String quitarCerosIzquierdaString(String numero) {

		/* instancias */
		Integer cadenaResultadoInt;
		String resultado;

		try {
			if (numero != null) {
				cadenaResultadoInt = Integer.parseInt(numero);
				resultado = String.valueOf(cadenaResultadoInt);

				return resultado;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-quitarCerosIzquierdaInteger =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * M�todo que obtiene el tipo de sistema
	 * 
	 * @param
	 * @return LinkedHashMap<String, String> : Lista de tipos de sistema
	 * @author Israel V�zquez Jim�nez
	 * @since 06/06/2016
	 */
	public static LinkedHashMap<String, String> tipoSistemaHashMap() {

		/* Instancias */

		try {
			tipoSistema.put(
					mensajeProperties("etiqueta_tipoSistema_electoral_sigla"),
					mensajeProperties("etiqueta_tipoSistema_electoral"));
			tipoSistema
					.put(mensajeProperties("etiqueta_tipoSistema_institucional_sigla"),
							mensajeProperties("etiqueta_tipoSistema_institucional"));

			return tipoSistema;
		} catch (Exception e) {
			logger.error(
					"Error:Utilidades-tipoSistemaHashMap =============================>>>>>>>>>>>>>>>>>",
					e);
			logger.error(e.toString());
			return null;
		}

	}

	public static String[] getRandomlyNames(final int characterLength,
			final int generateSize) {
		HashSet<String> list = new HashSet<String>();
		for (int i = 0; i < generateSize; ++i) {
			String name = null;
			do {
				name = org.apache.commons.lang.RandomStringUtils
						.randomAlphanumeric(org.apache.commons.lang.math.RandomUtils
								.nextInt(characterLength - 1) + 1);
			} while (list.contains(name));
			list.add(name);
		}
		return list.toArray(new String[] {});
	}

	/**
	 * M�todo encargado de consultar un servicio web cualquiera -Se asume que
	 * recibe par�metros de entrada en formato json
	 * {"idProceso":7,"idSistema":12
	 * ,"idEstado":1,"idDistrito":0,"grupo":"ELEC2015.CAPTURA.JL"} -Se asume que
	 * regresar� una respuesta en formato json
	 * 
	 * @param <code>rutaServicio</code> : String que contiene la ruta del
	 *        servicio web
	 * @param <code>parametros</code> : String de par�metros en formato json
	 **/
	public static String consultaWebService(String rutaServicio,
			String parametros) throws Exception {
		String respuesta = "";

		// Definimos el timeout a 3 segundos
		final HttpParams params = new BasicHttpParams();

		HttpConnectionParams.setConnectionTimeout(params, 3000);
		DefaultHttpClient httpClient = new DefaultHttpClient(params);
		/********** Linea para los certificados ********/
		httpClient = (DefaultHttpClient) SSLUtilidades.wrapClient(httpClient);
		if (httpClient != null) {

			HttpPost postRequest = new HttpPost(rutaServicio);
			StringEntity input = new StringEntity(parametros);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() == 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent()), "utf-8"));
			respuesta = null;

			respuesta = br.readLine();
			httpClient.getConnectionManager().shutdown();
		}

		return respuesta;

	}

	/**
	 * Helper method que revisa si un string es null o vacio
	 * 
	 * @param str
	 * @return True si el str es null o vacio, de lo contrario false
	 */
	public static boolean isEmpty(String str) {
		return !(str != null && !str.equals(""));
	}

	/**
	 * Compara que la fecha menor este antes que la fecha mayor.
	 * 
	 * @param menor
	 * @param mayor
	 * @return Resgres true si la primera fecha es menor que la segunda
	 */
	public static boolean estaAntes(Date menor, Date mayor) {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
		String fecha1 = formatoFecha.format(menor);
		String fecha2 = formatoFecha.format(mayor);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaMenor = null;
		Date fechaMayor = null;
		try {
			fechaMenor = formato.parse(fecha1);
			fechaMayor = formato.parse(fecha2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaMenor.before(fechaMayor);
	}

	/**
	 * @param primeraFecha
	 * @param segundaFecha
	 * @return Resgresa true si la primera fecha es mayor que la segunda
	 */
	public static boolean estaDespues(Date primeraFecha, Date segundaFecha) {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
		String fecha1 = formatoFecha.format(primeraFecha);
		String fecha2 = formatoFecha.format(segundaFecha);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fecha1ra = null;
		Date fecha2da = null;
		try {
			fecha1ra = formato.parse(fecha1);
			fecha2da = formato.parse(fecha2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fecha1ra.after(fecha2da);
	}

	/**
	 * Compara dos fechas exactamente por día, mes y año
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return Resgres true sii el día, mes y año de las dos fechas son iguales
	 */
	public static boolean comparaFechas(Date fecha1, Date fecha2) {
		boolean ret = false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fecha1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fecha2);

		if ((cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
				&& (cal1.get(Calendar.MONTH) + 1 == cal2.get(Calendar.MONTH) + 1)
				&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))) {
			ret = true;
		}

		return ret;
	}

	/**
	 * Extrae la variable del properties con parametros del archivo properties
	 * 
	 * @param llave
	 * @param parametros
	 * @return String
	 */
	public static String mensajeProperties(String llave, Object... parametros) {
		ResourceBundleMessageSource messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils
				.getApplicationContext().getBean("messageSource"));

		return messageSource.getMessage(llave, parametros, null);
	}

	/**
	 * Conviete un Date a un String de acuerdo a un formato
	 * 
	 * @param fecha
	 *            Date con la fecha a convertir
	 * @return formato String con la fecha convertida
	 */
	public static String dateToString(Date fecha) {
		String sFecha = "";

		try {
			if (fecha != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
						DEFAULT_LOCALE);
				sFecha = formatter.format(fecha);
			}
		} catch (Exception e) {
			return null;
		}

		return sFecha;
	}

	/**
	 * Valida que el día sea congruente al mes y año elegidos
	 * 
	 * @param d
	 * @param m
	 * @param a
	 * @return boolean
	 */
	public static boolean validaDia(String d, String m, String a) {
		Integer dia = Integer.parseInt(d);
		Integer mes = Integer.parseInt(m);
		Integer anio = Integer.parseInt(a);

		Integer ENERO_SUP = 31;
		// Se verifica si el año es bisiesto para ver el último día de febrero.
		Integer FEBRERO_SUP = ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) ? 29
				: 28;
		Integer MARZO_SUP = 31;
		Integer ABRIL_SUP = 30;
		Integer MAYO_SUP = 31;
		Integer JUNIO_SUP = 30;
		Integer JULIO_SUP = 31;
		Integer AGOSTO_SUP = 31;
		Integer SEPTIEMBRE_SUP = 30;
		Integer OCTUBRE_SUP = 31;
		Integer NOVIEMBRE_SUP = 30;
		Integer DICIEMBRE_SUP = 31;
		// SE VERIFICA SI ES AÑO BISIESTO PARA VER EL ÚLTIMO DÍA DE FEBRERO.
		if (dia <= 0) {
			return false;
		}
		switch (mes) {
		case 1:
			if (dia > ENERO_SUP) {
				return false;
			}
			break;
		case 2:
			if (dia > FEBRERO_SUP) {
				return false;
			}
			break;
		case 3:
			if (dia > MARZO_SUP) {
				return false;
			}
			break;
		case 4:
			if (dia > ABRIL_SUP) {
				return false;
			}
			break;
		case 5:
			if (dia > MAYO_SUP) {
				return false;
			}
			break;
		case 6:
			if (dia > JUNIO_SUP) {
				return false;
			}
			break;
		case 7:
			if (dia > JULIO_SUP) {
				return false;
			}
			break;
		case 8:
			if (dia > AGOSTO_SUP) {
				return false;
			}
			break;
		case 9:
			if (dia > SEPTIEMBRE_SUP) {
				return false;
			}
			break;
		case 10:
			if (dia > OCTUBRE_SUP) {
				return false;
			}
			break;
		case 11:
			if (dia > NOVIEMBRE_SUP) {
				return false;
			}
			break;
		case 12:
			if (dia > DICIEMBRE_SUP) {
				return false;
			}
			break;
		default:
			break;
		}

		return true;
	}

	/**
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param separador
	 * @return Date
	 */
	public static Date formatFecha(Integer dia, Integer mes, Integer anio,
			String separador) {
		return formatFecha(dia.toString(), mes.toString(), anio.toString(),
				separador);
	}

	/**
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param separador
	 * @return Date
	 */
	public static Date formatFecha(String dia, String mes, String anio,
			String separador) {

		String fecha = "";
		String dia1 = ((dia != null && dia != "") ? dia : "00");
		String mes1 = ((mes != null && mes != "") ? mes : "00");
		String anio1 = ((anio != null && anio != "") ? anio : "0000");
		fecha = dia1 + separador + mes1 + separador + anio1;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.parse(fecha);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return null;
	}

	/**
	 * Obtiene los años a partir de la fecha de nacimiento
	 * 
	 * @param fechaNacimiento
	 * @return int
	 */
	public static int obtieneEdadDeFechaNacimiento(Date fechaNacimiento) {
		Calendar calFN = Calendar.getInstance();
		Calendar fechaActual = Calendar.getInstance();
		calFN.setTime(fechaNacimiento);
		int año = fechaActual.get(Calendar.YEAR) - calFN.get(Calendar.YEAR);
		int mes = fechaActual.get(Calendar.MONTH) - calFN.get(Calendar.MONTH);
		int dia = fechaActual.get(Calendar.DATE) - calFN.get(Calendar.DATE);
		if (mes < 0 || (mes == 0 && dia < 0)) {
			año--;
		}
		return año;
	}

	/**
	 * Normaliza un numero, esto es. 1 --> 01 9 --> 09 10 --> 10 Definir un
	 * parametro para el radio
	 * 
	 * @param num
	 * @return String
	 */
	public static String normalizaNumero(int num) {

		String val = String.valueOf(num);

		if (val.length() == 1) {
			return "0" + val;
		} else if (val.length() > 1 && val.length() <= 2) {
			return val;
		}

		return val;
	}

	/**
	 * Mï¿½todo que encuentra un UIComponent de la vista por medio de su Id
	 * 
	 * @param String
	 *            : id del componente
	 * @return UIComponent : componente encontrado
	 ***/
	public static UIComponent findComponent(final String id) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();
		final UIComponent[] found = new UIComponent[1];

		try {
			if (root != null) {
				root.visitTree(new FullVisitContext(context),
						new VisitCallback() {
							@Override
							public VisitResult visit(VisitContext context,
									UIComponent component) {
								if (component.getId().equals(id)) {
									found[0] = component;
									return VisitResult.COMPLETE;
								}
								return VisitResult.ACCEPT;
							}
						});
			}
		} catch (Exception e) {
			logger.error("Error leer componentes ", e);
		}

		return found[0];
	}

	/**
	 * Conviete un Date a un String de acuerdo a un formato
	 * 
	 * @param fecha
	 *            Date con la fecha a convertir
	 * @return formato String con la fecha convertida
	 */
	public static String formateaFecha(Date fecha) {
		String sFecha = "";

		try {
			if (fecha != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(
						"dd/MMMMM/yyyy", DEFAULT_LOCALE);
				sFecha = formatter.format(fecha);
			}
		} catch (Exception e) {
			return null;
		}

		return sFecha;
	}

	public static String obtenTipoErrorConexion(int errorObtenido) {

		String mensajeError = "";

		String numeroErrorCadena = String.valueOf(errorObtenido);
		Integer longitudNumero = numeroErrorCadena.length();

		Integer numeroError = errorObtenido;

		Integer primerDigito = Double.valueOf(Math.pow(10, longitudNumero - 1))
				.intValue();
		Integer primerNumeroError = numeroError / primerDigito;

		switch (primerNumeroError) {
		case 2:
			mensajeError = "El usuario no existe o la contraseña es incorrecta, favor de verificar";
			break;
		case 3:
			mensajeError = "Se ha realizado un redireccionamiento de la URL, por favor comunicarse al CAU";
			break;
		case 4:
			mensajeError = "La solicitud realizada es incorrecta,debido a que no tiene permisos o el recurso no existe";
			break;
		case 5:
			mensajeError = "Ocurrió un error en la conexión,el servicio al que intenta acceder no esta disponible";
			break;
		}
		return mensajeError;

	}

	/**
	 * Método que identifica el tipo de cadena que se ingresa en el componente
	 * autocomplete 1.- Es un número (folio) 2.- Es una sola palabra 3.- Es una
	 * cadena de mas de una palabra
	 * 
	 * @param cadena
	 *            : Cadena ingresada por el usuario
	 * @return Integer : Tipo de cadena ingresada
	 * @since 12/09/2016
	 * @author Eduardo Pedroza Landa
	 */
	public static Integer defineTipoCadena(String cadena) {

		Integer tipoCadena = 0;
		try {
			Pattern patronNumero = Pattern.compile("\\d*");

			// Comprueba que la cadena no este vacia
			if (cadena != null && !cadena.isEmpty() && !cadena.equals("")) {
				Matcher mat = patronNumero.matcher(cadena);

				// verifica si es un número
				if (mat.matches()) {
					tipoCadena = 1;
				} else {
					int j = 0;
					String[] campos = cadena.split("\\s+");

					while (j < campos.length) {
						j++;
					}
					if (j == 1) { // Apellido Paterno
						tipoCadena = 2;
					} else if (j == 2) { // Apellido Paterno y Materno
						tipoCadena = 3;
					} else if (j > 2) { // Apellido Paterno , Materno y Nombre
						tipoCadena = 4;
					}
				}
			}

		} catch (Exception e) {
			logger.error("Error en Utilidades - defineTipoCadena " + e);
		}
		return tipoCadena;
	}

	/**
	 * Método para obtener un arreglo de cadenas separadas por un espacio en
	 * blanco
	 * 
	 * @param cadena
	 *            : cadena ingresada por el usuario
	 * @return String[] : Arreglos con apellido paterno y materno
	 * @since 12/09/2016
	 * @author Eduardo Pedroza Landa
	 */
	public static String[] obtenArregloCadenaString(String cadena) {

		try {
			if (cadena != null && !cadena.isEmpty() && !cadena.equals("")) {
				String cadenas[] = cadena.split("\\ ");
				return cadenas;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error en Utilidades - obtenArregloCadenaString " + e);
			return null;
		}

	}

	/**
	 * Método que conviernte una fecha en una cadena formateada
	 * 
	 * @param fecha
	 * @param bandera
	 * @return String
	 */
	public static String getFechaTextual(Date fecha, int bandera) {

		if (bandera == 1) {
			return formateaFecha("dd 'de' MMMMM 'de' yyyy", fecha);
		}
		return formateaFecha(fecha);
	}

	/**
	 * Conviete un Date a un String de acuerdo a un formato que se le indique
	 * 
	 * @param formato
	 * @param fecha
	 *            Date con la fecha a convertir
	 * @return formato String con la fecha convertida
	 */
	public static String formateaFecha(String formato, Date fecha) {
		String sFecha = "";

		try {
			if (fecha != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(formato,
						DEFAULT_LOCALE);
				sFecha = formatter.format(fecha);
			}
		} catch (Exception e) {
			logger.error("Error en formateaFecha :" + e.getMessage());			
		}

		return sFecha;
	}

	/**
	 * Para formatear las calificaciones flotantes de 3 decimales a un decimal
	 * 
	 * @param decimal
	 * @return
	 */
	public static String formateaCalificacion(double decimal) {

		DecimalFormat formateador = null;
		String mascara = "##.000";
		if (decimal <= 0) {
			mascara = "0.000";
		}

		if ((decimal % 1) == 0) {
			mascara = "##.000";
		}

		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		formateador = new DecimalFormat(mascara, dfs);
		formateador.setRoundingMode(RoundingMode.FLOOR);
		return formateador.format(decimal);
	}

	/**
	 * A partir de los valores de la casilla determina como se debe mostrar el
	 * nombre de esta
	 * 
	 * @author Isabel Espinoza Espinoza (isabel.espinozae@ine.mx)
	 * @version 1.0
	 * @since 23/09/2016
	 * @param tipoCasilla
	 * @param idCasilla
	 * @param extContigua
	 * @return String
	 */
	public static String formateaCasilla(String tipoCasilla, int idCasilla,
			int extContigua) {
		StringBuilder casilla = new StringBuilder();
		if (tipoCasilla.equals("B")) {
			return tipoCasilla;
		} else if (tipoCasilla.equals("C")) {
			return casilla.append(tipoCasilla).append(idCasilla).toString();
		} else {
			if (extContigua > 0) {
				return casilla.append(tipoCasilla).append(idCasilla)
						.append("C").append(extContigua).toString();
			}
			return casilla.append(tipoCasilla).append(idCasilla).toString();
		}
	}

	/**
	 * Método que verifica que un objeto sea nulo y le devuelve una cadena
	 * vacia. Ejemplo, de llamada al método: . validaNulosString(campo a
	 * validar);.
	 * 
	 * @since 26 / 09 / 2016
	 * @author José Alfredo Victoria Barbosa
	 * @param (String campo).
	 * @return String
	 */
	public static String validaNulos(Object campo) {
		String cadena = "";
		if (campo != null) {
			cadena = campo.toString().trim();
		}
		return cadena;
	}

	/**
	 * Método para verificar que un date no sobrepase la fecha actual
	 * 
	 * @author INE / José Alfredo Victoria Barbosa compararFechaConFechaActual()
	 * @param Date
	 *            fechaIngresar
	 * @return true sí fechaIngresar es igual a la fecha actual
	 * @since 06/ 10 / 2016
	 */
	public static boolean compararFechaConFechaActual(Date fechaIngresar) {
		boolean ret = false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fechaIngresar);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());

		if ((cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
				&& (cal1.get(Calendar.MONTH) + 1 == cal2.get(Calendar.MONTH) + 1)
				&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))) {
			ret = true;
		}

		return ret;
	}

	/**
	 * Método para verificar que un date no sobrepase la fecha actual
	 * 
	 * @author Dulce Magali Martinez Torres
	 * @param Date
	 *            fechaIngresar
	 * @return true sí fechaIngresar fecha actual, de lo contrario false
	 * @since 12/04/2017
	 */
	public static boolean compararFechaConFechaMayorIgual(Date fechaIngresar,
			Date fechaCompara) {
		boolean ret = false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fechaIngresar);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fechaCompara);

		logger.info("fechaIngresar : " + cal1.get(Calendar.YEAR) + "-"
				+ (cal1.get(Calendar.MONTH) + 1) + "-"
				+ cal1.get(Calendar.DAY_OF_MONTH));
		logger.info("fechaCompara : " + cal2.get(Calendar.YEAR) + "-"
				+ (cal2.get(Calendar.MONTH) + 1) + "-"
				+ cal2.get(Calendar.DAY_OF_MONTH));

		if (cal1.get(Calendar.YEAR) >= cal2.get(Calendar.YEAR)) {

			if ((cal1.get(Calendar.MONTH) + 1) > (cal2.get(Calendar.MONTH) + 1)) {
				ret = true;
			} else if ((cal1.get(Calendar.MONTH) + 1) == (cal2
					.get(Calendar.MONTH) + 1)) {

				if (cal1.get(Calendar.DAY_OF_MONTH) >= cal2
						.get(Calendar.DAY_OF_MONTH)) {
					ret = true;
				}

			}
		}

		return ret;
	}

	/**
	 * Método para verificar que un date no sobrepase la fecha actual
	 * 
	 * @author Dulce Magali Martinez Torres
	 * @param Date
	 *            fechaIngresar
	 * @return true sí fechaIngresar es menor o igual fecha actual, de lo
	 *         contrario false
	 * @since 12/04/2017
	 */
	public static boolean compararFechaConFechaMenorIgual(Date fechaIngresar,
			Date fechaCompara) {
		boolean ret = false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fechaIngresar);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fechaCompara);

		logger.info("fechaIngresar : " + cal1.get(Calendar.YEAR) + "-"
				+ (cal1.get(Calendar.MONTH) + 1) + "-"
				+ cal1.get(Calendar.DAY_OF_MONTH));
		logger.info("fechaCompara : " + cal2.get(Calendar.YEAR) + "-"
				+ (cal2.get(Calendar.MONTH) + 1) + "-"
				+ cal2.get(Calendar.DAY_OF_MONTH));

		if (cal1.get(Calendar.YEAR) <= cal2.get(Calendar.YEAR)) {

			if ((cal1.get(Calendar.MONTH) + 1) < (cal2.get(Calendar.MONTH) + 1)) {
				ret = true;
			} else if ((cal1.get(Calendar.MONTH) + 1) == (cal2
					.get(Calendar.MONTH) + 1)) {

				if (cal1.get(Calendar.DAY_OF_MONTH) <= cal2
						.get(Calendar.DAY_OF_MONTH)) {
					ret = true;
				}

			}
		}

		return ret;
	}

	/***
	 * Método que obtiene la fecha del servidor y la convierte al formato
	 * recibido.
	 * 
	 * @author INE / José Alfredo Victoria Barbosa fechaDiaHoy()
	 * @param String
	 *            formato
	 * @return String
	 * @since 07/ 10 / 2016
	 */
	public static String fechaDiaHoy(String formato) {
		try {
			String fecha = "";
			Date fechaActual = new Date();
			fecha = FormatoDateFecha(fechaActual, formato);
			return fecha;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Método para convierte una cadena en un String ,en otro formato
	 * espesificado de la cadena y lo regresa en DATE
	 * 
	 * @param fecha
	 *            String
	 * @param formato
	 *            String
	 * @return Date
	 */
	public static Date stingToDateFormatoEspesifico(String fecha, String formato) {
		/*
		 * El formato se da igual que en la funci�n mtDate2String Ejemplo Date
		 * fecha = mtString2Date("03/10/1980 1:30 PM","dd/MM/yyyy KK:mm a")
		 * fecha.toString() = "Fri Oct 03 13:30:00 CST 1980"
		 */

		if (fecha == null) {
			return null;
		}

		Locale mex = new Locale("es", "MX");
		SimpleDateFormat formateador = new SimpleDateFormat(formato, mex);
		ParsePosition pp = new ParsePosition(0);
		java.util.Date salida = new java.util.Date();
		try {
			salida = formateador.parse(fecha, pp);
		} catch (Exception e) {
			return null;
		}
		return salida;
	}

	/**
	 * Método que recibe una cadena con formato HH:mm y verifica que sea una
	 * hora válida
	 * 
	 * @param hora
	 * @return : true Hora válida, false hora no válida
	 * @author Israel G.M.
	 * @since 10/10/2016
	 */
	public static Boolean validacionFormatoHora(String hora) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setLenient(false);
		try {
			Date fecha = dateFormat.parse(hora);

			if (fecha != null) {
				return true;
			}
		} catch (ParseException e) {
			logger.error("Hora no válida");

		}
		return false;
	}

	/**
	 * Metodo que regresa un double con el numero de decimales que se necesiten
	 * 
	 * @param numeroDecimales
	 * @param decimal
	 * @return Double
	 */
	public static Double getReduceDecimales(int numeroDecimales, double decimal) {
		decimal = decimal * (java.lang.Math.pow(10, numeroDecimales));
		decimal = java.lang.Math.round(decimal);
		decimal = decimal / java.lang.Math.pow(10, numeroDecimales);
		return decimal;
	}

	/**
	 * @author José Alfredo Victoria Barbosa Método para obtener la fecha con el
	 *         nombre del mes
	 * @param String
	 *            fecha, formato dd/mm/aaaa
	 * @return Strin "dd de nombreMes del aaaa" (24 de octubre del 2016)
	 * @since 24 / 10 / 2016
	 */
	public static String obtenerFechaNombreMes(String fecha) {

		String[] split = fecha.split("/");

		String nombreMes = "";
		Integer mesInt = Integer.valueOf(split[1]);

		switch (mesInt) {
		case 1:
			nombreMes = "enero";
			break;
		case 2:
			nombreMes = "febrero";
			break;
		case 3:
			nombreMes = "marzo";
			break;
		case 4:
			nombreMes = "abril";
			break;
		case 5:
			nombreMes = "mayo";
			break;
		case 6:
			nombreMes = "junio";
			break;
		case 7:
			nombreMes = "julio";
			break;
		case 8:
			nombreMes = "agosto";
			break;
		case 9:
			nombreMes = "septiembre";
			break;
		case 10:
			nombreMes = "octubre";
			break;
		case 11:
			nombreMes = "noviembre";
			break;
		case 12:
			nombreMes = "diciembre";
			break;
		default:
			nombreMes = "mes desconocido";
			break;
		}

		return split[0] + " de " + nombreMes + " del " + split[2];
	}

	/**
	 * @author José Alfredo Victoria Barbosa Método para obtener el nombre del
	 *         mes
	 * @param String
	 *            mes
	 * @return Strin nombreMes
	 * @since 28 / 10 / 2016
	 */
	public static String obtenerNombreMes(String mes) {

		String nombreMes;
		Integer mesInt = Integer.valueOf(mes);

		switch (mesInt) {
		case 1:
			nombreMes = "enero";
			break;
		case 2:
			nombreMes = "febrero";
			break;
		case 3:
			nombreMes = "marzo";
			break;
		case 4:
			nombreMes = "abril";
			break;
		case 5:
			nombreMes = "mayo";
			break;
		case 6:
			nombreMes = "junio";
			break;
		case 7:
			nombreMes = "julio";
			break;
		case 8:
			nombreMes = "agosto";
			break;
		case 9:
			nombreMes = "septiembre";
			break;
		case 10:
			nombreMes = "octubre";
			break;
		case 11:
			nombreMes = "noviembre";
			break;
		case 12:
			nombreMes = "diciembre";
			break;
		default:
			nombreMes = "mes desconocido";
			break;
		}

		return nombreMes;
	}

	/**
	 * @author José Alfredo Victoria Barbosa Método para obtener el nombre del
	 *         mes
	 * @param Integer
	 *            mes
	 * @return Strin nombreMes
	 * @since 28 / 10 / 2016
	 */
	public static String obtenerNombreMes(Integer mes) {

		String nombreMes;
		switch (mes) {
		case 1:
			nombreMes = "enero";
			break;
		case 2:
			nombreMes = "febrero";
			break;
		case 3:
			nombreMes = "marzo";
			break;
		case 4:
			nombreMes = "abril";
			break;
		case 5:
			nombreMes = "mayo";
			break;
		case 6:
			nombreMes = "junio";
			break;
		case 7:
			nombreMes = "julio";
			break;
		case 8:
			nombreMes = "agosto";
			break;
		case 9:
			nombreMes = "septiembre";
			break;
		case 10:
			nombreMes = "octubre";
			break;
		case 11:
			nombreMes = "noviembre";
			break;
		case 12:
			nombreMes = "diciembre";
			break;
		default:
			nombreMes = "mes desconocido";
			break;
		}

		return nombreMes;
	}

	/**
	 * Método que envía un mensaje al menú lateral llamando a una función js
	 * desde el lado sel servidor
	 * 
	 * @param String
	 *            mensaje que será enviado al menú
	 **/
	public static void enviaMensajeMenuLateral(String mensaje) {
		try {
			RequestContext.getCurrentInstance().execute(
					"enviaMensajeMenu(\"" + mensaje + "\")");

		} catch (Exception e) {
			logger.error("Error al contactar al menú lateral ", e);
		}
	}

	/** Obtener la Imagen para mostroar en PDF **/
	public static String rutaCompletaFoto(String rutaFoto) {
		String rutaCompleta = "";
		try {
			if (rutaFoto != null) {
				Context initialContext = new InitialContext();// se crea el
																// objeto para
																// sacar
																// valores del
																// contexto
				String rutaGluster = (String) initialContext
						.lookup("util/glusterFS"); // ruta
													// del
													// gluster
													// del
													// servidor
				rutaFoto = rutaFoto.replace(rutaGluster, "");
				File archivoFoto = new File(rutaGluster + rutaFoto);// se crea
																	// el
																	// archivo
																	// en el
																	// gluster,
																	// en caso
																	// de
																	// existir
																	// se
																	// re-escribe
				rutaFoto = rutaFoto.replace('\\', '/');
				String[] ruta = rutaFoto.split("/");// se divide la cadena de la
													// ruta para
													// obtener unicamente el
													// nombre de la foto
													// (3)
				int ultimaposicion = ruta.length - 1;
				if (archivoFoto.exists()) {
					rutaCompleta = "/" + "fotosTemporales" + "/"
							+ ruta[ultimaposicion];// se
													// crea
													// la
													// ruta
													// temporal
					String path = FacesContext.getCurrentInstance()
							.getExternalContext()
							.getRealPath(File.separator + "fotosTemporales");// se
																				// obtiene
																				// la
																				// ruta
																				// completa
																				// desde
																				// raiz
					File file = new File(path + File.separator + ruta[3]);// se
																			// crea
																			// el
																			// archivo
																			// de
																			// la
																			// foto
																			// en
																			// la
																			// ruta
																			// temporal
					FileUtils.writeByteArrayToFile(file,
							FileUtils.readFileToByteArray(archivoFoto));// se
																		// escribe
																		// en la
																		// ruta
																		// temporal
																		// la
																		// codificacion
																		// de la
																		// imagen
					rutaCompleta = FacesContext.getCurrentInstance()
							.getExternalContext().getRealPath(rutaCompleta);
				} else {
					rutaCompleta = null;
				}
			} else {
				rutaCompleta = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rutaCompleta;
	}

	/**
	 * Método que limpia espacios en blanco innecesarios
	 * 
	 * @author Isabel Espinoza Espinoza (isabel.espinozae@ine.mx)
	 * @param cadena
	 * @return String
	 */
	public static String adecuarNombre(String cadena) {
		String cadenaNueva = null;
		if (cadena != null) {
			cadena.trim();
			String[] cadenas = cadena.split(" ");
			if (cadenas.length == 1) {
				cadenaNueva = cadenas[0];
			} else if (cadenas.length > 1) {
				StringBuilder nombre = new StringBuilder();
				for (String c : cadenas) {
					if (!c.equals("")) {
						nombre.append(c).append(" ");
					}
				}
				cadenaNueva = nombre.toString();
			}
		}
		return cadenaNueva;
	}


	public static String getRutaGlusterFS() {
		return rutaGlusterFS;
	}


	public static void setRutaGlusterFS(String rutaGlusterFS) {
		Utilidades.rutaGlusterFS = rutaGlusterFS;
	}
	
	
	/**
	 * Método que recibe un Object, castea a Clob y retorna un String	 * 
	 * @author Angel Omar Medel Hernandez
	 * @date 08/11/2017
	 * @params Object
	 * @return String
	 * */
	public static String clobToString(Object ob) {
		Clob data = (Clob) ob;
		StringBuilder sb = new StringBuilder();
	    try {
	        Reader reader = data.getCharacterStream();
	        BufferedReader br = new BufferedReader(reader);

	        String line;
	        while(null != (line = br.readLine())) {
	            sb.append(line);
	        }
	        br.close();
	    } catch (Exception e) {e.printStackTrace();}
	    return sb.toString();
		
	}
	
	
	/**
	 * MÉTODO PARA SABER EN QUE SO  SE ENCUENTRA CORRIENDO LA APLICACION UTIL PARA IR POR LAS RUTAS RELATIVAS DE LA APLICACION 
	 * @author Angel Omar Medel Hernandez
	 * @params null
	 * @return String
	 * ***/	
	public static String getSistemaOperativo() {
		return System.getProperty("os.name");
	}
	
	
	
	
	
	/**
	 * MÉTODO PARA LIMPIAR LAS CADENAS QUE TIENEN ACENTOS, DIRERESIS, Y TILDES 
	 * @author Angel Omar Medel Hernandez
	 * @params String cadena
	 * @return String limpio
	 * **/
	public static String limpiarAcentos(String cadena) {
	    String limpio =null;
	    if (cadena !=null) {
	        String valor = cadena;
	        valor = valor.toUpperCase();
	        // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
	        limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
	        // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
	        limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
	        // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
	        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
	    }
	    return limpio;
	}
}
