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
package de.cau.cs.kieler.kiml.evol.grana;

import java.util.Map;

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * Calculates a metric for the number of bends in the graph. Does not care for
 * hierarchies.
 *
 * @author mri
 * @author bdu
 */
public class BendsMetric implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {
        progressMonitor.begin("Bend metric analysis", 1);

        // load numbers from analyses
        final Object edgesResult = results.get("de.cau.cs.kieler.kiml.grana.edgeCount");
        final Object bendsResult = results.get("de.cau.cs.kieler.kiml.grana.bendpointCount");
        final int edgesCount = (Integer) edgesResult;
        final int bendsCount = (Integer) bendsResult;

        // count the number of edges and bend points
        // TODO: this is superfluous since the needed values are loaded.
        int m = 0;
        int bends = 0;
        for (final KNode node : parentNode.getChildren()) {
            for (final KEdge edge : node.getOutgoingEdges()) {
                m++;
                final KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
                bends += edgeLayout.getBendPoints().size();
            }
        }

        Assert.isTrue(edgesCount == m);
        Assert.isTrue(bendsCount == bends);
        System.out.println("edges: " + edgesCount + " bends: " + bendsCount);

        progressMonitor.done();

        // normalize
        Float result;
        if (edgesCount + bendsCount > 0) {
            result = 1.0f - (float) bendsCount / (float) (edgesCount + bendsCount);
        } else {
            result = 1.0f;
        }

        return result;
    }


}
