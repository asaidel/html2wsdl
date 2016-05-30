package html2wsdl.vo;

public class Struct
{
  private String msgName;
  private String param;
  private String xsdFile;
  private String xsdNs;
  private Tag stub;
  
 /**
 * @param msgName
 * @param param
 * @param xsdFile
 * @param xsdNs
 */
public Struct(String msgName, String param, String xsdFile, String xsdNs)
  {
    this.msgName = msgName;
    this.param = param;
    this.xsdFile = xsdFile;
    this.xsdNs = xsdNs;
  }
  
  public String getMsgName()
  {
    return this.msgName;
  }
  
  public void setMsgName(String msgName)
  {
    this.msgName = msgName;
  }
  
  public String getParam()
  {
    return this.param;
  }
  
  public void setParam(String param)
  {
    this.param = param;
  }
  
  public String getXsdFile()
  {
    return this.xsdFile;
  }
  
  public void setXsdFile(String xsdFile)
  {
    this.xsdFile = xsdFile;
  }
  
  public String getXsdNs()
  {
    return this.xsdNs;
  }
  
  public void setXsdNs(String xsdNs)
  {
    this.xsdNs = xsdNs;
  }

public Tag getStub() {
	return stub;
}

public void setStub(Tag stub) {
	this.stub = stub;
}
}
