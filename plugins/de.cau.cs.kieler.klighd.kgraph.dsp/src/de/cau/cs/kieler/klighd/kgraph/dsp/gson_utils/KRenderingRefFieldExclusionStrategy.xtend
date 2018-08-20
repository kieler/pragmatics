/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp.gson_utils

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import de.cau.cs.kieler.klighd.krendering.KRenderingRef

/**
 * @author stu114054
 * A Gson Exclusion Strategy to ignore the rendering Field during serialization, since it is already serialized in the
 * {@code KRenderingLibrary}.
 */
class KRenderingRefFieldExclusionStrategy implements ExclusionStrategy {
        
    override shouldSkipField(FieldAttributes f) {
        return KRenderingRef.isAssignableFrom(f.declaringClass) && (f.getName().equals("rendering"))
    }
    override shouldSkipClass(Class<?> clazz) {
        return false
    }

}