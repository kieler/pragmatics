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

import de.cau.cs.kieler.core.kivi.internal.IEffectsListener;

/**
 * A trigger that listens to effects being executed.
 * 
 * @author mmu
 * 
 */
public class EffectTrigger extends AbstractTrigger implements IEffectsListener {

    /**
     * {@inheritDoc}
     */
    public void executedEffect(final IEffect effect) {
        if (effect instanceof UndoEffect) {
            trigger(new EffectTriggerState<IEffect>(((UndoEffect) effect).getEffect(), true));
        } else {
            trigger(new EffectTriggerState<IEffect>(effect, false));
        }
    }

    @Override
    public void register() {
        KiVi.getInstance().addEffectsListener(this);
    }

    @Override
    public void unregister() {
        KiVi.getInstance().removeEffectsListener(this);
    }

    /**
     * The trigger state for recently executed effects.
     * 
     * @author mmu
     * 
     * @param <Effect>
     *            the effect class contained in this trigger state
     */
    public static class EffectTriggerState<Effect extends IEffect> extends AbstractTriggerState {

        private Effect effect;

        private boolean isUndo = false;

        /**
         * Default constructor.
         */
        public EffectTriggerState() {
        }

        /**
         * Create a new effect trigger state for the given effect.
         * 
         * @param e
         *            the effect
         * @param undo
         *            true if the effect was undone
         */
        public EffectTriggerState(final Effect e, final boolean undo) {
            effect = e;
            isUndo = undo;
        }

        /**
         * Get the effect. May be null if the effect never was executed
         * 
         * @return the effect
         */
        public Effect getEffect() {
            return effect;
        }

        /**
         * Checks whether the effect was undone or not.
         * 
         * @return true if undone, false if executed
         */
        public boolean isUndo() {
            return isUndo;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return EffectTrigger.class;
        }

        @Override
        public Class<?> getKeyClass() {
            if (effect == null) {
                return super.getKeyClass();
            } else {
                return effect.getClass();
            }
        }

    }
}
