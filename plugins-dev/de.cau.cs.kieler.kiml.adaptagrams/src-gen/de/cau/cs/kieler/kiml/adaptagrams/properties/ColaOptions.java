package de.cau.cs.kieler.kiml.adaptagrams.properties;

import de.cau.cs.kieler.adaptagrams.provider.ConstrainedLayoutProvider;
import de.cau.cs.kieler.kiml.adaptagrams.properties.ColaMetaDataProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class ColaOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Constrained Layout algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.cola";
  
  /**
   * Default value for {@link #DIRECTION} with algorithm "Constrained Layout".
   */
  private final static Direction DIRECTION_DEFAULT = Direction.UNDEFINED;
  
  /**
   * Overall direction of edges: horizontal (right / left) or
   * vertical (down / up).
   */
  public final static IProperty<Direction> DIRECTION = new Property<Direction>(
                                CoreOptions.DIRECTION,
                                DIRECTION_DEFAULT);
  
  /**
   * Default value for {@link #PADDING} with algorithm "Constrained Layout".
   */
  private final static ElkPadding PADDING_DEFAULT = new ElkPadding(10);
  
  /**
   * The padding to be left to a parent element's border when placing child elements. This can
   * also serve as an output option of a layout algorithm if node size calculation is setup
   * appropriately.
   */
  public final static IProperty<ElkPadding> PADDING = new Property<ElkPadding>(
                                CoreOptions.PADDING,
                                PADDING_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_NODE_NODE} with algorithm "Constrained Layout".
   */
  private final static double SPACING_NODE_NODE_DEFAULT = 20;
  
  /**
   * The minimal distance to be preserved between each two nodes.
   */
  public final static IProperty<Double> SPACING_NODE_NODE = new Property<Double>(
                                CoreOptions.SPACING_NODE_NODE,
                                SPACING_NODE_NODE_DEFAULT);
  
  /**
   * Causes non-overlap constraints to be generated for all rectangles, if it is set to true.
   */
  public final static IProperty<Boolean> AVOID_OVERLAPS = ColaMetaDataProvider.AVOID_OVERLAPS;
  
  /**
   * Whether ports should be moved to the point where edges cross the node&apos;s bounds.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = ColaMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  /**
   * If activated, prior node positons are considered during the layout
   */
  public final static IProperty<Boolean> CONSIDER_PREVIOUS_POSITIONS = ColaMetaDataProvider.CONSIDER_PREVIOUS_POSITIONS;
  
  /**
   * The ideal length of all edges.
   */
  public final static IProperty<Double> IDEAL_EDGE_LENGTH = ColaMetaDataProvider.IDEAL_EDGE_LENGTH;
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.cola",
        "Constrained Layout",
        "This method is based on a non-linear gradient projection technique. Conceptually it&apos;s similar to a force directed method like Fruchterman-Reingold, but using a more principled goal function and optimization techniques.",
        new org.eclipse.elk.core.util.AlgorithmFactory(ConstrainedLayoutProvider.class, ""),
        "org.eclipse.elk.force",
        null,
        null,
        null
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "org.eclipse.elk.direction",
        DIRECTION_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "org.eclipse.elk.padding",
        PADDING_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "org.eclipse.elk.spacing.nodeNode",
        SPACING_NODE_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "de.cau.cs.kieler.cola.avoidOverlaps",
        AVOID_OVERLAPS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "de.cau.cs.kieler.cola.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "de.cau.cs.kieler.cola.considerPreviousPositions",
        CONSIDER_PREVIOUS_POSITIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.cola",
        "de.cau.cs.kieler.cola.idealEdgeLength",
        IDEAL_EDGE_LENGTH.getDefault()
    );
  }
}
