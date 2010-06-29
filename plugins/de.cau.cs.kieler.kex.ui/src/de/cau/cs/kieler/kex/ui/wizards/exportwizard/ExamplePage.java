package de.cau.cs.kieler.kex.ui.wizards.exportwizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

public class ExamplePage extends WizardPage {

    private WizardResourceImportPage newResourcePage;

    private String projectId;

    private String location;

    private Text id;
    // TODO Textarea waere besser geeignet.
    private Text exampleName;

    private Text exampleDescription;

    private Text version;

    // TODO localprojects ueber den store laden... falls es keinen mechanismus gibt
    public ExamplePage(String pageName, IProject[] localProjects) {
        super(pageName);
        setTitle("Address Information");
        setDescription("Please enter your address information");

    }

    public void createControl(Composite parent) {

        // TODO projectId und location noch zusammenbauen
        // sch�nen mechanismus �berlegen

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        setControl(composite);

        new Label(composite, SWT.NONE).setText("ID");
        setId(new Text(composite, SWT.NONE));

        new Label(composite, SWT.NONE).setText("Name");
        setExampleName(new Text(composite, SWT.NONE));

        Text text = new Text(composite, SWT.NONE);
        text.setText("1.0.0");
        setVersion(text);

        new Label(composite, SWT.NONE).setText("description");
        setExampleDescription(new Text(composite, SWT.NONE));

        // newResourcePage = new ProjectImportPage();

        // Control control = newResourcePage.createControl(parent);
        // getWorkbench().getHelpSystem().setHelp(control,
        // IWorkbenchHelpContextIds.NEW_WIZARD_SELECTION_WIZARD_PAGE);
        // setControl(control);

    }

    public void setId(Text id) {
        this.id = id;
    }

    public Text getId() {
        return id;
    }

    public void setVersion(Text version) {
        this.version = version;
    }

    public Text getVersion() {
        return version;
    }

    public void setExampleName(Text exampleName) {
        this.exampleName = exampleName;
    }

    public Text getExampleName() {
        return this.exampleName;
    }

    public void setExampleDescription(Text exampleDescription) {
        this.exampleDescription = exampleDescription;
    }

    public Text getExampleDescription() {
        return exampleDescription;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
