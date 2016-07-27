<wsdl:definitions name="${getWsdlFile()}"
	targetNamespace="${getWsdlNs()}"
	xmlns:tns="${getWsdlNs()}"
	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:cs="http://mdwcorp.falabella.com/common/schema/clientservice"
	xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:req="${getRequest().xsdNs}"
	xmlns:resp="${getResponse().xsdNs}">

    <wsdl:types>
		<xs:schema>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_clientService.xsd" namespace="http://mdwcorp.falabella.com/common/schema/clientservice"/>
			<xs:import schemaLocation="../../../UT_EsquemasComunes_V.2.0/Specifications/XSD/Common/MdwCorp_Common_soapFault.xsd" namespace="http://schemas.xmlsoap.org/soap/envelope/"/>
			<xs:import schemaLocation="../../Resources/Schemas/${getRequest().xsdFile}" namespace="${getRequest().xsdNs}"/>
			<xs:import schemaLocation="../../Resources/Schemas/${getResponse().xsdFile}" namespace="${getResponse().xsdNs}"/>
		</xs:schema>
	</wsdl:types>

    <wsdl:message name="${getRequest().msgName}">
		<wsdl:part name="${getRequest().param}" element="req:${getRequest().msgName}"/>
	</wsdl:message>
	<wsdl:message name="${getResponse().msgName}">
		<wsdl:part name="${getResponse().param}" element="resp:${getResponse().msgName}"/>
	</wsdl:message>
    
    <wsdl:message name="FaultMsg">
		<wsdl:part name="FaultParam" element="env:Fault"/>
	</wsdl:message>
	<wsdl:message name="ClientServiceMsg">
		<wsdl:part name="ClientServiceParam" element="cs:ClientService"/>
	</wsdl:message>
    
    <wsdl:portType name="${getPortName()}">
		<wsdl:operation name="${getPortOp()}">
			<wsdl:input message="tns:${getRequest().msgName}"/>
			<wsdl:output message="tns:${getResponse().msgName}"/>
			<wsdl:fault name="Fault" message="tns:FaultMsg"/>
		</wsdl:operation>
	</wsdl:portType>
    
    <wsdl:binding name="${getBindingOp()}" type="tns:${getPortName()}">
		<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
		<wsdl:operation name="${getPortOp()}">
			<soap:operation soapAction="${getSoapAction()}"/>
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
    
    <wsdl:service name="${getServiceName()}">
		<wsdl:port name="${getServicePort()}" binding="tns:${getBindingOp()}">
			<soap:address location="http://localhost"/>
		</wsdl:port>
	</wsdl:service>
</wsdl:definitions>