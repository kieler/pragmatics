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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;

/**
 * A layout provider executes a layout algorithm to layout the child elements of a node.
 * <p>When used in Eclipse, layout providers must register through the {@code layoutProviders}
 * extension point. All layout providers published to Eclipse this way are collected in the
 * {@link LayoutDataService} singleton, provided the UI plugin is loaded.
 * 
 * @kieler.rating 2011-01-17 proposed yellow
 *     reviewed by haf, cmot, soh
 * @author ars
 * @author msp
 */
public abstract class AbstractLayoutProvider {

    /** the debug canvas to use. */
    private IDebugCanvas debugCanvas;
    
    /**
     * Initialize the layout provider with the given parameter.
     * 
     * @param parameter a string used to parameterize the layout provider instance
     */
    public void initialize(final String parameter) {
    }

    /**
     * Performs the actual layout process, that is attaches layout information
     * to the given node object.
     * 
     * @param parentNode the parent node which should be laid out
     * @param progressMonitor progress monitor used to keep track of progress
     * @throws UnsupportedGraphException if the given KGraph is not supported by
     *     this algorithm
     */
    public abstract void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor);
    
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
     * Sets the current debug canvas. Should not be used by subclasses.
     * 
     * @param thedebugCanvas the debug canvas
     */
    public final void setDebugCanvas(final IDebugCanvas thedebugCanvas) {
        this.debugCanvas = thedebugCanvas;
    }

    /**
     * Returns the current debug canvas. A debug canvas can be used to draw something onto the
     * diagram that is layouted in order to analyze and debug the layout algorithm code.
     * {@link IDebugCanvas#setOffset(KNode, float, float) should be set before anything is
     * drawn.
     * 
     * @return the debug canvas
     */
    public final IDebugCanvas getDebugCanvas() {
        return debugCanvas;
    }

}
