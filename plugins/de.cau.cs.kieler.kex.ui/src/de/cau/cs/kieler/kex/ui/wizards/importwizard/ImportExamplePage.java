package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

public class ImportExamplePage extends WizardResourceImportPage {

	protected ImportExamplePage(String name, IStructuredSelection selection) {
		super(name, selection);

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createSourceGroup(Composite parent) {

	}

	@Override
	protected ITreeContentProvider getFileProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ITreeContentProvider getFolderProvider() {
		// TODO Auto-generated method stub
		return null;
	}

}
