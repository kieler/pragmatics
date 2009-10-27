/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.structures;

import java.util.LinkedList;
import java.util.List;

/**
 * A linear segment of layer elements that represents a long edge in
 * a layered graph.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LinearSegment implements Comparable<LinearSegment> {
	
	/** list of layer elements contained in this linear segment */
	public List<LayerElement> elements = new LinkedList<LayerElement>();
	/** current rank of the linear segment */
	public int rank = -1;
	
	/**
	 * Determines whether the given element has preceding elements in this
	 * linear segment.
	 * 
	 * @param elem element to test
	 * @return true if there are other elements before <code>elem</code>
	 */
	public boolean hasPreceding(LayerElement elem) {
	    return elements.indexOf(elem) > 0;
	}
	
	/**
	 * Determines whether the given element has following elements in this
     * linear segment.
     * 
     * @param elem element to test
     * @return true if there are other elements after <code>elem</code>
     */
	public boolean hasFollowing(LayerElement elem) {
	    return elements.indexOf(elem) < elements.size() - 1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
	    return "seg:" + rank;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(LinearSegment other) {
		return this.rank == other.rank ? 0
				: (this.rank > other.rank ? 1
				: -1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return obj instanceof LinearSegment
				&& ((LinearSegment)obj).rank == this.rank;
	}
	
}
