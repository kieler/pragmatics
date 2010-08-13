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
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.ide.dialogs.ResourceTreeAndListGroup;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

@SuppressWarnings("restriction")
public class ResourcePage extends WizardPage {

	private Button headFileButton;

	private Label headFileLabel;

	private ResourceTreeAndListGroup resourceGroup;

	private final List<IProject> exportedProjects;
	private final List<IFolder> exportedFolders;

	private boolean insertHiddenFiles;

	protected ResourcePage(String pageName) {
		super(pageName);
		this.setTitle("Example Resources");
		this.setDescription("Choose resources to export");
		exportedFolders = new ArrayList<IFolder>();
		exportedProjects = new ArrayList<IProject>();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL));
		composite.setFont(parent.getFont());
		createResourcesGroup(composite);
		createHeadResourceGroup(composite);
		setControl(composite);
	}

	private void createHeadResourceGroup(Composite composite) {
		Composite headResourceComposite = new Composite(composite, SWT.BORDER);
		headResourceComposite.setLayout(new GridLayout());
		GridLayout data = new GridLayout();
		data.numColumns = 2;
		this.headFileButton = new Button(composite, SWT.CHECK);
		this.headFileButton.setText("Head File:");
		this.headFileButton
				.setToolTipText("Select one of the resources to make it head_file");
		this.headFileLabel = new Label(composite, SWT.BORDER);
		this.headFileButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO vlt. auch einfach wie destlocation implementieren, da so
				// nicht geht, da der benutzte tree kein selectionlistener
				// annimmt.
			}
		});

	}

	/**
	 * Converts the projects of workspace to input elements for
	 * {@link ResourceTreeAndListGroup} and initializes that.
	 * 
	 * @param parent
	 *            , Composite
	 */
	protected final void createResourcesGroup(Composite parent) {

		List<Object> input = new ArrayList<Object>();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].isOpen()) {
				input.add(projects[i]);
			}
		}
		initResourceGroup(parent, input);
	}

	/**
	 * Initializes {@link ResourceTreeAndListGroup}, that means creating two
	 * tree one for projects and folders and another one for the containing
	 * files.
	 * 
	 * @param parent
	 *            , Composite
	 * @param input
	 *            , input elements for {@link ResourceTreeAndListGroup}
	 */
	private void initResourceGroup(Composite parent, List<Object> input) {

		this.resourceGroup = new ResourceTreeAndListGroup(parent, input,
				getResourceProvider(IResource.FOLDER),
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				getResourceProvider(IResource.FILE),
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				SWT.BORDER, DialogUtil.inRegularFontMode(parent));
		this.resourceGroup.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();
				if (element instanceof IFolder) {
					if (event.getChecked())
						getExportedFolders().add((IFolder) element);
					else
						getExportedFolders().remove(element);
				}
				if (element instanceof IProject) {
					if (event.getChecked())
						getExportedProjects().add((IProject) element);
					else
						getExportedProjects().remove(element);
				}
			}

		});

	}

	/**
	 * Returns a content provider for {@link IResource}s that returns only
	 * children of the given resource type.
	 */
	// TODO verstehen.
	private ITreeContentProvider getResourceProvider(final int resourceType) {
		return new WorkbenchContentProvider() {
			@Override
			public Object[] getChildren(Object o) {
				if (o instanceof IContainer) {
					IResource[] members = null;
					try {
						members = ((IContainer) o).members();
					} catch (CoreException e) {
						// just return an empty set of children
						return new Object[0];
					}

					// filter out the desired resource types
					ArrayList<IResource> results = new ArrayList<IResource>();
					for (int i = 0; i < members.length; i++) {
						// And the test bits with the resource types to see if
						// they are what we want
						if ((members[i].getType() & resourceType) > 0) {
							results.add(members[i]);
						}
					}
					return results.toArray();
				}
				// input element case
				if (o instanceof ArrayList) {
					return ((ArrayList<?>) o).toArray();
				}
				return new Object[0];
			}
		};
	}

	/**
	 * Gives all files which are selected in the tree.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
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
	 * @return
	 */
	public List<IFolder> getExportedFolders() {
		return this.exportedFolders;
	}

	public String getHeadResource() {
		return headFileLabel.getText();
	}

	public List<IPath> getExportedResources() {
		List<IPath> result = new ArrayList<IPath>();
		List<IResource> duplicateChecker = new ArrayList<IResource>();
		for (IProject iProject : getExportedProjects()) {
			try {
				for (IResource resource : iProject.members()) {
					if (resource instanceof IFolder) {
						IPath resourcePath = filterResourceName(resource);
						addFolderWithElements((IContainer) resource,
								(IContainer) resource, result, duplicateChecker);
					}
					if (resource instanceof IFile) {
						if (!insertHiddenFiles
								&& !resource.getName().startsWith(".")) {
							if (!duplicateChecker.contains(resource)) {
								result.add(makeRelativePath(iProject, resource));
								duplicateChecker.add(resource);
							}
						}
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		for (IContainer folder : getExportedFolders()) {
			if (!duplicateChecker.contains(folder)) {
				addFolderWithElements(folder, folder, result, duplicateChecker);
			}
		}
		for (IFile file : getExportedFiles()) {
			if (!duplicateChecker.contains(file)) {
				result.add(filterResourceName(file));
				duplicateChecker.add(file);
			}
		}

		return result;
	}

	/**
	 * Adds the path of given folder resource to a given list of {@link IPath}.<br>
	 * Filters than all member resources of that folder and uses
	 * {@code makeRelativePath()}<br>
	 * to create paths which will be added to result.<br>
	 * All resource will be add to duplicateChecker list.
	 * 
	 * @param resource
	 * @param resourcePath
	 * @param destination
	 * @param duplicateChecker
	 */
	private void addFolderWithElements(IContainer resource, IContainer root,
			List<IPath> destination, List<IResource> duplicateChecker) {
		destination.add(makeRelativePath(root, resource));
		duplicateChecker.add(resource);
		try {
			for (IResource element : resource.members()) {
				if (element instanceof IFolder) {
					addFolderWithElements((IContainer) element, root,
							destination, duplicateChecker);
				} else {
					destination.add(makeRelativePath(root, element));
					duplicateChecker.add(element);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uses path child resource to create a relative path to parent resource
	 * (including parent resource name).
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	private IPath makeRelativePath(IResource parent, IResource child) {
		IPath relativePath = child.getFullPath().makeRelativeTo(
				parent.getFullPath().removeLastSegments(1));
		return relativePath;
	}

	private IPath filterResourceName(IResource resource) {
		IPath resourcePath = resource.getFullPath();
		return resourcePath
				.removeFirstSegments(resourcePath.segmentCount() - 1);
	}
}
