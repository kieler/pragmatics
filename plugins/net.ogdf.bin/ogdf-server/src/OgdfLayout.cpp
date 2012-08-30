/**
 * @file    OgdfLayout.cpp
 * @author  mri@informatik.uni-kiel.de, msp@informatik.uni-kiel.de
 * @version 0.2.0
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
 * The implementation of the functions defined in OgdfLayout.h.
 */

#include <OgdfLayout.h>

#include <cstdlib>
#include <stdexcept>

#include <ogdf/basic/exceptions.h>
#include <ogdf/basic/geometry.h>
#include <ogdf/basic/Graph_d.h>
#include <ogdf/basic/GraphAttributes.h>
#include <ogdf/basic/UMLGraph.h>
#include <ogdf/basic/extended_graph_alg.h>
#include <ogdf/module/LayoutModule.h>
#include <ogdf/module/UMLLayoutModule.h>
#include <ogdf/labeling/ELabelInterface.h>
#include <ogdf/labeling/ELabelPosSimple.h>
#include <ogdf/layered/SugiyamaLayout.h>
#include <ogdf/layered/FastHierarchyLayout.h>
#include <ogdf/layered/LongestPathRanking.h>
#include <ogdf/layered/CoffmanGrahamRanking.h>
#include <ogdf/layered/OptimalRanking.h>
#include <ogdf/layered/GreedyCycleRemoval.h>
#include <ogdf/layered/GreedyInsertHeuristic.h>
#include <ogdf/layered/GreedySwitchHeuristic.h>
#include <ogdf/layered/MedianHeuristic.h>
#include <ogdf/layered/SiftingHeuristic.h>
#include <ogdf/orthogonal/OrthoRep.h>
#include <ogdf/orthogonal/OrthoLayout.h>
#include <ogdf/planarity/PlanarizationLayout.h>
#include <ogdf/planarity/FastPlanarSubgraph.h>
#include <ogdf/planarlayout/FPPLayout.h>
#include <ogdf/planarlayout/SchnyderLayout.h>
#include <ogdf/planarlayout/PlanarStraightLayout.h>
#include <ogdf/planarlayout/MixedModelLayout.h>
#include <ogdf/planarlayout/PlanarDrawLayout.h>
#include <ogdf/planarity/VariableEmbeddingInserter.h>
#include <ogdf/planarity/VariableEmbeddingInserter2.h>
#include <ogdf/planarity/MultiEdgeApproxInserter.h>
#include <ogdf/energybased/FMMMLayout.h>
#include <ogdf/energybased/DavidsonHarelLayout.h>
#include <ogdf/energybased/SpringEmbedderFR.h>
#include <ogdf/energybased/SpringEmbedderKK.h>
#include <ogdf/energybased/GEMLayout.h>
#include <ogdf/energybased/FastMultipoleEmbedder.h>
#include <ogdf/energybased/StressMajorizationSimple.h>
#include <ogdf/misclayout/CircularLayout.h>
#include <ogdf/misclayout/BalloonLayout.h>
#include <ogdf/tree/TreeLayout.h>
#include <ogdf/tree/RadialTreeLayout.h>
#include <ogdf/upward/UpwardPlanarizationLayout.h>
#include <ogdf/upward/LayerBasedUPRLayout.h>
#include <ogdf/upward/DominanceLayout.h>
#include <ogdf/upward/VisibilityLayout.h>

using namespace std;
using namespace ogdf;

/*
 * Message stored for the last error that occurred while invoking OGDF layout.
 */
string lastLayoutError = "";

/*
 * Create a message for an arbitrary C++ exception.
 */
string ogdfExceptionToString(const char* name, Exception& e) {
	return name;
}

/*
 * Create a message for a "precondition violated" exception.
 */
string ogdfPreconditionExceptionToString(PreconditionViolatedException& e) {
	string result = ogdfExceptionToString("PreconditionViolatedException", e);
	result += ": ";
	switch (e.exceptionCode()) {
	case pvcSelfLoop:
		result += "graph contains a self-loop";
		break;
	case pvcTreeHierarchies:
		result += "hierarchies are not only trees";
		break;
	case pvcAcyclicHierarchies:
		result += "hierarchies are not acyclic";
		break;
	case pvcSingleSource:
		result += "graph has not a single source";
		break;
	case pvcUpwardPlanar:
		result += "graph is not upward planar";
	case pvcTree:
		result += "graph is not a rooted tree";
		break;
	case pvcForest:
		result += "graph is not a rooted forest";
		break;
	case pvcOrthogonal:
		result += "layout is not orthogonal";
		break;
	case pvcPlanar:
		result += "graph is not planar";
		break;
	case pvcClusterPlanar:
		result += "graph is not c-planar";
		break;
	case pvcNoCopy:
		result += "graph is not a copy of the corresponding graph";
		break;
	case pvcConnected:
		result += "graph is not connected";
		break;
	case pvcBiconnected:
		result += "graph is not twoconnected";
		break;
	default:
		result += "Unknown.";
	}
	return result;
}

/*
 * Create a message for an "algorithm failure" exception.
 */
string ogdfAlgorithmExceptionToString(AlgorithmFailureException& e) {
	string result = ogdfExceptionToString("AlgorithmFailureException", e);
	result += ": ";
	switch (e.exceptionCode()) {
	case afcIllegalParameter:
		result += "function parameter is illegal";
		break;
	case afcNoFlow:
		result += "min-cost flow could not find a legal flow";
		break;
	case afcSort:
		result += "sequence not sorted";
		break;
	case afcLabel:
		result += "labelling failed";
		break;
	case afcExternalFace:
		result += "external face not correct";
		break;
	case afcForbiddenCrossing:
		result += "crossing forbidden but necessary";
		break;
	case afcTimelimitExceeded:
		result += "it took too long";
		break;
	case afcNoSolutionFound:
		result += "couldn't solve the problem";
		break;
	default:
		result += "Unknown.";
	}
	return result;
}

/*
 * Create a message for a "library not supported" exception.
 */
string ogdfLibraryExceptionToString(LibraryNotSupportedException& e) {
	string result = ogdfExceptionToString("LibraryNotSupportedException", e);
	result += ": ";
	switch (e.exceptionCode()) {
	case lnscCoin:
		result += "COIN not supported";
		break;
	case lnscAbacus:
		result += "ABACUS not supported";
		break;
	case lnscFunctionNotImplemented:
		result += "the used library doesn't support that function";
		break;
	case lnscMissingCallbackImplementation:
		result += "missing callback implementation";
		break;
	default:
		result += "Unknown";
	}
	return result;
}

/*
 * Retrieve a string value from the transmitted layout options.
 * Returns true if the option is available, and false otherwise.
 */
bool GetOption(const string& key, string& value, StringMap& options) {
	StringMap::iterator it = options.find(key);
	if (it == options.end()) {
		return false;
	}
	value = it->second;
	return true;
}

/*
 * Retrieve an integer value from the transmitted layout options.
 * Returns true if the option is available, and false otherwise.
 */
bool GetOption(const string& key, int& value, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	value = atoi(s.c_str());
	return true;
}

/*
 * Retrieve a floating point value from the transmitted layout options.
 * Returns true if the option is available, and false otherwise.
 */
bool GetOption(const string& key, double& value, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	value = strtod(s.c_str(), NULL);
	return true;
}

/*
 * Retrieve a Boolean value from the transmitted layout options.
 * Returns true if the option is available, and false otherwise.
 */
bool GetOption(const string& key, bool& value, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	value = (s == "true" ? true : false);
	return true;
}

/*
 * Retrieve an (x,y) coordinate value from the transmitted layout options.
 * Returns true if the option is available, and false otherwise.
 */
bool GetOption(const string& key, double& x, double& y, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	size_t beg = s.find_first_of('(');
	size_t end = s.find_last_of(')');
	size_t sep = s.find_first_of(',');
	string xs = s.substr(beg + 1, sep - beg - 1);
	string ys = s.substr(sep + 1, end - sep - 1);
	x = strtod(xs.c_str(), NULL);
	y = strtod(ys.c_str(), NULL);
	return true;
}

/*
 * Retrieve a layout option and throw an error if it is not available.
 */
#define GetOptionSafe(key, value, options) if (!GetOption(key, value, options))\
	{throw runtime_error(string("Missing option: ") + key);}

/*
 * Get the layouter type enumeration value for a serialized name.
 */
LayouterType GetLayouterTypeByName(const string& name) {
	if (name == "SUGIYAMA") {
		return SUGIYAMA;
	} else if (name == "PLANARIZATION") {
		return PLANARIZATION;
	} else if (name == "FMMM") {
		return FMMM;
	} else if (name == "DAVIDSON_HAREL") {
		return DAVIDSON_HAREL;
	} else if (name == "FRUCHTERMAN_REINGOLD") {
		return FRUCHTERMAN_REINGOLD;
	} else if (name == "GEM") {
		return GEM;
	} else if (name == "CIRCULAR") {
		return CIRCULAR;
	} else if (name == "TREE") {
		return TREE;
	} else if (name == "RADIAL_TREE") {
		return RADIAL_TREE;
	} else if (name == "UPWARD_PLANARIZATION") {
		return UPWARD_PLANARIZATION;
	} else if (name == "FAST_MULTIPOLE") {
		return FAST_MULTIPOLE;
	} else if (name == "FAST_MULTIPOLE_MULTILEVEL") {
		return FAST_MULTIPOLE_MULTILEVEL;
	} else if (name == "KAMADA_KAWAI") {
		return KAMADA_KAWAI;
	} else if (name == "STRESS_MAJORIZATION") {
		return STRESS_MAJORIZATION;
	} else if (name == "DOMINANCE") {
		return DOMINANCE;
	} else if (name == "VISIBILITY") {
		return VISIBILITY;
	} else if (name == "FRAYSSEIX_PACH_POLLACK") {
		return FRAYSSEIX_PACH_POLLACK;
	} else if (name == "SCHNYDER") {
		return SCHNYDER;
	} else if (name == "CANONICAL_ORDER") {
		return CANONICAL_ORDER;
	} else if (name == "MIXED_MODEL") {
		return MIXED_MODEL;
	} else if (name == "CONVEX_GRID") {
		return CONVEX_GRID;
	} else if (name == "BALLOON") {
		return BALLOON;
	} else {
		return NO_LAYOUTER;
	}
}

/*
 * Transform an integer value t into an OGDF label type n. Execute the exit
 * command e if the type is not known.
 */
#define TRANSFORM_LABEL_TYPE(n, t, e) eLabelType n;\
    switch(t) {\
    case LABEL_TYPE_END1:\
        n = elEnd1;\
        break;\
    case LABEL_TYPE_MULT1:\
        n = elMult1;\
        break; \
    case LABEL_TYPE_NAME:\
        n = elName;\
        break;\
    case LABEL_TYPE_END2:\
        n = elEnd2;\
        break;\
    case LABEL_TYPE_MULT2:\
        n = elMult2;\
        break;\
    default:\
        e;\
    }

/*
 * Transform an integer value t into an OGDF orthogonal direction n.
 */
#define TRANSFORM_DIRECTION(n, t) OrthoDir n;\
    switch(t) {\
    case DIRECTION_NORTH:\
        n = odNorth;\
        break;\
    case DIRECTION_SOUTH:\
        n = odSouth;\
        break; \
    case DIRECTION_WEST:\
        n = odWest;\
        break;\
    case DIRECTION_EAST:\
        n = odEast;\
        break;\
    default:\
        n = odSouth;\
        break;\
    }

/*
 * Transform an integer value t into a "quality vs. speed" value for FMMM layout.
 */
#define TRANSFORM_QUALITY_VS_SPEED(n, t) FMMMLayout::QualityVsSpeed n;\
    switch(t) {\
    case GORGEOUS_AND_EFFICIENT:\
        n = FMMMLayout::qvsGorgeousAndEfficient;\
        break;\
    case NICE_AND_INCREDIBLE_SPEED:\
        n = FMMMLayout::qvsNiceAndIncredibleSpeed;\
        break;\
    case BEAUTIFUL_AND_FAST:\
    default:\
        n = FMMMLayout::qvsBeautifulAndFast;\
        break; \
    }

/*
 * Transform an integer value t into a "costs" value for Davidson-Harel layout.
 */
#define TRANSFORM_COSTS(n, t) DavidsonHarelLayout::SettingsParameter n;\
    switch(t) {\
    case COSTS_REPULSE:\
        n = DavidsonHarelLayout::spRepulse;\
        break;\
    case COSTS_PLANAR:\
        n = DavidsonHarelLayout::spPlanar;\
        break; \
    case COSTS_STANDARD:\
    default:\
        n = DavidsonHarelLayout::spStandard;\
        break;\
    }

/*
 * Transform an integer value t into a "speed" value for Davidson-Harel layout.
 */
#define TRANSFORM_SPEED(n, t) DavidsonHarelLayout::SpeedParameter n;\
    switch(t) {\
    case SPEED_FAST:\
        n = DavidsonHarelLayout::sppFast;\
        break;\
    case SPEED_HQ:\
        n = DavidsonHarelLayout::sppHQ;\
        break; \
    case SPEED_MEDIUM:\
    default:\
        n = DavidsonHarelLayout::sppMedium;\
        break;\
    }

/*
 * Transform an integer value t into an OGDF orientation direction n.
 */
#define TRANSFORM_ORIENTATION(n, t) Orientation n;\
    switch(t) {\
    case ORIENTATION_LEFT_TO_RIGHT:\
        n = leftToRight;\
        break;\
    case ORIENTATION_BOTTOM_TO_TOP:\
        n = bottomToTop;\
        break; \
    case ORIENTATION_RIGHT_TO_LEFT:\
        n = rightToLeft;\
        break; \
    case ORIENTATION_TOP_TO_BOTTOM:\
    default:\
        n = topToBottom;\
        break;\
    }

/**
 * Derive a UML graph from a plain graph.
 */
void DeriveUMLGraph(Graph& G, GraphAttributes& GA, UMLGraph& UMLG,
		StringMap& information) {
	// process nodes
	node v;
	forall_nodes(v, G) {
		UMLG.labelNode(v) = GA.labelNode(v);
		UMLG.width(v) = GA.width(v);
		UMLG.height(v) = GA.height(v);
	}
	// process edges
	edge e;
	forall_edges(e, G) {
		string label = GA.labelEdge(e).cstr();
		UMLG.labelEdge(e) = label.c_str();
		int type;
		if (GetOption(label + EDGE_TYPE_SUFFIX, type, information)) {
			switch(type) {
				case EDGE_TYPE_ASSOCIATION:
				UMLG.type(e) = Graph::association;
				break;
				case EDGE_TYPE_DEPENDENCY:
				UMLG.type(e) = Graph::dependency;
				break;
				case EDGE_TYPE_GENERALIZATION:
				UMLG.type(e) = Graph::generalization;
				break;
				default:
				break;
			}
		}
	}
}

/*
 * Transfer labels from the transmitted graph information to the given OGDF label interface.
 */
void TransferLabels(Graph& G, GraphAttributes& GA, LabelInterface& LI,
		StringMap& information) {
	edge e;
	forall_edges(e, G) {
		string label = GA.labelEdge(e).cstr();
		for (int type = 0; type < elNumLabels; ++type) {
			double width, height;
			// 48 + digit = ascii representation of the digit
			if (GetOption(label + EDGE_LABEL_SUFFIX + (char)(48 + type), width, height, information)) {
				EdgeLabel<double>& label = LI.getLabel(e);
				TRANSFORM_LABEL_TYPE(labelType, type, return);
				label.addType(labelType);
				label.setWidth(labelType, width);
				label.setHeight(labelType, height);
			}
		}
	}
}

/*
 * Perform layout on the given graph.
 */
GraphAttributes* Layout(Graph& G, ClusterGraph& CG, ClusterGraphAttributes* GA,
		LabelInterface*& LI, StringMap& options, StringMap& information) {
	// the graph attributes with applied layout
	GraphAttributes* LGA = GA;
	try {
		// get the layouter
		string layouterName;
		GetOptionSafe(OPTION_LAYOUTER, layouterName, options);
		LayouterType layouterType = GetLayouterTypeByName(layouterName);

		// set the random seed
		int randomSeed;
		if (GetOption(OPTION_RANDOM_SEED, randomSeed, options)) {
			srand(randomSeed);
		}

		// perform the layout
		switch (layouterType) {
		case SUGIYAMA: {
			SugiyamaLayout layout;
			AcyclicSubgraphModule* acyclicSubgraphModule = NULL;
			int acyclicSubgraphOption;
			if (GetOption(OPTION_ACYCLIC_SUBGRAPH_MODULE, acyclicSubgraphOption, options)) {
				switch (acyclicSubgraphOption) {
				case ACYCLIC_SUBGRAPH_GREEDY:
					acyclicSubgraphModule = new GreedyCycleRemoval;
					break;
				// default: ACYCLIC_SUBGRAPH_DFS
				}
			}
			int rankingOption;
			if (GetOption(OPTION_RANKING_MODULE, rankingOption, options)) {
				switch (rankingOption) {
				case RANKING_COFFMAN_GRAHAM: {
					CoffmanGrahamRanking* ranking = new CoffmanGrahamRanking;
					int width;
					if (GetOption(OPTION_WIDTH, width, options) && width > 0) {
						ranking->width(width);
					}
					if (acyclicSubgraphModule != NULL) {
						ranking->setSubgraph(acyclicSubgraphModule);
					}
					layout.setRanking(ranking);
					break;
				}
				case RANKING_OPTIMAL: {
					OptimalRanking* ranking = new OptimalRanking;
					if (acyclicSubgraphModule != NULL) {
						ranking->setSubgraph(acyclicSubgraphModule);
					}
					layout.setRanking(ranking);
					break;
				}
				default: // RANKING_LONGEST_PATH
					if (acyclicSubgraphModule != NULL) {
						LongestPathRanking* ranking = new LongestPathRanking;
						ranking->setSubgraph(acyclicSubgraphModule);
						layout.setRanking(ranking);
					}
				}
			}
			int crossMinOption;
			if (GetOption(OPTION_CROSS_MIN_MODULE, crossMinOption, options)) {
				switch (crossMinOption) {
				case CROSS_MIN_GREEDY_INSERT: {
					GreedyInsertHeuristic* crossMin = new GreedyInsertHeuristic;
					layout.setCrossMin(crossMin);
					break;
				}
				case CROSS_MIN_GREEDY_SWITCH: {
					GreedySwitchHeuristic* crossMin = new GreedySwitchHeuristic;
					layout.setCrossMin(crossMin);
					break;
				}
				case CROSS_MIN_MEDIAN: {
					MedianHeuristic* crossMin = new MedianHeuristic;
					layout.setCrossMin(crossMin);
					break;
				}
				case CROSS_MIN_SIFTING: {
					SiftingHeuristic* crossMin = new SiftingHeuristic;
					layout.setCrossMin(crossMin);
					break;
				}
				// default: CROSS_MIN_BARYCENTER
				}
			}
			int fails;
			if (GetOption(OPTION_FAILS, fails, options) && fails > 0) {
				layout.fails(fails);
			}
			int runs;
			if (GetOption(OPTION_RUNS, runs, options) && runs > 0) {
				layout.runs(runs);
			}
			bool transpose;
			if (GetOption(OPTION_TRANSPOSE, transpose, options)) {
				layout.transpose(transpose);
			}
			bool arrangeCCs;
			if (GetOption(OPTION_ARRANGE_CC, arrangeCCs, options)) {
				layout.arrangeCCs(arrangeCCs);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options) && minDistCC >= 0) {
				layout.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options) && pageRatio > 0) {
				layout.pageRatio(pageRatio);
			}
			FastHierarchyLayout* hierarchyLayout = new FastHierarchyLayout;
			layout.setLayout(hierarchyLayout);
			double nodeDistance;
			if (GetOption(OPTION_NODE_DISTANCE, nodeDistance, options) && nodeDistance >= 0) {
				hierarchyLayout->nodeDistance(nodeDistance);
			}
			double layerDistance;
			if (GetOption(OPTION_LAYER_DISTANCE, layerDistance, options) && layerDistance >= 0) {
				hierarchyLayout->layerDistance(layerDistance);
			}
			layout.call(*LGA);
			break;
		}

		case PLANARIZATION: {
			PlanarizationLayout layout;
			int runs;
			if (GetOption(OPTION_RUNS, runs, options) && runs >= 0) {
				FastPlanarSubgraph* planarSubgraph = new FastPlanarSubgraph;
				planarSubgraph->runs(runs);
				layout.setSubgraph(planarSubgraph);
			}
			int edgeInsertionOption;
			if (GetOption(OPTION_EDGE_INSERTION_MODULE, edgeInsertionOption, options)) {
				switch (edgeInsertionOption) {
				case EDGE_INSERTION_VARIABLE_EMB: {
					VariableEmbeddingInserter* edgeInsertion = new VariableEmbeddingInserter;
					layout.setInserter(edgeInsertion);
					break;
				}
				case EDGE_INSERTION_MULTIEDGE_APPROX: {
					MultiEdgeApproxInserter* edgeInsertion = new MultiEdgeApproxInserter;
					layout.setInserter(edgeInsertion);
					break;
				}
				// default: EDGE_INSERTION_FIXED_EMB
				}
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options) && pageRatio > 0) {
				layout.pageRatio(pageRatio);
			}
			bool preprocessCliques;
			if (GetOption(OPTION_PREPROCESS_CLIQUES, preprocessCliques, options)) {
				layout.preprocessCliques(preprocessCliques);
			}
			int minCliqueSize;
			if (GetOption(OPTION_MIN_CLIQUE_SIZE, minCliqueSize, options)) {
				layout.minCliqueSize(minCliqueSize);
			}
			OrthoLayout* orthoLayout = new OrthoLayout();
			layout.setPlanarLayouter(orthoLayout);
			double separation;
			if (GetOption(OPTION_SEPARATION, separation, options) && separation >= 0) {
				orthoLayout->separation(separation);
			}
			int direction;
			if (GetOption(OPTION_LAYOUT_DIRECTION, direction, options)) {
				TRANSFORM_DIRECTION(layoutDirection, direction);
				orthoLayout->preferedDir(layoutDirection);
			}
			int costAssoc;
			if (GetOption(OPTION_COST_ASSOC, costAssoc, options)) {
				orthoLayout->costAssoc(costAssoc);
			}
			int costGen;
			if (GetOption(OPTION_COST_GEN, costGen, options)) {
				orthoLayout->costGen(costGen);
			}
			bool isUMLGraph;
			if (GetOption(INFO_UML_GRAPH, isUMLGraph, information)
					&& isUMLGraph) {
				// an UML graph is required to utilize the full functionality of the layouter
				UMLGraph* UMLG = new UMLGraph(G, GraphAttributes::nodeGraphics
						| GraphAttributes::edgeGraphics
						| GraphAttributes::nodeLabel
						| GraphAttributes::edgeLabel
						| GraphAttributes::edgeType);
				DeriveUMLGraph(G, *GA, *UMLG, information);
				delete GA;
				LGA = UMLG;
				layout.call(*UMLG);
			} else {
				layout.call(*LGA);
			}
			break;
		}
		case FMMM: {
			FMMMLayout layout;
			layout.useHighLevelOptions(true);
			double unitEdgeLength;
			if (GetOption(OPTION_EDGE_LENGTH, unitEdgeLength, options)) {
				layout.unitEdgeLength(unitEdgeLength);
			}
			bool newInitialPlacement;
			if (GetOption(OPTION_NEW_INITIAL_PLACEMENT, newInitialPlacement,
					options)) {
				layout.newInitialPlacement(newInitialPlacement);
			}
			int qvs;
			if (GetOption(OPTION_QUALITY_VS_SPEED, qvs, options)) {
				TRANSFORM_QUALITY_VS_SPEED(qualityVsSpeed, qvs);
				layout.qualityVersusSpeed(qualityVsSpeed);
			}
			layout.call(*LGA);
			break;
		}
		case DAVIDSON_HAREL: {
			DavidsonHarelLayout layout;
			int costs;
			if (GetOption(OPTION_COSTS, costs, options)) {
				TRANSFORM_COSTS(theCosts, costs);
				layout.fixSettings(theCosts);
			}
			int speed;
			if (GetOption(OPTION_SPEED, speed, options)) {
				TRANSFORM_SPEED(theSpeed, speed);
				layout.setSpeed(theSpeed);
			}
			double edgeLength;
			if (GetOption(OPTION_EDGE_LENGTH, edgeLength, options)) {
				layout.setPreferredEdgeLength(edgeLength);
			}
			layout.call(*LGA);
			break;
		}
		case FRUCHTERMAN_REINGOLD: {
			SpringEmbedderFR layout;
			int iterations;
			if (GetOption(OPTION_ITERATIONS, iterations, options)) {
				layout.iterations(iterations);
			}
			double fineness;
			if (GetOption(OPTION_FINENESS, fineness, options)) {
				layout.fineness(fineness);
			}
			bool noise;
			if (GetOption(OPTION_NOISE, noise, options)) {
				layout.noise(noise);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layout.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layout.pageRatio(pageRatio);
			}
			layout.call(*LGA);
			break;
		}
		case GEM: {
			GEMLayout layout;
			int numberOfRounds;
			if (GetOption(OPTION_NUMBER_OF_ROUNDS, numberOfRounds, options)) {
				layout.numberOfRounds(numberOfRounds);
			}
			double minimalTemperature;
			if (GetOption(OPTION_MINIMAL_TEMPERATURE, minimalTemperature,
					options)) {
				layout.minimalTemperature(minimalTemperature);
			}
			double initialTemperature;
			if (GetOption(OPTION_INITIAL_TEMPERATURE, initialTemperature,
					options)) {
				layout.initialTemperature(initialTemperature);
			}
			double gravitationalConstant;
			if (GetOption(OPTION_GRAVITATIONAL_CONSTANT, gravitationalConstant,
					options)) {
				layout.gravitationalConstant(gravitationalConstant);
			}
			double desiredLength;
			if (GetOption(OPTION_DESIRED_LENGTH, desiredLength, options)) {
				layout.desiredLength(desiredLength);
			}
			double maximalDisturbance;
			if (GetOption(OPTION_MAXIMAL_DISTURBANCE, maximalDisturbance,
					options)) {
				layout.maximalDisturbance(maximalDisturbance);
			}
			double rotationAngle;
			if (GetOption(OPTION_ROTATION_ANGLE, rotationAngle, options)) {
				layout.rotationAngle(rotationAngle);
			}
			double oscillationAngle;
			if (GetOption(OPTION_OSCILLATION_ANGLE, oscillationAngle, options)) {
				layout.oscillationAngle(oscillationAngle);
			}
			double rotationSensitivity;
			if (GetOption(OPTION_ROTATION_SENSITIVITY, rotationSensitivity,
					options)) {
				layout.rotationSensitivity(rotationSensitivity);
			}
			double oscillationSensitivity;
			if (GetOption(OPTION_OSCILLATION_SENSITIVITY,
					oscillationSensitivity, options)) {
				layout.oscillationSensitivity(oscillationSensitivity);
			}
			int attractionFormula;
			if (GetOption(OPTION_ATTRACTION_FORMULA, attractionFormula, options)) {
				layout.attractionFormula(attractionFormula);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layout.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layout.pageRatio(pageRatio);
			}
			layout.call(*LGA);
			break;
		}
		case CIRCULAR: {
			CircularLayout layout;
			double minDistCircle;
			if (GetOption(OPTION_MIN_DIST_CIRCLE, minDistCircle, options)) {
				layout.minDistCircle(minDistCircle);
			}
			double minDistLevel;
			if (GetOption(OPTION_MIN_DIST_LEVEL, minDistLevel, options)) {
				layout.minDistLevel(minDistLevel);
			}
			double minDistSibling;
			if (GetOption(OPTION_MIN_DIST_SIBLING, minDistSibling, options)) {
				layout.minDistSibling(minDistSibling);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layout.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layout.pageRatio(pageRatio);
			}
			layout.call(*LGA);
			break;
		}
		case TREE: {
			TreeLayout layout;
			double siblingDistance;
			if (GetOption(OPTION_SIBLING_DISTANCE, siblingDistance, options)) {
				layout.siblingDistance(siblingDistance);
			}
			double subtreeDistance;
			if (GetOption(OPTION_SUBTREE_DISTANCE, subtreeDistance, options)) {
				layout.subtreeDistance(subtreeDistance);
			}
			double levelDistance;
			if (GetOption(OPTION_LEVEL_DISTANCE, levelDistance, options)) {
				layout.levelDistance(levelDistance);
			}
			double treeDistance;
			if (GetOption(OPTION_TREE_DISTANCE, treeDistance, options)) {
				layout.treeDistance(treeDistance);
			}
			bool orthogonal;
			if (GetOption(OPTION_ORTHOGONAL, orthogonal, options)) {
				layout.orthogonalLayout(orthogonal);
			}
			int orientation;
			if (GetOption(OPTION_ORIENTATION, orientation, options)) {
				TRANSFORM_ORIENTATION(theOrientation, orientation);
				layout.orientation(theOrientation);
			}
			layout.call(*LGA);
			break;
		}
		case RADIAL_TREE: {
			RadialTreeLayout layout;
			double levelDistance;
			if (GetOption(OPTION_LEVEL_DISTANCE, levelDistance, options)) {
				layout.levelDistance(levelDistance);
			}
			double ccDistance;
			if (GetOption(OPTION_CC_DISTANCE, ccDistance, options)) {
				layout.connectedComponentDistance(ccDistance);
			}
			layout.call(*LGA);
			break;
		}
		case UPWARD_PLANARIZATION: {
			UpwardPlanarizationLayout layout;
			LayerBasedUPRLayout* layerBasedUPRLayout =
					new LayerBasedUPRLayout();
			FastHierarchyLayout* fastHierarchyLayout =
					new FastHierarchyLayout();
			layerBasedUPRLayout->setLayout(fastHierarchyLayout);
			layout.setUPRLayout(layerBasedUPRLayout);
			double nodeDistance;
			if (GetOption(OPTION_NODE_DISTANCE, nodeDistance, options)) {
				fastHierarchyLayout->nodeDistance(nodeDistance);
			}
			double layerDistance;
			if (GetOption(OPTION_LAYER_DISTANCE, layerDistance, options)) {
				fastHierarchyLayout->layerDistance(layerDistance);
			}
			layout.call(*LGA);
			break;
		}
		case FAST_MULTIPOLE: {
			FastMultipoleEmbedder layout;
			int multipolePrec;
			if (GetOption(OPTION_MULTIPOLE_PREC, multipolePrec, options)) {
				layout.setMultipolePrec(multipolePrec);
			}
			int iterations;
			if (GetOption(OPTION_ITERATIONS, iterations, options)) {
				layout.setNumIterations(iterations);
			}
			bool randomize;
			if (GetOption(OPTION_RANDOMIZE, randomize, options)) {
				layout.setRandomize(randomize);
			}
			layout.call(*LGA);
			break;
		}
		case FAST_MULTIPOLE_MULTILEVEL: {
			FastMultipoleMultilevelEmbedder layout;
			int bound;
			if (GetOption(OPTION_MULTILEVEL_UNNAL, bound, options)) {
				layout.multilevelUntilNumNodesAreLess(bound);
			}
			layout.call(*LGA);
			break;
		}
		case KAMADA_KAWAI: {
			SpringEmbedderKK layout;
			double edgeLength;
			if (GetOption(OPTION_EDGE_LENGTH, edgeLength, options)) {
				layout.setDesLength(edgeLength);
			}
			int localIterations;
			if (GetOption(OPTION_LOCAL_ITERATIONS, localIterations, options)) {
				layout.setMaxLocalIterations(localIterations);
				layout.computeMaxIterations(false);
			}
			int globalIterations;
			if (GetOption(OPTION_GLOBAL_ITERATIONS, globalIterations, options)) {
				layout.setMaxGlobalIterations(globalIterations);
				layout.computeMaxIterations(false);
			}
			bool useLayout;
			if (GetOption(OPTION_USE_LAYOUT, useLayout, options)) {
				layout.setUseLayout(useLayout);
			}
			double stopTolerance;
			if (GetOption(OPTION_STOP_TOLERANCE, stopTolerance, options)) {
				layout.setStopTolerance(stopTolerance);
			}
			layout.call(*LGA);
			break;
		}
		case STRESS_MAJORIZATION: {
			StressMajorization layout;
			int iterations;
			if (GetOption(OPTION_ITERATIONS, iterations, options)) {
				layout.setIterations(iterations);
			}
			int localIterations;
			if (GetOption(OPTION_LOCAL_ITERATIONS, localIterations, options)) {
				layout.setMaxLocalIterations(localIterations);
				layout.computeMaxIterations(false);
			}
			int globalIterations;
			if (GetOption(OPTION_GLOBAL_ITERATIONS, globalIterations, options)) {
				layout.setMaxGlobalIterations(globalIterations);
				layout.computeMaxIterations(false);
			}
			bool useLayout;
			if (GetOption(OPTION_USE_LAYOUT, useLayout, options)) {
				layout.setUseLayout(useLayout);
			}
			double stopTolerance;
			if (GetOption(OPTION_STOP_TOLERANCE, stopTolerance, options)) {
				layout.setStopTolerance(stopTolerance);
			}
			bool upward;
			if (GetOption(OPTION_UPWARD, upward, options)) {
				layout.upward(upward);
			}
			bool radial;
			if (GetOption(OPTION_RADIAL, radial, options)) {
				layout.radial(radial);
			}
			layout.call(*LGA);
			break;
		}
		case DOMINANCE: {
			DominanceLayout layout;
			int gridDistance;
			if (GetOption(OPTION_GRID_DISTANCE, gridDistance, options)) {
				layout.setMinGridDistance(gridDistance);
			}
			layout.call(*LGA);
			break;
		}
		case VISIBILITY: {
			VisibilityLayout layout;
			int gridDistance;
			if (GetOption(OPTION_GRID_DISTANCE, gridDistance, options)) {
				layout.setMinGridDistance(gridDistance);
			}
			layout.call(*LGA);
			break;
		}
		case FRAYSSEIX_PACH_POLLACK: {
			FPPLayout layout;
			double separation;
			if (GetOption(OPTION_SEPARATION, separation, options)) {
				layout.separation(separation);
			}
			layout.call(*LGA);
			break;
		}
		case SCHNYDER: {
			SchnyderLayout layout;
			double separation;
			if (GetOption(OPTION_SEPARATION, separation, options)) {
				layout.separation(separation);
			}
			layout.call(*LGA);
			break;
		}
		case CANONICAL_ORDER: {
			if (!isPlanar(G)) {
				throw PreconditionViolatedException(pvcPlanar, __FILE__, __LINE__);
			}
			PlanarStraightLayout layout;
			double baseRatio;
			if (GetOption(OPTION_BASE_RATIO, baseRatio, options)) {
				layout.baseRatio(baseRatio);
			}
			layout.call(*LGA);
			break;
		}
		case MIXED_MODEL: {
			if (!isPlanar(G)) {
				throw PreconditionViolatedException(pvcPlanar, __FILE__, __LINE__);
			}
			MixedModelLayout layout;
			layout.call(*LGA);
			break;
		}
		case CONVEX_GRID: {
			if (!isPlanar(G)) {
				throw PreconditionViolatedException(pvcPlanar, __FILE__, __LINE__);
			}
			PlanarDrawLayout layout;
			double baseRatio;
			if (GetOption(OPTION_BASE_RATIO, baseRatio, options)) {
				layout.baseRatio(baseRatio);
			}
			layout.call(*LGA);
			break;
		}
		case BALLOON: {
			BalloonLayout layout;
			layout.call(*LGA);
			break;
		}
		default:
			throw runtime_error("The specified layouter does not exist.");
		}

		// add the edge intersections with the nodes bounding box as bend points
		LGA->addNodeCenter2Bends(1);

		// perform the label layout
		LI = new LabelInterface(*LGA);
		TransferLabels(G, *LGA, *LI, information);
		ELabelPosSimple simpleLabelLayout;
		double edgeDistance;
		if (GetOption(OPTION_LABEL_EDGE_DISTANCE, edgeDistance, options)) {
			simpleLabelLayout.m_edgeDistance = edgeDistance;
		}
		double marginDistance;
		if (GetOption(OPTION_LABEL_MARGIN_DISTANCE, marginDistance, options)) {
			simpleLabelLayout.m_marginDistance = marginDistance;
		}
		simpleLabelLayout.m_midOnEdge = false;
		simpleLabelLayout.call(*LGA, *LI);
		return LGA;

	} catch (DynamicCastFailedException& e) {
		lastLayoutError = ogdfExceptionToString("DynamicCastFailedException", e);
	} catch (InsufficientMemoryException& e) {
		lastLayoutError = ogdfExceptionToString("InsufficientMemoryException", e);
	} catch (NoStdComparerException& e) {
		lastLayoutError = ogdfExceptionToString("NoStdComparerException", e);
	} catch (PreconditionViolatedException& e) {
		lastLayoutError = ogdfPreconditionExceptionToString(e);
	} catch (AlgorithmFailureException& e) {
		lastLayoutError = ogdfAlgorithmExceptionToString(e);
	} catch (LibraryNotSupportedException& e) {
		lastLayoutError = ogdfLibraryExceptionToString(e);
	} catch (const exception& e) {
		lastLayoutError = e.what();
	}
	delete LGA;
	return NULL;
}

/*
 * Return the message stored for the last error that occurred while executing layout.
 */
const string& GetLastLayoutError() {
	return lastLayoutError;
}
