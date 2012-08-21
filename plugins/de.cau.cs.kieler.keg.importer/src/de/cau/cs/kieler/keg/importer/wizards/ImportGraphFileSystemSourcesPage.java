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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.cau.cs.kieler.core.ui.wizards.FileSystemResourcesPage;
import de.cau.cs.kieler.keg.importer.ImportManager;

/**
 * The import graph wizard page which lets the user select the source files from the file system and
 * the target folder.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class ImportGraphFileSystemSourcesPage extends FileSystemResourcesPage {

    /** the selection the wizard has been called on. */
    private IStructuredSelection selection;

    /**
     * Constructs an ImportGraphFileSystemSourcesPage.
     * 
     * @param selection
     *            the selection the wizard has been called on
     */
    public ImportGraphFileSystemSourcesPage(final IStructuredSelection selection) {
        super("importGraphFileSystemSourcesPage", true, //$NON-NLS-1$
                ImportManager.getInstance().getExtensions()); 
        this.selection = selection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeControls() {
        super.initializeControls();
        // set the initial import target
        if (!selection.isEmpty()) {
            Object element = selection.getFirstElement();
            if (element instanceof IResource) {
                IResource resource = (IResource) element;
                if (resource instanceof IContainer) {
                    getTargetGroupCombo().setText(resource.getFullPath().makeRelative().toString());
                } else if (resource.getParent() != null) {
                    getTargetGroupCombo().setText(
                            resource.getParent().getFullPath().makeRelative().toString());
                }
            }
        }
    }
}
