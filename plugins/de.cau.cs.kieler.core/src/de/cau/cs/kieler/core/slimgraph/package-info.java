/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

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
