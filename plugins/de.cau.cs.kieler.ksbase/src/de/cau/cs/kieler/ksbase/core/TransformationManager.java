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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * The main storage and management class. Contains a list of currently
 * registered editors. Handles import, export and Xtend file parsing.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 * @kieler.rating 2009-11-19 proposed yellow
 * 
 */
public final class TransformationManager {

    /**
     * The currently active editors that have been registered by using the
     * extension point.
     **/
    private HashMap<String, EditorTransformationSettings> activeEditors;
    /** The editors that have been added by the user using the preference pages. **/
    private HashMap<String, EditorTransformationSettings> activeUserEditors;

    /**
     * Manage state, set to true when
     * {@link TransformationManager.initializeTransformations} has been called.
     **/
    private boolean isInitialized;

    /** Logging interface. **/
    private static final ILog LOG = KSBasEPlugin.getDefault().getLog();

    /** Transformation-Manager instance. **/
    public static final TransformationManager INSTANCE = new TransformationManager();

    /**
     * FileNameFilter to check a file for a valid settings file. The extension
     * has to be '.sbase' to be valid.
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
        isInitialized = false;
        activeEditors = new HashMap<String, EditorTransformationSettings>();
        activeUserEditors = new HashMap<String, EditorTransformationSettings>();
    }

    /**
     * Gets the currently registered editors.
     * 
     * @return A list of EditorTransformationSettings
     */
    public Map<String, EditorTransformationSettings> getEditors() {
        return Collections.unmodifiableMap(activeEditors);
    }

    /**
     * Gets the list of user defined editors. This is done by checking if the
     * contributor is 'null' and is used by the preference page.
     * 
     * @return A list of EditorTransformationSettings
     */
    public Map<String, EditorTransformationSettings> getUserDefinedEditors() {
        return Collections.unmodifiableMap(activeUserEditors);
    }

    /**
     * Tries to find an editor with it's id.
     * 
     * @param editorId
     *            The editor's id.
     * @return The first editor in the list of registered editors which has the
     *         given name
     */
    public EditorTransformationSettings getEditorById(final String editorId) {
        return activeEditors.get(editorId);
    }

    /**
     * Tries to find an user defined editor with it's id.
     * 
     * @param editorId
     *            The editor's id.
     * @return The first editor in the list of registered editors which has the
     *         given name
     */
    public EditorTransformationSettings getUserDefinedEditorByName(final String editorId) {
        return activeUserEditors.get(editorId);
    }

    /**
     * @return True if the manager is initialized.
     */
    public boolean isInitialized() {
        return isInitialized;
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
                LOG.log(new Status(
                        IStatus.INFO, KSBasEPlugin.PLUGIN_ID,
                        "Unable to add the same editor twice."));

            } else {
                activeUserEditors.put(editor.getEditorId(), editor);
            }
        }
    }

    /**
     * Adds a new editor to the list of user defined editors. This class creates
     * an empty EditorTransformationSetting with the given editorId.
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
                            throw new SecurityException("Unable to delete stored settings!");
                        }
                    }
                } catch (IllegalStateException e) {
                    LOG.log(new Status(
                            IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "Error while deleting settings: file could not be found."));
                } catch (SecurityException e) {
                    LOG.log(new Status(
                            IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "Error while parsing settings: not allowed to delete file."));
                }
            }
        }
    }

    /**
     * Stores the user defined settings in the KSbasE state location folder.
     */
    public void storeUserDefinedTransformations() {

        if (activeEditors.size() > 0) {
            IPath metaPath = KSBasEPlugin.getDefault().getStateLocation();
            for (EditorTransformationSettings editor : activeUserEditors.values()) {
                ObjectOutputStream oos = null;
                try {
                    oos =
                            new ObjectOutputStream(new FileOutputStream(metaPath.append(
                                    editor.getEditorId() + ".sbase").toFile()));
                    oos.writeObject(editor);

                } catch (FileNotFoundException e) {
                    LOG.log(new Status(
                            IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "Error while storing settings: file not found."));
                } catch (IOException e) {
                    LOG.log(new Status(
                            IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "Error while parsing settings: file could not be read."));
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
     * Reads settings from KSBasE storage files and initializes user defined
     * editors and transformations.
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
                        LOG.log(new Status(
                                IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings: file not found."));
                    } catch (IOException e) {
                        LOG.log(new Status(
                                IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings: file not found."));
                    } catch (ClassNotFoundException e) {
                        LOG.log(new Status(
                                IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings."));
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
        isInitialized = true;
    }

    /**
     * Reads all existing extensions of the KSBasE extension point and
     * initializes the editors and transformations.
     */
    private void initializeExtensionPoints() {
        activeEditors = new HashMap<String, EditorTransformationSettings>();

        // From extension points first:
        IConfigurationElement[] configurations =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        "de.cau.cs.kieler.ksbase.configurations");
        if (configurations == null) {
            LOG.log(new Status(
                    IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                    "Invalid KSBasE extension point found."));
            return;
        }
        for (IConfigurationElement settings : configurations) {
            // Check for valid Configuration:
            if (settings.getAttribute("editorId") == null
                    || settings.getAttribute("packageName") == null) {
                LOG.log(new Status(
                        IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                        "Invalid KSBasE extension point found."));
                continue;
            }

            EditorTransformationSettings editor =
                    new EditorTransformationSettings(settings.getAttribute("editorId"));
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
                    if (t == null
                            || t.getAttribute("name") == null
                            || t.getAttribute("transformation") == null
                            || t.getAttribute("transformationId") == null) {
                        LOG.log(new Status(
                                IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Invalid KSBasE configuration found. Please check "
                                        + "transformations defined for " + editor.getEditorId()));
                    }
                    Transformation transformation =
                            new Transformation(t.getAttribute("name"), t
                                    .getAttribute("transformation"));
                    transformation.setKeyboardShortcut(t.getAttribute("keyboardShortcut"));
                    transformation.setTransformationId(t.getAttribute("transformationId"));
                    transformation.setIcon(t.getAttribute("icon"));
                    editor.addTransformation(transformation);
                }
            }
            // Read menu contributions
            IConfigurationElement[] menus = settings.getChildren("menus");

            if (menus != null && menus.length > 0) {
                // since we only allowed one single <menuContribution> child, we
                // are using it w/o iteration
                for (IConfigurationElement c : menus[0].getChildren("menuContribution")) {
                    KSBasEMenuContribution contrib =
                            new KSBasEMenuContribution(c.getAttribute("locationURI"));
                    for (IConfigurationElement m : c.getChildren("menu")) {
                        KSBasEMenuContribution menu =
                                new KSBasEMenuContribution(m.getAttribute("id"));
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

            // Read Xtend file from extension point configuration
            InputStream path;
            StringBuffer contentBuffer = new StringBuffer();
            try {
                if (settings.getContributor() != null && settings.getAttribute("XtendFile") != null) {
                    Bundle bundle = Platform.getBundle(settings.getContributor().getName());
                    if (bundle != null) {
                        URL urlPath = bundle.getEntry("/" + settings.getAttribute("XtendFile"));
                        // Parse xtend file to read transformations and
                        // parameters now:
                        editor.parseTransformations(false, urlPath);
                        if (urlPath != null) {
                            path = urlPath.openStream();
                            while (path.available() > 0) {
                                contentBuffer.append((char) path.read());

                            }
                        }
                    }
                    if (contentBuffer != null) {
                        editor.setExtFile(contentBuffer.toString());
                    }
                }
            } catch (IOException e) {
                LOG.log(new Status(
                        IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                        "KSBasE configuration exception: Can't read Xtend file."));
            }

            activeEditors.put(editor.getEditorId(), editor);
        }
    }

    /**
     * Loads the editor settings either from the extension point settings and
     * the preference store.
     */
    public void initializeTransformations() {
        initializeExtensionPoints();
        initalizeUserSettings();
    }
}
