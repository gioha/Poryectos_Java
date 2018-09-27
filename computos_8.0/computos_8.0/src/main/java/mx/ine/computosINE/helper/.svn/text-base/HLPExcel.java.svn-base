/**
 * @(#)HLPExcel.java 27/03/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.helper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;

import mx.ine.common.fechas.impl.ValidacionFechas;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase de ayuda para la manipulación del documento excel generado por el componente de primefaces
 *
 * @author José Antonio López Torres
 * @copyright Direccion de sistemas - INE
 * @since 27/03/2017
 */
@Scope("prototype")
@Component("hlpExcel")
public class HLPExcel {
    /**
     * Elemento para generar log
     */
    private static final Log LOGGER = LogFactory.getLog(HLPExcel.class);

    /**
     * Parámetros
     */
    private Map<String, Serializable> parametros;

    /**
     * Número de columnas
     */
    private int num;
    /**
     * Número de filas por default
     */
    private int rowDefault;

    /**
     * Estilo de celda con fuente en negritas
     */
    private CellStyle estiloCelda;
    /**
     * Estilo de celda con fuente en negritas y centrada
     */
    private CellStyle estiloCeldaCentrada;
    /**
     * Estilo de celda con fuente en negritas alineado a la derecha
     */
    private CellStyle estiloCeldaDerecha;
    /**
     * Estilo de celda de encabezado con relleno gris
     */
    private CellStyle estiloEncabezado;
    /**
     * Estilo de celda con fuente en negritas y relleno gris
     */
    private CellStyle estiloCeldaGris;
    /**
     * Fuente de negritas
     */
    private Font fuente;

    /**
     * Método encargado de manipular el documento excel generado y agregar información extra
     *
     * @param document   : Documento
     * @param parametros : Parámetros
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    public void postProcessXLS(Object document, Map<String, Serializable> parametros) {
        this.parametros = parametros;
        // Valor por default de columnas
        num = 4;
        rowDefault = 7;
        if (this.parametros.containsKey(Constantes.PARAMETRO_INTEGER_COLUMNAS)) {
            num = (Integer) parametros.get(Constantes.PARAMETRO_INTEGER_COLUMNAS);
        }
        // Validamos si el reporte tiene un rango entre 2 y 3 columnas
        if (num >= 2 && num < 4) {
            rowDefault = 8;
        }
        // Documento y Hoja
        HSSFWorkbook documento = (HSSFWorkbook) document;
        HSSFSheet hoja = documento.getSheetAt(0);
        // Crea fuente y estilos
        creaFuente(documento);
        creaEstiloCelda(documento);
        creaEstiloCeldaDerecha(documento);
        creaEstiloCeldaGris(documento);
        creaEstiloCeldaCentrada(documento);
        creaEstiloCeldaEncabezado(documento);
        // Agregar lineas en blanco
        hoja.shiftRows(0, hoja.getLastRowNum(), rowDefault);
        // Agrega titulo
        creaTitulo(hoja);
        // Agrega geografica
        creaGeografia(hoja);
        // Agrega fecha
        creaFecha(hoja);
        // Agreg estilo encabezado
        creaEstiloEncabezado(hoja);
        // Agrega logo
        creaLogo(documento, hoja);
    }

    /**
     * Método encargado de insertar el logo en el excel
     *
     * @param documento : Documento
     * @param hoja      : Hoja
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaLogo(HSSFWorkbook documento, HSSFSheet hoja) {
        try (InputStream inputStream = new FileInputStream(
                obtenRealPath(Utilidades.mensajeProperties("ruta_reportes_img_ine")))) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = documento.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            inputStream.close();
            CreationHelper helper = documento.getCreationHelper();
            Drawing drawing = hoja.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture picture = drawing.createPicture(anchor, pictureIdx);
            picture.resize();
        } catch (Exception e) {
            LOGGER.error("HLPExcel - creaLogo()", e);
        }
    }

    /**
     * Agrega titulo
     *
     * @param hoja : Hoja
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaTitulo(HSSFSheet hoja) {
        if (parametros.containsKey(Constantes.PARAMETRO_STRING_TITULO)) {
            String titulo = parametros.get(Constantes.PARAMETRO_STRING_TITULO).toString().toUpperCase();
            HSSFCell celda = hoja.getRow(0).createCell(0);
            celda.setCellValue(titulo);
            celda.setCellStyle(estiloCeldaCentrada);
            hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, num - 1));
        }
    }

    /**
     * Agrega fecha y da estilo a encabezado
     *
     * @param hoja : Hoja
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaFecha(HSSFSheet hoja) {
        HSSFCell celda = hoja.createRow(rowDefault - 1).createCell(num - 1);
        celda.setCellValue(ValidacionFechas.dateToString("dd/MMMM/yyyy HH:mm", new Date()).concat(" hrs."));
        celda.setCellStyle(estiloCeldaDerecha);
    }

    /**
     * Agrega estado y distrito
     *
     * @param hoja : Hoja
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaGeografia(HSSFSheet hoja) {
        // Row por default
        int row = 4;
        if (parametros.containsKey(Constantes.PARAMETRO_OBJECT_ESTADO)) {
            DTODetalleEstadoProcesoWS estado = (DTODetalleEstadoProcesoWS) parametros
                    .get(Constantes.PARAMETRO_OBJECT_ESTADO);
            // Etiqueta
            HSSFCell celda1 = hoja.createRow(row).createCell(0);
            celda1.setCellValue(Utilidades.mensajeProperties("etiqueta_generales_entidad_federativa").toUpperCase()
                    .concat(":"));
            celda1.setCellStyle(estiloCeldaGris);
            // Nombre estado
            HSSFCell celda2 = hoja.getRow(row).createCell(1);
            celda2.setCellValue(estado.getNombreEstado().toUpperCase());
            celda2.setCellStyle(estiloCeldaCentrada);
            // Combinar celdas
            hoja.addMergedRegion(new CellRangeAddress(row, row, 0, 0));
            hoja.addMergedRegion(new CellRangeAddress(row, row, 1, num - 1));
            row++;
        }
        if (parametros.containsKey(Constantes.PARAMETRO_OBJECT_MUNICIPIO)) {
            DTODetalleMunicipioProcesoWS municipio = (DTODetalleMunicipioProcesoWS) parametros
                    .get(Constantes.PARAMETRO_OBJECT_MUNICIPIO);
            // Etiqueta
            HSSFCell celda3 = hoja.createRow(row).createCell(0);
            celda3.setCellValue(Utilidades.mensajeProperties("etiqueta_sistema_municipio").toUpperCase().concat(":"));
            celda3.setCellStyle(estiloCeldaGris);
            // Id distrito
            HSSFCell celda2 = hoja.getRow(row).createCell(1);
            celda2.setCellValue(municipio.getIdMunicipio().toString());
            celda2.setCellStyle(estiloCeldaCentrada);
            // Combinar celdas
            hoja.addMergedRegion(new CellRangeAddress(row, row, 0, 0));
            // Validamos si el reporte tiene un rango entre 2 y 3 columnas
            if (num >= 2 && num < 4) {
                // Combinar celdas
                hoja.addMergedRegion(new CellRangeAddress(row, row, 1, num - 1));
                row++;
                // Etiqueta
                HSSFCell celda4 = hoja.getRow(row).createCell(0);
                celda4.setCellValue(Utilidades.mensajeProperties("etiqueta_generales_nombre").toUpperCase().concat(":"));
                celda4.setCellStyle(estiloCeldaGris);
                // Cabecera
                HSSFCell celda5 = hoja.getRow(row).createCell(1);
                celda5.setCellValue(municipio.getNombreMunicipio().toUpperCase());
                celda5.setCellStyle(estiloCeldaCentrada);
                // Combinar celdas
                hoja.addMergedRegion(new CellRangeAddress(row, row, 0, 0));
                hoja.addMergedRegion(new CellRangeAddress(row, row, 1, num - 1));
            } else {
                // Combinar celdas
                hoja.addMergedRegion(new CellRangeAddress(row, row, 1, 1));
                // Etiqueta
                HSSFCell celda4 = hoja.getRow(row).createCell(2);
                celda4.setCellValue(Utilidades.mensajeProperties("etiqueta_generales_nombre").toUpperCase().concat(":"));
                celda4.setCellStyle(estiloCeldaGris);
                // Cabecera
                HSSFCell celda5 = hoja.getRow(row).createCell(3);
                celda5.setCellValue(municipio.getNombreMunicipio().toUpperCase());
                celda5.setCellStyle(estiloCeldaCentrada);
                // Combinar celdas
                hoja.addMergedRegion(new CellRangeAddress(row, row, 2, 2));
                hoja.addMergedRegion(new CellRangeAddress(row, row, 3, num - 1));
            }
        }
    }

    /**
     * Agregs estilo encabezado tabla
     *
     * @param hoja : Hoja
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaEstiloEncabezado(HSSFSheet hoja) {
        for (int i = 0; i <= num - 1; i++) {
            hoja.autoSizeColumn(i);
            HSSFCell celda = hoja.getRow(rowDefault).getCell(i);
            celda.setCellStyle(estiloEncabezado);
        }
    }

    /**
     * Método encargado de crear la fuente
     *
     * @param documento : Documento
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaFuente(HSSFWorkbook documento) {
        fuente = documento.createFont();
        fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fuente.setFontHeightInPoints((short) 10);
    }

    /**
     * Método encargado de crear estilo celda
     *
     * @param documento : Documento
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaEstiloCelda(HSSFWorkbook documento) {
        estiloCelda = documento.createCellStyle();
        estiloCelda.setFont(fuente);
    }
    
    /**
     * Método encargado de crear estilo celda
     *
     * @param documento : Documento
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaEstiloCeldaDerecha(HSSFWorkbook documento) {
        estiloCeldaDerecha = documento.createCellStyle();
        estiloCeldaDerecha.setFont(fuente);
        estiloCeldaDerecha.setAlignment(CellStyle.ALIGN_RIGHT);
    }

    /**
     * Método encargado de crear estilo celda con fondo
     *
     * @param documento : Documento
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaEstiloCeldaGris(HSSFWorkbook documento) {
        estiloCeldaGris = documento.createCellStyle();
        estiloCeldaGris.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        estiloCeldaGris.setFillPattern(CellStyle.SOLID_FOREGROUND);
        estiloCeldaGris.setFont(fuente);
    }

    /**
     * Método encargado de crear estilo celda centrada
     *
     * @param documento : Documento
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaEstiloCeldaCentrada(HSSFWorkbook documento) {
        estiloCeldaCentrada = documento.createCellStyle();
        estiloCeldaCentrada.setAlignment(CellStyle.ALIGN_CENTER);
        estiloCeldaCentrada.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estiloCeldaCentrada.setFont(fuente);
    }

    /**
     * Método encargado de crear estilo de celda de encabezado
     *
     * @param documento : Documento
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    private void creaEstiloCeldaEncabezado(HSSFWorkbook documento) {
        estiloEncabezado = documento.createCellStyle();
        estiloEncabezado.setAlignment(CellStyle.ALIGN_CENTER);
        estiloEncabezado.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estiloEncabezado.setFont(fuente);
        estiloEncabezado.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        estiloEncabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }

    /**
     * Obtener real path de archivo
     *
     * @param archivo : Archivo
     *
     * @return String : path
     *
     * @author José Antonio López Torres
     * @since 27/03/2017
     */
    public String obtenRealPath(String archivo) {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(archivo);
    }
}
