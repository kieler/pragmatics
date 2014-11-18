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
package de.cau.cs.kieler.kiml.grana.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;

/**
 * A view management effect for graph analysis.
 * 
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class AnalysisEffect extends Job {

    /** the parent node. */
    private KNode parentNode;
    /** the analyses to perform. */
    private List<AnalysisData> analyses;

    /**
     * Creates an analysis effect.
     * 
     * @param theparentNode
     *            the parent node
     * @param theanalyses
     *            the analyses to perform
     */
    public AnalysisEffect(final KNode theparentNode,
            final List<AnalysisData> theanalyses) {
        super("Grana Analysis Job");
        this.parentNode = theparentNode;
        this.analyses = theanalyses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        // perform the analyses on the active diagram
        final Map<String, Object> results = AnalysisService.getInstance().analyze(parentNode, analyses,
                new BasicProgressMonitor(0));
        // visualize the results using silent methods
        VisualizationService.getInstance().visualize(analyses, results, true);
        
        return Status.OK_STATUS;
    }

}
