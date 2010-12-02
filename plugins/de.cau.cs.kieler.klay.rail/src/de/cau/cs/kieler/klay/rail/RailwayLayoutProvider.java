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
package de.cau.cs.kieler.klay.rail;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;

/**
 * 
 * Provider class for railway layout.
 * 
 * @author jjc
 * 
 */
public class RailwayLayoutProvider extends AbstractLayoutProvider {
    
    //Strategies for the 5 phases of layered layout, assuming this will use layered layout
    //This is still open, though
    
    /** phase 1: cycle breaking module. */
    private ICycleBreaker cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayerer layerer;
    /** phase 3: crossing minimization module. */
    private ICrossingMinimizer crossingMinimizer;
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter;

    @Override
    public void doLayout(KNode layoutNode, IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Railway layout", 1);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        
        System.out.println("Hi there!");


        progressMonitor.done();
        
    }

}
