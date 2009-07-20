package de.cau.cs.kieler.kiml.zest.graph;

import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.exampleStructures.SimpleRelationship;

/**
 * Implementation of the LayoutRelationship interface in the Zest layout package.
 * 
 * @author msp
 */
public class AdvancedRelationship extends SimpleRelationship {

	private Object realObject;
	
	/**
	 * Initializes the relationship.
	 * 
	 * @param realObject reference to the corresponding edge in the layout graph
	 * @param sourceEntity source entity
	 * @param destinationEntity target entity
	 */
	public AdvancedRelationship(Object realObject, LayoutEntity sourceEntity,
			LayoutEntity destinationEntity) {
		super(sourceEntity, destinationEntity, false);
		this.realObject = realObject;
	}
	
	/**
	 * Returns the reference to the corresponding edge in the layout graph.
	 * 
	 * @return edge from the layout graph
	 */
	public Object getRealObject() {
		return realObject;
	}

}
