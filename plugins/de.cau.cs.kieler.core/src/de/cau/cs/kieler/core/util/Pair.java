/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.core.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A simple pair implementation.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class Pair <F, S> {

	/** the first element */
	public F first;
	/** the second element */
	public S second;
	
	/**
	 * Constructs a pair given both elements.
	 * 
	 * @param first the first element
	 * @param second the second element
	 */
	public Pair (F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Comparator that uses the first element as base.
	 */
	public static class FirstComparator <F extends Comparable<F>, S>
	        implements Comparator<Pair<F,S>>, Serializable {
        private static final long serialVersionUID = 1;
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Pair<F, S> o1, Pair<F, S> o2) {
            return o1.first.compareTo(o2.first);
        }
	}

	/**
	 * Comparator that uses the second element as base.
	 */
	public static class SecondComparator <F, S extends Comparable<S>>
	        implements Comparator<Pair<F,S>>, Serializable {
        private static final long serialVersionUID = 1;
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Pair<F, S> o1, Pair<F, S> o2) {
            return o1.second.compareTo(o2.second);
        }
	}
	
}
