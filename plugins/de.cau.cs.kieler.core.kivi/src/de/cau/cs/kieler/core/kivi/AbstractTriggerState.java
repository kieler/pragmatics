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
package de.cau.cs.kieler.core.kivi;

/**
 * Base implementation for trigger states.
 * 
 * @author mmu
 * 
 */
public abstract class AbstractTriggerState implements ITriggerState {

    private long sequenceNumber = 0;

    /**
     * {@inheritDoc}
     */
    public void merge(final ITriggerState previous) {
        // do nothing, needs to be overridden for delta-supporting states
    }

    /**
     * {@inheritDoc}
     */
    public void finish() {
        // do nothing, needs to be overridden for event-ish states
    }

    /**
     * {@inheritDoc}
     */
    public long getSequenceNumber() {
        return sequenceNumber; // TODO determine best point of setting number
    }
}
