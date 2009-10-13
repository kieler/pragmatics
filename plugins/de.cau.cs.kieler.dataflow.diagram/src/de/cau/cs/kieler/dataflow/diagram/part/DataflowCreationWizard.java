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
package de.cau.cs.kieler.dataflow.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @generated
 */
public class DataflowCreationWizard extends Wizard implements INewWizard {

    /**
     * @generated
     */
    private IWorkbench workbench;

    /**
     * @generated
     */
    protected IStructuredSelection selection;

    /**
     * @generated
     */
    protected DataflowCreationWizardPage diagramModelFilePage;

    /**
     * @generated
     */
    protected DataflowCreationWizardPage domainModelFilePage;

    /**
     * @generated
     */
    protected Resource diagram;

    /**
     * @generated
     */
    private boolean openNewlyCreatedDiagramEditor = true;

    /**
     * @generated
     */
    public IWorkbench getWorkbench() {
        return workbench;
    }

    /**
     * @generated
     */
    public IStructuredSelection getSelection() {
        return selection;
    }

    /**
     * @generated
     */
    public final Resource getDiagram() {
        return diagram;
    }

    /**
     * @generated
     */
    public final boolean isOpenNewlyCreatedDiagramEditor() {
        return openNewlyCreatedDiagramEditor;
    }

    /**
     * @generated
     */
    public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {
        this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
    }

    /**
     * @generated
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setWindowTitle(Messages.DataflowCreationWizardTitle);
        setDefaultPageImageDescriptor(DataflowDiagramEditorPlugin
                .getBundledImageDescriptor("icons/wizban/NewDataflowWizard.gif")); //$NON-NLS-1$
        setNeedsProgressMonitor(true);
    }

    /**
     * @generated
     */
    public void addPages() {
        diagramModelFilePage = new DataflowCreationWizardPage(
                "DiagramModelFile", getSelection(), "dataflow_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
        diagramModelFilePage.setTitle(Messages.DataflowCreationWizard_DiagramModelFilePageTitle);
        diagramModelFilePage
                .setDescription(Messages.DataflowCreationWizard_DiagramModelFilePageDescription);
        addPage(diagramModelFilePage);

        domainModelFilePage = new DataflowCreationWizardPage(
                "DomainModelFile", getSelection(), "dataflow") { //$NON-NLS-1$ //$NON-NLS-2$

            public void setVisible(boolean visible) {
                if (visible) {
                    String fileName = diagramModelFilePage.getFileName();
                    fileName = fileName.substring(0, fileName.length()
                            - ".dataflow_diagram".length()); //$NON-NLS-1$
                    setFileName(DataflowDiagramEditorUtil.getUniqueFileName(getContainerFullPath(),
                            fileName, "dataflow")); //$NON-NLS-1$
                }
                super.setVisible(visible);
            }
        };
        domainModelFilePage.setTitle(Messages.DataflowCreationWizard_DomainModelFilePageTitle);
        domainModelFilePage
                .setDescription(Messages.DataflowCreationWizard_DomainModelFilePageDescription);
        addPage(domainModelFilePage);
    }

    /**
     * @generated
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            protected void execute(IProgressMonitor monitor) throws CoreException,
                    InterruptedException {
                diagram = DataflowDiagramEditorUtil.createDiagram(diagramModelFilePage.getURI(),
                        domainModelFilePage.getURI(), monitor);
                if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
                    try {
                        DataflowDiagramEditorUtil.openDiagram(diagram);
                    }
                    catch (PartInitException e) {
                        ErrorDialog
                                .openError(getContainer().getShell(),
                                        Messages.DataflowCreationWizardOpenEditorError, null, e
                                                .getStatus());
                    }
                }
            }
        };
        try {
            getContainer().run(false, true, op);
        }
        catch (InterruptedException e) {
            return false;
        }
        catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(),
                        Messages.DataflowCreationWizardCreationError, null, ((CoreException) e
                                .getTargetException()).getStatus());
            }
            else {
                DataflowDiagramEditorPlugin.getInstance().logError(
                        "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
            }
            return false;
        }
        return diagram != null;
    }
}
