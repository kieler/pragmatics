/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.kiml.ogdf.options.AcyclicSubgraphModule;
import de.cau.cs.kieler.kiml.ogdf.options.AttractionFormula;
import de.cau.cs.kieler.kiml.ogdf.options.Costs;
import de.cau.cs.kieler.kiml.ogdf.options.CrossBeautifModule;
import de.cau.cs.kieler.kiml.ogdf.options.CrossMinModule;
import de.cau.cs.kieler.kiml.ogdf.options.EdgeInsertionModule;
import de.cau.cs.kieler.kiml.ogdf.options.EmbedderModule;
import de.cau.cs.kieler.kiml.ogdf.options.LayoutAlgorithm;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfDirection;
import de.cau.cs.kieler.kiml.ogdf.options.Orientation;
import de.cau.cs.kieler.kiml.ogdf.options.QualityVsSpeed;
import de.cau.cs.kieler.kiml.ogdf.options.RankingModule;
import de.cau.cs.kieler.kiml.ogdf.options.Speed;
import net.ogdf.bin.OgdfServer;

/**
 * Setup for OGDF layout algorithm configurations.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class AlgorithmSetup {

    // general options used for multiple algorithms
    
    /** Spacing of edge labels to the edge. */
    public static final IProperty<Double> LABEL_EDGE_DIST = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.labelEdgeDistance", 15.0);
    /** Distance of edge labels to the nodes. */
    public static final IProperty<Double> LABEL_MARGIN_DIST = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.labelMarginDistance", 15.0);
    /** Whether edge labels shall be processed. */
    public static final IProperty<Boolean> PLACE_LABELS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.placeLabels", true);
    
    /** The targeted aspect ratio for the whole drawing. */
    public static final IProperty<Double> ASPECT_RATIO = new Property<Double>(
            CoreOptions.ASPECT_RATIO, 1.3);
    /** Factor for the spacing between connected components. */
    public static final IProperty<Double> MIN_DIST_CC = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0);
    /** Factor for the minimal distance between father and child components. */
    public static final IProperty<Double> LAYER_DISTANCE = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0);
    /** The number of iterations performed by the algorithm. */
    public static final IProperty<Integer> ITERATIONS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.iterations");
    /** Tolerance below which the system is regarded stable and the optimization is stopped. */
    public static final IProperty<Double> STOP_TOLERANCE = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.stopTolerance", 0.001);
    /** Determines how many times the crossing minimization phase (Sugiyama) or planar subgraph
     *  phase (Planarization) is repeated. */
    public static final IProperty<Integer> RUNS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.runs", 0);
    /** Whether ports should be moved to the point where edges cross the node's bounds. */
    public static final IProperty<Boolean> ADAPT_PORT_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.adaptPortPositions", true);
    
    // options for Sugiyama layouter
    
    /** Default value for the 'nodeDistance' option. */
    private static final double DEF_NODE_DISTANCE = 16.0;
    /** The number of times that the number of crossings may not decrease after a complete top-down
     *  bottom-up traversal, before a run is terminated. */
    public static final IProperty<Integer> FAILS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.fails", 4);
    /** Determines whether the transpose step is performed after each 2-layer crossing minimization. */
    public static final IProperty<Boolean> TRANSPOSE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.transpose", true);
    /** The module for finding an acyclic subgraph. */
    public static final IProperty<AcyclicSubgraphModule> ACYCLIC_SUBGRAPH
            = new Property<AcyclicSubgraphModule>("de.cau.cs.kieler.kiml.ogdf.option.acyclicSubgraph",
                    AcyclicSubgraphModule.DFS);
    /** The module for computing a layering (ranking) of the graph. */
    public static final IProperty<RankingModule> RANKING = new Property<RankingModule>(
            "de.cau.cs.kieler.kiml.ogdf.option.ranking", RankingModule.LONGEST_PATH);
    /** The maximal width of the Coffman-Graham layering. */
    public static final IProperty<Integer> WIDTH = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.width", 7);
    /** The module for crossing minimization used in the layer-sweep method. */
    public static final IProperty<CrossMinModule> CROSS_MIN = new Property<CrossMinModule>(
            "de.cau.cs.kieler.kiml.ogdf.option.crossMin", CrossMinModule.BARYCENTER);
    
    // options for planarization layouter
    
    /** Default value for the 'separation' option. */
    private static final double DEF_PLAN_SEPARATION = 20.0;
    /** If set to true, a preprocessing for cliques (complete subgraphs) is performed and cliques
     *  will be laid out in a special form (straight-line, not orthogonal). */
    public static final IProperty<Boolean> PREPROCESS_CLIQUES = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.preprocessCliques", false);
    /** If preprocessing of cliques is enabled, this option determines the minimal size of cliques. */
    public static final IProperty<Integer> MIN_CLIQUE_SIZE = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.minCliqueSize", 10);
    /** Defines the costs for association edges. */
    public static final IProperty<Integer> COST_ASSOC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.costAssoc", 1);
    /** Defines the costs for generalization edges. */
    public static final IProperty<Integer> COST_GEN = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.costGen", 4);
    /** The module for edge insertion used for planarization. */
    public static final IProperty<EdgeInsertionModule> EDGE_INSERTION
            = new Property<EdgeInsertionModule>("de.cau.cs.kieler.kiml.ogdf.option.edgeInsertion",
                    EdgeInsertionModule.FIXED_EMB);
    /** The module for choosing an embedding for the graph. */
    public static final IProperty<EmbedderModule> EMBEDDER
            = new Property<EmbedderModule>("de.cau.cs.kieler.kiml.ogdf.option.embedder",
                    EmbedderModule.SIMPLE);

    // options for FMMM layouter
    
    /** Specifies whether the algorithm prioritizes quality or speed. */
    public static final IProperty<QualityVsSpeed> QUALITY_VS_SPEED = new Property<QualityVsSpeed>(
            "de.cau.cs.kieler.kiml.ogdf.option.qualityVsSpeed", QualityVsSpeed.BEAUTIFULANDFAST);
    /** Sets whether the initial placement is different on every algorithm call. */
    public static final IProperty<Boolean> NEW_INITIAL_PLACEMENT = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.newInitialPlacement", false);
    
    // options for Davidson & Harel layouter
    
    /** Default value for the 'spacing' option. */
    private static final float DEF_DESIRED_EDGE_LENGTH = 80.0f;
    /** Defines the costs used in the Davidson-Harel algorithm. */
    public static final IProperty<Costs> COSTS = new Property<Costs>(
            "de.cau.cs.kieler.kiml.ogdf.option.costs", Costs.STANDARD);
    /** Defines the temperature and the number of iterations. */
    public static final IProperty<Speed> SPEED = new Property<Speed>(
            "de.cau.cs.kieler.kiml.ogdf.option.speed", Speed.MEDIUM);
    
    // options for Fruchterman & Reingold layouter
    
    /** The fineness option used in the Fruchterman-Reingold algorithm. */
    public static final IProperty<Double> FINENESS = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.fineness", 0.51);
    /** If set to true, small random perturbations are performed. */
    public static final IProperty<Boolean> NOISE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.noise", true);
    /** Default value for the 'minDistCC' option. */
    private static final double DEF_MIN_DIST_CC = 20.0;
    /** Factor for scaling the bounding box of the initial layout. */
    public static final IProperty<Double> SCALE_FUNCTION_FACTOR = new Property<Double>(
            "de.cau.cs.kieler.kiml.ogdf.option.scaleFunctionFactor", 8.0);
    
    // options for GEM layouter
    
    /** Default value for the 'desiredLength' option. */
    private static final double DEF_DESIRED_LENGTH = 30.0;
    /** The maximal number of rounds per node. */
    public static final IProperty<Integer> NUMBER_OF_ROUNDS = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.numberOfRounds", 20000);
    /** The minimal temperature. */
    public static final IProperty<Double> MINIMAL_TEMPERATURE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.minimalTemperature", 0.005);
    /** The initial temperature. */
    public static final IProperty<Double> INITIAL_TEMPERATURE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.initialTemperature", 10.0);
    /** The gravitational constant. */
    public static final IProperty<Double> GRAVITATIONAL_CONSTANT = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.gravitationalConstant", 0.0625);
    /** The maximal disturbance. */
    public static final IProperty<Double> MAXIMAL_DISTURBANCE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.maximalDisturbance", 0.0);
    /** The opening angle for rotations (in radians). */
    public static final IProperty<Double> ROTATION_ANGLE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.rotationAngle", 1.047);
    /** The opening angle for oscillations (in radians). */
    public static final IProperty<Double> OSCILLATION_ANGLE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationAngle", 1.57);
    /** The rotation sensitivity. */
    public static final IProperty<Double> ROTATION_SENSITIVITY = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.rotationSensitivity", 0.01);
    /** The oscillation sensitivity. */
    public static final IProperty<Double> OSCILLATION_SENSITIVITY = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationSensitivity", 0.3);
    /** The used formula for attraction. */
    public static final IProperty<AttractionFormula> ATTRACTION_FORMULA =
            new Property<AttractionFormula>("de.cau.cs.kieler.kiml.ogdf.option.attractionFormula",
                    AttractionFormula.FRUCHTERMAN_REINGOLD);
    
    // options for circular layouter
    
    /** Default value for the 'minDistCircle' option. */
    private static final double DEF_MIN_DIST_CIRCLE = 20.0;
    /** Factor for the minimal distance between father and child components. */
    public static final IProperty<Double> MIN_DIST_LEVEL = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0);
    /** factor for 'minDistSibling' option. */
    public static final IProperty<Double> MIN_DIST_SIBLING = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.subtreeDistance", 1.0);
    
    // options for tree layouter
    
    /** Default value for the 'siblingDistance' option. */
    private static final double DEF_SIBLING_DISTANCE = 20.0;
    /** factor for 'levelDistance' option. */
    public static final IProperty<Double> LEVEL_DISTANCE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0);
    /** Factor for the horizontal spacing between adjacent subtrees. */
    public static final IProperty<Double> SUBTREE_DISTANCE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.subtreeDistance", 1.0);
    /** factor for 'treeDistance' option. */
    public static final IProperty<Double> TREE_DISTANCE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0);
    
    // options for radial tree layouter
    
    /** Default value for the 'levelDistance' option. */
    private static final double DEF_TREE_SPACING = 50.0;
    /** factor for 'ccDistance' option. */
    public static final IProperty<Double> CC_DISTANCE = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0);
    
    // options for upward planarization layouter
    
    /** Default value for the 'spacing' option. */
    private static final double DEF_UPL_SPACING = 16.0;
    
    // options for fast multipole layouter
    
    /** The number of coefficients for expansions. */
    public static final IProperty<Integer> MULTIPOLE_PREC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.multipolePrec", 4);
    
    // options for the Kamada & Kawai layouter
    
    /** Default value for the 'edgeLength' option. */
    private static final double DEF_KK_EDGE_LENGTH = 30.0;
    
    // options for the stress majorization layouter
    
    /** Add upward constraints. */
    public static final IProperty<Boolean> UPWARD = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.upward", true);
    /** Add radial constraints. */
    public static final IProperty<Boolean> RADIAL = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.radial", false);
    
    // options for dominance and visibility layouters
    
    /** Default value for the 'gridDistance' option. */
    private static final double DEF_GRID_DISTANCE = 10.0;
    
    // options for FPP and Schnyder and other grid-based layouters
    
    /** Default value for the 'separation' option. */
    private static final double DEF_GRID_SEPARATION = 30.0;
    /** Ratio of the nodes on the external face giving a limit for the number of nodes placed
     *  on the base line. */
    public static final IProperty<Double> BASE_RATIO = new Property<>(
            "de.cau.cs.kieler.kiml.ogdf.option.baseRatio", 0.33);
    /** */
    public static final IProperty<CrossBeautifModule> CROSS_BEAUTIF = new Property<CrossBeautifModule>(
            "de.cau.cs.kieler.kiml.ogdf.option.crossingBeautifier", CrossBeautifModule.LOCAL_STRETCH);
    
    /**
     * Hidden constructor to prevent instantiation.
     */
    private AlgorithmSetup() {
    }
    
    /**
     * Set up the server communicator for the given algorithm.
     * 
     * @param algorithm an OGDF layout algorithm
     * @param comm the server communicator able to set option mappings
     * @param layoutNode the parent node of the layout graph
     */
    public static void setup(final LayoutAlgorithm algorithm, final OgmlServerCommunicator comm,
            final ElkNode layoutNode) {
        comm.addOption(OgdfServer.OPTION_LAYOUTER, algorithm);
        
        Integer seed = layoutNode.getProperty(CoreOptions.RANDOM_SEED);
        if (seed == null) {
            comm.addOption(OgdfServer.OPTION_RANDOM_SEED, 1);
        } else if (seed == 0) {
            comm.addOption(OgdfServer.OPTION_RANDOM_SEED, (int) System.currentTimeMillis());
        } else {
            comm.addOption(OgdfServer.OPTION_RANDOM_SEED, seed);
        }
        
        boolean processLabels = layoutNode.getProperty(PLACE_LABELS);
        if (processLabels) {
            // edge distance
            double edgeDistance = layoutNode.getProperty(LABEL_EDGE_DIST);
            if (edgeDistance < 0) {
                edgeDistance = LABEL_EDGE_DIST.getDefault();
            }
            comm.addOption(OgdfServer.OPTION_LABEL_EDGE_DISTANCE, edgeDistance);
            // margin distance
            double marginDistance = layoutNode.getProperty(LABEL_MARGIN_DIST);
            if (marginDistance < 0) {
                marginDistance = LABEL_MARGIN_DIST.getDefault();
            }
            comm.addOption(OgdfServer.OPTION_LABEL_MARGIN_DISTANCE, marginDistance);
        }
        
        switch (algorithm) {
        case SUGIYAMA: {
            // page ratio
            double pageRatio = layoutNode.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // node distance
            double nodeDistance = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (nodeDistance < 0) {
                nodeDistance = DEF_NODE_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_NODE_DISTANCE, nodeDistance);
            // number of fails
            int fails = layoutNode.getProperty(FAILS);
            comm.addOption(OgdfServer.OPTION_FAILS, fails);
            // number of runs
            int runs = layoutNode.getProperty(RUNS);
            if (runs <= 0) {
                runs = 15;    // SUPPRESS CHECKSTYLE MagicNumber
            }
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            // transpose
            boolean transpose = layoutNode.getProperty(TRANSPOSE);
            comm.addOption(OgdfServer.OPTION_TRANSPOSE, transpose);
            // arrange connected components
            Boolean arrangeCCs = layoutNode.getProperty(CoreOptions.SEPARATE_CONNECTED_COMPONENTS);
            comm.addOption(OgdfServer.OPTION_ARRANGE_CC,
                    arrangeCCs != null && arrangeCCs.booleanValue());
            // minimal distance of connected components
            double minDistCCFactor = layoutNode.getProperty(MIN_DIST_CC);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, nodeDistance * minDistCCFactor);
            // layer distance
            double layerDistanceFactor = layoutNode.getProperty(LAYER_DISTANCE);
            comm.addOption(OgdfServer.OPTION_LAYER_DISTANCE, nodeDistance * layerDistanceFactor);
            // acyclic subgraph module
            AcyclicSubgraphModule acyclicSubgraphModule = layoutNode.getProperty(ACYCLIC_SUBGRAPH);
            comm.addOption(OgdfServer.OPTION_ACYCLIC_SUBGRAPH_MODULE,
                    acyclicSubgraphModule.ordinal());
            // ranking module
            RankingModule rankingModule = layoutNode.getProperty(RANKING);
            comm.addOption(OgdfServer.OPTION_RANKING_MODULE, rankingModule.ordinal());
            // width of the ranking
            int width = layoutNode.getProperty(WIDTH);
            comm.addOption(OgdfServer.OPTION_WIDTH, width);
            // crossing minimization module
            CrossMinModule crossMinModule = layoutNode.getProperty(CROSS_MIN);
            comm.addOption(OgdfServer.OPTION_CROSS_MIN_MODULE, crossMinModule.ordinal());
            break;
        }
            
        case PLANARIZATION: {
            // page ratio
            double pageRatio = layoutNode.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // minimal spacing
            double separation = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (separation < 0) {
                separation = DEF_PLAN_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            // layout direction
            Direction direction = layoutNode.getProperty(CoreOptions.DIRECTION);
            OgdfDirection ogdfDirection = OgdfDirection.fromKielerDirection(direction);
            comm.addOption(OgdfServer.OPTION_LAYOUT_DIRECTION, ogdfDirection.ordinal());
            // preprocess cliques
            boolean preprocessCliques = layoutNode.getProperty(PREPROCESS_CLIQUES);
            comm.addOption(OgdfServer.OPTION_PREPROCESS_CLIQUES, preprocessCliques);
            // minimal clique size
            int minCliqueSize = layoutNode.getProperty(MIN_CLIQUE_SIZE);
            comm.addOption(OgdfServer.OPTION_MIN_CLIQUE_SIZE, minCliqueSize);
            // cost for association edges
            int costAssoc = layoutNode.getProperty(COST_ASSOC);
            comm.addOption(OgdfServer.OPTION_COST_ASSOC, costAssoc);
            // cost for generalization edges
            int costGen = layoutNode.getProperty(COST_GEN);
            comm.addOption(OgdfServer.OPTION_COST_GEN, costGen);
            // number of runs
            int runs = layoutNode.getProperty(RUNS);
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            // edge insertion module
            EdgeInsertionModule edgeInsertionModule = layoutNode.getProperty(EDGE_INSERTION);
            comm.addOption(OgdfServer.OPTION_EDGE_INSERTION_MODULE, edgeInsertionModule.ordinal());
            // embedder module
            EmbedderModule embedderModule = layoutNode.getProperty(EMBEDDER);
            comm.addOption(OgdfServer.OPTION_EMBEDDER_MODULE, embedderModule.ordinal());
            break;
        }
            
        case FMMM: {
            // quality vs. speed
            QualityVsSpeed qualityVsSpeed = layoutNode.getProperty(QUALITY_VS_SPEED);
            comm.addOption(OgdfServer.OPTION_QUALITY_VS_SPEED, qualityVsSpeed.ordinal());
            // new initial placement
            boolean newInitialPlacement = layoutNode.getProperty(NEW_INITIAL_PLACEMENT);
            comm.addOption(OgdfServer.OPTION_NEW_INITIAL_PLACEMENT, newInitialPlacement);
            break;
        }
            
        case DAVIDSON_HAREL: {
            // desired edge length
            double desiredEdgeLength = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (desiredEdgeLength < 0) {
                desiredEdgeLength = DEF_DESIRED_EDGE_LENGTH;
            }
            comm.addOption(OgdfServer.OPTION_EDGE_LENGTH, desiredEdgeLength);
            // costs
            Costs costs = layoutNode.getProperty(COSTS);
            comm.addOption(OgdfServer.OPTION_COSTS, costs.ordinal());
            // speed
            Speed speed = layoutNode.getProperty(SPEED);
            comm.addOption(OgdfServer.OPTION_SPEED, speed.ordinal());
            break;
        }
            
        case FRUCHTERMAN_REINGOLD: {
            // page ratio
            double pageRatio = layoutNode.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // iterations
            Integer iterations = layoutNode.getProperty(ITERATIONS);
            if (iterations != null) {
                comm.addOption(OgdfServer.OPTION_ITERATIONS, iterations);
            }
            // fineness
            double fineness = layoutNode.getProperty(FINENESS);
            comm.addOption(OgdfServer.OPTION_FINENESS, fineness);
            // noise
            boolean noise = layoutNode.getProperty(NOISE);
            comm.addOption(OgdfServer.OPTION_NOISE, noise);
            // minimal distance of connected components
            double minDistCC = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (minDistCC < 0) {
                minDistCC = DEF_MIN_DIST_CC;
            }
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCC);
            // scale function factor
            double scaleFactor = layoutNode.getProperty(SCALE_FUNCTION_FACTOR);
            comm.addOption(OgdfServer.OPTION_SCALE_FUNCTION_FACTOR, scaleFactor);
            break;
        }
            
        case GEM: {
            // page ratio
            double pageRatio = layoutNode.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // desired length
            double desiredLength = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (desiredLength < 0) {
                desiredLength = DEF_DESIRED_LENGTH;
            }
            comm.addOption(OgdfServer.OPTION_DESIRED_LENGTH, desiredLength);
            // number of rounds
            int numberOfRounds = layoutNode.getProperty(NUMBER_OF_ROUNDS);
            comm.addOption(OgdfServer.OPTION_NUMBER_OF_ROUNDS, numberOfRounds);
            // minimal temperature
            double minimalTemperature = layoutNode.getProperty(MINIMAL_TEMPERATURE);
            comm.addOption(OgdfServer.OPTION_MINIMAL_TEMPERATURE, minimalTemperature);
            // initial temperature
            double initialTemperature = layoutNode.getProperty(INITIAL_TEMPERATURE);
            comm.addOption(OgdfServer.OPTION_INITIAL_TEMPERATURE, initialTemperature);
            // gravitational constant
            double gravitationalConstant = layoutNode.getProperty(GRAVITATIONAL_CONSTANT);
            comm.addOption(OgdfServer.OPTION_GRAVITATIONAL_CONSTANT, gravitationalConstant);
            // maximal disturbance
            double maximalDisturbance = layoutNode.getProperty(MAXIMAL_DISTURBANCE);
            comm.addOption(OgdfServer.OPTION_MAXIMAL_DISTURBANCE, maximalDisturbance);
            // rotation angle
            double rotationAngle = layoutNode.getProperty(ROTATION_ANGLE);
            comm.addOption(OgdfServer.OPTION_ROTATION_ANGLE, rotationAngle);
            // oscillation angle
            double oscillationAngle = layoutNode.getProperty(OSCILLATION_ANGLE);
            comm.addOption(OgdfServer.OPTION_OSCILLATION_ANGLE, oscillationAngle);
            // rotation sensitivity
            double rotationSensitivity = layoutNode.getProperty(ROTATION_SENSITIVITY);
            comm.addOption(OgdfServer.OPTION_ROTATION_SENSITIVITY, rotationSensitivity);
            // oscillation sensitivity
            double oscillationSensitivity = layoutNode.getProperty(OSCILLATION_SENSITIVITY);
            comm.addOption(OgdfServer.OPTION_OSCILLATION_SENSITIVITY, oscillationSensitivity);
            // attraction formula
            AttractionFormula attractionFormula = layoutNode.getProperty(ATTRACTION_FORMULA);
            comm.addOption(OgdfServer.OPTION_ATTRACTION_FORMULA, attractionFormula.ordinal());
            // minimal distance of connected components
            double minDistCCFactor = layoutNode.getProperty(MIN_DIST_CC);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, desiredLength * minDistCCFactor);
            break;
        }
            
        case CIRCULAR: {
            // page ratio
            double pageRatio = layoutNode.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // minimal distance of circles
            double minDistCircle = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (minDistCircle < 0) {
                minDistCircle = DEF_MIN_DIST_CIRCLE;
            }
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CIRCLE, minDistCircle);
            // minimal distance of levels
            double minDistLevelFactor = layoutNode.getProperty(MIN_DIST_LEVEL);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_LEVEL, minDistCircle * minDistLevelFactor);
            // minimal distance of siblings
            double minDistSiblingFactor = layoutNode.getProperty(MIN_DIST_SIBLING);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_SIBLING, minDistCircle * minDistSiblingFactor);
            // minimal distance of connected components
            double minDistCCFactor = layoutNode.getProperty(MIN_DIST_CC);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCircle * minDistCCFactor);
            break;
        }
            
        case TREE: {
            // direction
            Direction direction = layoutNode.getProperty(CoreOptions.DIRECTION);
            Orientation orientation = Orientation.fromDirection(direction);
            comm.addOption(OgdfServer.OPTION_ORIENTATION, orientation.ordinal());
            // edge routing
            EdgeRouting edgeRouting = layoutNode.getProperty(CoreOptions.EDGE_ROUTING);
            boolean orthogonal = edgeRouting == EdgeRouting.ORTHOGONAL;
            comm.addOption(OgdfServer.OPTION_ORTHOGONAL, orthogonal);
            // sibling distance
            double siblingDistance = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (siblingDistance < 0) {
                siblingDistance = DEF_SIBLING_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_SIBLING_DISTANCE, siblingDistance);
            // level distance
            double levelDistanceFactor = layoutNode.getProperty(LEVEL_DISTANCE);
            comm.addOption(OgdfServer.OPTION_LEVEL_DISTANCE, siblingDistance * levelDistanceFactor);
            // subtree distance
            double subtreeDistanceFactor = layoutNode.getProperty(SUBTREE_DISTANCE);
            comm.addOption(OgdfServer.OPTION_SUBTREE_DISTANCE, siblingDistance * subtreeDistanceFactor);
            // tree distance
            double treeDistanceFactor = layoutNode.getProperty(TREE_DISTANCE);
            comm.addOption(OgdfServer.OPTION_TREE_DISTANCE, siblingDistance * treeDistanceFactor);
            break;
        }
            
        case RADIAL_TREE: {
            // level distance
            double levelDistance = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (levelDistance < 0) {
                levelDistance = DEF_TREE_SPACING;
            }
            comm.addOption(OgdfServer.OPTION_LEVEL_DISTANCE, levelDistance);
            // connected components distance
            double ccDistanceFactor = layoutNode.getProperty(CC_DISTANCE);
            comm.addOption(OgdfServer.OPTION_CC_DISTANCE, levelDistance * ccDistanceFactor);
            break;
        }
            
        case UPWARD_PLANARIZATION: {
            // the layouter crashes on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Upward-Planarization layout algorithm does not support non-connected graphs."); // SUPPRESS CHECKSTYLE LineLength
            }
            // node distance
            double nodeDistance = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (nodeDistance < 0) {
                nodeDistance = DEF_UPL_SPACING;
            }
            comm.addOption(OgdfServer.OPTION_NODE_DISTANCE, nodeDistance);
            // layer distance
            double layerDistanceFactor = layoutNode.getProperty(LAYER_DISTANCE);
            comm.addOption(OgdfServer.OPTION_LAYER_DISTANCE, nodeDistance * layerDistanceFactor);
            // number of runs
            int runs = layoutNode.getProperty(RUNS);
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            break;
        }
        
        case FAST_MULTIPOLE: {
            // multipole precision
            int multipolePrec = layoutNode.getProperty(MULTIPOLE_PREC);
            comm.addOption(OgdfServer.OPTION_MULTIPOLE_PREC, multipolePrec);
            // iterations
            Integer iterations = layoutNode.getProperty(ITERATIONS);
            if (iterations != null) {
                comm.addOption(OgdfServer.OPTION_ITERATIONS, iterations);
            }
            // randomize
            boolean interactive = layoutNode.getProperty(CoreOptions.INTERACTIVE);
            comm.addOption(OgdfServer.OPTION_RANDOMIZE, !interactive);
            break;
        }
        
        case FAST_MULTIPOLE_MULTILEVEL:
            // no options are currently available
            break;
            
        case KAMADA_KAWAI: {
            // the layouter returns NaN values on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Kamada-Kawai layout algorithm does not support non-connected graphs.");
            }
            // edge length
            double edgeLength = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (edgeLength < 0) {
                edgeLength = DEF_KK_EDGE_LENGTH;
            }
            comm.addOption(OgdfServer.OPTION_EDGE_LENGTH, edgeLength);
            // stop tolerance
            double stopTolerance = layoutNode.getProperty(STOP_TOLERANCE);
            comm.addOption(OgdfServer.OPTION_STOP_TOLERANCE, stopTolerance);
            break;
        }
        
        case STRESS_MAJORIZATION: {
            // the layouter returns NaN values on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Stress Majorization layout algorithm does not support non-connected graphs."); // SUPPRESS CHECKSTYLE LineLength
            }
            // iterations
            Integer iterations = layoutNode.getProperty(ITERATIONS);
            if (iterations != null) {
                comm.addOption(OgdfServer.OPTION_ITERATIONS, iterations);
            }
            // stop tolerance
            double stopTolerance = layoutNode.getProperty(STOP_TOLERANCE);
            comm.addOption(OgdfServer.OPTION_STOP_TOLERANCE, stopTolerance);
            // upward constraints
            boolean upward = layoutNode.getProperty(UPWARD);
            comm.addOption(OgdfServer.OPTION_UPWARD, upward);
            // radial constraints
            boolean radial = layoutNode.getProperty(RADIAL);
            comm.addOption(OgdfServer.OPTION_RADIAL, radial);
            break;
        }
        
        case DOMINANCE: {
            // the layouter crashes on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Dominance layout algorithm does not support non-connected graphs.");
            }
            // grid distance
            double distance = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (distance < 0) {
                distance = DEF_GRID_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_GRID_DISTANCE, Math.round(distance));
            // number of runs
            int runs = layoutNode.getProperty(RUNS);
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            break;
        }
        
        case VISIBILITY: {
            // the layouter crashes on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Visibility layout algorithm does not support non-connected graphs.");
            }
            // grid distance
            double distance = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (distance < 0) {
                distance = DEF_GRID_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_GRID_DISTANCE, Math.round(distance));
            // number of runs
            int runs = layoutNode.getProperty(RUNS);
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            break;
        }
        
        case FRAYSSEIX_PACH_POLLACK: {
            // the layouter crashes if there are multi-edges
            if (hasMultiEdges(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Fraysseix-Pach-Pollack layout algorithm does not support multi-edges.");
            }
            // the layouter crashes on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Fraysseix-Pach-Pollack layout algorithm does not support non-connected graphs."); // SUPPRESS CHECKSTYLE LineLength
            }
            // separation
            double separation = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            break;
        }
        
        case SCHNYDER: {
            // the layouter returns NaN values if there are multi-edges
            if (hasMultiEdges(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Schnyder layout algorithm does not support multi-edges.");
            }
            // the layouter returns NaN values on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Schnyder layout algorithm does not support non-connected graphs.");
            }
            // separation
            double separation = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            break;
        }
        
        case CANONICAL_ORDER: {
            // the layouter hangs if there are multi-edges
            if (hasMultiEdges(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Canonical Order layout algorithm does not support multi-edges.");
            }
            // separation
            double separation = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            // base ratio
            double baseRatio = layoutNode.getProperty(BASE_RATIO);
            comm.addOption(OgdfServer.OPTION_BASE_RATIO, baseRatio);
            // embedder module
            EmbedderModule embedderModule = layoutNode.getProperty(EMBEDDER);
            comm.addOption(OgdfServer.OPTION_EMBEDDER_MODULE, embedderModule.ordinal());
            break;
        }
        
        case CONVEX_GRID: {
            // the layouter hangs if there are multi-edges
            if (hasMultiEdges(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Convex Grid layout algorithm does not support multi-edges.");
            }
            // separation
            double separation = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            // base ratio
            double baseRatio = layoutNode.getProperty(BASE_RATIO);
            comm.addOption(OgdfServer.OPTION_BASE_RATIO, baseRatio);
            // embedder module
            EmbedderModule embedderModule = layoutNode.getProperty(EMBEDDER);
            comm.addOption(OgdfServer.OPTION_EMBEDDER_MODULE, embedderModule.ordinal());
            break;
        }
        
        case MIXED_MODEL: {
            // the layouter crashes if there are multi-edges
            if (hasMultiEdges(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The Mixed Model layout algorithm does not support multi-edges.");
            }
            // separation
            double separation = layoutNode.getProperty(CoreOptions.SPACING_NODE_NODE);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            // page ratio
            double pageRatio = layoutNode.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // number of runs
            int runs = layoutNode.getProperty(RUNS);
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            // edge insertion module
            EdgeInsertionModule edgeInsertionModule = layoutNode.getProperty(EDGE_INSERTION);
            comm.addOption(OgdfServer.OPTION_EDGE_INSERTION_MODULE, edgeInsertionModule.ordinal());
            // embedder module
            EmbedderModule embedderModule = layoutNode.getProperty(EMBEDDER);
            comm.addOption(OgdfServer.OPTION_EMBEDDER_MODULE, embedderModule.ordinal());
            // crossing beautification module
            CrossBeautifModule crossBeautifModule = layoutNode.getProperty(CROSS_BEAUTIF);
            comm.addOption(OgdfServer.OPTION_CROSS_BEAUTIF_MODULE, crossBeautifModule.ordinal());
            break;
        }
        
        case BALLOON: {
            // no specific options available
            break;
        }
        
        default:
            throw new IllegalStateException("Invalid value set for layout algorithm selection.");
        }
    }
    
    /**
     * Determine whether the graph defined by the given parent node is connected.
     * 
     * @param graph the parent node of the graph
     * @return whether the graph is connected
     */
   private static boolean isConnected(final ElkNode graph) {
       // empty graphs are connected
       if (graph.getChildren().size() == 0) {
           return true;
       }
       // mark all nodes connected to a random node
       Set<ElkNode> marker = new HashSet<>();
       LinkedList<ElkNode> nodeQueue = new LinkedList<>();
       nodeQueue.offer(graph.getChildren().get(0));
       do {
           ElkNode currentNode = nodeQueue.poll();
           if (!marker.contains(currentNode)) {
               marker.add(currentNode);
               for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(currentNode)) {
                   nodeQueue.offer(ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0)));
               }
               for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(currentNode)) {
                   nodeQueue.offer(ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0)));
               }
           }
       } while (!nodeQueue.isEmpty());
       
       // if there is still a not marked node the graph is not connected
       for (ElkNode currentNode : graph.getChildren()) {
           if (!marker.contains(currentNode)) {
               return false;
           }
       }
       
       return true;
   }
   
   /**
    * Determine whether the graph defined by the given parent node has any multi-edges.
    * 
    * @param graph the parent node of the graph
    * @return whether the graph contains a multi-edge
    */
   private static boolean hasMultiEdges(final ElkNode graph) {
       HashSet<Pair<ElkNode, ElkNode>> edgeSet = new HashSet<>();
       for (ElkNode source : graph.getChildren()) {
           for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(source)) {
               ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
               
               // self-loops are ignored by the server communicator
               if (source != target) {
                   Pair<ElkNode, ElkNode> pair = Pair.of(source, target);
                   if (edgeSet.contains(pair) || edgeSet.contains(Pair.of(target, source))) {
                       return true;
                   }
                   edgeSet.add(pair);
               }
           }
       }
       
       return false;
   }

}
