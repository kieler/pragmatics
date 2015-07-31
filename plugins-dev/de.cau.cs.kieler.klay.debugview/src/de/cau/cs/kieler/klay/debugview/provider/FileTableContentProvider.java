/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.debugview.provider;

import java.io.File;
import java.io.FileFilter;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the file table. Expects the table viewer to get a
 * {@code File} as input that denotes a directory.
 * 
 * @author cds
 */
public class FileTableContentProvider implements IStructuredContentProvider {
    
    private String fileExtension;
    
    /**
     * 
     */
    public FileTableContentProvider() {
        fileExtension = ".dot";
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // Nothing to do here.
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof File) {
            // Return a list of .dot files
            return ((File) inputElement).listFiles(new FileFilter() {
                public boolean accept(final File file) {
                    return file.isFile() && file.getName().endsWith(fileExtension);
                }
            });
            
        } else {
            return new Object[0];
        }
    }

    /**
     * @return the fileExtension
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * @param fileExtension the fileExtension to set
     */
    public void setFileExtension(final String fileExtension) {
        this.fileExtension = fileExtension;
    }
}