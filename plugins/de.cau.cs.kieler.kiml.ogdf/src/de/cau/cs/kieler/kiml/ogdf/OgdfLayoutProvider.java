/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.ogdf.options.LayoutAlgorithm;
import net.ogdf.bin.OgdfServer;
import net.ogdf.bin.OgdfServerPool;

/**
 * The OGDF layout provider, that is the entry class used by KIML to call individual layout
 * algorithms.
 * 
 * @author msp
 * @author mri
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class OgdfLayoutProvider extends AbstractLayoutProvider {

    /** the server communicator. */
    private OgmlServerCommunicator comm = new OgmlServerCommunicator();
    /** the selected layout algorithm. */
    private LayoutAlgorithm layoutAlgorithm;

    /**
     * {@inheritDoc}
     * 
     * @throws KielerException
     */
    @Override
    public void initialize(final String parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("Could not initialize OGDF layouter.");
        }
        layoutAlgorithm = LayoutAlgorithm.valueOf(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout(final ElkNode layoutNode, final IElkProgressMonitor progressMonitor) {
        if (layoutAlgorithm == null) {
            throw new IllegalStateException("The OGDF layout algorithm is not configured correctly."
                    + " Please check the parameter in the extension point");
        }
        
        // set up layout on the server communicator
        AlgorithmSetup.setup(layoutAlgorithm, comm, layoutNode);
        // create an OGDF server process instance or use an existing one
        OgdfServer ogdfServer = OgdfServerPool.INSTANCE.fetch();
        // send a layout request to the server process and apply the layout
        comm.requestLayout(layoutNode, progressMonitor, ogdfServer);
        // if everything worked well, release the used process instance
        OgdfServerPool.INSTANCE.release(ogdfServer);

    }
    
}
