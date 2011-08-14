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

package de.cau.cs.kieler.kwebs.client.kiml.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kwebs.client.kiml.activator.Activator;

/**
 * Preference constants used by the KWebS web service clients.
 *
 * @kieler.rating  2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *     
 * @author  swe
 */
public final class Preferences {

    /** Common prefix for property identifiers. */
    private static final String PREFIX
        = "de.cau.cs.kieler.kwebs.client.kieler.pref.";

    /** */
    public static final String PREFID_LAYOUT_SETTINGS_CHANGED
        = PREFIX + "layout.settings.changed";

    /** */
    public static final String PREFID_LAYOUT_USE_REMOTE
        = PREFIX + "layout.use.remote";

    /** */
    public static final String PREFID_LAYOUT_SERVERCONFIG_COUNT
        = PREFIX + "layout.serverconfig.count";

    /** */
    public static final String PREFID_LAYOUT_SERVERCONFIG_NAMEPREFIX
        = PREFIX + "layout.serverconfig.nameprefix";

    /** */
    public static final String PREFID_LAYOUT_SERVERCONFIG_ADDRESSPREFIX
        = PREFIX + "layout.serverconfig.addressprefix";

    /** */
    public static final String PREFID_LAYOUT_SERVERCONFIG_TRUSTSTOREPREFIX
        = PREFIX + "layout.serverconfig.truststoreprefix";

    /** */
    public static final String PREFID_LAYOUT_SERVERCONFIG_TRUSTSTOREPASSPREFIX
        = PREFIX + "layout.serverconfig.truststorepassprefix";

    /** Index prefix for the preference defining whether a provider is fixed.  */
    public static final String PREFID_LAYOUT_SERVERCONFIG_FIXEDPREFIX
        = PREFIX + "layout.serverconfig.fixed";

    /** Index prefix for the preference defining whether a provider is the currently active provider.  */
    public static final String PREFID_LAYOUT_SERVERCONFIG_ACTIVEPREFIX
        = PREFIX + "layout.serverconfig.active";

    /** Index prefix for the preference defining whether a provider is the standard provider.  */
    public static final String PREFID_LAYOUT_SERVERCONFIG_STANDARDPREFIX
        = PREFIX + "layout.serverconfig.standard";

    /**
     * This is a container class for preference store constants so no
     * instantiation is required.
     */
    private Preferences() {
    }

    /**
     * Returns the preference store for this plug-in.
     * 
     * @return the preference store
     */
    public static IPreferenceStore getPreferenceStore() {
        return Activator.getInstance().getPreferenceStore();
    }

}
