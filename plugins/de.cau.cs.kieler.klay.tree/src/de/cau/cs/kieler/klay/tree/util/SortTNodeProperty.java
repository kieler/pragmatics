/*
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
 */
package de.cau.cs.kieler.klay.tree.util;

import java.util.Comparator;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public class SortTNodeProperty implements Comparator<TNode> {
    IProperty<Integer> property;

    public SortTNodeProperty(IProperty<Integer> property) {
        this.property = property;
    }

    public int compare(TNode t1, TNode t2) {
        return t2.getProperty(property) - t1.getProperty(property);
    }
}
