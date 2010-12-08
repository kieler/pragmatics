/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
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

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.impl.WeichenknotenImpl;

/**
 * @author hdw
 * @param <TypeFeatures>
 *
 */
public class AddFeature extends AbstractAddFeature {

	/** the style provider. */ 
    protected IStyleProvider styleProvider;
    
    protected static final IColorConstant CLASS_TEXT_FOREGROUND =
        new ColorConstant(51, 51, 153);
 
    protected static final IColorConstant CLASS_FOREGROUND =
        new ColorConstant(255, 102, 0);
 
    protected static final IColorConstant CLASS_BACKGROUND =
        new ColorConstant(255, 204, 153);
    
    TypeFeatures type;
    
	public AddFeature(IFeatureProvider fp, final IStyleProvider thestyleProvider, TypeFeatures type) {
		super(fp);
		this.styleProvider = thestyleProvider;
		this.type = type;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.IAdd#canAdd(org.eclipse.graphiti.features.context.IAddContext)
	 */
	//public abstract boolean canAdd(IAddContext context);
    public boolean canAdd(IAddContext context) {
        // check if user wants to add a Einbruchsknoten
        if (isInstanceof(context.getNewObject())) {
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
	public  PictogramElement add(IAddContext context){
		switch (type){
		case BREANCH:
			return addBreach(context);
		case DEADENDVERTEX:
			return addDeadEndVertex(context);
		case SWITCHVERTEX_LEFT:
			return addSwitchVertex(context,EOrientation.LINKS);
		case SWITCHVERTEX_RIGHT:
			return addSwitchVertex(context,EOrientation.RECHTS);
		}
		return null;
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
	
	private PictogramElement addBreach(IAddContext context){
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
	private PictogramElement addDeadEndVertex(IAddContext context){
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
        }
 
        
        
        
        // SHAPE WITH LINE
        {
            // create shape for line
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set graphics algorithm
            Polyline polyline =
                gaService.createPolyline(shape, new int[] { width/2, 0, width/2, height });
            polyline.setForeground(manageColor(100,0,100));
            polyline.setLineWidth(2);
            polyline.setHeight(height);
            polyline.setWidth(width);
            polyline.setX(width/2);
            polyline.setY(0);
            gaService.setLocationAndSize(polyline,width/2, 0, width/2, height);
        }
        
        
     // triangle through points: top-middle, bottom-right, bottom-left
        //50, 0, 100, 100, 0, 100
        {
        int xy[] = new int[] { width/2, 0, width/2, height };
		//IGaService gaService = Graphiti.getGaService();
        Polyline p = gaService.createPolyline(containerShape, xy);
        }
 
        // SHAPE WITH TEXT
        {
            // create shape for text
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set text graphics algorithm
            addedClass.setName("Test");
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
	
	private PictogramElement addSwitchVertex(IAddContext context, EOrientation links) {
		Weichenknoten addedClass = (Weichenknoten) context.getNewObject();
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
        }
 
        
        
        
        // SHAPE WITH LINE
        {
            // create shape for line
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set graphics algorithm
            Polyline polyline =
                gaService.createPolyline(shape, new int[] { width/2, 0, width/2, height });
            polyline.setForeground(manageColor(100,0,100));
            polyline.setLineWidth(2);
            polyline.setHeight(height);
            polyline.setWidth(width);
            polyline.setX(width/2);
            polyline.setY(0);
            gaService.setLocationAndSize(polyline,width/2, 0, width/2, height);
        }
        
        
     // triangle through points: top-middle, bottom-right, bottom-left
        //50, 0, 100, 100, 0, 100
        {
        int xy[] = new int[] { 0, height/2, width, height /2};
		//IGaService gaService = Graphiti.getGaService();
        Polyline p = gaService.createPolyline(containerShape, xy);
        Polyline p2 = gaService.createPolyline(containerShape, new int[] { 0, height/2, width, (int)(0.57735026919*height /2)});
        }
 
        // SHAPE WITH TEXT
        {
            // create shape for text
            Shape shape = peCreateService.createShape(containerShape, false);
 
            // create and set text graphics algorithm
            addedClass.setName("Test");
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
