package html2wsdl.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import html2wsdl.vo.Tag;
import html2wsdl.vo.parameters.OutputParameters;

public class HtmlReader extends Reader {

	public Tag processExp(File input, OutputParameters expOutParameters)
	{			  
		Document doc;
		
		try {
			doc = Jsoup.parse(input, null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
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
		      // name:
		      Element element = (Element)elements.get(4);
		      String plainText = getPlainText(element);		      

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

		      // hierarchy
		      element = (Element)elements.get(3);
		      plainText = getPlainText(element);
		      stub.setOrder(plainText);
		      
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
					return null;
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
		  		  
			 /* OutputParameters compOutParameters = WrapParameters.compWrap(WsdlWriter.compInParameters);
			  OutputParameters implOutParameters = WrapParameters.implWrap(WsdlWriter.implInParameters);
		  */
		  
		  expOutParameters.getRequest().setStub(root);
		  
		  // TODO pass to freemarker
		  
		  return root;
	  }
	
	public Tag processComp(File input, OutputParameters compOutParameters)
	{
		Tag root = processExp(input, compOutParameters);
		 // TODO pass to freemarker
		
		return root;
	}
	
	

	public static String getPlainText(Element element) {
	    FormattingVisitor formatter = new FormattingVisitor();
	    NodeTraversor traversor = new NodeTraversor(formatter);
	    traversor.traverse(element);
	    return formatter.toString();
	  }
}

class FormattingVisitor implements NodeVisitor
  {
    private static final int maxWidth = 80;
    private int width = 0;
    private StringBuilder accum = new StringBuilder();
    
    public void head(Node node, int depth)
    {
      String name = node.nodeName();
      if ((node instanceof TextNode)) {
        append(((TextNode)node).text());
      } else if (name.equals("li")) {
        append("\n * ");
      } else if (name.equals("dt")) {
        append("  ");
      } else if (StringUtil.in(name, new String[] { "p", "h1", "h2", "h3", "h4", "h5", "tr" })) {
        append("\n");
      }
    }
    
    public void tail(Node node, int depth)
    {
      String name = node.nodeName();
      if (StringUtil.in(name, new String[] { "br", "dd", "dt", "p", "h1", "h2", "h3", "h4", "h5" })) {
        append("\n");
      } else if (name.equals("a")) {
        append(String.format(" <%s>", new Object[] { node.absUrl("href") }));
      }
    }
    
    private void append(String text)
    {
      if (text.startsWith("\n")) {
        this.width = 0;
      }
      if (text.equals(" ")) {
        if (this.accum.length() != 0)
        {
          if (!StringUtil.in(this.accum.substring(this.accum.length() - 1), new String[] { " ", "\n" })) {}
        }
        else {
          return;
        }
      }
      if (text.length() + this.width > 80)
      {
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++)
        {
          String word = words[i];
          boolean last = i == words.length - 1;
          if (!last) {
            word = word + " ";
          }
          if (word.length() + this.width > 80)
          {
            this.accum.append("\n").append(word);
            this.width = word.length();
          }
          else
          {
            this.accum.append(word);
            this.width += word.length();
          }
        }
      }
      else
      {
        this.accum.append(text);
        this.width += text.length();
      }
    }
    
    public String toString()
    {
      return this.accum.toString();
    }
  }