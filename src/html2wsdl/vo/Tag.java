package html2wsdl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine a stub = an @XMLElement. It's a tree-like data structure
 * 
 * @author chenjianjx 
 */
public class Tag {

	/**
	 * as "order" in @XmlType(name = "order")
	 */
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
	private List<Tag> childStubs = new ArrayList<Tag>();

	/**
	 * if parent stub's type = Product, is it FunProduct or NotFunProduct?
	 */
	private Class<?> subTypeOfParentStub;

	public void addChild(Tag e) {
		childStubs.add(e);
	}

	public Class<?> getSubTypeOfParentStub() {
		return subTypeOfParentStub;
	}

	public void setSubTypeOfParentStub(Class<?> subTypeOfDeclaringStub) {
		this.subTypeOfParentStub = subTypeOfDeclaringStub;
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

	public List<Tag> getChildStubs() {
		return childStubs;
	}

	public void setChildStubs(List<Tag> childStubs) {
		this.childStubs = childStubs;
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + ", type='" + type + "', minOccurs=" + minOccurs + ", maxOccurs=" + maxOccurs + "]";
	}
}
