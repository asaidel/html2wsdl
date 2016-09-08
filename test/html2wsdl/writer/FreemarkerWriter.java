package html2wsdl.writer;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerWriter {
		
	protected Configuration CFG;

	@Before
    public void setUp() throws IOException
	{
		CFG = new Configuration(Configuration.VERSION_2_3_25);
		CFG.setDirectoryForTemplateLoading(new File("resources"));				
		CFG.setDefaultEncoding("UTF-8");
		CFG.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CFG.setLogTemplateExceptions(false);
	}
}