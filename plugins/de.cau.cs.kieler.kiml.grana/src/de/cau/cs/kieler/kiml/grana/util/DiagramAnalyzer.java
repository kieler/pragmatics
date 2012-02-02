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
package de.cau.cs.kieler.kiml.grana.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;
import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * This class provides static methods to start an analysis of a given diagram.
 * 
 * @author mri
 */
public final class DiagramAnalyzer {

    /** maximal number of recursion levels for which progress is displayed. */
    private static final int MAX_PROGRESS_LEVELS = 3;

    /**
     * This class is an utility class and should not be instantiated.
     */
    private DiagramAnalyzer() {
    }

    /**
     * Starts the given analysis on a diagram.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if the diagram is not
     *            part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if the whole diagram
     *            shall be analyzed
     * @param analysis
     *            the analysis to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyze(final IEditorPart editorPart, final EditPart editPart,
            final AnalysisData analysis, final boolean progressBar) {
        return analyze(editorPart, editPart, Lists.newArrayList(analysis), progressBar);
    }
    
    private static final float CONFIGURE_WORK = 1;
    private static final float ANALYSIS_WORK = 4;
    private static final float TOTAL_WORK = CONFIGURE_WORK + ANALYSIS_WORK;

    /**
     * Starts the given analyses on the diagram.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if the diagram is not
     *            part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if the whole diagram
     *            shall be analyzed
     * @param analyses
     *            the analyses to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final IEditorPart editorPart,
            final EditPart editPart, final List<AnalysisData> analyses,
            final boolean progressBar) {
        final IDiagramLayoutManager<?> manager = EclipseLayoutInfoService.getInstance().getManager(
                editorPart, editPart);
        if (manager == null) {
            return Collections.emptyMap();
        }
        
        final Maybe<LayoutMapping<?>> layoutMapping = new Maybe<LayoutMapping<?>>();
        final Maybe<Map<String, Object>> result = new Maybe<Map<String, Object>>();
        if (progressBar) {
            // perform analysis with a progress bar
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // first phase: build the graph
                protected void preUIexec() {
                    layoutMapping.set(manager.buildLayoutGraph(editorPart, editPart));
                }

                // second phase: analyze the graph
                protected IStatus execute(final IProgressMonitor monitor) {
                    IKielerProgressMonitor kmonitor = new KielerProgressMonitor(monitor,
                            MAX_PROGRESS_LEVELS);
                    kmonitor.begin("Diagram analysis", TOTAL_WORK);
                    // configure the layout graph to set proper layout options
                    DiagramLayoutEngine.INSTANCE.getOptionManager().configure(layoutMapping.get(),
                            kmonitor.subTask(CONFIGURE_WORK));
                    // perform analyses on the graph
                    KNode graph = layoutMapping.get().getLayoutGraph();
                    result.set(AnalysisService.getInstance().analyze(graph, analyses,
                            kmonitor.subTask(ANALYSIS_WORK)));
                    if (kmonitor.isCanceled()) {
                        return new Status(IStatus.CANCEL, GranaPlugin.PLUGIN_ID, null);
                    } else {
                        return new Status(IStatus.OK, GranaPlugin.PLUGIN_ID, null);
                    }
                }
            };
            monitoredOperation.runMonitored();
            return result.get();

        } else {
            // perform analysis without a progress bar
            MonitoredOperation.runInUI(new Runnable() {
                // first phase: build the graph
                public void run() {
                    layoutMapping.set(manager.buildLayoutGraph(editorPart, editPart));
                }
            }, true);
            
            // second phase: analyze the graph
            KNode graph = layoutMapping.get().getLayoutGraph();
            return AnalysisService.getInstance().analyze(graph, analyses, new BasicProgressMonitor(0));
        }
    }
    
}
