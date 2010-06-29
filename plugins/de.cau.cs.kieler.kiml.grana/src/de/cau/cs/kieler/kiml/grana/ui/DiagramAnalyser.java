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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.grana.AnalysisCanceled;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * This class provides static methods to start an analysis of a given diagram.
 * 
 * @author mri
 */
public final class DiagramAnalyser {

    /** maximal number of recursion levels for which progress is displayed. */
    private static final int MAX_PROGRESS_LEVELS = 3;

    /** the currently analysed graph. */
    private static KNode currentGraph;

    /** the last analysis result. */
    private static Object[] lastResult;

    /**
     * This class is an utility class and should not be instantiated.
     */
    private DiagramAnalyser() {
    }

    /**
     * Starts the given analysis on a diagram.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if
     *            the whole diagram shall be analysed
     * @param analysis
     *            the analysis to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analysis result
     */
    public static Object analyse(final IEditorPart editorPart,
            final EditPart editPart, final IAnalysis analysis,
            final boolean progressBar) {
        IAnalysis[] analyses = new IAnalysis[1];
        analyses[0] = analysis;
        Object[] result = analyse(editorPart, editPart, analyses, progressBar);
        if (result != null && result.length > 0) {
            return result[0];
        } else {
            return null;
        }
    }

    /**
     * Starts the given analyses on the diagram.
     * 
     * @param editorPart
     *            the editor the analysis is performed on, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the edit part the analysis is performed on, or {@code null} if
     *            the whole diagram shall be analysed
     * @param analyses
     *            the analyses to perform
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the analyses results
     */
    public static Object[] analyse(final IEditorPart editorPart,
            final EditPart editPart, final IAnalysis[] analyses,
            final boolean progressBar) {
        lastResult = null;
        // perform analysis with a progress bar
        if (progressBar) {
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // first phase: build the graph
                @Override
                protected void preUIexec() {
                    currentGraph = EclipseLayoutServices.getInstance().getManager(editorPart,
                            editPart).buildLayoutGraph(editorPart, editPart,
                            false);
                }

                // second phase: analyse the graph
                @Override
                protected IStatus execute(final IProgressMonitor monitor) {
                    IKielerProgressMonitor kmonitor = new KielerProgressMonitor(
                            monitor, MAX_PROGRESS_LEVELS);
                    kmonitor.begin("Begin analyses", analyses.length);
                    lastResult = doAnalyses(kmonitor, analyses);
                    kmonitor.done();
                    if (kmonitor.isCanceled()) {
                        return new Status(IStatus.CANCEL,
                                GranaPlugin.PLUGIN_ID, 0, null, null);
                    } else {
                        return new Status(IStatus.OK, GranaPlugin.PLUGIN_ID,
                                IStatus.OK, null, null);
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
                    currentGraph = EclipseLayoutServices.getInstance().getManager(editorPart,
                            editPart).buildLayoutGraph(editorPart, editPart,
                            false);
                }
            }, true);
            // second phase: analyse the graph
            return doAnalyses(new BasicProgressMonitor(0), analyses);
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
    public static Object analyse(final KNode parentNode, final IAnalysis analysis,
            final boolean progressBar) {
        IAnalysis[] analyses = new IAnalysis[1];
        analyses[0] = analysis;
        Object[] result = analyse(parentNode, analyses, progressBar);
        if (result != null && result.length > 0) {
            return result[0];
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
    public static Object[] analyse(final KNode parentNode,
            final IAnalysis[] analyses, final boolean progressBar) {
        currentGraph = parentNode;
        lastResult = null;
        // perform analysis with a progress bar
        if (progressBar) {
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // analyse the graph
                @Override
                protected IStatus execute(final IProgressMonitor monitor) {
                    IKielerProgressMonitor kmonitor = new KielerProgressMonitor(
                            monitor, MAX_PROGRESS_LEVELS);
                    kmonitor.begin("Begin analyses", analyses.length);
                    lastResult = doAnalyses(kmonitor, analyses);
                    kmonitor.done();
                    if (kmonitor.isCanceled()) {
                        return new Status(IStatus.CANCEL,
                                GranaPlugin.PLUGIN_ID, 0, null, null);
                    } else {
                        return new Status(IStatus.OK, GranaPlugin.PLUGIN_ID,
                                IStatus.OK, null, null);
                    }
                }
            };
            monitoredOperation.runMonitored();
            return lastResult;

            // perform analysis without a progress bar
        } else {
            // analyse the graph
            return doAnalyses(new BasicProgressMonitor(0), analyses);
        }
    }

    /**
     * Calls the actual analyses for the current graph.
     * 
     * @param monitor
     *            the monitor
     * @param analyses
     *            the analyses
     * @return the analyses results
     */
    private static Object[] doAnalyses(final IKielerProgressMonitor monitor,
            final IAnalysis[] analyses) {
        Object[] result = new Object[analyses.length];
        int i = 0;
        for (IAnalysis analysis : analyses) {
            try {
                if (monitor.isCanceled()) {
                    for (; i < result.length; ++i) {
                        result[i] = new AnalysisCanceled();
                    }
                    return result;
                }
                result[i++] = analysis.doAnalysis(currentGraph, monitor.subTask(1));
            } catch (KielerException e) {
                result[i++] = new AnalysisFailed();
            }
        }
        return result;
    }
}
