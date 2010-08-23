/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivi.core.impl;

import de.cau.cs.kieler.kivi.core.ITrigger;
import de.cau.cs.kieler.kivi.core.Viewmanagement;

/**
 * Abstract base implementation for triggers.
 * 
 * @author mmu
 *
 */
public abstract class AbstractTrigger implements ITrigger {
    
    private boolean active = false;

    /**
     * {@inheritDoc}
     */
    public void trigger(final ITrigger trigger) {
        Viewmanagement.getInstance().trigger(trigger);
    }

    /**
     * {@inheritDoc}
     */
    public void setActive(final boolean a) {
        active = a;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isActive() {
        return active;
    }

}
