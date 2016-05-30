<wsdl:definitions name="OSB_Cliente_ClaveCrear"
	targetNamespace="${out.wsdlNs}"
	xmlns:tns="${out.wsdlNs}"
	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:cs="http://mdwcorp.falabella.com/common/schema/clientservice"
	xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:req="${out.request.xsdNs}"
	xmlns:resp="http://mdwcorp.falabella.com/OSB/schema/FIF/CORP/cliente/clave/crear/Resp-v2015.08">

    <wsdl:types>
		<xs:schema>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_clientService.xsd" namespace="http://mdwcorp.falabella.com/common/schema/clientservice"/>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_soapFault.xsd" namespace="http://schemas.xmlsoap.org/soap/envelope/"/>
			<xs:import schemaLocation="../Schemas/${out.request.xsdFile}" namespace="${out.request.xsdNs}"/>
			<xs:import schemaLocation="../Schemas/OSB_Cliente_Clave_CrearResp.xsd" namespace="http://mdwcorp.falabella.com/OSB/schema/FIF/CORP/cliente/clave/crear/Resp-v2015.08"/>
		</xs:schema>
	</wsdl:types>

    <wsdl:message name="${out.request.payload}">
		<wsdl:part name="ClienteClaveCrearReqParam" element="req:clienteClaveCrearExpReq"/>
	</wsdl:message>
	<wsdl:message name="ClienteClaveCrearMsgResp">
		<wsdl:part name="ClienteClaveCrearRespParam" element="resp:clienteClaveCrearExpResp"/>
	</wsdl:message>
    
    <wsdl:message name="FaultMsg">
		<wsdl:part name="FaultParam" element="env:Fault"/>
	</wsdl:message>
	<wsdl:message name="ClientServiceMsg">
		<wsdl:part name="ClientServiceParam" element="cs:ClientService"/>
	</wsdl:message>
    
    <wsdl:portType name="ClienteClaveCrearPt">
		<wsdl:operation name="ClienteClaveCrearOp">
			<wsdl:input message="tns:ClienteClaveCrearMsgReq"/>
			<wsdl:output message="tns:ClienteClaveCrearMsgResp"/>
			<wsdl:fault name="Fault" message="tns:FaultMsg"/>
		</wsdl:operation>
	</wsdl:portType>
    
    <wsdl:binding name="ClienteClaveCrearBinding" type="tns:ClienteClaveCrearPt">
		<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
		<wsdl:operation name="ClienteClaveCrearOp">
			<soap:operation soapAction="http://mdwcorp.falabella.com/OSB/wsdl/FIF/CORP/cliente/clave/crear-v1.0/Op"/>
			<wsdl:input>
				<soap:header message="tns:ClientServiceMsg" part="ClientServiceParam" use="literal"/>
				<soap:body use="literal"/>
			</wsdl:input>
			<wsdl:output>
				<soap:body use="literal"/>
			</wsdl:output>
			<wsdl:fault name="Fault">
				<soap:fault name="Fault" use="literal"/>
			</wsdl:fault>
		</wsdl:operation>
	</wsdl:binding>
    
    <wsdl:service name="ClienteClaveCrearService">
		<wsdl:port name="ClienteClaveCrearPort" binding="tns:ClienteClaveCrearBinding">
			<soap:address location="http://localhost"/>
		</wsdl:port>
	</wsdl:service>
</wsdl:definitions>