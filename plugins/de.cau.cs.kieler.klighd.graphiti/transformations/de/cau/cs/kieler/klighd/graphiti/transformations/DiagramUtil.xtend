package de.cau.cs.kieler.klighd.graphiti.transformations

import org.eclipse.emf.ecore.EObject
import org.eclipse.graphiti.mm.MmFactory
import org.eclipse.graphiti.mm.Property
import org.eclipse.graphiti.mm.pictograms.PictogramElement
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory
import org.eclipse.graphiti.mm.pictograms.Shape
import org.eclipse.graphiti.mm.pictograms.Anchor
import org.eclipse.graphiti.mm.pictograms.Connection
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory
import org.eclipse.graphiti.mm.algorithms.styles.Color
import org.eclipse.graphiti.mm.algorithms.styles.Point
import org.eclipse.graphiti.mm.algorithms.Rectangle
import org.eclipse.graphiti.mm.algorithms.styles.Style
import org.eclipse.graphiti.mm.algorithms.styles.Font
import org.eclipse.graphiti.mm.algorithms.Text
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm
import org.eclipse.graphiti.mm.algorithms.styles.Orientation
import de.cau.cs.kieler.core.annotations.IntAnnotation
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator
import java.util.List

class DiagramUtil {
	
    /**
     * Shortcut method for creating shapes. 
     */
	def Diagram create diag: PictogramsFactory::eINSTANCE.createDiagram getDiagram() {		
        // color rect is needed to synthesize valid diagrams to be displayed by a graphiti editor
        val rect = AlgorithmsFactory::eINSTANCE.createRectangle;
        rect.setHeight(1000);
        rect.setWidth(1000);
                
		diag.link = PictogramsFactory::eINSTANCE.createPictogramLink;
        diag.setGraphicsAlgorithm(rect); 
	}
	
	
    /**
     * Shortcut method for creating shapes. 
     */
	def Shape create shape: PictogramsFactory::eINSTANCE.createShape createShape(Object o) {
        shape.setActive(true);
        shape.setVisible(true);
        shape.setLink(PictogramsFactory::eINSTANCE.createPictogramLink);
        getDiagram.children.add(shape);
	}

    /**
     * Just a wrapper to be used to reveal the shape
     *  indicating that it has been created already!
     *  (only for code-readability)
     */
    def Shape getShape(Object o) {
        createShape(o);
    }


    /**
     * Create and add an invisible anchor to 'shape'.
     *  The anchor will be a ChopBoxAnchor resulting
     *  in an edge rendering that one used to from typical
     *  graph layouts (edge tip points to the center of shape's GA).
     */
    def create anchor:PictogramsFactory::eINSTANCE.createChopboxAnchor createAnchor(Shape shape) {
        anchor.setActive(false)
        anchor.setVisible(true)
        anchor.setLink(PictogramsFactory::eINSTANCE.createPictogramLink)
        anchor.setReferencedGraphicsAlgorithm(shape.graphicsAlgorithm)
        shape.anchors.add(anchor)
        anchor
    }


    /**
     * Creates an anchor and a related port figure as well as a port label
     *  onto the east side of a given shape with the port label text 'label'.
     *  The anchor is related to the given EObjects.
     */
    def Anchor createLabeledEastPortAnchor(Shape shape, String label, EObject eo1) {
    	shape.createLabeledEastPortAnchor(label, newArrayList(eo1, eo1, eo1))
    }
    def Anchor createLabeledEastPortAnchor(Shape shape, String label, EObject eo1, EObject eo2) {
    	shape.createLabeledEastPortAnchor(label, newArrayList(eo1, eo2, eo2))
    }
    def Anchor createLabeledEastPortAnchor(Shape shape, String label, EObject eo1, EObject eo2, EObject eo3) {
    	shape.createLabeledEastPortAnchor(label, newArrayList(eo1, eo2, eo3))
    }
    // @deprecated: legacy
    def Anchor createLabeledEastPortAnchor(Shape shape, EObject eo, String label) {
    	shape.createLabeledEastPortAnchor(label, newArrayList(eo, eo, eo));
    }


    /**
     * Creates an anchor and a related port figure as well as a port label
     *  onto the west side of a given shape with the port label text 'label'.
     *  The anchor is related to the given EObjects.
     */
    def Anchor createLabeledWestPortAnchor(Shape shape, String label, EObject eo1) {
    	shape.createLabeledWestPortAnchor(label, newArrayList(eo1, eo1, eo1))
    }
    def Anchor createLabeledWestPortAnchor(Shape shape, String label, EObject eo1, EObject eo2) {
    	shape.createLabeledWestPortAnchor(label, newArrayList(eo1, eo2, eo2))
    }
    def Anchor createLabeledWestPortAnchor(Shape shape, String label, EObject eo1, EObject eo2, EObject eo3) {
    	shape.createLabeledWestPortAnchor(label, newArrayList(eo1, eo2, eo3))
    }
    // @deprecated: legacy
    def Anchor createLabeledWestPortAnchor(Shape shape, EObject eo, String label) {
    	shape.createLabeledWestPortAnchor(label, newArrayList(eo, eo, eo));
    }

    def private Anchor createLabeledEastPortAnchor(Shape shape, String label, List<EObject> eos) {
    	val x = shape.graphicsAlgorithm.width
    	val y = shape.getAndAddIntProperty("eastports") * 15 + verticalPortPlacementOffsetTop.value;
    	val anchor = shape.createPortAnchor(eos, x,y);
    	val rect = anchor.createRectangle(0,0,7,7, "black_black".style);
    	rect.createLabelText(anchor, -outerHorizontalPortLabelPlacementOffset.value, -2, label, Orientation::ALIGNMENT_RIGHT, "default".font);
        shape.graphicsAlgorithm.setHeight(Math::max(shape.graphicsAlgorithm.height, y+15));
    	return anchor    	
    }

    def private Anchor createLabeledWestPortAnchor(Shape shape, String label, List<EObject> eos) {
    	val x = -5
    	val y = shape.getAndAddIntProperty("westports") * 15 + verticalPortPlacementOffsetTop.value;
    	val anchor = shape.createPortAnchor(eos,x,y);
    	val rect = anchor.createRectangle(0,0,7,7, "black_black".style);
    	rect.createLabelText(anchor, outerHorizontalPortLabelPlacementOffset.value, -2, label, Orientation::ALIGNMENT_LEFT, "default".font);
        shape.graphicsAlgorithm.setHeight(Math::max(shape.graphicsAlgorithm.height, y+15));
    	return anchor
    }
    
    def private Anchor createPortAnchor(Shape shape, List<EObject> eos,int x, int y) {
    	val anchor = getPortAnchor(eos.get(0), eos.get(1), eos.get(2));
        anchor.setActive(true);
        anchor.setVisible(true);        
        anchor.setLocation(createPoint(x,y));
        anchor.setReferencedGraphicsAlgorithm(shape.graphicsAlgorithm);
        shape.anchors.add(anchor);
        return anchor
    }

    def private FixPointAnchor create anchor: PictogramsFactory::eINSTANCE.createFixPointAnchor getPortAnchor(EObject eo1, EObject eo2, EObject eo3) {
        anchor.setLink(PictogramsFactory::eINSTANCE.createPictogramLink);
        anchor.link.businessObjects.addAll(newArrayList(eo1, eo2, eo3));
    }
    
    def FixPointAnchor getPortAnchor(EObject eo) {
    	eo.getPortAnchor(eo, eo);
    }


    /**
     * Default constant. Configured to enable a proper box label placement.
     * Can be reconfigured using '...verticalPortPlacementOffsetTop.setValue'. 
     */
    def IntAnnotation create offset: AnnotationsFactory::eINSTANCE.createIntAnnotation getVerticalPortPlacementOffsetTop() {
    	offset.value = 25;
    }
    
    
    /**
     * Default constant. Configured to enable a proper box label placement.
     * Can be reconfigured using '...verticalPortPlacementOffsetTop.setValue'. 
     */
    def IntAnnotation create offset: AnnotationsFactory::eINSTANCE.createIntAnnotation getOuterHorizontalPortLabelPlacementOffset() {
    	offset.value = 10;
    }


    /**
     *
     */
    def Text createText(GraphicsAlgorithm ga, String value, Font font) {
        val text = AlgorithmsFactory::eINSTANCE.createText
        text.setFont(font);
        text.setForeground("black".color);
        text.setWidth(ga.width);
        text.setHorizontalAlignment(Orientation::ALIGNMENT_CENTER);
        text.setValue(value);
        ga.graphicsAlgorithmChildren.add(text);
        return text
    }

    def Text createText(GraphicsAlgorithm ga, String value) {
        return createText(ga, value, "default".font)
    }

    def Text createText(GraphicsAlgorithm ga) {
        return createText(ga, null)
    }


    /**
     * Special extension targeting the label text creation.
     * This is reasonable since the label element is pickable in the graphic represention
     * and, hence, should be mapped to the related model element, as well.
     */
    def Text createLabelText(GraphicsAlgorithm ga, Anchor a, int x, int y, String value, Orientation alignment, Font font) {
        val text = a.createLabelText()
        text.setX(x);
        text.setY(y);
        text.setWidth(ga.width);
        text.setValue(value);
        text.setHorizontalAlignment(alignment);
        text.setFont(font);
        text.setForeground("black".color);
        ga.graphicsAlgorithmChildren.add(text);
        return text
    }

    def private Text create text: a.graphicsAlgorithm.createText() createLabelText(Anchor a) {
    }
    
    def Text getLabelText(Anchor a) {
    	return a.createLabelText
    }
    
    

    /**
     *
     */
    def Connection create connection: PictogramsFactory::eINSTANCE.createFreeFormConnection createConnection(Object o) {
        val polyline = AlgorithmsFactory::eINSTANCE.createPolyline;
        polyline.setLineWidth(1);
        polyline.setForeground(getColor("black"));
        connection.setActive(true);
        connection.setVisible(true);
        connection.setGraphicsAlgorithm(polyline);
        connection.setLink(PictogramsFactory::eINSTANCE.createPictogramLink);  
        getDiagram().connections.add(connection);
    }

    /**
     * Just a wrapper to be used to reveal the connection
     *  indicating that it has been created already!
     *  (only for code-readability)
     */
    def Connection getConnection(Object o) {
        o.createConnection();
    }
    
    def Connection createConnection(Object o, int width, Color color){
    	val connection = o.createConnection(width)
    	connection.graphicsAlgorithm.setForeground(color)
    	return connection
    }
    
    def Connection createConnection(Object o, int width) {
        val connection = o.createConnection;
        connection.graphicsAlgorithm.setLineWidth(width);
        return connection
        
    }
    
    def Connection from(Connection connection, Anchor start) {
    	connection.setStart(start);
    	return connection
    }

    def Connection to(Connection connection, Anchor end) {
    	connection.setEnd(end);
    	return connection
    }


    /**
      *
      */
    def ConnectionDecorator addConnectionArrow(Connection connection, int scale, boolean toHead) {
        val decorator = PictogramsFactory::eINSTANCE.createConnectionDecorator;
        val figure = AlgorithmsFactory::eINSTANCE.createPolygon;
        if (toHead) {
            figure.points.addAll(newArrayList(
        	    createPoint(scale*-8,scale*3), createPoint(scale*-6,0), createPoint(scale*-8,scale*-3), createPoint(0,0)
            ));
        } else {
            figure.points.addAll(newArrayList(
        	    createPoint(scale* 8,scale*3), createPoint(scale* 6,0), createPoint(scale* 8,scale*-3), createPoint(0,0)
            ));
        }
        figure.setForeground(connection.graphicsAlgorithm.foreground);
        figure.setBackground(figure.foreground);
        figure.setFilled(true);  
        decorator.setVisible(true);
        decorator.setLocation(if (toHead) Float::valueOf("0.95") else Float::valueOf("0.05"));
        decorator.setLocationRelative(true);
        decorator.setGraphicsAlgorithm(figure);
        connection.connectionDecorators.add(decorator);
        return decorator
    }
    
    def Connection addHeadArrow(Connection connection, int scale) {
    	connection.addConnectionArrow(scale, true);
    	return connection
    }
    
    def Connection addTailArrow(Connection connection, int scale) {
    	connection.addConnectionArrow(scale, false);
    	return connection
    }
    
    def Connection addHeadArrow(Connection connection) {
        return addHeadArrow(connection,  1)
    }
    
    def Connection addTailArrow(Connection connection) {
        return addTailArrow(connection,  1)
    }
    
    /**
     *
     */
    def Point createPoint(int x, int y) {
        val point = StylesFactory::eINSTANCE.createPoint;
        point.setX(x);
        point.setY(y);
        return point
    }


    /**
     * Shorthand wraper for property creation.
     */
    def Property createProperty(String key, String value) {
        val property = MmFactory::eINSTANCE.createProperty;
        property.setKey(key)
        property.setValue(value)
        return property
    }
    
    /**
     * Shorthand wraper for property creation.
     */
    def <T extends PictogramElement> T addProperty(T element, String key, String value) {
        element.properties.add(createProperty(key, value));
    	return element
    }

    /**********************/
    /*                    */
    /* GraphicsAlgorithms */
    /*                    */
    /**********************/


    /**
      *
      */
    def Rectangle createRectangle(int x, int y, int width, int height, int lineWidth, Color fg, Color bg) {
        val rect = AlgorithmsFactory::eINSTANCE.createRectangle
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(height);
        rect.setWidth(width);
        rect.setFilled(true);
        rect.setLineVisible(true);
        rect.setLineWidth(lineWidth);
        rect.setBackground(bg);
        rect.setForeground(fg);
        return rect;
    }
    
    def Rectangle createRectangle(PictogramElement element, int x, int y, int width, int height, int lineWidth, Color fg, Color bg) {
    	val rect = createRectangle(x,y,width,height, lineWidth, fg, bg);
    	element.setGraphicsAlgorithm(rect);
    	return rect;
    }
    
    
    /**
      *
      */
    def Rectangle createRectangle(int x, int y, int width, int height, Style style) {
        val rect = AlgorithmsFactory::eINSTANCE.createRectangle
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(height);
        rect.setWidth(width);
        rect.setFilled(true);
        rect.setLineVisible(true);
        rect.setStyle(style);
        return rect;
    }
    
    def Rectangle createRectangle(PictogramElement element, int x, int y, int width, int height, Style style) {
    	val rect = createRectangle(x,y,width,height, style);
    	element.setGraphicsAlgorithm(rect);
    	return rect;
    }
    
    def Rectangle createRectangle(PictogramElement element, int width, int height, Style style) {
    	val rect = createRectangle(0,0,width,height, style);
    	element.setGraphicsAlgorithm(rect);
    	return rect;
    }
    

    /**
     * Creation of the color elements
     *  allows to refer to colors by name
     *  adds them to the diagram on demand
     */
    def Color create color: StylesFactory::eINSTANCE.createColor getColor(String name) {
      switch (name) {
       case "black" : {
         color.setRed(0);
         color.setGreen(0);
         color.setBlue(0);
       }
       case "blue" : {
        color.setRed(51);
        color.setGreen(51);
        color.setBlue(153);
       }
       case "lightBlue" : {
        color.setRed(150);
        color.setGreen(150);
        color.setBlue(255);
       }
       case "darkGray" : {
        color.setRed(105);
        color.setGreen(105);
        color.setBlue(105);
       }
       case "darkOrange" : {
        color.setRed(248);
        color.setGreen(179);
        color.setBlue(0);
       }
       case "error" : {
        color.setRed(255);
        color.setGreen(0);
        color.setBlue(0);
       }
       case "gray" : {
        color.setRed(190);
        color.setGreen(190);
        color.setBlue(190);
       }
       case "lemon" : {
        color.setRed(255);
        color.setGreen(250);
        color.setBlue(205);
       }
       case "red" : {
        color.setRed(255);
        color.setGreen(0);
        color.setBlue(0);
       }
       case "white" : {
        color.setRed(255);
        color.setGreen(255);
        color.setBlue(255);
       }
       case "yellow" : {
        color.setRed(255);
        color.setGreen(255);
        color.setBlue(0);
       }
       case "green" : {
        color.setRed(0);
        color.setGreen(255);
        color.setBlue(0);
       }
      }
      getDiagram().colors.add(color);
    }


    /**
     * Creation of the available style collection
     *  allows to refer to colors by name
     *  adds them to the diagram on demand
     */
    def Style create style: StylesFactory::eINSTANCE.createStyle getStyle(String name) {
      switch(name) {
       case "black_lemon" : {
        style.setId("black_lemon");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("lemon"));
        style.setLineWidth(1);
       }
       case "black_white" : {
        style.setId("black_white");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("white"));
        style.setLineWidth(1);
       }
       case "black_white_2" : {
        style.setId("black_white");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("white"));
        style.setLineWidth(2);
       }
       case "black_white_4" : {
        style.setId("black_white");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("white"));
        style.setLineWidth(4);
       }
       case "black_black" : {
        style.setId("black_black");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("black"));
        style.setLineWidth(1);
       }
       case "black_gray_4" : {
        style.setId("black_gray");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("gray"));
        style.setLineWidth(4);
       }
       case "black_darkGray_4" : {
        style.setId("black_darkGray");
        style.setForeground(getColor("black"));
        style.setBackground(getColor("darkGray"));
        style.setLineWidth(4);
       }
       case "red_white" : {
        style.setId("red_white");
        style.setForeground(getColor("red"));
        style.setBackground(getColor("white"));
        style.setLineWidth(1);
       }
      }
      getDiagram().styles.add(style);
    }


    /**
     * Creation of the available font collection
     *  here we will use the same font for all labels
     *  adds it to the diagram on demand
     */
    def Font create font: StylesFactory::eINSTANCE.createFont getFont(String name) {
      switch (name) {
       case "10" : {
        font.setName("Arial");
        font.setSize(10);
        font.setBold(false);
       }
       case "11" : {
        font.setName("Arial");
        font.setSize(11);
        font.setBold(false);
       }
       case "12" : {
        font.setName("Arial");
        font.setSize(12);
        font.setBold(false);
       }
       case "bold" : {
        font.setName("Arial");
        font.setSize(8);
        font.setBold(true);
       }
       case "bold10" : {
        font.setName("Arial");
        font.setSize(10);
        font.setBold(true);
       }
       case "bold12" : {
        font.setName("Arial");
        font.setSize(12);
        font.setBold(true);
       }
       case "default" : {
        font.setName("Arial");
        font.setSize(8);
        font.setBold(false);
       }
      }
      getDiagram().fonts.add(font);
    }


    /**********************
     *                    *
     *  auxiliary stuff   *
     *                    *
     **********************/
    
     def IntAnnotation intEObject(Integer value) {
     	 val eo = AnnotationsFactory::eINSTANCE.createIntAnnotation;
     	 eo.value = value;
     	 eo
     }
     /**
      * 
      */
     def IntAnnotation create intAnno: AnnotationsFactory::eINSTANCE.createIntAnnotation getIntProperty(Shape shape, String name) {
         intAnno.setName(name);
         intAnno.setValue(0);
     }


     def int getAndAddIntProperty(Shape shape, String name) {
         val intAnno = shape.getIntProperty(name);
         intAnno.setValue(intAnno.value + 1);
         intAnno.value
     }
}