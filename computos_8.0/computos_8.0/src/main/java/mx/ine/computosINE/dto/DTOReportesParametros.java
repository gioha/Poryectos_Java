/**
 * @(#)DTOReportesParametros.java 07/02/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */

package mx.ine.computosINE.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import mx.ine.computosINE.dto.helper.HLPReportesEncabezado;

public class DTOReportesParametros implements Serializable {

	private static final long serialVersionUID = -2699321079617680125L;

	private static final Log LOGGER = LogFactory.getLog(DTOReportesParametros.class);	
	private static final Integer DIVISION_COLUMNAS_ENCABEZADO_TRES = 3;
	private static final Integer DIVISION_COLUMNAS_ENCABEZADO_DOS = 2;
	private static final String RUTA_IMAGEN_COMPUTOS = "resources/image/computos.png";

	// Parametros para crear el encabezado del reporte	
	private String tituloReporte;
	// Para controlar colspan en vista
	private Integer columnas;
	// Se guardan metadatos del header de cada columna
	private List<HLPReportesEncabezado> encabezado;
	
	private String descEntidad;
	private Integer anchoDescEntidad;
	private Integer idMunicipioElectoral;
	private Integer anchoIdMunicipioElectoral;	
	private String descMunicipioElectoral;
	private Integer anchoDescMunicipioElectoral;
	private String fechaHora;	
	private Integer anchoFechaHora;
	private boolean esReporteEstatusCasilla;
	
	
	public DTOReportesParametros() {
		this.encabezado = new ArrayList<HLPReportesEncabezado>();
	}

	/**
	 * Se definen el tamaño de las celdas para el titulo del reporte
	 */
	public void obtenMedidasEncabezado() {
		LOGGER.info("  MUNICIPIO " + getDescMunicipioElectoral());

		// Para cuando es un reporte a nivel casillas
		if (getDescEntidad() != null && !getDescEntidad().isEmpty()
				&& getDescMunicipioElectoral() != null
				&& !getDescMunicipioElectoral().isEmpty()) {
			Integer anchoColumna = getColumnas()
					/ DIVISION_COLUMNAS_ENCABEZADO_TRES;
			setAnchoDescEntidad(anchoColumna);
			setAnchoIdMunicipioElectoral(anchoColumna);
			setAnchoDescMunicipioElectoral(getColumnas() - anchoColumna * 2);
			setAnchoFechaHora(getColumnas());

		}		
		// Para cuando el reporte es a nivel distrito
		else if (!getDescEntidad().isEmpty()) {
			LOGGER.info(" ENTRA EN LA DIVISIÓN  ");
			Integer anchoColumna = getColumnas()
					/ DIVISION_COLUMNAS_ENCABEZADO_DOS;
			setAnchoDescEntidad(anchoColumna);
			setAnchoFechaHora(getColumnas() - anchoColumna);
			LOGGER.info("  ANCHO :" + anchoColumna);
			LOGGER.info(" ANCHO ENTIDAD : " + getAnchoDescEntidad());
			LOGGER.info(" ANCHO FECHA :" + getAnchoFechaHora());
		}
		

	}

	/**
     * Método que se encarga de crear el encabezado del Excel
     */
    public HSSFWorkbook crearEncabezadoXLS(HSSFWorkbook libro){
		HSSFWorkbook wb = libro;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFSheet hoja = wb.createSheet();
		
		/*..................................................imagen....................................................*/
		try{
			String rutaimg = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
			File file = new File(rutaimg+RUTA_IMAGEN_COMPUTOS);
	        if (file.exists()) {
		        InputStream my_banner_image = new FileInputStream(file);
		        byte[] bytes = IOUtils.toByteArray(my_banner_image);
		        int my_picture_id = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		        my_banner_image.close();
		        CreationHelper helper = wb.getCreationHelper();
		        Drawing drawing = hoja.createDrawingPatriarch();
		        ClientAnchor anchor = helper.createClientAnchor();
		        anchor.setAnchorType(3);
		        anchor.setCol1(0);
		        anchor.setRow1(1);
		        Picture pict = drawing.createPicture(anchor, my_picture_id);
		        pict.resize(2.0,1.0);
	        }
		}catch(Exception e){
			LOGGER.error("Al incluir imagen en el xls."+e);
		}
		
		//agregamos el encabezado
		HSSFFont fuenteEncabezadoCampos = wb.createFont();
		fuenteEncabezadoCampos.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		/*............................................Estilos de las celdas...........................................*/
		// Encabezado principal
		HSSFCellStyle estiloEncabezado = wb.createCellStyle();
		estiloEncabezado.setFont(fuenteEncabezadoCampos);
		estiloEncabezado.setWrapText(true);
		estiloEncabezado.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		//titulo
		HSSFCellStyle estiloTitulo = wb.createCellStyle();
		estiloTitulo.setFont(fuenteEncabezadoCampos);
		estiloTitulo.setFillForegroundColor(HSSFColor.WHITE.index);
		estiloTitulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloTitulo.setWrapText(true);
		estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		estiloTitulo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//Informacion reporte
		HSSFCellStyle estiloInfo = wb.createCellStyle();
		estiloInfo.setFont(fuenteEncabezadoCampos);
		estiloInfo.setFillForegroundColor(HSSFColor.WHITE.index);
		estiloInfo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloInfo.setWrapText(true);
		estiloInfo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// Encabezado campos
		HSSFCellStyle estiloEncabezadoCampos = wb.createCellStyle();
		estiloEncabezadoCampos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloEncabezadoCampos.setWrapText(true);
		estiloEncabezadoCampos.setFont(fuenteEncabezadoCampos);
		estiloEncabezadoCampos.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		estiloEncabezadoCampos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloEncabezadoCampos.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// Totales Votos
		HSSFCellStyle estiloTotales = wb.createCellStyle();
		estiloTotales.setFont(fuenteEncabezadoCampos);		
		estiloTotales.setWrapText(true);
		// Primera Columna
		HSSFCellStyle estiloPrimerColumna = wb.createCellStyle();
		estiloPrimerColumna.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloPrimerColumna.setWrapText(true);
		
		/*..............................................Numero de columnas............................................*/
		Integer numColumnas=getColumnas()-1;
		/*........................................Iniciamos en la fila 1 columna A....................................*/
		Integer numRow=0;
		
		/*..............Colocalmos informacion basica del reporte(Titulo, nombre entidad y/o distritos, fecha)........*/
		// en la primera fila colocamos el titulo
		HSSFRow titulo = hoja.createRow(numRow);
		titulo.setHeight((short) 350); // alto de fila
		HSSFCell celdaTitulo = titulo.createCell(0);
		celdaTitulo.setCellValue(getTituloReporte());
		CellRangeAddress rangeTitulo=new CellRangeAddress(0, 0, 0, numColumnas);
		hoja.addMergedRegion(rangeTitulo);
		celdaTitulo.setCellStyle(estiloTitulo);
		// Imagen
		numRow++;
		HSSFRow imagen = hoja.createRow(numRow);
		imagen.setHeight((short) 850); // alto de fila
		HSSFCell celdaImagen = imagen.createCell(0);
		CellRangeAddress rangeImagen=new CellRangeAddress(numRow, numRow, 0, numColumnas);
		hoja.addMergedRegion(rangeImagen);
		celdaImagen.setCellStyle(estiloInfo);
		// Entidad
		numRow++;
		HSSFRow infoFiltro = hoja.createRow(numRow);
		infoFiltro.setHeight((short) 350); // alto de fila
		HSSFCell celdaEntidad = infoFiltro.createCell(0);
		celdaEntidad.setCellValue("Entidad federativa: "+getDescEntidad());
		CellRangeAddress rangeEntidad=new CellRangeAddress(numRow, numRow, 0, numColumnas);
		hoja.addMergedRegion(rangeEntidad);
		celdaEntidad.setCellStyle(estiloInfo);
		
		
		// Cabecera Municipal
		if (getIdMunicipioElectoral() != null) {
			numRow++;
			HSSFRow infoFiltroReporte = hoja.createRow(numRow);
			infoFiltroReporte.setHeight((short) 300); // alto de fila
			HSSFCell celdaCabecera = infoFiltroReporte.createCell(0);
			celdaCabecera.setCellValue("Municipio electoral: " + getIdMunicipioElectoral());
			CellRangeAddress rangeCabecera = new CellRangeAddress(numRow, numRow, 0, numColumnas);
			hoja.addMergedRegion(rangeCabecera);
			celdaCabecera.setCellStyle(estiloInfo);
		}
		
		// Cabecera Municipal
		if (getDescMunicipioElectoral() != null) {
			numRow++;
			HSSFRow infoFiltroReporte = hoja.createRow(numRow);
			infoFiltroReporte.setHeight((short) 300); // alto de fila
			HSSFCell celdaCabecera = infoFiltroReporte.createCell(0);
			celdaCabecera.setCellValue("Cabecera: " + getDescMunicipioElectoral());
			CellRangeAddress rangeCabecera = new CellRangeAddress(numRow, numRow, 0, numColumnas);
			hoja.addMergedRegion(rangeCabecera);
			celdaCabecera.setCellStyle(estiloInfo);
		}

		// Fecha
		numRow++;
		HSSFRow infoFecha = hoja.createRow(numRow);
		infoFecha.setHeight((short) 300); // alto de fila	
		HSSFCell fecha = infoFecha.createCell(0);
		fecha.setCellValue("Fecha y hora de impresión: " + getFechaHora() + " hrs.");
		CellRangeAddress rangefecha=new CellRangeAddress(numRow, numRow, 0,numColumnas);
		hoja.addMergedRegion(rangefecha);
		fecha.setCellStyle(estiloInfo);
		
		Integer numIni = numRow;
		/*.................................Pintamos los encabezados de la tabla antes del contenido...................*/
		numRow++;
		HSSFRow encabezadoTabla = hoja.createRow(numRow);
		encabezadoTabla.setHeight((short) 750);		
		List<Integer> primerasColumnas = obtenerCeldaDePartida();// Se obtienen los numeros de cada columna donde se empieza a dibujar la celda
		numRow = pintarEncabezado(hoja, encabezadoTabla, numRow, primerasColumnas,
	    		estiloEncabezado, estiloEncabezadoCampos, 1);
		bordesCeldas(wb, hoja, numIni+1, numRow);//pintamos los bordes de las celdas de los encabezados antes del contenido
		
		/*..................colocamos el contenido de la hoja en una nueva para recorrer las filas....................*/
	    Iterator<Row> rowIterator = sheet.iterator();
	    Row row;
	    Integer fila = numRow;
	    Integer contador = 0; // empezamos desde la segunda fila
	    while (rowIterator.hasNext()) {
	    	row = rowIterator.next();
	    	if(contador > 0){
		    	HSSFRow hssfRow = hoja.createRow(fila);
		    	hssfRow.setHeight((short) 500);
	            // Obtenemos el iterator que permite recorres todas las celdas de una fila
	            Iterator<Cell> cellIterator = row.cellIterator();
	            Cell celda;
	            Integer columna = 0;
	            while (cellIterator.hasNext()) {
	            	celda = cellIterator.next();
	            	boolean esFilaTotales = !rowIterator.hasNext();
	            	boolean esPrimeraColumna = columna==0;
	            	if(celda.getStringCellValue() != null){
		            	HSSFCell hssfCell = hssfRow.createCell(columna);
		            	if(esFilaTotales && !esReporteEstatusCasilla){
		            		hssfCell.setCellStyle(estiloTotales);
		            	}else if(esPrimeraColumna){
		            		hssfCell.setCellStyle(estiloPrimerColumna);
		            	}
		            	hssfCell.setCellValue(celda.getStringCellValue());
		        		CellRangeAddress range=new CellRangeAddress(fila,fila,columna,columna);
		        		hoja.addMergedRegion(range);
		        		hoja.setColumnWidth(columna, 5400);
		        		columna++;
	            	}
	            }
	            fila++;
	    	}
            contador++;
	    }
		/*.................................Pintamos los encabezados de la tabla despues del contenido.................*/
		Integer numFilas=fila;
		Integer numFilasAux=fila;
		numRow = pintarEncabezado(hoja, encabezadoTabla, fila, primerasColumnas,
	    		estiloEncabezado, estiloEncabezadoCampos, 2);
		for(HLPReportesEncabezado hlp: getEncabezado()){
			if(hlp.getTipoFila().get(0) > 1){
				numFilas++;
			}
		}
		bordesCeldas(wb, hoja, numFilasAux, numFilas);//pintamos los bordes de las celdas de los encabezados despues del contenido
		
		/*.........................................Removemos la hoja que iteramos.....................................*/
	    wb.removeSheetAt(0);
		return wb;
	}
    
    /**
     * Método encargado de pintar el encabezado antes y depues
     * @param  hoja						: hoja(HSSFSheet) de la tabla donde se esta trabajando
     * 		   encabezadoTabla			: fila(HSSFRow) de la tabla donde se esta trabajando
     * 		   numRow					: fila en la que estamos colocados
     *  	   primerasColumnas			: Son la columna principal de cada celda de los encabezados donde se empezara a pintar
     *   	   estiloEncabezado			: estilo 1
     *    	   estiloEncabezadoCampos 	: estilo 2
     *    	   tipoEncabezado			: 1 es para los encabezados antes del contenido y 2 para despues del contenido donde van totales
     * @return Integer
     */
    private Integer pintarEncabezado(HSSFSheet hoja, HSSFRow encabezadoTabla, Integer numRow, List<Integer> primerasColumnas,
    		HSSFCellStyle estiloEncabezado, HSSFCellStyle estiloEncabezadoCampos, Integer tipoEncabezado){
    	Integer row_aux=0;
		Integer primerColumna=0;
		for(HLPReportesEncabezado hlp: getEncabezado()){
			boolean aumenta = true;
			if(row_aux != 0){
				encabezadoTabla = hoja.createRow(numRow);   
				encabezadoTabla.setHeight((short) 500); 	    		
	    	}
            for(Integer index: hlp.getIndex()){
            	if(hlp.getTipoFila().get(index) != 3 && tipoEncabezado == 1){
	            	HSSFCell celdaEncabezadoTabla = encabezadoTabla.createCell(primerasColumnas.get(primerColumna));
	    			celdaEncabezadoTabla.setCellStyle(estiloEncabezadoCampos);
	    			CellRangeAddress cellRangeAddress = new CellRangeAddress(
	    					numRow, numRow+(hlp.getAlto().get(index)-1), 
	    					primerasColumnas.get(primerColumna), 
	    					primerasColumnas.get(primerColumna) + (hlp.getAncho().get(index)-1));
	    			// agregar border a un  MergedRegion
	    			hoja.addMergedRegion(cellRangeAddress);
	    			celdaEncabezadoTabla.setCellValue(hlp.getDescEncabezado().get(index));
            	}else if(hlp.getTipoFila().get(index) > 1 && tipoEncabezado == 2){
            		HSSFCell celdaEncabezadoTabla = encabezadoTabla.createCell(primerasColumnas.get(primerColumna));
	            	if(hlp.getTipoFila().get(index) == 2){
	            		celdaEncabezadoTabla.setCellStyle(estiloEncabezadoCampos);
	            	}else{
	            		celdaEncabezadoTabla.setCellStyle(estiloEncabezado);
	            	}
	    			CellRangeAddress cellRangeAddress = new CellRangeAddress(
	    					numRow, numRow+(hlp.getAlto().get(index)-1), 
	    					primerasColumnas.get(primerColumna), 
	    					primerasColumnas.get(primerColumna) + (hlp.getAncho().get(index)-1));
	    			// agregar border a un  MergedRegion
	    			hoja.addMergedRegion(cellRangeAddress);
	    			celdaEncabezadoTabla.setCellValue(hlp.getDescEncabezado().get(index));
            	}else{
            		aumenta = false;
            	}
    			primerColumna++;
            }
            row_aux++;
            if(aumenta){
            	numRow++;
            }
        }
		return numRow;
    }
    
    /**
     * Método encargado de pintar bordes a la celda
     * @return List<Integer>
     */
    private void bordesCeldas(HSSFWorkbook wb, HSSFSheet hoja, Integer incio, Integer fin){
    	for(int encabezado = incio; encabezado < fin; encabezado++){
			for(int x = 0; x < getColumnas(); x++){
				CellRangeAddress cellRangeAddress = new CellRangeAddress(encabezado,encabezado,x,x);
				HSSFRegionUtil.setBorderTop(1, cellRangeAddress, hoja, wb);
				HSSFRegionUtil.setBorderLeft(1, cellRangeAddress, hoja, wb);
				HSSFRegionUtil.setBorderRight(1, cellRangeAddress, hoja, wb);
				HSSFRegionUtil.setBorderBottom(1, cellRangeAddress, hoja, wb);
            }
		}
    }
    
    /**
     * Método encargado de obtener la columna de la fila donde se empezara a dibujar la celda del excel
     * @return List<Integer>
     */
    private List<Integer> obtenerCeldaDePartida(){
    	List<Integer> primerasColumnas = new ArrayList<Integer>();
		List<Integer> cordenadaY = new ArrayList<Integer>();
		List<Integer> cordenadaX = new ArrayList<Integer>();
		Integer y = 0;
		for(HLPReportesEncabezado hlp: getEncabezado()){
			int index = 0;
			for(int x = 0; x < getColumnas(); x++){
				int contador=0;
				for(int cordenadas = 0;cordenadas < cordenadaY.size();cordenadas++){
					if(cordenadaY.get(cordenadas) == y && cordenadaX.get(cordenadas) == x){
						contador++;
					}
				}
				if(contador == 0){
					primerasColumnas.add(x);
					for(int ancho = 0;ancho<hlp.getAncho().get(index);ancho++){
						if(ancho > 0){
							cordenadaY.add(y);
							cordenadaX.add(x+ancho);
						}
					}
					for(int alto = 0;alto<hlp.getAlto().get(index);alto++){
						if(alto > 0){
							cordenadaY.add(y+alto);
							cordenadaX.add(x);
						}
					}
					index++;
				}
            }
			y++;
		}
    	return primerasColumnas;
    }
    
	public String getDescEntidad() {
		return descEntidad;
	}

	public void setDescEntidad(String descEntidad) {
		this.descEntidad = descEntidad;
	}

	public Integer getIdMunicipioElectoral() {
		return idMunicipioElectoral;
	}

	public void setIdMunicipioElectoral(Integer idMunicipioElectoral) {
		this.idMunicipioElectoral = idMunicipioElectoral;
	}

	public String getDescMunicipioElectoral() {
		return descMunicipioElectoral;
	}

	public void setDescMunicipioElectoral(String descMunicipioElectoral) {
		this.descMunicipioElectoral = descMunicipioElectoral;
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

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getAnchoFechaHora() {
		return anchoFechaHora;
	}

	public void setAnchoFechaHora(Integer anchoFechaHora) {
		this.anchoFechaHora = anchoFechaHora;
	}

	public Integer getAnchoDescEntidad() {
		return anchoDescEntidad;
	}

	public void setAnchoDescEntidad(Integer anchoDescEntidad) {
		this.anchoDescEntidad = anchoDescEntidad;
	}

	public Integer getAnchoIdMunicipioElectoral() {
		return anchoIdMunicipioElectoral;
	}

	public void setAnchoIdMunicipioElectoral(Integer anchoIdMunicipioElectoral) {
		this.anchoIdMunicipioElectoral = anchoIdMunicipioElectoral;
	}

	public Integer getAnchoDescMunicipioElectoral() {
		return anchoDescMunicipioElectoral;
	}

	public void setAnchoDescMunicipioElectoral(
			Integer anchoDescMunicipioElectoral) {
		this.anchoDescMunicipioElectoral = anchoDescMunicipioElectoral;
	}

	public List<HLPReportesEncabezado> getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(List<HLPReportesEncabezado> encabezado) {
		this.encabezado = encabezado;
	}
	
	public void setEsReporteEstatusCasilla(boolean esReporteEstatusCasilla) {
		this.esReporteEstatusCasilla = esReporteEstatusCasilla;
	}

}
