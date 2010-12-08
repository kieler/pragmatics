package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
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

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;

public class AddBreachFeature extends AddFeature {
    
    public AddBreachFeature(IFeatureProvider fp, final IStyleProvider thestyleProvider) {
        super(fp,thestyleProvider);
    }
 
    public boolean canAdd(IAddContext context) {
        // check if user wants to add a Einbruchsknoten
        if (context.getNewObject() instanceof Einbruchsknoten) {
            // check if user wants to add to a diagram
            if (context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;
    }
 
    public PictogramElement add(IAddContext context) {
        Einbruchsknoten addedClass = (Einbruchsknoten) context.getNewObject();
        Diagram targetDiagram = (Diagram) context.getTargetContainer();
 
        // CONTAINER SHAPE WITH CIRCLE
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape =
             peCreateService.createContainerShape(targetDiagram, true);
 
        
        
        // define a default size for the shape
        int width = 50;
        int height = 50; 
        IGaService gaService = Graphiti.getGaService();
 
        
        
        {
        	//Create Ellipse
            Ellipse ellipse = gaService.createEllipse( containerShape);
            ellipse.setLineWidth(3);
            ellipse.setFilled(false);
            ellipse.setForeground(manageColor(0,0,0));
            ellipse.setBackground(manageColor(255,255,255));
            
            gaService.setLocationAndSize(ellipse,
                    context.getX(), context.getY()+10, width, height-10);
            
            //ellipse.setStyle(styleProvider.getStyle("1"));
            
            
 
            // if added Class has no resource we add it to the resource 
            // of the diagram
            // in a real scenario the business model would have its own resource
            if (addedClass.eResource() == null) {
                     getDiagram().eResource().getContents().add(addedClass);
            }
            // create link and wire it
            link(containerShape, addedClass);
        }
 
        // SHAPE WITH TEXT
        {
            // create shape for text
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set text graphics algorithm
            addedClass.setName("test");  //TODO ???
            Text text = gaService.createDefaultText(shape, addedClass.getName()); //addedClass.getName()
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
