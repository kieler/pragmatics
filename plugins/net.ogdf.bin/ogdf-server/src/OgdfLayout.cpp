/**
 * @file
 * @author  mri (mri@informatik.uni-kiel.de)
 * @version 0.1.0.qualifier
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
#include <ogdf/module/LayoutModule.h>
#include <ogdf/module/UMLLayoutModule.h>
#include <ogdf/labeling/ELabelInterface.h>
#include <ogdf/labeling/ELabelPosSimple.h>
#include <ogdf/layered/SugiyamaLayout.h>
#include <ogdf/layered/FastHierarchyLayout.h>
#include <ogdf/orthogonal/OrthoRep.h>
#include <ogdf/orthogonal/OrthoLayout.h>
#include <ogdf/planarity/PlanarizationLayout.h>
#include <ogdf/energybased/FMMMLayout.h>
#include <ogdf/energybased/DavidsonHarelLayout.h>
#include <ogdf/energybased/SpringEmbedderFR.h>
#include <ogdf/energybased/GEMLayout.h>
#include <ogdf/misclayout/CircularLayout.h>
#include <ogdf/tree/TreeLayout.h>
#include <ogdf/tree/RadialTreeLayout.h>
#include <ogdf/upward/UpwardPlanarizationLayout.h>
#include <ogdf/upward/LayerBasedUPRLayout.h>

using namespace std;
using namespace ogdf;

/*
 * Locals
 */

string lastLayoutError = "";

/*
 * Helper functions for displaying ogdf errors
 */

string ogdfExceptionToString(const char* name, Exception& e) {
	return name;
}

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
 * Helper functions and macros for receiving options
 */

bool GetOption(const string& key, string& value, StringMap& options) {
	StringMap::iterator it = options.find(key);
	if (it == options.end()) {
		return false;
	}
	value = it->second;
	return true;
}

bool GetOption(const string& key, int& value, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	value = atoi(s.c_str());
	return true;
}

bool GetOption(const string& key, double& value, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	value = strtod(s.c_str(), NULL);
	return true;
}

bool GetOption(const string& key, bool& value, StringMap& options) {
	string s;
	if (!GetOption(key, s, options)) {
		return false;
	}
	value = (s == "true" ? true : false);
	return true;
}

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

#define GetOptionSafe(key, value, options) if (!GetOption(key, value, options))\
	{throw runtime_error(string("Missing option: ") + key);}

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
	} else {
		return NO_LAYOUTER;
	}
}

#define TRANSFORM_LABEL_TYPE(n, t, e) eLabelTyp n;\
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
 * Helper functions for transforming and creating graph structures
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

void TransferLabels(Graph& G, GraphAttributes& GA, LabelInterface& LI,
		StringMap& information) {
	edge e;
	forall_edges(e, G) {
		string label = GA.labelEdge(e).cstr();
		for (int type = 0; type < labelNum; ++type) {
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
 * Interface implementation
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
			SugiyamaLayout layouter;
			FastHierarchyLayout* fastHierarchyLayout = new FastHierarchyLayout;
			layouter.setLayout(fastHierarchyLayout);
			int fails;
			if (GetOption(OPTION_FAILS, fails, options)) {
				layouter.fails(fails);
			}
			int runs;
			if (GetOption(OPTION_RUNS, runs, options)) {
				layouter.runs(runs);
			}
			bool transpose;
			if (GetOption(OPTION_TRANSPOSE, transpose, options)) {
				layouter.transpose(transpose);
			}
			bool arrangeCCs;
			if (GetOption(OPTION_ARRANGE_CC, arrangeCCs, options)) {
				layouter.arrangeCCs(arrangeCCs);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layouter.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layouter.pageRatio(pageRatio);
			}
			double nodeDistance;
			if (GetOption(OPTION_NODE_DISTANCE, nodeDistance, options)) {
				fastHierarchyLayout->nodeDistance(nodeDistance);
			}
			double layerDistance;
			if (GetOption(OPTION_LAYER_DISTANCE, layerDistance, options)) {
				fastHierarchyLayout->layerDistance(layerDistance);
			}
			// needs to be called with an GraphAttributes argument to invoke the
			// correct polymorphic method
			layouter.call(*LGA);
			break;
		}
		case PLANARIZATION: {
			PlanarizationLayout layouter;
			OrthoLayout* orthoLayout = new OrthoLayout();
			layouter.setPlanarLayouter(orthoLayout);
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layouter.pageRatio(pageRatio);
			}
			bool preprocessCliques;
			if (GetOption(OPTION_PREPROCESS_CLIQUES, preprocessCliques, options)) {
				layouter.preprocessCliques(preprocessCliques);
			}
			int minCliqueSize;
			if (GetOption(OPTION_MIN_CLIQUE_SIZE, minCliqueSize, options)) {
				layouter.minCliqueSize(minCliqueSize);
			}
			double separation;
			if (GetOption(OPTION_SEPARATION, separation, options)) {
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
				// an UML graph is required to utilize the full functionality of
				// the layouter
				UMLGraph* UMLG = new UMLGraph(G, GraphAttributes::nodeGraphics
						| GraphAttributes::edgeGraphics
						| GraphAttributes::nodeLabel
						| GraphAttributes::edgeLabel
						| GraphAttributes::edgeType);
				DeriveUMLGraph(G, *GA, *UMLG, information);
				delete GA;
				LGA = UMLG;
				layouter.call(*UMLG);
			} else {
				layouter.call(*GA);
			}
			break;
		}
		case FMMM: {
			FMMMLayout layouter;
			layouter.useHighLevelOptions(true);
			double unitEdgeLength;
			if (GetOption(OPTION_EDGE_LENGTH, unitEdgeLength, options)) {
				layouter.unitEdgeLength(unitEdgeLength);
			}
			bool newInitialPlacement;
			if (GetOption(OPTION_NEW_INITIAL_PLACEMENT, newInitialPlacement,
					options)) {
				layouter.newInitialPlacement(newInitialPlacement);
			}
			int qvs;
			if (GetOption(OPTION_QUALITY_VS_SPEED, qvs, options)) {
				TRANSFORM_QUALITY_VS_SPEED(qualityVsSpeed, qvs);
				layouter.qualityVersusSpeed(qualityVsSpeed);
			}
			layouter.call(*LGA);
			break;
		}
		case DAVIDSON_HAREL: {
			DavidsonHarelLayout layouter;
			int costs;
			if (GetOption(OPTION_COSTS, costs, options)) {
				TRANSFORM_COSTS(theCosts, costs);
				layouter.fixSettings(theCosts);
			}
			int speed;
			if (GetOption(OPTION_SPEED, speed, options)) {
				TRANSFORM_SPEED(theSpeed, speed);
				layouter.setSpeed(theSpeed);
			}
			double edgeLength;
			if (GetOption(OPTION_EDGE_LENGTH, edgeLength, options)) {
				layouter.setPreferredEdgeLength(edgeLength);
			}
			layouter.call(*GA);
			break;
		}
		case FRUCHTERMAN_REINGOLD: {
			SpringEmbedderFR layouter;
			int iterations;
			if (GetOption(OPTION_ITERATIONS, iterations, options)) {
				layouter.iterations(iterations);
			}
			double fineness;
			if (GetOption(OPTION_FINENESS, fineness, options)) {
				layouter.fineness(fineness);
			}
			bool noise;
			if (GetOption(OPTION_NOISE, noise, options)) {
				layouter.noise(noise);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layouter.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layouter.pageRatio(pageRatio);
			}
			layouter.call(*GA);
			break;
		}
		case GEM: {
			GEMLayout layouter;
			int numberOfRounds;
			if (GetOption(OPTION_NUMBER_OF_ROUNDS, numberOfRounds, options)) {
				layouter.numberOfRounds(numberOfRounds);
			}
			double minimalTemperature;
			if (GetOption(OPTION_MINIMAL_TEMPERATURE, minimalTemperature,
					options)) {
				layouter.minimalTemperature(minimalTemperature);
			}
			double initialTemperature;
			if (GetOption(OPTION_INITIAL_TEMPERATURE, initialTemperature,
					options)) {
				layouter.initialTemperature(initialTemperature);
			}
			double gravitationalConstant;
			if (GetOption(OPTION_GRAVITATIONAL_CONSTANT, gravitationalConstant,
					options)) {
				layouter.gravitationalConstant(gravitationalConstant);
			}
			double desiredLength;
			if (GetOption(OPTION_DESIRED_LENGTH, desiredLength, options)) {
				layouter.desiredLength(desiredLength);
			}
			double maximalDisturbance;
			if (GetOption(OPTION_MAXIMAL_DISTURBANCE, maximalDisturbance,
					options)) {
				layouter.maximalDisturbance(maximalDisturbance);
			}
			double rotationAngle;
			if (GetOption(OPTION_ROTATION_ANGLE, rotationAngle, options)) {
				layouter.rotationAngle(rotationAngle);
			}
			double oscillationAngle;
			if (GetOption(OPTION_OSCILLATION_ANGLE, oscillationAngle, options)) {
				layouter.oscillationAngle(oscillationAngle);
			}
			double rotationSensitivity;
			if (GetOption(OPTION_ROTATION_SENSITIVITY, rotationSensitivity,
					options)) {
				layouter.rotationSensitivity(rotationSensitivity);
			}
			double oscillationSensitivity;
			if (GetOption(OPTION_OSCILLATION_SENSITIVITY,
					oscillationSensitivity, options)) {
				layouter.oscillationSensitivity(oscillationSensitivity);
			}
			int attractionFormula;
			if (GetOption(OPTION_ATTRACTION_FORMULA, attractionFormula, options)) {
				layouter.attractionFormula(attractionFormula);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layouter.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layouter.pageRatio(pageRatio);
			}
			layouter.call(*GA);
			break;
		}
		case CIRCULAR: {
			CircularLayout layouter;
			double minDistCircle;
			if (GetOption(OPTION_MIN_DIST_CIRCLE, minDistCircle, options)) {
				layouter.minDistCircle(minDistCircle);
			}
			double minDistLevel;
			if (GetOption(OPTION_MIN_DIST_LEVEL, minDistLevel, options)) {
				layouter.minDistLevel(minDistLevel);
			}
			double minDistSibling;
			if (GetOption(OPTION_MIN_DIST_SIBLING, minDistSibling, options)) {
				layouter.minDistSibling(minDistSibling);
			}
			double minDistCC;
			if (GetOption(OPTION_MIN_DIST_CC, minDistCC, options)) {
				layouter.minDistCC(minDistCC);
			}
			double pageRatio;
			if (GetOption(OPTION_PAGE_RATIO, pageRatio, options)) {
				layouter.pageRatio(pageRatio);
			}
			layouter.call(*GA);
			break;
		}
		case TREE: {
			TreeLayout layouter;
			double siblingDistance;
			if (GetOption(OPTION_SIBLING_DISTANCE, siblingDistance, options)) {
				layouter.siblingDistance(siblingDistance);
			}
			double subtreeDistance;
			if (GetOption(OPTION_SUBTREE_DISTANCE, subtreeDistance, options)) {
				layouter.subtreeDistance(subtreeDistance);
			}
			double levelDistance;
			if (GetOption(OPTION_LEVEL_DISTANCE, levelDistance, options)) {
				layouter.levelDistance(levelDistance);
			}
			double treeDistance;
			if (GetOption(OPTION_TREE_DISTANCE, treeDistance, options)) {
				layouter.treeDistance(treeDistance);
			}
			bool orthogonal;
			if (GetOption(OPTION_ORTHOGONAL, orthogonal, options)) {
				layouter.orthogonalLayout(orthogonal);
			}
			int orientation;
			if (GetOption(OPTION_ORIENTATION, orientation, options)) {
				TRANSFORM_ORIENTATION(theOrientation, orientation);
				layouter.orientation(theOrientation);
			}
			layouter.call(*GA);
			break;
		}
		case RADIAL_TREE: {
			RadialTreeLayout layouter;
			double levelDistance;
			if (GetOption(OPTION_LEVEL_DISTANCE, levelDistance, options)) {
				layouter.levelDistance(levelDistance);
			}
			double ccDistance;
			if (GetOption(OPTION_CC_DISTANCE, ccDistance, options)) {
				layouter.connectedComponentDistance(ccDistance);
			}
			layouter.call(*GA);
			break;
		}
		case UPWARD_PLANARIZATION: {
			UpwardPlanarizationLayout layouter;
			LayerBasedUPRLayout* layerBasedUPRLayout =
					new LayerBasedUPRLayout();
			FastHierarchyLayout* fastHierarchyLayout =
					new FastHierarchyLayout();
			layerBasedUPRLayout->setLayout(fastHierarchyLayout);
			layouter.setUPRLayout(layerBasedUPRLayout);
			double nodeDistance;
			if (GetOption(OPTION_NODE_DISTANCE, nodeDistance, options)) {
				fastHierarchyLayout->nodeDistance(nodeDistance);
			}
			double layerDistance;
			if (GetOption(OPTION_LAYER_DISTANCE, layerDistance, options)) {
				fastHierarchyLayout->layerDistance(layerDistance);
			}
			layouter.call(*GA);
			break;
		}
		default:
			throw runtime_error("The specified layouter does not exist.");
		}
		// add the edge intersections with the nodes bounding box as bend points
		LGA->addNodeCenter2Bends(1);
		// perform the label layout -- TODO make label processing optional
		LI = new LabelInterface(*LGA);
		TransferLabels(G, *LGA, *LI, information);
		ELabelPosSimple simpleLabelLayout;
		GetOption(OPTION_LABEL_EDGE_DISTANCE, simpleLabelLayout.m_edgeDistance,
				options);
		GetOption(OPTION_LABEL_MARGIN_DISTANCE,
				simpleLabelLayout.m_edgeDistance, options);
		simpleLabelLayout.m_midOnEdge = false;
		simpleLabelLayout.call(*LGA, *LI);
		return LGA;

	} catch (DynamicCastFailedException& e) {
		lastLayoutError
				= ogdfExceptionToString("DynamicCastFailedException", e);
	} catch (InsufficientMemoryException& e) {
		lastLayoutError = ogdfExceptionToString("InsufficientMemoryException",
				e);
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
	return 0;
}

const string& GetLastLayoutError() {
	return lastLayoutError;
}
