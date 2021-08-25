package html2wsdl.vo;

//import java.util.Comparator;

public class SimpleBean //implements Comparable<SimpleBean>
{
	private Integer index;	
	private String name;
	private String value;
	
	public SimpleBean()
	{
		super();
	}

	public SimpleBean(String name, String value)
	{
		super();
		setName(name);
		setValue(value);
	}
	
	public SimpleBean(Integer index, String value)
	{
		super();
		setIndex(index);
		setValue(value);
	}
	
	public SimpleBean(String value)
	{
		super();		
		this.setValue(value);
	}
/*
	static final Comparator<SimpleBean> NAME_ORDER = new Comparator<SimpleBean>() 
    {
		public int compare(SimpleBean e1, SimpleBean e2) 
		{
			return e2.getName().compareTo(e1.getName());
		}
    };
    
	static final Comparator<SimpleBean> INDEX_ORDER = new Comparator<SimpleBean>() 
    {
		public int compare(SimpleBean e1, SimpleBean e2) 
		{
			return e2.getIndex().compareTo(e1.getIndex());
		}		
    };    
*/	
	public Integer getIndex()
	{
		return index;
	}

	public void setIndex(Integer index)
	{
		this.index = index;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
	
/*	public int compareTo(SimpleBean o)
	{
		// TODO Auto-generated method stub
		return this.compareTo(o);
	}

	public void setIndexAndName(Integer index, String name)
	{		
		this.index = index;
		this.name = name;
	}	*/
}
