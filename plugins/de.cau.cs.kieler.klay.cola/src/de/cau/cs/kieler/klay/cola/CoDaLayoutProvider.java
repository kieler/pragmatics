/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.libavoid.LibavoidLayoutProvider;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;

/**
 * @author uru
 */
public class CoDaLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("CoDaFlow Layout", 1);

        
        final KLayoutData parentLayout = parentNode.getData(KLayoutData.class);

        /*
         * Cola
         */
        ColaLayoutProvider cola = new ColaLayoutProvider();

        parentLayout.setProperty(ColaProperties.CONSIDER_PREVIOUS_POSITION, true);
        cola.doLayout(parentNode, progressMonitor.subTask(1));

        
        /*
         * ACA
         */
        ACALayoutProvider aca = new ACALayoutProvider();

        parentLayout.setProperty(ColaProperties.CONSIDER_PREVIOUS_POSITION, true);

        aca.doLayout(parentNode, progressMonitor.subTask(1));

        /*
         * Libavoid
         */
        
        parentLayout.setProperty(ColaProperties.REPOSITION_HIERARCHICAL_PORTS, false);
        if (parentLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {

            de.cau.cs.kieler.klay.cola.LibavoidLayoutProvider globalLibavoid =
                    new de.cau.cs.kieler.klay.cola.LibavoidLayoutProvider();

            globalLibavoid.doLayout(parentNode, progressMonitor.subTask(1));

        } else {
            LibavoidLayoutProvider libavoid = new LibavoidLayoutProvider();

            libavoid.doLayout(parentNode, progressMonitor.subTask(1));
        }

        
        
        // Print execution times
        for (IKielerProgressMonitor pm : progressMonitor.getSubMonitors()) {
            System.out.println(pm.getTaskName() + " " + pm.getExecutionTime() + "s");
        }

        progressMonitor.done();
    }

}
