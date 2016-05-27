package html2wsdl.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import html2wsdl.parameters.wrapper.WrapParameters;
import html2wsdl.vo.Tag;
import html2wsdl.vo.parameters.InputParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class HtmlReader extends Reader {

	@Override
	public void expHtml(File input, InputParameters expInParameters)
	{	
		  OutputParameters expOutParameters = WrapParameters.expWrap(expInParameters);
		 /* OutputParameters compOutParameters = WrapParameters.compWrap(WsdlWriter.compInParameters);
		  OutputParameters implOutParameters = WrapParameters.implWrap(WsdlWriter.implInParameters);
	  */
		  Document doc;
		try {
			doc = Jsoup.parse(input, null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		 
		  boolean found = false;
		  
		  // TODO candidates to be params
		  // rows loop
		  int j = 20;
		  
		  int initialRow = 12;
		  
		  List<Tag> tagList = new ArrayList<Tag>();
		  
		  Tag root = new Tag(), stubBefore = null, stub;
		  
		  for (int i = initialRow; i < j; i++)
		  {			  
		      System.out.println("\nline " + i);
		      String rows = "html > body > table > tbody > tr:nth-child(" + i + ") > td";
		      
		      Elements elements = doc.select(rows);
		      
		      stub = new Tag();	
		      
		      // column loop
		      // hierarchy
		      Element element = (Element)elements.get(3);
		      String plainText = getPlainText(element);
		      stub.setOrder(plainText);

		      // name:
		      element = (Element)elements.get(4);
		      plainText = getPlainText(element);		      

		      // jump unnecessary header lines
		      if ("ClientService".equals(plainText) && !found)
		      {
		    	  i += 7;
		    	  initialRow = i+1;
		    	  j = 30;
		    	  found = true;
		    	  System.out.println("header found!");
		    	  continue;
		      }	              	

		      stub.setName(plainText);
		      System.out.println(plainText);

		      // minOccurs
		      element = (Element)elements.get(5);
		      plainText = getPlainText(element);
		      stub.setMinOccurs(Integer.valueOf(plainText.substring(0,1)));

		      // maxOccurs
		      String maxOccurs = plainText.substring(3,4);
		      if ("n".equals(maxOccurs) || "u".equals(maxOccurs))
		    	  stub.setMaxOccurs("unbounded");
		      else stub.setMaxOccurs(maxOccurs);

		      System.out.println(plainText);

		      // tipo
		      element = (Element)elements.get(6);
		      plainText = getPlainText(element);

		      if (i > initialRow)
		      {
		    	  stubBefore = super.handleOrder(tagList, stubBefore, stub, plainText);
		      }
		      else // no type
		      {
		    	  stubBefore = stub;    	 
		    	 // BeanUtils.copyProperties(root, stubBefore);
		    	  try {
					root = (Tag) stubBefore.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}		    	  
		      }
		      
		      /* String stubOrderPadded = StringUtils.rightPad(stub.getOrder(), 
	    		stubParent.getOrder().length());
	    	
	    	  if (stubOrderPadded.compareTo(stubParent.getOrder())<0)

	    	  if (plainText.length()<=2)
	    	  {    		  
	    		  stubParent.addChild(stub);
	    		  stubParent = stub;
	    	  }
	    	  */ 
		      
		      tagList.add(stub);
		      
		      System.out.println(plainText);

		      System.out.println(stub.toString());
		  } 
		  
		  super.displayNodes(root, " ");
		  
		  expOutParameters.getRequest().setStub(root);
	  }
}