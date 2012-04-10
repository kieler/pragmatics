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
package de.cau.cs.kieler.kiml.export.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.cau.cs.kieler.core.ui.wizards.WorkspaceResourcesPage;

/**
 * The Export graph wizard page which lets the user select the source files from the workspace and
 * the target folder.
 * 
 * @author wah
 */
public class ExportGraphWorkspaceSourcesPage extends WorkspaceResourcesPage {

 // CONSTANTS
    /**
     * The wizard page name.
     */
    private static final String PAGE_NAME = "exportDiagramsWorkspaceSourcesPage"; //$NON-NLS-1$
    
    
    /**
     * Constructs a new instance.
     * 
     * @param selection the selection the wizard was called on.
     */
    public ExportGraphWorkspaceSourcesPage(final IStructuredSelection selection) {
        super(PAGE_NAME, true, null, selection);
        this.setTitle(Messages.ExportGraphWizard_title);
        this.setDescription(Messages.ExportGraphWizard_Exporting_workspace_task);
        this.setMessage(Messages.ExportGraphWizard_Exporting_workspace_task);
    }
    
    
    /**
     * Returns the selected source files to export. This method may take a while
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
    
    
}
