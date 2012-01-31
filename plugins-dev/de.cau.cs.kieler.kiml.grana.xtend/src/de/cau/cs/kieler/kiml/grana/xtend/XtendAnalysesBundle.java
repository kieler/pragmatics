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
package de.cau.cs.kieler.kiml.grana.xtend;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.kiml.grana.IAnalysisBundle;
import de.cau.cs.kieler.kiml.grana.IBundleChangedListener;
import de.cau.cs.kieler.kiml.service.grana.AbstractInfoAnalysis;

/**
 * The bundle that supplies the xtend analyses to the graph analysis service.
 * 
 * @author mri
 */
public class XtendAnalysesBundle implements IAnalysisBundle {

    /** a list of all bundle changed listeners. */
    private static List<IBundleChangedListener> listeners =
            new LinkedList<IBundleChangedListener>();
    /** the registered xtend analyses. */
    private static List<AbstractInfoAnalysis> analyses =
            new LinkedList<AbstractInfoAnalysis>();

    /**
     * Adds a xtend analysis to the bundle.
     * 
     * @param analysis
     *            the xtend analysis
     */
    public static void addXtendAnalysis(final XtendAnalysis analysis) {
        analyses.add(analysis);
        notifyListenersAnalysisAdded(analysis);
    }

    /**
     * Removes a xtend analysis from the bundle.
     * 
     * @param analysis
     *            the xtend analysis
     */
    public static void removeXtendAnalysis(final XtendAnalysis analysis) {
        analyses.remove(analysis);
        notifyListenersAnalysisRemoved(analysis);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<AbstractInfoAnalysis> getAnalyses() {
        return analyses;
    }

    /**
     * Adds a listener to the bundle.
     * 
     * @param listener
     *            the listener
     */
    public void addBundleChangedListener(final IBundleChangedListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * Removes a listener from the bundle.
     * 
     * @param listener
     *            the listener
     */
    public void removeBundleChangedListener(
            final IBundleChangedListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all listeners that an analysis was added.
     * 
     * @param analysis
     *            the analysis
     */
    protected static void notifyListenersAnalysisAdded(
            final AbstractInfoAnalysis analysis) {
        for (IBundleChangedListener listener : listeners) {
            listener.analysisAdded(analysis);
        }
    }

    /**
     * Notifes all listeners that an analysis was removed.
     * 
     * @param analysis
     *            the analysis
     */
    protected static void notifyListenersAnalysisRemoved(
            final AbstractInfoAnalysis analysis) {
        for (IBundleChangedListener listener : listeners) {
            listener.analysisRemoved(analysis);
        }
    }
}
