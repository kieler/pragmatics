/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.rail.Topologie.Model;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.SpecializedVerticesFactory;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;
import de.cau.cs.kieler.rail.editor.KrailDiagramEditor;

/**
 * @author hdw
 *
 */
public class CreateDeadEndVertex extends AbstractCreateFeature {

	/**
	 * @param fp
	 * @param name
	 * @param description
	 */
	public CreateDeadEndVertex(IFeatureProvider fp) {
		super(fp, "Stumpfgleis", "Stumpfgleis erstellen");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreate#canCreate(org.eclipse.graphiti.features.context.ICreateContext)
	 */
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreate#create(org.eclipse.graphiti.features.context.ICreateContext)
	 */
	public Object[] create(ICreateContext context) {
		// ask user for Stumpfgleisknoten name
        Stumpfgleisknoten vertex = SpecializedVerticesFactory.eINSTANCE.createStumpfgleisknoten();
        Model model = ((KrailDiagramEditor) getDiagramEditor()).fetchModel(context.getTargetContainer());
        
        model.getVertices().add(vertex);    
        
        // do the add
        addGraphicalRepresentation(context, vertex);
 
        // return newly created business object(s)
        return new Object[] { vertex };
	}

}
