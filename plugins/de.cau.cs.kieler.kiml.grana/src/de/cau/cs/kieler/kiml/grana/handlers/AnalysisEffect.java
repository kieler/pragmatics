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
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.util.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.grana.visualization.VisualizationServices;

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
     * @param theparentNode
     *            the parent node
     * @param theanalyses
     *            the analyses to perform
     * @param theprogressBar
     *            whether a progress bar should be used
     */
    public AnalysisEffect(final KNode theparentNode,
            final List<AbstractInfoAnalysis> theanalyses,
            final boolean theprogressBar) {
        this.parentNode = theparentNode;
        this.analyses = theanalyses;
        this.progressBar = theprogressBar;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        // perform the analyses on the active diagram
        final Map<String, Object> results =
                DiagramAnalyzer.analyze(parentNode, analyses, progressBar);
        // visualize the results using silent methods
        VisualizationServices.getInstance().visualize(analyses, results, true);
    }

}
