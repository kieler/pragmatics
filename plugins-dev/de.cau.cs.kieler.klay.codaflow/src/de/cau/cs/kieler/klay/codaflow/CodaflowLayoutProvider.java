/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.adaptagrams.properties.CoLaProperties;
import de.cau.cs.kieler.adaptagrams.provider.SWIGLibavoidLayoutProvider;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.codaflow.properties.CodaflowProperties;

/**
 * @author uru
 */
public class CodaflowLayoutProvider extends AbstractLayoutProvider {

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

        parentLayout.setProperty(CoLaProperties.CONSIDER_PREVIOUS_POSITIONS, true);
        cola.doLayout(parentNode, progressMonitor.subTask(1));

        
        /*
         * ACA
         */
        ACALayoutProvider aca = new ACALayoutProvider();

        parentLayout.setProperty(CoLaProperties.CONSIDER_PREVIOUS_POSITIONS, true);

        aca.doLayout(parentNode, progressMonitor.subTask(1));

        /*
         * Libavoid
         */
        
        parentLayout.setProperty(CodaflowProperties.REPOSITION_HIERARCHICAL_PORTS, false);
        if (parentLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {

            de.cau.cs.kieler.klay.codaflow.GlobalLibavoidLayoutProvider globalLibavoid =
                    new de.cau.cs.kieler.klay.codaflow.GlobalLibavoidLayoutProvider();

            globalLibavoid.doLayout(parentNode, progressMonitor.subTask(1));

        } else {
            SWIGLibavoidLayoutProvider libavoid = new SWIGLibavoidLayoutProvider();

            libavoid.doLayout(parentNode, progressMonitor.subTask(1));
        }

        
        
        // Print execution times
        for (IKielerProgressMonitor pm : progressMonitor.getSubMonitors()) {
            System.out.println(pm.getTaskName() + " " + pm.getExecutionTime() + "s");
        }

        progressMonitor.done();
    }

}
