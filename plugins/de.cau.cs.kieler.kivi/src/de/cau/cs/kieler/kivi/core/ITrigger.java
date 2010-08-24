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
package de.cau.cs.kieler.kivi.core;

/**
 * A trigger listens to editor events and informs the view management about
 * these.
 * 
 * @author mmu
 * 
 */
public interface ITrigger {

    /**
     * Inform the view management about an event. Call this method when the
     * specific trigger implementation has detected an event.
     * 
     * @param trigger new instance of this class with set trigger parameters
     */
    void trigger(ITrigger trigger);

    /**
     * Called by the view management to register or unregister this trigger.
     * @param a true if registering
     */
    void setActive(boolean a);
    
    /**
     * Checks whether the trigger is active or not.
     * @return true if active
     */
    boolean isActive();
}
