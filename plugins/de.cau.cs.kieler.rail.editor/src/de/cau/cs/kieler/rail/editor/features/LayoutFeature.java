/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;

/**
 * @author hdw
 *
 */
public class LayoutFeature extends AbstractLayoutFeature {
 
    private static final int MIN_HEIGHT = 50;
 
    private static final int MIN_WIDTH = 50;
    
    
    private int height = 50;
    private int width = 50;
    
    TypeFeatures type;
 
    public LayoutFeature(IFeatureProvider fp ,TypeFeatures type, int height, int width) {
        super(fp);
        this.type = type;
        this.height = height;
        this.width = width;
    }
 
    public boolean canLayout(ILayoutContext context) {
       // return true, if pictogram element is linked to an EClass
       PictogramElement pe = context.getPictogramElement();
       if (!(pe instanceof ContainerShape))
           return false;
       EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
       
       //((ContainerShape)pe).getGraphicsAlgorithm().setHeight(50);
       //((ContainerShape)pe).getGraphicsAlgorithm().setWidth(50);
       
       return businessObjects.size() == 1
              && isInstanceof(businessObjects.get(0));
    }
 
    public boolean layout(ILayoutContext context) {
        boolean anythingChanged = false;
        ContainerShape containerShape =
            (ContainerShape) context.getPictogramElement();
        GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
 
        // height
        if (containerGa.getHeight() != height) {
            containerGa.setHeight(height);
            anythingChanged = true;
        }
 
        // width
        if (containerGa.getWidth() != width) {
            containerGa.setWidth(width);
            anythingChanged = true;
        }
 
        int containerWidth = containerGa.getWidth();
        Iterator iter = containerShape.getChildren().iterator();
        while (iter.hasNext()) {
            Shape shape = (Shape) iter.next();
            GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
            IGaService gaService = Graphiti.getGaService();
            IDimension size =
                 gaService.calculateSize(graphicsAlgorithm);
            if (containerWidth != size.getWidth()) {
                if (graphicsAlgorithm instanceof Polyline) {
                    Polyline polyline = (Polyline) graphicsAlgorithm;
                    Point secondPoint = polyline.getPoints().get(1);
                    Point newSecondPoint =
                        gaService.createPoint(containerWidth, secondPoint.getY());
                    polyline.getPoints().set(1, newSecondPoint);
                    anythingChanged = true;
                } else {
                    gaService.setWidth(graphicsAlgorithm,
                        containerWidth);
                    anythingChanged = true;
                }
            }
        }
        return anythingChanged;
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
    
}
