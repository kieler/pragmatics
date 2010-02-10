/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.kiml.ogdf;

import net.ogdf.lib.PlanarizationLayout;
import net.ogdf.lib.UMLLayoutModule;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfUMLLayouter {

    /**
     * {@inheritDoc}
     */
    protected UMLLayoutModule prepareLayouter(final KNode node,
            final IKielerProgressMonitor progressMonitor) {
        // create the layouter
        PlanarizationLayout layout = new PlanarizationLayout();
        
        // set options
        //TODO set the options and modules
        
        return layout;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        return null;
    }
}
