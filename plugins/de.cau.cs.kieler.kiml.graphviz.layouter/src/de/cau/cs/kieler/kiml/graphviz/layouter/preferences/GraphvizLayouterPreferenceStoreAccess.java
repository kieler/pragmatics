/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter.preferences;

/**
 * Allows access to the {@link GraphvizLayouterPreferenceStore} in a ui save way. That is, the
 * eclipse.ui dependency is optional for this plugin. The methods of this class regard this by
 * returning default values if no preference store is available.
 * 
 * @author uru
 */
public final class GraphvizLayouterPreferenceStoreAccess {

    private static boolean uiAvailable = true;

    private GraphvizLayouterPreferenceStoreAccess() {
    }

    /**
     * @param name
     *            name of the requested preference
     * @return See {@link org.eclipse.jface.preference.IPreferenceStore#getInt(String)
     *         IPreferenceStore#getInt(String)}.
     */
    public static int getUISaveInt(final String name) {
        if (uiAvailable) {
            try {
                return GraphvizLayouterPreferenceStore.getInstance().getPreferenceStore()
                        .getInt(name);
            } catch (NoClassDefFoundError e) {
                uiAvailable = false;
            }
        }
        return 0;
    }

    /**
     * @param name
     *            name of the requested preference
     * @return See {@link org.eclipse.jface.preference.IPreferenceStore#getString(String)
     *         IPreferenceStore#getString(String)}.
     */
    public static String getUISaveString(final String name) {
        if (uiAvailable) {
            try {
                return GraphvizLayouterPreferenceStore.getInstance().getPreferenceStore()
                        .getString(name);
            } catch (NoClassDefFoundError e) {
                uiAvailable = false;
            }
        }
        return "";
    }

    /**
     * @param name
     *            name of the requested preference
     * @param ifFailed
     *            the boolean value to be returned if the preference store is not available.
     * @return See {@link org.eclipse.jface.preference.IPreferenceStore#getBoolean(String)
     *         IPreferenceStore#getBoolean(String)}.
     */
    public static boolean getUISaveBoolean(final String name, final boolean ifFailed) {
        if (uiAvailable) {
            try {
                return GraphvizLayouterPreferenceStore.getInstance().getPreferenceStore()
                        .getBoolean(name);
            } catch (NoClassDefFoundError e) {
                uiAvailable = false;
            }
        }
        return ifFailed;
    }
}
