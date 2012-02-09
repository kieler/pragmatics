/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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

import de.cau.cs.kieler.core.ui.wizards.FileSystemResourcesPage;
import de.cau.cs.kieler.kaom.importer.ptolemy.Messages;
import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImportConstants;


/**
 * A wizard page to import files from the file system.
 * 
 * @author cds
 * @kieler.rating yellow 2010-03-14
 *      reviewed by haf, msp, pkl
 */
public class ImportDiagramsFileSystemSourcesPage extends FileSystemResourcesPage {
    
    // CONSTANTS
    /**
     * The wizard page name.
     */
    private static final String PAGE_NAME = "importDiagramsFileSystemSourcesPage"; //$NON-NLS-1$
    
    // VARIABLES
    private IStructuredSelection selection;
    
    
    /**
     * Constructs a new instance.
     * 
     * @param theSelection the selection the wizard was called on.
     */
    public ImportDiagramsFileSystemSourcesPage(final IStructuredSelection theSelection) {
        super(PAGE_NAME, true, PtolemyImportConstants.PTOLEMY_FILE_EXTENSIONS);
        
        this.setMessage(
                Messages.ImportDiagramsFileSystemSourcesPage_message);
        
        selection = theSelection;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeControls() {
        super.initializeControls();
        
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
