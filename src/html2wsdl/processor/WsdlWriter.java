package html2wsdl.processor;

// import html2wsdl.reader.ExcelReader;
import html2wsdl.reader.HtmlReader;
import html2wsdl.vo.parameters.InputParameters;

import java.io.File;
import java.io.IOException;

public class WsdlWriter
{	
  public static void main(String[] args)
  {
	  File input = new File("/asaidel/txt/falabella/Paytrue/tarjetaConfiguracionCambiar/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
	
	  InputParameters expInParameters = new InputParameters("Tarjeta", "Configuracion", "Cambiar", "v1.0", "FIF", 
		      "CORP", "OSB", "v2016.04", null);
	  
	  HtmlReader reader = new HtmlReader();
	  reader.expHtml(input, expInParameters);
  }
}
