package de.cau.cs.kieler.kaom.diagram.part;

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
public class KaomCreationWizard extends Wizard implements INewWizard {

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
    protected KaomCreationWizardPage diagramModelFilePage;

    /**
     * @generated
     */
    protected KaomCreationWizardPage domainModelFilePage;

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
        setWindowTitle(Messages.KaomCreationWizardTitle);
        setDefaultPageImageDescriptor(KaomDiagramEditorPlugin
                .getBundledImageDescriptor("icons/wizban/NewKaomWizard.gif")); //$NON-NLS-1$
        setNeedsProgressMonitor(true);
    }

    /**
     * @generated
     */
    public void addPages() {
        diagramModelFilePage = new KaomCreationWizardPage(
                "DiagramModelFile", getSelection(), "kaod"); //$NON-NLS-1$ //$NON-NLS-2$
        diagramModelFilePage.setTitle(Messages.KaomCreationWizard_DiagramModelFilePageTitle);
        diagramModelFilePage
                .setDescription(Messages.KaomCreationWizard_DiagramModelFilePageDescription);
        addPage(diagramModelFilePage);

        domainModelFilePage = new KaomCreationWizardPage("DomainModelFile", getSelection(), "kaom") { //$NON-NLS-1$ //$NON-NLS-2$

            public void setVisible(boolean visible) {
                if (visible) {

                    String fileName = diagramModelFilePage.getFileName();
                    if (fileName.endsWith(".kaod")) {
                        fileName = fileName.substring(0, fileName.length() - ".kaod".length()); //$NON-NLS-1$
                    }

                    setFileName(KaomDiagramEditorUtil.getUniqueFileName(getContainerFullPath(),
                            fileName, "kaom")); //$NON-NLS-1$
                }
                super.setVisible(visible);
            }
        };
        domainModelFilePage.setTitle(Messages.KaomCreationWizard_DomainModelFilePageTitle);
        domainModelFilePage
                .setDescription(Messages.KaomCreationWizard_DomainModelFilePageDescription);
        addPage(domainModelFilePage);
    }

    /**
     * @generated
     */
    public boolean performFinish() {

        if (domainModelFilePage.getFileName().matches("default\\d*.\\w*")) {
            String name = diagramModelFilePage.getFileName();
            String domainFileName = name.replace(".kaod", "");
            domainFileName += ".kaom";
            domainModelFilePage.setFileName(domainFileName);
        }

        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            protected void execute(IProgressMonitor monitor) throws CoreException,
                    InterruptedException {
                diagram = KaomDiagramEditorUtil.createDiagram(diagramModelFilePage.getURI(),
                        domainModelFilePage.getURI(), monitor);
                if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
                    try {
                        KaomDiagramEditorUtil.openDiagram(diagram);
                    } catch (PartInitException e) {
                        ErrorDialog.openError(getContainer().getShell(),
                                Messages.KaomCreationWizardOpenEditorError, null, e.getStatus());
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
                        Messages.KaomCreationWizardCreationError, null,
                        ((CoreException) e.getTargetException()).getStatus());
            } else {
                KaomDiagramEditorPlugin.getInstance().logError(
                        "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
            }
            return false;
        }
        return diagram != null;
    }
}
