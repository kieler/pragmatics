package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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

	// workaround to store folder and projects.
	private final List<Object> exportedResouces;

	protected ResourcePage(String pageName) {
		super(pageName);
		exportedResouces = new ArrayList<Object>();
		this.setDescription("Choose resources to export");
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
				getResourceProvider(IResource.FOLDER | IResource.PROJECT),
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				getResourceProvider(IResource.FILE),
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				SWT.BORDER, DialogUtil.inRegularFontMode(parent));
		this.resourceGroup.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();
				if (element instanceof IFolder || element instanceof IProject) {
					if (event.getChecked())
						getExportedResouces().add(element);
					else
						getExportedResouces().remove(element);
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
	public List<File> getExportedFiles() {
		return resourceGroup.getAllCheckedListItems();
	}

	/**
	 * Gives all projects and folders which are selected in the tree.
	 * 
	 * @return
	 */
	public List<Object> getExportedResouces() {
		return exportedResouces;
	}

}
