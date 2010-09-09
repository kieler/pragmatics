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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.ILayoutListener;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyser;
import de.cau.cs.kieler.kiml.grana.views.AnalysisResultViewPart;

/**
 * A listener on the automatic graph layout that invokes the currently
 * configured analyses and refreshes the analysis result view.
 * 
 * @author mri
 */
public class AutomaticAnalysisLayoutListener extends AbstractAnalysisHandler
        implements ILayoutListener {

    /**
     * {@inheritDoc}
     */
    public void layoutRequested(final KNode layoutGraph) {
        // nothing
    }

    /**
     * {@inheritDoc}
     */
    public void layoutPerformed(final KNode layoutGraph,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Performing graph analysis", 1);
        // let the user select the analyses
        final List<AbstractInfoAnalysis> analyses = getLastAnalysesSelection();
        // perform the analyses on the active diagram
        final Map<String, Object> results =
                DiagramAnalyser.analyse(layoutGraph, analyses, false);
        // refresh the result view
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                AnalysisResultViewPart view = AnalysisResultViewPart.findView();
                if (view != null) {
                    view.setAnalysisResults(analyses, results);
                }
            }
        }, false);
        monitor.done();
    }

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        // ignore, this is not a real handler
        return null;
    }
}
