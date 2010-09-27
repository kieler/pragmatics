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

import java.util.List;

/**
 * A combination connects triggers and effects, it receives a set of events from
 * triggers and determines which effects need to be executed.
 * 
 * @author mmu
 * 
 */
public interface ICombination {

    /**
     * Trigger the evaluation and execution of this combination.
     * 
     * @return the list of effects to execute
     */
    List<IEffect> trigger();
    
    /**
     * Get the array of trigger state classes this combination listens to.
     * 
     * @return array of trigger states
     */
    Class<? extends ITriggerState>[] getTriggerStates();

    /**
     * Attempt to undo all active effects, for example when shutting down view
     * management or when disabling this combination.
     */
    void undo();
    
    /**
     * Check whether this combination is active.
     * 
     * @return true if the combination is active
     */
    boolean isActive();
    
    /**
     * Change the active status of this combination.
     * 
     * @param active new active status
     */
    void setActive(boolean active);
}
