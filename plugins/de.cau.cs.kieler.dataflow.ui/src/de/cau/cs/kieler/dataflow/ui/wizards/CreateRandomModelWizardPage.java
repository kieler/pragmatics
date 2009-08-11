package de.cau.cs.kieler.dataflow.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import de.cau.cs.kieler.dataflow.ui.Activator;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (dataflow).
 * 
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public class CreateRandomModelWizardPage extends WizardPage {

    public static final String PREF_NODES = "nodes";
    public static final String PREF_MIN_CONNECTIONS = "minConnections";
    public static final String PREF_MAX_CONNECTIONS = "maxConnections";
    public static final String PREF_HIERARCHY = "hierarchy";

    public static final int DEF_NODES = 10;
    public static final int DEF_MIN_CONNECTIONS = 0;
    public static final int DEF_MAX_CONNECTIONS = 3;
    public static final float DEF_HIERARCHY = 0.1f;
    
    private Text containerText;
	private Text fileText;
	private Text nodeText;
	private Text minConnectionText;
	private Text maxConnectionText;
	private Text hierarchyText;
	
	private int nodes;
	private int minConnections;
	private int maxConnections;
	private float hierarchyProb;
	
	private ISelection selection;

	

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public CreateRandomModelWizardPage(ISelection selection) {
		super("createRandomModelWizard");
		setTitle("Create Random Model");
		setDescription("This wizard creates a new EMF model of given size with some initial content. It might be used for benchmarking or other things.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
	    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		// Specify number of Nodes
		label = new Label(container, SWT.NULL); // empty label to fill grid
		label = new Label(container, SWT.NULL);
		label.setText("&Number of nodes:");
		
		nodes = preferenceStore.getInt(PREF_NODES);
		if (nodes <= 0)
		    nodes = DEF_NODES;
		nodeText = new Text(container, SWT.BORDER | SWT.SINGLE);
		nodeText.setText(Integer.toString(nodes));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		nodeText.setLayoutData(gd);
		nodeText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		// Specify minimal number of Connections
		label = new Label(container, SWT.NULL); // empty label to fill grid
		label = new Label(container, SWT.NULL);
		label.setText("&Minimal number of outgoing connections per node:");
		
		minConnections = preferenceStore.getInt(PREF_MIN_CONNECTIONS);
		if (minConnections < 0)
		    minConnections = DEF_MIN_CONNECTIONS;
		minConnectionText = new Text(container, SWT.BORDER | SWT.SINGLE);
		minConnectionText.setText(Integer.toString(minConnections));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		minConnectionText.setLayoutData(gd);
		minConnectionText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
	      // Specify number of Connections
        label = new Label(container, SWT.NULL); // empty label to fill grid
        label = new Label(container, SWT.NULL);
        label.setText("Ma&ximal number of outgoing connections per node:");
        
        maxConnections = preferenceStore.getInt(PREF_MAX_CONNECTIONS);
        if (maxConnections < minConnections)
            maxConnections = Math.max(minConnections, DEF_MAX_CONNECTIONS);
        maxConnectionText = new Text(container, SWT.BORDER | SWT.SINGLE);
        maxConnectionText.setText(Integer.toString(maxConnections));
        gd = new GridData(GridData.FILL_HORIZONTAL);
        maxConnectionText.setLayoutData(gd);
        maxConnectionText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });
		
		// Specify probability of hierarchy level introduction
		label = new Label(container, SWT.NULL); // empty label to fill grid
		label = new Label(container, SWT.NULL);
		label.setText("&Probability of introducing new hierarchy levels:");

		hierarchyProb = preferenceStore.getFloat(PREF_HIERARCHY);
		if (hierarchyProb < 0.0 || hierarchyProb > 1.0)
		    hierarchyProb = DEF_HIERARCHY;
		hierarchyText = new Text(container, SWT.BORDER | SWT.SINGLE);
		hierarchyText.setText(Float.toString(hierarchyProb));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		hierarchyText.setLayoutData(gd);
		hierarchyText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText("random.dataflow");
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String fileName = getFileName();

		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("dataflow") == false) {
				updateStatus("File extension must be \"dataflow\"");
				return;
			}
		}
		try{
			nodes = Integer.parseInt(nodeText.getText());
			if (nodes <= 0)
			    throw new NumberFormatException();
		}
		catch( NumberFormatException exc ){
			updateStatus("Number of nodes must be a positive integer!");
			return;
		}
		try{
		    minConnections = Integer.parseInt(minConnectionText.getText());
            if (minConnections < 0)
                throw new NumberFormatException();
		}
		catch( NumberFormatException exc ){
			updateStatus("Minimal number of connections must be a non-negative integer!");
			return;
		}
        try{
              maxConnections = Integer.parseInt(maxConnectionText.getText());
              if (maxConnections < minConnections)
                  throw new NumberFormatException();
        }
        catch( NumberFormatException exc ){
            updateStatus("Maximal number of connections must be an integer greater or equal to the minimal number of connections!");
            return;
        }
		try{
		    float temp = Float.parseFloat(hierarchyText.getText());
			if(temp >= 0 && temp <= 1)
				hierarchyProb = temp;
			else throw new NumberFormatException("must be within 0 and 1");
		}
		catch( NumberFormatException exc ){
			updateStatus("Hierarchy Probability must be a float number in the range from 0.0 to 1.0");
			return;
		}
		
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText();
	}
	
	public int getNodes(){
		return nodes;
	}
	
	public int getMinConnections(){
		return minConnections;
	}
	
	public int getMaxConnections() {
	    return maxConnections;
	}

	public float getHierarchyProb() {
		return hierarchyProb;
	}
	
	/**
	 * Store the current value into the preference store.
	 */
	public void storeDefaults() {
	    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
	    preferenceStore.setValue(PREF_NODES, nodes);
	    preferenceStore.setValue(PREF_MIN_CONNECTIONS, minConnections);
	    preferenceStore.setValue(PREF_MAX_CONNECTIONS, maxConnections);
	    preferenceStore.setValue(PREF_HIERARCHY, hierarchyProb);
	}
}
