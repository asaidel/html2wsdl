package html2wsdl.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import html2wsdl.vo.Tag;
import html2wsdl.vo.parameters.OffsetParameters;
import html2wsdl.vo.parameters.OutputParameters;

public class HtmlReader extends Reader {

	/**
	 * @param offsetParameters
	 * @param outParameters
	 * @param input
	 * @return
	 * @throws IOException 
	 */
	public void process(OffsetParameters offsetParameters, OutputParameters outParameters, File input) throws IOException
	{			  
		Document doc = Jsoup.parse(input, null);

		// rows loop
		Tag request = readHTML(offsetParameters.getRowSize(), offsetParameters.getInitialRow(),
				offsetParameters.getHeaderSize(), offsetParameters.getInitialCol(), false, doc);

		outParameters.getRequest().setStub(request);

		System.out.println("\nResponse");

		Tag response = readHTML(offsetParameters.getRowSizeResponse(), offsetParameters.getInitialRowResponse(),
				offsetParameters.getHeaderSize(), offsetParameters.getInitialCol(), true, doc);

		outParameters.getResponse().setStub(response);		  
	  }

	/**
	 * @param rowSize
	 * @param initialRow
	 * @param headerSize
	 * @param initialCol
	 * @param found
	 * @param doc
	 * @return
	 */
	private Tag readHTML(int rowSize, int initialRow, int headerSize, int initialCol, boolean found, Document doc) {
		  List<Tag> tagList = new ArrayList<Tag>();
		  
		  Tag root = new Tag(), stubBefore = null, stub;
		  
		  int limit = initialRow + rowSize;
		  
		  for (int i = initialRow; i < limit; i++)
		  {
		      System.out.println("\nline " + i);

		      String rows = "html > body > table > tbody > tr:nth-child(" + i + ") > td";
		    
		      Elements elements = doc.select(rows);
		      
		      stub = new Tag();	
		      
		      // column loop  
		      // name:
		      Element element = (Element)elements.get(initialCol+1);
		      String plainText = getPlainText(element);		      

		      // jump unnecessary header lines
		      if ("ClientService".equals(plainText) && !found)
		      {
		    	  i += headerSize;
		    	  initialRow = i+1;   	 
		    	  found = true;
		    	  System.out.println("header found!");
		    	  continue;
		      }

		      stub.setName(plainText);
		      System.out.println(plainText);

		      // minOccurs
		      element = (Element)elements.get(initialCol+2);
		      plainText = getPlainText(element);
		      stub.setMinOccurs(Integer.valueOf(plainText.substring(0,1)));

		      // maxOccurs
		      String maxOccurs = plainText.substring(3,4);
		      if ("n".equals(maxOccurs) || "u".equals(maxOccurs))
		    	  stub.setMaxOccurs("unbounded");
		      else stub.setMaxOccurs(maxOccurs);

		      System.out.println(plainText);

		      // order
		      element = (Element)elements.get(initialCol);
		      plainText = getPlainText(element);
		      stub.setOrder(plainText);
		      
		      // tipo
		      element = (Element)elements.get(initialCol+3);
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
		  
		  System.out.println();
		  
		  super.displayNodes(root, " ");
		  	
		  return root;
	}
	
	public static String getPlainText(Element element) {
	    FormattingVisitor formatter = new FormattingVisitor();
	    NodeTraversor traversor = new NodeTraversor();
	    traversor.traverse(formatter, element);
	    return formatter.toString().replaceAll("[^a-zA-Z0-9.]", "");
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