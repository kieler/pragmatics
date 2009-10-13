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
package de.cau.cs.kieler.ksbase.ui.handler;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in the de.cau.cs.kieler.ksbase.ui.handler package
 * @author Michael Matzen
 *
 */
public class Messages extends NLS {
    private static final String BUNDLE_NAME = "de.cau.cs.kieler.ksbase.core.messages"; //$NON-NLS-1$
    public static String EditorTransformationSettings_Default_Menu_Location;
    public static String EditorTransformationSettings_Default_Menu_Name;
    public static String EditorTransformationSettings_Default_Toolbar_Location;
    public static String ExecuteTransformationCommand_Workflow_Initialization_Error;
    public static String ExecuteTransformationCommand_Workflow_Invoke_Error;
    public static String ExecuteTransformationEditPolicy_Transformation_Command_Name;
    public static String Preferences_DefaultIcon;
    public static String Preferences_ExtFile;
    public static String Preferences_MenuLocation;
    public static String Preferences_MenuName;
    public static String Preferences_ModelPackageClass;
    public static String Preferences_RegisteredEditors;
    public static String Preferences_ToolbarLocation;
    public static String Preferences_Transformation_Command;
    public static String Preferences_Transformation_Icon;
    public static String Preferences_Transformation_Name;
    public static String Preferences_Transformation_PartConfig;
    public static String Preferences_Transformation_Selections;
    public static String Preferences_Transformation_Shortcut;
    public static String Preferences_Transformations;
    public static String Preferences_Visibility;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
