package html2wsdl.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import html2wsdl.vo.ExcelObject;
import html2wsdl.vo.SimpleBean;
import html2wsdl.util.StringUtil;

public class ExcelReader {		
	
	static Logger logger = Logger.getLogger(ExcelReader.class);
	
	public ExcelObject readFile(InputStream stream)
	{		
		Workbook wb = null;
		List<SimpleBean> errores = new ArrayList<SimpleBean>();
		//SolicitudError solError; = new SolicitudError();
		
		try
		{
			wb = WorkbookFactory.create(stream);
		}
		catch (InvalidFormatException e)
		{
			logger.error("Formato de archivo inválido",e);
			errores.add(new SimpleBean("archivo", "Formato de archivo inválido"));
		}
		catch (FileNotFoundException e)
		{	
			logger.error("Archivo no encontrado",e);
			errores.add(new SimpleBean("archivo","Archivo no encontrado"));
			
		}
		catch (IOException e)
		{
			logger.error("Error de lectura de archivo",e);
			errores.add(new SimpleBean("archivo","Error de lectura de archivo"));			
		}
		
		Sheet sheet = wb.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();
		formatter.setDefaultNumberFormat( NumberFormat.getInstance(Locale.getDefault()));
				
		int rowSize = sheet.getLastRowNum();
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		List< List<String> > registros = new ArrayList<List<String>>(rowSize);		
		String datePattern = "yyyy-MM-dd";
		
		for (int r = 1; r <= rowSize; r++) // 0 es header
		{
			Row row = sheet.getRow(r);
						
			if (row == null) 
			{	
				logger.error("Linea "+r+" es nula");				
				//registros.add(null);
				continue;
			}
			
			int colSize = row.getLastCellNum();
			List<String> rowList = new ArrayList<String>(colSize);			
			
			for (int c = 0; c < colSize; c++) 
			{
				Cell cell = row.getCell(c);
				
				if(cell != null)
				{
					String auxCell = null;
					
					switch(cell.getCellType()) 
					{						
						case Cell.CELL_TYPE_FORMULA:
							auxCell = formatter.formatCellValue(cell, evaluator);
							rowList.add(auxCell);
							break;
							
						case Cell.CELL_TYPE_NUMERIC:
					        if(DateUtil.isCellDateFormatted(cell)) 
					        {					            
					            String dateString = provider.util.DateUtil.getDateAsString(cell.getDateCellValue(), datePattern);
								auxCell=dateString.trim();				            
					        } 
					        else 					        	
					        	auxCell=formatter.formatCellValue(cell);
					        
					        rowList.add(auxCell);
					        break;	
					        
					    default: // formato "general"
					    	String var;
							String stringCellValue = cell.getStringCellValue();
							
						/*	if (provider.util.DateUtil.isDate(stringCellValue, datePattern))
								var = provider.util.DateUtil.getDate(stringCellValue, datePattern).toString();							
							else 
						 */    	
							
							var = stringCellValue;   	
							auxCell = var.trim();
		                	rowList.add(auxCell); 
					}
					// logger.info("columna: "+c+" valor:"+auxCell.getValue());
				}
				else 
					rowList.add("");
			}
			
			registros.add(rowList);
		}
		
		ExcelObject excelObj = new ExcelObject();
//		excelObj.setNombreArchivo(stream.);
		excelObj.setRegistros(registros);
		
	//	excelObj.setErrores(errores);
		
		return excelObj;
	}

	public static void main(String[] args)
	{		
		System.out.println(StringUtil.abcIndex("A")); //0
		System.out.println(StringUtil.abcIndex("Z"));// 25
		System.out.println(StringUtil.abcIndex("AJ")); // 35
		System.out.println(StringUtil.abcIndex("AZ")); // 51
		System.out.println(StringUtil.abcIndex("AB")); // 27
		System.out.println(StringUtil.abcIndex("BA")); // 52	
		
		String[] codigoProductos = "".split(",");
		
		for (int i = 0; i < codigoProductos.length; i++)
			codigoProductos[i]=codigoProductos[i].trim();			
		
		System.out.println(codigoProductos[0]+" "+codigoProductos[1]);
		
		String path = "h:/txt/job/pedro/";
		
		// an ApplicationContext is also a BeanFactory (via inheritance)
		//BeanFactory factory = context;
		
		ExcelObject solObj = null;
		try {
			solObj = new ExcelReader().readFile(
					//"c:/temp/planilla carga consumo_01.xls");
					//"c:/temp/03122009_NVOC.xls");
				new FileInputStream(path+"paris1.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				//"c:/temp/formato carga masiva transporte_2.xls");
	
		final long t1 = System.currentTimeMillis();
		final long t3 = System.nanoTime();
	      
		/*soLoader.setSolObj(solObj);
		soLoader.cargarSolicitudes();
		*/
		
		final long t2 = System.currentTimeMillis();
		final long t5 = System.nanoTime();
                
	/*	for (SimpleBean error : solObj.getErrores())
		{
			System.out.println(error.getName()+" "+error.getValue());
		}	
	*/	
		 System.out.println( "\n n:" +
	        		solObj.getRegistros().size() +
	                " time: " +
	                ( t2 - t1 ));
		 
		 System.out.println( "\n nano time n:" +
	        		solObj.getRegistros().size() +
	                " time: " +
	                ( t5 - t3 ));
	}		
}
