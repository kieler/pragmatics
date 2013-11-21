/*
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
 */
package de.cau.cs.kieler.kiml.formats.json

import com.google.common.collect.Iterators
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import java.util.Iterator

/**
 * Some convenience.
 * 
 * @author uru
 */
class JsonExtensions {
    
    def layout(KNode node) {
        return node.getData(typeof(KShapeLayout))
    }

    def layout(KPort port) {
        return port.getData(typeof(KShapeLayout))
    }
    
    def layout(KLabel label) {
        return label.getData(typeof(KShapeLayout))
    }

    def layout(KEdge edge) {
        return edge.getData(typeof(KEdgeLayout))
    }

    /**
     * Maps infinite or NaN values to 0f.
     */
    def float floatValueValid(Double d) {
        if (d == null || d.infinite || d.naN) {
            return 0f
        } else {
            return d.floatValue
        }
    }

    def <T> Iterator<T> emptyIfNull(Iterator<T> iterator) {
        if (iterator == null) {
            return Iterators.emptyIterator
        } else {
            iterator
        }
    }
}
