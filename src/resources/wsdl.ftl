<wsdl:definitions name="OSB_Cliente_ClaveCrear"
	targetNamespace="${out.wsdlNs}"
	xmlns:tns="${out.wsdlNs}"
	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:cs="http://mdwcorp.falabella.com/common/schema/clientservice"
	xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:req="${out.request.xsdNs}"
	xmlns:resp="${out.response.xsdNs}">

    <wsdl:types>
		<xs:schema>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_clientService.xsd" namespace="http://mdwcorp.falabella.com/common/schema/clientservice"/>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_soapFault.xsd" namespace="http://schemas.xmlsoap.org/soap/envelope/"/>
			<xs:import schemaLocation="../Schemas/${out.request.xsdFile}" namespace="${out.request.xsdNs}"/>
			<xs:import schemaLocation="../Schemas/${out.response.xsdFile}" namespace="${out.response.xsdNs}"/>
		</xs:schema>
	</wsdl:types>

    <wsdl:message name="${out.request.msgName}">
		<wsdl:part name="${out.request.param}" element="req:clienteClaveCrearExpReq"/>
	</wsdl:message>
	<wsdl:message name="${out.response.msgName}">
		<wsdl:part name="${out.response.msgName}" element="resp:clienteClaveCrearExpResp"/>
	</wsdl:message>
    
    <wsdl:message name="FaultMsg">
		<wsdl:part name="FaultParam" element="env:Fault"/>
	</wsdl:message>
	<wsdl:message name="ClientServiceMsg">
		<wsdl:part name="ClientServiceParam" element="cs:ClientService"/>
	</wsdl:message>
    
    <wsdl:portType name="${out.portName}">
		<wsdl:operation name="${out.portOp}">
			<wsdl:input message="tns:${out.request.msgName}"/>
			<wsdl:output message="tns:${out.response.msgName}"/>
			<wsdl:fault name="Fault" message="tns:FaultMsg"/>
		</wsdl:operation>
	</wsdl:portType>
    
    <wsdl:binding name="${out.bindingOp}" type="tns:${out.portName}">
		<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
		<wsdl:operation name="${out.portOp}">
			<soap:operation soapAction="${out.soapAction}"/>
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
    
    <wsdl:service name="${out.serviceName}">
		<wsdl:port name="${out.servicePort}" binding="tns:${out.bindingOp}">
			<soap:address location="http://localhost"/>
		</wsdl:port>
	</wsdl:service>
</wsdl:definitions>