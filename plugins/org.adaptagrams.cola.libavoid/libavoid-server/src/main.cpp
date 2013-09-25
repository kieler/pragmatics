#include <iostream>
#include <string>
#include "libavoid/libavoid.h"
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <vector>

using namespace std;

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

	os << "EDGE " << conn->id() << " ";

	const Avoid::PolyLine route = conn->route();
	for (size_t i = 0; i < route.ps.size(); ++i) {
		os << route.ps[i].x << " " << route.ps[i].y << " ";
	}
	os << endl;
}

int main(void) {

	const unsigned int CENTRE = 1;

	vector<Avoid::ShapeRef *> shapes(20);
	vector<Avoid::ConnRef *> cons;

	Avoid::Router *router = new Avoid::Router(Avoid::PolyLineRouting);

	// read graph from stdin
	for (std::string line; std::getline(std::cin, line);) {

		// split the line into its parts
		istringstream iss(line);
		vector<string> tokens;
		copy(istream_iterator<string>(iss), istream_iterator<string>(),
				back_inserter<vector<string> >(tokens));

		if (tokens.at(0) == "NODE") {
			// format: id topleft bottomright
			if (tokens.size() != 6) {
				cout << "ERROR" << endl;
			}

			int id = toInt(tokens.at(1));
			double topLeftX = toDouble(tokens.at(2));
			double topLeftY = toDouble(tokens.at(3));
			double bottomRightX = toDouble(tokens.at(4));
			double bottomRightY = toDouble(tokens.at(5));

			Avoid::Rectangle rectangle(Avoid::Point(topLeftX, topLeftY),
					Avoid::Point(bottomRightX, bottomRightY));
			Avoid::ShapeRef *shapeRef = new Avoid::ShapeRef(router, rectangle,
					id);

			// remember in vector
			shapes[id] = shapeRef;

			// create a connection pin for the node
			new Avoid::ShapeConnectionPin(shapeRef, CENTRE,
					Avoid::ATTACH_POS_CENTRE, Avoid::ATTACH_POS_CENTRE);

		} else if (tokens.at(0) == "EDGE") {
			// format: edgeId srcId tgtId

			int edgeId = toInt(tokens.at(1));
			int srcId = toInt(tokens.at(2));
			int tgtId = toInt(tokens.at(3));

			// get the shapes for the src and tgt node
			Avoid::ShapeRef *srcShape = shapes[srcId];
			Avoid::ShapeRef *tgtShape = shapes[tgtId];

			// create endpoints
			Avoid::ConnEnd srcPt(srcShape, CENTRE);
			Avoid::ConnEnd tgtPt(tgtShape, CENTRE);
			// create the connector
			Avoid::ConnRef *connRef = new Avoid::ConnRef(router, srcPt, tgtPt, edgeId);

			cons.push_back(connRef);

		} else if (tokens.at(0) == "GRAPHEND") {
			break;
		}

		std::cout << line << std::endl;
	}

	// perform edge routing
	router->processTransaction();

	// write out graph
	ostream& out = cout;

	out << "LAYOUTEDGRAPH" << endl;

	for (std::vector<int>::size_type i = 0; i != cons.size(); i++) {
		writeEdgeLayout(cout, cons[i]);
	}

	out << "LAYOUTEDGRAPHEND" << endl;


	// cleanup
	delete router;

	return 0;
}

