package de.cau.cs.kieler.graphs.diagram.part;

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
public class GraphsCreationWizard extends Wizard implements INewWizard {

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
    protected GraphsCreationWizardPage diagramModelFilePage;

    /**
     * @generated
     */
    protected GraphsCreationWizardPage domainModelFilePage;

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
        setWindowTitle(Messages.GraphsCreationWizardTitle);
        setDefaultPageImageDescriptor(GraphsDiagramEditorPlugin
                .getBundledImageDescriptor("icons/wizban/NewGraphsWizard.gif")); //$NON-NLS-1$
        setNeedsProgressMonitor(true);
    }

    /**
     * @generated
     */
    public void addPages() {
        diagramModelFilePage = new GraphsCreationWizardPage(
                "DiagramModelFile", getSelection(), "graph_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
        diagramModelFilePage.setTitle(Messages.GraphsCreationWizard_DiagramModelFilePageTitle);
        diagramModelFilePage
                .setDescription(Messages.GraphsCreationWizard_DiagramModelFilePageDescription);
        addPage(diagramModelFilePage);

        domainModelFilePage = new GraphsCreationWizardPage("DomainModelFile", getSelection(), "graph") { //$NON-NLS-1$ //$NON-NLS-2$

            public void setVisible(boolean visible) {
                if (visible) {
                    String fileName = diagramModelFilePage.getFileName();
                    fileName = fileName.substring(0, fileName.length() - ".graph_diagram".length()); //$NON-NLS-1$
                    setFileName(GraphsDiagramEditorUtil.getUniqueFileName(getContainerFullPath(),
                            fileName, "graph")); //$NON-NLS-1$
                }
                super.setVisible(visible);
            }
        };
        domainModelFilePage.setTitle(Messages.GraphsCreationWizard_DomainModelFilePageTitle);
        domainModelFilePage.setDescription(Messages.GraphsCreationWizard_DomainModelFilePageDescription);
        addPage(domainModelFilePage);
    }

    /**
     * @generated
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
                diagram = GraphsDiagramEditorUtil.createDiagram(diagramModelFilePage.getURI(),
                        domainModelFilePage.getURI(), monitor);
                if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
                    try {
                        GraphsDiagramEditorUtil.openDiagram(diagram);
                    } catch (PartInitException e) {
                        ErrorDialog.openError(getContainer().getShell(),
                                Messages.GraphsCreationWizardOpenEditorError, null, e.getStatus());
                    }
                }
            }
        };
        try {
            getContainer().run(false, true, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(),
                        Messages.GraphsCreationWizardCreationError, null, ((CoreException) e
                                .getTargetException()).getStatus());
            } else {
                GraphsDiagramEditorPlugin.getInstance().logError(
                        "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
            }
            return false;
        }
        return diagram != null;
    }
}
