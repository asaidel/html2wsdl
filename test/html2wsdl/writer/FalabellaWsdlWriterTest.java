package html2wsdl.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import html2wsdl.parameters.wrapper.WrapParameters;
import html2wsdl.reader.HtmlReader;
import html2wsdl.vo.parameters.InputParameters;
import html2wsdl.vo.parameters.OffsetParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class FalabellaWsdlWriterTest extends FreemarkerWriterTest 
{	
	@Before
    public void setUp() throws IOException {
		super.setUp();		
    }

	@Test
	public void expTest() throws Exception
	{
		File inputHtml = new File(
				"resources/spec/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
	
		InputParameters parametersIn = new InputParameters("Tarjeta", "Configuracion", "Cambiar", "v1.0", "FIF",
				"CORP", "OSB", "v2016.04", null);
	
		OutputParameters expOutParameters = WrapParameters.expWrap(parametersIn);
		OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4, 3);		
		this.writeFiles(offsetParameters, expOutParameters, inputHtml);
	}

	@Test
	public void compTest() throws Exception
	{
		File inputHtml = new File(
				"resources/spec/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
		
		InputParameters parametersIn = new InputParameters("Tarjeta", "Configuracion", "Cambiar", "v1.0", "FIF",
				"CORP", "OSB", "v2016.04", null);		
		
		OutputParameters compOutParameters = WrapParameters.compWrap(parametersIn);
		OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4, 3);
		this.writeFiles(offsetParameters, compOutParameters, inputHtml);	
	}
	
	@Test
	public void implTest() throws Exception
	{
		File inputHtml = new File(
				"resources/spec/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");

		InputParameters parametersIn = new InputParameters("Tarjeta", "RiskRules", "setCardConfig", "v1.0", "CORP",
				"CORP", "OSB", "v2016.04", "Paytrue");
		
		OutputParameters implOutParameters = WrapParameters.implWrap(parametersIn);
		OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4, 3);
		this.writeFiles(offsetParameters, implOutParameters, inputHtml);	
	}

	private void writeFiles(OffsetParameters offsetParameters, OutputParameters outParameters, File inputHtml) 
	{		
		
		try {
			this.writeXml(outParameters, "wsdl.ftl", outParameters.getWsdlFile());
		
			new HtmlReader().process(offsetParameters, outParameters, inputHtml);
			
			this.writeXml(outParameters, "request.ftl", "Schemas/"+outParameters.getRequest().getXsdFile());
			
			this.writeXml(outParameters, "response.ftl", "Schemas/"+outParameters.getResponse().getXsdFile());	
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeXml(OutputParameters outParameters, String ftl, String file) throws IOException, TemplateException 
	{
		Writer fileWriter = new FileWriter(new File("output/" + file));	
		Template template = super.getCfg().getTemplate(ftl);
		template.process(outParameters, fileWriter);		
	}
}
