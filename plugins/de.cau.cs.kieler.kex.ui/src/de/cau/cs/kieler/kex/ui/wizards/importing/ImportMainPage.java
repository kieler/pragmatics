package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.ui.wizards.importing.viewer.ExampleViewer;

public class ImportMainPage extends WizardPage implements IShellProvider {

    private static final int MINIMUM_HEIGHT = 480;

    private ExampleViewer viewer;

    public ImportMainPage(String name) {
        super(name);
        setDescription(Messages.MainPage_pageDescription);
        setPageComplete(false);
    }

    public void createControl(Composite parent) {
        viewer = new ExampleViewer(this, getContainer());
        viewer.setVerifyUpdateSiteAvailability(true);
        viewer.setMinimumHeight(MINIMUM_HEIGHT);
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                setPageComplete(viewer.isComplete());
            }
        });
        viewer.createControl(parent);
        setControl(viewer.getControl());
    }

    public List<Example> getExamples() {
        return viewer.getInstallableExamples();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            Display.getCurrent().asyncExec(new Runnable() {
                public void run() {
                    if (!getControl().isDisposed() && isCurrentPage()) {
                        viewer.updateContents();
                    }
                }
            });
        }
    }

}
