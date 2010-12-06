/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;

/**
 * @author hdw
 *
 */
public class AddDeadEndVertex extends AbstractAddShapeFeature {

    private static final IColorConstant CLASS_TEXT_FOREGROUND =
        new ColorConstant(51, 51, 153);
	
	public AddDeadEndVertex(IFeatureProvider fp) {
		super(fp);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.IAdd#canAdd(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a Stumpfgleisknoten
        if (context.getNewObject() instanceof Stumpfgleisknoten) {
            // check if user wants to add to a diagram
            if (context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.IAdd#add(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public PictogramElement add(IAddContext context) {
		Stumpfgleisknoten addedClass = (Stumpfgleisknoten) context.getNewObject();
        Diagram targetDiagram = (Diagram) context.getTargetContainer();
 
        // CONTAINER SHAPE WITH ROUNDED RECTANGLE
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape =
             peCreateService.createContainerShape(targetDiagram, true);
 
        // define a default size for the shape
        int width = 50;
        int height = 50; 
        IGaService gaService = Graphiti.getGaService();
 
        {
        	// if added Clas has no resource we add it to the resource 
            // of the diagram
            // in a real scenario the business model would have its own resource
            if (addedClass.eResource() == null) {
                     getDiagram().eResource().getContents().add(addedClass);
            }
            // create link and wire it
            link(containerShape, addedClass);
        	
        	
        	
        	/*
            // create and set graphics algorithm
            RoundedRectangle roundedRectangle =
                gaService.createRoundedRectangle(containerShape, 5, 5);
            roundedRectangle.setForeground(manageColor(CLASS_FOREGROUND));
            roundedRectangle.setBackground(manageColor(CLASS_BACKGROUND));
            roundedRectangle.setLineWidth(2);
            
            gaService.setLocationAndSize(roundedRectangle,
                    context.getX(), context.getY(), width, height);*/
            
            //NEU
        	{
        	Shape shape = peCreateService.createShape(containerShape, false);
        	Polyline polyline =
                gaService.createPolyline(shape, new int[] { width/2, 0, width/2, height });
        	polyline.setForeground(manageColor(0,0,0));
            polyline.setLineWidth(2);
            
        	}
        	
        	/*
            Ellipse ellipse = gaService.createEllipse( containerShape);
            ellipse.setLineWidth(3);
            ellipse.setFilled(false);
            ellipse.setForeground(manageColor(0,0,0));
            ellipse.setBackground(manageColor(255,255,255));
            
            gaService.setLocationAndSize(ellipse,
                    context.getX(), context.getY()+10, width, height-10);*/
            
            //NEU
            
            
 
         
        }
 
        
        
        /*
        // SHAPE WITH LINE
        {
            // create shape for line
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set graphics algorithm
            Polyline polyline =
                gaService.createPolyline(shape, new int[] { 0, 20, width, 20 });
            polyline.setForeground(manageColor(CLASS_FOREGROUND));
            polyline.setLineWidth(2);
        }*/
 
        // SHAPE WITH TEXT
        {
            // create shape for text
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set text graphics algorithm
            Text text = gaService.createDefaultText(shape, addedClass.getName());
            text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
            text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
            text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
            text.getFont().setBold(true);
            gaService.setLocationAndSize(text, 0, 0, width, 20);
 
            // create link and wire it
            link(shape, addedClass);
        }
        
        // add a chopbox anchor to the shape
        peCreateService.createChopboxAnchor(containerShape);
  
        // call the layout feature
        layoutPictogramElement(containerShape);
        
 
        return containerShape;
	}

}
