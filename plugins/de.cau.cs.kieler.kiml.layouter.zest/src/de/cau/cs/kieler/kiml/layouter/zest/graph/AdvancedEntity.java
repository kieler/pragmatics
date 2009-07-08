package de.cau.cs.kieler.kiml.layouter.zest.graph;

import org.eclipse.zest.layouts.exampleStructures.SimpleNode;

/**
 * Implementation of the LayoutEntity interface in the Zest layout package.
 * 
 * @author msp
 */
public class AdvancedEntity extends SimpleNode {

	/**
	 * Initializes the entity.
	 * 
	 * @param realObject reference to the corresponding layout node in the layout graph
	 * @param x current horizontal position
	 * @param y current vertical position
	 * @param width current width
	 * @param height current height
	 */
	public AdvancedEntity(Object realObject, double x, double y, double width,
			double height) {
		super(realObject, x, y, width, height);
	}

}
