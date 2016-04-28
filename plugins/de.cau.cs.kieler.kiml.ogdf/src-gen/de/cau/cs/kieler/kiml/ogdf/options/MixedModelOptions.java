package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.CrossBeautifModule;
import de.cau.cs.kieler.kiml.ogdf.options.EdgeInsertionModule;
import de.cau.cs.kieler.kiml.ogdf.options.EmbedderModule;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class MixedModelOptions implements ILayoutMetaDataProvider {
  /**
   * Property constant to access Place Labels from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PLACE_LABELS = OgdfMetaDataProvider.PLACE_LABELS;
  
  /**
   * Property constant to access Label Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_EDGE_DISTANCE = OgdfMetaDataProvider.LABEL_EDGE_DISTANCE;
  
  /**
   * Property constant to access Label Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_MARGIN_DISTANCE = OgdfMetaDataProvider.LABEL_MARGIN_DISTANCE;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Mixed Model".
   */
  private final static float SPACING_NODE_DEFAULT = 30;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Property constant to access Runs from within the layout algorithm code.
   */
  public final static IProperty<Integer> RUNS = OgdfMetaDataProvider.RUNS;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Mixed Model".
   */
  private final static int RANDOM_SEED_DEFAULT = 1;
  
  /**
   * Property constant to access Randomization Seed from within the layout algorithm code.
   */
  public final static IProperty<Integer> RANDOM_SEED = new Property<Integer>(
                                CoreOptions.RANDOM_SEED,
                                RANDOM_SEED_DEFAULT);
  
  /**
   * Property constant to access Edge Insertion Module from within the layout algorithm code.
   */
  public final static IProperty<EdgeInsertionModule> EDGE_INSERTION = OgdfMetaDataProvider.EDGE_INSERTION;
  
  /**
   * Property constant to access Embedder Module from within the layout algorithm code.
   */
  public final static IProperty<EmbedderModule> EMBEDDER = OgdfMetaDataProvider.EMBEDDER;
  
  /**
   * Property constant to access Crossing Beautifier from within the layout algorithm code.
   */
  public final static IProperty<CrossBeautifModule> CROSSING_BEAUTIFIER = OgdfMetaDataProvider.CROSSING_BEAUTIFIER;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Mixed Model".
   */
  private final static float ASPECT_RATIO_DEFAULT = 1.3f;
  
  /**
   * Property constant to access Aspect Ratio from within the layout algorithm code.
   */
  public final static IProperty<Float> ASPECT_RATIO = new Property<Float>(
                                CoreOptions.ASPECT_RATIO,
                                ASPECT_RATIO_DEFAULT);
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "Mixed Model",
        "The Mixed-Model layout algorithm by Gutwenger and Mutzel 1998, which is based upon ideas by Kant. In particular, Kant&apos;s algorithm has been changed concerning the placement phase and the vertex boxes, and it has been generalized to work for connected planar graphs. This algorithm draws a d-planar graph on a grid such that every edge has at most three bends and the minimum angle between two edges is at least 2/d radians. The algorithm runs in several phases. In the preprocessing phase, vertices with degree one are temporarily deleted and the graph is being augmented to a biconnected planar graph using a planar biconnectivity augmentation module. Then, a shelling order for biconnected plane graphs is computed. In the next step, boxes around each vertex are defined in such a way that the incident edges appear regularly along the box. Finally, the coordinates of the vertex boxes are computed taking the degree-one vertices into account. The input graph must not contain multi-edges.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "MIXED_MODEL"),
        "org.eclipse.elk.orthogonal",
        "OGDF",
        "images/mixed_model.png",
        EnumSet.of(GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        RUNS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "org.eclipse.elk.randomSeed",
        RANDOM_SEED_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.edgeInsertion",
        EDGE_INSERTION.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        EMBEDDER.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.crossingBeautifier",
        CROSSING_BEAUTIFIER.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "org.eclipse.elk.aspectRatio",
        ASPECT_RATIO_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
