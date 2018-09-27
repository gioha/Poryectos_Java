package mx.ine.computosINE.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Clase auxiliar para el uso de constantes en java
 * @author INE
 * @since Abril, 2017
 * @copyright Instituto Nacional Electoral (INE). Todos los derechos reservados.
 */
@Component("constante")
public class Constantes implements Serializable{
	
	private static final long serialVersionUID = 5731594271746237740L;
	
	/* ************************* Constantes de mensajes utilizados en java ************************* */
	
	/**
	 * Constantes para las acciones del modulo de generación de acta
	 */
	public static final String ACCION_CAPTURAR					= "C";
	public static final String ACCION_MODIFICAR 				= "M";
	public static final String ACCION_CONSULTAR                 = "Q";
	
	/**
	 * Constantes para definir los tipos de candidatura del estado de Nayarit
	 */
	public static final String DIPUTADO_MR                      = "DIPUTADO_MR";
	public static final String DIPUTADO_RP                      = "DIPUTADO_RP";
	public static final String DIPUTADO_RP_ESTATAL              = "DIPUTADO_RP_ESTATAL";
	public static final String GOBERNADOR                       = "GOBERNADOR";
	public static final String AYUNTAMIENTO                     = "AYUNTAMIENTO";
	public static final String REGIDURIA_MR						= "REGIDURIA_MR";
	public static final String REGIDURIA_RP                     = "REGIDURIA_RP";
	
	/**
	 * Constantes para definir los nombres cortos de los diferentes tipos de candidaturas de Nayarit
	 */
	public static final String NOM_CORTO_DIPUTADO_MR            = "DIPUTADO LOCAL MR";
	public static final String NOM_CORTO_DIPUTADO_RP            = "DIPUTADO LOCAL RP";
	public static final String NOM_CORTO_GOBERNADOR             = "GOBERNADOR ESTATAL";
	public static final String NOM_CORTO_AYUNTAMIENTO           = "PRESIDENTE MUNICIPAL";
	public static final String NOM_CORTO_REGIDURIA_MR			= "REGIDOR FISCALIZABLE MR";
	public static final String NOM_CORTO_REGIDURIA_RP           = "REGIDOR FISCALIZABLE RP";
	
	public static final String NOM_CORTO_DIPUTADO_RP_MR_ESTATAL  	="DIPUTADO RP/MR ESTATAL";

	/**
	 * Constantes para definir los nombres largos de los diferentes tipos de candidaturas
	 */
	public static final String NOM_LARGO_DIPUTADO_MR            = "DIPUTADOS LOCAL POR PRINCIPIO DE MAYOR\u00CDA RELATIVA";
	public static final String NOM_LARGO_DIPUTADO_RP            = "DIPUTADOS LOCAL POR PRINCIPIO DE REPRESENTACI\u00D3N PROPORCIONAL";
	public static final String NOM_LARGO_GOBERNADOR             = "GOBERNADOR ESTATAL";
	public static final String NOM_LARGO_AYUNTAMIENTO           = "PRESIDENTES MUNICIPALES";
	public static final String NOM_LARGO_REGIDURIA_MR			= "REGIDOR FISCALIZABLE POR PRINCIPIO DE MAYOR\u00CDA RELATIVA";
	public static final String NOM_LARGO_REGIDURIA_RP           = "REGIDOR FISCALIZABLE POR PRINCIPIO DE REPRESENTACI\u00D3N PROPORCIONAL";
	
	/***
	 *Constantes para definir los id´s de los tipos de candidaturas 
	 */
	public static final Integer ID_TIPO_CANDIDATURA_INICIAL     = -1;
	public static final Integer ID_TIPO_CAND_DIPUTADO_MR 		= 7;
	public static final Integer ID_TIPO_CAND_DIPUTADO_RP 		= 8;
	public static final Integer ID_TIPO_CAND_GOBERNADOR			= 6;
	public static final Integer ID_TIPO_CAND_AYUNTAMIENTO       = 9;
	public static final Integer ID_TIPO_CAND_REGIDURIA_MR       = 15;
	public static final Integer ID_TIPO_CAND_REGIDURIA_RP       = 16;
	
	public static final Integer ID_TIPO_CAND_DIPUTADO_RP_MR_ESTATAL = 0;
	
	
	/**
	 * Constantes para definir los tipos de roles que participaran en la eleccion local
	 */
	public static final String ADMIN_PARAM_CAPTURA_OC		    = "COMPUTOS.ADMIN.PARAM.CAPTURA.OC";
	public static final String ADMIN_CAPTURA_OC                 = "COMPUTOS.ADMIN.CAPTURA.OC";
	public static final String CONSULTA_CONSEJERO_OC			= "COMPUTOS.CONSEJERO.OC";
	public static final String CONSULTA_0C                      = "COMPUTOS.CONSULTA.OC";
	public static final String ADMIN_LECTURA_CAU_OC             = "COMPUTOS.CAU.OC";
	public static final String CAPTURA_JL						= "COMPUTOS.CAPTURA.JL";
	public static final String CONSULTA_JL						= "COMPUTOS.CONSULTA.JL";
	public static final String CAPTURA_MUNICIPAL                = "COMPUTOS.CAPTURA.JM";
	public static final String CONSULTA_MUNICIPAL               = "COMPUTOS.CONSULTA.JM";
	public static final String CAPTURA_JD						= "COMPUTOS.CAPTURA.JD";
	public static final String CONSULTA_JD                      = "COMPUTOS.CONSULTA.JD";
	public static final String CONSULTA_PARTIDO_JL				= "COMPUTOS.PARTIDO.CONSULTA.JL";
	public static final String CONSULTA_PARTIDO_JM				= "PARTIDO.CONSULTA.JM";
	public static final String CAPTURA_OPLE                     = "COMPUTOS.ADMIN.EXT.OPLE.JL";
	
	
	/**
	 * Identificadores de los tipos de Asociaciones Politicas registradas en el sistema de Órganos Directivos
	 */
	//1.-PARTIDO, 2.-AGRUPACION, 3.-COALICION, 4.-CANDIDATO_INDEPENDIENTE, 5.-CANDIDATURA COMÚN|
	public static final Integer TIPO_ASOCIACION_PARTIDO				= 1;
	public static final Integer TIPO_ASOCIACION_AGRUPACION			= 2;
	public static final Integer TIPO_ASOCIACION_COALICIONES			= 3;
	public static final Integer TIPO_ASOCIACION_CAND_INDEPENDIENTE	= 4;
	public static final Integer TIPO_ASOCIACION_CAND_COMUN			= 5;
	public static final Integer TIPO_ASOCIACION_CAND_NO_REG			= -1;
	public static final Integer TIPO_ASOCIACION_VOTOS_NULOS			= -2;
	
	
	public static final Integer TIPO_ACTA_PARCIAL = 0;
	public static final Integer TIPO_ACTA_FINAL = 1;
	
	/**
	 * Para el tipo de acta diputado RP a nivel estatal
	 */
	public static final Integer TIPO_ACTA_ESTATAL = 2;
	
	
	/**
	 * Constantes para las acciones de los modulos de captura de votos
	 */
	public static final String ACCION_CAPTURA					= "CAPTURA";
	public static final String ACCION_MODIFICA 					= "MODIFICA";
	public static final String ACCION_CONSULTA                 	= "CONSULTA";
	public static final String TITULO_CAND_NO_REGISTRADO		= "CANDIDATOS NO REGISTRADOS";
	
	/* ************************* Constantes de mensajes utilizados en REPORTES ************************* */
	
	public static final String TITULO_VOTOS_NULOS				= "VOTOS NULOS";
	
	/**
         * Constantes para los códigos de mensaje en el ApplicationException
         */
	public static final int CODIGO_ERROR                           = 1;
	public static final int CODIGO_WARN                            = 2;
	public static final int CODIGO_INFO                            = 3;
	
	/**
	 * Constantes para identificar en entorno geografico de la candidatura
	 */
	public static final int ENTORNO_ESTADO                         = 1;
	public static final int ENTORNO_DISTRITO                       = 2;
	public static final int ENTORNO_MUNICIPIO                      = 3;
	public static final int ENTORNO_DEMARCACION                    = 4;
	
	/**
	 * Constante para identificar que el tipo de casilla es especial
	 */
	public static final String  TIPO_CASILLA_ESPECIAL			= "S";
	
	/**
	 * Constantes de mapeos de tablas de la BD (Utilizado para hql)
	 */
	public static final String REPRESENTANTES                              = "DTORepresentante";
	public static final String CONSEJEROS                                  = "DTOConsejero";
	public static final String ACTA_TIPO_CANDIDATURA                       = "DTOActaTipoCandidatura";
	public static final String DISTRIBUCION_TOT_PARCIAL                    = "DTODistribucionTotParcial";
	public static final String DISTRIBUCION_TOTALES                        = "DTODistribucionTotales";
	public static final String DISTRIBUCION_PARTIDOS_CI                    = "DTODistribucionPartidosCI";
	public static final String DISTRIBUCION_CANDIDATOS                     = "DTODistribucionCandidatos";
	public static final String DISTRIBUCION_CAND_PARCIAL                   = "DTODistribucionCandParcial";
	
	
	/**
	 *Constantes para identificar cuando no se tiene alguno de los datos 
	 *
	 */
	public static final Integer SIN_DISTRITO								= -5;
	public static final Integer SIN_MUNICIPIO                               = -6;
	public static final Integer SIN_REGIDURIA								= -7;
	public static final Integer SIN_COMUNIDAD								= -8;
	public static final Integer SIN_SECCION								    = -9;
	public static final Integer SIN_COALICION                               = -10;
	
	/**
	 * Constantes para llenar combos de candidaturas en reportes
	 * 
	 */
	public static final String NOM_GOBERNADOR = "GOBERNADOR";
	public static final String NOM_AYUNTAMIENTO = "PRESIDENTE MUNICIPAL";
	public static final String NOM_REGIDOR_MR = "REGIDOR MR";
	public static final String NOM_REGIDOR_RP = "REGIDOR RP";
	public static final String NOM_DIPUTADO_MR = "DIPUTADO LOCAL MR";
	public static final String NOM_DIPUTADO_RP = "DIPUTADO LOCAL RP";
	
	/**
	 * Constantes para asignar el valor del emblema cuando sea candidatos no registrados o votos nulos
	 * 
	 */
	public static final String  EMBLEMA_CNREG								 ="CNREG";
	public static final String  EMBLEMA_NULOS								 ="NULOS";
	public static final String  EMBLEMA_CI								 	 ="CIL";
	
	
	/**
	 * Constantes de version del sistema
	 */
	public static final String OC                          = "OC";
	public static final String JL                          = "JL";
	public static final String JM                          = "JM";
	
        /**
         * Constantes para exportar aexcel y pdf
         */
        public static final String PARAMETRO_OBJECT_ESTADO              = "estado";
        public static final String PARAMETRO_OBJECT_MUNICIPIO           = "municipio";
        public static final String PARAMETRO_OBJECT_PARTIDO             = "partido";
        public static final String PARAMETRO_STRING_TITULO              = "titulo";
        public static final String PARAMETRO_INTEGER_COLUMNAS           = "columnas";
        public static final String PARAMETRO_ANCHOS_COLUMNAS            = "anchos";

	
        /**
          * Map que contiene todas constantes manejadas en el sistema las cuales pueden ser accesadas mediante los
          * m&eacute;todos p&uacute;blicos
          */
         static Map<String, Object> constantes;
        
         static {
             constantes = new HashMap<>();
             constantes.put("ID_TIPO_CAND_REGIDURIA_MR", ID_TIPO_CAND_REGIDURIA_MR);
             constantes.put("ID_TIPO_CAND_GOBERNADOR", ID_TIPO_CAND_GOBERNADOR);
         }
         
         /**
          * Regresa un objeto generico <code>Object</code> de la constante solicitada.
          *
          * @param llaveConstante : llave
          *
          * @return Object : Constante
          *
          * @author José Antonio López Torres
          * @since 21/9/2016
          */
         public static Object getConstante(String llaveConstante) {
             return constantes.get(llaveConstante);
         }
}	
