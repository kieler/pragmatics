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
package de.cau.cs.kieler.kiml.config;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;

/**
 * Layout option configuration interface.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public interface ILayoutConfig {
    
    /**
     * Return the priority of this layout configuration, which is relevant when multiple configurations
     * are applied.
     * 
     * @return the priority
     */
    int getPriority();
    
    /**
     * Enrich the given context with additional information that can be derived from what is already
     * contained.
     * 
     * @param context a context for layout configuration
     */
    void enrich(LayoutContext context);
    
    /**
     * Get the current value for a layout option in the given context.
     * 
     * @param optionData a layout option descriptor
     * @param context a context for layout configuration
     * @return the layout option value, or {@code null} if the option has no value in this context
     */
    Object getValue(LayoutOptionData<?> optionData, LayoutContext context);
    
    /**
     * Transfer all non-default values that are managed by this layout configuration to the given
     * graph data holder.
     * 
     * @param graphData a graph data instance that can hold layout options
     * @param context a context for layout configuration
     */
    void transferValues(KGraphData graphData, LayoutContext context);

}
