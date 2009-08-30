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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.LinkedList;

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

    private LinkedList<EditorTransformationSettings> registeredEditors;// The
    // currently
    // registered
    // editors

    // Thread-safe initialization
    public static TransformationManager instance = new TransformationManager();

    /**
     * Since this is a singleton class the constructor is private
     */
    private TransformationManager() {
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

    public void removeEditor(String editor) {
        for (int i = 0; i < registeredEditors.size(); ++i) {
            if (registeredEditors.get(i).getEditor().equals(editor))
                registeredEditors.remove(i);
        }
    }

    /**
     * Loads the editor settings from preference store
     */
    public void initializeTransformations() {
        registeredEditors = new LinkedList<EditorTransformationSettings>();
        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();

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
                settings.setToolbarLocation(store.getString(editor
                        + Messages.Preferences_ToolbarLocation));
                settings.setVisibilityFlags(store.getInt(editor
                        + Messages.Preferences_Visibility));
                settings.setDefaultIconURI(URI.create(store.getString(editor
                        + Messages.Preferences_DefaultIcon)));
                String[] transformations = store.getString(
                        editor + Messages.Preferences_Transformations).split(
                        ","); //$NON-NLS-2$
                for (String transformation : transformations) {
                    if (transformation.length() > 0) {
                        String prefix = editor + "." + transformation; //$NON-NLS-1$
                        Transformation t = new Transformation(store
                                .getString(prefix + ".Name"), transformation);
                        t
                                .setNumSelections(store
                                        .getInt(prefix
                                                + Messages.Preferences_Transformation_Selections));
                        t.setIconURI(URI.create(store.getString(prefix
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
            store.setValue(prefix + Messages.Preferences_ToolbarLocation,
                    settings.getToolbarLocation());
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
