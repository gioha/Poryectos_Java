package mx.ine.computosINE.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.reportes.DTOEncabezadoAsociaciones;
import mx.ine.computosINE.util.Utilidades;

public class HLPComputoPorCasilla implements Serializable {

	private static final long serialVersionUID = 210420170100L;

	private static final int ALTO_CELDA_TABLA_UNO = 1;
	private static final int ANCHO_CELDA_TABLA_UNO = 1;
	private static final int TIPO_FILA_TABLA_UNO = 1;
	private static final int NUM_COLUMNAS_OMITIENDO_ASOCIACIONES = 5;

	// Para almacenar las filas del encabezado
	private List<HLPReportesEncabezado> listaEncabezados;
	// Lista de las asociaciones
	private List<DTOEncabezadoAsociaciones> asociaciones;
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

		//Se acompleta el titulo del reporte
		obtenTituloReporte(filtros.getTipoCandidatura(), filtros.getNombreDistrito(), filtros.getNombreDemarcacion());

		// Nuúmero de columnas que tendrá el reporte
		this.columnas = this.asociaciones.size()
				+ NUM_COLUMNAS_OMITIENDO_ASOCIACIONES;

		// 1er fila del Encabezado
		HLPReportesEncabezado hlpReportesEncabezado = new HLPReportesEncabezado();
		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
				Utilidades.mensajeProperties("etiqueta_tablatitulo_Seccion"),
				TIPO_FILA_TABLA_UNO);

		hlpReportesEncabezado
				.ingresarEncabezado(
						indexColumna++,
						ANCHO_CELDA_TABLA_UNO,
						ALTO_CELDA_TABLA_UNO,
						Utilidades
								.mensajeProperties("etiqueta_tablatitulo_CasillaAprobada"),
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

		hlpReportesEncabezado
				.ingresarEncabezado(
						indexColumna++,
						ANCHO_CELDA_TABLA_UNO,
						ALTO_CELDA_TABLA_UNO,
						Utilidades
								.mensajeProperties("etiqueta_tablatitulo_ListaNominalCasilla"),
						TIPO_FILA_TABLA_UNO);

		hlpReportesEncabezado.ingresarEncabezado(indexColumna++,
				ANCHO_CELDA_TABLA_UNO, ALTO_CELDA_TABLA_UNO,
				Utilidades.mensajeProperties("etiqueta_tablatitulo_Estatus"),
				TIPO_FILA_TABLA_UNO);

		this.listaEncabezados.add(hlpReportesEncabezado);
	}

	private void obtenTituloReporte(String tipoCandidatura,
			String nombreDistrito, String nombreDemarcacion) {
		boolean distritoSeCapturo = nombreDistrito != null
				&& !nombreDistrito.isEmpty();
		boolean demarcacionSeCapturo = nombreDemarcacion != null
				&& !nombreDemarcacion.isEmpty();
		String titulo = Utilidades
				.mensajeProperties("etiqueta_reporte_titulo_computoPorcasilla");
		// Se agrega el nombre de la candidatura
		titulo += " para " + tipoCandidatura;
		// Se agrega el nombre del distrito para cuando se selecciona la
		// candidatura de diputados
		if (distritoSeCapturo) {
			titulo += " - " + nombreDistrito;
		}
		// Se agrega el nombre de la demarcacion para cuando se selecciona la
		// candidatura de regidores
		else if (demarcacionSeCapturo) {
			titulo += " - " + nombreDemarcacion;
		}
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

	public List<DTOEncabezadoAsociaciones> getAsociaciones() {
		return asociaciones;
	}

	public void setAsociaciones(List<DTOEncabezadoAsociaciones> asociaciones) {
		this.asociaciones = asociaciones;
	}

}
