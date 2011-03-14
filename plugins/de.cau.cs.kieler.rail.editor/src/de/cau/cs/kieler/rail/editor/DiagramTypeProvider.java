/**
 *
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Diagram type provider used to initialize the diagram editor.
 * @author Hauke
 *
 */
public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

	/**
	 * The tool Behavior Providers
	 */
	private IToolBehaviorProvider[] toolBehaviorProviders;

	/**
	 * the constructor for DiagramTypeProvider creates a FeatureProvider
	 */
	public DiagramTypeProvider() {
	    super();
	    setFeatureProvider(new FeatureProvider(this));
	}
	/**
     * {@inheritDoc}
     */
    @Override
	public final IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
        if (toolBehaviorProviders == null) {
            toolBehaviorProviders =
            new IToolBehaviorProvider[] {new ToolBehaviorProvider(this)};
        }
        return toolBehaviorProviders;
    }
}
