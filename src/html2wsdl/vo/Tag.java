package html2wsdl.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Imagine a stub = an @XMLElement. It's a tree-like data structure
 * 
 * @author chenjianjx 
 */
public class Tag implements Cloneable{

	private String order;
	
	private String name;

	/**
	 * the java type of a element, such as "Order.class"
	 */
	private String type;

	private int minOccurs;

	private String maxOccurs;

	/**
	 * Child elements, such as {orderId, orderDate}
	 */
	private List<Tag> children = new ArrayList<Tag>();	

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void addChild(Tag e) {
		children.add(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	public String getMaxOccurs() {
		return maxOccurs;
	}

	public void setMaxOccurs(String maxOccurs) {
		this.maxOccurs = maxOccurs;
	}	

	public List<Tag> getChildren() {
		return children;
	}

	public void setChildren(List<Tag> children) {
		this.children = children;
	}

	/* TODO test: not used yet */
	 public Stream<Tag> flattened() {
	        return Stream.concat(Stream.of(this), children.stream().flatMap(Tag::flattened));
	}
	
	@Override
	public String toString() {
		return "Tag [name=" + name + ", type='" + type + "', minOccurs=" + minOccurs + ", maxOccurs=" + maxOccurs + "]";
	}


	public Tag findFather(List<Tag> tagList) {
		for (Tag father : tagList)
		  {
			if (father.getChildren().contains(this))			
				return father;	
		  }
		return null;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		/*		
		tag.setType(this.getType());
		tag.setOrder(this.getOrder());
		tag.setChildren(this.getChildren());
		tag.setName(this.getName());
		tag.setMinOccurs(this.getMinOccurs());
		tag.setMaxOccurs(this.getMaxOccurs());
	 */	
		return super.clone();//tag;
	}
}
