/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

import de.cau.cs.kieler.kaom.importer.ptolemy.utils.Utils;


/**
 * A wizard page to import files from the workspace.
 * 
 * @author cds
 */
public class ImportDiagramsWorkspaceSourcesPage extends WizardExportResourcesPage {

    // CONSTANTS
    private static final String PAGE_NAME = "importDiagramsWorkspaceSourcesPage";
    
    // UI WIDGETS
    private Composite targetComposite;
    private Label targetLabel;
    private Text targetText;
    private Button targetBrowseButton;
    
    
    /**
     * Constructs a new instance.
     * 
     * @param selection the selection the wizard was called on.
     */
    public ImportDiagramsWorkspaceSourcesPage(IStructuredSelection selection) {
        super(PAGE_NAME, selection);
        
        this.setTitle("Import from workspace");
        this.setMessage("Select the files to import from the workspace.");
    }
    
    
    /**
     * Returns the selected source files to import. This method may take a while
     * to complete and shows its progress using a progress monitor.
     * 
     * @return list of selected source files.
     */
    public List<File> getSourceFiles() {
        List<?> selectedResources = this.getSelectedResources();
        List<File> files = new ArrayList<File>();
        
        // Iterate through the list of selected resources looking for Ptolemy2 files
        for (Object o : selectedResources) {
            if (o instanceof IFile) {
                IFile iFile = (IFile) o;
                
                if (Utils.isPtolemyFile(iFile.getName())) {
                    files.add(iFile.getLocation().toFile());
                }
            }
        }
        
        return files;
    }
    
    /**
     * Returns the target container path entered by the user. The container might
     * not exist yet.
     * 
     * @return the container to import to.
     */
    public IPath getTargetContainerPath() {
        return getPathFromText(targetText).makeAbsolute();
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void handleEvent(Event event) {
        /* It's not in the least bit clear to me why WizardDataTranserPage implements
         * Listener, but does not implement it. (neither does WizardExportResourcesPage)
         */
        System.out.println("HANDLE_EVENT: " + event.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean allowNewContainerName() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDestinationGroup(Composite parent) {
        GridLayout gl = new GridLayout(3, false);
        
        // Target Composite
        targetComposite = new Composite(parent, SWT.NULL);
        targetComposite.setLayout(gl);
        targetComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        // Target Label
        targetLabel = new Label(targetComposite, SWT.NULL);
        targetLabel.setText("Into folder:");
        
        // Target Text
        targetText = new Text(targetComposite, SWT.SINGLE | SWT.BORDER);
        targetText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        // Target Browse Button
        targetBrowseButton = new Button(targetComposite, SWT.NULL);
        targetBrowseButton.setText("Browse...");
        
        // Event Handlers
        targetBrowseButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                doBrowse();
            }
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void createOptionsGroup(Composite parent) {
        // We don't need no stinkin' options group around here!
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWizard(IWizard newWizard) {
        super.setWizard(newWizard);
        
        // Set the options page as the previous page
        this.setPreviousPage(((ImportDiagramsWizard) newWizard).getOptionsPage());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IWizardPage getNextPage() {
        // This is the wizard's last page
        return null;
    }
    
    /**
     * Browse for container.
     */
    private void doBrowse() {
        // Find the current container, if any
        IPath currentPath = getTargetContainerPath();
        IContainer currentContainer = null;
        
        if (currentPath != null) {
            IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
            IResource resource = workspaceRoot.findMember(currentPath);
            
            if (resource instanceof IContainer) {
                currentContainer = (IContainer) resource;
            }
        }
        
        // Find a new container
        IPath newPath = queryForContainer(
                currentContainer,
                "Select a folder to import into.",
                "Import into Folder");
        
        if (newPath != null) {
            targetText.setText(newPath.makeRelative().toString());
        }
    }
}
