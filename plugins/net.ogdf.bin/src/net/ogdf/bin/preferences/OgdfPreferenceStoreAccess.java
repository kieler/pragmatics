/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package net.ogdf.bin.preferences;

/**
 * Allows access to the {@link OgdfPreferenceStore} in a ui save way. That is, the eclipse.ui
 * dependency is optional for this plugin. The methods of this class regard this by returning
 * default values if no preference store is available.
 * 
 * @author uru
 */
public final class OgdfPreferenceStoreAccess {

    private static boolean uiAvailable = true;

    private OgdfPreferenceStoreAccess() {
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
                return OgdfPreferenceStore.getInstance().getPreferenceStore().getInt(name);
            } catch (NoClassDefFoundError e) {
                uiAvailable = false;
            }
        }
        return 0;
    }

}
