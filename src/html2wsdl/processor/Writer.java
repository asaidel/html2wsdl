package html2wsdl.processor;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public abstract class Writer {

	public final static Configuration CFG = new Configuration(Configuration.VERSION_2_3_25);

	public static Template configure(String ftl) throws IOException
	{
		CFG.setDirectoryForTemplateLoading(new File("resources"));				
		CFG.setDefaultEncoding("UTF-8");
		CFG.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CFG.setLogTemplateExceptions(false);
	    	  
		Template template = CFG.getTemplate(ftl);
		return template;
	}
}