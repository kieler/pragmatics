/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.core.ui.wizards;

import java.io.File;

import org.eclipse.ui.dialogs.FileSystemElement;


/**
 * A {@code FileSystemElement} representing {@code File}s and carrying extended states.
 * Most importantly, this element tracks whether it has already been visited or not.
 * "Visited" in this context means that someone has already added all subdirectories
 * and subfolders to it. This class is used by {@link FileSystemResourcesPage}.
 * 
 * <p>An instance of this class can be flagged as being the input element to a viewer.
 * This doesn't influence the behaviour of the instance, but can be used to change how
 * a viewer gets populated. When using an instance of this class as the input element
 * to a tree viewer, the tree's top elements would probably be the children of the viewer's
 * input element. This might not always be desirable, though. With the input element marked
 * as such, the behaviour can be changed to have the top level element be the input element.
 * An example of this can be found in the {@link FileSystemResourcesPage} class.</p>
 * 
 * @author cds
 * @kieler.design 2010-03-14
 *      reviewed by haf, msp, pkl
 * @kieler.rating yellow 2010-03-14
 *      reviewed by haf, msp, pkl
 */
public class ExtendedFileSystemElement extends FileSystemElement {
    /**
     * Whether this element is used as the input for a viewer or not.
     */
    private boolean isInputElement = false;
    
    /**
     * Whether this element has already been visited or not.
     */
    private boolean visited = false;
    
    
    /**
     * Constructs a new instance. This instance is not flagged as being the input element
     * for a viewer.
     * 
     * @param file the file represented by this object..
     * @param parent the parent, if any.
     */
    public ExtendedFileSystemElement(final File file, final FileSystemElement parent) {
        super(file.getName(), parent, file.isDirectory());
        
        setFileSystemObject(file);
    }
    
    /**
     * Constructs a new instance.
     * 
     * @param file the file represented by this object..
     * @param inputElement {@code true} if this element is being used as the input element
     *                     for a viewer.
     */
    public ExtendedFileSystemElement(final File file, final boolean inputElement) {
        this(file, null);
        
        isInputElement = inputElement;
    }
    
    
    /**
     * Returns whether or not this element was flagged as being used as a viewer's input
     * element.
     * 
     * @return {@code true} if this element is being used as a viewer's input element.
     */
    public boolean isInputElement() {
        return isInputElement;
    }
    
    /**
     * Checks if the element has already been visited. Visited objects can be trusted to
     * return correct results for {@code getFiles()} and {@code getFolders()}.
     * 
     * @return {@code true} if the element has already been visited, {@code false} otherwise.
     */
    public boolean isVisited() {
        return visited;
    }
    
    /**
     * Indicates that this object has now been visited.
     */
    public void visit() {
        visited = true;
    }
    
    /**
     * Returns the file this object represents.
     * 
     * @return the file.
     */
    public File getFile() {
        return (File) getFileSystemObject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileSystemObject(final Object value) {
        if (value instanceof File) {
            super.setFileSystemObject(value);
        } else {
            super.setFileSystemObject(null);
        }
    }
    
}
