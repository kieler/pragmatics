package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.EdgeInsertionModule;
import de.cau.cs.kieler.kiml.ogdf.options.EmbedderModule;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class PlanarizationOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #DIRECTION} with algorithm "Planarization".
   */
  private final static Direction DIRECTION_DEFAULT = Direction.UP;
  
  /**
   * Property constant to access Direction from within the layout algorithm code.
   */
  public final static IProperty<Direction> DIRECTION = new Property<Direction>(
                                CoreOptions.DIRECTION,
                                DIRECTION_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Planarization".
   */
  private final static float SPACING_NODE_DEFAULT = 20;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Planarization".
   */
  private final static int RANDOM_SEED_DEFAULT = 1;
  
  /**
   * Property constant to access Randomization Seed from within the layout algorithm code.
   */
  public final static IProperty<Integer> RANDOM_SEED = new Property<Integer>(
                                CoreOptions.RANDOM_SEED,
                                RANDOM_SEED_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Planarization".
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
   * Default value for {@link #ASPECT_RATIO} with algorithm "Planarization".
   */
  private final static float ASPECT_RATIO_DEFAULT = 1.3f;
  
  /**
   * Property constant to access Aspect Ratio from within the layout algorithm code.
   */
  public final static IProperty<Float> ASPECT_RATIO = new Property<Float>(
                                CoreOptions.ASPECT_RATIO,
                                ASPECT_RATIO_DEFAULT);
  
  /**
   * Property constant to access Preprocess Cliques from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PREPROCESS_CLIQUES = OgdfMetaDataProvider.PREPROCESS_CLIQUES;
  
  /**
   * Property constant to access Minimum Clique Size from within the layout algorithm code.
   */
  public final static IProperty<Integer> MIN_CLIQUE_SIZE = OgdfMetaDataProvider.MIN_CLIQUE_SIZE;
  
  /**
   * Property constant to access Cost for Association Edges from within the layout algorithm code.
   */
  public final static IProperty<Integer> COST_ASSOC = OgdfMetaDataProvider.COST_ASSOC;
  
  /**
   * Property constant to access Cost for Generalization Edges from within the layout algorithm code.
   */
  public final static IProperty<Integer> COST_GEN = OgdfMetaDataProvider.COST_GEN;
  
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
   * Property constant to access Runs from within the layout algorithm code.
   */
  public final static IProperty<Integer> RUNS = OgdfMetaDataProvider.RUNS;
  
  /**
   * Property constant to access Edge Insertion Module from within the layout algorithm code.
   */
  public final static IProperty<EdgeInsertionModule> EDGE_INSERTION = OgdfMetaDataProvider.EDGE_INSERTION;
  
  /**
   * Property constant to access Embedder Module from within the layout algorithm code.
   */
  public final static IProperty<EmbedderModule> EMBEDDER = OgdfMetaDataProvider.EMBEDDER;
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "Planarization",
        "A planarization-based algorithm based on Gutwenger and Mutzel \'04. The main emphasis is put on the minimization of the number of edge crossings. Edges are routed orthogonally, which is achieved through the &quot;topology-shape-metrics&quot; approach by Batini, Nardelli and Tamassia \'86. The algorithm also contains special treatment for mixed-upward drawings in order to support UML class diagrams, where generalizations need to be handled differently from other edge types.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "PLANARIZATION"),
        "org.eclipse.elk.orthogonal",
        "OGDF",
        "images/planarization.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.direction",
        DIRECTION_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.randomSeed",
        RANDOM_SEED_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.aspectRatio",
        ASPECT_RATIO_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.preprocessCliques",
        PREPROCESS_CLIQUES.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.minCliqueSize",
        MIN_CLIQUE_SIZE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.costAssoc",
        COST_ASSOC.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.costGen",
        COST_GEN.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        RUNS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.edgeInsertion",
        EDGE_INSERTION.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        EMBEDDER.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
