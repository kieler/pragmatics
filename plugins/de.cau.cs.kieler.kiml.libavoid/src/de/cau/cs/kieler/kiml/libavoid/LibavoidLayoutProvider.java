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
package de.cau.cs.kieler.kiml.libavoid;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;

/**
 * @author uru
 *
 */
public class LibavoidLayoutProvider extends AbstractLayoutProvider {

    private LibavoidServerCommunicator comm = new LibavoidServerCommunicator();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor) {
        
        comm.layout(parentNode);

    }

}
