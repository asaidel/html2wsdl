<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:tns="${request.xsdNs}"
	targetNamespace="${request.xsdNs}"
	elementFormDefault="unqualified">
	
	<xs:element name="${request.msgName}"
    	type="tns:${request.msgName}_TYPE" />    

<#macro complexTypes stub>
<#if stub.children?size!=0> 
     <xs:complexType name="${stub.name}_TYPE" >    
	    <xs:sequence>
	    	<#list stub.children as child>	    			    		
				<xs:element name="${child.name}" <@compress single_line=true>
				type=<#if child.type=="" || child.type==" ">"tns:${child.name}_TYPE"
				<#else>"xs:${child.type?uncap_first}"</#if>
				<#if child.minOccurs=0> minOccurs="0"</#if>
    			<#if child.maxOccurs!="1"> maxOccurs="unbounded"</#if>/></@compress>
	    	</#list>
		</xs:sequence>
    </xs:complexType>
    <#list stub.children as child>
    	<@complexTypes stub=child />
    </#list> 
</#if>
</#macro>

	<@complexTypes stub=request.stub />
</xs:schema>