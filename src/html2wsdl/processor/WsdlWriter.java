package html2wsdl.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import html2wsdl.parameters.wrapper.WrapParameters;
// import html2wsdl.reader.ExcelReader;
import html2wsdl.reader.HtmlReader;
import html2wsdl.vo.parameters.InputParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class WsdlWriter
{	
  public static void main(String[] args)
  {
	  File inputHtml = new File("/asaidel/txt/falabella/Paytrue/tarjetaConfiguracionCambiar/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
	
	  InputParameters expInParameters = new InputParameters("Tarjeta", "Configuracion", "Cambiar", "v1.0", "FIF", 
		      "CORP", "OSB", "v2016.04", null);
	  
	  HtmlReader reader = new HtmlReader();
	  OutputParameters expOutParameters = WrapParameters.expWrap(expInParameters);
	  reader.processExp(inputHtml, expOutParameters);
	  	  
	  // 1. Configure FreeMarker
	    //
	    // You should do this ONLY ONCE, when your application starts,
	    // then reuse the same Configuration object elsewhere.
	    
	    Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        try {
			cfg.setDirectoryForTemplateLoading(new File("resources"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    
	    // 2.2. Get the template
	    Writer fileWriter = null;	    
	    try {
	    Template template = cfg.getTemplate("wsdl.ftl");
	      
	    // 2.3. Generate the output

	    // Write output to the console
	   // Writer consoleWriter = new OutputStreamWriter(System.out);
	    //template.process(expOutParameters, consoleWriter);

	    // For the sake of example, also write output into a file:
	    fileWriter= new FileWriter(new File("output/"+expOutParameters.getWsdlFile()));
	    
	    //List<Item> list;

	      template.process(expOutParameters, fileWriter);
	    } catch(Exception e)
	    {
		      try {
				fileWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    finally {
	      try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }
}
