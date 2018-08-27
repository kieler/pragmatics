/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import io.typefox.sprotty.server.xtext.tracing.TraceRegionProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.util.TextRegion

/**
 * Class to prevent Sprotty from searching the significant region (the name of it) since it can not be found in Objects
 * like KGraph and will cause errors instead.
 * 
 * @author nir
 */
public class SimpleTraceRegionProvider extends TraceRegionProvider {
    /**
     * returns the text region of the entire definition of this EObject
     */
    override TextRegion getSignificantRegion(EObject element) {
        return NodeModelUtils.findActualNodeFor(element).toTextRegion
    }
}