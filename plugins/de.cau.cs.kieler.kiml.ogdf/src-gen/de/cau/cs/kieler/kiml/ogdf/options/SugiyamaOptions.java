package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.AcyclicSubgraphModule;
import de.cau.cs.kieler.kiml.ogdf.options.CrossMinModule;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import de.cau.cs.kieler.kiml.ogdf.options.RankingModule;
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
public class SugiyamaOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Sugiyama".
   */
  private final static float SPACING_NODE_DEFAULT = 16;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Sugiyama".
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
   * Default value for {@link #RANDOM_SEED} with algorithm "Sugiyama".
   */
  private final static int RANDOM_SEED_DEFAULT = 1;
  
  /**
   * Property constant to access Randomization Seed from within the layout algorithm code.
   */
  public final static IProperty<Integer> RANDOM_SEED = new Property<Integer>(
                                CoreOptions.RANDOM_SEED,
                                RANDOM_SEED_DEFAULT);
  
  /**
   * Property constant to access Fails from within the layout algorithm code.
   */
  public final static IProperty<Integer> FAILS = OgdfMetaDataProvider.FAILS;
  
  /**
   * Default value for {@link #RUNS} with algorithm "Sugiyama".
   */
  private final static int RUNS_DEFAULT = 15;
  
  /**
   * Property constant to access Runs from within the layout algorithm code.
   */
  public final static IProperty<Integer> RUNS = new Property<Integer>(
                                OgdfMetaDataProvider.RUNS,
                                RUNS_DEFAULT);
  
  /**
   * Property constant to access Transpose from within the layout algorithm code.
   */
  public final static IProperty<Boolean> TRANSPOSE = OgdfMetaDataProvider.TRANSPOSE;
  
  /**
   * Default value for {@link #SEPARATE_CONNECTED_COMPONENTS} with algorithm "Sugiyama".
   */
  private final static boolean SEPARATE_CONNECTED_COMPONENTS_DEFAULT = false;
  
  /**
   * Property constant to access Separate Connected Components from within the layout algorithm code.
   */
  public final static IProperty<Boolean> SEPARATE_CONNECTED_COMPONENTS = new Property<Boolean>(
                                CoreOptions.SEPARATE_CONNECTED_COMPONENTS,
                                SEPARATE_CONNECTED_COMPONENTS_DEFAULT);
  
  /**
   * Property constant to access Component Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> MIN_DIST_C_C = OgdfMetaDataProvider.MIN_DIST_C_C;
  
  /**
   * Property constant to access Level Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> MIN_DIST_LEVEL = OgdfMetaDataProvider.MIN_DIST_LEVEL;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Sugiyama".
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
   * Property constant to access Acyclic Subgraph Module from within the layout algorithm code.
   */
  public final static IProperty<AcyclicSubgraphModule> ACYCLIC_SUBGRAPH = OgdfMetaDataProvider.ACYCLIC_SUBGRAPH;
  
  /**
   * Property constant to access Layering Module from within the layout algorithm code.
   */
  public final static IProperty<RankingModule> RANKING = OgdfMetaDataProvider.RANKING;
  
  /**
   * Property constant to access Layering Width from within the layout algorithm code.
   */
  public final static IProperty<Integer> WIDTH = OgdfMetaDataProvider.WIDTH;
  
  /**
   * Property constant to access Crossing Min. Module from within the layout algorithm code.
   */
  public final static IProperty<CrossMinModule> CROSS_MIN = OgdfMetaDataProvider.CROSS_MIN;
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "Sugiyama",
        "Sugiyama&apos;s layout algorithm, based on Gansner et al. 1993 and Sander 1996. This method emphasizes the direction of edges by reversing as few edges as possible. Nodes are arranged in horizontal layers.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "SUGIYAMA"),
        "org.eclipse.elk.layered",
        "OGDF",
        "images/sugiyama.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.randomSeed",
        RANDOM_SEED_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.fails",
        FAILS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        RUNS_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.transpose",
        TRANSPOSE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.separateConnectedComponents",
        SEPARATE_CONNECTED_COMPONENTS_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        MIN_DIST_C_C.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        MIN_DIST_LEVEL.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.aspectRatio",
        ASPECT_RATIO_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.acyclicSubgraph",
        ACYCLIC_SUBGRAPH.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.ranking",
        RANKING.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.width",
        WIDTH.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.crossMin",
        CROSS_MIN.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
