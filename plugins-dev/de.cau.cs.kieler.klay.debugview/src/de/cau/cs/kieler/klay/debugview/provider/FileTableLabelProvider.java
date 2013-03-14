/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.debugview.provider;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.klay.debugview.KlayDebugViewPlugin;

/**
 * Label provider for the file table.
 * 
 * @author cds
 */
public class FileTableLabelProvider extends ColumnLabelProvider {
    /**
     * The name column.
     */
    public static final int COL_NAME = 0;
    
    /**
     * The creation time column.
     */
    public static final int COL_CREATED = 1;
    
    /**
     * Length of the DOT and PNG file extensions.
     */
    public static final int FILE_EXTENSION_LENGTH = 3;
    
    /**
     * Image for files whose PNG hasn't been created yet.
     */
    private Image unconverted = KlayDebugViewPlugin.loadImage("notconverted.gif"); //$NON-NLS-1$

    /**
     * Image for files whose PNG is available.
     */
    private Image converted = KlayDebugViewPlugin.loadImage("converted.gif"); //$NON-NLS-1$
    
    /**
     * The column to provide labels for.
     */
    private int column = 0;
    
    
    /**
     * Creates a new label provider for the given column.
     * 
     * @param column the column.
     */
    public FileTableLabelProvider(final int column) {
        this.column = column;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void dispose() {
        super.dispose();
        
        if (converted != null) {
            converted.dispose();
        }
        
        if (unconverted != null) {
            unconverted.dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Image getImage(final Object element) {
        if (column == COL_NAME) {
            String path = ((File) element).getPath();
            File pngCounterpart = new File(
                    path.substring(0, path.length() - FILE_EXTENSION_LENGTH) + "png"); //$NON-NLS-1$
            
            if (pngCounterpart.exists()) {
                return converted;
            } else {
                return unconverted;
            }
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getText(final Object element) {
        File file = (File) element;
        
        if (column == COL_NAME) {
            return file.getName();
        } else if (column == COL_CREATED) {
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(
                    new Date(file.lastModified()));
        } else {
            return null;
        }
    }
}