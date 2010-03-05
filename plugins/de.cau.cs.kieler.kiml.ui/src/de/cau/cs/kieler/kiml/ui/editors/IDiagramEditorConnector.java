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
package de.cau.cs.kieler.kiml.ui.editors;

import org.eclipse.ui.IEditorPart;

/**
 * 
 * Interface for a connector class mediating between KIML and an unknown editor. Only usable for
 * editors that have a nested editor which is known by KIML, e.g. a GMF editor.
 * 
 * @author jjc
 */
public interface IDiagramEditorConnector {

    /**
     * Get the currently active nested editor.
     * 
     * @param editorPart editor which contains the known nested editor
     * @return the known editor to work with
     */
    IEditorPart getActiveEditor(IEditorPart editorPart);

    /**
     * Register a listener for change of the active editor.
     * 
     * @param editorPart editor to register to
     * @param listener listener to register
     */
    void addChangeListener(IEditorPart editorPart, IEditorChangeListener listener);

    /**
     * Remove a change listener from the last registered editor.
     * 
     * @param listener listener to remove
     */
    void removeChangeListener(IEditorChangeListener listener);

    /**
     * Check whether this connector class can be used for the given editor.
     * 
     * @param editorPart editor which might be connected with this class
     * @return {@code true} if this class can handle the editor
     */
    boolean supports(IEditorPart editorPart);

}
