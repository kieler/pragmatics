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
 * Abstract base implementation for triggers.
 * 
 * @author mmu
 */
public abstract class AbstractTrigger implements ITrigger {
    
    private boolean active = false;

    /**
     * {@inheritDoc}
     */
    public void trigger(final ITriggerState triggerState) {
        KiVi.getInstance().trigger(triggerState);
    }
    
    /**
     * Triggers a TriggerState just as {@link #trigger(ITriggerState)}, but
     * blocks this thread until all effects that are created following this trigger
     * are executed on the effects thread. Hence this method can be used to 
     * create back pressure and block the triggering of new effects until all
     * old effects have been executed. Therefore the effects queue will not 
     * explode.
     * @param triggerState the new state that KIVi should distribute
     * @throws InterruptedException if the blocking is interrupted
     */
    public void synchronizedTrigger(final ITriggerState triggerState) throws InterruptedException {
        synchronized (triggerState) {
            trigger(triggerState);
            triggerState.wait();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setActive(final boolean a) {
        if (!active && a) {
            register();
        } else if (active && !a) {
            unregister();
        }
        active = a;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * {@inheritDoc}
     */
    public abstract void register();
    
    /**
     * {@inheritDoc}
     */
    public abstract void unregister();
    
    

}
