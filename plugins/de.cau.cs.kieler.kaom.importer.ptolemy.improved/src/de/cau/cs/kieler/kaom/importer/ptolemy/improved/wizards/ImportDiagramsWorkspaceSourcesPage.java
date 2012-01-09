/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy.improved.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

import de.cau.cs.kieler.core.ui.wizards.WorkspaceResourcesPage;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.Messages;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.PtolemyImportConstants;


/**
 * A wizard page to import files from the workspace.
 * 
 * @author cds
 * @kieler.rating yellow 2010-03-14
 *      reviewed by haf, msp, pkl
 */
public class ImportDiagramsWorkspaceSourcesPage extends WorkspaceResourcesPage {
    
    // CONSTANTS
    /**
     * The wizard page name.
     */
    private static final String PAGE_NAME = "importDiagramsWorkspaceSourcesPage"; //$NON-NLS-1$
    
    
    /**
     * Constructs a new instance.
     * 
     * @param selection the selection the wizard was called on.
     */
    public ImportDiagramsWorkspaceSourcesPage(final IStructuredSelection selection) {
        super(PAGE_NAME, true, PtolemyImportConstants.PTOLEMY_FILE_EXTENSIONS, selection);
        
        this.setMessage(Messages.ImportDiagramsWorkspaceSourcesPage_message);
    }
    
    
    /**
     * Returns the selected source files to import. This method may take a while
     * to complete and shows its progress using a progress monitor.
     * 
     * @param monitor progress monitor.
     * @return list of selected source files.
     */
    public List<File> getSourceFiles(final IProgressMonitor monitor) {
        List<IResource> selectedResources = this.getResources(monitor);
        List<File> files = new ArrayList<File>();
        
        for (IResource resource : selectedResources) {
            if (resource instanceof IFile) {
                IFile iFile = (IFile) resource;
                files.add(iFile.getLocation().toFile());
            }
        }
        
        return files;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeControls() {
        super.initializeControls();
        
        // Set the initial target container name
        IStructuredSelection selection = this.getWorkspaceSelection();
        if (!selection.isEmpty()) {
            Object element = selection.getFirstElement();
            
            if (element instanceof IResource) {
                IResource resource = (IResource) element;
                
                if (resource instanceof IContainer) {
                    getTargetGroupCombo().setText(
                            resource.getFullPath().makeRelative().toString());
                    validate();
                } else if (resource.getParent() != null) {
                    getTargetGroupCombo().setText(
                            resource.getParent().getFullPath().makeRelative().toString());
                    validate();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWizard(final IWizard newWizard) {
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
}
