package html2wsdl.writer;

import java.io.File;
import java.io.IOException;

import org.junit.Before;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerWriterTest {
		
	private Configuration cfg;

	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	@Before
	protected void setUp() throws IOException
	{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
		cfg.setDirectoryForTemplateLoading(new File("resources"));				
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		this.setCfg(cfg);
	}
}