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
package de.cau.cs.kieler.kivi.examples;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.ILayoutListener;
import de.cau.cs.kieler.kivi.core.Viewmanagement;
import de.cau.cs.kieler.kivi.core.impl.AbstractTrigger;

/**
 * Listens to automatic layout being performed.
 * 
 * @author mmu
 * 
 */
public class LayoutTrigger extends AbstractTrigger implements ILayoutListener {

    private static LayoutTrigger instance;

    /**
     * Default constructor.
     * 
     * FIXME check if actually needed, and if yes then split up trigger and layout listener into two
     * classes to avoid the instantiation hassle.
     */
    public LayoutTrigger() {
        if (instance != null) {
            // KIML creates a new instance on first layout, use this instance in the view management
            // instead of the instance created by the view management on activation of a combination
            Viewmanagement.getInstance().swapTrigger(this);
        }
        // Remember the instance created because the layout mechanism creates its own listener
        instance = this;
    }

    /**
     * Get the faux-singleton instance.
     * 
     * @return the instance
     */
    public static LayoutTrigger getInstance() {
        return instance;
    }

    @Override
    public void register() {
        // nothing to do because KIML doesn't support dynamic registering/unregistering
    }

    @Override
    public void unregister() {
        // nothing to do because KIML doesn't support dynamic registering/unregistering
    }

    /**
     * {@inheritDoc}
     */
    public void layoutRequested(final KNode layoutGraph) {
        // nothing to do
    }

    /**
     * {@inheritDoc}
     */
    public void layoutPerformed(final KNode layoutGraph, final IKielerProgressMonitor monitor) {
        if (isActive()) {
            Viewmanagement.getInstance().trigger(this);
        }
    }

}
