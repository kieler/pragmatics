package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class TreeOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Tree".
   */
  private final static float SPACING_BORDER_DEFAULT = 15;
  
  /**
   * Property constant to access Border Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_BORDER = new Property<Float>(
                                CoreOptions.SPACING_BORDER,
                                SPACING_BORDER_DEFAULT);
  
  /**
   * Property constant to access Label Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_EDGE_DISTANCE = OgdfMetaDataProvider.LABEL_EDGE_DISTANCE;
  
  /**
   * Property constant to access Label Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_MARGIN_DISTANCE = OgdfMetaDataProvider.LABEL_MARGIN_DISTANCE;
  
  /**
   * Default value for {@link #DIRECTION} with algorithm "Tree".
   */
  private final static Direction DIRECTION_DEFAULT = Direction.RIGHT;
  
  /**
   * Property constant to access Direction from within the layout algorithm code.
   */
  public final static IProperty<Direction> DIRECTION = new Property<Direction>(
                                CoreOptions.DIRECTION,
                                DIRECTION_DEFAULT);
  
  /**
   * Default value for {@link #EDGE_ROUTING} with algorithm "Tree".
   */
  private final static EdgeRouting EDGE_ROUTING_DEFAULT = EdgeRouting.POLYLINE;
  
  /**
   * Property constant to access Edge Routing from within the layout algorithm code.
   */
  public final static IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
                                CoreOptions.EDGE_ROUTING,
                                EDGE_ROUTING_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Tree".
   */
  private final static float SPACING_NODE_DEFAULT = 20;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Property constant to access Level Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> MIN_DIST_LEVEL = OgdfMetaDataProvider.MIN_DIST_LEVEL;
  
  /**
   * Property constant to access Subtree Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> SUBTREE_DISTANCE = OgdfMetaDataProvider.SUBTREE_DISTANCE;
  
  /**
   * Property constant to access Component Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> MIN_DIST_C_C = OgdfMetaDataProvider.MIN_DIST_C_C;
  
  /**
   * Property constant to access Node Size Constraints from within the layout algorithm code.
   */
  public final static IProperty<EnumSet<SizeConstraint>> NODE_SIZE_CONSTRAINTS = CoreOptions.NODE_SIZE_CONSTRAINTS;
  
  /**
   * Property constant to access Node Size Options from within the layout algorithm code.
   */
  public final static IProperty<EnumSet<SizeOptions>> NODE_SIZE_OPTIONS = CoreOptions.NODE_SIZE_OPTIONS;
  
  /**
   * Property constant to access Place Labels from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PLACE_LABELS = OgdfMetaDataProvider.PLACE_LABELS;
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "Tree",
        "Specialized algorithm for trees, after Buchheim, Juenger and Leipert \'06. The branches of the tree are arranged in a given direction.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "TREE"),
        "org.eclipse.elk.tree",
        "OGDF",
        "images/tree.png",
        EnumSet.of(GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.direction",
        DIRECTION_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.edgeRouting",
        EDGE_ROUTING_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        MIN_DIST_LEVEL.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.subtreeDistance",
        SUBTREE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        MIN_DIST_C_C.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
