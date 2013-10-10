/**
 * @file    LibavoidRouting.h
 * @author  uru
 * @version 0.1.0
 *
 * @section LICENSE
 *
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * @section DESCRIPTION
 *
 * Definition of Libavoid's options and functions to setup the graph for
 * connector routing as well as to write the results back to an output stream.
 *
 * Protocol:
 *  - All nodes are passed together with a continuously increasing id starting by 1. (1 2 3 4 ...) 
 *  - The same goes for the edges. 
 */
#include <iostream>
#include <string>
#include <sstream>

#include "libavoid/libavoid.h"

/*
 * Edge Routing
 */
#define EDGE_ROUTING                "de.cau.cs.kieler.edgeRouting"
#define EDGE_ROUTING_POLYLINE       "POLYLINE"
#define EDGE_ROUTING_ORTHOGONAL     "ORTHOGONAL"

/*
 * Routing Penalties
 */
#define SEGMENT_PENALTY 			"de.cau.cs.kieler.kiml.libavoid.segmentPenalty"
#define ANGLE_PENALTY 				"de.cau.cs.kieler.kiml.libavoid.anglePenalty"
#define CROSSING_PENALTY 			"de.cau.cs.kieler.kiml.libavoid.crossingPenalty"
#define CLUSTER_CROSSING_PENALTY 	"de.cau.cs.kieler.kiml.libavoid.clusterCrossingPenalty"
#define FIXED_SHARED_PATH_PENALTY 	"de.cau.cs.kieler.kiml.libavoid.fixedSharedPathPenalty"
#define PORT_DIRECTION_PENALTY		"de.cau.cs.kieler.kiml.libavoid.portDirectionPenalty"
#define SHAPE_BUFFER_DISTANCE 		"de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance"
#define IDEAL_NUDGING_DISTANCE 		"de.cau.cs.kieler.kiml.libavoid.idealNudgingDistance"

/*
 * Routing Options
 */
#define NUDGE_ORTHOGONAL_SEGMENTS			"de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalSegmentsConnectedToShapes"
#define IMPROVE_HYPEREDGES					"de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingJunctions"
#define PENALISE_ORTH_SHATE_PATHS			"de.cau.cs.kieler.kiml.libavoid.penaliseOrthogonalSharedPathsAtConnEnds"
#define NUDGE_ORTHOGONAL_COLINEAR_SEGMENTS	"de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalTouchingColinearSegments"

/*
 * Port Sides 
 */
#define PORT_SIDE_NORTH		"NORTH"
#define PORT_SIDE_EAST		"EAST"
#define PORT_SIDE_SOUTH		"SOUTH"
#define PORT_SIDE_WEST		"WEST"

/*
 * Pin Types
 */
//const unsigned int PIN_CENTRE = 1;
const unsigned int PIN_ARBITRARY = 0;

/**
 * Assembling the graph
 */
void setPenalty(std::string optionId, std::string token, Avoid::Router* router);

void setOption(std::string optionId, std::string token, Avoid::Router* router);

void addNode(std::vector<std::string> &tokens, std::vector<Avoid::ShapeRef*> &shapes,
        Avoid::Router* router);

void addPort(std::vector<std::string> &tokens, std::vector<Avoid::ShapeConnectionPin*> &pins, 
		std::vector<Avoid::ShapeRef*> &shapes, Avoid::Router* router);

void addEdge(std::vector<std::string> &tokens, Avoid::ConnType connectorType,
        std::vector<Avoid::ShapeRef*> &shapes, std::vector<Avoid::ConnRef*> &cons,
        Avoid::Router* router);

/**
 * Writing the graph to the output stream
 */
void writeLayout(std::ostream& out, std::vector<Avoid::ConnRef*> cons);

/*
 * Convenient methods
 */
inline double toDouble(std::string const& s) {
    std::istringstream i(s);
    double x;
    i >> x;

    return x;
}

inline int toInt(std::string const& s) {
    std::istringstream i(s);
    int x;
    i >> x;

    return x;
}

inline bool toBool(std::string const& s) {
    return (s == "true" || s == "TRUE" || s == "True");
}

void tokenize(std::string text, std::vector<std::string>& tokens);
