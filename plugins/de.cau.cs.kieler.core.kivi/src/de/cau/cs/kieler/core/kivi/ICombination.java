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

import java.util.List;

/**
 * A combination connects triggers and effects, it receives a set of events from triggers and
 * determines which effects need to be executed. Handling of triggers is done by the
 * {@link ITriggerListener} interface. During a call of
 * {@link ITriggerListener#handle(ITriggerState)}, the combination should collect some
 * {@link IEffect}s, which KiVi will retrieve by {@link #getEffects()}.
 * 
 * @author haf, mmu
 * 
 */
public interface ICombination extends ITriggerListener {

    /**
     * Get the list of effects that are ready to be executed after the last triggering of an
     * {@link ITriggerState}.
     * 
     * @return list of effects that should be executed
     */
    List<IEffect> getEffects();

    /**
     * Attempt to undo all active effects, for example when shutting down view management or when
     * disabling this combination. Schedules all undo immediately.
     */
    void undo();

    /**
     * Check whether this combination is active.
     * 
     * @return true if the combination is active
     */
    boolean isActive();

    /**
     * Change the active status of this combination.
     * 
     * @param active
     *            new active status
     */
    void setActive(boolean active);
    
    /**
     * If true a progressMonitor will be displayed if execution of the Effects
     * takes a certain time.
     * 
     * @return true if progressMonitor should be displayed false else.
     */
    boolean runWithProgressMonitor();
    
    /**
     * The name of the combination. Will for example be displayed in the 
     * progressMonitor.
     * @return name of the combination
     */
    String getName();
}
