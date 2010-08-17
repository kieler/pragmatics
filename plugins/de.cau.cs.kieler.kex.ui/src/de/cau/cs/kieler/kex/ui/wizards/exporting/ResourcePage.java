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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.ide.dialogs.ResourceTreeAndListGroup;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import de.cau.cs.kieler.kex.model.ExportResource;

@SuppressWarnings("restriction")
public class ResourcePage extends WizardPage {

	private Combo headFileCombo;

	private ResourceTreeAndListGroup resourceGroup;

	private final List<IProject> exportedProjects;
	private final List<IFolder> exportedFolders;

	private boolean insertHiddenFiles;

	private final List<ExportResource> exportResources;

	protected ResourcePage(String pageName) {
		super(pageName);
		this.setTitle("Example Resources");
		this.setDescription("Choose resources to export");
		this.exportResources = new ArrayList<ExportResource>();
		exportedFolders = new ArrayList<IFolder>();
		exportedProjects = new ArrayList<IProject>();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setFont(parent.getFont());
		createResourcesGroup(composite);
		createHeadFileComposite(composite);
		setControl(composite);
	}

	private void createHeadFileComposite(Composite composite) {
		Composite headResourceComposite = new Composite(composite, SWT.BORDER);
		GridLayout data = new GridLayout();
		data.numColumns = 2;
		headResourceComposite.setLayout(data);
		headResourceComposite.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		new Label(headResourceComposite, SWT.NONE).setText("Head File:");
		this.headFileCombo = new Combo(headResourceComposite, SWT.READ_ONLY);
		this.headFileCombo
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.headFileCombo
				.setToolTipText("Choose one of selected files to make it the headfile.");
		headFileCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// e.data;
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

				fillHeadFileCombo();
			}

		});

	}

	protected void fillHeadFileCombo() {
		@SuppressWarnings("unchecked")
		List<IFile> allCheckedListItems = this.resourceGroup
				.getAllCheckedListItems();
		int size = allCheckedListItems.size();
		String[] items = new String[size];
		for (int i = 0; i < size; i++) {
			Object object = allCheckedListItems.get(i);
			if (object instanceof IFile) {
				items[i] = ((IFile) object).getFullPath().toString();
			}
		}
		headFileCombo.setItems(items);
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

	public IPath getHeadFile() {
		return null;
	}

	// TODO validier mechanismen schon bei umschlagen auf den neue page
	// abprüfen,
	// im wizard, nicht erst bei finish.

	@Override
	public boolean isPageComplete() {
		return true;
	}

	public void buildResourceStructure() {
		List<IResource> duplicateChecker = new ArrayList<IResource>();
		for (IProject iProject : getExportedProjects()) {
			try {
				for (IResource resource : iProject.members()) {
					if (resource instanceof IFolder) {
						addFolderWithElements((IContainer) resource,
								(IContainer) resource, duplicateChecker);
					}
					if (resource instanceof IFile) {
						if (!insertHiddenFiles
								&& !resource.getName().startsWith(".")) {
							if (!duplicateChecker.contains(resource)) {
								IPath fileRootPath = makeRelativePath(iProject,
										resource);
								this.exportResources.add(new ExportResource(
										resource, fileRootPath));
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
				addFolderWithElements(folder, folder, duplicateChecker);
			}
		}
		for (IFile file : getExportedFiles()) {
			if (!duplicateChecker.contains(file)) {

				IPath fileRootPath = filterResourceName(file);
				this.exportResources
						.add(new ExportResource(file, fileRootPath));
				duplicateChecker.add(file);
			}
		}
	}

	public List<ExportResource> getExportedResources() {
		return this.exportResources;
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
	 * @param duplicateChecker
	 */
	private void addFolderWithElements(IContainer resource, IContainer root,
			List<IResource> duplicateChecker) {
		IPath rootPath = makeRelativePath(root, resource);
		this.exportResources.add(new ExportResource(resource, rootPath));
		duplicateChecker.add(resource);
		try {
			for (IResource element : resource.members()) {
				if (element instanceof IFolder) {
					addFolderWithElements((IContainer) element, root,
							duplicateChecker);
				} else {
					IPath fileRootPath = makeRelativePath(root, element);
					this.exportResources.add(new ExportResource(element,
							fileRootPath));
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
