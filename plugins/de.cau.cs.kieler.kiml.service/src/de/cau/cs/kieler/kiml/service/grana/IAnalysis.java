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
package de.cau.cs.kieler.kiml.service.grana;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The interface all graph analysis algorithms have to implement.
 * 
 * @author mri
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public interface IAnalysis {

    /**
     * Performs the actual analysis process and returns the results. If more
     * than one component have been specified for the analysis in the extension
     * the method is expected to return an array.
     * 
     * @param parentNode
     *            the parent node which the analysis is performed on
     * @param results
     *            the result of analyses that were performed before this one (it
     *            should include the results of all dependency analyses)
     * @param progressMonitor
     *            progress monitor used to keep track of progress
     * @return the analysis results
     */
    Object doAnalysis(KNode parentNode, Map<String, Object> results,
            IKielerProgressMonitor progressMonitor);

}
