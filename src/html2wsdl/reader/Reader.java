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
		
		  if (stub.getOrder().length() > stubBefore.getOrder().length())
		  {
			  stubBefore.addChild(stub);
		  }
		  else if (stub.getOrder().length() == stubBefore.getOrder().length()) 
		  {
			  // root.flattened().filter(tag -> tag.getChildren().contains(stubParent)).findFirst();
			  Tag grandPa = stubBefore.findFather(tagList);		    		  
			  grandPa.addChild(stub);
		  }
		  else // stub.getOrder().length() < stubBefore.getOrder().length()
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
