package html2wsdl.reader;

import java.util.List;

import html2wsdl.vo.Tag;

public abstract class Reader {

	protected static void displayNodes(Tag stub, String str) {
		
		System.out.println(str+stub.getName());
		
		for (Tag child: stub.getChildren())
		{
			displayNodes(child, str+" ");
		}		
	}

	/**
	 * @param tagList
	 * @param stubBefore
	 * @param stub
	 * @param type
	 * @return
	 */
	protected Tag handleOrder(List<Tag> tagList, Tag stubBefore, Tag stub, String type) {
		
		  /*  stub > stubBefore
		   * example 2.2.1 > 2.2
		   */
		int stubLength = stub.getOrder().length();
		int stubBeforeLength = stubBefore.getOrder().length();
		
		if (stubLength == stubBeforeLength + 2)
		  {
			  stubBefore.addChild(stub);
		  }   
		  else if (stubLength == stubBeforeLength ||  
				  stubLength == stubBeforeLength + 1) // example 2.2.10 > 2.2.9
		  {
			  // root.flattened().filter(tag -> tag.getChildren().contains(stubParent)).findFirst();
			  Tag grandPa = stubBefore.findFather(tagList);		    		  
			  grandPa.addChild(stub);
		  }
		  else // stub < stubBefore
		  {		
			  // TODO maybe sibling search is more precise comparing order and/or reuse grandPa above
			  Tag grandGrandPa = stubBefore.findFather(tagList).findFather(tagList);		    		 
			  grandGrandPa.addChild(stub);
		  }
		  
		stub.setType(type);
		stubBefore = stub;
		return stubBefore;
	}
}
