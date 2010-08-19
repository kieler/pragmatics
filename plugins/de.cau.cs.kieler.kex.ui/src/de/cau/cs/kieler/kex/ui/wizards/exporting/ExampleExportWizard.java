package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.ExportResource;
import de.cau.cs.kieler.kex.model.SourceType;

public class ExampleExportWizard extends Wizard implements IWizard {

	private ExamplePage examplePage;
	private ExampleResourcePage exRePage;
	private ResourcePage rePage;

	private final IStructuredSelection selection;

	public ExampleExportWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setWindowTitle("Kieler Example Export");
	}

	@Override
	public void addPages() {
		examplePage = new ExamplePage("examplePage");
		exRePage = new ExampleResourcePage("projectImportPage");
		rePage = new ResourcePage("resourcePage");
		addPage(examplePage);
		addPage(exRePage);
		addPage(rePage);
	}

	public IStructuredSelection getSelection() {
		return this.selection;
	}

	@Override
	public boolean performFinish() {
		try {
			Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();

			addAttributes(result);

			SourceType exportType = exRePage.getExportType();
			if (exportType == null)
				throw new KielerException("Export type was not set.");
			result.put(ExampleElement.SOURCETYPE, exportType);

			String destLocation = exRePage.getDestLocation();
			validateField(destLocation, 2, "Destination Location");
			result.put(ExampleElement.DEST_LOCATION, destLocation);

			List<String> categories = exRePage.getCategories();
			validateElement(categories, 1, "Categories");
			result.put(ExampleElement.CATEGORIES, categories);

			rePage.buildResourceStructure();
			List<ExportResource> exportedResources = rePage
					.getExportedResources();
			validateElement(exportedResources, 1, "Exported Resources");
			result.put(ExampleElement.RESOURCES, exportedResources);

			result.put(ExampleElement.HEAD_RESOURCE, rePage.getHeadFile());

			List<String> creatableCategories = exRePage
					.getCreatableCategories();
			if (creatableCategories.size() > 0) {
				result.put(ExampleElement.CREATE_CATEGORIES,
						creatableCategories);
			}

			List<String> deletableCategories = exRePage
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
		String exampleId = examplePage.getId();
		validateField(exampleId, 4, "Example Id");
		map.put(ExampleElement.ID, exampleId);

		String exampleName = examplePage.getExampleName();
		validateField(exampleName, 4, "Example Name");
		map.put(ExampleElement.NAME, exampleName);

		String exampleDescription = examplePage.getExampleDescription();
		validateField(exampleDescription, 10, "Example Description");
		map.put(ExampleElement.DESCRIPTION, exampleDescription);

		String exampleVersion = examplePage.getVersion();
		validateVersion(exampleVersion);
		map.put(ExampleElement.VERSION, exampleVersion);

		String exampleContact = examplePage.getContact();
		// min 5 chars a@b.c
		validateField(exampleContact, 5, "Example Contact");
		map.put(ExampleElement.CONTACT, exampleContact);
	}

	private void validateElement(List<?> list, int minLength, String listName)
			throws KielerException {
		if (list == null || list.size() < minLength) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("No ").append(listName)
					.append(" has been selected.\n")
					.append("Please choose at least ")
					.append(String.valueOf(minLength));
			throw new KielerException(errorMsg.toString());
		}
	}

	private void validateVersion(String exampleVersion) throws KielerException {
		try {
			if (Version.emptyVersion.equals(Version
					.parseVersion(exampleVersion)))
				throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("The field ")
					.append("Example Version")
					.append(" has to be set and a correct version like 1.0 or 1.4.");
			throw new KielerException(errorMsg.toString());
		}
	}

	private void validateField(String checkable, int minLength,
			String checkableName) throws KielerException {
		if (checkable == null || checkable.length() < minLength) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("The field ").append(checkableName)
					.append(" has to be set with at least ")
					.append(String.valueOf(minLength)).append(" characters.");
			throw new KielerException(errorMsg.toString());
		}
	}

}
