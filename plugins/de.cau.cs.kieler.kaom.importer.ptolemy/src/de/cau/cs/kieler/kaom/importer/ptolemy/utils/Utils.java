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

package de.cau.cs.kieler.kaom.importer.ptolemy.utils;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * Contains utility methods used in the plug-in.
 * 
 * @author cds
 */
public final class Utils {
    
    /**
     * This class is not supposed to be instantiated.
     */
    private Utils() {
    }
    
    
    // DIALOG SETTINGS
    
    /**
     * Returns the given setting from the given dialog settings instance, or the given
     * default if none is found.
     * 
     * @param settings the dialog settings to retrieve the setting from.
     * @param key the setting's key.
     * @param deflt the default value.
     * @return the setting's value or its default value.
     */
    public static String getSetting(final IDialogSettings settings, final String key,
            final String deflt) {
        
        String setting = settings.get(key);
        
        if (setting == null) {
            return deflt;
        } else {
            return setting;
        }
    }

    /**
     * Returns the given setting from the given dialog settings instance, or the given
     * default if none is found.
     * 
     * @param settings the dialog settings to retrieve the setting from.
     * @param key the setting's key.
     * @param deflt the default value.
     * @return the setting's value or its default value.
     */
    public static boolean getSettingBoolean(final IDialogSettings settings, final String key,
            final boolean deflt) {
        
        String setting = settings.get(key);
        
        if (setting == null) {
            return deflt;
        } else {
            return settings.getBoolean(key);
        }
    }

    /**
     * Returns the given setting from the given dialog settings instance, or the given
     * default if none is found.
     * 
     * @param settings the dialog settings to retrieve the setting from.
     * @param key the setting's key.
     * @param deflt the default value.
     * @return the setting's value or its default value.
     */
    public static String[] getSettingArray(final IDialogSettings settings, final String key,
            final String[] deflt) {
        
        String setting = settings.get(key);
        
        if (setting == null) {
            return deflt;
        } else {
            return settings.getArray(key);
        }
    }
    
    
    // LAYOUT
    
    /**
     * Inserts the given amount of vertical space into the given container. This is
     * accomplished by inserting a label with suitable layout data. The container is
     * assumed to use {@code GridLayout}. This method must only be called at the
     * beginning of a new line in the layout manager.
     * 
     * @param container the container to insert the space into.
     * @param space the amount of space to insert.
     * @param columns the number of columns in the grid layout.
     */
    public static void insertVerticalSpace(final Composite container, final int space,
            final int columns) {
        
        Label label = new Label(container, SWT.NULL);
        
        GridData gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalSpan = columns;
        gd.minimumHeight = space;
        gd.heightHint = space;
        
        label.setLayoutData(gd);
    }
    
    
    // FILES
    
    /**
     * Returns the file extension of the file with the given name. If a file name begins
     * with a dot, the part following it is not considered a file extension.
     * 
     * @param fileName name of the file whose extension to return.
     * @return the file extension, if any.
     */
    public static String getFileExtension(final String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex <= 0 || extensionIndex == fileName.length() - 1) {
            return "";
        }
        
        return fileName.substring(extensionIndex + 1);
    }
}
