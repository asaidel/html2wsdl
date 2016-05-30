package html2wsdl.vo.parameters;

import html2wsdl.vo.Struct;

public class OutputParameters
{  
  private String wsdlFile;
  private String wsdlNs;
  private String portName;
  private String portOp;
  private String bindingOp;
  private String soapAction;
  private String serviceName;
  private String servicePort;
  
  private Struct request;
  private Struct response;
  
  public String getWsdlFile()
  {
    return this.wsdlFile;
  }
  
  public void setWsdlFile(String wsdlFile)
  {
    this.wsdlFile = wsdlFile;
  }
  
  public String getWsdlNs()
  {
    return this.wsdlNs;
  }
  
  public void setWsdlNs(String wsdlNs)
  {
    this.wsdlNs = wsdlNs;
  }
  
  public String getPortOp()
  {
    return this.portOp;
  }
  
  public void setPortOp(String portOp)
  {
    this.portOp = portOp;
  }
  
  public String getPortName()
  {
    return this.portName;
  }
  
  public void setPortName(String portName)
  {
    this.portName = portName;
  }
  
  public String getBindingOp()
  {
    return this.bindingOp;
  }
  
  public void setBindingOp(String bindingOp)
  {
    this.bindingOp = bindingOp;
  }
  
  public String getSoapAction()
  {
    return this.soapAction;
  }
  
  public void setSoapAction(String soapAction)
  {
    this.soapAction = soapAction;
  }
  
  public String getServiceName()
  {
    return this.serviceName;
  }
  
  public void setServiceName(String serviceName)
  {
    this.serviceName = serviceName;
  }
  
  public String getServicePort()
  {
    return this.servicePort;
  }
  
  public void setServicePort(String servicePort)
  {
    this.servicePort = servicePort;
  }
  
  public Struct getRequest()
  {
    return this.request;
  }
  
  public void setRequest(Struct request)
  {
    this.request = request;
  }
  
  public Struct getResponse()
  {
    return this.response;
  }
  
  public void setResponse(Struct response)
  {
    this.response = response;
  }
}
