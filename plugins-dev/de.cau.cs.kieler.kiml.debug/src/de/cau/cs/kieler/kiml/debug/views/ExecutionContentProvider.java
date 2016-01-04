/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug.views;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;

/**
 * Content provider for KIELER progress monitors.
 * 
 * @author msp
 */
public class ExecutionContentProvider implements ITreeContentProvider {

    /**
     * {@inheritDoc}
     */
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof IKielerProgressMonitor) {
            return ((IKielerProgressMonitor) parentElement).getSubMonitors().toArray();
        }
        return new IKielerProgressMonitor[0];
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(final Object element) {
        if (element instanceof IKielerProgressMonitor) {
            return ((IKielerProgressMonitor) element).getParentMonitor();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasChildren(final Object element) {
        return element instanceof IKielerProgressMonitor
                && !((IKielerProgressMonitor) element).getSubMonitors().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof List<?>) {
            return ((List<?>) inputElement).toArray();
        }
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
