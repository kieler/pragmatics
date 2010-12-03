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

import org.eclipse.ui.dialogs.FileSystemElement;
import org.eclipse.ui.model.AdaptableList;


/**
 * Input element suitable for use with a resource group. Because of the semantics of
 * tree viewers, the input element cannot be returned as one of the root elements
 * shown by the viewer. The problem is, we want exactly that: the root folder (which
 * is the input element) should be shown on the uppermost level. Thus, we use an
 * instance of this class as the input element. It wraps the root element and delegates
 * all method calls accordingly.
 * 
 * @author cds
 */
public class ExtendedFileSystemInputElement extends ExtendedFileSystemElement {
    /**
     * The wrapped element.
     */
    private ExtendedFileSystemElement wrappedElement;
    
    
    /**
     * Constructs a new instance representing the given file.
     * 
     * @param file
     */
    public ExtendedFileSystemInputElement(File file) {
        super(file, null);
        
        wrappedElement = new ExtendedFileSystemElement(file, null);
    }
    
    
    /**
     * Returns the element wrapped by this object.
     * 
     * @return the wrapped element.
     */
    public ExtendedFileSystemElement getWrappedElement() {
        return wrappedElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisited() {
        return wrappedElement.isVisited();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit() {
        wrappedElement.visit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getFile() {
        return wrappedElement.getFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getFileSystemObject() {
        return wrappedElement.getFileSystemObject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileSystemObject(Object value) {
        wrappedElement.setFileSystemObject(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(FileSystemElement child) {
        wrappedElement.addChild(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdapter(Class adapter) {
        return wrappedElement.getAdapter(adapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileNameExtension() {
        return wrappedElement.getFileNameExtension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdaptableList getFiles() {
        return wrappedElement.getFiles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdaptableList getFolders() {
        return wrappedElement.getFolders();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSystemElement getParent() {
        return wrappedElement.getParent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDirectory() {
        return wrappedElement.isDirectory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFolder(FileSystemElement child) {
        wrappedElement.removeFolder(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParent(FileSystemElement element) {
        wrappedElement.setParent(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return wrappedElement.toString();
    }
}
