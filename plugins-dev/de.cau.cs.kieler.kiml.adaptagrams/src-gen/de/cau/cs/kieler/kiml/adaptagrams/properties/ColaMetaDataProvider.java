package de.cau.cs.kieler.kiml.adaptagrams.properties;

import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class ColaMetaDataProvider implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #IDEAL_EDGE_LENGTH}.
   */
  private final static double IDEAL_EDGE_LENGTH_DEFAULT = 100;
  
  /**
   * Lower bound value for {@link #IDEAL_EDGE_LENGTH}.
   */
  private final static Comparable<? super Double> IDEAL_EDGE_LENGTH_LOWER_BOUND = Double.valueOf(0.0);
  
  /**
   * The ideal length of all edges.
   */
  public final static IProperty<Double> IDEAL_EDGE_LENGTH = new Property<Double>(
            "de.cau.cs.kieler.cola.idealEdgeLength",
            IDEAL_EDGE_LENGTH_DEFAULT,
            IDEAL_EDGE_LENGTH_LOWER_BOUND,
            null);
  
  /**
   * Default value for {@link #AVOID_OVERLAPS}.
   */
  private final static boolean AVOID_OVERLAPS_DEFAULT = true;
  
  /**
   * Causes non-overlap constraints to be generated for all rectangles, if it is set to true.
   */
  public final static IProperty<Boolean> AVOID_OVERLAPS = new Property<Boolean>(
            "de.cau.cs.kieler.cola.avoidOverlaps",
            AVOID_OVERLAPS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #CONSIDER_PREVIOUS_POSITIONS}.
   */
  private final static boolean CONSIDER_PREVIOUS_POSITIONS_DEFAULT = false;
  
  /**
   * If activated, prior node positons are considered during the layout
   */
  public final static IProperty<Boolean> CONSIDER_PREVIOUS_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.cola.considerPreviousPositions",
            CONSIDER_PREVIOUS_POSITIONS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ADAPT_PORT_POSITIONS}.
   */
  private final static boolean ADAPT_PORT_POSITIONS_DEFAULT = false;
  
  /**
   * Whether ports should be moved to the point where edges cross the node&apos;s bounds.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.cola.adaptPortPositions",
            ADAPT_PORT_POSITIONS_DEFAULT,
            null,
            null);
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutOptionData(
        "de.cau.cs.kieler.cola.idealEdgeLength",
        "",
        "Ideal Edge Length",
        "The ideal length of all edges.",
        IDEAL_EDGE_LENGTH_DEFAULT,
        IDEAL_EDGE_LENGTH_LOWER_BOUND,
        null,
        org.eclipse.elk.core.data.LayoutOptionData.Type.DOUBLE,
        Double.class,
        EnumSet.of(org.eclipse.elk.core.data.LayoutOptionData.Target.PARENTS, org.eclipse.elk.core.data.LayoutOptionData.Target.EDGES),
        org.eclipse.elk.core.data.LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new org.eclipse.elk.core.data.LayoutOptionData(
        "de.cau.cs.kieler.cola.avoidOverlaps",
        "",
        "Avoid Overlaps",
        "Causes non-overlap constraints to be generated for all rectangles, if it is set to true.",
        AVOID_OVERLAPS_DEFAULT,
        null,
        null,
        org.eclipse.elk.core.data.LayoutOptionData.Type.BOOLEAN,
        Boolean.class,
        EnumSet.of(org.eclipse.elk.core.data.LayoutOptionData.Target.PARENTS),
        org.eclipse.elk.core.data.LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new org.eclipse.elk.core.data.LayoutOptionData(
        "de.cau.cs.kieler.cola.considerPreviousPositions",
        "",
        "Consider Previous Node Positions",
        "If activated, prior node positons are considered during the layout",
        CONSIDER_PREVIOUS_POSITIONS_DEFAULT,
        null,
        null,
        org.eclipse.elk.core.data.LayoutOptionData.Type.BOOLEAN,
        Boolean.class,
        EnumSet.of(org.eclipse.elk.core.data.LayoutOptionData.Target.PARENTS),
        org.eclipse.elk.core.data.LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new org.eclipse.elk.core.data.LayoutOptionData(
        "de.cau.cs.kieler.cola.adaptPortPositions",
        "",
        "Adapt Port Positions",
        "Whether ports should be moved to the point where edges cross the node&apos;s bounds.",
        ADAPT_PORT_POSITIONS_DEFAULT,
        null,
        null,
        org.eclipse.elk.core.data.LayoutOptionData.Type.BOOLEAN,
        Boolean.class,
        EnumSet.of(org.eclipse.elk.core.data.LayoutOptionData.Target.PARENTS),
        org.eclipse.elk.core.data.LayoutOptionData.Visibility.VISIBLE
    ));
    new de.cau.cs.kieler.kiml.adaptagrams.properties.ColaOptions().apply(registry);
  }
}
