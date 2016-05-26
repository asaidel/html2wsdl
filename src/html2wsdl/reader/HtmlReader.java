package html2wsdl.reader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import html2wsdl.parameters.wrapper.WrapParameters;
import html2wsdl.processor.WsdlWriter;
import html2wsdl.vo.Tag;
import html2wsdl.vo.parameters.InputParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class HtmlReader {

	public static void execHtml(File input, InputParameters expInParameters) throws IOException
	  {	
		  OutputParameters expOutParameters = WrapParameters.expWrap(expInParameters);
		 /* OutputParameters compOutParameters = WrapParameters.compWrap(WsdlWriter.compInParameters);
		  OutputParameters implOutParameters = WrapParameters.implWrap(WsdlWriter.implInParameters);
	  */
		  Document doc = Jsoup.parse(input, null);
		 
		  boolean found = false;
		  
		  // rows loop
		  int j = 20;
		  
		  int initialRow = 12;
		  
		  Tag stubParent = null, root = null;
		  
		  for (int i = initialRow; i < j; i++)
		  {			  
		      System.out.println("\nline " + i);
		      String rows = "html > body > table > tbody > tr:nth-child(" + i + ") > td";
		      
		      Elements elements = doc.select(rows);
		      	 
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
		        
	        	Tag stub = new Tag();	        	
		  
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
		        
//		        if (stubParent == null)
//		        	stubParent = new Tag();
		        
		        if (i==initialRow)
		        {
		        	stubParent = stub;
		        	root = stubParent;
		        }
	          	else // no type
		        if (plainText.length()<=2)		        
		        {
		        	stubParent.addChild(stub);
	        		stubParent = stub;
		        }
		        else {
		        	stubParent.addChild(stub);		        	
		        	stub.setType(plainText);
		        }		         
		          
		        System.out.println(plainText);
		        
		        System.out.println(stub.toString());
		  }
		  
		  displayNodes(root, " ");
	  }

	private static void displayNodes(Tag stub, String str) {
		
		System.out.println(str+stub.getName());
		
		for (Tag child: stub.getChildStubs())
		{
			displayNodes(child, str+" ");
		}		
	}

public static String getPlainText(Element element)
  {
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
