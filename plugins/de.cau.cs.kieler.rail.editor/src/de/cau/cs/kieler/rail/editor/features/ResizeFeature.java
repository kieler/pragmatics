/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.SpecializedVerticesFactory;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;

/**
 * @author hdw
 *
 */
public class ResizeFeature extends DefaultResizeShapeFeature {
   
    private TypeFeatures type;

	public ResizeFeature(IFeatureProvider fp, TypeFeatures type) {
        super(fp);
        this.type = type;
    }
 
    @Override
    public boolean canResizeShape(IResizeShapeContext context) {
        boolean canResize = super.canResizeShape(context);
 
        // perform further check only if move allowed by default feature
        if (canResize) {
            // don't allow resize if the class name has the length of 1
            Shape shape = context.getShape();
            Object bo = getBusinessObjectForPictogramElement(shape);
            if (isInstanceof(bo)) {  //TODO Don't know if his is correct.
                Vertex c = (Vertex) bo;
                //TODO How does it work?
                if (c.getName() != null && c.getName().length() == 1) {
                    canResize = false;
                }
            }
        }
        return canResize;
    }
	public boolean isInstanceof(Object object){
		switch (type){
		case BREANCH:
			return object instanceof Einbruchsknoten;
		case DEADENDVERTEX:
			return object instanceof Stumpfgleisknoten;
		case SWITCHVERTEX_LEFT:
		case SWITCHVERTEX_RIGHT:
			return object instanceof Weichenknoten;
		}
		return false;
	}
	
    private Vertex getVertex()
    {
    	Vertex vertex;
		switch(type){
    	case BREANCH:
        	return SpecializedVerticesFactory.eINSTANCE.createEinbruchsknoten();
    	case DEADENDVERTEX:
    		return SpecializedVerticesFactory.eINSTANCE.createStumpfgleisknoten();
    	//TODO Make for both cases possible.
    	case SWITCHVERTEX_LEFT:
    		vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		((Weichenknoten)vertex).setAbzweigendeLage(EOrientation.LINKS);
    		return vertex;
    	case SWITCHVERTEX_RIGHT:
    		vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		((Weichenknoten)vertex).setAbzweigendeLage(EOrientation.RECHTS);
    		return vertex;
		}
    	return null;
    }
}
