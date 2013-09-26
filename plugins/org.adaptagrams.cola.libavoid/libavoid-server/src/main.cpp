#include <iostream>
#include <string>

#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <vector>

#include <ChunkStream.h>
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

void writeEdgeLayout(ostream& os, Avoid::ConnRef* conn) {

	os << "EDGE " << conn->id() << "=";

	const Avoid::PolyLine route = conn->route();
	for (size_t i = 0; i < route.ps.size(); ++i) {
		os << route.ps[i].x << " " << route.ps[i].y << " ";
	}
	os << endl;
}

/*
 * Function implementations
 */
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

void tokenize(string text, vector<string>& tokens) {
	istringstream iss(text);
	copy(istream_iterator<string>(iss), istream_iterator<string>(),
			back_inserter<vector<string> >(tokens));
}

void HandleRequest(chunk_istream& stream, ostream& out) {

	const unsigned int CENTRE = 1;
	const unsigned int ARBITRARY = 2;

	vector<Avoid::ShapeRef *> shapes(20);
	vector<Avoid::ConnRef *> cons;

	Avoid::Router *router = new Avoid::Router(
			Avoid::OrthogonalRouting | Avoid::PolyLineRouting);

	//router->setRoutingOption(Avoid::nudgeOrthogonalSegmentsConnectedToShapes,
		//	true);

	// options
	float spacing = 0;
	Avoid::ConnType connectorType = Avoid::ConnType_PolyLine;

	// read graph from stdin
	for (std::string line; std::getline(std::cin, line);) {

		// split the line into its parts
		vector<string> tokens;
		tokenize(line, tokens);

		if (tokens[0] == "OPTION") {

			if (tokens[1] == OPTION_SPACING) {
				// spacing
				spacing = toDouble(tokens.at(2));
			} else if (tokens[1] == OPTION_EDGE_ROUTING) {
				// edge routing
				if (tokens[2] == OPTION_VALUE_EDGE_ROUTING_ORTHOGONAL) {
					connectorType = Avoid::ConnType_Orthogonal;
				} else {
					// default polyline
					connectorType = Avoid::ConnType_PolyLine;
				}
			}

		} else if (tokens.at(0) == "NODE") {
			// format: id topleft bottomright
			if (tokens.size() != 6) {
				cout << "ERROR" << endl;
			}

			int id = toInt(tokens.at(1));
			double topLeftX = toDouble(tokens.at(2));
			double topLeftY = toDouble(tokens.at(3));
			double bottomRightX = toDouble(tokens.at(4));
			double bottomRightY = toDouble(tokens.at(5));

			Avoid::Rectangle rectangle(
					Avoid::Point(topLeftX - spacing, topLeftY - spacing),
					Avoid::Point(bottomRightX + spacing,
							bottomRightY + spacing));

			Avoid::ShapeRef *shapeRef = new Avoid::ShapeRef(router, rectangle,
					id);

			// remember in vector
			shapes[id] = shapeRef;

			// create four connection pins for the node
			Avoid::ShapeConnectionPin *pinLeft  = new Avoid::ShapeConnectionPin(shapeRef, ARBITRARY,
					Avoid::ATTACH_POS_LEFT, Avoid::ATTACH_POS_CENTRE, Avoid::ConnDirLeft);
			pinLeft->setExclusive(false);
			Avoid::ShapeConnectionPin *pinRight = new Avoid::ShapeConnectionPin(shapeRef, ARBITRARY,
					Avoid::ATTACH_POS_RIGHT, Avoid::ATTACH_POS_CENTRE, Avoid::ConnDirRight);
			pinRight->setExclusive(false);
			Avoid::ShapeConnectionPin *pinTop = new Avoid::ShapeConnectionPin(shapeRef, ARBITRARY,
					Avoid::ATTACH_POS_CENTRE, Avoid::ATTACH_POS_TOP, Avoid::ConnDirUp);
			pinTop->setExclusive(false);
			Avoid::ShapeConnectionPin *pinDown = new Avoid::ShapeConnectionPin(shapeRef, ARBITRARY,
					Avoid::ATTACH_POS_CENTRE, Avoid::ATTACH_POS_BOTTOM, Avoid::ConnDirDown);
			pinDown->setExclusive(false);

		} else if (tokens.at(0) == "EDGE") {
			// format: edgeId srcId tgtId

			int edgeId = toInt(tokens.at(1));
			int srcId = toInt(tokens.at(2));
			int tgtId = toInt(tokens.at(3));

			// get the shapes for the src and tgt node
			Avoid::ShapeRef *srcShape = shapes[srcId];
			Avoid::ShapeRef *tgtShape = shapes[tgtId];

			// create endpoints
			Avoid::ConnEnd srcPt(srcShape, ARBITRARY);
			Avoid::ConnEnd tgtPt(tgtShape, ARBITRARY);
			// create the connector
			Avoid::ConnRef *connRef = new Avoid::ConnRef(router, srcPt, tgtPt,
					edgeId);
			connRef->setRoutingType(connectorType);

			cons.push_back(connRef);

		} else if (tokens.at(0) == "GRAPHEND") {
			break;
		}

		std::cout << line << std::endl;
	}

	// perform edge routing
	router->processTransaction();

	// write out graph
	//ostream & out = cout;

	out << "LAYOUT" << endl;

	for (std::vector<int>::size_type i = 0; i != cons.size(); i++) {
		writeEdgeLayout(cout, cons[i]);
	}

	out << "DONE" << endl;

	// cleanup
	delete router;

}

