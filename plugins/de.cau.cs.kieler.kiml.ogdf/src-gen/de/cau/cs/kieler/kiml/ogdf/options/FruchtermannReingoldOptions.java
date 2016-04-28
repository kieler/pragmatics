package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class FruchtermannReingoldOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Fruchterman-Reingold".
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
   * Default value for {@link #ITERATIONS} with algorithm "Fruchterman-Reingold".
   */
  private final static int ITERATIONS_DEFAULT = 400;
  
  /**
   * Property constant to access Iterations from within the layout algorithm code.
   */
  public final static IProperty<Integer> ITERATIONS = new Property<Integer>(
                                OgdfMetaDataProvider.ITERATIONS,
                                ITERATIONS_DEFAULT);
  
  /**
   * Property constant to access Fineness from within the layout algorithm code.
   */
  public final static IProperty<Float> FINENESS = OgdfMetaDataProvider.FINENESS;
  
  /**
   * Property constant to access Noise from within the layout algorithm code.
   */
  public final static IProperty<Boolean> NOISE = OgdfMetaDataProvider.NOISE;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Fruchterman-Reingold".
   */
  private final static float SPACING_NODE_DEFAULT = 20;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Fruchterman-Reingold".
   */
  private final static float ASPECT_RATIO_DEFAULT = 1.3f;
  
  /**
   * Property constant to access Aspect Ratio from within the layout algorithm code.
   */
  public final static IProperty<Float> ASPECT_RATIO = new Property<Float>(
                                CoreOptions.ASPECT_RATIO,
                                ASPECT_RATIO_DEFAULT);
  
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
   * Property constant to access Scale Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> SCALE_FUNCTION_FACTOR = OgdfMetaDataProvider.SCALE_FUNCTION_FACTOR;
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "Fruchterman-Reingold",
        "Force-based algorithm after Fruchterman and Reingold 1991. This is a modification of the spring-embedder model of Eades for undirected graphs with straight edges. The heuristic strives for uniform edge lengths, and is developed in analogy to forces in natural systems.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "FRUCHTERMAN_REINGOLD"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/fruchterman_reingold.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.iterations",
        ITERATIONS_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.fineness",
        FINENESS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.noise",
        NOISE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.aspectRatio",
        ASPECT_RATIO_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.scaleFunctionFactor",
        SCALE_FUNCTION_FACTOR.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
