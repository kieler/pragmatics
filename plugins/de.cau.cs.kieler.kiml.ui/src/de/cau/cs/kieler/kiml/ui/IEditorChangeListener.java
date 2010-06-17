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
package de.cau.cs.kieler.kiml.ui;

import org.eclipse.jface.viewers.ISelectionChangedListener;

/**
 * Listener for change of the active editor or active selection.
 * 
 * @author jjc
 */
public interface IEditorChangeListener extends ISelectionChangedListener {

    /**
     * Fired when the active editor has changed so that any views on the current
     * editor must be refreshed.
     */
    void editorChanged();

}
