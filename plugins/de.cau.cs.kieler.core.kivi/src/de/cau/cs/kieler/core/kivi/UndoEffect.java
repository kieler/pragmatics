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
     * @param e
     *            the effect to be undone
     */
    public UndoEffect(final IEffect e) {
        effect = e;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        effect.undo();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isMergeable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public IEffect merge(final IEffect otherEffect) {
        // if there are any scheduled executions of the effect then cancel those executions
        // undo alone will suffice
        if (effect == otherEffect) {
            return this;
        } else {
            return null;
        }
    }
    
    /**
     * Get the effect that is supposed to be undone.
     * 
     * @return the effect
     */
    public IEffect getEffect() {
        return effect;
    }
    
    @Override
    public String toString(){
        return "Undo"+effect; 
    }

}
