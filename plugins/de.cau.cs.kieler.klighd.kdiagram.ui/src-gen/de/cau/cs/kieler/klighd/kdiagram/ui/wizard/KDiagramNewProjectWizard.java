package de.cau.cs.kieler.klighd.kdiagram.ui.wizard;

import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.ui.wizard.XtextNewProjectWizard;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import com.google.inject.Inject;

public class KDiagramNewProjectWizard extends XtextNewProjectWizard {

	private WizardNewProjectCreationPage mainPage;

	@Inject
	public KDiagramNewProjectWizard(IProjectCreator projectCreator) {
		super(projectCreator);
		setWindowTitle("New KDiagram Project");
	}

	/**
	 * Use this method to add pages to the wizard.
	 * The one-time generated version of this class will add a default new project page to the wizard.
	 */
	public void addPages() {
		mainPage = new WizardNewProjectCreationPage("basicNewProjectPage");
		mainPage.setTitle("KDiagram Project");
		mainPage.setDescription("Create a new KDiagram project.");
		addPage(mainPage);
	}

	/**
	 * Use this method to read the project settings from the wizard pages and feed them into the project info class.
	 */
	@Override
	protected IProjectInfo getProjectInfo() {
		de.cau.cs.kieler.klighd.kdiagram.ui.wizard.KDiagramProjectInfo projectInfo = new de.cau.cs.kieler.klighd.kdiagram.ui.wizard.KDiagramProjectInfo();
		projectInfo.setProjectName(mainPage.getProjectName());
		return projectInfo;
	}

}
