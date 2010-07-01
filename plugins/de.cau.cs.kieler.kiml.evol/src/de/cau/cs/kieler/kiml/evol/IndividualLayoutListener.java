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
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;

/**
 * A layout listener that measures the layout graph it is notified about, and
 * assigns the rating to the specified individual.
 *
 * @author bdu
 * @deprecated
 */
@Deprecated
public class IndividualLayoutListener extends LayoutListener {
    /**
     *
     * @return the {@code Individual} associated with this listener
     */
    public Individual getIndividual() {
        return this.individual;
    }

    /**
     *
     * @param ind
     *            an individual. Must not be {@code null}.
     */
    public void setIndividual(final Individual ind) {
        Assert.isLegal(ind != null);
        this.individual = ind;
    }

    private Individual individual;
    /**
     *
     * @param theInd
     *            the {@code Individual} that shall be rated by this listener.
     */
    public IndividualLayoutListener(final Individual theInd) {
        this.individual = theInd;
    }

    @Override
    public void layoutPerformed(final KNode theLayoutGraph, final IKielerProgressMonitor monitor) {
        System.out.println("LayoutListener: Layout performed.");
        MonitoredOperation.runInUI(new DiagramMeasurer(theLayoutGraph, this.individual), true);
        // tableViewer.refresh();
    }

    @Override
    public void layoutRequested(final KNode theLayoutGraph) {
        super.layoutRequested(theLayoutGraph);
        System.out.println(theLayoutGraph.getData());
    }

    /**
     *
     * @author bdu
     *
     */
    private class DiagramMeasurer implements Runnable {
        private final KNode sourceGraph;
        private final Individual targetIndividual;

        public DiagramMeasurer(final KNode theSourceGraph, final Individual theTargetIndividual) {
            this.sourceGraph = theSourceGraph;
            this.targetIndividual = theTargetIndividual;
        }

        public void run() {
            final int rating = EvolUtil.measureDiagram(false, this.sourceGraph);
            System.out.println("Assign rating " + rating + " to individual" + ": "
                    + IndividualLayoutListener.this.individual.toString());
            this.targetIndividual.setRating(rating);
        }
    }
}
