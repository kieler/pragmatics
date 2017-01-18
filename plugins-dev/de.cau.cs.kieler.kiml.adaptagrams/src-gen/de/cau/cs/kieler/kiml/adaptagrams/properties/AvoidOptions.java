package de.cau.cs.kieler.kiml.adaptagrams.properties;

import de.cau.cs.kieler.adaptagrams.provider.SWIGLibavoidLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class AvoidOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Libavoid Edge Routing algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.avoid.cola";
  
  /**
   * Overall direction of edges: horizontal (right / left) or
   * vertical (down / up).
   */
  public final static IProperty<Direction> DIRECTION = CoreOptions.DIRECTION;
  
  /**
   * Default value for {@link #EDGE_ROUTING} with algorithm "Libavoid Edge Routing".
   */
  private final static EdgeRouting EDGE_ROUTING_DEFAULT = EdgeRouting.ORTHOGONAL;
  
  /**
   * What kind of edge routing style should be applied for the content of a parent node.
   * Algorithms may also set this option to single edges in order to mark them as splines.
   * The bend point list of edges with this option set to SPLINES must be interpreted as control
   * points for a piecewise cubic spline.
   */
  public final static IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
                                CoreOptions.EDGE_ROUTING,
                                EDGE_ROUTING_DEFAULT);
  
  /**
   * Margins define additional space around the actual bounds of a graph element. For instance,
   * ports or labels being placed on the outside of a node's border might introduce such a
   * margin. The margin is used to guarantee non-overlap of other graph elements with those
   * ports or labels.
   * <h3>Algorithm Specific Details</h3>
   * Margin of a (atomic) node, i.e. space to be preserved around a node.
   */
  public final static IProperty<ElkMargin> MARGINS = CoreOptions.MARGINS;
  
  /**
   * The padding to be left to a parent element's border when placing child elements. This can
   * also serve as an output option of a layout algorithm if node size calculation is setup
   * appropriately.
   * <h3>Algorithm Specific Details</h3>
   * Padding of the parent node, i.e. restriction of the drawing area.
   */
  public final static IProperty<ElkPadding> PADDING = CoreOptions.PADDING;
  
  /**
   * Defines constraints of the position of the ports of a node.
   */
  public final static IProperty<PortConstraints> PORT_CONSTRAINTS = CoreOptions.PORT_CONSTRAINTS;
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.avoid.cola",
        "Libavoid Edge Routing",
        "libavoid is a cross-platform C++ library providing fast, object-avoiding orthogonal and polyline connector routing for use in interactive diagram editors.",
        new org.eclipse.elk.core.util.AlgorithmFactory(SWIGLibavoidLayoutProvider.class, ""),
        null,
        null,
        null,
        null
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.avoid.cola",
        "org.eclipse.elk.direction",
        DIRECTION.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.avoid.cola",
        "org.eclipse.elk.edgeRouting",
        EDGE_ROUTING_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.avoid.cola",
        "org.eclipse.elk.margins",
        MARGINS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.avoid.cola",
        "org.eclipse.elk.padding",
        PADDING.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.avoid.cola",
        "org.eclipse.elk.portConstraints",
        PORT_CONSTRAINTS.getDefault()
    );
  }
}
