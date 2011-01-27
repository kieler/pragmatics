/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
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
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.StyleProvider;

/**
 * @author hdw
 * 
 */
public class AddVertexFeature extends AbstractAddFeature {

    /** the style provider. */
    protected IStyleProvider styleProvider;

    protected static final IColorConstant CLASS_TEXT_FOREGROUND =
            new ColorConstant(51, 51, 153);

    protected static final IColorConstant CLASS_FOREGROUND = new ColorConstant(
            255, 102, 0);

    protected static final IColorConstant CLASS_BACKGROUND = new ColorConstant(
            255, 204, 153);

    private static final int PORT_SIZE = 10;

    private TypeFeatures type;

    public AddVertexFeature(final IFeatureProvider fp,
            final IStyleProvider thestyleProvider, final TypeFeatures type) {
        super(fp);
        this.styleProvider = thestyleProvider;
        this.type = type;
    }

    /* (non-Javadoc)
     * @see org.eclipse.graphiti.func.IAdd#canAdd(org.eclipse.graphiti.features.context.IAddContext)
     */
    public boolean canAdd(final IAddContext context) {
        // check if user wants to add a the right vertex
        if (isInstanceof(context.getNewObject())) {
            // check if user wants to add to a diagram
            if (context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add the right vertex
     * 
     * @see org.eclipse.graphiti.func.IAdd#add(org.eclipse.graphiti.features.context.IAddContext)
     */
    public PictogramElement add(final IAddContext context) {
        PictogramElement pe = null;
        switch (type) {
        case BREANCH:
            pe = addBreach(context);
            break;
        case DEADENDVERTEX:
            pe = addDeadEndVertex(context);
            break;
        case SWITCHVERTEX_LEFT:
            pe = addSwitchVertex(context, EOrientation.LINKS);
            break;
        case SWITCHVERTEX_RIGHT:
            pe = addSwitchVertex(context, EOrientation.RECHTS);
            break;
        }
        layoutPictogramElement(pe);
        return pe;
    }

    /**
     * Checks if object is a instance of the right vertex with the information
     * in the type variable
     * 
     * @param object
     * @return
     */
    public boolean isInstanceof(final Object object) {
        switch (type) {
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

    /**
     * PictogramElement for the breach vertex
     * 
     * @param context
     * @return
     */
    private PictogramElement addBreach(final IAddContext context) {
        Einbruchsknoten einbruchsknoten =
                (Einbruchsknoten) context.getNewObject();
        Diagram targetDiagram = (Diagram) context.getTargetContainer();

        // CONTAINER SHAPE WITH CIRCLE
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape =
                peCreateService.createContainerShape(targetDiagram, true);

        // define a default size for the shape
        int width = 50;
        int height = 50;
        IGaService gaService = Graphiti.getGaService();

        System.out.println(context.getHeight());
        System.out.println(context.getWidth());

        Ellipse ellipse;
        {
            // Create Ellipse
            ellipse = gaService.createEllipse(containerShape);
            ellipse.setLineWidth(3);
            ellipse.setFilled(false);
            ellipse.setForeground(manageColor(0, 0, 0));
            ellipse.setBackground(manageColor(255, 255, 255));

            gaService.setLocationAndSize(ellipse, context.getX(),
                    context.getY() + 10, width, height - 10);

            // if added Class has no resource we add it to the resource
            // of the diagram
            // in a real scenario the business model would have its own resource
            if (einbruchsknoten.eResource() == null) {
                getDiagram().eResource().getContents().add(einbruchsknoten);
            }
            // create link and wire it
            link(containerShape, einbruchsknoten);
        }

        // SHAPE WITH TEXT
        {
            // create shape for text
            Shape shape = peCreateService.createShape(containerShape, false);

            // create and set text graphics algorithm
            // Compromise only
            String ans = einbruchsknoten.getName();
            // only ask for name if none is set already
            if (ans == null) {
                ans = ExampleUtil.askString("", "Enter Label", "");
                // ans = JOptionPane.showInputDialog(null, "Enter Label");
                einbruchsknoten.setName(ans); // TODO ???
            }
            Text text =
                    gaService.createDefaultText(shape,
                            einbruchsknoten.getName()); // addedClass.getName()
            text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
            text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
            text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
            text.getFont().setBold(true);
            gaService.setLocationAndSize(text, 0, 0, width, 20);

            // for the Layouter
            Property properPort = MmFactory.eINSTANCE.createProperty();
            properPort.setKey("layout:de.cau.cs.kieler.klay.rail.portType");
            properPort.setValue("STUMP");
            containerShape.getProperties().add(properPort);

            // PORT
            Port port = einbruchsknoten.getPorts().get(0);
            final BoxRelativeAnchor boxAnchor =
                    peCreateService.createBoxRelativeAnchor(containerShape);
            boxAnchor.setActive(true);
            boxAnchor.setRelativeHeight(0.5);
            boxAnchor.setRelativeWidth(0.5);
            boxAnchor.setReferencedGraphicsAlgorithm(ellipse);
            Rectangle rec = gaService.createRectangle(boxAnchor);
            rec.setStyle(styleProvider.getStyle(StyleProvider.PORT));

            gaService.setLocationAndSize(rec, 0, 0, PORT_SIZE, PORT_SIZE);

            link(boxAnchor, port);
            // PORT

            // create link and wire it
            link(shape, einbruchsknoten);
        }

        // add a chopbox anchor to the shape
        peCreateService.createChopboxAnchor(containerShape);

        // call the layout feature
        layoutPictogramElement(containerShape);

        return containerShape;
    }

    /**
     * PictogramElement for the deadend vertex
     * 
     * @param context
     * @return
     */
    // BREACH_OR_CLOSE
    private PictogramElement addDeadEndVertex(final IAddContext context) {
        Stumpfgleisknoten addedClass =
                (Stumpfgleisknoten) context.getNewObject();
        Diagram targetDiagram = (Diagram) context.getTargetContainer();

        // CONTAINER SHAPE WITH ROUNDED RECTANGLE
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape =
                peCreateService.createContainerShape(targetDiagram, true);
        peCreateService.createChopboxAnchor(containerShape);

        // define a default size for the shape
        // TODO make constants (50)
        int width = context.getWidth() != 50 ? 50 : context.getWidth();
        int height = context.getHeight() != 50 ? 50 : context.getHeight();
        IGaService gaService = Graphiti.getGaService();

        Rectangle portContainer =
                gaService.createInvisibleRectangle(containerShape);

        gaService.setLocationAndSize(portContainer, context.getX(),
                context.getY(), width, height);
        {

            Rectangle rectangleShape = gaService.createRectangle(portContainer);

            // if added Class has no resource we add it to the resource
            // of the diagram
            // in a real scenario the business model would have its own resource
            if (addedClass.eResource() == null) {
                getDiagram().eResource().getContents().add(addedClass);
            }
            // create link and wire it
            // link(containerShape, addedClass);
        }

        // SHAPE WITH LINE
        {
            // create shape for line
            Shape shape = peCreateService.createShape(containerShape, false);

            // create and set graphics algorithm
            Polyline polyline =
                    gaService.createPolyline(shape, new int[] { width / 2, 0,
                            width / 2, height });
            polyline.setStyle(styleProvider
                    .getStyle(StyleProvider.DEFAULT_STYLE));
            // gaService.setLocationAndSize(polyline,width/2, 0, width/2,
            // height);
        }

        // SHAPE WITH TEXT
        {
            // create shape for text
            Shape shapeLabel =
                    peCreateService.createShape(containerShape, false);

            // create and set text graphics algorithm
            addedClass.setName("Test");
            Text text =
                    gaService.createDefaultText(shapeLabel,
                            addedClass.getName());
            text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
            text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
            text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
            text.getFont().setBold(true);
            gaService.setLocationAndSize(text, 0, 0, width, 20);

            // create link and wire it
            link(shapeLabel, addedClass);

            /*


            //TODO ???? don't know if this is necessary 
            // set container shape for direct editing after object creation
            IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
            directEditingInfo.setMainPictogramElement(containerShape);
            // set shape and graphics algorithm where the editor for
            // direct editing shall be opened after object creation
            directEditingInfo.setPictogramElement(shapeLabel);
            directEditingInfo.setGraphicsAlgorithm(text);
            
            */

        }

        // TODO maybe to delete (at the other places maybe too)
        // add a chopbox anchor to the shape
        peCreateService.createChopboxAnchor(containerShape);

        // TODO maybe kick out
        // call the layout feature
        layoutPictogramElement(containerShape);

        return containerShape;
    }

    private PictogramElement addSwitchVertex(final IAddContext context,
            final EOrientation orientatin) {
        // create Switch from source
        Weichenknoten switchVertex = (Weichenknoten) context.getNewObject();
        switchVertex.setAbzweigendeLage(orientatin);

        Diagram targetDiagram = (Diagram) context.getTargetContainer();

        // CONTAINER SHAPE
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape =
                peCreateService.createContainerShape(targetDiagram, true);

        // for the Layouter
        Property properOrientatin = MmFactory.eINSTANCE.createProperty();
        properOrientatin.setKey("layout:de.cau.cs.kieler.klay.rail.nodeType");
        switch (orientatin) {
        case LINKS:
            properOrientatin.setValue("SWITCH_LEFT");
            break;
        case RECHTS:
            properOrientatin.setValue("SWITCH_RIGHT");
            break;
        }
        containerShape.getProperties().add(properOrientatin);

        IGaService gaService = Graphiti.getGaService();

        // virtual Rectangle
        Rectangle R = gaService.createRectangle(containerShape);
        R.setStyle(styleProvider.getStyle(StyleProvider.DEFAULT_STYLE));
        R.setForeground(manageColor(255, 255, 255));

        // PORT
        for (Port port : switchVertex.getPorts()) {
            final BoxRelativeAnchor boxAnchor =
                    peCreateService.createBoxRelativeAnchor(containerShape);
            boxAnchor.setActive(true);

            double portWidth = PORT_SIZE / 50;

            Property properPort = MmFactory.eINSTANCE.createProperty();
            properPort.setKey("layout:de.cau.cs.kieler.klay.rail.portType");

            boxAnchor.setRelativeHeight(0.4);// (0.5-portWidth);
            switch (port.getName()) {
            case SPITZE:
                boxAnchor.setRelativeWidth(0.0);
                properPort.setValue("STUMP");
                break;
            case STAMM:
                boxAnchor.setRelativeWidth(0.85);
                properPort.setValue("STRAIGHT");
                break;
            case ABZWEIG:
                properPort.setValue("BRANCH");
                if (orientatin == EOrientation.LINKS) {
                    boxAnchor.setRelativeWidth(0.8);
                } else {
                    boxAnchor.setRelativeWidth(0.2);
                }
                boxAnchor.setRelativeHeight(0.0);
            }
            containerShape.getProperties().add(properPort);

            boxAnchor.setReferencedGraphicsAlgorithm(R);

            Rectangle rec = gaService.createRectangle(boxAnchor);
            rec.setStyle(styleProvider.getStyle(StyleProvider.PORT));

            gaService.setLocationAndSize(rec, 0, 0, PORT_SIZE, PORT_SIZE);

            link(boxAnchor, port);
        }
        // PORT

        // Line (straight line)
        Shape shapep = peCreateService.createShape(containerShape, false);
        Polyline polyline =
                gaService.createPolyline(shapep, new int[] { 0, 25, 25, 25 });

        // Line (30°)
        Shape shapep30 = peCreateService.createShape(containerShape, false);
        Polyline polyline30 =
                gaService.createPolyline(shapep30, new int[] { 20, 25, 0,
                        (int) (25 * 0.577350269) });

        Shape shape = peCreateService.createShape(containerShape, false);

        switchVertex.setName("");
        Text text = gaService.createDefaultText(shape, switchVertex.getName());
        text.setStyle(styleProvider.getStyle(StyleProvider.DEFAULT_STYLE));
        text.setX(context.getX());
        text.setY(context.getY());

        layoutPictogramElement(containerShape);

        gaService.setLocationAndSize(containerShape.getGraphicsAlgorithm(),
                context.getX(), context.getY(), 50, 50);
        link(containerShape, switchVertex);

        updatePictogramElement(containerShape);

        return containerShape;
    }

    /**
     * Create a port that is bound to an vertex's boundary.
     * 
     * @param container
     *            the container shape of the parent entity
     * @param x
     *            the x position
     * @param y
     *            the y position
     * @return a new PictogramElement for the port
     */
    private PictogramElement createBoundPort(final ContainerShape container,
            final int x, final int y) {
        int nodeWidth = container.getGraphicsAlgorithm().getWidth();
        int nodeHeight = container.getGraphicsAlgorithm().getHeight();
        float widthPercent = (float) x / nodeWidth;
        float heightPercent = (float) y / nodeHeight;

        // TODO DEBUG
        System.out.println(widthPercent);
        System.out.println(nodeWidth);
        System.out.println(heightPercent);
        System.out.println(nodeHeight);

        if (widthPercent + heightPercent <= 1
                && widthPercent - heightPercent <= 0) {
            // port is put to the left
            widthPercent = 0;
        } else if (widthPercent + heightPercent >= 1
                && widthPercent - heightPercent >= 0) {
            // port is put to the right
            widthPercent = 1;
        } else if (heightPercent < 1.0f / 2) {
            // port is put to the top
            heightPercent = 0;
        } else {
            // port is put to the bottom
            heightPercent = 1;
        }

        // TODO DEBUG
        heightPercent = 1;
        heightPercent = 1;

        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        BoxRelativeAnchor boxAnchor =
                peCreateService.createBoxRelativeAnchor(container);
        boxAnchor.setRelativeWidth(widthPercent);
        boxAnchor.setRelativeHeight(heightPercent);
        boxAnchor.setActive(true);

        IGaService gaService = Graphiti.getGaService();
        // look for the actual rectangle that represents the parent entity
        for (GraphicsAlgorithm ga : container.getGraphicsAlgorithm()
                .getGraphicsAlgorithmChildren()) {
            if (ga instanceof Rectangle) {
                boxAnchor.setReferencedGraphicsAlgorithm(ga);
                break;
            }
        }

        Rectangle rectangleShape = gaService.createRectangle(boxAnchor);
        rectangleShape.setStyle(styleProvider
                .getStyle(StyleProvider.SOLID_STYLE));
        gaService.setLocationAndSize(rectangleShape, -PORT_SIZE, -PORT_SIZE,
                PORT_SIZE, PORT_SIZE);

        return boxAnchor;
    }
}
