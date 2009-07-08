/**
 * Slim graph representation for undirected graphs, used in many
 * graph algorithms. Although edges do have a direction, the incoming
 * and outgoing edges of a node are stored in the same incidence list.
 * This makes this graph structure very suitable for algorithms for
 * undirected graphs such as planarity related algorithms.
 * <p>
 * The dual graph, which consists of faces and edges separating those
 * faces, can be generated from a slim graph instance.
 */
package de.cau.cs.kieler.core.slimgraph;
