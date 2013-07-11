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
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import ptolemy.kernel.Entity

/**
 * 
 * 
 * @author ckru
 * @author cds
 */
class PtolemySvgFigureProvider {
    
    /** Instantiating Ptolemy entities. */
    @Inject extension PtolemyInterface
    
    
    def KRendering createPtolemySvgRendering(String className) {
        // Try to instantiate the Ptolemy entity
        var Entity entity = null;
        try {
            entity = instantiatePtolemyEntity(className)
        } catch (Exception e) {
            return null;
        }
        
        
        
        return null;
    }
}