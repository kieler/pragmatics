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
package de.cau.cs.kieler.ksbase.ui.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import de.cau.cs.kieler.core.model.util.ModelingUtil;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;

/**
 * Utilities that may be called from Xtend.
 * 
 * @author mim
 * 
 */
public class TransformationUtils implements ITransformationEventListener {
    /** The object to select after the transformation has been executed. **/
    private static volatile EObject selection = null;;

    /**
     * Sets the object that should be selected after the transformation has been
     * executed.
     * 
     * @param e
     *            The object to select
     */
    public static void setPostTransformationSelection(final Object e) {
        TransformationUtils.selection = (EObject) e;
    }

    /**
     * Pre-transformation code, empty right now.
     * 
     * @param args
     *            Empty arguments.
     */
    public void transformationAboutToExecute(final Object[] args) {
        // nothing to do here.
    }

    /**
     * Sets the diagram editor selection to the selection object.
     * 
     * @param args
     *            The transformation arguments, see
     *            {@link TransformationUIManager}
     * 
     */
    public void transformationExecuted(final Object[] args) {
        if (selection != null) {
            if (args != null && args.length == 2
                    && args[1] instanceof IEditorPart) {
                IEditorPart activeEditor = (IEditorPart) args[1];
                if (activeEditor instanceof IDiagramWorkbenchPart) {
                    EditPart p = ModelingUtil.getEditPart(selection,
                            ((IDiagramWorkbenchPart) activeEditor)
                                    .getDiagramGraphicalViewer()
                                    .getRootEditPart());
                    if (p != null) {
                        ((IEditorPart) args[1]).getEditorSite()
                                .getSelectionProvider().setSelection(
                                        new StructuredSelection(p));
                    }
                }
            }
            // Clear selection so we don't automatically select this object
            // again.
            selection = null;
        }
    }

    /**
     * Get the file name of the currently active editor.
     * 
     * @return the file name.
     */
    public String getFileNameOfActiveEditor() {
        String result = "SyncChart";
        IEditorPart editor = getActiveEditor();
        if (editor != null) {
            IEditorInput iInput = editor.getEditorInput();
            if (iInput instanceof IFileEditorInput) {
                IFileEditorInput input = (IFileEditorInput) iInput;
                IFile file = input.getFile();
                if (file != null) {
                    String name = file.getName();
                    result = name.replace(".kids", "");
                }
            }
        }
        return result;
    }

    /**
     * Get the active editor for the page.
     * 
     * @return the active editor.
     */
    private IEditorPart getActiveEditor() {
        IEditorPart result = null;
        IWorkbench workbench = KSBasEUIPlugin.getDefault().getWorkbench();
        if (workbench != null) {
            IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
            if (window != null) {
                IWorkbenchPage page = window.getActivePage();
                if (page != null) {
                    result = page.getActiveEditor();
                }
            }
        }
        return result;
    }
}
