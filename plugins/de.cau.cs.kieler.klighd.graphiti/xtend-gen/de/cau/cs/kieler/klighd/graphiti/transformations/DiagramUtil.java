package de.cau.cs.kieler.klighd.graphiti.transformations;

import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.IntAnnotation;
import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class DiagramUtil {
  /**
   * Shortcut method for creating shapes.
   */
  public Diagram getDiagram() {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList();
    final Diagram _result;
    synchronized (_createCache_getDiagram) {
      if (_createCache_getDiagram.containsKey(_cacheKey)) {
        return _createCache_getDiagram.get(_cacheKey);
      }
      Diagram _createDiagram = PictogramsFactory.eINSTANCE.createDiagram();
      _result = _createDiagram;
      _createCache_getDiagram.put(_cacheKey, _result);
    }
    _init_getDiagram(_result);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Diagram> _createCache_getDiagram = CollectionLiterals.newHashMap();
  
  private void _init_getDiagram(final Diagram diag) {
      Rectangle _createRectangle = AlgorithmsFactory.eINSTANCE.createRectangle();
      final Rectangle rect = _createRectangle;
      rect.setHeight(1000);
      rect.setWidth(1000);
      PictogramLink _createPictogramLink = PictogramsFactory.eINSTANCE.createPictogramLink();
      diag.setLink(_createPictogramLink);
      diag.setGraphicsAlgorithm(rect);
  }
  
  /**
   * Shortcut method for creating shapes.
   */
  public Shape createShape(final Object o) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(o);
    final Shape _result;
    synchronized (_createCache_createShape) {
      if (_createCache_createShape.containsKey(_cacheKey)) {
        return _createCache_createShape.get(_cacheKey);
      }
      Shape _createShape = PictogramsFactory.eINSTANCE.createShape();
      _result = _createShape;
      _createCache_createShape.put(_cacheKey, _result);
    }
    _init_createShape(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Shape> _createCache_createShape = CollectionLiterals.newHashMap();
  
  private void _init_createShape(final Shape shape, final Object o) {
      shape.setActive(true);
      shape.setVisible(true);
      PictogramLink _createPictogramLink = PictogramsFactory.eINSTANCE.createPictogramLink();
      shape.setLink(_createPictogramLink);
      Diagram _diagram = this.getDiagram();
      EList<Shape> _children = _diagram.getChildren();
      _children.add(shape);
  }
  
  /**
   * Just a wrapper to be used to reveal the shape
   *  indicating that it has been created already!
   *  (only for code-readability)
   */
  public Shape getShape(final Object o) {
    Shape _createShape = this.createShape(o);
    return _createShape;
  }
  
  /**
   * Create and add an invisible anchor to 'shape'.
   *  The anchor will be a ChopBoxAnchor resulting
   *  in an edge rendering that one used to from typical
   *  graph layouts (edge tip points to the center of shape's GA).
   */
  public ChopboxAnchor createAnchor(final Shape shape) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(shape);
    final ChopboxAnchor _result;
    synchronized (_createCache_createAnchor) {
      if (_createCache_createAnchor.containsKey(_cacheKey)) {
        return _createCache_createAnchor.get(_cacheKey);
      }
      ChopboxAnchor _createChopboxAnchor = PictogramsFactory.eINSTANCE.createChopboxAnchor();
      _result = _createChopboxAnchor;
      _createCache_createAnchor.put(_cacheKey, _result);
    }
    _init_createAnchor(_result, shape);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,ChopboxAnchor> _createCache_createAnchor = CollectionLiterals.newHashMap();
  
  private void _init_createAnchor(final ChopboxAnchor anchor, final Shape shape) {
      anchor.setActive(false);
      anchor.setVisible(true);
      PictogramLink _createPictogramLink = PictogramsFactory.eINSTANCE.createPictogramLink();
      anchor.setLink(_createPictogramLink);
      GraphicsAlgorithm _graphicsAlgorithm = shape.getGraphicsAlgorithm();
      anchor.setReferencedGraphicsAlgorithm(_graphicsAlgorithm);
      EList<Anchor> _anchors = shape.getAnchors();
      _anchors.add(anchor);
  }
  
  /**
   * Create and add a visible anchor intended to serve
   *  as port to 'shape'. // at position (x,y).
   *  To color end an active and visible FixPointAnchor is created.
   */
  public FixPointAnchor getPortAnchor(final EObject o) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(o);
    final FixPointAnchor _result;
    synchronized (_createCache_getPortAnchor) {
      if (_createCache_getPortAnchor.containsKey(_cacheKey)) {
        return _createCache_getPortAnchor.get(_cacheKey);
      }
      FixPointAnchor _createFixPointAnchor = PictogramsFactory.eINSTANCE.createFixPointAnchor();
      _result = _createFixPointAnchor;
      _createCache_getPortAnchor.put(_cacheKey, _result);
    }
    _init_getPortAnchor(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,FixPointAnchor> _createCache_getPortAnchor = CollectionLiterals.newHashMap();
  
  private void _init_getPortAnchor(final FixPointAnchor anchor, final EObject o) {
      PictogramLink _createPictogramLink = PictogramsFactory.eINSTANCE.createPictogramLink();
      anchor.setLink(_createPictogramLink);
      PictogramLink _link = anchor.getLink();
      EList<EObject> _businessObjects = _link.getBusinessObjects();
      _businessObjects.add(o);
  }
  
  public Anchor createPortAnchor(final Shape shape, final EObject o, final int x, final int y) {
      FixPointAnchor _portAnchor = this.getPortAnchor(o);
      final FixPointAnchor anchor = _portAnchor;
      anchor.setActive(true);
      anchor.setVisible(true);
      Point _createPoint = this.createPoint(x, y);
      anchor.setLocation(_createPoint);
      GraphicsAlgorithm _graphicsAlgorithm = shape.getGraphicsAlgorithm();
      anchor.setReferencedGraphicsAlgorithm(_graphicsAlgorithm);
      EList<Anchor> _anchors = shape.getAnchors();
      _anchors.add(anchor);
      return anchor;
  }
  
  /**
   * Creates a anchor and a related port figure as well as a port label
   *  onto the west side of a given shape with the port label text 'label'.
   */
  public Anchor createLabeledEastPortAnchor(final Shape shape, final EObject o, final String label) {
      GraphicsAlgorithm _graphicsAlgorithm = shape.getGraphicsAlgorithm();
      int _width = _graphicsAlgorithm.getWidth();
      final int x = _width;
      int _andAddIntProperty = this.getAndAddIntProperty(shape, "eastports");
      int _operator_multiply = IntegerExtensions.operator_multiply(((Integer)_andAddIntProperty), ((Integer)15));
      IntAnnotation _verticalPortPlacementOffsetTop = this.getVerticalPortPlacementOffsetTop();
      int _value = _verticalPortPlacementOffsetTop.getValue();
      int _operator_plus = IntegerExtensions.operator_plus(((Integer)_operator_multiply), ((Integer)_value));
      final int y = _operator_plus;
      Anchor _createPortAnchor = this.createPortAnchor(shape, o, x, y);
      final Anchor anchor = _createPortAnchor;
      Style _style = this.getStyle("black_black");
      Rectangle _createRectangle = this.createRectangle(anchor, 0, 0, 7, 7, _style);
      final Rectangle rect = _createRectangle;
      IntAnnotation _outerHorizontalPortLabelPlacementOffset = this.getOuterHorizontalPortLabelPlacementOffset();
      int _value_1 = _outerHorizontalPortLabelPlacementOffset.getValue();
      int _operator_minus = IntegerExtensions.operator_minus(_value_1);
      int _operator_minus_1 = IntegerExtensions.operator_minus(2);
      Font _font = this.getFont("default");
      Text _createLabelText = this.createLabelText(rect, o, _operator_minus, _operator_minus_1, label, Orientation.ALIGNMENT_RIGHT, _font);
      final Text text = _createLabelText;
      return anchor;
  }
  
  /**
   * Creates a anchor and a related port figure as well as a port label
   *  onto the west side of a given shape with the port label text 'label'.
   */
  public Anchor createLabeledWestPortAnchor(final Shape shape, final EObject o, final String label) {
      int _operator_minus = IntegerExtensions.operator_minus(5);
      final int x = _operator_minus;
      int _andAddIntProperty = this.getAndAddIntProperty(shape, "westports");
      int _operator_multiply = IntegerExtensions.operator_multiply(((Integer)_andAddIntProperty), ((Integer)15));
      IntAnnotation _verticalPortPlacementOffsetTop = this.getVerticalPortPlacementOffsetTop();
      int _value = _verticalPortPlacementOffsetTop.getValue();
      int _operator_plus = IntegerExtensions.operator_plus(((Integer)_operator_multiply), ((Integer)_value));
      final int y = _operator_plus;
      Anchor _createPortAnchor = this.createPortAnchor(shape, o, x, y);
      final Anchor anchor = _createPortAnchor;
      Style _style = this.getStyle("black_black");
      Rectangle _createRectangle = this.createRectangle(anchor, 0, 0, 7, 7, _style);
      final Rectangle rect = _createRectangle;
      IntAnnotation _outerHorizontalPortLabelPlacementOffset = this.getOuterHorizontalPortLabelPlacementOffset();
      int _value_1 = _outerHorizontalPortLabelPlacementOffset.getValue();
      int _operator_minus_1 = IntegerExtensions.operator_minus(2);
      Font _font = this.getFont("default");
      this.createLabelText(rect, o, _value_1, _operator_minus_1, label, Orientation.ALIGNMENT_LEFT, _font);
      return anchor;
  }
  
  /**
   * Default constant. Configured to enable a proper box label placement.
   * Can be reconfigured using '...verticalPortPlacementOffsetTop.setValue'.
   */
  public IntAnnotation getVerticalPortPlacementOffsetTop() {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList();
    final IntAnnotation _result;
    synchronized (_createCache_getVerticalPortPlacementOffsetTop) {
      if (_createCache_getVerticalPortPlacementOffsetTop.containsKey(_cacheKey)) {
        return _createCache_getVerticalPortPlacementOffsetTop.get(_cacheKey);
      }
      IntAnnotation _createIntAnnotation = AnnotationsFactory.eINSTANCE.createIntAnnotation();
      _result = _createIntAnnotation;
      _createCache_getVerticalPortPlacementOffsetTop.put(_cacheKey, _result);
    }
    _init_getVerticalPortPlacementOffsetTop(_result);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,IntAnnotation> _createCache_getVerticalPortPlacementOffsetTop = CollectionLiterals.newHashMap();
  
  private void _init_getVerticalPortPlacementOffsetTop(final IntAnnotation offset) {
    offset.setValue(25);
  }
  
  /**
   * Default constant. Configured to enable a proper box label placement.
   * Can be reconfigured using '...verticalPortPlacementOffsetTop.setValue'.
   */
  public IntAnnotation getOuterHorizontalPortLabelPlacementOffset() {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList();
    final IntAnnotation _result;
    synchronized (_createCache_getOuterHorizontalPortLabelPlacementOffset) {
      if (_createCache_getOuterHorizontalPortLabelPlacementOffset.containsKey(_cacheKey)) {
        return _createCache_getOuterHorizontalPortLabelPlacementOffset.get(_cacheKey);
      }
      IntAnnotation _createIntAnnotation = AnnotationsFactory.eINSTANCE.createIntAnnotation();
      _result = _createIntAnnotation;
      _createCache_getOuterHorizontalPortLabelPlacementOffset.put(_cacheKey, _result);
    }
    _init_getOuterHorizontalPortLabelPlacementOffset(_result);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,IntAnnotation> _createCache_getOuterHorizontalPortLabelPlacementOffset = CollectionLiterals.newHashMap();
  
  private void _init_getOuterHorizontalPortLabelPlacementOffset(final IntAnnotation offset) {
    offset.setValue(10);
  }
  
  public Text createText(final GraphicsAlgorithm ga, final String value, final Font font) {
      Text _createText = AlgorithmsFactory.eINSTANCE.createText();
      final Text text = _createText;
      text.setFont(font);
      Color _color = this.getColor("black");
      text.setForeground(_color);
      int _width = ga.getWidth();
      text.setWidth(_width);
      text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
      text.setValue(value);
      EList<GraphicsAlgorithm> _graphicsAlgorithmChildren = ga.getGraphicsAlgorithmChildren();
      _graphicsAlgorithmChildren.add(text);
      return text;
  }
  
  public Text createText(final GraphicsAlgorithm ga, final String value) {
    Font _font = this.getFont("default");
    Text _createText = this.createText(ga, value, _font);
    return _createText;
  }
  
  public Text createText(final GraphicsAlgorithm ga) {
    Text _createText = this.createText(ga, null);
    return _createText;
  }
  
  /**
   * Special extension targeting the label text creation.
   * This is reasonable since the label element is pickable in the graphic represention
   * and, hence, should be mapped to the related model element, as well.
   */
  public Text createLabelText(final GraphicsAlgorithm ga, final EObject o, final int x, final int y, final String value, final Orientation alignment, final Font font) {
      Text _createLabelText = this.createLabelText(o);
      final Text text = _createLabelText;
      text.setX(x);
      text.setY(y);
      int _width = ga.getWidth();
      text.setWidth(_width);
      text.setValue(value);
      text.setHorizontalAlignment(alignment);
      text.setFont(font);
      Color _color = this.getColor("black");
      text.setForeground(_color);
      EList<GraphicsAlgorithm> _graphicsAlgorithmChildren = ga.getGraphicsAlgorithmChildren();
      _graphicsAlgorithmChildren.add(text);
      return text;
  }
  
  private Text createLabelText(final EObject o) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(o);
    final Text _result;
    synchronized (_createCache_createLabelText) {
      if (_createCache_createLabelText.containsKey(_cacheKey)) {
        return _createCache_createLabelText.get(_cacheKey);
      }
      FixPointAnchor _portAnchor = this.getPortAnchor(o);
      GraphicsAlgorithm _graphicsAlgorithm = _portAnchor.getGraphicsAlgorithm();
      Text _createText = this.createText(_graphicsAlgorithm);
      _result = _createText;
      _createCache_createLabelText.put(_cacheKey, _result);
    }
    _init_createLabelText(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Text> _createCache_createLabelText = CollectionLiterals.newHashMap();
  
  private void _init_createLabelText(final Text text, final EObject o) {
  }
  
  public Text getLabelText(final EObject o) {
    Text _createLabelText = this.createLabelText(o);
    return _createLabelText;
  }
  
  public Connection createConnection(final Object o) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(o);
    final FreeFormConnection _result;
    synchronized (_createCache_createConnection) {
      if (_createCache_createConnection.containsKey(_cacheKey)) {
        return _createCache_createConnection.get(_cacheKey);
      }
      FreeFormConnection _createFreeFormConnection = PictogramsFactory.eINSTANCE.createFreeFormConnection();
      _result = _createFreeFormConnection;
      _createCache_createConnection.put(_cacheKey, _result);
    }
    _init_createConnection(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Connection> _createCache_createConnection = CollectionLiterals.newHashMap();
  
  private void _init_createConnection(final FreeFormConnection connection, final Object o) {
      Polyline _createPolyline = AlgorithmsFactory.eINSTANCE.createPolyline();
      final Polyline polyline = _createPolyline;
      polyline.setLineWidth(((Integer)1));
      Color _color = this.getColor("black");
      polyline.setForeground(_color);
      connection.setActive(true);
      connection.setVisible(true);
      connection.setGraphicsAlgorithm(polyline);
      PictogramLink _createPictogramLink = PictogramsFactory.eINSTANCE.createPictogramLink();
      connection.setLink(_createPictogramLink);
      Diagram _diagram = this.getDiagram();
      EList<Connection> _connections = _diagram.getConnections();
      _connections.add(connection);
  }
  
  /**
   * Just a wrapper to be used to reveal the connection
   *  indicating that it has been created already!
   *  (only for code-readability)
   */
  public Connection getConnection(final Object o) {
    Connection _createConnection = this.createConnection(o);
    return _createConnection;
  }
  
  public Connection createConnection(final Object o, final int width, final Color color) {
      Connection _createConnection = this.createConnection(o, width);
      final Connection connection = _createConnection;
      GraphicsAlgorithm _graphicsAlgorithm = connection.getGraphicsAlgorithm();
      _graphicsAlgorithm.setForeground(color);
      return connection;
  }
  
  public Connection createConnection(final Object o, final int width) {
      Connection _createConnection = this.createConnection(o);
      final Connection connection = _createConnection;
      GraphicsAlgorithm _graphicsAlgorithm = connection.getGraphicsAlgorithm();
      _graphicsAlgorithm.setLineWidth(((Integer)width));
      return connection;
  }
  
  public Connection from(final Connection connection, final Anchor start) {
      connection.setStart(start);
      return connection;
  }
  
  public Connection to(final Connection connection, final Anchor end) {
      connection.setEnd(end);
      return connection;
  }
  
  public ConnectionDecorator addConnectionArrow(final Connection connection, final int scale, final boolean toHead) {
      ConnectionDecorator _createConnectionDecorator = PictogramsFactory.eINSTANCE.createConnectionDecorator();
      final ConnectionDecorator decorator = _createConnectionDecorator;
      Polygon _createPolygon = AlgorithmsFactory.eINSTANCE.createPolygon();
      final Polygon figure = _createPolygon;
      if (toHead) {
        EList<Point> _points = figure.getPoints();
        int _operator_minus = IntegerExtensions.operator_minus(8);
        int _operator_multiply = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)_operator_minus));
        int _operator_multiply_1 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)3));
        Point _createPoint = this.createPoint(_operator_multiply, _operator_multiply_1);
        int _operator_minus_1 = IntegerExtensions.operator_minus(6);
        int _operator_multiply_2 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)_operator_minus_1));
        Point _createPoint_1 = this.createPoint(_operator_multiply_2, 0);
        int _operator_minus_2 = IntegerExtensions.operator_minus(8);
        int _operator_multiply_3 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)_operator_minus_2));
        int _operator_minus_3 = IntegerExtensions.operator_minus(3);
        int _operator_multiply_4 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)_operator_minus_3));
        Point _createPoint_2 = this.createPoint(_operator_multiply_3, _operator_multiply_4);
        Point _createPoint_3 = this.createPoint(0, 0);
        ArrayList<Point> _newArrayList = CollectionLiterals.<Point>newArrayList(_createPoint, _createPoint_1, _createPoint_2, _createPoint_3);
        _points.addAll(_newArrayList);
      } else {
        EList<Point> _points_1 = figure.getPoints();
        int _operator_multiply_5 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)8));
        int _operator_multiply_6 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)3));
        Point _createPoint_4 = this.createPoint(_operator_multiply_5, _operator_multiply_6);
        int _operator_multiply_7 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)6));
        Point _createPoint_5 = this.createPoint(_operator_multiply_7, 0);
        int _operator_multiply_8 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)8));
        int _operator_minus_4 = IntegerExtensions.operator_minus(3);
        int _operator_multiply_9 = IntegerExtensions.operator_multiply(((Integer)scale), ((Integer)_operator_minus_4));
        Point _createPoint_6 = this.createPoint(_operator_multiply_8, _operator_multiply_9);
        Point _createPoint_7 = this.createPoint(0, 0);
        ArrayList<Point> _newArrayList_1 = CollectionLiterals.<Point>newArrayList(_createPoint_4, _createPoint_5, _createPoint_6, _createPoint_7);
        _points_1.addAll(_newArrayList_1);
      }
      GraphicsAlgorithm _graphicsAlgorithm = connection.getGraphicsAlgorithm();
      Color _foreground = _graphicsAlgorithm.getForeground();
      figure.setForeground(_foreground);
      Color _foreground_1 = figure.getForeground();
      figure.setBackground(_foreground_1);
      figure.setFilled(((Boolean)true));
      decorator.setVisible(true);
      Float _xifexpression = null;
      if (toHead) {
        Float _valueOf = Float.valueOf("0.95");
        _xifexpression = _valueOf;
      } else {
        Float _valueOf_1 = Float.valueOf("0.05");
        _xifexpression = _valueOf_1;
      }
      decorator.setLocation(_xifexpression);
      decorator.setLocationRelative(true);
      decorator.setGraphicsAlgorithm(figure);
      EList<ConnectionDecorator> _connectionDecorators = connection.getConnectionDecorators();
      _connectionDecorators.add(decorator);
      return decorator;
  }
  
  public Connection addHeadArrow(final Connection connection, final int scale) {
      this.addConnectionArrow(connection, scale, true);
      return connection;
  }
  
  public Connection addTailArrow(final Connection connection, final int scale) {
      this.addConnectionArrow(connection, scale, false);
      return connection;
  }
  
  public Connection addHeadArrow(final Connection connection) {
    Connection _addHeadArrow = this.addHeadArrow(connection, 1);
    return _addHeadArrow;
  }
  
  public Connection addTailArrow(final Connection connection) {
    Connection _addTailArrow = this.addTailArrow(connection, 1);
    return _addTailArrow;
  }
  
  public Point createPoint(final int x, final int y) {
      Point _createPoint = StylesFactory.eINSTANCE.createPoint();
      final Point point = _createPoint;
      point.setX(x);
      point.setY(y);
      return point;
  }
  
  /**
   * Shorthand wraper for property creation.
   */
  public Property createProperty(final String key, final String value) {
      Property _createProperty = MmFactory.eINSTANCE.createProperty();
      final Property property = _createProperty;
      property.setKey(key);
      property.setValue(value);
      return property;
  }
  
  /**
   * Shorthand wraper for property creation.
   */
  public <T extends PictogramElement> T addProperty(final T element, final String key, final String value) {
      EList<Property> _properties = element.getProperties();
      Property _createProperty = this.createProperty(key, value);
      _properties.add(_createProperty);
      return element;
  }
  
  public Rectangle createRectangle(final int x, final int y, final int width, final int height, final int lineWidth, final Color fg, final Color bg) {
      Rectangle _createRectangle = AlgorithmsFactory.eINSTANCE.createRectangle();
      final Rectangle rect = _createRectangle;
      rect.setX(x);
      rect.setY(y);
      rect.setHeight(height);
      rect.setWidth(width);
      rect.setFilled(((Boolean)true));
      rect.setLineVisible(((Boolean)true));
      rect.setLineWidth(((Integer)lineWidth));
      rect.setBackground(bg);
      rect.setForeground(fg);
      return rect;
  }
  
  public Rectangle createRectangle(final PictogramElement element, final int x, final int y, final int width, final int height, final int lineWidth, final Color fg, final Color bg) {
      Rectangle _createRectangle = this.createRectangle(x, y, width, height, lineWidth, fg, bg);
      final Rectangle rect = _createRectangle;
      element.setGraphicsAlgorithm(rect);
      return rect;
  }
  
  public Rectangle createRectangle(final int x, final int y, final int width, final int height, final Style style) {
      Rectangle _createRectangle = AlgorithmsFactory.eINSTANCE.createRectangle();
      final Rectangle rect = _createRectangle;
      rect.setX(x);
      rect.setY(y);
      rect.setHeight(height);
      rect.setWidth(width);
      rect.setFilled(((Boolean)true));
      rect.setLineVisible(((Boolean)true));
      rect.setStyle(style);
      return rect;
  }
  
  public Rectangle createRectangle(final PictogramElement element, final int x, final int y, final int width, final int height, final Style style) {
      Rectangle _createRectangle = this.createRectangle(x, y, width, height, style);
      final Rectangle rect = _createRectangle;
      element.setGraphicsAlgorithm(rect);
      return rect;
  }
  
  public Rectangle createRectangle(final PictogramElement element, final int width, final int height, final Style style) {
      Rectangle _createRectangle = this.createRectangle(0, 0, width, height, style);
      final Rectangle rect = _createRectangle;
      element.setGraphicsAlgorithm(rect);
      return rect;
  }
  
  /**
   * Creation of the color elements
   *  allows to refer to colors by name
   *  adds them to the diagram on demand
   */
  public Color getColor(final String name) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(name);
    final Color _result;
    synchronized (_createCache_getColor) {
      if (_createCache_getColor.containsKey(_cacheKey)) {
        return _createCache_getColor.get(_cacheKey);
      }
      Color _createColor = StylesFactory.eINSTANCE.createColor();
      _result = _createColor;
      _createCache_getColor.put(_cacheKey, _result);
    }
    _init_getColor(_result, name);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Color> _createCache_getColor = CollectionLiterals.newHashMap();
  
  private void _init_getColor(final Color color, final String name) {
      final String name_1 = name;
      boolean matched = false;
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black")) {
          matched=true;
          {
            color.setRed(0);
            color.setGreen(0);
            color.setBlue(0);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"blue")) {
          matched=true;
          {
            color.setRed(51);
            color.setGreen(51);
            color.setBlue(153);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"lightBlue")) {
          matched=true;
          {
            color.setRed(150);
            color.setGreen(150);
            color.setBlue(255);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"darkGray")) {
          matched=true;
          {
            color.setRed(105);
            color.setGreen(105);
            color.setBlue(105);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"darkOrange")) {
          matched=true;
          {
            color.setRed(248);
            color.setGreen(179);
            color.setBlue(0);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"error")) {
          matched=true;
          {
            color.setRed(255);
            color.setGreen(0);
            color.setBlue(0);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"gray")) {
          matched=true;
          {
            color.setRed(190);
            color.setGreen(190);
            color.setBlue(190);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"lemon")) {
          matched=true;
          {
            color.setRed(255);
            color.setGreen(250);
            color.setBlue(205);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"red")) {
          matched=true;
          {
            color.setRed(255);
            color.setGreen(0);
            color.setBlue(0);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"white")) {
          matched=true;
          {
            color.setRed(255);
            color.setGreen(255);
            color.setBlue(255);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"yellow")) {
          matched=true;
          {
            color.setRed(255);
            color.setGreen(255);
            color.setBlue(0);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"green")) {
          matched=true;
          {
            color.setRed(0);
            color.setGreen(255);
            color.setBlue(0);
          }
        }
      }
      Diagram _diagram = this.getDiagram();
      EList<Color> _colors = _diagram.getColors();
      _colors.add(color);
  }
  
  /**
   * Creation of the available style collection
   *  allows to refer to colors by name
   *  adds them to the diagram on demand
   */
  public Style getStyle(final String name) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(name);
    final Style _result;
    synchronized (_createCache_getStyle) {
      if (_createCache_getStyle.containsKey(_cacheKey)) {
        return _createCache_getStyle.get(_cacheKey);
      }
      Style _createStyle = StylesFactory.eINSTANCE.createStyle();
      _result = _createStyle;
      _createCache_getStyle.put(_cacheKey, _result);
    }
    _init_getStyle(_result, name);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Style> _createCache_getStyle = CollectionLiterals.newHashMap();
  
  private void _init_getStyle(final Style style, final String name) {
      final String name_1 = name;
      boolean matched = false;
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_lemon")) {
          matched=true;
          {
            style.setId("black_lemon");
            Color _color = this.getColor("black");
            style.setForeground(_color);
            Color _color_1 = this.getColor("lemon");
            style.setBackground(_color_1);
            style.setLineWidth(((Integer)1));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_white")) {
          matched=true;
          {
            style.setId("black_white");
            Color _color_2 = this.getColor("black");
            style.setForeground(_color_2);
            Color _color_3 = this.getColor("white");
            style.setBackground(_color_3);
            style.setLineWidth(((Integer)1));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_white_2")) {
          matched=true;
          {
            style.setId("black_white");
            Color _color_4 = this.getColor("black");
            style.setForeground(_color_4);
            Color _color_5 = this.getColor("white");
            style.setBackground(_color_5);
            style.setLineWidth(((Integer)2));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_white_4")) {
          matched=true;
          {
            style.setId("black_white");
            Color _color_6 = this.getColor("black");
            style.setForeground(_color_6);
            Color _color_7 = this.getColor("white");
            style.setBackground(_color_7);
            style.setLineWidth(((Integer)4));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_black")) {
          matched=true;
          {
            style.setId("black_black");
            Color _color_8 = this.getColor("black");
            style.setForeground(_color_8);
            Color _color_9 = this.getColor("black");
            style.setBackground(_color_9);
            style.setLineWidth(((Integer)1));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_gray_4")) {
          matched=true;
          {
            style.setId("black_gray");
            Color _color_10 = this.getColor("black");
            style.setForeground(_color_10);
            Color _color_11 = this.getColor("gray");
            style.setBackground(_color_11);
            style.setLineWidth(((Integer)4));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"black_darkGray_4")) {
          matched=true;
          {
            style.setId("black_darkGray");
            Color _color_12 = this.getColor("black");
            style.setForeground(_color_12);
            Color _color_13 = this.getColor("darkGray");
            style.setBackground(_color_13);
            style.setLineWidth(((Integer)4));
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"red_white")) {
          matched=true;
          {
            style.setId("red_white");
            Color _color_14 = this.getColor("red");
            style.setForeground(_color_14);
            Color _color_15 = this.getColor("white");
            style.setBackground(_color_15);
            style.setLineWidth(((Integer)1));
          }
        }
      }
      Diagram _diagram = this.getDiagram();
      EList<Style> _styles = _diagram.getStyles();
      _styles.add(style);
  }
  
  /**
   * Creation of the available font collection
   *  here we will use the same font for all labels
   *  adds it to the diagram on demand
   */
  public Font getFont(final String name) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(name);
    final Font _result;
    synchronized (_createCache_getFont) {
      if (_createCache_getFont.containsKey(_cacheKey)) {
        return _createCache_getFont.get(_cacheKey);
      }
      Font _createFont = StylesFactory.eINSTANCE.createFont();
      _result = _createFont;
      _createCache_getFont.put(_cacheKey, _result);
    }
    _init_getFont(_result, name);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Font> _createCache_getFont = CollectionLiterals.newHashMap();
  
  private void _init_getFont(final Font font, final String name) {
      final String name_1 = name;
      boolean matched = false;
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"10")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(10);
            font.setBold(false);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"11")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(11);
            font.setBold(false);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"12")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(12);
            font.setBold(false);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"bold")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(8);
            font.setBold(true);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"bold10")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(10);
            font.setBold(true);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"bold12")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(12);
            font.setBold(true);
          }
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(name_1,"default")) {
          matched=true;
          {
            font.setName("Arial");
            font.setSize(8);
            font.setBold(false);
          }
        }
      }
      Diagram _diagram = this.getDiagram();
      EList<Font> _fonts = _diagram.getFonts();
      _fonts.add(font);
  }
  
  public IntAnnotation getIntProperty(final Shape shape, final String name) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(shape, name);
    final IntAnnotation _result;
    synchronized (_createCache_getIntProperty) {
      if (_createCache_getIntProperty.containsKey(_cacheKey)) {
        return _createCache_getIntProperty.get(_cacheKey);
      }
      IntAnnotation _createIntAnnotation = AnnotationsFactory.eINSTANCE.createIntAnnotation();
      _result = _createIntAnnotation;
      _createCache_getIntProperty.put(_cacheKey, _result);
    }
    _init_getIntProperty(_result, shape, name);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,IntAnnotation> _createCache_getIntProperty = CollectionLiterals.newHashMap();
  
  private void _init_getIntProperty(final IntAnnotation intAnno, final Shape shape, final String name) {
      intAnno.setName(name);
      intAnno.setValue(0);
  }
  
  public int getAndAddIntProperty(final Shape shape, final String name) {
      IntAnnotation _intProperty = this.getIntProperty(shape, name);
      final IntAnnotation intAnno = _intProperty;
      int _value = intAnno.getValue();
      int _operator_plus = IntegerExtensions.operator_plus(((Integer)_value), ((Integer)1));
      intAnno.setValue(_operator_plus);
      int _value_1 = intAnno.getValue();
      return _value_1;
  }
}
