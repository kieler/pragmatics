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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;

/**
 * The state of a layout trigger.
 * 
 * @author soh
 */
public class LayoutGraphTriggerState extends AbstractTriggerState {

    private KNode layoutGraph;

    private String state;

    /**
     * 
     * Creates a new LayoutGraphTriggerState.
     * 
     */
    public LayoutGraphTriggerState() {
        super();
    }

    /**
     * Creates a new LayoutGraphTriggerState.
     * 
     * @param layoutGraphParam
     *            the layout graph
     * @param stateParam
     *            the state
     */
    public LayoutGraphTriggerState(final KNode layoutGraphParam,
            final String stateParam) {
        super();
        this.layoutGraph = layoutGraphParam;
        setState(stateParam);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Class<? extends ITrigger> getTriggerClass() {
        return LayoutGraphTrigger.class;
    }

    /**
     * Setter for the layoutGraph.
     * 
     * @param layoutGraphParam
     *            the layoutGraph to set
     */
    public void setLayoutGraph(final KNode layoutGraphParam) {
        this.layoutGraph = layoutGraphParam;
    }

    /**
     * Getter for the layoutGraph.
     * 
     * @return the layoutGraph
     */
    public KNode getLayoutGraph() {
        return layoutGraph;
    }

    /**
     * Setter for the state.
     * 
     * @param stateParam
     *            the state to set
     */
    public void setState(final String stateParam) {
        if (!stateParam.equals(LayoutGraphTrigger.POST_LAYOUT)
                && !stateParam.equals(LayoutGraphTrigger.PRE_LAYOUT)) {
            throw new IllegalArgumentException();
        }
        this.state = stateParam;
    }

    /**
     * Getter for the state.
     * 
     * @return the state
     */
    public String getState() {
        return state;
    }

}
