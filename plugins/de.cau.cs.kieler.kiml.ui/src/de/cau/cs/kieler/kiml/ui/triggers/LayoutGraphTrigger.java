/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.triggers;

import java.util.HashSet;
import java.util.Set;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractTrigger;

/**
 * Trigger for performed and requested layouts.
 * 
 * @author soh
 */
public class LayoutGraphTrigger extends AbstractTrigger {

    /** Constant indicating that a layout is about to be performed. */
    public static final String PRE_LAYOUT = "PRE_LAYOUT";
    /** Constant indicating that a layout is just finished. */
    public static final String POST_LAYOUT = "POST_LAYOUT";

    /** All triggers. */
    private static Set<LayoutGraphTrigger> triggers =
            new HashSet<LayoutGraphTrigger>();

    @Override
    public void register() {
        triggers.add(this);
    }

    @Override
    public void unregister() {
        triggers.remove(this);
    }

    /**
     * Notify all triggers of the finished layout graph.
     * 
     * @param graph
     *            the layout graph
     * @param stateParam
     *            the state of the layouter.
     */
    private static void triggerAll(final KNode graph, final String stateParam) {
        LayoutGraphTriggerState state =
                new LayoutGraphTriggerState(graph, stateParam);
        for (LayoutGraphTrigger trigger : triggers) {
            trigger.trigger(state);
        }
    }

    /**
     * Notify all combinations that a layout is about to execute.
     * 
     * @param graph
     *            the layout graph built by the layout manager
     */
    public static void triggerPreLayout(final KNode graph) {
        triggerAll(graph, PRE_LAYOUT);
    }

    /**
     * Notify all combinations that a layout has just finished.
     * 
     * @param graph
     *            the layout graph
     */
    public static void triggerPostLayout(final KNode graph) {
        triggerAll(graph, POST_LAYOUT);
    }
}
