package mx.ine.computosINE.helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPConsejero;
import mx.ine.computosINE.dto.helper.HLPDatosActa;
import mx.ine.computosINE.dto.helper.HLPRepresentante;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase auxiliar para la generacion de los formatos pdf de las actas
 * @author INE
 * @since Abril, 2017
 * @copyright Instituto Nacional Electoral (INE). Todos los derechos reservados.
 */
@Component("hlpGeneracionActas")
@Scope ("prototype")
public class HLPGeneradorActas implements java.io.Serializable
{
	/**
	 * Numero de version que posee cada clase Serializable, el cual es usado en la
	 * deserializacion para verificar que la compatibilidad
	 */
	private static final long serialVersionUID = 6227473499405551407L;
	
	/** 
	 * Objeto de escritura para la consola de mensajes de la aplicacion.
	 */
	private static final Logger logger = Logger.getLogger(HLPGeneradorActas.class);
	
	/**
	 * Referencia al bean con la ruta del Gluster
	 * */
	@Autowired
	@Qualifier("rutaGluster")
	protected String rutaGluster;
	
	//Espacio total en el renglon = 525
	/** total maximo de columnas de votos para un partido/coalicion/candidato en un renglon */
	private int totalLugaresXRenglon	= 12;
	
	/** Bandera que indica si el usuario que genera el acta es un capturista municipal o de un ople (por actas parciales o finales en diputados mr/rp y gobernador) */
	private boolean esUsuarioMunicipal 	= false;
	
	/** Bandera que indica si el acta trae un distrito especicicado (por el caso de actas finales de diputados rp para ople, estatal o por distrito */
	private boolean esActaConDistrito		= false;
	
	/* ----------------------------------------------------------------------------------------- */
	/* ------------------------------------ CONSTRUCTOR  --------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */
	
	/**
	 * Genera el acta en formato PDF, de acuerdo a la informacion proporcionada.
	 * Los tipos de acta posibles a generarse son:
	 * - Acta de computo final de Regidores MR
	 * - Acta de computo final de Regidores RP
	 * - Acta de computo final de Ayuntamientos
	 * - Acta de computo parcial de Diputados MR
	 * - Acta de computo parcial de Diputados RP
	 * - Acta de computo parcial de Gobernador
	 * - Acta de computo final de Diputados MR
	 * - Acta de computo final de Diputados RP
	 * - Acta de computo final de Gobernador
	 * @param hlpActa Informacion del acta a generarse (fecha, hora, lugar, condiciones,
	 * resultados por totales, por partido o por candidato, tipo de candidatura, consejeros, representantes)
	 * @return Los bytes del reporte pdf.
	 * @throws Exception Lanza una excepcian en caso de haber una falla en la generacian del reporte.
	 */
	public byte[] generarActa(HLPDatosActa hlpActa) throws Exception
	{
		try{
		//Establecemos el texto oficial y el tipo de consejo
		DTOUsuarioLogin dtoUsuarioLogin = ((DTOUsuarioLogin)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		//Establecemos la bandera para diferenciar actas parciales o finales entre usuarios municipales o usuarios en los ople
		this.esUsuarioMunicipal = dtoUsuarioLogin.getIdMunicipioSeleccionado()!=null && dtoUsuarioLogin.getIdMunicipioSeleccionado().intValue()>0;
		//establecemos la bandera para diferenciar las actas finales de usuarios ople de diputados rp, distrital o estatal
		this.esActaConDistrito = hlpActa.getIdDistrito()!=null && hlpActa.getIdDistrito().intValue()>0;
		
		//TEMP
//		hlpActa = cargarDatosTemporales();
		//TEMP
		
		String rutaImgs = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/image/");
		String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
		String pathJasperActa = (rutaJasper + File.separator + "acta.jasper").replace("/", File.separator);
		//Establecemos valores que lleva el acta (reporte principal de jasper)
//		String pathLogoINE = rutaImgs + File.separator + "logoINE.png";
		String pathLogoEdo = rutaImgs + File.separator + "logoEdo18.jpg";;
		String pathLogoProceso = rutaImgs + File.separator + "logoProceso.png";;
		
		//Proceso que se esta llevando a cabo
//		String descripcionProceso = Utilidades.mensajeProperties("etiqueta_acta_titulo_proceso");
		//Titulo que llevara el acta
		String tituloActa = "";
		//Nota de pie de pagina del acta
		String nota;
		String nombreEstado = hlpActa.getNomEstado();
		
		//Establecemos el titulo del acta, tipo de candidatura y consejo
		if( esUsuarioMunicipal )
		{
			if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue() )
			{
				if( hlpActa.isActaRecuento() )
					tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_regidor_mr_recuento");
				else
					tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_regidor_mr");
			}else
			{
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_RP.intValue() )
				{
					if( hlpActa.isActaRecuento() )
						tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_regidor_rp_recuento");
					else
						tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_regidor_rp");
				}else
				{
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue() )
					{
						if( hlpActa.isActaRecuento() )
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_ayuntamientos_recuento");
						else
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_ayuntamientos");
					}else
					{
						if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() )
						{
							if( hlpActa.isActaRecuento() )
								tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_parcial_diputados_mr_recuento");
							else
								tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_parcial_diputados_mr");
						}else
						{
							if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
							{
								if( hlpActa.isActaRecuento() )
									tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_parcial_diputados_rp_recuento");
								else
									tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_parcial_diputados_rp");
							}else
							{
								if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue() )
								{
									if( hlpActa.isActaRecuento() )
										tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_parcial_gobernador_recuento");
									else
										tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_parcial_gobernador");
								}
							}
						}
					}
				}
			}
		}else
		{
			if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() )
			{
				if( hlpActa.isActaRecuento() )
					tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_diputados_mr_recuento");
				else
					tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_diputados_mr");
			}else
			{
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
				{
					if( esActaConDistrito )
					{
						//Acta por distrito
						if( hlpActa.isActaRecuento() )
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_diputados_rp_recuento");
						else
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_diputados_rp");
					}else
					{
						//Acta estatal
						if( hlpActa.isActaRecuento() )
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_diputados_rp_estatal_recuento");
						else
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_diputados_rp_estatal");
					}
				}else
				{
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue() )
					{
						if( hlpActa.isActaRecuento() )
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_gobernador_recuento");
						else
							tituloActa = Utilidades.mensajeProperties("etiqueta_acta_titulo_gobernador");
					}
				}
			}
		}
		
		//Nota que llevara al final el acta
		nota = Utilidades.mensajeProperties("etiqueta_acta_nota");
		
		JasperPrint jasperPrint = new JasperPrint();
		
		//Parametros del reporte
		Map<String, Object> parametrosActa = new HashMap<String, Object>();
		parametrosActa.put("P_PATH_LOGO1", pathLogoEdo);
		parametrosActa.put("P_PATH_LOGO2", pathLogoProceso);
		parametrosActa.put("P_NOMBRE_ESTADO", nombreEstado);
		parametrosActa.put("P_TITULO_ACTA", tituloActa);
		parametrosActa.put("P_NOTA", nota);
		
		//------------SUBREPORTE CON LA UBICACION (estado, distrito/municipio/regiduria)
		llenarSubreporteUbicacion(parametrosActa, hlpActa);
		
		//------------SUBREPORTE CON DESCRIPCION DEL COMPUTO (fecha, lugar, condiciones, texto oficial)
		llenarSubreporteDescripcion(parametrosActa, hlpActa);
		
		//Ahora llenamos los subreportes de tablas de resultados
		if( hlpActa.getIdTipoCandidatura().intValue()!=Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() &&
			hlpActa.getIdTipoCandidatura().intValue()!=Constantes.ID_TIPO_CAND_REGIDURIA_RP.intValue()	)
		{
			//Presentaremos los renglores de totales, resultados x partidos resultados x candidato
			//segun sean especificados
			
			//------------SUBREPORTE CON TOTALES DE VOTOS
			llenarSubreporteResultadosTotales(parametrosActa, hlpActa);
			
			//------------SUBREPORTE CON TOTALES DE VOTOS POR PARTIDO
			llenarSubreporteResultadosPartido(parametrosActa, hlpActa);
			
			//------------SUBREPORTE CON TOTALES DE VOTOS POR CANDIDATO
			llenarSubreporteResultadosCandidato(parametrosActa, hlpActa);
			
		}else
		{
			//Caso RP, tenemos solo resultados por partido, pero este se despliega hacia abajo
			//------------SUBREPORTE CON TOTALES DE VOTOS POR PARTIDO (RP)
			llenarSubreporteResultadosPartidoRP(parametrosActa, hlpActa);
		}
		
		//------------SUBREPORTE CON LISTADO DE CONSEJEROS
		llenarSubreporteConsejeros(parametrosActa, hlpActa);
		
		//------------SUBREPORTE CON LISTADO DE REPRESENTANTES
		llenarSubreporteRepresentantes(parametrosActa, hlpActa);
		
		//En algunos reportes se requiere para que muestre los decimales con punto y no con comas
		parametrosActa.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX"));
		
		//Cargamos el reporte y sus subreportes
		JasperReport jInforme = (JasperReport)JRLoader.loadObjectFromFile( pathJasperActa );
		
		//CASO PDF
		//Se añade un elemento vacio a fin de evitar que jasper omita el primer renglon
		List<HLPDatosActa> listaDatosActa = new ArrayList<HLPDatosActa>();
		listaDatosActa.add(0, new HLPDatosActa());
		JRBeanCollectionDataSource dataActa = new JRBeanCollectionDataSource(listaDatosActa);
		
		jasperPrint = JasperFillManager.fillReport(jInforme, parametrosActa, dataActa);
		
		byte[] bytesActa = null;
		
		bytesActa = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return  bytesActa;
		}catch(Exception e)
		{
			logger.error("Error en el HLPGeneradorActas - generarActa");
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * Carga la informacion del subreporte con la entidad, municipio, distrito, regiduria, segun aplique
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param hlpActa La informacion que llevara el acta (estado, distrito/municipio/regiduria)
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteUbicacion(Map<String, Object> parametrosActa, HLPDatosActa hlpActa) throws Exception
	{
		Integer idDistrito = hlpActa.getIdDistrito();
		Integer idMunicipio = hlpActa.getIdMunicipio();
		Integer idRegiduria = hlpActa.getIdDemarcacion();
		String nombreDistrito = hlpActa.getNomDistrito();
		String nombreMunicipio = hlpActa.getNomMunicipio();
		
		//Establecemos los parametros que llevara el subreporte con la descripcion del computo
		Map<String, Object> parametrosSubreporte = new HashMap<String, Object>();
		
		//Titulos que describen cada seccion geografica que identifica el acta
		String tituloNumeroDistrito = Utilidades.mensajeProperties("etiqueta_acta_distrito_electoral");
		String tituloNombreDistrito = Utilidades.mensajeProperties("etiqueta_acta_cabecera_distrital");
		
		String tituloNumeroRegiduria = Utilidades.mensajeProperties("etiqueta_acta_demarcacion_municipal_electoral");
		String tituloNombreRegiduria = Utilidades.mensajeProperties("etiqueta_acta_municipio");
		
		String tituloNumeroMunicipio = Utilidades.mensajeProperties("etiqueta_acta_municipio");
		String tituloNombreMunicipio = Utilidades.mensajeProperties("etiqueta_acta_cabecera_municipal");
		//bandera que indica la posicion de la linea, para el caso de que se muestre el
		//distrito electoral (caso de actas de diputados)
		boolean desplazarLinea = false;
		
		if( esUsuarioMunicipal )
		{
			//En todos los tipos de acta de municipales, se presenta el nombre del municipio
			parametrosSubreporte.put("P_NOMBRE_MUNICIPIO", nombreMunicipio);
			if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue() )
			{
				//En estos casos presentamos ademas el #Regiduria
				parametrosSubreporte.put("P_NUMERO_REGIDURIA", idRegiduria.toString());
			}else
			{
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_RP.intValue() )
				{
					//En estos casos presentamos ademas el #Regiduria
					parametrosSubreporte.put("P_NUMERO_REGIDURIA", idRegiduria.toString());
				}else
				{
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue() )
					{
						//En estos casos presentamos ademas el #municipio
						parametrosSubreporte.put("P_NUMERO_MUNICIPIO", idMunicipio.toString());
					}else
					{
						//ACTA PARCIALES EN MUNICIPALES
						if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() )
						{
							//En estos casos presentamos ademas el #municipio, #Distrito Local
							parametrosSubreporte.put("P_NUMERO_MUNICIPIO", idMunicipio.toString());
							parametrosSubreporte.put("P_NUMERO_DISTRITO", idDistrito.toString());
							tituloNumeroDistrito = Utilidades.mensajeProperties("etiqueta_acta_distrito_electoral_local");
							desplazarLinea = true;
						}else
						{
							if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
							{
								//En estos casos presentamos ademas el #municipio
								parametrosSubreporte.put("P_NUMERO_MUNICIPIO", idMunicipio.toString());
								parametrosSubreporte.put("P_NUMERO_DISTRITO", idDistrito.toString());
								tituloNumeroDistrito = Utilidades.mensajeProperties("etiqueta_acta_distrito_electoral_local");
								desplazarLinea = true;
							}else
							{
								if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue() )
								{
									//En estos casos presentamos ademas el #municipio
									parametrosSubreporte.put("P_NUMERO_MUNICIPIO", idMunicipio.toString());
								}
							}
						}
					}
				}
			}
		}else
		{
			if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
			{
				if( esActaConDistrito )
				{
					//Acta por distrito
					//En estos casos presentamos: #Distrito, Cabecera distrital
					parametrosSubreporte.put("P_NUMERO_DISTRITO", idDistrito.toString());
					parametrosSubreporte.put("P_NOMBRE_DISTRITO", nombreDistrito);
				}else
				{
					//Acta estatal
					//En estos casos presentamos solo la Entidad
				}
			}else
			{
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() )
				{
					//En estos casos presentamos: #Distrito, Cabecera distrital
					parametrosSubreporte.put("P_NUMERO_DISTRITO", idDistrito.toString());
					parametrosSubreporte.put("P_NOMBRE_DISTRITO", nombreDistrito);
				}else
				{
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue() )
					{
						//ACTA FINAL EN OPL
						//En estos casos presentamos solo la Entidad
					}
				}
			}
		}
		//Agregamos las etiquetas de los titulos de cada ubicacion geografica donde se realiza el acta
		//(solo se mostraran cuando se especifiquen el numero al que estan asociadas)
		parametrosSubreporte.put("P_TITULO_NUMERO_REGIDURIA", tituloNumeroRegiduria);
		parametrosSubreporte.put("P_TITULO_NOMBRE_REGIDURIA", tituloNombreRegiduria);
		parametrosSubreporte.put("P_TITULO_NUMERO_MUNICIPIO", tituloNumeroMunicipio);
		parametrosSubreporte.put("P_TITULO_NOMBRE_MUNICIPIO", tituloNombreMunicipio);
		parametrosSubreporte.put("P_TITULO_NUMERO_DISTRITO", tituloNumeroDistrito);
		parametrosSubreporte.put("P_TITULO_NOMBRE_DISTRITO", tituloNombreDistrito);
		parametrosSubreporte.put("P_DESPLAZAR_LINEA", desplazarLinea?"true":"false");
		
		//Cargamos los datos que requiere el subreporte
		//(para este caso, un elemento vacio, o no aparece el subreporte)
		List<HLPDatosActa> listaUbicacion = new ArrayList<HLPDatosActa>();
		listaUbicacion.add(new HLPDatosActa());
		JRBeanCollectionDataSource dataSubreporte = new JRBeanCollectionDataSource(listaUbicacion);
		
		String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
		//Establecemos la ruta donde se almacena el molde jasper a utilizar
		String pathSubReporte = (rutaJasper + File.separator + "ubicacionActa.jasper").replace("/", File.separator);
		//Cargamos el molde jasper
		JasperReport subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte);
		
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_UBICACION", subreporte );
		parametrosActa.put("P_DATA_SUBREPORTE_UBICACION", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_UBICACION", parametrosSubreporte);
	}
	/**
	 * Carga la informacion del subreporte con la descripcion que llevara el acta, en los parametros del
	 * modelo jasper principal que contendra el acta completa
	 * Presentara la fecha, el lugar y las condiciones en que se llevo a cabo el computo
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param hlpActa La informacion que llevara el acta (fecha, ubicacion y condiciones del computo)
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteDescripcion(Map<String, Object> parametrosActa, HLPDatosActa hlpActa) throws Exception
	{
		//"<style isBold='true' pdfFontName='Helvetica-Bold'>"
		
		//Establecemos datos que requiere subreporte
		
		//SimpleDateFormat sfHora = new SimpleDateFormat("HH:mm a", new Locale ("es","MX"));
		SimpleDateFormat sfHora = new SimpleDateFormat("hh", new Locale ("es","MX"));
		SimpleDateFormat sfMinuto = new SimpleDateFormat("mm", new Locale ("es","MX"));
		SimpleDateFormat sfHoraAmPm = new SimpleDateFormat("a", new Locale ("es","MX"));
		//SimpleDateFormat sfFecha = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale ("es","MX"));
		SimpleDateFormat sfDia = new SimpleDateFormat("dd", new Locale ("es","MX"));
		
		String nombreEstado = "NAYARIT";
		String horaComputo = sfHora.format(hlpActa.getDatosGenerales().getFechaHora()).toLowerCase();
		String minutoComputo = sfMinuto.format(hlpActa.getDatosGenerales().getFechaHora()).toLowerCase();
		String horaAmPmComputo = sfHoraAmPm.format(hlpActa.getDatosGenerales().getFechaHora()).toLowerCase();
		String horaAmComputo = horaAmPmComputo.equalsIgnoreCase("am")?"true":null;
		String horaPmComputo = horaAmPmComputo.equalsIgnoreCase("pm")?"true":null;
		String diaComputo = sfDia.format(hlpActa.getDatosGenerales().getFechaHora()).toLowerCase();
		String ubicacionComputo1 = hlpActa.getDatosGenerales().getUbicacionComputo();
		String ubicacionComputo2 = "";
		String ubicacionComputo3 = "";
		if( ubicacionComputo1.length()>110 )
		{
			//Agarramos el ultimo espacio para no dividir palabras
			int indiceUltimoEspacio = ubicacionComputo1.substring(0, 110).trim().lastIndexOf(" ");
			if( indiceUltimoEspacio<=0 )	indiceUltimoEspacio = 110;
			ubicacionComputo2 = ubicacionComputo1.substring(indiceUltimoEspacio, ubicacionComputo1.length()).trim();
			ubicacionComputo1 = ubicacionComputo1.substring(0, indiceUltimoEspacio).trim();
			if( ubicacionComputo2.length()>155 )
			{
				//Agarramos el ultimo espacio para no dividir palabras
				indiceUltimoEspacio = ubicacionComputo2.substring(0, 155).trim().lastIndexOf(" ");
				if( indiceUltimoEspacio<=0 )	indiceUltimoEspacio = 155;
				ubicacionComputo3 = ubicacionComputo2.substring(indiceUltimoEspacio, ubicacionComputo2.length()).trim();
				ubicacionComputo2 = ubicacionComputo2.substring(0, indiceUltimoEspacio).trim();
			}
		}
//		String numeroMunicipio = null;
		String textoActa = "";
		String descripcionConsejo = "";
		//Solo habra nombre de municipio en la descripcion, si se tratara de un acta de usuarios municipales
		String nombreMunicipio = "";
		
		//Establecemos el texto oficial y el tipo de consejo
		if( esUsuarioMunicipal )
		{
			//Actas generadas por los consejos municipales
			descripcionConsejo = Utilidades.mensajeProperties("etiqueta_acta_titulo_consejo_municipal");
//			numeroMunicipio = hlpActa.getIdMunicipio().toString();
			nombreMunicipio = hlpActa.getNomMunicipio();
		}else
		{
			//Actas generadas por OPL
			if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() || 
				(hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue()&& esActaConDistrito) )
				descripcionConsejo = Utilidades.mensajeProperties("etiqueta_acta_titulo_consejo_distrital");
			else
				descripcionConsejo = Utilidades.mensajeProperties("etiqueta_acta_titulo_consejo_local");
		}
		textoActa = hlpActa.getDatosGenerales().getTextoActa();
		
		String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
		//Establecemos la ruta donde se almacena el molde jasper a utilizar
		String pathSubReporte = (rutaJasper + File.separator + "descripcionActa.jasper").replace("/", File.separator);
		//Cargamos el molde jasper
		JasperReport subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte);
		
		//Establecemos los parametros que llevara el subreporte con la descripcion del computo
		Map<String, Object> parametrosSubreporte = new HashMap<String, Object>();
		parametrosSubreporte.put("P_NOMBRE_ESTADO", nombreEstado);
		parametrosSubreporte.put("P_HORA_COMPUTO", horaComputo);
		parametrosSubreporte.put("P_MINUTO_COMPUTO", minutoComputo);
		parametrosSubreporte.put("P_HORA_AM_COMPUTO", horaAmComputo);
		parametrosSubreporte.put("P_HORA_PM_COMPUTO", horaPmComputo);
		parametrosSubreporte.put("P_DIA_COMPUTO", diaComputo);
		parametrosSubreporte.put("P_NOMBRE_MUNICIPIO", nombreMunicipio);
		parametrosSubreporte.put("P_UBICACION_COMPUTO1", ubicacionComputo1);
		parametrosSubreporte.put("P_UBICACION_COMPUTO2", ubicacionComputo2);
		parametrosSubreporte.put("P_UBICACION_COMPUTO3", ubicacionComputo3);
		parametrosSubreporte.put("P_TEXTO_ACTA", textoActa);
		parametrosSubreporte.put("P_DESCRIPCION_CONSEJO", descripcionConsejo);
		
		//Cargamos los datos que requiere el subreporte
		List<HLPDatosActa> listaDescripcion = new ArrayList<HLPDatosActa>();
		listaDescripcion.add(new HLPDatosActa());
		JRBeanCollectionDataSource dataSubreporte = new JRBeanCollectionDataSource(listaDescripcion);
		
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_DESCRIPCION", subreporte );
		parametrosActa.put("P_DATA_SUBREPORTE_DESCRIPCION", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_DESCRIPCION", parametrosSubreporte);
	}
	/**
	 * Metodo encargado de agrupar los totales especificados, a fin de imprimirse en el acta, en columnas
	 * | "Etiqueta" | PP/COA/CI 1 | PP/COA/CI 2 | PP/COA/CI 3 | .... | PP/COA/CI N |
	 * |            |    TOTAL 1  |   TOTAL 2   |   TOTAL 3   | .... |   TOTAL N   |
	 * @param listaTotales Los totales a agruparse en columnas
	 * @return Una lista de datos, con los totales agrupados en columnas
	 */
	@SuppressWarnings("rawtypes")
	private List<HLPDatosActa> agruparResultadosVotos(List listaTotales, int tipoResultado)
	{
		String rutaImgsGluster = "" + rutaGluster;
		char caracter = rutaImgsGluster.charAt(rutaImgsGluster.length()-1);
		if( caracter=='/' || caracter=='\\' )
			rutaImgsGluster = rutaImgsGluster.substring(0, rutaImgsGluster.length()-1);
		
		List<HLPDatosActa> totalesVotos = new ArrayList<HLPDatosActa>();
		
		int indiceElemento = 0;
		
		//Recuperamos los totales de votos de todos los partidos, coaliciones y candidatos independientes,
		//agrupandolos de acuerdo al renglon (habra tantas columnas como se especifique en totalLugaresXRenglon)
		while( indiceElemento<listaTotales.size() )
		{
			//Inicializamos el renglon pN, con tantos columnas/totales como sea determinado por totalLugaresXRenglon
			int posicion = 0;
			HLPDatosActa dtoDatos = new HLPDatosActa();
			
			//Llenaremos todas las posiciones de un renglon
			while( posicion<totalLugaresXRenglon && indiceElemento<listaTotales.size() )
			{
				Integer votos = null;
				Integer tipoAsociacion = null;
				String emblema = null;
				String nombreAsociacion = null;
				
				//Verificamos de que se tratan los resultados, para extraer la asociacion y los votos
				switch(tipoResultado)
				{
					case 1://Se tratan de Resultados: Totales de votos
					{
						if( listaTotales.get(indiceElemento) instanceof DTODistribucionTotales )
						{
							//Resultados Totales de votos para actas finales
							DTODistribucionTotales dtoDistribucion = (DTODistribucionTotales)listaTotales.get(indiceElemento);
							if( dtoDistribucion!=null )
							{
								votos = dtoDistribucion.getVotos();
								tipoAsociacion = dtoDistribucion.getTipoAsociacion();
								emblema = dtoDistribucion.getEmblema();
								//en el campo emblema se esta guardando el nombre del candidato
								nombreAsociacion = dtoDistribucion.getEmblema();
							}
						}else
						{
							//Resultados Totales de votos para actas parciales
							DTODistribucionTotParcial dtoDistribucion = (DTODistribucionTotParcial)listaTotales.get(indiceElemento);
							if( dtoDistribucion!=null )
							{
								votos = dtoDistribucion.getVotos();
								tipoAsociacion = dtoDistribucion.getTipoAsociacion();
								emblema = dtoDistribucion.getEmblema();
								//en el campo emblema se esta guardando el nombre del candidato
								nombreAsociacion = dtoDistribucion.getEmblema();
							}
						}
						break;
					}
					case 2: //Se tratan de Resultados por Partido
					{
						if( listaTotales.get(indiceElemento) instanceof DTODistribucionPartidosCI )
						{
							//Actas finales
							DTODistribucionPartidosCI dtoDistribucion = (DTODistribucionPartidosCI)listaTotales.get(indiceElemento);
							if( dtoDistribucion!=null )
							{
								votos = dtoDistribucion.getVotos();
								tipoAsociacion = dtoDistribucion.getTipoAsociacion();
								emblema = dtoDistribucion.getEmblema();
								//en el campo emblema se esta guardando el nombre del candidato
								nombreAsociacion = dtoDistribucion.getEmblema();
							}
						}else
						{
							//Actas parciales (para este caso no se creo una tabla especial donde guardar los resultados
							//por lo que se reutilizo esta tabla)
							DTODistribucionCandParcial dtoDistribucion = (DTODistribucionCandParcial)listaTotales.get(indiceElemento);
							if( dtoDistribucion!=null )
							{
								votos = dtoDistribucion.getVotos();
								tipoAsociacion = dtoDistribucion.getTipoAsociacion();
								emblema = dtoDistribucion.getEmblema();
								//en el campo emblema se esta guardando el nombre del candidato
								nombreAsociacion = dtoDistribucion.getEmblema();
							}
						}
						break;
					}
					case 3: //Se tratan de Resultados por Candidato
					{
						if( listaTotales.get(indiceElemento) instanceof DTODistribucionCandidatos )
						{
							//Resultados por Candidato para actas finales
							DTODistribucionCandidatos dtoDistribucion = (DTODistribucionCandidatos)listaTotales.get(indiceElemento);
							if( dtoDistribucion!=null )
							{
								votos = dtoDistribucion.getVotos();
								tipoAsociacion = dtoDistribucion.getTipoAsociacion();
								emblema = dtoDistribucion.getEmblema();
								//en el campo emblema se esta guardando el nombre del candidato
								nombreAsociacion = dtoDistribucion.getEmblema();
							}
						}else
						{
							//Resultados por Candidato para actas parciales
							DTODistribucionCandParcial dtoDistribucion = (DTODistribucionCandParcial)listaTotales.get(indiceElemento);
							if( dtoDistribucion!=null )
							{
								votos = dtoDistribucion.getVotos();
								tipoAsociacion = dtoDistribucion.getTipoAsociacion();
								emblema = dtoDistribucion.getEmblema();
								//en el campo emblema se esta guardando el nombre del candidato
								nombreAsociacion = dtoDistribucion.getEmblema();
							}
						}
						break;
					}
				}
				//Solo pasamos el emblema si es partido o coalicion
				if( tipoAsociacion!=null && (tipoAsociacion.intValue()==Constantes.TIPO_ASOCIACION_PARTIDO || 
						tipoAsociacion.intValue()==Constantes.TIPO_ASOCIACION_COALICIONES) &&
						(emblema!=null && !emblema.trim().equals("")) )
					emblema = rutaImgsGluster + emblema.trim();
				else
					emblema = null;
				
				dtoDatos.getEmblemasAsociaciones().add(emblema);
				dtoDatos.getTiposAsociaciones().add(tipoAsociacion);
				dtoDatos.getTotalesVotos().add(votos);
				
				//Verificamos si tendremos url de partido/coalicion o indice de candidato
				if( tipoAsociacion!=null && tipoAsociacion.intValue()==Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE.intValue() )
					//Es un candidato independiente, se presentara el nombre del candidato si no tiene emblema
					dtoDatos.getNombresAsociaciones().add(nombreAsociacion);
				else
					dtoDatos.getNombresAsociaciones().add(null);
				
				posicion++;
				indiceElemento++;
			}
			//No caben mas totales en el renglon (o se acabo el listado)
			//Lo agregamos a la lista de renglones de votos
			totalesVotos.add(dtoDatos);
		}
		
		return totalesVotos;
	}
	/**
	 * Carga la informacion del subreporte con los totales de la votacion por partido, coalicion, subcoalicion y por candidato independiente
	 * - Para cada candidato independiente presentara un logo (si tiene uno definido), o bien el nombre del candidato y su total de votos
	 * - Para cada partido, coalicion o subcoalicion, se presentara su logo y su total de votos
	 * Este reporte mostrara los totales, agrupandolos en columnas
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param hlpActa Informacion del acta (distrito con que se genera -caso Diputados MR parcial) 
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteResultadosTotales(Map<String, Object> parametrosActa, HLPDatosActa hlpActa)
	throws Exception
	{
		JasperReport subreporte = null;
		JRBeanCollectionDataSource dataSubreporte = null;
		Map<String, Object> parametrosSubreporte = null;
		//Titulo que llevaran las tablas de resultados
		String tituloResultadosVotacion = Utilidades.mensajeProperties("etiqueta_acta_titulo_resultados_votacion");
		
		//Titulo de la tabla de resultados totales de votos
		String tituloResultados = "";
		
		//Titulo del renglon de cada tabla por construirse, agrupando la informacion
		String tituloRenglon = Utilidades.mensajeProperties("etiqueta_acta_titulo_pp_coa_ci");
		
		//Extraemos los totales de votos por partido coaliciones, subcoaliciones y candidatos independientes
		List listaTotalesVotos = null;
		
		if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue() ||
			hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue() )
		{
			//Extraemos los resultados de votos finales, no hay parcial en estos casos
			listaTotalesVotos = hlpActa.getListTotalVotos();
		}else
		{
			//Caso de Diputados MR, Diputados RP y Gobernador: puede ser parcial o final
			if( esUsuarioMunicipal )
				//Especificaron un municipio, se trata de acta parcial
				listaTotalesVotos = hlpActa.getListTotalVotosParcial();
			else
				listaTotalesVotos = hlpActa.getListTotalVotos();
		}
		
		//Solo si tiene datos, generamos contenido al subreporte
		if( listaTotalesVotos!=null && !listaTotalesVotos.isEmpty() )
		{
			//Preparamos los listado de informacion con los votos solo si traemos informacion
			if( esUsuarioMunicipal )
			{
				//Actas generados en los consejos municipales
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue() ||
					hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue() )
				{
					//TOTAL DE VOTOS
					tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos");
				}else
				{
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() )
					{
						String idDistrito = hlpActa.getIdDistrito().toString();
						tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_distrito") + " " + idDistrito;
					}else
					{
						if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue() )
							tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_municipio");
					}
				}
			}else
			{
				//Actas generados en los OPLE
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue() )
					tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_distrito");
				else
				{
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue() )
						tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_estado");
				}
			}
			
			//Total de votos recibidos para cada partido politico o candidato independiente, agrupados para su despliegue
			List<HLPDatosActa> totalesVotos = new ArrayList<HLPDatosActa>();
			
			//Cargamos los totales, agrupados 
			totalesVotos = agruparResultadosVotos(listaTotalesVotos, 1);
			//Sumamos los totales para la ultima columna
			int totalVotacion = 0;
			for (HLPDatosActa hlpDatosActa : totalesVotos)
			{
				for (Integer votos : hlpDatosActa.getTotalesVotos())
					if( votos!=null )
						totalVotacion += votos.intValue();
			}
			
			//Totamos el ultimo elemento para verificar si ahi podemos insertar la columna de votos totales
			HLPDatosActa hlpDatosActa = totalesVotos.get(totalesVotos.size()-1);
			if( hlpDatosActa.getTotalesVotos().size()<totalLugaresXRenglon )
			{
				//Se puede insertar en el ultimo renglon
				hlpDatosActa.getNombresAsociaciones().add(null);
				hlpDatosActa.getTiposAsociaciones().add(null);
				hlpDatosActa.getEmblemasAsociaciones().add(null);
				//hlpDatosActa.getTotalesVotos().add(""+totalVotacion);
				hlpDatosActa.getTotalesVotos().add(totalVotacion);
			}else
			{
				//Necesitamos un nuevo renglon
				HLPDatosActa hlp = new HLPDatosActa();
				hlp.getNombresAsociaciones().add(null);
				hlp.getTiposAsociaciones().add(null);
				hlp.getEmblemasAsociaciones().add(null);
				//hlp.getTotalesVotos().add(""+totalVotacion);
				hlp.getTotalesVotos().add(totalVotacion);
				totalesVotos.add(hlp);
			}
			
			String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
			//Establecemos la ruta donde se almacena el molde jasper a utilizar
			String pathSubReporte2 = (rutaJasper + File.separator + "resultadosHorizontales.jasper").replace("/", File.separator);
			//Cargamos el molde jasper
			subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte2);
			
			//Establecemos los parametros que llevara el subreporte con los resultados de la votacion
			parametrosSubreporte = new HashMap<String, Object>();
			parametrosSubreporte.put("P_TITULO_RESULTADOS_VOTACION", tituloResultadosVotacion);
			parametrosSubreporte.put("P_TITULO_RESULTADOS", tituloResultados);
			parametrosSubreporte.put("P_TITULO_RENGLON", tituloRenglon);
			parametrosSubreporte.put("P_TITULO_COALICION", Utilidades.mensajeProperties("etiqueta_acta_titulo_coalicion"));
			parametrosSubreporte.put("P_TITULO_CAND_NO_REG", Utilidades.mensajeProperties("etiqueta_acta_titulo_cand_no_reg"));
			parametrosSubreporte.put("P_TITULO_VOTOS_NULOS", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_nulos"));
			parametrosSubreporte.put("P_TITULO_VOTOS_TOTALES", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_totales"));
			parametrosSubreporte.put("P_TIPO_ASOCIACION_PARTIDO", Constantes.TIPO_ASOCIACION_PARTIDO);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_COALICION", Constantes.TIPO_ASOCIACION_COALICIONES);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CI", Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_VOTOS_NULOS", Constantes.TIPO_ASOCIACION_VOTOS_NULOS);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CAND_NO_REG", Constantes.TIPO_ASOCIACION_CAND_NO_REG);
			
			//Cargamos los datos que requiere el subreporte
			dataSubreporte = new JRBeanCollectionDataSource(totalesVotos);
		}
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_RESULTADOS_TOTALES", subreporte);
		parametrosActa.put("P_DATA_SUBREPORTE_RESULTADOS_TOTALES", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_RESULTADOS_TOTALES", parametrosSubreporte);
	}
	
	/**
	 * Carga la informacion del subreporte con los totales de la votacion por partido
	 * - Para cada candidato independiente presentara su logo (si cuenta con uno), o bien el nombre del candidato, y su total de votos
	 * - Para cada partido presentara su logo y su total de votos
	 * Este reporte mostrara los totales, agrupandolos en columnas
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param hlpActa La informacion del acta (totales de votos pro partido
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteResultadosPartido(Map<String, Object> parametrosActa, HLPDatosActa hlpActa)
	throws Exception
	{
		JasperReport subreporte = null;
		JRBeanCollectionDataSource dataSubreporte = null;
		Map<String, Object> parametrosSubreporte = null;
		
		//Titulo de la tabla de resultados totales por partido
		String tituloResultados = "";
		
		//Titulo del renglon de cada tabla por construirse, agrupando la informacion
		String tituloRenglon = Utilidades.mensajeProperties("etiqueta_acta_titulo_pp_coa_ci");
		
		//Extraemos los totales de votos por partido y por candidato independiente
		//(en este caso no hay distincion entre acta parcial o final)
		List listaTotalesVotosPartidosCI = null;
		
		//Verificamos de que tabla/listado se recupera el listado
		//Para las actas que son finales, se recupera de la distribucion de partidos
		//Para actas parciales, se reutilizara la tabla de distribucion candidatos parcial
		if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue() ||
				hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue() ||
				(!esUsuarioMunicipal && hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue()) ||
				(!esUsuarioMunicipal && hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_GOBERNADOR.intValue()) )
		{
			//Actas finales que llevan totales de partidos
			//Las parciales no presentan totales de partidos (mas que Diputados RP, que se maneja en metodo RP)
			listaTotalesVotosPartidosCI = hlpActa.getListDistribucionPPCI();
		}
		
		//Solo si tiene datos, generamos contenido al subreporte
		if( listaTotalesVotosPartidosCI!=null && !listaTotalesVotosPartidosCI.isEmpty() )
		{
			//Especificamos el titulo de la seccion
			tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_pp_ci");
			
			//Preparamos los listado de informacion con los votos, solo si hay votos especificados
			//Total de votos recibidos para cada partido politico 
			List<HLPDatosActa> totalesVotosPartido = new ArrayList<HLPDatosActa>();
			
			//Cargamos los totales, agrupados
			totalesVotosPartido = agruparResultadosVotos(listaTotalesVotosPartidosCI, 2);
			
			String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
			//Establecemos la ruta donde se almacena el molde jasper a utilizar
			String pathSubReporte2 = (rutaJasper + File.separator + "resultadosHorizontales.jasper").replace("/", File.separator);
			//Cargamos el molde jasper
			subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte2);
			
			//Establecemos los parametros que llevara el subreporte con los resultados de la votacion
			parametrosSubreporte = new HashMap<String, Object>();
			parametrosSubreporte.put("P_TITULO_RESULTADOS_VOTACION", null);
			parametrosSubreporte.put("P_TITULO_RESULTADOS", tituloResultados);
			parametrosSubreporte.put("P_TITULO_RENGLON", tituloRenglon);
			parametrosSubreporte.put("P_TITULO_COALICION", Utilidades.mensajeProperties("etiqueta_acta_titulo_coalicion"));
			parametrosSubreporte.put("P_TITULO_CAND_NO_REG", Utilidades.mensajeProperties("etiqueta_acta_titulo_cand_no_reg"));
			parametrosSubreporte.put("P_TITULO_VOTOS_NULOS", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_nulos"));
			parametrosSubreporte.put("P_TITULO_VOTOS_TOTALES", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_totales"));
			parametrosSubreporte.put("P_TIPO_ASOCIACION_PARTIDO", Constantes.TIPO_ASOCIACION_PARTIDO);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_COALICION", Constantes.TIPO_ASOCIACION_COALICIONES);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CI", Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_VOTOS_NULOS", Constantes.TIPO_ASOCIACION_VOTOS_NULOS);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CAND_NO_REG", Constantes.TIPO_ASOCIACION_CAND_NO_REG);
			
			//Cargamos los datos que requiere el subreporte
			dataSubreporte = new JRBeanCollectionDataSource(totalesVotosPartido);
		}
		
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_RESULTADOS_PARTIDO_HORIZONTAL", subreporte);
		parametrosActa.put("P_DATA_SUBREPORTE_RESULTADOS_PARTIDO_HORIZONTAL", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_RESULTADOS_PARTIDO_HORIZONTAL", parametrosSubreporte);
	}
	/**
	 * Carga la informacion del subreporte con los totales de la votacion por candidato
	 * - Para cada candidato independiente presentara su logo (si cuenta con uno), o bien el nombre del candidato y su total de votos
	 * - Para cada partido sin coaligar o coalicion, presentara su logo y su total de votos
	 * Este reporte mostrara los totales, agrupandolos en columnas
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param hlpActa La informacion relacionada al tipo de candidatura y donde se genera el acta estado/distrito/municipio/regiduria
	 * @param listaTotalesVotosCandidatos Los totales de votos por partido sin coaligar, coaliciones y candidatos independientes
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteResultadosCandidato(Map<String, Object> parametrosActa, HLPDatosActa hlpActa)
	throws Exception
	{
		JasperReport subreporte = null;
		JRBeanCollectionDataSource dataSubreporte = null;
		Map<String, Object> parametrosSubreporte = null;
		//Titulo de la tabla de resultados totales por candidato
		String tituloResultados = "";
		
		//Titulo del renglon de cada tabla por construirse, agrupando la informacion
		String tituloRenglon = Utilidades.mensajeProperties("etiqueta_acta_titulo_pp_coa_ci");
		
		//Extraemos los totales de votos por partido, coaliciones y candidatos independientes
		List listaTotalesVotosCandidatos = null;
		
		if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue() ||
				hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue() )
		{
			//Extraemos los resultados por candidato para actas finales, no hay parciales en estos casos
			listaTotalesVotosCandidatos = hlpActa.getListDistFinalCandidatos();
		}else
		{
			//Caso de Diputados MR y Gobernador: puede ser parcial o final
			if( esUsuarioMunicipal )
				//Especificaron un municipio, se trata de un acta parcial
				listaTotalesVotosCandidatos = hlpActa.getListDistParcialCandidatos();
			else
				listaTotalesVotosCandidatos = hlpActa.getListDistFinalCandidatos();
		}
		
		//Solo si tiene datos, generamos contenido al subreporte
		if( listaTotalesVotosCandidatos!=null && !listaTotalesVotosCandidatos.isEmpty() )
		{
			//Especificamos en que consejo se realizo el computo de os resultados totales
			tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_candidatos");
			//Preparamos los listado de informacion con los votos solo si tenemos votos por candidato
			
			//Total de votos recibidos para cada candidato, agrupados para su despliegue
			List<HLPDatosActa> totalesVotosCandidato = new ArrayList<HLPDatosActa>();
			
			//Cargamos los totales, agrupados 
			totalesVotosCandidato = agruparResultadosVotos(listaTotalesVotosCandidatos, 3);
			
			String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
			//Establecemos la ruta donde se almacena el molde jasper a utilizar
			String pathSubReporte = (rutaJasper + File.separator + "resultadosHorizontales.jasper").replace("/", File.separator);
			//Cargamos el molde jasper
			subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte);
			
			//Establecemos los parametros que llevara el subreporte con los resultados de la votacion
			parametrosSubreporte = new HashMap<String, Object>();
			parametrosSubreporte.put("P_TITULO_RESULTADOS_VOTACION", null);
			parametrosSubreporte.put("P_TITULO_RESULTADOS", tituloResultados);
			parametrosSubreporte.put("P_TITULO_RENGLON", tituloRenglon);
			parametrosSubreporte.put("P_TITULO_COALICION", Utilidades.mensajeProperties("etiqueta_acta_titulo_coalicion"));
			parametrosSubreporte.put("P_TITULO_CAND_NO_REG", Utilidades.mensajeProperties("etiqueta_acta_titulo_cand_no_reg"));
			parametrosSubreporte.put("P_TITULO_VOTOS_NULOS", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_nulos"));
			parametrosSubreporte.put("P_TITULO_VOTOS_TOTALES", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_totales"));
			parametrosSubreporte.put("P_TIPO_ASOCIACION_PARTIDO", Constantes.TIPO_ASOCIACION_PARTIDO);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_COALICION", Constantes.TIPO_ASOCIACION_COALICIONES);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CI", Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_VOTOS_NULOS", Constantes.TIPO_ASOCIACION_VOTOS_NULOS);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CAND_NO_REG", Constantes.TIPO_ASOCIACION_CAND_NO_REG);
			
			//Cargamos los datos que requiere el subreporte
			dataSubreporte = new JRBeanCollectionDataSource(totalesVotosCandidato);
		}
		parametrosActa.put("P_SUBREPORTE_RESULTADOS_CANDIDATO", subreporte);
		parametrosActa.put("P_DATA_SUBREPORTE_RESULTADOS_CANDIDATO", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_RESULTADOS_CANDIDATO", parametrosSubreporte);
	}
	/**
	 * Carga la informacion del subreporte con los totales de la votacion por partido
	 * (solo se recibe partidos, pero se deja preparado por si eventualmente se quisieran mostrar candidatos independientes,
	 * pues por ley, no hay candidatos independientes en RP)
	 * - Para cada candidato independiente presentara su logo (si lo tiene), o bien el nombre del candidato y su total de votos
	 * - Para cada partido presentara su logo y su total de votos
	 * Este reporte mostrara los totales sin agrupar (se presentan uno por renglon)
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param hlpActa La informacion del acta (resultados de votos por partido/candidato independiente)
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteResultadosPartidoRP(Map<String, Object> parametrosActa, HLPDatosActa hlpActa)
	throws Exception
	{
		String rutaImgsGluster = "" + rutaGluster;
		char caracter = rutaImgsGluster.charAt(rutaImgsGluster.length()-1);
		if( caracter=='/' || caracter=='\\' )
			rutaImgsGluster = rutaImgsGluster.substring(0, rutaImgsGluster.length()-1);
		
		Integer idDistrito = hlpActa.getIdDistrito();
		
		JasperReport subreporte = null;
		JRBeanCollectionDataSource dataSubreporte = null;
		Map<String, Object> parametrosSubreporte = null;
		
		//Titulo que llevaran las tablas de resultados
		String tituloResultadosVotacion = Utilidades.mensajeProperties("etiqueta_acta_titulo_resultados_votacion");
		
		//Titulo de la tabla de resultados totales por partido
		String tituloResultados = "";
		
		//Titulo de que llevaran los totales recibidos
		String tituloRenglon = Utilidades.mensajeProperties("etiqueta_acta_titulo_pp");
		
		//Extraemos los totales de votos por partido y por candidato independiente
		//(en este caso no hay distincion entre actas parciales y finales)
		List listaTotalesVotosPartidosCI = null;
		
		//Verificamos de que tabla/listado se recupera el listado
		//Para las actas que son finales, se recupera de la distribucion de partidos
		//Para actas parciales, se reutilizara la tabla de distribucion candidatos parcial
		if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_RP.intValue() ||
			(!esUsuarioMunicipal && hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue()))
		{
			//Actas finales, extraemos los resultados de votos finales
			listaTotalesVotosPartidosCI = hlpActa.getListDistribucionPPCI();
		}else
		{
			//Caso de Diputados RP parcial: los votos vienen en otro listado
			if( esUsuarioMunicipal && hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
				listaTotalesVotosPartidosCI = hlpActa.getListDistParcialCandidatos();
		}
		
		//Solo si tiene datos, generamos contenido al subreporte
		if( listaTotalesVotosPartidosCI!=null && !listaTotalesVotosPartidosCI.isEmpty() )
		{
			//Especificamos el titulo de la seccion
			if( esUsuarioMunicipal )
			{
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_REGIDURIA_RP.intValue() )
					tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos");
				else
					if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
						//Caso de acta parcial de diputados rp, se muestra el distrito
						tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_distrito") + " " + idDistrito;
			}else
			{
				if( hlpActa.getIdTipoCandidatura().intValue()==Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
				{
					if( esActaConDistrito )
						//Caso de acta por distrito
						tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos");
					else
						//Caso acta por estado
						tituloResultados = Utilidades.mensajeProperties("etiqueta_acta_titulo_total_votos_estado");
				}
			}
			
			//Preparamos los listado de informacion con los votos, solo si hay votos especificados
			//Total de votos recibidos para cada partido politico 
			List<HLPDatosActa> totalesVotosPartido = new ArrayList<HLPDatosActa>();
			
			int totalVotacion = 0;
			//Recuperamos los totales de votos de los partidos y candidatos independientes
			//En este subreporte, no agrupamos
			Iterator it = listaTotalesVotosPartidosCI.iterator();
			while( it.hasNext() )
			{
				Object obj = it.next();
				Integer votos = null;
				Integer tipoAsociacion = null;
				String emblema = null;
				String nombreAsociacion = null;
				
				if( obj!=null )
				{
					HLPDatosActa hlpDatos = new HLPDatosActa();
					
					//Extraemos la informacion, dependiendo de si fue parcial o final
					if( obj instanceof DTODistribucionPartidosCI )
					{
						//Actas finales
						DTODistribucionPartidosCI dtoDistribucion = (DTODistribucionPartidosCI)obj;
						if( dtoDistribucion!=null )
						{
							votos = dtoDistribucion.getVotos();
							if( votos!=null )
								totalVotacion += votos;
							tipoAsociacion = dtoDistribucion.getTipoAsociacion();
							emblema = dtoDistribucion.getEmblema();
							//en el campo emblema se esta guardando el nombre del candidato
							nombreAsociacion = dtoDistribucion.getEmblema();
						}
					}else
					{
						//Actas parciales (para este caso no se creo una tabla especial donde guardar los resultados
						//por lo que se reutilizo esta tabla)
						DTODistribucionCandParcial dtoDistribucion = (DTODistribucionCandParcial)obj;
						if( dtoDistribucion!=null )
						{
							votos = dtoDistribucion.getVotos();
							if( votos!=null )
								totalVotacion += votos;
							tipoAsociacion = dtoDistribucion.getTipoAsociacion();
							emblema = dtoDistribucion.getEmblema();
							//en el campo emblema se esta guardando el nombre del candidato
							nombreAsociacion = dtoDistribucion.getEmblema();
						}
					}
					
					if( tipoAsociacion!=null && tipoAsociacion.intValue()==Constantes.TIPO_ASOCIACION_PARTIDO &&
							(emblema!=null && !emblema.trim().equals("")) )
					{
						emblema = rutaImgsGluster + emblema;
					}else
					{
						emblema = null;
					}
					if( tipoAsociacion==null || tipoAsociacion.intValue()!=Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE )
					{
						//Solo en candidatos independientes tendremos un nombre
						nombreAsociacion = null;
					}
					hlpDatos.setEmblemaAsociacion(emblema);
					hlpDatos.setNombreAsociacion(nombreAsociacion);
					hlpDatos.setTipoAsociacion(tipoAsociacion);
					if( votos!=null )
					{
						hlpDatos.setTotalVotosNumero(votos);
						hlpDatos.setTotalVotosLetra(Utilidades.convertirNumeroALetra(votos.intValue(), false));
					}else
					{
						hlpDatos.setTotalVotosNumero(0);
						hlpDatos.setTotalVotosLetra(Utilidades.convertirNumeroALetra(0, false));
					}
					
					totalesVotosPartido.add(hlpDatos);
				}
			}
			
			//Agregamos el renglo de total de votos
			HLPDatosActa dtoDatosTotales = new HLPDatosActa();
			//dtoDatosTotales.setTotalVotosNumero(""+totalVotacion);
			dtoDatosTotales.setTotalVotosNumero(totalVotacion);
			dtoDatosTotales.setTotalVotosLetra(Utilidades.convertirNumeroALetra(totalVotacion, false));
			totalesVotosPartido.add(dtoDatosTotales);
			
			String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
			//Establecemos la ruta donde se almacena el molde jasper a utilizar
			String pathSubReporte = (rutaJasper + File.separator + "resultadosVerticales.jasper").replace("/", File.separator);
			//Cargamos el molde jasper
			subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte);
			
			//Establecemos los parametros que llevara el subreporte con los resultados de la votacion
			parametrosSubreporte = new HashMap<String, Object>();
			parametrosSubreporte.put("P_TITULO_RESULTADOS_VOTACION", tituloResultadosVotacion);
			parametrosSubreporte.put("P_TITULO_RESULTADOS", tituloResultados);
			parametrosSubreporte.put("P_TITULO_RENGLON", tituloRenglon);
			parametrosSubreporte.put("P_TITULO_CAND_NO_REG", Utilidades.mensajeProperties("etiqueta_acta_titulo_cand_no_reg"));
			parametrosSubreporte.put("P_TITULO_VOTOS_NULOS", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_nulos"));
			parametrosSubreporte.put("P_TITULO_VOTOS_TOTALES", Utilidades.mensajeProperties("etiqueta_acta_titulo_votos_totales"));
			parametrosSubreporte.put("P_TIPO_ASOCIACION_PARTIDO", Constantes.TIPO_ASOCIACION_PARTIDO);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CI", Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_VOTOS_NULOS", Constantes.TIPO_ASOCIACION_VOTOS_NULOS);
			parametrosSubreporte.put("P_TIPO_ASOCIACION_CAND_NO_REG", Constantes.TIPO_ASOCIACION_CAND_NO_REG);
			
			//Cargamos los datos que requiere el subreporte
			dataSubreporte = new JRBeanCollectionDataSource(totalesVotosPartido);
		}
		
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_RESULTADOS_PARTIDO_VERTICAL", subreporte);
		parametrosActa.put("P_DATA_SUBREPORTE_RESULTADOS_PARTIDO_VERTICAL", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_RESULTADOS_PARTIDO_VERTICAL", parametrosSubreporte);
	}
	/**
	 * Carga la informacion del subreporte con los nombres de los consejeros (presidente, secretario y los consejeros
	 * de las seis formulas)
	 * - Para el presidente y secretario, presentara el nombre completo y si es titular o suplente
	 * - Para cada consejero de las formulas, presentara el nombre completo y si es propietario o suplente
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param listaConsejeros Listado completo de consejeros a presentar en el acta
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteConsejeros(Map<String, Object> parametrosActa, HLPDatosActa hlpActa) throws Exception
	{
		//Cargamos la informacion del presidente, secretario y los seis consejeros restantes
		//(nombre completo, calidad del funcionario -T/P o S-)
		HLPConsejero presidente;
		HLPConsejero secretario;
		List<HLPConsejero> consejeros = new ArrayList<HLPConsejero>();
		HLPConsejero[] arrayConsejeros = new HLPConsejero[8];
		//Inicializamos el arreglo completo de consejeros
		for( int i=0; i<arrayConsejeros.length; i++ )
			arrayConsejeros[i] = new HLPConsejero();
		
		//Extraemos la informacion de los consejeros que se registraron,
		//acomodandolos de acuerdo al orden que requerimos para identificar
		//quien esta y quien no
		if( hlpActa.getDatosGenerales().getConsejeros()!=null && !hlpActa.getDatosGenerales().getConsejeros().isEmpty() )
		{
			for (HLPConsejero hlpConsejero : hlpActa.getDatosGenerales().getConsejeros())
				arrayConsejeros[hlpConsejero.getIdTipoConsejero().intValue()-1] = hlpConsejero;
		}
		
		//Extraemos lo que tengamos de presidente y secretario
		presidente = arrayConsejeros[0];
		secretario = arrayConsejeros[1];
		//Extraemos lo que tengamos del resto de los consejeros
		for(int i=2; i<arrayConsejeros.length; i++)
			consejeros.add(arrayConsejeros[i]);
		
		//Consejo Municipal o Local
		String descripcionConsejo = "";
		
		//Establecemos el tipo de consejo
		if( esUsuarioMunicipal )
			descripcionConsejo = Utilidades.mensajeProperties("etiqueta_acta_titulo_consejo_municipal").toUpperCase(new Locale("es", "MX"));
		else
			descripcionConsejo = Utilidades.mensajeProperties("etiqueta_acta_titulo_consejo_local").toUpperCase(new Locale("es", "MX"));
		
		//Nomenclaturas utilizadas en la calidad de los puestos de los consejeros 
		String descripcionCalidadPresidente = Utilidades.mensajeProperties("etiqueta_acta_propietarios_suplentes");
		String tituloCalidadPresidente = Utilidades.mensajeProperties("etiqueta_acta_titulo_calidad_propietarios_suplentes");
		String descripcionCalidadSecretario = "";
		String tituloCalidadSecretario = "";
		String descripcionCalidadConsejeros = Utilidades.mensajeProperties("etiqueta_acta_propietarios");
		String tituloCalidadConsejeros = Utilidades.mensajeProperties("etiqueta_acta_titulo_calidad_propietarios");
		if( esUsuarioMunicipal )
		{
			//Se omite Suplente para secretario, dado que no hay suplentes en los Consejos municipales
			descripcionCalidadSecretario = Utilidades.mensajeProperties("etiqueta_acta_titulares");
			tituloCalidadSecretario = Utilidades.mensajeProperties("etiqueta_acta_titulo_calidad_titulares");
		}else
		{
			//En OPLEs se maneja el concepto de Encargado
			descripcionCalidadSecretario = Utilidades.mensajeProperties("etiqueta_acta_encargados_suplentes");
			tituloCalidadSecretario = Utilidades.mensajeProperties("etiqueta_acta_titulo_calidad_encargados_suplentes");
		}
		
		String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
		//Establecemos la ruta donde se almacena el molde jasper a utilizar
		String pathSubReporte = (rutaJasper + File.separator + "consejeros.jasper").replace("/", File.separator);
		//Cargamos el molde jasper
		JasperReport subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte);
		
		//Establecemos los parametros que llevara el subreporte con los resultados de la votacion
		Map<String, Object> parametrosSubreporte = new HashMap<String, Object>();
		parametrosSubreporte.put("P_DESCRIPCION_CONSEJO", descripcionConsejo);
		parametrosSubreporte.put("P_CONSEJERO_PRESIDENTE", presidente.getNombreCompleto());
		parametrosSubreporte.put("P_CALIDAD_CONSEJERO_PRESIDENTE", presidente.getCalidad());
		parametrosSubreporte.put("P_SECRETARIO", secretario.getNombreCompleto());
		parametrosSubreporte.put("P_CALIDAD_SECRETARIO", secretario.getCalidad());
		parametrosSubreporte.put("P_DESC_CALIDAD_PRESIDENTE", descripcionCalidadPresidente);
		parametrosSubreporte.put("P_TIT_CALIDAD_PRESIDENTE", tituloCalidadPresidente);
		parametrosSubreporte.put("P_DESC_CALIDAD_SECRETARIO", descripcionCalidadSecretario);
		parametrosSubreporte.put("P_TIT_CALIDAD_SECRETARIO", tituloCalidadSecretario);
		parametrosSubreporte.put("P_DESC_CALIDAD_CONSEJEROS", descripcionCalidadConsejeros);
		parametrosSubreporte.put("P_TIT_CALIDAD_CONSEJEROS", tituloCalidadConsejeros);
		
		//Cargamos los datos que requiere el subreporte
		JRBeanCollectionDataSource dataSubreporte = new JRBeanCollectionDataSource(consejeros);
		
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_CONSEJEROS", subreporte);
		parametrosActa.put("P_DATA_SUBREPORTE_CONSEJEROS", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_CONSEJEROS", parametrosSubreporte);
	}
	/**
	 * Carga la informacion del subreporte con los nombres de los representantes de partido y de candidatos independientes
	 * - Para cada representante de partido presentara el nombre completo y el logo del partido.
	 * - Para cada representante de candidato independiente, presentara el logo (si tiene), o bien el nombre del candidato
	 * @param parametrosActa Los parametros para el acta en donde se cargara la informacion del subreporte
	 * @param listaRepresentantes La lista completa de representantes de partidos y de candidatos independientes
	 * a presentar en el acta
	 * @throws Exception Excepcion generica que me permite cachar cualquier excepcion ocurrida
	 * en el proceso y enmascarla para presentarla en capas superiores
	 */
	private void llenarSubreporteRepresentantes(Map<String, Object> parametrosActa, HLPDatosActa hlpActa) throws Exception
	{
		String rutaImgsGluster = "" + rutaGluster;
		char caracter = rutaImgsGluster.charAt(rutaImgsGluster.length()-1);
		if( caracter=='/' || caracter=='\\' )
			rutaImgsGluster = rutaImgsGluster.substring(0, rutaImgsGluster.length()-1);
		
		//Extraemos el titulo que llevara el subreporte
		String tituloRepresentantes = "";
		//Titulo de que llevaran las columnas de emblemas/nombres de candidatos independientes
		String tituloRenglon = "";
		
		if( hlpActa.getIdTipoCandidatura().intValue()!=Constantes.ID_TIPO_CAND_REGIDURIA_RP.intValue() &&
				hlpActa.getIdTipoCandidatura().intValue()!=Constantes.ID_TIPO_CAND_DIPUTADO_RP.intValue() )
		{
			//En los demas tipos de actas se tienen representantes de partidos y/o candidatos independientes 
			tituloRepresentantes = Utilidades.mensajeProperties("etiqueta_acta_titulo_representantes_pp_ci");
			tituloRenglon = Utilidades.mensajeProperties("etiqueta_acta_titulo_pp_ci");
		}
		else
		{
			//En actas RP solo van representantes de partidos
			tituloRepresentantes = Utilidades.mensajeProperties("etiqueta_acta_titulo_representantes_pp");
			tituloRenglon = Utilidades.mensajeProperties("etiqueta_acta_titulo_pp");
		}
		//Nomenclaturas utilizadas en la calidad de los puestos de los representantes 
		String descripcionCalidad = Utilidades.mensajeProperties("etiqueta_acta_propietarios_suplentes");
		
		String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/pdfs/");
		//Establecemos la ruta donde se almacena el molde jasper a utilizar
		String pathSubReporte = (rutaJasper + File.separator + "representantes.jasper").replace("/", File.separator);
		//Cargamos el molde jasper
		JasperReport subreporte = (JasperReport)JRLoader.loadObjectFromFile(pathSubReporte);
		
		List<HLPRepresentante> representantes = new ArrayList<HLPRepresentante>();
		
		//Ajustamos la ruta de emblemas para partidos y coaliciones
		if( hlpActa.getDatosGenerales().getRepresentantes()!=null && !hlpActa.getDatosGenerales().getRepresentantes().isEmpty() )
		{
			for (HLPRepresentante infoRepresentante : hlpActa.getDatosGenerales().getRepresentantes())
			{
				HLPRepresentante hlpRepresentante  = new HLPRepresentante();
				hlpRepresentante.setNombre(infoRepresentante.getNombre());
				hlpRepresentante.setaPaterno(infoRepresentante.getaPaterno());
				hlpRepresentante.setaMaterno(infoRepresentante.getaMaterno());
				hlpRepresentante.setCalidad(infoRepresentante.getCalidad());
				hlpRepresentante.setTipoAsociacion(infoRepresentante.getTipoAsociacion());
				//Para los candidatos independientes, el nombre del candidato seguira viniendo en nombreAsociacion
				//(en las distribuciones si vendra como emblema)
				String nombreAsociacion = infoRepresentante.getNombreAsociacion();
				String emblema = infoRepresentante.getEmblemaAsociacion();
				
				if( infoRepresentante.getTipoAsociacion()!=null )
				{
					if( nombreAsociacion!=null && infoRepresentante.getTipoAsociacion().intValue()==Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE )
						hlpRepresentante.setNombreAsociacion(nombreAsociacion.trim());
					else
					{
						//Solo pasamos la imagen si es partido o coalicion
						if( (infoRepresentante.getTipoAsociacion().intValue()==Constantes.TIPO_ASOCIACION_PARTIDO || 
								infoRepresentante.getTipoAsociacion().intValue()==Constantes.TIPO_ASOCIACION_COALICIONES) &&
								(emblema!=null && !emblema.trim().equals("")) )
							hlpRepresentante.setEmblemaAsociacion(rutaImgsGluster + emblema.trim());
					}
				}
				representantes.add(hlpRepresentante);
			}
		}
		
		//Establecemos los parametros que llevara el subreporte con los resultados de la votacion
		Map<String, Object> parametrosSubreporte = new HashMap<String, Object>();
		parametrosSubreporte.put("P_TITULO_REPRESENTANTES", tituloRepresentantes);
		parametrosSubreporte.put("P_TITULO_RENGLON", tituloRenglon);
		parametrosSubreporte.put("P_TIPO_ASOCIACION_CI", Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE);
		parametrosSubreporte.put("P_DESCRIPCION_CALIDAD", descripcionCalidad);
		
		//Cargamos los datos que requiere el subreporte
		JRBeanCollectionDataSource dataSubreporte = new JRBeanCollectionDataSource(representantes);
		
		//Pasamos la informacion del subreporte al reporte principal que englobara a todos
		parametrosActa.put("P_SUBREPORTE_REPRESENTANTES", subreporte );
		parametrosActa.put("P_DATA_SUBREPORTE_REPRESENTANTES", dataSubreporte);
		parametrosActa.put("P_SUBREPORT_PARAMS_REPRESENTANTES", parametrosSubreporte);
	}
	//Metodo temporal para probar cambios, cuando no se tienen datos para probar en bd
	public HLPDatosActa cargarDatosTemporales() throws Exception
	{
		//Regiduria MR - YA
		//Regiduria MR recuento - YA
		//Regiduria RP - YA
		//Regiduria RP recuento - YA
		//Ayuntamiento - YA
		//Ayuntamiento recuento - YA
		//Diputados MR parcial - YA
		//Diputados MR parcial recuento - YA
		//Diputados RP parcial - YA
		//Diputados RP parcial recuento - YA
		//Gobernador parcial - YA
		//Gobernador parcial recuento - YA
		
		//Diputados MR - YA
		//Diputados MR recuento - YA
		
		//Actas Municipales
		HLPDatosActa hlpActa = new HLPDatosActa();
		boolean esRecuento = false;
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_REGIDURIA_MR, true, false, esRecuento);
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_REGIDURIA_RP, true, false, esRecuento);
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_AYUNTAMIENTO, true, false, esRecuento);
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_DIPUTADO_MR, true, true, esRecuento);
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_DIPUTADO_RP, true, true, esRecuento);
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_GOBERNADOR, true, false, esRecuento);
		//Actas OPLE
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_DIPUTADO_MR, false, true, esRecuento);
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_DIPUTADO_RP, false, true, esRecuento);//distrital
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_DIPUTADO_RP, false, false, esRecuento);//estatal
//		hlpActa = HLPDatosPruebaActas.cargarDatosTemporal(Constantes.ID_TIPO_CAND_GOBERNADOR, false, false, esRecuento);
		
		this.esUsuarioMunicipal = hlpActa.getIdMunicipio()!=null && hlpActa.getIdMunicipio().intValue()>0;
		this.esActaConDistrito = hlpActa.getIdDistrito()!=null && hlpActa.getIdDistrito().intValue()>0;
		
		return hlpActa;
	}
}
