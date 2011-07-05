/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

/**
 * An interface for events from viewers of type {@code IViewer}. See the sub-package {@code events}
 * for the implementations of this interface.
 * 
 * @author mri
 */
public interface IViewerEvent {

    /**
     * Returns the viewer which is the source of this event.
     * 
     * @return the source viewer
     */
    IViewer<?> getSource();
    
}
