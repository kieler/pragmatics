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
package de.cau.cs.kieler.kivi.examples;

import de.cau.cs.kieler.kivi.core.IEffect;
import de.cau.cs.kieler.kivi.core.impl.AbstractEffect;

/**
 * Takes another effect and performs undo when executed.
 * 
 * @author mmu
 * 
 */
public class UndoEffect extends AbstractEffect {

    private IEffect effect;

    /**
     * Create a new undo effect.
     * 
     * @param e the effect to be undone
     */
    public UndoEffect(final IEffect e) {
        effect = e;
    }

    @Override
    public void execute() {
        effect.undo();
    }

}
