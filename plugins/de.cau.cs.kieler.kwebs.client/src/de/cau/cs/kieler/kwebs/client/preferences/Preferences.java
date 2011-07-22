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

package de.cau.cs.kieler.kwebs.client.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kwebs.client.activator.Activator;

/**
 * Preference constants used by the KWebS web service clients.
 *
 * @kieler.rating  2011-05-04 red
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
    public static final String PREFID_LAYOUT_PROVIDER_INDEX
        = PREFIX + "layout.provider.index";

    /** */
    public static final String PREFID_LAYOUT_PROVIDER_COUNT
        = PREFIX + "layout.provider.count";

    /** */
    public static final String PREFID_LAYOUT_PROVIDER_NAMEPREFIX
        = PREFIX + "layout.provider.nameprefix";

    /** */
    public static final String PREFID_LAYOUT_PROVIDER_ADDRESSPREFIX
        = PREFIX + "layout.provider.addressprefix";

    /** */
    public static final String PREFID_LAYOUT_PROVIDER_TRUSTSTOREPREFIX
        = PREFIX + "layout.provider.truststoreprefix";

    /** */
    public static final String PREFID_LAYOUT_PROVIDER_TRUSTSTOREPASSPREFIX
        = PREFIX + "layout.provider.truststorepassprefix";

    /** */
    public static final String PREFID_LAYOUT_PROVIDER_FIXEDPREFIX
        = PREFIX + "layout.provider.fixed";

    /**
     * This is a container class for preference store constants so no
     * instantiation is required.
     */
    private Preferences() {
    }

    /**
     * Returns the preference store for this plugin.
     * 
     * @return the preference store
     */
    public static IPreferenceStore getPreferenceStore() {
        return Activator.getInstance().getPreferenceStore();
    }

}
