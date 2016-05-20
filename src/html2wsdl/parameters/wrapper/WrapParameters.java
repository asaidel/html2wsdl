package html2wsdl.parameters.wrapper;

import html2wsdl.parameters.InputParameters;
import html2wsdl.parameters.OutputParameters;
import html2wsdl.parameters.Struct;

public class WrapParameters
{
  
  private static final String SITE = "http://mdwcorp.falabella.com/";

OutputParameters expWrap(InputParameters in)
  {
    OutputParameters out = new OutputParameters();
    
    String nsPrefix = SITE + in.getNegocio() + "/" + in.getLocalizacion() + "/" + in.getLocalizacion() + 
      "/" + in.getCapa() + "/" + in.getObjeto() + "/" + in.getEntidadDiscreta() + "/" + in.getOperacion();
    
    out.setURI(nsPrefix + "/" + in.getVersion());
    
    String filePrefix = in.getNegocio() + "_" + in.getLocalizacion() + "_" + in.getCapa() + "_" + 
      in.getObjeto() + "_" + in.getEntidadDiscreta() + "_" + in.getOperacion();
    
    out.setWsdlFile(filePrefix + ".wsdl");
    
    out.setWsdlNs(SITE + in.getNegocio() + "/" + in.getLocalizacion() + "/" + in.getCapa() + "/wsdl/" + 
      in.getObjeto() + "/" + in.getEntidadDiscreta() + "/" + in.getOperacion() + "-" + in.getVersion());
    
    String negEntOp = in.getNegocio() + in.getEntidadDiscreta() + in.getOperacion();
    
    Struct request = new Struct(negEntOp + "MsgReq", negEntOp + "ReqParam", filePrefix + "Req.xsd", 
      nsPrefix + "Req-" + in.getVersionXSD());
    out.setRequest(request);
    
    Struct response = new Struct(negEntOp + "MsgResp", negEntOp + "RespParam", filePrefix + "Resp.xsd", 
      nsPrefix + "Resp-" + in.getVersionXSD());
    out.setResponse(response);
    
    out.setBindingOp(negEntOp + "Binding");
    out.setServiceName(negEntOp + "Service");
    out.setPortType(negEntOp + "Port");
    
    out.setSoapAction(out.getWsdlNs() + "/Op");
    
    return out;
  }
  
  OutputParameters compWrap(InputParameters in)
  {
    OutputParameters out = new OutputParameters();
    
    String nsPrefix = SITE + in.getNegocio() + "/" + in.getLocalizacion() + "/" + in.getLocalizacion() + 
      "/" + in.getCapa() + "/" + in.getObjeto() + "/" + in.getEntidadDiscreta() + "/" + in.getOperacion();
    
    out.setURI(nsPrefix + "/" + in.getVersion() + "/COMP");
    
    String filePrefix = in.getNegocio() + "_" + in.getLocalizacion() + "_" + in.getCapa() + "_" + 
      in.getObjeto() + "_" + in.getEntidadDiscreta() + "_" + in.getOperacion();
    
    out.setWsdlFile(filePrefix + "_COMP.wsdl");
    
    out.setWsdlNs(SITE + in.getNegocio() + "/" + in.getLocalizacion() + "/" + in.getCapa() + "/wsdl/" + 
      in.getObjeto() + "/" + in.getEntidadDiscreta() + "/" + in.getOperacion() + "-" + in.getVersion() + 
      "/COMP");
    
    String negEntOp = in.getNegocio() + in.getEntidadDiscreta() + in.getOperacion();
    
    Struct request = new Struct(negEntOp + "MsgReq", negEntOp + "ReqParam", filePrefix + "_COMP_Req.xsd", 
      nsPrefix + "Req-" + in.getVersionXSD());
    out.setRequest(request);
    
    Struct response = new Struct(negEntOp + "MsgResp", negEntOp + "RespParam", filePrefix + "_COMP_Resp.xsd", 
      nsPrefix + "Resp-" + in.getVersionXSD());
    out.setResponse(response);
    
    out.setBindingOp(negEntOp + "Binding");
    out.setServiceName(negEntOp + "ServiceCOMP");
    out.setPortType(negEntOp + "PortCOMP");
    
    out.setSoapAction(out.getWsdlNs() + "/Op");
    
    return out;
  }
  
  OutputParameters implWrap(InputParameters in)
  {
    OutputParameters out = new OutputParameters();
    
    String nsPrefix = SITE + in.getNegocio() + "/" + in.getLocalizacion() + "/" + in.getLocalizacion() + 
      "/" + in.getCapa() + "/" + in.getObjeto() + "/" + in.getEntidadDiscreta() + "/" + in.getOperacion();
    
    out.setURI(nsPrefix + "/" + in.getVersion() + "/COMP");
    
    String filePrefix = in.getNegocio() + "_" + in.getLocalizacion() + "_" + in.getCapa() + "_" + 
      in.getObjeto() + "_" + in.getEntidadDiscreta() + "_" + in.getOperacion();
    
    out.setWsdlFile(filePrefix + "_COMP.wsdl");
    
    out.setWsdlNs(SITE + in.getNegocio() + "/" + in.getLocalizacion() + "/" + in.getCapa() + "/wsdl/" + 
      in.getObjeto() + "/" + in.getEntidadDiscreta() + "/" + in.getOperacion() + "-" + in.getVersion() + 
      "/COMP");
    
    String negEntOp = in.getNegocio() + in.getEntidadDiscreta() + in.getOperacion();
    
    Struct request = new Struct(negEntOp + "MsgReq", negEntOp + "ReqParam", filePrefix + "_COMP_Req.xsd", 
      nsPrefix + "Req-" + in.getVersionXSD());
    out.setRequest(request);
    
    Struct response = new Struct(negEntOp + "MsgResp", negEntOp + "RespParam", filePrefix + "_COMP_Resp.xsd", 
      nsPrefix + "Resp-" + in.getVersionXSD());
    out.setResponse(response);
    
    out.setBindingOp(negEntOp + "Binding");
    out.setServiceName(negEntOp + "ServiceCOMP");
    out.setPortType(negEntOp + "PortCOMP");
    
    out.setSoapAction(out.getWsdlNs() + "/Op");
    
    return out;
  }
}
