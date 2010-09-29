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
package de.cau.cs.kieler.core.kivi.internal;

import de.cau.cs.kieler.core.kivi.IEffect;

/**
 * Listens to effects being executed.
 * 
 * @author mmu
 *
 */
public interface IEffectsListener {

    /**
     * Called after an effect has been executed.
     * 
     * @param effect the executed effect
     */
    void executedEffect(IEffect effect);
}
