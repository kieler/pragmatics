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

import java.util.List;

/**
 * Interface to handle compound effects. An ICompoundEffect is no IEffect itself, instead it has one
 * method to retrieve a list of primitive effects. For now this makes later merging of effects
 * simpler by only handling primitve effects. An ICompoundEffect can be used in an ICombination to
 * schedule multiple primitive effects at once.
 * 
 * TODO: In the future a real AbstractCompoundEffect should also implement IEffect or the corresponding
 * handlers (AbstractCombination and the EffectsWorker) should be extended to also handle compound
 * effects to also support arbitrarily nested effects.
 * 
 * @author haf
 */
public interface ICompoundEffect {

    /**
     * Get the list of primitive IEffects that this compound effect is composed of.
     * 
     * @return list of IEffects
     */
    List<IEffect> getPrimitiveEffects();

}
