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
package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.ide.dialogs.ResourceTreeAndListGroup;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kex.controller.ExportResource;
import de.cau.cs.kieler.kex.ui.KEXUIPlugin;

/**
 * The examples resource page manages the resources for an export.
 * 
 * @author pkl
 * 
 */
public class ExampleResourcesPage extends WizardPage {

    private static final int MIN_WIDTH = 540;
    private static final int MIN_HEIGHT = 600;
    private static final int VERTICAL_INDENT = 10;

    // preferred height
    private static final int HEIGHT_HINT = 80;

    private Tree directOpenTree;

    @SuppressWarnings("restriction")
    private ResourceTreeAndListGroup resourceGroup;

    private final List<IProject> exportedProjects;
    private final List<IFolder> exportedFolders;

    private final List<ExportResource> exportResources;

    /**
     * Constructor of {@link ExampleResourcesPage}.
     * 
     * @param pageName
     *            , {@link String}
     * @param selection
     *            , {@link IStructuredSelection}
     */
    protected ExampleResourcesPage(final String pageName, final IStructuredSelection selection) {
        super(pageName);
        this.setTitle(pageName);
        this.setDescription("Choose resources to export and set direct opens.");
        this.exportResources = new ArrayList<ExportResource>();
        exportedFolders = new ArrayList<IFolder>();
        exportedProjects = new ArrayList<IProject>();
    }

    /**
     * Creates a new composite and adds it to parent composite. Besides to that a group with
     * resources and and composite with directopens will create.
     * 
     * @param parent
     *            , {@link Composite}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parent.getFont());
        createResourcesGroup(composite);
        createDirectOpenComposite(composite);
        setControl(composite);
        getShell().setMinimumSize(MIN_WIDTH, MIN_HEIGHT);
    }

    private void createDirectOpenComposite(final Composite composite) {
        Label directOpenDesc = new Label(composite, SWT.NONE);
        GridData directOpenDescLayoutData = new GridData();
        directOpenDescLayoutData.verticalIndent = VERTICAL_INDENT;
        directOpenDesc.setLayoutData(directOpenDescLayoutData);
        directOpenDesc.setText("Select files, that should "
                + "open directly when importing that example.");
        this.directOpenTree = new Tree(composite, SWT.CHECK | SWT.BORDER | SWT.H_SCROLL
                | SWT.V_SCROLL);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = HEIGHT_HINT;
        directOpenTree.setLayoutData(gridData);
        fillDirectOpenTree();
    }

    /**
     * Converts the projects of workspace to input elements for {@link ResourceTreeAndListGroup} and
     * initializes that.
     * 
     * @param parent
     *            , Composite
     */
    @SuppressWarnings("restriction")
    protected final void createResourcesGroup(final Composite parent) {
        List<Object> input = new ArrayList<Object>();
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].isOpen()) {
                input.add(projects[i]);
            }
        }
        Label resourceDesc = new Label(parent, SWT.NONE);
        resourceDesc.setText("Choose resources to export:");
        initResourceGroup(parent, input);
    }

    /**
     * Initializes {@link ResourceTreeAndListGroup}, that means creating two tree one for projects
     * and folders and another one for the containing files.
     * 
     * @param parent
     *            , Composite
     * @param input
     *            , input elements for {@link ResourceTreeAndListGroup}
     */
    @SuppressWarnings("restriction")
    private void initResourceGroup(final Composite parent, final List<Object> input) {
        this.resourceGroup = new ResourceTreeAndListGroup(parent, input,
                getResourceProvider(IResource.FOLDER),
                WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
                getResourceProvider(IResource.FILE),
                WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(), SWT.NONE,
                DialogUtil.inRegularFontMode(parent));
        this.resourceGroup.addCheckStateListener(new ICheckStateListener() {

            public void checkStateChanged(final CheckStateChangedEvent event) {
                Object element = event.getElement();
                if (element instanceof IFolder) {
                    if (event.getChecked()) {
                        getExportedFolders().add((IFolder) element);
                    } else {
                        getExportedFolders().remove(element);
                    }
                }
                if (element instanceof IProject) {
                    if (event.getChecked()) {
                        getExportedProjects().add((IProject) element);
                    } else {
                        getExportedProjects().remove(element);
                    }
                }
                // TODO update tree muss implementiert werden, nicht immer alles
                // neu machen...
                fillDirectOpenTree();
            }

        });

    }

    private void fillDirectOpenTree() {

        this.directOpenTree.removeAll();

        @SuppressWarnings({ "unchecked", "restriction" })
        List<IFile> allCheckedListItems = this.resourceGroup.getAllCheckedListItems();
        int size = allCheckedListItems.size();
        for (int i = 0; i < size; i++) {
            Object object = allCheckedListItems.get(i);
            if (object instanceof IFile) {
                TreeItem item = new TreeItem(this.directOpenTree, SWT.CHECK);
                item.setText(((IFile) object).getFullPath().toString());
            }
        }

    }

    /**
     * Returns a content provider for {@link IResource}s that returns only children of the given
     * resource type.
     */
    private ITreeContentProvider getResourceProvider(final int resourceType) {
        return new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(final Object param) {
                if (param instanceof IContainer) {
                    IResource[] members = null;
                    try {
                        members = ((IContainer) param).members();
                    } catch (CoreException e) {
                        return new Object[0];
                    }

                    ArrayList<IResource> results = new ArrayList<IResource>();
                    for (int i = 0; i < members.length; i++) {
                        if ((members[i].getType() & resourceType) > 0
                                && !members[i].getName().startsWith(".")) {
                            results.add(members[i]);
                        }
                    }
                    return results.toArray();
                }
                if (param instanceof ArrayList<?>) {
                    return ((ArrayList<?>) param).toArray();
                }
                return new Object[0];
            }
        };
    }

    /**
     * Gives all files which are selected in the tree.
     * 
     * @return {@link List} of {@link IFile}s
     */
    @SuppressWarnings({ "restriction", "unchecked" })
    public List<IFile> getExportedFiles() {
        return resourceGroup.getAllCheckedListItems();
    }

    /**
     * Gives all projects which are selected in the tree.
     * 
     * @return
     */
    List<IProject> getExportedProjects() {
        return this.exportedProjects;
    }

    /**
     * Gives all folders which are selected in the tree.
     * 
     * @return {@link List} of {@link IFolder}s
     */
    public List<IFolder> getExportedFolders() {
        return this.exportedFolders;
    }

    /**
     * getter for head file.
     * 
     * @return {@link IPath}
     */
    public IPath getHeadFile() {
        return null;
    }

    @Override
    public boolean isPageComplete() {
        return true;
    }

    /**
     * builds the resource structure out of the choosen komponents of the resource tree. The
     * exportResources will be generated from the exported projects, exported directories and export
     * files.
     */
    public void buildResourceStructure() {
        List<IResource> duplicateChecker = new ArrayList<IResource>();
        for (IProject iProject : getExportedProjects()) {
            try {
                this.exportResources.add(new ExportResource(iProject, makeRelativePath(iProject,
                        iProject)));
                for (IResource resource : iProject.members()) {
                    if (checkHiddenResource(resource)) {
                        continue;
                    }
                    if (resource instanceof IFolder) {
                        addFolderWithElements((IContainer) resource, (IContainer) iProject,
                                duplicateChecker);
                    }
                    if (resource instanceof IFile) {
                        if (!duplicateChecker.contains(resource)) {
                            IPath fileRootPath = makeRelativePath(iProject, resource);
                            this.exportResources.add(new ExportResource(resource, fileRootPath));
                            duplicateChecker.add(resource);
                        }
                    }
                }

            } catch (CoreException e) {
                StatusManager.getManager()
                        .handle(new Status(IStatus.ERROR, KEXUIPlugin.PLUGIN_ID, e
                                .getLocalizedMessage(), e));
            }
        }
        for (IContainer folder : getExportedFolders()) {
            if (checkHiddenResource(folder)) {
                continue;
            }
            if (!duplicateChecker.contains(folder)) {
                addFolderWithElements(folder, folder, duplicateChecker);
            }
        }
        for (IFile file : getExportedFiles()) {
            if (checkHiddenResource(file)) {
                continue;
            }
            if (!duplicateChecker.contains(file)) {

                IPath fileRootPath = filterResourceName(file);
                this.exportResources.add(new ExportResource(file, fileRootPath));
                duplicateChecker.add(file);
            }
        }
        setDirectOpens();
    }

    /**
     * getter for exported resources.
     * 
     * @return {@link List} of {@link ExportResource}s
     */
    public List<ExportResource> getExportedResources() {
        return this.exportResources;
    }

    private boolean checkHiddenResource(final IResource resource) {
        return resource.getName().startsWith(".");
    }

    /**
     * Adds the path of given folder resource to a given list of {@link IPath}.<br>
     * Filters than all member resources of that folder and uses {@code makeRelativePath()}<br>
     * to create paths which will be added to result.<br>
     * All resource will be add to duplicateChecker list.
     * 
     * @param resource
     * @param resourcePath
     * @param duplicateChecker
     */
    private void addFolderWithElements(final IContainer resource, final IContainer root,
            final List<IResource> duplicateChecker) {
        IPath rootPath = makeRelativePath(root, resource);
        this.exportResources.add(new ExportResource(resource, rootPath));
        duplicateChecker.add(resource);
        try {
            for (IResource element : resource.members()) {
                if (checkHiddenResource(element)) {
                    continue;
                }
                if (element instanceof IFolder) {
                    addFolderWithElements((IContainer) element, root, duplicateChecker);
                } else {
                    IPath fileRootPath = makeRelativePath(root, element);
                    this.exportResources.add(new ExportResource(element, fileRootPath));
                    duplicateChecker.add(element);
                }
            }
        } catch (CoreException e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KEXUIPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
        }
    }

    /**
     * Uses path child resource to create a relative path to parent resource (including parent
     * resource name).
     * 
     * @param parent
     * @param child
     * @return
     */
    private IPath makeRelativePath(final IResource parent, final IResource child) {
        IPath relativePath = child.getFullPath().makeRelativeTo(
                parent.getFullPath().removeLastSegments(1));
        return relativePath;
    }

    private IPath filterResourceName(final IResource resource) {
        IPath resourcePath = resource.getFullPath();
        return resourcePath.removeFirstSegments(resourcePath.segmentCount() - 1);
    }

    private void setDirectOpens() {
        for (TreeItem item : this.directOpenTree.getItems()) {
            if (item.getChecked()) {
                String localPath = item.getText();
                for (ExportResource resource : exportResources) {
                    if (localPath.equals(resource.getResource().getFullPath().toString())) {
                        resource.setDirectOpen(true);
                        break;
                    }
                }
            }
        }
    }
}
