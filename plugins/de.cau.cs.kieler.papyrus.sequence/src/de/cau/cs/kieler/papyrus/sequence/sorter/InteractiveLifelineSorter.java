/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence.sorter;

import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.papyrus.sequence.ILifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;

/**
 * Lifeline sorting algorithm that respects the given order of the lifelines. The lifelines are
 * numbered as they are ordered before.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class InteractiveLifelineSorter implements ILifelineSorter {

    /**
     * Sort lifelines as they were sorted before layout.
     * {@inheritDoc}
     */
    public List<SLifeline> sortLifelines(final SGraph graph, final LGraph lgraph, 
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Interactive lifeline sorting", 1);
        
        List<SLifeline> lifelines = (List<SLifeline>) graph.getLifelines();
        java.util.Collections.sort(lifelines);
        for (int i = 0; i < lifelines.size(); i++) {
            lifelines.get(i).setHorizontalSlot(i);
        }
        
        progressMonitor.done();
        
        return lifelines;
    }

}
