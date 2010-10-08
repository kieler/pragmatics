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
package de.cau.cs.kieler.core.kivi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.ui.util.EditorUtils;

/**
 * Listens for selection and deselection of graphical elements.
 * 
 * @author mmu
 * 
 */
public class SelectionTrigger extends AbstractTrigger implements ISelectionListener {

    /**
     * Create a new SelectionTrigger.
     */
    public SelectionTrigger() {

    }

    /**
     * {@inheritDoc}
     */
    public void register() {
        final ISelectionListener isl = this;
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
                        .addSelectionListener(isl);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void unregister() {
        final ISelectionListener isl = this;
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
                        .removeSelectionListener(isl);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final IWorkbenchPart p, final ISelection s) {
        if (p instanceof DiagramEditor && s instanceof IStructuredSelection) {
            List<EObject> list = new ArrayList<EObject>();
            IStructuredSelection selection = (IStructuredSelection) s;
            for (Object o : selection.toList()) {
                if (o instanceof EditPart) {
                    list.add(((View) ((EditPart) o).getModel()).getElement());
                }
            }
            trigger(new SelectionState(list, (DiagramEditor) p));
        }
    }

    /**
     * Contains the currently selected EObjects.
     * 
     * @author mmu
     * 
     */
    public static final class SelectionState extends AbstractTriggerState {

        private List<EObject> objects;

        private DiagramEditor editor;

        /**
         * Default constructor.
         */
        public SelectionState() {

        }

        /**
         * Create a new selection state.
         * 
         * @param list
         *            the selected objects
         * @param e
         *            the diagram editor
         */
        private SelectionState(final List<EObject> list, final DiagramEditor e) {
            objects = list;
            editor = e;
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
        public List<EObject> getSelectedEObjects() {
            if (objects != null) {
                return objects;
            } else {
                return new ArrayList<EObject>();
            }
        }

        /**
         * Get the editor that contains the selection.
         * 
         * @return the DiagramEditor
         */
        public DiagramEditor getDiagramEditor() {
            // return active editor as default
            if (editor != null) {
                return editor;
            } else {
                IEditorPart editorPart = EditorUtils.getLastActiveEditor();
                if (editorPart instanceof DiagramEditor) {
                    return (DiagramEditor) editorPart;
                } else {
                    return null;
                }
            }
        }
    }

}
