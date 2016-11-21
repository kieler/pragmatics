/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.json

import com.google.common.collect.Iterators
import java.util.Iterator

/**
 * Some convenience.
 * 
 * @author uru
 */
class JsonExtensions {
    def <T> Iterator<T> emptyIfNull(Iterator<T> iterator) {
        if (iterator == null) {
            return Iterators.emptyIterator
        } else {
            iterator
        }
    }
    
    def <T> T firstNonNullElement(T element1, T element2) {
        if (element1 != null) {
            return element1;
        } else {
            return element2;
        }
    }
}
