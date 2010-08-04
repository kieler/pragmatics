/*
 * The c++ side wrapper of the OGDF library for java.
 * Uses jni to provide it's functionality.
 *
 * Author: mri
 */

#include <cstdlib>
#include <cstdio>
#include <map>
#include <stdexcept>

#include <ogdf/basic/exceptions.h>
#include <ogdf/basic/geometry.h>
#include <ogdf/basic/Graph_d.h>
#include <ogdf/basic/GraphAttributes.h>
#include <ogdf/basic/UMLGraph.h>
#include <ogdf/labeling/ELabelInterface.h>
#include <ogdf/labeling/ELabelPosSimple.h>
#include <ogdf/labeling/EdgeLabel.h>
#include <ogdf/module/LayoutModule.h>
#include <ogdf/module/UMLLayoutModule.h>
#include <ogdf/layered/SugiyamaLayout.h>
#include <ogdf/layered/FastHierarchyLayout.h>
#include <ogdf/orthogonal/OrthoRep.h>
#include <ogdf/orthogonal/OrthoLayout.h>
#include <ogdf/planarity/PlanarizationLayout.h>
#include <ogdf/energybased/FMMMLayout.h>
#include <ogdf/energybased/DavidsonHarelLayout.h>
#include <ogdf/energybased/SpringEmbedderFR.h>
#include <ogdf/misclayout/CircularLayout.h>
#include <ogdf/tree/TreeLayout.h>
#include <ogdf/tree/RadialTreeLayout.h>
#include <ogdf/upward/UpwardPlanarizationLayout.h>

// Has to be last include.
// Some defines in the OGDF library (for __int64, __uint64, ...) are also
// required by the JNI. Any other approach to solve this problem involves
// changing the OGDF library. This is only relevant for some platforms.
#include <jni.h>

using namespace std;
using namespace ogdf;

#ifndef SAFE_DELETE
#define SAFE_DELETE(a) if(a) { delete a; a = 0; }
#endif

#define EDGE_TYPE_ASSOCIATION  0
#define EDGE_TYPE_GENERALIZATION 1
#define EDGE_TYPE_DEPENDENCY 2

#define LABEL_TYPE_END1 0
#define LABEL_TYPE_MULT1 1
#define LABEL_TYPE_NAME 2
#define LABEL_TYPE_END2 3
#define LABEL_TYPE_MULT2 4

#define DIRECTION_NORTH 0
#define DIRECTION_SOUTH 1
#define DIRECTION_WEST 2
#define DIRECTION_EAST 3

#define GORGEOUS_AND_EFFICIENT 0
#define BEAUTIFUL_AND_FAST 1
#define NICE_AND_INCREDIBLE_SPEED 2

#define COSTS_STANDARD 0
#define COSTS_REPULSE 1
#define COSTS_PLANAR 2

#define SPEED_FAST 0
#define SPEED_MEDIUM 1
#define SPEED_HQ 2

#define ORIENTATION_TOP_TO_BOTTOM 0
#define ORIENTATION_BOTTOM_TO_TOP 1
#define ORIENTATION_LEFT_TO_RIGHT 2
#define ORIENTATION_RIGHT_TO_LEFT 3

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

#ifdef __cplusplus
extern "C" {
#endif

// the current graph
Graph* graph = 0;
GraphAttributes* graphAttributes = 0;
bool isUmlGraph = false;
DRect boundingBox;
// the current layouter
LayoutModule* layouter = 0;
bool isUmlLayouter = false;
// the label layouter
ELabelPosSimple* labelLayouter = 0;
ELabelInterface<double>* labelInterface = 0;
// element mappings
map<long, node> nodeMap;
map<long, edge> edgeMap;
long nodeIndex = 0;
long edgeIndex = 0;
// the current polyline and iterator
DPolyline* polyline;
ListConstIterator<DPoint> bendsIt;

// helper functions

// functions to convert ogdf exceptions to readable messages

std::string ogdfExceptionToString(const char* name, Exception& e) {
	std::string result = name;
	result += "\n\tFile: ";
	result += e.file() != 0 ? e.file() : "Unknown";
	result += "\n\tLine: ";
	if(e.line() != -1) {
		char buf[11];
		sprintf(buf, "%d", e.line());
		result += buf;
	}
	else 
		result += "Unknown";
	return result;
}

std::string ogdfPreconditionExceptionToString(PreconditionViolatedException& e) {
	std::string result = ogdfExceptionToString("PreconditionViolatedException", e);
	result += "\n\tDescription: ";
	switch(e.exceptionCode()) {
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

std::string ogdfAlgorithmExceptionToString(AlgorithmFailureException& e) {
	std::string result = ogdfExceptionToString("AlgorithmFailureException", e);
	result += "\n\tDescription:\n";
	switch(e.exceptionCode()) {
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

std::string ogdfLibraryExceptionToString(LibraryNotSupportedException& e) {
	std::string result = ogdfExceptionToString("LibraryNotSupportedException", e);
	result += "\n\tDescription:\n";
	switch(e.exceptionCode()) {
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

void throwJavaException(JNIEnv* jenv, const char* msg) {
    jenv->ExceptionClear();
    jclass excep = jenv->FindClass("java/lang/RuntimeException");
    if (excep) {
        jenv->ThrowNew(excep, msg);
    }
}

// the jni wrapper interface

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createGraph
  (JNIEnv *, jclass, jboolean umlGraph) {
    SAFE_DELETE(graphAttributes);
    SAFE_DELETE(graph);
    isUmlGraph = false;
    nodeMap.clear();
    edgeMap.clear();
    nodeIndex = 0;
    edgeIndex = 0;

    isUmlGraph = umlGraph > 0;
    graph = new Graph;
    if (isUmlGraph) {
        graphAttributes = new UMLGraph(*graph,
            GraphAttributes::nodeGraphics | GraphAttributes::edgeGraphics |
            GraphAttributes::edgeLabel | GraphAttributes::edgeType);
    } else {
        graphAttributes = new GraphAttributes(*graph,
            GraphAttributes::nodeGraphics | GraphAttributes::edgeGraphics |
            GraphAttributes::edgeLabel);
    }
}

JNIEXPORT jlong JNICALL Java_net_ogdf_lib_Ogdf_Graph_1addNode
  (JNIEnv *, jclass, jfloat x, jfloat y, jfloat w, jfloat h) {
    node n = graph->newNode();
    graphAttributes->x(n) = x;
    graphAttributes->y(n) = y;
    graphAttributes->width(n) = w;
    graphAttributes->height(n) = h;
    long index = nodeIndex++;
    nodeMap[index] = n;
    return index;
}

JNIEXPORT jlong JNICALL Java_net_ogdf_lib_Ogdf_Graph_1addEdge
  (JNIEnv *, jclass, jlong node1, jlong node2) {
    node n1 = nodeMap[node1];
    node n2 = nodeMap[node2];
    edge e = graph->newEdge(n1, n2);
    long index = edgeIndex++;
    edgeMap[index] = e;
    return index;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_Graph_1setEdgeType
  (JNIEnv *, jclass, jlong edge, jint type) {
    Graph::EdgeType t;
    switch(type) {
    case EDGE_TYPE_ASSOCIATION:
        t = Graph::association;
        break;
    case EDGE_TYPE_GENERALIZATION:
        t = Graph::association;
        break;
    case EDGE_TYPE_DEPENDENCY:
        t = Graph::dependency;
        break;
    default:
        return;
    }
    graphAttributes->type(edgeMap[edge]) = t;
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getNodeX
  (JNIEnv *, jclass, jlong node) {
    return graphAttributes->x(nodeMap[node]);
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getNodeY
  (JNIEnv *, jclass, jlong node) {
    return graphAttributes->y(nodeMap[node]);
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getNodeWidth
  (JNIEnv *, jclass, jlong node) {
    return graphAttributes->width(nodeMap[node]);
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getNodeHeight
  (JNIEnv *, jclass, jlong node) {
    return graphAttributes->height(nodeMap[node]);
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getBoundingBox
  (JNIEnv *, jclass) {
    boundingBox = graphAttributes->boundingBox();
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getBoundingBoxX
  (JNIEnv *, jclass) {
    return boundingBox.p1().m_x;
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getBoundingBoxY
  (JNIEnv *, jclass) {
    return boundingBox.p1().m_y;
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getBoundingBoxWidth
  (JNIEnv *, jclass) {
    return boundingBox.width();
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getBoundingBoxHeight
  (JNIEnv *, jclass) {
    return boundingBox.height();
}

JNIEXPORT jint JNICALL Java_net_ogdf_lib_Ogdf_Graph_1getNumberOfBends
  (JNIEnv *, jclass, jlong edge) {
    DPolyline& polyline = graphAttributes->bends(edgeMap[edge]);
    return polyline.size();
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_Graph_1addNodeCenter2Bends
  (JNIEnv *, jclass) {
    graphAttributes->addNodeCenter2Bends(1);
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createBendsInterator
  (JNIEnv *, jclass, jlong edge) {
    polyline = &graphAttributes->bends(edgeMap[edge]);
    bendsIt = polyline->begin();
}

JNIEXPORT jboolean JNICALL Java_net_ogdf_lib_Ogdf_BendsIterator_1hasNext
  (JNIEnv *, jclass) {
    return bendsIt != polyline->end();
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_BendsIterator_1next
  (JNIEnv *, jclass) {
    bendsIt++;
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_BendsIterator_1getX
  (JNIEnv *, jclass) {
    return (*bendsIt).m_x;
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_BendsIterator_1getY
  (JNIEnv *, jclass) {
    return (*bendsIt).m_y;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createLabelInterface
  (JNIEnv *, jclass) {
    SAFE_DELETE(labelInterface);
    labelInterface = new ELabelInterface<double>(*graphAttributes);
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_Label_1addLabel
  (JNIEnv *, jclass, jlong edge, jint type, jfloat width, jfloat height) {
    EdgeLabel<double>& label = labelInterface->getLabel(edgeMap[edge]);
    TRANSFORM_LABEL_TYPE(labelType, type, return);
    label.addType(labelType);
    label.setWidth(labelType, width);
    label.setHeight(labelType, height);
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Label_1getX
  (JNIEnv *, jclass, jlong edge, jint type) {
    EdgeLabel<double>& label = labelInterface->getLabel(edgeMap[edge]);
    TRANSFORM_LABEL_TYPE(labelType, type, return 0);
    return label.getX(labelType);
}

JNIEXPORT jfloat JNICALL Java_net_ogdf_lib_Ogdf_Label_1getY
  (JNIEnv *, jclass, jlong edge, jint type) {
    EdgeLabel<double>& label = labelInterface->getLabel(edgeMap[edge]);
    TRANSFORM_LABEL_TYPE(labelType, type, return 0);
    return label.getY(labelType);
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createLabelLayouter
  (JNIEnv *, jclass) {
    SAFE_DELETE(labelLayouter);
    labelLayouter = new ELabelPosSimple();
    labelLayouter->m_midOnEdge = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_LabelLayouter_1setEdgeDistance
  (JNIEnv *, jclass, jfloat distance) {
    labelLayouter->m_edgeDistance = distance;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_LabelLayouter_1setMarginDistance
  (JNIEnv *, jclass, jfloat distance) {
    labelLayouter->m_marginDistance = distance;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_LabelLayouter_1layout
  (JNIEnv *, jclass) {
    if (labelLayouter) {
        try {
            labelLayouter->call(*graphAttributes, *labelInterface);
        }
        catch(...) {
            printf("Error: an unknown error occurred\n");
        }
    }
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createSugiyamaLayouter
  (JNIEnv *, jclass, jfloat nodeDistance, jfloat layerDistance) {
    SAFE_DELETE(layouter);
    SugiyamaLayout* sl = new SugiyamaLayout();
    layouter = sl;
    FastHierarchyLayout* fhl = new FastHierarchyLayout();
    fhl->nodeDistance(nodeDistance);
    fhl->layerDistance(layerDistance);
    sl->setLayout(fhl);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createMixedUpwardPlanarizationLayouter
  (JNIEnv *, jclass, jfloat pageRatio, jfloat separation, jint direction) {
    SAFE_DELETE(layouter);
    PlanarizationLayout* pl = new PlanarizationLayout();
    layouter = pl;
    pl->pageRatio(pageRatio);
    pl->preprocessCliques(true);
    OrthoLayout* ol = new OrthoLayout();
    ol->separation(separation);
    TRANSFORM_DIRECTION(layoutDirection, direction);
    ol->preferedDir(layoutDirection);
    pl->setPlanarLayouter(ol);
    isUmlLayouter = true;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createFMMMLayouter
  (JNIEnv *, jclass, jfloat unitEdgeLength, jboolean newInitialPlacement,
    jint qvs) {
    SAFE_DELETE(layouter);
    FMMMLayout* fmmml = new FMMMLayout();
    layouter = fmmml;
    fmmml->useHighLevelOptions(true);
    fmmml->unitEdgeLength(unitEdgeLength);
    fmmml->newInitialPlacement(newInitialPlacement);
    TRANSFORM_QUALITY_VS_SPEED(qualityVsSpeed, qvs);
    fmmml->qualityVersusSpeed(qualityVsSpeed);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createFMMMLayouterDetail
  (JNIEnv *, jclass, jboolean coolTemperature, jfloat coolValue,
jfloat fineTuneScalar, jint fineTuningIterations, jint fixedIterations,
jfloat forceScalingFactor, jint gridQuotient, jint maxIterFactor,
jfloat minDistCC, jint minGraphSize, jint particlesInLeaves, jint precision,
jfloat postSpringStrength, jfloat strengthOfRepForces, jint randomTries,
jfloat repForcesStrength, jfloat springStrength,
jint stepsForRotatingComponents, jfloat threshold) {
    SAFE_DELETE(layouter);
    FMMMLayout* fmmml = new FMMMLayout();
    layouter = fmmml;
    fmmml->useHighLevelOptions(false);
    fmmml->threshold(threshold);
    fmmml->stepsForRotatingComponents(stepsForRotatingComponents);
    fmmml->springStrength(springStrength);
    fmmml->repForcesStrength(repForcesStrength);
    fmmml->randomTries(randomTries);
    fmmml->postStrengthOfRepForces(strengthOfRepForces);
    fmmml->postSpringStrength(postSpringStrength);
    fmmml->nmPrecision(precision);
    fmmml->nmParticlesInLeaves(particlesInLeaves);
    fmmml->minGraphSize(minGraphSize);
    fmmml->minDistCC(minDistCC);
    fmmml->maxIterFactor(maxIterFactor);
    fmmml->frGridQuotient(gridQuotient);
    fmmml->forceScalingFactor(forceScalingFactor);
    fmmml->fixedIterations(fixedIterations);
    fmmml->fineTuningIterations(fineTuningIterations);
    fmmml->fineTuneScalar(fineTuneScalar);
    fmmml->coolValue(coolValue);
    fmmml->coolTemperature(coolTemperature);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createDavidsonHarelLayouter
    (JNIEnv *, jclass, jint costs, jint speed, jfloat edgeLength) {
    SAFE_DELETE(layouter);
    DavidsonHarelLayout* dhl = new DavidsonHarelLayout();
    layouter = dhl;
    TRANSFORM_COSTS(theCosts, costs);
    dhl->fixSettings(theCosts);
    TRANSFORM_SPEED(theSpeed, speed);
    dhl->setSpeed(theSpeed);
    dhl->setPreferredEdgeLength(edgeLength);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createSpringEmbedderFRLayouter
    (JNIEnv *, jclass, jint iterations) {
    SAFE_DELETE(layouter);
    SpringEmbedderFR* sefr = new SpringEmbedderFR();
    layouter = sefr;
    sefr->iterations(iterations);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createCircularLayouter
    (JNIEnv *, jclass, jfloat minDistCircle, jfloat minDistLevel,
    jfloat minDistSibling, jfloat minDistCC) {
    SAFE_DELETE(layouter);
    CircularLayout* cl = new CircularLayout();
    layouter = cl;
    cl->minDistCircle(minDistCircle);
    cl->minDistLevel(minDistLevel);
    cl->minDistSibling(minDistSibling);
    cl->minDistCC(minDistCC);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createTreeLayouter
    (JNIEnv *, jclass, jfloat siblingDistance,
    jfloat subtreeDistance, jfloat levelDistance, jfloat treeDistance,
    jboolean orthogonal, jint orientation) {
    SAFE_DELETE(layouter);
    TreeLayout* tl = new TreeLayout();
    layouter = tl;
    tl->siblingDistance(siblingDistance);
    tl->subtreeDistance(subtreeDistance);
    tl->levelDistance(levelDistance);
    tl->treeDistance(treeDistance);
    tl->orthogonalLayout(orthogonal);
    TRANSFORM_ORIENTATION(theOrientation, orientation);
    tl->orientation(theOrientation);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createRadialTreeLayouter
    (JNIEnv *, jclass, jfloat minDistLevel, jfloat minDistCC) {
    SAFE_DELETE(layouter);
    RadialTreeLayout* rtl = new RadialTreeLayout();
    layouter = rtl;
    rtl->levelDistance(minDistLevel);
    rtl->connectedComponentDistance(minDistCC);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_createUpwardPlanarizationLayouter
    (JNIEnv *, jclass, jfloat nodeDistance, jfloat layerDistance) {
    SAFE_DELETE(layouter);
    UpwardPlanarizationLayout* upl = new UpwardPlanarizationLayout();
    layouter = upl;
    FastHierarchyLayout* fhl = new FastHierarchyLayout();
    fhl->nodeDistance(nodeDistance);
    fhl->layerDistance(layerDistance);
    LayerBasedUPRLayout* lbuprl = new LayerBasedUPRLayout();
    lbuprl->setLayout(fhl);
    upl->setUPRLayout(lbuprl);
    isUmlLayouter = false;
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_layout
  (JNIEnv *jenv, jclass) {
    if (layouter) {
        try {
            if (isUmlLayouter && isUmlGraph) {
                ((UMLLayoutModule*)layouter)->call(*((UMLGraph*)graphAttributes));
            } else {
                layouter->call(*graphAttributes);
            }
        } catch (DynamicCastFailedException& e) {
            throwJavaException(jenv,
                ogdfExceptionToString("DynamicCastFailedException", e).c_str());
        } catch (InsufficientMemoryException& e) {
            throwJavaException(jenv,
                ogdfExceptionToString("InsufficientMemoryException", e).c_str());
        } catch (NoStdComparerException& e) {
            throwJavaException(jenv,
                ogdfExceptionToString("NoStdComparerException", e).c_str());
        } catch (PreconditionViolatedException& e) {
            throwJavaException(jenv,
                ogdfPreconditionExceptionToString(e).c_str());
        } catch (AlgorithmFailureException& e) {
            throwJavaException(jenv, ogdfAlgorithmExceptionToString(e).c_str());
        } catch (LibraryNotSupportedException& e) {
            throwJavaException(jenv, ogdfLibraryExceptionToString(e).c_str());
        } catch (const std::exception& e) {
            throwJavaException(jenv, e.what());
        }
    }
}

JNIEXPORT void JNICALL Java_net_ogdf_lib_Ogdf_cleanup
  (JNIEnv *, jclass) {
    SAFE_DELETE(labelLayouter);
    SAFE_DELETE(labelInterface);
    SAFE_DELETE(layouter);
    SAFE_DELETE(graphAttributes);
    SAFE_DELETE(graph);
    isUmlGraph = false;
    isUmlLayouter = false;
    nodeMap.clear();
    edgeMap.clear();
    nodeIndex = 0;
    edgeIndex = 0;
}

#ifdef __cplusplus
}
#endif
