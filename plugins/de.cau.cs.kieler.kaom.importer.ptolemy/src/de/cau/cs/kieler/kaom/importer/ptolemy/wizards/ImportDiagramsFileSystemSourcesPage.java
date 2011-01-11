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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

import de.cau.cs.kieler.core.ui.wizards.ImportFromFilesystemPage;
import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImporterConstants;


/**
 * A wizard page to import files from the file system.
 * 
 * @author cds
 */
public class ImportDiagramsFileSystemSourcesPage extends ImportFromFilesystemPage {
    
    // CONSTANTS
    private static final String PAGE_NAME = "importDiagramsFileSystemSourcesPage";
    
    // VARIABLES
    private IStructuredSelection selection;
    
    
    /**
     * Constructs a new instance.
     * 
     * @param theSelection the selection the wizard was called on.
     */
    public ImportDiagramsFileSystemSourcesPage(final IStructuredSelection theSelection) {
        super(PAGE_NAME, true, PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS);
        
        this.setMessage(
                "Select the Ptolemy2 .moml or .xml files to import from the local file system.");
        
        selection = theSelection;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Wizardry Stuff
    
    // This is used to control the wizard's page flow.
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void restoreDialogSettings() {
        super.restoreDialogSettings();
        
        // Set the initial target container name
        if (!selection.isEmpty()) {
            Object element = selection.getFirstElement();
            
            if (element instanceof IResource) {
                IResource resource = (IResource) element;
                
                if (resource instanceof IContainer) {
                    getTargetGroupCombo().setText(
                            resource.getFullPath().makeRelative().toString());
                } else if (resource.getParent() != null) {
                    getTargetGroupCombo().setText(
                            resource.getParent().getFullPath().makeRelative().toString());
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
