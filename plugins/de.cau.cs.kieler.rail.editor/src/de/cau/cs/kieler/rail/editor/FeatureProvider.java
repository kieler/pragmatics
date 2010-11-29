/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
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
        // is object for add request a EClass or EReference?
        if (context.getNewObject() instanceof EClass) {
            return new AddEClassFeature(this);
        } else if (context.getNewObject() instanceof EReference) {
            return new AddEReferenceFeature(this);
        }
        return super.getAddFeature(context);
    }


    @Override
    public ICreateFeature[] getCreateFeatures() {
        return new ICreateFeature[] { new CreateEClassFeature(this) };
    }
    
    @Override
    public ICreateConnectionFeature[] getCreateConnectionFeatures() {
        return new ICreateConnectionFeature[] {
            new CreateEReferenceFeature (this) };
    }

}
