/**
 * @file    main.cpp
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
 * Main entry point for the libavoid-server application.
 */
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <vector>

#include "ChunkStream.h"
#include "libavoid/libavoid.h"
#include "LibavoidRouting.h"

using namespace std;

/* The keyword used to separate parts of the data transmission. */
#define CHUNK_KEYWORD "[CHUNK]\n"

/**
 * Handles a layout request, which consists of reading the graph and layout
 * options from the input stream, performing the actual connector routing
 * using the Libavoid library and writing the results back to an output stream.
 *
 * @param in
 *            the input stream
 * @param out
 *            the output stream
 */
void HandleRequest(chunk_istream& stream, ostream& out);

/**
 * The program entry point.
 */
int main(void) {

    // handle requests from stdin, writes to stdout
    chunk_istream chunkStream(cin, CHUNK_KEYWORD);
    while (!chunkStream.isRealEof()) {
        HandleRequest(chunkStream, cout);
        chunkStream.nextChunk();
    }

    cout << "Terminating ..." << endl;

    return 0;
}

void HandleRequest(chunk_istream& stream, ostream& out) {

    vector<Avoid::ShapeRef *> shapes;
    vector<Avoid::ConnRef *> cons;

    Avoid::Router *router = new Avoid::Router(Avoid::OrthogonalRouting | Avoid::PolyLineRouting);

    // options
    Avoid::ConnType connectorType = Avoid::ConnType_PolyLine;

    // read graph from stdin
    for (std::string line; std::getline(std::cin, line);) {

        // split the line into its parts
        vector<string> tokens;
        tokenize(line, tokens);

        if (tokens[0] == "PENALTY") {

            /* Penalties */
            setPenalty(tokens[1], tokens[2], router);

        } else if (tokens[0] == "ROUTINGOPTION") {

            /* Routing options */
            setOption(tokens[1], tokens[2], router);

        } else if (tokens[0] == "OPTION") {

            /* General options */
            if (tokens[1] == EDGE_ROUTING) {
                // edge routing
                if (tokens[2] == EDGE_ROUTING_ORTHOGONAL) {
                    connectorType = Avoid::ConnType_Orthogonal;
                } else {
                    // default polyline
                    connectorType = Avoid::ConnType_PolyLine;
                }
            }

        } else if (tokens.at(0) == "NODE") {
            // format: id topleft bottomright
            if (tokens.size() != 6) {
                cerr << "ERROR: invalid node format" << endl;
            }

            addNode(tokens, shapes, router);

        } else if (tokens.at(0) == "EDGE") {
            // format: edgeId srcId tgtId
            if (tokens.size() != 4) {
                cerr << "ERROR: invalid edge format" << endl;
            }

            addEdge(tokens, connectorType, shapes, cons, router);

        } else if (tokens.at(0) == "GRAPHEND") {
            break;
        }

        //std::cout << line << std::endl;
    }

    // perform edge routing
    router->processTransaction();

    // write the layout to std out
    writeLayout(cout, cons);

    // cleanup
    delete router;

}

