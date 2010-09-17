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
 * Abstract base implementation for effects.
 * 
 * @author mmu
 *
 */
public abstract class AbstractEffect implements IEffect {
    
    /**
     * {@inheritDoc}
     */
    public abstract void execute();
    
    /**
     * {@inheritDoc}
     */
    public void schedule() {
        KiVi.getInstance().executeEffect(this);
    }

    /**
     * {@inheritDoc}
     */
    public void undo() {
        // needs to be overwritten when required
    }
    
    /**
     * {@inheritDoc}
     */
    public void scheduleUndo() {
        KiVi.getInstance().undoEffect(this);
    }

    /**
     * {@inheritDoc}
     */
    public boolean combinable() {
        // default behavior: not combinable
        return false;
    }
    
}
