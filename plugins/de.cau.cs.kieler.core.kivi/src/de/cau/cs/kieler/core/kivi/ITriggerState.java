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
 * Holds the state for a specific trigger. Gets updated every time the trigger receives an event.
 * Any concrete getData method needs to provide sane default values.
 * 
 * @author mmu
 * 
 */
public interface ITriggerState {

    /**
     * Merge this state instance with the previous instance in case of delta trigger events.
     * 
     * @param previous the previous instance of this trigger state
     */
    void merge(ITriggerState previous);
    
    /**
     * Invalidate any data that is only valid once, for example push button events.
     */
    void finish();
    
    /**
     * Get the trigger class associated with this trigger state.
     * 
     * @return the trigger class
     */
    Class<? extends ITrigger> getTriggerClass();
    
    /**
     * Get the sequence number of this state to determine a temporal order.
     * 
     * @return the sequence number
     */
    long getSequenceNumber();
}
