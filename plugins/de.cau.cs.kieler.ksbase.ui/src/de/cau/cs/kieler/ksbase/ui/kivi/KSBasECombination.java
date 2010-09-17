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
package de.cau.cs.kieler.ksbase.ui.kivi;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.kiml.gmf.LayoutEffect;
import de.cau.cs.kieler.ksbase.ui.kivi.KSBasETrigger.KSBasEState;

/**
 * A Combination handling KSBasE-related effects after transformations.
 * 
 * @author mmu
 * 
 */
public class KSBasECombination extends AbstractCombination {

    /**
     * Execute the KSBasECombination after the KSBasEState was updated.
     * 
     * @param state
     *            the KSBasEState
     */
    public void execute(final KSBasEState state) {
        new LayoutEffect(state.getDiagramEditor(), state.getEObject()).schedule();
    }

}
