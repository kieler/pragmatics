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
        trigger(new EffectTriggerState<IEffect>(effect));
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
         */
        public EffectTriggerState(final Effect e) {
            effect = e;
        }

        /**
         * Get the effect.
         * 
         * @return the effect
         */
        public Effect getEffect() {
            return effect;
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
