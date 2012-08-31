/**
 * @file    OgdfLayout.h
 * @author  mri@informatik.uni-kiel.de
 * @version 0.1.0
 *
 * @section LICENSE
 *
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * @section DESCRIPTION
 *
 * The definition of functions, which handle the layout of graphs, specified
 * by key-value-pair options stored in a map for application in the KIML OGDF
 * layout provider.
 */

#ifndef __OGDF_LAYOUT_H__INCLUDED__
#define __OGDF_LAYOUT_H__INCLUDED__

#include <map>
#include <ostream>
#include <string>

#include <ogdf/basic/Graph_d.h>
#include <ogdf/cluster/ClusterGraph.h>
#include <ogdf/cluster/ClusterGraphAttributes.h>
#include <ogdf/labeling/ELabelInterface.h>


/* Marks the input graph as UML graph, which is handled differently by some algorithms. */
#define INFO_UML_GRAPH "umlGraph"

/*
 * Definition of edge types.
 */
#define EDGE_TYPE_SUFFIX "type"
#define EDGE_TYPE_ASSOCIATION 0
#define EDGE_TYPE_DEPENDENCY 1
#define EDGE_TYPE_GENERALIZATION  2

/*
 * Definition of edge label positions.
 */
#define EDGE_LABEL_SUFFIX  "label"
#define LABEL_TYPE_END1 0
#define LABEL_TYPE_MULT1 1
#define LABEL_TYPE_NAME 2
#define LABEL_TYPE_END2 3
#define LABEL_TYPE_MULT2 4

/*
 * Definition of layout options.
 */
#define OPTION_ACYCLIC_SUBGRAPH_MODULE "acyclicSubgraphModule"
#define OPTION_ARRANGE_CC "arrangeCCs"
#define OPTION_ATTRACTION_FORMULA "attractionFormula"
#define OPTION_BASE_RATIO "baseRatio"
#define OPTION_CC_DISTANCE "ccDistance"
#define OPTION_COST_ASSOC "costAssoc"
#define OPTION_COST_GEN "costGen"
#define OPTION_COSTS "costs"
#define OPTION_CROSS_MIN_MODULE "crossingMinimizationModule"
#define OPTION_DESIRED_LENGTH "desiredLength"
#define OPTION_EDGE_INSERTION_MODULE "edgeInsertionModule"
#define OPTION_EDGE_LENGTH "edgeLength"
#define OPTION_EMBEDDER_MODULE "embedderModule"
#define OPTION_FAILS "fails"
#define OPTION_FINENESS "fineness"
#define OPTION_GLOBAL_ITERATIONS "globalIterations"
#define OPTION_GRAVITATIONAL_CONSTANT "gravitationalConstant"
#define OPTION_GRID_DISTANCE "gridDistance"
#define OPTION_INITIAL_TEMPERATURE "initialTemperature"
#define OPTION_ITERATIONS "iterations"
#define OPTION_LABEL_EDGE_DISTANCE "labelEdgeDistance"
#define OPTION_LABEL_MARGIN_DISTANCE "labelMarginDistance"
#define OPTION_LAYER_DISTANCE "layerDistance"
#define OPTION_LAYOUT_DIRECTION "layoutDirection"
#define OPTION_LAYOUTER "layouter"
#define OPTION_LOCAL_ITERATIONS "localIterations"
#define OPTION_LEVEL_DISTANCE "levelDistance"
#define OPTION_MAXIMAL_DISTURBANCE "maximalDisturbance"
#define OPTION_MIN_CLIQUE_SIZE "minCliqueSize"
#define OPTION_MIN_DIST_CC "minDistCC"
#define OPTION_MIN_DIST_CIRCLE "minDistCircle"
#define OPTION_MIN_DIST_LEVEL "minDistLevel"
#define OPTION_MIN_DIST_SIBLING "minDistSibling"
#define OPTION_MINIMAL_TEMPERATURE "minimalTemperature"
#define OPTION_MULTILEVEL_UNNAL "multilevelUntilNumNodesAreLess"
#define OPTION_MULTIPOLE_PREC "multipolePrec"
#define OPTION_NEW_INITIAL_PLACEMENT "newInitialPlacement"
#define OPTION_NODE_DISTANCE "nodeDistance"
#define OPTION_NOISE "noise"
#define OPTION_NUMBER_OF_ROUNDS "numberOfRounds"
#define OPTION_ORIENTATION "orientation"
#define OPTION_ORTHOGONAL "orthogonal"
#define OPTION_OSCILLATION_ANGLE "oscillationAngle"
#define OPTION_OSCILLATION_SENSITIVITY "oscillationSensitivity"
#define OPTION_PAGE_RATIO "pageRatio"
#define OPTION_PREPROCESS_CLIQUES "preprocessCliques"
#define OPTION_QUALITY_VS_SPEED "qualityVsSpeed"
#define OPTION_RADIAL "radial"
#define OPTION_RANDOMIZE "randomize"
#define OPTION_RANDOM_SEED "randomSeed"
#define OPTION_RANKING_MODULE "rankingModule"
#define OPTION_ROTATION_ANGLE "rotationAngle"
#define OPTION_ROTATION_SENSITIVITY "rotationSensitivity"
#define OPTION_RUNS "runs"
#define OPTION_SEPARATION "separation"
#define OPTION_SIBLING_DISTANCE "siblingDistance"
#define OPTION_SPEED "speed"
#define OPTION_STOP_TOLERANCE "stopTolerance"
#define OPTION_SUBTREE_DISTANCE "subtreeDistance"
#define OPTION_TRANSPOSE "transpose"
#define OPTION_TREE_DISTANCE "treeDistance"
#define OPTION_UPWARD "upward"
#define OPTION_USE_LAYOUT "useLayout"
#define OPTION_WIDTH "width"

/*
 * Definition of available layout algorithms.
 */
enum LayouterType {
	NO_LAYOUTER,
	SUGIYAMA,
	PLANARIZATION,
	FMMM,
	DAVIDSON_HAREL,
	FRUCHTERMAN_REINGOLD,
	GEM,
	CIRCULAR,
	TREE,
	RADIAL_TREE,
	UPWARD_PLANARIZATION,
	FAST_MULTIPOLE,
	FAST_MULTIPOLE_MULTILEVEL,
	KAMADA_KAWAI,
	STRESS_MAJORIZATION,
	DOMINANCE,
	VISIBILITY,
	FRAYSSEIX_PACH_POLLACK,
	SCHNYDER,
	CANONICAL_ORDER,
	MIXED_MODEL,
	CONVEX_GRID,
	BALLOON
};

/*
 * Definition of orthogonal directions.
 */
#define DIRECTION_NORTH 0
#define DIRECTION_SOUTH 1
#define DIRECTION_WEST 2
#define DIRECTION_EAST 3

/*
 * Definition of "quality vs. speed" values for FMMM layout.
 */
#define GORGEOUS_AND_EFFICIENT 0
#define BEAUTIFUL_AND_FAST 1
#define NICE_AND_INCREDIBLE_SPEED 2

/*
 * Definition of "costs" values for Davidson-Harel layout.
 */
#define COSTS_STANDARD 0
#define COSTS_REPULSE 1
#define COSTS_PLANAR 2

/*
 * Definition of "speed" values for Davidson-Harel layout.
 */
#define SPEED_FAST 0
#define SPEED_MEDIUM 1
#define SPEED_HQ 2

/*
 * Definition of OGDF orientation directions.
 */
#define ORIENTATION_TOP_TO_BOTTOM 0
#define ORIENTATION_BOTTOM_TO_TOP 1
#define ORIENTATION_LEFT_TO_RIGHT 2
#define ORIENTATION_RIGHT_TO_LEFT 3

/*
 * Definition of ranking modules for Sugiyama layout.
 */
#define RANKING_LONGEST_PATH   0
#define RANKING_COFFMAN_GRAHAM 1
#define RANKING_OPTIMAL        2

/*
 * Definition of acyclic subgraph modules for Sugiyama layout.
 */
#define ACYCLIC_SUBGRAPH_DFS    0
#define ACYCLIC_SUBGRAPH_GREEDY 1

/*
 * Definition of crossing minimization modules for Sugiyama layout.
 */
#define CROSS_MIN_BARYCENTER    0
#define CROSS_MIN_GREEDY_INSERT 1
#define CROSS_MIN_GREEDY_SWITCH 2
#define CROSS_MIN_MEDIAN        3
#define CROSS_MIN_SIFTING       4

/*
 * Definition of edge insertion modules for planarization layout.
 */
#define EDGE_INSERTION_FIXED_EMB        0
#define EDGE_INSERTION_VARIABLE_EMB     1
#define EDGE_INSERTION_MULTIEDGE_APPROX 2

/*
 * Definition of embedder modules for planarization layout. */
#define EMBEDDER_SIMPLE                    0
#define EMBEDDER_MAX_FACE                  1
#define EMBEDDER_MAX_FACE_LAYERS           2
#define EMBEDDER_MIN_DEPTH                 3
#define EMBEDDER_MIN_DEPTH_MAX_FACE        4
#define EMBEDDER_MIN_DEPTH_MAX_FACE_LAYERS 5
#define EMBEDDER_PIZZONIA_TAMASSIA         6


/** the type for the string map. */
typedef std::map<std::string, std::string> StringMap;
/** the type for the label interface. */
typedef ogdf::ELabelInterface<double> LabelInterface;

/**
 * Layouts the given graph using the specified options, which includes the
 * layouter.
 *
 * @param G
 *            the graph
 * @param CG
 *            the cluster graph
 * @param GA
 *            the graph attributes
 * @param LI
 *            the label interface
 * @param options
 *            the options
 * @param information
 *            additional information about the graph
 * @return the pointer to an graph attributes instance with applied layout or NULL
 * 		   if the layout failed
 */
ogdf::GraphAttributes* Layout(ogdf::Graph& G, ogdf::ClusterGraph& CG,
		ogdf::ClusterGraphAttributes* GA, LabelInterface*& LI,
		StringMap& options, StringMap& information);

/**
 * Returns a description of the last error that occurred while layouting.
 * Returns the empty string if no error occurred so far.
 */
const std::string& GetLastLayoutError();

#endif
