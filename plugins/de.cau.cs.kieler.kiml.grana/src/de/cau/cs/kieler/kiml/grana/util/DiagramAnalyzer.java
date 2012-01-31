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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;
import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * This class provides static methods to start an analysis of a given diagram. Most of the defined
 * methods provide a convinience API.
 * 
 * @author mri
 */
public final class DiagramAnalyzer {

    /** maximal number of recursion levels for which progress is displayed. */
    private static final int MAX_PROGRESS_LEVELS = 3;
    /** the currently analysed graph. */
    private static KNode currentGraph;
    /** the last analysis result. */
    private static Map<String, Object> lastResult;

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
     *            shall be analysed
     * @param analysis
     *            the analysis to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyze(final IEditorPart editorPart, final EditPart editPart,
            final AnalysisData analysis, final boolean progressBar) {
        return analyze(editorPart, editPart, analysis, null, progressBar);
    }

    /**
     * Starts the given analysis on a diagram with predefined options.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if the diagram is not
     *            part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if the whole diagram
     *            shall be analysed
     * @param analysis
     *            the analysis to perform
     * @param options
     *            the options
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyze(final IEditorPart editorPart, final EditPart editPart,
            final AnalysisData analysis, final Map<String, Object> options,
            final boolean progressBar) {
        List<AnalysisData> analyses = new LinkedList<AnalysisData>();
        analyses.add(analysis);
        Map<String, Object> result = analyze(editorPart, editPart, analyses, options, progressBar);
        if (result.containsKey(analysis.getId())) {
            return result.get(analysis.getId());
        } else {
            return null;
        }
    }

    /**
     * Starts the given analyses on the diagram.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if the diagram is not
     *            part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if the whole diagram
     *            shall be analysed
     * @param analyses
     *            the analyses to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final IEditorPart editorPart,
            final EditPart editPart, final List<AnalysisData> analyses,
            final boolean progressBar) {
        return analyze(editorPart, editPart, analyses, null, progressBar);
    }

    /**
     * Starts the given analyses on the diagram with predefined options.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if the diagram is not
     *            part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if the whole diagram
     *            shall be analysed
     * @param analyses
     *            the analyses to perform
     * @param options
     *            the options
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final IEditorPart editorPart,
            final EditPart editPart, final List<AnalysisData> analyses,
            final Map<String, Object> options, final boolean progressBar) {
        final List<AnalysisData> analysesSequence = AnalysisService.getInstance()
                .getExecutionOrder(analyses);
        lastResult = null;
        final IDiagramLayoutManager<?> manager = EclipseLayoutInfoService.getInstance().getManager(
                editorPart, editPart);
        if (manager == null) {
            return Collections.emptyMap();
        }
        
        // perform analysis with a progress bar
        if (progressBar) {
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // first phase: build the graph
                @Override
                protected void preUIexec() {
                    LayoutMapping<?> mapping = manager.buildLayoutGraph(editorPart, editPart);
                    currentGraph = mapping.getLayoutGraph();
                }

                // second phase: analyse the graph
                @Override
                protected IStatus execute(final IProgressMonitor monitor) {
                    IKielerProgressMonitor kmonitor = new KielerProgressMonitor(monitor,
                            MAX_PROGRESS_LEVELS);
                    lastResult = doAnalyses(kmonitor, analysesSequence, options);
                    if (kmonitor.isCanceled()) {
                        return new Status(IStatus.CANCEL, GranaPlugin.PLUGIN_ID, 0, null, null);
                    } else {
                        return new Status(IStatus.OK, GranaPlugin.PLUGIN_ID, IStatus.OK, null, null);
                    }
                }
            };
            monitoredOperation.runMonitored();
            return lastResult;

            // perform analysis without a progress bar
        } else {
            MonitoredOperation.runInUI(new Runnable() {
                // first phase: build the graph
                public void run() {
                    LayoutMapping<?> mapping = manager.buildLayoutGraph(editorPart, editPart);
                    currentGraph = mapping.getLayoutGraph();
                }
            }, true);
            // second phase: analyse the graph
            return doAnalyses(new BasicProgressMonitor(0), analysesSequence, options);
        }
    }

    /**
     * Starts the given analysis on a diagram.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analysis
     *            the analysis to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyze(final KNode parentNode, final AnalysisData analysis,
            final boolean progressBar) {
        return analyze(parentNode, analysis, null, progressBar);
    }

    /**
     * Starts the given analysis on a diagram with predefined options.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analysis
     *            the analysis to perform
     * @param options
     *            the options
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyze(final KNode parentNode, final AnalysisData analysis,
            final Map<String, Object> options, final boolean progressBar) {
        List<AnalysisData> analyses = new LinkedList<AnalysisData>();
        analyses.add(analysis);
        Map<String, Object> result = analyze(parentNode, analyses, options, progressBar);
        if (result.containsKey(analysis.getId())) {
            return result.get(analysis.getId());
        } else {
            return null;
        }
    }

    /**
     * Starts the given analyses on the kgraph.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analyses
     *            the analyses to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final KNode parentNode,
            final List<AnalysisData> analyses, final boolean progressBar) {
        return analyze(parentNode, analyses, null, progressBar);
    }

    /**
     * Starts the given analyses on the kgraph with predefined options.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analyses
     *            the analyses to perform
     * @param options
     *            the options
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final KNode parentNode,
            final List<AnalysisData> analyses, final Map<String, Object> options,
            final boolean progressBar) {
        final List<AnalysisData> analysesSequence = AnalysisService.getInstance()
                .getExecutionOrder(analyses);
        currentGraph = parentNode;
        lastResult = null;
        // perform analysis with a progress bar
        if (progressBar) {
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // analyse the graph
                @Override
                protected IStatus execute(final IProgressMonitor monitor) {
                    IKielerProgressMonitor kmonitor = new KielerProgressMonitor(monitor,
                            MAX_PROGRESS_LEVELS);
                    lastResult = doAnalyses(kmonitor, analysesSequence, options);
                    if (kmonitor.isCanceled()) {
                        return new Status(IStatus.CANCEL, GranaPlugin.PLUGIN_ID, 0, null, null);
                    } else {
                        return new Status(IStatus.OK, GranaPlugin.PLUGIN_ID, IStatus.OK, null, null);
                    }
                }
            };
            monitoredOperation.runMonitored();
            return lastResult;

            // perform analysis without a progress bar
        } else {
            // analyse the graph
            return doAnalyses(new BasicProgressMonitor(0), analysesSequence, options);
        }
    }

    /**
     * Starts the given analysis on a diagram using a progress monitor.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analysis
     *            the analysis to perform
     * @param monitor
     *            the progress monitor
     * @return the analysis result
     */
    public static Object analyze(final KNode parentNode, final AnalysisData analysis,
            final IKielerProgressMonitor monitor) {
        return analyze(parentNode, analysis, null, monitor);
    }

    /**
     * Starts the given analysis on a diagram with predefined options and using a progress monitor.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analysis
     *            the analysis to perform
     * @param options
     *            the options
     * @param monitor
     *            the progress monitor
     * @return the analysis result
     */
    public static Object analyze(final KNode parentNode, final AnalysisData analysis,
            final Map<String, Object> options, final IKielerProgressMonitor monitor) {
        List<AnalysisData> analyses = new LinkedList<AnalysisData>();
        analyses.add(analysis);
        Map<String, Object> result = analyze(parentNode, analyses, options, monitor);
        if (result.containsKey(analysis.getId())) {
            return result.get(analysis.getId());
        } else {
            return null;
        }
    }

    /**
     * Starts the given analyses on the kgraph using a progress monitor.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analyses
     *            the analyses to perform
     * @param monitor
     *            the progress monitor
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final KNode parentNode,
            final List<AnalysisData> analyses, final IKielerProgressMonitor monitor) {
        return analyze(parentNode, analyses, null, monitor);
    }

    /**
     * Starts the given analyses on the kgraph with predefined options and using a progress monitor.
     * 
     * @param parentNode
     *            the graph the analyses shall be performed on
     * @param analyses
     *            the analyses to perform
     * @param options
     *            the options
     * @param monitor
     *            the progress monitor
     * @return the analyses results
     */
    public static Map<String, Object> analyze(final KNode parentNode,
            final List<AnalysisData> analyses, final Map<String, Object> options,
            final IKielerProgressMonitor monitor) {
        final List<AnalysisData> analysesSequence = AnalysisService.getInstance()
                .getExecutionOrder(analyses);
        currentGraph = parentNode;
        lastResult = null;
        return doAnalyses(monitor, analysesSequence, options);
    }

    /**
     * Calls the actual analyses for the current graph.
     * 
     * @param monitor
     *            the monitor
     * @param analyses
     *            the analyses
     * @param options
     *            the options
     * @return the analyses results
     */
    private static Map<String, Object> doAnalyses(final IKielerProgressMonitor monitor,
            final List<AnalysisData> analyses, final Map<String, Object> options) {
        monitor.begin("Begin analyses", analyses.size());
        Map<String, Object> results;
        if (options == null) {
            results = new HashMap<String, Object>(analyses.size());
        } else {
            results = options;
        }
        for (AnalysisData analysis : analyses) {
            try {
                if (monitor.isCanceled()) {
                    results.put(analysis.getId(), new AnalysisFailed(AnalysisFailed.Type.Canceled));
                } else {
                    IAnalysis ana = analysis.getInstancePool().fetch();
                    Object obj = ana.doAnalysis(currentGraph, results, monitor.subTask(1));
                    results.put(analysis.getId(), obj);
                    analysis.getInstancePool().release(ana);
                }
            } catch (Exception e) {
                results.put(analysis.getId(), new AnalysisFailed(AnalysisFailed.Type.Failed, e));
            }
        }
        monitor.done();
        return results;
    }
    
}
