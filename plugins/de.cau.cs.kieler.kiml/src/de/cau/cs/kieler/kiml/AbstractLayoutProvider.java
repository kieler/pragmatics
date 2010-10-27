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
package de.cau.cs.kieler.kiml;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;

/**
 * A layout provider executes a layout algorithm to layout the child elements of a node.
 * <p>When used in Eclipse, layout providers must register through the {@code layoutProviders}
 * extension point. All layout providers published to Eclipse this way are collected in the
 * {@link LayoutServices} singleton, provided the UI plugin is loaded.</p>
 * <p>Layout providers can hold properties, which represent the default layout option values
 * of the layout provider. Subclasses can register their default layout option values in
 * their constructor.</p>
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author ars
 * @author msp
 */
public abstract class AbstractLayoutProvider extends MapPropertyHolder {

    /** the debug canvas to use. */
    private IDebugCanvas debugCanvas;
    
    /**
     * Initialize the layout provider with the given parameter.
     * 
     * @param parameter a string used to parameterize the layout provider instance
     * @throws KielerException if the provider has received a wrong parameter
     */
    public void initialize(final String parameter) throws KielerException {
    }

    /**
     * Performs the actual layout process, that is attaches layout information
     * to the given node object.
     * 
     * @param parentNode the parent node which should be laid out
     * @param progressMonitor progress monitor used to keep track of progress
     * @throws KielerException if the method fails to perform layout
     */
    public abstract void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor)
            throws KielerException;
    
    /**
     * Determines whether this layout provider would handle the complete hierarchy of the
     * given layout node. If it does, it is expected to layout not only the first hierarchy
     * level of the input graph, but also all its children.
     * 
     * @param layoutNode the parent node for which layout is requested
     * @return true if the layout provider supports hierarchy
     */
    public boolean supportsHierarchy(final KNode layoutNode) {
        return false;
    }
    
    /**
     * Sets the current debug canvas.
     * 
     * @param thedebugCanvas the debug canvas
     */
    public final void setDebugCanvas(final IDebugCanvas thedebugCanvas) {
        this.debugCanvas = thedebugCanvas;
    }

    /**
     * Returns the current debug canvas.
     * 
     * @return the debug canvas
     */
    public final IDebugCanvas getDebugCanvas() {
        return debugCanvas;
    }

}
