# HTML2WSDL
This is the reverse tool for wsdl2html. You have the spec and want to generate the WSDL.

## PARAMETERS
Consider 3 layers: EXP, COMP, IMPL. Each one with its own parameters.

Pass InputParameters and OffsetParameters:

 * HTML spec file (check resources/spec/ATC02...)
 * @param objeto
 * @param entidadDiscreta / interface in IMPL
 * @param operacion
 * @param version
 * @param negocio
 * @param localizacion
 * @param capa
 * @param versionXSD
 * @param backend
 
 * @param initialRow: First spec row with first field (Request)
 * @param headerSize: Header size if any (for jumping rows because it is imported in template already).
 * @param rowSize: Row size in spec (Request)
 * @param initialRowResponse: First spec row with first field (Response)
 * @param rowSizeResponse: Row size in spec (Response)
 * @param initialCol: Column offset (name column)
	 
 The output object for freemarker is OutputParameters. Your WSDL will be in output/ and output/Schemas

## RUN 
* Build your own Junit test, see TransaccionRiesgoEvaluarWsdlWriterTest class. 

You can also change the WrapParameters class, HtmlReader and freemarker templates for parameters wrapping. 

## HISTORY
* 2.0
	* Java 8 required
	* Freemarker 2.3.31
	* Jsoup 1.14.2
	* Junit 4.13.2
	* fixes
	
* 1.5
	* added initialCol
	* better field offset
	* Junit 4.12
	* javadoc for some methods 
	 
* 1.0
	* release

## TODO:
* translate parameters to english.
* in addition to HTML input, it could be excel for parameters and/or spec.