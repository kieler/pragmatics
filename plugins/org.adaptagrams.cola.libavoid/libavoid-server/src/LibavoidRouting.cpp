/**
 * @file    LibavoidRouting.cpp
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
 * The implementation of the functions defined in LibavoidRouting.h.
 */
#include "LibavoidRouting.h"

#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <vector>

#include "libavoid/libavoid.h"

using namespace std;

void tokenize(std::string text, std::vector<string>& tokens) {
    istringstream iss(text);
    copy(istream_iterator < string > (iss), istream_iterator<string>(),
            back_inserter < vector<string> > (tokens));
}

void setPenalty(string optionId, string token, Avoid::Router* router) {

    float value = toDouble(token);

    if (optionId == SEGMENT_PENALTY) {
        router->setRoutingPenalty(Avoid::segmentPenalty, value);
    } else if (optionId == ANGLE_PENALTY) {
        router->setRoutingPenalty(Avoid::anglePenalty, value);
    } else if (optionId == CROSSING_PENALTY) {
        router->setRoutingPenalty(Avoid::crossingPenalty, value);
    } else if (optionId == CLUSTER_CROSSING_PENALTY) {
        router->setRoutingPenalty(Avoid::clusterCrossingPenalty, value);
    } else if (optionId == FIXED_SHARED_PATH_PENALTY) {
        router->setRoutingPenalty(Avoid::fixedSharedPathPenalty, value);
    } else if (optionId == PORT_DIRECTION_PENALTY) {
        router->setRoutingPenalty(Avoid::portDirectionPenalty, value);
    } else if (optionId == SHAPE_BUFFER_DISTANCE) {
        router->setRoutingPenalty(Avoid::shapeBufferDistance, value);
    } else if (optionId == IDEAL_NUDGING_DISTANCE) {
        router->setRoutingPenalty(Avoid::idealNudgingDistance, value);
    } else {
        cerr << "ERROR: unknown penalty " << optionId << "." << endl;
    }
}

void setOption(string optionId, string token, Avoid::Router* router) {

    bool value = toBool(token);

    if (optionId == NUDGE_ORTHOGONAL_SEGMENTS) {
        router->setRoutingOption(Avoid::nudgeOrthogonalSegmentsConnectedToShapes, value);
    } else if (optionId == IMPROVE_HYPEREDGES) {
        router->setRoutingOption(Avoid::improveHyperedgeRoutesMovingJunctions, value);
    } else if (optionId == PENALISE_ORTH_SHATE_PATHS) {
        router->setRoutingOption(Avoid::penaliseOrthogonalSharedPathsAtConnEnds, value);
    } else if (optionId == NUDGE_ORTHOGONAL_COLINEAR_SEGMENTS) {
        router->setRoutingOption(Avoid::nudgeOrthogonalSegmentsConnectedToShapes, value);
    } else {
        cerr << "ERROR: unknown option " << optionId << "." << endl;
    }
}

void addNode(vector<string> &tokens, vector<Avoid::ShapeRef*> &shapes, Avoid::Router* router) {
    int id = toInt(tokens.at(1));
    double topLeftX = toDouble(tokens.at(2));
    double topLeftY = toDouble(tokens.at(3));
    double bottomRightX = toDouble(tokens.at(4));
    double bottomRightY = toDouble(tokens.at(5));

    // add the actual rectangle
    Avoid::Rectangle rectangle(Avoid::Point(topLeftX, topLeftY),
            Avoid::Point(bottomRightX, bottomRightY));

    Avoid::ShapeRef *shapeRef = new Avoid::ShapeRef(router, rectangle, id);

    // remember in vector
    shapes[id] = shapeRef;

    // create four connection pins for the node
    Avoid::ShapeConnectionPin *pinLeft = new Avoid::ShapeConnectionPin(shapeRef, PIN_ARBITRARY,
            Avoid::ATTACH_POS_LEFT, Avoid::ATTACH_POS_CENTRE, Avoid::ConnDirLeft);
    pinLeft->setExclusive(false);
    Avoid::ShapeConnectionPin *pinRight = new Avoid::ShapeConnectionPin(shapeRef, PIN_ARBITRARY,
            Avoid::ATTACH_POS_RIGHT, Avoid::ATTACH_POS_CENTRE, Avoid::ConnDirRight);
    pinRight->setExclusive(false);
    Avoid::ShapeConnectionPin *pinTop = new Avoid::ShapeConnectionPin(shapeRef, PIN_ARBITRARY,
            Avoid::ATTACH_POS_CENTRE, Avoid::ATTACH_POS_TOP, Avoid::ConnDirUp);
    pinTop->setExclusive(false);
    Avoid::ShapeConnectionPin *pinDown = new Avoid::ShapeConnectionPin(shapeRef, PIN_ARBITRARY,
            Avoid::ATTACH_POS_CENTRE, Avoid::ATTACH_POS_BOTTOM, Avoid::ConnDirDown);
    pinDown->setExclusive(false);
}

void addEdge(vector<string> &tokens, Avoid::ConnType connectorType, vector<Avoid::ShapeRef*> &shapes,
        vector<Avoid::ConnRef*> &cons, Avoid::Router* router) {
    int edgeId = toInt(tokens.at(1));
    int srcId = toInt(tokens.at(2));
    int tgtId = toInt(tokens.at(3));

    // get the shapes for the src and tgt node
    Avoid::ShapeRef *srcShape = shapes[srcId];
    Avoid::ShapeRef *tgtShape = shapes[tgtId];

    // create endpoints
    Avoid::ConnEnd srcPt(srcShape, PIN_ARBITRARY);
    Avoid::ConnEnd tgtPt(tgtShape, PIN_ARBITRARY);
    // create the connector
    Avoid::ConnRef *connRef = new Avoid::ConnRef(router, srcPt, tgtPt, edgeId);
    connRef->setRoutingType(connectorType);

    cons.push_back(connRef);
}

void writeLayout(ostream& out, vector<Avoid::ConnRef*> cons) {
    out << "LAYOUT" << endl;

    for (std::vector<int>::size_type i = 0; i != cons.size(); i++) {

        out << "EDGE " << cons[i]->id() << "=";

        const Avoid::PolyLine route = cons[i]->route();
        for (size_t i = 0; i < route.ps.size(); ++i) {
            out << route.ps[i].x << " " << route.ps[i].y << " ";
        }
        out << endl;
    }

    out << "DONE" << endl;
}
