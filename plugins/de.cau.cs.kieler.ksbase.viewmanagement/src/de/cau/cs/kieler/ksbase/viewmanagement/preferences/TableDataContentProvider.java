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

package de.cau.cs.kieler.ksbase.viewmanagement.preferences;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The Class TableDataContentProvider.
 * 
 * @author Christian Motika - cmot AT informatik.uni-kiel.de
 */
public class TableDataContentProvider implements ITreeContentProvider {

    /**
     * Returns the list of Elements for the given input element.
     * 
     * @param inputElement
     *            The input element to evaluate.
     * @return An array of elements.
     */
    public Object[] getElements(final Object inputElement) {
        return ((TableDataList) inputElement).getArray();
    }

    /**
     * Disposes this provider.
     */
    public void dispose() {
    }

    /**
     * Called when the input has changed.
     * 
     * @param viewer
     *            the viewer.
     * @param oldInput
     *            the old input.
     * @param newInput
     *            the new input.
     * 
     */
    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput) {
    }

    /**
     * Gets all children of the given element.
     * 
     * @param parentElement
     *            The element to evaluate.
     * @return An array of children objects.
     */
    public Object[] getChildren(final Object parentElement) {
        return new Object[0];
    }

    /**
     * Gets the parent object of a given element.
     * 
     * @param element
     *            the element to evaluate.
     * @return The parent object if exists.
     */
    public Object getParent(final Object element) {
        return null;
    }

    /**
     * Checks if the given element has children.
     * 
     * @param element The element to evaluate.
     * @return True if this element has childen
     */
    public boolean hasChildren(final Object element) {
        return false;
    }

}
