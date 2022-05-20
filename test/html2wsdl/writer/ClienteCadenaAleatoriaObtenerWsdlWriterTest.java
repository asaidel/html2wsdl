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

public class ClienteCadenaAleatoriaObtenerWsdlWriterTest extends FreemarkerWriterTest 
{	
	@Before
    public void setUp() throws IOException {
		super.setUp();		
    }

	@Test
	public void expTest() throws Exception
	{
		InputParameters parametersIn = new InputParameters("Cliente", "CadenaAleatoria", "Obtener", "v1.0", "FIF",
				"CORP", "OSB", "v2016.03", null);
	
		OutputParameters expOutParameters = WrapParameters.expWrap(parametersIn);
		
		this.writeFtl(expOutParameters, "wsdl.ftl", expOutParameters.getWsdlFile());
		
		OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4, 3);
		
		File inputHtml = new File(
				"resources/spec/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
		
		this.writeXsdFiles(offsetParameters, expOutParameters, inputHtml);
	}

	//@Test
	public void compTest() throws Exception
	{
		InputParameters parametersIn = new InputParameters("Cliente", "Hardtoken", "Asignar", "v1.0", "FIF",
				"CORP", "OSB", "v2016.09", null);		
		
		OutputParameters compOutParameters = WrapParameters.compWrap(parametersIn);
		
		this.writeFtl(compOutParameters, "wsdl.ftl", compOutParameters.getWsdlFile());
		
		OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4, 3);
		
		File inputHtml = new File(
				"resources/spec/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
		
		this.writeXsdFiles(offsetParameters, compOutParameters, inputHtml);	
		
	}
	
	@Test
	public void implTest() throws Exception
	{
		InputParameters parametersIn = new InputParameters("Cliente", "UsersOOB", "loginSaltID", "v1.0", "CORP",
				"CORP", "OSB", "v2016.03", "Vucore");
		
		OutputParameters implOutParameters = WrapParameters.implWrap(parametersIn);
		
		this.writeFtl(implOutParameters, "wsdl.ftl", implOutParameters.getWsdlFile());
		
		OffsetParameters offsetParameters = new OffsetParameters(12, 7, 18, 34, 4, 3);
		
		File inputHtml = new File(
				"resources/spec/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
	
		this.writeXsdFiles(offsetParameters, implOutParameters, inputHtml);	
	}

	private void writeXsdFiles(OffsetParameters offsetParameters, OutputParameters outParameters, 
			File inputHtml) throws IOException, TemplateException 
	{		
		new HtmlReader().process(offsetParameters, outParameters, inputHtml);
		
		this.writeFtl(outParameters, "request.ftl", "Schemas/"+outParameters.getRequest().getXsdFile());
		
		this.writeFtl(outParameters, "response.ftl", "Schemas/"+outParameters.getResponse().getXsdFile());	
	}
	
	private void writeFtl(OutputParameters outParameters, String ftl, String file) throws IOException, TemplateException 
	{
		Writer fileWriter = new FileWriter(new File("output/" + file));	
		Template template = super.getCfg().getTemplate(ftl);
		template.process(outParameters, fileWriter);		
	}
}
