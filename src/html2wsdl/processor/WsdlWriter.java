package html2wsdl.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import html2wsdl.parameters.wrapper.WrapParameters;
// import html2wsdl.reader.ExcelReader;
import html2wsdl.reader.HtmlReader;
import html2wsdl.vo.parameters.InputParameters;
import html2wsdl.vo.parameters.OffsetParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class WsdlWriter extends html2wsdl.processor.Writer
{	
  public static void main(String[] args)
  {
	File inputHtml = new File(
			"/asaidel/txt/falabella/Paytrue/tarjetaConfiguracionCambiar/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");

	InputParameters parametersIn = new InputParameters("Tarjeta", "Configuracion", "Cambiar", "v1.0", "FIF",
			"CORP", "OSB", "v2016.04", null);

	OutputParameters expOutParameters = WrapParameters.expWrap(parametersIn);
	OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4);
	
	HtmlReader reader = new HtmlReader();
	reader.process(expOutParameters, offsetParameters, inputHtml);

	// 1. Configure FreeMarker
	//
	// You should do this ONLY ONCE, when your application starts,
	// then reuse the same Configuration object elsewhere.

	try {
		writeXml(expOutParameters, "wsdl.ftl", expOutParameters.getWsdlFile());
	
		writeXml(expOutParameters, "request.ftl", "Schemas/"+expOutParameters.getRequest().getXsdFile());
		
		writeXml(expOutParameters, "response.ftl", "Schemas/"+expOutParameters.getResponse().getXsdFile());	
	} catch (IOException | TemplateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }

	private static void writeXml(OutputParameters expOutParameters, String ftl, String file) throws IOException, TemplateException 
	{
		Writer fileWriter = new FileWriter(new File("output/" + file));		
		Template template = configure(ftl);
		template.process(expOutParameters, fileWriter);		
	}
  }