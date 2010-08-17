package de.cau.cs.kieler.kex.ui.wizards.importing;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

public class ImportExamplePage extends WizardResourceImportPage {

	protected ImportExamplePage(String name, IStructuredSelection selection) {
		super(name, selection);
	}

	@Override
	protected void createSourceGroup(Composite parent) {

	}

	@Override
	protected ITreeContentProvider getFileProvider() {
		return null;
	}

	@Override
	protected ITreeContentProvider getFolderProvider() {
		return null;
	}

	public IPath getContainerPath() {
		return super.getContainerFullPath();
	}

}
