/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

/**
 * @author Hauke
 *
 */
public class DiagramTypeProvider extends AbstractDiagramTypeProvider {
	public DiagramTypeProvider() {
	    super();
	    setFeatureProvider(new FeatureProvider(this));
	}
}
