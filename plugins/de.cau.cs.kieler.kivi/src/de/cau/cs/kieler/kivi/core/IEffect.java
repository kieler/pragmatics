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
package de.cau.cs.kieler.kivi.core;

/**
 * An effect executes some type of view management action, usually a visual
 * change of the graphical model.
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
     * Attempt to undo the effect.
     */
    void undo();

    /**
     * Determines whether this effect should be executed synchronously by the
     * worker thread, ie if other effects may be started while this effect still
     * is running.
     * 
     * @return true if the effect should be executed synchronously
     */
    boolean synchronous();

    /**
     * Determines whether multiple instances of this effect can be combined into
     * one instance. For example multiple layout calls need not be executed
     * multiple times, once is enough.
     * 
     * @return true if multiple instances of this effect can be combined into
     *         one instance
     */
    boolean combinable();
    
    // TODO targets, parameters?
}
