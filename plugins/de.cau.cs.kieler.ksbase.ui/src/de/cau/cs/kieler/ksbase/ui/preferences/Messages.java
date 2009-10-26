/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
 * Messages used in the de.cau.cs.kieler.ksbase.ui package
 * 
 * @author Michael Matzen
 * 
 */
public class Messages extends NLS {
    private static final String BUNDLE_NAME = "de.cau.cs.kieler.ksbase.ui.preferences.messages"; //$NON-NLS-1$
    public static String KSBasEPreferencePage_Button_Browse;
    public static String KSBasEPreferencePage_Button_Cancel;
    public static String KSBasEPreferencePage_Button_Edit_Transformations;
    public static String KSBasEPreferencePage_Button_OK;
    public static String KSBasEPreferencePage_DefaultIcon;
    public static String KSBasEPreferencePage_DefaultIconName;
    public static String KSBasEPreferencePage_EditorSelection_Title;
    public static String KSBasEPreferencePage_EditTransformations_Message;
    public static String KSBasEPreferencePage_EditTransformations_Title;
    public static String KSBasEPreferencePage_IconExtension_ICO;
    public static String KSBasEPreferencePage_IconExtension_PNG;
    public static String KSBasEPreferencePage_ModelPackage;
    public static String KSBasEPreferencePage_ModelPackage_NoPackageFound_Default;
    public static String KSBasEPreferencePage_ModelPackage_NoPackageFound_Message;
    public static String KSBasEPreferencePage_ModelPackage_NoPackageFound_Title;
    public static String KSBasEPreferencePage_Shortcut_ALT;
    public static String KSBasEPreferencePage_Shortcut_CTRL;
    public static String KSBasEPreferencePage_Shortcut_SHIFT;
    public static String KSBasEPreferencePage_Table_Col_Id;
    public static String KSBasEPreferencePage_Table_Col_Id_ToolTip;
    public static String KSBasEPreferencePage_Table_Col_Icon;
    public static String KSBasEPreferencePage_Table_Col_Name;
    public static String KSBasEPreferencePage_Table_Col_Name_ToolTip;
    public static String KSBasEPreferencePage_Table_Col_Shortcut;
    public static String KSBasEPreferencePage_Table_Col_Shortcut_ToolTip;
    public static String KSBasEPreferencePage_Table_Col_Transformation;
    public static String KSBasEPreferencePage_Table_Col_Transformation_ToolTip;
    public static String KSBasEPreferencePage_TableTitle;
    public static String KSBasEPreferencePage_Title;
    public static String KSBasEPreferencePage_XtendFile;
    public static String KSBasEPreferencePage_XtendFile_DefaultName;
    public static String KSBasEPreferencePage_XtendFile_DialogTitle;
    public static String KSBasEPreferencePage_XtendFile_Extension;
    public static String KSBasEPreferencePage_XtendFile_InvalidFile;
    static {
	// initialize resource bundle
	NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
