/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
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
 * An interface for listeners of {@link ITriggerState} changes. Subclasses should specify
 * for which kinds of trigger states they want to receive notifications in {@link #getTriggerStates()}
 * and a specific trigger state needs to be handled in {@link #handle(ITriggerState)}.
 * <p>
 * Implementations are then registered and coordinated by {@link KiVi}.
 * 
 * @author haf
 */
public interface ITriggerListener {

    /**
     * Get the array of trigger state classes this combination listens to.
     * 
     * @return array of trigger states
     */
    Class<? extends ITriggerState>[] getTriggerStates();
    
    /**
     * Handle one specific trigger state that just changed.
     * 
     * @param triggerState the trigger state responsible for the triggering
     */
    void handle(ITriggerState triggerState);
    
}
