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
package de.cau.cs.kieler.core.kivi.triggers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kivi.AbstractTrigger;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;

/**
 * Listens for selection of workbench elements.
 * 
 * @author mmu
 * @author msp
 */
public class SelectionTrigger extends AbstractTrigger implements ISelectionListener {

    /**
     * Remember old selection to avoid triggering KiVi every time the user clicks on the same
     * element.
     */
    private List<?> oldSelection;

    /**
     * {@inheritDoc}
     */
    public void register() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                // register with all workbench windows that are currently open
                for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                    window.getSelectionService().addSelectionListener(SelectionTrigger.this);
                }
                // register with all workbench windows that will be open in the future
                PlatformUI.getWorkbench().addWindowListener(new IWindowListener() {
                    public void windowOpened(final IWorkbenchWindow window) {
                        window.getSelectionService().addSelectionListener(SelectionTrigger.this);
                    }
                    public void windowClosed(final IWorkbenchWindow window) {
                    }
                    public void windowActivated(final IWorkbenchWindow window) {
                    }
                    public void windowDeactivated(final IWorkbenchWindow window) {
                    }
                });
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void unregister() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                    window.getSelectionService().removeSelectionListener(SelectionTrigger.this);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final IWorkbenchPart p, final ISelection s) {
        if (s instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) s;
            List<?> newSelection = selection.toList();

            // make sure to trigger only if selection has changed
            if (!newSelection.equals(oldSelection)) {
                oldSelection = newSelection;
                trigger(new SelectionState(new ArrayList<Object>(newSelection), p));
            }
        }
    }
    
    /**
     * The selection trigger state.
     */
    public static final class SelectionState extends AbstractTriggerState {

        /** The editor or view in which a selection has been done. */
        private IWorkbenchPart workbenchPart;

        /** The list of selected objects. */
        private List<Object> objects;
        
        /**
         * Default constructor.
         */
        public SelectionState() {
            objects = Collections.emptyList();
        }

        /**
         * Create a new selection state.
         * 
         * @param list
         *            the selected objects
         * @param e
         *            the diagram editor
         */
        private SelectionState(final List<Object> list, final IWorkbenchPart e) {
            objects = list;
            workbenchPart = e;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return SelectionTrigger.class;
        }
        
        /**
         * Get the selected EObjects.
         * 
         * @return the EObjects
         */
        public List<Object> getSelection() {
            return objects;
        }

        /**
         * Get the editor that contains the selection.
         * 
         * @return the DiagramEditor
         */
        public IWorkbenchPart getWorkbenchPart() {
            return workbenchPart;
        }
    }

}
