# HTML2WSDL

This is the reverse tool for wsdl2html. You have the spec and want to generate the WSDL.

##PARAMETERS
Consider 2 layers: EXP, COMP. Each one with its own parameters. Check WsdlWriter class.

 * HTML file
 * @param objeto
 * @param entidadDiscreta
 * @param operacion
 * @param version
 * @param negocio
 * @param localizacion
 * @param capa
 * @param versionXSD
 * @param backend
 
 The output object for freemarker is OutputParameters 

##RUN 
* WsdlWriter class. 

You can also modify the WrapParameters class, freemarker templates for parameters wrapping. 

##STATUS
* Beta state yet
* EXP, COMP layer ready (Java part)
* EXP WSDL, XSD ready

##TODO:
* add example
* translate parameters to english
* in addition to HTML input, it could be excel for parameters and/or spec.
* COMP freemarker templates (WSDL, XSD). 