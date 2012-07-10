/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.layout;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kwebs.client.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.Preferences;

/**
 * This is an utility class for switching between local and remote layout.
 *
 * @author swe
 */
public final class SwitchLayoutMode {

    /**
     * Switch layout mode to local.
     */
    public static void toLocal() {
        activateRemoteLayout(false);
    }
    
    /**
     * Switch layout mode to remote.
     */
    public static void toRemote() {
        activateRemoteLayout(true);
    }

    /** The preference store. */
    private static IPreferenceStore preferenceStore
        = Preferences.getPreferenceStore();
    
    /**
     * Enable or disable remote layout.
     *  
     * @param remoteLayout
     *            whether to enable or disable remote layout
     */
    private static void activateRemoteLayout(final boolean remoteLayout) {        
        boolean oldSetting = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        if (oldSetting != remoteLayout) {
            preferenceStore.setValue(Preferences.PREFID_LAYOUT_USE_REMOTE, remoteLayout);
            // Fire property change event so that the RemoteGraphLayoutEngine can
            // initialize itself on the new conditions.         
            preferenceStore.firePropertyChangeEvent(
                Preferences.PREFID_LAYOUT_SETTINGS_CHANGED, "1", "2"
            );
        }
    }

    /**
     * Checks whether any layout client feature is currently active.
     * 
     * @return whether any layout client feature is currently active
     */
    public static boolean isRemoteLayoutActive() {
        return preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
    }

    /**
     * Checks whether any layout client feature is installed.
     * 
     * @return whether any layout client feature is installed
     */
    public static boolean isRemoteLayoutInstalled() {
        return LayoutServiceClients.getInstance().countClients() != 0;
    }
    
    /**
     * Private Constructor for utility class.
     */
    private SwitchLayoutMode() {        
    }
    
}
