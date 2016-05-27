/**
 * Declarations for the OGDF layouters and properties.
 */
package de.cau.cs.kieler.kiml.ogdf.options;

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
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class OgdfMetaDataProvider implements ILayoutMetaDataProvider {
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
        Integer.class,
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
        Float.class,
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
        Float.class,
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
        Integer.class,
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
        Float.class,
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
        Float.class,
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
        Integer.class,
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
        Boolean.class,
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
        Boolean.class,
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
        Float.class,
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
        Integer.class,
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
        Integer.class,
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
        Integer.class,
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
        Float.class,
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
        Float.class,
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
        Boolean.class,
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
        Float.class,
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
        Integer.class,
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
        Float.class,
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
        Float.class,
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
        Float.class,
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
        Float.class,
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
        Float.class,
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
        Float.class,
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
        Boolean.class,
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
        Boolean.class,
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
        Integer.class,
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
        Float.class,
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
        Boolean.class,
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
        Boolean.class,
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
        Float.class,
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
        Integer.class,
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
        Float.class,
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
        Boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    new de.cau.cs.kieler.kiml.ogdf.options.SugiyamaOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.PlanarizationOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.FmmmOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.DavidsonHarelOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.FruchtermannReingoldOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.GemOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.CircularOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.TreeOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.RadialTreeOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.UpwardPlanarizationOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.FastMultipoleOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.FastMultipoleMultilevelOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.KamadaKawaiOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.StressMajorizationOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.DominanceOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.VisibilityOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.FraysseixPachPollackOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.SchnyderOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.CanonicalOrderOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.MixedModelOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.ConvexGridOptions().apply(registry);
    new de.cau.cs.kieler.kiml.ogdf.options.BalloonOptions().apply(registry);
  }
}
