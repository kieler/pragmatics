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
 * The entry point for the OGDF layout server used in the OGDF-KIML bridge.
 */

#include <cstdio>
#include <cstring>
#include <list>
#include <map>
#include <string>
#include <ctime>

#include <ogdf/basic/Graph_d.h>
#include <ogdf/cluster/ClusterGraph.h>
#include <ogdf/cluster/ClusterGraphAttributes.h>
#include <ogdf/fileformats/GmlParser.h>
#include <ogdf/fileformats/OgmlParser.h>

#include <ChunkStream.h>
#include <OgdfLayout.h>

using namespace std;
using namespace ogdf;

/*
 * Definitions
 */

#ifndef SAFE_DELETE
#define SAFE_DELETE(a) if(a) { delete a; a = 0; }
#endif

#define CHUNK_KEYWORD "[CHUNK]\n"

enum GraphInputFormat {
	OGML, GML
};

#define ERROR_GRAPH_READ_FAILED  "Could not parse the received graph."

/*
 * Function prototypes
 */

/**
 * Handles a layout request, which consists of reading the graph and layout
 * options from the input stream, performing the actual layout using the OGDF
 * library and writing the results back to an output stream.
 *
 * @param in
 *            the input stream
 * @param out
 *            the output stream
 * @param graphInputFormat
 *            the graph input format
 */
void HandleRequest(chunk_istream& stream, ostream& out,
		GraphInputFormat graphInputFormat);

/**
 * Reads a graph in the specified graph input format from the stream.
 *
 * @param in
 *            the stream
 * @param G
 *            the graph
 * @param CG
 *            the cluster graph
 * @param GA
 *            the graph attributes
 * @param graphInputFormat
 *            the graph input format
 * @return true if the graph has been successfully read; false else
 */
bool ReadGraph(istream& in, Graph& G, ClusterGraph& CG,
		ClusterGraphAttributes& GA, GraphInputFormat graphInputFormat);

/**
 * Reads key-value-pairs separated by newline from the stream and returns them
 * in a map.
 *
 * @param in
 *            the stream
 * @param options
 *            the map to store the key-value-pairs in
 */
void ReadKeyValuePairs(istream& in, map<string, string>& options);

/**
 * Parses a key-value-pair from the buffer and adds it to the map of pairs.
 * Returns whether the buffer could successfully be parsed.
 *
 * @param buf
 *            the buffer
 * @param options
 *            the map of key-value-pairs
 */
bool ReadKeyValuePair(string& buf, map<string, string>& pairs);

/**
 * Writes the layout information for the given graph including the layout
 * information for all nodes and edges as specified in the graph attributes
 * to the output stream.
 *
 * @param os
 *            the output stream
 * @param GA
 *            the graph attributes
 * @param G
 *            the graph
 * @param LI
 *            the label interface
 */
void WriteGraphLayout(ostream& os, GraphAttributes& GA, Graph& G,
		LabelInterface& LI);

/**
 * Writes the layout information for a given node as specified in the graph
 * attributes to the output stream.
 *
 * @param os
 *            the output stream
 * @param GA
 *            the graph attributes
 * @param v
 *            the node
 */
void WriteNodeLayout(ostream& os, GraphAttributes& GA, node v);

/**
 * Writes the layout information for a given edge as specified in the graph
 * attributes to the output stream.
 *
 * @param os
 *            the output stream
 * @param GA
 *            the graph attributes
 * @param e
 *            the edge
 */
void WriteEdgeLayout(ostream& os, GraphAttributes& GA, edge e);

/**
 * Writes the layout information for the labels of a given edge as specified in
 * the graph attributes and the label interface to the output stream.
 *
 * @param os
 *            the output stream
 * @param GA
 *            the graph attributes
 * @param LI
 *            the label interface
 * @param e
 *            the edge
 */
void WriteLabelLayout(ostream& os, GraphAttributes& GA, LabelInterface& LI,
		edge e);

/**
 * Writes an error message to the output stream.
 *
 * @param os
 *            the output stream
 * @param message
 *            the error message
 */
void WriteError(ostream& os, const string& message);

/*
 * Function implementations
 */

/**
 * The program entry point.
 *
 * @param argc
 *            the number of program arguments
 * @param argv
 *            the program arguments
 * @return the exit code
 */
int main(int argc, char* argv[]) {
	// get graph input type
	GraphInputFormat graphInputFormat = OGML;
	if (argc > 1) {
		if (strcmp(argv[1], "GML") == 0) {
			graphInputFormat = GML;
		}
	}
	// handle requests from stdin
	chunk_istream chunkStream(cin, CHUNK_KEYWORD);
	while (!chunkStream.isRealEof()) {
		HandleRequest(chunkStream, cout, graphInputFormat);
		chunkStream.nextChunk();
	}
}

void HandleRequest(chunk_istream& in, ostream& out,
		GraphInputFormat graphInputFormat) {
	// read the graph
	Graph G;
	ClusterGraph CG(G);
	ClusterGraphAttributes* GA = new ClusterGraphAttributes(CG,
			GraphAttributes::nodeGraphics | GraphAttributes::edgeGraphics
					| GraphAttributes::nodeLabel | GraphAttributes::edgeLabel);
	LabelInterface* LI = 0;
	bool graphRead = ReadGraph(in, G, CG, *GA, graphInputFormat);
	// read the layout options
	in.nextChunk();
	map < string, string > options;
	ReadKeyValuePairs(in, options);
	// read additionalInformation
	in.nextChunk();
	map < string, string > information;
	ReadKeyValuePairs(in, information);
	if (graphRead) {
		// perform the layout
		GraphAttributes* LGA;
		if (LGA = ::Layout(G, CG, GA, LI, options, information)) {
			// on success write the graph layout
			WriteGraphLayout(out, *LGA, G, *LI);
			SAFE_DELETE(LGA);
			SAFE_DELETE(LI);
		} else {
			// on failure write the reason
			WriteError(out, GetLastLayoutError());
		}
	} else {
		WriteError(out, ERROR_GRAPH_READ_FAILED);
		SAFE_DELETE(GA);
	}
}

bool ReadGraph(istream& in, Graph& G, ClusterGraph& CG,
		ClusterGraphAttributes& GA, GraphInputFormat graphInputFormat) {
	switch (graphInputFormat) {
	case OGML: {
		OgmlParser parser;
		bool parseDone = parser.read(&in, G, CG, GA);
		return parseDone;
	}
	case GML: {
		GmlParser parser(in);
		parser.readAttributedCluster(G, CG, GA);
		return !parser.error();
	}
	default:
		return false;
	}
}

void ReadKeyValuePairs(istream& in, map<string, string>& options) {
	string buf;
	while (in.good()) {
		// read a line
		getline(in, buf);
		if (buf.size() == 0) {
			continue;
		}
		// parse a key-value-pair from the buffer
		ReadKeyValuePair(buf, options);
	}
}

bool ReadKeyValuePair(string& buf, map<string, string>& pairs) {
	size_t pos = buf.find_first_of('=');
	if (pos == string::npos) {
		return false;
	}
	string key = buf.substr(0, pos);
	if (key.size() == 0) {
		return false;
	}
	string value = buf.substr(pos + 1);
	pairs.insert(pair<string, string> (key, value));
	return true;
}

void WriteGraphLayout(ostream& os, GraphAttributes& GA, Graph& G,
		LabelInterface& LI) {
	DRect bb = GA.boundingBox();
	os << "LAYOUT" << endl;
	os << "graph=" << "(" << bb.p1().m_x << "," << bb.p1().m_y << ")," << "("
			<< bb.width() << "," << bb.height() << ")" << endl;
	// write node layout information
	node v;
	forall_nodes(v, G) {
		WriteNodeLayout(os, GA, v);
	}
	// write edge layout information
	edge e;
	forall_edges(e, G) {
		WriteEdgeLayout(os, GA, e);
		WriteLabelLayout(os, GA, LI, e);
	}
	os << "DONE" << endl;
}

void WriteNodeLayout(ostream& os, GraphAttributes& GA, node v) {
	os << GA.labelNode(v) << "=" << "(" << GA.x(v) << "," << GA.y(v) << "),"
			<< "(" << GA.width(v) << "," << GA.height(v) << ")" << endl;
}

void WriteEdgeLayout(ostream& os, GraphAttributes& GA, edge e) {
	DPolyline& bends = GA.bends(e);
	bends.unify();
	bends.normalize();
	os << GA.labelEdge(e) << "=";
	bool first = true;
	for (DPolyline::iterator it = bends.begin(); it != bends.end(); ++it) {
		if (first) {
			first = false;
		} else {
			os << ",";
		}
		os << "(" << (*it).m_x << "," << (*it).m_y << ")";
	}
	os << endl;
}

void WriteLabelLayout(ostream& os, GraphAttributes& GA, LabelInterface& LI,
		edge e) {
	EdgeLabel<double>& label = LI.getLabel(e);
	for (int type = 0; type < labelNum; ++type) {
		eLabelTyp labelType = (eLabelTyp) type;
		if (label.usedLabel(labelType)) {
			os << GA.labelEdge(e) << EDGE_LABEL_SUFFIX << type << "=("
					<< label.getX(labelType) << "," << label.getY(labelType) << ")"
					<< endl;
		}
	}
}

void WriteError(ostream& os, const string& message) {
	os << "ERROR" << endl << message << endl << "DONE" << endl;
}
