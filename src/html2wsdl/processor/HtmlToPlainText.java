package html2wsdl.processor;

import html2wsdl.parameters.InputParameters;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class HtmlToPlainText
{
  private static InputParameters expParameters;
  private static InputParameters compParameters;
  private static InputParameters implParameters;
  
  public static void main(String... args)
    throws IOException
  {
    File input = new File("/asaidel/txt/falabella/Paytrue/tarjetaConfiguracionCambiar/ATC02 - Definicion y Mapeo de Mensajería - tarjetaConfiguracionCambiar - CMRCL_archivos/sheet003.htm");
    
    Document doc = Jsoup.parse(input, null);
    
    HtmlToPlainText formatter = new HtmlToPlainText();
    
    expParameters = new InputParameters("Tarjeta", "Configuracion", "Cambiar", "v1.0", "FIF", 
      "CORP", "OSB", "v2016.04");
    for (int i = 12; i < 30; i++)
    {
      System.out.println("\nline " + i);
      String rows = "html > body > table > tbody > tr:nth-child(" + i + ") > td";
      
      Elements elements = doc.select(rows);
      for (int k = 4; k < 7; k++)
      {
        Element element = (Element)elements.get(k);
        String plainText = formatter.getPlainText(element);
        System.out.println(plainText);
      }
    }
  }
  
  public String getPlainText(Element element)
  {
    FormattingVisitor formatter = new FormattingVisitor();
    NodeTraversor traversor = new NodeTraversor(formatter);
    traversor.traverse(element);
    return formatter.toString();
  }
  
  private class FormattingVisitor
    implements NodeVisitor
  {
    private static final int maxWidth = 80;
    private int width = 0;
    private StringBuilder accum = new StringBuilder();
    
    private FormattingVisitor() {}
    
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
}
