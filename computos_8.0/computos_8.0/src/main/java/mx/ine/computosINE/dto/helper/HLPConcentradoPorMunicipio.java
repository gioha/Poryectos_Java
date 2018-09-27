package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.reportes.DTOEncabezadoAsociaciones;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HLPConcentradoPorMunicipio  implements Serializable{
	private static final long serialVersionUID = -4411399245464278554L;
	private static final Log logger = LogFactory.getLog(HLPConcentradoPorMunicipio.class);
	
	private static final int ALTO_CELDA_TABLA_UNO=1;
	private static final int ANCHO_CELDA_TABLA_UNO=1;
	private static final int TIPO_FILA_TABLA_UNO=1;
	private static final int NUM_COLUMNAS_OMITIENDO_ASOCIACIONES= 3;
	
	
	private List<HLPReportesEncabezado> listaEncabezados;
	private String tituloReporte;
	private String idMunicipio;
	private String nombreMunicipio;
	private String total;
	private Integer columnas;
	
	/*Valores pertenecientes al encabezado*/
	private List<DTOEncabezadoAsociaciones> encabezados;
	
	public void iniciaReporte(DTOFiltroReportes filtros){
		this.listaEncabezados= new ArrayList<HLPReportesEncabezado>();
		int indexColumn = 0;
		
		//Se acompleta el titulo del reporte
		obtenTituloReporte(filtros.getTipoCandidatura());
		
		// Número de columnas que tendrá el reporte
		this.columnas = this.encabezados.size() 
				+ NUM_COLUMNAS_OMITIENDO_ASOCIACIONES;
		
		// 1er fila del Encabezado
		HLPReportesEncabezado hlpReportesEncabezado = new HLPReportesEncabezado();
		hlpReportesEncabezado.ingresarEncabezado(
				indexColumn++, 
				ANCHO_CELDA_TABLA_UNO, 
				ALTO_CELDA_TABLA_UNO,
				Utilidades.mensajeProperties("etiqueta_tablatitulo_numero"), 
				TIPO_FILA_TABLA_UNO);
		
		hlpReportesEncabezado.ingresarEncabezado(
				indexColumn++, 
				ANCHO_CELDA_TABLA_UNO, 
				ALTO_CELDA_TABLA_UNO, 
				Utilidades.mensajeProperties("etiqueta_tablatitulo_nombreMunicipio"), 
				TIPO_FILA_TABLA_UNO);
		
		for (int i = 0; i < this.encabezados.size(); i++) {
			hlpReportesEncabezado.ingresarEncabezado(
					i + indexColumn,
					ANCHO_CELDA_TABLA_UNO,
					ALTO_CELDA_TABLA_UNO, 
					this.encabezados.get(i).getSiglas(),
					TIPO_FILA_TABLA_UNO);
		}
		indexColumn += this.encabezados.size();
		
		hlpReportesEncabezado.ingresarEncabezado(
				indexColumn++,
				ANCHO_CELDA_TABLA_UNO,
				ALTO_CELDA_TABLA_UNO, 
				Utilidades.mensajeProperties("etiqueta_tablatitulo_Total"),
				TIPO_FILA_TABLA_UNO);
		logger.info(" TOTAL :" +hlpReportesEncabezado);
		
		listaEncabezados.add(hlpReportesEncabezado);
		
	}

	private void obtenTituloReporte(String tipoCandidatura) {
		String titulo = Utilidades
				.mensajeProperties("etiqueta_reporte_titulo_concentradoPorMunicipio");
		titulo += " para " + tipoCandidatura;
		this.tituloReporte = titulo.toUpperCase();
	}

	/**
	 * @return the listaEncabezados
	 */
	public List<HLPReportesEncabezado> getListaEncabezados() {
		return listaEncabezados;
	}

	/**
	 * @param listaEncabezados the listaEncabezados to set
	 */
	public void setListaEncabezados(List<HLPReportesEncabezado> listaEncabezados) {
		this.listaEncabezados = listaEncabezados;
	}

	/**
	 * @return the tituloReporte
	 */
	public String getTituloReporte() {
		return tituloReporte;
	}

	/**
	 * @param tituloReporte the tituloReporte to set
	 */
	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}

	/**
	 * @return the idMunicipio
	 */
	public String getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return the nombreMunicipio
	 */
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	/**
	 * @param nombreMunicipio the nombreMunicipio to set
	 */
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the columnas
	 */
	public Integer getColumnas() {
		return columnas;
	}

	/**
	 * @param columnas the columnas to set
	 */
	public void setColumnas(Integer columnas) {
		this.columnas = columnas;
	}

	/**
	 * @return the encabezados
	 */
	public List<DTOEncabezadoAsociaciones> getEncabezados() {
		return encabezados;
	}

	/**
	 * @param encabezados the encabezados to set
	 */
	public void setEncabezados(List<DTOEncabezadoAsociaciones> encabezados) {
		this.encabezados = encabezados;
	}

}
