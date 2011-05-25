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

/**
 * A simple effect that unlocks a given Object. Can be used to synchronize effects with other
 * objects.
 * 
 * @author haf
 * 
 */
public class UnlockEffect extends AbstractEffect {

    private Object unlockTarget;

    /**
     * Create a new UnlockEffect with a given target on which {@link #notifyAll()} will be called.
     * @param unlockTarget the target to call notifyAll on
     */
    public UnlockEffect(final Object unlockTarget) {
        this.unlockTarget = unlockTarget;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        synchronized (unlockTarget) {
            unlockTarget.notifyAll();
        }
    }

}
