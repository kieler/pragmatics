/**
 * Declarations for the OGDF layouters and properties.
 */
package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.AcyclicSubgraphModule;
import de.cau.cs.kieler.kiml.ogdf.options.AttractionFormula;
import de.cau.cs.kieler.kiml.ogdf.options.Costs;
import de.cau.cs.kieler.kiml.ogdf.options.CrossBeautifModule;
import de.cau.cs.kieler.kiml.ogdf.options.CrossMinModule;
import de.cau.cs.kieler.kiml.ogdf.options.EdgeInsertionModule;
import de.cau.cs.kieler.kiml.ogdf.options.EmbedderModule;
import de.cau.cs.kieler.kiml.ogdf.options.QualityVsSpeed;
import de.cau.cs.kieler.kiml.ogdf.options.RankingModule;
import de.cau.cs.kieler.kiml.ogdf.options.Speed;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class OgdfOptions implements ILayoutMetaDataProvider {
  /**
   * The number of iterations performed by the algorithm
   */
  public final static IProperty<Integer> ITERATIONS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.iterations");
  
  /**
   * Default value for {@link #LABEL_MARGIN_DISTANCE}.
   */
  private final static float LABEL_MARGIN_DISTANCE_DEFAULT = 15;
  
  /**
   * Distance of edge labels to the nodes
   */
  public final static IProperty<Float> LABEL_MARGIN_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
            LABEL_MARGIN_DISTANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #LABEL_EDGE_DISTANCE}.
   */
  private final static float LABEL_EDGE_DISTANCE_DEFAULT = 15;
  
  /**
   * Spacing of edge labels to the edge
   */
  public final static IProperty<Float> LABEL_EDGE_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
            LABEL_EDGE_DISTANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #COSTS}.
   */
  private final static Costs COSTS_DEFAULT = Costs.STANDARD;
  
  /**
   * Defines the costs used in the algorithm
   */
  public final static IProperty<Costs> COSTS = new Property<Costs>(
            "de.cau.cs.kieler.kiml.ogdf.costs",
            COSTS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #FAILS}.
   */
  private final static int FAILS_DEFAULT = 4;
  
  /**
   * The number of times that the number of crossings may not decrease after a complete
   * top-down bottom-up traversal, before a run is terminated
   */
  public final static IProperty<Integer> FAILS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.fails",
            FAILS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #FINENESS}.
   */
  private final static float FINENESS_DEFAULT = 0.51f;
  
  /**
   * The fineness option used in the algorithm
   */
  public final static IProperty<Float> FINENESS = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.fineness",
            FINENESS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #INITIAL_TEMPERATURE}.
   */
  private final static float INITIAL_TEMPERATURE_DEFAULT = 10.0f;
  
  /**
   * The initial temperature
   */
  public final static IProperty<Float> INITIAL_TEMPERATURE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.initialTemperature",
            INITIAL_TEMPERATURE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #MIN_CLIQUE_SIZE}.
   */
  private final static int MIN_CLIQUE_SIZE_DEFAULT = 10;
  
  /**
   * If preprocessing of cliques is enabled, this option determines the minimal size
   * of cliques to search for
   */
  public final static IProperty<Integer> MIN_CLIQUE_SIZE = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.minCliqueSize",
            MIN_CLIQUE_SIZE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #NEW_INITIAL_PLACEMENT}.
   */
  private final static boolean NEW_INITIAL_PLACEMENT_DEFAULT = false;
  
  /**
   * Sets whether the initial placement is different on every algorithm call
   */
  public final static IProperty<Boolean> NEW_INITIAL_PLACEMENT = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.newInitialPlacement",
            NEW_INITIAL_PLACEMENT_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #PREPROCESS_CLIQUES}.
   */
  private final static boolean PREPROCESS_CLIQUES_DEFAULT = false;
  
  /**
   * If set to true, a preprocessing for cliques (complete subgraphs) is performed and cliques
   * will be laid out in a special form (straight-line, not orthogonal). The preprocessing
   * may reduce running time and improve layout quality if the input graphs contains dense subgraphs.
   */
  public final static IProperty<Boolean> PREPROCESS_CLIQUES = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.preprocessCliques",
            PREPROCESS_CLIQUES_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #QUALITY_VS_SPEED}.
   */
  private final static QualityVsSpeed QUALITY_VS_SPEED_DEFAULT = QualityVsSpeed.BEAUTIFULANDFAST;
  
  /**
   * Specifies whether the algorithm prioritizes quality or speed
   */
  public final static IProperty<QualityVsSpeed> QUALITY_VS_SPEED = new Property<QualityVsSpeed>(
            "de.cau.cs.kieler.kiml.ogdf.qualityVsSpeed",
            QUALITY_VS_SPEED_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #MIN_DIST_C_C}.
   */
  private final static float MIN_DIST_C_C_DEFAULT = 1.0f;
  
  /**
   * Factor for the spacing between connected components
   */
  public final static IProperty<Float> MIN_DIST_C_C = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.minDistCC",
            MIN_DIST_C_C_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #COST_ASSOC}.
   */
  private final static int COST_ASSOC_DEFAULT = 1;
  
  /**
   * Defines the costs for association edges
   */
  public final static IProperty<Integer> COST_ASSOC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.costAssoc",
            COST_ASSOC_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #COST_GEN}.
   */
  private final static int COST_GEN_DEFAULT = 4;
  
  /**
   * Defines the costs for generalization edges
   */
  public final static IProperty<Integer> COST_GEN = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.costGen",
            COST_GEN_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #RUNS}.
   */
  private final static int RUNS_DEFAULT = 0;
  
  /**
   * Determines how many times the crossing minimization phase (Sugiyama)
   * or planar subgraph phase (Planarization) is repeated.
   */
  public final static IProperty<Integer> RUNS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.runs",
            RUNS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #SPEED}.
   */
  private final static Speed SPEED_DEFAULT = Speed.MEDIUM;
  
  /**
   * Defines the temperature and the number of iterations
   */
  public final static IProperty<Speed> SPEED = new Property<Speed>(
            "de.cau.cs.kieler.kiml.ogdf.speed",
            SPEED_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ROTATION_ANGLE}.
   */
  private final static float ROTATION_ANGLE_DEFAULT = 1.047f;
  
  /**
   * The opening angle for rotations in radians (between 0 and pi/2)
   */
  public final static IProperty<Float> ROTATION_ANGLE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.rotationAngle",
            ROTATION_ANGLE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ROTATION_SENSITIVITY}.
   */
  private final static float ROTATION_SENSITIVITY_DEFAULT = 0.01f;
  
  /**
   * The rotation sensitivity (between 0 and 1)
   */
  public final static IProperty<Float> ROTATION_SENSITIVITY = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.rotationSensitivity",
            ROTATION_SENSITIVITY_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #TRANSPOSE}.
   */
  private final static boolean TRANSPOSE_DEFAULT = true;
  
  /**
   * Determines whether the transpose step is performed after each 2-layer crossing minimization;
   * this step tries to reduce the number of crossings by switching neighbored nodes on a layer
   */
  public final static IProperty<Boolean> TRANSPOSE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.transpose",
            TRANSPOSE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ATTRACTION_FORMULA}.
   */
  private final static AttractionFormula ATTRACTION_FORMULA_DEFAULT = AttractionFormula.FRUCHTERMAN_REINGOLD;
  
  /**
   * The used formula for attraction
   */
  public final static IProperty<AttractionFormula> ATTRACTION_FORMULA = new Property<AttractionFormula>(
            "de.cau.cs.kieler.kiml.ogdf.attractionFormula",
            ATTRACTION_FORMULA_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #MIN_DIST_LEVEL}.
   */
  private final static float MIN_DIST_LEVEL_DEFAULT = 1.0f;
  
  /**
   * Factor for the minimal distance between father and child components
   */
  public final static IProperty<Float> MIN_DIST_LEVEL = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
            MIN_DIST_LEVEL_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #NUMBER_OF_ROUNDS}.
   */
  private final static int NUMBER_OF_ROUNDS_DEFAULT = 20000;
  
  /**
   * The maximal number of rounds per node
   */
  public final static IProperty<Integer> NUMBER_OF_ROUNDS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.numberOfRounds",
            NUMBER_OF_ROUNDS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #OSCILLATION_ANGLE}.
   */
  private final static float OSCILLATION_ANGLE_DEFAULT = 1.57f;
  
  /**
   * The opening angle for oscillations in radians (between 0 and pi/2)
   */
  public final static IProperty<Float> OSCILLATION_ANGLE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.oscillationAngle",
            OSCILLATION_ANGLE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #OSCILLATION_SENSITIVITY}.
   */
  private final static float OSCILLATION_SENSITIVITY_DEFAULT = 0.3f;
  
  /**
   * The oscillation sensitivity (between 0 and 1)
   */
  public final static IProperty<Float> OSCILLATION_SENSITIVITY = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.oscillationSensitivity",
            OSCILLATION_SENSITIVITY_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #MINIMAL_TEMPERATURE}.
   */
  private final static float MINIMAL_TEMPERATURE_DEFAULT = 0.005f;
  
  /**
   * The minimal temperature
   */
  public final static IProperty<Float> MINIMAL_TEMPERATURE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.minimalTemperature",
            MINIMAL_TEMPERATURE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #MAXIMAL_DISTURBANCE}.
   */
  private final static float MAXIMAL_DISTURBANCE_DEFAULT = 0.0f;
  
  /**
   * The maximal disturbance.
   */
  public final static IProperty<Float> MAXIMAL_DISTURBANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.maximalDisturbance",
            MAXIMAL_DISTURBANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #GRAVITATIONAL_CONSTANT}.
   */
  private final static float GRAVITATIONAL_CONSTANT_DEFAULT = 0.0625f;
  
  /**
   * The gravitational constant
   */
  public final static IProperty<Float> GRAVITATIONAL_CONSTANT = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.gravitationalConstant",
            GRAVITATIONAL_CONSTANT_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #SUBTREE_DISTANCE}.
   */
  private final static float SUBTREE_DISTANCE_DEFAULT = 1.0f;
  
  /**
   * Factor for the horizontal spacing between adjacent subtrees
   */
  public final static IProperty<Float> SUBTREE_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.subtreeDistance",
            SUBTREE_DISTANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #NOISE}.
   */
  private final static boolean NOISE_DEFAULT = true;
  
  /**
   * If set to true, small random perturbations are performed
   */
  public final static IProperty<Boolean> NOISE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.noise",
            NOISE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #PLACE_LABELS}.
   */
  private final static boolean PLACE_LABELS_DEFAULT = true;
  
  /**
   * Whether edge labels shall be processed
   */
  public final static IProperty<Boolean> PLACE_LABELS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.placeLabels",
            PLACE_LABELS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #MULTIPOLE_PREC}.
   */
  private final static int MULTIPOLE_PREC_DEFAULT = 4;
  
  /**
   * The number of coefficients for expansions
   */
  public final static IProperty<Integer> MULTIPOLE_PREC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.multipolePrec",
            MULTIPOLE_PREC_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #STOP_TOLERANCE}.
   */
  private final static float STOP_TOLERANCE_DEFAULT = 0.001f;
  
  /**
   * Tolerance below which the system is regarded stable and the optimization is stopped
   */
  public final static IProperty<Float> STOP_TOLERANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.stopTolerance",
            STOP_TOLERANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #UPWARD}.
   */
  private final static boolean UPWARD_DEFAULT = true;
  
  /**
   * Add upward constraints
   */
  public final static IProperty<Boolean> UPWARD = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.upward",
            UPWARD_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #RADIAL}.
   */
  private final static boolean RADIAL_DEFAULT = false;
  
  /**
   * Add radial constraints
   */
  public final static IProperty<Boolean> RADIAL = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.radial",
            RADIAL_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #BASE_RATIO}.
   */
  private final static float BASE_RATIO_DEFAULT = 0.33f;
  
  /**
   * Ratio of the nodes on the external face giving a limit
   * for the number of nodes placed on the base line
   */
  public final static IProperty<Float> BASE_RATIO = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.baseRatio",
            BASE_RATIO_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ACYCLIC_SUBGRAPH}.
   */
  private final static AcyclicSubgraphModule ACYCLIC_SUBGRAPH_DEFAULT = AcyclicSubgraphModule.DFS;
  
  /**
   * The module for finding an acyclic subgraph
   */
  public final static IProperty<AcyclicSubgraphModule> ACYCLIC_SUBGRAPH = new Property<AcyclicSubgraphModule>(
            "de.cau.cs.kieler.kiml.ogdf.acyclicSubgraph",
            ACYCLIC_SUBGRAPH_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #RANKING}.
   */
  private final static RankingModule RANKING_DEFAULT = RankingModule.LONGEST_PATH;
  
  /**
   * The module for computing a layering (ranking) of the graph
   */
  public final static IProperty<RankingModule> RANKING = new Property<RankingModule>(
            "de.cau.cs.kieler.kiml.ogdf.ranking",
            RANKING_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #WIDTH}.
   */
  private final static int WIDTH_DEFAULT = 7;
  
  /**
   * The maximal width of the Coffman-Graham layering
   */
  public final static IProperty<Integer> WIDTH = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.width",
            WIDTH_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #CROSS_MIN}.
   */
  private final static CrossMinModule CROSS_MIN_DEFAULT = CrossMinModule.BARYCENTER;
  
  /**
   * The module for crossing minimization used in the layer-sweep method
   */
  public final static IProperty<CrossMinModule> CROSS_MIN = new Property<CrossMinModule>(
            "de.cau.cs.kieler.kiml.ogdf.crossMin",
            CROSS_MIN_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #EDGE_INSERTION}.
   */
  private final static EdgeInsertionModule EDGE_INSERTION_DEFAULT = EdgeInsertionModule.FIXED_EMB;
  
  /**
   * The module for edge insertion used for planarization
   */
  public final static IProperty<EdgeInsertionModule> EDGE_INSERTION = new Property<EdgeInsertionModule>(
            "de.cau.cs.kieler.kiml.ogdf.edgeInsertion",
            EDGE_INSERTION_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #EMBEDDER}.
   */
  private final static EmbedderModule EMBEDDER_DEFAULT = EmbedderModule.SIMPLE;
  
  /**
   * The module for choosing an embedding for the graph
   */
  public final static IProperty<EmbedderModule> EMBEDDER = new Property<EmbedderModule>(
            "de.cau.cs.kieler.kiml.ogdf.embedder",
            EMBEDDER_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #SCALE_FUNCTION_FACTOR}.
   */
  private final static float SCALE_FUNCTION_FACTOR_DEFAULT = 8.0f;
  
  /**
   * Factor for scaling the bounding box of the initial layout
   */
  public final static IProperty<Float> SCALE_FUNCTION_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.scaleFunctionFactor",
            SCALE_FUNCTION_FACTOR_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #CROSSING_BEAUTIFIER}.
   */
  private final static CrossBeautifModule CROSSING_BEAUTIFIER_DEFAULT = CrossBeautifModule.LOCAL_STRETCH;
  
  /**
   * The module for crossing beautification
   */
  public final static IProperty<CrossBeautifModule> CROSSING_BEAUTIFIER = new Property<CrossBeautifModule>(
            "de.cau.cs.kieler.kiml.ogdf.crossingBeautifier",
            CROSSING_BEAUTIFIER_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ADAPT_PORT_POSITIONS}.
   */
  private final static boolean ADAPT_PORT_POSITIONS_DEFAULT = true;
  
  /**
   * Whether ports should be moved to the point where edges cross the node&apos;s bounds.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
            ADAPT_PORT_POSITIONS_DEFAULT,
            null,
            null);
  
  /**
   * Required value for dependency between {@link #LABEL_EDGE_DISTANCE} and {@link #PLACE_LABELS}.
   */
  private final static boolean LABEL_EDGE_DISTANCE_DEP_PLACE_LABELS = true;
  
  /**
   * Required value for dependency between {@link #MIN_CLIQUE_SIZE} and {@link #PREPROCESS_CLIQUES}.
   */
  private final static boolean MIN_CLIQUE_SIZE_DEP_PREPROCESS_CLIQUES = true;
  
  /**
   * Required value for dependency between {@link #WIDTH} and {@link #RANKING}.
   */
  private final static RankingModule WIDTH_DEP_RANKING = RankingModule.COFFMAN_GRAHAM;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Sugiyama".
   */
  private final static float SUGIYAMA_SUP_SPACING_NODE = 16;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Sugiyama".
   */
  private final static float SUGIYAMA_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Sugiyama".
   */
  private final static int SUGIYAMA_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #RUNS} with algorithm "Sugiyama".
   */
  private final static int SUGIYAMA_SUP_RUNS = 15;
  
  /**
   * Default value for {@link #SEPARATE_CONNECTED_COMPONENTS} with algorithm "Sugiyama".
   */
  private final static boolean SUGIYAMA_SUP_SEPARATE_CONNECTED_COMPONENTS = false;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Sugiyama".
   */
  private final static float SUGIYAMA_SUP_ASPECT_RATIO = 1.3f;
  
  /**
   * Default value for {@link #DIRECTION} with algorithm "Planarization".
   */
  private final static Direction PLANARIZATION_SUP_DIRECTION = Direction.UP;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Planarization".
   */
  private final static float PLANARIZATION_SUP_SPACING_NODE = 20;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Planarization".
   */
  private final static int PLANARIZATION_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Planarization".
   */
  private final static float PLANARIZATION_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Planarization".
   */
  private final static float PLANARIZATION_SUP_ASPECT_RATIO = 1.3f;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "FMMM".
   */
  private final static float FMMM_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Davidson-Harel".
   */
  private final static float DAVIDSON_HAREL_SUP_SPACING_NODE = 80;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Davidson-Harel".
   */
  private final static float DAVIDSON_HAREL_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Fruchterman-Reingold".
   */
  private final static float FRUCHTERMAN_REINGOLD_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #ITERATIONS} with algorithm "Fruchterman-Reingold".
   */
  private final static int FRUCHTERMAN_REINGOLD_SUP_ITERATIONS = 400;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Fruchterman-Reingold".
   */
  private final static float FRUCHTERMAN_REINGOLD_SUP_SPACING_NODE = 20;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Fruchterman-Reingold".
   */
  private final static float FRUCHTERMAN_REINGOLD_SUP_ASPECT_RATIO = 1.3f;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "GEM".
   */
  private final static float GEM_SUP_ASPECT_RATIO = 1.3f;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "GEM".
   */
  private final static float GEM_SUP_SPACING_NODE = 30;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Circular".
   */
  private final static float CIRCULAR_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Circular".
   */
  private final static float CIRCULAR_SUP_SPACING_NODE = 20;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Circular".
   */
  private final static float CIRCULAR_SUP_ASPECT_RATIO = 1.3f;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Tree".
   */
  private final static float TREE_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #DIRECTION} with algorithm "Tree".
   */
  private final static Direction TREE_SUP_DIRECTION = Direction.RIGHT;
  
  /**
   * Default value for {@link #EDGE_ROUTING} with algorithm "Tree".
   */
  private final static EdgeRouting TREE_SUP_EDGE_ROUTING = EdgeRouting.POLYLINE;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Tree".
   */
  private final static float TREE_SUP_SPACING_NODE = 20;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Radial Tree".
   */
  private final static float RADIAL_TREE_SUP_SPACING_NODE = 50;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Radial Tree".
   */
  private final static float RADIAL_TREE_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #MIN_DIST_C_C} with algorithm "Radial Tree".
   */
  private final static float RADIAL_TREE_SUP_MIN_DIST_C_C = 1.0f;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Upward-Planarization".
   */
  private final static float UPWARD_PLANARIZATION_SUP_SPACING_BORDER = 15;
  
  /**
   * Default value for {@link #MIN_DIST_LEVEL} with algorithm "Upward-Planarization".
   */
  private final static float UPWARD_PLANARIZATION_SUP_MIN_DIST_LEVEL = 16;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Upward-Planarization".
   */
  private final static float UPWARD_PLANARIZATION_SUP_SPACING_NODE = 16;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Upward-Planarization".
   */
  private final static int UPWARD_PLANARIZATION_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #ITERATIONS} with algorithm "Fast Multipole".
   */
  private final static int FAST_MULTIPOLE_SUP_ITERATIONS = 100;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Fast Multipole".
   */
  private final static int FAST_MULTIPOLE_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Fast Multipole Multilevel".
   */
  private final static int FAST_MULTIPOLE_MULTILEVEL_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Kamada-Kawai".
   */
  private final static float KAMADA_KAWAI_SUP_SPACING_NODE = 30;
  
  /**
   * Default value for {@link #ITERATIONS} with algorithm "Stress Majorization".
   */
  private final static int STRESS_MAJORIZATION_SUP_ITERATIONS = 300;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Dominance".
   */
  private final static float DOMINANCE_SUP_SPACING_NODE = 10;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Dominance".
   */
  private final static int DOMINANCE_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Visibility".
   */
  private final static float VISIBILITY_SUP_SPACING_NODE = 10;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Visibility".
   */
  private final static int VISIBILITY_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Fraysseix-Pach-Pollack".
   */
  private final static float FRAYSSEIX_PACH_POLLACK_SUP_SPACING_NODE = 30;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Schnyder".
   */
  private final static float SCHNYDER_SUP_SPACING_NODE = 30;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Canonical Order".
   */
  private final static float CANONICAL_ORDER_SUP_SPACING_NODE = 30;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Mixed Model".
   */
  private final static float MIXED_MODEL_SUP_SPACING_NODE = 30;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Mixed Model".
   */
  private final static int MIXED_MODEL_SUP_RANDOM_SEED = 1;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "Mixed Model".
   */
  private final static float MIXED_MODEL_SUP_ASPECT_RATIO = 1.3f;
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Convex Grid".
   */
  private final static float CONVEX_GRID_SUP_SPACING_NODE = 30;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.iterations",
        "",
        "Iterations",
        "The number of iterations performed by the algorithm",
        null,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        "",
        "Label Distance",
        "Distance of edge labels to the nodes",
        LABEL_MARGIN_DISTANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        "",
        "Label Spacing",
        "Spacing of edge labels to the edge",
        LABEL_EDGE_DISTANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.addDependency(
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        LABEL_EDGE_DISTANCE_DEP_PLACE_LABELS
    );
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.costs",
        "",
        "Costs",
        "Defines the costs used in the algorithm",
        COSTS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        Costs.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.fails",
        "",
        "Fails",
        "The number of times that the number of crossings may not decrease after a complete top-down bottom-up traversal, before a run is terminated",
        FAILS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.fineness",
        "",
        "Fineness",
        "The fineness option used in the algorithm",
        FINENESS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.initialTemperature",
        "",
        "Initial Temperature",
        "The initial temperature",
        INITIAL_TEMPERATURE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.minCliqueSize",
        "",
        "Minimum Clique Size",
        "If preprocessing of cliques is enabled, this option determines the minimal size of cliques to search for",
        MIN_CLIQUE_SIZE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.addDependency(
        "de.cau.cs.kieler.kiml.ogdf.minCliqueSize",
        "de.cau.cs.kieler.kiml.ogdf.preprocessCliques",
        MIN_CLIQUE_SIZE_DEP_PREPROCESS_CLIQUES
    );
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.newInitialPlacement",
        "",
        "New Initial Placement",
        "Sets whether the initial placement is different on every algorithm call",
        NEW_INITIAL_PLACEMENT_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.preprocessCliques",
        "",
        "Preprocess Cliques",
        "If set to true, a preprocessing for cliques (complete subgraphs) is performed and cliques will be laid out in a special form (straight-line, not orthogonal). The preprocessing may reduce running time and improve layout quality if the input graphs contains dense subgraphs.",
        PREPROCESS_CLIQUES_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.qualityVsSpeed",
        "",
        "Quality vs Speed",
        "Specifies whether the algorithm prioritizes quality or speed",
        QUALITY_VS_SPEED_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        QualityVsSpeed.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        "",
        "Component Spacing Factor",
        "Factor for the spacing between connected components",
        MIN_DIST_C_C_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.costAssoc",
        "",
        "Cost for Association Edges",
        "Defines the costs for association edges",
        COST_ASSOC_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.costGen",
        "",
        "Cost for Generalization Edges",
        "Defines the costs for generalization edges",
        COST_GEN_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.runs",
        "",
        "Runs",
        "Determines how many times the crossing minimization phase (Sugiyama) or planar subgraph phase (Planarization) is repeated.",
        RUNS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.speed",
        "",
        "Speed",
        "Defines the temperature and the number of iterations",
        SPEED_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        Speed.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.rotationAngle",
        "",
        "Rotation Angle",
        "The opening angle for rotations in radians (between 0 and pi/2)",
        ROTATION_ANGLE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.rotationSensitivity",
        "",
        "Rotation Sensitivity",
        "The rotation sensitivity (between 0 and 1)",
        ROTATION_SENSITIVITY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.transpose",
        "",
        "Transpose",
        "Determines whether the transpose step is performed after each 2-layer crossing minimization; this step tries to reduce the number of crossings by switching neighbored nodes on a layer",
        TRANSPOSE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.attractionFormula",
        "",
        "Attraction Formula",
        "The used formula for attraction",
        ATTRACTION_FORMULA_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        AttractionFormula.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        "",
        "Level Spacing Factor",
        "Factor for the minimal distance between father and child components",
        MIN_DIST_LEVEL_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.numberOfRounds",
        "",
        "Number Of Rounds",
        "The maximal number of rounds per node",
        NUMBER_OF_ROUNDS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.oscillationAngle",
        "",
        "Oscillation Angle",
        "The opening angle for oscillations in radians (between 0 and pi/2)",
        OSCILLATION_ANGLE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.oscillationSensitivity",
        "",
        "Oscillation Sensitivity",
        "The oscillation sensitivity (between 0 and 1)",
        OSCILLATION_SENSITIVITY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.minimalTemperature",
        "",
        "Minimal Temperature",
        "The minimal temperature",
        MINIMAL_TEMPERATURE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.maximalDisturbance",
        "",
        "Maximal Disturbance",
        "The maximal disturbance.",
        MAXIMAL_DISTURBANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.gravitationalConstant",
        "",
        "Gravitational Constant",
        "The gravitational constant",
        GRAVITATIONAL_CONSTANT_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.subtreeDistance",
        "",
        "Subtree Spacing Factor",
        "Factor for the horizontal spacing between adjacent subtrees",
        SUBTREE_DISTANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.noise",
        "",
        "Noise",
        "If set to true, small random perturbations are performed",
        NOISE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        "",
        "Place Labels",
        "Whether edge labels shall be processed",
        PLACE_LABELS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.multipolePrec",
        "",
        "Precision",
        "The number of coefficients for expansions",
        MULTIPOLE_PREC_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.stopTolerance",
        "",
        "Stop Tolerance",
        "Tolerance below which the system is regarded stable and the optimization is stopped",
        STOP_TOLERANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.upward",
        "",
        "Upward",
        "Add upward constraints",
        UPWARD_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.radial",
        "",
        "Radial",
        "Add radial constraints",
        RADIAL_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.baseRatio",
        "",
        "Base Ratio",
        "Ratio of the nodes on the external face giving a limit for the number of nodes placed on the base line",
        BASE_RATIO_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.acyclicSubgraph",
        "",
        "Acyclic Subgraph Module",
        "The module for finding an acyclic subgraph",
        ACYCLIC_SUBGRAPH_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        AcyclicSubgraphModule.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.ranking",
        "",
        "Layering Module",
        "The module for computing a layering (ranking) of the graph",
        RANKING_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        RankingModule.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.width",
        "",
        "Layering Width",
        "The maximal width of the Coffman-Graham layering",
        WIDTH_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.INT,
        int.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.addDependency(
        "de.cau.cs.kieler.kiml.ogdf.width",
        "de.cau.cs.kieler.kiml.ogdf.ranking",
        WIDTH_DEP_RANKING
    );
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.crossMin",
        "",
        "Crossing Min. Module",
        "The module for crossing minimization used in the layer-sweep method",
        CROSS_MIN_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        CrossMinModule.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.edgeInsertion",
        "",
        "Edge Insertion Module",
        "The module for edge insertion used for planarization",
        EDGE_INSERTION_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        EdgeInsertionModule.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        "",
        "Embedder Module",
        "The module for choosing an embedding for the graph",
        EMBEDDER_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        EmbedderModule.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.scaleFunctionFactor",
        "",
        "Scale Factor",
        "Factor for scaling the bounding box of the initial layout",
        SCALE_FUNCTION_FACTOR_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.crossingBeautifier",
        "",
        "Crossing Beautifier",
        "The module for crossing beautification",
        CROSSING_BEAUTIFIER_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.ENUM,
        CrossBeautifModule.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        "",
        "Adapt Port Positions",
        "Whether ports should be moved to the point where edges cross the node&apos;s bounds.",
        ADAPT_PORT_POSITIONS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
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
        SUGIYAMA_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.spacing.border",
        SUGIYAMA_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.randomSeed",
        SUGIYAMA_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.fails",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        SUGIYAMA_SUP_RUNS
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.transpose",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.separateConnectedComponents",
        SUGIYAMA_SUP_SEPARATE_CONNECTED_COMPONENTS
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.aspectRatio",
        SUGIYAMA_SUP_ASPECT_RATIO
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.acyclicSubgraph",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.ranking",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.width",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.crossMin",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.sugiyama",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
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
        PLANARIZATION_SUP_DIRECTION
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.spacing.node",
        PLANARIZATION_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.randomSeed",
        PLANARIZATION_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.spacing.border",
        PLANARIZATION_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.aspectRatio",
        PLANARIZATION_SUP_ASPECT_RATIO
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.preprocessCliques",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.minCliqueSize",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.costAssoc",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.costGen",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.edgeInsertion",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.planarization",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "FMMM",
        "The fast multipole multilevel layout algorithm, after Hachul and Juenger 2004. This force-directed drawing method is suited also for very large graphs. It is based on a combination of an efficient multilevel scheme and a strategy for approximating the repusive forces in the system by rapidly evaluating potential fields.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "FMMM"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/fmmm.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.qualityVsSpeed",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.newInitialPlacement",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "org.eclipse.elk.spacing.border",
        FMMM_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "Davidson-Harel",
        "Force-based algorithm after Davidson and Harel 1996, applying the paradigm of simulated annealing. This is applicable for undirected graphs with straight-line connections.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "DAVIDSON_HAREL"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/davidson_harel.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "org.eclipse.elk.spacing.node",
        DAVIDSON_HAREL_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "org.eclipse.elk.spacing.border",
        DAVIDSON_HAREL_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "de.cau.cs.kieler.kiml.ogdf.costs",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "de.cau.cs.kieler.kiml.ogdf.speed",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.davidsonHarel",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
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
        FRUCHTERMAN_REINGOLD_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.iterations",
        FRUCHTERMAN_REINGOLD_SUP_ITERATIONS
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.fineness",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.noise",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.spacing.node",
        FRUCHTERMAN_REINGOLD_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.aspectRatio",
        FRUCHTERMAN_REINGOLD_SUP_ASPECT_RATIO
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.scaleFunctionFactor",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fruchtermanReingold",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "GEM",
        "Energy-based &quot;Graph Embedder&quot; algorithm after Frick, Ludwig, and Mehldau 1995. The method is based on the spring-embedder paradigm and contains several heuristics to improve the convergence, including local temperatures, gravitational forces, and the detection of rotations and oscillations.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "GEM"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/gem.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.spacing.border",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.aspectRatio",
        GEM_SUP_ASPECT_RATIO
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.spacing.node",
        GEM_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.numberOfRounds",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.minimalTemperature",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.initialTemperature",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.gravitationalConstant",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.maximalDisturbance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.rotationAngle",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.oscillationAngle",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.rotationSensitivity",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.oscillationSensitivity",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.attractionFormula",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "Circular",
        "Circular layout after Dogrusoz, Madden and Madden 1997. The layouts emphasize natural group structures inherent in a graph&apos;s topology, which is well suited for ring and star network topologies. The nodes are clustered by biconnectivity or node degree and are positioned on radiating circles.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "CIRCULAR"),
        "org.eclipse.elk.circle",
        "OGDF",
        "images/circular.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "org.eclipse.elk.spacing.border",
        CIRCULAR_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "org.eclipse.elk.spacing.node",
        CIRCULAR_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.subtreeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "org.eclipse.elk.aspectRatio",
        CIRCULAR_SUP_ASPECT_RATIO
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.circular",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
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
        TREE_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.direction",
        TREE_SUP_DIRECTION
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.edgeRouting",
        TREE_SUP_EDGE_ROUTING
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.spacing.node",
        TREE_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.subtreeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.tree",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "Radial Tree",
        "Specialized algorithm for trees. Nodes are arranged radially around the root.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "RADIAL_TREE"),
        "org.eclipse.elk.tree",
        "OGDF",
        "images/radial_tree.png",
        EnumSet.of(GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "org.eclipse.elk.spacing.node",
        RADIAL_TREE_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "org.eclipse.elk.spacing.border",
        RADIAL_TREE_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        RADIAL_TREE_SUP_MIN_DIST_C_C
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.radialTree",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "Upward-Planarization",
        "Upward planarization layout by Chimani, Gutwenger, Mutzel and Wong \'10. While usual layer-based methods first determine a layering and then minimize crossings, this approach aims at directly computing an upward planar representation (UPR), where edge crossings are represented by dummy vertices. A layering is then derived from this UPR, which also induces a node order in each layer. This leads to significantly less edge crossings compared to former methods, while the overall edge direction is still preserved. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "UPWARD_PLANARIZATION"),
        "org.eclipse.elk.layered",
        "OGDF",
        "images/upward.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.spacing.border",
        UPWARD_PLANARIZATION_SUP_SPACING_BORDER
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        UPWARD_PLANARIZATION_SUP_MIN_DIST_LEVEL
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.spacing.node",
        UPWARD_PLANARIZATION_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.nodeSize.constraints",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.nodeSize.options",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.randomSeed",
        UPWARD_PLANARIZATION_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "Fast Multipole",
        "The Fast-Multipole-Embedder layout algorithm.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "FAST_MULTIPOLE"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/fast_mp.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "de.cau.cs.kieler.kiml.ogdf.multipolePrec",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "de.cau.cs.kieler.kiml.ogdf.iterations",
        FAST_MULTIPOLE_SUP_ITERATIONS
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "org.eclipse.elk.interactive",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "org.eclipse.elk.randomSeed",
        FAST_MULTIPOLE_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipole",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipoleMultilevel",
        "Fast Multipole Multilevel",
        "The Fast-Multipole-Multilevel-Embedder layout algorithm.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "FAST_MULTIPOLE_MULTILEVEL"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/fast_mp_ml.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipoleMultilevel",
        "org.eclipse.elk.randomSeed",
        FAST_MULTIPOLE_MULTILEVEL_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipoleMultilevel",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipoleMultilevel",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipoleMultilevel",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fastMultipoleMultilevel",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "Kamada-Kawai",
        "The spring-embedder layout algorithm after Kamada and Kawai 1989. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "KAMADA_KAWAI"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/kamada_kawai.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "org.eclipse.elk.spacing.node",
        KAMADA_KAWAI_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.stopTolerance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "Stress Majorization",
        "Stress majorization layout that allows radial constraints based on shortest path distances. The implementation is based on Brandes and Pich 2009. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "STRESS_MAJORIZATION"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/stress_majorization.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.iterations",
        STRESS_MAJORIZATION_SUP_ITERATIONS
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.stopTolerance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.upward",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.radial",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "Dominance",
        "Dominance layout method. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "DOMINANCE"),
        "org.eclipse.elk.planar",
        "OGDF",
        "images/dominance.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "org.eclipse.elk.spacing.node",
        DOMINANCE_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "org.eclipse.elk.randomSeed",
        DOMINANCE_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.dominance",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "Visibility",
        "Visibility layout method. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "VISIBILITY"),
        "org.eclipse.elk.planar",
        "OGDF",
        "images/visibility.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "org.eclipse.elk.spacing.node",
        VISIBILITY_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "org.eclipse.elk.randomSeed",
        VISIBILITY_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.visibility",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.fraysseixPachPollack",
        "Fraysseix-Pach-Pollack",
        "The layout algorithm by de Fraysseix, Pach, and Pollack 1990. This algorithm draws a planar graph straight-line without crossings. The algorithm runs in three phases. In the \uFB01rst phase, the graph is augmented by adding new arti\uFB01cial edges to get a triangulated plane graph. Then, a so-called shelling order (also called canonical ordering) for triangulated planar graphs is computed. In the third phase the vertices are placed incrementally according to the shelling order. The input graph must be planar and connected and must not contain multi-edges.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "FRAYSSEIX_PACH_POLLACK"),
        "org.eclipse.elk.planar",
        "OGDF",
        "images/fpp.png",
        EnumSet.of(GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fraysseixPachPollack",
        "org.eclipse.elk.spacing.node",
        FRAYSSEIX_PACH_POLLACK_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fraysseixPachPollack",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fraysseixPachPollack",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fraysseixPachPollack",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fraysseixPachPollack",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.schnyder",
        "Schnyder",
        "The layout algorithm by Schnyder 1990. This algorithm draws a planar graph straight-line without crossings. The algorithm runs in three phases. In the \uFB01rst phase, the graph is augmented by adding new arti\uFB01cial edges to get a triangulated plane graph. Then, a partition of the set of interior edges in three trees (also called Schnyder trees) with special orientation properties is derived. In the third step, the actual coordinates are computed. The input graph must be planar and connected and must not contain multi-edges.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "SCHNYDER"),
        "org.eclipse.elk.planar",
        "OGDF",
        "images/schnyder.png",
        EnumSet.of(GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.schnyder",
        "org.eclipse.elk.spacing.node",
        SCHNYDER_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.schnyder",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.schnyder",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.schnyder",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "Canonical Order",
        "A straight-line drawing algorithm for planar graphs after Kant 1996. The algorithm runs in several phases. In the first phase, the graph is augmented by adding edges to achieve a certain connectivity (e.g., biconnected or triconnected). Then, a shelling order of the the resulting graph is computed. The input graph must be planar and must not contain multi-edges.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "CANONICAL_ORDER"),
        "org.eclipse.elk.planar",
        "OGDF",
        "images/canonical_order.png",
        EnumSet.of(GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "de.cau.cs.kieler.kiml.ogdf.baseRatio",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "org.eclipse.elk.spacing.node",
        CANONICAL_ORDER_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.canonicalOrder",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
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
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "org.eclipse.elk.spacing.node",
        MIXED_MODEL_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "org.eclipse.elk.randomSeed",
        MIXED_MODEL_SUP_RANDOM_SEED
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.edgeInsertion",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.crossingBeautifier",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "org.eclipse.elk.aspectRatio",
        MIXED_MODEL_SUP_ASPECT_RATIO
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.mixedModel",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "Convex Grid",
        "A straight-line drawing algorithm for planar graphs after Chrobak and Kant 1997. The algorithm runs in several phases. In the first phase, the graph is augmented by adding edges to achieve a certain connectivity (e.g., biconnected or triconnected). Then, a shelling order of the the resulting graph is computed. The input graph must be planar and must not contain multi-edges.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "CONVEX_GRID"),
        "org.eclipse.elk.planar",
        "OGDF",
        "images/convex_grid.png",
        EnumSet.of(GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "de.cau.cs.kieler.kiml.ogdf.baseRatio",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "de.cau.cs.kieler.kiml.ogdf.embedder",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "org.eclipse.elk.spacing.node",
        CONVEX_GRID_SUP_SPACING_NODE
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.convexGrid",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.balloon",
        "Balloon",
        null,
        new AlgorithmFactory(OgdfLayoutProvider.class, "BALLOON"),
        null,
        "OGDF",
        "images/balloon.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.balloon",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.balloon",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.balloon",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.balloon",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        null
    );
  }
}
