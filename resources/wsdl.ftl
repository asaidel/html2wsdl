<wsdl:definitions name="${wsdlFile}"
	targetNamespace="${wsdlNs}"
	xmlns:tns="${wsdlNs}"
	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:cs="http://mdwcorp.falabella.com/common/schema/clientservice"
	xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:req="${request.xsdNs}"
	xmlns:resp="${response.xsdNs}">

    <wsdl:types>
		<xs:schema>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_clientService.xsd" namespace="http://mdwcorp.falabella.com/common/schema/clientservice"/>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_soapFault.xsd" namespace="http://schemas.xmlsoap.org/soap/envelope/"/>
			<xs:import schemaLocation="../Schemas/${request.xsdFile}" namespace="${request.xsdNs}"/>
			<xs:import schemaLocation="../Schemas/${response.xsdFile}" namespace="${response.xsdNs}"/>
		</xs:schema>
	</wsdl:types>

    <wsdl:message name="${request.msgName}">
		<wsdl:part name="${request.param}" element="req:${request.msgName}"/>
	</wsdl:message>
	<wsdl:message name="${response.msgName}">
		<wsdl:part name="${response.param}" element="resp:${response.msgName}"/>
	</wsdl:message>
    
    <wsdl:message name="FaultMsg">
		<wsdl:part name="FaultParam" element="env:Fault"/>
	</wsdl:message>
	<wsdl:message name="ClientServiceMsg">
		<wsdl:part name="ClientServiceParam" element="cs:ClientService"/>
	</wsdl:message>
    
    <wsdl:portType name="${portName}">
		<wsdl:operation name="${portOp}">
			<wsdl:input message="tns:${request.msgName}"/>
			<wsdl:output message="tns:${response.msgName}"/>
			<wsdl:fault name="Fault" message="tns:FaultMsg"/>
		</wsdl:operation>
	</wsdl:portType>
    
    <wsdl:binding name="${bindingOp}" type="tns:${portName}">
		<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
		<wsdl:operation name="${portOp}">
			<soap:operation soapAction="${soapAction}"/>
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
    
    <wsdl:service name="${serviceName}">
		<wsdl:port name="${servicePort}" binding="tns:${bindingOp}">
			<soap:address location="http://localhost"/>
		</wsdl:port>
	</wsdl:service>
</wsdl:definitions>