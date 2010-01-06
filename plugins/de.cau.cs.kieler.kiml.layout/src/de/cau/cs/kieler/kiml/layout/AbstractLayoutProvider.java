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
package de.cau.cs.kieler.kiml.layout;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * A layout provider executes a layout algorithm to layout the child elements of
 * a node.
 * <p>
 * When used in Eclipse, layout providers must register through the {@code
 * layoutProviders} extension point. All layout providers published to Eclipse
 * this way are collected in the {@link LayoutServices} singleton, provided the
 * UI plugin is loaded.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author ars
 */
public abstract class AbstractLayoutProvider {

    /**
     * Initialize the layout provider with the given parameter. The default
     * implementation does nothing.
     * 
     * @param parameter a string used to parameterize the layout provider
     *            instance
     */
    public void initialize(final String parameter) {
    }

    /**
     * Performs the actual layout process, that is attaches layout information
     * to the given node object.
     * 
     * @param layoutNode the parent node which should be laid out
     * @param progressMonitor progress monitor used to keep track of progress
     * @throws KielerException if the method fails to perform layout
     */
    public abstract void doLayout(KNode layoutNode, IKielerProgressMonitor progressMonitor)
            throws KielerException;

    /**
     * Returns the default value for the given layout option. The default
     * implementation always returns {@code null}.
     * 
     * @param optionId identifier of a layout option
     * @return the default value for the given option, or {@code null} if this
     *         layout provider does not know that option
     */
    public Object getDefault(final String optionId) {
        return null;
    }
    
    /**
     * Determines whether this layout provider supports hierarchy. If it does, it is
     * expected to layout not only the first hierarchy level of the input graph, but
     * also all children. The default implementation returns {@code false}.
     * 
     * @param layoutNode the parent node for which layout is requested
     * @return true if the layout provider supports hierarchy
     */
    public boolean supportsHierarchy(final KNode layoutNode) {
        return false;
    }

}
