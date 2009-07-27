package de.cau.cs.kieler.ksbase.generator.popup.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.WorkflowContextDefaultImpl;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.issues.IssuesImpl;
import org.eclipse.emf.mwe.core.issues.MWEDiagnostic;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.ksbase.generator.utils.Utils;

public class GenerateFeatures implements IObjectActionDelegate {

    //private Shell shell;
    private String file;

    /**
     * Constructor for Action1.
     */
    public GenerateFeatures() {
        super();
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        //shell = targetPart.getSite().getShell();
    }

    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action) {
        if (file.length() > 0) {
            IProject project = null;
            IProgressMonitor nullMonitor = new org.eclipse.core.runtime.NullProgressMonitor();
            NullProgressMonitor monitor = new NullProgressMonitor();
            try {
                // Create project:
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                project = root.getProject(Utils.targetProjectName);
                project.create( nullMonitor );
                project.open( nullMonitor );
                IProjectDescription descr = project.getDescription();
                descr.setNatureIds( new String[] {JavaCore.NATURE_ID, JavaCore.PLUGIN_ID});
                project.setDescription(descr, nullMonitor);
            } catch (CoreException e) {
                //Project already exists
                //May need a switch betw. project.create & project.open exceptions
            }
                KielerWorkflow workflow = new KielerWorkflow(file,
                        ResourcesPlugin.getWorkspace().getRoot().getLocation()
                                .toOSString()
                                + "/" + Utils.targetProjectName);

                WorkflowContext wfx = new WorkflowContextDefaultImpl();
                Issues issues = new IssuesImpl();

                try {
                    workflow.invoke(wfx, monitor, issues);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (issues.hasErrors()) {
                    for (MWEDiagnostic warning : issues.getWarnings())
                        System.err.println(warning.getMessage());
                    for (MWEDiagnostic error : issues.getErrors())
                        System.err.println(error.getMessage());
                } else {
                }
                try {
                project.refreshLocal( IResource.DEPTH_INFINITE, nullMonitor);
                }
                catch (CoreException e) {
                    System.err.println("Error during refresh");
                }
        }
    }
    
    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof TreeSelection) {
            Object selected = ((TreeSelection) selection).getFirstElement();
            if (selected instanceof IFile) {
                Utils.sourceFilePath = ((IFile) selected).getFullPath()
                        .toOSString();
                Utils.projectName = ((IFile) selected).getProject().getName();
                Utils.targetProjectName = Utils.projectName
                        + ".diagram.features";
                // platform:/resource/test/default.mealy
                file = "platform:/resource"
                        + ((IFile) selected).getFullPath().toString();
            }
        }
    }

}
