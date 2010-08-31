package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.controller.ExportResource;
import de.cau.cs.kieler.kex.model.SourceType;

public class ExampleExportWizard extends Wizard implements IExportWizard {

	private ExampleAttributesPage examplePage;
	private ExampleResourcesPage resourcePage;
	private ExampleExportPage exportPage;

	public ExampleExportWizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Kieler Example Export");
		setNeedsProgressMonitor(true);
		examplePage = new ExampleAttributesPage("Example Export", selection);
		resourcePage = new ExampleResourcesPage("Example Resources", selection);
		exportPage = new ExampleExportPage("Destination Choice", selection);
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(examplePage);
		addPage(resourcePage);
		addPage(exportPage);
	}

	@Override
	public boolean performFinish() {
		try {
			Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();

			addAttributes(result);

			SourceType exportType = exportPage.getExportType();
			if (exportType == null)
				throw new KielerException("Export type was not set.");
			result.put(ExampleElement.SOURCETYPE, exportType);

			String destLocation = exportPage.getDestLocation();
			validateField(destLocation, 2, "Destination Location");
			result.put(ExampleElement.DEST_LOCATION, destLocation);

			List<String> categories = exportPage.getCheckedCategories();
			validateElement(categories, 1, "Categories");
			result.put(ExampleElement.CATEGORIES, categories);

			result.put(ExampleElement.CREATE_EXAMPLE_FOLDER, exportPage
					.createExampleFolder());

			resourcePage.buildResourceStructure();
			List<ExportResource> exportedResources = resourcePage
					.getExportedResources();
			validateElement(exportedResources, 1, "Exported Resources");
			result.put(ExampleElement.RESOURCES, exportedResources);

			List<String> creatableCategories = exportPage
					.getCreatableCategories();
			if (creatableCategories.size() > 0) {
				result.put(ExampleElement.CREATE_CATEGORIES,
						creatableCategories);
			}

			List<String> deletableCategories = exportPage
					.getDeletableCategories();
			if (deletableCategories.size() > 0) {
				result.put(ExampleElement.DELETE_CATEGORIES,
						deletableCategories);
			}

			ExampleManager.get().export(result);
		} catch (KielerException e) {
			MessageDialog.open(MessageDialog.INFORMATION, getShell(),
					"Error while exporting example.", e.getMessage(), SWT.NONE);
			return false;
		}
		return true;

	}

	private void addAttributes(Map<ExampleElement, Object> map)
			throws KielerException {
		String exampleTitle = examplePage.getTitle();
		validateField(exampleTitle, 4, "Example Title");
		map.put(ExampleElement.TITLE, exampleTitle);

		String author = examplePage.getAuthor();
		validateField(author, 3, "Author");
		map.put(ExampleElement.AUTHOR, author);

		String exampleDescription = examplePage.getExampleDescription();
		validateField(exampleDescription, 10, "Example Description");
		map.put(ExampleElement.DESCRIPTION, exampleDescription);

		String exampleContact = examplePage.getContact();
		// min 5 chars a@b.c
		validateField(exampleContact, 5, "Example Contact");
		map.put(ExampleElement.CONTACT, exampleContact);

		IPath overviewPicPath = exportPage.getOverviewPicPath();
		if (overviewPicPath != null) {
			map.put(ExampleElement.OVERVIEW_PIC, overviewPicPath
					.toPortableString());
		}

	}

	private void validateElement(List<?> list, int minLength, String listName)
			throws KielerException {
		if (list == null || list.size() < minLength) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("No ").append(listName).append(
					" has been selected.\n").append("Please choose at least ")
					.append(String.valueOf(minLength));
			throw new KielerException(errorMsg.toString());
		}
	}

	private void validateField(String checkable, int minLength,
			String checkableName) throws KielerException {
		if (checkable == null || checkable.length() < minLength) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("The field ").append(checkableName).append(
					" has to be set with at least ").append(
					String.valueOf(minLength)).append(" characters.");
			throw new KielerException(errorMsg.toString());
		}
	}

}
