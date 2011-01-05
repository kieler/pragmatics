/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;


/**
 * An analysis that computes the size of the area a graph drawing occupies. Returns
 * a two-component result {@code (width, height)}.
 * 
 * @author cds
 */
public class AreaAnalysis implements IAnalysis {
    
    /**
     * ID of this analysis.
     */
    public static final String ANALYSIS_ID = "de.cau.cs.kieler.kiml.grana.area";
    

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {
        
        progressMonitor.begin("Area analysis", 1);
        
        // TODO Do the actual analysis
        
        progressMonitor.done();
        
        return new Object[] {100, 10};
    }

}
