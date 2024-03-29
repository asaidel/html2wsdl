package html2wsdl.parameters.wrapper;

import html2wsdl.vo.Struct;
import html2wsdl.vo.parameters.InputParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class WrapParameters
{
  
  private static final String SITE = "http://mdwcorp.falabella.com/";

  public static OutputParameters expWrap(InputParameters in)
  {
    OutputParameters out = new OutputParameters();
    
    String filePrefix = in.getNegocio() + "_" + in.getLocalizacion() + "_" + in.getCapa() + "_" + 
      in.getObjeto() + "_" + in.getEntidadDiscreta() + "_" + in.getOperacion();
    
    out.setWsdlFile(filePrefix + ".wsdl");
    
    out.setWsdlNs(getNsPrefix(in, "wsdl") + "-" + in.getVersion());
    
    String negEntOp = in.getObjeto() + in.getEntidadDiscreta() + in.getOperacion();
    
    Struct request = new Struct(negEntOp + "MsgReq", negEntOp + "ReqParam", filePrefix + "Req.xsd", 
    		getNsPrefix(in, "schema") + "/Req-" + in.getVersionXSD());
    out.setRequest(request);
    
    Struct response = new Struct(negEntOp + "MsgResp", negEntOp + "RespParam", filePrefix + "Resp.xsd", 
    		getNsPrefix(in, "schema") + "/Resp-" + in.getVersionXSD());
    out.setResponse(response);
    
    out.setBindingOp(negEntOp + "Binding");
    out.setServiceName(negEntOp + "Service");
    out.setPortName(negEntOp + "Pt");
    out.setSoapAction(out.getWsdlNs() + "/Op");
    out.setPortOp(negEntOp + "Op");    
    out.setServicePort(negEntOp + "Port");
    
    return out;
  }

  public static OutputParameters compWrap(InputParameters in)
  {
    OutputParameters out = new OutputParameters();
 
    String filePrefix = in.getNegocio() + "_" + in.getLocalizacion() + "_" + in.getCapa() + "_" + 
      in.getObjeto() + "_" + in.getEntidadDiscreta() + "_" + in.getOperacion();
    
    out.setWsdlFile(filePrefix + "_COMP.wsdl");
    
    out.setWsdlNs(getNsPrefix(in, "wsdl") + "-" + in.getVersion() + "/COMP");
    
    String negEntOp = in.getObjeto() + in.getEntidadDiscreta() + in.getOperacion();
    
    Struct request = new Struct(negEntOp + "MsgReq", negEntOp + "ReqParam", filePrefix + "_COMP_Req.xsd", 
    		getNsPrefix(in, "schema") + "/Req/COMP/" + in.getVersionXSD());
    out.setRequest(request);
    
    Struct response = new Struct(negEntOp + "MsgResp", negEntOp + "RespParam", filePrefix + "_COMP_Resp.xsd", 
    		getNsPrefix(in, "schema") + "/Resp/COMP/" + in.getVersionXSD());
    out.setResponse(response);
    
    out.setBindingOp(negEntOp + "Binding");
    out.setServiceName(negEntOp + "ServiceCOMP");
    out.setPortName(negEntOp + "Pt");
    out.setSoapAction(out.getWsdlNs() + "/Op");
    out.setPortOp(negEntOp + "Op");    
    out.setServicePort(negEntOp + "PortCOMP");
    
    return out;
  }  
  
  public static OutputParameters implWrap(InputParameters in)
  {
    OutputParameters out = new OutputParameters();  
    
    String filePrefix = in.getCapa() + "_" + in.getNegocio() + "_" + in.getLocalizacion() + "_" + in.getBackend() +  
    		"_" + in.getObjeto() + "_" + in.getEntidadDiscreta() + "_" + in.getOperacion();
    
    out.setWsdlFile(filePrefix + "_IMPL.wsdl");
    
    out.setWsdlNs(getNsPrefix(in, "wsdl") + "/IMPL/" + in.getVersion());
    
    String negEntOp = in.getBackend() + in.getObjeto() + in.getEntidadDiscreta() + in.getOperacion();
    
    Struct request = new Struct(negEntOp + "MsgReq", negEntOp + "ReqParam", filePrefix + "_IMPL_Req.xsd", 
    		getNsPrefix(in, "schema") + "/Req/IMPL/" + in.getVersionXSD());
    out.setRequest(request);
    
    Struct response = new Struct(negEntOp + "MsgResp", negEntOp + "RespParam", filePrefix + "_IMPL_Resp.xsd", 
    		getNsPrefix(in, "schema") + "/Resp/IMPL/" + in.getVersionXSD());
    out.setResponse(response);
    
    out.setBindingOp(negEntOp + "Binding");
    out.setServiceName(negEntOp + "ServiceIMPL");
    out.setPortName(negEntOp + "Pt");
    out.setSoapAction(out.getWsdlNs() + "/Op");
    out.setPortOp(negEntOp + "Op");    
    out.setServicePort(negEntOp + "PortIMPL");   
    
    return out;
  }

private static String getNsPrefix(InputParameters in, String str) {
	return SITE + in.getNegocio() + "/" +  in.getLocalizacion() + "/" + in.getCapa() + "/" + 
			str + "/" + (in.getBackend() == null ? "" : in.getBackend() + "/") + in.getObjeto() + 
			"/" + in.getEntidadDiscreta() + "/" + in.getOperacion();
	}  
}
