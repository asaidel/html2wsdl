package html2wsdl.parameters;

public class Struct
{
  private String payload;
  private String param;
  private String xsdFile;
  private String xsdNs;
  
  /**
 * @param payload
 * @param param
 * @param xsdFile
 * @param xsdNs
 */
public Struct(String payload, String param, String xsdFile, String xsdNs)
  {
    this.payload = payload;
    this.param = param;
    this.xsdFile = xsdFile;
    this.xsdNs = xsdNs;
  }
  
  public String getPayload()
  {
    return this.payload;
  }
  
  public void setPayload(String payload)
  {
    this.payload = payload;
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
}
