package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.editor.StyleProvider;

public class AddPortFeature extends AbstractAddShapeFeature{
	/** the default size of ports. */
    public static final int PORT_SIZE = 10;

    
    /** the style provider. */ 
    private IStyleProvider styleProvider;

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     * @param sp the style provider
     */
    public AddPortFeature(final IFeatureProvider fp, final IStyleProvider sp) {
        super(fp);
        this.styleProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        return (context.getNewObject() instanceof Port);
    }
    
    /**
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        PictogramElement newPort = null;
        if (!(context.getTargetContainer() instanceof Diagram)) {
            newPort = createBoundPort(context.getTargetContainer(),
                    context.getX(), context.getY());
            link(newPort, context.getNewObject());
        }
        return newPort;
    }
    
    /**
     * Create a port that is bound to an vertex's boundary.
     * 
     * @param container the container shape of the parent entity
     * @param x the x position
     * @param y the y position
     * @return a new PictogramElement for the port
     */
    private PictogramElement createBoundPort(final ContainerShape container,
            final int x, final int y) {
        int nodeWidth = container.getGraphicsAlgorithm().getWidth();
        int nodeHeight = container.getGraphicsAlgorithm().getHeight();
        float widthPercent = (float) x / nodeWidth;
        float heightPercent = (float) y / nodeHeight;
        
        //TODO DEBUG
        System.out.println(widthPercent);
        System.out.println(nodeWidth);
        System.out.println(heightPercent);
        System.out.println(nodeHeight);
        
        
        if (widthPercent + heightPercent <= 1 && widthPercent - heightPercent <= 0) {
            // port is put to the left
            widthPercent = 0;
        } else if (widthPercent + heightPercent >= 1 && widthPercent - heightPercent >= 0) {
            // port is put to the right
            widthPercent = 1;
        } else if (heightPercent < 1.0f / 2) {
            // port is put to the top
            heightPercent = 0;
        } else {
            // port is put to the bottom
            heightPercent = 1;
        }

        //TODO DEBUG
        heightPercent = 1;
        heightPercent = 1;
        
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        BoxRelativeAnchor boxAnchor = peCreateService.createBoxRelativeAnchor(container);
        boxAnchor.setRelativeWidth(widthPercent);
        boxAnchor.setRelativeHeight(heightPercent);
        boxAnchor.setActive(true);

        IGaService gaService = Graphiti.getGaService();
        // look for the actual rectangle that represents the parent entity
        for (GraphicsAlgorithm ga : container.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
            if (ga instanceof Rectangle) {
                boxAnchor.setReferencedGraphicsAlgorithm(ga);
                break;
            }
        }

        Rectangle rectangleShape = gaService.createRectangle(boxAnchor);
        rectangleShape.setStyle(styleProvider.getStyle(StyleProvider.SOLID_STYLE));
        gaService.setLocationAndSize(rectangleShape, -PORT_SIZE , -PORT_SIZE,
                PORT_SIZE, PORT_SIZE);

        return boxAnchor;
    }
}
