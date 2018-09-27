package mx.ine.computosINE.helper;

import java.io.Serializable;

import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;

/**
 * Clase Helper para guardar los datos del tipo de candidatura
 * según el tipo de acta que se vaya a generar.
 * @author Jean Pierre
 *
 */
public class HLPTipoCandidatura implements Serializable{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -7984196461952668609L;
	
	/*
	 * EL id del tipo de candidatura
	 */
	private Integer idTipoCandidatura;
	
	/*
	 * EL nombre corto del tipo de candidatura
	 */
	private String nombreCorto;
	
	/*
	 * EL nombre largo del tipo de candidatura
	 */
	private String nombreLargo;
	
	
	/*
	 * Titulo de la vista para generación acta
	 */
	private String titulo;
	
	/**
	 *  Constructor vacío
	 */
	public HLPTipoCandidatura(){
		
	}
	
	/**
	 * Asigna valores a los atributos de la clase dependiendo del tipo de 
	 * acta que se vaya a generar.
	 * @author Alejandra Gomez
	 * @param tipoActa - El tipo de acta que se vaya a generar.
	 */
	public HLPTipoCandidatura(String tipoActa){
		switch(tipoActa){
		case Constantes.DIPUTADO_MR:
			idTipoCandidatura = Constantes.ID_TIPO_CAND_DIPUTADO_MR;
			nombreCorto = Constantes.NOM_CORTO_DIPUTADO_MR;
			nombreLargo = Constantes.NOM_LARGO_DIPUTADO_MR;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_diputados_mr");
			break;
		case Constantes.DIPUTADO_RP:
			
			idTipoCandidatura = Constantes.ID_TIPO_CAND_DIPUTADO_RP;
			nombreCorto = Constantes.NOM_CORTO_DIPUTADO_RP;
			nombreLargo = Constantes.NOM_LARGO_DIPUTADO_RP;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_diputados_rp");
			break;
		case Constantes.DIPUTADO_RP_ESTATAL:
			
			idTipoCandidatura = Constantes.ID_TIPO_CAND_DIPUTADO_RP;
			nombreCorto = Constantes.NOM_CORTO_DIPUTADO_RP;
			nombreLargo = Constantes.NOM_LARGO_DIPUTADO_RP;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_diputados_rp_estatal");
			break;
			
		case Constantes.GOBERNADOR:
			idTipoCandidatura = Constantes.ID_TIPO_CAND_GOBERNADOR;
			nombreCorto = Constantes.NOM_CORTO_GOBERNADOR;
			nombreLargo = Constantes.NOM_LARGO_GOBERNADOR;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_gobernador");
		   break;
		case Constantes.AYUNTAMIENTO:
			idTipoCandidatura = Constantes.ID_TIPO_CAND_AYUNTAMIENTO;
			nombreCorto = Constantes.NOM_CORTO_AYUNTAMIENTO;
			nombreLargo = Constantes.NOM_LARGO_AYUNTAMIENTO;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_ayuntamiento");
			break;
		case Constantes.REGIDURIA_MR:
			idTipoCandidatura = Constantes.ID_TIPO_CAND_REGIDURIA_MR;
			nombreCorto = Constantes.NOM_CORTO_REGIDURIA_MR;
			nombreLargo = Constantes.NOM_LARGO_REGIDURIA_MR;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_regiduria_mr");
			break;
		case Constantes.REGIDURIA_RP:
			idTipoCandidatura = Constantes.ID_TIPO_CAND_REGIDURIA_RP;
			nombreCorto = Constantes.NOM_CORTO_REGIDURIA_RP;
			nombreLargo = Constantes.NOM_LARGO_REGIDURIA_RP;
			titulo = Utilidades.mensajeProperties("etiqueta_titulo_generacion_actas_regiduria_rp");
			break;
		default:
			idTipoCandidatura = Constantes.ID_TIPO_CANDIDATURA_INICIAL;
			nombreCorto = "";
			nombreLargo = "";
			break;
		}
	}
	
	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}
	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNombreLargo() {
		return nombreLargo;
	}
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
