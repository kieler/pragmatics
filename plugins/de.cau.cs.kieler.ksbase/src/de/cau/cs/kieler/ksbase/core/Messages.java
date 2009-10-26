/******************************************************************************
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
package de.cau.cs.kieler.ksbase.core;

import org.eclipse.osgi.util.NLS;

/**
 * The externalized messages used in the de.cau.cs.kieler.ksbase package.
 * 
 * @author Michael Matzen
 * 
 */
public class Messages extends NLS {
    /** Name of the target bundle **/
    private static final String BUNDLE_NAME = "de.cau.cs.kieler.ksbase.core.messages"; //$NON-NLS-1$
    /** Name of the localized message in messages.properties **/
    public static String EditorTransformationSettings_Default_Menu_Location;
    /** Name of the localized message in messages.properties **/    
    public static String EditorTransformationSettings_Default_Menu_Name;
    /** Name of the localized message in messages.properties **/
    public static String EditorTransformationSettings_Default_Toolbar_Location;
    /** Name of the localized message in messages.properties **/
    public static String ExecuteTransformationCommand_Workflow_Initialization_Error;
    /** Name of the localized message in messages.properties **/
    public static String ExecuteTransformationCommand_Workflow_Invoke_Error;
    /** Name of the localized message in messages.properties **/
    public static String ExecuteTransformationEditPolicy_Transformation_Command_Name;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_DefaultIcon;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_ExtFile;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_MenuLocation;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_PopupLocation;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_MenuName;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_MenuId;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_ModelPackageClass;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_RegisteredEditors;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_ToolbarLocation;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformation_Command;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformation_Icon;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformation_Name;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformation_PartConfig;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformation_Selections;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformation_Shortcut;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Transformations;
    /** Name of the localized message in messages.properties **/
    public static String Preferences_Visibility;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
