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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImporterConstants;


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
    public static String getSetting(IDialogSettings settings, String key, String deflt) {
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
    public static boolean getSettingBoolean(IDialogSettings settings, String key, boolean deflt) {
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
    public static String[] getSettingArray(IDialogSettings settings, String key, String[] deflt) {
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
    public static void insertVerticalSpace(Composite container, int space, int columns) {
        Label label = new Label(container, SWT.NULL);
        
        GridData gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalSpan = columns;
        gd.minimumHeight = space;
        gd.heightHint = space;
        
        label.setLayoutData(gd);
    }
    
    
    // ARRAYS
    
    /**
     * Returns a new array with the given item inserted or moved to the top. If the
     * item is already in the source array, it is moved to the top. If not, it is
     * inserted. If the array contains more items than {@code maxSize}, the item
     * with the highest index (after insertion / move) is removed. If the item refers
     * to the empty string or is {@code null}, the source array is returned.
     * 
     * @param array the source array.
     * @param item the item to insert or to move.
     * @param maxSize the maximum size of the returned array.
     * @return the array with the given item moved or inserted.
     */
    public static String[] insertOrMoveToTop(String[] array, String item, int maxSize) {
        if (item == null || item.length() == 0) {
            return array;
        }
        
        List<String> newList = new ArrayList<String>(maxSize + 1);
        
        // Go through the old array adding all items if they don't equal the item
        // to be moved. Traverse the array backwards to preserve the item ordering.
        for (int i = array.length - 1; i >= 0; i--) {
            if (!array[i].equals(item)) {
                newList.add(array[i]);
            }
        }
        
        // Add the item
        newList.add(item);
        
        // If the list is too large, remove the last item
        if (newList.size() > maxSize) {
            newList.remove(newList.size() - 1);
        }
        
        // Return the new array
        return newList.toArray(new String[newList.size()]);
    }
    
    
    // FILES
    
    /**
     * Returns the file extension of the file with the given name. If a file name begins
     * with a dot, the part following it is not considered a file extension.
     * 
     * @param fileName name of the file whose extension to return.
     * @return the file extension, if any.
     */
    public static String getFileExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex <= 0 || extensionIndex == fileName.length() - 1) {
            return "";
        }
        
        return fileName.substring(extensionIndex + 1);
    }
    
    /**
     * Checks if the given file name's file extension matches one of the file extension
     * we accept for Ptolemy2 diagram files.
     * 
     * @param name the file name to check.
     * @return {@code true} if we think that the given name denotes a Ptolemy2 diagram file.
     * @see de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS
     */
    public static boolean isPtolemyFile(String name) {
        String extension = getFileExtension(name);
        if (extension.length() == 0) {
            return false;
        }
        
        for (String candidate : PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS) {
            if (candidate.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        
        return false;
    }
}
