package html2wsdl.reader;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import html2wsdl.vo.Tag;
import html2wsdl.vo.parameters.InputParameters;

public class Reader {

	protected static void displayNodes(Tag stub, String str) {
		
		System.out.println(str+stub.getName());
		
		for (Tag child: stub.getChildren())
		{
			displayNodes(child, str+" ");
		}		
	}

	public static String getPlainText(Element element) {
	    FormattingVisitor formatter = new FormattingVisitor();
	    NodeTraversor traversor = new NodeTraversor(formatter);
	    traversor.traverse(element);
	    return formatter.toString();
	  }

	public Reader() {
		super();
	}

	protected void expHtml(File input, InputParameters expInParameters) 
	{
		// TODO Auto-generated method stub		
	}

	/**
	 * @param tagList
	 * @param stubBefore
	 * @param stub
	 * @param plainText
	 * @return
	 */
	protected Tag handleOrder(List<Tag> tagList, Tag stubBefore, Tag stub, String plainText) {
		if (stub.getOrder().length() > stubBefore.getOrder().length())
		  {
			  stubBefore.addChild(stub);		     		 
		  }
		  else if (stub.getOrder().length() == stubBefore.getOrder().length()) 
		  {
			  // root.flattened().filter(tag -> tag.getChildren().contains(stubParent)).findFirst();
			  Tag father = stubBefore.findFather(tagList);		    		  
			  father.addChild(stub);    		 	    		  
		  }
		  else // stub.getOrder().length() == stubBefore.getOrder().length()
		  {		
			  // TODO maybe sibling search is more precise comparing order
			  Tag grandPa = stubBefore.findFather(tagList).findFather(tagList);		    		 
			  grandPa.addChild(stub);
		  }
		  
		stub.setType(plainText);
		stubBefore = stub;
		return stubBefore;
	}
}

/*	private static Tag searchFather(Tag root, Tag child) {		
//Tag father = null;

Optional<Tag> optional = root.getChildren().parallelStream()
		.filter(father -> test(root, father, child)).findFirst();

for (Iterator<Tag> iterator = root.getChilds().iterator(); iterator.hasNext();) {
	father = iterator.next();
	
	if (father.getChilds().contains(child))		
		break;
	else 
		searchFather(child, root);
}


Tag result = null;

if (optional.isPresent()) 
	result = optional.get();

return result;		
}

private static boolean test(Tag root, Tag father, Tag child) {	
if (!father.getChildren().contains(child))
	searchFather(root, child);
else return true;

return false;	
}
*/

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