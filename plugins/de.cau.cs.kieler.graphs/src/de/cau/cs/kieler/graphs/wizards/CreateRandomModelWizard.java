package de.cau.cs.kieler.graphs.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.graphs.Graph;
import de.cau.cs.kieler.graphs.GraphsPlugin;

/**
 * A wizard for random graph models. Its role is to create a new file resource in the provided
 * container. If the container resource (a folder or a project) is selected in the workspace when
 * the wizard is opened, it will accept it as the target container. The wizard creates one file with
 * the extension "graphs".
 * 
 * @author haf
 * @author msp
 */
public class CreateRandomModelWizard extends Wizard implements INewWizard {

    private CreateRandomModelWizardPage page;
    private ISelection selection;

    /**
     * Constructor for CreateRandomModelWizard.
     */
    public CreateRandomModelWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        page = new CreateRandomModelWizardPage(selection);
        addPage(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        page.storeDefaults();
        final String containerName = page.getContainerName();
        final String fileName = page.getFileName();
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(containerName, fileName, monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
        } catch (InterruptedException exception) {
            return false;
        } catch (InvocationTargetException exception) {
            Throwable realException = exception.getTargetException();
            Status status = new Status(IStatus.ERROR, GraphsPlugin.PLUGIN_ID, 0,
                    "Error while creating file.", realException);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            return false;
        }
        return true;
    }

    /**
     * Find the container and create the file if missing or just replace its contents.
     * 
     * @param containerName name of the container project or folder
     * @param fileName file name
     * @param monitor progress monitor
     * @throws IOException if writing the file fails
     * @throws CoreException if 
     */
    private void doFinish(final String containerName, final String fileName,
            final IProgressMonitor monitor) throws IOException, CoreException {
        // create a sample file
        monitor.beginTask("Creating File" + fileName, 2);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            IStatus status = new Status(IStatus.ERROR, GraphsPlugin.PLUGIN_ID, 0,
                    "Container \"" + containerName + "\" does not exist.", null);
            throw new CoreException(status);
        }
        IContainer container = (IContainer) resource;
        final IFile file = container.getFile(new Path(fileName));

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource emfResource = resourceSet.createResource(URI.createURI(
                file.getLocationURI().toString()));
        RandomGraphCreator modelCreator = new RandomGraphCreator();
        Graph graph = modelCreator.createModel(page.getNodes(), page.getMinConnections(),
                page.getMaxConnections(), page.getHierarchyProb());
        emfResource.getContents().add(graph);
        emfResource.save(Collections.EMPTY_MAP);
        file.refreshLocal(1, null);

        monitor.done();
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theselection) {
        this.selection = theselection;
    }

}
