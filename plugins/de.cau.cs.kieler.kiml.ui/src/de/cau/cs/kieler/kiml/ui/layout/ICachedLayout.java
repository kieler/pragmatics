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
package de.cau.cs.kieler.kiml.ui.layout;

import org.eclipse.ui.IWorkbenchPart;

/**
 * Interface for cached layouts that can be applied repeatedly.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public interface ICachedLayout {
    
    /**
     * Applies the cached layout to the given editor part.
     * 
     * @param workbenchPart a diagram workbench part
     * @param animate if true, Draw2D animation is activated
     */
    void applyLayout(IWorkbenchPart workbenchPart, boolean animate);
    
    /**
     * Applies the cached layout to the given editor part.
     * 
     * @param workbenchPart a diagram workbench part
     */
    void applyLayout(IWorkbenchPart workbenchPart);

}
