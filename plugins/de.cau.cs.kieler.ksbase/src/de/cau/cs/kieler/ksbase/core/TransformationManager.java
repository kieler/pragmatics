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
package de.cau.cs.kieler.ksbase.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.model.transformation.ITransformationFramework;
import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * The main storage and management class. Contains a list of currently registered editors. Handles
 * import and export.
 * 
 * @author mim
 * 
 * @kieler.rating 2010-01-22 yellow
 *          review by msp, skn
 */
public final class TransformationManager {

    /**
     * The currently active editors that have been registered by using the extension point.
     **/
    private HashMap<String, EditorTransformationSettings> activeEditors;
    /** The editors that have been added by the user using the preference pages. **/
    private HashMap<String, EditorTransformationSettings> activeUserEditors;

    /** Transformation-Manager instance. **/
    public static final TransformationManager INSTANCE = new TransformationManager();

    /** Id of the KSBasE configuration extension point. **/
    private static final String KSBASE_EXTENSIONPOINT = "de.cau.cs.kieler.ksbase.configurations";
    /** Id of the KSBasE configuration extension point. **/
    private static final String KSBASE_LOADER_EXTENSIONPOINT = "de.cau.cs.kieler.ksbase.activator";

    /**
     * FileNameFilter to check a file for a valid settings file. The extension has to be '.sbase' to
     * be valid.
     * 
     * @author Michael Matzen - mim AT informatik.uni-kiel.de
     * 
     */
    private static class KSBasEFileNameFilter implements FilenameFilter {
        /**
         * Checks if the given input is valid.
         * 
         * @param dir
         *            The file directory.
         * @param name
         *            The filename.
         * @return True if the extension of the given file is .sbase
         */
        public boolean accept(final File dir, final String name) {
            if (name.endsWith(".sbase")) {
                return true;
            }
            return false;
        }

    }

    /**
     * Since this is a singleton class the constructor is private.
     */
    private TransformationManager() {
        activeEditors = new HashMap<String, EditorTransformationSettings>();
        activeUserEditors = new HashMap<String, EditorTransformationSettings>();
    }

    /**
     * Gets the currently registered editors.
     * 
     * @return A list of EditorTransformationSettings
     */
    public Collection<EditorTransformationSettings> getEditors() {
        return Collections.unmodifiableCollection(activeEditors.values());
    }

    /**
     * Gets the list of user defined editors. This is done by checking if the contributor is 'null'
     * and is used by the preference page.
     * 
     * @return A list of EditorTransformationSettings
     */
    public Collection<EditorTransformationSettings> getUserDefinedEditors() {
        return Collections.unmodifiableCollection(activeUserEditors.values());
    }

    /**
     * Tries to find an editor with it's id.
     * 
     * @param editorId
     *            The editor's id.
     * @return The first editor in the list of registered editors which has the given name
     */
    public EditorTransformationSettings getEditorById(final String editorId) {
        if (activeEditors.containsKey(editorId)) {
            return activeEditors.get(editorId);
        } else if (activeUserEditors.containsKey(editorId)) {
            return activeUserEditors.get(editorId);
        } else {
            return null;
        }
    }

    /**
     * Tries to find an user defined editor with its id.
     * 
     * @param editorId
     *            The editor's id.
     * @return The first editor in the list of registered editors which has the given name
     */
    public EditorTransformationSettings getUserDefinedEditorById(final String editorId) {
        return activeUserEditors.get(editorId);
    }

    /**
     * Adds a new editor to the list of user defined editors.
     * 
     * @param editor
     *            The EditorTransformationSetting that describes the editor
     */
    public void addEditor(final EditorTransformationSettings editor) {
        if (editor != null) {

            if (activeUserEditors.containsKey(editor.getEditorId())) {
                KSBasEPlugin.getDefault().logInfo("Unable to add the same editor twice.");

            } else {
                // We are using the default framework here
                editor.setFramework(TransformationFrameworkFactory
                        .getDefaultTransformationFramework());
                activeUserEditors.put(editor.getEditorId(), editor);
            }
        }
    }

    /**
     * Adds a new editor to the list of user defined editors. This class creates an empty
     * EditorTransformationSetting with the given editorId.
     * 
     * @param editorId
     *            The name of the new editor
     * @return The newly created EditorTransformationSettings
     */
    public EditorTransformationSettings addEditor(final String editorId) {
        if (editorId != null && editorId.length() > 0 && !activeEditors.containsKey(editorId)) {
            EditorTransformationSettings editor = new EditorTransformationSettings(editorId);
            activeUserEditors.put(editorId, editor);
            return editor;
        }
        return null;
    }

    /**
     * Removes an editor from the list of user defined editors.
     * 
     * @param editorId
     *            The id of the editor
     */
    public void removeEditor(final String editorId) {
        if (editorId != null && editorId.length() > 0) {
            EditorTransformationSettings editor = activeUserEditors.get(editorId);
            if (editor != null) {
                activeUserEditors.remove(editorId);
                // Remove exsiting .sbase file settings for editor:
                try {
                    IPath metaPath = KSBasEPlugin.getDefault().getStateLocation();
                    File storedSettings = metaPath.append(editorId + ".sbase").toFile();
                    if (storedSettings.exists()) {
                        if (!storedSettings.delete()) {
                            KSBasEPlugin.getDefault().logWarning(
                                    "Error while deleting settings: not allowed to delete file.");
                        }
                    }
                } catch (IllegalStateException e) {
                    KSBasEPlugin.getDefault().logWarning(
                            "Error while deleting settings: "
                                    + "could not find KSBasE plug-in state location.");
                }
            }
        }
    }

    /**
     * Stores the user defined settings in the KSbasE state location folder.
     */
    public void storeUserDefinedTransformations() {

        if (activeUserEditors.size() > 0) {
            IPath metaPath = KSBasEPlugin.getDefault().getStateLocation();
            for (EditorTransformationSettings editor : activeUserEditors.values()) {
                ObjectOutputStream oos = null;
                try {
                    File f = metaPath.append(editor.getEditorId() + ".sbase").toFile();
                    if (f.exists()) {
                        f.delete();
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(metaPath.append(
                            editor.getEditorId() + ".sbase").toFile()));
                    oos.writeObject(editor);

                } catch (FileNotFoundException e) {
                    KSBasEPlugin.getDefault().logError(
                            "Error while storing settings: file not found.");
                } catch (IOException e) {
                    KSBasEPlugin.getDefault().logError(
                            "Error while parsing settings: file could not be read.");
                } finally {
                    if (oos != null) {
                        try {
                            oos.flush();
                            oos.close();
                        } catch (IOException e) {
                            // ignoring nested exception
                        }
                    }
                }
            }

        }
    }

    /**
     * Reads settings from KSBasE storage files and initializes user defined editors and
     * transformations.
     */
    private void initalizeUserSettings() {

        // Now let's load the settings from the state location:
        activeUserEditors = new HashMap<String, EditorTransformationSettings>();
        File metaPath = KSBasEPlugin.getDefault().getStateLocation().toFile();
        if (metaPath != null && metaPath.isDirectory()) {

            File[] files = metaPath.listFiles(new KSBasEFileNameFilter());

            if (files != null && files.length > 0) {
                for (File file : files) {
                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        Object content = ois.readObject();
                        if (content instanceof EditorTransformationSettings) {
                            activeUserEditors.put(((EditorTransformationSettings) content)
                                    .getEditorId(), ((EditorTransformationSettings) content));
                        }
                    } catch (FileNotFoundException e) {
                        KSBasEPlugin.getDefault().logError(
                                "Error while parsing user defined settings file not found.");
                    } catch (IOException e) {
                        KSBasEPlugin.getDefault().logError(
                                "Error while parsing user defined settings, file "
                                        + file.getAbsolutePath() + " seems to be corrupted.");
                    } catch (ClassNotFoundException e) {
                        KSBasEPlugin.getDefault().logError("Error while parsing settings.");
                    } finally {
                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (IOException e) {
                                // ignoring nested exception
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Reads all existing extensions of the KSBasE extension point and initializes the editors and
     * transformations.
     */
    private void initializeExtensionPoints() {
        activeEditors = new HashMap<String, EditorTransformationSettings>();

        // From extension points first:
        IConfigurationElement[] configurations = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(KSBASE_EXTENSIONPOINT);
        for (IConfigurationElement settings : configurations) {

            // Check for valid Configuration:
            if (!settings.getName().equals("configuration")
                    || settings.getAttribute("editorId") == null
                    || settings.getAttribute("packageName") == null) {
                KSBasEPlugin.getDefault().logWarning("Invalid KSBasE extension point found.");
                continue;
            }

            EditorTransformationSettings editor = new EditorTransformationSettings(settings
                    .getAttribute("editorId"));
            editor.setContributor(settings.getContributor());
            editor.setContext(settings.getAttribute("contextId"));
            editor.setDefaultIcon(settings.getAttribute("defautlIcon"));
            editor.setModelPackageClass(settings.getAttribute("packageName"));

            IConfigurationElement[] transformations = settings.getChildren("transformations");
            if (transformations != null && transformations.length > 0) {
                // since we only allowed one single <transformations> child, we
                // are using it w/o iteration
                for (IConfigurationElement t : transformations[0].getChildren("transformation")) {
                    // Check for valid Configuration:
                    if (t == null || t.getAttribute("name") == null
                            || t.getAttribute("transformation") == null
                            || t.getAttribute("transformationId") == null) {
                        KSBasEPlugin.getDefault().logWarning(
                                "Invalid KSBasE configuration found. Please check "
                                        + "transformations defined for " + editor.getEditorId());
                    }
                    KSBasETransformation transformation = new KSBasETransformation(t
                            .getAttribute("name"), t.getAttribute("transformation"));
                    transformation.setKeyboardShortcut(t.getAttribute("keyboardShortcut"));
                    transformation.setTransformationId(t.getAttribute("transformationId"));
                    transformation.setIcon(t.getAttribute("icon"));
                    transformation.setValidation(t.getAttribute("validation"));
                    editor.addTransformation(transformation);
                }
            }
            // read alternate command handler
            IConfigurationElement[] commandHandler = settings.getChildren("commandHandler");
            if (commandHandler != null && commandHandler.length == 1
                    && commandHandler[0].getAttribute("class") != null) {
                editor.setCommandHandler(settings.getAttribute("class"));
            } else {
                // set empty value, so the default handler will be used
                editor.setCommandHandler("");
            }
            IConfigurationElement[] tFactory = settings.getChildren("transformationFactory");
            if (tFactory != null && tFactory.length == 1
                    && tFactory[0].getAttribute("class") != null) {
                try {
                    editor.setFramework((ITransformationFramework) tFactory[0]
                            .createExecutableExtension("class"));
                } catch (CoreException ce) {
                    KSBasEPlugin.getDefault().logError(
                            "Invalid transformation framework specified\t Configuration : "
                                    + editor.getEditorId() + " Framework : "
                                    + tFactory[0].getAttribute("class"));
                }
            } else {
                // If no framework has been set, use the default framework
                editor.setFramework(TransformationFrameworkFactory
                        .getDefaultTransformationFramework());
            }
            // Read menu contributions
            IConfigurationElement[] menus = settings.getChildren("menus");

            if (menus != null && menus.length > 0) {
                // since we only allowed one single <menuContribution> child, we
                // are using it w/o iteration
                for (IConfigurationElement c : menus[0].getChildren("menuContribution")) {
                    KSBasEMenuContribution contrib = new KSBasEMenuContribution(c
                            .getAttribute("locationURI"));
                    for (IConfigurationElement m : c.getChildren("menu")) {
                        KSBasEMenuContribution menu = new KSBasEMenuContribution(m
                                .getAttribute("id"));
                        menu.setLabel(m.getAttribute("label"));
                        for (IConfigurationElement com : m.getChildren()) {
                            menu.addCommand(com.getAttribute("transformationId"));
                        }
                        contrib.addSubMenu(menu);
                    }
                    for (IConfigurationElement com : c.getChildren("transformationCommand")) {
                        contrib.addCommand(com.getAttribute("transformationId"));
                    }
                    editor.addMenuContribution(contrib);
                }
            }

            // Read file from extension point configuration
            InputStream inStream;
            StringBuffer contentBuffer = new StringBuffer();
            try {
                if (settings.getContributor() != null) {
                    String transformationFile = null;
                    if (settings.getAttribute("TransformationFile") != null) {
                        transformationFile = settings.getAttribute("TransformationFile");
                    } else if (settings.getAttribute("XtendFile") != null) {
                        transformationFile = settings.getAttribute("XtendFile");
                        KSBasEPlugin.getDefault().logWarning(
                                "The KSBasE configuration for the editor : " + editor.getEditorId()
                                        + " uses the deprecated configuration attribute"
                                        + " 'XtendFile' please use the new "
                                        + "'TransformationFile' attribute ");
                    }
                    if (transformationFile == null) {
                        KSBasEPlugin.getDefault().logError(
                                "Missing attribute 'TransformationFile' the extension point"
                                        + " configuration for " + editor.getEditorId());
                        continue;
                    }
                    Bundle bundle = Platform.getBundle(settings.getContributor().getName());
                    if (bundle != null) {
                        URL urlPath = bundle.getEntry("/" + transformationFile);
                        // Parse transformation file to read transformations and
                        // parameters now:
                        editor.parseTransformations(false, urlPath);
                        if (urlPath != null) {
                            inStream = urlPath.openStream();
                            while (inStream.available() > 0) {
                                contentBuffer.append((char) inStream.read());

                            }
                        }
                    }
                    // Write transformation file to .metadata
                    IPath path = ResourcesPlugin.getPlugin().getStateLocation();
                    // Transformation file name:
                    path = path.append(editor.getEditorId());
                    // Add extension:
                    path = path.addFileExtension(editor.getFramework().getFileExtension());

                    File file = new File(path.toOSString());
                    if (file != null) {
                        FileOutputStream out = new FileOutputStream(file);
                        if (out != null) {
                            if (!file.exists()) {
                                if (!file.createNewFile()) {
                                    KSBasEPlugin.getDefault().logError(
                                            "Error while storing transformation file for editor: "
                                                    + editor.getEditorId());
                                }
                            }

                            out.write(contentBuffer.toString().getBytes());
                            out.flush();
                            out.close();
                        }
                        // Set delete on exit flag, so the files will be cleaned when exiting
                        // eclipse
                        file.deleteOnExit();
                        editor.setTransformationFile(file.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                KSBasEPlugin.getDefault().logWarning(
                        "KSBasE configuration exception: Can't read transformation file for editor :"
                                + editor.getEditorId());
            }

            activeEditors.put(editor.getEditorId(), editor);
        }
    }

    /**
     * Activates all projects that are using the project loader extension point.
     */
    private void activateProjects() {
        // From extension points first:
        IConfigurationElement[] configurations = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(KSBASE_LOADER_EXTENSIONPOINT);
        if (configurations != null) {
            for (IConfigurationElement config : configurations) {
                if (config != null && config.getAttribute("class") != null) {
                    try {
                        config.createExecutableExtension("class");
                    } catch (CoreException e) {
                        //There are maybe some dependency tree related exceptions here, but the
                        //actual project was activated so we do not care.
                    }
                }
            }
        }
    }

    /**
     * Initializes the transformation manager by reading the extension points and the user defined
     * settings.
     */
    public void initializeTransformations() {
        initializeExtensionPoints();
        initalizeUserSettings();
        activateProjects();
    }

}
