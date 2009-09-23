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
package de.cau.cs.kieler.ksbase.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * The main storage and management class. Contains a list of currently
 * registered editors. Manages the creation of ExecuteTransformationCommands.
 * Handles import, export and Xtend file parsing.
 * 
 * @author Michael Matzen
 * 
 */
public class TransformationManager {

    private LinkedList<EditorTransformationSettings> registeredEditors;// The currently registered editors
    private boolean workspaceSettings;
    
    // Thread-safe initialization
    public static TransformationManager instance = new TransformationManager();

    /**
     * Since this is a singleton class the constructor is private
     */
    private TransformationManager() {
        workspaceSettings = false;
        registeredEditors = new LinkedList<EditorTransformationSettings>();
    }

    /**
     * Get the currently registered editors
     * 
     * @return A list of the registered editors
     */
    public LinkedList<EditorTransformationSettings> getEditors() {
        return registeredEditors;
    }

    /**
     * Tries to find an editor with it's name.
     * 
     * @param editor
     *            The editor's name.
     * @return The first editor in the list of registered editors which has the
     *         given name
     */
    public EditorTransformationSettings getEditorByName(String editor) {
        for (EditorTransformationSettings settings : registeredEditors) {
            if (settings.getEditor().equals(editor))
                return settings;
        }
        return null;
    }

    /**
     * Adds a new editor to the list of registered editors.
     * 
     * @param editor
     *            The EditorTransformationSetting that describes the editor
     */
    public void addEditor(EditorTransformationSettings editor) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        if (editor.getEditor().length() > 0)
            registeredEditors.add(editor);
    }

    /**
     * Adds a new editor to the list of registered editors. This class creates
     * an empty EditorTransformationSetting with the given editorName.
     * 
     * @param editorName
     *            The name of the new editor
     * @return The newly created EditorTransformationSettings
     */
    public EditorTransformationSettings addEditor(String editorName) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        if (editorName.length() > 0) {
            EditorTransformationSettings editor = new EditorTransformationSettings(
                    editorName);
            registeredEditors.add(editor);
            return editor;
        } else
            return null;
    }

    /**
     * Removes an editor from the registry. 
     * @param editor The fqn class name of the editor
     */
    public void removeEditor(String editor) {
        for (int i = 0; i < registeredEditors.size(); ++i) {
            if (registeredEditors.get(i).getEditor().equals(editor))
                registeredEditors.remove(i);
        }
    }
    
    /**
     * Returns true if the workspace specific settings are enabled.
     * @return
     */
    public boolean isWorkspaceSpecific() {
        return workspaceSettings;
    }
    
    /**
     * Sets the workspace specific settings flag.
     * After setting the flag and storing it in 
     * the preference store, the transformations are reinitialized from the new source
     * @param value
     */
    public void setWorkspaceSpecific(boolean value) {
        workspaceSettings = value;
        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
        store.setValue("workspaceSpecific", value);
        if (!value) {
        	//When deactivating workspace settings, we will overwrite changes
        	//here but the user will know.
        	//Read from ext points
        	initializeTransformations();
        	//store in pref page
        	storeTransformations();
        }
        
    }
    
    /**
     * Loads the editor settings either from the extension point settings
     * or the preference store
     */
    public void initializeTransformations() {
        registeredEditors = new LinkedList<EditorTransformationSettings>();
        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
        workspaceSettings = store.getBoolean("workspaceSpecific");
       
        if (store.getBoolean("workspaceSpecific")) {
            String[] editors = store.getString(
                    Messages.Preferences_RegisteredEditors).split(","); //$NON-NLS-2$
            for (String editor : editors) {
                if (editor.length() > 0) {
                    EditorTransformationSettings settings = new EditorTransformationSettings(
                            editor);
                    settings.setModelPackageClass(store.getString(editor
                            + Messages.Preferences_ModelPackageClass));
                    settings.setExtFile(store.getString(editor
                            + Messages.Preferences_ExtFile));
                    settings.setMenuName(store.getString(editor
                            + Messages.Preferences_MenuName));
                    settings.setMenuLocation(store.getString(editor
                            + Messages.Preferences_MenuLocation));
                    settings.setPopupLocation(store.getString(editor
                            + Messages.Preferences_PopupLocation));
                    settings.setToolbarLocation(store.getString(editor
                            + Messages.Preferences_ToolbarLocation));
                    settings.setVisibilityFlags(store.getInt(editor
                            + Messages.Preferences_Visibility));
                    settings.setDefaultIconURI(URI.create(store
                            .getString(editor
                                    + Messages.Preferences_DefaultIcon)));
                    settings.setContext(store.getString(editor + ".Context"));
                    String[] transformations = store.getString(
                            editor + Messages.Preferences_Transformations)
                            .split(","); //$NON-NLS-2$
                    for (String transformation : transformations) {
                        if (transformation.length() > 0) {
                            String prefix = editor + "." + transformation; //$NON-NLS-1$
                            Transformation t = new Transformation(store
                                    .getString(prefix + ".Name"),
                                    transformation);
                            t
                                    .setNumSelections(store
                                            .getInt(prefix
                                                    + Messages.Preferences_Transformation_Selections));
                            t
                                    .setIconURI(URI
                                            .create(store
                                                    .getString(prefix
                                                            + Messages.Preferences_Transformation_Icon)));
                            t
                                    .setKeyboardShortcut(store
                                            .getString(prefix
                                                    + Messages.Preferences_Transformation_Shortcut));
                            t
                                    .setPartConfig(store
                                            .getString(
                                                    prefix
                                                            + Messages.Preferences_Transformation_PartConfig)
                                            .split(",")); //$NON-NLS-1$
                            settings.addTransformation(t);
                        }
                    }
                    registeredEditors.add(settings);
                }
            }
        }
        else {
            //use extension points
            IConfigurationElement[] configurations = Platform.getExtensionRegistry().getConfigurationElementsFor("de.cau.cs.kieler.ksbase.configuration");
            for ( IConfigurationElement settings : configurations) {
                EditorTransformationSettings editor = new EditorTransformationSettings(settings.getAttribute("editor"));
                editor.setContext(settings.getAttribute("contextID"));
                editor.setMenuLocation(settings.getAttribute("menuLocationURI"));
                editor.setPopupLocation(settings.getAttribute("popupLocationURI"));
                editor.setMenuName(settings.getAttribute("menuName"));
                editor.setToolbarLocation(settings.getAttribute("toolbarLocationURI"));
                int flags = 0;
                if ( settings.getAttribute("createMenu") != null && settings.getAttribute("createMenu").equals("true"))
                    flags += KSBasEPlugin.SHOW_MENU;
                if ( settings.getAttribute("createPopup") != null && settings.getAttribute("createPopup").equals("true"))
                    flags += KSBasEPlugin.SHOW_CONTEXT;
                editor.setVisibilityFlags(flags);
                editor.setModelPackageClass(settings.getAttribute("packageName"));
                editor.setPerformAutoLayout(true);
                for ( IConfigurationElement t : settings.getChildren("transformation")) {
                    Transformation transformation = new Transformation(t.getAttribute("name"), t.getAttribute("transformation"));
                    transformation.setNumSelections(Integer.valueOf(t.getAttribute("selectionCount")));
                    transformation.setKeyboardShortcut(t.getAttribute("keyboardShortcut"));
                    int tflags = 0;
                    if ( t.getAttribute("showInMenu") != null && t.getAttribute("showInMenu").equals("true"))
                        tflags += KSBasEPlugin.SHOW_MENU;
                    if ( t.getAttribute("showInPopup") != null && t.getAttribute("showInPopup").equals("true"))
                        tflags += KSBasEPlugin.SHOW_CONTEXT;
                    transformation.setVisibility(tflags);
                    IConfigurationElement[] parts = t.getChildren("element_selection");
                    if ( parts != null && parts.length > 0) {
                        String[] partConfig = new String[parts.length];
                        for (int i = 0; i < parts.length; ++i) {
                            partConfig[i] = parts[i].getAttribute("class");
                        }
                        transformation.setPartConfig(partConfig);
                    }
                    editor.addTransformation(transformation);
                }
                //Read Xtend file from extension point configuration
                String content = "";
                InputStream path;
                    try {
                        path = Platform.getBundle(settings.getContributor().getName()).getEntry("/"+settings.getAttribute("XtendFile")).openStream();
                        while ( path.available() > 0) {
                            content += (char)path.read();
                        }
                        editor.setExtFile(content);
                    } catch (IOException e) {
                        System.err.println("KSBasE configuration exception: Can't read Xtend file");
                    }

                registeredEditors.add(editor);
            }
        }
    }

    /**
     * Imports settings from an external file
     * 
     * @param file
     *            The absolute file name
     */
    @SuppressWarnings("unchecked")
    public void importSettings(String file) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    file));
            registeredEditors = new LinkedList<EditorTransformationSettings>();
            registeredEditors = (LinkedList<EditorTransformationSettings>) in
                    .readObject();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores the currently registered editor settings to the preference store
     * Stores the values as strings: EditorName.ModelPackageName
     * EditorName.MenuName etc.
     */
    public void storeTransformations() {
        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
        String editors = ""; //$NON-NLS-1$
        for (EditorTransformationSettings settings : registeredEditors) {
            String prefix = settings.getEditor();
            editors += prefix + ","; //$NON-NLS-1$
            store.setValue(prefix + Messages.Preferences_ModelPackageClass,
                    settings.getModelPackageClass());
            store.setValue(prefix + Messages.Preferences_ExtFile, settings
                    .getExtFile());
            store.setValue(prefix + Messages.Preferences_MenuName, settings
                    .getMenuName());
            store.setValue(prefix + Messages.Preferences_MenuLocation, settings
                    .getMenuLocation());
            store.setValue(prefix + Messages.Preferences_PopupLocation, settings
                    .getPopupLocation());
            store.setValue(prefix + Messages.Preferences_ToolbarLocation,
                    settings.getToolbarLocation());
            store.setValue(prefix + ".Context", settings.getContext());
            store.setValue(prefix + Messages.Preferences_Visibility, settings
                    .getVisibility());
            store.setValue(prefix + Messages.Preferences_DefaultIcon, settings
                    .getDefaultIconURI().toString());
            String transformations = ""; //$NON-NLS-1$
            for (Transformation t : settings.getTransformations()) {
                String tprefix = prefix + "." + t.getTransformationName(); //$NON-NLS-1$
                transformations += t.getTransformationName() + ","; //$NON-NLS-1$
                store.setValue(tprefix + ".Name", t.getName());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_Selections, t
                        .getNumSelections());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_PartConfig, t
                        .getPartConfigList());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_Icon, t
                        .getIconString());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_Shortcut, t
                        .getKeyboardShortcut());
            }
            if (transformations.length() > 0) {
                store.setValue(prefix + Messages.Preferences_Transformations,
                        transformations.substring(0,
                                transformations.length() - 1));
            }
        }
        if (editors.length() > 0)
        // trunc the last ','
        store.setValue(Messages.Preferences_RegisteredEditors, editors
                .substring(0, editors.length() - 1));

    }

    /**
     * Exports the settings to an external file
     * 
     * @param file
     *            The absolute file name
     */
    public void exportSettings(String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            out.writeObject(registeredEditors);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
