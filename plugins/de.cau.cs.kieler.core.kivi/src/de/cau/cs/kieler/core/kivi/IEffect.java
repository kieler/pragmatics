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
 * An effect executes some type of view management action, usually a visual change of the graphical
 * model.
 * 
 * haf: The execute and undo methods get called by the EffectsWorker thread and are expected
 * to do something on the UI thread. Avoid working on the UI thread in an Effects *Constructor*, as
 * it will be called in another thread causing an UI-thread Deadlock!
 * 
 * @author mmu
 * 
 */
public interface IEffect {

    /**
     * Execute the effect.
     */
    void execute();

    /**
     * Schedule the effect for execution.
     */
    void schedule();

    /**
     * Attempt to undo the effect.
     */
    void undo();

    /**
     * Schedule the effect to be undone.
     */
    void scheduleUndo();

    /**
     * Determines whether this effect can be merged with previously scheduled effects.
     * 
     * @return true if it can be merged, false if no search through the effect queue is necessary
     */
    boolean isMergeable();
    
    /**
     * Merge this effect with another effect. Will return null if the effects can not be merged.
     * 
     * @param otherEffect the effect to merge with
     * @return the newly merged effect, or null if no merge was possible.
     */
    IEffect merge(IEffect otherEffect);

    /**
     * Speaking name of the effect. Will for example be used in the progressMonitor.
     * @return name of the effect.
     */
    String getName();
    
}
