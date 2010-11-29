/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

/**
 * @author Hauke
 *
 */
public class FeatureProvider extends DefaultFeatureProvider {

	/**
	 * 
	 */
	public FeatureProvider( IDiagramTypeProvider dtp) {
        super(dtp);
    }
	
    @Override
    public IAddFeature getAddFeature(IAddContext context) {
        // is object for add request a EClass?
        if (context.getNewObject() instanceof EClass) {
            return new AddEClassFeature(this);
        }
        return super.getAddFeature(context);
    }


    @Override
    public ICreateFeature[] getCreateFeatures() {
        return new ICreateFeature[] { new CreateEClassFeature(this) };
    }
}
