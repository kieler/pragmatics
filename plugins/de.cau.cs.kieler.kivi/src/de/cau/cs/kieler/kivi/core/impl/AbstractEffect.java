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
package de.cau.cs.kieler.kivi.core.impl;

import de.cau.cs.kieler.kivi.core.IEffect;

/**
 * Abstract base implementation for effects.
 * 
 * @author mmu
 *
 */
public abstract class AbstractEffect implements IEffect {

    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kivi.core.IEffect#execute()
     */
    public abstract void execute();

    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kivi.core.IEffect#undo()
     */
    public void undo() {
        // needs to be overwritten when required
    }

    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kivi.core.IEffect#synchronous()
     */
    public boolean synchronous() {
        // default behavior: synchronous
        return true;
    }

    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kivi.core.IEffect#combineable()
     */
    public boolean combinable() {
        // default behavior: not combinable
        return false;
    }
    
}
