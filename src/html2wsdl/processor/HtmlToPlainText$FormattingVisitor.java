package html2wsdl.processor;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

class FormattingVisitor
  implements NodeVisitor
{
  private static final int maxWidth = 80;
  private int width = 0;
  private StringBuilder accum = new StringBuilder();
  
  private FormattingVisitor(HtmlToPlainText paramHtmlToPlainText) {}
  
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
