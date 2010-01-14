/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in the de.cau.cs.kieler.ksbase.ui package.
 * 
 * @author Michael Matzen
 * 
 */
public final class Messages extends NLS {
    /** Name of the target bundle. **/
    private static final String BUNDLE_NAME = 
        "de.cau.cs.kieler.ksbase.ui.preferences.messages"; //$NON-NLS-1$
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEEPreferencePageTitle;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageTitle;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageButtonBrowse;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageEditTransformationsTitle;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageEditorSelectionTitle;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageModelPackage;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageTableColName;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageFileDialogTitle;
    /** Name of the localized message in messages.properties. **/
    public static String kSBasEPreferencePageFileInvalidFile;
    /** Name of the localized message in messages.properties. **/
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
