/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaLayoutService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.rail.editor.Geometry;
import de.cau.cs.kieler.rail.editor.RotationSwitchHandler;
import de.cau.cs.kieler.rail.editor.StyleProvider;
import de.menges.topologie.Topologie.Basegraph.EPort;
import de.menges.topologie.Topologie.Basegraph.Port;
import de.menges.topologie.Topologie.SpecializedVertices.*;
import de.menges.topologie.ui.TopoDSL2InfrastrukturGenerator;

import org.eclipse.core.resources.IResource;

/**
 * @author hdw
 * 
 */
public class AddVertexFeature extends AbstractAddFeature {

	/** the style provider. */
	protected IStyleProvider styleProvider;

	protected static final IColorConstant CLASS_TEXT_FOREGROUND = new ColorConstant(
			51, 51, 153);

	protected static final IColorConstant CLASS_FOREGROUND = new ColorConstant(
			255, 102, 0);

	protected static final IColorConstant CLASS_BACKGROUND = new ColorConstant(
			255, 204, 153);

	private static final int PORT_SIZE = 10;

	private static final int WIDTH_BREACH = 50;
	private static final int HEIGHT_BREACH = 50;

	private static final int WIDTH_DEADEND = 50;
	private static final int HEIGHT_DEADEND = 50;

	private static final int HEIGHT_SWITCH = 50;

	private static final int WIDTH_SWITCH = 50;

	private static final double MIDDLE = 0.5;

	public static final String KLAY_NODETYPE_KEY = "layout:de.cau.cs.kieler.klay.rail.nodeType";

	private static final int BREACH_WIDTH = 40;

	private static final int BREACH_HEIGHT = 40;

	private TypeFeatures type;

	public AddVertexFeature(final IFeatureProvider fp,
			final IStyleProvider thestyleProvider, final TypeFeatures type) {
		super(fp);
		this.styleProvider = thestyleProvider;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IAdd#canAdd(org.eclipse.graphiti.features.context
	 * .IAddContext)
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
     * {@inheritDoc}
     */
	public final PictogramElement add(final IAddContext context) {
		//TopoDSL2InfrastrukturGenerator tD= null;
			//tD = new TopoDSL2InfrastrukturGenerator();
		//TopoDSL2InfrastrukturGenerator.transformModel("/home/hdw/Downloads/lindaunis.topo",
		//		"/home/hdw/Downloads/lindaunis.topo2");
		
		PictogramElement pe = null;
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
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
		default:
			break;
		}

		// peCreateService.createChopboxAnchor((Shape) pe);
		layoutPictogramElement(pe);
		pe.setActive(true);
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
		Einbruchsknoten einbruchsknoten = (Einbruchsknoten) context
				.getNewObject();
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// CONTAINER SHAPE WITH CIRCLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape = peCreateService.createContainerShape(
				targetDiagram, true);

		// define a default size for the shape
		int width = WIDTH_BREACH;
		int height = HEIGHT_BREACH;
		IGaService gaService = Graphiti.getGaService();

		System.out.println(context.getHeight());
		System.out.println(context.getWidth());

		Rectangle rect = gaService.createRectangle(containerShape);
		rect.setStyle(styleProvider.getStyle(StyleProvider.DEFAULT_STYLE));
		rect.setForeground(manageColor(ColorConstant.WHITE));
		Ellipse ellipse;
		{
			// Create Ellipse

			ellipse = gaService.createEllipse(containerShape);

			ellipse.setWidth(BREACH_WIDTH);
			ellipse.setHeight(BREACH_HEIGHT);
			ellipse.setX(5);
			ellipse.setY(10);

			ellipse.setLineWidth(3);
			ellipse.setFilled(true);
			ellipse.setStyle(styleProvider.getStyle(StyleProvider.BREACH));

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
			Shape textShape = peCreateService
					.createShape(containerShape, false);

			// create and set text graphics algorithm
			// Compromise only
			String ans = einbruchsknoten.getName();
			// only ask for name if none is set already
			if (ans == null) {
				ans = ExampleUtil.askString("", "Enter Label", "");
				// ans = JOptionPane.showInputDialog(null, "Enter Label");
				einbruchsknoten.setName(ans); // TODO ???
			}
			Text text = gaService.createDefaultText(textShape,
					einbruchsknoten.getName()); // addedClass.getName()
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 0, 0, width, 20);

			// direct editable
			// set container shape for direct editing after object creation
			IDirectEditingInfo directEditingInfo = getFeatureProvider()
					.getDirectEditingInfo();
			directEditingInfo.setMainPictogramElement(containerShape);
			// set shape and graphics algorithm where the editor for
			// direct editing shall be opened after object creation
			directEditingInfo.setPictogramElement(textShape);
			directEditingInfo.setGraphicsAlgorithm(text);
			// direct editable

			// for the Layouter
			Property properPort = MmFactory.eINSTANCE.createProperty();
			properPort.setKey("layout:de.cau.cs.kieler.klay.rail.portType");
			properPort.setValue("STUMP");
			containerShape.getProperties().add(properPort);

			// PORT
			Port port = einbruchsknoten.getPorts().get(0);
			final BoxRelativeAnchor boxAnchor = peCreateService
					.createBoxRelativeAnchor(containerShape);
			boxAnchor.setActive(true);
			boxAnchor.setRelativeHeight(MIDDLE);
			boxAnchor.setRelativeWidth(MIDDLE);
			boxAnchor.setReferencedGraphicsAlgorithm(ellipse);
			Rectangle rec = gaService.createRectangle(boxAnchor);
			rec.setStyle(styleProvider.getStyle(StyleProvider.BREACH_PORT));

			gaService.setLocationAndSize(rec, -PORT_SIZE / 2, -PORT_SIZE / 2,
					PORT_SIZE, PORT_SIZE);

			link(boxAnchor, port);
			// PORT

			// create link and wire it
			link(textShape, einbruchsknoten);
		}

		// add a chopbox anchor to the shape
		// peCreateService.createChopboxAnchor(containerShape);

		// call the layout feature
		// layoutPictogramElement(containerShape);

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
		Stumpfgleisknoten deadEndVertex = (Stumpfgleisknoten) context
				.getNewObject();
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape = peCreateService.createContainerShape(
				targetDiagram, true);
		peCreateService.createChopboxAnchor(containerShape);

		// define a default size for the shape
		// TODO make constants (50)
		int width = context.getWidth() != WIDTH_DEADEND ? WIDTH_DEADEND
				: context.getWidth();
		int height = context.getHeight() != WIDTH_DEADEND ? WIDTH_DEADEND
				: context.getHeight();
		IGaService gaService = Graphiti.getGaService();

		Rectangle portContainer = gaService
				.createInvisibleRectangle(containerShape);

		gaService.setLocationAndSize(portContainer, context.getX(),
				context.getY(), width, height);
		{

			Rectangle rectangleShape = gaService.createRectangle(portContainer);

			// if added Class has no resource we add it to the resource
			// of the diagram
			// in a real scenario the business model would have its own resource
			if (deadEndVertex.eResource() == null) {
				getDiagram().eResource().getContents().add(deadEndVertex);
			}
			// create link and wire it
			link(containerShape, deadEndVertex);
		}

		// SHAPE WITH LINE
		{
			// create shape for line
			Shape shape = peCreateService.createShape(containerShape, false);

			// create and set graphics algorithm
			Polyline polyline = gaService.createPolyline(shape, new int[] {
					width / 2, 0, width / 2, height });
			polyline.setStyle(styleProvider
					.getStyle(StyleProvider.DEFAULT_STYLE));
			// gaService.setLocationAndSize(polyline,width/2, 0, width/2,
			// height);
		}

		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shapeLabel = peCreateService.createShape(containerShape,
					false);

			// create and set text graphics algorithm
			String ans = deadEndVertex.getName();
			if (ans == null) {
				ans = ExampleUtil.askString("", "Enter Label", "");
				// ans = JOptionPane.showInputDialog(null, "Enter Label");
				deadEndVertex.setName(ans); // TODO ???
			}

			Text text = gaService.createDefaultText(shapeLabel,
					deadEndVertex.getName());
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 0, 0, width, 20);

			// create link and wire it
			link(shapeLabel, deadEndVertex);

			/*
			 * 
			 * 
			 * //TODO ???? don't know if this is necessary // set container
			 * shape for direct editing after object creation IDirectEditingInfo
			 * directEditingInfo = getFeatureProvider().getDirectEditingInfo();
			 * directEditingInfo.setMainPictogramElement(containerShape); // set
			 * shape and graphics algorithm where the editor for // direct
			 * editing shall be opened after object creation
			 * directEditingInfo.setPictogramElement(shapeLabel);
			 * directEditingInfo.setGraphicsAlgorithm(text);
			 */

		}

		// PORT
		Port port = deadEndVertex.getPorts().get(0);
		final BoxRelativeAnchor boxAnchor = peCreateService
				.createBoxRelativeAnchor(containerShape);
		boxAnchor.setActive(true);
		boxAnchor.setRelativeHeight(0.5);
		boxAnchor.setRelativeWidth(0.5);
		boxAnchor.setReferencedGraphicsAlgorithm(portContainer);
		Rectangle rec = gaService.createRectangle(boxAnchor);
		rec.setStyle(styleProvider.getStyle(StyleProvider.PORT));

		gaService.setLocationAndSize(rec, -PORT_SIZE / 2, -PORT_SIZE / 2,
				PORT_SIZE, PORT_SIZE);

		link(boxAnchor, port);
		// PORT

		// TODO maybe to delete (at the other places maybe too)
		// add a chopbox anchor to the shape
		// peCreateService.createChopboxAnchor(containerShape);

		// TODO maybe kick out
		// call the layout feature
		layoutPictogramElement(containerShape);

		return containerShape;
	}

	/**
	 * Creates the Graphic representation for a switch
	 * 
	 * @param context
	 *            The context witch is use to create the pictorgram element
	 * @param orientatin
	 *            left or right switch
	 * @return The pictorgram Element with the switch.
	 */
	private PictogramElement addSwitchVertex(final IAddContext context,
			final EOrientation orientatin) {

		int[] spitzeStammXY = { 0, 0, 0, 0 };
		int[] mitteAbzweigXY = { 25, 25, 0, 0 };

		// create Switch from source
		Weichenknoten switchVertex = (Weichenknoten) context.getNewObject();
		switchVertex.setAbzweigendeLage(orientatin);

		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// CONTAINER SHAPE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape = peCreateService.createContainerShape(
				targetDiagram, true);

		// for the Layouter
		Property properOrientatin = MmFactory.eINSTANCE.createProperty();
		properOrientatin.setKey(KLAY_NODETYPE_KEY);
		switch (orientatin) {
		case LINKS:
			properOrientatin.setValue("SWITCH_LEFT");
			break;
		case RECHTS:
			properOrientatin.setValue("SWITCH_RIGHT");
			break;
		default:
			break;
		}
		containerShape.getProperties().add(properOrientatin);

		IGaService gaService = Graphiti.getGaService();

		// for the angle
		Property proper = MmFactory.eINSTANCE.createProperty();
		proper.setKey(RotationSwitchHandler.MULTIPLEANGLE_KEY);
		proper.setValue("0");
		containerShape.getProperties().add(proper);

		// virtual Rectangle
		Rectangle rect = gaService.createRectangle(containerShape);
		rect.setStyle(styleProvider.getStyle(StyleProvider.DEFAULT_STYLE));
		rect.setForeground(manageColor(255, 255, 255)); // TODO const

		// PORT
		int width = 50;// containerShape.getGraphicsAlgorithm().getWidth();
		int height = 50;// containerShape.getGraphicsAlgorithm().getHeight();

		if (switchVertex.eResource() == null) {
			getDiagram().eResource().getContents().add(switchVertex);
		}

		for (Port port : switchVertex.getPorts()) {
			if (port != null) {
				final BoxRelativeAnchor boxAnchor = peCreateService
						.createBoxRelativeAnchor(containerShape);
				boxAnchor.setActive(true);

				double portWidth = PORT_SIZE / 50;

				// for the Layouter
				Property properPort = MmFactory.eINSTANCE.createProperty();
				properPort.setKey(KLAY_NODETYPE_KEY);

				boxAnchor.setRelativeHeight(0.4);// (0.5-portWidth);

				int boxWidth = PORT_SIZE;
				int boxHeight = PORT_SIZE;

				switch (port.getName()) {
				case SPITZE:
					boxAnchor.setRelativeWidth(0.0);
					properPort.setValue("STUMP");
					spitzeStammXY[0] = (int) (width
							* (boxAnchor.getRelativeWidth()) - boxWidth / 2);
					spitzeStammXY[1] = (int) (height
							* (boxAnchor.getRelativeHeight()) + boxHeight / 2);
					break;
				case STAMM:
					boxAnchor.setRelativeWidth(0.85);
					properPort.setValue("STRAIGHT");
					spitzeStammXY[2] = (int) (width
							* (boxAnchor.getRelativeWidth()) + boxWidth / 2);
					spitzeStammXY[3] = (int) (height
							* (boxAnchor.getRelativeHeight()) + boxHeight / 2);
					break;
				case ABZWEIG:
					properPort.setValue("BRANCH");
					if (orientatin == EOrientation.LINKS) {
						// boxAnchor.setRelativeWidth(0.8);
						boxAnchor.setRelativeWidth(Geometry.getRelativWeight(
								0.5, Geometry.degreeToRad(30), 1.0));
					} else {
						// boxAnchor.setRelativeWidth(0.2);
						boxAnchor.setRelativeWidth(Geometry.getRelativWeight(
								0.5, Geometry.degreeToRad(90 + 30), 1.0));
					}
					boxAnchor.setRelativeHeight(0.0);
					mitteAbzweigXY[2] = (int) (width
							* (boxAnchor.getRelativeWidth()) + boxWidth / 2);
					mitteAbzweigXY[3] = (int) (height
							* (boxAnchor.getRelativeHeight()) + boxHeight / 2);
				}
				boxAnchor.getProperties().add(properPort);

				boxAnchor.setReferencedGraphicsAlgorithm(rect);

				createGraphicalPort(boxAnchor, port.getName());

				link(boxAnchor, port);
			}
		}

		// PORT

		// LINES

		int[][] spitzeStammMitteAbzweigXY = UpdateSwitchFeature
				.getSpitzeStammMitteAbzweigXY(containerShape.getAnchors(),
						height, width, getFeatureProvider());

		// Line (straight line)
		Shape shapep = peCreateService.createShape(containerShape, false);
		// Polyline polyline =
		// gaService.createPolyline(shapep, spitzeStammXY); //{ 0, 25, 25, 25
		// });
		Polyline polyline = gaService.createPolyline(shapep,
				spitzeStammMitteAbzweigXY[0]); // { 0, 25, 25, 25 });
		polyline.setStyle(styleProvider.getStyle(StyleProvider.POLYLINE));

		// Line (30°)
		mitteAbzweigXY[1] = getYFromArray(mitteAbzweigXY, 25);
		Shape shapep30 = peCreateService.createShape(containerShape, false);
		// mitteAbzweigXY
		// Polyline polyline30 =
		// gaService.createPolyline(shapep30,mitteAbzweigXY);//new int[] { 20,
		// 25, 0,
		Polyline polyline30 = gaService.createPolyline(shapep30,
				spitzeStammMitteAbzweigXY[1]);// new int[] { 20, 25, 0,
		polyline30.setStyle(styleProvider.getStyle(StyleProvider.POLYLINE));
		// (int) (25 * 0.577350269) });
		// polyline30.getPoints().get(0).setX(mitteAbzweigXY[0]);

		// triangle
		Shape shapeptriangle = peCreateService.createShape(containerShape,
				false);
		int[] polyXY = new int[] { mitteAbzweigXY[0], mitteAbzweigXY[1], 0, 0,
				0, 0 };

		if (orientatin == EOrientation.LINKS) {
			polyXY[2] = 32;
		} else {
			polyXY[2] = 50 - 32;
		}
		polyXY[3] = getYFromArray(mitteAbzweigXY, polyXY[2]);
		polyXY[4] = polyXY[2];
		polyXY[5] = 25;

		Polygon polygon = gaService.createPolygon(shapeptriangle, polyXY);

		// create shape for text
		Shape shape = peCreateService.createShape(containerShape, false);

		// create and set text graphics algorithm
		// Compromise only
		String ans = switchVertex.getName();
		// only ask for name if none is set already
		if (ans == null) {
			ans = ExampleUtil.askString("", "Enter Label", "");
			// ans = JOptionPane.showInputDialog(null, "Enter Label");
			switchVertex.setName(ans); // TODO ???
		}
		Text text = gaService.createDefaultText(shape, switchVertex.getName()); // addedClass.getName()
		text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		text.getFont().setBold(true);
		gaService.setLocationAndSize(text, 0, 30, width, 20);
		// TEXT

		layoutPictogramElement(containerShape);

		gaService.setLocationAndSize(containerShape.getGraphicsAlgorithm(),
				context.getX(), context.getY(), HEIGHT_SWITCH, WIDTH_SWITCH);

		link(shape, switchVertex);

		// provide information to support direct-editing directly
		// after object creation (must be activated additionally)
		IDirectEditingInfo directEditingInfo = getFeatureProvider()
				.getDirectEditingInfo();
		// set container shape for direct editing after object creation
		directEditingInfo.setMainPictogramElement(containerShape);
		// set shape and graphics algorithm where the editor for
		// direct editing shall be opened after object creation
		directEditingInfo.setPictogramElement(shape);
		directEditingInfo.setGraphicsAlgorithm(text);

		link(containerShape, switchVertex); // containerShape
		if (switchVertex.eResource() == null) {
			getDiagram().eResource().getContents().add(switchVertex);
		}

		RotationSwitchHandler.setMultipleAngle(containerShape,
				getFeatureProvider(), 0, 0.1);

		updatePictogramElement(containerShape);

		return containerShape;
	}

	/**
	 * create the graphical port with a sideffect.
	 * 
	 * @param boxAnchor
	 *            The BoxRelativeAnchor witch is connected with the port.
	 * @param portType
	 *            The port type is imported for the drawing (only White, line,
	 *            etc.)
	 */
	private void createGraphicalPort(final BoxRelativeAnchor boxAnchor,
			final EPort portType) {

		IGaService gaService = Graphiti.getGaService();
		Style style = styleProvider.getStyle();

		switch (portType) {
		case ENDE:
			style = styleProvider.getStyle(StyleProvider.PORT_END);
			break;
		default:
			style = styleProvider.getStyle(StyleProvider.PORT);
		}

		Rectangle rec = gaService.createRectangle(boxAnchor);
		// Polyline poly = gaService.createPolyline(boxAnchor ,new int[]{0,
		// PORT_SIZE / 2, PORT_SIZE, PORT_SIZE / 2});
		rec.setStyle(style);
		// poly.setStyle(style);
		gaService.setLocationAndSize(rec, 0, 0, PORT_SIZE, PORT_SIZE);
		// gaService.setLocationAndSize(poly, 0, 0, PORT_SIZE, PORT_SIZE);

	}

	/**
	 * Calculate the Y pos for the straight line (port Stamm to port Ende) for a
	 * x pos
	 * 
	 * @param mitteAbzweigXY
	 *            The data witch is necessary for the linear function
	 *            (x1,y1,x2,y2)
	 * @param x
	 *            the x position for the calculated function
	 * @return f(x)
	 */
	private int getYFromArray(final int[] mitteAbzweigXY, final int x) {
		double m = (mitteAbzweigXY[3] - mitteAbzweigXY[1])
				/ (mitteAbzweigXY[2] - mitteAbzweigXY[0]);
		double b = mitteAbzweigXY[1] - m * mitteAbzweigXY[0];
		return (int) (m * x + b);

	}
}
