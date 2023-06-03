package com.sigecap.sigecapexamenbackend.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class Print {

	public  byte[] imprimirBoleta (InputStream archivoJasper, HttpServletResponse response, Map<String, Object> parametros,
			List<?> listaImprimir,String tipoReporte,String fileName,boolean tieneData,String springDatasourceUrl) throws IOException, JRException {
		
		int numeroVisitas = 0;
		String jsPDF = "var pp = this.getPrintParams();" + "pp.interactive = pp.constants.interactionLevel.silent;";
		
		if(tieneData) {
			numeroVisitas = listaImprimir.size();
			for (int i = 0; i < numeroVisitas; i++) {
				jsPDF = jsPDF + "pp.firstPage = " + i + ";" + "pp.lastPage = " + i + ";" + "this.print(pp);";
			}
		}else {
			jsPDF = jsPDF + "pp.firstPage = " + 0 + ";" + "pp.lastPage = " + 0 + ";" + "this.print(pp);";
		}
		
		return imprimirPDFWeb(archivoJasper, response, fileName, parametros, listaImprimir, jsPDF,tipoReporte,tieneData,springDatasourceUrl);
	}

	
	
	public  byte[] imprimirPDFWeb (InputStream archivoJasper, HttpServletResponse response, String fileName,
			Map<String, Object> parametros, List<?> listaImprimir, String jsPDF,String tipoReporte,boolean tieneData,String springDatasourceUrl) throws IOException, JRException {
		
		if(tipoReporte.equals("1")){
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
		}

		return imprimirPDF (archivoJasper, response.getOutputStream(), parametros, listaImprimir, jsPDF,tipoReporte,tieneData,springDatasourceUrl);
	}

	public static DataSource getDataSource(String springDatasourceUrlProperties) {
    	DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    	dataSourceBuilder.url(springDatasourceUrlProperties);
        dataSourceBuilder.username("sigecap_user");
        dataSourceBuilder.password("S1g3c4p2021$");
        return dataSourceBuilder.build();
    }
	
	public  byte[] imprimirPDF (InputStream archivoJasper, OutputStream outputStream, Map<String, Object> parametros,
			List<?> listaImprimir, String jsPDF,String tipoReporte,boolean tieneData,String springDatasourceUrl) throws JRException {
		try{
			JRBeanCollectionDataSource dataSource = null;
			JasperPrint print = null;
			
			if(tieneData) {
				dataSource = new JRBeanCollectionDataSource(listaImprimir);
				print = JasperFillManager.fillReport(archivoJasper, parametros, dataSource);
			}else {
				if(springDatasourceUrl == null  || springDatasourceUrl.equals("")) {
					print = JasperFillManager.fillReport(archivoJasper, parametros, new JREmptyDataSource());
				}else {
					print = JasperFillManager.fillReport(archivoJasper, parametros, DataSourceUtils.getConnection(getDataSource(springDatasourceUrl)));
				}
			}

				return JasperExportManager.exportReportToPdf(print);
				/*
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput (print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				exporter.setConfiguration(configuration);
				/*if (jsPDF != null) {
					exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, jsPDF);
				}
				exporter.exportReport();

*
			*/
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}

	}
	
	
	public  void imprimirBoleta (InputStream archivoJasper, HttpServletResponse response, Map<String, Object> parametros, 
			List<?> listaImprimir,String tipoReporte,String fileName,boolean tieneData,Connection connection) throws IOException, JRException {
		
		int numeroVisitas = 0;
		String jsPDF = "var pp = this.getPrintParams();" + "pp.interactive = pp.constants.interactionLevel.silent;";
		
		if(tieneData) {
			numeroVisitas = listaImprimir.size();
			for (int i = 0; i < numeroVisitas; i++) {
				jsPDF = jsPDF + "pp.firstPage = " + i + ";" + "pp.lastPage = " + i + ";" + "this.print(pp);";
			}
		}else {
			jsPDF = jsPDF + "pp.firstPage = " + 0 + ";" + "pp.lastPage = " + 0 + ";" + "this.print(pp);";
		}
		
		imprimirPDFWeb(archivoJasper, response, fileName, parametros, listaImprimir, jsPDF,tipoReporte,tieneData,connection);
	}
	
	public  void imprimirPDFWeb (InputStream archivoJasper, HttpServletResponse response, String fileName, 
			Map<String, Object> parametros, List<?> listaImprimir, String jsPDF,String tipoReporte,boolean tieneData,Connection connection) throws IOException, JRException {
		
		if(tipoReporte.equals("1")){
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
		}else if(tipoReporte.equals("2")){
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		}else if(tipoReporte.equals("3")){
			response.setContentType("application/text/csv");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".csv");
		}
		
		
		
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		

		
		imprimirPDF (archivoJasper, response.getOutputStream(), parametros, listaImprimir, jsPDF,tipoReporte,tieneData,connection);
	}


	
	public  void imprimirPDF (InputStream archivoJasper, OutputStream outputStream, Map<String, Object> parametros, 
			List<?> listaImprimir, String jsPDF,String tipoReporte,boolean tieneData,Connection connection) throws JRException {
		try{
			JRBeanCollectionDataSource dataSource = null;
			JasperPrint print = null;
			
			if(tieneData) {
				dataSource = new JRBeanCollectionDataSource(listaImprimir);
				print = JasperFillManager.fillReport(archivoJasper, parametros, dataSource);
			}else {
				
				print = JasperFillManager.fillReport(archivoJasper, parametros, connection);
				
				
			}
			
			
			if(tipoReporte.equals("1")){
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput (print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				exporter.setConfiguration(configuration);
				/*if (jsPDF != null) {
					exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, jsPDF);
				}*/
				exporter.exportReport();
			}else if(tipoReporte.equals("2")){
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setUseTimeZone(true);
				//we set the one page per sheet parameter here
				configuration.setOnePagePerSheet(false);
				
				exporter.setConfiguration(configuration);
				
				exporter.exportReport();
				
			}else if(tipoReporte.equals("3")){
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				
				exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
				SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
				configuration.setFieldDelimiter(";");
			    configuration.setWriteBOM(Boolean.TRUE);
			    
			    exporter.setConfiguration(configuration);
				exporter.exportReport();
			}
			connection.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

	}
}
