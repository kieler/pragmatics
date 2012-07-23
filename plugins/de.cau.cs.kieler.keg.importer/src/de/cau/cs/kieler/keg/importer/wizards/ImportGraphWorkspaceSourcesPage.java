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
package de.cau.cs.kieler.keg.importer.wizards;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.cau.cs.kieler.core.ui.wizards.WorkspaceResourcesPage;
import de.cau.cs.kieler.keg.importer.ImportManager;

/**
 * The import graph wizard page which lets the user select the source files from the workspace and
 * the target folder.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class ImportGraphWorkspaceSourcesPage extends WorkspaceResourcesPage {

    /** the selection the wizard has been called on. */
    private IStructuredSelection selection;

    /**
     * Constructs an ImportGraphWorkspaceSourcesPage.
     * 
     * @param selection
     *            the selection the wizard has been called on
     */
    public ImportGraphWorkspaceSourcesPage(final IStructuredSelection selection) {
        super("importGraphWorkspaceSourcesPage", true, //$NON-NLS-1$
                ImportManager.getInstance().getExtensions(), 
                selection);
        this.selection = selection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeControls() {
        super.initializeControls();
        // set the initial import target folder
        if (!selection.isEmpty()) {
            Object element = selection.getFirstElement();
            if (element instanceof IResource) {
                IResource resource = (IResource) element;
                if (resource instanceof IContainer) {
                    getTargetGroupCombo().setText(resource.getFullPath().makeRelative().toString());
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
     * Returns a list of selected workspace files.
     * 
     * @param monitor
     *            the progress monitor
     * @return the selected files
     */
    public List<IFile> getFiles(final IProgressMonitor monitor) {
        List<IFile> files = new LinkedList<IFile>();
        for (IResource resource : getResources(monitor)) {
            if (resource instanceof IFile) {
                IFile file = (IFile) resource;
                if (file.exists()) {
                    files.add(file);
                }
            }
        }
        return files;
    }
}
