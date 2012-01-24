/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.diagram;

import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;

/**
 * Interface for managers of diagram layout.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @param <T> the type of diagram part that is handled by this diagram layout manager
 * @author msp
 */
public interface IDiagramLayoutManager<T> {

    /**
     * Determine whether this layout manager is able to perform layout for the given object.
     * 
     * @param object a workbench part or edit part
     * @return true if this layout manager supports the object
     */
    boolean supports(Object object);

    /**
     * Build a KGraph instance for the given diagram. The resulting layout graph should reflect
     * the structure of the original diagram.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent object for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @return a layout graph mapping
     */
    LayoutMapping<T> buildLayoutGraph(IWorkbenchPart workbenchPart,
            Object diagramPart);

    /**
     * Apply the computed layout back to the diagram.
     * 
     * @param mapping a layout mapping that was created by this layout manager
     * @param zoomToFit whether the diagram should zoom to fit
     * @param animationTime the animation time in milliseconds, or 0 for no animation
     */
    void applyLayout(LayoutMapping<T> mapping, boolean zoomToFit,
            int animationTime);
    
    /**
     * Undo the layout in the original diagram (optional operation).
     * 
     * @param mapping a layout mapping that was created by this layout manager
     */
    void undoLayout(LayoutMapping<T> mapping);
    
    /**
     * Return a framework-specific layout configuration.
     * 
     * @return a layout configuration for this diagram layout manager
     */
    IMutableLayoutConfig getLayoutConfig();

}
