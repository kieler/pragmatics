/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
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

import net.ogdf.bin.OgdfServer;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.UnsupportedGraphException;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ogdf.options.AcyclicSubgraphModule;
import de.cau.cs.kieler.kiml.ogdf.options.AttractionFormula;
import de.cau.cs.kieler.kiml.ogdf.options.Costs;
import de.cau.cs.kieler.kiml.ogdf.options.LayoutAlgorithm;
import de.cau.cs.kieler.kiml.ogdf.options.QualityVsSpeed;
import de.cau.cs.kieler.kiml.ogdf.options.RankingModule;
import de.cau.cs.kieler.kiml.ogdf.options.Speed;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Setup for OGDF layout algorithm configurations.
 * 
 * @author msp
 */
public final class AlgorithmSetup {

    // general options used for multiple algorithms
    
    /** label edge distance option. */
    public static final IProperty<Float> LABEL_EDGE_DIST = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.labelEdgeDistance", 15.0f);
    /** label margin distance option. */
    public static final IProperty<Float> LABEL_MARGIN_DIST = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.labelMarginDistance", 15.0f);
    /** label placement option. */
    public static final IProperty<Boolean> PLACE_LABELS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.placeLabels", true);
    
    /** 'aspectRatio' option. */
    private static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, 1.3f);
    /** factor for 'minDistCC' option. */
    private static final IProperty<Float> MIN_DIST_CC = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0f);
    /** factor for 'layerDistance' option. */
    private static final IProperty<Float> LAYER_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0f);
    /** 'iterations' option. */
    private static final IProperty<Integer> ITERATIONS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.iterations");
    /** 'stopTolerance' option. */
    private static final IProperty<Float> STOP_TOLERANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.stopTolerance", 0.001f);

    // options for Sugiyama layouter
    
    /** 'nodeDistance' option. */
    private static final float DEF_NODE_DISTANCE = 16.0f;
    /** 'fails' option. */
    private static final IProperty<Integer> FAILS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.fails", 4);
    /** 'runs' option. */
    private static final IProperty<Integer> RUNS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.runs", 15);
    /** 'transpose' option. */
    private static final IProperty<Boolean> TRANSPOSE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.transpose", true);
    /** 'acyclicSubgraphModule' option. */
    private static final IProperty<AcyclicSubgraphModule> ACYCLIC_SUBGRAPH
            = new Property<AcyclicSubgraphModule>("de.cau.cs.kieler.kiml.ogdf.option.acyclicSubgraph",
                    AcyclicSubgraphModule.DFS);
    /** 'rankingModule' option. */
    private static final IProperty<RankingModule> RANKING = new Property<RankingModule>(
            "de.cau.cs.kieler.kiml.ogdf.option.ranking", RankingModule.LONGEST_PATH);
    /** 'width' option. */
    private static final IProperty<Integer> WIDTH = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.width", 7);
    
    // options for planarization layouter
    
    /** 'separation' option. */
    private static final float DEF_PLAN_SEPARATION = 20.0f;
    /** 'preprocessCliques' option. */
    private static final IProperty<Boolean> PREPROCESS_CLIQUES = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.preprocessCliques", false);
    /** 'minCliqueSize' option. */
    private static final IProperty<Integer> MIN_CLIQUE_SIZE = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.minCliqueSize", 10);
    /** 'costAssoc' option. */
    private static final IProperty<Integer> COST_ASSOC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.costAssoc", 1);
    /** 'costGen' option. */
    private static final IProperty<Integer> COST_GEN = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.costGen", 4);

    // options for FMMM layouter
    
    /** 'quality vs speed' option. */
    private static final IProperty<QualityVsSpeed> QUALITY_VS_SPEED = new Property<QualityVsSpeed>(
            "de.cau.cs.kieler.kiml.ogdf.option.qualityVsSpeed", QualityVsSpeed.BEAUTIFULANDFAST);
    /** 'new initial placement' option. */
    private static final IProperty<Boolean> NEW_INITIAL_PLACEMENT = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.newInitialPlacement", false);
    
    // options for Davidson & Harel layouter
    
    /** 'spacing' option. */
    private static final float DEF_DESIRED_EDGE_LENGTH = 80.0f;
    /** 'costs' option. */
    private static final IProperty<Costs> COSTS = new Property<Costs>(
            "de.cau.cs.kieler.kiml.ogdf.option.costs", Costs.STANDARD);
    /** 'speed' option. */
    private static final IProperty<Speed> SPEED = new Property<Speed>(
            "de.cau.cs.kieler.kiml.ogdf.option.speed", Speed.MEDIUM);
    
    // options for Fruchterman & Reingold layouter
    
    /** 'fineness' option. */
    private static final IProperty<Float> FINENESS = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.fineness", 0.51f);
    /** 'noise' option. */
    private static final IProperty<Boolean> NOISE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.noise", true);
    /** 'minDistCC' option. */
    private static final float DEF_MIN_DIST_CC = 20.0f;
    
    // options for GEM layouter
    
    /** 'desiredLength' option. */
    private static final float DEF_DESIRED_LENGTH = 30.0f;
    /** 'numberOfRounds' option. */
    private static final IProperty<Integer> NUMBER_OF_ROUNDS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.numberOfRounds", 20000);
    /** 'minimalTemperature' option. */
    private static final IProperty<Float> MINIMAL_TEMPERATURE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minimalTemperature", 0.005f);
    /** 'initialTemperature' option. */
    private static final IProperty<Float> INITIAL_TEMPERATURE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.initialTemperature", 10.0f);
    /** 'gravitationalConstant' option. */
    private static final IProperty<Float> GRAVITATIONAL_CONSTANT = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.gravitationalConstant", 0.0625f);
    /** 'maximalDisturbance' option. */
    private static final IProperty<Float> MAXIMAL_DISTURBANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.maximalDisturbance", 0.0f);
    /** 'rotationAngle' option. */
    private static final IProperty<Float> ROTATION_ANGLE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.rotationAngle", 0.33f);
    /** 'oscillationAngle' option. */
    private static final IProperty<Float> OSCILLATION_ANGLE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationAngle", 0.5f);
    /** 'rotationSensitivity' option. */
    private static final IProperty<Float> ROTATION_SENSITIVITY = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.rotationSensitivity", 0.01f);
    /** 'oscillationSensitivity' option. */
    private static final IProperty<Float> OSCILLATION_SENSITIVITY = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationSensitivity", 0.3f);
    /** 'attractionFormula' option. */
    private static final IProperty<AttractionFormula> ATTRACTION_FORMULA =
            new Property<AttractionFormula>("de.cau.cs.kieler.kiml.ogdf.option.attractionFormula",
                    AttractionFormula.FRUCHTERMAN_REINGOLD);
    
    // options for circular layouter
    
    /** 'minDistCircle' option. */
    private static final float DEF_MIN_DIST_CIRCLE = 20.0f;
    /** factor for 'minDistLevel' option. */
    private static final IProperty<Float> MIN_DIST_LEVEL = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0f);
    /** factor for 'minDistSibling' option. */
    private static final IProperty<Float> MIN_DIST_SIBLING = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.subtreeDistance", 1.0f);
    
    // options for tree layouter
    
    /** 'siblingDistance' option. */
    private static final float DEF_SIBLING_DISTANCE = 20.0f;
    /** factor for 'levelDistance' option. */
    private static final IProperty<Float> LEVEL_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0f);
    /** factor for 'subtreeDistance' option. */
    private static final IProperty<Float> SUBTREE_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.subtreeDistance", 1.0f);
    /** factor for 'treeDistance' option. */
    private static final IProperty<Float> TREE_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0f);
    
    // options for radial tree layouter
    
    /** 'levelDistance' option. */
    private static final float DEF_TREE_SPACING = 50.0f;
    /** factor for 'ccDistance' option. */
    private static final IProperty<Float> CC_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0f);
    
    // options for upward planarization layouter
    
    /** 'spacing' option. */
    private static final float DEF_UPL_SPACING = 16.0f;
    
    // options for fast multipole layouter
    
    /** 'multipolePrec' option. */
    private static final IProperty<Integer> MULTIPOLE_PREC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.multipolePrec", 4);
    
    // options for the Kamada & Kawai layouter
    
    /** 'edgeLength' option. */
    private static final float DEF_KK_EDGE_LENGTH = 30.0f;
    
    // options for the stress majorization layouter
    
    /** 'upward' option. */
    private static final IProperty<Boolean> UPWARD = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.upward", true);
    /** 'radial' option. */
    private static final IProperty<Boolean> RADIAL = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.radial", false);
    
    // options for dominance and visibility layouters
    
    /** 'gridDistance' option. */
    private static final float DEF_GRID_DISTANCE = 10.0f;
    
    // options for FPP and Schnyder layouters
    
    /** 'separation' option. */
    private static final float DEF_GRID_SEPARATION = 30.0f;
    
    // options for the canonical order layouter
    
    /** 'baseRatio' option. */
    private static final IProperty<Float> BASE_RATIO = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.baseRatio", 0.33f);
    
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
            final KNode layoutNode) {
        comm.addOption(OgdfServer.OPTION_LAYOUTER, algorithm);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        
        Integer seed = parentLayout.getProperty(LayoutOptions.RANDOM_SEED);
        if (seed == null) {
            comm.addOption(OgdfServer.OPTION_RANDOM_SEED, 1);
        } else if (seed == 0) {
            comm.addOption(OgdfServer.OPTION_RANDOM_SEED, (int) System.currentTimeMillis());
        } else {
            comm.addOption(OgdfServer.OPTION_RANDOM_SEED, seed);
        }
        
        boolean processLabels = parentLayout.getProperty(PLACE_LABELS);
        if (processLabels) {
            // edge distance
            float edgeDistance = parentLayout.getProperty(LABEL_EDGE_DIST);
            if (edgeDistance < 0) {
                edgeDistance = LABEL_EDGE_DIST.getDefault();
            }
            comm.addOption(OgdfServer.OPTION_LABEL_EDGE_DISTANCE, edgeDistance);
            // margin distance
            float marginDistance = parentLayout.getProperty(LABEL_MARGIN_DIST);
            if (marginDistance < 0) {
                marginDistance = LABEL_MARGIN_DIST.getDefault();
            }
            comm.addOption(OgdfServer.OPTION_LABEL_MARGIN_DISTANCE, marginDistance);
        }
        
        switch (algorithm) {
        case SUGIYAMA: {
            // page ratio
            float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // node distance
            float nodeDistance = parentLayout.getProperty(LayoutOptions.SPACING);
            if (nodeDistance < 0) {
                nodeDistance = DEF_NODE_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_NODE_DISTANCE, nodeDistance);
            // number of fails
            int fails = parentLayout.getProperty(FAILS);
            comm.addOption(OgdfServer.OPTION_FAILS, fails);
            // number of runs
            int runs = parentLayout.getProperty(RUNS);
            comm.addOption(OgdfServer.OPTION_RUNS, runs);
            // transpose
            boolean transpose = parentLayout.getProperty(TRANSPOSE);
            comm.addOption(OgdfServer.OPTION_TRANSPOSE, transpose);
            // arrange connected components
            Boolean arrangeCCs = parentLayout.getProperty(LayoutOptions.SEPARATE_CC);
            comm.addOption(OgdfServer.OPTION_ARRANGE_CC,
                    arrangeCCs != null && arrangeCCs.booleanValue());
            // minimal distance of connected components
            float minDistCCFactor = parentLayout.getProperty(MIN_DIST_CC);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, nodeDistance * minDistCCFactor);
            // layer distance
            float layerDistanceFactor = parentLayout.getProperty(LAYER_DISTANCE);
            comm.addOption(OgdfServer.OPTION_LAYER_DISTANCE, nodeDistance * layerDistanceFactor);
            // acyclic subgraph module
            AcyclicSubgraphModule acyclicSubgraphModule = parentLayout.getProperty(ACYCLIC_SUBGRAPH);
            comm.addOption(OgdfServer.OPTION_ACYCLIC_SUBGRAPH_MODULE,
                    acyclicSubgraphModule.toServerParam());
            // ranking module
            RankingModule rankingModule = parentLayout.getProperty(RANKING);
            comm.addOption(OgdfServer.OPTION_RANKING_MODULE, rankingModule.toServerParam());
            // width of the ranking
            int width = parentLayout.getProperty(WIDTH);
            comm.addOption(OgdfServer.OPTION_WIDTH, width);
            break;
        }
            
        case PLANARIZATION: {
            // page ratio
            float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // minimal spacing
            float separation = parentLayout.getProperty(LayoutOptions.SPACING);
            if (separation < 0) {
                separation = DEF_PLAN_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            // layout direction
            Direction direction = parentLayout.getProperty(LayoutOptions.DIRECTION);
            int layoutDirection;
            switch (direction) {
            case UP:
                layoutDirection = OgdfServer.DIRECTION_SOUTH;
                break;
            case LEFT:
                layoutDirection = OgdfServer.DIRECTION_WEST;
                break;
            case RIGHT:
                layoutDirection = OgdfServer.DIRECTION_EAST;
                break;
            default:
                layoutDirection = OgdfServer.DIRECTION_NORTH;
                break;
            }
            comm.addOption(OgdfServer.OPTION_LAYOUT_DIRECTION, layoutDirection);
            // preprocess cliques
            boolean preprocessCliques = parentLayout.getProperty(PREPROCESS_CLIQUES);
            comm.addOption(OgdfServer.OPTION_PREPROCESS_CLIQUES, preprocessCliques);
            // minimal clique size
            int minCliqueSize = parentLayout.getProperty(MIN_CLIQUE_SIZE);
            comm.addOption(OgdfServer.OPTION_MIN_CLIQUE_SIZE, minCliqueSize);
            // cost for association edges
            int costAssoc = parentLayout.getProperty(COST_ASSOC);
            comm.addOption(OgdfServer.OPTION_COST_ASSOC, costAssoc);
            // cost for generalization edges
            int costGen = parentLayout.getProperty(COST_GEN);
            comm.addOption(OgdfServer.OPTION_COST_GEN, costGen);
            break;
        }
            
        case FMMM: {
            // quality vs. speed
            QualityVsSpeed qualityVsSpeed = parentLayout.getProperty(QUALITY_VS_SPEED);
            comm.addOption(OgdfServer.OPTION_QUALITY_VS_SPEED, qualityVsSpeed.toServerParam());
            // new initial placement
            boolean newInitialPlacement = parentLayout.getProperty(NEW_INITIAL_PLACEMENT);
            comm.addOption(OgdfServer.OPTION_NEW_INITIAL_PLACEMENT, newInitialPlacement);
            break;
        }
            
        case DAVIDSON_HAREL: {
            // desired edge length
            float desiredEdgeLength = parentLayout.getProperty(LayoutOptions.SPACING);
            if (desiredEdgeLength < 0) {
                desiredEdgeLength = DEF_DESIRED_EDGE_LENGTH;
            }
            comm.addOption(OgdfServer.OPTION_EDGE_LENGTH, desiredEdgeLength);
            // costs
            Costs costs = parentLayout.getProperty(COSTS);
            comm.addOption(OgdfServer.OPTION_COSTS, costs.toServerParam());
            // speed
            Speed speed = parentLayout.getProperty(SPEED);
            comm.addOption(OgdfServer.OPTION_SPEED, speed.toServerParam());
            break;
        }
            
        case FRUCHTERMAN_REINGOLD: {
            // page ratio
            float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // iterations
            Integer iterations = parentLayout.getProperty(ITERATIONS);
            if (iterations != null) {
                comm.addOption(OgdfServer.OPTION_ITERATIONS, iterations);
            }
            // fineness
            float fineness = parentLayout.getProperty(FINENESS);
            comm.addOption(OgdfServer.OPTION_FINENESS, fineness);
            // noise
            boolean noise = parentLayout.getProperty(NOISE);
            comm.addOption(OgdfServer.OPTION_NOISE, noise);
            // minimal distance of connected components
            float minDistCC = parentLayout.getProperty(LayoutOptions.SPACING);
            if (minDistCC < 0) {
                minDistCC = DEF_MIN_DIST_CC;
            }
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCC);
            break;
        }
            
        case GEM: {
            // page ratio
            float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // desired length
            float desiredLength = parentLayout.getProperty(LayoutOptions.SPACING);
            if (desiredLength < 0) {
                desiredLength = DEF_DESIRED_LENGTH;
            }
            comm.addOption(OgdfServer.OPTION_DESIRED_LENGTH, desiredLength);
            // number of rounds
            int numberOfRounds = parentLayout.getProperty(NUMBER_OF_ROUNDS);
            comm.addOption(OgdfServer.OPTION_NUMBER_OF_ROUNDS, numberOfRounds);
            // minimal temperature
            float minimalTemperature = parentLayout.getProperty(MINIMAL_TEMPERATURE);
            comm.addOption(OgdfServer.OPTION_MINIMAL_TEMPERATURE, minimalTemperature);
            // initial temperature
            float initialTemperature = parentLayout.getProperty(INITIAL_TEMPERATURE);
            comm.addOption(OgdfServer.OPTION_INITIAL_TEMPERATURE, initialTemperature);
            // gravitational constant
            float gravitationalConstant = parentLayout.getProperty(GRAVITATIONAL_CONSTANT);
            comm.addOption(OgdfServer.OPTION_GRAVITATIONAL_CONSTANT, gravitationalConstant);
            // maximal disturbance
            float maximalDisturbance = parentLayout.getProperty(MAXIMAL_DISTURBANCE);
            comm.addOption(OgdfServer.OPTION_MAXIMAL_DISTURBANCE, maximalDisturbance);
            // rotation angle
            float rotationAngle = parentLayout.getProperty(ROTATION_ANGLE);
            comm.addOption(OgdfServer.OPTION_ROTATION_ANGLE, rotationAngle);
            // oscillation angle
            float oscillationAngle = parentLayout.getProperty(OSCILLATION_ANGLE);
            comm.addOption(OgdfServer.OPTION_OSCILLATION_ANGLE, oscillationAngle);
            // rotation sensitivity
            float rotationSensitivity = parentLayout.getProperty(ROTATION_SENSITIVITY);
            comm.addOption(OgdfServer.OPTION_ROTATION_SENSITIVITY, rotationSensitivity);
            // oscillation sensitivity
            float oscillationSensitivity = parentLayout.getProperty(OSCILLATION_SENSITIVITY);
            comm.addOption(OgdfServer.OPTION_OSCILLATION_SENSITIVITY, oscillationSensitivity);
            // attraction formula
            AttractionFormula attractionFormula = parentLayout.getProperty(ATTRACTION_FORMULA);
            comm.addOption(OgdfServer.OPTION_ATTRACTION_FORMULA, attractionFormula.toServerParam());
            // minimal distance of connected components
            float minDistCCFactor = parentLayout.getProperty(MIN_DIST_CC);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, desiredLength * minDistCCFactor);
            break;
        }
            
        case CIRCULAR: {
            // page ratio
            float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
            comm.addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
            // minimal distance of circles
            float minDistCircle = parentLayout.getProperty(LayoutOptions.SPACING);
            if (minDistCircle < 0) {
                minDistCircle = DEF_MIN_DIST_CIRCLE;
            }
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CIRCLE, minDistCircle);
            // minimal distance of levels
            float minDistLevelFactor = parentLayout.getProperty(MIN_DIST_LEVEL);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_LEVEL, minDistCircle * minDistLevelFactor);
            // minimal distance of siblings
            float minDistSiblingFactor = parentLayout.getProperty(MIN_DIST_SIBLING);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_SIBLING, minDistCircle * minDistSiblingFactor);
            // minimal distance of connected components
            float minDistCCFactor = parentLayout.getProperty(MIN_DIST_CC);
            comm.addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCircle * minDistCCFactor);
            break;
        }
            
        case TREE: {
            // direction
            Direction direction = parentLayout.getProperty(LayoutOptions.DIRECTION);
            int orientation;
            switch (direction) {
            case LEFT:
                orientation = OgdfServer.ORIENTATION_RIGHT_TO_LEFT;
                break;
            case UP:
                orientation = OgdfServer.ORIENTATION_TOP_TO_BOTTOM;
                break;
            case DOWN:
                orientation = OgdfServer.ORIENTATION_BOTTOM_TO_TOP;
                break;
            default:
                orientation = OgdfServer.ORIENTATION_LEFT_TO_RIGHT;
            }
            comm.addOption(OgdfServer.OPTION_ORIENTATION, orientation);
            // edge routing
            EdgeRouting edgeRouting = parentLayout.getProperty(LayoutOptions.EDGE_ROUTING);
            boolean orthogonal = edgeRouting == EdgeRouting.ORTHOGONAL;
            comm.addOption(OgdfServer.OPTION_ORTHOGONAL, orthogonal);
            // sibling distance
            float siblingDistance = parentLayout.getProperty(LayoutOptions.SPACING);
            if (siblingDistance < 0) {
                siblingDistance = DEF_SIBLING_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_SIBLING_DISTANCE, siblingDistance);
            // level distance
            float levelDistanceFactor = parentLayout.getProperty(LEVEL_DISTANCE);
            comm.addOption(OgdfServer.OPTION_LEVEL_DISTANCE, siblingDistance * levelDistanceFactor);
            // subtree distance
            float subtreeDistanceFactor = parentLayout.getProperty(SUBTREE_DISTANCE);
            comm.addOption(OgdfServer.OPTION_SUBTREE_DISTANCE, siblingDistance * subtreeDistanceFactor);
            // tree distance
            float treeDistanceFactor = parentLayout.getProperty(TREE_DISTANCE);
            comm.addOption(OgdfServer.OPTION_TREE_DISTANCE, siblingDistance * treeDistanceFactor);
            break;
        }
            
        case RADIAL_TREE: {
            // level distance
            float levelDistance = parentLayout.getProperty(LayoutOptions.SPACING);
            if (levelDistance < 0) {
                levelDistance = DEF_TREE_SPACING;
            }
            comm.addOption(OgdfServer.OPTION_LEVEL_DISTANCE, levelDistance);
            // connected components distance
            float ccDistanceFactor = parentLayout.getProperty(CC_DISTANCE);
            comm.addOption(OgdfServer.OPTION_CC_DISTANCE, levelDistance * ccDistanceFactor);
            break;
        }
            
        case UPWARD_PLANARIZATION: {
            // the layouter crashes on not connected graphs
            if (!isConnected(layoutNode)) {
                throw new UnsupportedGraphException(
                        "The upward planarization layout algorithm does not support non-connected graphs.");
            }
            // node distance
            float nodeDistance = parentLayout.getProperty(LayoutOptions.SPACING);
            if (nodeDistance < 0) {
                nodeDistance = DEF_UPL_SPACING;
            }
            comm.addOption(OgdfServer.OPTION_NODE_DISTANCE, nodeDistance);
            // layer distance
            float layerDistanceFactor = parentLayout.getProperty(LAYER_DISTANCE);
            comm.addOption(OgdfServer.OPTION_LAYER_DISTANCE, nodeDistance * layerDistanceFactor);
            break;
        }
        
        case FAST_MULTIPOLE: {
            // multipole precision
            int multipolePrec = parentLayout.getProperty(MULTIPOLE_PREC);
            comm.addOption(OgdfServer.OPTION_MULTIPOLE_PREC, multipolePrec);
            // iterations
            Integer iterations = parentLayout.getProperty(ITERATIONS);
            if (iterations != null) {
                comm.addOption(OgdfServer.OPTION_ITERATIONS, iterations);
            }
            // randomize
            boolean interactive = parentLayout.getProperty(LayoutOptions.INTERACTIVE);
            comm.addOption(OgdfServer.OPTION_RANDOMIZE, !interactive);
            break;
        }
        
        case FAST_MULTIPOLE_MULTILEVEL:
            // no options are currently available
            break;
            
        case KAMADA_KAWAI: {
            // edge length
            float edgeLength = parentLayout.getProperty(LayoutOptions.SPACING);
            if (edgeLength < 0) {
                edgeLength = DEF_KK_EDGE_LENGTH;
            }
            comm.addOption(OgdfServer.OPTION_EDGE_LENGTH, edgeLength);
            // stop tolerance
            float stopTolerance = parentLayout.getProperty(STOP_TOLERANCE);
            comm.addOption(OgdfServer.OPTION_STOP_TOLERANCE, stopTolerance);
            break;
        }
        
        case STRESS_MAJORIZATION: {
            // iterations
            Integer iterations = parentLayout.getProperty(ITERATIONS);
            if (iterations != null) {
                comm.addOption(OgdfServer.OPTION_ITERATIONS, iterations);
            }
            // stop tolerance
            float stopTolerance = parentLayout.getProperty(STOP_TOLERANCE);
            comm.addOption(OgdfServer.OPTION_STOP_TOLERANCE, stopTolerance);
            // upward constraints
            boolean upward = parentLayout.getProperty(UPWARD);
            comm.addOption(OgdfServer.OPTION_UPWARD, upward);
            // radial constraints
            boolean radial = parentLayout.getProperty(RADIAL);
            comm.addOption(OgdfServer.OPTION_RADIAL, radial);
            break;
        }
        
        case DOMINANCE: {
            // grid distance
            float distance = parentLayout.getProperty(LayoutOptions.SPACING);
            if (distance < 0) {
                distance = DEF_GRID_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_GRID_DISTANCE, Math.round(distance));
            break;
        }
        
        case VISIBILITY: {
            // grid distance
            float distance = parentLayout.getProperty(LayoutOptions.SPACING);
            if (distance < 0) {
                distance = DEF_GRID_DISTANCE;
            }
            comm.addOption(OgdfServer.OPTION_GRID_DISTANCE, Math.round(distance));
            break;
        }
        
        case FRAYSSEIX_PACH_POLLACK: {
            // separation
            float separation = parentLayout.getProperty(LayoutOptions.SPACING);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            break;
        }
        
        case SCHNYDER: {
            // separation
            float separation = parentLayout.getProperty(LayoutOptions.SPACING);
            if (separation < 0) {
                separation = DEF_GRID_SEPARATION;
            }
            comm.addOption(OgdfServer.OPTION_SEPARATION, separation);
            break;
        }
        
        case CANONICAL_ORDER: {
            // base ratio
            float baseRatio = parentLayout.getProperty(BASE_RATIO);
            comm.addOption(OgdfServer.OPTION_BASE_RATIO, baseRatio);
            break;
        }
        
        case MIXED_MODEL:
            // no options are currently available
            break;
        
        case CONVEX_GRID: {
            // base ratio
            float baseRatio = parentLayout.getProperty(BASE_RATIO);
            comm.addOption(OgdfServer.OPTION_BASE_RATIO, baseRatio);
            break;
        }
        
        case BALLOON: {
            break;
        }
        
        default:
            throw new IllegalStateException("Invalid value set for layout algorithm selection.");
        }
    }
    
    /**
     * Determine whether the graph defined by the given parent node is connected.
     * 
     * @param node
     *            the parent node of the graph
     * @return whether the graph is connected
     */
   private static boolean isConnected(final KNode node) {
       // empty graphs are connected
       if (node.getChildren().size() == 0) {
           return true;
       }
       // mark all nodes connected to a random node
       Set<KNode> marker = new HashSet<KNode>();
       LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
       nodeQueue.offer(node.getChildren().get(0));
       do {
           KNode currentNode = nodeQueue.poll();
           if (!marker.contains(currentNode)) {
               marker.add(currentNode);
               for (KEdge edge : currentNode.getOutgoingEdges()) {
                   nodeQueue.offer(edge.getTarget());
               }
               for (KEdge edge : currentNode.getIncomingEdges()) {
                   nodeQueue.offer(edge.getSource());
               }
           }
       } while (!nodeQueue.isEmpty());
       // if there is still a not marked node the graph is not connected
       for (KNode currentNode : node.getChildren()) {
           if (!marker.contains(currentNode)) {
               return false;
           }
       }
       return true;
   }

}
