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

package de.cau.cs.kieler.kaom.importer.ptolemy.improved.utils;

import org.eclipse.jface.dialogs.IDialogSettings;


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
    
    
    // FILES
    
    /**
     * Returns the file extension of the file with the given name. If a file name begins
     * with a dot, the part following it is not considered a file extension.
     * 
     * @param fileName name of the file whose extension to return.
     * @return the file extension, if any, without the dot.
     */
    public static String getFileExtension(final String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex <= 0 || extensionIndex == fileName.length() - 1) {
            return "";
        }
        
        return fileName.substring(extensionIndex + 1);
    }
    
    /**
     * Returns the file name without file extension.
     * 
     * @param fileName name of the file whose base name to return.
     * @return the base name.
     */
    public static String getFileBaseName(final String fileName) {
        String extension = getFileExtension(fileName);
        
        if (extension.length() == 0) {
            return fileName;
        } else {
            return fileName.substring(0, fileName.length() - extension.length() - 1);
        }
    }
}
