package de.cau.cs.kieler.ksbase.core;

import org.eclipse.osgi.util.NLS;

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
