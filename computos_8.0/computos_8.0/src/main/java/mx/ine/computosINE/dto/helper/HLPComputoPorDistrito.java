package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.reportes.DTOEncabezadoAsociaciones;
import mx.ine.computosINE.util.Utilidades;

public class HLPComputoPorDistrito implements Serializable {

	private static final long serialVersionUID = 130520171159L;

	private static final int ALTO_CELDA_TABLA_UNO = 1;
	private static final int ANCHO_CELDA_TABLA_UNO = 1;
	private static final int TIPO_FILA_TABLA_UNO = 1;
	private static final int NUM_COLUMNAS_OMITIENDO_ASOCIACIONES = 3;

	// Para almacenar las filas del encabezado
	private List<HLPReportesEncabezado> listaEncabezados;
	// Lista de las asociaciones
	private List<DTOEncabezadoAsociaciones> asociaciones;

	private String tituloReporte;
	private Integer columnas;

	public void iniciaReporte(DTOFiltroReportes filtros) {
		this.listaEncabezados = new ArrayList<HLPReportesEncabezado>();
		int indexColumna = 0;

		//Se acompleta el titulo del reporte
		obtenTituloReporte(filtros.getTipoCandidatura());

		// Nuúmero de columnas que tendrá el reporte
		this.columnas = this.asociaciones.size()
				+ NUM_COLUMNAS_OMITIENDO_ASOCIACIONES;

		// 1er fila del Encabezado
		HLPReportesEncabezado hlpReportesEncabezado = new HLPReportesEncabezado();
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
				Utilidades.mensajeProperties("etiqueta_tablatitulo_Distrito"),
				TIPO_FILA_TABLA_UNO);
		hlpReportesEncabezado
				.ingresarEncabezado(
						indexColumna++,
						ANCHO_CELDA_TABLA_UNO,
						ALTO_CELDA_TABLA_UNO,
						Utilidades
								.mensajeProperties("etiqueta_tablatitulo_nombreDistrito"),
						TIPO_FILA_TABLA_UNO);

		for (int i = 0; i < this.asociaciones.size(); i++) {
			hlpReportesEncabezado.ingresarEncabezado(i + indexColumna,
					ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
					this.asociaciones.get(i).getSiglas(), TIPO_FILA_TABLA_UNO);
		}
		indexColumna += this.asociaciones.size();

		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
				Utilidades.mensajeProperties("etiqueta_tablatitulo_Total"),
				TIPO_FILA_TABLA_UNO);
		listaEncabezados.add(hlpReportesEncabezado);
	}

	private void obtenTituloReporte(String tipoCandidatura) {
		String titulo = Utilidades
				.mensajeProperties("etiqueta_reporte_titulo_computoPorDistrito");
		titulo += " para " + tipoCandidatura;
		this.tituloReporte = titulo.toUpperCase();
	}

	public List<HLPReportesEncabezado> getListaEncabezados() {
		return listaEncabezados;
	}

	public void setListaEncabezados(List<HLPReportesEncabezado> listaEncabezados) {
		this.listaEncabezados = listaEncabezados;
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

	public List<DTOEncabezadoAsociaciones> getAsociaciones() {
		return asociaciones;
	}

	public void setAsociaciones(List<DTOEncabezadoAsociaciones> asociaciones) {
		this.asociaciones = asociaciones;
	}

}
