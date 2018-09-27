package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.util.Utilidades;

public class HLPEstatusDeCasillas implements Serializable{

	private static final long serialVersionUID = 060620171214L;

	private static final int ALTO_CELDA_TABLA_UNO = 1;
	private static final int ANCHO_CELDA_TABLA_UNO = 1;
	private static final int TIPO_FILA_TABLA_UNO = 1;
	private static final int NUM_COLUMNAS = 7;

	// Para almacenar las filas del encabezado
	private List<HLPReportesEncabezado> listaEncabezados;
	// Lista de las asociaciones
	private String tituloReporte;
	private Integer columnas;

	/**
	 * Se inicializan medidas de las celdas para los datos del reporte en HTML
	 * @param tipoCandidatura 
	 * @param nombreDemarcacion 
	 * @param nombreDistrito 
	 */
	public void iniciaReporte(DTOFiltroReportes filtros) {
		this.listaEncabezados = new ArrayList<HLPReportesEncabezado>();
		int indexColumna = 0;

		// Se acompleta el titulo del reporte
		obtenTituloReporte(filtros.getTipoCandidatura());

		// Nuúmero de columnas que tendrá el reporte
		this.columnas = NUM_COLUMNAS;

		// 1er fila del Encabezado
		HLPReportesEncabezado hlpReportesEncabezado = new HLPReportesEncabezado();
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
				"", TIPO_FILA_TABLA_UNO);

		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
						Utilidades.mensajeProperties("etiqueta_reporte_estatus_cotejo"), TIPO_FILA_TABLA_UNO);
		
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
						Utilidades.mensajeProperties("etiqueta_reporte_estatus_recuento_parcial"), TIPO_FILA_TABLA_UNO);
		
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
						Utilidades.mensajeProperties("etiqueta_reporte_estatus_casilla_no_instalada"), TIPO_FILA_TABLA_UNO);
		
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
						Utilidades.mensajeProperties("etiqueta_reporte_estatus_paquete_no_entregado"), TIPO_FILA_TABLA_UNO);
		
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
						Utilidades.mensajeProperties("etiqueta_reporte_estatus_recuento_total"), TIPO_FILA_TABLA_UNO);
		
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
						Utilidades.mensajeProperties("etiqueta_reporte_estatus_totales"), TIPO_FILA_TABLA_UNO);
		
		this.listaEncabezados.add(hlpReportesEncabezado);
	}

	private void obtenTituloReporte(String tipoCandidatura) {		
		String titulo = Utilidades
				.mensajeProperties("etiqueta_reporte_titulo_estatusCasillas");
		// Se agrega el nombre de la candidatura
		titulo += " para " + tipoCandidatura;
		this.tituloReporte = titulo.toUpperCase();
	}

	public String getTituloReporte() {
		return tituloReporte;
	}

	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}

	public Integer getColumnas() {
		return columnas;
	}

	public void setColumnas(Integer columnas) {
		this.columnas = columnas;
	}

	public List<HLPReportesEncabezado> getListaEncabezados() {
		return listaEncabezados;
	}

	public void setListaEncabezados(List<HLPReportesEncabezado> listaEncabezados) {
		this.listaEncabezados = listaEncabezados;
	}
}
