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
package de.cau.cs.kieler.kiml.grana.handlers;

import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.grana.views.AnalysisResultViewPart;

/**
 * A view management effect for graph analysis.
 *
 * @author msp
 */
public class AnalysisEffect extends AbstractEffect {

    /** the parent node. */
    private KNode parentNode;
    /** the analyses to perform. */
    private List<AbstractInfoAnalysis> analyses;
    /** whether a progress bar should be used. */
    private boolean progressBar;
    
    /**
     * Creates an analysis effect.
     * 
     * @param theparentNode the parent node
     * @param theanalyses the analyses to perform
     * @param theprogressBar whether a progress bar should be used
     */
    public AnalysisEffect(final KNode theparentNode,
            final List<AbstractInfoAnalysis> theanalyses, final boolean theprogressBar) {
        this.parentNode = theparentNode;
        this.analyses = theanalyses;
        this.progressBar = theprogressBar;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        final Maybe<AnalysisResultViewPart> viewPart = new Maybe<AnalysisResultViewPart>();
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                viewPart.set(AnalysisResultViewPart.findView());
            }
        }, true);
        if (viewPart.get() != null) {
            // perform the analyses on the active diagram
            final Map<String, Object> results =
                    DiagramAnalyzer.analyse(parentNode, analyses, progressBar);
            // refresh the result view
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    viewPart.get().setAnalysisResults(analyses, results);
                }
            }, false);
        }
    }

}
