package html2wsdl.vo;

import java.util.List;

public class ExcelObject {
		
	private List< List<String> > registros;
	private String codClienteSis;	

	public List<List<String>> getRegistros() {
		return registros;
	}

	public void setRegistros(List<List<String>> registros) {
		this.registros = registros;
	}

	public void setCodClienteSis(String codClienteSis) {
		this.codClienteSis = codClienteSis;
	}

	public String getCodClienteSis() {
		return codClienteSis;
	}
}
