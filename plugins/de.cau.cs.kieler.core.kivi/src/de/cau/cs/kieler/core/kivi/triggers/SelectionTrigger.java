/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.triggers;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
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
 * @author chsch
 */
public class SelectionTrigger extends AbstractTrigger implements ISelectionListener {

    /**
     * Remember old selection to avoid triggering KiVi every time the user clicks on the same
     * element.
     */
    private ISelection oldSelection;

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
        if (!s.equals(oldSelection)) {
            trigger(new SelectionState(s, p));
        }
// chsch: old implementation:
//        if (s instanceof IStructuredSelection) {
//            IStructuredSelection selection = (IStructuredSelection) s;
//            List<?> newSelection = selection.toList();
//
//            // make sure to trigger only if selection has changed
//            if (!newSelection.equals(oldSelection)) {
//                oldSelection = newSelection;
//                trigger(new SelectionState(new ArrayList<Object>(newSelection), p));
//            }
//        }
    }

    /**
     * The selection trigger state.
     */
    public static final class SelectionState extends AbstractTriggerState {

        /** The editor or view in which a selection has been done. */
        private final IWorkbenchPart workbenchPart;

        /** The selection to be kept. */
        private final ISelection selection;
        
        /**
         * Default constructor.
         */
        public SelectionState() {
            selection = null;
            workbenchPart = null;
        }

        /**
         * Create a new selection state.
         * 
         * @param list
         *            the selected objects
         * @param e
         *            the diagram editor
         */
        private SelectionState(final ISelection s, final IWorkbenchPart e) {
            selection = s;
            workbenchPart = e;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return SelectionTrigger.class;
        }

        /**
         * Provides the current {@link ISelection}.
         * 
         * @return the current {@link ISelection}
         */
        public ISelection getSelection() {
            return selection;
        }

        /**
         * Provides the current selection if it is an {@link IStructuredSelection}.
         * 
         * @return the current {@link IStructuredSelection} or {@link StructuredSelection#EMPTY} if
         *         the current selection is not an {@link IStructuredSelection}
         */
        public IStructuredSelection getStructuredSelection() {
            if (selection instanceof IStructuredSelection) {
                return (IStructuredSelection) selection;
            } else {
                return StructuredSelection.EMPTY;
            }
        }

        /**
         * Get the selected Objects.
         * 
         * @return a list of the selected objects or {@link Collections#emptyList()} if the current
         *         selection is not an {@link IStructuredSelection}
         */
        public List<?> getSelectionElements() {
            if (selection instanceof IStructuredSelection) {
                return ((IStructuredSelection) selection).toList();
            } else {
                return Collections.emptyList();
            }
        }

        /**
         * Provides the current selection.
         * 
         * @param <T>
         *            the type of the expected selection
         * @param type
         *            the type of the expected selection
         * @return the desired selection of type T or <code>null</code> if the current selection is
         *         not of type T
         */
        public <T extends ISelection> T getSelection(final Class<T> type) {
            if (type.isInstance(selection)) {
                @SuppressWarnings("unchecked")
                final T result = (T) selection;
                return result; 
            } else {
                return null;
            }
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
