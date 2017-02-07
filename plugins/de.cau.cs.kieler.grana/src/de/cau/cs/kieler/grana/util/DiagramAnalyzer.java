/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.service.util.MonitoredOperation;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Maybe;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisData;
import de.cau.cs.kieler.grana.AnalysisService;

/**
 * This class provides static methods to start an analysis of a given diagram.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public final class DiagramAnalyzer {

    /** the executor service for analysis operations. */
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor(); 

    /**
     * This class is an utility class and should not be instantiated.
     */
    private DiagramAnalyzer() {
    }

    /**
     * Starts the given analysis on a diagram.
     * 
     * @param workbenchPart
     *            the workbench part the analysis is performed on, or {@code null} if the diagram is
     *            not part of an editor
     * @param analysis
     *            the analysis to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyze(final IEditorPart workbenchPart,
            final AnalysisData analysis, final boolean progressBar) {
        return analyze(workbenchPart, Lists.newArrayList(analysis), progressBar);
    }
    
    private static final float CONFIGURE_WORK = 1;
    private static final float ANALYSIS_WORK = 4;
    private static final float TOTAL_WORK = CONFIGURE_WORK + ANALYSIS_WORK;

    /**
     * Starts the given analyses on the diagram.
     * 
     * @param workbenchPart
     *            the workbench part the analysis is performed on, or {@code null} if the diagram is
     *            not part of an editor
     * @param analyses
     *            the analyses to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final IWorkbenchPart workbenchPart,
            final List<AnalysisData> analyses, final boolean progressBar) {
        final IDiagramLayoutConnector manager =
                LayoutConnectorsService.getInstance().getConnector(workbenchPart, null);
        if (manager == null) {
            return Collections.emptyMap();
        }
        
        final Maybe<LayoutMapping> layoutMapping = new Maybe<LayoutMapping>();
        final Maybe<Map<String, Object>> result = new Maybe<Map<String, Object>>();
        final MonitoredOperation monitoredOperation = new MonitoredOperation(EXECUTOR_SERVICE) {
            // first phase: build the graph
            protected void preUIexec() {
                layoutMapping.set(manager.buildLayoutGraph(workbenchPart, null));
            }

            // second phase: analyze the graph
            protected IStatus execute(final IElkProgressMonitor monitor) {
                monitor.begin("Diagram analysis", TOTAL_WORK);
                // configure the layout graph to set proper layout options
                // MIGRATE do we still require this?
                // DiagramLayoutEngine.INSTANCE.getOptionManager().configure(layoutMapping.get(),
                //        monitor.subTask(CONFIGURE_WORK));

                // take a look at DiagramLayoutEngine#addDiagramConfig
                // LayoutConfigurator diagramConfig = configManager.createConfigurator(layoutMapping);
                
                // perform analyses on the graph
                ElkNode graph = layoutMapping.get().getLayoutGraph();
                result.set(AnalysisService.getInstance().analyze(graph, analyses,
                        monitor.subTask(ANALYSIS_WORK)).getResults());
                if (monitor.isCanceled()) {
                    return Status.CANCEL_STATUS;
                } else {
                    return Status.OK_STATUS;
                }
            }
        };

        if (progressBar) {
            // perform analysis with a progress bar
            monitoredOperation.runMonitored();
        } else {
            // perform analysis without a progress bar
            monitoredOperation.runUnmonitored();
        }
        
        if (result.get() != null) {
            return result.get();
        } else {
            return Collections.emptyMap();
        }
    }
    
}
